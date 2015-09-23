<!-- css -->
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/select2/select2.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/admin/pages/css/login.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/admin/pages/css/error.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jstree/dist/themes/default/style.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-toastr/toastr.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME STYLES -->
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/css/components.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link id="style_color" href="${pageContext.servletContext.contextPath }/resources/assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>


<!-- js -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/resourcespond.min.js"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.servletContext.contextPath }/resources/myjs/table-managed.js" ></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jstree/dist/jstree.min.js"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jstree/dist/jstree.js"></script>

 
<!-- END PAGE LEVEL SCRIPTS -->
<script src="${pageContext.servletContext.contextPath }/resources/myjs/jquery.form.js" ></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>

<!-- 表单校验 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/validate/main.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.login.validate.js"></script>

<script>
		jQuery(document).ready(function() {     
		  Metronic.init(); // init metronic core components
Layout.init(); // init current layout
QuickSidebar.init();// init quick sidebar
		});
	</script>
<!-- END JAVASCRIPTS -->
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
  ga('create', 'UA-37564768-1', 'keenthemes.com');
  ga('send', 'pageview');
</script>