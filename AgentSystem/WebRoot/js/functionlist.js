$().ready(function() {

	// 全选功能
	$("#cball").change(function() {
		var checkList = ""; // 保存全选的结果
		var cblist = $(".cb"); // 获取所有class="cb"的checkbox
		// var roleId = $("#roleid").val();
		for ( var i = 0; i < cblist.length; i++) {
			cblist[i].checked = $(this).attr("checked") == 'checked' ? 'checked' : '';
		}
	});

	// 保存选定的功能
	$("#saverolefunc").click(function() {
		var checkList = ""; // 已选
		var cblist = $(".cb");
		var roleId = $("#roleid").val();
		for ( var i = 0; i < cblist.length; i++) {
			if (cblist[i].checked) {
				checkList = checkList + cblist[i].value;
				checkList = checkList + ",";
			}
		}
		$.post("/saverolefunc.action", {
			"roleId" : roleId,
			"checkList" : checkList
		}, function(result) {
			if (result == "success")
				humane.success("保存成功");
			else
				humane.error("保存失败");
		}, "html");
	});
	
});