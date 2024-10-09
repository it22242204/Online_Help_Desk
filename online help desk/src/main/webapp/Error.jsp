<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true" %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Error</title>
</head>
<body>
	<center>
		<h1>Error</h1>
		<h2><%=exception.getMessage() %><br/> </h2>
	<style>
        p.error-message {
            font-size: 24px; /* Adjust the font size as needed */
            color: red;    /* Set the text color to red */
        }
    </style>
</head>
<body>
    <p class="error-message">${Error}</p>
</body>
</html>