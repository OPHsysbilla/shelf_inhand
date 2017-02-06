package sid;

import java.io.*;
import java.util.zip.*;

/**
 * 程序实现了ZIP压缩。共分为2部分 ： 压缩（compression）与解压（decompression）
 * <p>
 * 大致功能包括用了多态，递归等JAVA核心技术，可以对单个文件和任意级联文件夹进行压缩和解压。 需在代码中自定义源输入路径和目标输出路径。
 * <p>
 * 在本段代码中，实现的是压缩部分；解压部分见本包中Decompression部分。
 * 
 * @author HAN
 * 
 */

public class MyZipCompressing {
	private int k = 1; // 定义递归次数变量

	public MyZipCompressing() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	/**
	 * @desc 将源文件/文件夹生成指定格式的压缩文件,格式zip
	 * @param resourePath 源文件/文件夹
	 * @param targetPath  目的压缩文件保存路径
	 * @return void
	 * @throws Exception 
	 */
	public void compressedFile(String resourcesPath,String targetPath) throws Exception{
		File resourcesFile = new File(resourcesPath);     //源文件
		File targetFile = new File(targetPath);           //目的
		System.out.println(targetFile.getPath());
		//如果目的路径不存在，则新建
		if(!targetFile.exists()){     
			targetFile.mkdirs();  
		}
		
		String targetName = resourcesFile.getName()+".zip";   //目的压缩文件名
		System.out.println(targetName);;
		FileOutputStream outputStream = new FileOutputStream(targetPath+".zip");
		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(outputStream));
		
		createCompressedFile(out, resourcesFile, "");
		
		out.close();  
	}
	
	/**
	 * @desc 生成压缩文件。
	 * 	             如果是文件夹，则使用递归，进行文件遍历、压缩
	 *       如果是文件，直接压缩
	 * @param out  输出流
	 * @param file  目标文件
	 * @return void
	 * @throws Exception 
	 */
	public void createCompressedFile(ZipOutputStream out,File file,String dir) throws Exception{
		//如果当前的是文件夹，则进行进一步处理
		if(file.isDirectory()){
			//得到文件列表信息
			File[] files = file.listFiles();
			//将文件夹添加到下一级打包目录
			out.putNextEntry(new ZipEntry(dir+"/"));
			
			dir = dir.length() == 0 ? "" : dir +"/";
			
			//循环将文件夹中的文件打包
			for(int i = 0 ; i < files.length ; i++){
				createCompressedFile(out, files[i], dir + files[i].getName());         //递归处理
			}
		}
		else{   //当前的是文件，打包处理
			//文件输入流
			FileInputStream fis = new FileInputStream(file);
			
			out.putNextEntry(new ZipEntry(dir));
			//进行写操作
			int j =  0;
			byte[] buffer = new byte[1024];
			while((j = fis.read(buffer)) > 0){
				out.write(buffer,0,j);
			}
			//关闭输入流
			fis.close();
		}
	}
	
	/*public static void main(String[] args){
		MyZipCompressing myZipCompressing = new MyZipCompressing();
		try {
			myZipCompressing.compressedFile("/Users/apple/Library/Tomcat/webapps/shelf_inhand/jzy", "/Users/apple/Library/Tomcat/webapps/shelf_inhand/jzy");
			System.out.println("压缩文件已经生成...");
			MoveFile moveFile = new MoveFile();
				moveFile.move("/Users/apple/Library/Tomcat/webapps/shelf_inhand/"+username+".zip","/Users/apple/Library/Tomcat/webapps/shelf_inhand/jzy/jzy.zip"+"/"+username+".zip");
		} catch (Exception e) {
			System.out.println("压缩文件生成失败...");
			e.printStackTrace();
		}
		
	}*/
}
