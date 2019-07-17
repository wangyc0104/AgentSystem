function validateLoginUserFunc() {

	var flag = false;

	var usercode = $.trim($("#usercode").val());
	var userpassword = $.trim($("#userpassword").val());

	if (usercode == "" || usercode == null) {
		$("#usercode").focus();
		alert("账号不能为空！");
	} else if (userpassword == "" || userpassword == null) {
		$("#userpassword").focus();
		alert("密码不能为空！");
	} else {
		$.ajax({
			url : '/validateLoginUser.action',
			type : 'post',
			async : false,
			data : {
				'user.userCode' : usercode,
				'user.userPassword' : userpassword
			},
			dataType : 'html',
			success : function(data) {
				if ("noexitusercode" == data) {
					alert("对不起，账号不存在");
				} else if ("errorpwd" == data) {
					alert("对不起，密码错误");
				} else if ("failed" == data) {
					alert("服务器错误");
				} else if ("success" == data) {
					flag = true;
				}
			}
		});
	}
	
	return flag;
	
}