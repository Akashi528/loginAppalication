package changepwFunction;

import dao.loginDAO;
import data.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "changePassword_Servlet", value = "/changePassword_Servlet")
public class changePassword_Servlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userInfo;
        HttpSession session=request.getSession();
        userInfo=(User)session.getAttribute("user");
        String oldPassword=request.getParameter("oldPassword");
        if(!oldPassword.equals(userInfo.getPassWord())){
            //若原密码错误转发到原页面并显示原密码错误
            request.setAttribute("error","原密码错误");
            RequestDispatcher dispatcher=request.getRequestDispatcher("changePassword.jsp");
            dispatcher.forward(request,response);
            return;
        }
        String newPassword=request.getParameter("newPassword");
        if(newPassword.equals(oldPassword)){
            //若新密码和老密码一样转发原页面并显示新密码和原密码不能一样
            request.setAttribute("error","新密码与旧密码不能一样");
            RequestDispatcher dispatcher=request.getRequestDispatcher("changePassword.jsp");
            dispatcher.forward(request,response);
            return;
        }
        String confirmPassword=request.getParameter("confirmPassword");
        if(!newPassword.equals(confirmPassword)){
            //若确认密码错误则转发到原页面并显示原密码错误
            request.setAttribute("error","确认密码错误");
            RequestDispatcher dispatcher=request.getRequestDispatcher("changePassword.jsp");
            dispatcher.forward(request,response);
            return;
        }
        loginDAO dao=new loginDAO();
        userInfo.setPassWord(newPassword);
        dao.change(userInfo);
        //若信息全部正确转发到登陆页面重新登陆
        request.setAttribute("error","修改密码成功");
        RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
