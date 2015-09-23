package org.apache.jsp.WEB_002dINF.jsp.group;

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
      out.write("$(function(){\r\n");
      out.write("\t$(\"a.delete\").confirmOperator();\r\n");
      out.write("\tvar success='");
      out.print(request.getAttribute("success"));
      out.write("';\r\n");
      out.write("\tvar error='");
      out.print(request.getAttribute("error"));
      out.write("';\r\n");
      out.write("\tif (success != 'null') {\r\n");
      out.write("\t\ttoastr.success(success);\r\n");
      out.write("\t}\r\n");
      out.write("\tif (error != 'null') {\r\n");
      out.write("\t\ttoastr.error(error);\r\n");
      out.write("\t}\r\n");
      out.write("\t/**\r\n");
      out.write("\t * 显示树\r\n");
      out.write("\t */\r\n");
      out.write("\t$(\"#showTree\").click(function(){\r\n");
      out.write("\t\tvar selectedIds = $('input[title=check]:checked'); //获取被选的checkbox\r\n");
      out.write("\t\tif(selectedIds.length != 1){\r\n");
      out.write("\t\t\ttoastr.error(\"请选中其中一行!\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar t = $(\"#tree\").mytree({\r\n");
      out.write("\t\t\turl:$(\"#treePath\").val()+selectedIds.val(),\r\n");
      out.write("\t\t\tmine:{listChild:0,expandAll:true}\r\n");
      out.write("\t\t});\t\t\t\r\n");
      out.write("\t\t$(\"#addmodel\").modal();\r\n");
      out.write("\t\t\r\n");
      out.write("\t});\r\n");
      out.write("\t/**\r\n");
      out.write("\t * 设置树\r\n");
      out.write("\t */\r\n");
      out.write("\t$(\"#setTree\").click(function(){\r\n");
      out.write("\t\tvar selectedIds = $('input[title=check]:checked'); //获取被选的checkbox\r\n");
      out.write("\t\tif(selectedIds.length != 1){\r\n");
      out.write("\t\t\ttoastr.error(\"请选中其中一行!\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar _cs = new Array();\r\n");
      out.write("\t\tvar t = $(\"#tree\").mytree({\r\n");
      out.write("\t\t\turl:$(\"#setTreePath\").val(),\r\n");
      out.write("\t\t\tmine:{listChild:0},\r\n");
      out.write("\t\t\tcallback:{\r\n");
      out.write("\t\t\t\tonAsyncSuccess:initTree,\r\n");
      out.write("\t\t\t\tbeforeCheck:function(treeId,treeNode) { //点击之前\r\n");
      out.write("\t\t\t\t\tif(!treeNode.checked) { //添加当前被选中节点和父节点（当节点被勾选前，treeNode.checked==false）\r\n");
      out.write("\t\t\t\t\t\t//ps中的节点应该进行添加操作\r\n");
      out.write("\t\t\t\t\t\tvar ps = t.getCheckParentNodes(treeNode,false);//获取被选中的节点的父节点\r\n");
      out.write("\t\t\t\t\t\tps.push(treeNode);//加入被选中的节点\r\n");
      out.write("\t\t\t\t\t\t//ps就是要添加的元素\r\n");
      out.write("\t\t\t\t\t\taddGroupChannel(ps);\r\n");
      out.write("\t\t\t\t\t} else {\t//删除当前被选中节点和子节点\r\n");
      out.write("\t\t\t\t\t\tvar cs = new Array();\r\n");
      out.write("\t\t\t\t\t\tt.getCheckChildNodes(treeNode,true,cs);//获取要删除的节点的子节点\r\n");
      out.write("\t\t\t\t\t\tcs.push(treeNode);//加入被删除的节点\r\n");
      out.write("\t\t\t\t\t\t//cs就是要删除的元素\r\n");
      out.write("\t\t\t\t\t\tdeleteGroupChannel(cs);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\tonCheck:function(event,treeId,treeNode) {//点击之后\r\n");
      out.write("\t\t\t\t\t//如果取消之后父节点依然存在就不操作，父节点不存在就进行删除\r\n");
      out.write("\t\t\t\t\tif(!treeNode.checked) {\r\n");
      out.write("\t\t\t\t\t\tvar ps = t.getCheckParentNodes(treeNode,false);//删除当前被选中节点和父节点（当节点被取消勾选后，treeNode.checked==false）\r\n");
      out.write("\t\t\t\t\t\t//ps就表示要删除的元素\r\n");
      out.write("\t\t\t\t\t\tdeleteGroupChannel(ps);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tcheck:{\r\n");
      out.write("\t\t\t\tenable:true,\r\n");
      out.write("\t\t\t\tchkboxType: { \"Y\": \"p\", \"N\": \"ps\" }\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\"#addmodel\").modal(); //弹框\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//全局异常\r\n");
      out.write("\t\tfunction handler_(msg,exc) {\r\n");
      out.write("\t\t\talert(msg);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tdwr.engine.setErrorHandler(handler_);\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction addGroupChannel(cs) {\r\n");
      out.write("\t\t\tvar gid = $('input[title=check]:checked').val();\r\n");
      out.write("\t\t\tfor(var i=0;i<cs.length;i++) {\r\n");
      out.write("\t\t\t\tvar c = cs[i];\r\n");
      out.write("\t\t\t\tif(c.id>0) {\r\n");
      out.write("\t\t\t\t\tdwrService.addGroupChannel(gid,c.id);\r\n");
      out.write("\t\t\t\t\ttoastr.success(\"添加成功!\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction deleteGroupChannel(cs) {\r\n");
      out.write("\t\t\tvar gid = $('input[title=check]:checked').val();\r\n");
      out.write("\t\t\tfor(var i=0;i<cs.length;i++) {\r\n");
      out.write("\t\t\t\tvar c = cs[i];\r\n");
      out.write("\t\t\t\tif(c.id>0) {\r\n");
      out.write("\t\t\t\t\tdwrService.deleteGroupChannel(gid,c.id);\r\n");
      out.write("\t\t\t\t\ttoastr.success(\"删除成功!\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//初始化树\r\n");
      out.write("\t\tfunction initTree() {\r\n");
      out.write("\t\t\tvar cids = new Array();　//创建一个数组\r\n");
      out.write("\t\t\t//根据组id获取该组的所有管理栏目的id\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\t\turl : $(\"#getChannelIdPath\").val()+selectedIds.val(),\r\n");
      out.write("\t\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\t\tt.expandAll(true);//打开树节点\r\n");
      out.write("\t\t\t\t\tfor(var i=0;i<data.length;i++) {\r\n");
      out.write("\t\t\t\t\t\tvar cid = data[i];\r\n");
      out.write("\t\t\t\t\t\tvar n = t.getNodeByParam(\"id\",cid,null);\r\n");
      out.write("\t\t\t\t\t\tt.checkNode(n,true,true);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("\t\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<input type=\"hidden\" id=\"treePath\" value=\"");
      out.print(request.getContextPath());
      out.write("/admin/group/groupTree/\"/>\r\n");
      out.write("<input type=\"hidden\" id=\"getChannelIdPath\" value=\"");
      out.print(request.getContextPath());
      out.write("/admin/group/groupChannelIds/\"/>\r\n");
      out.write("<input type=\"hidden\" id=\"setTreePath\" value=\"");
      out.print(request.getContextPath());
      out.write("/admin/channel/treeAll\"/>\r\n");
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
      out.write("\t\t\t\t\t用户列表\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"tools\">\r\n");
      out.write("\t\t\t\t\t<a href=\"javascript:;\" class=\"collapse\"> </a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"portlet-body\">\r\n");
      out.write("\t\t\t\t<div class=\"table-toolbar\">\r\n");
      out.write("\t\t\t\t\t<div class=\"btn-group\">\r\n");
      out.write("\t\t\t\t\t\t<a class=\"btn red ajaxify\" href=\"admin/group/addUI\">新增用户组</a>\r\n");
      out.write("\t\t\t\t\t\t<a href=\"javascript:void(0);\" id=\"showTree\" class=\"btn green\">查询管理栏目</a>\r\n");
      out.write("\t\t\t\t\t\t<a href=\"javascript:void(0);\" id=\"setTree\" class=\"btn yellow\">设置管理栏目</a>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<table class=\"table table-striped table-hover table-bordered\" id=\"sample_1\">\r\n");
      out.write("\t\t\t\t\t<thead>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<th class=\"table-checkbox\"  style=\"text-align:center\"><input type=\"checkbox\"\r\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"group-checkable\" data-set=\"#sample_1 .checkboxes\" />\r\n");
      out.write("\t\t\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th>用户组名称</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th>用户组描述</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th>用户组操作</th>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</thead>\r\n");
      out.write("\t\t\t\t\t<tbody>\r\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_c_forEach_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t\t\t<tfoot>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"6\" style=\"text-align:right;margin-right:10px;\">\r\n");
      out.write("\t\t\t\t\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/jsp/pager.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("totalRecord", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${datas.total }", java.lang.String.class, (PageContext)_jspx_page_context, null), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("url", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("admin/group/groups", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</tfoot>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- END EXAMPLE TABLE PORTLET-->\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- END PAGE CONTENT -->\r\n");
      out.write("\r\n");
      out.write("<!-- 弹框  -->\r\n");
      out.write("<div id=\"addmodel\" class=\"modal fade\" role=\"dialog\" aria-labelledby=\"myModalLabel10\" aria-hidden=\"true\">\r\n");
      out.write("\t<div class=\"modal-dialog\">\r\n");
      out.write("\t\t<div class=\"modal-content\">\r\n");
      out.write("\t\t\t<div class=\"modal-header\">\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\"></button>\r\n");
      out.write("\t\t\t\t<h4 class=\"modal-title\">管理栏目</h4>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"modal-body\">\r\n");
      out.write("\t\t\t\t<div id=\"tree\" class=\"ztree\"></div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t\t\t<a class=\"btn default\" data-dismiss=\"modal\" aria-hidden=\"true\">取消</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>");
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
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${datas.datas }", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_0.setVar("group");
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t<tr>\r\n");
          out.write("\t\t\t\t\t\t\t\t<td style=\"text-align:center\">\r\n");
          out.write("\t\t\t\t\t\t\t\t\t<input type=\"checkbox\" class=\"checkboxes\" title=\"check\" value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.id }", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" />\r\n");
          out.write("\t\t\t\t\t\t\t\t</td>\r\n");
          out.write("\t\t\t\t\t\t\t\t<td>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t<a href=\"admin/group/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.id }", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" class=\"ajaxify\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.name }", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</a>\r\n");
          out.write("\t\t\t\t\t\t\t\t</td>\r\n");
          out.write("\t\t\t\t\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.descr }", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("&nbsp;</td>\r\n");
          out.write("\t\t\t\t\t\t\t\t<td width=\"400px;\">\r\n");
          out.write("\t\t\t\t\t\t\t\t\t<a href=\"admin/group/delete/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.id }", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" title=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.id }", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" class=\"btn btn-sm red delete ajaxify\">删除</a>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t<a href=\"admin/group/updateUI/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.id }", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" class=\"btn btn-sm green ajaxify\">更新</a>\t\t\t\t\t\t\t\t\r\n");
          out.write("\t\t\t\t\t\t\t\t\t<a href=\"admin/group/clearUsers/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.id }", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" class=\"btn btn-sm blue delete ajaxify\">清空用户</a>\t\t\t\t\t\t\t\t\r\n");
          out.write("\t\t\t\t\t\t\t\t&nbsp;\r\n");
          out.write("\t\t\t\t\t\t\t\t</td>\r\n");
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
