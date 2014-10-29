<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*,org.apache.commons.lang3.tuple.*" %>

<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <meta name="description" content="blog">
  <jsp:include page="partial/head.jsp" flush="true">
    <jsp:param name="title" value="更新历史" />
  </jsp:include>
</head>
<body>
  <jsp:include page="partial/header.jsp" flush="true" />
  <div class="container">
    <div class="row">
      <div class="col-md-9">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h1 class="panel-title">更新历史</h1>
          </div>
          <ul class="list-group">
            <li class="list-group-item">
              <h3 class="list-group-item-heading"><span class="label label-info">0.0.1</span><small class="pull-right">更新时间:2014-05-20</small></h3>
              <ol class="version-list">
                <li>创建blog</li>
              </ol>
            </li>
            <li class="list-group-item">
              <h3 class="list-group-item-heading"><span class="label label-info">0.0.2</span><small class="pull-right">更新时间:2014-05-21</small></h3>
              <ol class="version-list">
                <li>#bugfixed 删除文章后与标签关联信息未同时删除</li>
                <li>#bugfixed 没有文章时页面显示404</li>
                <li>#feature 增加百度站长统计功能</li>
              </ol>
            </li>
            <li class="list-group-item">
              <h3 class="list-group-item-heading"><span class="label label-info">0.0.3</span><small class="pull-right">更新时间:2014-05-22</small></h3>
              <ol class="version-list">
                <li>#bugfixed 增加链接时未判断url是否合法</li>
                <li>#bugfixed 点开文章时，浏览次数与侧边栏的统计不同步</li>
                <li>#feature 提供基本信息配置功能</li>
              </ol>
            </li>
            <li class="list-group-item">
              <h3 class="list-group-item-heading"><span class="label label-info">0.0.4</span><small class="pull-right">更新时间:2014-06-14</small></h3>
              <ol class="version-list">
                <li>#feature 后台增加访问和函数监控日志</li>
                <li>#feature 将日志的创建时间，评论次数等信息从文章底端移动到标题底端，并且用图表替换文字</li>
              </ol>
            </li>
            <li class="list-group-item">
              <h3 class="list-group-item-heading"><span class="label label-info">0.0.5</span><small class="pull-right">更新时间:2014-06-16</small></h3>
              <ol class="version-list">
                <li>#improve 使用guava替代apache commons，改进参数合法性检查</li>
                <li>#improve 使用interceptor改善侧边栏渲染的代码结构</li>
                <li>#improve 使用lombok库简化代码</li>
              </ol>
            </li>
            <li class="list-group-item">
              <h3 class="list-group-item-heading"><span class="label label-info">0.1.0</span><small class="pull-right">更新时间:2014-08-02</small></h3>
              <ol class="version-list">
                <li>#improve 改善项目组织结构，分为dadoo-blog-domain,dadoo-blog-biz和dadoo-blog-web三个子项目</li>
                <li>#improve 将各DAO的RowMapper作为单独的类</li>
                <li>#improve 引入log4j2作为日志组件，替代logback和dadoo-log系统</li>
              </ol>
            </li>
            <li class="list-group-item">
              <h3 class="list-group-item-heading"><span class="label label-info">0.1.2</span><small class="pull-right">更新时间:2014-08-29</small></h3>
              <ol class="version-list">
                <li>#bugfixed 修正分页错误</li>
                <li>#improve 改进异常参数的判断和处理</li>
              </ol>
            </li>
            <li class="list-group-item">
              <h3 class="list-group-item-heading"><span class="label label-info">0.1.3</span><small class="pull-right">更新时间:2014-09-16</small></h3>
              <ol class="version-list">
                <li>#bugfixed 修正分页错误</li>
                <li>#improve guava升级到18.0，springframework升级到4.1.0</li>
              </ol>
            </li>
          </ul>
        </div>
      </div>
      <div class="col-md-3">
        <jsp:include page="partial/nav.jsp" flush="true" />
        <jsp:include page="partial/most-visit-articles.jsp" flush="true" />
        <jsp:include page="partial/tag-well.jsp" flush="true" />
      </div>
    </div>
  </div>
  <jsp:include page="partial/footer.jsp" flush="true" />
  <script type="text/javascript">
  /* * * CONFIGURATION VARIABLES: EDIT BEFORE PASTING INTO YOUR WEBPAGE * * */
  var disqus_shortname = 'dadoo-blog'; // required: replace example with your forum shortname

  /* * * DON'T EDIT BELOW THIS LINE * * */
  (function () {
      var s = document.createElement('script'); s.async = true;
      s.type = 'text/javascript';
      s.src = '//' + disqus_shortname + '.disqus.com/count.js';
      (document.getElementsByTagName('HEAD')[0] || document.getElementsByTagName('BODY')[0]).appendChild(s);
  }());
  </script>
</body>
