<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/inc/head.jsp"></jsp:include>

<div class="mbxnav">
	<!-- 导航 -->
	系统管理\ <a href="/rolelist.action">角色管理</a>
</div>

<div class="container">
	<!-- 添加面板 -->
	<div id="addRoleDiv" class="addRoleDivClass addback">
		<ul>
			<li class="lititle"><br>添加角色信息</li>
			<li>角色名称：<input type="text" id="a_roleName" name="role.roleName" /></li>
			<li>是否启用： <select id="a_isStart" name="role.isStart">
					<option value="1" selected="true">启用</option>
					<option value="0">停用</option>
			</select> <input id="addRoleSubmit" type="button" value="保存"> <input
				id="addCancel" type="reset" value="取消">
			</li>
		</ul>
	</div>
	<!-- 修改面板 -->
	<div id="modifyRoleDiv" class="addRoleDivClass modifyback">
		<ul>
			<li class="lititle"><br>修改角色信息</li>
			<input type="hidden" id="m_roleId">
			<li>角色名称：<input type="text" id="m_roleName" name="role.roleName" /></li>
			<li>是否启用： <select id="m_isStart" name="role.isStart">
					<option value="1" selected="true">启用</option>
					<option value="0">停用</option>
			</select> <input id="modifyRoleSubmit" type="button" value="保存"> <input
				id="modifyCancel" type="reset" value="取消">
			</li>
		</ul>
	</div>
	<!-- 添加按钮 -->
	<div class="addRoleDiv">
		<input id="addRole" type="button" value="新增">
	</div>
	<!-- 角色列表 -->
	<table>
		<thead>
			<tr>
				<th>角色名称</th>
				<th>创建时间</th>
				<th>是否启用</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="roleList" status="st">
				<tr>
					<td><s:property value="roleName" /></td>
					<td><s:date name="creationTime" format="yyyy-MM-dd HH:mm:ss" /></td>
					<td><s:if test="isStart==1">启用</s:if> <s:else>停用</s:else></td>
					<td>
						<span 
							class="modifyRole" 
							roleid="<s:property value="id"/>"
							rolename="<s:property value="roleName"/>"
							isstart="<s:property value="isStart"/>"
						>修改</span> 
						<span
							class="deleteRole" 
							roleid="<s:property value="id"/>"
							rolename="<s:property value="roleName"/>"
						>删除</span>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</div>

<jsp:include page="/inc/foot.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/css/rolelist.css">
<script type="text/javascript" src="/js/rolelist.js"></script>
</body>
</html>
<s:debug></s:debug>