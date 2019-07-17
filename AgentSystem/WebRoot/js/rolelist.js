$().ready(function() {

	// head栏目菜单显示
	mover(4);

	/** *************添加角色************* */
	// 打开添加面板
	// $("#addRoleDiv").css()
	$("#addRole").click(function() {
		$("#addRoleDiv").slideDown();
		$("#modifyRoleDiv").slideUp();
	});
	// 在添加面板内的添加动作
	$("#addRoleSubmit").click(function() {
		var a_roleName = $("#a_roleName").val();
		var a_isStart = $("#a_isStart").val();
		if (a_roleName == "") {
			humane.error("角色名称不能为空！");
		} else {
			$.post("/editrole.action?type=add", {
				"role.roleName" : a_roleName,
				"role.isStart" : a_isStart
			}, function(result) {
				if ("success" == result) {
					humane.success("添加成功");
					window.location.href = "/rolelist.action";
				} else {
					humane.error("添加失败");
				}
			}, "html");
		}
	});
	// 点击取消关闭添加面板
	$("#addCancel").click(function() {
		$("#addRoleDiv").slideUp(); // 隐藏
	});

	/** *************修改角色************* */
	// 点击修改打开面板
	$(".modifyRole").click(function() {
		$("#modifyRoleDiv").slideDown();
		$("#addRoleDiv").slideUp();

		var b = $(this);
		$("#m_roleId").val(b.attr('roleid'));
		$("#m_roleName").val(b.attr('rolename'));
		$("#m_isStart").val(b.attr('isstart'));
	});
	// 在修改面板内的修改动作
	$("#modifyRoleSubmit").click(function() {
		var m_roleId = $("#m_roleId").val();
		var m_roleName = $("#m_roleName").val();
		var m_isStart = $("#m_isStart").val();
		if (m_roleName == "") {
			humane.error("角色名称不能为空！");
		} else {
			$.post("/editrole.action?type=modify", {
				"role.id" : m_roleId,
				"role.roleName" : m_roleName,
				"role.isStart" : m_isStart
			}, function(result) {
				if ("success" == result) {
					humane.success("修改成功");
					window.location.href = "/rolelist.action";
				} else {
					humane.error("修改失败");
				}
			}, "html");
		}
	});
	// 点击取消关闭修改面板
	$("#modifyCancel").click(function() {
		$("#modifyRoleDiv").slideUp(); // 隐藏
	});

	/** *************删除角色************* */
	// 点击删除按钮
	$(".deleteRole").click(function() {
		var b = $(this);
		var b_roleId = b.attr('roleid');
		if (confirm("您确认要删除吗？")) {
			// delete
			$.post("/deleterole.action", {
				"role.id" : b_roleId
			}, function(result) {
				if ("success" == result) {
					humane.success("删除成功");
					window.location.href = "/rolelist.action";
				} else {
					humane.error("删除失败");
				}
			}, "html");
		}
	});

});