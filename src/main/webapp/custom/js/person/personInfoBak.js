

$(function() {  
  var appModel = new AppModel();
    ko.applyBindings(appModel);   
    appModel.init();    
   
}); 


function AppModel(){
	var self = this;
	self.personInfo = ko.observable(new PersonInfo());
	self.hitPersonInfos = ko.observableArray();
	self.hitPersonInfo = ko.observable();
	self.areaSheng = ko.observableArray(); 
	self.areaShi = ko.observableArray(); 
	self.areaXian = ko.observableArray(); 
	self.areaXiang = ko.observableArray(); 
	
	self.init = function(){
		$("#hitPerson_dlg").dialog("close");
		self.loadAreaSheng(0); 
		$("#personInfo_birthDay").datepicker();   
		 
		$('#user_apply_grid').datagrid({
			url: '../apply/getApplayForUser',
			method: 'get',
			title: '待审核',
			iconCls: 'icon-save',
			width: "100%",
			height: 450,
			checkbox:true,
			columns:[[
			    {field:'ck', checkbox:true},
				{field:'familyName',title:'申请家族',width:80},
				{field:'applyValidate',title:'验证信息',width:80,align:'right'},
				{field:'isDone',title:'是否处理',width:80,align:'right'},
				{field:'applyResult',title:'处理结果',width:250}
			]], 
			toolbar:[{
				text: '删除',
				iconCls : 'icon-remove',
				handler:function(){
					var applies = $('#person_tree_table').treegrid('getSelected');
					console.log(applies);
				}
			}]
		});
		
//		$('#admin_apply_grid').datagrid({
//			url: '../apply/getApplayForFamilyAdmin',
//			method: 'get',
//			title: '个人申请',
//			iconCls: 'icon-save',
//			width: "100%",
//			height: 450,
//			checkbox:true,
//			singleSelect:false,
//			columns:[[
//			    {field:'ck', checkbox:true},
//				{field:'familyName',title:'申请家族',width:80},
//				{field:'applyValidate',title:'验证信息',width:80,align:'right'},
//				{field:'isDone',title:'是否处理',width:80,align:'right'},
//				{field:'applyResult',title:'处理结果',width:250},
//				{field:'applyUserName',title:'申请用户',width:250}
//			]], 
//			toolbar:[{
//				text: '同意',
//				iconCls : 'icon-add',
//				handler:function(){
//					var applies = $('#admin_apply_grid').treegrid('getSelections');
//					$.ajax({
//						url : "../apply/checkApples",
//						cache : false,
//						type : "POST",
//						dataType : "json",
//						contentType : "application/json", 
//						data: JSON.stringify(applies),
//						success : function(result) {  
//							$('#admin_apply_grid').treegrid("reload");
//						},
//						error : function() {
//							 
//						},
//						complete : function(){ 
//
//						}
//					}); 
//					
////					console.log(JSON.stringify(applies))
//				}
//			},'-',{
//				text: '删除',
//				iconCls : 'icon-remove',
//				handler:function(){
//					
//				}
//			}]
//		});
	 
//	var cmenu;
//	function createColumnMenu(){
//		cmenu = $('<div/>').appendTo('body');
//		cmenu.menu({
//			onClick: function(item){
//				if (item.iconCls == 'icon-ok'){
//					$('#dg').datagrid('hideColumn', item.name);
//					cmenu.menu('setIcon', {
//						target: item.target,
//						iconCls: 'icon-empty'
//					});
//				} else {
//					$('#dg').datagrid('showColumn', item.name);
//					cmenu.menu('setIcon', {
//						target: item.target,
//						iconCls: 'icon-ok'
//					});
//				}
//			}
//		});
//		var fields = $('#dg').datagrid('getColumnFields');
//		for(var i=0; i<fields.length; i++){
//			var field = fields[i];
//			var col = $('#dg').datagrid('getColumnOption', field);
//			cmenu.menu('appendItem', {
//				text: col.title,
//				name: field,
//				iconCls: 'icon-ok'
//			});
//		}
//	}
//		
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
					if(result.data != null){
						self.initPersonInfo(result.data); 
					} 
				}  
			},
			error : function() {
				 
			},
			complete : function(){ 

			}
		}); 
	};
	
	self.hitFamilyForUser = function(){ 
		self.hitPersonInfos.remove(function(){
			return true;
		});
		$.ajax({
			url : "../getFamilyByPersonInfo",
			cache : false,
			type : "POST",
			dataType : "json",
			contentType : "application/json", 
			data: JSON.stringify({
				areaId : self.personInfo().areaId(),
				firstName: self.personInfo().firstName(),
				lastName: self.personInfo().lastName(),
				fullName: self.personInfo().fullName(),
				nick: self.personInfo().nick(),
				email: self.personInfo().email(),
				phone: self.personInfo().phone(),
				cid: self.personInfo().cid(),
				sex: self.personInfo().sex(),
				birthDay: $("#personInfo_birthDay").val()
			}),
			success : function(result) {  
				if (result != null && result != "undefind" && result.code == "0") { 
					if(result.data.length > 0){
						$.each(result.data, function(i, n){
							self.hitPersonInfos.push(self.initHitPersonInfo(n));
						}); 
						$("#hitPerson_dlg").dialog({autoOpen: true, modal: false, draggable: true, resizable:true});
					}; 
				}  
			},
			error : function() {
				 
			},
			complete : function(){ 

			}
		}); 
	}; 
	self.bindHitPerson = function(){  
		$.ajax({
			url : "../apply/sendApply",
			cache : false,
			type : "POST",
			dataType : "json",
			contentType : "application/json", 
			data: JSON.stringify({
				familyId : self.hitPersonInfo().familyId(),
				personId: self.hitPersonInfo().personId(), 
				applyValidate: "我是" +  self.hitPersonInfo().fullName()
			}),
			success : function(result) {  
				if (result != null && result != "undefind" && result.code == "0") { 
					$("#hitPerson_dlg").dialog("close");
					showSuccessDialog("申请已发送，静候佳音!");
				}  
			},
			error : function() {
				 
			},
			complete : function(){ 

			}
		});
	};
	
	self.initHitPersonInfo = function(family){ 
		var hitPerson = new PersonInfo();
		var personInfo = family.person;
		var addressId = personInfo.addressId + "";  
		if(addressId.length > 0){
			$.each(self.areaSheng(), function(i, n){
				if(n.id == addressId.substring(0,2)){ 
					self.personInfo().areaSheng(n);  
					return false;
				}
			});  
		}   
		if(addressId.length > 2){ 
			self.initAreaForUpate(addressId.substring(0,2), self.areaShi, addressId.substring(0,4), hitPerson.areaShi);
		} 
		if(addressId.length > 4){ 
			self.initAreaForUpate(addressId.substring(0,4), self.areaXian, addressId.substring(0, 6), hitPerson.areaXian);
		} 
		if(addressId.length > 6){
			self.initAreaForUpate(addressId.substring(0, 6), self.areaXiang, addressId, hitPerson.areaXiang);
		}  
		hitPerson.familyId(family.familyId);
		hitPerson.familyName(family.familyName);
		hitPerson.nick(personInfo.nick);
		hitPerson.email(personInfo.email);  
		hitPerson.birthDay(personInfo.birthDayStr); 
		hitPerson.cid(personInfo.cid); 
		hitPerson.firstName(personInfo.firstName); 
		hitPerson.lastName(personInfo.lastName);  
		hitPerson.phone(personInfo.phone);  
		hitPerson.sex(personInfo.sex);  
		hitPerson.personId(personInfo.personId);
		return hitPerson;
	};
	
	self.selectHitPerson = function(row){
		if(row.selected() == 0){ 
			if(self.hitPersonInfo() != null && self.hitPersonInfo().personId() != row.personId()){
				self.hitPersonInfo().selected(0);
			}
			self.hitPersonInfo(row);
		}  
	}; 
	
	self.initPersonInfo = function(personInfo){ 
		var addressId = personInfo.addressId + "";  
		if(addressId.length > 0){
			$.each(self.areaSheng(), function(i, n){
				if(n.id == addressId.substring(0,2)){ 
					self.personInfo().areaSheng(n);  
					return false;
				}
			});  
		}   
		if(addressId.length > 2){ 
			self.initAreaForUpate(addressId.substring(0,2), self.areaShi, addressId.substring(0,4), self.personInfo().areaShi);
		} 
		if(addressId.length > 4){ 
			self.initAreaForUpate(addressId.substring(0,4), self.areaXian, addressId.substring(0, 6), self.personInfo().areaXian);
		} 
		if(addressId.length > 6){
			self.initAreaForUpate(addressId.substring(0, 6), self.areaXiang, addressId, self.personInfo().areaXiang);
		}  
 
		self.personInfo().nick(personInfo.nick);
		self.personInfo().email(personInfo.email);  
		self.personInfo().birthDay(personInfo.birthDayStr); 
		self.personInfo().cid(personInfo.cid); 
		self.personInfo().firstName(personInfo.firstName); 
		self.personInfo().lastName(personInfo.lastName);  
		self.personInfo().phone(personInfo.phone);  
		self.personInfo().sex(personInfo.sex);  
		self.personInfo().headUrl(personInfo.headUrl);  
		self.personInfo().personId(personInfo.personId);  
	};
	
	self.initAreaForUpate = function(id, c, checkedId, targetObj){
		$.ajax({
			url : "../area/" + id + "/getAreaByParentId",
			cache : false,
			type : "GET",
			dataType : "json",
			contentType : "application/json", 
			success : function(result) {  
				if (result != null && result != "undefind" && result.code == "0") {  
					$.each(result.data, function(i, n){ 
						var areaInfo = new AreaInfo(n);
						c.push(areaInfo);
						if(checkedId == areaInfo.id){ 
							targetObj(areaInfo); 
						}
					});
				};  
			},
			error : function() {
				 
			},
			complete : function(){ 

			}
		}); 
	}; 
	 
	
	self.getAreaByParentId = function(row, c, checkedId){ 
		$.ajax({
			url : "../area/" + row.id + "/getAreaByParentId",
			cache : false,
			type : "GET",
			dataType : "json",
			contentType : "application/json", 
			success : function(result) {  
				if (result != null && result != "undefind" && result.code == "0") { 
					row.loadArea(result.data); 
					$.each(row.subAreas(), function(i, n){ 
						c.push(n);
						if(checkedId == n.id){ 
							targetObj(n); 
						}
					});
				};  
			},
			error : function() {
				 
			},
			complete : function(){ 

			}
		}); 
	};  
	
	self.areaShengChange = function(){   
		self.areaShi.remove(function(item){
			return true;
		});
		if(self.personInfo().areaSheng() == null || self.personInfo().areaSheng().subAreas().length <= 0){ 
			self.getAreaByParentId(self.personInfo().areaSheng(), self.areaShi);
		}else{ 
			$.each(self.personInfo().areaSheng().subAreas(), function(i, n){
				self.areaShi.push(n);
			});
		};  
	};
	self.areaShiChange = function(){   
		self.areaXian.remove(function(item){
			return true;
		});
		if(self.personInfo().areaShi() == null || self.personInfo().areaShi().subAreas().length <= 0){ 
			self.getAreaByParentId(self.personInfo().areaShi(), self.areaXian);
		}else{ 
			$.each(self.personInfo().areaShi().subAreas(), function(i, n){
				self.areaShi.push(n);
			});
		};    
	};
	self.areaXianChange = function(){  
		self.areaXiang.remove(function(item){
			return true;
		});
		if(self.personInfo().areaXian() == null || self.personInfo().areaXian().subAreas().length <= 0){ 
			self.getAreaByParentId(self.personInfo().areaXian(), self.areaXiang);
		}else{ 
			$.each(self.personInfo().areaXian().subAreas(), function(i, n){
				self.areaShi.push(n);
			});
		}    
	}; 
	
	self.loadAreaSheng = function(areaId){
		$.ajax({
			url : "../area/" + areaId + "/getAreaByParentId",
			cache : false,
			type : "GET",
			dataType : "json",
			contentType : "application/json", 
			success : function(result) {  
				if (result != null && result != "undefind" && result.code == "0") {   
					$.each(result.data, function(i, n){
						self.areaSheng.push(new AreaInfo(n, 1)); 
					}) ; 
				} ; 
			},
			error : function() {
				 
			},
			complete : function(){ 
				self.loadPersonInfo();
			}
		}); 
	}; 
	 
	
	self.savePersonInfo = function(){  
		$.ajax({
			url : "../person/" + self.personInfo().personId() == null ? "addPerson": "modifyPerson",
			cache : false,
			type : "POST",
			dataType : "json",
			contentType : "application/json", 
			data: JSON.stringify({
				personId: self.personInfo().personId(),
				areaId : self.personInfo().areaId(),
				firstName: self.personInfo().firstName(),
				lastName: self.personInfo().lastName(),
				fullName: self.personInfo().fullName(),
				nick: self.personInfo().nick(),
				email: self.personInfo().email(),
				phone: self.personInfo().phone(),
				cid: self.personInfo().cid(),
				sex: self.personInfo().sex(),
				birthDay: $("#personInfo_birthDay").val(),
				headUrl: $("#user_head_img").attr("src").substring(basePath.length + 1)
			}),
			success : function(result) {  
				if (result != null && result != "undefind" && result.code == "0") { 
					
					showSuccessDialog("保存用户信息成功!");
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
	self.selected = ko.observable(0);
	self.areaSheng = ko.observable();
	self.areaShi = ko.observable();
	self.areaXian = ko.observable();
	self.areaXiang = ko.observable();
	self.familyName = ko.observable();
	self.familyId = ko.observable(); 
	self.createPersonName = ko.observable();
	self.nick = ko.observable();
	self.email = ko.observable();  
	self.birthDay = ko.observable(); 
	self.cid = ko.observable(); 
	self.firstName = ko.observable(); 
	self.lastName = ko.observable();  
	self.phone = ko.observable();  
	self.sex = ko.observable(1);  
	self.personId = ko.observable();  
	self.headUrl = ko.observable();  
	
	
	self.sexStr = ko.computed(function(){  
		return self.sex() == 1 ? "男": "女";
	});
	
	self.fullName = ko.computed(function(){  
		return self.firstName() + self.lastName();
	});
	self.areaName = ko.computed(function(){  
		var areaName = ""; 
		if(self.areaSheng() != null){
			areaName += self.areaSheng().name;
		}
		if(self.areaShi() != null){
			areaName += self.areaShi().name;
		}
		if(self.areaXian() != null){
			areaName += self.areaXian().name;
		}
		if(self.areaXiang() != null){
			areaName += self.areaXiang().name;
		}
		return areaName;
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


var AreaInfo = function(data, l){
	var self = this;
	self.level = l;
	self.id = data.id;
	self.name = data.name;
	self.subAreas = ko.observableArray([]); 
	self.loadArea = function(areas){
		$.each(areas, function(i, n){
			console.log(n);
			self.subAreas.push(new AreaInfo(n, self.level + 1)); 
		}) ; 
	};
};

 