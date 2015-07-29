<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*,org.apache.commons.lang3.tuple.*,im.dadoo.blog.web.vo.*" %>

<%
  List<TagWellVO> tagWellVOs = (List<TagWellVO>)request.getAttribute("tagWellVOs");
%>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">标签</h3>
  </div>
  <div class="panel-body">
    <ul class="sidebar-ul">
      <% if (tagWellVOs != null) { %>
        <% for (TagWellVO tagWellVO : tagWellVOs) { %>
        <li>(<%= tagWellVO.getSize() %>)<a href="/tag/<%= tagWellVO.getTag().getId() %>"><%= tagWellVO.getTag().getName() %></a></li>
        <% } %>
      <% } %>
    </ul>
  </div>
</div>