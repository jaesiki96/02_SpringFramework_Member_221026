<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-10-26
  Time: 오후 3:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>memberMain.jsp</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
</head>
<body>
    <div class="container">
        <h2>${sessionScope.loginEmail}님 환영합니다.</h2>
        <h2>model 값: ${modelEmail}</h2>
        <button class="btn btn-warning" onclick="updateForm()">내정보수정</button>
        <button class="btn btn-danger" onclick="logout()">로그아웃</button>
        <a href="/">index.jsp</a>
    </div>
</body>
<script>
    <%--  /update 주소를 요청하는 update 함수  --%>
    const updateForm = () => {
        location.href = "/update";
    }
    const logout = () => {
        location.href = "/logout";
    }
</script>
</html>
