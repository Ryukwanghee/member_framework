<%--
  Created by IntelliJ IDEA.
  User: rkh
  Date: 2024-02-20
  Time: 오후 5:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
    <h2>${sessionScope.loginEmail} 님 환영합니다.</h2>
    <button onclick="update()">내정보 수정하기</button>
    <button onclick="logout()">로그아웃</button>
</body>
<script>
    const update = () => {
        location.href = "/member/update";
    }

    const logout = () => {
        location.href = "/member/logout";
    }
</script>
</html>
