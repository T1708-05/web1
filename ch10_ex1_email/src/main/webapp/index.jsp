<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="mma" uri="/WEB-INF/murach.tld" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Join our email list</title>
  <link rel="stylesheet" href="styles/main.css"/>
</head>
<body>
  <h1>Join our email list</h1>
  <p><em>*</em> marks required fields</p>

  <form action="join" method="post">
    <label class="pad_top">Email:</label>
    <input type="email" name="email" value="" required/>
    <mma:ifEmptyMark field="${param.email}"/><br/>

    <label class="pad_top">First Name:</label>
    <input type="text" name="firstName" value=""/>
    <mma:ifEmptyMark field="${param.firstName}"/><br/>

    <label class="pad_top">Last Name:</label>
    <input type="text" name="lastName" value=""/>
    <mma:ifEmptyMark field="${param.lastName}"/><br/>

    <label>&nbsp;</label>
    <input type="submit" value="Join Now"/>
  </form>

  <p class="pad_top">
    Today is <mma:currentDate/>.
    <br/>
    Current time: <mma:currentTime/>.
  </p>

  <mma:ifWeekday>
    <p>Live support available at 1-800-555-2222 (Mon–Fri)</p>
  </mma:ifWeekday>
</body>
</html>
