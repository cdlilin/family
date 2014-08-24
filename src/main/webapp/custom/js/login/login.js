/**
 * Created by star on 5/15/14.
 */
$(function() {
    $("#inputUsername").focus(); 
    
    $("#loginButton").click(function(){
    	 $("#loginForm").submit();
    });
    $("#loginForm").submit(function() {
        if($("#inputUsername").val().trim().length == 0) {
            return false;
        } 
        $("input[name='username']").val($("#inputUsername").val());
        $("input[name='password']").val($("#inputPassword").val());
    });

    $("#inputPassword").bind("keydown", function(e) {

        if (e.keyCode == 13) {
            e.preventDefault();

            $("#loginForm").submit();
        }
    });
    
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
    
    var appModel = new AppModel();
    ko.applyBindings(appModel);  

    appModel.init();    
   
}); 


function AppModel(){
	var self = this;
	self.regModel = ko.observable(new RegModel());
	self.init = function(){
		
	};
	self.regUser = function(){
		console.log(ko.toJSON(self.regModel()));
		
		$.ajax({
			url : "user/regUser",
			cache : false,
			type : "POST",
			dataType : "json",
			contentType : "application/json",
			data : ko.toJSON(self.regModel()),
			success : function(result) {  
				if (result != null && result != "undefind" && result.code == "0") {  
					 top.location = "login";
				}  
			},
			error : function() {
				 
			},
			complete : function(){ 

			}
		}); 
	};
}


function RegModel(){
	var self = this; 
	//交路列表 
	self.userName = ko.observable("");
	self.password1 = ko.observable("");
	self.password2 = ko.observable("");
}







