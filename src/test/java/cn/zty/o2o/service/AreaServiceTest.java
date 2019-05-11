/**  
*    文件名:  AreaServiceTest.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月9日  
*/  
package cn.zty.o2o.service;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import cn.zty.o2o.BaseTest;
import cn.zty.o2o.entity.Area;

/**
*    类描述: 
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月9日 下午7:34:18
*/

public class AreaServiceTest extends BaseTest {
	
    @Autowired
    private AreaService areaService;
    
    @Test
    public void getAreaListTest() {
    	List<Area> areaList = areaService.getAreaList();
    	assertEquals("西苑", areaList.get(0).getAreaName());
    	//assertEquals(2, areaList.size());
    }
}
