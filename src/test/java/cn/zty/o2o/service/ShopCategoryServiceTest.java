/**  
*    文件名:  ShopCategoryServiceTest.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月15日  
*/  
package cn.zty.o2o.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.zty.o2o.BaseTest;
import cn.zty.o2o.entity.ShopCategory;

/**
*  类描述: 
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月15日 上午11:12:37
*/


public class ShopCategoryServiceTest extends BaseTest{
	
	@Autowired
	ShopCategoryService shopCategoryService;

	@Test
	public void getShopCategoryTest() {

		List<ShopCategory> listShopCategory = shopCategoryService.getShopCategory(new ShopCategory());
		System.out.println(listShopCategory.size());
		System.out.println("--------------------------------");
		for (ShopCategory shopCategory : listShopCategory) {
			System.out.println(shopCategory);
			System.out.println("\n");
		}
	}

}
