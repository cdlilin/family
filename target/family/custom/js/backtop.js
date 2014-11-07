$(document).ready(function(){

//首先将#backTop隐藏
 $("#sifixed").hide();
//当滚动条的位置处于距顶部400像素以下时，跳转链接出现，否则消失
$(function () {
$(window).scroll(function(){
if ($(window).scrollTop()>400){
$("#sifixed").fadeIn(500);
}
else
{
$("#sifixed").fadeOut(500);
}
});

//当点击跳转链接后，回到页面顶部位置

$("#gotop").click(function(){
$('body,html').animate({scrollTop:0},500);
return false;
});
});
});