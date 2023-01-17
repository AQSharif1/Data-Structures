import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;





public class TSP {
	public static Scanner scanner = new Scanner(System.in);
	private File inputFile;
	private File outputFile;
	private PrintWriter output;
	private Graph graph;
	private static final Color WHITE = Color.WHITE;
	private static final Color GRAY = Color.GRAY;
	private ArrayList<Integer>storing = new ArrayList<Integer>();
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	public ArrayList<String> paths = new ArrayList<String>();
	public ArrayList<Integer> tempStoring = new ArrayList<Integer>();
	private String path = "";
	private int steps = 0;
	private int num = 0;
	private int totalDistance = 0;
	private Node startingNode = null;
	private boolean quit = false;


	//Constructor 
	public TSP(File in, Graph gr) {
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

		// Calls the method that will run TSP
		runTSP();

		output.flush();
	}

	/**
	 * Call Method
	 */
	private void runTSP() {
		//Set all nodes to white
		for (Node no : graph.getNodeList()) {
			no.setColor(WHITE);
		}
		
		main(); // Call Main method
		Collections.sort(storing); // Sort storing arraylist
		summaryPrint();//summary print
		path.substring(0, path.length() - 3);
		int index = tempStoring.indexOf(storing.get(0)); // get index of first element
		
		//Write into File
		try {
			BufferedWriter write = new BufferedWriter(new FileWriter("Verbose.txt"));
			write.write("\nVerbose Print\n");

			write.write("(" +paths.get(0)+ " " + tempStoring.get(0) + " )   (Starting)\n");
			for(int i = 1; i < paths.size(); i++) {
				write.write("(" +paths.get(i)+ " " + tempStoring.get(i) + " )   (SWAP)\n");
			}
			write.write("Shortest path found was "+ paths.get(index)+" " + storing.get(0) + " after "+ steps + " steps");
			write.close();
		} catch(Exception e) {
			e.printStackTrace();
		}


	}
	/**
	 * Generate random starting index
	 * @return
	 */
	public int randomInt() {
		int randomInt;
		randomInt = (int)(Math.random() * graph.getNodeList().size()+1);
		return randomInt;

	}

	/**
	 * Simulator Method
	 */
	public void main() {

		while(quit == false) {
			int choice = 0;
			System.out.println("\nWelcome to the Tour Simulator");
			System.out.println("Make a choice");
			System.out.println("\n1.Run tour simulation with random starting point");
			//System.out.println("\n2. print tour simulation result");
			System.out.println("\n2. Quit simulation and print tour");	
			choice = scanner.nextInt();
			switch(choice) {
			case 1:
				steps++;
				for (Edge n : edges) {
					n.getHead().setColor(WHITE);
				}
				path = "";
				startingNode = graph.getNodeList().get(randomInt()-1);
				System.out.println("Starting at City: " + startingNode.getName());
				newTour(startingNode);
				System.out.println("Path: " + path + " Distance: " + totalDistance);
				paths.add(path );
				storing.add(totalDistance);
				tempStoring.add(totalDistance);
				num =0;
				totalDistance = 0;


				break;
			case 2:	
				quit = true;
				break;
			default: System.out.println("INVALID CHOICE PICK 1 OR 2\n");
			main();
			}	
		}
	}

	/**
	 * Distance Method
	 * @param n1
	 * @param n2
	 * @return Distance between two nodes
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
	 * Tour Method 
	 * @param u
	 */
	public void newTour(Node u) {
		Node temp = null ;
		edges.addAll(u.getOutgoingEdges());
		Collections.sort(edges, new CompareListEdge());
		Edge m = edges.get(0);
		u.setColor(GRAY);


		for(int i = 0; i < u.getOutgoingEdges().size()-1; i++) {
			if((m.getDistance() > u.getOutgoingEdges().get(i).getDistance() && u.getOutgoingEdges().get(i).getHead().getColor().equalsIgnoreCase("white") ))  {
				m = u.getOutgoingEdges().get(i);

			}			
		}
		if(m.getHead().getColor().equalsIgnoreCase("white")) {
			path += m.getTail().getAbbrev() + " -->";
			totalDistance += m.getDistance();
			num++;
		}
		if(m.getHead().getColor().equalsIgnoreCase("gray")) {
			for(Edge e : edges) {
				if(e.getHead().getColor().equalsIgnoreCase("white")) {
					temp = e.getHead();
				}
			}
			if(temp != null) {
				totalDistance += distance(m.getHead(), temp);
				path+=" -->" +temp.getAbbrev();
				path+= " -->" +startingNode.getAbbrev();
				totalDistance += distance(temp, startingNode);
			}

		}



		if(m.getHead().getColor().equalsIgnoreCase("white")) {

			newTour(m.getHead());
		}
		totalDistance += m.getDistance();
	}
	/**
	 * Print Summary 
	 */
	public void summaryPrint() {
		System.out.println("Summary Print\n");
		int index = tempStoring.indexOf(storing.get(0));

		for(int i = 0; i < paths.size(); i++) {
			if(tempStoring.get(0) >= tempStoring.get(i)) {
				System.out.println("(" +paths.get(i)+ " " + tempStoring.get(i) + " )\n");
			}	
		}
		System.out.println("Shortest path found was "+ paths.get(index)+" " + storing.get(0) + " after "+ steps + " steps");
	}
	
	/**
	 * Comparator Class
	 * @author Abdul
	 *
	 */
	class CompareListEdge implements Comparator<Edge>{
		public int compare(Edge o1, Edge o2) {

			double o1V = o1.getDistance();
			double o2V = o2.getDistance();

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