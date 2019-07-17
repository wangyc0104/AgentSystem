<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/inc/head.jsp"></jsp:include>


<div class="mbxnav">
	<!-- 导航 -->
	代理商管理\当前账户信息\ <a href="/accountdetail.action">账户明细</a>
</div>

<div class="container">
	
	<!-- accountdetail列表 -->
	<table>
		<thead>
			<tr>
				<th>序号</th>
				<th>财务类型</th>
				<th>账务资金</th>
				<th>账户余额</th>
				<th>备注信息</th>
				<th>明细时间</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="pager.items" status="st">
				<tr>
					<td><s:property value="#st.index+1" /></td>
					<td><s:property value="detailTypeName" /></td>
					<td>￥<s:property value="money" /></td>
					<td>￥<s:property value="accountMoney" /></td>
					<td><s:property value="memo" /></td>
					<td><s:date name="detailDateTime" format="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<!-- 分页按钮 -->
	<div class="pagination pagination-centered">
		<ul>
			<li>
				<a href="/accountdetail.action?pager.page=1&uname=<s:property value="uname"/>">
					首页
				</a>
			</li>
			<!-- 前一段页面 -->
			<s:if test="pager.prevPages!=null">
				<s:iterator value="pager.prevPages" var="number">
					<li>
						<a href="/accountdetail.action?pager.page=<s:property value="#number"/>">
							<s:property value="#number"/>
						</a>
					</li>
				</s:iterator>
			</s:if>
			<!-- 当前页面 -->
			<li class="active">
				<a href="#"><s:property value="pager.page"/></a>
			</li>
			<!-- 后一段页面 -->
			<s:if test="pager.nextPages!=null">
				<s:iterator value="pager.nextPages" var="number">
					<li>
						<a href="/accountdetail.action?pager.page=<s:property value="#number"/>">
							<s:property value="#number"/>
						</a>
					</li>
				</s:iterator>
			</s:if>
			<!-- 尾页 -->
			<li>
				<a href="/accountdetail.action?pager.page=<s:property value="pager.pageCount"/>">
					尾页
				</a>
			</li>
		</ul>
	</div>
</div>

<jsp:include page="/inc/foot.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/css/accountdetail.css">
</body>
</html>
<s:debug></s:debug>