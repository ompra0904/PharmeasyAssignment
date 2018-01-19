<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
       
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="plugin.jsp" %>
 <title>Login</title>
    
</head>
<body>
  <%@include file="header.jsp" %>
    <div class="container"> 
    
    <h2> Login</h2>
    
    <form class="form-horizontal" method="POST" action='LoginController' name="frmLoginUser">
            <div class="form-group">              
                  User name : <input type="text" name="username" value="<c:out value="${user.username}" />" /> 
           </div>
           <div class="form-group"> 
               Password : <input type="password" name="password" value="<c:out value="${user.password}" />" /> <br /> 
           </div>
            
            <div class="form-group">
        		<div class="col-sm-offset-1 col-sm-10">
      		    <input class="btn btn-default" type="submit" value="Submit" />
        		<button type="button" class="btn btn-default" onclick="window.open('${pageContext.request.contextPath}/SignUp.jsp','_self');"> SignUp</button>
                </div> 
            </div>
               
    </form>
        </div> 
   
    <%@include file="footer.jsp" %>
</body>
</html>