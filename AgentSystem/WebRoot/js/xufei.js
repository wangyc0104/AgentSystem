$().ready(function() {
	var kid = $("#kid").val();
	$("#submitkeyword").click(function() {
		var servicetype = $("#servicetype").val();
		var years = $("#serviceyear").val();
		if (servicetype == "") {
			humane.error("对不起，请选择服务类型");
			$("#servicetype").focus();
		} else if (years == "") {
			humane.error("对不起，请选择服务年限");
			$("#serviceyear").focus();
		} else {
			// 提交
			$.post("/keywordsxufei.action", {
				"p": servicetype + "-" +years,
				"keywords.id": kid
			}, function(result) {
				if (result == "exception") {
					humane.error("对不起，您提交的关键词出现异常");
				} else if (result == "nomoney") {
					humane.error("对不起，您当前的余额不能支付本次续费，请充值");
				} else {
					humane.success("恭喜您，续费成功！");
					$("#accountspan").html(result);
				}
			}, "html");
		}
	});
	
	$("#servicetype").change(function() {
		jisuan();
	});
	$("#serviceyear").change(function() {
		jisuan();
	});
	
});

//根据选择的服务类型及服务年限自动计算价格
function jisuan() {
	var servicetype = $("#servicetype").val();
	var serviceyear = $("#serviceyear").val();
	if (servicetype=="") {
		humane.error("请选择服务类型");
		$("#servicetype").focus();
	} else if (serviceyear==""){
		humane.error("请选择服务年限");
		$("#serviceyear").focus();
	} else {
		$.post("/jisuan.action", {
			"p": servicetype + "-" + serviceyear
		}, function(result) {
			if (result == "exception") {
				humane.error("计算价格时出现异常，请重试");
			} else {
				$("#price").val(result); // result为返回金额
			}
		}, "html");
	}
}