/**  
*    文件名:  ShopServiceImpl.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月13日  
*/  
package cn.zty.o2o.service.impl;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zty.o2o.dao.ShopDao;
import cn.zty.o2o.dto.ShopExecution;
import cn.zty.o2o.entity.Shop;
import cn.zty.o2o.enums.ShopStateEnum;
import cn.zty.o2o.exceptions.ShopOperationException;
import cn.zty.o2o.service.ShopService;
import cn.zty.o2o.util.ImageUtil;
import cn.zty.o2o.util.PathUtil;

/**
*    类描述: 
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月13日 下午7:53:29
*/

@Service
public class ShopServiceImpl  implements ShopService{

	@Autowired
	ShopDao shopDao;
	
	@Override
	public ShopExecution addShop(Shop shop, File shopImg) {
		// TODO Auto-generated method stub
		
		// 空值判断
		if(shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		
		// 4步操作
		// 异常为何要抛出 ShopOperationException 异常，而不是 Exception？
		// 答： ShopOperationException 异常事务可以终止回滚，Exception 异常事务无法回滚；
		
		try {
			// 给店铺信息赋初始值 (外部不能改变的值)
			shop.setEnableStatus(0); //  0 - check 状态
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());	
			
			// 添加店铺信息
			int effectedNum = shopDao.insertShop(shop);
			
			if(effectedNum <=0) {
				throw new ShopOperationException("店铺创建失败！");
			}else {
				if(shopImg != null) {
					// 存储图片
					try {
						addShopImg(shop, shopImg);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						throw new ShopOperationException("addShopImg error: " + e.getMessage());
					}
				}
				// 更新店铺的图片地址
				effectedNum = shopDao.updateShop(shop);
				if(effectedNum <=0) {
					throw new ShopOperationException("更新图片地址失败！ ");
				}			
			}			
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new ShopOperationException("addShop error: " + e.getMessage());
		}		
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	/**
	*  方法描述: 
	*  @param shop
	*  @param shopImg
	*  @since 2019年5月13日 
	*/
	private void addShopImg(Shop shop, File shopImg) {
		// TODO Auto-generated method stub
		
		// 获取shop图片目录的相对路径值
		String dest= PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr= ImageUtil.generateThumbnail(shopImg, dest);	
		shop.setShopImg(shopImgAddr);
	}
}