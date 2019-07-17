<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
  <head>
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- <link rel="stylesheet" type="text/css" href="styles.css"> -->
	<link rel="stylesheet" type="text/css" href="/css/public.css">
	<link rel="stylesheet" type="text/css" href="/css/main.css">
	
	<!-- humane提醒库 -->
	<link id='theme' rel='stylesheet' href='/humane/themes/libnotify.css'/>
	<script src='/humane/humane.js'></script>
	<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="/js/main.js"></script>
	<script type="text/javascript" src="/js/public.js"></script>
  </head>
  
  <body>
  	<!-- 顶部导航栏 -->
  	<div class="mbxnav">
  		账号：[<s:property value="user.userCode"/>] LOG操作日志
  	</div>
  	
  	<!-- 内容窗体 -->
  	<div class="container">
  		<div class="searcheruserdiv ope">
  			<ul>
  				<li>
  					<form action="/loglist.action" method="post">
  						<input type="hidden" 
  							   id="userCode" 
  							   name="user.userCode" 
  							   value="<s:property value="user.userCode"/>"/>
  						<input type="hidden" 
  							   id="userId" 
  							   name="user.id" 
  							   value="<s:property value="user.id"/>"/>
  						操作时间：
  						<input class="Wdate"
  							   name="logs.operateDatetime"
  							   size="15"
  							   readonly="true"
  							   onClick="WdatePicker()"
							   type="text"
  							   value="<s:date name="logs.operateDatetime" 
  							   format="yyyy-MM-dd"/>"/>
  						<input type="submit" value="查询"/>
  					</form>
  				</li>
  			</ul>
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
					<li><a href="/loglist.action?
								  pager.page=1&
								  user.id=<s:property value="user.id"/>&
								  user.userCode=<s:property value="user.userCode"/>&
								  logs.operateDatetime=<s:date name="operateDatetime" format="yyyy-MM-dd"/>"
						>首页</a></li>
					<!-- 前一段页面 -->
					<s:if test="pager.prevPages!=null">
						<s:iterator value="pager.prevPages" var="number">
							<li><a href="/loglist.action?
								  		  pager.page=<s:property value="#number"/>&
								  		  user.id=<s:property value="user.id"/>&
								 		  user.userCode=<s:property value="user.userCode"/>&
								  		  logs.operateDatetime=<s:date name="operateDatetime" format="yyyy-MM-dd"/>"
								><s:property value="#number"/></a>
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
							<li><a href="/loglist.action?
								  		  pager.page=<s:property value="#number"/>&
								  		  user.id=<s:property value="user.id"/>&
								 		  user.userCode=<s:property value="user.userCode"/>&
								  		  logs.operateDatetime=<s:date name="operateDatetime" format="yyyy-MM-dd"/>"
								 ><s:property value="#number"/></a>
							</li>
						</s:iterator>
					</s:if>
					<li><a href="/loglist.action?
								  pager.page=<s:property value="pager.pageCount"/>&
								  user.id=<s:property value="user.id"/>&
								  user.userCode=<s:property value="user.userCode"/>&
								  logs.operateDatetime=<s:date name="operateDatetime" format="yyyy-MM-dd"/>"
						>尾页</a>
					</li>
				</ul>
			</div>
  		</div>
  	</div>
  <link rel="stylesheet" href="/css/loglist.css"/>
  <script type="text/javascript" src="/medire/WdatePicker.js"></script>
  </body>
</html>