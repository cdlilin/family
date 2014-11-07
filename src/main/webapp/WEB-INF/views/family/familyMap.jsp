<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.LockedAccountException "%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>用户信息页面 - Bootstrap后台管理系统模版Ace下载</title>
		<meta name="keywords" content="Bootstrap模版,Bootstrap模版下载,Bootstrap教程,Bootstrap中文" />
		<meta name="description" content="站长素材提供Bootstrap模版,Bootstrap教程,Bootstrap中文翻译等相关Bootstrap插件下载" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		<link href="${ctx}/massets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctx}/massets/css/font-awesome.min.css" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="${ctx}/massets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<link rel="stylesheet" href="${ctx}/massets/css/jquery-ui-1.10.3.custom.min.css" />
		<link rel="stylesheet" href="${ctx}/massets/css/jquery.gritter.css" />
		<link rel="stylesheet" href="${ctx}/massets/css/select2.css" />
		<link rel="stylesheet" href="${ctx}/massets/css/bootstrap-editable.css" />
		<link href="${ctx}/assets/easyui/themes/metro/easyui.css" rel="stylesheet">
		<%-- <link href="${ctx}/assets/easyui/themes/icon.css" rel="stylesheet"> --%>

		<!-- fonts -->

		<!-- <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" /> -->
		<!-- ace styles -->

		<link rel="stylesheet" href="${ctx}/massets/css/ace.min.css" />
		<link rel="stylesheet" href="${ctx}/massets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${ctx}/massets/css/ace-skins.min.css" /> 
		 

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="${ctx}/massets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->

		<script src="${ctx}/massets/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="${ctx}/massets/js/html5shiv.js"></script>
		<script src="${ctx}/massets/js/respond.min.js"></script>
		
		<![endif]-->
		<link rel="stylesheet" href="${ctx}/assets/jorgchart/css/jquery.jOrgChart.css"/>
        <link rel="stylesheet" href="${ctx}/assets/jorgchart/css/custom.css"/>
        
		<link rel="stylesheet" href="${ctx}/massets/css/custom.css" />
		<script type="text/javascript">
		 var basePath = "${ctx}";
		</script>
		 
	</head>

	<body>
		<div class="color-panel hidden-sm">
        <div class="color-mode-icons icon-color"></div>
        <div class="color-mode-icons icon-color-close"></div>
        <div class="color-mode">
            <p>主题颜色</p>
            <ul class="inline">
                <li class="color-blue current color-default" data-style="blue"></li>
                <li class="color-red" data-style="red"></li>
                <li class="color-green" data-style="green"></li>
                <li class="color-orange" data-style="orange"></li>
            </ul>
            <label>
                <span>Header</span>
                <select class="header-option form-control input-small">
                    <option value="default" selected>Default</option>
                    <option value="fixed">Fixed</option>
                </select>
            </label>
        </div>
    </div>
    <!-- END BEGIN STYLE CUSTOMIZER -->   

    <!-- BEGIN HEADER -->
    <div class="header navbar navbar-default navbar-static-top">
        <!-- BEGIN TOP BAR -->
        <div class="front-topbar">
            <div class="container">
                <div class="row">
                    <div class="col-md-9 col-sm-9">
                        <ul class="list-unstyle inline">
                            <li><i class="fa fa-phone topbar-info-icon top-2"></i>联系我们: <span>13880529348</span></li>
                            <li class="sep"><span>|</span></li>
                            <li><i class="fa fa-envelope-o topbar-info-icon top-2"></i>邮箱: <span>llaysz@163.com</span></li>
                        </ul>
                    </div>
                    <div class="col-md-3 col-sm-3 login-reg-links">
                        <ul class="list-unstyled inline">
                            <li><a href="${ctx}/login">登录</a></li>
                            <li class="sep"><span>|</span></li>
                            <li><a href="${ctx}/signup">注册</a></li>
                        </ul>
                    </div>
                </div>
            </div>        
        </div>
        <!-- END TOP BAR -->
       <div class="container">
            <div class="navbar-header">
                <!-- BEGIN RESPONSIVE MENU TOGGLER --> 
                <!-- END RESPONSIVE MENU TOGGLER -->
                <!-- BEGIN LOGO (you can use logo image instead of text)-->
                <a class="navbar-brand logo-v1" href="${ctx}/index">
                    <img src="${ctx}/assets/img/logo_blue.png" id="logoimg" alt="">
                </a>
                <!-- END LOGO -->
            </div>
        
            <!-- BEGIN TOP NAVIGATION MENU -->
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                     <li class="dropdown">
                            <a class="dropdown-toggle" href="${ctx}/index">首页 </a> 
                     </li>
                   <li class="dropdown active">
                            <a class="dropdown-toggle" href="${ctx}/user/userSpace">
                            	个人空间
                        </a> 
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle">
                            	家族广场
                        </a>
                    </li>
                   <!--  <li class="menu-search">
                        <span class="sep"></span>
                        <i class="fa fa-search search-btn"></i>

                        <div class="search-box">
                            <form action="#">
                                <div class="input-group input-large">
                                    <input class="form-control" type="text" placeholder="Search">
                                    <span class="input-group-btn">
                                        <button type="submit" class="btn theme-btn">搜索</button>
                                    </span>
                                </div>
                            </form>
                        </div> 
                    </li> -->
                </ul>                         
            </div>
            <!-- BEGIN TOP NAVIGATION MENU -->
        </div> 
    </div>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<div class="sidebar" id="sidebar">
					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					</script>

					<div class="sidebar-shortcuts" id="sidebar-shortcuts">
						<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
							<button class="btn btn-success">
								<i class="icon-signal"></i>
							</button>

							<button class="btn btn-info">
								<i class="icon-pencil"></i>
							</button>

							<button class="btn btn-warning">
								<i class="icon-group"></i>
							</button>

							<button class="btn btn-danger">
								<i class="icon-cogs"></i>
							</button>
						</div>

						<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
							<span class="btn btn-success"></span>

							<span class="btn btn-info"></span>

							<span class="btn btn-warning"></span>

							<span class="btn btn-danger"></span>
						</div>
					</div><!-- #sidebar-shortcuts -->

					<ul class="nav nav-list">
						<li >
							<a href="${ctx}/user/userSpace">
								<i class="icon-dashboard"></i>
								<span class="">个人信息</span>
							</a>
						</li>

						<li>
							<a href="${ctx}/familyManage">
								<i class="icon-text-width"></i>
								<span class="">家族维护</span>
							</a>
						</li>
                        <li >
							<a href="${ctx}/familyApply">
								<i class="icon-text-width"></i>
								<span class="">个人申请</span>
							</a>
						</li>
						<li>
							<a href="${ctx}/familyCheck">
								<i class="icon-text-width"></i>
								<span class="">入族申请</span>
							</a>
						</li>
						<li   class="active">
							<a href="${ctx}/familytree" >
								<i class="icon-dashboard"></i>
								<span class="">家族树 </span>
							</a>  						
						</li> 
					</ul><!-- /.nav-list -->

					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>

					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
				</div>

				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="${ctx}/user/userSpace">个人空间</a>
							</li>

							<li class="active">
								<a href="${ctx}/user/userSpace">个人信息</a>
							</li>
						</ul><!-- .breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- #nav-search -->
					</div>

					<div class="page-content">
						<div class="page-header">
							<h1>
								家族树维护
								<small>
									<i class="icon-double-angle-right"></i> 
								</small>
							</h1>
						</div><!-- /.page-header --> 
						<div class="row"> 
						    <div class="treebox pull-left" style="width:80%;" align="center">  
				                <div id="chart" class="orgChart" align="center"></div> 
				            </div>  
				            <div id="chartBuffe" style="display: none" ></div> 
				 
						</div> 
						 <div class="contextMenu" id="myMenu1">
					      <ul>
					        <li id="add"><i class="fa fa-refresh"></i> 新建子节点</li>
					        <li id="email"><i class="fa fa-refresh"></i>删除字节点</li>
					        <li id="save"><i class="fa fa-refresh"></i>修改子节点</li>
					      </ul>
					    </div>    
					    
					    <div id="dlg" class="easyui-dialog" title="导入对数表文件"
							data-options="iconCls:'icon-save'" >
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
	                  <!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

				 
			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->

		<%-- <script src="${ctx}/massets/js/jquery-2.0.3.min.js"></script> --%>
	    <script type="text/javascript" src="${ctx}/assets/jorgchart/jquery.min.js"></script>
		<!-- <![endif]-->

		<!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->

		<!--[if !IE]> -->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='${ctx}/massets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='${ctx}/massets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${ctx}/massets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${ctx}/massets/js/bootstrap.min.js"></script>
		<script src="${ctx}/massets/js/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="${ctx}/massets/js/excanvas.min.js"></script>
		<![endif]-->
        
		<script src="${ctx}/massets/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src="${ctx}/massets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="${ctx}/massets/js/jquery.gritter.min.js"></script>
		<script src="${ctx}/massets/js/bootbox.min.js"></script>
		<script src="${ctx}/massets/js/jquery.slimscroll.min.js"></script>
		<script src="${ctx}/massets/js/jquery.easy-pie-chart.min.js"></script>
		<script src="${ctx}/massets/js/jquery.hotkeys.min.js"></script>
		<script src="${ctx}/massets/js/bootstrap-wysiwyg.min.js"></script>
		<script src="${ctx}/massets/js/select2.min.js"></script>
		<script src="${ctx}/massets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="${ctx}/massets/js/fuelux/fuelux.spinner.min.js"></script>
		<script src="${ctx}/massets/js/x-editable/bootstrap-editable.min.js"></script>
		<script src="${ctx}/massets/js/x-editable/ace-editable.min.js"></script>
		<script src="${ctx}/massets/js/jquery.maskedinput.min.js"></script>
		<script type="text/javascript" src="${ctx}/assets/easyui/jquery.easyui.min.js"></script>

		<!-- ace scripts -->

		<script src="${ctx}/massets/js/ace-elements.min.js"></script>
		<script src="${ctx}/massets/js/ace.min.js"></script>
		<script type="text/javascript" src="${ctx}/assets/jquery.imgareaselect-0.9.10/ajaxfileupload.js"></script> 
    
        <script type="text/javascript" src="${ctx}/assets/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.min.js"></script>
	    <script type="text/javascript" src="${ctx}/assets/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.pack.js"></script>
        <script type="text/javascript" src="${ctx}/assets/scripts/knockout.js"></script> 
        
   
	    <script type="text/javascript" src="${ctx}/assets/jorgchart/jquery.contextmenu.r2.packed.js"></script>
	    <script type="text/javascript" src="${ctx}/assets/jorgchart/jquery.jOrgChart.js"></script>
	    <script type="text/javascript" src="${ctx}/custom/js/family/familytree.js"></script>  
		<!-- inline scripts related to this page -->

		<script type="text/javascript">
			jQuery(function($) {
			
				//editables on first profile page
				$.fn.editable.defaults.mode = 'inline';
				$.fn.editableform.loading = "<div class='editableform-loading'><i class='light-blue icon-2x icon-spinner icon-spin'></i></div>";
			    $.fn.editableform.buttons = '<button type="submit" class="btn btn-info editable-submit"><i class="icon-ok icon-white"></i></button>'+
			                                '<button type="button" class="btn editable-cancel"><i class="icon-remove"></i></button>';    
				
				//editables 
			    $('#username').editable({
					type: 'text',
					name: 'username'
			    });
			
			
				var countries = [];
			    $.each({ "CA": "Canada", "IN": "India", "NL": "Netherlands", "TR": "Turkey", "US": "United States"}, function(k, v) {
			        countries.push({id: k, text: v});
			    });
			
				var cities = [];
				cities["CA"] = [];
				$.each(["Toronto", "Ottawa", "Calgary", "Vancouver"] , function(k, v){
					cities["CA"].push({id: v, text: v});
				});
				cities["IN"] = [];
				$.each(["Delhi", "Mumbai", "Bangalore"] , function(k, v){
					cities["IN"].push({id: v, text: v});
				});
				cities["NL"] = [];
				$.each(["Amsterdam", "Rotterdam", "The Hague"] , function(k, v){
					cities["NL"].push({id: v, text: v});
				});
				cities["TR"] = [];
				$.each(["Ankara", "Istanbul", "Izmir"] , function(k, v){
					cities["TR"].push({id: v, text: v});
				});
				cities["US"] = [];
				$.each(["New York", "Miami", "Los Angeles", "Chicago", "Wysconsin"] , function(k, v){
					cities["US"].push({id: v, text: v});
				});
				
				var currentValue = "NL";
			    $('#country').editable({
					type: 'select2',
					value : 'NL',
			        source: countries,
					success: function(response, newValue) {
						if(currentValue == newValue) return;
						currentValue = newValue;
						
						var new_source = (!newValue || newValue == "") ? [] : cities[newValue];
						
						//the destroy method is causing errors in x-editable v1.4.6
						//it worked fine in v1.4.5
						/**			
						$('#city').editable('destroy').editable({
							type: 'select2',
							source: new_source
						}).editable('setValue', null);
						*/
						
						//so we remove it altogether and create a new element
						var city = $('#city').removeAttr('id').get(0);
						$(city).clone().attr('id', 'city').text('Select City').editable({
							type: 'select2',
							value : null,
							source: new_source
						}).insertAfter(city);//insert it after previous instance
						$(city).remove();//remove previous instance
						
					}
			    });
			
				$('#city').editable({
					type: 'select2',
					value : 'Amsterdam',
			        source: cities[currentValue]
			    }); 
			
				$('#signup').editable({
					type: 'date',
					format: 'yyyy-mm-dd',
					viewformat: 'dd/mm/yyyy',
					datepicker: {
						weekStart: 1
					}
				});
			
			    $('#age').editable({
			        type: 'spinner',
					name : 'age',
					spinner : {
						min : 16, max:99, step:1
					}
				});
				
				//var $range = document.createElement("INPUT");
				//$range.type = 'range';
			    $('#login').editable({
			        type: 'slider',//$range.type == 'range' ? 'range' : 'slider',
					name : 'login',
					slider : {
						min : 1, max:50, width:100
					},
					success: function(response, newValue) {
						if(parseInt(newValue) == 1)
							$(this).html(newValue + " hour ago");
						else $(this).html(newValue + " hours ago");
					}
				});
			
				$('#about').editable({
					mode: 'inline',
			        type: 'wysiwyg',
					name : 'about',
					wysiwyg : {
						//css : {'max-width':'300px'}
					},
					success: function(response, newValue) {
					}
				});
				
				
				
				// *** editable avatar *** //
				try {//ie8 throws some harmless exception, so let's catch it
			
					//it seems that editable plugin calls appendChild, and as Image doesn't have it, it causes errors on IE at unpredicted points
					//so let's have a fake appendChild for it!
					if( /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase()) ) Image.prototype.appendChild = function(el){}
			
					var last_gritter
					$('#avatar').editable({
						type: 'image',
						name: 'avatar',
						value: null,
						image: {
							//specify ace file input plugin's options here
							btn_choose: '修改头像',
							droppable: true,
							/**
							//this will override the default before_change that only accepts image files
							before_change: function(files, dropped) {
								return true;
							},
							*/
			
							//and a few extra ones here
							name: 'avatar',//put the field name here as well, will be used inside the custom plugin
							max_size: 110000,//~100Kb
							on_error : function(code) {//on_error function will be called when the selected file has a problem
								if(last_gritter) $.gritter.remove(last_gritter);
								if(code == 1) {//file format error
									last_gritter = $.gritter.add({
										title: '上传的不是图片!',
										text: '请选择以下类型 jpg|gif|png 的图片!',
										class_name: 'gritter-error gritter-center'
									});
								} else if(code == 2) {//file size rror
									last_gritter = $.gritter.add({
										title: '图片过大!',
										text: '只支持最大上传 100Kb!',
										class_name: 'gritter-error gritter-center'
									});
								}
								else {//other error
								}
							},
							on_success : function() {
								$.gritter.removeAll();
							}
						},
					    url: function(params) {
							// ***UPDATE AVATAR HERE*** //
							//You can replace the contents of this function with examples/profile-avatar-update.js for actual upload
							var deferred = new $.Deferred
			
							//if value is empty, means no valid files were selected
							//but it may still be submitted by the plugin, because "" (empty string) is different from previous non-empty value whatever it was
							//so we return just here to prevent problems
							var value = $('#avatar').next().find('input[type=hidden]:eq(0)').val();
							if(!value || value.length == 0) {
								deferred.resolve();
								return deferred.promise();
							}
							
							console.log($('#avatar').next().find('img'));
			
							//dummy upload
							setTimeout(function(){
								if("FileReader" in window) {
									//for browsers that have a thumbnail of selected image
									var thumb = $('#avatar').next().find('img').data('thumb');
									if(thumb) $('#avatar').get(0).src = thumb;
								}
								
								deferred.resolve({'status':'OK'});
			
								if(last_gritter) $.gritter.remove(last_gritter);
								last_gritter = $.gritter.add({
									title: '你的头像被修改!',
									text: 'Uploading to server can be easily implemented. A working example is included with the template.',
									class_name: 'gritter-info gritter-center'
								});
								
							 } , parseInt(Math.random() * 800 + 800))
			
							return deferred.promise();
						},
						
						success: function(response, newValue) {
						}
					});
				}catch(e) {}
				
				
			
				//another option is using modals
				 
			
				
			
				//////////////////////////////
				$('#profile-feed-1').slimScroll({
				height: '250px',
				alwaysVisible : true
				});
			
				$('.profile-social-links > a').tooltip();
			
				$('.easy-pie-chart.percentage').each(function(){
				var barColor = $(this).data('color') || '#555';
				var trackColor = '#E2E2E2';
				var size = parseInt($(this).data('size')) || 72;
				$(this).easyPieChart({
					barColor: barColor,
					trackColor: trackColor,
					scaleColor: false,
					lineCap: 'butt',
					lineWidth: parseInt(size/10),
					animate:false,
					size: size
				}).css('color', barColor);
				});
			  
				///////////////////////////////////////////
			
				//show the user info on right or left depending on its position
				$('#user-profile-2 .memberdiv').on('mouseenter', function(){
					var $this = $(this);
					var $parent = $this.closest('.tab-pane');
			
					var off1 = $parent.offset();
					var w1 = $parent.width();
			
					var off2 = $this.offset();
					var w2 = $this.width();
			
					var place = 'left';
					if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) place = 'right';
					
					$this.find('.popover').removeClass('right left').addClass(place);
				}).on('click', function() {
					return false;
				});
			
			
				///////////////////////////////////////////
				/* $('#user-profile-3')
				.find('input[type=file]').ace_file_input({
					style:'well',
					btn_choose:'Change avatar',
					btn_change:null,
					no_icon:'icon-picture',
					thumbnail:'large',
					droppable:true,
					before_change: function(files, dropped) {
						var file = files[0];
						if(typeof file === "string") {//files is just a file name here (in browsers that don't support FileReader API)
							if(! (/\.(jpe?g|png|gif)$/i).test(file) ) return false;
						}
						else {//file is a File object
							var type = $.trim(file.type);
							if( ( type.length > 0 && ! (/^image\/(jpe?g|png|gif)$/i).test(type) )
									|| ( type.length == 0 && ! (/\.(jpe?g|png|gif)$/i).test(file.name) )//for android default browser!
								) return false;
			
							if( file.size > 110000 ) {//~100Kb
								return false;
							}
						}
			
						return true;
					}
				})
				.end().find('button[type=reset]').on(ace.click_event, function(){
					$('#user-profile-3 input[type=file]').ace_file_input('reset_input');
				})
				.end().find('.date-picker').datepicker().next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
				 */
				
				$('.input-mask-phone').mask('99999999999'); 
			});
		</script>
		
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
						
					var imgTest = null; 
					$(function () {
						$('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
							$(this).prev().focus();
						});
					    $('<div><img id="showTest" src="${ctx}/custom/images/avatar1.png.jpg" style="position: relative;"/><div>')
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
									imgTest.cancelSelection(); 
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
					    	$("#ferret").attr("src", $("#testSize").attr("src"));
							$("#showTest").attr("src",$("#testSize").attr("src"));  
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
					    
					    imgTest = $('#ferret').imgAreaSelect({ 
								    	aspectRatio: '1:1', 
								    	instance: true,
								    	onSelectChange: preview,
								    	onSelectEnd: selectEnd  
					        });

					   
					});
					</script>
	<!-- <div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div> -->
</body>
</html>
