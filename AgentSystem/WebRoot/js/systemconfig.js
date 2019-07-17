$().ready(function() {
	
	//head栏目菜单显示
	mover(5);
	
	/** *************添加系统配置************* */
	// 显示添加面板
	$("#addsystemconfig").click(function() {
		$("#addSystemdiv").slideDown();
	});
	// 给按钮绑定事件：点击保存事件
	$("#addSystemConfigBtn").click(function() {
		// 需要configType、configTypeName、isStart验证
		var configType = $("#addConfigType").val();
		var configTypeName = $("#addConfigName").val();
		var isStart = $("#addIsStartSelect").val();
		// 根据情况判断是否需要configValue
		if (($("#addConfigValue").val()) != null)
			var configValue = $("#addConfigValue").val();
		if (configType == '')
			humane.error("配置类型不能为空！");
		else if (configTypeName == '')
			humane.error("配置类型名称不能为空！");
		else {
			// 提交到action处理
			$.post("/addconfig.action", {
				"systemConfig.configType" : configType,
				"systemConfig.configTypeName" : configTypeName,
				"systemConfig.configValue" : configValue,
				"systemConfig.isStart" : isStart
			}, function(result) {// peat：重复；nopeat：未重复
				if (result == "peat")
					humane.error("对不起，该类型名称已存在");
				else {
					$("#addSystemdiv").slideUp(); // 隐藏
					humane.success("已添加成功");
					window.location.reload(true);
				}
			}, "html");
		}
	});
	// 点击取消增加系统配置按钮
	$("#cancelAddSystemConfigBtn").click(function() {
		$("#addSystemdiv").slideUp(); // 隐藏
	});

	/** *************修改系统配置************* */
	// 显示修改面板
	$(".modifySystemBtn").click(function() {
		var obj = $(this);
		$("#sid").val(obj.attr("sid"));
		$("#modifyConfigName").val(obj.attr("sname"));
		$("#modifyConfigValue").val(obj.attr("svalue"));
		$("#modifyIsStartSelect").val(obj.attr("isstart"));
		$("#modifySystemdiv").slideDown(); // 显示
	});
	// 点击修改保存事件
	$("#modifySystemConfigBtn").click(function() {
		// 需要configType、configTypeName、isStart验证
		var id = $("#sid").val();
		var configType = $("#modifyConfigType").val();
		var configTypeName = $("#modifyConfigName").val();
		var isStart = $("#modifyIsStartSelect").val();
		if ($("#modifyConfigValue").val() != null)
			var configValue = $("#modifyConfigValue").val();
		if (configType == '')
			humane.error("配置类型不能为空！");
		else if (configTypeName == '')
			humane.error("配置类型名称不能为空！");
		else {
			// 提交到action处理
			$.post("/modifyconfig.action", {
				"systemConfig.id" : id,
				"systemConfig.configType" : configType,
				"systemConfig.configTypeName" : configTypeName,
				"systemConfig.configValue" : configValue,
				"systemConfig.isStart" : isStart
			}, function(result) {
				$("#modifySystemdiv").slideUp(); // 隐藏
				humane.success("已修改成功");
				window.location.reload(true);
			}, "html");
		}
	});
	// 点击取消修改系统配置按钮
	$("#cancelModifySystemConfigBtn").click(function() {
		$("#modifySystemdiv").slideUp();
	});

	/** *************删除系统配置************* */
	// 删除配置项
	$(".deleteSystemBtn").click(function() {
		var obj = $(this);
		var id = obj.attr("sid");
		$.post("/deleteconfig.action", {
			"systemConfig.id" : id
		}, function(result) {
			if (result == "success") {
				humane.success("已删除成功");
				window.location.reload(true);
			} else
				humane.error("删除失败！");
		}, "html");
	});

	/** *************服务年限配置修改************* */
	$("#simpleBtn").click(function() {
		var configType = $("#configType").val();
		var simpleConfigId = $("#simpleConfigId").val();
		var simpleTypeName = $("#simpleTypeName").val();
		var simpleConfigValue = $("#simpleConfigValue").val();
		if (simpleTypeName == "") {
			humane.error("配置名称不能为空！");
		} else if (simpleConfigValue == "") {
			humane.error("配置数值不能为空！");
		} else {
			$.post("/modifyconfig.action", {
				"systemConfig.id" : simpleConfigId,
				"systemConfig.configType" : configType,
				"systemConfig.configTypeName" : simpleTypeName,
				"systemConfig.configValue" : simpleConfigValue
			}, function(result) {
				$("#modifySystemdiv").slideUp(); // 隐藏
				humane.success("已修改成功");
				window.location.reload(true);
			}, "html");
		}
	});

});
