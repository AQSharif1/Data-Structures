
import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Comparator;

public class MST {
	private PriorityQueue<Node> q;
	private File inputFile;
	private File outputFile;
	private PrintWriter output;
	private Graph graph;
	private int totalCost =0 ;

	//Constructor 
	public MST(File in, Graph gr) {
		inputFile = in;
		graph = gr;

		// Set up for writing to a file
		try {
			// Use input file name to create output file in the same location
			String inputFileName = inputFile.toString();
			String outputFileName = inputFileName.substring(0, inputFileName.length() - 4).concat("_out.txt");
			outputFile = new File(outputFileName);

			// A Printwriter is an object that can write to a file
			output = new PrintWriter(outputFile);
		} catch (Exception x) {
			System.err.format("Exception: %s%n", x);
			System.exit(0);
		}

		// Calls the method that will run MST
		mst();

		output.flush();
	}

	//*********************************************************************************
	
	/**
	 * Call mstPrime method and print method
	 */
	private void mst() {
		MSTPrim();
		print();
	}
	
	/**
	 * Starting with "s" Node we create a minimum spanning tree into Queue
	 */
	public void MSTPrim(){
		Node root = null;
		for (Node n : graph.getNodeList()) {
			if(n.getValue().equalsIgnoreCase("s")){
				root= n;
				n.setPrev(null);
			}
			n.setKey((int)Double.POSITIVE_INFINITY);	
		}
		root.setKey(0);

		q = new PriorityQueue<Node>(graph.getNodeList().size(), new Extract_Min());
		for (Node n : graph.getNodeList()) {
			q.offer(n);
		}
		while(!q.isEmpty()){
			Node n = q.poll();
			for (Edge e : n.getOutgoingEdges()) {
				Node v = e.getHead();	
				if(q.contains(v) && e.getDistance() < v.getKey()) {
					v.setPrev(n);
					v.setKey(e.getDistance());
					q.remove(v);
					q.add(v);
				}
			}
		}

	}

	/**
	 * Prints mst and total cost of mst
	 */
	public void print() {

		for(Node n : graph.getNodeList()) {
			if(n.getPrev() != null) {
				totalCost += (n.getKey() );
			}
		}
		System.out.println("The minimum spanning tree has a total cost of " + totalCost + 
				" and includes the following \nedges:\n" );

		for( int i = 0; i < graph.getNodeList().size(); i++) {
			Node n = graph.getNodeList().get(i);	
			if(n.getPrev()!= null) {

				System.out.println(n.getAbbrev()+ "-" + n.getPrev().getAbbrev());
			}
		}
	}

	/**
	 * Comparator Class
	 * @author Abdul
	 *
	 */
	class Extract_Min implements Comparator<Node>{
		public int compare(Node o1, Node o2) {
			if (o1.getKey() > o2.getKey()) {
				return 1;
			}
			else if (o1.getKey() < o2.getKey()) {
				return -1;
			}
			else  if(o1.getKey() == o2.getKey()) {
				return o1.getAbbrev().compareToIgnoreCase(o2.getAbbrev());
			}
			return 0;
		}
	}





}

