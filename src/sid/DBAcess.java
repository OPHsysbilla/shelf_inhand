package sid;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.sql.*;

import org.omg.CORBA.CTX_RESTRICT_SCOPE;

import javax.activation.DataSource;
import javax.naming.*;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import com.sun.xml.internal.bind.CycleRecoverable.Context;

import sun.launcher.resources.launcher;

public class DBAcess {
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/user_information?characterEncoding=utf-8";
	private String username = "root";
	private String password = "741316636jzy";
	private Connection conn;
	private Statement stm;
	private ResultSet rs;
	//创建连接
	public boolean createConn() {
		boolean b = false;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			b = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 获取连接
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	//修改
	public boolean update(String sql){
		boolean b = false;
		try {
			stm = conn.createStatement();
			stm.execute(sql);
			b = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	//查询
	public void query(String sql){
		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//判断有无数据
	public boolean next(){
		boolean b = false;
		try {
			if(rs.next()){
				b = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	//获取表字段值
	public String getValue(String field) {
		String value = null;
		try {
			if (rs != null) {
				value = rs.getString(field);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	public boolean upload(String sql,String path) {
		boolean b = false;
		PreparedStatement ps = null;
        
		try {
			ps = conn.prepareStatement(sql);
			int count = ps.executeUpdate();
			if (count > 0) {
				b = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	public void download(String username) {
		String targetPath = "/Users/apple/Documents/Java/5.jpg";
        PreparedStatement ps = null;
        ResultSet rs = null;
		try {
			try {
				String sql = "select * from user_photo where user_name =?";
	            ps = conn.prepareStatement(sql);
	            ps.setString(1, username);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                InputStream in = rs.getBinaryStream("photo");
	                System.out.println(in.available());
	                imageUtil.readBin2Image(in, targetPath);
	            }
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//关闭连接
	public void closeConn() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//关闭statement
	public void closeStm() {
		try {
			if (stm != null) {
				stm.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//关闭ResultSet
	public void closeRs() {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Statement getStm() {
		return stm;
	}
	public void setStm(Statement stm) {
		this.stm = stm;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public Connection getConn() {
		return conn;
	}
}
