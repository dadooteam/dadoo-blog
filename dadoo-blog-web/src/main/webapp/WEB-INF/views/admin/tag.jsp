<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*" %>

<%
  List<Tag> tags = (List<Tag>)request.getAttribute("tags");
%>

<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <jsp:include page="../partial/head.jsp" flush="true">
    <jsp:param name="title" value="标签管理" />
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
        <a class="btn btn-primary pull-right" href="/admin/tag/add">新标签</a>
        <table class="table table-hover">
          <thead>
            <tr>
              <th>名称</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <% if (tags != null) { %>
              <% for (Tag tag : tags) { %>
              <tr>
                <td><%= tag.getName() %></td>
                <td>
                  <a href="/admin/tag/<%= tag.getId() %>/update">修改</a>
                  <a href="/admin/tag/<%= tag.getId() %>/delete">删除</a>
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
    $("#admin-tag-li").addClass("active");
  </script>
</body>
