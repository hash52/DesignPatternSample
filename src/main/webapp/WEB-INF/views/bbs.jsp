<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
 *
 *総合演習(BBS制作)
 *@author HirokiHashi
 *所属:株式会社ラクス
 *作成日:2016.11.14
 *
--%>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- 勉強用コメント -->
		<!-- 絶対パスはwebapp直下から記述 -->
		<link href="/css/bbs.css" rel="stylesheet">
		<!-- ../css/bbs.css(相対パス)にすると、bbs.jspのurlが変わったときにcssを参照できなくなる -->
		<!-- コントローラの@RequestMappingでurlは簡単に変えられる -->
		
		
<title>BBS</title>
</head>
<body>
	<h2>掲示板アプリケーション</h2>
	<div class="errormessage">
		<c:out value="${emptyNameMessage}"></c:out><br>
		<c:out value="${emptyContentMessage}"></c:out>
	</div>
	<div class="articleform">
		<form:form modelAttribute="articleForm" action="/bbs/writearticle">
			投稿者名:<form:input path="name"/><br>
			記事内容：<form:textarea path="content"/><br>
			<input type="submit" value="記事投稿">
		</form:form>
	</div>
	
	<c:forEach var="article" items="${articleList}">
		<div class="articleandcomennts">
			投稿id:<c:out value="${article.id}"></c:out><br>
			名前：<c:out value="${article.name}"></c:out><br>
			内容：<c:out value="${article.content}"></c:out><br>
			<form:form modelAttribute="deleteForm" action="/bbs/delete">
				<form:hidden value="${article.id}" path="articleId"/>
				<input type="submit" value="記事削除"><br>
			</form:form>
			<br>
			<c:forEach var="comment" items="${commentList}">
				<c:if test="${article.id == comment.articleId}">
					<div class="comment">
					コメントID：<c:out value="${comment.id}"></c:out><br>
					名前:<c:out value="${comment.name}"></c:out><br>
					コメント：<c:out value="${comment.content}"></c:out><br><br>
					</div>
				</c:if>
			</c:forEach>
			<c:if test="${articleIdOfEmptyCommentForm == article.id}">
				<div class="errormessage">
					<c:out value="${emptyCommentNameMessage}"></c:out><br>
					<c:out value="${emptyCommentContentMessage}"></c:out>
				</div>
			</c:if>
			<form:form modelAttribute="commentForm" action="/bbs/writecomment">
				投稿者名:<form:input path="name"/><br>
				記事内容：<form:textarea path="content"/><br>
						<form:hidden value="${article.id}" path="articleId"/>
				<input type="submit" value="コメント投稿">
			</form:form>
		</div>
	</c:forEach>
</body>
</html>