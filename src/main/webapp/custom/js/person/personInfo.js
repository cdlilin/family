

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
		$('#hitPerson_dlg').dialog({
		    title: '这些可能是您的家族',
		    width: 600,
		    height: 500,
		    closed: true,
		    cache: false, 
		    modal: true
		});
		self.loadAreaSheng(0);  
		 
		
		$('#hit_family_grid').datagrid({
			url: '../getFamilyByPersonInfo',
			method: 'POST',  
			width: "100%",
			height: "100%",
			checkbox:true,
			singleSelect:true, 
			columns:[[
			    {field:'ck', checkbox:true},
				{field:'familyName',title:'申请家族',width:80}
			]], 
			toolbar:[{
				text: '申请绑定',
				iconCls : 'icon-add',
				handler:function(){
					var row = $('#hit_family_grid').datagrid('getSelected');
					self.bindHitPerson(row);
				}
			},'-',{
				text: '删除',
				iconCls : 'icon-remove',
				handler:function(){
					
				}
			}],
			onLoadSuccess:function(data){  
		        if(data.total > 0){  //如果能命中就弹出窗口哦提示用户
		        	$('#hitPerson_dlg').dialog("open");
		        } 
		    },
		    onSelect: function(i, d){
		    	self.loadFamilyPerson(d);
		    }
		});
		
		$('#person_tree_table').treegrid({ 
		    method:"GET",
		    idField:'personId',
		    treeField:'fullName',
		    width: "100%",
			height: "100%",
		    collapsible: true, 
		    columns:[[
		        {title:'姓名 ',field:'fullName',width:180, editor:'text' }, 
		        {title:'配偶',field:'spouses',width:180, formatter: function(value){
		        	var str = "";
		        	$.each(value, function(i, n){
		        		str +=  n.fullName + "<a class='btn' onclick='javascript:delSpouse(\"" + n.personId + "\", \"" + n.parentId + "\")'><i class='icon-add'/></a>";
		        	});
		        	return str;
		        }},
		        {title:'昵称',field:'nick',width:180, editor:'text'},
		        {title:'关系',field:'relationShipType',width:180, editor:'text'}, 
		        {title:'生日',field:'birthDayStr',width:180, editor:'datebox'} 
		    ]]  
		}); 
		
		
	 
		var cmenu;
		function createColumnMenu(){
			cmenu = $('<div/>').appendTo('body');
			cmenu.menu({
				onClick: function(item){
					if (item.iconCls == 'icon-ok'){
						$('#dg').datagrid('hideColumn', item.name);
						cmenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-empty'
						});
					} else {
						$('#dg').datagrid('showColumn', item.name);
						cmenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-ok'
						});
					}
				}
			});
			var fields = $('#dg').datagrid('getColumnFields');
			for(var i=0; i<fields.length; i++){
				var field = fields[i];
				var col = $('#dg').datagrid('getColumnOption', field);
				cmenu.menu('appendItem', {
					text: col.title,
					name: field,
					iconCls: 'icon-ok'
				});
			}
		}
		
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
	
	self.loadFamilyPerson = function(row){ 
		$('#person_tree_table').treegrid({
			url: "../" + row.familyId + "/getFamilyTreeForGrid"
		});
	};
	
	self.hitFamilyForUser = function(){  
		 console.log(self.personInfo());
//		if(self.personInfo().pesonId() == null || self.personInfo().pesonId() == ""){
			$('#hit_family_grid').datagrid("load", { 
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
			}); 
//		} 
	}; 
	self.bindHitPerson = function(row){  
		
		$.ajax({
			url : "../apply/sendApply",
			cache : false,
			type : "POST",
			dataType : "json",
			contentType : "application/json", 
			data: JSON.stringify({
				familyId : row.familyId,
				personId: row.person.personId, 
				applyValidate: row.person.fullName
			}),
			success : function(result) {  
				if (result != null && result != "undefind" && result.code == "0") { 
					$("#hitPerson_dlg").dialog("close");
					$.messager.alert('提示','申请已发送，静候佳音!');
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
		self.personInfo().userName(personInfo.userName);
		self.personInfo().profile(personInfo.profile);
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
			url : "../person/" + (self.personInfo().personId() == null ? "addPerson": "modifyPerson"),
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
				profile: self.personInfo().profile(),
				birthDay: $("#personInfo_birthDay").val(),
				headUrl: $("#user_head_img").attr("src").substring(basePath.length + 1)
			}),
			success : function(result) {  
				if (result != null && result != "undefind" && result.code == "0") { 
					
					$.messager.alert("提示", "保存用户信息成功!")
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
	self.userName = ko.observable();  
	self.profile = ko.observable();  
	self.personId = ko.observable();  
	
	
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

 