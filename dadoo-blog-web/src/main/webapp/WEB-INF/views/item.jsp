<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*,org.apache.commons.lang3.tuple.*,im.dadoo.blog.biz.dto.*,im.dadoo.blog.cons.*" %>

<%
  ArticleDTO articleDTO = (ArticleDTO)request.getAttribute("articleDTO");
  Pair<ArticleDTO, ArticleDTO> pn = (Pair<ArticleDTO, ArticleDTO>)request.getAttribute("prev-next");
%>

<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <meta name="description" content="blog">
  <jsp:include page="partial/head.jsp" flush="true">
    <jsp:param name="title" value="<%= articleDTO.getArticle().getTitle() %>" />
  </jsp:include>
</head>
<body>
  <jsp:include page="partial/header.jsp" flush="true" />
  <div class="container">
    <div class="row">
      <div class="col-md-9">
        <% if (articleDTO != null) { %>
        <div id="article-<%= articleDTO.getArticle().getId() %>" class="panel panel-default">
            <div class="panel-heading">
              <h1 class="panel-title">
                <% if(articleDTO.getArticle().getTop() == DadooConstant.TOP_Y) { %>
                  <small>[顶]</small>
                <% } %>
                <%= articleDTO.getArticle().getTitle() %>
              </h1>
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
            <div id="main-content" class="panel-body"><%= articleDTO.getArticle().getHtml() %></div>
          </div>
          <div>
            <% if (pn != null) { %>
            <ul class="pager">
              <% if (pn.getLeft() != null) { %>
                <li class="pull-left"><a href="/article/<%= pn.getLeft().getArticle().getId() %>">Prev:<%= pn.getLeft().getArticle().getTitle() %></a></li>
              <% } %>
              <% if (pn.getRight() != null) { %>
                <li class="pull-right"><a href="/article/<%= pn.getRight().getArticle().getId() %>">Next:<%= pn.getRight().getArticle().getTitle() %></a></li>
              <% } %>
            </ul>
            <% } %>
          </div>
          <div id="disqus_thread"></div>
          <script type="text/javascript">
              /* * * CONFIGURATION VARIABLES: EDIT BEFORE PASTING INTO YOUR WEBPAGE * * */
              var disqus_shortname = 'dadoo-blog'; // required: replace example with your forum shortname

              /* * * DON'T EDIT BELOW THIS LINE * * */
              (function() {
                  var dsq = document.createElement('script'); dsq.type = 'text/javascript'; dsq.async = true;
                  dsq.src = '//' + disqus_shortname + '.disqus.com/embed.js';
                  (document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(dsq);
              })();
          </script>
          <noscript>Please enable JavaScript to view the <a href="http://disqus.com/?ref_noscript">comments powered by Disqus.</a></noscript>
          <a href="http://disqus.com" class="dsq-brlink">comments powered by <span class="logo-disqus">Disqus</span></a>
        <% } %>
      </div>
      <div class="col-md-3">
        <jsp:include page="partial/nav.jsp" flush="true" />
        <jsp:include page="partial/most-visit-articles.jsp" flush="true" />
        <jsp:include page="partial/tag-well.jsp" flush="true" />
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
  $("#main-content").html(markdown.toHTML($("#main-content").text()));
  </script>
</body>
