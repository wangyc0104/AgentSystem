<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/inc/head.jsp"></jsp:include>


<div class="mbxnav">
	<!-- 导航 -->
	代理商管理\ <a href="/customlist.action">代理商客户管理</a>\ 添加客户信息
</div>

<div class="container">
	<form id="cform" action="/addsavecustom.action" method="post">
		<!-- ***************基本信息*************** -->
		<div class="subtitle">基本信息</div>
		<div class="info1">
			<ul>
				<li>企业名称：<input type="text" name="custom.customName" id="customname"></li>
				<li>
					企业类型：
					<input type="hidden" id="customtypename" name="custom.customTypeName"/>
					<s:select id="customtype"
							  name="custom.customType"
							  list="customTypeList" 
							  listKey="configTypeValue" 
							  listValue="configTypeName"
							  headerKey=""
							  headerValue="--请选择--">
					</s:select>
				</li>
				<li>企业主页：<input type="text" name="custom.siteUrl"></li>
				<li>
					状态：
					<s:select name="custom.customStatus" list="#{'0':'停用','1':'启用'}"/>
				</li>
			</ul>
			<div class="clear"></div>
		</div>
		<!-- ***************门户信息*************** -->
		<div class="subtitle">门户信息</div>
		<div class="info2">
			<ul>
				<li>法人代表：<input type="text" name="custom.bossName"></li>
				<li>
					注册日期：
					<input id="regdate"
						   type="text" 
				    	   name="custom.regDatetime" 
				    	   class="Wdate" 
				    	   onclick="WdatePicker();">
				</li>
				<li>
					证件类型：
					<input id="customcardtypename"
						   type="hidden"
						   name="custom.cardTypeName"
						   value="${custom.cardTypeName}">
					<s:select id="customcardtype"
							  name="custom.cardType"
							  value="custom.cardType"
							  list="cardTypeList"
							  listKey="configTypeValue"
							  listValue="configTypeName"
							  headerKey=""
							  headerValue="--请选择--">
					</s:select>
				</li>
				<li>
					证件号码：
					<input id="cardnum"
						   type="text" 
						   name="custom.cardNum"
						   value="${custom.cardNum}">
				</li>
				<li>国家：
					<input type="text" 
						   name="custom.country"
						   value="${custom.country}">
				</li>
				<li>
					省/地区：
					<s:select id="customprovince"
							  name="custom.province"
							  list="provinceList"
							  listKey="provinceID"
							  listValue="province"
							  headerKey=""
							  headerValue="--请选择--">
					</s:select>
				</li>
				<li>
					公司传真：
					<input type="text"
						   name="custom.companyFax"
						   value="${custom.companyFax}">
				</li>
				<li>
					城市：
					<select name="custom.city" id="customcity">
						<option value="">--请选择--</option>
					</select>
				</li>
				<li>
					公司电话：
					<input type="text" 
						   name="custom.companyTel"
						   value="${custom.companyTel}">
				</li>
				<li>
					区：
					<select name="custom.area" id="customarea">
						<option value="">--请选择--</option>
					</select>
				</li>
				<li>
					公司地址：
					<input type="text" 
						   name="custom.companyAddress"
						   value="${custom.companyAddress}">
				</li>
			</ul>
			<div class="clear"></div>
			<div>&nbsp;备注：<textarea name="custom.memo" rows="2" cols="50"></textarea></div>
		</div>
		<!-- ***************添加一个联系人*************** -->
		<div class="subtitle">
			<a href="javascript:void(0);" id="addcontact">添加一个联系人</a>
		</div>
		<div class="info3">
			<table>
				<thead>
					<tr>
						<th>姓名</th>
						<th>电话</th>
						<th>传真</th>
						<th>邮箱</th>
						<th>职务</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="addtr">
					<!-- 此处使用js传递 -->
				</tbody>
			</table>
			<div class="clear"></div>
		</div>
		<div class="goback">
			<input type="button" value="保存" onclick="checksave();">
			<input type="button" value="返回" onclick="history.go(-1);">
		</div>
	</form>
</div>

<jsp:include page="/inc/foot.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/css/addcustom.css">
<script type="text/javascript" src="/js/addcustom.js"></script>
<script type="text/javascript" src="/medire/WdatePicker.js"></script>
</body>
</html>
<s:debug></s:debug>