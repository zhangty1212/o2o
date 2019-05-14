/**  
*    文件名:  ShopManagementController.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月14日  
*/
package cn.zty.o2o.web.shopadmin;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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
import cn.zty.o2o.entity.PersonInfo;
import cn.zty.o2o.entity.Shop;
import cn.zty.o2o.enums.ShopStateEnum;
import cn.zty.o2o.service.ShopService;
import cn.zty.o2o.util.HttpServletRequestUtil;
import cn.zty.o2o.util.ImageUtil;
import cn.zty.o2o.util.PathUtil;

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

	@RequestMapping("/registershop")
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request) {

		Map<String, Object> modelMap = new HashMap<String, Object>();
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
			// owner 信息可以通过session 获取，无需再从前端获取；
			PersonInfo owner = new PersonInfo();
			owner.setUserId(9L);
			shop.setOwner(owner);

			File shopImgFile = new File(PathUtil.getImageBasePath() + ImageUtil.getRandomFileName());
			try {
				shopImgFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}
			try {
				inputStreamToFile(shopImg.getInputStream(), shopImgFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}
			ShopExecution sExecution = shopService.addShop(shop, shopImgFile);

			if (sExecution.getState() == ShopStateEnum.CHECK.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", sExecution.getStateInfo());
			}
			return modelMap;

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
	}

	// 将 InputStream 转换为 File
	private static void inputStreamToFile(InputStream ins, File file) {
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = ins.read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("调用inputStreamToFile产生异常" + e.getMessage());
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				if (ins != null) {
					ins.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("inputStreamToFile 关闭io 产生异常" + e.getMessage());
			}
		}
	}

}
