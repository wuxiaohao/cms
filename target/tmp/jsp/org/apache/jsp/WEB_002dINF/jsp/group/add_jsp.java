package org.apache.jsp.WEB_002dINF.jsp.group;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class add_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_sf_input_path_class_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_sf_form_modelAttribute_method_id_class_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_sf_textarea_rows_path_class_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_sf_input_path_class_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_sf_form_modelAttribute_method_id_class_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_sf_textarea_rows_path_class_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_sf_input_path_class_nobody.release();
    _jspx_tagPool_sf_form_modelAttribute_method_id_class_action.release();
    _jspx_tagPool_sf_textarea_rows_path_class_nobody.release();
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
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t$(\"#addForm\").cmsvalidate();\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("</script>\r\n");
      out.write("<div class=\"row\">\r\n");
      out.write("\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t<!-- BEGIN PAGE TITLE & BREADCRUMB-->\r\n");
      out.write("\t\t<h3 class=\"page-title\">\r\n");
      out.write("\t\t\t用户组管理\r\n");
      out.write("\t\t\t<small> <i class=\"fa fa-shopping-cart\"></i> 增加删除修改用户组信息 </small>\r\n");
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
      out.write("\t\t\t\t<a href=\"admin/group/groups\" class=\"ajaxify\">用户组管理</a>\r\n");
      out.write("\t\t\t\t>>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t\t<a href=\"admin/group/addUI/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.id }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"ajaxify\">添加用户组</a>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<!-- END PAGE TITLE & BREADCRUMB-->\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"row\">\r\n");
      out.write("\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t<!-- BEGIN VALIDATION STATES-->\r\n");
      out.write("\t\t<div class=\"portlet box purple\">\r\n");
      out.write("\t\t\t<div class=\"portlet-title\">\r\n");
      out.write("\t\t\t\t<div class=\"caption\">\r\n");
      out.write("\t\t\t\t\t<i class=\"fa fa-gift\"></i>修改用户组功能\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"tools\">\r\n");
      out.write("\t\t\t\t\t<a href=\"javascript:;\" class=\"collapse\">\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"portlet-body\">\r\n");
      out.write("\t\t\t\t<!-- BEGIN FORM-->\r\n");
      out.write("\t\t\t\t");
      if (_jspx_meth_sf_form_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t<!-- END FORM-->\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- END VALIDATION STATES-->\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
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

  private boolean _jspx_meth_sf_form_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sf:form
    org.springframework.web.servlet.tags.form.FormTag _jspx_th_sf_form_0 = (org.springframework.web.servlet.tags.form.FormTag) _jspx_tagPool_sf_form_modelAttribute_method_id_class_action.get(org.springframework.web.servlet.tags.form.FormTag.class);
    _jspx_th_sf_form_0.setPageContext(_jspx_page_context);
    _jspx_th_sf_form_0.setParent(null);
    _jspx_th_sf_form_0.setId("addForm");
    _jspx_th_sf_form_0.setMethod("post");
    _jspx_th_sf_form_0.setModelAttribute("group");
    _jspx_th_sf_form_0.setAction("admin/group/add");
    _jspx_th_sf_form_0.setDynamicAttribute(null, "class", new String("form-horizontal ajaxiform"));
    int[] _jspx_push_body_count_sf_form_0 = new int[] { 0 };
    try {
      int _jspx_eval_sf_form_0 = _jspx_th_sf_form_0.doStartTag();
      if (_jspx_eval_sf_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t<div class=\"form-body\">\r\n");
          out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
          out.write("\t\t\t\t\t\t\t<label class=\"control-label col-md-3\">角色名称 <span class=\"required\">\r\n");
          out.write("\t\t\t\t\t\t\t* </span>\r\n");
          out.write("\t\t\t\t\t\t\t</label>\r\n");
          out.write("\t\t\t\t\t\t\t<div class=\"col-md-4\">\r\n");
          out.write("\t\t\t\t\t\t\t\t<div class=\"input-icon right\">\r\n");
          out.write("\t\t\t\t\t\t\t\t\t");
          if (_jspx_meth_sf_input_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_sf_form_0, _jspx_page_context, _jspx_push_body_count_sf_form_0))
            return true;
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t</div>\r\n");
          out.write("\t\t\t\t\t\t\t</div>\r\n");
          out.write("\t\t\t\t\t\t</div>\r\n");
          out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
          out.write("\t\t\t\t\t\t\t<label class=\"control-label col-md-3\">用户组描述<span class=\"required\">\r\n");
          out.write("\t\t\t\t\t\t\t* </span>\r\n");
          out.write("\t\t\t\t\t\t\t</label>\r\n");
          out.write("\t\t\t\t\t\t\t<div class=\"col-md-4\">\r\n");
          out.write("\t\t\t\t\t\t\t\t<div class=\"input-icon right\">\r\n");
          out.write("\t\t\t\t\t\t\t\t\t");
          if (_jspx_meth_sf_textarea_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_sf_form_0, _jspx_page_context, _jspx_push_body_count_sf_form_0))
            return true;
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t</div>\r\n");
          out.write("\t\t\t\t\t\t\t</div>\r\n");
          out.write("\t\t\t\t\t\t</div>\r\n");
          out.write("\t\t\t\t\t</div>\r\n");
          out.write("\t\t\t\t\t<div class=\"form-actions\">\r\n");
          out.write("\t\t\t\t\t\t<div class=\"row\">\r\n");
          out.write("\t\t\t\t\t\t\t<div class=\"col-md-offset-3 col-md-9\">\r\n");
          out.write("\t\t\t\t\t\t\t\t<button type=\"submit\" class=\"btn green\">提交</button>\r\n");
          out.write("\t\t\t\t\t\t\t\t<a class=\"btn default ajaxify\" href=\"admin/group/groups\">取消</a>\r\n");
          out.write("\t\t\t\t\t\t\t</div>\r\n");
          out.write("\t\t\t\t\t\t</div>\r\n");
          out.write("\t\t\t\t\t</div>\r\n");
          out.write("\t\t\t\t");
          int evalDoAfterBody = _jspx_th_sf_form_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_sf_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_sf_form_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_sf_form_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_sf_form_0.doFinally();
      _jspx_tagPool_sf_form_modelAttribute_method_id_class_action.reuse(_jspx_th_sf_form_0);
    }
    return false;
  }

  private boolean _jspx_meth_sf_input_0(javax.servlet.jsp.tagext.JspTag _jspx_th_sf_form_0, PageContext _jspx_page_context, int[] _jspx_push_body_count_sf_form_0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sf:input
    org.springframework.web.servlet.tags.form.InputTag _jspx_th_sf_input_0 = (org.springframework.web.servlet.tags.form.InputTag) _jspx_tagPool_sf_input_path_class_nobody.get(org.springframework.web.servlet.tags.form.InputTag.class);
    _jspx_th_sf_input_0.setPageContext(_jspx_page_context);
    _jspx_th_sf_input_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_sf_form_0);
    _jspx_th_sf_input_0.setPath("name");
    _jspx_th_sf_input_0.setDynamicAttribute(null, "class", new String("form-control"));
    int[] _jspx_push_body_count_sf_input_0 = new int[] { 0 };
    try {
      int _jspx_eval_sf_input_0 = _jspx_th_sf_input_0.doStartTag();
      if (_jspx_th_sf_input_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_sf_input_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_sf_input_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_sf_input_0.doFinally();
      _jspx_tagPool_sf_input_path_class_nobody.reuse(_jspx_th_sf_input_0);
    }
    return false;
  }

  private boolean _jspx_meth_sf_textarea_0(javax.servlet.jsp.tagext.JspTag _jspx_th_sf_form_0, PageContext _jspx_page_context, int[] _jspx_push_body_count_sf_form_0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sf:textarea
    org.springframework.web.servlet.tags.form.TextareaTag _jspx_th_sf_textarea_0 = (org.springframework.web.servlet.tags.form.TextareaTag) _jspx_tagPool_sf_textarea_rows_path_class_nobody.get(org.springframework.web.servlet.tags.form.TextareaTag.class);
    _jspx_th_sf_textarea_0.setPageContext(_jspx_page_context);
    _jspx_th_sf_textarea_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_sf_form_0);
    _jspx_th_sf_textarea_0.setPath("descr");
    _jspx_th_sf_textarea_0.setDynamicAttribute(null, "class", new String("form-control"));
    _jspx_th_sf_textarea_0.setRows("3");
    int[] _jspx_push_body_count_sf_textarea_0 = new int[] { 0 };
    try {
      int _jspx_eval_sf_textarea_0 = _jspx_th_sf_textarea_0.doStartTag();
      if (_jspx_th_sf_textarea_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_sf_textarea_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_sf_textarea_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_sf_textarea_0.doFinally();
      _jspx_tagPool_sf_textarea_rows_path_class_nobody.reuse(_jspx_th_sf_textarea_0);
    }
    return false;
  }
}
