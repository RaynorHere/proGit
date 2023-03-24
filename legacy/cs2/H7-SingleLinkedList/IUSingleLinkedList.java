import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Single-linked node implementation of IndexedUnsortedList. An Iterator with
 * working remove() method is implemented, but ListIterator is unsupported.
 * 
 * @author Jim "JCIII" Crowell
 * 
 * @param <T> type to store
 * 
 * @version 1.25, 03/23/21
 */
public class IUSingleLinkedList<T> implements IndexedUnsortedList<T> {
	private DNode<T> head, tail;
	private int size;
	private int modCount;

	/** Creates an empty list */
	public IUSingleLinkedList() {
		head = tail = null;
		size = 0;
		modCount = 0;
	}

	// Create a new head of the list, containing the passed element
	public void addToFront(T element) {
		// This happens no matter what; we need to make the new node
		DNode<T> newAdd = new DNode<T>(element);

		// Special Case: empty list
		if (size == 0) {
			head = tail = newAdd;
		}

		else {
			// If everything cool
			newAdd.setNext(head);
			head = newAdd;
		}

		size++;
		modCount++;
	}

	// Create a new tail of the list, containing the past element
	public void addToRear(T element) {
		DNode<T> newAdd = new DNode<T>(element);

		// Special Case: empty list
		if (size == 0) {
			head = tail = newAdd;
		} else {
			tail.setNext(newAdd);
			tail = newAdd;
		}

		size++;
		modCount++;
	}

	// Functionally, add() is identical to addToRear(), so we can just call that
	// function
	public void add(T element) {
		addToRear(element);
	}

	// Walk through the list up to the element indicated, and insert the passed
	// element behind that element
	public void addAfter(T element, T target) {
		// We'll need two nodes: one to track placement, and the other to be the actual
		// addition
		DNode<T> newAdd = new DNode<T>(element);
		DNode<T> nodicTrack = new DNode<T>();

		// Special Case: empty list
		if (size == 0)
			throw new NoSuchElementException();

		// We'll need to track the "found" status of our target
		boolean sighted = false;

		// Set tracker to its initial position
		nodicTrack = head;

		while (nodicTrack != null && !sighted) {
			if (nodicTrack.getElement().equals(target))
				sighted = true;
			else
				nodicTrack = nodicTrack.getNext();
		}

		if (!sighted)
			throw new NoSuchElementException();
		else {
			// Special Case: target is current tail
			if (nodicTrack == tail) {
				addToRear(element);
			}

			// Special Case: target is current head, and it's a list of size 2+
			else if (nodicTrack == head) {
				newAdd.setNext(head.getNext());
				head.setNext(newAdd);
				size++;
				modCount++;
			}

			// All other cases should be mid-list-located
			else {
				newAdd.setNext(nodicTrack.getNext());
				nodicTrack.setNext(newAdd);

				size++;
				modCount++;
			}
		}

	}

	// Insert a new node containing the passed element into the list at the
	// indicated index
	public void add(int index, T element) {
		// Initial argument validity check
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();

		// Special Case: empty list AND passed index is not 0, which is totally valid
		if (size == 0 && index != 0)
			throw new IndexOutOfBoundsException();

		// Special Case: index passed is head
		if (index == 0) {
			addToFront(element);
		}

		// Special Case: index passed is the same as the next open spot
		else if (index == size) {
			addToRear(element);
		}

		else {
			// We're gonna need a few tools
			int indInquire = 0;
			DNode<T> newAdd = new DNode<T>(element);
			DNode<T> nodicTrack = new DNode<T>();

			// Runner to his/her mark
			nodicTrack = head;

			// Adding at index is going to mean stopping the track at the index before, to
			// attach that next to the new node
			while (indInquire < index - 1) {
				nodicTrack = nodicTrack.getNext();
				indInquire++;
			}

			// We should now be at the prior node, so, first, we attach newAdd's next
			// Otherwise we'll lose this reference
			newAdd.setNext(nodicTrack.getNext());

			// NOW we can attach newAdd to be the next of the index-1 node
			nodicTrack.setNext(newAdd);

			size++;
			modCount++;
		}

	}

	// "Delete" head, if it exists, by reassigning it
	public T removeFirst() {
		// Special Case: empty list
		if (size == 0)
			throw new NoSuchElementException();

		// Special Case: single-element list
		if (head == tail) {
			T choppingBlock = head.getElement();
			head = tail = null;
			size--;
			modCount++;
			return choppingBlock;
		}

		T choppingBlock = head.getElement();
		head = head.getNext();

		// I'm not gonna lie to you; it snuck up on me how easy that was. So... it's
		// probably wrong.

		size--;
		modCount++;
		return choppingBlock;
	}

	// Remove the node currently labeled as "tail", if there is one, and then
	// reassign "tail"
	public T removeLast() {
		// Since we need to return something, we'll need one of these
		T retVal;
		if (size == 0)
			throw new NoSuchElementException();

		// Special Case: single-element list
		if (head == tail) {
			retVal = tail.getElement();
			head = tail = null;
			size--;
			modCount++;
			return retVal;
		}

		// We're still gonna need this guy to walk through our list up to behind tail
		// Since there's no "backward" function, we still have to walk FORWARD up to
		// that point
		DNode<T> nodicTrack = new DNode<T>();
		nodicTrack = head;

		// This should position us directly behind tail
		while (nodicTrack.getNext() != tail) {
			nodicTrack = nodicTrack.getNext();
		}

		// Now, we snip out tail by removing its preceding node's reference to it
		// After we pull out whatever's sitting in tail right now for retVal
		// And yes, I do know that, ostensibly, I could fill retVal by just saying it
		// equals tail.getElement()
		// For some reason, I really didn't want to
		retVal = nodicTrack.getNext().getElement();
		nodicTrack.setNext(null);
		tail = nodicTrack;

		size--;
		modCount++;
		return retVal;
	}

	// Walk through the list until finding the element passed, and remove the node
	// containing it
	// Else, throw a NoSuchElementException if it isn't found
	public T remove(T element) {
		if (size == 0) {
			throw new NoSuchElementException();
		}

		boolean found = false;
		DNode<T> previous = null;
		DNode<T> current = head;

		while (current != null && !found) {
			if (element.equals(current.getElement())) {
				found = true;
			} else {
				previous = current;
				current = current.getNext();
			}
		}

		if (!found) {
			throw new NoSuchElementException();
		}

		if (size() == 1) { // only node
			head = tail = null;
		} else if (current == head) { // first node
			head = current.getNext();
		} else if (current == tail) { // last node
			tail = previous;
			tail.setNext(null);
		} else { // somewhere in the middle
			previous.setNext(current.getNext());
		}

		size--;
		modCount++;

		return current.getElement();
	}

	// Walk list to a specific point, and "remove" the node from the list by
	// attaching ITS next node to the node behind it
	public T remove(int index) {
		// Validity check
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();

		if (size == 0)
			throw new IndexOutOfBoundsException();

		// Usual initial setup
		int stepCounter = 0;
		DNode<T> nodicTrack = new DNode<T>();
		T retVal;
		nodicTrack = head;

		// Special Case: single-element list
		if (size == 1) {
			retVal = head.getElement();
			head = tail = null;
			size--;
			modCount++;
			return retVal;
		}

		// The following two scenarios don't work with the function I've built, because
		// my function is predicated on
		// "jumping" to the node ahead from the node behind. Head has no prior node, and
		// Tail has no following node

		// Special Case: removing the 0th node
		if (index == 0) {
			retVal = head.getElement();
			head = head.getNext();
			size--;
			modCount++;
			return retVal;
		}

		// Special Case: removing the tail node
		if (index == size - 1) {
			nodicTrack = head;

			// This should walk us up right behind tail
			while (!nodicTrack.getNext().equals(tail)) {
				nodicTrack = nodicTrack.getNext();
			}

			// Which we now assign to be the new tail, bing-bong-boom
			retVal = tail.getElement();
			tail = nodicTrack;
			nodicTrack.setNext(null);
			size--;
			modCount++;
			return retVal;
		}

		// Otherwise (and we don't need an "else", due to the "return" statement):

		// Roll through the list until we reach the node just behind the indicated
		// index, which we will then "jump"
		while (stepCounter < index - 1) {
			nodicTrack = nodicTrack.getNext();
			stepCounter++;
		}

		// Now that we're here, we first grab the element we're removing
		retVal = nodicTrack.getNext().getElement();

		// And NOW, we can clip the indicated node out of the list by removing the only
		// next value that refers to it
		nodicTrack.setNext(nodicTrack.getNext().getNext());

		size--;
		modCount++;
		return retVal;
	}

	// Change the node at the indicated index to hold the indicated element, if the
	// node is extant
	public void set(int index, T element) {
		// Validity check
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();

		int stepCounter = 0;
		DNode<T> nodicTrack = new DNode<T>();
		nodicTrack = head;

		// This should bring us up to the indicated node
		while (stepCounter < index) {
			nodicTrack = nodicTrack.getNext();
			stepCounter++;
		}

		// Now, we change the contents of this node, do some housekeeping, and we're
		// done
		nodicTrack.setElement(element);

		modCount++;

	}

	// Walk through the list to a given point, and return the element at that point
	public T get(int index) {
		if (size == 0)
			throw new IndexOutOfBoundsException();

		// Validity check; "size" is invalid because it marks the next open spot and
		// there's nothing there
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();

		// Make a constant time function where we can
		if (size != 0 && index == 0) {
			T retVal = head.getElement();
			return retVal;
		}

		// Ditto; size - 1 being the tail node
		else if (size != 0 && index == size - 1) {
			T retVal = tail.getElement();
			return retVal;
		}

		else {
			// Our usual spelunking tools
			DNode<T> nodicTrack = new DNode<T>();
			T retVal;
			int stepCounter = 0;

			// Starting line
			nodicTrack = head;

			// This should get us to our desired location
			while (stepCounter < index) {
				nodicTrack = nodicTrack.getNext();
				stepCounter++;
			}

			retVal = nodicTrack.getElement();

			return retVal;
		}

	}

	// Walk the array, return the number in the line of the node containing the
	// element, and -1 if it isn't found
	public int indexOf(T element) {
		int indyQuery = -1;
		int stepCounter = 0;
		DNode<T> nodicTrack = new DNode<T>();
		nodicTrack = head;

		while (nodicTrack != null && indyQuery == -1) {
			if (nodicTrack.getElement().equals(element)) {
				indyQuery = stepCounter;
			} else {
				nodicTrack = nodicTrack.getNext();
				stepCounter++;
			}

		}

		return indyQuery;
	}

	// Return whatever is marked as the head of the list, if extant
	public T first() {
		// Special Case: the same one we basically always do first
		if (size == 0)
			throw new NoSuchElementException();

		// And the only reason we do THIS is to preserve encapsulation
		T retVal = head.getElement();

		return retVal;
	}

	// Return whatever is marked as the tail of list, if extant
	public T last() {
		// Special Case: duh
		if (size == 0)
			throw new NoSuchElementException();

		T retVal = tail.getElement();

		return retVal;
	}

	// Walk through the list and see if we find a node that contains something what
	// looks like the passed element
	public boolean contains(T target) {
		if (size == 0)
			return false;

		// We need a boolean to track whether or not we've found it, and a node to walk
		// the list
		boolean aHa = false;
		DNode<T> nodicTrack = new DNode<T>();
		nodicTrack = head;

		while (!aHa && nodicTrack != null) {
			if (nodicTrack.getElement().equals(target))
				aHa = true;
			else
				nodicTrack = nodicTrack.getNext();
		}

		return aHa;
	}

	// If size is 0, there ain't nothin' in the list
	public boolean isEmpty() {
		return (size == 0);
	}

	// Make a copy of the size variable and return it
	public int size() {
		int fallGuy = size;

		return fallGuy;
	}

	// Create a string of the list's elements and print them out
	public String toString() {
		StringBuilder stringKing = new StringBuilder();

		// I think the easiest way to do this is just to make a special case for an
		// empty list
		if (size == 0) {
			stringKing.append("[]");
			return stringKing.toString();
		}

		DNode<T> stringSlinger = new DNode<T>();
		stringSlinger = head;

		// This is gonna be a little weird. First step, append a guaranteed open bracket
		stringKing.append("[");

		while (stringSlinger != null) {
			// Every node, StringBuilder grabs the String'd-out contents, and attaches a
			// grammatical separator
			stringKing.append(stringSlinger.getElement().toString());
			stringKing.append(", ");
			stringSlinger = stringSlinger.getNext();
		}

		// Outside the loop, we should have run through the entire list, so clip off the
		// last comma and space
		stringKing.delete(stringKing.length() - 2, stringKing.length());

		// And attach the back bracket
		stringKing.append("]");

		// And we're done
		return stringKing.toString();

	}

	// This is extrapolated on in the private class below
	public Iterator<T> iterator() {
		return new SLLIterator();
	}

	// No! Down! NOT time for you, yet!
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	// Back! Back, I say!
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}

	/** Iterator for IUSingleLinkedList */
	private class SLLIterator implements Iterator<T> {
		private DNode<T> nextNode;
		private int iterModCount;

		// Flag to (dis-/en-)able remove() function
		boolean remSleep = true;

		/** Creates a new iterator for the list */
		public SLLIterator() {
			nextNode = head;
			iterModCount = modCount;
		}

		// Check if there are any elements ahead of the iterator's current position
		public boolean hasNext() {
			// We run this every time, because if the iterator and the list aren't in sync,
			// it needs to EXPLOOOOOOOOODE
			// I could write a function for it, but this is such a brief write that I'm not
			// gonna worry about it
			if (iterModCount != modCount)
				throw new ConcurrentModificationException();

			// If I'm right, the only check we need to do is if the thing ahead of the
			// pointer has anything in it
			// And I think we can invert it by attaching a bang to the statement
			// If so, if the iterator is at the end, under this config, (nextNode == null)
			// will be true, the ! will flip it, and
			// so hasNext() will return false.
			return !(nextNode == null);
		}

		// Advance the iterator one position, and return the element it passed
		public T next() {
			// This will allow us to simultaneously check if there's anything ahead of us
			// AND do a version check
			if (!hasNext())
				throw new NoSuchElementException();

			// Past that, we'll need a variable to return the contents
			T retVal = nextNode.getElement();

			// Then, we advance the iterator
			nextNode = nextNode.getNext();

			// THEN, we deactivate remSleep, allowing a call of remove() after any call of
			// next()
			remSleep = false;

			return retVal;
		}

		// Remove the last element the iterator most recently passed (i.e., the element
		// immediately behind it), if it has
		// Else, return an illegal state
		public void remove() {
			if (iterModCount != modCount)
				throw new ConcurrentModificationException();

			// Here's our "no consecutive remove() calls" check
			if (remSleep)
				throw new IllegalStateException();

			// "Removing" in this case is gonna mean attaching the node that is behind the
			// node that's behind the iterator to the
			// node ahead of the iterator. To do this, we'll need our trusty tracker
			DNode<T> nodicTrack = new DNode<T>();
			nodicTrack = head;

			// You'll recall from our remove(index) function that we needed special cases
			// for 0th and final node removals.
			// Single-size lists, too

			// Special Case: single-element list
			if (head == tail) {
				head = tail = null;
				size--;
				modCount++;
				iterModCount++;
				remSleep = true;
			}

			// Special Case: removing head node
			// If head's getNext() is the same as nextNode, then the iterator is just past
			// head, and that's what's being removed
			else if (head.getNext().equals(nextNode)) {
				head = nextNode;
				size--;
				modCount++;
				iterModCount++;
				remSleep = true;

			}

			// Special Case: removing tail node
			// This one needs a setup because there's no nextNode to call; it's a void after
			// Tail
			else if (!hasNext()) {
				// Walk up to just behind Tail
				while (!nodicTrack.getNext().equals(tail)) {
					nodicTrack = nodicTrack.getNext();
				}
				// Set the new tail
				nodicTrack.setNext(null);
				tail = nodicTrack;
				size--;
				modCount++;
				iterModCount++;
				remSleep = true;
			}

			// All other cases
			else {

				// The node ahead of the iterator should be referenced by nextNode. Therefore,
				// we should be able to
				// walk the tracker node up to the point where its getNext().getNext(), or two
				// nodes ahead comes out to nextNode
				while (nodicTrack.getNext().getNext() != nextNode) {
					nodicTrack = nodicTrack.getNext();
				}

				// Now that we should be located two nodes behind the iterator, we'll change
				// this node's next to nextNode, cutting
				// the last-passed node out of the list
				nodicTrack.setNext(nextNode);

				// Then we flip remSleep on, so remove() can't be called twice
				remSleep = true;

				// Vital stats changes
				size--;
				modCount++;
				iterModCount++;

				// I could have sworn this was supposed to return a value, but if it's a void
				// here, I'm not going to worry about it
			}
		}
	}
}
