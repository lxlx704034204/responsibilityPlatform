package com.orbit.utils;

import java.util.List;

public class TreeNode<T> {
	
	private Integer depth;
	private T value;
	private TreeNode<T> parent;
	private List<TreeNode<T>> children;
	
	public void setDepth(Integer depth){
		this.depth = depth;
	}
	public Integer getDepth(){
		return this.depth;
	}
	
	public void setValue(T obj){
		this.value = obj;
	}
	public T getValue(){
		return this.value;
	}
	
	public void setParent(TreeNode<T> parent){
		this.parent = parent;
	}
	public TreeNode<T> getParent(){
		return this.parent;
	}
	
	public void setChildren(List<TreeNode<T>> children){
		this.children = children;
	}
	public List<TreeNode<T>> getChildren(){
		return this.children;
	}
}
