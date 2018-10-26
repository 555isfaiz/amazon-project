<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Photo Share</title>
</head>
<%String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
String url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);%>  
<body>
<h1>
  Welcom to Photo Share Center!
</h1>
<form name="Form2">
  <h1>Please choose what you want to do!</h1>
  <td><%out.println("<a href= \"" + basePath + "Upload" + "\">Upload Photos</a>");%></td>
  <td><%out.println("<a href= \"" + basePath + "View" + "\">View Photos</a>");%></td>
</form>
</body>
</html>