(function($){
	var __validate = $.fn.validate;
	$.fn.cmsvalidate = function(opts) {
		var __rules = $.extend({
			username:{
				 required: true,
				 noChinese:true,
				 minlength: 3,
				 maxlength: 16,
				 remote: {
                    type: "post",
                    url: $("#contextPath").val()+"/admin/user/checkUserName",
                    data: {
                        username: function() {
                            return $("#username").val();
                        }
                    },
                    dataType: "html",
                    dataFilter: function(data, type) {
                        if (data == "true")
                            return true;
                        else
                            return false;
                    }
                }
			},
			nickname:{
				required: true,
				minlength: 2,
				maxlength: 10
			},
			password:"required",
			name:{
				required: true,
				maxlength: 10
			},
			confirmPwd:{
				equalTo:"#password"
			},
			email:{
				required: true,
				email: true
			},
			phone:{
				isMobile:true
			},
			title:"required",
			cname:{
				required: true
			},
			newName:"required",
			checkcode:"required",
			type:{
				required: true,
				maxlength: 10
			},
			picNameOlds:"required"
		},opts?(opts.rules||{}):{});
		var __messages = $.extend({
			username:{
				required: "请输入用户名，3-16个字符（字母、数字、下划线），注册后不能更改",
				noChinese:"只能包含英文、数字、下划线字符",
				minlength: "用户名长度不能小于3个字符",
				maxlength: "用户名长度不能大于16个字符",
	            remote: "用户名已存在！请重新输入"
			},
			nickname:{
				required: "显示名称不能为空",
				minlength: "显示名称长度不能小于2个字符",
				maxlength: "显示名称长度不能大于10个字符",
			},
			password:"密码不能为空",
			confirmPwd:"两次输入的密码不相同",
			email:{
				required:"邮箱不能为空",
				email:"邮件格式不正确",
			},
			name:{
				required: "名称不能为空",
				maxlength: "名称长度不能大于10个字符"
			},
			cname:"属栏目不能为空！",
			title:"标题不能为空！",
			newName:"首页图片必须上传",
			checkcode:"验证码不能为空",
			type:{
				required: "类型不能为空",
				maxlength: "类型长度不能大于10个字符"
			},
			picNameOlds:"图片名称不能为空！"
		},opts?(opts.messages||{}):{});
		var __defaultOpts = $.extend(opts||{},{
			rules:__rules,
			messages:__messages,
			errorElement: opts?(opts.errorElement||"span"):"span",
			successElement: opts?(opts.errorElement||"span"):"span",
			errorPlacement: function(error, element) { //指定错误信息位置 
				if(element.is("input[name=picNameOlds]")) {
					error.appendTo(".checkpicnull"); 
				} else {
					error.appendTo( element.parent().parent()); 
				}
			},
			errorClass:opts?(opts.errorClass||"errorContainer"):"errorContainer"
		});
		$.extend($.fn.validate.prototype,__defaultOpts);
		__validate.call(this,__defaultOpts);
		return this;
	}
})(jQuery)