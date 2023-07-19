<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/07/19
  Time: 1:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<p>${pointList.trjs_pnt}</p>--%>
<%--<fmt:formatDate value="${pointList.trjs_dtm}" pattern="yy.MM.dd" var="trjs_dtm" />--%>
<%--<p>포맷된 날짜: ${trjs_dtm}</p>--%>
<h1>적립금 내역</h1>
<table>
    <tr>
        <th>날짜</th>
        <th>내용</th>
        <th>유효기간</th>
        <th>금액</th>
    </tr>
    <c:forEach var="point" items="${pointList}">
<%--        <fmt:formatDate value="${point.trjs_dtm}" pattern="yy.MM.dd" var="formatDate" />--%>
        <tr>
            <td>${point.formattedTrjsDtm}</td>
            <td>${point.dscpt}</td>
            <td>${point.vld_end_dt}</td>
            <td>${point.trjs_pnt}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
