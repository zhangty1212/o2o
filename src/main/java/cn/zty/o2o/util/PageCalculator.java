/**  
*    文件名:  PageCalculator.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月21日  
*/  
package cn.zty.o2o.util;
/**
*    类描述:  calculateRowIndex 将 pageIndx 转化为 rowIndex；前端只认pageIndx（页），数据库只认 rowIndex （行）；
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月21日 下午1:48:14
*/
public class PageCalculator {
	
	public static int calculateRowIndex(int pageIndex, int pageSize) {
		return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
	}

}
