<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*,org.apache.commons.lang3.tuple.*,im.dadoo.blog.biz.dto.*" %>

<%
  List<ArticleDTO> articleDTOs = (List<ArticleDTO>)request.getAttribute("articleDTOs");
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
            <% if (articleDTOs != null) { %>
              <% for (ArticleDTO articleDTO : articleDTOs) { %>
              <tr>
                <td><a href="/article/<%= articleDTO.getArticle().getId() %>"><%= articleDTO.getArticle().getTitle() %></a></td>
                <td><%= DateFormatUtils.format(articleDTO.getArticle().getGmtCreate(), "yyyy-MM-dd HH:mm", TimeZone.getTimeZone("GMT+8")) %></td>
                <td>
                  <% if (articleDTO.getTags() != null && !articleDTO.getTags().isEmpty()) { %>
                    <% for (Tag tag : articleDTO.getTags()) { %>
                      &nbsp;<a href="/tag/<%= tag.getId() %>"><%= tag.getName() %></a>
                    <% } %>
                  <% } %>
                </td>
                <td><%= articleDTO.getArticle().getClick() %></td>
                <td>
                  <a href="/admin/article/<%= articleDTO.getArticle().getId() %>/update">修改</a>
                  <a href="/admin/article/<%= articleDTO.getArticle().getId() %>/delete">删除</a>
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
