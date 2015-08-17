<%@page import="im.dadoo.blog.cons.DadooConstant"%>
<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*,org.apache.commons.lang3.tuple.*,im.dadoo.blog.biz.dto.*" %>

<%
  List<QiniuFile> files = (List<QiniuFile>) request.getAttribute("files");
%>

<!DOCTYPE html>
<html lang="zh_cn">
  <head>
    <meta name="description" content="dadoo blog">
    <jsp:include page="../partial/head.jsp" flush="true">
      <jsp:param name="title" value="Media管理" />
    </jsp:include>
  </head>
  <body>
    <jsp:include page="../partial/header.jsp" flush="true" />
    <div class="container">
      <div class="row">
        <div class="col-md-3">
          <jsp:include page="partial/leftsidebar.jsp" flush="true" />
        </div>
        <div class="col-md-9">
          <a class="btn btn-primary pull-right" href="/admin/media/add">新Media</a>
          <div class="col-md-12">
            <h1 class="page-header">Media Gallery</h1>
          </div>
          <% for (QiniuFile file : files) { %>
            <div class="col-lg-3 col-md-4 col-xs-6 thumb">
              <a class="thumbnail" href="<%= file.getUrl() %>">
                <img class="img-responsive" src="<%= file.getUrl() %>" style="width:180px;height:135px">
              </a>
              <a href="/admin/media/<%= file.getKey() %>/delete">删除</a>
            </div>
          <% } %>
        </div>
      </div>
    </div>
    <jsp:include page="../partial/footer.jsp" flush="true" />
    <script>
      $("#admin-media-li").addClass("active");
    </script>
  </body>
