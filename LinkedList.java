import java.io.*;
import java.util.*;

public class LinkedList<T extends Comparable<T>>
{
	private Node<T> head;  // pointer to the front (first) element of the list
	private Node<T> tail;  // pointer to the last elem of the list ( caboose or tail node)

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
		tail = head;
	}

	// USE THE TOSTRING AS OUR PRINT
	public String toString()
	{
		String toString = "";

		for (Node<T> curr = head; curr != null; curr = curr.next)
		{
			toString += curr.data;		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.next != null)
				toString += " -> ";
		}

		return (String) (toString + " ");
	}
	
	// ########################## Y O U   W R I T E   T H E S E    M E T H O D S  
	// . . .AND ANY SUPPORTING METHODS YOU NEED FOR THEM 

	// THIS VERSION JUST LOADS THE LISTS FROM THE FILE BEFORE THEY ARE MERGED
	public void insertAtTail( T data )
	{
		Node<T> newN = new Node<T>(data);
		if(head == null){
			head = new Node<T>(data);
			return;
		}
		newN.next = null;
		Node<T> cur = head;
		while(cur.next != null){
			cur = cur.next;
		}

		cur.next = newN;
		return;
	}
	public LinkedList<T> merge( LinkedList<T> other )  // think 'sorted union' but only 1 pass allowed
	{
	
		LinkedList<T> merge = new LinkedList<T>();
		tail = null;
		Node<T> cur1 = this.head;
		Node<T> cur2 = other.head;

		while(cur2 != null ){
			if(cur1.data.compareTo(cur2.data) < 0 ){
				merge.insertAtTail(cur1.data);
				tail = cur1;
				cur1 = cur1.next;
				//System.out.print(cur1.data);
			}else{
				merge.insertAtTail(cur2.data);
				tail = cur2;
				cur2 = cur2.next;
				//System.out.print(cur2.data);
			}
			
		}
		if(cur2 == null){
			while(cur1 != null){
			merge.insertAtTail(cur1.data);
			cur1 = cur1.next;
			}
		}
		if(cur1 == null){
			while(cur2 != null){
			merge.insertAtTail(cur2.data);
			cur2 = cur2.next;
			}
		}

		

		//System.out.print(cur1.data);
		//System.out.print(cur1.data);
		//System.out.print(cur2.data);
		return merge;
	}
} //END OF LINKEDLIST CLASS DEFINITION

// NODE CLASS
 class Node<T>
{
  T data;
  Node<T> next;

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
	  return "" + data; // stringify the data
  } 
	 
} //EOF
