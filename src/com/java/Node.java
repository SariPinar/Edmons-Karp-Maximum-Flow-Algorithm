package com.java;
import java.util.ArrayList;
import java.util.List;

class Node
{
	private int nodeID;
	private List<Integer> paths;
	
	public Node(int nodeID, List<Integer> paths)
	{
		this.nodeID = nodeID;
		this.paths = new ArrayList<>(paths);
		this.paths.add(nodeID);
	}
	
	public int GetNodeID() 
	{
		return nodeID;
	}
	
	public List<Integer> GetPaths() 
	{
		return paths;
	}
	
	public void SetNodeID(int nodeID) 
	{
		this.nodeID = nodeID;
	}
	
	public void SetPaths(List<Integer> paths) 
	{
		this.paths = paths;
	}
	
}