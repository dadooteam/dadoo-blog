<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*,org.apache.commons.lang3.tuple.*,im.dadoo.blog.biz.dto.*,im.dadoo.blog.cons.*" %>

<%
  ArticleDTO articleDTO = (ArticleDTO)request.getAttribute("articleDTO");
  List<Tag> tags = (List<Tag>)request.getAttribute("tags");
%>
<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <meta name="description" content="dadoo blog">
  <jsp:include page="../partial/head.jsp" flush="true">
    <jsp:param name="title" value="修改文章" />
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
        <form id="update-article-form" enctype="multipart/form-data" action="/admin/article/<%= articleDTO.getArticle().getId() %>/update" method="post">
          <div class="form-group">
            <label for="title">标题</label>
            <input name="title" type="text" class="form-control" value="<%= articleDTO.getArticle().getTitle() %>">
          </div>
          <div class="form-group">
            <label for="tagIds">标签</label>
            <select id="tagIds" name="tagIds" multiple class="form-control" size="8">
              <% if (tags != null) { %>
                <% for (Tag tag : tags) { %>
                  <% Boolean flag = false; %>
                  <% for (Tag t : articleDTO.getTags()) { %>
                    <% if (t.getId() == tag.getId()) { %>
                      <option selected="selected" value="<%= tag.getId() %>"><%= tag.getName() %></option>
                      <% flag = true; %>
                    <% } %>
                  <% } %>
                  <% if (!flag) { %>
                    <option value="<%= tag.getId() %>"><%= tag.getName() %></option>
                  <% } %>
                <% } %>
              <% } %>
            </select>
          </div>
          <div class="form-group">
            <label>
              <% if(articleDTO.getArticle().getTop() == DadooConstant.TOP_Y) { %>
                <input type="checkbox" name="top" checked value="2">
              <% } else { %>
                <input type="checkbox" name="top" value="2">
              <% } %>
              置顶
            </label>
            <label>
              <% if(articleDTO.getArticle().getHidden() == DadooConstant.HIDDEN_Y) { %>
                <input type="checkbox" name="hidden" checked value="2">
              <% } else { %>
                <input type="checkbox" name="hidden" value="2">
              <% } %>
              隐藏
            </label>
          </div>
          <div class="form-group">
            <label for="html">内容(markdown)</label>
            <textarea id="html" name="html" class="form-control" data-provide="markdown" rows="15"><%= articleDTO.getArticle().getHtml() %></textarea>
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
    $("#admin-article-li").addClass("active");
    $("#html").html(markdown.toHTML($("html").text()));
  </script>
</body>
