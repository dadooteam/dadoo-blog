<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*,org.apache.commons.lang3.tuple.*" %>

<%
  List<Pair<Tag, Integer>> pairs = (List<Pair<Tag, Integer>>)request.getAttribute("tag-size-pairs");
%>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">标签</h3>
  </div>
  <div class="panel-body">
    <ul class="sidebar-ul">
      <% if (pairs != null) { %>
        <% for (Pair<Tag, Integer> pair : pairs) { %>
        <li>(<%= pair.getRight() %>)<a href="/tag/<%= pair.getLeft().getId() %>"><%= pair.getLeft().getName() %></a></li>
        <% } %>
      <% } %>
    </ul>
  </div>
</div>