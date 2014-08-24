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
    <title>家族人员</title>
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
							<li><a target="top" href="${ctx}/familyCommunity">家族关注圈</a></li>
							<li><a target="top" class="on" href="${ctx}/familyPerson">家族人物</a></li>
							<li><a target="top" href="${ctx}/familyImg">地图关系图</a></li>
							<li><a target="top" href="${ctx}/person/personInfo">个人信息</a></li>
					</ul> 
			</div>
	</div>   
</div> 
<div class="subtools" style="padding-top: 130px;">
		<div class="content center-block clearfix" style="min-height: 30px"> 
				<div class="hd"><em class="tit">家族树</em></div>
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
<div class="treebox">
								<table class="treetable" align="center" border="0" cellspacing="0" cellpadding="0">
										<tr>
												<td>&nbsp;</td>
												<td><div class="treeitem treeitem-noline" >
																<div class="treeline treeline-noline"></div>
																<div class="bd">
																		<div class="newstip"></div>
																		<div class="pic"><img src="images/photo04.jpg">
																				<div class="tools">
																						<div class="tools-bd"><a href="#"><em class="icon-edit"></em>编辑</a></div>
																				</div>
																		</div>
																		<div class="name"><a href="#">杨延昭</a></div>
																</div>
																<div class="treesub">
																		<div class="treesub-bd">
																				<div class="treesub-pic"><img src="images/photo04.jpg"></div>
																				<div class="treesub-name"><a href="#">杨氏</a></div> 
																		</div> 
																</div>
																<div class="treesub">
																		<div class="treesub-bd">
																				<div class="treesub-pic"><img src="images/photo04.jpg"></div>
																				<div class="treesub-name"><a href="#">杨氏</a></div> 
																		</div> 
																</div>
														</div></td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
										</tr>
										<tr>
												<td><div class="treeitem">
																<div class="treeline treeline2"></div>
																<div class="bd">
																		<div class="pic"><img src="images/photo04.jpg">
																				<div class="tools">
																						<div class="tools-bd"><a href="#"><em class="icon-edit"></em>编辑</a></div>
																				</div>
																		</div>
																		<div class="newstip"></div>
																		<div class="name"><a href="#">杨延昭</a></div>
																</div>
																<div class="treesub">
																		<div class="treesub-bd">
																				<div class="treesub-pic"><img src="images/photo04.jpg"></div>
																				<div class="treesub-name"><a href="#">杨氏</a></div>
																		</div>
																</div>
														</div></td>
												<td><div class="treeitem">
																<div class="treeline treeline1"></div>
																<div class="bd">
																		<div class="newstip"></div>
																		<div class="pic"><img src="images/photo04.jpg">
																				<div class="tools">
																						<div class="tools-bd"><a href="#"><em class="icon-edit"></em>编辑</a></div>
																				</div>
																		</div>
																		<div class="name"><a href="#">杨延昭</a></div>
																</div>
																<div class="treesub">
																		<div class="treesub-bd">
																				<div class="treesub-pic"><img src="images/photo04.jpg"></div>
																				<div class="treesub-name"><a href="#">杨氏</a></div>
																		</div>
																</div>
														</div></td>
												<td><div class="treeitem">
																<div class="treeline treeline4"></div>
																<div class="bd">
																		<div class="pic"><img src="images/photo04.jpg">
																				<div class="tools">
																						<div class="tools-bd"><a href="#"><em class="icon-edit"></em>编辑</a></div>
																				</div>
																		</div>
																		<div class="name"><a href="#">杨延昭</a></div>
																</div>
																<div class="treesub">
																		<div class="treesub-bd">
																				<div class="treesub-pic"><img src="images/photo04.jpg"></div>
																				<div class="treesub-name"><a href="#">杨氏</a></div>
																		</div>
																</div>
														</div></td>
												<td><div class="treeitem">
																<div class="treeline treeline3"></div>
																<div class="bd">
																		<div class="pic"><img src="images/photo04.jpg">
																				<div class="tools">
																						<div class="tools-bd"><a href="#"><em class="icon-edit"></em>编辑</a></div>
																				</div>
																		</div>
																		<div class="name"><a href="#">杨延昭</a></div>
																</div>
																<div class="treesub">
																		<div class="treesub-bd">
																				<div class="treesub-pic"><img src="images/photo04.jpg"></div>
																				<div class="treesub-name"><a href="#">杨氏</a></div>
																		</div>
																</div>
														</div></td>
										</tr>
										<tr>
												<td>&nbsp;</td>
												<td><div class="treeitem treeitem-noline" >
																<div class="treeline"></div>
																<div class="bd">
																		<div class="pic"><img src="images/photo03.jpg">
																				<div class="tools">
																						<div class="tools-bd"><a href="#"><em class="icon-edit"></em>编辑</a></div>
																				</div>
																		</div>
																		<div class="name"><a href="#">杨延昭</a></div>
																</div>
																<div class="treesub">
																		<div class="treesub-bd">
																				<div class="treesub-pic"><img src="images/photo04.jpg"></div>
																				<div class="treesub-name"><a href="#">杨氏</a></div>
																		</div>
																</div>
														</div></td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
										</tr>
										<tr>
												<td>&nbsp;</td>
												<td><div class="treeitem treeitem-noline myself" >
																<div class="treeline"></div>
																<div class="bd">
																		<div class="newstip"></div>
																		<div class="pic"><img src="images/photo02.jpg">
																				<div class="tools">
																						<div class="tools-bd"><a href="#"><em class="icon-edit"></em>编辑</a></div>
																				</div>
																		</div>
																		<div class="name"><a href="#">杨再兴</a></div>
																</div>
																<div class="treesub">
																		<div class="treesub-bd">
																				<div class="treesub-pic"><img src="images/photo04.jpg"></div>
																				<div class="treesub-name"><a href="#">杨氏</a></div>
																		</div>
																</div>
														</div></td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
										</tr>
								</table>
								<div class="treerow"> </div>
								<div class="treerow"> </div>
						</div>
 
</body>
</html>
