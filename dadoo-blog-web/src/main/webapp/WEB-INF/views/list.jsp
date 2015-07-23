<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*,org.apache.commons.lang3.tuple.*,im.dadoo.blog.biz.dto.*" %>

<%
  List<ArticleDTO> articleDTOs = (List<ArticleDTO>)request.getAttribute("articleDTOs");
  Tag currentTag = (Tag)request.getAttribute("currentTag");
%>

<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <meta name="description" content="blog">
  <% if (currentTag != null) { %>
    <jsp:include page="partial/head.jsp" flush="true">
      <jsp:param name="title" value="<%= currentTag.getName() %>" />
    </jsp:include>
  <% } else { %>
    <jsp:include page="partial/head.jsp" flush="true">
      <jsp:param name="title" value="主页" />
    </jsp:include>
  <% } %>
  <jsp:include page="partial/head.jsp" flush="true" />
</head>
<body>
  <jsp:include page="partial/header.jsp" flush="true" />
  <div class="container">
    <div class="row">
      <div class="col-md-9">
        <% if (currentTag != null) { %>
          <h4><span class="label label-success"><%= currentTag.getName() %></span></h4>
        <% } %>
        <% if (articleDTOs != null) { %>
          <% for (ArticleDTO articleDTO : articleDTOs) { %>
          <div id="article-<%= articleDTO.getArticle().getId() %>" class="panel panel-default">
            <div class="panel-heading">
              <h1 class="panel-title"><a href="/article/<%= articleDTO.getArticle().getId() %>"><%= articleDTO.getArticle().getTitle() %></a></h1>
              <h6 class="panel-meta">
                <span class="glyphicon glyphicon-calendar"></span><span class="meta-content"><%= DateFormatUtils.format(articleDTO.getArticle().getGmtCreate(), "yyyy-MM-dd HH:mm", TimeZone.getTimeZone("GMT+8")) %></span>
                <span class="glyphicon glyphicon-eye-open"></span><span class="meta-content"><%= articleDTO.getArticle().getClick() %>次点击</span>
                <span class="glyphicon glyphicon-comment"></span><span class="meta-content"><a href="/article/<%= articleDTO.getArticle().getId() %>#disqus_thread"></a>条评论</span>
                <span class="glyphicon glyphicon-folder-open"></span>
                <span class="meta-content">
                  <% if (articleDTO.getTags() != null && !articleDTO.getTags().isEmpty()) { %>
                    <% for (Tag tag : articleDTO.getTags()) { %>
                      &nbsp;<a href="/tag/<%= tag.getId() %>"><%= tag.getName() %></a>
                    <% } %>
                  <% } %>
                </span>
              </h6>
            </div>
            <div class="panel-body">
              <% if (articleDTO.getArticle().getText().length() < 245) { %>
                <%= articleDTO.getArticle().getText() %>
                <div class="read-more"><a class="btn btn-default btn-xs" href="/article/<%= articleDTO.getArticle().getId() %>">Read More</a></div>
              <% } else { %>
                <%= articleDTO.getArticle().getText().substring(0, 245) %>
                <div class="read-more"><a class="btn btn-default btn-xs" href="/article/<%= articleDTO.getArticle().getId() %>">Read More</a></div>
              <% } %>
            </div>
          </div>
          <% } %>
        <% } %>
        <jsp:include page="partial/pagination.jsp" flush="true" />
      </div>
      <div class="col-md-3">
        <jsp:include page="partial/nav.jsp" flush="true" />
        <jsp:include page="partial/most-visit-articles.jsp" flush="true" />
        <jsp:include page="partial/tag-well.jsp" flush="true" />
        <jsp:include page="partial/link-well.jsp" flush="true" />
      </div>
    </div>
  </div>
  <jsp:include page="partial/footer.jsp" flush="true" />
  <script type="text/javascript">
  /* * * CONFIGURATION VARIABLES: EDIT BEFORE PASTING INTO YOUR WEBPAGE * * */
  var disqus_shortname = 'dadoo-blog'; // required: replace example with your forum shortname

  /* * * DON'T EDIT BELOW THIS LINE * * */
  (function () {
      var s = document.createElement('script'); s.async = true;
      s.type = 'text/javascript';
      s.src = '//' + disqus_shortname + '.disqus.com/count.js';
      (document.getElementsByTagName('HEAD')[0] || document.getElementsByTagName('BODY')[0]).appendChild(s);
  }());
  </script>
</body>
