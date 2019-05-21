/**  
*    文件名:  ImageUtil.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月13日  
*/  
package cn.zty.o2o.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
*  类描述: 
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月13日 下午3:20:54
*/


public class ImageUtil {
	
	public static String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
	public static final SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss"); //yyyy-MM-dd hh:mm:ss
	public static final Random r=new Random();
	private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
	
	
	/**
	*  方法描述: 将 CommonsMultipartFile 转变为 File
	*  @param cFile
	*  @return
	*  @since 2019年5月13日 
	*/
	public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile){
		
		File newFile= new File(cFile.getOriginalFilename());
		try {
			cFile.transferTo(newFile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			e.printStackTrace();
		}
		return newFile;
	}
	
	/**
	*  方法描述: 生成缩略图,并返回新生成图片的相对路径值；
	*  @param thumbnail
	*  @param targetAddr
	*  @return
	*  @since 2019年5月13日 
	*/
	
	         
	public static String generateThumbnail(InputStream thumbnailInputStream,String fileName,String targetAddr) {   // generateThumbnail(CommonsMultipartFile thumbnail,String targetAddr)
		
		String realFileName = getRandomFileName();
		String extension=getFileExtension(fileName);
		makeDirPath(targetAddr);
		String relativeAddr= targetAddr + realFileName + extension;
		logger.debug("current relativeAddr is: " + relativeAddr);
		File dest= new File(PathUtil.getImageBasePath()+ relativeAddr);
		logger.debug("current complete addr is: " + PathUtil.getImageBasePath() + relativeAddr);
		logger.debug("basePath: "+ basePath);
		
		try {
			Thumbnails.of(thumbnailInputStream).size(300, 300)
			.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(PathUtil.getImageBasePath() + "\\watermark.png")), 0.35f) //new File(basePath + "watermark.png" 为何报错？
			.outputQuality(0.8f)
			.toFile(dest);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			e.printStackTrace();
		}
		// 返回图片相对地址
		// 返回相对地址，而不是绝对地址，是为了更好的解耦，跨平台运作
		return relativeAddr;
	}
	
	

	/**
	*  方法描述: 创建目标路径所涉及到的目录，即 /home/work/xiangzai/xxx.jpg,
	*  那么 home work xiangzai 这三个文件夹都得自动创建；
	*  @param targetAddr
	*  @since 2019年5月13日 
	*/
	private static void makeDirPath(String targetAddr) {
		// TODO Auto-generated method stub
		
		// 获取项目全路径
		String realFileParentPath = PathUtil.getImageBasePath() + targetAddr;
		
		File dirPath= new File(realFileParentPath);
		if(!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/**
	*  方法描述: 获取输入文件流（图片）的扩展名
	*  @param fileName
	*  @return
	*  @since 2019年5月13日 
	*/
	private static String getFileExtension(String fileName) {
		// TODO Auto-generated method stub
		
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	*  方法描述: 生成随机文件名，当前年月日时分秒 + 五位随机数
	*  @return
	*  @since 2019年5月13日 
	*/
	public static String getRandomFileName( ) {
		// TODO Auto-generated method stub
		
		// 获取随机的五位数
		int rannum=r.nextInt(89999)+10000;
	//	System.out.println("------- new Date(): " + new Date());
		String nowTimeStr = simpleDateFormat.format(new Date());
		return nowTimeStr+rannum;
	}
	
	
	/**
	 * storePath是文件的路径还是目录的路径，
	 * 如果storePath是文件路径则删除该文件，
	 * 如果storePath是目录路径则删除该目录下的所有文件
	 * @param storePath
	 */
	public static void deleteFileOrPath(String storePath) {
		File fileOrPath = new File(PathUtil.getImageBasePath() + storePath);
		if (fileOrPath.exists()) {
			if (fileOrPath.isDirectory()) {
				File files[] = fileOrPath.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			fileOrPath.delete();
		}
	}

	public static void main(String[] args) throws IOException {
		
		String basePath= Thread.currentThread().getContextClassLoader().getResource("").getPath();
		System.out.println("---------bashPath: "  + basePath);

		Thumbnails.of(new File("C:/Users/25472/Desktop/o2o/image/paopao.png"))
		.size(300, 300)
		.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.png")), 0.35f)
		.outputQuality(0.8f)
		.toFile("C:/Users/25472/Desktop/o2o/image/paopaonew.png");
		
	}
	
}
