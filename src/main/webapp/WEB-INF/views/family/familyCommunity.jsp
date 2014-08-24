<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>家族广场</title>
    <link rel="shortcut icon" href="${ctx}/assets/img/favicon.ico" />
    <link type="text/css" href="${ctx}/assets/css/minified/jquery-ui.min.css" rel="stylesheet"/>
    <link href="${ctx}/assets/css/custom-bootstrap.css" rel="stylesheet"> 
    <!--font-awesome-->
    <link  type="text/css" rel="stylesheet" href="${ctx}/assets/css/font-awesome.min.css"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/custom/css/common.css">
	<link type="text/css" rel="stylesheet" href="${ctx}/custom/css/main.css">
     <link type="text/css" rel="stylesheet" href="${ctx}/custom/css/familytree.css">
    <!-- Custom styles for this template -->
    <link href="${ctx}/assets/css/style.css" rel="stylesheet">
    <script src="${ctx}/assets/js/html5.js"></script>
    <script src="${ctx}/assets/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/minified/jquery-ui.min.js"></script>
    <script src="${ctx}/assets/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/trainplan/common.js"></script> 
    <script type="text/javascript" src="${ctx}/assets/js/knockout.js"></script>
    <script type="text/javascript" src="${ctx}/custom/js/talk/talk.js"></script>
   
</head>
<body>
<div style="position: fixed;width:100%;z-index:500">
	<div id="header">
			<div class="bd"> </div>
	</div>
	<div id="nav">
			<div class="bd content center-block clearfix" style="min-height: 30px">
					<ul class="toolsul clearfix">
							<li><a href="#"><em class="ico icon-message"></em><em class="num">1</em></a></li>
							<li><a href="#"><em class="ico icon-cog" href="#"></em></a></li>
							<li><a href="#"><em class="ico icon-quit" href="#"></em></a></li>
					</ul>
					<ul class="navul clearfix">
							<li><a target="top" href="${ctx}/familytree">家族树</a></li>
							<li><a target="top" class="on" href="${ctx}/familyCommunity">家族关注圈</a></li>
							<li><a target="top"  href="${ctx}/familyPerson">家族人物</a></li>
							<li><a target="top" href="${ctx}/familyImg">地图关系图</a></li>
							<li><a target="top" href="${ctx}/person/personInfo">个人信息</a></li>
					</ul> 
			</div>
	</div>   
</div> 
<div class="subtools" style="padding-top: 130px;">
		<div class="content center-block clearfix" style="min-height: 30px"> 
				<div class="hd"><em class="tit">我的家族圈</em></div>
				<div class="bd clearfix">
						<div class="search clearfix"> <em>模糊查找：</em>
								<input type="text"  name="" class="input" placeholder="姓名">
								<button class="button" type="submit">搜索</button>
						</div>
						<div class="filtrate"> <a id="btn-filtr" class="btn-filtr" href="javascript:;"><em class="icon-filtr"></em>精确查找</a>
								<div id="filtrbox" class="filtrbox">
										<div class="filtrbox-bd">
												<dl>
														<dt>姓&nbsp;&nbsp;名：</dt>
														<dd>
																<input class="input" type="text" />
														</dd>
												</dl>
												<dl>
														<dt>地&nbsp;&nbsp;区：</dt>
														<dd>
																<input class="input" type="text" />
														</dd>
												</dl>
												<div class="btns">
														<button class="btn-seek" type="button">查找</button>
												</div>
										</div>
								</div>
								<script>
						$(function(){
								$("#btn-filtr").click(function(){
										$("#filtrbox").toggle(500)
									})
							})
					</script> 
						</div>
						<div class="link"><a href="#">我的家族圈</a>&nbsp;&nbsp;<a href="#">切换到地图</a></div>
				</div>
		</div>
</div>

<div class="container">
        <div class="mod-comments" style="margin-left:200px">
				<div class="mod-commnets-poster" style="width:600px">
						<div class="textinput">
								<input class="input" data-bind="value: userTalk" type="text"  placeholder="我也说一句"/>
						</div>
						<div class="quick-comment"> <a data-bind="click: sendTalk" class="btn btn-success">发表</a> </div>
				</div>
		</div>
		<div class="content center-block clearfix pt30">
				<div class="rows clearfix">
						<div class="column-1">
								<div data-bind="template: { name: 'community-template', foreach: talks }" class="mesgroup">
										 
								</div>
								<div class="loadmore mt10 pl110"> <a class="btn-more" href="#"><em>没有更多了</em></a> </div>
						</div>
						<div class="column-2">
								<div class="myfamily">
										<div class="hd"><a class="more" href="#"><em class="icon-cre-src"></em></a><em class="tit">我的家人</em></div>
										<div class="bd">
												<ul class="clearfix">
														<li>
																<div class="myItem">
																		<div class="pic"><a href="#"><img src="images/photo01.jpg"></a></div>
																		<div class="cont">
																				<div class="title"><a class="name" href="#">就是我</a><em>关系</em><a class="focuson" href="#"><i class="icon-focus"></i>关注</a></div>
																				<p>90后小女子一枚，远离家乡亲人远离男友只身一人来北京北漂至于...</p>
																		</div>
																</div>
														</li>
														<li>
																<div class="myItem">
																		<div class="pic"><a href="#"><img src="images/photo01.jpg"></a></div>
																		<div class="cont">
																				<div class="title"><a class="name" href="#">就是我</a><em>关系</em><a class="focuson" href="#"><i class="icon-focus"></i>关注</a></div>
																				<p>90后小女子一枚，远离家乡亲人远离男友只身一人来北京北漂至于...</p>
																		</div>
																</div>
														</li>
														<li>
																<div class="myItem">
																		<div class="pic"><a href="#"><img src="images/photo01.jpg"></a></div>
																		<div class="cont">
																				<div class="title"><a class="name" href="#">就是我</a><em>关系</em><a class="focuson" href="#"><i class="icon-focus"></i>关注</a></div>
																				<p>90后小女子一枚，远离家乡亲人远离男友只身一人来北京北漂至于...</p>
																		</div>
																</div>
														</li>
														<li>
																<div class="myItem">
																		<div class="pic"><a href="#"><img src="images/photo01.jpg"></a></div>
																		<div class="cont">
																				<div class="title"><a class="name" href="#">就是我</a><em>关系</em><a class="focuson" href="#"><i class="icon-focus"></i>关注</a></div>
																				<p>90后小女子一枚，远离家乡亲人远离男友只身一人来北京北漂至于...</p>
																		</div>
																</div>
														</li> 
												</ul>
										</div>
								</div>
						</div>
				</div>
		</div>
</div> 
<div class="footer">
		<div class="bd content center-block">
				<div class="copyright">Copyright © 2014 家族社交 All Rights Reserved</div>
		</div>
</div> 
 
</body>

<script type="text/html" id="community-template"> 
 <div class="mesblock">
		<div class="user"><a href="#"><img data-bind="attr:{src: userHeadUrl}"></a></div>
		    <div class="mescont">
				<div class="mesiner">
					<div class="title"><a class="name" href="#"><span data-bind="text: nick"></span></a><em class="time">2014-07-17 17:01</em></div>
						<div class="summary" data-bind="text: talkContent"> 
						</div>
						<div class="mesbot">
					         <div class="tools"><a href="#"><em class="icon-com"></em>评论</a>(<span data-bind="html: replyCount"></span>)<a href="#"><em class="icon-praise"></em>赞(<span data-bind="html: agreeCount"></span>)</a></div>
					               <div class="mod-comments">
					                   <div class="mod-commnets-poster">
					        	           <div class="textinput">
					           		           <input data-bind="value: replyContent" class="input" type="text"  placeholder="我也说一句"/>
								           </div>
									       <div class="quick-comment"> <a data-bind="click: $root.sendReply" class="btn-quick-comment">发表</a> </div>
							            </div>
									</div>
								</div>
							</div>
						</div>
				</div>
</script> 
</html>
