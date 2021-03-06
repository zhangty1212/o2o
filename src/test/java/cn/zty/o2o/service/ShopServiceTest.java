/**  
*    文件名:  ShopServiceTest.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月13日  
*/  
package cn.zty.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.zty.o2o.BaseTest;
import cn.zty.o2o.dto.ShopExecution;
import cn.zty.o2o.entity.Area;
import cn.zty.o2o.entity.PersonInfo;
import cn.zty.o2o.entity.Shop;
import cn.zty.o2o.entity.ShopCategory;
import cn.zty.o2o.enums.ShopStateEnum;
import cn.zty.o2o.exceptions.ShopOperationException;

/**
*  类描述: 
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月13日 下午8:42:28
*/

public class ShopServiceTest extends BaseTest{
	
	@Autowired
	ShopService shopService;
	
	@Test
	@Ignore
	public void addShopTest() throws FileNotFoundException {
		
		Shop shop= new Shop();
	    
	    PersonInfo owner= new PersonInfo();
	    Area area= new Area();
	    ShopCategory shopCategory=new ShopCategory();
	    owner.setUserId(10L);
	    area.setAreaId(1);
	    shopCategory.setShopCategoryId(36L);
	    
	    shop.setArea(area);
	    shop.setOwner(owner);
	    shop.setShopCategory(shopCategory);		
	    shop.setShopName("小 one 之家 6");
	    shop.setShopAddr("深圳 南山 蛇口");
	    shop.setShopDesc("ShopService 实现测试");
	    shop.setPhone("13662247086");
	    shop.setPriority(3);
	    shop.setCreateTime(new Date());
	    shop.setEnableStatus(ShopStateEnum.CHECK.getState());
	    shop.setAdvice("审核中");
	    
/*	    File shopImg = new File("C:/Users/25472/Desktop/o2o/image/paopao.png");
	    ShopExecution sExecution= shopService.addShop(shop, shopImg);  // addShop(Shop shop,InputStream shopImgInputStream,String fileName);
	    assertEquals(ShopStateEnum.CHECK.getState(), sExecution.getState());*/
	    
		String imgPath = "C:/Users/25472/Desktop/o2o/image/paopao.png";
		File file = new File(imgPath);
		String fileName = file.getName();
		FileInputStream shopImgInputStream = null;
		shopImgInputStream = new FileInputStream(imgPath);
		ShopExecution sExecution = shopService.addShop(shop, shopImgInputStream, fileName);
		assertEquals(ShopStateEnum.CHECK.getState(), sExecution.getState());
	}
	
	@Test
	@Ignore
	public void modifyShopTest() throws ShopOperationException,FileNotFoundException{
		
		Shop shop= new Shop();
		shop.setShopId(48L);
		shop.setShopName("更新店铺名称- 711");	
		
		String imgPath = "C:/Users/25472/Desktop/o2o/image/dabai.png";
		File shopImg = new File(imgPath);
		String fileName = shopImg.getName();
		InputStream inputStream=new FileInputStream(shopImg);
		
		ShopExecution shopExecution= shopService.modifyShop(shop, inputStream, fileName);
		System.out.println("新的图片地址为：" + shopExecution.getShop().getShopImg());
	}
	
	
	@Test
	public void getShopListTest() {
		
		Shop shopCondition = new Shop();
		PersonInfo personInfo=new PersonInfo();
		personInfo.setUserId(10L);
		shopCondition.setOwner(personInfo);
		ShopExecution sExecution=shopService.getShopList(shopCondition,2, 3);
		System.out.println("查询第二页，得到的店铺总数为: "+ sExecution.getShopList().size());
		System.out.println("用户id为10的用户，拥有的店铺总数为："+sExecution.getCount());
	}
	
}
