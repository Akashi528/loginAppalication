package loginFunction;

import dao.loginDAO;
import data.User;
import toUTF.myUTF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "register_Servlet", value = "/register_Servlet")
public class register_Servlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loginDAO dao=new loginDAO();
        String userID=myUTF.getNewString(request.getParameter("RuserID"));
        if(dao.find(userID).getUserID()!=null){
            //显示已有用户名
            request.setAttribute("error","已存在该用户名");
            RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request,response);
            return;
        }
        String password= myUTF.getNewString(request.getParameter("Rpassword"));
        String question=myUTF.getNewString(request.getParameter("Rquestion"));
        String answer=myUTF.getNewString(request.getParameter("Ranswer"));
        String confirmPassword=myUTF.getNewString(request.getParameter("RconfirmPassword"));
        if(!confirmPassword.equals(password)){
            //显示确认密码错误
            request.setAttribute("error","确认密码错误");
            RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request,response);
            return;
        }
        User user=new User();
        user.setUserID(userID);
        user.setPassWord(password);
        user.setQuestion(question);
        user.setAnswer(answer);
        dao.save(user);
        request.setAttribute("error","注册成功，请登录");//注册成功在login。jsp提示
        RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
