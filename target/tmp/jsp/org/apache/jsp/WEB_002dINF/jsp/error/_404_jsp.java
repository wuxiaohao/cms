package org.apache.jsp.WEB_002dINF.jsp.error;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.util.*;

public final class _404_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/jsp/common.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<!--[if IE 8]> <html lang=\"en\" class=\"ie8 no-js\"> <![endif]-->\n");
      out.write("<!--[if IE 9]> <html lang=\"en\" class=\"ie9 no-js\"> <![endif]-->\n");
      out.write("<!--[if !IE]><!-->\n");
      out.write("<!--<![endif]-->\n");
      out.write("<!-- BEGIN HEAD -->\n");
      out.write("<head>\n");
      out.write("<meta charset=\"utf-8\"/>\n");
      out.write("<title><spring:message code=\"title\"/></title>\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\"/>\n");
      out.write("<meta content=\"\" name=\"description\"/>\n");
      out.write("<meta content=\"\" name=\"author\"/>\n");
      out.write("<!-- 获取总资源 -->\n");
      out.write("<!-- css -->\n");
      out.write("<!-- BEGIN GLOBAL MANDATORY STYLES -->\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/simple-line-icons/simple-line-icons.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/uniform/css/uniform.default.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<!-- END GLOBAL MANDATORY STYLES -->\n");
      out.write("<!-- BEGIN PAGE LEVEL STYLES -->\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.css\"/>\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/select2/select2.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/jquery-multi-select/css/multi-select.css\"/>\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/admin/pages/css/login.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/admin/pages/css/error.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/jstree/dist/themes/default/style.min.css\"/>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css\"/>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/bootstrap-toastr/toastr.min.css\"/>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css\"/>\n");
      out.write("<!-- END PAGE LEVEL SCRIPTS -->\n");
      out.write("<!-- BEGIN THEME STYLES -->\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/css/components.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/css/plugins.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/admin/layout/css/layout.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<link id=\"style_color\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/admin/layout/css/themes/default.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/admin/layout/css/custom.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- js -->\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->\n");
      out.write("<!-- BEGIN CORE PLUGINS -->\n");
      out.write("<!--[if lt IE 9]>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/resourcespond.min.js\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/excanvas.min.js\"></script> \n");
      out.write("<![endif]-->\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/jquery-1.11.0.min.js\" type=\"text/javascript\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/jquery-migrate-1.2.1.min.js\" type=\"text/javascript\"></script>\n");
      out.write("<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js\" type=\"text/javascript\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/bootstrap/js/bootstrap.min.js\" type=\"text/javascript\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js\" type=\"text/javascript\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js\" type=\"text/javascript\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/jquery.blockui.min.js\" type=\"text/javascript\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/jquery.cokie.min.js\" type=\"text/javascript\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/uniform/jquery.uniform.min.js\" type=\"text/javascript\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js\" type=\"text/javascript\"></script>\n");
      out.write("<!-- END CORE PLUGINS -->\n");
      out.write("\n");
      out.write("<!-- BEGIN PAGE LEVEL PLUGINS -->\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/jquery-validation/js/jquery.validate.min.js\" type=\"text/javascript\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/select2/select2.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/bootstrap-toastr/toastr.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/jstree/dist/jstree.min.js\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/jstree/dist/jstree.js\"></script>\n");
      out.write("\n");
      out.write(" \n");
      out.write("<!-- END PAGE LEVEL SCRIPTS -->\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/scripts/metronic.js\" type=\"text/javascript\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/admin/layout/scripts/layout.js\" type=\"text/javascript\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/admin/layout/scripts/quick-sidebar.js\" type=\"text/javascript\"></script>\n");
      out.write("\n");
      out.write("<!-- 表单校验 -->\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/js/jquery.validate.js\"></script>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(request.getContextPath() );
      out.write("/resources/css/validate/main.css\"/>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/js/core/validate-methods.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/js/core/jquery.cms.validate.js\"></script>\n");
      out.write("<!-- ztree -->\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(request.getContextPath() );
      out.write("/resources/css/zTree/zTreeStyle.css\"/>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/js/tree/jquery.ztree.core-3.5.min.js\"></script>\n");
      out.write("<!-- 导入ztree可选框的js包 -->\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/js/tree/jquery.ztree.excheck-3.5.min.js\"></script>\n");
      out.write("\n");
      out.write("<!-- 新增 -->\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/bootbox.js\"></script>\n");
      out.write("\n");
      out.write("<!-- 自定义插件 -->\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/js/core/jquery.cms.core.js\"></script>\n");
      out.write("<!-- 必须引入dwr的engine.js -->\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/dwr/engine.js\"></script>\n");
      out.write("<!-- 将java的类引入 -->\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/dwr/interface/dwrService.js\"></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("\tjQuery(document).ready(function() {     \n");
      out.write("\t\tMetronic.init(); // init metronic core components\n");
      out.write("\t\tLayout.init(); // init current layout\n");
      out.write("\t\tQuickSidebar.init();// init quick sidebar\n");
      out.write("\t\t\n");
      out.write("\t\t//初始化配置toastr\n");
      out.write("\t\ttoastr.options = {\n");
      out.write("\t\t        \"closeButton\": true, //是否显示关闭按钮\n");
      out.write("\t\t        \"debug\": false, //是否使用debug模式\n");
      out.write("\t\t        \"positionClass\": \"toast-top-right\",//弹出窗的位置\n");
      out.write("\t\t        \"showDuration\": \"300\",//显示的动画时间\n");
      out.write("\t\t        \"hideDuration\": \"1000\",//消失的动画时间\n");
      out.write("\t\t        \"timeOut\": \"4000\", //展现时间\n");
      out.write("\t\t        \"extendedTimeOut\": \"1000\",//加长展示时间\n");
      out.write("\t\t        \"showEasing\": \"swing\",//显示时的动画缓冲方式\n");
      out.write("\t\t        \"hideEasing\": \"linear\",//消失时的动画缓冲方式\n");
      out.write("\t\t        \"showMethod\": \"fadeIn\",//显示时的动画方式\n");
      out.write("\t\t        \"hideMethod\": \"fadeOut\" //消失时的动画方式\n");
      out.write("\t\t        };\n");
      out.write("\t\t});\n");
      out.write("</script>\n");
      out.write("<!-- END JAVASCRIPTS -->\n");
      out.write("<script>\n");
      out.write("  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){\n");
      out.write("  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),\n");
      out.write("  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)\n");
      out.write("  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');\n");
      out.write("  ga('create', 'UA-37564768-1', 'keenthemes.com');\n");
      out.write("  ga('send', 'pageview');\n");
      out.write("</script>");
      out.write("\n");
      out.write("</head>\n");
      out.write("<body class=\"page-404-full-page\">\n");
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"row\">\n");
      out.write("\t<div class=\"col-md-12 page-404\">\n");
      out.write("\t\t<div class=\"number\">\n");
      out.write("\t\t\t 404\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"details\">\n");
      out.write("\t\t\t<h3>不好意思！页面不存在</h3>\n");
      out.write("\t\t\t<p>\n");
      out.write("\t\t\t\t我们没有发现你所请求的路径.<br/>\n");
      out.write("\t\t\t\t<a href=\"admin\">返回主页</a> 或尝试搜索你所要的内容. \n");
      out.write("\t\t\t</p>\n");
      out.write("\t\t\t\t<div class=\"input-group input-medium\">\n");
      out.write("\t\t\t\t\t<input type=\"text\" class=\"form-control\" name=\"keyword\" id=\"keyword\">\n");
      out.write("\t\t\t\t\t<span class=\"input-group-btn\">\n");
      out.write("\t\t\t\t\t<button type=\"submit\" class=\"btn blue\" onclick=\"keyword();\"><i class=\"fa fa-search\"></i></button>\n");
      out.write("\t\t\t\t\t</span>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<!-- /input-group -->\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
      out.write("<script>\n");
      out.write("function keyword(){ \n");
      out.write("var keyword=$(\"#keyword\").val();\n");
      out.write("window.open(\"http://www.baidu.com/s?wd=\"+keyword);\n");
      out.write("}\n");
      out.write("</script>\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("<!-- END BODY -->\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
