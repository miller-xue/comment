function remove(id) {
	$("#mainForm").attr("action",$("#basePath").val() + "/businesses/" + id);
	$("#mainForm").submit();
}

function search(currentPage) {
	$("#mainForm").attr("method","GET");

	$("#mainForm").attr("action",$("#basePath").val() + "/businesses");
    $("#currentPage").val(currentPage);
	$("#mainForm").submit();
}

function modifyInit(id) {
	location.href = $("#basePath").val() + "/businesses/" + id;
}