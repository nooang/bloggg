<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.id}번 게시글 수정</title>
<style type="text/css">
    a:link, a:hover, a:visited, a:active {
        color: #333;
        text-decoration: none;
    }

    div.grid {
        display: grid;
        grid-template-columns: 80px 1fr;
        grid-template-rows: 28px 28px 320px 1fr;
        row-gap: 10px;
    }

    div.grid > div.btn-group {
        display: grid;
        grid-column: 1 / 3;
    }

    div.grid div.right-align {
        text-align: right;
    }

    label {
        padding-left: 10px;
    }

    button, input, textarea {
        padding: 10px;
    }

    input[type=file] {
        padding: 0;
    }

    div.errors {
        background-color: #ff00004a;
        opacity: 0.8;
        padding: 10;
        color: #333;
    }
    
    div.errors:last-child {
        margin-bottom: 15px;
    }
    
    ul.horizontal-list {
		padding: 0;
		margin: 0;
	}

	ul.horizontal-list > li {
		display: inline;
	}

    .membermenu {
        text-align: right;
    }
</style>
</head>
<body>
    <div class="membermenu">
        <jsp:include page="../member/membermenu.jsp"></jsp:include>
    </div>
    <h1 id="id">${board.id}번 게시글 수정</h1>
    <form:form modelAttribute="boardVO" method="post" action="/board/modify" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${board.id}">
        
        <div>
            <form:errors path="subject" element="div" cssClass="errors"/>
            <form:errors path="content" element="div" cssClass="errors"/>
        </div>

        <div class="grid">
            <label for="subject">제목</label>
            <input type="text" id="subject" name="subject" value="${board.subject}">
            
            <label for="file">첨부파일</label>
            <div>
                <input type="file" id="file" name="file">
                현재 업로드된 파일: ${board.originFileName}
            </div>
    
            <label for="content">내용</label>
            <textarea name="content" id="content" style="height: 300px;">${board.content}</textarea>
    
            <div class="btn-group">
                <div class="right-align">
                    <input type="submit" value="수정">
                    <a href="/board/view/${board.id}"><button>취소</button></a>
                </div>
            </div>
        </div>
    </form:form>
    <script>
        const id = document.getElementById('id');
        let boardId = `${board.id}`;
        let idNum = boardId.match(/0*([1-9]\d*)$/)[1];

        document.title = `\${idNum}번 게시글 수정`;
        id.innerHTML = `\${idNum}번 게시글 수정`;
    </script>
</body>
</html>