/**  
*    文件名:  PathUtil.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月13日  
*/  
package cn.zty.o2o.util;

import java.util.Date;

/**
*    类描述: 
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月13日 下午3:55:20
*/

public class PathUtil {

	// 获取文件分割符
	private static String seperator=System.getProperty("file.separator");

	/**
	*  方法描述: 返回项目图片根路径
	*  @return
	*  @since 2019年5月13日 
	*/
	public static String getImageBasePath() {

	//	System.out.println("------seperator: " + seperator);

		String os = System.getProperty("os.name");
	//	System.out.println("------os: " + os);

		// 项目图片存储根路径
		String basePath = "";

		if (os.toLowerCase().startsWith("win")) {
			basePath = "C:/Users/25472/Desktop/o2o/image";
		} else {
			basePath = "/home/zty";
		}

		// 注： windows 系统文件的分隔符是 反斜杠 \ ; Linux 系统文件的分隔符是 斜杠 /;
		// basePath.replace("/", seperator) 确保文件路径的有效性，无论是 windows 还是 Linux；
		// 这里，由于我们的本机使用windows 系统，return 的 basePath 经 replace 处理后，会变为 C:\Users\25472\Desktop\o2o\image
		basePath = basePath.replace("/", seperator);
		return basePath;
	}
	
	
	/**
	*  方法描述: 返回项目图片子路径
	*  @param shopId
	*  @return
	*  @since 2019年5月13日 
	*/
	public static String getShopImagePath(Long shopId) {
		
		String imagePath= "/upload/item/shop" + shopId + "/";
		return imagePath.replace("/", seperator);
	}
	
	
	public static void main(String[] args) {

		System.out.println(PathUtil.getImageBasePath());
		System.out.println("------- new Date(): " + new Date());
	}

}
