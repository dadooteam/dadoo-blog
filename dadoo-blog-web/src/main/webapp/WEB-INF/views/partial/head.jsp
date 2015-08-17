<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 

<meta name="render" content="webkit">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="dadoo blog">
<meta name="author" content="dadooteam">
<title><%= request.getParameter("title") %> &nbsp; | &nbsp; <%= session.getAttribute("header") %></title>

<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
<link href="//cdn.bootcss.com/bootstrap-markdown/2.9.0/css/bootstrap-markdown.min.css" rel="stylesheet">
<link rel="stylesheet/less" href="/resources/less/style.less" />

<!--[if lt IE 9]>
  <script src="//cdn.bootcss.com/html5shiv/r29/html5.min.js"></script>
  <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

<script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/less.js/2.5.1/less.min.js"></script>

<script src="//cdn.bootcss.com/bootstrap-markdown/2.9.0/js/bootstrap-markdown.min.js"></script>
<script src="//cdn.bootcss.com/markdown.js/0.5.0/markdown.min.js"></script>

