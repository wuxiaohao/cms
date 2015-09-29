var Toastr = function() {
	//四种提示类型success、info、warning、error
	//8个展示方向Top Right
	//	Bottom Right
	//	Bottom Left
	//	Top Left
	//	Top Center
	//	Bottom Center
	//	Top Full Width
	//	Bottom Full Width
	function showtoastr(shortCutFunction, msg, title) {
		var i = -1,
		//基本配置
		toastCount = 0, $timeOut = 5000, $showDuration = 1000, $hideDuration = 1000, $extendedTimeOut = 1000, $showEasing = 'swing', $hideEasing = 'linear', $showMethod = 'fadeIn', $hideMethod = 'fadeOut';
		var toastIndex = toastCount++;
		toastr.options = {
			closeButton : true,
			debug : false,
			positionClass : 'toast-top-right',
			onclick : null
		};
		//点击事件，暂时无用

		//            if ($('#addBehaviorOnToastClick').prop('checked')) {
		//                toastr.options.onclick = function () {
		//                    alert('You can perform some custom action after a toast goes away');
		//                };
		//            }

		if ($showDuration.length) {
			toastr.options.showDuration = $showDuration;
		}

		if ($hideDuration.length) {
			toastr.options.hideDuration = $hideDuration;
		}

		if ($timeOut.length) {
			toastr.options.timeOut = $timeOut;
		}

		if ($extendedTimeOut.length) {
			toastr.options.extendedTimeOut = $extendedTimeOut;
		}

		if ($showEasing.length) {
			toastr.options.showEasing = $showEasing;
		}

		if ($hideEasing.length) {
			toastr.options.hideEasing = $hideEasing;
		}

		if ($showMethod.length) {
			toastr.options.showMethod = $showMethod;
		}

		if ($hideMethod.length) {
			toastr.options.hideMethod = $hideMethod;
		}

		if (!msg) {
			msg = "结果";
		}

		var $toast = toastr[shortCutFunction](msg, title); // Wire up an event handler to a button in the toast, if it exists

		$toastlast = $toast;
		if ($toast.find('#okBtn').length) {
			$toast.delegate('#okBtn', 'click', function() {
				alert('you clicked me. i was toast #' + toastIndex
						+ '. goodbye!');
				$toast.remove();
			});
		}
		if ($toast.find('#surpriseBtn').length) {
			$toast.delegate('#surpriseBtn', 'click', function() {
				alert('Surprise! you clicked me. i was toast #' + toastIndex
						+ '. You could perform an action here.');
			});
		}

	}
	return {
		//main function to initiate the module
		showtoastr : function(shortCutFunction,msg, title) {
			showtoastr(shortCutFunction,msg, title);
		}

	};

}();