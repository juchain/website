$().ready(function() {
	loadAppType();
	loadEnvType();
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/application/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名字"
			}
		}
	})
}

function loadAppType() {
	var html = "";
	$.ajax({
		url : '/common/sysDict/list/application_type',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {

					html += '<option value="' + data[i].value
							+ '">' + data[i].name
							+ '</option>'
			}
			$("#appType").append(html);
			$("#appType").chosen({
				maxHeight : 200
			});
			
			$("#appType").val($("#happtype").text());
			$("#appType").trigger("chosen:updated");
			//点击事件
			$('#appType').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				$('#exampleTable').bootstrapTable('refresh', opt);
			});
		}
	});
}

function loadEnvType() {
	var html = "";
	$.ajax({
		url : '/common/sysDict/list/env_type',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value
                    + '">' + data[i].name
                    + '</option>'

            }

				$("#envType").append(html);
				$("#envType").chosen({
					maxHeight : 200
				});


            $("#envType").val($("#henvType").text());
            $("#envType").trigger("chosen:updated");
				//点击事件
				$('#envType').on('change', function(e, params) {
					console.log(params.selected);
					var opt = {
						query : {
							type : params.selected,
						}
					}
					$('#exampleTable').bootstrapTable('refresh', opt);
				});
			}

	});
}