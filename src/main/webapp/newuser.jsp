<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>User Registration <%= application.getAttribute("counter")%></h1>




<form action="register" method="post">
Username<input type="text" name="username"></br>
email<input type="text" name="email">

<%
String err1=null;

if (request.getAttribute ("invalidemail")!=null){
 err1= (String)request.getAttribute ("invalidemail");
}


if (err1 != null){
	out.print (err1);
}
%>

</br>
Password<input type="text" name="pwd"><%

if (request.getAttribute ("pwdmiscatch") != null){
	out.print (request.getAttribute ("pwdmiscatch"));
}

%>
</br> 
Confirm Password<input type="text" name="cpwd"></br>
<input type="submit" value="Register">
</form>>

</body>
</html>