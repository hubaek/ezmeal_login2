<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/07/20
  Time: 5:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>관리자 로그인 완료 페이지</h1>

<c:if test="${not empty loginAdminInfo}">
    <li><a href="/mypage/main">회원명 : ${loginAdminInfo.name}</a></li>
</c:if>

</body>
</html>
