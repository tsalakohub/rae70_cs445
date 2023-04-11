import java.io.*;
import java.util.*;

public class CDLL_JosephusList<T> {
	private CDLL_Node<T> head; // pointer to the front (first) element of the list
	private int count = 0;

	// private Scanner kbd = new Scanner(System.in); // FOR DEBUGGING. See
	// executeRitual() method
	public CDLL_JosephusList() {
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE

	public CDLL_JosephusList(String infileName) throws Exception {
		BufferedReader infile = new BufferedReader(new FileReader(infileName));
		while (infile.ready()) {
			@SuppressWarnings("unchecked")
			T data = (T) infile.readLine(); // CAST CUASES WARNING (WHICH WE CONVENIENTLY SUPPRESS)
			insertAtTail(data);
		}
		infile.close();
	}

	// ########################## Y O U W R I T E / F I L L I N T H E S E M E T H O
	// D S ########################

	// TACK ON NEW NODE AT END OF LIST
	public void insertAtTail(T data) {
		CDLL_Node<T> newT = new CDLL_Node<T>(data);
		if (head == null) {
			head = newT;
			newT.prev = newT;
			newT.next = newT;
			return;
		}

		CDLL_Node<T> cur = head;

		while (cur.next != head) {
			cur = cur.next;
		}

		cur.next = newT;
		newT.next = head;
		newT.prev = cur;
		head.prev = newT;
	}

	public int size() {
		count = 0;
		CDLL_Node<T> temp = head;

		if (head == null) {
			return 0;
		}
		do {
			temp = temp.next;
			count++;
		} while (temp != head);

		return count;
	}

	// RETURN REF TO THE FIRST NODE CONTAINING KEY. ELSE RETURN NULL
	public CDLL_Node<T> search(T key) {
		CDLL_Node<T> cur = new CDLL_Node<T>();
		cur = head;

		if (head == null) {
			return null;
		} else {
			do {
				if (cur.data.equals(key)) {
					return cur;
				}
				cur = cur.next;
			} while (cur != this.head);
			return null;
		}
	}

	// RETURNS CONATENATION OF CLOCKWISE TRAVERSAL

	public String toString() {
		String toString = "";
		CDLL_Node<T> cur = head;
		if (head == null) {
			return " ";
		}
		do {
			toString += cur.data;
			if (cur.next != head) {
				toString += "<=>";
			}
			cur = cur.next;

		} while (cur != head);
		return toString;

	}

	void removeNode(CDLL_Node<T> deadNode) {
		if (head == null) {
			return;
		}
		if (size() == 1 && head.equals(deadNode)) {
			head = null;
			return;
		}

		deadNode.next.prev = deadNode.prev;
		deadNode.prev.next = deadNode.next;

	}

	public void executeRitual(T first2Bdeleted, int skipCount) {
		if (skipCount > 0) {
			String move = "CLOCKWISE";
		} else {
			String move = "COUNTER_CLOCKWISE";
		}
		if (size() <= 1)
			return;
		CDLL_Node<T> curr = search(first2Bdeleted);
		if (curr == null)
			return;

		// OK THERE ARE AT LEAST 2 NODES AND CURR IS SITING ON first2Bdeleted
		do {
			int i = 0;
			CDLL_Node<T> deadNode = curr;
			T deadName = deadNode.getData();

			System.out.println("stopping on " + curr.data + " to delete " + curr.data);

			// BEFORE DOING ACTUAL DELETE DO THESE TWO THINGS

			// 1: you gotta move that curr off of the deadNode.
			// if skipCount poitive do curr=curr.next esle do curr=curr.prev
			if (skipCount > 0) {
				curr = curr.next;
			} else {
				curr = curr.prev;
			}
			// 2: check to see if HEAD is pointing to the deadnode.
			// If so make head=curr
			if (head.equals(deadNode)) {
				head = curr;
			}

			// NOW DELETE THE DEADNODE
			removeNode(deadNode);

			System.out.println("deleted. list now: " + toString()); // toString prints the

			// if the list size has reached 1 return YOU ARE DONE. RETURN RIGHT HERE
			if (size() <= 1)
				return;

			if (skipCount > 0) {
				System.out.println(
						"resuming at " + curr.data + ", skipping " + curr.data + " " + "+" + " "
								+ (Math.abs(skipCount) - 1) + " " + "nodes"
								+ " " + "CLOCKWISE" + " " + "after");
			} else {

				System.out.println(
						"resuming at " + curr.data + ", skipping " + curr.data + " " + "+" + " "
								+ (Math.abs(skipCount) - 1) + " " + "nodes"
								+ " " + "COUNTER_CLOCKWISE" + " " + "after");
			}
			// System.out.print(size());
			// write loop that advances curr pointer skipCount times (be sure of CLOCKWISE
			// or COUNTER)

			while (i < Math.abs(skipCount)) {
				if (skipCount > 0)
					curr = curr.next;
				else
					curr = curr.prev;
				i++;
			}

			// OPTIONAL HERE FOR DEBUGGING TO MAKE IT STOP AT BOTTOM OF LOOP
			// Scanner kbd = new Scanner(System.in);
			// String junk = kbd.nextLine();

		} while (size() > 1); // ACTUALLY COULD BE WHILE (TRUE) SINCE WE RETURN AS SOON AS SIZE READES 1

	}

} // END CDLL_LIST CLASS

class CDLL_Node<T> {
	T data;
	CDLL_Node<T> prev, next; // EACH CDLL_Node PTS TO ITS PREV & NEXT

	CDLL_Node() {
		this(null, null, null); // 3 FIELDS TO INIT
	}

	CDLL_Node(T data) {
		this(data, null, null);
	}

	CDLL_Node(T data, CDLL_Node<T> prev, CDLL_Node<T> next) {
		setData(data);
		setPrev(prev);
		setNext(next);
	}

	T getData() {
		return data;
	}

	CDLL_Node<T> getPrev() {
		return prev;
	}

	CDLL_Node<T> getNext() {
		return next;
	}

	void setData(T data) {
		this.data = data;
	}

	void setNext(CDLL_Node<T> next) {
		this.next = next;
	}

	void setPrev(CDLL_Node<T> prev) {
		this.prev = prev;
	}

	public String toString() {
		return "" + getData();
	}

} // EOF