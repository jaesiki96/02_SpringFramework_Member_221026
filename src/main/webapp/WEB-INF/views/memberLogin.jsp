<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-10-26
  Time: 오전 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>memberLogin.jsp</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
</head>
<body>
    <div class="container">
    <form action="/login" method="post">
        <input type="text" name="memberEmail" placeholder="이메일" class="form-control"> <br>
        <input type="text" name="memberPassword" placeholder="비밀번호" class="form-control"> <br>
        <input type="submit" class="btn btn-primary" value="로그인">
    </form>
    </div>
</body>
</html>
