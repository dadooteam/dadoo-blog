<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*,org.apache.commons.lang3.tuple.*" %>

<%
  List<Pair<Article, List<Tag>>> pairs = (List<Pair<Article, List<Tag>>>)request.getAttribute("article-tags-pairs");
%>

<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <meta name="description" content="dadoo blog">
  <jsp:include page="../partial/head.jsp" flush="true">
    <jsp:param name="title" value="文章管理" />
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
        <a class="btn btn-primary pull-right" href="/admin/article/add">新文章</a>
        <table class="table table-hover">
          <thead>
            <tr>
              <th>名称</th>
              <th>创建时间</th>
              <th>标签</th>
              <th>浏览次数</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <% if (pairs != null) { %>
              <% for (Pair<Article, List<Tag>> pair : pairs) { %>
              <tr>
                <td><a href="/article/<%= pair.getLeft().getId() %>"><%= pair.getLeft().getTitle() %></a></td>
                <td><%= DateFormatUtils.format(pair.getLeft().getPublishDatetime(), "yyyy-MM-dd HH:mm", TimeZone.getTimeZone("GMT+8")) %></td>
                <td>
                  <% if (pair.getRight() != null && !pair.getRight().isEmpty()) { %>
                    <% for (Tag tag : pair.getRight()) { %>
                      &nbsp;<a href="/tag/<%= tag.getId() %>"><%= tag.getName() %></a>
                    <% } %>
                  <% } %>
                </td>
                <td><%= pair.getLeft().getClick() %></td>
                <td>
                  <a href="/admin/article/<%= pair.getLeft().getId() %>/update">修改</a>
                  <a href="/admin/article/<%= pair.getLeft().getId() %>/delete">删除</a>
                </td>
              </tr>
              <% } %>
            <% } %>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  <jsp:include page="../partial/footer.jsp" flush="true" />
  <script>
    $("#admin-article-li").addClass("active");
  </script>
</body>
