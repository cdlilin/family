$(function(){
	var appModel = new AppModel();
	 ko.applyBindings(appModel);   
	 appModel.init();    
});
function AppModel(){
	var self = this;
	self.regModel = ko.observable(new RegModel());
	self.init = function(){
		   App.init();
		   App.initUniform();  
		   self.regModel.errors = ko.validation.group(self.regModel);
	};
	self.regUser = function(){
		 if (self.regModel.errors && self.regModel.errors.length > 0) { 
             self.regModel.errors.showAllMessages(); 
             return;
         }
		$.ajax({
			url : "user/regUser",
			cache : false,
			type : "POST",
			dataType : "json",
			contentType : "application/json",
			data : ko.toJSON(self.regModel()),
			success : function(result) {  
				if (result != null && result != "undefind" && result.code == "0") {   
					 top.location = "user/emailRedirect/" + result.data.email;
				}else{
					 
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
	self.userName = ko.observable("").extend({ required: { message: validateMessage("用户名不能为空") } });
	self.email = ko.observable("").extend({ required: { message: validateMessage('邮箱不能为空') } });
	self.email = ko.observable("").extend({ required: { message: validateMessage('密码不能为空') } });
	self.password1 = ko.observable("").extend({ required: { message: validateMessage('密码不能为空') } });
	self.password2 = ko.observable("").extend({
		required: {message: validateMessage('请确认密码')},
        validation: { validator: function (val, other) {
            return val == other;
        }, message: validateMessage('和第一次输入不匹配'), params: self.password1 } 
    });
}

function validateMessage(alert){
	return '<i class="fa fa-warning tooltips" data-original-title="' + alert + '" data-container="body" title="' + alert + '"></i>';
}