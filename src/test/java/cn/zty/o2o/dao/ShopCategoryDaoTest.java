/**  
*    文件名:  ShopCategoryDaoTest.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月14日  
*/  
package cn.zty.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.zty.o2o.BaseTest;
import cn.zty.o2o.entity.ShopCategory;

/**
*    类描述: 
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月14日 下午8:25:27
*/

public class ShopCategoryDaoTest extends BaseTest {
	
	@Autowired 
	ShopCategoryDao shopCategoryDao;
	
	@Test
	public void queryShopCategoryTest() {
		
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
		System.out.println("--------------:" + new ShopCategory());
		assertEquals(1, shopCategoryList.size());
		
		ShopCategory parentShopCategory=new ShopCategory();
		ShopCategory childShopCategory=new ShopCategory();
		parentShopCategory.setShopCategoryId(36L);
		childShopCategory.setParent(parentShopCategory);
		shopCategoryList=shopCategoryDao.queryShopCategory(childShopCategory);
		assertEquals(1, shopCategoryList.size());
		System.out.println(shopCategoryList.get(0).getShopCategoryName());
	}

}
