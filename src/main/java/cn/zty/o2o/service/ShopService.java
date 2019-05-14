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

import java.io.File;

import cn.zty.o2o.dto.ShopExecution;
import cn.zty.o2o.entity.Shop;

public interface ShopService {
	
	ShopExecution addShop(Shop shop,File shopImg);
    
	
}
