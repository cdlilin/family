<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.LockedAccountException "%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8" />
    <title>欢迎登录</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <meta http-equiv="refresh" content="0.2;url=${url}">

   <!-- BEGIN GLOBAL MANDATORY STYLES -->          
   <link href="${ctx}/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
   <link href="${ctx}/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
   <!-- END GLOBAL MANDATORY STYLES -->
   
   <!-- BEGIN PAGE LEVEL PLUGIN STYLES --> 
   <link href="${ctx}/assets/plugins/fancybox/source/jquery.fancybox.css" rel="stylesheet" /> 
   <link href="${ctx}/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>   
   <!-- END PAGE LEVEL PLUGIN STYLES -->

   <!-- BEGIN THEME STYLES --> 
   <link href="${ctx}/assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
   <link href="${ctx}/assets/css/style.css" rel="stylesheet" type="text/css"/>
   <link href="${ctx}/assets/css/pages/page404.css" rel="stylesheet" type="text/css"/>
   <link href="${ctx}/assets/css/themes/blue.css" rel="stylesheet" type="text/css" id="style_color"/>
   <link href="${ctx}/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
   <link href="${ctx}/assets/css/custom.css" rel="stylesheet" type="text/css"/>
   <!-- END THEME STYLES -->

   <link rel="shortcut icon" href="favicon.ico" />
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body>
    <!-- BEGIN STYLE CUSTOMIZER -->
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
    <!-- END HEADER -->

    <!-- BEGIN PAGE CONTAINER -->  
    <div class="page-container"> 
        <!-- END BREADCRUMBS -->

        <!-- BEGIN CONTAINER -->   
        <div class="container container margin-top-240 margin-bottom-240">
          <div class="row">
            <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 login-signup-page">
                    <h2>${message}</h2> 
                    <br>
                                                                      如果你的浏览器不支持跳转请直接点击:<a href="${url}">跳转</a>
                       
            </div>
          </div>
        </div>
        <!-- END CONTAINER -->

  </div>
    <!-- END PAGE CONTAINER -->  

    <!-- BEGIN FOOTER -->
    <div class="footer">
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-sm-4 space-mobile">
                    <!-- BEGIN ABOUT -->                    
                    <h2>关于我们</h2>
                    <p class="margin-bottom-30">我们致力于创建全球值得信奈的完整家谱，希望可以通过我们的努力，您可以轻松的找到自己的家谱</p>
                    <div class="clearfix"></div>                    
                    <!-- END ABOUT -->          

                    <h2>家谱展示</h2>
                    <!-- BEGIN BLOG PHOTOS STREAM -->
                    <div class="blog-photo-stream margin-bottom-30">
                        <ul class="list-unstyled">
                            <li><a href="#"><img src="assets/img/people/img5-small.jpg" alt=""></a></li>
                            <li><a href="#"><img src="assets/img/works/img1.jpg" alt=""></a></li>
                            <li><a href="#"><img src="assets/img/people/img4-large.jpg" alt=""></a></li>
                            <li><a href="#"><img src="assets/img/works/img6.jpg" alt=""></a></li>
                            <li><a href="#"><img src="assets/img/pics/img1-large.jpg" alt=""></a></li>
                            <li><a href="#"><img src="assets/img/pics/img2-large.jpg" alt=""></a></li>
                            <li><a href="#"><img src="assets/img/works/img3.jpg" alt=""></a></li>
                            <li><a href="#"><img src="assets/img/people/img2-large.jpg" alt=""></a></li>
                            <li><a href="#"><img src="assets/img/works/img2.jpg" alt=""></a></li>
                            <li><a href="#"><img src="assets/img/works/img5.jpg" alt=""></a></li>
                        </ul>                    
                    </div>
                    <!-- END BLOG PHOTOS STREAM -->                              
                </div>
                <div class="col-md-4 col-sm-4 space-mobile">
                    <!-- BEGIN CONTACTS -->                                    
                    <h2>我们的联系地址</h2>
                    <address class="margin-bottom-40">
                                                                                      中国 <br />
                                                                                      成都市<br />
                                                                                     荆翠中路88号 <br />
                                                                                    电话: (028) 110 <br />
                                                                                    邮箱: <a href="mailto:llaysz@163.com">llaysz@163.com</a>                        
                    </address>
                    <!-- END CONTACTS -->                                    

                    <!-- BEGIN SUBSCRIBE -->                                    
                    <h2>Monthly Newsletter</h2>  
                    <p>Subscribe to our newsletter and stay up to date with the latest news and deals!</p>
                    <form action="#" class="form-subscribe">
                        <div class="input-group input-large">
                            <input class="form-control" type="text">
                            <span class="input-group-btn">
                                <button class="btn theme-btn" type="button">SUBSCRIBE</button>
                            </span>
                        </div>
                    </form>

                    <!-- END SUBSCRIBE -->                                    
                </div>
                <div class="col-md-4 col-sm-4">
                    <!-- BEGIN TWITTER BLOCK -->                                                    
                    <h2>最新动态</h2>
                    <dl class="dl-horizontal f-twitter">
                        <dt><i class="fa fa-twitter"></i></dt>
                        <dd>
                            <a href="#">@keenthemes</a>
                            2015年X家谱网正式上线了，期待您的加入
                            <span>未来120天</span>
                        </dd>
                    </dl>                    
                    <dl class="dl-horizontal f-twitter">
                        <dt><i class="fa fa-twitter"></i></dt>
                        <dd>
                            <a href="#">@keenthemes</a>
                            Sequat ipsum dolor onec eget orci fermentum condimentum lorem sit consectetur adipiscing
                            <span>8 min ago</span>
                        </dd>
                    </dl>                    
                    <dl class="dl-horizontal f-twitter">
                        <dt><i class="fa fa-twitter"></i></dt>
                        <dd>
                            <a href="#">@keenthemes</a>
                            Remonde sequat ipsum dolor lorem sit consectetur adipiscing
                            <span>12 min ago</span>
                        </dd>
                    </dl>                    
                    <dl class="dl-horizontal f-twitter">
                        <dt><i class="fa fa-twitter"></i></dt>
                        <dd>
                            <a href="#">@keenthemes</a>
                            Pilsum dolor lorem sit consectetur adipiscing orem sequat
                            <span>16 min ago</span>
                        </dd>
                    </dl>                    
                    <!-- END TWITTER BLOCK -->                                                                        
                </div>
            </div>
        </div>
    </div>
    <!-- END FOOTER -->

    <!-- BEGIN COPYRIGHT -->
    <div class="copyright"> 
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <p>
                        <span class="margin-right-10">2013 © Metronic. ALL Rights Reserved.</span> 
                        <a href="#">Privacy Policy</a> | <a href="#">Terms of Service</a>
                    </p>
                </div>
                <div class="col-md-4">
                    <ul class="social-footer">
                        <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                        <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                        <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                        <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                        <li><a href="#"><i class="fa fa-skype"></i></a></li>
                        <li><a href="#"><i class="fa fa-github"></i></a></li>
                        <li><a href="#"><i class="fa fa-youtube"></i></a></li>
                        <li><a href="#"><i class="fa fa-dropbox"></i></a></li>
                    </ul>                
                </div>
            </div>
        </div>
    </div>
    <!-- END COPYRIGHT -->
    <!-- Load javascripts at bottom, this will reduce page load time -->
    <!-- BEGIN CORE PLUGINS(REQUIRED FOR ALL PAGES) -->
    <!--[if lt IE 9]>
    <script src="${ctx}/assets/plugins/respond.min.js"></script>  
    <![endif]-->  
    <script src="${ctx}/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="${ctx}/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
    <script src="${ctx}/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>      
    <script type="text/javascript" src="${ctx}/assets/plugins/hover-dropdown.js"></script>
    <script type="text/javascript" src="${ctx}/assets/plugins/back-to-top.js"></script>    
	<script src="${ctx}/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript" ></script>
    <!-- END CORE PLUGINS -->

    <!-- BEGIN PAGE LEVEL JAVASCRIPTS(REQUIRED ONLY FOR CURRENT PAGE) -->
    <script type="text/javascript" src="${ctx}/assets/plugins/fancybox/source/jquery.fancybox.pack.js"></script>
    <script src="${ctx}/assets/plugins/knockout.js"></script>  
   

    <script src="${ctx}/assets/scripts/app.js"></script>  
    <script src="${ctx}/custom/js/login/login.js"></script>  
    
    <!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>