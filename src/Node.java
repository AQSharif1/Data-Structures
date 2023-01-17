import java.awt.Color;
import java.util.*;

// A node of a graph

public class Node {

	private String name;
	private String value;  // The value of the Node which was stored in the value column
	private String abbrev;  // The abbreviation for the Node
	private ArrayList<Edge> outgoingEdges;  
	private ArrayList<Edge> incomingEdges;
	private Color color;
	private int discovery = 0;
	private int finish = 0;
	private int key =0;
	private Node prev;
	
	
	
	/**
	 * Constructor
	 * @param abbreviation
	 */
	public Node(String abbreviation) {
		abbrev = abbreviation;
		value = null;
		name = null;
		outgoingEdges = new ArrayList<Edge>();
		incomingEdges = new ArrayList<Edge>();
		color = null;
		
	}
	
	/**
	 * Abrev Getter
	 * @return
	 */
	public String getAbbrev() {
		return abbrev;
	}
	
	/**
	 * Name Getter
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Value Getter
	 * @return
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Color Getter
	 * @return
	 */
	public String getColor() {
		if(color.equals(color.WHITE)) {
			return "WHITE";
		}
		else if(color.equals(color.GRAY)) {
			return "GRAY";
		}
		else if(color.equals(color.BLACK)) {
			return "BLACK";
		}
		
		return "Unknown Color";
	}
	
	/**
	 * Discovery Time Getter
	 * @return
	 */
	public int getDiscovery() {
		return discovery;
	}
	/**
	 * Finished Time getter
	 * @return
	 */
	public int getFinish() {
		return this.finish;
	}
	/**
	 * OutGoing Edges Getter
	 * @return
	 */
	public ArrayList<Edge> getOutgoingEdges() {
		return outgoingEdges;
	}
	/**
	 * Incoming Edges Getter
	 * @return
	 */
	public ArrayList<Edge> getIncomingEdges() {
		return incomingEdges;
	}
	/**
	 * Abrev Setter
	 * @param abbreviation
	 */
	public void setAbbrev(String abbreviation) {
		abbrev = abbreviation;
	}
	/**
	 * Name Setter
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Value Setter
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * Outgoing Edges Setter
	 * @param e
	 */
	public void addOutgoingEdge(Edge e) {
		outgoingEdges.add(e);
	}
	/**
	 * Incoming Edge Setter
	 * @param e
	 */
	public void addIncomingEdge(Edge e) {
		incomingEdges.add(e);
	}
	/**
	 * Color Setter
	 * @param c
	 */
	public void setColor(Color c) {
		this.color = c;
	}
	/**
	 * Discover Time Setter
	 * @param d
	 */
	public void d(int d) {
		this.discovery = d;
	}
	/**
	 * Finished Time Setter
	 * @param f
	 */
	public void f(int f) {
		this.finish = f;
	}

	/**
	 * Key Getter
	 * @return
	 */
	public int getKey() {
		return key;
	}

	/**
	 * Key Setter
	 * @param key
	 */
	public void setKey(int key) {
		this.key = key;
	}
	/**
	 * Prev Getter
	 * @return
	 */
	public Node getPrev() {
		return prev;
	}
	/**
	 *  Prev Setter
	 * @param n
	 */
	public void setPrev(Node n) {
		
		this.prev = n;
	}
	
}

