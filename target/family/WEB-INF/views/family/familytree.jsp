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
    <title>家族树</title> 
		
    <link rel="shortcut icon" href="${ctx}/assets/img/favicon.ico" />
    <link href="${ctx}/assets/css/custom-bootstrap.css" rel="stylesheet"> 

    <!--font-awesome-->
    <link  type="text/css" rel="stylesheet" href="${ctx}/assets/css/font-awesome.min.css"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/custom/css/common.css">
	<link type="text/css" rel="stylesheet" href="${ctx}/custom/css/main.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/custom/css/familytree.css"> 
     
    
    
    <link href="${ctx}/assets/css/cross/custom-bootstrap.css" rel="stylesheet">
	<!--font-awesome-->
	<link href="${ctx}/assets/css/datepicker.css" rel="stylesheet">
	
	<link type="text/css" rel="stylesheet" href="${ctx}/assets/css/font-awesome.min.css" />
	 
	<!-- Custom styles for this template --> 
	<link href="${ctx}/assets/css/cross/cross.css" rel="stylesheet">  
	<link href="${ctx}/assets/css/style.css" rel="stylesheet"> 
	<link href="${ctx}/assets/easyui/themes/default/easyui.css" rel="stylesheet">
	<link href="${ctx}/assets/easyui/themes/icon.css" rel="stylesheet">
	
	<script type="text/javascript" src="${ctx}/assets/lib/jorgchart/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/js/html5.js"></script>
	<script type="text/javascript" src="${ctx}/assets/js/bootstrap.min.js"></script> 
	<script type="text/javascript" src="${ctx}/assets/js/jquery-ui-1.10.4.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/js/jquery.ui.widget.js"></script>
	
	<script type="text/javascript" src="${ctx}/assets/js/respond.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/easyui/jquery.easyui.min.js"></script>
	
	<script type="text/javascript" src="${ctx}/assets/js/knockout.js"></script>
	<script type="text/javascript" src="${ctx}/assets/js/jquery.freezeheader.js"></script>
	<script type="text/javascript" src="${ctx}/assets/js/datepicker.js"></script>
	<script type="text/javascript" src="${ctx}/assets/js/jquery.gritter.min.js"></script> 
	<script type="text/javascript" src="${ctx}/assets/js/trainplan/common.js"></script> 
    <!-- jQuery includes -->
    <link rel="stylesheet" href="${ctx}/assets/lib/jorgchart/css/jquery.jOrgChart.css"/>
    <link rel="stylesheet" href="${ctx}/assets/lib/jorgchart/css/custom.css"/>
   
    <script type="text/javascript" src="${ctx}/assets/lib/jorgchart/jquery-ui.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/lib/jorgchart/jquery.contextmenu.r2.packed.js"></script>
    <script src="${ctx}/assets/lib/jorgchart/jquery.jOrgChart.js"></script>
    <script src="${ctx}/custom/js/family/familytree.js"></script>
   
    <script type="text/javascript" src="${ctx}/assets/js/common.js"></script> 
    <script src="assets/scripts/app.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function() {
            App.init();    
            App.initBxSlider();
        });
    </script>
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
							<li><a target="top" class="on" href="${ctx}/familytree">家族树</a></li>
							<li><a target="top" href="${ctx}/familyCommunity">家族关注圈</a></li>
							<li><a target="top"  href="${ctx}/familyPerson">家族人物</a></li>
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
        <div class="row" style="min-height:600px">		
 		     <div class="pull-left" style="width:20%"> 
				<table class="table table-bordered table-striped table-hover" style="margin-left:5px; margin-right:5px; width:98%"
						id="cross_table_crossInfo">
						<thead>
							<tr> 
								<th style="width: 34px" align="center"><input type="checkbox" style="margin-top:0" value="1"></th>
								<th style="" align="center">家族名称</th>  
							</tr>
						</thead> 
						<tbody data-bind="foreach: families">
							<tr data-bind="style:{color: $parent.currentFamily() != null && $parent.currentFamily().familyId == familyId ? 'blue':''}, click: $root.loadFamilyTree" >
								<td data-bind=" text: $index()+1 "></td>
								<td align="center" data-bind="text: familyName" ></td>
							</tr> 
						</tbody> 
					</table> 
				</div>  
				<div class="treebox pull-left" style="width:80%;" align="center">  
				   <div id="chart" class="orgChart" align="center"></div> 
				</div>  
				 <div id="chartBuffe" style="display: none" ></div> 
				 
		</div> 
		 <div class="contextMenu" id="myMenu1">
	      <ul>
	        <li id="add"><i class="fa fa-refresh"></i> 打开</li>
	        <li id="email"><i class="fa fa-refresh"></i> 邮件</li>
	        <li id="save"><i class="fa fa-refresh"></i> 保存</li>
	      </ul>
	    </div>   
	    <div id="dlg" class="easyui-dialog" title="导入对数表文件" data-options="iconCls:'icon-save'" >
			<form id="file_upload_id" name="file_upload_name" action="cross/fileUpload" method="post" enctype="multipart/form-data"> 
				<div  class="row" style="width: 100%; margin-top: 10px;">
				       <label for="exampleInputEmail3" class="control-label pull-left">
												姓:&nbsp;</label>
						<div class="pull-left">
							<input type="text" data-bind="value: personInfo().firstName" class="form-control" style="width: 80px;" >
						</div> 
						 <label for="exampleInputEmail3" class="control-label pull-left">
												名:&nbsp;</label>
						<div class="pull-left">
							<input type="text" data-bind="value: personInfo().lastName" class="form-control" style="width: 80px;" >
						</div> 
				</div> 
				<div  class="row" style="width: 100%; margin-top: 10px;">
				     <label for="exampleInputEmail3" class="control-label pull-left">
												关系:&nbsp;</label>
					  <select style="width:100px;margin-left:5px" class="form-control pull-left"   data-bind="options: [{name: '子女', code: '2'}], value: personInfo().relationShipType, optionsText: 'name', optionsValue: 'code',  optionsCaption: ''"></select>
				</div>
				<div  class="row" style="width: 100%; margin-top: 10px;">
				     <a type="button" id="btn_fileToUpload"
						class="btn btn-success" data-toggle="modal" data-target="#" data-bind="click: savePersonNode">添加</a>
					<!-- <input type="submit"  value="上传" data-bind=/> -->
				</div>
			</form>
		</div>  
	</body>
</html>
