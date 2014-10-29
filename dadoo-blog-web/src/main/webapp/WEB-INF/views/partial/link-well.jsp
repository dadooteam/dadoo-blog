<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*,org.apache.commons.lang3.tuple.*" %>

<%
  List<Link> links = (List<Link>)request.getAttribute("links");
%>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">友情链接</h3>
  </div>
  <div class="panel-body">
    <ul class="sidebar-ul">
      <% if (links != null) { %>
        <% for (Link link : links) { %>
        <li><img src="<%= link.getUrl() %>/favicon.ico" height="15px" width="15px"><a href="<%= link.getUrl() %>"><%= link.getName() %></a></li>
        <% } %>
      <% } %>
    </ul>
  </div>
</div>