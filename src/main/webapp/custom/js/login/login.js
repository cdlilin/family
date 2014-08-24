/**
 * Created by star on 5/15/14.
 */
$(function() {  
    /**
     * 获取项目根路径
     * @returns /projectPath
     */
    function getRootPath(){
    	var _self = this;
        //获取主机地址之后的目录，如： /uimcardprj/share/meun.jsp
        var pathName = _self.location.pathname;
        
        //获取带"/"的项目名，如：/uimcardprj
        var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
        return projectName;
    };
    
    
    
    var _self = this;
	var currentLocation = _self.location;	//当前页面的location
	var topLocation = top.location;			//当前页面所属的父页面的location
	
    if (topLocation != currentLocation) {
    	top.location = getRootPath()+"/login";
    }
	

    App.init();
	App.initUniform(); 
   
    var loginModel = new LoginModel();
    ko.applyBindings(loginModel);  

    loginModel.LoginModelInit();    
   
}); 

function LoginModel(){
	 self = this; 
	 
	 self.userName = ko.observable("test");  
	 
	 self.userNameValidate = ko.observable(false);
	 
	 self.password = ko.observable("test");
	 
	 self.passwordValidate = ko.observable(false);
	 
	 self.loginErrorMessage = ko.observable("");
	 
	 
	 self.LoginModelInit = function(){
//		 $("#loginForm").submit(function() { 
//		        $("input[name='username']").val(self.userName());
//		        $("input[name='password']").val(self.password());
//		    });  
	 };
	 
	 self.passwordKeydown = function(e) { 
//	        if (e.keyCode == 13) {
//	            e.preventDefault(); 
//	            $("#loginForm").submit();
//	        }
	  }; 
	 
	 self.validateUserName = function(){
		 if(self.userName() == null || self.userName() == ""){
			 self.userNameValidate(true);
			 return false;
		 }else{
			 self.userNameValidate(false);
			 return true;
		 } 
	 };
	 
	 self.validatePassword = function(){
		 if(self.password() == null || self.password() == ""){
			 self.passwordValidate(true);
			 return false;
		 }else{
			 self.passwordValidate(false); 
			 return true;
		 }
		 
	 };
	 
	 self.loginSubmit = function(){  
		 if(self.validatePassword() && self.validateUserName()){ 
//			 $("#loginForm").submit();
			$.ajax({
					url : "login",
					cache : false,
					type : "POST",
					dataType : "json",
					contentType : "application/json", 
					data : JSON.stringify({
						userName: self.userName(),
						password: self.password()
					}),
					success : function(result) {  
						if (result != null && result != "undefind" && result.code == "0") {  
							 top.location = "index";
						}else{
							self.loginErrorMessage(result.message);
						}  
					},
					error : function() {
						 
					},
					complete : function(){ 

					}
				});
			 
		 }
		 
	 };
} 







