<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*" %>

<%
  List<Link> links = (List<Link>)request.getAttribute("links");
%>

<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <jsp:include page="../partial/head.jsp" flush="true">
    <jsp:param name="title" value="链接管理" />
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
        <a class="btn btn-primary pull-right" href="/admin/link/add">新链接</a>
        <table class="table table-hover">
          <thead>
            <tr>
              <th>名称</th>
              <th>地址</th>
              <th>描述</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <% if (links != null) { %>
              <% for (Link link : links) { %>
              <tr>
                <td><a href="<%= link.getUrl() %>"><%= link.getName() %></a></td>
                <td><%= link.getUrl() %></td>
                <td><%= link.getDescription() %></td>
                <td>
                  <a href="/admin/link/<%= link.getId() %>/update">修改</a>
                  <a href="/admin/link/<%= link.getId() %>/delete">删除</a>
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
    $("#admin-link-li").addClass("active");
  </script>
</body>
