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
    
    <title>客运组织与调度管理</title>
    <link rel="shortcut icon" href="${ctx}/assets/img/favicon.ico" />
     <link href="${ctx}/assets/css/custom-bootstrap.css" rel="stylesheet"> 
    <!--font-awesome-->
    <link  type="text/css" rel="stylesheet" href="${ctx}/assets/css/font-awesome.min.css"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/custom/css/common.css">
	<link type="text/css" rel="stylesheet" href="${ctx}/custom/css/main.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/custom/css/familytree.css">
    
     
     
    <link  type="text/css" rel="stylesheet" href="${ctx}/assets/jquery.imgareaselect-0.9.10/css/imgareaselect-animated.css"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/assets/jquery.imgareaselect-0.9.10/css/imgareaselect-default.css">
	<link type="text/css" rel="stylesheet" href="${ctx}/assets/jquery.imgareaselect-0.9.10/css/imgareaselect-deprecated.css">
    <!-- Custom styles for this template -->
    <link href="${ctx}/assets/css/style.css" rel="stylesheet">
   
    <link href="${ctx}/assets/easyui/themes/default/easyui.css" rel="stylesheet">
	<link href="${ctx}/assets/easyui/themes/icon.css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/assets/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/easyui/jquery.easyui.min.js"></script>
    <script src="${ctx}/assets/js/html5.js"></script>
    <script src="${ctx}/assets/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/jquery.gritter.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/trainplan/common.js"></script> 
    <script type="text/javascript" src="${ctx}/assets/js/knockout.js"></script>
    <script type="text/javascript" src="${ctx}/custom/js/person/personInfo.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/datepicker.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/ajaxfileupload.js"></script> 
    
    <script type="text/javascript" src="${ctx}/assets/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.pack.js"></script>
	<script type="text/javascript">
		var basePath = "${ctx}";
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
							<li><a target="top" href="${ctx}/familytree">家族树</a></li>
							<li><a target="top" href="${ctx}/familyCommunity">家族关注圈</a></li>
							<li><a target="top" href="${ctx}/familyPerson">家族人物</a></li>
							<li><a target="top" href="${ctx}/familyImg">地图关系图</a></li>
							<li><a target="top" class="on" href="${ctx}/person/personInfo">个人信息</a></li>
					</ul> 
			</div>
	</div>   
</div>  
 <div class="easyui-tabs" style="width:100%;height:auto;padding-top: 120px;">
        <div title="个人信息维护" style="padding:10px">
          <div class="subtools" style="">
			   <div class="content center-block clearfix">
				  <div class="rows clearfix pt30">
					 <div class="formgroup">
					 		 <div class="form-hd"><span class="form-tit">个人信息</span></div>
					 		 <div class="form-bd"> 
							 		<table class="formtable" width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
												<td width="180"><span class="dt">姓</span></td>
												<td><input data-bind="value: personInfo().firstName, event:{change: hitFamilyForUser}" class="txt-input" type="text" /></td>
												<td rowspan="3">
												   <div id="head_img_div">
												        <div>
												  	 	   <img id="user_head_img" data-bind="attr:{src: '${ctx}/' + personInfo().headUrl()}" />
												   		</div>
												   		<div>
												   		   <input id="changeHead" type="button" value="更改头像">
												   		</div>
												   </div>
												   <div id="head_img_change_div" style="display: none">
												    <div class="row">
													   <img id="ferret" src="${ctx}/custom/images/p11.jpg" style="float: left; margin-right: 10px;"/>
													</div>
													<div class="row">
													  <input class="pull-left" style="width:75px" id="headSelectInput" type="file" name="fileName"><input class="pull-left" style="margin-left:10px" id="headSelectSure" type="button" value="确定">
													</div>
													<img id="testSize" style="display: none"/>
													<input id="head_cut_w" type="hidden" value=""/>
													<input id="head_cut_h" type="hidden" value=""/>
													<input id="head_cut_x1" type="hidden" value=""/>
													<input id="head_cut_y1" type="hidden" value=""/>
													<script type="text/javascript">
													function preview(img, selection) {
													    var scaleX = 100 / (selection.width || 1);
													    var scaleY = 100 / (selection.height || 1); 
													    $('#ferret + div > img').css({
													        width: Math.round(scaleX * $("#ferret").width()) + 'px',
													        height: Math.round(scaleY *  $("#ferret").height()) + 'px',
													        marginLeft: '-' + Math.round(scaleX * selection.x1) + 'px',
													        marginTop: '-' + Math.round(scaleY * selection.y1) + 'px'
													    });
													}  
													 
													$(document).ready(function () {
													    $('<div><img id="showTest" src="${ctx}/custom/images/p11.jpg" style="position: relative;"/><div>')
													        .css({
													            float: 'left',
													            position: 'relative',
													            overflow: 'hidden',
													            width: '100px',
													            height: '100px'
													        })
													        .insertAfter($('#ferret'));
													    
													    $("#changeHead").click(function(){
													    	$('#ferret').imgAreaSelect("show");
													    	$("#ferret").attr("src", $("#user_head_img").attr("src"));
															$("#showTest").attr("src", $("#user_head_img").attr("src")); 
															$("#head_img_div").hide();
															$("#head_img_change_div").show();
															
													    });
													    
													    $("#headSelectSure").click(function(){
													    	$.ajax({
																url : "../person/cutHeadForUser",
																cache : false,
																type : "POST",
																dataType : "json",
																contentType : "application/json", 
															    data: JSON.stringify({
												                	w:  $("#head_cut_w").val(),
												                	h:  $("#head_cut_h").val(),
												                	x1: $("#head_cut_x1").val(),
												                	y1: $("#head_cut_y1").val(),
												                	sw: $("#ferret").width(),
												                	sh: $("#ferret").height(),
												                	uploadFileName: $("#ferret").attr("src") 
												                }),
																success : function(result) {  
																	$('#ferret').imgAreaSelect("hide", true);
																	$("#user_head_img").attr("src", "${ctx}/" + result.message); 
															    	$("#head_img_div").show();
																	$("#head_img_change_div").hide();  
																},
																error : function() {
																	 
																},
																complete : function(){ 
																	 
																}
															}); 
													    	
													    	
													    }); 
													    
													    $("#testSize").load(function(){
													    	if($("#testSize").width() <= 300 && $("#testSize").height() <= 300){
										                		$("#ferret").attr("src", $("#testSize").attr("src"));
																$("#showTest").attr("src",$("#testSize").attr("src")); 
										                	}else{
										                		showErrorDialog("请选择图片尺寸不大于300X300的");
										                	} 
													    });
													    function selectEnd(img, selection){ 
													    	$("#head_cut_w").val(selection.x2 - selection.x1);
										                	$("#head_cut_h").val(selection.y2 - selection.y1);
										                	$("#head_cut_x1").val(selection.x1);
										                	$("#head_cut_y1").val(selection.y1); 
													    }
													    $("#headSelectInput").change(function(){  
													    	$.ajaxFileUpload
													        ({
												                url:'../person/uploadUserHead',
												                secureuri:false,
												                fileElementId:'headSelectInput',
												                type : "POST",
												                dataType: 'json',  
												                data:{
												                	uploadFileName:  $("#headSelectInput").val()
												                },
												                success: function (data, status)
												                {   
												                	var result = $.parseJSON($(data).html());
												                	  
												                	$("#testSize").attr("src", "${ctx}/" + result.message); 
												                	
												                },
												                error: function(result){
												                	showErrorDialog("上传失败");
												                }
												            });  
													    	$("#ferret").attr("src", $("#testImg").val());
															$("#showTest").attr("src", $("#testImg").val());  
													    });
													    
													    $('#ferret').imgAreaSelect({ 
													    	aspectRatio: '1:1',  
													    	onSelectChange: preview,
													    	onSelectEnd: selectEnd});

													   
													});
													</script>
												</div>
												</td>
										</tr>
										<tr>
												<td width="180"><span class="dt">名</span></td>
												<td><input data-bind="value: personInfo().lastName, event:{change: hitFamilyForUser}"  class="txt-input" type="text" /></td>
										</tr>
										<tr>
												<td width="180"><span class="dt">昵称</span></td>
												<td><input data-bind="value: personInfo().nick"  class="txt-input" type="text" /></td>
										</tr>
										<tr>
												<td><span class="dt">性别</span></td>
												<td colspan="2"><label class="radiolab" ><input data-bind="checked: personInfo().sex, event:{change: hitFamilyForUser}"  value="1" class="radio" type="radio" >男</label>&nbsp;&nbsp;<label class="radiolab"><input data-bind="checked: personInfo().sex, event:{change: hitFamilyForUser}"  value="2" class="radio" type="radio">女</label></td>
										</tr>
										<tr>
												<td><span class="dt">出生日期</span></td>
												<td colspan="2"><input class="form-control" id="personInfo_birthDay" style="width:150px;" placeholder="" data-bind="value: personInfo().birthDay"></td>
										</tr>
								</table>
								<div class="hr"></div>
								<table class="formtable" width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
												<td width="180"><span class="dt">户口所在地</span></td> 
												<td colspan="2">
													<div class="row">
														<select style="width:100px;margin-left:5px" class="form-control pull-left"   data-bind="options: areaSheng, value: personInfo().areaSheng, optionsText: 'name',  optionsCaption: '' ,event:{change: areaShengChange}"></select>
														<select style="width:100px;margin-left:5px" class="form-control pull-left" data-bind="options: areaShi, value: personInfo().areaShi, optionsText: 'name',  optionsCaption: '' ,event:{change: areaShiChange}"></select>
														<select style="width:100px;margin-left:5px" class="form-control pull-left" data-bind="options: areaXian, value: personInfo().areaXian, optionsText: 'name',  optionsCaption: '' ,event:{change: areaXianChange}"></select>
														<select style="width:100px;margin-left:5px" class="form-control pull-left" data-bind="options: areaXiang, value: personInfo().areaXiang, optionsText: 'name',  optionsCaption: '' "></select>
													</div>
												</td>
										</tr> 
										<tr>
												<td><span class="dt">联系方式</span></td>
												<td colspan="2"><input data-bind="value: personInfo().phone, event:{change: hitFamilyForUser}" class="txt-input" type="text" /></td>
										</tr>
										<tr>
												<td><span class="dt">电子邮箱</span></td>
												<td colspan="2"><input data-bind="value: personInfo().email, event:{change: hitFamilyForUser}" class="txt-input" type="text" /></td>
										</tr>
								</table>
								<div class="hr"></div>
								<table class="formtable" width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
												<td><span class="dt">证件</span></td>
												<td><select class="select input-200"><option>身份证</option></select>&nbsp;&nbsp;<input data-bind="value: personInfo().cid, event:{change: hitFamilyForUser}" class="txt-input" type="text" /></td>
										</tr> 
										<tr>
												<td>&nbsp;</td>
												<td><br><button class="btn-save" type="button" data-bind="click: savePersonInfo">保存</button></td>
										</tr>
								</table>
							 </div>
					 	</div>
					</div>
				</div>
			</div> 
        </div>
        <div title="个人申请" style="padding:10px">
		    <table class="easyui-datagrid" title="申请记录"  style="margin-left:5px; margin-right:5px; width:98%"
								id="user_apply_grid">
			</table> 
        </div>
         <div title="待审核申请" style="padding:10px">
		    <table class="easyui-datagrid"  title="待申请"  style="margin-left:5px; margin-right:5px; width:98%"
								id="admin_apply_grid"> 
			</table> 
        </div>
     </div>

 
     <div id="hitPerson_dlg" class="easyui-dialog" title="命中的人物信息"
		data-options="iconCls:'icon-save'"
		style="width: 500px; height: 280px; padding: 10px">
		<div class="form-group"
			style="margin-left: 20px; margin-top:10px">
			<a type="button" class="btn btn-success" data-bind="click: bindHitPerson"  data-toggle="modal"
				data-target="#" id="btn_cross_sure">申请绑定</a>  
		</div> 
	   <table class="table table-bordered table-striped table-hover" style="margin-left:5px; margin-right:5px; width:98%" id="cross_table_crossInfo">
		<thead>
			<tr>   
			    <th align="center"></th>
				<th style="" align="center">家族名称</th>  
				<td>姓名</td>
				<td>创建人</td>
				<td>性别</td>
				<td>地址</td>
				<td>电话</td>
				<td>email</td>
			</tr>
		</thead> 
		<tbody data-bind="foreach: hitPersonInfos">
			<tr> 
			    <td align="center" style="width: 27px"><input type="checkbox" value="1" data-bind="event:{change: $parent.selectHitPerson}, checked: selected"></td>
				<td align="center" data-bind="text: familyName" ></td>  
				<td align="center" data-bind="text: fullName" ></td>  
				<td align="center" data-bind="text: createPersonName" ></td>  
				<td align="center" data-bind="text: sex" ></td>  
				<td align="center" data-bind="text: areaName" ></td>  
				<td align="center" data-bind="text: phone" ></td>  
				<td align="center" data-bind="text: email" ></td> 
			</tr> 
		</tbody> 
	</table> 
</div>
<div class="footer">
		<div class="bd content center-block">
				<div class="copyright">Copyright © 2014 家族社交 All Rights Reserved</div>
		</div>
</div>
</body>
</html>
