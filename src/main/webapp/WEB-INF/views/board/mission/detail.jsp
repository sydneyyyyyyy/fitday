<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>인증 게시판 상세 조회</title>

    <style type="text/css">
        #allDiv{
            height: 600px;
            width: 80%;
            margin: auto;
            margin-top: 20px;
            display: block;
            justify-content: center;
            text-align: center;
        }
        form {
            width: 80%;
        }
        #buttonDiv {
            margin-top: 50px;
            text-align: center;
        }
        #contentsDiv{
            margin: 20px;
        }

    </style>
</head>
<body>
<jsp:include page="../../header.jsp"/>
<div id="allDiv">
    <div>
        <h2>${mission.title}</h2>
        <h6>작성자 | ${mission.date} | 조회수 ${mission.viewCount}</h6>
    </div>

    <div id="contentsDiv">
        <img src="<%=request.getContextPath()%>/uploads/${mission.fileName}" onerror="this.src='<%=request.getContextPath()%>/images/missionDefault.png'" width="600">
        <p>${mission.content}</p>
        <c:if test="${currentUser.getUserSeq() == board.userSeq}">
            <div id="buttonDiv">
                <button type="button" class="btn btn-primary" onclick="location.href='/board/mission/update/${mission.boardSeq}'">수정하기</button>
                <button type="button" class="btn btn-primary" onclick="confirmDelete(${mission.boardSeq})">삭제하기</button>
            </div>
        </c:if>
    </div>


</div>
<jsp:include page="../../footer.jsp"/>
<script>
    function confirmDelete(boardSeq){
        if (confirm('삭제하시겠습니까?')){
            location.href='/board/mission/delete/' + boardSeq;
            alert("삭제되었습니다.")
        }
    }
</script>
</body>
</html>
