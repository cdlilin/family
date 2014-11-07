var updRowsOpenDlg = null;
$(function() {  
  var appModel = new AppModel();
    ko.applyBindings(appModel);   
    appModel.init();  
    updRowsOpenDlg = appModel.updRowsOpenDlg;
}); 


function AppModel(){
	var self = this;
	self.families = ko.observableArray(); 
	self.currentFamily = ko.observable(); 
	self.personInfo = ko.observable(new PersonInfo());
	self.areaSheng = ko.observableArray(); 
	self.areaShi = ko.observableArray(); 
	self.areaXian = ko.observableArray(); 
	self.areaXiang = ko.observableArray(); 
	 
	self.init = function(){ 
//		self.loadFamily(); 
		self.loadFamilyTree();
		$('#dlg').dialog("close") 
//		var str = '<ul id="org" style="display:none">'
//	              + '<li>CEO: Jack Paul<img alt="" style="width:180px;height:100px" src="">'
//	              +'<ul> <li id="beer">CTO: Tom Lee</li> <li>CIO: Mary lou <a href="http://www.gbtags.com" target="_blank"><div style="padding:5px;">about</div></a>'
//	              +'<ul><li>Manager: Alice lou</li><li><a href="http://www.gbtags.com" target="_blank">Senior Manager: Hua li</a>'
//	              +'</li> </ul> </li>'
//	              +'<li>CFO: John sall</li><li class="collapsed">VP: Rick langston'
//	              +'<ul> <li>Topdeck</li> <li>Reese\'s Cups</li> </ul> </li> </ul> </li> </ul> '; 
//		
//		 
//		 $(str).jOrgChart({
//	            chartElement : '#chart',
//	            dragAndDrop  : true
//	        }); 
//	         
	}; 
	
	self.refashFamilyTree = function(chartStr){
		$("#chart").html("");
		$(chartStr).jOrgChart({
            chartElement : '#chart',
            dragAndDrop  : true
        });
	};
	
	self.loadFamilyTree = function(row){
//		self.currentFamily(row);
		$.ajax({
			url : "getFamilyRealtionShipForCurrentUser",
			cache : false,
			type : "GET",
			dataType : "json",
			contentType : "application/json", 
			success : function(result) {  
				if (result != null && result != "undefind" && result.code == "0") {  
					self.refashFamilyTree(getElementStr(result.data));
				};  
			},
			error : function() {
				 
			},
			complete : function(){ 

			}
		}); 
	};
	self.loadFamily = function(){
		$.ajax({
			url : "getFamilyForCurrentUser",
			cache : false,
			type : "GET",
			dataType : "json",
			contentType : "application/json", 
			success : function(result) {  
				if (result != null && result != "undefind" && result.code == "0") {  
					$.each(result.data, function(i, n){ 
						self.families.push(new familyInfo(n));
					});
				};  
			},
			error : function() {
				 
			},
			complete : function(){ 

			}
		}); 
	};
	
	self.loadPersonInfo = function(){
		$.ajax({
			url : "../person/getUserPersonInfo",
			cache : false,
			type : "GET",
			dataType : "json",
			contentType : "application/json", 
			success : function(result) {  
				if (result != null && result != "undefind" && result.code == "0") { 
					self.initPersonInfo(result.data); 
				}  
			},
			error : function() {
				 
			},
			complete : function(){ 

			}
		}); 
	};
	
	self.updRowsOpenDlg = function(row) { 
		self.personInfo().parentId($(row).find("img").attr("id"));
		$('#dlg').dialog("open");
	};  
	
	self.savePersonNode = function(){ 
		$.ajax({
			url : "person/addPersonNode",
			cache : false,
			type : "POST",
			dataType : "json",
			contentType : "application/json", 
			data: JSON.stringify({ 
				firstName: self.personInfo().firstName(),
				lastName: self.personInfo().lastName(),
				fullName: self.personInfo().fullName(),   
				familyId: self.currentFamily().familyId(),
				parentId: self.personInfo().parentId(),
				relationShipType: self.personInfo().relationShipType() 
			}),
			success : function(result) {  
				if (result != null && result != "undefind" && result.code == "0") { 
					$("<div id='test_test_test' style='display:none'>").appendTo("body");
					$(self.currentFamily().familyTree()).appendTo("#test_test_test"); 
					$("<li>" + self.personInfo().fullName() + "<img src='/" + "/family/" +  result.data.headUrl + "'" + " id='" + result.data.personId + "' style='width:180px; height:100px'></li>").appendTo($("#test_test_test").find("#children_" + self.personInfo().parentId()));
					 
					console.log($("#test_test_test").html()); 
					self.currentFamily().familyTree($("#test_test_test").html());
					$("#test_test_test").remove();
					$('#dlg').dialog("close");
					self.refashFamilyTree();
				}  
			},
			error : function() {
				 
			},
			complete : function(){ 

			}
		}); 
		
		
		
	}; 
} 
 

var PersonInfo = function(){
	var self = this; 
	self.areaSheng = ko.observable();
	self.areaShi = ko.observable();
	self.areaXian = ko.observable();
	self.areaXiang = ko.observable();
	self.nick = ko.observable();
	self.email = ko.observable();  
	self.birthDay = ko.observable(); 
	self.cid = ko.observable(); 
	self.firstName = ko.observable(); 
	self.lastName = ko.observable();  
	self.phone = ko.observable();  
	self.sex = ko.observable(1);  
	self.relationShipType = ko.observable(); 
	self.parentId = ko.observable(); 
	
	self.fullName = ko.computed(function(){  
		return self.firstName() + self.lastName();
	});
	self.areaId = ko.computed(function(){
		if(self.areaXiang() != null){
			return self.areaXiang().id;
		}else if(self.areaXian() != null){
			return self.areaXian().id;
		}else if(self.areaShi() != null){
			return self.areaShi().id;
		}else if(self.areaSheng() != null){
			return self.areaSheng().id;
		}
		return 0;
	});
};

var familyInfo = function(data){
	var self = this; 
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
	familyStr += "<img src='" + (person.headUrl == null ? ('/family/' + 'custom/images/avatar1.png'): ("/family/" +  person.headUrl)) + "' id='" + person.personId + "' style='width:180px;height:100px;'/></li>" ; 
	return familyStr;
};   
 



 

 