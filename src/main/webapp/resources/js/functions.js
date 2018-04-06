function fncDelete(id, idName , tableName) {
	var deleteStatus = confirm("Are you sure to delete?");
	if (deleteStatus) {
		$.ajax({
			url : '/tasklist/taskdelete',
			type: 'post',
			data: {'id': id, 'idName':idName, 'tableName':tableName},
			success : function(data) {
				if (data == "") {
					alert("Delete error!");
				}else {
					$('tr#'+data).fadeOut("slow");
					pageCount();
				}
			}
		});
		
	}
}