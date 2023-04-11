import java.io.*;

// NOTICE THE "<T extends Comparable<T>>"
// using <T extends Comparable<T>> in here means compiler wont let the code in main send in any T type
// that does not implement Comparable.  Now we do not have to cast the incoming key to a Comparable
// in our insertInOrder() method. Compiler now lets us call .compareTo off the dot on the incoming key
// without throwing an error.

public class LinkedList1<T extends Comparable<T>> 
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LinkedList1()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FROM INCOMING FILE
	@SuppressWarnings("unchecked")
	public LinkedList1( String fileName, boolean orderedFlag )
	{	head = null;
		try
		{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{
				if (orderedFlag)
					insertInOrder( (T)infile.readLine() );  // WILL INSERT EACH ELEM INTO IT'S SORTED POSITION
				else
					insertAtTail( (T)infile.readLine() );  // TACK EVERY NEWELEM ONTO END OF LIST. ORIGINAL ORDER PRESERVED
			}
			infile.close();
		}
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}
	}

	//-------------------------------------------------------------

	// inserts new elem at front of list - pushing old elements back one place

	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

	// we use toString as our print

	public String toString()
	{
		String toString = "";

		for (Node<T> curr = head; curr != null; curr = curr.next )
		{
			toString += curr.data;		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.next != null)
				toString += " ";
		}

		return toString;
	}

	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################



	public int size() // OF COURSE MORE EFFICIENT to KEEP COUNTER BUT YOU  MUST WRITE LOOP
	{
		int size = 1;
    	Node<T> cur = head;
    	while(cur.next!= null)
    {
        cur = cur.next;
        size++;     
    }
        return size; // YOUR CODE HERE
	}

	public boolean empty()
	{
		return(head == null);
         // YOUR CODE HERE
	}

	public boolean contains( T key )
	{
		
		return search(key) != null;

		  // YOUR CODE HERE
	}

	public Node<T> search( T key )
	{
		
		Node<T> cur = head;
		while(cur != null && !key.equals(cur.data)){
			cur = cur.next;
		}
		return cur;  // YOUR CODE HERE
	}

	// TACK A NEW NODE (CABOOSE) ONTO THE END OF THE LIST
	public void insertAtTail(T data)
	{
		if(head == null){
			insertAtFront(data);
			return;
		}
		Node<T> cur = head;
		while(cur.next != null){
			cur = cur.next;
		}
		Node<T> tail= new Node<T>(data,null);
		cur.next = tail;// YOUR CODE HERE
	}

	// IF YOU DEFINE <T> at the top of this class as <T implements Comparable>
	// YOU DO NOT HAVE TO CAST TO COMPARABLE AND YOU DO NOT NEED TO SUPPRESS 
	public void insertInOrder(T  data)
	{
		// YOUR CODE HERE
        if(head == null || data.compareTo(head.data) < 0 ){
            insertAtFront(data);
            return;
        }
        Node<T> cur = head;
        while(cur.next != null && data.compareTo(cur.next.data)> 0){
            cur = cur.next;
        }
        Node<T> enter = new Node<T>(data);
        
        
        enter.next = cur.next;
        cur.next = enter;
        
	}

	public boolean remove(T key)
	{
		 //  REPLACE WITH YOUR CODE 
        if(head == null || head.data.equals(key)){
            return removeAtFront();
        }
        
        Node<T> cur = head;
        while(cur.next != null && !cur.next.data.equals(key)){
            cur = cur.next;
        }
        if(cur.next == null){
            return false;
        }else{
            cur.next = cur.next.next;
            return true;
        }

        
	}

	public boolean removeAtTail()	// RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		 // YOUR CODE HERE
        if(head == null){
            return false;
        }
        if(head.next == null){
            return removeAtFront();
        }
        Node<T> cur = head;
        while(cur.next.next != null){
            cur = cur.next;
        }
        cur.next = null;
        return true;
	}

	public boolean removeAtFront() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		if(head == null){
            return false;
        }
        head = head.next;
        return true;
        
        // YOUR CODE HERE
	}

	public LinkedList1<T> union( LinkedList1<T> other )
	{
		LinkedList1<T> union = new LinkedList1<T>();

		// YOUR CODE HERE
		
        Node<T> cur = this.head;
		if(cur == null){
			return union;
		}
        while(cur != null){
            
            union.insertInOrder(cur.data);
			cur = cur.next;
		}
        
		Node<T> cur1 = other.head;
		while(cur1 != null){
			if(!union.contains(cur1.data)){
				union.insertInOrder(cur1.data);
			}
			cur1 = cur1.next;
		}   

		return union;
	}
	public LinkedList1<T> inter( LinkedList1<T> other )
	{
		LinkedList1<T> inter = new LinkedList1<T>();

		// YOUR CODE HERE
		Node<T> cur = this.head;
		Node<T> cur1 = other.head;
		if(cur == null || cur1 == null){
			return inter;
		}
		
		while(cur.next != null){
			if(other.contains(cur.data)){
				inter.insertInOrder(cur.data);
			}
		cur = cur.next;
		}

		return inter;
	}
	public LinkedList1<T> diff( LinkedList1<T> other )
	{
		LinkedList1<T> diff = new LinkedList1<T>();

		// YOUR CODE HERE
		Node<T> cur = this.head;
		while(cur != null){
			if(!other.contains(cur.data)){
				diff.insertInOrder(cur.data);
			}
		cur = cur.next;
		}


		return diff;
	}
	public LinkedList1<T> xor( LinkedList1<T> other )
	{
		return (this.union(other).diff(this.inter(other)));  // REPLACE WITH YOUR CODE 

	}

} //END LINKEDLIST CLASS 

// A D D   N O D E   C L A S S  D O W N   H E R E 
// R E M O V E  A L L  P U B L I C  &  P R I V A T E (except toString)
// R E M O V E  S E T T E R S  &  G E T T E R S 
// M A K E  T O  S T R I N G  P U B L I C
class Node<T extends Comparable<T>> // tells compiler our incoming T type implements Comparable
{
    T data;
    Node<T> next;

    Node()
    {
     this( null, null );
    }

    Node(T data)
    {
        this( data, null );
    }

    Node(T data, Node<T> next)
    {
        this.data = data;
        this.next = next;
    }
    public String toString()
    {
        return ""+ data;
    } 
        
} //EOF