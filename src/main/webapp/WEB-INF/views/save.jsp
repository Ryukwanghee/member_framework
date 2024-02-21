<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>save</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
    <form id="signupForm" action="/member/save" method="post">
        <%--<input type="text" name="memberEmail" placeholder="이메일" id="memberEmail" onblur="emailCheck()"> --%> <%--onblur는 이벤트처리방법, 입력창 벗어났을 때 함수 호출하는 이벤트처리방식--%>
            <input type="text" name="memberEmail" placeholder="이메일" id="memberEmail">
            <!-- 이메일 중복 확인 버튼 추가 -->
            <button type="button" id="emailCheckBtn">이메일 중복 확인</button>
            <p id="check-result"></p>

        <input type="text" name="memberPassword" placeholder="비밀번호">
        <input type="text" name="memberName" placeholder="이름">
        <input type="text" name="memberAge" placeholder="나이">
        <input type="text" name="memberMobile" placeholder="전화번호">
        <input type="submit" value="회원가입">
    </form>
</body>
<script>
// 이메일 입력값을 가져오고,
// 입력값을 서버로 전송하고 똑같은 이메일이 있는지 체크한 후
// 사용 가능 여부를 이메일 입력창 아래에 표시
const emailCheck = () => {
    const email = document.getElementById("memberEmail").value;
    const checkResult = document.getElementById("check-result");
    console.log("입력한 이메일", email);
    $.ajax({
        // 요청방식: post, url: "email-check", 데이터: 이메일
        type: "post",               /* post방식을 */
        url: "/member/email-check", /* 이 url로 보내겠다*/
        data: {
            "memberEmail": email /*사용자가 입력한 이메일 값*/
        },
        success: function(res) {
            console.log("요청성공", res);
            if (res == "ok") {
                console.log("사용가능한 이메일");
                checkResult.style.color = "green";
                checkResult.innerHTML = "사용가능한 이메일";
            } else {
                console.log("이미 사용중인 이메일");
                checkResult.style.color = "red";
                checkResult.innerHTML = "이미 사용중인 이메일";
            }
        },
        error: function(err) {
            console.log("에러발생", err);
        }
    });
}

// 이메일 중복 확인 버튼에 클릭 이벤트 리스너 추가
document.getElementById("emailCheckBtn").addEventListener("click", emailCheck);

document.getElementById("signupForm").addEventListener("submit", function(event) {
    const isEmailValid = checkResult.innerHTML !== "이미 사용중인 이메일";
    if (!isEmailValid) {
        event.preventDefault(); // 폼 제출 막기
        alert("중복된 이메일입니다. 다른 이메일을 사용해 주세요.");
    }
});

</script>
</html>
