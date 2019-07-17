$().ready(function() {

	// head栏目菜单显示
	mover(4);

	/** *************添加用户************* */
	// 点击addUser打开添加面板
	$("#addUser").click(function() {
		$("#addUserDiv").slideDown();
		$("#modifyUserDiv").slideUp();
	});
	// 点击保存按钮触发的事件
	$("#addUserSubmit").click(function() {
		var a_userCode = $("#a_userCode").val();
		var a_userName = $("#a_userName").val();
		var a_userPassword = $("#a_userPassword").val();
		var a_roleId = $("#a_roleId").val();
		var a_isStart = $("#a_isStart").val();
		if (a_userCode == '')
			humane.error('登录账号不能为空！');
		else if (a_userName == '')
			humane.error('用户名称不能为空！');
		else if (a_userPassword == '')
			humane.error('用户密码不能为空！');
		else if (a_roleId == '')
			humane.error('请选择角色！');
		else
			$.post("/edituser.action?type=add", {
				"user.userCode" : a_userCode,
				"user.userName" : a_userName,
				"user.userPassword" : a_userPassword,
				"user.roleId" : a_roleId,
				"user.isStart" : a_isStart
			}, function(result) {
				if (result == "success") {
					humane.success("用户添加成功！");
					window.location.href = "/userlist.action";
				} else if ("repeat" == result) {
					humane.error("账号已存在！");
				} else {
					humane.error("添加失败!");
				}
			}, "html");
	});
	// 点击取消按钮触发的事件
	$("#addCancel").click(function() {
		$("#addUserDiv").slideUp();
	});

	/** *************修改用户************* */
	// 点击modifyUser打开修改面板
	$(".modifyUser").click(function() {
		var b = $(this);
		$("#modifyUserDiv").slideDown();
		$("#addUserDiv").slideUp();
		$("#m_userId").val(b.attr('userid'));
		$("#m_userCode").val(b.attr('usercode'));
		$("#m_userName").val(b.attr('username'));
		$("#m_userPassword").val(b.attr('userpassword'));
		$("#m_roleId").val(b.attr('roleid'));
		$("#m_isStart").val(b.attr('isstart'));
	});
	$("#modifyUserSubmit").click(function() {
		var m_userId = $("#m_userId").val();
		var m_userCode = $("#m_userCode").val();
		var m_userName = $("#m_userName").val();
		var m_userPassword = $("#m_userPassword").val();
		var m_roleId = $("#m_roleId").val();
		var m_isStart = $("#m_isStart").val();
		if (m_userCode == '')
			humane.error('登录账号不能为空！');
		else if (m_userName == '')
			humane.error('用户名称不能为空！');
		else if (m_userPassword == '')
			humane.error('用户密码不能为空！');
		else if (m_roleId == '')
			humane.error('请选择角色！');
		else
			$.post("/edituser.action?type=modify", {
				"user.id" : m_userId,
				"user.userCode" : m_userCode,
				"user.userName" : m_userName,
				"user.userPassword" : m_userPassword,
				"user.roleId" : m_roleId,
				"user.isStart" : m_isStart
			}, function(result) {
				if (result == "success") {
					humane.success("用户修改成功！");
					window.location.href = "/userlist.action";
				} else if ("repeat" == result) {
					humane.error("账号已存在！");
				} else {
					humane.error("修改失败!");
				}
			}, "html");
	});
	// 点击取消按钮触发的事件
	$("#modifyCancel").click(function() {
		$("#modifyUserDiv").slideUp();
	});

	/** *************删除用户************* */
	// 点击deleteUser删除用户
	$(".deleteUser").click(function() {
		var d = $(this);
		var d_userId = d.attr('userid');
		var d_userCode = d.attr('usercode');
		var d_userRoleId = d.attr("roleid");
		if (d_userRoleId == 1) {
			alert("该账号角色为系统管理员，不能被删除！");
			return;
		}
		if (confirm("您确定要删除[" + d_userCode + "]吗？")) {
			// 确认删除
			$.post("/deluser.action", {
				'user.id' : d_userId
			}, function(result) {
				if ("success" == result) {
					humane.success("删除成功！");
					window.location.href = "/userlist.action";
				} else
					humane.error("删除失败！");
			}, "html");
		}
	});

});