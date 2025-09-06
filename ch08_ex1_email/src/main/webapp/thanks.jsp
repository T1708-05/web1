<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Thanks</title></head>
<body>
  <h1>Thanks for joining</h1>

  <p>
    <strong>Email:</strong> ${sessionScope.user.email}<br>
    <strong>First Name:</strong> ${sessionScope.user.firstName}<br>
    <strong>Last Name:</strong> ${sessionScope.user.lastName}
  </p>

  <hr>

  <p><strong>Current date:</strong> ${requestScope.currentDate}</p>

  <h3>First two users</h3>
  <ul>
    <li>
      ${sessionScope.users[0].email}
      — ${sessionScope.users[0].firstName} ${sessionScope.users[0].lastName}
    </li>
    <li>
      ${sessionScope.users[1].email}
      — ${sessionScope.users[1].firstName} ${sessionScope.users[1].lastName}
    </li>
  </ul>

  <p><strong>Customer Service:</strong> ${initParam.custServEmail}</p>

  <form action="emailList" method="get">
    <button type="submit">Return</button>
  </form>
</body>
</html>
