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
    <title>Metronic Frotnend | Homepage</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />

   <!-- BEGIN GLOBAL MANDATORY STYLES -->          
   <link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
   <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
   <!-- END GLOBAL MANDATORY STYLES -->
   
   <!-- BEGIN PAGE LEVEL PLUGIN STYLES --> 
   <link href="assets/plugins/fancybox/source/jquery.fancybox.css" rel="stylesheet" />              
   <link rel="stylesheet" href="assets/plugins/revolution_slider/css/rs-style.css" media="screen">
   <link rel="stylesheet" href="assets/plugins/revolution_slider/rs-plugin/css/settings.css" media="screen"> 
   <link href="assets/plugins/bxslider/jquery.bxslider.css" rel="stylesheet" />                
   <!-- END PAGE LEVEL PLUGIN STYLES -->

   <!-- BEGIN THEME STYLES --> 
   <link href="assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
   <link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
   <link href="assets/css/themes/blue.css" rel="stylesheet" type="text/css" id="style_color"/>
   <link href="assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
   <link href="assets/css/custom.css" rel="stylesheet" type="text/css"/>
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
                <button class="navbar-toggle btn navbar-btn" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
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
                    <li class="dropdown active">
                            <a class="dropdown-toggle" href="${ctx}/index">首页 </a> 
                     </li>
                   <li class="dropdown">
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
    <!-- END HEADER -->
    <!-- BEGIN PAGE CONTAINER -->  
     <div class="page-container">
        <!-- BEGIN REVOLUTION SLIDER -->    
        <div class="fullwidthbanner-container slider-main">
            <div class="fullwidthabnner">
                <ul id="revolutionul" style="display:none;"> 
                        <li data-transition="fade" data-slotamount="8" data-masterspeed="700" data-delay="9400" data-thumb="assets/img/sliders/revolution/thumbs/thumb2.jpg">
                            <img src="assets/img/sliders/revolution/bg1.jpg" alt=""> 
                            <div class="caption lft slide_title slide_item_left"
                                 data-x="0"
                                 data-y="105"
                                 data-speed="400"
                                 data-start="1500"
                                 data-easing="easeOutExpo">
                                	不知道自己来自哪 ? 
                            </div>
                            <div class="caption lft slide_subtitle slide_item_left"
                                 data-x="0"
                                 data-y="180"
                                 data-speed="400"
                                 data-start="2000"
                                 data-easing="easeOutExpo">
                                                                                            想知道吗？
                            </div>
                            <div class="caption lft slide_desc slide_item_left"
                                 data-x="0"
                                 data-y="220"
                                 data-speed="400"
                                 data-start="2500"
                                 data-easing="easeOutExpo">
                                                                                         也许你就在我们的家谱中<br>
                                                                                         来试试吧，做一个先行者，为自己的家人留下点什么
                            </div>
                            <a class="caption lft btn green slide_btn slide_item_left" href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes"
                                 data-x="0"
                                 data-y="290"
                                 data-speed="400"
                                 data-start="3000"
                                 data-easing="easeOutExpo">
                                                                                      点击我开始寻找吧!
                            </a>                        
                            <div class="caption lfb"
                                 data-x="640" 
                                 data-y="55" 
                                 data-speed="700" 
                                 data-start="1000" 
                                 data-easing="easeOutExpo"  >
                                 <img src="assets/img/sliders/revolution/man-winner.png" alt="Image 1">
                            </div>
                        </li>  
                </ul>
                <div class="tp-bannertimer tp-bottom"></div>
            </div>
        </div> 
        <!-- END REVOLUTION SLIDER --> 
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
    <script src="assets/plugins/respond.min.js"></script>  
    <![endif]-->  
    <script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
    <script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>      
    <script type="text/javascript" src="assets/plugins/hover-dropdown.js"></script>
    <script type="text/javascript" src="assets/plugins/back-to-top.js"></script>    
    <!-- END CORE PLUGINS -->

    <!-- BEGIN PAGE LEVEL JAVASCRIPTS(REQUIRED ONLY FOR CURRENT PAGE) -->
    <script type="text/javascript" src="assets/plugins/fancybox/source/jquery.fancybox.pack.js"></script>  
    <script type="text/javascript" src="assets/plugins/revolution_slider/rs-plugin/js/jquery.themepunch.plugins.min.js"></script>
    <script type="text/javascript" src="assets/plugins/revolution_slider/rs-plugin/js/jquery.themepunch.revolution.min.js"></script> 
    <script type="text/javascript" src="assets/plugins/bxslider/jquery.bxslider.min.js"></script>
    <script src="assets/scripts/app.js"></script>
    <script src="assets/scripts/index.js"></script>    
    <script type="text/javascript">
        jQuery(document).ready(function() {
            App.init();    
            App.initBxSlider();
            Index.initRevolutionSlider();                    
        });
    </script>
    <!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>