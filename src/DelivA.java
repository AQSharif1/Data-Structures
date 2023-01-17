import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class DelivA {

	private File inputFile;
	private File outputFile;
	private PrintWriter output;
	private Graph graph;

	//Constructor 
	public DelivA(File in, Graph gr) {
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

		// Calls the method that will do the work of deliverable A
		runDelivA();

		output.flush();
	}

	//*********************************************************************************
	//               This is where your work starts

	/**
	 * Method Runs DelivA
	 * Sorts and returns Node values in chosen order
	 */
	private void runDelivA() {
		CompareDegree compareDegree = new CompareDegree();
		Collections.sort(graph.getNodeList(), compareDegree );

		System.out.println("Indegree: \n");
		for(int i = 0; i< graph.getNodeList().size(); i++) {

			System.out.println("Node " + graph.getNodeList().get(i).getAbbrev() + " has " +
					"indegree "  +  graph.getNodeList().get(i).getIncomingEdges().size());
		}

		Collections.sort(graph.getNodeList(), new ComparingDegrees() );

		System.out.println("\nOutdegree: \n");
		for(int i = 0; i< graph.getNodeList().size(); i++) {
			System.out.println("Node " + graph.getNodeList().get(i).getAbbrev() + " has "+ "outdegree " + graph.getNodeList().get(i).getOutgoingEdges().size()  );
		}

	}

	class CompareDegree implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			if (o1.getIncomingEdges().size() > o2.getIncomingEdges().size()) {
				return -1;
			}

			else if (o1.getIncomingEdges().size() < o2.getIncomingEdges().size()) {
				return 1;
			}

			else if(o1.getIncomingEdges().size() == o2.getIncomingEdges().size()) {

				if (o1.getOutgoingEdges().size() > o2.getOutgoingEdges().size()) {
					return 1;
				}
				else if (o1.getOutgoingEdges().size() < o2.getOutgoingEdges().size()) {
					return -1;
				}
				else if((o1.getOutgoingEdges().size() == o2.getOutgoingEdges().size())) {
					return o1.getAbbrev().compareTo(o2.getAbbrev());
				}
			}
			return 0;
		}

	}

	/**
	 * 
	 * @author Abdul
	 * Class implements Comparator<Node> 
	 * 
	 */
	class ComparingDegrees implements Comparator<Node>{

		@Override
		public int compare(Node o1, Node o2) {
			if (o1.getOutgoingEdges().size() > o2.getOutgoingEdges().size()) {
				return -1;
			}

			if (o1.getOutgoingEdges().size() < o2.getOutgoingEdges().size()) {
				return 1;
			}

			if(o1.getOutgoingEdges().size() == o2.getOutgoingEdges().size()) {
				if (o1.getIncomingEdges().size() > o2.getIncomingEdges().size()) {
					return 1;
				}
				else if (o1.getIncomingEdges().size() < o2.getIncomingEdges().size()) {
					return -1;
				}
				else if((o1.getIncomingEdges().size() == o2.getIncomingEdges().size())) {
					return o1.getAbbrev().compareToIgnoreCase(o2.getAbbrev());
				}
			}
			return 0;
		}
	}
}



