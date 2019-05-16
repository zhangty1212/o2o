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
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.zty.o2o.BaseTest;
import cn.zty.o2o.dto.ShopExecution;
import cn.zty.o2o.entity.Area;
import cn.zty.o2o.entity.PersonInfo;
import cn.zty.o2o.entity.Shop;
import cn.zty.o2o.entity.ShopCategory;
import cn.zty.o2o.enums.ShopStateEnum;

/**
*  类描述: 
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月13日 下午8:42:28
*/

public class ShopServiceTest extends BaseTest{
	
	@Autowired
	ShopService shopService;
	
	@Test
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
}
