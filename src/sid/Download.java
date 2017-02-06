package sid;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
public class Download {
	public Download(){
		
	}
	public static void downloadFile(URL theURL, String filePath,String username) throws IOException {  
	     File dirFile = new File(filePath);
	        if(!dirFile.exists()){//文件路径不存在时，自动创建目录
	          dirFile.mkdir();
	        }
	   //从服务器上获取文件并保存
	      URLConnection  connection = theURL.openConnection();
	      InputStream in = connection.getInputStream();  
	      FileOutputStream os = new FileOutputStream(filePath+"/"+username+".zip"); 
	      byte[] buffer = new byte[4 * 1024];  
	      int read;  
	      while ((read = in.read(buffer)) > 0) {  
	          os.write(buffer, 0, read);  
	           }  
	        os.close();  
	        in.close();
	   }   
	     /* public static void main(String[] args) {   
	        String urlPath = "http://1.1.9.59:8089/image/123.png";   
	        String filePath = "d:\\excel";   
	        URL url = new URL(urlPath);   
	          try {   
	              downloadFile(url,filePath);   
	           } catch (IOException e) {   
	            e.printStackTrace();   
	         }   
	      }   */
}
