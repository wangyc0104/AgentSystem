<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/inc/head.jsp"></jsp:include>


<div class="mbxnav">
	<!-- 导航 -->
	<a href="/main.action">代理商管理</a>\当前账户信息\ <a href="/mylogs.action">查看操作日志</a>
</div>

<!-- 内容窗体 -->
  	<div class="container">
  		
  			<table>
  				<tr>
  					<td>登录账户</td>
  					<td>操作信息</td>
  					<td>操作时间</td>
  				</tr>
  				<s:iterator value="pager.items">
  					<tr>
	  					<td><s:property value="userName"/></td>
	  					<td><s:property value="operateInfo"/></td>
	  					<td><s:date name="operateDatetime" format="yyyy-MM-dd"/></td>
  					</tr>
  				</s:iterator>
  			</table>
  			<!-- 分页按钮 -->
  			<div class="pagination pagination-centered">
				<ul>
					<li><a href="/mylogs.action?pager.page=1">首页</a></li>
					<!-- 前一段页面 -->
					<s:if test="pager.prevPages!=null">
						<s:iterator value="pager.prevPages" var="number">
							<li>
								<a href="/mylogs.action?pager.page=<s:property value="#number"/>">
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
								<a href="/mylogs.action?pager.page=<s:property value="#number"/>">
									<s:property value="#number"/>
								</a>
							</li>
						</s:iterator>
					</s:if>
					<li>
						<a href="/mylogs.action?pager.page=<s:property value="pager.pageCount"/>">
							尾页
						</a>
					</li>
				</ul>
			</div>
  		</div>
  	</div>
  <link rel="stylesheet" href="/css/logs.css"/>
  <script type="text/javascript" src="/js/logs.js"></script>
</body>
</html>
<s:debug></s:debug>