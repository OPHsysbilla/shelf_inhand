package sid;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.bind.CycleRecoverable.Context;

import sid.UserBean;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2779036201589069250L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String method = request.getParameter("method");
		if("login".equals(method)){//登录
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			 if(username==null||"".equals(username.trim())||password==null||"".equals(password.trim())){
				  System.out.println("用户名或密码不能为空！");
				  response.sendRedirect("login.jsp");
				  return;
			  }
			 UserBean userBean = new UserBean();
			  boolean isValid = userBean.valid(username,password);
			  if(isValid){
				  session.setAttribute("username", username);
				  System.out.println("登录成功！");
				  request.getSession().setAttribute("username", username);
				  response.sendRedirect("welcome.jsp");
				  return;
			  }else{
				  System.out.println("用户名或密码错误，登录失败！");
				  response.sendRedirect("login.jsp");
				  return;
			  }
		}else if("logout".equals(method)){//退出登录
			System.out.println("退出登录！");
			request.getSession().removeAttribute("username");
			response.sendRedirect("login.jsp");
			return;
		}else if("register".equals(method)){//注册
			 String username = request.getParameter("username");
			  String password1 = request.getParameter("password1");
			  String password2 = request.getParameter("password2");
			  String email = request.getParameter("email");
			  if(username==null||"".equals(username.trim())||password1==null||"".equals(password1.trim())||password2==null||"".equals(password2.trim())||!password1.equals(password2)){
				  System.out.println("用户名或密码不能为空！");
				  response.sendRedirect("register.jsp");
				  return;
			  }
			  UserBean userBean = new UserBean();
			  boolean isExit = userBean.isExist(username);
			  if(!isExit){
				  userBean.add(username, password1, email);
				  System.out.println("注册成功，请登录！");
				  response.sendRedirect("login.jsp");
				  return;
			  }else{
				  System.out.println("用户名已存在！");
				  response.sendRedirect("register.jsp");
				  return;
			  }
		}
		else if ("upload".equals(method)) {
			String username = (String) session.getAttribute("username");
			String path = (String) session.getAttribute("path");
			if (path == null||"".equals(path.trim())) {
				System.out.println("上传路径不能为空");
				response.sendRedirect("upload.jsp");
				return;
			}
			UserBean userBean = new UserBean();
			boolean isValid = userBean.readImage2DB(username,path);
			if (isValid) {
				System.out.println("图片上传成功");
			}
			else{
				System.out.println("图片上传失败");
			}
			MyZipCompressing myZipCompressing = new MyZipCompressing();
			try {
				myZipCompressing.compressedFile("/Users/apple/Library/Tomcat/webapps/shelf_inhand/"+username, "/Users/apple/Library/Tomcat/webapps/shelf_inhand/"+username);
				System.out.println("压缩文件已经生成...");
				MoveFile moveFile = new MoveFile();
				moveFile.move("/Users/apple/Library/Tomcat/webapps/shelf_inhand/"+username+".zip","/Users/apple/Library/Tomcat/webapps/shelf_inhand/"+username+"/"+username+".zip");
			} catch (Exception e) {
				System.out.println("压缩文件生成失败...");
				e.printStackTrace();
			}
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/welcome.jsp");
			dispatcher.forward(request, response);
		}
		else if("download".equals(method)){
			String username = (String) session.getAttribute("username");
			//UserBean userBean = new UserBean();
			//userBean.readDB2Image(username);
			Download download = new Download();
			String urlPath = "http://localhost:8080/shelf_inhand/"+username+"/"+username+".zip";   
	        String filePath = "/Users/apple/Documents/Java";   
	        URL url = new URL(urlPath);   
	          try {   
	              download.downloadFile(url,filePath,username);   
	           } catch (IOException e) {   
	            e.printStackTrace();   
	         }   
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/welcome.jsp");
			dispatcher.forward(request, response);
		}
	}

}
