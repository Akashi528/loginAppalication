<%--
  Created by IntelliJ IDEA.
  User: Akashi
  Date: 2022/5/24
  Time: 15:24
  To change this template use File | Settings | File Templates.
  hot fix
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
</head>
<h1>用户登录系统</h1>
<div>
    <body>
    <form action="login_Servlet" method="post" name="login"><!--调用servlet再重定向-->
        请输入用户名<input type="text" id="userID" name="userID" value=${param.userID}><br>
        请输入密码<input type="text" id="password" name="password" value=${param.password}>
        <br><input type="submit" id="submit" name="login" value="登录"><% if(request.getAttribute("error")!=null){ %>
        <span style="color:red"><%=request.getAttribute("error")%>
        <% } %>
    </form>
    <form action="findBackGetuser.jsp" method="post" name="changePassword">
        <br><input type="submit" id="changePassword" name="changePassword" value="找回密码">
    </form>
    <form action="register.jsp" method="post" name="register">
        <br><input type="submit" id="register" name="register" value="注册账号">
    </form>
    </body>
</div>
</html>
