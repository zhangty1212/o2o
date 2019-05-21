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

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	
	/**
	*    方法描述:  通过 shopId 查询店铺
	*  @param shopId
	*  @return
	*  @since 2019年5月20日 
	*/
	Shop queryByShopId(long shopId);
	

	/**
	 * 分页查询店铺，可输入的条件有：店铺名(模糊),店铺状态，店铺类别，区域Id,owner
	 * 
	 * @param shopCondition
	 * @param rowIndex      从第几行开始取数据
	 * @param pageSize      返回的条数
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);
	
	/**
	 * 返回queryShopList总数
	 * 
	 * @param shopCondition
	 * @return
	 */
	int queryShopCount(@Param("shopCondition") Shop shopCondition);

}
