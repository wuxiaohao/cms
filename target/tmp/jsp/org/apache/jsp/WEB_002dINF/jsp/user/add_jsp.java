package org.apache.jsp.WEB_002dINF.jsp.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class add_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_sf_option_value;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_sf_select_path_class;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_sf_password_path_class_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_sf_errors_path_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_sf_input_path_class_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_sf_checkboxes_path_items_itemValue_itemLabel_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_sf_form_modelAttribute_method_id_class_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_sf_errors_path_cssClass_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_sf_option_value = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_sf_select_path_class = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_sf_password_path_class_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_sf_errors_path_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_sf_input_path_class_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_sf_checkboxes_path_items_itemValue_itemLabel_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_sf_form_modelAttribute_method_id_class_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_sf_errors_path_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_sf_option_value.release();
    _jspx_tagPool_sf_select_path_class.release();
    _jspx_tagPool_sf_password_path_class_nobody.release();
    _jspx_tagPool_sf_errors_path_nobody.release();
    _jspx_tagPool_sf_input_path_class_nobody.release();
    _jspx_tagPool_sf_checkboxes_path_items_itemValue_itemLabel_nobody.release();
    _jspx_tagPool_sf_form_modelAttribute_method_id_class_action.release();
    _jspx_tagPool_sf_errors_path_cssClass_nobody.release();
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
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\t$(function(){\n");
      out.write("\t\t$(\"#addForm\").cmsvalidate();\n");
      out.write("\t});\n");
      out.write("\t\n");
      out.write("</script>\n");
      out.write("<div class=\"row\">\n");
      out.write("\t<div class=\"col-md-12\">\n");
      out.write("\t\t<!-- BEGIN PAGE TITLE & BREADCRUMB-->\n");
      out.write("\t\t<h3 class=\"page-title\">\n");
      out.write("\t\t\t用户信息管理\n");
      out.write("\t\t\t<small> <i class=\"fa fa-shopping-cart\"></i> 增加删除修改用户信息 </small>\n");
      out.write("\t\t</h3>\n");
      out.write("\t\t<ul class=\"page-breadcrumb breadcrumb\">\n");
      out.write("\t\t\t<li>\n");
      out.write("\t\t\t\t<i class=\"fa fa-home\"></i>\n");
      out.write("\t\t\t\t<a class=\"ajaxify start\" href=\"layout_ajax_content_1.html\">首页</a>\n");
      out.write("\t\t\t\t>>\n");
      out.write("\t\t\t</li>\n");
      out.write("\t\t\t<li>\n");
      out.write("\t\t\t\t<a>组织机构管理</a>\n");
      out.write("\t\t\t\t>>\n");
      out.write("\t\t\t</li>\n");
      out.write("\t\t\t<li>\n");
      out.write("\t\t\t\t<a href=\"admin/user/users\" class=\"ajaxify\">用户信息管理</a>\n");
      out.write("\t\t\t\t>>\n");
      out.write("\t\t\t</li>\n");
      out.write("\t\t\t<li>\n");
      out.write("\t\t\t\t<a href=\"admin/user/addUI\" class=\"ajaxify\">新增用户</a>\n");
      out.write("\t\t\t</li>\n");
      out.write("\t\t</ul>\n");
      out.write("\t\t<!-- END PAGE TITLE & BREADCRUMB-->\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
      out.write("<div class=\"row\">\n");
      out.write("\t<div class=\"col-md-12\">\n");
      out.write("\t\t<!-- BEGIN VALIDATION STATES-->\n");
      out.write("\t\t<div class=\"portlet box purple\">\n");
      out.write("\t\t\t<div class=\"portlet-title\">\n");
      out.write("\t\t\t\t<div class=\"caption\">\n");
      out.write("\t\t\t\t\t<i class=\"fa fa-gift\"></i>添加用户功能\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div class=\"tools\">\n");
      out.write("\t\t\t\t\t<a href=\"javascript:;\" class=\"collapse\">\n");
      out.write("\t\t\t\t\t</a>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"portlet-body\">\n");
      out.write("\t\t\t\t<!-- BEGIN FORM-->\n");
      out.write("\t\t\t\t");
      //  sf:form
      org.springframework.web.servlet.tags.form.FormTag _jspx_th_sf_form_0 = (org.springframework.web.servlet.tags.form.FormTag) _jspx_tagPool_sf_form_modelAttribute_method_id_class_action.get(org.springframework.web.servlet.tags.form.FormTag.class);
      _jspx_th_sf_form_0.setPageContext(_jspx_page_context);
      _jspx_th_sf_form_0.setParent(null);
      _jspx_th_sf_form_0.setId("addForm");
      _jspx_th_sf_form_0.setMethod("post");
      _jspx_th_sf_form_0.setModelAttribute("userDto");
      _jspx_th_sf_form_0.setAction("admin/user/add");
      _jspx_th_sf_form_0.setDynamicAttribute(null, "class", new String("form-horizontal ajaxiform"));
      int[] _jspx_push_body_count_sf_form_0 = new int[] { 0 };
      try {
        int _jspx_eval_sf_form_0 = _jspx_th_sf_form_0.doStartTag();
        if (_jspx_eval_sf_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\n");
            out.write("\t\t\t\t\t<div class=\"form-body\">\n");
            out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
            out.write("\t\t\t\t\t\t\t<label class=\"control-label col-md-3\">用户名 <span class=\"required\">\n");
            out.write("\t\t\t\t\t\t\t* </span>\n");
            out.write("\t\t\t\t\t\t\t</label>\n");
            out.write("\t\t\t\t\t\t\t<div class=\"col-md-4\">\n");
            out.write("\t\t\t\t\t\t\t\t<div class=\"input-icon right\">\n");
            out.write("\t\t\t\t\t\t\t\t\t");
            if (_jspx_meth_sf_input_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_sf_form_0, _jspx_page_context, _jspx_push_body_count_sf_form_0))
              return;
            out.write("\n");
            out.write("\t\t\t\t\t\t\t\t\t");
            //  sf:errors
            org.springframework.web.servlet.tags.form.ErrorsTag _jspx_th_sf_errors_0 = (org.springframework.web.servlet.tags.form.ErrorsTag) _jspx_tagPool_sf_errors_path_cssClass_nobody.get(org.springframework.web.servlet.tags.form.ErrorsTag.class);
            _jspx_th_sf_errors_0.setPageContext(_jspx_page_context);
            _jspx_th_sf_errors_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_sf_form_0);
            _jspx_th_sf_errors_0.setCssClass("errorContainer");
            _jspx_th_sf_errors_0.setPath("username");
            int[] _jspx_push_body_count_sf_errors_0 = new int[] { 0 };
            try {
              int _jspx_eval_sf_errors_0 = _jspx_th_sf_errors_0.doStartTag();
              if (_jspx_th_sf_errors_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                return;
              }
            } catch (Throwable _jspx_exception) {
              while (_jspx_push_body_count_sf_errors_0[0]-- > 0)
                out = _jspx_page_context.popBody();
              _jspx_th_sf_errors_0.doCatch(_jspx_exception);
            } finally {
              _jspx_th_sf_errors_0.doFinally();
              _jspx_tagPool_sf_errors_path_cssClass_nobody.reuse(_jspx_th_sf_errors_0);
            }
            out.write("\n");
            out.write("\t\t\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
            out.write("\t\t\t\t\t\t\t<label class=\"control-label col-md-3\">显示名称(可以是中文) <span class=\"required\">\n");
            out.write("\t\t\t\t\t\t\t* </span>\n");
            out.write("\t\t\t\t\t\t\t</label>\n");
            out.write("\t\t\t\t\t\t\t<div class=\"col-md-4\">\n");
            out.write("\t\t\t\t\t\t\t\t<div class=\"input-icon right\">\n");
            out.write("\t\t\t\t\t\t\t\t\t");
            if (_jspx_meth_sf_input_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_sf_form_0, _jspx_page_context, _jspx_push_body_count_sf_form_0))
              return;
            out.write("\n");
            out.write("\t\t\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
            out.write("\t\t\t\t\t\t\t<label class=\"control-label col-md-3\">用户密码 <span class=\"required\">\n");
            out.write("\t\t\t\t\t\t\t* </span>\n");
            out.write("\t\t\t\t\t\t\t</label>\n");
            out.write("\t\t\t\t\t\t\t<div class=\"col-md-4\">\n");
            out.write("\t\t\t\t\t\t\t\t<div class=\"input-icon right\">\n");
            out.write("\t\t\t\t\t\t\t\t\t");
            if (_jspx_meth_sf_password_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_sf_form_0, _jspx_page_context, _jspx_push_body_count_sf_form_0))
              return;
            out.write("\n");
            out.write("\t\t\t\t\t\t\t\t\t");
            //  sf:errors
            org.springframework.web.servlet.tags.form.ErrorsTag _jspx_th_sf_errors_1 = (org.springframework.web.servlet.tags.form.ErrorsTag) _jspx_tagPool_sf_errors_path_cssClass_nobody.get(org.springframework.web.servlet.tags.form.ErrorsTag.class);
            _jspx_th_sf_errors_1.setPageContext(_jspx_page_context);
            _jspx_th_sf_errors_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_sf_form_0);
            _jspx_th_sf_errors_1.setCssClass("errorContainer");
            _jspx_th_sf_errors_1.setPath("password");
            int[] _jspx_push_body_count_sf_errors_1 = new int[] { 0 };
            try {
              int _jspx_eval_sf_errors_1 = _jspx_th_sf_errors_1.doStartTag();
              if (_jspx_th_sf_errors_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                return;
              }
            } catch (Throwable _jspx_exception) {
              while (_jspx_push_body_count_sf_errors_1[0]-- > 0)
                out = _jspx_page_context.popBody();
              _jspx_th_sf_errors_1.doCatch(_jspx_exception);
            } finally {
              _jspx_th_sf_errors_1.doFinally();
              _jspx_tagPool_sf_errors_path_cssClass_nobody.reuse(_jspx_th_sf_errors_1);
            }
            out.write("\n");
            out.write("\t\t\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
            out.write("\t\t\t\t\t\t\t<label class=\"control-label col-md-3\">确认密码 <span class=\"required\">\n");
            out.write("\t\t\t\t\t\t\t* </span>\n");
            out.write("\t\t\t\t\t\t\t</label>\n");
            out.write("\t\t\t\t\t\t\t<div class=\"col-md-4\">\n");
            out.write("\t\t\t\t\t\t\t\t<div class=\"input-icon right\">\n");
            out.write("\t\t\t\t\t\t\t\t\t<input type=\"password\" id=\"confirmPwd\" name=\"confirmPwd\" class=\"form-control\" />\n");
            out.write("\t\t\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
            out.write("\t\t\t\t\t\t\t<label class=\"control-label col-md-3\">联系电话 <span class=\"required\">\n");
            out.write("\t\t\t\t\t\t\t* </span>\n");
            out.write("\t\t\t\t\t\t\t</label>\n");
            out.write("\t\t\t\t\t\t\t<div class=\"col-md-4\">\n");
            out.write("\t\t\t\t\t\t\t\t<div class=\"input-icon right\">\n");
            out.write("\t\t\t\t\t\t\t\t\t");
            if (_jspx_meth_sf_input_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_sf_form_0, _jspx_page_context, _jspx_push_body_count_sf_form_0))
              return;
            out.write("\n");
            out.write("\t\t\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
            out.write("\t\t\t\t\t\t\t<label class=\"control-label col-md-3\">电子邮件 <span class=\"required\">\n");
            out.write("\t\t\t\t\t\t\t* </span>\n");
            out.write("\t\t\t\t\t\t\t</label>\n");
            out.write("\t\t\t\t\t\t\t<div class=\"col-md-4\">\n");
            out.write("\t\t\t\t\t\t\t\t<div class=\"input-icon right\">\n");
            out.write("\t\t\t\t\t\t\t\t\t");
            if (_jspx_meth_sf_input_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_sf_form_0, _jspx_page_context, _jspx_push_body_count_sf_form_0))
              return;
            out.write("\n");
            out.write("\t\t\t\t\t\t\t\t\t");
            //  sf:errors
            org.springframework.web.servlet.tags.form.ErrorsTag _jspx_th_sf_errors_2 = (org.springframework.web.servlet.tags.form.ErrorsTag) _jspx_tagPool_sf_errors_path_nobody.get(org.springframework.web.servlet.tags.form.ErrorsTag.class);
            _jspx_th_sf_errors_2.setPageContext(_jspx_page_context);
            _jspx_th_sf_errors_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_sf_form_0);
            _jspx_th_sf_errors_2.setPath("email");
            int[] _jspx_push_body_count_sf_errors_2 = new int[] { 0 };
            try {
              int _jspx_eval_sf_errors_2 = _jspx_th_sf_errors_2.doStartTag();
              if (_jspx_th_sf_errors_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                return;
              }
            } catch (Throwable _jspx_exception) {
              while (_jspx_push_body_count_sf_errors_2[0]-- > 0)
                out = _jspx_page_context.popBody();
              _jspx_th_sf_errors_2.doCatch(_jspx_exception);
            } finally {
              _jspx_th_sf_errors_2.doFinally();
              _jspx_tagPool_sf_errors_path_nobody.reuse(_jspx_th_sf_errors_2);
            }
            out.write("\n");
            out.write("\t\t\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
            out.write("\t\t\t\t\t\t\t<label class=\"control-label col-md-3\">状态 <span class=\"required\">\n");
            out.write("\t\t\t\t\t\t\t* </span>\n");
            out.write("\t\t\t\t\t\t\t</label>\n");
            out.write("\t\t\t\t\t\t\t<div class=\"col-md-4\">\n");
            out.write("\t\t\t\t\t\t\t\t<div class=\"input-icon right\">\n");
            out.write("\t\t\t\t\t\t\t\t\t");
            //  sf:select
            org.springframework.web.servlet.tags.form.SelectTag _jspx_th_sf_select_0 = (org.springframework.web.servlet.tags.form.SelectTag) _jspx_tagPool_sf_select_path_class.get(org.springframework.web.servlet.tags.form.SelectTag.class);
            _jspx_th_sf_select_0.setPageContext(_jspx_page_context);
            _jspx_th_sf_select_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_sf_form_0);
            _jspx_th_sf_select_0.setPath("status");
            _jspx_th_sf_select_0.setDynamicAttribute(null, "class", new String("bs-select form-control"));
            int[] _jspx_push_body_count_sf_select_0 = new int[] { 0 };
            try {
              int _jspx_eval_sf_select_0 = _jspx_th_sf_select_0.doStartTag();
              if (_jspx_eval_sf_select_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                do {
                  out.write("\n");
                  out.write("\t\t\t\t\t\t\t\t\t\t");
                  //  sf:option
                  org.springframework.web.servlet.tags.form.OptionTag _jspx_th_sf_option_0 = (org.springframework.web.servlet.tags.form.OptionTag) _jspx_tagPool_sf_option_value.get(org.springframework.web.servlet.tags.form.OptionTag.class);
                  _jspx_th_sf_option_0.setPageContext(_jspx_page_context);
                  _jspx_th_sf_option_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_sf_select_0);
                  _jspx_th_sf_option_0.setValue(new String("0"));
                  int[] _jspx_push_body_count_sf_option_0 = new int[] { 0 };
                  try {
                    int _jspx_eval_sf_option_0 = _jspx_th_sf_option_0.doStartTag();
                    if (_jspx_eval_sf_option_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_sf_option_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_push_body_count_sf_option_0[0]++;
                        _jspx_th_sf_option_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_sf_option_0.doInitBody();
                      }
                      do {
                        out.write('停');
                        out.write('用');
                        int evalDoAfterBody = _jspx_th_sf_option_0.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                      if (_jspx_eval_sf_option_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                        out = _jspx_page_context.popBody();
                        _jspx_push_body_count_sf_option_0[0]--;
                    }
                    if (_jspx_th_sf_option_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      return;
                    }
                  } catch (Throwable _jspx_exception) {
                    while (_jspx_push_body_count_sf_option_0[0]-- > 0)
                      out = _jspx_page_context.popBody();
                    _jspx_th_sf_option_0.doCatch(_jspx_exception);
                  } finally {
                    _jspx_th_sf_option_0.doFinally();
                    _jspx_tagPool_sf_option_value.reuse(_jspx_th_sf_option_0);
                  }
                  out.write("\n");
                  out.write("\t\t\t\t\t\t\t\t\t\t");
                  //  sf:option
                  org.springframework.web.servlet.tags.form.OptionTag _jspx_th_sf_option_1 = (org.springframework.web.servlet.tags.form.OptionTag) _jspx_tagPool_sf_option_value.get(org.springframework.web.servlet.tags.form.OptionTag.class);
                  _jspx_th_sf_option_1.setPageContext(_jspx_page_context);
                  _jspx_th_sf_option_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_sf_select_0);
                  _jspx_th_sf_option_1.setValue(new String("1"));
                  int[] _jspx_push_body_count_sf_option_1 = new int[] { 0 };
                  try {
                    int _jspx_eval_sf_option_1 = _jspx_th_sf_option_1.doStartTag();
                    if (_jspx_eval_sf_option_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_sf_option_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_push_body_count_sf_option_1[0]++;
                        _jspx_th_sf_option_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_sf_option_1.doInitBody();
                      }
                      do {
                        out.write('启');
                        out.write('用');
                        int evalDoAfterBody = _jspx_th_sf_option_1.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                      if (_jspx_eval_sf_option_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                        out = _jspx_page_context.popBody();
                        _jspx_push_body_count_sf_option_1[0]--;
                    }
                    if (_jspx_th_sf_option_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      return;
                    }
                  } catch (Throwable _jspx_exception) {
                    while (_jspx_push_body_count_sf_option_1[0]-- > 0)
                      out = _jspx_page_context.popBody();
                    _jspx_th_sf_option_1.doCatch(_jspx_exception);
                  } finally {
                    _jspx_th_sf_option_1.doFinally();
                    _jspx_tagPool_sf_option_value.reuse(_jspx_th_sf_option_1);
                  }
                  out.write("\n");
                  out.write("\t\t\t\t\t\t\t\t\t");
                  int evalDoAfterBody = _jspx_th_sf_select_0.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
              }
              if (_jspx_th_sf_select_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                return;
              }
            } catch (Throwable _jspx_exception) {
              while (_jspx_push_body_count_sf_select_0[0]-- > 0)
                out = _jspx_page_context.popBody();
              _jspx_th_sf_select_0.doCatch(_jspx_exception);
            } finally {
              _jspx_th_sf_select_0.doFinally();
              _jspx_tagPool_sf_select_path_class.reuse(_jspx_th_sf_select_0);
            }
            out.write("\n");
            out.write("\t\t\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
            out.write("\t\t\t\t\t\t\t<label class=\"control-label col-md-3\">角色<span class=\"required\">\n");
            out.write("\t\t\t\t\t\t\t* </span>\n");
            out.write("\t\t\t\t\t\t\t</label>\n");
            out.write("\t\t\t\t\t\t\t<div class=\"col-md-4\">\n");
            out.write("\t\t\t\t\t\t\t\t<div class=\"input-icon right\">\n");
            out.write("\t\t\t\t\t\t\t\t\t");
            if (_jspx_meth_sf_checkboxes_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_sf_form_0, _jspx_page_context, _jspx_push_body_count_sf_form_0))
              return;
            out.write("\n");
            out.write("\t\t\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
            out.write("\t\t\t\t\t\t\t<label class=\"control-label col-md-3\">用户组<span class=\"required\">\n");
            out.write("\t\t\t\t\t\t\t* </span>\n");
            out.write("\t\t\t\t\t\t\t</label>\n");
            out.write("\t\t\t\t\t\t\t<div class=\"col-md-4\">\n");
            out.write("\t\t\t\t\t\t\t\t<div class=\"input-icon right\">\n");
            out.write("\t\t\t\t\t\t\t\t\t");
            if (_jspx_meth_sf_checkboxes_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_sf_form_0, _jspx_page_context, _jspx_push_body_count_sf_form_0))
              return;
            out.write("\n");
            out.write("\t\t\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t<div class=\"form-actions\">\n");
            out.write("\t\t\t\t\t\t<div class=\"row\">\n");
            out.write("\t\t\t\t\t\t\t<div class=\"col-md-offset-3 col-md-9\">\n");
            out.write("\t\t\t\t\t\t\t\t<button type=\"submit\" class=\"btn green\">提交</button>\n");
            out.write("\t\t\t\t\t\t\t\t<a type=\"button\" class=\"btn default ajaxify\" href=\"admin/user/users\">取消</a>\n");
            out.write("\t\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t\t</div>\n");
            out.write("\t\t\t\t");
            int evalDoAfterBody = _jspx_th_sf_form_0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_sf_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return;
        }
      } catch (Throwable _jspx_exception) {
        while (_jspx_push_body_count_sf_form_0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_sf_form_0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_sf_form_0.doFinally();
        _jspx_tagPool_sf_form_modelAttribute_method_id_class_action.reuse(_jspx_th_sf_form_0);
      }
      out.write("\n");
      out.write("\t\t\t\t<!-- END FORM-->\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<!-- END VALIDATION STATES-->\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
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

  private boolean _jspx_meth_sf_input_0(javax.servlet.jsp.tagext.JspTag _jspx_th_sf_form_0, PageContext _jspx_page_context, int[] _jspx_push_body_count_sf_form_0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sf:input
    org.springframework.web.servlet.tags.form.InputTag _jspx_th_sf_input_0 = (org.springframework.web.servlet.tags.form.InputTag) _jspx_tagPool_sf_input_path_class_nobody.get(org.springframework.web.servlet.tags.form.InputTag.class);
    _jspx_th_sf_input_0.setPageContext(_jspx_page_context);
    _jspx_th_sf_input_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_sf_form_0);
    _jspx_th_sf_input_0.setPath("username");
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

  private boolean _jspx_meth_sf_input_1(javax.servlet.jsp.tagext.JspTag _jspx_th_sf_form_0, PageContext _jspx_page_context, int[] _jspx_push_body_count_sf_form_0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sf:input
    org.springframework.web.servlet.tags.form.InputTag _jspx_th_sf_input_1 = (org.springframework.web.servlet.tags.form.InputTag) _jspx_tagPool_sf_input_path_class_nobody.get(org.springframework.web.servlet.tags.form.InputTag.class);
    _jspx_th_sf_input_1.setPageContext(_jspx_page_context);
    _jspx_th_sf_input_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_sf_form_0);
    _jspx_th_sf_input_1.setPath("nickname");
    _jspx_th_sf_input_1.setDynamicAttribute(null, "class", new String("form-control"));
    int[] _jspx_push_body_count_sf_input_1 = new int[] { 0 };
    try {
      int _jspx_eval_sf_input_1 = _jspx_th_sf_input_1.doStartTag();
      if (_jspx_th_sf_input_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_sf_input_1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_sf_input_1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_sf_input_1.doFinally();
      _jspx_tagPool_sf_input_path_class_nobody.reuse(_jspx_th_sf_input_1);
    }
    return false;
  }

  private boolean _jspx_meth_sf_password_0(javax.servlet.jsp.tagext.JspTag _jspx_th_sf_form_0, PageContext _jspx_page_context, int[] _jspx_push_body_count_sf_form_0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sf:password
    org.springframework.web.servlet.tags.form.PasswordInputTag _jspx_th_sf_password_0 = (org.springframework.web.servlet.tags.form.PasswordInputTag) _jspx_tagPool_sf_password_path_class_nobody.get(org.springframework.web.servlet.tags.form.PasswordInputTag.class);
    _jspx_th_sf_password_0.setPageContext(_jspx_page_context);
    _jspx_th_sf_password_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_sf_form_0);
    _jspx_th_sf_password_0.setPath("password");
    _jspx_th_sf_password_0.setDynamicAttribute(null, "class", new String("form-control"));
    int[] _jspx_push_body_count_sf_password_0 = new int[] { 0 };
    try {
      int _jspx_eval_sf_password_0 = _jspx_th_sf_password_0.doStartTag();
      if (_jspx_th_sf_password_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_sf_password_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_sf_password_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_sf_password_0.doFinally();
      _jspx_tagPool_sf_password_path_class_nobody.reuse(_jspx_th_sf_password_0);
    }
    return false;
  }

  private boolean _jspx_meth_sf_input_2(javax.servlet.jsp.tagext.JspTag _jspx_th_sf_form_0, PageContext _jspx_page_context, int[] _jspx_push_body_count_sf_form_0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sf:input
    org.springframework.web.servlet.tags.form.InputTag _jspx_th_sf_input_2 = (org.springframework.web.servlet.tags.form.InputTag) _jspx_tagPool_sf_input_path_class_nobody.get(org.springframework.web.servlet.tags.form.InputTag.class);
    _jspx_th_sf_input_2.setPageContext(_jspx_page_context);
    _jspx_th_sf_input_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_sf_form_0);
    _jspx_th_sf_input_2.setPath("phone");
    _jspx_th_sf_input_2.setDynamicAttribute(null, "class", new String("form-control"));
    int[] _jspx_push_body_count_sf_input_2 = new int[] { 0 };
    try {
      int _jspx_eval_sf_input_2 = _jspx_th_sf_input_2.doStartTag();
      if (_jspx_th_sf_input_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_sf_input_2[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_sf_input_2.doCatch(_jspx_exception);
    } finally {
      _jspx_th_sf_input_2.doFinally();
      _jspx_tagPool_sf_input_path_class_nobody.reuse(_jspx_th_sf_input_2);
    }
    return false;
  }

  private boolean _jspx_meth_sf_input_3(javax.servlet.jsp.tagext.JspTag _jspx_th_sf_form_0, PageContext _jspx_page_context, int[] _jspx_push_body_count_sf_form_0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sf:input
    org.springframework.web.servlet.tags.form.InputTag _jspx_th_sf_input_3 = (org.springframework.web.servlet.tags.form.InputTag) _jspx_tagPool_sf_input_path_class_nobody.get(org.springframework.web.servlet.tags.form.InputTag.class);
    _jspx_th_sf_input_3.setPageContext(_jspx_page_context);
    _jspx_th_sf_input_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_sf_form_0);
    _jspx_th_sf_input_3.setPath("email");
    _jspx_th_sf_input_3.setDynamicAttribute(null, "class", new String("form-control"));
    int[] _jspx_push_body_count_sf_input_3 = new int[] { 0 };
    try {
      int _jspx_eval_sf_input_3 = _jspx_th_sf_input_3.doStartTag();
      if (_jspx_th_sf_input_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_sf_input_3[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_sf_input_3.doCatch(_jspx_exception);
    } finally {
      _jspx_th_sf_input_3.doFinally();
      _jspx_tagPool_sf_input_path_class_nobody.reuse(_jspx_th_sf_input_3);
    }
    return false;
  }

  private boolean _jspx_meth_sf_checkboxes_0(javax.servlet.jsp.tagext.JspTag _jspx_th_sf_form_0, PageContext _jspx_page_context, int[] _jspx_push_body_count_sf_form_0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sf:checkboxes
    org.springframework.web.servlet.tags.form.CheckboxesTag _jspx_th_sf_checkboxes_0 = (org.springframework.web.servlet.tags.form.CheckboxesTag) _jspx_tagPool_sf_checkboxes_path_items_itemValue_itemLabel_nobody.get(org.springframework.web.servlet.tags.form.CheckboxesTag.class);
    _jspx_th_sf_checkboxes_0.setPageContext(_jspx_page_context);
    _jspx_th_sf_checkboxes_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_sf_form_0);
    _jspx_th_sf_checkboxes_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${roles}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_sf_checkboxes_0.setItemLabel("name");
    _jspx_th_sf_checkboxes_0.setItemValue("id");
    _jspx_th_sf_checkboxes_0.setPath("roleIds");
    int[] _jspx_push_body_count_sf_checkboxes_0 = new int[] { 0 };
    try {
      int _jspx_eval_sf_checkboxes_0 = _jspx_th_sf_checkboxes_0.doStartTag();
      if (_jspx_th_sf_checkboxes_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_sf_checkboxes_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_sf_checkboxes_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_sf_checkboxes_0.doFinally();
      _jspx_tagPool_sf_checkboxes_path_items_itemValue_itemLabel_nobody.reuse(_jspx_th_sf_checkboxes_0);
    }
    return false;
  }

  private boolean _jspx_meth_sf_checkboxes_1(javax.servlet.jsp.tagext.JspTag _jspx_th_sf_form_0, PageContext _jspx_page_context, int[] _jspx_push_body_count_sf_form_0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sf:checkboxes
    org.springframework.web.servlet.tags.form.CheckboxesTag _jspx_th_sf_checkboxes_1 = (org.springframework.web.servlet.tags.form.CheckboxesTag) _jspx_tagPool_sf_checkboxes_path_items_itemValue_itemLabel_nobody.get(org.springframework.web.servlet.tags.form.CheckboxesTag.class);
    _jspx_th_sf_checkboxes_1.setPageContext(_jspx_page_context);
    _jspx_th_sf_checkboxes_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_sf_form_0);
    _jspx_th_sf_checkboxes_1.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${groups }", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_sf_checkboxes_1.setPath("groupIds");
    _jspx_th_sf_checkboxes_1.setItemLabel("name");
    _jspx_th_sf_checkboxes_1.setItemValue("id");
    int[] _jspx_push_body_count_sf_checkboxes_1 = new int[] { 0 };
    try {
      int _jspx_eval_sf_checkboxes_1 = _jspx_th_sf_checkboxes_1.doStartTag();
      if (_jspx_th_sf_checkboxes_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_sf_checkboxes_1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_sf_checkboxes_1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_sf_checkboxes_1.doFinally();
      _jspx_tagPool_sf_checkboxes_path_items_itemValue_itemLabel_nobody.reuse(_jspx_th_sf_checkboxes_1);
    }
    return false;
  }
}
