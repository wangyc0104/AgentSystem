<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/inc/iframehead.jsp"></jsp:include>

<div class="addAppTitle">
	<h2>
		<font size="2">为${keywords.keywords}续费</font>
		<font>【当前账户余额：￥<span id="accountspan">${account.money}</span>】</font>
	</h2>
</div>

<div class="message">
	<s:actionmessage/>
	<s:actionerror/>
</div>

<div class="formdiv">
	<form action="/xufei.action" method="post">
		<div>
			<ul>
				<li>客户名称：
					<input type="text" 
						   id="customname" 
						   class="customname" 
						   readonly="readonly"
						   value="${keywords.customName}">
				</li>
				<li>关键词：
					<input type="text"
						   id="keyword"
						   class="keyword"
						   value="${keywords.keywords}">
					<span id="keywordtip" class="keywordtip"></span>
				</li>
				<li>服务类别：
					<s:select id="servicetype"
							  list="serviceType"
							  value="keywords.productType"
							  headerKey="" 
							  headerValue="--请选择--"
							  listKey="id"
							  listValue="configTypeName">
					</s:select>
				</li>
				<li>服务年限：
					<!-- 使用Struts2的bean标签显示下拉列表的一种不常用的方式 -->
					<select id="serviceyear">
						<option value="" selected="selected">--请选择--</option>
						<option value="1">1年</option>
						<option value="2">2年</option>
						<option value="3">3年</option>
						<option value="4">4年</option>
						<option value="5">5年</option>
						<option value="6">6年</option>
						<option value="7">7年</option>
						<option value="8">8年</option>
						<option value="9">9年</option>
						<option value="10">10年</option>
					</select>
				</li>
				<li>价格：
					<input class="price" id="price" type="text" readonly="readonly">
				</li>
				<li><input type="button" id="submitkeyword" value="续费提交"></li>
			</ul>
			<input type="hidden" id="kid" value="${keywords.id}">
		</div>
		<div class="clear"></div>
	</form>
</div>

<link rel="stylesheet" type="text/css" href="/css/xufei.css">
<script type="text/javascript" src="/js/xufei.js"></script>
</body>
</html>