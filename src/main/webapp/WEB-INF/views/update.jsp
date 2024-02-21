<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update</title>
</head>
<body>
<form action="/member/update" method="post" name="updateForm">
    id: <input type="text" name="id" value="${member.id}"readonly>
    email: <input type="text" name="memberEmail" value="${member.memberEmail}" readonly>
    password: <input type="text" name="memberPassword" id="memberPassword">
    name: <input type="text" name="memberName" value="${member.memberName}" readonly>
    age: <input type="text" name="memberAge" value="${member.memberAge}">
    mobile: <input type="text" name="memberMobile" value="${member.memberMobile}">
    <input type="button" value="수정" onclick="update()"> <%--type을 submit으로 하면 action에 주어진 url쪽으로 값이 넘어가는데 다른 어떤 기능을 주고 싶다하면 button으로--%>

</form>
</div>
</body>
<script>
    const update = () => {
        const passwordDB = '${member.memberPassword}';
        const password = document.getElementById("memberPassword").value;
        if (passwordDB == password) {
            document.updateForm.submit();
        } else {
            alert("비밀번호가 일치하지 않습니다!");
        }
    }
</script>
</html>