<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="mma" uri="/WEB-INF/murach.tld" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Thanks!</title>
  <link rel="stylesheet" href="styles/main.css"/>
</head>
<body>
  <h1>Thanks for joining our list</h1>

  <h2>Subscriber</h2>
  <label>Email:</label><span>${user.email}</span><br/>
  <label>First Name:</label><span>${user.firstName}</span><br/>
  <label>Last Name:</label><span>${user.lastName}</span><br/>

  <p class="pad_top">
    Date: <mma:currentDate/> — Time: <mma:currentTime/>
  </p>

  <p class="pad_top"><a href="index.jsp">Back</a></p>
</body>
</html>
