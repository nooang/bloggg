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
		grid-template-rows: 28px 28px 28px 1fr 28px;
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

	ul.page-nav {
		margin: 0;
		padding: 0;
		text-align: center;
	}

	ul.page-nav > li {
		display: inline-block;
		padding: 10px;
		color: #333;
	}

	ul.page-nav > li.active > a {
		color: #f00;
		font-weight: bold;
	}
</style>
</head>
<body>
	<div class="grid">
		<jsp:include page="../member/membermenu.jsp"></jsp:include>
		<div class="right-align">
			총 ${boardList.boardCnt}건의 게시글이 검색되었습니다.
		</div>
		
		<!-- 검색 영역 START -->
		<form action="/board/list" method="get" id="search-form">
			<div class="right-align">
				<select name="searchType" id="searchType">
					<option value="subject" ${searchBoardVO.searchType eq 'subject' ? 'selected' : ''}>제목</option>
					<option value="content" ${searchBoardVO.searchType eq 'content' ? 'selected' : ''}>내용</option>
				</select>
				<input type="text" name="searchKeyword" id="searchKeyword" value="${searchBoardVO.searchKeyword}">
				<input type="hidden" name="pageNo" id="pageNo">
				<button id="search-btn">검색</button>
			</div>
		</form>
		<!-- 검색 영역 END -->
		
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
								<td><strong>${board.memberVO.nickName}</strong> (${board.email})</td>
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
		
		<!-- 페이지네이션 시작 -->
		<div>
			<div>
				<ul class="page-nav">
					<c:if test="${searchBoardVO.hasPrevGroup}">
						<li>
							<a href="/board/list?pageNo=0?&listSize=${searchBoardVO.listSize}">처음</a>
						</li>
						<li>
							<a href="/board/list?pageNo=${searchBoardVO.prevGroupStartPageNo}?&listSize=${searchBoardVO.listSize}">이전</a>
						</li>
					</c:if>
					<c:forEach begin="${searchBoardVO.groupStartPageNo}" end="${searchBoardVO.groupEndPageNo}" step="1" var="p">
						<li class="${searchBoardVO.pageNo eq p ? 'active' : ''}">
							<a href="/board/list?pageNo=${p}&listSize=${searchBoardVO.listSize}">${p + 1}</a>
						</li>
					</c:forEach>
					<c:if test="${searchBoardVO.hasNextGroup}">
						<li>
							<a href="/board/list?pageNo=${searchBoardVO.nextGroupStartPageNo}?&listSize=${searchBoardVO.listSize}">다음</a>
						</li>
						<li>
							<a href="/board/list?pageNo=${searchBoardVO.pageCount-1}?&listSize=${searchBoardVO.listSize}">마지막</a>
						</li>
					</c:if>
				</ul>
			</div>
		</div>
		<!-- 페이지 네이션 끝 -->

		<c:if test="${not empty sessionScope._LOGIN_USER_}">
			<div class="right-align">
				<a href="/board/excel/download">엑셀 다운로드</a>
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

		const searchBtn = document.getElementById('search-btn');
		searchBtn.addEventListener('click', () => {
			movePage();
		})

		function movePage(pageNo = 0) {
			const pageNoElement = document.getElementById('pageNo');
			pageNoElement.value = pageNo;

			const searchForm = document.getElementById('search-form');
			searchForm.setAttribute('action', '/board/list');
			searchForm.setAttribute('method', 'get');
			searchForm.submit();
		}
	</script>
</body>
</html>