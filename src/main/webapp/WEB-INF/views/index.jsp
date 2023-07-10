<%@ page import="com.teamProject.ezmeal.domain.MemberDto" %>
<%@ page import="com.teamProject.ezmeal.service.MemberService" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ page session="false" %>--%>
<%--<c:set var="loginId"--%>
<%--       value="${pageContext.request.getSession(false)==null ? '' : pageContext.request.session.getAttribute('id')}"/>--%>

<c:set var="loginOutLink" value="${sessionScope.memberId==null ? '/login' : '/logout'}"/>
<c:set var="loginOut" value="${sessionScope.memberId==null ? '로그인' : '로그아웃'}"/>
<%--<%--%>
<%--    HttpSession session1 = request.getSession();--%>
<%--    Long memberId = (Long) session1.getAttribute("memberId");--%>
<%--    MemberService memberService = (MemberService) request.getAttribute("memberService");--%>
<%--    if (memberId != null && memberService != null) {--%>
<%--        MemberDto loginMbrInfo = memberService.mbrInfo(memberId);--%>
<%--        request.setAttribute("loginMbrInfo", loginMbrInfo);--%>
<%--    }--%>
<%
    MemberDto loginMbrInfo = (MemberDto) session.getAttribute("loginMbrInfo");
%>

<%--%>--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>ezMeal-test</title>
</head>
<body>
<ul>
    <li><a href="/">로컬 메인</a></li>
    <c:if test="${not empty loginMbrInfo}">
        <li><a href="/mypage/main">회원명 : <%=loginMbrInfo.getName()%></a></li>
    </c:if>
    <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
    <li><a href="/cart/general">일반 장바구니</a></li>
    <li><a href="/cart/subscript">구독 장바구니</a></li>
    <li><a href="//productcatelist">상품 목록</a></li>
    <li><a href="/address">배송지</a></li>
    <li><a href="/member/signup">회원가입</a></li>
<%--    <li><a href="/mypage/withdrawal">회원탈퇴</a></li>--%>
    <li><a href="/mypage/main">마이페이지</a></li>
    <li><a href=""></a></li>
</ul>
</body>
</html>
