package sid;



import java.io.FileInputStream;
import java.io.IOException;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import sid.GenerateUUID;

public class UserBean {
	//登录验证
	public boolean valid(String username,String password){
		boolean isValid = false;
		DBAcess db = new DBAcess();
		if(db.createConn()){
			String sql = "select * from p_user where username='"+username+"' and password='"+password+"'";
			db.query(sql);
			if(db.next()){
				isValid = true;
			}
			db.closeRs();
			db.closeStm();
			db.closeConn();
		}
		return isValid;
	}
	//注册验证
	public boolean isExist(String username){
		boolean isValid = false;
		DBAcess db = new DBAcess();
		if(db.createConn()){
			String sql = "select * from p_user where username='"+username+"'";
			db.query(sql);
			if(db.next()){
				isValid = true;
			}
			db.closeRs();
			db.closeStm();
			db.closeConn();
		}
		return isValid;
	}
	//注册用户
	public boolean add(String username,String password,String email){
		boolean isValid = false;
		DBAcess db = new DBAcess();
		if(db.createConn()){
			String sql = "insert into p_user(id,username,password,email) values('"+GenerateUUID.next()+"','"+username+"','"+password+"','"+email+"')";
			isValid = db.update(sql);
			db.closeStm();
			db.closeConn();
		}
		return isValid;
	}
	// 将图片插入数据库
    public boolean readImage2DB(String username,String path) throws IOException {
    	System.out.println(path);
    	System.out.println("miaomaio");
    	boolean isValid = false;
    	DBAcess db = new DBAcess();
    	FileInputStream in = null;
    	if (db.createConn()) {
    		in = imageUtil.readImage(path);
			String sql = "insert into user_photo (user_name,photo) values('"+username+"','"+in+"')";
			isValid = db.upload(sql, path);
    		db.closeStm();
			db.closeConn();
		}
    	return isValid;
    }

    // 读取数据库中图片
    public  void readDB2Image(String username) {
    	DBAcess db = new DBAcess();
        if (db.createConn()) {
        	db.download(username);
       		db.closeStm();
			db.closeConn();
			db.closeRs();
		}
    }
    /*public static void eraseImage(){
    	Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_information","root","741316636jzy");
            String sql = "delete from user_information.user_photo where user_name = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, 123412);
            ps.executeUpdate();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }*/

}
