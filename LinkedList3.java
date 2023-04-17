// 2021 FALL CS 445 LAB #3  STUDENT STARTER FILE

import java.io.*;
import java.util.*;

public class LinkedList3<T extends Comparable<T>>
{
	private Node<T> head;  // pointer to the front (first) element of the list
	private Node<T> tail;  // pointer to the last elem of the list ( caboose or tail node)

	public LinkedList3()
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
		
	}
	public LinkedList<T> merge( LinkedList<T> other )  // think 'sorted union' but only 1 pass allowed
	{
		return null;
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