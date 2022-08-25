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
    <title>登陆成功界面</title>
</head>
<body>
登陆成功！
<div>
    <form action="logout_Servlet" id="back" method="post" name="back">
        <input type="submit" value="退出登录" name="logout" id="logout">
    </form>
</div>
<div>
    <form action="changePassword.jsp" id="Tochangepassword" name="changepassword" method="post">
        <input type="submit" value="修改密码" name="changePassword" id="changepassword">
    </form>
</div>
</body>
</html>
