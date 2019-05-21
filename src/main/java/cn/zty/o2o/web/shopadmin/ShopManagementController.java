/**  
*    文件名:  ShopManagementController.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月14日  
*/
package cn.zty.o2o.web.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;


import cn.zty.o2o.dto.ShopExecution;
import cn.zty.o2o.entity.Area;
import cn.zty.o2o.entity.PersonInfo;
import cn.zty.o2o.entity.Shop;
import cn.zty.o2o.entity.ShopCategory;
import cn.zty.o2o.enums.ShopStateEnum;
import cn.zty.o2o.exceptions.ShopOperationException;
import cn.zty.o2o.service.AreaService;
import cn.zty.o2o.service.ShopCategoryService;
import cn.zty.o2o.service.ShopService;
import cn.zty.o2o.util.CodeUtil;
import cn.zty.o2o.util.HttpServletRequestUtil;

/**
 * 类描述:
 * 
 * @author 张涛一 ,2547260515@qq.com
 * @since 2019年5月14日 上午9:49:28
 */

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {

	@Autowired
	private ShopService shopService;
	
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping("/getshopbyid")
	@ResponseBody
	private Map<String, Object> getShopById(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Long  shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if(shopId > -1) {
			try {
				Shop shop= shopService.getByShopId(shopId);
				List<Area> areaList= areaService.getAreaList();
				modelMap.put("shop", shop);
				modelMap.put("areaList", areaList);
				modelMap.put("success", true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
			
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty shopId");
		}
		return modelMap;
	}
	
	@RequestMapping("/getshopinitinfo")
	@ResponseBody
	private Map<String, Object> getShopInitInfo(){
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ShopCategory> shopCategoryList= new ArrayList<ShopCategory>();
		List<Area> areaList=new ArrayList<Area>();
		try {
			shopCategoryList=shopCategoryService.getShopCategory(new ShopCategory());
			areaList=areaService.getAreaList();
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;	
	}
	
	@RequestMapping("/registershop")
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request) {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码输入错误");
			return modelMap;
		}
		// 1. 接受并转化前端相应的参数，包括店铺信息以及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr"); // 前端传过来 "shopStr"
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}

		// 接收图片，使用spring自带的 CommonsMultipartFile
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());

		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		} else { // 如果不要求图片强制上传，将 else 部分删除即可；
			modelMap.put("success", false);
			modelMap.put("errMsg", "长传图片不能为空");
			return modelMap;
		}

		// 2. 注册店铺
		if (shop != null && shopImg != null) {
			// owner 信息可以通过 Session 获取，无需再从前端获取；
			/*PersonInfo owner = new PersonInfo();
			owner.setUserId(9L);
			shop.setOwner(owner);	*/	
			
			// 从 session 里面获取用户登录信息
			@SuppressWarnings("unused")
			PersonInfo	owner = (PersonInfo) request.getSession().getAttribute("user");	
			ShopExecution sExecution;
			
			try {
				sExecution = shopService.addShop(shop, shopImg.getInputStream(),shopImg.getOriginalFilename());	
				if (sExecution.getState() == ShopStateEnum.CHECK.getState()) {
					modelMap.put("success", true);
					
					// 该用户可以操作的店铺列表
					@SuppressWarnings("unchecked")
					List<Shop> shopList= (List<Shop>)request.getSession().getAttribute("shopList");
					if(shopList == null || shopList.size()==0) {	
						shopList = new ArrayList<Shop>();
					}
					shopList.add(sExecution.getShop());
					request.getSession().setAttribute("shopList", shopList);	
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", sExecution.getStateInfo());
				}		
			}catch (ShopOperationException e) {
				// TODO Auto-generated catch block
				modelMap.put("success", false);
				modelMap.put("errMsg",e.getMessage());
			}catch (IOException e) {
				// TODO Auto-generated catch block
				modelMap.put("success", false);
				modelMap.put("errMsg",e.getMessage());
			}	
			return modelMap;

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
	}
	
	
	@RequestMapping("/modifyshop")
	@ResponseBody
	private Map<String, Object> modifyShop(HttpServletRequest request) {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码输入错误");
			return modelMap;
		}
		// 1. 接受并转化前端相应的参数，包括店铺信息以及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr"); // 前端传过来 "shopStr"
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}

		// 接收图片，使用spring自带的 CommonsMultipartFile
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());

		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		} 

		// 2. 修改店铺信息
		if (shop != null && shop.getShopId() !=null) {
			ShopExecution sExecution;
			try {
				if (shopImg == null) {
					sExecution=shopService.modifyShop(shop, null, null);
				}else {
					sExecution = shopService.modifyShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());	
				}	
				if (sExecution.getState() == ShopStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", sExecution.getStateInfo());
				}		
			}catch (ShopOperationException e) {
				// TODO Auto-generated catch block
				modelMap.put("success", false);
				modelMap.put("errMsg",e.getMessage());
			}catch (IOException e) {
				// TODO Auto-generated catch block
				modelMap.put("success", false);
				modelMap.put("errMsg",e.getMessage());
			}	
			return modelMap;

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺id");
			return modelMap;
		}
	}
}
