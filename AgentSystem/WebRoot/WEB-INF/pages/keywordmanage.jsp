<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/inc/head.jsp"></jsp:include>


<div class="mbxnav">
	<!-- 导航 -->
	代理商管理\ <a href="/keywordmanage.action">关键词申请管理</a>
</div>

<div class="container">
	<!-- 搜索框 -->
	<div>
		<ul>
			<li>
				<form action="/keywordmanage.action" method="post">
					关键词：<input type="text" name="keywords.keywords" value="${keywords.keywords}">
					<input type="submit" value="查询">
				</form>
			</li>
		</ul>
	</div>
	
	
	<table>
		<thead>
			<tr>
				<th>序号</th>
				<th>关键词</th>
				<th>客户名称</th>
				<th>代理商</th>
				<th>申请年限</th>
				<th>申请日期</th>
				<th>到期日期</th>
				<th>申请到期状态</th>
				<th>审核状态</th>
				<th>使用状态</th>
				<th>APP开通状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="pager.items" status="st">
				<tr>
					<td>${st.index+1}</td>
					<td>${keywords}</td>
					<td>${customName}</td>
					<td>${agentName}</td>
					<td>${serviceYears}</td>
					<td><s:date name="regDatetime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:date name="regPassDatetime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<s:if test="isPass==0">不过期</s:if>
						<s:if test="isPass==1">预注册过期</s:if>
						<s:if test="isPass==2">正式注册过期</s:if>
					</td>
					<td>
						<s:if test="checkStatus==0">已申请</s:if>
						<s:if test="checkStatus==1">审核中</s:if>
						<s:if test="checkStatus==2">已通过</s:if>
						<s:if test="checkStatus==3">未通过</s:if>
					</td>
					<td>
						<s:if test="isUse==0">未使用</s:if>
						<s:if test="isUse==1">已使用</s:if>
					</td>
					<td>
						<s:if test="openApp==0"><font color="red">未开通</font></s:if>
						<s:if test="openApp==1"><font color="green">已开通</font></s:if>
					</td>
					<td>
						<s:if test="isUse==1">
							<s:if test="checkStatus==2">
								<s:if test="openApp==0">
									<a class="openapp" 
									   kid="${id}"
									   keyword="${keywords}">开通APP</a>
								</s:if>
								<a class="xufei" 
								   kid="${id}"
								   keyword="${keywords}">续费</a>
							</s:if>
							<s:elseif test="isPass==2">
								<a class="xufei" 
								   kid="${id}"
								   keyword="${keywords}">续费</a>
								<a class="deletekeywords" 
								   kid="${id}"
								   keyword="${keywords}">删除</a>
							</s:elseif>
							<s:else>无操作</s:else>
							<s:if test="checkStatus==3">
								<a class="deletekeywords" 
								   kid="${id}"
								   keyword="${keywords}">删除</a>
							</s:if>
						</s:if>
						<s:else>无操作</s:else>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<!-- 分页按钮 -->
	<div class="pagination pagination-centered">
		<ul>
			<li>
				<a href="/keywordmanage.action?pager.page=1&keywords.keywords=<s:property value="keywords.keywords"/>">
					首页
				</a>
			</li>
			<!-- 前一段页面 -->
			<s:if test="pager.prevPages!=null">
				<s:iterator value="pager.prevPages" var="number">
					<li>
						<a href="/keywordmanage.action?
								  pager.page=<s:property value="#number"/>&
								  keywords.keywords=<s:property value="keywords.keywords"/>">
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
						<a href="/keywordmanage.action?
								  pager.page=<s:property value="#number"/>&
								  keywords.keywords=<s:property value="keywords.keywords"/>">
							<s:property value="#number"/>
						</a>
					</li>
				</s:iterator>
			</s:if>
			<!-- 尾页 -->
			<li>
				<a href="/keywordmanage.action?
						  pager.page=<s:property value="pager.pageCount"/>&
						  keywords.keywords=<s:property value="keywords.keywords"/>">
					尾页
				</a>
			</li>
		</ul>
	</div>
</div>

<jsp:include page="/inc/foot.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/css/keywordmanage.css">
<script type="text/javascript" src="/js/keywordmanage.js"></script>
</body>
</html>
<s:debug></s:debug>