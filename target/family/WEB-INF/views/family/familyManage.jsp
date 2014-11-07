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
                    
                   <!--  
                   
                   <li class="dropdown">
                        <a class="dropdown-toggle">
                            	家族广场
                        </a>
                    </li>
                   <li class="menu-search">
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

						<li  class="active">
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
						<li>
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
						    <div class="col-xs-6 col-sm-10"> 
								<table id="person_tree_table" title="家族成员列表" class="easyui-treegrid" style="width:100%;height:250px">
								</table>  
							</div>   
					    </div>
					    
						<div id="person_grid_menu" class="easyui-menu" style="width:120px;">
							<div data-bind="click: addParent" data-options="iconCls:'icon-add'">添加父节点</div>
							<div data-bind="click: addChildren"  data-options="iconCls:'icon-add'">添加子节点</div>
							<!-- <div data-bind="click: addPN"  data-options="iconCls:'icon-add'">添加兄弟节点</div> -->
							<div data-bind="click: addSpouse"  data-options="iconCls:'icon-add'">添加配偶节点</div> 
						</div>
	
					    <div id="person_info_dlg" class="easyui-dialog" title="用户基本信息"
							   data-options="iconCls:'icon-save'" style="width: 850px; height: 450px; padding: 10px">
								<div class="row"> 
									  <div class="pull-left" style="width: 50%">
										    <div id="head_img_div" style="display: none">
										        <p  style="text-align:center">
										  	 	   <img id="user_head_img" data-bind="attr:{src: '${ctx}/custom/images/avatar'  + (newPersonInfo().sex() == null ? '1' : newPersonInfo().sex()) + '.png'}"/>
										  	 	</p>
										   		<p style="text-align:center"><button type="button" id="changeHead">更改头像</button></p> 
										   </div>
										   <div id="swfContainer_div">
												<p id="swfContainer">
								                                                                             本组件需要安装Flash Player后才可使用，请从<a href="http://www.adobe.com/go/getflashplayer">这里</a>下载安装。
												</p>
												<p style="text-align:center"><a type="button" class="btn btn-xs btn-pink disabled" id="upload"  >确认保存头像</a></p>
											</div>
										</div>
									    <div class="pull-left" style="width: 48%">
										   <form class="form-horizontal" role="form">
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="form-field-1">姓</label>
													<div class="col-sm-9">
														<input type="text" data-bind="value: newPersonInfo().firstName" placeholder="姓" class="col-xs-10 col-sm-5" />
													</div>
												</div>  
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="form-field-1">名</label>
													<div class="col-sm-9">
														<input type="text" id="form-field-1" data-bind="value: newPersonInfo().lastName" placeholder="名" class="col-xs-10 col-sm-5" />
													</div>
												</div>   
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right">性别 </label> 
													<div class="col-sm-9">
														<label class="inline">
															<input name="form-field-radio" value="1" type="radio" class="ace" data-bind="checked: newPersonInfo().sex"  />
															<span class="lbl"> 男</span>
														</label>

														&nbsp; &nbsp; &nbsp;
														<label class="inline">
															<input name="form-field-radio" value="2" type="radio" class="ace" data-bind="checked: newPersonInfo().sex"   />
															<span class="lbl"> 女</span>
														</label>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="form-field-comment">个人简介</label>

													<div class="col-sm-9">
														<textarea id="form-field-comment" data-bind="value: newPersonInfo().profile"></textarea>
													</div>
												</div>
												<div class="clearfix ">
													<div class="col-md-offset-3 col-md-9">
														<button class="btn btn-info" type="button" data-bind="click: savePersonNode" >
															<i class="icon-ok "></i>
															保存
														</button>
			
														&nbsp; &nbsp; &nbsp;
														<button class="btn" type="reset">
															<i class="icon-undo"></i>
															重置
														</button>
													</div>
												</div>
									    </form> 
									   </div>
									    </div>		 
							  </div>
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

		<script src="${ctx}/massets/js/jquery-2.0.3.min.js"></script>

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
        <script type="text/javascript" src="${ctx}/custom/js/family/familyManage.js"></script>  
        
        <script type="text/javascript" src="${ctx}/assets/fullAvatarEditor-2.0/scripts/swfobject.js"></script>
        <script type="text/javascript" src="${ctx}/assets/fullAvatarEditor-2.0/scripts/fullAvatarEditor.js"></script>
        <script src="assets/scripts/app.js"></script>
	    <script type="text/javascript">
	        jQuery(document).ready(function() {
	            App.init();    
	            App.initBxSlider();
	        });
	    </script>
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
		 var swf = null;
		$("#changeHead").click(function(){ 
			$("#head_img_div").hide();
			$("#swfContainer_div").show();
		});
		$("#upload").click(function(){ 
			swf.call("upload");
		});
		swfobject.addDomLoadEvent(function () {
			swf = new fullAvatarEditor("swfContainer",300, 450, {
				    id: 'swfContainer',
					upload_url: 'person/uploadUserHead',
					tab_visible : false,				 
			        button_visible : true,				 
			        src_upload : 2,						 
			        checkbox_visible : false,			 
			        browse_box_align : 38,				
					webcam_box_align : 38,				
					avatar_sizes : '100*100',			
					avatar_sizes_desc :'100*100的照片',	
					avatar_intro : '最终得到照片',
					avatar_tools_visible:false
				}, function (msg) {
					switch(msg.code)
					{
						case 1 : 
							break;
						case 2 :  
							$("#upload").removeClass("disabled"); 
							break;
						case 3 : 
						break;
						case 5 : 
							if(msg.type == 0)
							{
								$("#user_head_img").attr("src", "${ctx}/" + msg.content.sourceUrl);
								$("#swfContainer_div").hide();
								$("#upload").addClass("disabled"); 
								$("#head_img_div").show();
							}
						break;
					}
				}
			); 
        });
						 
	</script>
	<!-- <div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div> -->
</body>
</html>
