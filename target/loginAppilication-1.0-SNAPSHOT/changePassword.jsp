<%--
  Created by IntelliJ IDEA.
  User: Akashi
  Date: 2022/5/24
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>找回密码</title>
</head>
<body>
    <form method="post" action="changePassword_Servlet" id="confirm"><!--调用servlet再重定向-->
        请输入旧密码<input name="oldPassword" type="text" id="oldPassword" value=${param.oldPassword}>
        <br>请输入新密码<input name="newPassword" type="text" id="newPassword"value=${param.newPassword}>
        <br>请确认新密码<input name="confirmPassword" type="text" id="confirmPassword" value=${param.confirmPassword}>
        <br><input type="submit" value="修改密码" name="changepassword" id="changepassword">
        <% if(request.getAttribute("error")!=null){ %>
        <span style="color:red"><%=request.getAttribute("error")%>
        <% } %>
    </form>
</body>
</html>
