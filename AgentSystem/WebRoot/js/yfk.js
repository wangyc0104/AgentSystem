function searchyfklistFunc() {
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