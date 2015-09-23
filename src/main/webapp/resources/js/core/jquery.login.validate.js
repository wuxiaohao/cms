(function($){
	var __validate = $.fn.validate;
	$.fn.cmsvalidate = function(opts) {
		var __rules = $.extend({
			username:"required",
			password:"required",
			checkcode:"required"
		},opts?(opts.rules||{}):{});
		var __messages = $.extend({
			username:"用户名不能为空",
			password:"密码不能为空",
			checkcode:"验证码不能为空"
		},opts?(opts.messages||{}):{});
		var __defaultOpts = $.extend(opts||{},{
			rules:__rules,
			messages:__messages,
			errorElement: opts?(opts.errorElement||"span"):"span",
			errorClass:opts?(opts.errorClass||"errorContainer"):"errorContainer"
		});
		$.extend($.fn.validate.prototype,__defaultOpts);
		__validate.call(this,__defaultOpts);
		return this;
	}
})(jQuery)