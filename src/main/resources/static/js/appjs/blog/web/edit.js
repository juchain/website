$().ready(function() {
	$('.summernote').summernote({
		height : '220px',
		lang : 'zh-CN',
        callbacks: {
            onImageUpload: function(files, editor, $editable) {
                console.log("onImageUpload");
                sendFile(files);
            }
        }
    });
	var content = $("#content").val();

	$('#content_sn').summernote('code', content);
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save(status) {
	$("#status").val(status);

	var content_sn = $("#content_sn").summernote('code');
	$("#content").val(content_sn);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/web/bContent/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				

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
				required : icon + "请输入姓名"
			}
		}
	})
}

function returnList() {
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}



layui.use('upload', function () {
    var upload = layui.upload;
    //执行实例
    var uploadInst = upload.render({
        elem: '#test1', //绑定元素
        url: '/common/sysFile/upload', //上传接口
        size: 10000,
        accept: 'file',
        done: function (r) {
            layer.msg(r.msg);
            $("#coverUrl").val(r.fileName);
            $("#contentUrl").attr('src',r.fileName);


            $("#contentUrl").show()
            // $("#contentUrl").src(r.fileName);
        },
        error: function (r) {
            layer.msg(r.msg);
        }
    });
});




layui.use('upload', function () {
    var upload = layui.upload;
    //执行实例
    var uploadInst = upload.render({
        elem: '#test2', //绑定元素
        url: '/common/sysFile/upload', //上传接口
        size: 10000,
        accept: 'file',
        done: function (r) {
            layer.msg(r.msg);
            $("#url").val(r.fileName);
            $("#titleUrl").attr('src',r.fileName);
            $("#titleUrl").show()
        },
        error: function (r) {
            layer.msg(r.msg);
        }
    });
});


