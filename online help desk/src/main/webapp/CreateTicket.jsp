<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>HelpDesk</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="http://localhost:8090/signup/Login" class="navbar-brand"> HelpDesk </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">DETAIL COLLECTION</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-6">
		<div class="card">
			<div class="card-body">
				<c:if test="${user != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
					  <c:if test="${user != null}">
            			Edit detail
            		  </c:if>
						<c:if test="${user == null}">
            			Add New detail
            		   </c:if>
					</h2>
				</caption>

				<c:if test="${user != null}">
					<input type="hidden" name="ID" value="<c:out value='${user.ID}' />" />
				</c:if>
             
				<fieldset class="form-group">
              <label>Name</label>
             <input type="text" value="<c:out value='${user.name}' />" class="form-control" name="Name" required="required"
             >
              </fieldset>


				<fieldset class="form-group">
            <label>Email</label>
          <input type="email" value="<c:out value='${user.email}' />" class="form-control" name="Email" 
            pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}"
           title="Please enter a valid email address">
             </fieldset>

				<fieldset class="form-group">
					<label>Address</label> <input type="text"
						value="<c:out value='${user.address}' />" class="form-control"
						name="address">
				</fieldset>
				
				

				<button type="submit" class="btn btn-success">Save</button>
			 </form>
			</div>
		</div>
	</div>
</body>
</html>
