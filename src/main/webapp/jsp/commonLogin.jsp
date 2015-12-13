<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/select2/select2.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/admin/pages/css/login-soft.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME STYLES -->
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/css/components-md.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/css/plugins-md.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link id="style_color" href="${pageContext.servletContext.contextPath }/resources/assets/admin/layout/css/themes/darkblue.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/select2/select2.min.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/admin/pages/scripts/login-soft.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/select2/select2.min.js"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
<!-- 表单校验 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>

<script>
jQuery(document).ready(function() {     
  Metronic.init(); // init metronic core components
Layout.init(); // init current layout
  Login.init();
  Demo.init();
       // init background slide images
       $.backstretch([
        "${pageContext.servletContext.contextPath }/resources/assets/admin/pages/media/bg/1.jpg",
        "${pageContext.servletContext.contextPath }/resources/assets/admin/pages/media/bg/2.jpg",
        "${pageContext.servletContext.contextPath }/resources/assets/admin/pages/media/bg/3.jpg",
        "${pageContext.servletContext.contextPath }/resources/assets/admin/pages/media/bg/4.jpg"
        ], {
          fade: 1300,
          duration: 1800
    }
    );
});
</script>