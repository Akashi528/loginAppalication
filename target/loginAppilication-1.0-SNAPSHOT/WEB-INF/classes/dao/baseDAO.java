package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class baseDAO {
    private static final String URL="jdbc:mysql://127.0.0.1:3306/user";
    private static final String user="root";
    private static final String password="";

    public Connection getConnection(){//连接数据库
        Connection conn=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(URL,user,password);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public void close(AutoCloseable object){//关闭数据库
        if(object!=null){
            try {
                object.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public  void executeSql(String sql,Object...objs){//用于直接执行sql语句
        Connection conn=this.getConnection();
        PreparedStatement ps=null;
        try{
            ps=conn.prepareStatement(sql);
            for(int i=0;i<objs.length;i++){
                ps.setObject(i+1,objs[i]);
            }
            ps.execute();
            System.out.println("执行"+sql+"成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            this.close(ps);
            this.close(conn);
        }
    }
}
