<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("login")==null){
        response.sendRedirect("../login.jsp");
    }
    else if(!session.getAttribute("roleName").toString().equals("admin")){
        response.sendError(403);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome to Admin Area</h1>
    </body>
</html>
