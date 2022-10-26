<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-10-26
  Time: 오후 3:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>memberList.jsp</title>
    <style>
        table, th, td {
            border: 1px solid #bcbcbc;
        }
        table {
            width: 600px;
            height: 200px;
            margin-left: auto;
            margin-right: auto;
            margin-top: 200px;
        }
        tr, td {
            text-align: center;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <th>회원번호</th>
        <th>이메일</th>
        <th>비밀번호</th>
        <th>이름</th>
        <th>나이</th>
        <th>전화번호</th>
    </tr>
    <c:forEach items="${memberList}" var="member">
        <tr>
            <td>${member.memberId}</td>
            <td>${member.memberEmail}</td>
            <td>${member.memberPassword}</td>
            <td>${member.memberName}</td>
            <td>${member.memberAge}</td>
            <td>${member.memberMobile}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
