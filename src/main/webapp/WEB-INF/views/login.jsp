<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
</br>
</br>
</br>
<form action="/login?redirectURL=${redirectURL}" method="post">
     <label for="id">id</label><input type="text" name="tryMbrId" id="id" value="${tryMbrId != null ? tryMbrId : ''}">
    </br>

    <c:if test="${not empty wrongIdMsg}">
        <p>${wrongIdMsg}</p>
    </c:if>
    <label for="pw">pwd</label><input type="password" name="tryMbrPw" id="pw"/>
    <c:if test="${not empty wrongPwMsg}">
        <p>${wrongPwMsg}</p>
    </c:if>
    </br>

    <input type="submit" value="login"/>
    <%--  check 되면 dom property : checked=true  --%>
    id 기억<input type="checkbox" name="remember" id="remember" ${cookie.id != null ? "checked" : ''}/>
</form>
</body>
</html>
