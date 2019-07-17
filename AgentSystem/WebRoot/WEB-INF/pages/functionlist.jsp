<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/inc/iframehead.jsp"></jsp:include>
<div class="container">
	<h3>功能列表</h3>
	<!-- 操作按钮 -->
	<div class="btndiv">
		<input type="hidden" id="roleid" value="<s:property value="roleId"/>">
		<input id="saverolefunc" type="button" value="保存">
		<input id="cancel" type="button" value="取消">
	</div>
	
	<!-- 功能表格 -->
	<table>
		<thead>
			<tr>
				<th><input type="checkbox" id="cball">全选/全不选</th>
				<th>功能名称</th>
				<th>功能URL</th>
				<th>创建时间</th>
				<th>是否启用</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="funcList">
			<tr>
				<td>
					<s:if test="check"><input type="checkbox" class="cb" checked="checked" value="<s:property value="id"/>"></s:if>
					<s:else><input type="checkbox" class="cb" value="<s:property value="id"/>"></s:else>
				</td>
				<td><s:property value="functionName"/></td>
				<td><s:property value="funcUrl"/></td>
				<td><s:date name="creationTime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td><s:if test="isStart==1">启用</s:if><s:else>停用</s:else></td>
			</tr>
			</s:iterator>
		</tbody>
	</table>
</div>

<link rel="stylesheet" type="text/css" href="/css/functionlist.css">
<script type="text/javascript" src="/js/functionlist.js"></script>
</body>
</html>
<s:debug></s:debug>