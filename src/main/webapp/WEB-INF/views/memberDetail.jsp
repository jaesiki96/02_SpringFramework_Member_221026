<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-10-26
  Time: 오후 4:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>memberDetail.jsp</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
</head>
<body>
  <div class="container">
    <table class="table table-striped">
      <tr>
        <th>회원번호</th>
        <td>${member.id}</td>
      </tr>
      <tr>
        <th>이메일</th>
        <td>${member.memberEmail}</td>
      </tr>
      <tr>
        <th>비밀번호</th>
        <td>${member.memberPassword}</td>
      </tr>
      <tr>
        <th>이름</th>
        <td>${member.memberName}</td>
      </tr>
      <tr>
        <th>나이</th>
        <td>${member.memberAge}</td>
      </tr>
    </table>
  </div>
</body>
</html>
