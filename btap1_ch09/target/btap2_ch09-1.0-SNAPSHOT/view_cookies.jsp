<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Cookies</title>
  <link rel="stylesheet" href="styles/main.css">
</head>
<body>
  <h1>Cookies</h1>

  <table class="list">
    <tr><th>Name</th><th>Value</th></tr>
    <c:forEach var="cook" items="${cookie}">
      <tr>
        <td><c:out value="${cook.value.name}"/></td>
        <td><c:out value="${cook.value.value}"/></td>
      </tr>
    </c:forEach>
  </table>

  <p class="links"><a href="<c:url value='/download'/>">Back</a></p>
</body>
</html>
