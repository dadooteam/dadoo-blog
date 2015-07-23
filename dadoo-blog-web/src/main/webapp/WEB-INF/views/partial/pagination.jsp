<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%
  Integer cur = (Integer)request.getAttribute("curPagecount");
  Integer max = (Integer)request.getAttribute("maxPagecount");
%>
<ul class="pagination">
  <% if (cur > 1) { %>
    <li><a href="/?pagecount=<%= cur-1 %>">&laquo;</a></li>
  <% } %>
  <% for (Integer i = 1; i <= max; i++) { %>
    <% if (i == cur) { %>
    <li class="active"><a href="/?pagecount=<%= i %>"><%= i %></a></li>
    <% } else { %>
      <li><a href="/?pagecount=<%= i %>"><%= i %></a></li>
    <% } %>
  <% } %>
  <% if (cur < max) { %>
    <li><a href="/?pagecount=<%= cur+1 %>">&raquo;</a></li>
  <% } %> 
</ul>