// 2021 FALL CS 445 LAB #4  STUDENT STARTER FILE

import java.io.*;
import java.util.*;



public class LL_Recursive<T>
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LL_Recursive()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// INSERTS NEW NODE AT FRONT PUSHING EXISTING NODES BACK ONE PLACE
	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

 	
	// #####  W R I T E  (O R  R E-W R I T E)  T H E S E   M E T H O D S   R E C U R S I V E L Y ####


 	// MUST USE (CALL) search() METHOD IN THIS CODE TO DETERMINE THE RETURN VALUE
 	// NO LOOPS ALLOWED NO RECURSION ALLOWED.  THE SEARCH WILL BE RECURSIVE THOUGH
 	public boolean contains( T key )
 	{
 		//cal search if null return false
		return search(key) != null; //JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}

	// YOU MUST WRITE THIS USING RECURSION
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public void insertAtTail(T data)
	{
		if(head == null){
			insertAtFront(data);
			return; 
		}
		insertAtTailHelper(head, data);
		// YOUR CODE HERE. MUST USE insertAtFront() IN BASE CASE
	}
	private Node<T> insertAtTailHelper(Node<T> head, T data){
		if(head.next == null){
			head.next = new Node<T>(data, null);
			return head.next;
		}else
		{
			return insertAtTailHelper(head.next, data);
		}
		//return head;
		
	}
	// WE will illustrate this in class today 2/6/2022
	public int size()
	{
		return sizeHelper( head ); 
	}
	private int sizeHelper( Node<T> head )
	{
		if (head==null) return 0;
		return 1 + sizeHelper(head.next);
	}
	
	// USE THE TOSTRING AS OUR PRINT.  ***MUST RE-WRITE USING RECURSION***
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE

	private String toStringHelper(Node<T> cur){
		if(cur.next != null){
			return cur.data  + " -> "+ toStringHelper(cur.next);
		}else{
			return (String) cur.data;
		}
	}
	public String toString()
	{
		return toStringHelper(head);
	}

	// MUST BE RECURSIVE. YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	
	
	public Node<T> search( T key )
	{
		return searchHelper(head, key); //JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}
	
	private Node<T> searchHelper(Node<T> head ,T key){
		if(head == null){
			return head;
		}
		if(head.data.equals(key)){
			return head;
		}
		return searchHelper(head.next, key);
	}
} //END OF LL_Recursive CLASS


///////////////////////////////////////////////////////////////////////////////////////////////////

class Node<T>
{ T data;
  Node<T> next;
  Node() { this( null, null ); }
  Node(T data){this( data, null ); }
  Node(T data, Node<T> next) { this.data=data; this.next=next; }
  public String toString() { return ""+data; }
} //END OF NODE CLASS