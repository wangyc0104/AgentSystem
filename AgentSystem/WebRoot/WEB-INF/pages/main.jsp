<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/inc/head.jsp"></jsp:include>
<div class="mbxnav">
代理商管理\当前账户信息
</div>
<div class="container">
	<div class="userInfo">
		<ul>
			<li>
				您好，<b><s:property value="currentUser.userName"/></b>！
				您上次登录时间：<s:date name="currentUser.lastLoginTime" format="yyyy-MM-dd HH:mm:ss"/>
			</li>
			<li>
				<!-- 显示Account对象的属性 -->
				您当前账户余额：
				<s:if test="account==null">您还没有开户！</s:if>
				<s:else><s:property value="account.money"/></s:else>
				<a href="/accountdetail.action">&emsp;查看账户明细</a>			
			</li>
		</ul>
	</div>
	
</div>
<jsp:include page="/inc/foot.jsp"></jsp:include>
</body>
</html>
