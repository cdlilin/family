var delSpouse  = null;
$(function() {  
  var appModel = new AppModel();
    ko.applyBindings(appModel);   
    appModel.init();  
    delSpouse = appModel.delSpouse;
});  
 

function AppModel(){
	var self = this;
	self.families = ko.observableArray(); 
	self.currentFamily = ko.observable(new familyInfo()); 
	self.personInfo = ko.observable(new PersonInfo());
	self.newPersonInfo = ko.observable(new PersonInfo());
	self.areaSheng = ko.observableArray(); 
	self.areaShi = ko.observableArray(); 
	self.areaXian = ko.observableArray(); 
	self.areaXiang = ko.observableArray(); 
	 
	self.init = function(){ 
//		self.loadFamily(); 
		self.loadFamilyPerson();
		$('#family_create_dlg').dialog("close") ;
		$("#person_info_dlg").dialog("close");
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
	
	self.refashFamilyTree = function(){
		$("#chart").html("");
		$(self.currentFamily().familyTree()).jOrgChart({
            chartElement : '#chart',
            dragAndDrop  : true
        });
	}; 
	self.addPN = function(row){
		console.log(row);
	};
	self.addParent = function(row){ 
		console.log(self.personInfo())
		if(self.personInfo().parentId() == 0){
			self.showPersonInfoDlg ();
			self.newPersonInfo(new PersonInfo({sex: "1",familyId: self.personInfo().familyId(),parentId: 0, relationShipType: 1}));
		}else{
			$.messager.alert("提示", "当前人物已有父节点无法添加");
		} 
	};
	self.addChildren = function(){ 
		self.showPersonInfoDlg();
		self.newPersonInfo(new PersonInfo({sex: "1", familyId: self.personInfo().familyId(),parentId: self.personInfo().personId(), relationShipType: 2}));
	};
	
	self.showPersonInfoDlg = function(parentId,relationShipType){
		$("#person_info_dlg").dialog("open");
	};
	self.addSpouse = function(row){ 
		self.showPersonInfoDlg();
		self.newPersonInfo(new PersonInfo({sex: "1", familyId: self.personInfo().familyId(),parentId: self.personInfo().personId(), relationShipType: 3}));
	};
	
	self.delSpouse = function(familyId, personId, parentId){
		$.ajax({
			url : "person/delSpouse",
			cache : false,
			type : "POST",
			dataType : "json",
			contentType : "application/json", 
			data: JSON.stringify({  
				familyId: familyId,
				personId: personId 
			}),
			success : function(result) {  
				if (result != null && result != "undefind" && result.code == "0") {  
					var currNode = $('#person_tree_table').treegrid('find', parentId);
					for(var i = 0; i < currNode.spouses.length; i++){
						if(currNode.spouses[i].personId == personId){
							currNode.spouses.splice(i, 1);
							break;
						}
					} 
			 		$('#person_tree_table').treegrid('refresh', currNode.personId);
				};  
			},
			error : function() {
				 
			},
			complete : function(){ 

			}
		}); 
	};
	
	self.onContextMenu = function(e, row){
		e.preventDefault();
		$(this).treegrid('select', row.personId);
		self.personInfo(new PersonInfo(row));
		$('#person_grid_menu').menu('show',{
			left: e.pageX,
			top: e.pageY
		});
	};
	
	self.loadFamilyPerson = function(){ 
		
		$('#person_tree_table').treegrid({
		    url: "getFamilyTreeForCurrentUser",
		    title: "家族成员列表",
		    method:"GET",
		    idField:'personId',
		    treeField:'fullName',
		    width:'100%',
		    height:'auto',
		    collapsible: true,
		    onContextMenu: self.onContextMenu,
		    columns:[[
		        {title:'姓名 ',field:'fullName',width:180, editor:'text' },
		        
		        {title:'配偶',field:'spouses',width:180, formatter: function(value){
		        	var str = "";
		        	$.each(value, function(i, n){
		        		str +=  n.fullName + "<a onclick='javascript:delSpouse(\"" + n.familyId + "\",\"" + n.personId + "\", \"" + n.parentId + "\")'><i class='icon-bolt'/></a>";
		        	});
		        	return str;
		        }},
		        {title:'昵称',field:'nick',width:180, editor:'text'},
		        {title:'关系',field:'relationShipType',formatter:function(value){
		        	switch(value){
		        	    case 1 :
		        	    	return "父子";
		        	    	break;
		        	    case 2 :
		        	    	return "子女";
		        	    	break;
		        	    case 3 :
		        	    	return "配偶";
		        	    	break;
		        	}
		        }, width:180, editor:'text'}, 
		        {title:'生日',field:'birthDayStr',width:180, editor:'datebox'} 
		    ]]  
		});  
	};
	self.loadFamily = function(){
		self.families.remove(function(item){
			return true;
		});
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
	self.saveFamily = function(){
		
	};
	
	self.savePersonNode = function(){ 
		$.ajax({
			url : "person/addPersonNode",
			cache : false,
			type : "POST",
			dataType : "json",
			contentType : "application/json", 
			data: JSON.stringify({ 
				firstName: self.newPersonInfo().firstName(),
				lastName: self.newPersonInfo().lastName(),
				fullName: self.newPersonInfo().fullName(),   
				familyId: self.newPersonInfo().familyId(),
				parentId: self.newPersonInfo().parentId(),
				sex: self.newPersonInfo().sex(),
				headUrl: $("#user_head_img").attr("src").substring(basePath.length + 1),
				personId: self.personInfo().personId(),
				profile: self.newPersonInfo().profile(),
				relationShipType: self.newPersonInfo().relationShipType() 
				
			}),
			success : function(result) {  
				if (result != null && result != "undefind" && result.code == "0") { 
					$('#person_tree_table').treegrid("reload");
					$.messager.alert("成功提示", "添加成员成功");
					$("#person_info_dlg").dialog("close");
					//不刷新树的添加方式
//					 switch(self.newPersonInfo().relationShipType() + ''){
//					 	case '1':
//					 		var data = [{personId: result.data.personId, fullName: self.newPersonInfo().fullName(), children: $('#person_tree_table').treegrid('getData')}];
//					 		$('#person_tree_table').treegrid('loadData', data);
//					 		break;
//					 	case '2':
//					 		console.log(result);
//					 		console.log(self.newPersonInfo().parentId());
//					 		$('#person_tree_table').treegrid('append',{
//					 			parent: self.newPersonInfo().parentId(),  // the node has a 'id' value that defined through 'idField' property
//					 			data: [{
//					 				fullName: self.newPersonInfo().fullName(),
//					 				personId: result.data.personId
//					 			}]
//					 		});
//					 		break;
//					 	case '3':
//					 		console.log(result);
//					 		var currNode = $('#person_tree_table').treegrid('getSelected');
//					 		currNode.spouses.push({
//				 				fullName: self.newPersonInfo().fullName(),
//				 				personId: result.data.personId
//				 			});
//					 		$('#person_tree_table').treegrid('refresh', currNode.personId);
//					 		break;
//					 }
				}else{
					showErrorDialog("添加成员失败");
					$("#person_info_dlg").dialog("close");
				}  
				
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
	
	self.openCreateFamilyDlg = function(row) { 
		self.currentFamily(new familyInfo());
		$('#family_create_dlg').dialog("open");
	};  
	
	self.delFamily = function(){
		
	}; 
	
	self.saveFamily = function(){ 
		$.ajax({
			url : "saveFamily",
			cache : false,
			type : "POST",
			dataType : "json",
			contentType : "application/json", 
			data: JSON.stringify({ 
				familyName: self.currentFamily().familyName() 
			}),
			success : function(result) {  
				if (result != null && result != "undefind" && result.code == "0") { 
					self.currentFamily().familyId(result.data.familyId);
					self.families.push(self.currentFamily());
					showSuccessDialog("添加家族成功");
					$('#family_create_dlg').dialog("close");
				}  
			},
			error : function() {
				 
			},
			complete : function(){ 

			}
		}); 
		
		
		
	}; 
} 
 

var PersonInfo = function(data){
	var self = this; 
	self.personId = ko.observable(data == null ? "" : data.personId);
	self.areaSheng = ko.observable(data == null ? "" : data.areaSheng);
	self.areaShi = ko.observable(data == null ? "" : data.areaShi);
	self.areaXian = ko.observable(data == null ? "" : data.areaXian);
	self.areaXiang = ko.observable(data == null ? "" : data.areaXiang);
	self.nick = ko.observable(data == null ? "" : data.nick);
	self.email = ko.observable(data == null ? "" : data.email);  
	self.birthDay = ko.observable(data == null ? "" : data.birthDay); 
	self.cid = ko.observable(data == null ? "" : data.cid); 
	self.firstName = ko.observable(data == null ? "" : data.firstName); 
	self.lastName = ko.observable(data == null ? "" : data.lastName);  
	self.phone = ko.observable(data == null ? "" : data.phone);  
	self.sex = ko.observable(data == null ? "" : data.sex);  
	self.relationShipType = ko.observable(data == null ? "" : data.relationShipType); 
	self.parentId = ko.observable(data == null ? "" : data.parentId); 
	self.familyId =  ko.observable(data == null ? "" : data.familyId); 
	self.headUrl = ko.observable(data == null ? "" : data.headUrl);
	self.profile = ko.observable(data == null ? "" : data.profile);
	
	
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
	if(person.children && person.children.length > 0){
		familyStr += "<ul id='children_" + person.personId + "'>";
		$.each(person.children, function(i, n){
			familyStr +=  getElementStr(n);
		}); 
		familyStr += "</ul>" ;
	} 
	familyStr += "<img id='" + person.personId + "' style='width:180px;height:100px;'/></li>" ; 
	return familyStr;
};   

