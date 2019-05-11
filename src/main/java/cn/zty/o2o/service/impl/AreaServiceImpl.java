/**  
*    文件名:  AreaServiceImpl.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月9日  
*/  
package cn.zty.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zty.o2o.dao.AreaDao;
import cn.zty.o2o.entity.Area;
import cn.zty.o2o.service.AreaService;

/**
*    类描述: 
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月9日 下午7:30:30
*/

@Service
public class AreaServiceImpl implements AreaService {
	
	@Autowired
	private AreaDao areaDao;

	@Override
	public List<Area> getAreaList() {
		// TODO Auto-generated method stub
		return areaDao.queryArea();
	}
	
}
