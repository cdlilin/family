package org.swz.com.family.entity;

import java.util.List;

public class JitTreeNode {
	
	private String id;
	private String name;
	private List<JitTreeNode> children;
	
	private JitPerson data;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<JitTreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<JitTreeNode> children) {
		this.children = children;
	}
	public JitPerson getData() {
		return data;
	}
	public void setData(JitPerson data) {
		this.data = data;
	} 
	
	

}
