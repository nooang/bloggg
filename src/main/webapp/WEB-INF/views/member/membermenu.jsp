<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="right-align">
    <ul class="horizontal-list">
        <c:choose>
            <c:when test="${empty sessionScope._LOGIN_USER_}">
                <li><a href="/member/regist">회원가입</a></li>
                <li><a href="/member/login">로그인</a></li>
            </c:when>
            <c:otherwise>
                <li style="margin-right: 15px;">
                    <strong>${sessionScope._LOGIN_USER_.nickName}</strong> (${sessionScope._LOGIN_USER_.email})
                </li>
                <li>
                    <a href="/member/logout">로그아웃</a>
                </li>
                <li>
                    <a href="/member/delete-me" id="delete-me">탈퇴</a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
<script>
    const deleteMe = document.getElementById('delete-me');
    const user = `${sessionScope._LOGIN_USER_}`;
    if (user) {
        deleteMe.addEventListener('click', (e) => {
            if (!confirm('정말 탈퇴하시겠습니까?')) {
                e.preventDefault();
            }
        })
    }
</script>