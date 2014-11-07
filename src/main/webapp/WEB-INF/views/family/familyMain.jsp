<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%  
String basePath = request.getContextPath(); 
%>
<!DOCTYPE HTML>
<html lang="en">
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>家族维护</title>
<!-- Bootstrap core CSS -->
<link href="<%=basePath %>/assets/css/cross/custom-bootstrap.css" rel="stylesheet">
<!--font-awesome-->
<link href="<%=basePath %>/assets/css/datepicker.css" rel="stylesheet">

<link type="text/css" rel="stylesheet" href="<%=basePath %>/assets/css/font-awesome.min.css" />
 
<!-- Custom styles for this template --> 
<link href="<%=basePath %>/assets/css/cross/cross.css" rel="stylesheet">  
<link href="<%=basePath %>/assets/css/style.css" rel="stylesheet"> 

<link type="text/css" rel="stylesheet" href="<%=basePath %>/custom/css/common.css">
<link type="text/css" rel="stylesheet" href="<%=basePath %>/custom/css/main.css">
<link type="text/css" rel="stylesheet" href="<%=basePath %>/custom/css/familytree.css"> 
<link href="<%=basePath %>/assets/easyui/themes/default/easyui.css" rel="stylesheet">
<%-- <link href="<%=basePath %>/assets/easyui/themes/icon.css" rel="stylesheet"> --%>
<script type="text/javascript" src="<%=basePath %>/assets/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/assets/js/html5.js"></script>
<script type="text/javascript" src="<%=basePath %>/assets/js/bootstrap.min.js"></script> 

<script type="text/javascript" src="<%=basePath %>/assets/js/respond.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/assets/easyui/jquery.easyui.min.js"></script>

<script type="text/javascript" src="<%=basePath %>/assets/js/knockout.js"></script>
<script type="text/javascript" src="<%=basePath %>/custom/js/family/familyManage.js"></script>  
<script type="text/javascript" src="<%=basePath %>/assets/js/datepicker.js"></script>
<script type="text/javascript" src="<%=basePath %>/assets/js/jquery.gritter.min.js"></script> 
<script type="text/javascript" src="<%=basePath %>/assets/js/trainplan/common.js"></script> 
<script type="text/javascript">
var basePath = "<%=basePath %>";
</script>

 
<style type="text/css">
.pagination > li > a, .pagination > li > span {
	position: relative;
	float: left; 
	line-height: 1.428571429;
	text-decoration: none;
	background-color: #ffffff;
	border: 1px solid #dddddd;
	margin-left: -1px;
}
.Iframe_body {
   padding: 12px 2%;
}
.dropdown-menu.datepicker { max-width:220px; z-index: 10000 }

.ckbox.disabled{
	cursor: not-allowed;
	pointer-events: none;
	opacity: 0.65;
	filter: alpha(opacity=65);
	-webkit-box-shadow: none;
	box-shadow: none;
}
</style>

 
</head>
<body>
	<div class="row" > 
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
	</div>
	<!--分栏框开始-->
	<div class="row" style="padding-top:140px;min-height:700px">
        <div class="pull-left" style="width:30%;">   
				<div class="row" >
				  <div class="panel panel-default"> 
					<div class="table-responsive">
						<div class="form-group"
							style="margin-left: 20px; margin-top:10px">
							<a type="button" data-toggle="modal"
								data-target="#" class="btn btn-success" data-bind="click: openCreateFamilyDlg">添加</a>
							<a  type="button" class="btn btn-success" data-toggle="modal"
								data-target="#" id="btn_cross_delete" style="margin-left: 2px;" data-bind="click: delFamily">删除</a>
						    <a type="button" class="btn btn-success" data-toggle="modal" style="margin-left: 30px;"
										data-target="#" id="btn_cross_search"  data-bind="click: loadFamily">刷新</a> 
						</div> 
						<table class="table table-bordered table-striped table-hover" style="margin-left:5px; margin-right:5px; width:98%"
							id="cross_table_crossInfo">
							<thead>
								<tr> 
									<th style="width: 34px" align="center"></th>
									<th style="" align="center">家族名称</th>  
								</tr>
							</thead> 
							<tbody data-bind="foreach: families">
								<tr data-bind="style:{color: $parent.currentFamily() != null && $parent.currentFamily().familyId == familyId ? 'blue':''}, click: $root.loadFamilyPerson" >
									<td data-bind=" text: $index()+1 "></td>
									<td align="center" data-bind="text: familyName" ></td>
								</tr> 
							</tbody> 
						</table> 
					</div>
				</div>
			</div>  
		</div>   
	    <div class="pull-right" style="width: 69%;"> 
	       <div class="panel panel-default">  
				<div class="row" style="margin: 15px 10px 10px 10px;">
					<div class="pull-left" style="width: 100%;">  
				          <div class="panel-body" style="overflow-y:auto">
							<div class="table-responsive"> 
								<table id="person_tree_table" title="家族成员列表" class="easyui-treegrid" style="width:700px;height:250px">
								</table> 
							</div>
						</div> 
				 
					</div>
					<!-- <div class="pull-right" style="width: 28%;margin: 0px 10px 10px 10px;"> -->
				
				</div>
				</div>
			</div>  
	 
	</div>
	<div id="person_grid_menu" class="easyui-menu" style="width:120px;">
		<div data-bind="click: addParent" data-options="iconCls:'icon-add'">添加父节点</div>
		<div data-bind="click: addChildren"  data-options="iconCls:'icon-add'">添加子节点</div>
		<div data-bind="click: addPN"  data-options="iconCls:'icon-add'">添加兄弟节点</div>
		<div data-bind="click: addSpouse"  data-options="iconCls:'icon-add'">添加妻子节点</div> 
	</div>
	
	<div id="person_info_dlg" class="easyui-dialog" title="用户基本信息"
			data-options="iconCls:'icon-save'" style="width: 300px; height: 200px; padding: 10px">
			<form id="file_upload_id" name="file_upload_name" action="cross/fileUpload" method="post" enctype="multipart/form-data"> 
				<div  class="row" style="width: 100%; margin-top: 10px;">
				       <label for="exampleInputEmail3" class="control-label pull-left">
												姓:&nbsp;</label>
						<div class="pull-left">
							<input type="text" data-bind="value: newPersonInfo().firstName" class="form-control" style="width: 80px;" >
						</div> 
						 <label for="exampleInputEmail3" class="control-label pull-left">
												名:&nbsp;</label>
						<div class="pull-left">
							<input type="text" data-bind="value: newPersonInfo().lastName" class="form-control" style="width: 80px;" >
						</div> 
				</div>  
				<div  class="row " style="width: 100%; margin-top: 10px;">
					<div class="pull-right">
				     	<a type="button"  data-bind="click: savePersonNode" class="btn btn-success" data-toggle="modal"  >添加</a>
					</div>
					<!-- <input type="submit"  value="上传" data-bind=/> -->
				</div>
			</form>
		</div>  
 
    <!--导入弹窗--> 
	<div id="family_create_dlg" class="easyui-dialog" title="添加家族"
		data-options="iconCls:'icon-save'"
		style="width: 300px; height: 180px; padding: 10px">
		<form id="" name="file_upload_name" action="cross/fileUpload" method="post" enctype="multipart/form-data"> 
			 <div class="row" style="margin: 10px 0 5px 0;">
				<label for="exampleInputEmail2" class="control-label pull-left">家族名称:&nbsp;</label>
		        <div class="pull-left">
		           <input class="form-control" id="birth_day" style="width:150px;" placeholder="" data-bind="value: currentFamily().familyName">
		        </div> 
		   </div> 
			<div  class="row" style="width: 100%; margin-top: 10px;">
			     <a type="button" class="btn btn-success" data-toggle="modal" data-target="#" data-bind="click: saveFamily">保存</a>
				<!-- <input type="submit"  value="上传" data-bind=/> -->
			</div>
		</form>
	</div>   
</body>  
 <script type="text/html" id="tablefooter-short-template"> 
  <table style="width:100%;height:20px;">
    <tr style="width:100%;height:20px;">
     <td style="width:60%;height:20px;">
  		<span class="pull-left">共<span data-bind="html: totalCount()"></span>条  当前<span data-bind="html: totalCount() > 0 ? (currentIndex() + 1) : '0'"></span>到<span data-bind="html: endIndex()"></span>条   共<span data-bind="text: pageCount()"></span>页</span> 								 
  	 </td>
     <td style="width:40%;height:20px;padding:0px;pading-bottom:-14">   
		<span data-bind="attr:{class:currentPage() == 0 ? 'disabed': ''}"><a style="cursor:pointer;background-color: #ffffff;border: 1px solid #dddddd;margin-right:-5px;padding:0px 5px;" data-bind="text:'<<', click: currentPage() == 0 ? null: loadPre"></a>
	    <input type="text"  style="padding-left:8px;margin-bottom:0px;padding-bottom:0;width:30px;height: 19px;background-color: #ffffff;border: 1px solid #dddddd;" data-bind="value: parseInt(currentPage())+1, event:{keyup: pageNbrChange}"/>
		<a style="cursor:pointer;background-color: #ffffff;border: 1px solid #dddddd;margin-left:-5px;padding:0px 5px;" data-bind="text:'>>', click: (currentPage() == pageCount()-1 || totalCount() == 0) ? null: loadNext"  style="padding:0px 5px;"></a>
       </ul> 
	 
     </td >
  </tr>
</table> 
</script> 
</html>
