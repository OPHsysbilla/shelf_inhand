package sid;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.spi.orbutil.fsm.Input;
import com.sun.xml.internal.ws.api.message.Attachment;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OutputStream outputStream;
		InputStream inputStream;
		String filename = (String)request.getAttribute("username");
		if (filename == null) {
			outputStream = response.getOutputStream();
			outputStream.write("Please input filename.".getBytes());
			outputStream.close();
			return;
		}
		filename = filename+"/"+filename+".zip";
		System.out.println(filename);
		//获取本地文件的输入流
		inputStream = getServletContext().getResourceAsStream("/Users/apple/Documents/Database/"+filename);
		//int length = inputStream.available();
		response.setContentType("application/force-download");
		response.setHeader("Content-Length", String.valueOf(26022));
		response.setHeader("Content-Disposition", "attachment;filename = \""+filename+"\"");
		
		outputStream = response.getOutputStream();
		int bytesRead = 0;
		byte[] buffer = new byte[512];
		while((bytesRead = inputStream.read(buffer))!=-1){
			outputStream.write(buffer, 0, bytesRead);
		}
		inputStream.close();
		outputStream.close();
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
