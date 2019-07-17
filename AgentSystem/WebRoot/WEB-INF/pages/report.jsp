<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/inc/head.jsp"></jsp:include>

<div class="mbxnav">
	<!-- 导航 -->
	<a href="/report.action">报表管理</a>
</div>

<div class="container">
	<!-- search box -->
	<div class="searchuserdiv ope">
		<ul>
			<li>
				<form action="/report.action" method="post" onsubmit="return searchReportFunc()">
					操作类型:
					<select name="reportType" id="reporttype">
						<option value="">财务报表</option>
						<option value="1" <s:if test="reportType==1">selected="selected"</s:if>>&nbsp;&nbsp;&nbsp;&nbsp;代理商账户余额报表</option>
						<option value="2" <s:if test="reportType==2">selected="selected"</s:if>>&nbsp;&nbsp;&nbsp;&nbsp;预付款流水报表</option>
						<option value="3" <s:if test="reportType==3">selected="selected"</s:if>>&nbsp;&nbsp;&nbsp;&nbsp;代理商流水报表</option>
						<option value="">产品报表</option>
						<option value="4" <s:if test="reportType==4">selected="selected"</s:if>>&nbsp;&nbsp;&nbsp;&nbsp;产品分类数量/金额汇总</option>
					</select>
					<div id="opertime" style="margin-left:250px; margin-top:-20px;">
					操作时间：
					<input class="Wdate"
						   type="text"
						   onClick="WdatePicker()"
						   size="15" 
						   id="starttime"
						   readonly="readonly"
						   name="startTime" 
						   value="<s:date name="startTime" format="yyyy-MM-dd"/>">
					至
					<input class="Wdate"
						   type="text"
						   onClick="WdatePicker()"
						   size="15" 
						   id="endtime"
						   readonly="readonly"
						   name="endTime"
						   value="<s:date name="endTime" format="yyyy-MM-dd"/>">
					<input type="submit" style="margin-left:380px; margin-top:-20px;" value="查询">
					</div>
					<input id="nodatesubmit" hidden="hidden" type="submit" style="margin-left:280px; margin-top:-20px;" value="查询">
				</form>
			</li>
		</ul>
	</div>
	
	<!-- 查询结果列表 -->
	<s:if test="reportType==1">
		<!-- 代理商余额报表 -->
		<div class="downloadfile">
			<ul>
				<li><img src="/imgs/pdf.png"><a href="/reportaccount.action">PDF下载</a></li>
				<li><img src="/imgs/excel.png"><a href="/reportaccounte.action">Excel下载</a></li>
			</ul>
		</div>
		<h1>代理商余额报表</h1>
		<table>
			<thead>
				<tr>
					<th>序号</th>
					<th>代理商名称</th>
					<th>账户余额</th>
				</tr>
			</thead>
			<s:iterator value="accountList" status="st">
			<tbody>
				<tr>
					<td>${st.index+1}</td>
					<td>${userName}</td>
					<td>${money}</td>
				</tr>
			</tbody>
			</s:iterator>
		</table>
	</s:if>
	<s:elseif test="reportType==2">
		<!-- 预付款流水报表 -->
		<div class="downloadfile">
			<ul>
				<li><img src="/imgs/pdf.png"><a href="/reportyfkpdf.action?startTime=<s:date name="startTime" format="yyyy-MM-dd"/>&endTime=<s:date name="endTime" format="yyyy-MM-dd"/>">PDF下载</a></li>
				<li><img src="/imgs/excel.png"><a href="/reportyfkxls.action?startTime=<s:date name="startTime" format="yyyy-MM-dd"/>&endTime=<s:date name="endTime" format="yyyy-MM-dd"/>">Excel下载</a></li>
			</ul>
		</div>
		<h1>预付款流水报表</h1>
		<table>
			<thead>
				<tr>
					<th>序号</th>
					<th>代理商名称</th>
					<th>预付款</th>
					<th>账户余额</th>
					<th>备注信息</th>
					<th>时间</th>
				</tr>
			</thead>
			<s:iterator value="accountDetailList" status="st">
			<tbody>
				<tr>
					<td>${st.index+1}</td>
					<td>${userName}</td>
					<td>${money}</td>
					<td>${accountMoney}</td>
					<td>${memo}</td>
					<td><s:date name="detailDateTime" format="yyyy-MM-dd HH:mm:ss"></s:date></td>
				</tr>
			</tbody>
			</s:iterator>
		</table>
	</s:elseif>
	<s:elseif test="reportType==3">
		<!-- 代理商流水报表 -->
		<div class="downloadfile">
			<ul>
				<li><img src="/imgs/pdf.png"><a href="/reportdlspdf.action?startTime=<s:date name="startTime" format="yyyy-MM-dd"/>&endTime=<s:date name="endTime" format="yyyy-MM-dd"/>">PDF下载</a></li>
				<li><img src="/imgs/excel.png"><a href="/reportdlsxls.action?startTime=<s:date name="startTime" format="yyyy-MM-dd"/>&endTime=<s:date name="endTime" format="yyyy-MM-dd"/>">Excel下载</a></li>
			</ul>
		</div>
		<h1>代理商流水报表</h1>
		<table>
			<thead>
				<tr>
					<th>序号</th>
					<th>代理商名称</th>
					<th>预付款</th>
					<th>账户余额</th>
					<th>备注信息</th>
					<th>时间</th>
				</tr>
			</thead>
			<s:iterator value="accountDetailList" status="st">
			<tbody>
				<tr>
					<td>${st.index+1}</td>
					<td>${userName}</td>
					<td>${money}</td>
					<td>${accountMoney}</td>
					<td>${memo}</td>
					<td><s:date name="detailDateTime" format="yyyy-MM-dd HH:mm:ss"></s:date></td>
				</tr>
			</tbody>
			</s:iterator>
		</table>
	</s:elseif>
	<s:elseif test="reportType==4">
		<!-- 产品分类数量/金额汇总 -->
		<div class="downloadfile">
			<ul>
				<li><img src="/imgs/pdf.png"><a href="/reportproductpdf.action?startTime=<s:date name="startTime" format="yyyy-MM-dd"/>&endTime=<s:date name="endTime" format="yyyy-MM-dd"/>">PDF下载</a></li>
				<li><img src="/imgs/excel.png"><a href="/reportproductxls.action?startTime=<s:date name="startTime" format="yyyy-MM-dd"/>&endTime=<s:date name="endTime" format="yyyy-MM-dd"/>">Excel下载</a></li>
			</ul>
		</div>
		<h1>产品分类数量/金额汇总</h1>
		<table>
			<thead>
				<tr>
					<th>序号</th>
					<th>产品分类名称</th>
					<th>数量</th>
					<th>价格</th>
				</tr>
			</thead>
			<s:iterator value="reportProductList" status="st">
			<tbody>
				<tr>
					<td>${st.index+1}</td>
					<td>${configTypeName}</td>
					<td>${number}</td>
					<td>￥${price}</td>
				</tr>
			</tbody>
			</s:iterator>
		</table>
	</s:elseif>
</div>

<jsp:include page="/inc/foot.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/css/report.css">
<script type="text/javascript" src="/js/report.js"></script>
<script type="text/javascript" src="/medire/WdatePicker.js"></script>
</body>
</html>
<s:debug></s:debug>