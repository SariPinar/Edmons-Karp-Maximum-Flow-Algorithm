package com.java;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import org.graphstream.graph.Graph;

import org.graphstream.graph.implementations.*;

import org.graphstream.graph.Node;

public class EdmonsKarp extends JFrame {

	
	private static final long serialVersionUID = 1L;
	static Graph graph;
	static List<Edge> Edges;
	static int maxFlow;
	static int nodeCount = 0;
	static Solver MaxFlowSolver;
	static JComboBox<String> mycomboFrom;
	static JComboBox<String> mycomboTo;
	static JComboBox<String> mycomboStart;
	static JComboBox<String> mycomboEnd;
	Label labelFrom;
	Label labelTo;
	List<Edge> UsedEdges;

	public static void main(String[] args) {
		
		MaxFlowSolver = new Solver();
		graph = new SingleGraph("Pınar Sarı");
		new EdmonsKarp();
		List<Edge> UsedEdges = MaxFlowSolver.GetUsedEdges();
		Edges = MaxFlowSolver.GetEdges();
		
		execute();
		
		graph.display();

	}

	public static void runProgram() {
		
		for (int i = 0; i < Edges.size(); i++) {

			graph.getEdge(i).addAttribute("ui.label", (Edges.get(i).getMainCapacity() - Edges.get(i).GetCapacity())
					+ "/" + Edges.get(i).getMainCapacity());
		}
	}

	public EdmonsKarp()

	{
		Frame f = new Frame("Menu");
		mycomboFrom=new JComboBox<String>();
		mycomboFrom.setBounds(250, 200, 100, 20);
		mycomboTo=new JComboBox<String>();
		mycomboTo.setBounds(250, 230, 100, 20);
		mycomboStart=new JComboBox<String>();
		mycomboStart.setBounds(55, 90, 100, 20);
		mycomboEnd=new JComboBox<String>();
		mycomboEnd.setBounds(55, 120, 100, 20);
		TextField tf = new TextField();
		TextField twFrom = new TextField();
		TextField twTo = new TextField();
		TextField twCapacity = new TextField();
		labelFrom = new Label("From:");
		labelTo = new Label("To:");
		Label maxLabel = new Label("Max Flow:");
		Label labelCapacity = new Label("Capacity:");
		Label startLabel = new Label("Start:");
		Label endLabel = new Label("End:");
		tf.setBounds(70, 50, 150, 20);
		twFrom.setBounds(250, 200, 100, 20);
		twTo.setBounds(250, 230, 100, 20);
		twCapacity.setBounds(250, 270, 100, 20);
		//start.setBounds(30, 150, 100, 20);
		//end.setBounds(30, 170, 100, 20);
		labelFrom.setBounds(210, 200, 40, 20);
		labelTo.setBounds(210, 230, 40, 20);
		maxLabel.setBounds(0, 50, 150, 20);
		labelCapacity.setBounds(210, 250, 110, 20);
		startLabel.setBounds(0, 90, 40, 20);
		endLabel.setBounds(0, 120, 40, 20);
		Button runButton = new Button("Run");
		Button nodeButton = new Button("Node");
		Button edgeButton = new Button("Edge");
		runButton.setBounds(20, 145, 150, 50);
		nodeButton.setBounds(220, 100, 100, 50);
		edgeButton.setBounds(100, 200, 100, 100);
		
		
		
		runButton.addActionListener((ActionListener) new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				String inputStart = mycomboStart.getSelectedItem().toString();			
				String inputEnd = mycomboEnd.getSelectedItem().toString();
				maxFlow = MaxFlowSolver.GetMaxFlow(Integer.valueOf(inputStart), Integer.valueOf(inputEnd), nodeCount);
				execute();
				
				tf.setText(String.valueOf(maxFlow));
				System.out.println("Max Flow: "+maxFlow);
				System.out.println();
				UsedEdges = MaxFlowSolver.GetUsedEdges();
				
				for (int i = 0; i < Edges.size(); i++) {
					System.out.println("From : " + Edges.get(i).GetFrom() + ", To : " + Edges.get(i).GetTo() + ",      "
							+ (Edges.get(i).getMainCapacity() - Edges.get(i).GetCapacity()) + "/"
							+ Edges.get(i).getMainCapacity());
				}
				System.out.println();

				for (int i = 0; i < UsedEdges.size(); i++) 
					System.out.println("Used edges: "+UsedEdges.get(i).GetFrom()+"-"+UsedEdges.get(i).GetTo());
	
				runProgram();
			}
		});

		nodeButton.addActionListener((ActionListener) new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				addNodeDraw(String.valueOf(nodeCount));
				addNodeArray();
				nodeCount++;
				execute();
			}
		});

		edgeButton.addActionListener((ActionListener) new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String input =  mycomboFrom.getSelectedItem().toString();
				String inputTo = mycomboTo.getSelectedItem().toString();
				String inputCapacity = twCapacity.getText().toString();
				addEdgeDraw(input, inputTo, inputCapacity);
			}
		});

		f.add(runButton);
		f.add(tf);
		f.add(nodeButton);
		f.add(edgeButton);
		f.add(twCapacity);
		f.add(labelFrom);
		f.add(labelTo);
		f.add(labelCapacity);
		f.add(startLabel);
		f.add(endLabel);
		f.add(maxLabel);
		f.add(mycomboFrom);
		f.add(mycomboTo);		
		f.add(mycomboStart);
		f.add(mycomboEnd);
		f.setSize(400, 400);
		f.setLayout(null);
		f.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void writeEdge() {

		for (int i = 0; i < Edges.size(); i++)
			graph.getEdge(i).addAttribute("ui.label", "0/" + Edges.get(i).getMainCapacity());
	}
	


	public static void writeLabel() {

		graph.setAttribute("stylesheet",
				"node { " + "     shape: circle; " + "     padding: 10px; " + "     fill-color: white; "
				+ "     stroke-mode: plain; " + "     size-mode: fit; " + "     text-size: 20px; " + "} "
				 + "edge { "
				 + "fill-color: red; "
				 + "text-size: 25px;"
			
				 + "}"
				);
		graph.addAttribute("ui.quality");
		graph.addAttribute("ui.antialias");
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		
		for (Node node : graph) 
			node.addAttribute("ui.label", node.getId() + " Node");

	}

	public static void execute() {
		writeLabel();
		writeEdge();
	
	}

	public static void addNodeDraw(String nodeName) {
		graph.addNode(nodeName);
	}

	public static void addEdgeDraw(String from, String to, String c) {
		String addEdgeName = from + to;
		graph.addEdge(addEdgeName, from, to, true);
		MaxFlowSolver.AddEdge(Integer.valueOf(from), Integer.valueOf(to), Integer.valueOf(c));
		
		execute();
	}
	
	public static void addNodeArray() {
		
		mycomboFrom.addItem(String.valueOf(nodeCount));
		mycomboTo.addItem(String.valueOf(nodeCount));
		mycomboStart.addItem(String.valueOf(nodeCount));
		mycomboEnd.addItem(String.valueOf(nodeCount));
		
		
	}


}