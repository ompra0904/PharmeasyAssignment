<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="plugin.jsp" %>

<!-- <link type="text/css"
    href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script> -->

 <title>Sign Up</title>
    
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
</head>
<body>
<%@include file="header.jsp" %>
 <div class="container"> 
    <h2> Sign UP</h2>
    <form class="form-horizontal" method="POST" action='UserController' name="frmAddUser">
        <div class="form-group"> 
        	First Name : <input type="text" name="firstname" value="<c:out value="${user.firstname}" />" />
         </div>
        <div class="form-group"> 
        	Last Name : <input type="text" name="lastname" value="<c:out value="${user.lastname}" />" />
         </div>
        <div class="form-group"> 
        	Email : <input type="text" name="email" value="<c:out value="${user.email}" />" />
         </div>
        <div class="form-group"> 
        	Gender :  <div class="radio">
                        <input type="radio" name="gender" value="<c:out value="Male" />" >Male
                    </div>
                    <div class="radio">
                        <input type="radio" name="gender" value="<c:out value="Female" />" >Female
                    </div>
         </div>
        <div class="form-group"> 
        	Role : <div class="radio">
                        <input type="radio" name="roleid" value="<c:out value="1" />" >Doctor
                    </div>
                    <div class="radio">
                        <input type="radio" name="roleid" value="<c:out value="2" />" >Patient
                    </div>
                    <div class="radio">
                        <input type="radio" name="roleid" value="<c:out value="3" />" >Pharmacist
                    </div>
         </div>
        <div class="form-group"> 
        	User name : <input type="text" name="username" value="<c:out value="${user.username}" />" />
         </div>
        <div class="form-group"> 
        	Password : <input type="password" name="password" value="<c:out value="${user.password}" />" /> 
            
         </div>
         <div class="form-group"> 
         	<input type="submit" value="Submit" />
         </div>      
    </form>
    </div>
    <%@include file="footer.jsp" %>
    

   
</body>
</html>