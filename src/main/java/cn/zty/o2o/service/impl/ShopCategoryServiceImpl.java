/**  
*    文件名:  ShopCategoryImpl.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月15日  
*/  
package cn.zty.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zty.o2o.dao.ShopCategoryDao;
import cn.zty.o2o.entity.ShopCategory;
import cn.zty.o2o.service.ShopCategoryService;

/**
*    类描述: 
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月15日 上午11:08:37
*/

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

   @Autowired
   ShopCategoryDao shopCategoryDao;
	
	@Override
	public List<ShopCategory> getShopCategory(ShopCategory shopCategory) {
		// TODO Auto-generated method stub
		return shopCategoryDao.queryShopCategory(new ShopCategory());
	}
	
	
}
