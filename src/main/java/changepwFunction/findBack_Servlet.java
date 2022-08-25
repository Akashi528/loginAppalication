package changepwFunction;

import dao.loginDAO;
import data.User;
import toUTF.myUTF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "findBack_Servlet", value = "/findBack_Servlet")
public class findBack_Servlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String answer= myUTF.getNewString(request.getParameter("answer"));
        HttpSession session= request.getSession();
        User user=(User) session.getAttribute("user");
        if(!user.getAnswer().equals(answer)){
            //密保答案错误返回并显示答案错误
            request.setAttribute("error","密保答案错误");
            RequestDispatcher dispatcher= request.getRequestDispatcher("findBack.jsp");
            dispatcher.forward(request,response);
            return;
        }
        String password=request.getParameter("newPassword");
        if(password.equals(user.getPassWord())){
            //若新密码和旧密码不一样则返回并显示新旧密码不能一样
            request.setAttribute("error","新密码与旧密码不能一样");
            RequestDispatcher dispatcher= request.getRequestDispatcher("findBack.jsp");
            dispatcher.forward(request,response);
            return;
        }
        String confirmPassword=request.getParameter("newConfirmPassword");
        if(!password.equals(confirmPassword)){
            //若确认密码和新密码不一样则返回并显示确认密码错误
            request.setAttribute("error","确认密码与新密码不一致");
            RequestDispatcher dispatcher= request.getRequestDispatcher("findBack.jsp");
            dispatcher.forward(request,response);
            return;
        }
        loginDAO dao=new loginDAO();
        user.setPassWord(password);//用新密码覆盖旧密码
        dao.change(user);
        RequestDispatcher dispatcher= request.getRequestDispatcher("login.jsp");
        request.setAttribute("error","修改密码成功！");
        dispatcher.forward(request,response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
