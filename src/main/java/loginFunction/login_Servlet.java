package loginFunction;

import dao.loginDAO;
import data.User;
import toUTF.myUTF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "login_Servlet", value = "/login_Servlet")
public class login_Servlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userInfo=new User();
        loginDAO dao=new loginDAO();
        request.setAttribute("user",userInfo);//将user保存进request使servlet可以将user传到其他页面
        String inUserID= myUTF.getNewString(request.getParameter("userID"));
        String inPassword=myUTF.getNewString(request.getParameter("password"));
        userInfo=dao.find(inUserID);
        /*若用户名或密码错误重定向到登陆界面*/
        if(userInfo.getUserID()==null){
            //显示用户名错误
            request.setAttribute("error","未找到用户名");
            RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request,response);
            return;
        }
        if(!compareUser(inPassword,userInfo)){
            //显示密码错误 隐藏字置为不隐藏
            request.setAttribute("error","密码错误");
            RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request,response);
            return;
        }
        /*若信息都正确重定向到完成界面*/
        /*通过session存储登录信息*/
        HttpSession session=request.getSession();
        session.setAttribute("user",userInfo);
        RequestDispatcher dispatcher=request.getRequestDispatcher("finish.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    private boolean compareUser(String inPassword,User user){
        return user.getPassWord().equals(inPassword);//检查密码是否与输入的密码相等，string要用equals检查
    }
}
