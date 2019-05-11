/**  
*    文件名:  BaseTest.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月9日  
*/  
package cn.zty.o2o;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
*    类描述:  BaseTest 用来配置 spring 和 junit 整合，junit 启动时加载 springIOC 容器
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月9日 下午7:09:01
*/

//表示继承了SpringJUnit4ClassRunner类，告诉 junit spring 用什么类跑单元测试；
@RunWith(SpringJUnit4ClassRunner.class)	

// 告诉junit spring 配置文件的位置
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml","classpath:spring/spring-web.xml"})

// @ WebAppConfiguration(“src/main/resources”)，指定配置文件根目录所在路径;
// 指定根目录后，再从根目录往下，找到你的配置文件： @ContextConfiguration({ “classpath:spring/spring-dao.xml”,"classpath:spring/spring-service.xml","classpath:spring/spring-web.xml"})
@WebAppConfiguration("src/main/resources") 

public class BaseTest {

	
}
