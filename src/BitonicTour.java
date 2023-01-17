import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class BitonicTour {

	private File inputFile;
	private File outputFile;
	private PrintWriter output;
	private Graph graph;
	private LinkedList<Node> nodes = new LinkedList<Node>();
	Node start = null;
	int num = 0;

	// Constructor 
	public BitonicTour(File in, Graph gr) {
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
			System.err.format("Exception: %s%num", x);
			System.exit(0);
		}

		// Calls the method that will run bitonicTour
		bitonicTour();

		output.flush();
	}

	// Bitonic tour call method
	private void bitonicTour() { 
		Collections.sort(graph.getNodeList(), new CompareVal());
		num = graph.getNodeList().size();
		System.out.println("Shortest Bitonic Tour has Distance: " + bitonic(graph.getNodeList().size()-1, graph.getNodeList().size()-1));
		System.out.println("Tour: " );
		
	}

	/**
	 * Distance method
	 * @param n1
	 * @param n2
	 * @return Int distance between two nodes
	 */
	public int distance(Node n1, Node n2) {
		Edge tempE;
		int tempTotal = 0;
		ArrayList<Edge>tempEdges = null;
		tempEdges = n1.getOutgoingEdges();
		for(Edge e: tempEdges){
			tempE = e;
			if(n2 == tempE.getHead()) {
				//System.out.println(n1.getAbbrev() + n2.getAbbrev());
				tempTotal = tempE.getDistance();
			}
			
		}

		return tempTotal;
	}

	/**
	 * Bitonic Algorithm
	 * @param i
	 * @param j
	 * @return distance
	 */
	public int bitonic (int i, int j) {
		int temp;
		int valueDistance = Integer.MAX_VALUE;
		if(i < j-1 ) {
			return bitonic(i, j-1) + distance(graph.getNodeList().get(j-1), graph.getNodeList().get(j));
		}

		else if (i == 0 && j == 1) {
			return distance(graph.getNodeList().get(i),graph.getNodeList().get(j));
		}
		else {
			for (int k = 0; k< j-1; k++) {
				temp = bitonic(k, j-1)+ distance(graph.getNodeList().get(k),(graph.getNodeList().get(j)));

				if (valueDistance > temp) {
					valueDistance = temp;
				}
			}
			
			valueDistance =  distance(graph.getNodeList().get(j-1), graph.getNodeList().get(i)) + valueDistance;
			return valueDistance;
		}
	}


	public void addIn(Node n) {
		if(!nodes.contains(n)) {
			nodes.add(n);
		}
	}
	
	/**
	 * Comparator Class
	 * @author Abdul
	 *
	 */
	class CompareVal implements Comparator<Node>{
		public int compare(Node o1, Node o2) {
			String i;
			String j;
			i = o1.getValue().substring(0, o1.getValue().length() -1);
			j = o2.getValue().substring(0, o2.getValue().length() -1);
			double o1V = Double.parseDouble(i);
			double o2V = Double.parseDouble(j);

			if(o1V > o2V) {
				return -1;
			}
			if(o1V < o2V) {
				return 1;
			}
			else return 0;
		}
	}
}



