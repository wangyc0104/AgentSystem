<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/inc/head.jsp"></jsp:include>


<div class="mbxnav">
	<!-- 导航 -->
	系统管理\ <a href="/caiwu.action">用户管理</a>
</div>

<div class="container">
	<div class="searchuserdiv">
		<div>搜索用户：<input id="searchUserText" type="text">输入后自动搜索</div>
		<div class="searchresult" id="searchresult"></div>
		<div class="searchuserdiv ope">
			<ul>
				<li>操作类型：
					<s:select id="zijintype"
							  headerKey=""
							  headerValue="--请选择--"
							  list="accountConfigList" 
							  listKey="configTypeValue" 
							  listValue="configTypeName">
					</s:select>
				</li>
				<li>操作资金：<input type="text" id="zijin"><br>
					重要提示：输入的资金数，<b>正数(1000)</b>向账户输入1000元，<b>负数(-1000)</b>向账户减少1000元
				</li>
				<li>操作备注：
					<textarea id="memo"></textarea>
				</li>
				<li><input type="button" id="caiwuok" value="确定"></li>
				<li class="tip">
					操作提示：<span id="systemtip">输入的资金数，<b>正数(1000)</b>向账户输入1000元，<b>负数(-1000)</b>向账户减少1000元</span>
				</li>
			</ul>
		</div>
	</div>
</div>

<jsp:include page="/inc/foot.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/css/caiwu.css">
<script type="text/javascript" src="/js/caiwu.js"></script>
</body>
</html>
<s:debug></s:debug>