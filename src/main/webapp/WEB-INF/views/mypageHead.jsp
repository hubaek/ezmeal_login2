<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/07/16
  Time: 8:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/screens/mypageHead.css">
</head>
<body>


<div class="head">
    <div class="user-info-box">
        <div class="inner">
            <div class="column user-info">
                            <span class="img">
                                <i class="ico-cou-level01">{등급이미지}</i>
                            </span>
                <div class="txt">
                    <p class="p-name">
                        <strong class="name">{회원명}</strong>
                    </p>
                    <ul class="list grade">
                        <li>
                            <!-- <i class="ico-bl-crown"></i> -->
                            풋사과{회원등급}
                        </li>
                        <li id="groupPointRateText">
                            1%적립{등급별적립금}
                        </li>
                    </ul>
                    <p class="level" id="levelUp">300,000원 더 구매 시, 청사과로 등급 상승!</p>
                </div>
                <a href="/mypage/memberBenefitsInfo" class="benefit button"> <span>등급 혜택</span> </a>
            </div>
            <!-- user info 끝 -->
            <dl class="column order">
                <dt>주문/배송</dt>
                <dd>
                    <a href="/mypage/orderlist">
                        <Strong class="num" id="orderConuntText">0</Strong>
                        건
                    </a>
                </dd>
            </dl>
            <!-- order -->
            <dl class="column coupon">
                <dt>쿠폰</dt>
                <dd>
                    <a href="/mypage/myCoupon">
                        <strong class="num" id="couponCountText">0</strong>
                        개
                    </a>
                </dd>
            </dl>
            <!-- coupon -->
            <dl class="column point">
                <dt>포인트</dt>
                <dd class="text-primary">
                    <a href="/mypage/myPoint">
                        <strong class="num" id="pointText">0</strong>
                        p
                    </a>
                </dd>
            </dl>
        </div>
    </div>
</div>
<!-- head 끝 -->
</body>
</html>
