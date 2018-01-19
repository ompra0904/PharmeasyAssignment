<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Doctor Dashboard</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.js"></script>

    
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
      <ul class="nav navbar-nav">
        <!--<li><a href="#">Approve Appointment</a></li>-->
        
        <li><a href="#">Create Prescription</a></li>
        <li><a href="#"></a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="${pageContext.request.contextPath}/home.jsp"> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
  
<div class="container-fluid text-center">    
  <div class="row content">
    
      <div class="col-sm-8 text-left">
          <h2>All Patients</h2>
          
          
      <table border="1">
      <thead>
                <tr>
                     <th style="display:none">Person ID </th>
                    <th>Name</th>
                    <th>Medical Prescription</th>
                    <th>Operation</th>
                    
                </tr>
      </thead>
      <tbody>
      <c:forEach items="${lstUsersMapping}" var="personmapping">
          <tr>
              <td style="display:none"><c:out value="${personmapping.personid}" /></td>
              <td><c:out value="${personmapping.firstname}" /></td>                                       
               <c:choose>
                    <c:when test="${personmapping.isapproved}">
                        <td>${personmapping.prescriptionhistory}</td>
                        <td>Approval granted for history</td>
                    </c:when>
                    <c:otherwise>
                       <td>Not Authorized</td>
                            <c:choose>
                            <c:when test="${personmapping.hasrequestedhistory}">
                                 <td>Requested</td>
                            </c:when>
                            <c:otherwise>
                                 <td><a href="UserController?action=updateDoctor&patientid=<c:out value="${personmapping.personid}"/>&doctorid=<c:out value="${loginpersonid}"/>">Request Prescription</a></td>
                            </c:otherwise>
                            </c:choose>
                     </c:otherwise>
               </c:choose>   
            </tr>
        </c:forEach>
      </tbody>
      </table>
         
          
          <!-- <table class="table table-bordered" >
              
              <thead>
                  <tr>
                      <th>name</th>
                      <th>Prescription History</th>
                      <th>Request Permission</th>
                  </tr>
              </thead>
              <tbody>
                  <tr>
                      <td>Khetan</td>
                      <td>Cough Fever - decold</td>
                      <td><button type="button" class="btn btn-primary btn-sm" id="reqHistory1">Request Prescription</button></td>
                  </tr>
                  <tr>
                      <td>Omprakash</td>
                      <td>Fever - detol</td>
                      <td><button type="button" class="btn btn-primary btn-sm" id="reqHistory2">Request Prescription</button></td>
                  </tr>
                  <tr>
                      <td>Kunal</td>
                      <td>Thyphiod paracetomol</td>
                      <td><button type="button" class="btn btn-primary btn-sm" id="reqHistory3">Request Prescription</button></td>
                  </tr>
              </tbody>
          </table> -->
      </div>

  </div>
</div>

</body>
</html>