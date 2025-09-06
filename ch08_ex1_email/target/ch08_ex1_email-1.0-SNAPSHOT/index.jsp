<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Email List</title></head>
<body>
  <h1>Join our Email List</h1>

  <form action="emailList" method="post">
    <p>First name:
      <input type="text" name="firstName" value="${sessionScope.user.firstName}">
    </p>
    <p>Last name:
      <input type="text" name="lastName" value="${sessionScope.user.lastName}">
    </p>
    <p>Email:
      <input type="email" name="email" value="${sessionScope.user.email}">
    </p>
    <p><button type="submit">Join</button></p>
  </form>
</body>
</html>
