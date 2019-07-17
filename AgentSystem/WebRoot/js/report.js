$().ready(function() {
	mover(3);
	$("#reporttype").change(function() {
		if ($("#reporttype").val() != 1) {
			$("#opertime").css("display", "block");
			$("#nodatesubmit").css("display", "none");
		} else {
			$("#nodatesubmit").css("display", "block");
			$("#opertime").css("display", "none");
		}
	});
});

function searchReportFunc() {
	if (comtime($("#starttime").val(), $("#endtime").val())) {
		humane.error("开始时间大于结束时间，请检查");
		return false;
	}
	return true;
}

function comtime(s, e) {
	var arr1 = s.split("-");
	var starttime = new Date(arr1[0], arr1[1], arr1[2]);
	var ss = starttime.getTime();
	var arr2 = e.split("-");
	var endtime = new Date(arr2[0], arr2[1], arr2[2]);
	var es = endtime.getTime();
	return (ss > es);
}