<%@page import="com.unisoft.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int error = 0;
    if(request.getMethod().equalsIgnoreCase("post")){
        String email = request.getParameter("email");
        String passwd = request.getParameter("passwd");
        UserInfo info = DBManager.authenticateUser(email, passwd);
        if(info==null){
            error = 1;
        }else if(info.getVerified()==0){
            error = 2;
        }else{
            session.setAttribute("login", 1);
            session.setAttribute("email", email);
            session.setAttribute("roleName", info.getRoleName());
            session.setAttribute("name", info.getName() );
            if(info.getRoleName().equalsIgnoreCase("admin")){
                response.sendRedirect("admin/index.jsp");
            }
            else if(info.getRoleName().equalsIgnoreCase("member")){
                response.sendRedirect("member/index.jsp");
            }
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>SIS</title>
        <link href="css/default.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%@include file="WEB-INF/jspf/guest_header.jspf" %>
        <%@include file="WEB-INF/jspf/guest_menu.jspf" %>
        <div id="content">
            <div id="left">
                <form action="login.jsp" method="POST" style="width: 350;">
                    <fieldset>
                        <legend>Login Form</legend>
                        <table>
                            <tbody>
                                <tr>
                                    <td>Email ID : </td>
                                    <td><input type="text" name="email" value="" /></td>
                                </tr>
                                <tr>
                                    <td>Password : </td>
                                    <td><input type="password" name="passwd" value="" /></td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="right_column">
                                        <input type="submit" value="Login" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </fieldset>
                </form>
                <% if(error==1) { %>
                <h2 class="error">Email ID / Password is Incorrect</h2>
                <% } %>
                <% if(error==2) { %>
                <h2 class="error">Your email is not yet verified.</h2>
                <% } %>
            </div>
        </div>
        <%@include file="WEB-INF/jspf/guest_footer.jspf" %>
    </body>
</html>
