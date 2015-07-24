<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 

<div id="header" class="container">
  <div class="row">
    <div class="col-md-12">
      <div id="header-title">
        <h1><a href="/"><%= session.getAttribute("header") %></a></h1>
      </div>
    </div>
  </div>
</div>