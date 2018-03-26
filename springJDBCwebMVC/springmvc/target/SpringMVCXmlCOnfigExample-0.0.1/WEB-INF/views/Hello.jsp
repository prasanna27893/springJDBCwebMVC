<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Spring MVC Hello World Example</h1>
	<h2>${msg}</h2>
	<div align="center">
		 
		<h3>User List</h3>
		<h4>
			<a href="/newUser">New User</a>
		</h4>
		                         
		<table border="1">
			  
			<th>id</th>                 
			<th>FirstName</th>                 
			<th>LastName</th>                 
			<th>Email</th>                 
			<c:forEach var="user" items="${listUser}" varStatus="status">
                <tr>
					<td>${user.id}</td>                     
					<td>${user.firstName}</td>                     
					<td>${user.lastName}</td>                     
					<td>${user.email}</td>                                         
					<td><a href="/editUser?id=${user.id}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a href="/deleteUser?id=${user.id}">Delete</a>
					</td>                                                    
				</tr>
                </c:forEach>

			                   
		</table>
		        
	</div>
</body>
</html>