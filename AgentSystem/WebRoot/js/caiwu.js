var userid;
$().ready(function() {
	userid = -1;
	mover(4);
	$("#searchUserText").keyup(function() {
		loadUsers();
	});
	$("#searchUserText").click(function() {
		loadUsers();
	});
	$("#caiwuok").click(function() {
		// 提交并执行账户操作
		if (userid < 0)
			humane.error("对不起，您还没有选择用户，请搜索进行用户选择");
		else if ($("#zijintype").val() == '')
			humane.error("对不起，您还没有选择操作类型，请选择");
		else if ($("#zijin").val() == '' || $("#zijin").val().length <= 0)
			humane.error("对不起，您还没有输入金额");
		else if (confirm("是否确定执行当前操作？"))
			$.post("/opeaccount.action", {
				'account.userId' : userid,
				'account.userName' : $("#searchUserText").val(),
				'account.money' : $("#zijin").val(),
				'accountDetail.detailType' : $("#zijintype").val(),
				'accountDetail.detailTypeName' : $("#zijintype option:selected").text(),
				'accountDetail.memo' : $("#memo").val()
			}, function(result) {
				if (result == "success"){
					humane.success("操作成功");
					$("#systemtip").html("恭喜，当前操作成功");
				} else {
					humane.error("对不起，当前操作失败");
					$("#systemtip").html("对不起，当前操作失败");
				}
			}, "html");
	});
});

function confirmUser(uid, ucode) {
	userid = uid;
	$("#searchUserText").val(ucode);
	$("#searchresult").html("");
}

function loadUsers() {
	$.post("/searchuser.action", {
		'user.userCode' : $("#searchUserText").val()
	}, function(result) {
		// result是List<User>的json格式
		var userList = "<ul>";
		for ( var i = 0; i < result.length; i++) {
			userList = userList + "<li onclick=\"confirmUser('" + result[i].id + "', '" + result[i].userCode + "')\">" + result[i].userCode + "</li>";
		}
		userList = userList + "</ul>";
		$("#searchresult").html(userList);
	}, "json");
}