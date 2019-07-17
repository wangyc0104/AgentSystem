$().ready(function() {

	mover(1);

	$("#addCustomBtn").click(function() {
		location.href = "/addcustom.action";
	});

	$(".viewCustom").click(function() {
		var c = $(this);
		var c_id = c.attr("id");
		window.location.href = "/viewcustom.action?custom.id=" + c_id;
	});

	$(".modifyCustom").click(function() {
		var c = $(this); // a标签
		var c_id = c.attr("id");
		window.location.href = "/modifycustom.action?custom.id=" + c_id;
	});

	$(".modifyCustomStatus").click(function() {
		var c = $(this);
		var c_id = c.attr("id");
		var c_status = c.attr("mStatus");
		var c_customName = c.attr("customName");
		var action = c_status == 1 ? "停用": "启用";
		var str = ("您是否确定要" + (c_status.toString() == 1 ? "停用": "启用") + "[" + c_customName + "]这个客户？");
		if (confirm(str)) {
			$.post("/modifycustomstatus.action", {
				"custom.id": c_id,
				"custom.customName": c_customName,
				"custom.customStatus": c_status
			},
			function(result) {
				if (result == "success") {
					humane.success(action + "成功");
					window.location.reload(true);
				} else {
					humane.error(action + "失败");
				}
			},
			"html");
		}
	});

});