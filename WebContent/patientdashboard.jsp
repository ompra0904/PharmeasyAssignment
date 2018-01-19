<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
           
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient Dashboard</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 450px}
    
       
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
   
  </style>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    
    <div class="collapse navbar-collapse" id="myNavbar">
      
      <ul class="nav navbar-nav navbar-right">
        <li><a href="${pageContext.request.contextPath}/home.jsp"> Logout</a></li>
        
      </ul>
    </div>
  </div>
</nav>


 <!-- <form action='UserController' name="frmPatient">  -->
<div class="container-fluid text-center">    
  <div class="row content">
    
    <div class="col-sm-4 text-left"> 
      <h3>List of Doctors</h3>
      <table border="1">
      <thead>
                <tr>
                     <th style="display:none">Person ID </th>
                    <th>Name</th>
                    <th>Operation</th>
                    
                </tr>
      </thead>
      <tbody>
      <c:forEach items="${lstDoctors}" var="doctor">
          <tr>
              <td style="display:none"><c:out value="${doctor.personid}" /></td>
              <td><c:out value="${doctor.firstname}" /></td>
                
              <c:choose>
                    <c:when test="${doctor.hasadded}">
                         <td>Added</td>
                    </c:when>
                    <c:otherwise>
                          <td><a href="UserController?action=addPharmacistOrDoctor&patientid=<c:out value="${loginpersonid}"/>&doctorid=<c:out value="${doctor.personid}"/>">Add</a></td>
                    </c:otherwise>
              </c:choose> 
              
              
              <%-- <td> <input type="submit" value="Add" onclick="action=UserController/AddDoctor&patientid=<c:out value="${personid}"/>&doctorid=<c:out value="${doctor.personid}"/>"/></td> --%>
                    
                </tr>
            </c:forEach>
      </tbody>
      </table>
      
      
    </div>


      <div class="col-sm-4 text-left">
          <h3>List of Pharmacist</h3>
          
      <table border="1">
      <thead>
                <tr>
                     <th style="display:none">Person ID </th>
                    <th>Name</th>
                    <th>Operation</th>
                    
                </tr>
      </thead>
      <tbody>
      <c:forEach items="${lstPharmacists}" var="pharmacist">
                <tr>
                    <td style="display:none"><c:out value="${pharmacist.personid}" /></td>
                    <td><c:out value="${pharmacist.firstname}" /></td>
                    
                    
                    <c:choose>
                    <c:when test="${pharmacist.hasadded}">
                         <td>Added</td>
                    </c:when>
                    <c:otherwise>
                          <td><a href="UserController?action=addPharmacistOrDoctor&patientid=<c:out value="${loginpersonid}"/>&doctorid=<c:out value="${pharmacist.personid}"/>">Add</a></td>
                    </c:otherwise>
                    </c:choose> 
                    
                    
                </tr>
            </c:forEach>
      </tbody>
      </table>
          
          
          
          
          
          

      </div>

      <div class="col-sm-4 text-left">
          <h3>List of Approval</h3>
          
          
          <table border="1">
      <thead>
                <tr>
                     <th style="display:none">Person ID </th>
                    <th>Name</th>
                    <th>Role</th>
                    <th>Operation</th>
                    
                </tr>
      </thead>
      <tbody>
      <c:forEach items="${lstPersonApproveCTO}" var="personApproveCTO">
                <tr>
                    <td style="display:none"><c:out value="${personApproveCTO.personid}" /></td>
                    <td><c:out value="${personApproveCTO.firstname}" /></td>
                    <td><c:out value="${personApproveCTO.rolename}" /></td>
                    
                    
                    <c:choose>
                    <c:when test="${personApproveCTO.isapproved}">
                         <td>Approved</td>
                    </c:when>
                    <c:otherwise>
                          <td><a href="UserController?action=updatePharmacistOrDoctor&patientid=<c:out value="${loginpersonid}"/>&requesterid=<c:out value="${personApproveCTO.personid}"/>">Approve</a></td>
                    </c:otherwise>
                    </c:choose> 
                    
                    
                </tr>
            </c:forEach>
      </tbody>
      </table>
          
         

      </div>
  </div>
</div>

<!-- </form> -->

</body>
</html>