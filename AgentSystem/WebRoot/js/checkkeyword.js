$().ready(function() {
	mover(4);
	$(".checkselect").change(function() {
		var obj = $(this);
		var v = obj.val();
		var kid = obj.attr("kid");
		var keyword = obj.attr("keyword");
		if (v==1) {// 审核中
			if (confirm("您确定要将" + keyword + "的状态修改为[审核中]吗？")) {
				$.post("/updatekeywords.action", {
					"keywords.id": kid,
					"keywords.checkStatus": 1
				}, function(result) {
					if (result=="success") {
						humane.success("恭喜您，" + keyword + "的状态被修改为[审核中]，操作成功");
						window.location.reload();
					} else {
						humane.error("对不起，" + keyword + "的状态修改失败，请重试");
					}
				}, "html");
			}
		} else if (v==2) {// 通过
			if (confirm("您确定要将" + keyword + "的状态修改为[审核通过]吗？")) {
				$.post("/updatekeywords.action", {
					"keywords.id": kid,
					"keywords.checkStatus": 2
				}, function(result) {
					if (result=="success") {
						humane.success("恭喜您，" + keyword + "的状态被修改为[审核通过]，操作成功");
						window.location.reload();
					} else {
						humane.error("对不起，" + keyword + "的状态修改失败，请重试");
					}
				}, "html");
			}
		} else if (v==3) {// 不通过
			if (confirm("您确定要将" + keyword + "的状态修改为[审核不通过]吗？")) {
				$.post("/updatekeywords.action", {
					"keywords.id": kid,
					"keywords.checkStatus": 3
				}, function(result) {
					if (result=="success") {
						humane.success("恭喜您，" + keyword + "的状态被修改为[审核不通过]，操作成功");
						window.location.reload();
					} else {
						humane.error("对不起，" + keyword + "的状态修改失败，请重试");
					}
				}, "html");
			}
		} else if (v==4) {// 续费


		} else if (v==5) {// 不使用
			if (confirm("您确定要将" + keyword + "的状态修改为[不使用]吗？")) {
				$.post("/updatekeywords.action", {
					"keywords.id": kid,
					"keywords.isUse": 0
				}, function(result) {
					if (result=="success") {
						humane.success("恭喜您，" + keyword + "的使用状态被修改为[不使用]，操作成功");
						window.location.reload();
					} else {
						humane.error("对不起，" + keyword + "的使用状态修改失败，请重试");
					}
				}, "html");
			}
		} else if (v==6) {// 使用
			if (confirm("您确定要将" + keyword + "的状态修改为[使用]吗？")) {
				$.post("/updatekeywords.action", {
					"keywords.id": kid,
					"keywords.isUse": 1
				}, function(result) {
					if (result=="success") {
						humane.success("恭喜您，" + keyword + "的使用状态被修改为[使用]，操作成功");
						window.location.reload();
					} else {
						humane.error("对不起，" + keyword + "的使用状态修改失败，请重试");
					}
				}, "html");
			}
		}
	});
});