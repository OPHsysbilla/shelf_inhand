package sid;
import java.sql.*;
import java.io.FileInputStream;
import java.io.InputStream;


public class ImageIO {

    // 将图片插入数据库
    public static void readImage2DB() {
        String path = "/Users/apple/Documents/Java/1.jpg";
        Connection connection = null;
        PreparedStatement ps = null;
        FileInputStream in = null;
        try {
            in = imageUtil.readImage(path);
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_information","root","741316636jzy");
            String sql = "insert into user_photo (user_name,photo)values(?,?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, 123412);
            ps.setBinaryStream(2, in, in.available());
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("插入成功！");
            } else {
                System.out.println("插入失败！");
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    // 读取数据库中图片
    public static void readDB2Image() {
        String targetPath = "/Users/apple/Documents/Java/2.jpg";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_information","root","741316636jzy");
            String sql = "select * from user_photo where user_name =?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, 123412);
            rs = ps.executeQuery();
            while (rs.next()) {
                InputStream in = rs.getBinaryStream("photo");
                imageUtil.readBin2Image(in, targetPath);
            }
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
    }
    public static void eraseImage(){
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
    }
 }