 
$(function() {  
  var appModel = new AppModel();
    ko.applyBindings(appModel);   
    appModel.init();   
});  
 

function AppModel(){
	var self = this;
	self.talks = ko.observableArray();  
	
	self.startIndex = 0;
	self.endIndex = 20;
	self.pageSize = 20;
	
	self.userTalk = ko.observable("");
	 
	self.init = function(){  
		self.loadTalks();
	};  
	
	self.sendReply = function(row){
		console.log(row);
		self.sendUserTalk({
			talkType: 1,
			talkContent: row.replyContent(),
			repliedTalkId: row.talkId()
		});
 
	};
	
	self.sendUserTalk = function(talk){
		$.ajax({
			url : "talk/sendTalk",
			cache : false,
			type : "POST",
			dataType : "json",
			contentType : "application/json", 
			data: JSON.stringify(talk),
			success : function(result) {  
				if (result != null && result != "undefind" && result.code == "0") { 
					self.loadTalks();
				}  
			},
			error : function() {
				 
			},
			complete : function(){ 

			}
		});
	};
	 
	self.loadTalks = function(){
		self.talks.remove(function(item){
			return true;
		});
		$.ajax({
			url : "talk/getTalks",
			cache : false,
			type : "POST",
			dataType : "json",
			contentType : "application/json", 
			data: JSON.stringify({ 
				startIndex: self.startIndex,
				endIndex: self.endIndex
			}),
			success : function(result) {   
				$.each(result, function(i, n){
					self.talks.push(new TalkInfo(n));
				}); 
			},
			error : function() {
				 
			},
			complete : function(){ 

			}
		}); 
	};
	
	self.openCreateFamilyDlg = function(row) { 
		self.currentFamily(new familyInfo());
		$('#family_create_dlg').dialog("open");
	};   
	
	self.sendTalk = function(row){ 
		self.sendUserTalk({
			talkType: 0,
			talkContent: self.userTalk()
		});
	}; 
} 
 

var TalkInfo = function(data){
	var self = this;   
	
	self.talkId = ko.observable(data == null ? "" : data.talkId);
	self.talkContent = ko.observable(data == null ? "" : data.talkContent);
	self.userId = ko.observable(data == null ? "" : data.userId);
	self.nick = ko.observable(data == null ? "" : data.nick);
	self.createTime = ko.observable(data == null ? "" : data.createTime);
	self.modifiedTime = ko.observable(data == null ? "" : data.modifiedTime);
	self.replyCount = ko.observable(data == null ? "" : data.replyCount);  
	self.repliedTime = ko.observable(data == null ? "" : data.repliedTime); 
	self.repliedTalkId = ko.observable(data == null ? "" : data.repliedTalkId); 
	self.talkType = ko.observable(data == null ? "" : data.talkType); 
	self.agreeCount = ko.observable(data == null ? "" : data.agreeCount);   
	
	self.userHeadUrl = ko.observable(data == null ? "" : data.userHeadUrl);
	self.replyContent =  ko.observable();
};

var defaultFamilyInfo = {
		familyId:"",
		familyName:"",
		createUser:"",
		isFamilyAdmin:""
};

var familyInfo = function(data){
	var self = this; 
	 if(data == null){
		 data = defaultFamilyInfo;
	 }
	self.familyId = ko.observable(data.familyId); 
	self.familyName = ko.observable(data.familyName); 
	self.createUser = ko.observable(data.createUser); 
	self.isFamilyAdmin = ko.observable(data.isFamilyAdmin); 
	 
	self.familyTree = ko.observable();
};

var getElementStr = function(person){  
	var familyStr = "<li id='person_" + person.personId + "'>" + person.fullName;
	if(person.children.length > 0){
		familyStr += "<ul id='children_" + person.personId + "'>";
		$.each(person.children, function(i, n){
			familyStr +=  getElementStr(n);
		}); 
		familyStr += "</ul>" ;
	} 
	familyStr += "<img id='" + person.personId + "' style='width:180px;height:100px;'/></li>" ; 
	return familyStr;
};   

