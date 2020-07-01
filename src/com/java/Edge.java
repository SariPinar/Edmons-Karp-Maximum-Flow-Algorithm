package com.java;



class Edge 
{
	private int from;
	private int to;
	private int capacity;
	private int mainCapacity;
	
	public void ResetCapacity() 
	{
		this.capacity = this.mainCapacity;
	}
	
	public Edge(int from, int to, int capacity) 
	{
		this.from = from;
		this.to = to;
		this.capacity = capacity;
		this.mainCapacity=capacity;
	}
	
	
	public int getMainCapacity() {
		return mainCapacity;
	}


	public void setMainCapacity(int mainCapacity) {
		this.mainCapacity = mainCapacity;
	}


	public int GetFrom()
	{
		return from;
	}
	
	public int GetTo()
	{
		return to;
	}
	
	public int GetCapacity()
	{
		return capacity;
	}
	
	public void SetFrom(int from) 
	{
		this.from = from;
	}
	
	public void SetTo(int to) 
	{
		this.to = to;
	}
	
	public void SetCapacity(int capacity) 
	{
		this.capacity = capacity;
	}
}