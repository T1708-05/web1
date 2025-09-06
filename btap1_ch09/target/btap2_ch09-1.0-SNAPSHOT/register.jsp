<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Register</title>
  <link rel="stylesheet" href="styles/main.css">
</head>
<body>
  <h1>Register to Download</h1>

  <c:if test="${not empty param.code}">
    <p>You’re registering to download: <strong><c:out value="${param.code}"/></strong></p>
  </c:if>

  <form action="<c:url value='/download'/>" method="post" class="form">
    <input type="hidden" name="action" value="saveRegistration"/>
    <input type="hidden" name="code" value="<c:out value='${param.code}'/>"/>

    <p>
      <label>First name:</label>
      <input type="text" name="firstName"
             value="<c:out value='${sessionScope.user.firstName}'/>">
    </p>
    <p>
      <label>Last name:</label>
      <input type="text" name="lastName"
             value="<c:out value='${sessionScope.user.lastName}'/>">
    </p>
    <p>
      <label>Email:</label>
      <input type="email" name="email"
             value="<c:out value='${sessionScope.user.email}'/>">
    </p>
    <p><button type="submit">Submit</button></p>
  </form>

  <p class="links"><a href="<c:url value='/download'/>">Back</a></p>
</body>
</html>
