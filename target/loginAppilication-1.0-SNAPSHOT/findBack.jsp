<%@ page import="data.User" %><%--
  Created by IntelliJ IDEA.
  User: Akashi
  Date: 2022/5/24
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="data.User" scope="request"/>
<html>
<head>
    <title>找回密码</title>
</head>
<body>
<form method="post" action="findBack_Servlet" id="confirm"><!--调用servlet再重定向-->
    问题：<%user=(User)session.getAttribute("user");%>
    <%=user.getQuestion()%><!--获取用户选择的密保问题 先输入用户名submit获取question然后转发到此页面-->
    <br>请输入密保答案<input name="answer" type="text" id="answer" value=${param.answer}>
    <br>请输入新密码<input name="newPassword" type="text" id="newPassword" value=${param.newPassword}>
    <br>请确认新密码<input name="newConfirmPassword" type="text" id="newConfirmPassword" value=${param.newConfirmPassword}>
    <br><input type="submit" value="找回密码" name="changepassword" id="changepassword">
    <% if(request.getAttribute("error")!=null){ %>
    <span style="color:red"><%=request.getAttribute("error")%>
        <% } %>
</form>
</body>
</html>
