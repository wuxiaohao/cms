
function ajaxformbyclass(Class) {
/*	 Metronic.scrollTop();
	  Metronic.startPageLoading();*/
    var url =$("."+Class).attr("action");
    var pageContent = $('.page-content');
    var pageContentBody = $('.page-content .page-content-body');
    $("."+Class).ajaxForm({
    	dataType: "html",
        success: function(res) {
            Layout.fixContentHeight(); // fix content height
            Metronic.initAjax(); // initialize core stuff
        	Metronic.stopPageLoading();
            pageContentBody.html(res);
         
        }
    });

   $("."+Class).submit();
}
