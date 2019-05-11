/**  
*    文件名:  AreaDaoTest.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月9日  
*/
package cn.zty.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.zty.o2o.BaseTest;
import cn.zty.o2o.entity.Area;

/**
  * 类描述:
 * @author 张涛一 ,2547260515@qq.com
 * @since 2019年5月9日 下午7:07:49
 */

public class AreaDaoTest extends BaseTest {

	@Autowired
	private AreaDao areaDao;

	@Test
	public void testQueryArea() {
		List<Area> areaList = areaDao.queryArea();
		assertEquals(2, areaList.size());
	}

}
