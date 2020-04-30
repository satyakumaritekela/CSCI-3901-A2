/***** Author: Satya Kumar Itekela, Banner ID: B00839907 ****/

/**** creating a node for storing each value with previous and next pointers ****/
public class Node {
	protected String value;		// String value stored by a node
	protected Node previous, next, top, bottom;		// Pointers to locate 4 nodes

	public Node(String value, Node previous, Node next, Node top, Node bottom) {		 //Constructor that initializes a node with given values
	    this.value = value;
	    this.previous = previous;
	    this.next = next;
	    this.top = top;
	    this.bottom = bottom;
	}
	
	/**** getters and setters that returns the values and pointers of this node ****/
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Node getPrevious() {
		return previous;
	}

	public void setPrevious(Node previous) {
		this.previous = previous;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node getTop() {
		return top;
	}

	public void setTop(Node top) {
		this.top = top;
	}

	public Node getBottom() {
		return bottom;
	}

	public void setBottom(Node bottom) {
		this.bottom = bottom;
	}
}

/***** Author: Satya Kumar Itekela, Banner ID: B00839907 ****/