/**  
*    文件名:  CodeUtil.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月15日  
*/  
package cn.zty.o2o.util;

import javax.servlet.http.HttpServletRequest;

/**
*  类描述: 判断用户输入的验证码是否正确
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月15日 下午4:07:56
*/


public class CodeUtil {
	
	/**
	 * 检查验证码是否和预期相符
	 * 
	 * @param request
	 * @return
	 */
	public static boolean checkVerifyCode(HttpServletRequest request) {
		String verifyCodeExpected = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
		if (verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExpected)) {
			return false;
		}
		return true;
	}

}
