<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/inc/iframehead.jsp"></jsp:include>

<div class="addAppTitle">
	<h2>开通APP</h2>
</div>

<div class="message">
	<s:actionmessage/>
	<s:actionerror/>
</div>

<div class="formdiv">
	<form action="/modifyapp.action" method="post">
		<div>
			<input type="hidden" name="keywords.id" value="${keywords.id}">
			<input type="hidden" name="keywords.openApp" value="1">
			<input type="hidden" name="keywords.keywords" value="${keywords.keywords}">
			<input type="hidden" name="keywords.customName" value="${keywords.customName}">
			<input type="hidden" name="keywords.price" value="${keywords.price}">
			<input type="hidden" name="systemConfig.configTypeName" value="${systemConfig.configTypeName}">
			<ul>
				<li>登录账号：<input type="text" name="keywords.appUserName" value="${keywords.appUserName}"></li>
				<li>登录密码：<input type="text" name="keywords.appPassword" value="${keywords.appPassword}"></li>
				<li>关键词：${keywords.keywords}</li>
				<li>企业名称：${keywords.customName}</li>
				<li>价格：￥${keywords.price}</li>
				<li>服务类型：${systemConfig.configTypeName}</li>
			</ul>
		</div>
		<div class="clear"></div>
		<div class="submitdiv"><input type="submit" value="开通APP"></div>
	</form>
</div>

<link rel="stylesheet" type="text/css" href="/css/openapp.css">
<script type="text/javascript" src="/js/openapp.js"></script>
</body>
</html>