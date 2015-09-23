package org.apache.jsp.WEB_002dINF.jsp.role;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class list_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_var_items.release();
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t$(\"a.delete\").confirmOperator();\r\n");
      out.write("\t\tvar success='");
      out.print(request.getAttribute("success"));
      out.write("';\r\n");
      out.write("\t\tvar error='");
      out.print(request.getAttribute("error"));
      out.write("';\r\n");
      out.write("\t\tif (success != 'null') {\r\n");
      out.write("\t\t\ttoastr.success(success);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif (error != 'null') {\r\n");
      out.write("\t\t\ttoastr.error(error);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<div class=\"row\">\r\n");
      out.write("\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t<!-- BEGIN PAGE TITLE & BREADCRUMB-->\r\n");
      out.write("\t\t<h3 class=\"page-title\">\r\n");
      out.write("\t\t\t角色设置\r\n");
      out.write("\t\t\t<small> <i class=\"fa fa-shopping-cart\"></i> 增加删除修改角色 </small>\r\n");
      out.write("\t\t</h3>\r\n");
      out.write("\t\t<ul class=\"page-breadcrumb breadcrumb\">\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t\t<i class=\"fa fa-home\"></i>\r\n");
      out.write("\t\t\t\t<a class=\"ajaxify start\" href=\"layout_ajax_content_1.html\">首页</a>\r\n");
      out.write("\t\t\t\t>>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t\t<a>组织机构管理</a>\r\n");
      out.write("\t\t\t\t>>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t\t<a href=\"admin/role/roles\" class=\"ajaxify\">角色管理</a>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<!-- END PAGE TITLE & BREADCRUMB-->\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<!-- BEGIN PAGE CONTENT-->\r\n");
      out.write("<div class=\"row\">\r\n");
      out.write("\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t<!-- BEGIN EXAMPLE TABLE PORTLET-->\r\n");
      out.write("\t\t<div class=\"portlet box blue\">\r\n");
      out.write("\t\t\t<div class=\"portlet-title\">\r\n");
      out.write("\t\t\t\t<div class=\"caption\">\r\n");
      out.write("\t\t\t\t\t<i class=\"fa fa-edit\"></i>\r\n");
      out.write("\t\t\t\t\t角色列表\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"tools\">\r\n");
      out.write("\t\t\t\t\t<a href=\"javascript:;\" class=\"collapse\"> </a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"portlet-body\">\r\n");
      out.write("\t\t\t\t<div class=\"table-toolbar\">\r\n");
      out.write("\t\t\t\t\t<div class=\"btn-group\">\r\n");
      out.write("\t\t\t\t\t\t<a class=\"btn green ajaxify\" href=\"admin/role/addUI\">新增角色</a>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<table class=\"table table-striped table-hover table-bordered\" id=\"sample_1\">\r\n");
      out.write("\t\t\t\t\t<thead>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<th style=\"text-align:center\" class=\"table-checkbox\"><input type=\"checkbox\"\r\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"group-checkable\" data-set=\"#sample_1 .checkboxes\" />\r\n");
      out.write("\t\t\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th>角色名称</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th>角色类型</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th>操作</th>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</thead>\r\n");
      out.write("\t\t\t\t\t<tbody>\r\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_c_forEach_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- END EXAMPLE TABLE PORTLET-->\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- END PAGE CONTENT -->");
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

  private boolean _jspx_meth_c_forEach_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent(null);
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${roles}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_0.setVar("role");
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t<tr>\r\n");
          out.write("\t\t\t\t\t\t\t\t<td style=\"text-align:center\"><input type=\"checkbox\" class=\"checkboxes\"\r\n");
          out.write("\t\t\t\t\t\t\t\t\tname=\"checkid\" value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${role.id }", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" /></td>\r\n");
          out.write("\t\t\t\t\t\t\t\t<td><a href=\"admin/role/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${role.id }", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" class=\"ajaxify\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${role.name }", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</a></td>\r\n");
          out.write("\t\t\t\t\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${role.roleType }", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</td>\r\n");
          out.write("\t\t\t\t\t\t\t\t<td><a href=\"admin/role/delete/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${role.id }", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" class=\"btn btn-sm red ajaxify delete\"> 删除 </a>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t<a href=\"admin/role/updateUI/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${role.id }", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" class=\"btn btn-sm blue ajaxify\"> 更新 </a>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t<a href=\"admin/role/clearUsers/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${role.id }", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" class=\"btn btn-sm yellow ajaxify delete\"> 清空用户 </a>\r\n");
          out.write("\t\t\t\t\t\t\t\t</a></td>\r\n");
          out.write("\t\t\t\t\t\t\t</tr>\r\n");
          out.write("\t\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }
}
