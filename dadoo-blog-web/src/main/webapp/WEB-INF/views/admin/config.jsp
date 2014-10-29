<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*" %>

<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <jsp:include page="../partial/head.jsp" flush="true">
    <jsp:param name="title" value="配置管理" />
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
        <form id="config-form" action="/admin/config" method="post">
          <div class="form-group">
            <label for="title">标题</label>
            <input name="title" type="text" class="form-control" value="<%= request.getAttribute("title") %>">
          </div>
          <div class="form-group">
            <label for="most-visit-article-size">访问最多文章显示条数</label>
            <input name="most-visit-article-size" type="text" class="form-control" value="<%= request.getAttribute("most-visit-article-size") %>">
          </div>
          <div class="form-group">
            <label for="article-pagesize">每页显示文章数</label>
            <input name="article-pagesize" type="text" class="form-control" value="<%= request.getAttribute("article-pagesize") %>">
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
    $("#admin-config-li").addClass("active");
  </script>
</body>
