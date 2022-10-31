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
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <script src="/resources/js/jquery.js"></script>
    <style>
        table, th, td {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
<table class="table table-striped table-hover">
    <tr>
        <th>회원번호</th>
        <th>이메일</th>
        <th>비밀번호</th>
        <th>이름</th>
        <th>나이</th>
        <th>조회</th>
        <th>삭제</th>
        <th>조회(ajax)</th>
        <%--  조회(ajax): 화면을 넘어가지 않고 그 페이지에 조회를 띄워주는 것  --%>
    </tr>
    <c:forEach items="${memberList}" var="member">
        <tr>
            <td>${member.id}</td>
            <td>${member.memberEmail}</td>
            <td>${member.memberPassword}</td>
            <td>${member.memberName}</td>
            <td>${member.memberAge}</td>
            <td>
                <a href="/member?id=${member.id}">조회</a>
            </td>
            <td>
                                        <%-- onclick안에 싱글 쿼터 사용!   --%>
                <button class="btn btn-danger" onclick="deleteMember('${member.id}')">삭제</button>
            </td>
            <td>
                <button class="btn btn-primary" onclick="findMember('${member.id}')">조회(ajax)</button>
            </td>
        </tr>
    </c:forEach>
</table>
    <div id="detail-area">

    </div>
</div>
</body>
<script>
    // 삭제
    const deleteMember = (clickedId) => {
        console.log("클릭한 id값: ", clickedId);
        // delete 라는 주소로 id 값이 넘어간다.
        location.href = "/delete?id="+clickedId;
    }
    // 조회 (ajax)
    const findMember = (findId) => {
        console.log("findId", findId);
        const detailArea = document.getElementById("detail-area");
        $.ajax({
            type: "get",
            url: "/detail-ajax",
            data: {id: findId},
            dataType: "json",
            success: function (member) {
                console.log("조회결과", member);
                console.log("조회 ID:", member.id);
                let result =
                    "        <table class=\"table table-striped\">\n" +
                    "            <tr>\n" +
                    "                <th>id</th>\n" +
                    "                <td>"+ member.id +"</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <th>email</th>\n" +
                    "                <td>" + member.memberEmail + "</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <th>password</th>\n" +
                    "                <td>" + member.memberPassword + "</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <th>name</th>\n" +
                    "                <td> " + member.memberName + "</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <th>age</th>\n" +
                    "                <td>" + member.memberAge + "</td>\n" +
                    "            </tr>\n" +
                    "        </table>";
                detailArea.innerHTML = result;
            },
            error: function () {

            }
        });
    }
</script>
</html>
