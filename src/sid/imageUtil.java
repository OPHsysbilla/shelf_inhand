//封装图片输入输出
package sid;
import java.io.*;
public class imageUtil {
	//读取本地图片获取输入流
	public static FileInputStream readImage(String path) throws IOException{
			return new FileInputStream(new File(path));
	}
	// 读取表中图片获取输出流
    public static void readBin2Image(InputStream in, String targetPath) {
        File file = new File(targetPath);
        String path = targetPath.substring(0, targetPath.lastIndexOf("/"));
        if (!file.exists()) {
            new File(path).mkdir();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[in.available()];
            System.out.println(in.available());
            while ((len = in.read(buf)) != -1) {
            	System.out.println(len);
                fos.write(buf, 0, len);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
