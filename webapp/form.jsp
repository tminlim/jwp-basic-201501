<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include file="/include/tags.jspf"%><!DOCTYPE html>
<html>
<head>
<%@ include file="/include/header.jspf"%>
</head>
<body>
	<div id="main">
		<c:set var="method" value="post" />

		<form name="questionForm" action="/save.next" method="${method}">
			<c:choose>
				<c:when test="${selectedQusetion == null}">
				</c:when>
				<c:otherwise>
					<input type="hidden" name="questionId" value="${selectedQusetion.questionId}">
				</c:otherwise>
			</c:choose>
			<table>
				<tr>
					<td width="80">글쓴이</td>
					<c:choose>
						<c:when test="${selectedQusetion == null}">
							<td><input type="text" name="writer" size="40" /></td>
						</c:when>
						<c:otherwise>
							<td><input type="text" name="writer" size="40"
								value="${selectedQusetion.writer}" /></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td width="80">제목</td>
					<c:choose>
						<c:when test="${selectedQusetion == null}">
							<td><input type="text" name="title" size="70" /></td>
						</c:when>
						<c:otherwise>
							<td><input type="text" name="title" size="70"
								value="${selectedQusetion.title}" /></td>
						</c:otherwise>
					</c:choose>

				</tr>
				<tr>
					<td width="80">내용</td>
					<c:choose>
						<c:when test="${selectedQusetion == null}">
							<td><textarea name="contents" rows="5" cols="130"></textarea></td>
						</c:when>
						<c:otherwise>
							<td><textarea name="contents" rows="5" cols="130">${selectedQusetion.contents}</textarea></td>
						</c:otherwise>
					</c:choose>

				</tr>
			</table>
			<c:choose>
				<c:when test="${selectedQusetion == null}">
					<input type="submit" value="질문하기" name="button" />
				</c:when>
				<c:otherwise>
					<input type="submit" value="수정하기" name="button" />
				</c:otherwise>
			</c:choose>

		</form>
	</div>
</body>
</html>