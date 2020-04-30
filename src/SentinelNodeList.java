/***** Author: Satya Kumar Itekela, Banner ID: B00839907 ****/

/**** Class that holds all the head and tail nodes in each level ****/
public class SentinelNodeList {
    protected static int numberOfLevel;
    protected static int totalNumberOfLevels;       // total number of levels in the hierarchy
    private static Node headNode;
    private static Node tailNode;
    private static int position;
    
	public SentinelNodeList() {		// constructor that initializes all the values
		numberOfLevel = 0;
		totalNumberOfLevels = 0;
		headNode = new Node(null,null,null,null,null);
		tailNode = new Node(null,headNode,null,null,null);
		headNode.setNext(headNode);
		setPosition(0);
	}

	/**** getters and setters for each attribute****/
	public static int getNumberOfLevel() {
		return numberOfLevel;
	}

	public static void setNumberOfLevel(int numberOfLevel) {
		SentinelNodeList.numberOfLevel = numberOfLevel;
	}

	public static int getTotalNumberOfLevels() {
		return totalNumberOfLevels;
	}

	public static void setTotalNumberOfLevels(int totalNumberOfLevels) {
		SentinelNodeList.totalNumberOfLevels = totalNumberOfLevels;
	}
	
	public static boolean isEmpty() {
        return totalNumberOfLevels == 0 ? true: false;
    }

	public static int getPosition() {
		return position;
	}

	public static void setPosition(int position) {
		SentinelNodeList.position = position;
	}

	/**** Adding a sentinel head node in a linked lists for each level****/
	public static void addHeadNode(Node headValue,Node tailValue) { 
		if(isEmpty()) {		// if it is empty at a first position or level
			headNode = headValue;
			tailNode = tailValue;
			headNode.setNext(tailNode);
			tailNode.setPrevious(headNode);
			totalNumberOfLevels++;	
			setPosition(getPosition() + 1);
			return;
		}
		if(headNode.getTop() == null) {		// add a each level head sentinel node respectively
			headNode.setTop(headValue);
			tailNode.setTop(tailValue);
			tailValue.setBottom(tailValue);		// linking the head and tail nodes
			headNode.setBottom(headValue);
			headNode = headValue;
			tailNode = tailValue;
			setPosition(getPosition() + 1);		// increment the pointer which level indicates
			totalNumberOfLevels++;	
			return;
		}
    }
	
	/**** get a head Node at each level using position pointer and level ****/
	/**** recursive function for reaching the level that pointer needs to locate and return the head Node ****/
	public static Node getHeadNode(int level){
		if(getPosition() == level) {
			return headNode;
		}
		else if(level < getPosition()) {
			for(int i = getPosition(); i > 0; ) {
				headNode = headNode.getBottom();
				setPosition(getPosition() - 1);
				return getHeadNode(getPosition());		// if the pointer that does not match recurse the function again
			}
		}
		else if(level <= totalNumberOfLevels && getPosition() < level){
			for(int i = getPosition(); i < level;) {
				headNode = headNode.getTop();
				setPosition(getPosition() + 1);
				return getHeadNode(getPosition());		// if the pointer that does not match recurse the function again
			}			
		}
		return null;		// if node not found return null;
	}

	/**** get a tail Node at each level using position pointer and level ****/
	/**** recursive function for reaching the level that pointer needs to locate and return the tail Node ****/
	
    public static Node getTailNode(int level) {
		if(getPosition() == level) {
			return tailNode;
		}
		else if(level < getPosition()) {
			for(int i = getPosition(); i > 0; ) {
				tailNode = tailNode.getBottom();
				setPosition(getPosition() - 1);
				return getHeadNode(getPosition());		// if the pointer that does not match recurse the function again
			}	
		}
		else {
			for(int i = getPosition(); i < level;) {
				tailNode = tailNode.getTop();
				setPosition(getPosition() + 1);
				return getHeadNode(getPosition());		// if the pointer that does not match recurse the function again
			}			
		}
		return null;
    }
    
}

/***** Author: Satya Kumar Itekela, Banner ID: B00839907 ****/