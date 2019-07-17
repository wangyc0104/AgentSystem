<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/inc/head.jsp"></jsp:include>

<div class="mbxnav">
	<!-- 导航 -->
	系统配置管理\
	<s:if test="configType==1">
		<a href="/caiwutype.action">财务类型</a>
	</s:if>
	<s:if test="configType==2">
		<a href="/servicetype.action">服务类型</a>
	</s:if>
	<s:if test="configType==3">
		<a href="/serviceyears.action">服务年限</a>
	</s:if>
	<s:if test="configType==4">
		<a href="/appurl.action">APP地址</a>
	</s:if>
	<s:if test="configType==5">
		<a href="/customtype.action">客户类型</a>
	</s:if>
	<s:if test="configType==6">
		<a href="/cardtype.action">证件类型</a>
	</s:if>
	<s:if test="configType==7">
		<a href="/youhuitype.action">优惠类型</a>
	</s:if>
</div>

<div class="container">
	<!-- 添加输入页面 -->
	<div class="modifySystemdiv addback" id="addSystemdiv">
		<ul>
			<li class="lititle">您正在进行添加操作 <input type="hidden"
				id="addConfigType" value="<s:property value="configType"/>">
			</li>
			<li>配置类型名称：<input type="text" id="addConfigName"></li>
			<s:if test="configType==2||configType==7">
				<li>配置项值：<input type="text" id="addConfigValue"></li>
			</s:if>
			<li>是否启用： <select id="addIsStartSelect">
					<option value="1">启用</option>
					<option value="0">停用</option>
			</select> <input type="button" id="addSystemConfigBtn" value="保存"> <input
				type="button" id="cancelAddSystemConfigBtn" value="取消">
			</li>
		</ul>

	</div>

	<!-- 修改界面 -->
	<div class="modifySystemdiv modifyback" id="modifySystemdiv">
		<ul>
			<li class="lititle">您正在进行修改操作 <input type="hidden" id="sid" />
			<!-- 本配置项的主键ID --> <input type="hidden" id="modifyConfigType"
				value="<s:property value="configType"/>" />
			</li>
			<li>配置类型名称：<input type="text" id="modifyConfigName"></li>
			<s:if test="configType==2||configType==7">
				<li>配置项值：<input type="text" id="modifyConfigValue" /></li>
			</s:if>
			<li>是否启用： <select id="modifyIsStartSelect">
					<option value="1">启用</option>
					<option value="0">停用</option>
			</select> <input type="button" id="modifySystemConfigBtn" value="保存">
				<input type="button" id="cancelModifySystemConfigBtn" value="取消">
			</li>
		</ul>
	</div>

	<!-- 列表 -->
	<s:if test="configType==1||configType==2||configType==5||configType==6||configType==7">
	<div>
		<!-- 添加按钮 -->
		<div class="addSystemConfig">
			<input type="button" id="addsystemconfig"
				value="添加<s:property value="configTypeName"/>" />
		</div>
		<!-- 列表 -->
		<div>
			<table>
				<thead>
					<tr>
						<th>序号</th>
						<th>配置类型</th>
						<s:if test="configType==2||configType==7">
							<th>配置数值</th>
						</s:if>
						<th>是否启用</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="systemConfigList" status="st">
						<tr>
							<td><s:property value="#st.index+1" /></td>
							<td><s:property value="configTypeName" /></td>
							<s:if test="configType==2||configType==7">
								<td><s:property value="configValue" /></td>
							</s:if>
							<td><s:if test="isStart==1">启用</s:if>
								<s:else>停用</s:else></td>
							<td><span class="modifySystemBtn"
								sid="<s:property value="id"/>"
								configtype="<s:property value="configType"/>"
								sname="<s:property value="configTypeName"/>"
								svalue="<s:property value="configValue"/>"
								isstart="<s:property value="isStart"/>">修改</span> <span
								class="deleteSystemBtn" sid="<s:property value="id"/>"
								sname="<s:property value="configTypeName"/>">删除</span></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div>
	</s:if>
	
	<!-- simple div -->
	<s:if test="configType==3||configType==4">
	<div class="simpleconfig">
		<h3>
			<s:if test="configType==3">服务年限管理-填写最大的服务年限</s:if>
			<s:if test="configType==4">APP URL管理-填写制作APP的系统的URL地址</s:if>
		</h3>
		<input type="hidden" id="configType" value="<s:property value="configType"/>"> <!-- 服务年限configType=3 -->
		<input type="hidden" id="simpleConfigId" value="<s:property value="systemConfig.id"/>">
		<ul>
			<li>配置名称：<input type="text" id="simpleTypeName" value="<s:property value="systemConfig.configTypeName"/>"></li>
			<li>配置数值：<input type="text" id="simpleConfigValue" value="<s:property value="systemConfig.configValue"/>"></li>
			<li><input type="button" id="simpleBtn" value="保存"><li>
		</ul>
	</div>
	</s:if>
	
</div>

<jsp:include page="/inc/foot.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/css/systemconfig.css">
<script type="text/javascript" src="/js/systemconfig.js"></script>
</body>
</html>
<s:debug></s:debug>