var contactcount = 0;
$().ready(function() {
	mover(1);
	contactcount = $("#contactcount").val();
	// 初始化日期
	$("#regdate").val(new Date().format("yyyy-MM-dd"));
			
	var province = $("#customprovince").val();
	var city = $("#city").val();
	var area = $("#area").val();
	
	// 省份和城市联动
	if (province != "" && city != "") {
		$.post("/loadcity.action", {
			"province.provinceID": province
		}, function(result) {
			// result cityList
			if (result != "") {
				for (var i = 0; i < result.length; i++) {
					// if (city == result[i].cityID)
					//	$("#customcity").append("<option selected='selected' value='" + result[i].cityID + "'>" + result[i].city + "</option>");
					// else
						$("#customcity").append("<option value='" + result[i].cityID + "'>" + result[i].city + "</option>");
				}
				$("#customcity").val(city); // 回显
			} else humane.error("城市加载失败");
		}, "json");
	}
	$("#customprovince").change(function() {
		// 初始化
		$("#customcity").empty();
		$("#customcity").append("<option value='' selected='selected'>--请选择--</option>");
		$("#customarea").empty();
		$("#customarea").append("<option value='' selected='selected'>--请选择--</option>");
		var s_province = $("#customprovince").val(); // 这里实际上传的是<s:select>标签里面的listKey值
		// ajax请求：按省份获取相应城市列表
		$.post("/loadcity.action", {
			"province.provinceID": s_province
		}, function(result) {
			// result cityList
			if (result != "") {
				for (var i = 0; i < result.length; i++) {
					$("#customcity").append("<option value='" + result[i].cityID + "'>" + result[i].city + "</option>");
				}
			} else humane.error("城市加载失败");
		}, "json");
	});
	
	// 城市和区域联动
	if (province != "" && city != "" && area != "") {
		$.post("/loadarea.action", {
			"city.cityID" : city
		}, function(result) {
			// result areaList
			if (result != "") {
				for (var i = 0; i < result.length; i++) {
					$("#customarea").append("<option value='" + result[i].areaID + "'>" + result[i].area + "</option>");
				}
				$("#customarea").val(area);
			} else humane.error("区域加载失败");
		}, "json");
	}
	$("#customcity").change(function() {
		$("#customarea").empty();
		$("#customarea").append("<option value='' selected='selected'>--请选择--</option>");

		var s_city = $("#customcity").val();
		// ajax请求：按城市获取相应区域列表
		$.post("/loadarea.action", {
			"city.cityID" : s_city
		}, function(result) {
			// result areaList
			if (result != "") {
				for (var i = 0; i < result.length; i++) {
					$("#customarea").append("<option value='" + result[i].areaID + "'>" + result[i].area + "</option>");
				}
			} else humane.error("区域加载失败");
		}, "json");
	});
	
	// 初始化customtypename
	$("#customtype").change(function(){
		$("#customtypename").val($("#customtype option:selected").text());
	});
	// 初始化customcardtypename
	$("#customcardtype").change(function(){
		$("#customcardtypename").val($("#customcardtype option:selected").text());
	});
	// 编辑联系人
	$("#addcontact").click(function(){
		var str = "<tr>" +
					"<td><input type='text' name='contactList[" + contactcount + "].contactName'></td>" +
					"<td><input type='text' name='contactList[" + contactcount + "].contactTel'></td>" +
					"<td><input type='text' name='contactList[" + contactcount + "].contactFax'></td>" +
					"<td><input type='text' name='contactList[" + contactcount + "].contactEmail'></td>" +
					"<td><input type='text' name='contactList[" + contactcount + "].contactRole'></td>" +
					"<td onclick='delTr(this)'><a href='javascript:void(0)'>删除</a></td>" +
				  "</tr>";
		$("#addtr").append(str);
		contactcount++;
	});
	// 验证证件号码是否是数字
	$("#cardnum").blur(function() {
		var t = $("#cardnum").val();
		if(!checkValidateNum(t))
			humane.error("证件号码请输入数字");
	});
});

// 验证号码是否合规
function checkValidateNum(value) {
	var reg = new RegExp("^[0-9]*$");
	if (reg.test(value))
		return true;
	else
		return false;
}

function delTr(obj) {
	$(obj).parent().remove();
	contactcount--;
}
// 验证，提交
function checksave() {
	var customname = $.trim($("#customname").val());
	var customtypename = $("#customtypename").val();
	var cardnum = $("#cardnum").val();
	if (customname.length==0){
		humane.error("客户名称不能为空");
		return ;
	}
	if (customtypename.length==0){
		humane.error("请选择客户类型");
		return ;
	}
	if(!checkValidateNum(cardnum)){
		humane.error("证件号码请输入数字");
		return ;
	}
	$.post("/isexistcustomname.action",{
		"custom.customName" : customname,
	}, function(result) {
		if(result=="peat"){
			humane.error("对不起，该客户名称已存在");
		}else if(result=="nopeat"){
			$("#cform").submit();
		}
	});
}