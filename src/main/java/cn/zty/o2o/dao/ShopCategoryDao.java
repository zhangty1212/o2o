/**  
*    文件名:  ShopCategoryDao.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月14日  
*/  
package cn.zty.o2o.dao;
/**
*    类描述: 
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月14日 下午8:01:30
*/

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zty.o2o.entity.ShopCategory;

public interface ShopCategoryDao {
	
	/**
	 * 根据传入的查询条件返回店铺类别列表
	 * @param shopCategoryCondition
	 * @return
	 */
	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);

}
