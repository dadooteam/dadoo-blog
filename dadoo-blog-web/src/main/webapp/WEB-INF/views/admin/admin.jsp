<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*" %>

<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <meta name="description" content="dadoo blog">
  <jsp:include page="../partial/head.jsp" flush="true">
    <jsp:param name="title" value="管理界面" />
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
        
      </div>
    </div>
  </div>
  <jsp:include page="../partial/footer.jsp" flush="true" />
</body>
