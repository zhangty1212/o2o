/**  
*    文件名:  ShopAdminController.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月14日  
*/  
package cn.zty.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
*   类描述: 
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月14日 下午5:02:36
*/

@Controller
@RequestMapping("/shopadmin")
public class ShopAdminController {
	@RequestMapping("/shopoperation")
	public String shopOperation() {

		// spring-web.xml 中的视图解析器 做了前后缀处理
		// <property name="prefix" value="/WEB-INF/html/"></property>
		// <property name="suffix" value=".html"></property>
		return "shop/shopoperation";
	}
}
