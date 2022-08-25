<%--
  Created by IntelliJ IDEA.
  User: Akashi
  Date: 2022/5/26
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请输入用户名</title>
</head>
<body>
<form method="post" action="getQuestion_Servlet" name="getUserID">
    请输入用户名<input type="text" name="userID" id="userID" value=${param.userID}>
    <input type="submit" name="submit" value="提交" id="submit">
    <% if(request.getAttribute("error")!=null){ %>
    <span style="color:red"><%=request.getAttribute("error")%>
        <% } %>
</form>
</body>
</html>
