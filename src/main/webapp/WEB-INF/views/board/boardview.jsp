<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.id}번 게시글 조회</title>
<style type="text/css">
    a:link, a:hover, a:visited, a:active {
        color: #333;
        text-decoration: none;
    }

    div.grid {
        display: grid;
        grid-template-columns: 80px 1fr;
        grid-template-rows: 28px 28px 28px 28px 28px 28px 320px 1fr;
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

    a {
        border: solid 1px #ccc;
        border-radius: 5px;
        padding: 5px;
    }

    a:hover {
        background-color: #ccc;
    }
</style>
</head>
<body>
    <h1 id="id">${board.id}번 게시글</h1>
    <div class="grid">
        <label for="subject">제목</label>
        <div>${board.subject}</div>
        <label for="email">이메일</label>
        <div>${board.email}</div>
        <label for="viewCnt">조회수</label>
        <div>${board.viewCnt}</div>
        <label for="originFileName">첨부파일</label>
        <div>
            <a href="/board/file/download/${board.id}">${board.originFileName}</a>
        </div>
        <label for="CrtDt">등록일</label>
        <div>${board.crtDt}</div>
        <label for="mdfyDt">수정일</label>
        <div id="mdfy-dt">${board.mdfyDt}</div>
        <label for="content">내용</label>
        <div>${board.content}</div>
        <div class="btn-group">
            <div class="right-align">
                <a href="/board/list">게시판으로</a>
                <a href="/board/modify/${board.id}">수정</a>
                <a href="/board/delete/${board.id}" id="del">삭제</a>
            </div>
        </div>
    </div>
    <script>
        const id = document.getElementById('id');
        const mdfyDt = document.getElementById('mdfy-dt');
        const del = document.getElementById('del');
        let boardId = `${board.id}`;
        let idNum = boardId.match(/0*([1-9]\d*)$/)[1];

        document.title = `\${idNum}번 게시글`;
        id.innerHTML = `\${idNum}번 게시글`;

        if (`${board.mdfyDt}` === '') {
            mdfyDt.innerHTML = '-';
        }

        del.addEventListener('click', (e) => {
            if (!confirm('정말 삭제하시겠습니까?')) {
                e.preventDefault();
            }
        });
    </script>
</body>
</html>