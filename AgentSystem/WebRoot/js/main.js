//modifypwdbtna
//modifydiv
//oldpwdtext
//newpwd
//newpwd2
//modifypwdbtn
//modifypwdcancel

$().ready(function() {
	mover(1); // 住嘴
	$("#modifypwdbtna").click(function() {
		$("#modifydiv").show();
		$("#oldpwdtext").val('');
		$("#newpwd").val('');
		$("#newpwd2").val('');
	});

	$("#oldpwdtext").blur(function() {
		if ($("#oldpwdtext").val() != "")
			$("#oldpwdtexttip").css("color", "green");
		else
			$("#oldpwdtexttip").css("color", "red");
	});
	$("#newpwd").blur(function() {
		if ($("#newpwd").val() != "")
			$("#newpwdtip").css("color", "green");
		else
			$("#newpwdtip").css("color", "red");
	});
	$("#newpwd2").blur(function() {
		if ($("#newpwd2").val() != "")
			$("#newpwd2tip").css("color", "green");
		else
			$("#newpwd2tip").css("color", "red");
	});
	$("#modifypwdbtn").click(function() {
		var oldpwd = $("#oldpwdtext").val();
		var newpwd = $("#newpwd").val();
		var newpwd2 = $("#newpwd2").val();
		if (oldpwd.length < 6) {
			$("#oldpwdtext").focus();
			humane.error("请输入原始密码");
		} else if (newpwd.length < 6) {
			$("#newpwd").focus();
			humane.error("密码长度不能小于6");
		} else if (newpwd2.length < 6) {
			$("#newpwd2").focus();
			humane.error("密码长度不能小于6");
		} else if (newpwd != newpwd2) {
			humane.error("两次密码不相同！");
		} else {
			// 提交到服务器
			$.post("/modifypwd.action", {
				"user.userName" : oldpwd,
				'user.userPassword' : newpwd
			}, function(result) {
				// "success","failed"
				if (result == "success") {
					humane.success("密码修改成功！");
					$("#modifydiv").hide();
				} else {
					humane.error("密码修改失败！");
				}
			}, 'html');
		}
	});
	$("#modifypwdcancel").click(function() {
		$("#modifydiv").hide();
		$("#oldpwdtext").val('');
		$("#newpwd").val('');
		$("#newpwd2").val('');
	});
});