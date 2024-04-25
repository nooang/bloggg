<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.id}번 게시글 수정</title>
<style type="text/css">
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
</style>
</head>
<body>
    <h1 id="id">${board.id}번 게시글 수정</h1>
    <form method="post" action="/board/modify">
        <input type="hidden" name="id" value="${board.id}">
        <div class="grid">
            <label for="subject">제목</label>
            <input type="text" id="subject" name="subject" value="${board.subject}">
    
            <label for="email">이메일</label>
            <input type="email" id="email" name="email" value="${board.email}">
    
            <label for="content">내용</label>
            <textarea name="content" id="content" style="height: 300px;">${board.content}</textarea>
    
            <div class="btn-group">
                <div class="right-align">
                    <input type="submit" value="수정">
                </div>
            </div>
        </div>
    </form>
    <script>
        const id = document.getElementById('id');
        let boardId = `${board.id}`;
        let idNum = boardId.match(/0*([1-9]\d*)$/)[1];

        document.title = `\${idNum}번 게시글 수정`;
        id.innerHTML = `\${idNum}번 게시글 수정`;
    </script>
</body>
</html>