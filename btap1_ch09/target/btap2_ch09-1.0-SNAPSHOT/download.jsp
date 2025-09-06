<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Download</title>
  <link rel="stylesheet" href="styles/main.css">
</head>
<body>
  <h1>Download</h1>

  <!-- tìm item theo code -->
  <c:set var="match" value="${null}" />
  <c:forEach items="${applicationScope.downloads}" var="d">
    <c:if test="${d.code == param.code}">
      <c:set var="match" value="${d}" />
    </c:if>
  </c:forEach>

  <!-- TH1: không tìm thấy -->
  <c:if test="${empty match}">
    <p><em>Unknown or missing download code.</em></p>
    <p><a href="<c:url value='/download'/>">Back</a></p>
  </c:if>

  <!-- TH2: có bài hợp lệ -->
  <c:if test="${not empty match}">
    <p>Bài hát: <strong><c:out value="${match.title}"/></strong></p>

    <audio controls preload="metadata" style="width:100%;max-width:640px;">
      <source src="<c:url value='/file'>
                     <c:param name='name' value='${match.filename}'/>
                   </c:url>" type="audio/mpeg" />
      Trình duyệt của bạn không hỗ trợ audio.
    </audio>

    <p style="margin-top:10px">
      <a class="btn" href="<c:url value='/file'>
                             <c:param name='name' value='${match.filename}'/>
                             <c:param name='dl' value='1'/>
                           </c:url>">Tải về (MP3)</a>
    </p>

    <p><a href="<c:url value='/download'/>">Back</a></p>
  </c:if>
</body>
</html>
