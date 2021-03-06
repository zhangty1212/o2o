/**  
*    文件名:  ShopDaoTest.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月13日  
*/  
package cn.zty.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.zty.o2o.BaseTest;
import cn.zty.o2o.entity.Area;
import cn.zty.o2o.entity.PersonInfo;
import cn.zty.o2o.entity.Shop;
import cn.zty.o2o.entity.ShopCategory;

/**
*  类描述: 
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月13日 上午11:05:10
*/
public class ShopDaoTest extends BaseTest {
	
	@Autowired
	private ShopDao shopDao;
	
	@Test
	@Ignore
    public void insertShopTest(){
		
    Shop shop= new Shop();
    
    PersonInfo owner= new PersonInfo();
    Area area= new Area();
    ShopCategory shopCategory=new ShopCategory();
    owner.setUserId(9L);
    area.setAreaId(1);
    shopCategory.setShopCategoryId(36L);
    
    shop.setArea(area);
    shop.setOwner(owner);
    shop.setShopCategory(shopCategory);		
    shop.setShopName("小 one 之家 2");
    shop.setShopAddr("深圳 南山 西乡");
    shop.setShopDesc("商店描述 2");
    shop.setShopImg("店铺图片 2");
    shop.setPhone("13662247086");
    shop.setPriority(2);
    shop.setCreateTime(new Date());
    shop.setEnableStatus(0);
    shop.setAdvice("审核中");
  
    int effectedNum=shopDao.insertShop(shop);
    assertEquals(1, effectedNum);
    
	}
	
	@Test
	@Ignore
    public void updateShopTest(){
		
    Shop shop= new Shop();
    shop.setShopId(37L);
    
    Area area= new Area();
    ShopCategory shopCategory=new ShopCategory();
    area.setAreaId(1);
    shopCategory.setShopCategoryId(36L);
    
    shop.setArea(area);
    shop.setShopCategory(shopCategory);		
    shop.setShopName("小 one 之家 2 更新");
    shop.setShopAddr("深圳 南山 西乡");
    shop.setShopDesc("测试商店信息更新");
    shop.setShopImg("店铺图片 2");
    shop.setPhone("13430592927");
    shop.setPriority(2);
    shop.setLastEditTime(new Date());
    shop.setEnableStatus(0);
    shop.setAdvice("审核中");
  
    int effectedNum=shopDao.updateShop(shop);
    assertEquals(1, effectedNum);
    
	}
	
	@Test
	@Ignore
	public void queryByShopIdTest() {

		long shopId = 36L;
		Shop shop = shopDao.queryByShopId(shopId);

		System.out.println(shop.getArea().getAreaName());
		System.out.println(shop.getShopCategory().getShopCategoryName());
		System.out.println(shop.getOwner().getName());
	}
	
	
	@Test
	@Ignore
	public void queryShopCountTest() {
		
		Shop shoCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(10L);
		shoCondition.setOwner(owner);
		int con = shopDao.queryShopCount(shoCondition);
		System.out.println("con: " + con);	
	}
	
	@Test
	public void queryShopListTest() {
		
		Shop shop = new Shop();
		List<Shop> shopList;
		shopList=shopDao.queryShopList(shop,0, 5);
		System.out.println("shopList: " + shopList.size());
		for (Shop shop2 : shopList) {
			System.out.println(shop2);
			System.out.println("\n");
		}
	}
}
