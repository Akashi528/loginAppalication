<%@ page import="java.io.File" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %><%--
  Created by IntelliJ IDEA.
  User: Akashi
  Date: 2022/5/24
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.io.*" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="question" class="data.question" scope="request"/>
<html>
<head>
    <title>注册账号</title>
</head>
<body>
<%
    String filePath="D:/JSP/loginAppilication/src/main/webapp/WEB-INF/";
    String fileName="questionList.txt";
    LinkedList questionList=new LinkedList<String>();
    try{
        File f=new File(filePath,fileName);
        RandomAccessFile randomAccess=new RandomAccessFile(f,"r");
        String s=null;
        StringBuffer ss=new StringBuffer();
        while((s=randomAccess.readLine())!=null){
            byte b[]=s.getBytes("ISO-8859-1");
            String line=new String(b,"UTF-8");
            questionList.add(line);
        }
    }
    catch(Exception exp){
        questionList.add("读取失败"+exp.toString());
    }
%>
    </form>
    <form action="register_Servlet" method="post" name="register"><!--servlet然后重定向到注册成功倒数界面然后转跳到login.jsp-->
        请输入用户名<input type="text" name="RuserID" id="RuserID" value=${param.RuserID} >
        <br>请输入密码<input type="text" name="Rpassword" id="Rpassword" value=${param.Rpassword}>
        <br>请输入确认密码<input type="text" name="RconfirmPassword" id="RconfirmPassword" value=${param.RconfirmPassword}>
        <br>请选择一个密保问题
        <select name="Rquestion">
            <%
            for(int i=0;i<questionList.size();i++){%>
            <option selected value="<%=questionList.get(i)%>"><%=questionList.get(i)%>
            <%}
            %>
        </select><!--通过文件选择密保问题，连同密保问题一起传入数据库-->
        <br>请输入密保答案<input type="text" name="Ranswer" id="Ranswer" value=${param.Ranswer}>
        <br><input type="submit" value="注册" name="registerConfirm" id="registerConfirm"><% if(request.getAttribute("error")!=null){ %>
        <span style="color:red"><%=request.getAttribute("error")%>
        <% } %>
    </form>
</body>
</html>
