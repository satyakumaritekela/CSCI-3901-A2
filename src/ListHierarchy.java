/***** Author: Satya Kumar Itekela, Banner ID: B00839907 ****/

/**** List Hierarchy that holds the string elements in n number of levels ****/
public class ListHierarchy {
	protected static Node head;		// creating head and tail in the list
	protected static Node tail;
	protected static int size;		// number of elements
	protected static int numberOfLevel;		// number of level
	protected static int totalNumberOfLevels;		// total number of levels in the hierarchy
	protected static Coin randomVariable;
	static SentinelNodeList sentinelNodeList = new SentinelNodeList();
	
	public ListHierarchy(Coin flip) {		 // Constructor that takes a object of Coin for generating random data 
	    size = 0;
	    numberOfLevel = 0;
	    totalNumberOfLevels = 0;
	    head = new Node(null, null, null, null, null);		// create head
	    tail = new Node(null, head, null, null, null);		// create tail
	    head.setNext(tail); 
	    randomVariable = flip;
	}
	
	public static int size() {		// Returns size of the list
		return size; 
	}
	
    public static boolean isEmpty() {		//Returns whether the list is empty
    	return (size == 0);
    } 
 
    public static Node getFirst() throws IllegalStateException {		// returns the first node
        if(isEmpty()) {
        	throw new IllegalStateException("List is empty");
        }
        return head.getNext();
    }

    public static Node getLast() throws IllegalStateException {		// returns the last node
    	if (isEmpty()) {
    		throw new IllegalStateException("List is empty");
    	}
    	return tail.getPrevious();
    }
    
    public static Node getPrevious(Node nodeValue) throws IllegalArgumentException {		// Returns previous node
    	if(nodeValue == head) {
    		throw new IllegalArgumentException("Can't pass back the head");
    	}
        return nodeValue.getPrevious();
    }
    
    public static Node getNext(Node nodeValue) throws IllegalArgumentException {		// Returns next node
    	if(nodeValue == tail) {
    		throw new IllegalArgumentException("Can't pass from the tail");
    	}
        return nodeValue.getNext();
    }
    
    public static boolean hasPrev(Node nodevalue) {		// checks whether the previous is head or not and returns true or false
    	return nodevalue != head; 
    }
    
    public static boolean hasNext(Node nodevalue) {		// checks whether the previous is tail or not and returns true or false
    	return nodevalue != tail; 
    }
    
    /**** adds an element into the list and checks with the random variable whether to add it to next level or not ****/
    
	public static boolean add(String key) {
    	if(!key.matches("^[a-z\\sA-Z]*$")){		// checking whether the element is string and returns false if it is integer
    		System.out.println("Can add only strings. Cannot be able to add integers");
    		return false;
    	}
    	if(key.length() > 15 || key.length() == 0 || key == null) {
    		System.out.println("The string cannot be empty or null / The string must be maximum of 15 characters");
    		return false;
    	}
    	/**** If it is a proper string create a new head and tail node for adding elements in between ****/
    	
	    Node dataNode = new Node(key, null, null, null, null); 	// create a new node in the list 
	    Node temporaryNode, insertionNode;		// create a temporary node and a traversing node  for traversing through the list
	    boolean inserted = false;
	    
	    /**** Link all the head Nodes in the sentinelNodeList for linking up all the levels ****/
	    /**** checking if empty or not and adding first element to the list ****/
	    
	    if(isEmpty()) {		// if the list is empty add the value after a head
	    	SentinelNodeList.addHeadNode(head,tail); 	// linking the first level to the sentinelNodeList
		    dataNode.setValue(key);  	// assigning values to the node
	        dataNode.setPrevious(head);
	        dataNode.setNext(tail);
	        tail.setPrevious(dataNode);
	        head.setNext(dataNode); 
	        size++;
	        totalNumberOfLevels++;
	        numberOfLevel++;
	        inserted = true;
	    }
		else if(key.compareToIgnoreCase(getFirst().getValue()) == 0) {	// If the first element value if already exists in the list do add in the list
			System.out.println("The list must contain unique names. Please check the list entered");
	        inserted = false;
			return false;
		}	    
	    else if(key.compareToIgnoreCase(getFirst().getValue()) < 0) {	// if the value is less than the value in first node and make a new node as head
	    	dataNode.setNext(getFirst());	    	
	    	getFirst().setPrevious(dataNode);
	        head.setNext(dataNode);
	        size++;
	    	numberOfLevel++;
	        inserted = true;
	    }
	    else {
	    	temporaryNode = getFirst();		// taking a temporary and traverse node for pointing to head and its next node for easy traverse while inserting
	    	insertionNode = temporaryNode.getNext();
	    	while(hasNext(insertionNode)) {		// scan through all the list until the node pointing to null that is tail
	    		if(key.compareToIgnoreCase(temporaryNode.getValue()) > 0 && key.compareToIgnoreCase(insertionNode.getValue()) < 0) {
	    			temporaryNode.setNext(dataNode);			// inserting the value in correct position by checking the conditions
	    			dataNode.setPrevious(temporaryNode);
	    			dataNode.setNext(insertionNode);
	    			insertionNode.setPrevious(dataNode);
	    	        inserted = true;
	    	    	numberOfLevel++;
	    		    break;
                }
	    		else if(key.compareToIgnoreCase(temporaryNode.getValue()) == 0) {	// If the value already exists in the list do add in the list
	    			System.out.println("The list must contain unique names. Please check the list entered");
	    	        inserted = false;
	    			return false;
	    		}
	    		else {	// if the value is not satisfied with the existing nodes traverse to next nodes
	    			temporaryNode = insertionNode;
	    			insertionNode = insertionNode.getNext();
	    		}
	    	}
	    	if(!inserted) {	// if the value is not inserted yet add it next to existing tail node
	    		temporaryNode.setNext(dataNode);			// inserting the value in correct position by checking the conditonS
    			dataNode.setPrevious(temporaryNode);
    			dataNode.setNext(tail);
    	    	numberOfLevel++;
                inserted = true;
            }
	    }
	    /**** After inserting to First level GoToNextLevel checks the random variable to add it to next level or not ****/
	    if(inserted) {
	    	goToNextLevel(dataNode, key);
	    }
  		return inserted;
	}
	
	/**** checking the random variable o or 1 and returns the level to reach if 1****/
	public static int level() {
		if(randomVariable != null) {
			if(randomVariable.flip() == 1) {
				return numberOfLevel;
			}
			else {
				numberOfLevel = 0;
				SentinelNodeList.numberOfLevel = 0;
				return numberOfLevel;
			}			
		}
		else {
			System.out.println("Cannot pass a null coin to the constructor. So cannot add it to next level");
		}
		return 0;
	}
	
	/**** Function that adds the element to the next levels based on random variable generated from the function level()****/
	public static Object goToNextLevel(Node dataNode,String key) {
		if((level() > 0) && (totalNumberOfLevels + 1 > numberOfLevel)) {		// After reaching the most upper level should not add it to the function so stopping using these condition
			
			Node moveNextLevel = new Node(key, null, null, null, null);		// taking a new Node and copying the node that moves to next level
			Node newHeadNode = null;
			Node newTailNode = null;  
		    boolean inserted = false;
			
			moveNextLevel.setBottom(dataNode);		// link between the nodes that is moving to next level
			dataNode.setTop(moveNextLevel);
			
			/**** Adding to the new SentinelNode for the next Level****/
			if(SentinelNodeList.getHeadNode(numberOfLevel+1) == null) {		// creating a sentinelNode and linking with lower level nodes
				newHeadNode = new Node(null, null, null, null, SentinelNodeList.getHeadNode(numberOfLevel));
				newTailNode = new Node(null, SentinelNodeList.getTailNode(numberOfLevel), null, null, null);
				newHeadNode.setNext(newTailNode);
				SentinelNodeList.addHeadNode(newHeadNode,newTailNode);				
			}
			else {
				newHeadNode = SentinelNodeList.getHeadNode(numberOfLevel+1);
				newTailNode = SentinelNodeList.getTailNode(numberOfLevel+1);
			}
			
			Node temporaryNode = newHeadNode;		
			Node insertionNode = temporaryNode.getNext();
			
			/**** traversing the list and add in the list in any level****/
			if(insertionNode == newTailNode) {
				newHeadNode.setNext(moveNextLevel);
				moveNextLevel.setPrevious(newHeadNode);
				moveNextLevel.setNext(newTailNode);
				newTailNode.setPrevious(moveNextLevel);
				inserted = true;
			}    
		    else if(key.compareToIgnoreCase(newHeadNode.getNext().getValue()) < 0) {	// if the value is less than the value in first node and make a new node as head
		    	moveNextLevel.setNext(newHeadNode.getNext());	    	
		    	newHeadNode.getNext().setPrevious(moveNextLevel);
		    	newHeadNode.setNext(moveNextLevel);
		        inserted = true;
		    }
			else {
				temporaryNode = newHeadNode.getNext();
				insertionNode = temporaryNode.getNext();
				
				while(insertionNode != newTailNode) {		// scan through all the list until the node pointing to null that is tail
		    		
					try {
						if(key.compareToIgnoreCase(temporaryNode.getValue()) > 0 && key.compareToIgnoreCase(insertionNode.getValue()) < 0) {
					
		    			temporaryNode.setNext(moveNextLevel);			// inserting the value in correct position by checking the conditions
		    			moveNextLevel.setPrevious(temporaryNode);
		    			moveNextLevel.setNext(insertionNode);
		    			insertionNode.setPrevious(moveNextLevel);
		    			inserted = true;
		    		    break;
		                }
			    		else {	// if the value is not satisfied with the existing nodes traverse to next nodes
			    			temporaryNode = insertionNode;
			    			insertionNode = insertionNode.getNext();
			    		}
					}
					catch(NullPointerException ne) {
						return true;
					}
		    	}
		    	if(!inserted) {	// if the value is not inserted yet add it before to existing tail node
		    		temporaryNode.setNext(moveNextLevel);			// inserting the value in correct position by checking the conditonS
		    		moveNextLevel.setPrevious(temporaryNode);
		    		moveNextLevel.setNext(newTailNode);
	                inserted = true;
	            }				
			}
			numberOfLevel++;		// counting the number of levels it is raising
			return goToNextLevel(dataNode, key);	// check again with the random variable to raise to the next level or not
		}
		else {	// if not satisfied getting the total Number of levels
			totalNumberOfLevels = totalNumberOfLevels > numberOfLevel ? totalNumberOfLevels : numberOfLevel;
			numberOfLevel = 0;
			SentinelNodeList.numberOfLevel = 0;
			SentinelNodeList.totalNumberOfLevels = totalNumberOfLevels;
			return null;
		}
	}
	
	/**** Find the respective key present in the list ****/
	/**** traverse through all the levels from the top Node using while function ****/
	public static boolean find(String key) {
		if(isEmpty()) {		// If the list is empty return false
			System.out.println("The List is Empty. Cannot find a value");
			return false;
		}
		Node topNode = SentinelNodeList.getHeadNode(totalNumberOfLevels).getNext();		// get the topNode for traversing
		while (topNode != null) {
	        if (key.compareToIgnoreCase(topNode.getValue()) == 0) {
	        	System.out.println("Found the value: "+key);
	            return true;
	        }
	        /**** Reach each level until head and tail if found move to bottom or next****/
	        else if(key.compareToIgnoreCase(topNode.getValue()) > 0) {
	        	if(topNode.getNext().getValue() == null) {
	        		topNode = topNode.getBottom();
	        	}
	        	else {
	        		topNode = topNode.getNext();
	        	}
	        }
	        else {
	        	if(topNode.getPrevious().getValue() == null) {
	        		topNode = topNode.getBottom();
	        	}
	        	else {
	        		topNode = topNode.getPrevious();
	        	}
	        }
	    }
		//return false if not found in the list
    	System.out.println("Could not found the value: "+key);
		return false;
	}
    /**** Displaying the elements per each level that are present in the list****/
    public static boolean display() {		
    	/**** representation of the list in all levels ****/
    	for(int i = totalNumberOfLevels; i > 1; i--) {
    		System.out.print("Level: "+ (i-1));
    		System.out.print(" [");
	   		Node traverse = SentinelNodeList.getHeadNode(i).getNext();
	   		while(traverse.getValue() != null) {
	   			System.out.print(traverse.getValue());
	   			traverse = traverse.getNext();
	   			if (traverse.getValue() != null) {
		   			System.out.print(",");
	      	    }
	   		}
		   	System.out.println("]");
	   	}

		System.out.print("Level: 0 ");
	   	String list = "[";
	   	Node nodeValue = head.getNext();
	   	while(hasNext(nodeValue)) {
	   		list += nodeValue.getValue();
	   		nodeValue =nodeValue.getNext();
	   		if (hasNext(nodeValue)) {
	   			list += ",";
      	    }
        }
        list += "]";
        System.out.println(list);
    	System.out.println();
    	return true;
    }
}

/***** Author: Satya Kumar Itekela, Banner ID: B00839907 ****/