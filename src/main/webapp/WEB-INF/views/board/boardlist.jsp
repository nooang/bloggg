<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블로그 게시판입니다.</title>
<style type="text/css">
	a:link, a:hover, a:visited, a:active {
		color: #333;
		text-decoration: none;
	}

	table.table {
		border-collapse: collapse;
		border: 1px solid #DDD;
	}

	table.table > thead > tr {
		background-color: #fff;
	}

	table.table th, table.table td {
		border-right: 1px solid #f0f0f0;
	}

	table.table th:last-child, table.table td:last-child {
		border-right: none;
	}

	table.table > tbody tr:nth-child(odd) {
		background-color: #f5f5f5;
	}

	table.table > tbody tr:hover {
		background-color: #fafafa;
	}

	table.table > tbody td {
		padding: 10px;
		color: #333;
	}

	div.grid {
		display: grid;
		grid-template-columns: 1fr;
		grid-template-rows: 28px 28px 1fr 28px;
		row-gap: 10px;
	}

	div.grid div.right-align {
		text-align: right;
	}

	.center-align {
		text-align: center;
	}

	ul.horizontal-list {
		padding: 0;
		margin: 0;
	}

	ul.horizontal-list > li {
		display: inline;
	}
</style>
</head>
<body>
	<div class="grid">
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
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div class="right-align">
			총 ${boardList.boardCnt}건의 게시글이 검색되었습니다.
		</div>
		<table class="table">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>이메일</th>
					<th>조회수</th>
					<th>등록일</th>
					<th>수정일</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty boardList.boardList}">
						<c:forEach items="${boardList.boardList}" var="board">
							<tr>
								<td name="id">${board.id}</td>
								<td><a href="/board/view?id=${board.id}">${board.subject}</a></td>
								<td>${board.email}</td>
								<td>${board.viewCnt}</td>
								<td>${board.crtDt}</td>
								<td>${board.mdfyDt}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="6" class="center-align">등록된 게시글이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<c:if test="${not empty sessionScope._LOGIN_USER_}">
			<div class="right-align">
				<a href="/board/write">글쓰기</a>
			</div>
		</c:if>
	</div>
	<script>
		const id = document.getElementsByName('id');

		for (let i = 0; i < id.length; i++) {
			let idString = id[i].innerText;
			let idNum = idString.match(/0*([1-9]\d*)$/)[1];
			id[i].innerText = `\${idNum}`
		}
	</script>
</body>
</html>