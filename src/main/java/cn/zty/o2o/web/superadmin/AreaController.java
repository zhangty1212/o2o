/**  
*    文件名:  AreaController.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月10日  
*/  
package cn.zty.o2o.web.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zty.o2o.entity.Area;
import cn.zty.o2o.service.AreaService;

/**
*    类描述: 
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月10日 上午9:15:44
*/

@Controller
@RequestMapping("/superadmin")
public class AreaController {
	
    Logger logger= LoggerFactory.getLogger(AreaController.class);
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value="/listarea")
	@ResponseBody
	private Map<String, Object> listArea() {
		
        logger.info("=== listArea() start===");
        long startTime=System.currentTimeMillis();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<Area> list = new ArrayList<Area>();

		try {
			list = areaService.getAreaList();
			// 因为前端框架 eazyUI 分页控件用到的是 rows 和 total，因此这里的 key 值写了 rows 和 total;
			modelMap.put("rows", list);
			modelMap.put("total", list.size());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		logger.error("test error!");
		long endTime=System.currentTimeMillis();
		logger.debug("costTime:[{}ms]",endTime - startTime);
		logger.info("===listArea() end===");
		return modelMap;
	}

}
