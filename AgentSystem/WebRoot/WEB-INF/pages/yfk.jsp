<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/inc/head.jsp"></jsp:include>


<div class="mbxnav">
	<!-- 导航 -->
	<a href="/main.action">代理商管理</a>\当前账户信息\ <a href="/yfk.action">代理商预付款</a>
</div>

<div class="container">
	<!-- search box -->
	<div class="searchuserdiv ope">
		<ul>
			<li>
				<form action="/yfk.action" method="post" onsubmit="return searchyfklistFunc()">
					操作类型:
					<select name="accountDetail.detailType">
						<option value="">请选择</option>
						<option value="9999" <s:if test="accountDetail.detailType==9999">selected="selected"</s:if>>
							系统自动-关键词申请扣款
						</option>
						<option value="9998" <s:if test="accountDetail.detailType==9998">selected="selected"</s:if>>
							系统自动-返回预注册冻结的资金
						</option>
						<option value="9997" <s:if test="accountDetail.detailType==9997">selected="selected"</s:if>>
							系统自动-扣除申请关键词的所有资金
						</option>
						<option value="9996" <s:if test="accountDetail.detailType==9996">selected="selected"</s:if>>
							系统自动-扣除关键词续费资金
						</option>
						<s:iterator value="accountConfigList">
							<option value="<s:property value="id"/>" <s:if test="id==accountDetail.detailType">selected="selected"</s:if>>
								<s:property value="configTypeName"/>
							</option>
						</s:iterator>
					</select>
					操作时间：
					<input class="Wdate"
						   type="text"
						   onClick="WdatePicker()"
						   size="15" 
						   id="starttime"
						   readonly="readonly"
						   name="accountDetail.startTime" 
						   value="<s:date name="accountDetail.startTime" format="yyyy-MM-dd"/>">
					至
					<input class="Wdate"
						   type="text"
						   onClick="WdatePicker()"
						   size="15" 
						   id="endtime"
						   readonly="readonly"
						   name="accountDetail.endTime" 
						   value="<s:date name="accountDetail.endTime" format="yyyy-MM-dd"/>">
					<input type="submit" value="查询">
				</form>
			</li>
		</ul>
	</div>
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
				<a href="/yfk.action?pager.page=1&accountDetail.detailType=<s:property value="accountDetail.detailType"/>&accountDetail.startTime=<s:date name="accountDetail.startTime" format="yyyy-MM-dd"/>&accountDetail.endTime=<s:date name="accountDetail.endTime" format="yyyy-MM-dd"/>">
					首页
				</a>
			</li>
			<!-- 前一段页面 -->
			<s:if test="pager.prevPages!=null"> <!-- 此处的pager.prevPages直接通过调用PageSupport中的getPrevPages()方法取到的值，而没有相应的属性值 -->
				<s:iterator value="pager.prevPages" var="number">
					<li>
						<a href="/yfk.action?pager.page=<s:property value="#number"/>&accountDetail.detailType=<s:property value="accountDetail.detailType"/>&accountDetail.startTime=<s:date name="accountDetail.startTime" format="yyyy-MM-dd"/>&accountDetail.endTime=<s:date name="accountDetail.endTime" format="yyyy-MM-dd"/>">
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
						<a href="/yfk.action?pager.page=<s:property value="#number"/>&accountDetail.detailType=<s:property value="accountDetail.detailType"/>&accountDetail.startTime=<s:date name="accountDetail.startTime" format="yyyy-MM-dd"/>&accountDetail.endTime=<s:date name="accountDetail.endTime" format="yyyy-MM-dd"/>">
							<s:property value="#number"/>
						</a>
					</li>
				</s:iterator>
			</s:if>
			<!-- 尾页 -->
			<li>
				<a href="/yfk.action?pager.page=<s:property value="pager.pageCount"/>&accountDetail.detailType=<s:property value="accountDetail.detailType"/>&accountDetail.startTime=<s:date name="accountDetail.startTime" format="yyyy-MM-dd"/>&accountDetail.endTime=<s:date name="accountDetail.endTime" format="yyyy-MM-dd"/>">
					尾页
				</a>
			</li>
		</ul>
	</div>
</div>

<jsp:include page="/inc/foot.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/css/yfk.css">
<script type="text/javascript" src="/js/yfk.js"></script>
<script type="text/javascript" src="/medire/WdatePicker.js"></script>
</body>
</html>
<s:debug></s:debug>