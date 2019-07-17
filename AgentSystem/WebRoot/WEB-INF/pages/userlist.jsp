<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/inc/head.jsp"></jsp:include>


<div class="mbxnav">
	<!-- 导航 -->
	系统管理\ <a href="/userlist.action">用户管理</a>
</div>

<div class="container">
	<!-- 添加面板 -->
	<div id="addUserDiv" class="addUserDivClass addback">
		<ul>
			<li class="lititle"><br>添加代理商用户信息</li>
			<li>登录账号：<input type="text" id="a_userCode" name="user.userCode" /><span></span></li>
			<li>登录名称：<input type="text" id="a_userName" name="user.userName" /><span></span></li>
			<li>密码：<input type="password" 
						   id="a_userPassword" 
						   name="user.userPassword" 
					 	   value="123456">
				<span>*默认初始密码为123456</span>
			</li>
			<li>角色：
				<s:select id="a_roleId"
						  name="user.roleId" 
						  headerKey="" 
						  headerValue="——请选择——" 
						  list="roleList" 
						  listKey="id" 
						  listValue="roleName">
				</s:select>
			</li>
			<li>是否启用： 
				<select id="a_isStart" name="user.isStart">
					<option value="1" selected="true">启用</option>
					<option value="0">停用</option>
				</select>
				<span></span>
				<input id="addUserSubmit" type="button" value="保存"> 
				<input id="addCancel" type="reset" value="取消">
			</li>
		</ul>
	</div>
	
	<!-- 修改面板 -->
	<div id="modifyUserDiv" class="addUserDivClass modifyback">
		<ul>
			<li class="lititle"><br>修改代理商用户信息</li>
			<li><input type="hidden" id="m_userId" name="user.id"/></li>
			<li>登录账号：<input type="text" id="m_userCode" name="user.userCode" /><span></span></li>
			<li>登录名称：<input type="text" id="m_userName" name="user.userName" /><span></span></li>
			<li>密码：<input type="password" 
						   id="m_userPassword" 
						   name="user.userPassword" 
					 	   value="123456">
				<span>*默认初始密码为123456</span>
			</li>
			<li>角色：
				<s:select id="m_roleId"
						  name="user.roleId" 
						  headerKey="" 
						  headerValue="——请选择——" 
						  list="roleList" 
						  listKey="id" 
						  listValue="roleName">
				</s:select>
			</li>
			<li>是否启用： 
				<select id="m_isStart" name="user.isStart">
					<option value="1" selected="true">启用</option>
					<option value="0">停用</option>
				</select>
				<span></span>
				<input id="modifyUserSubmit" type="button" value="保存"> 
				<input id="modifyCancel" type="reset" value="取消">
			</li>
		</ul>
	</div>
	
	<!-- 添加按钮 -->
	<div class="addUserDiv">
		<input id="addUser" type="button" value="新增">
	</div>
	
	<!-- 用户搜索框 -->
	<div>
		<form action="/userlist.action" method="post">
			用户名称：<input type="text" name="uname" value="<s:property value="uname"/>">
			角色：<s:select name="user.roleId" headerKey="" headerValue="——请选择——" list="roleList" listKey="id" listValue="roleName"></s:select>
			是否启用：<s:select list="#{'1':'启用','0':'未启用' }" name="user.isStart"></s:select>
			<input type="submit" value="查询">
		</form>
	</div>
	
	<!-- 用户列表 -->
	<table>
		<thead>
			<tr>
				<th>登录账号</th>
				<th>用户名称</th>
				<th>角色</th>
				<th>创建时间</th>
				<th>是否启用</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="pager.items" status="st">
				<tr>
					<td><s:property value="userCode" /></td>
					<td><s:property value="userName" /></td>
					<td><s:property value="roleName" /></td>
					<td><s:date name="creationTime" format="yyyy-MM-dd HH:mm:ss" /></td>
					<td><s:if test="isStart==1">启用</s:if> <s:else>停用</s:else></td>
					<td>
						<span class="modifyUser" 
							  userid='<s:property value="id"/>'
							  usercode='<s:property value="userCode"/>'
							  username='<s:property value="userName"/>'
							  userpassword='<s:property value="userPassword"/>'
							  isstart='<s:property value="isStart"/>'
							  roleid='<s:property value="roleId"/>'
						>修改</span> 
						<span>|</span>
						<span class="deleteUser" 
							  userid='<s:property value="id"/>'
							  usercode='<s:property value="userCode"/>'
							  roleid='<s:property value="roleId"/>'
						>删除</span>
						<span>|</span>
						<span>
							<a href="javascript:ymPrompt.win('/yfklist.action?user.id=<s:property value="id"/>&user.userCode=<s:property value="userCode"/>',1000,500,'预付款',null,null,null,true)"
							>预付款</a>
						</span>
						<span>|</span>
						<span>
							<a href="javascript:ymPrompt.win('/loglist.action?user.id=<s:property value="id"/>&user.userCode=<s:property value="userCode"/>',1000,500,'LOG日志',null,null,null,true)"
							>LOG日志</a>
						</span>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<!-- 分页按钮 -->
	<div class="pagination pagination-centered">
		<ul>
			<li><a href="/userlist.action?
						  pager.page=1&
						  uname=<s:property value="uname"/>&
						  user.roleId=<s:property value="user.roleId"/>&
						  user.isStart=<s:property value="user.isStart"/>"
				>首页</a></li>
			<!-- 前一段页面 -->
			<s:if test="pager.prevPages!=null">
				<s:iterator value="pager.prevPages" var="number">
					<li><a href="/userlist.action?
								  pager.page=<s:property value="#number"/>&
								  uname=<s:property value="uname"/>&
								  user.roleId=<s:property value="user.roleId"/>&
								  user.isStart=<s:property value="user.isStart"/>"
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
					<li><a href="/userlist.action?
								  pager.page=<s:property value="#number"/>&
								  uname=<s:property value="uname"/>&
								  user.roleId=<s:property value="user.roleId"/>&
								  user.isStart=<s:property value="user.isStart"/>"
						><s:property value="#number"/></a>
					</li>
				</s:iterator>
			</s:if>
			<!-- 尾页 -->
			<li><a href="/userlist.action?
						  pager.page=<s:property value="#number"/>&
						  uname=<s:property value="uname"/>&
						  user.roleId=<s:property value="user.roleId"/>&
						  user.isStart=<s:property value="user.isStart"/>"
				>尾页</a>
			</li>
		</ul>
	</div>
</div>

<jsp:include page="/inc/foot.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/css/userlist.css">
<script type="text/javascript" src="/js/userlist.js"></script>
</body>
</html>
<s:debug></s:debug>