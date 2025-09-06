<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Download Site</title>
  <link rel="stylesheet" href="styles/main.css">
</head>
<body>
  <h1>Download Site</h1>

  <!-- Chào mừng nếu có cookie -->
  <c:if test="${not empty cookie.firstName.value}">
    <p>Welcome back, <strong><c:out value="${cookie.firstName.value}"/></strong>!</p>
  </c:if>

  <p class="links">
    <a href="<c:url value='/download'><c:param name='action' value='register'/></c:url>">Register</a> |
    <a href="<c:url value='/download'><c:param name='action' value='viewCookies'/></c:url>">View Cookies</a> |
    <a href="<c:url value='/download'><c:param name='action' value='deleteCookies'/></c:url>">Delete Cookies</a>
  </p>

  <hr/>

  <!-- Danh mục download từ application scope -->
  <c:choose>
    <c:when test="${empty applicationScope.downloads}">
      <p><em>No downloads available.</em></p>
    </c:when>
    <c:otherwise>
      <table class="list">
        <tr>
          <th>Title</th>
          <th class="right">Action</th>
        </tr>
        <c:forEach items="${applicationScope.downloads}" var="d">
          <tr>
            <td><c:out value="${d.title}"/></td>
            <td class="right">
              <a href="<c:url value='/download'>
                         <c:param name='action' value='download'/>
                         <c:param name='code' value='${d.code}'/>
                       </c:url>">Go to download</a>
            </td>
          </tr>
        </c:forEach>
      </table>
    </c:otherwise>
  </c:choose>

  <p class="muted">
    Server time:
    <fmt:formatDate value="<%= new java.util.Date() %>" pattern="yyyy-MM-dd HH:mm:ss"/>
  </p>
</body>
</html>
