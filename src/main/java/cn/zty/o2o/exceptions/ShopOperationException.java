/**  
*    文件名:  ShopOperationException.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月13日  
*/  
package cn.zty.o2o.exceptions;
/**
*    类描述: 
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月13日 下午8:27:14
*/

public class ShopOperationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1757636175357889125L;

	public ShopOperationException(String msg) {
		super(msg);
	}
}
