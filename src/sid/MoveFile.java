package sid;

import java.io.*;

public class MoveFile {
	public MoveFile(){
		
	}
	public void move(String resource,String target) {
		InputStream inStream = null;
        OutputStream outStream = null;

        try {

            File afile = new File(resource);
            File bfile = new File(target);

            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0) {

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            //delete the original file
            afile.delete();

            System.out.println("File is copied successful!");

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
