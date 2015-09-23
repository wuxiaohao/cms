package org.apache.jsp.WEB_002dINF.jsp.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/jsp/commonLogin.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_if_test.release();
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
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
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
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/global/plugins/select2/select2.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
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
      out.write("/resources/myjs/table-managed.js\" ></script>\n");
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
      out.write("/resources/myjs/jquery.form.js\" ></script>\n");
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
      out.write("/resources/js/core/jquery.login.validate.js\"></script>\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("\t\tjQuery(document).ready(function() {     \n");
      out.write("\t\t  Metronic.init(); // init metronic core components\n");
      out.write("Layout.init(); // init current layout\n");
      out.write("QuickSidebar.init();// init quick sidebar\n");
      out.write("\t\t});\n");
      out.write("\t</script>\n");
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
      out.write("<script type=\"text/javascript\">\n");
      out.write("\t$(function(){\n");
      out.write("\t\t$(\"#myForm\").cmsvalidate();\n");
      out.write("\t});\n");
      out.write("\t\n");
      out.write("\tfunction reCheckcode(img) {\n");
      out.write("\t\timg.src=\"drawCheckCode?\"+Math.random();\n");
      out.write("\t}\n");
      out.write("\t\n");
      out.write("\tif (window != top){\n");
      out.write("\t\ttop.location.href = location.href; \n");
      out.write("\t}\n");
      out.write("\t\n");
      out.write("</script>\n");
      out.write("<title>后台管理登录</title>\n");
      out.write("</head>\n");
      out.write("<body class=\"login\">\n");
      out.write("<!-- BEGIN LOGO -->\n");
      out.write("<div class=\"logo\">\n");
      out.write("\t<a href=\"index.html\">\n");
      out.write("\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.servletContext.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/assets/admin/layout/img/logo-big.png\" alt=\"\"/>\n");
      out.write("\t</a>\n");
      out.write("</div>\n");
      out.write("<!-- END LOGO -->\n");
      out.write("<!-- BEGIN SIDEBAR TOGGLER BUTTON -->\n");
      out.write("<div class=\"menu-toggler sidebar-toggler\">\n");
      out.write("</div>\n");
      out.write("<!-- END SIDEBAR TOGGLER BUTTON -->\n");
      out.write("<!-- BEGIN LOGIN -->\n");
      out.write("<div class=\"content\">\n");
      out.write("\t<!-- BEGIN LOGIN FORM -->\n");
      out.write("\t<form class=\"login-form\" id=\"myForm\" action=\"\" method=\"post\">\n");
      out.write("\t\t<h3 class=\"form-title\">请输入你的账号</h3>\n");
      out.write("\t\t<div style=\"color: red\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${error }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</div>\n");
      out.write("\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->\n");
      out.write("\t\t\t<label class=\"control-label visible-ie8 visible-ie9\">用户名</label>\n");
      out.write("\t\t\t<div class=\"input-icon\">\n");
      out.write("\t\t\t\t<i class=\"fa fa-user\"></i>\n");
      out.write("\t\t\t\t<input class=\"form-control placeholder-no-fix\" type=\"text\"\n");
      out.write("\t\t\t\t\t autocomplete=\"off\" placeholder=\"用户名\" name=\"username\" ");
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write("/>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t<label class=\"control-label visible-ie8 visible-ie9\">密码</label>\n");
      out.write("\t\t\t<div class=\"input-icon\">\n");
      out.write("\t\t\t\t<i class=\"fa fa-lock\"></i>\n");
      out.write("\t\t\t\t<input class=\"form-control placeholder-no-fix\" type=\"password\" autocomplete=\"off\" placeholder=\"密码\" name=\"password\"/>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->\n");
      out.write("\t\t\t<label class=\"control-label visible-ie8 visible-ie9\">验证码</label>\n");
      out.write("\t\t\t<div class=\"input-icon\">\n");
      out.write("\t\t\t\t<input class=\"form-control placeholder-no-fix\" type=\"text\" autocomplete=\"off\" placeholder=\"验证码\" name=\"checkcode\" id=\"validateCode\"/>\t\t\t\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div style=\"color: red\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${errorCode }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"form-actions\">\n");
      out.write("\t\t\t<label class=\"checkbox\">\n");
      out.write("\t\t\t<input type=\"checkbox\" value=\"1\" onclick=\"javascript:document.getElementById('hid').value=this.checked;\" /> 记住用户名 </label>\n");
      out.write("\t\t\t<input type=\"hidden\" id=\"hid\" name=\"remember\" value=\"false\"/>\n");
      out.write("\t\t\t<button type=\"submit\" class=\"btn green-haze pull-right\">\n");
      out.write("\t\t\t登录 <i class=\"m-icon-swapright m-icon-white\"></i>\n");
      out.write("\t\t\t</button>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"login-options\">\n");
      out.write("\t\t\t<h4>验证码</h4>\n");
      out.write("\t\t\t<div class=\"control-group\" style=\"text-align:center;cursor:pointer\">\n");
      out.write("\t\t\t\t<img src=\"drawCheckCode\" onclick=\"reCheckcode(this)\"/></span>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"forget-password\">\n");
      out.write("\t\t\t<h4>忘记你的密码?</h4>\n");
      out.write("\t\t\t<p>\n");
      out.write("\t\t\t\t 别担心, 请点击 <a href=\"javascript:;\" id=\"forget-password\">\n");
      out.write("\t\t\t\t这里</a>\n");
      out.write("\t\t\t\t申请重置你的密码.\n");
      out.write("\t\t\t</p>\n");
      out.write("\t\t</div>\n");
      out.write("\t</form>\n");
      out.write("\t<!-- END LOGIN FORM -->\n");
      out.write("\t<!-- BEGIN FORGOT PASSWORD FORM -->\n");
      out.write("\t<form class=\"forget-form\" action=\"index.html\" method=\"post\">\n");
      out.write("\t\t<h3>Forget Password ?</h3>\n");
      out.write("\t\t<p>\n");
      out.write("\t\t\t Enter your e-mail address below to reset your password.\n");
      out.write("\t\t</p>\n");
      out.write("\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t<div class=\"input-icon\">\n");
      out.write("\t\t\t\t<i class=\"fa fa-envelope\"></i>\n");
      out.write("\t\t\t\t<input class=\"form-control placeholder-no-fix\" type=\"text\" autocomplete=\"off\" placeholder=\"Email\" name=\"email\"/>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"form-actions\">\n");
      out.write("\t\t\t<button type=\"button\" id=\"back-btn\" class=\"btn\">\n");
      out.write("\t\t\t<i class=\"m-icon-swapleft\"></i> Back </button>\n");
      out.write("\t\t\t<button type=\"submit\" class=\"btn green-haze pull-right\">\n");
      out.write("\t\t\tSubmit <i class=\"m-icon-swapright m-icon-white\"></i>\n");
      out.write("\t\t\t</button>\n");
      out.write("\t\t</div>\n");
      out.write("\t</form>\n");
      out.write("\t<!-- END FORGOT PASSWORD FORM -->\n");
      out.write("</div>\n");
      out.write("<!-- END LOGIN -->\n");
      out.write("<!-- BEGIN COPYRIGHT -->\n");
      out.write("<div class=\"copyright\">\n");
      out.write("\t 2015 &copy; 城市学院. 吴晓豪 制作.\n");
      out.write("</div>\n");
      out.write("<script>\n");
      out.write("\tjQuery(document).ready(function() {\n");
      out.write("\t  Login.init();\n");
      out.write("\t});\n");
      out.write("</script>\n");
      out.write("</body>\n");
      out.write("</html>");
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

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${cms_cookie_username!=''}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${cms_cookie_username}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write('"');
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }
}
