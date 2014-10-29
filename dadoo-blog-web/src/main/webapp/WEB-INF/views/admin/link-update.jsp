<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*" %>

<%
  Link link = (Link)request.getAttribute("link");
%>

<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <jsp:include page="../partial/head.jsp" flush="true">
    <jsp:param name="title" value="修改链接" />
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
        <form id="update-link-form" action="/admin/link/<%= link.getId() %>/update" method="post">
          <div class="form-group">
            <label for="name">名称</label>
            <input id="name" name="name" type="text" class="form-control" value="<%= link.getName() %>">
          </div>
          <div class="form-group">
            <label for="url">地址</label>
            <input id="url" name="url" type="text" class="form-control" value="<%= link.getUrl() %>">
          </div>
          <div class="form-group">
            <label for="description">描述</label>
            <input id="description" name="description" type="text" class="form-control" value="<%= link.getDescription() %>">
          </div>
          <div class="form-group">
            <button type="submit" class="btn btn-default">保存</button>
          </div>
        </form>
      </div>
    </div>
  </div>
  <jsp:include page="../partial/footer.jsp" flush="true" />
  <script>
    $("#admin-link-li").addClass("active");
  </script>
</body>
