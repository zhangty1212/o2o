/**  
*    文件名:  ShopService.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月13日  
*/  
package cn.zty.o2o.service;
/**
*    类描述: 
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月13日 下午7:50:14
*/

import java.io.InputStream;

import cn.zty.o2o.dto.ShopExecution;
import cn.zty.o2o.entity.Shop;
import cn.zty.o2o.exceptions.ShopOperationException;

public interface ShopService {
	
	/**
	*    方法描述:  注册店铺信息，包括对图片的处理
	*  @param shop
	*  @param shopImgInputStream
	*  @param fileName
	*  @return
	*  @since 2019年5月20日 
	*/
	public ShopExecution addShop(Shop shop,InputStream shopImgInputStream,String fileName) throws ShopOperationException;
	
	/**
	*    方法描述:  通过 shopId 查询店铺
	*  @param shopId
	*  @return
	*  @since 2019年5月20日 
	*/
	public Shop getByShopId(long shopId);
	
	/**
	*    方法描述: 更新店铺信息，包括对图片的处理
	*  @param shop
	*  @param shopImgInputStream
	*  @param fileName
	*  @return
	*  @since 2019年5月20日 
	*/
	public ShopExecution modifyShop(Shop shop,InputStream shopImgInputStream,String fileName) throws ShopOperationException;
	
	
	/**
	*    方法描述:  根据 shopCondition 分页返回相应店铺列表
	*  @param shopCondition
	*  @param rowIndex
	*  @param pageSize
	*  @return
	*  @since 2019年5月21日 
	*/
	public ShopExecution getShopList(Shop shopCondition,  int pageIndex, int pageSize);
	
	
}
