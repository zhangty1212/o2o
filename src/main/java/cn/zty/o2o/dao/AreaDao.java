/**  
*    文件名:  AreaDao.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月9日  
*/  
package cn.zty.o2o.dao;
/**
*    类描述: 
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月9日 下午6:45:49
*/

import java.util.List;
import cn.zty.o2o.entity.Area;

public interface AreaDao {
	
	// 查询所有 Area
	List<Area> queryArea();

}
