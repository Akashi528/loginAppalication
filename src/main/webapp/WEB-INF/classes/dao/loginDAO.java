package dao;

import data.User;

import javax.servlet.RequestDispatcher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginDAO extends baseDAO implements loginInterface{
    @Override
    public User find(String userID) { //查找用户密码信息
        Connection conn=this.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        User user1=new User();
        try{
            ps=conn.prepareStatement("SELECT password FROM userInfo WHERE userID=?;"); //userID作为主键 通过userid查询密码是否相等
            ps.setString(1,userID);
            rs=ps.executeQuery();
            if(rs.next()){
                user1.setUserID(userID);  //没找到用户名 密码错误
                user1.setPassWord(rs.getString("password"));
            }
            return user1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            this.close(rs);
            this.close(ps);
            this.close(conn);
        }
        return user1;
    }

    @Override
    public void save(User user) { //注册
        executeSql("INSERT INTO userInfo VALUES(?,?,?,?);",user.getUserID(),user.getPassWord(),user.getQuestion(),user.getAnswer());
    }

    @Override
    public void delete(User user)
    { //注销  找一次账户
        executeSql("DELETE FROM userInfo WHERE userID=?;",user.getUserID());
    }

    @Override
    public void change(User user) {//修改密码
        executeSql("UPDATE userInfo SET password=? WHERE userID=?",user.getPassWord(),user.getUserID());
    }

    @Override
    public User getBack(String userID) {//找回密码的获取密保功能
        Connection conn=this.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        String question=null;
        String password=null;
        String answer=null;
        String ID=null;
        User userInfo=new User();
        try{
            ps=conn.prepareStatement("SELECT * FROM userInfo WHERE userID=?;"); //userID作为主键查询密码是否相等
            ps.setString(1,userID);
            rs=ps.executeQuery();
            if(rs.next()){
                ID=rs.getString("userID");
                question=rs.getString("question");
                answer=rs.getString("answer");
                password=rs.getString("password");
            }
            userInfo.setUserID(ID);
            userInfo.setQuestion(question);
            userInfo.setAnswer(answer);
            userInfo.setPassWord(password);
            return userInfo;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            this.close(rs);
            this.close(ps);
            this.close(conn);
        }
        return userInfo;
    }
}
