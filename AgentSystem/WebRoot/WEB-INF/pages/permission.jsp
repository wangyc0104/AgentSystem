<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/inc/head.jsp"></jsp:include>

<div class="mbxnav">
	<!-- 导航 -->
	系统管理\ <a href="permission.action">角色权限管理</a>
</div>

<div class="container">
	<ul>
		<!-- 角色列表 -->
		<li class="jslist">
			<ul>
				<s:iterator value="roleList">
					<li>
						<div id="sidebar">
							<a target="funclist" href="/funclist.action?roleId=<s:property value="id"/>">
							<s:property value="roleName"/></a>
						</div>
					</li>
				</s:iterator>
			</ul>
		</li>
		<!-- 功能列表 -->
		<li class="iframeli">
			<iframe id="funclist" name="funclist"
					width="100%" height="99%" src=""></iframe>
		</li>
	</ul>
</div>

<jsp:include page="/inc/foot.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/css/permission.css">
<script type="text/javascript" src="/js/permission.js"></script>
</body>
</html>
<s:debug></s:debug>