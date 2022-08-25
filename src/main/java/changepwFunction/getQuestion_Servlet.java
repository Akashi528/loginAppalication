package changepwFunction;

import dao.loginDAO;
import data.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "getQuestion_Servlet", value = "/getQuestion_Servlet")
public class getQuestion_Servlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID=request.getParameter("userID");
        loginDAO dao=new loginDAO();
        User user=dao.getBack(userID);//此时user含该user全部数据
        if(user.getUserID()==null){
            //若用户名不存在则返回并显示用户名不存在
            request.setAttribute("error","未找到用户名");
            RequestDispatcher dispatcher= request.getRequestDispatcher("findBackGetuser.jsp");
            dispatcher.forward(request,response);
            return;
        }
        HttpSession session= request.getSession();
        session.setAttribute("user",user);//将user存入session，含有user全部数据
        RequestDispatcher dispatcher= request.getRequestDispatcher("findBack.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
