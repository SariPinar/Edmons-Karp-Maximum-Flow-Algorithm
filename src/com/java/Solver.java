package com.java;

import java.util.*; 

class Solver 
{ 
	private List<Edge> edges = new ArrayList<Edge>();
	private List<Edge> UsedEdges = new ArrayList<Edge>();
	
	public List<Edge> GetEdges()
	{ 	
		return edges;
	}
	
	public Solver() 
    { 
    } 
  
	public void AddEdge(int from,int to, int capacity)  
    {   
    	edges.add(new Edge(from, to, capacity));
    }
    
    private Edge FindEdge(int from, int to) 
    {
    	for (int i = 0; i < edges.size(); i++) 
    	{
			if(edges.get(i).GetFrom() == from && edges.get(i).GetTo() == to) 
				return edges.get(i);
		}
    	
    	return new Edge(-1,-1,-1);
    }
    
    private List<Edge> FindEdgeNB(int from) 
    {	
		List<Edge> NB = new ArrayList<Edge>(); // NB = NeighBor
		
    	for (int i = 0; i < edges.size(); i++) 
    	{
			if(edges.get(i).GetFrom() == from)
				NB.add(edges.get(i));
		}
    	
    	return NB;
	}
    
    private int FindBottleNeck(Node node) 
    {	
    	int bottleNeck = 0;	
    	List<Integer> bottleNecksArray = new ArrayList<Integer>();
    	
    	for (int i = 0; i < node.GetPaths().size()-1; i++)
    		bottleNecksArray.add(FindEdge(node.GetPaths().get(i), node.GetPaths().get(i+1)).GetCapacity());
		
		if(bottleNecksArray.size() > 0)
			bottleNeck = Collections.min(bottleNecksArray);
		
		for (int i = 0; i < node.GetPaths().size()-1; i++) 
		{
			Edge e = FindEdge(node.GetPaths().get(i), node.GetPaths().get(i+1));
			e.SetCapacity(e.GetCapacity() - bottleNeck);
		}
		
		return bottleNeck;
    }
    
    private void ResetEdgesCapacity()
	{
		for (int i = 0; i < edges.size(); i++)
			edges.get(i).ResetCapacity();
	}
    
    public int GetMaxFlow(int source, int destination, int nodeCount) 
    {	
    	ResetEdgesCapacity();
    	int flow = 0;
		
    	while(true) {
			
			int result = this.BFS(source, destination, nodeCount);
			
			if(result == 0) 
				break;
			
			flow = flow + result;
		}
    	
    	return flow;
    }
    
    private void AddUsedEdgesList(Node node) 
    {
    	for (int i = 0; i < node.GetPaths().size()-1; i++)
			UsedEdges.add(FindEdge(node.GetPaths().get(i), node.GetPaths().get(i+1)));
    }
    
    public List<Edge> GetUsedEdges() 
    {
    	return UsedEdges;
    }
    
    private int BFS(int source, int destination, int nodeCount) 
    {	
    	boolean[] visited = new boolean[nodeCount];	
    	visited[source] = true;
    	Queue<Node> queue = new ArrayDeque<Node>();
    	
    	queue.add(new Node(source,new ArrayList<Integer>()));
    	
    	while(!queue.isEmpty()) 
    	{	
    		Node currentNode = queue.peek();
    		int peek = currentNode.GetNodeID();
    		
    		if(peek == destination) 
    		{    			
    			AddUsedEdgesList(currentNode);	
    			return FindBottleNeck(currentNode);
    		}
    		
    		queue.poll();
    		List<Edge> NB = FindEdgeNB(peek);    		
			for (int i = 0; i < NB.size(); i++) 
			{	
				if(NB.get(i).GetCapacity() > 0) 
				{	
					int nextPath = NB.get(i).GetTo();
					
					if(!visited[nextPath]) 
					{	
						visited[nextPath] = true;
						queue.add(new Node(nextPath, currentNode.GetPaths()));
					}	
				}
			}
    	}
    	
    	return 0;
    }
}