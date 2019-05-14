/**  
*    文件名:  ShopDao.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月13日  
*/
package cn.zty.o2o.dao;
/**
*  类描述: 店铺 Shop 实体类之 Dao层接口
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月13日 上午10:13:49
*/

import cn.zty.o2o.entity.Shop;


public interface ShopDao {

	/**
	*  方法描述: 新增店铺
	*  @param shop
	*  @return 
	*  @since 2019年5月13日 
	*/
	public int insertShop(Shop shop);

	/**
	*   方法描述: 更新店铺信息
	*  @param shop
	*  @return
	*  @since 2019年5月13日 
	*/
	public int updateShop(Shop shop);
	
	
	

}
