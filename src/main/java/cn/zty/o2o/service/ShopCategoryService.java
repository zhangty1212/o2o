/**  
*    文件名:  ShopCategoryService.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月15日  
*/  
package cn.zty.o2o.service;

import java.util.List;

import cn.zty.o2o.entity.ShopCategory;

/**
*  类描述: 
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月15日 上午11:07:09
*/

public interface ShopCategoryService {
	
	 List<ShopCategory> getShopCategory(ShopCategory shopCategory);

}
