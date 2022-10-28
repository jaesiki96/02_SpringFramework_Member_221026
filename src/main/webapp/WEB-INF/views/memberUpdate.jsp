<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-10-27
  Time: 오후 2:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>memberUpdate.jsp</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <style>
        update-form {
            width: 800px;
        }
    </style>
</head>
<body>
<div class="container" id="update-form">
    <form action="/update" method="post" name="updateForm">
                            <%--  readonly는 읽기만 할 수 있는 정보  --%>
        id: <input type="text" name="id" value="${member.id}" class="form-control" readonly>
        email: <input type="text" name="memberEmail" value="${member.memberEmail}" class="form-control" readonly>
                    <%--  비밀번호 입력 후 일치하면 패스 일치하지 않으면 일치하지 않는 문구  --%>
        password: <input type="text" name="memberPassword" class="form-control" id="memberPassword">
        name: <input type="text" name="memberName" value="${member.memberName}" class="form-control">
        age: <input type="text" name="memberAge" value="${member.memberAge}" class="form-control">
        <input type="button" value="수정" class="btn btn-warning" onclick="update()">
    </form>
</div>
</body>
<script>
    const update = () => {
      const passwordDB = '${member.memberPassword}'; // DB 에서 가져온 비밀번호
        /*
        사용자가 input 태그에 입력한 비밀번호와 DB 에서 가져온 비밀번호가 일치하면 수정 요청을 보내고
        일치하지 않으면 alert 로 "비밀번호가 일치하지 않습니다." 출력
         */
        const password = document.getElementById("memberPassword").value;
        if (passwordDB == password) {
            // 맞으면 updateForm 을 제출!
            document.updateForm.submit();
        } else {
            alert("비밀번호가 일치하지 않습니다!");
        }
    }
</script>
</html>
