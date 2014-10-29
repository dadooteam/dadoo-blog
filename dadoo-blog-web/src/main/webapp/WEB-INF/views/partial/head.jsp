<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 

<meta name="render" content="webkit">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="dadoo blog">
<meta name="author" content="dadoosoon">
<title><%= request.getParameter("title") %> &nbsp; | &nbsp; <%= session.getAttribute("title") %></title>

<link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.1.1/css/bootstrap.min.css" />
<link rel="stylesheet/less" href="/resources/less/style.less" />
<!--[if lt IE 9]>
  <script src="http://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
  <script src="http://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script src="http://cdn.staticfile.org/less.js/1.7.0/less.min.js"></script>

