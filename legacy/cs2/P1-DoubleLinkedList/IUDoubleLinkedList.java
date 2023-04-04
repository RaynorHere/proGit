import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Double-linked node implementation of IndexedUnsortedList. This list aims to
 * support ListIterator
 * 
 * @author Jim "JCIII" Crowell
 * 
 * @param <T> type to store
 * 
 * @version 2.2, 04/08/21
 */
public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {
	private DNode<T> head, tail;
	private int size;
	private int modCount;

	/** Creates an empty list */
	public IUDoubleLinkedList() {
		head = tail = null;
		size = 0;
		modCount = 0;
	}

	// Create a new head of the list, containing the passed element
	public void addToFront(T element) {

		// Should be able to spawn an iterator behind head and just add the element?
		// If you can't tell, reader, this is me juuuuust starting to rewrite functions
		// as in terms of the iterator. I couldn't leave it alone
		DLLIterator scamp = new DLLIterator(0);
		scamp.add(element);

//		// This happens no matter what; we need to make the new node
//		DNode<T> newAdd = new DNode<T>(element);
//
//		// Special Case: empty list
//		if (size == 0) {
//			head = tail = newAdd;
//		}
//
//		else {
//			// If everything cool
//			newAdd.setNext(head);
//			head.setPrevious(newAdd);
//			head = newAdd;
//		}
//
//		size++;
//		modCount++;
	}

	// Create a new tail of the list, containing the past element
	public void addToRear(T element) {

		// "Rear" means the next open spot, which size points at, so spawn an iterator
		// at size....
		DLLIterator scamp = new DLLIterator(size);
		scamp.add(element);

//		DNode<T> newAdd = new DNode<T>(element);
//
//		// Special Case: empty list
//		if (size == 0) {
//			head = tail = newAdd;
//		} else {
//			tail.setNext(newAdd);
//			newAdd.setPrevious(tail);
//			tail = newAdd;
//		}
//
//		size++;
//		modCount++;
	}

	// Functionally, add() is identical to addToRear(), so we can just call that
	// function
	public void add(T element) {
		addToRear(element);
	}

	// Walk through the list up to the element indicated, and insert the passed
	// element behind that element
	public void addAfter(T element, T target) {

		// This one will take a little more work
		DLLIterator scamp = new DLLIterator();

		boolean elloGorgeous = false;

		while (scamp.hasNext() && !elloGorgeous) {
			if (scamp.next().equals(target))
				elloGorgeous = true;
		}

		// Never found it?
		if (!elloGorgeous)
			throw new NoSuchElementException();

		// If we're here, then the iterator returned it. And the loop stopped. So... we
		// can just run add, and that's that
		scamp.add(element);

//		// We'll need two nodes: one to track placement, and the other to be the actual
//		// addition
//		DNode<T> newAdd = new DNode<T>(element);
//		DNode<T> nodicTrack = new DNode<T>();
//
//		// Special Case: empty list
//		if (size == 0)
//			throw new NoSuchElementException();
//
//		// We'll need to track the "found" status of our target
//		boolean elloPoppet = false;
//
//		// Set tracker to its initial position
//		nodicTrack = head;
//
//		while (nodicTrack != null && !elloPoppet) {
//			if (nodicTrack.getElement().equals(target))
//				elloPoppet = true;
//			else
//				nodicTrack = nodicTrack.getNext();
//		}
//
//		if (!elloPoppet)
//			throw new NoSuchElementException();
//		else {
//			// Special Case: target is current tail
//			if (nodicTrack == tail) {
//				addToRear(element);
//			}
//
//			// Special Case: target is current head, and it's a list of size 2+
//			else if (nodicTrack == head) {
//				// This eradicates dot functions following dot functions; it's ugly
//				DNode<T> headFollower;
//				headFollower = head.getNext();
//
//				// Insert new node in front of what WAS behind head
//				newAdd.setNext(headFollower);
//				headFollower.setPrevious(newAdd);
//
//				// Attach head
//				head.setNext(newAdd);
//				newAdd.setPrevious(head);
//
//				// Sweeping up
//				size++;
//				modCount++;
//			}
//
//			// All other cases should be mid-list-located
//			else {
//				DNode<T> rightHand = new DNode<T>();
//				rightHand = nodicTrack.getNext();
//				newAdd.setNext(rightHand);
//				rightHand.setPrevious(newAdd);
//				nodicTrack.setNext(newAdd);
//				newAdd.setPrevious(nodicTrack);
//
//				size++;
//				modCount++;
//			}
//		}
	}

	// Insert a new node containing the passed element into the list at the
	// indicated index
	public void add(int index, T element) {

		// The iterator's overloaded constructor makes this one another cakewalk
		DLLIterator scamp = new DLLIterator(index);
		scamp.add(element);

//		// Initial argument validity check
//		if (index < 0 || index > size)
//			throw new IndexOutOfBoundsException();
//
//		// Special Case: empty list AND passed index is not 0, which is totally valid
//		if (size == 0 && index != 0)
//			throw new IndexOutOfBoundsException();
//
//		// Special Case: index passed is head
//		if (index == 0) {
//			addToFront(element);
//		}
//
//		// Special Case: index passed is the same as the next open spot
//		else if (index == size) {
//			addToRear(element);
//		}
//
//		else {
//			// We're gonna need a few tools
//			int indInquire = 0;
//			DNode<T> newAdd = new DNode<T>(element);
//			DNode<T> nodicTrack = new DNode<T>();
//			DNode<T> rightHand = new DNode<T>();
//
//			// Runner to his/her mark
//			nodicTrack = head;
//
//			// Adding at index is going to mean stopping the track at the index before, to
//			// attach that next to the new node
//			while (indInquire < index - 1) {
//				nodicTrack = nodicTrack.getNext();
//				indInquire++;
//			}
//
//			// We should now be at the prior node, so, first, we'll mark that node with our
//			// other placeholder and set it up it and the new guy's mutuals
//			rightHand = nodicTrack.getNext();
//			newAdd.setNext(rightHand);
//			rightHand.setPrevious(newAdd);
//
//			// NOW we can attach newAdd to be the next of the index-1 node and vice versa
//			nodicTrack.setNext(newAdd);
//			newAdd.setPrevious(nodicTrack);
//
//			size++;
//			modCount++;
//		}

	}

	// "Delete" head, if it exists, by reassigning it
	public T removeFirst() {

		// Hm. This one's a little different. Ain't void, for starters. Further, we'll
		// have to advance the iterator to target the right node. Still... If I've coded
		// this thing correctly, it'll still behave, and it'll still throw the 
		// appropriate exceptions at the appropriate times. Let's see if I'm right
		DLLIterator scamp = new DLLIterator();

		// Remove() is a void function, so we'll need to capture what we're pulling out
		// when we call next()
		T choppingBlock = scamp.next();
		scamp.remove();
		return choppingBlock;

//		// Special Case: empty list
//		if (size == 0)
//			throw new NoSuchElementException();
//
//		// Special Case: single-element list
//		if (head == tail) {
//			T choppingBlock = head.getElement();
//			head = tail = null;
//			size--;
//			modCount++;
//			return choppingBlock;
//		}
//
//		T choppingBlock = head.getElement();
//		DNode<T> rightHand = head.getNext();
//		rightHand.setPrevious(null);
//		head = rightHand;
//
//		size--;
//		modCount++;
//		return choppingBlock;
	}

	// Remove the node currently labeled as "tail", if there is one, and then
	// reassign "tail"
	public T removeLast() {

		// All right, remFirst() seems to work. So I should be able to do the reverse
		// here
		DLLIterator scamp = new DLLIterator(size);
		T choppingBlock = scamp.previous();
		scamp.remove();

		return choppingBlock;

//		// Since we need to return something, we'll need one of these
//		T retVal;
//		if (size == 0)
//			throw new NoSuchElementException();
//
//		// Special Case: single-element list
//		if (head == tail) {
//			retVal = tail.getElement();
//			head = tail = null;
//			size--;
//			modCount++;
//			return retVal;
//		}
//
//		// Now that we have a previous function to play with, we'll just set up right
//		// behind the current tail...
//		DNode<T> nodicTrack = new DNode<T>();
//		nodicTrack = tail.getPrevious();
//
//		// Now, we snip out tail by removing its preceding node's reference to it
//		// After we pull out whatever's sitting in tail right now for retVal
//		// And yes, I do know that, ostensibly, I could fill retVal by just saying it
//		// equals tail.getElement()
//		// For some reason, I really didn't want to
//		retVal = tail.getElement();
//		nodicTrack.setNext(null);
//		tail = nodicTrack;
//
//		size--;
//		modCount++;
//		return retVal;
	}

	// Walk through the list until finding the element passed, and remove the node
	// containing it. Else, throw a NoSuchElementException if it isn't found
	public T remove(T element) {

		// This should be simple enough. Gonna look a lot like the addAfter() function
		DLLIterator scamp = new DLLIterator();
		boolean elloGorgeous = false;
		T choppingBlock = null;

		while (scamp.hasNext() && !elloGorgeous) {
			if (scamp.next().equals(element)) {
				elloGorgeous = true;

				// This feels weird, but because it only triggers if the listIter finds
				// the thing we're looking for, I think it's excusable. Besides, I couldn't
				// think of another way to pull out the element we're removing to return,
				// short of setting chopBlock equal to scamp.previous after we'd found
				// element. I think that would work, but it's also redundant
				choppingBlock = element;
			}
		}

		if (!elloGorgeous)
			throw new NoSuchElementException();

		scamp.remove();

		// We don't want this to return null, but the way it's set up, I believe it
		// CAN'T, because chopBlock only stays null if we don't find the thing
		// we're looking for. If we don't find the thing we're looking for, 
		// the NSE Exception above triggers. Otherwise, we've found it
		// and set chopBlock equal to the element that was originally passed
		return choppingBlock;

//		if (size == 0) {
//			throw new NoSuchElementException();
//		}
//
//		boolean elloPoppet = false;
//		DNode<T> previous = null;
//		DNode<T> current = head;
//		DNode<T> next = null;
//		T choppingBlock;
//
//		while (current != null && !elloPoppet) {
//			if (element.equals(current.getElement())) {
//				elloPoppet = true;
//			} else {
//				previous = current;
//				current = current.getNext();
//			}
//		}
//
//		if (!elloPoppet) {
//			throw new NoSuchElementException();
//		}
//
//		// Only node contains target
//		if (size() == 1) {
//			head = tail = null;
//		}
//		// Head contains target
//		else if (current == head) {
//			next = head.getNext();
//			next.setPrevious(null);
//			head = next;
//		}
//		// Tail contains target
//		else if (current == tail) {
//			previous.setNext(null);
//			tail = previous;
//		}
//		// Commoner
//		else {
//			next = current.getNext();
//			previous = current.getPrevious();
//			next.setPrevious(previous);
//			previous.setNext(next);
//		}
//
//		size--;
//		modCount++;
//
//		choppingBlock = current.getElement();
//		return choppingBlock;
	}

	// Walk list to a specific point, and "remove" the node from the list by
	// attaching ITS next node to the node behind it
	public T remove(int index) {

		// Validity check
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();

		// I love these index ones
		DLLIterator scamp = new DLLIterator(index);
		T choppingBlock = scamp.next();
		scamp.remove();
		return choppingBlock;

//		if (size == 0)
//			throw new IndexOutOfBoundsException();
//
//		// Usual initial setup
//		int stepCounter;
//		DNode<T> nodicTrack = new DNode<T>();
//		DNode<T> rightHand = new DNode<T>();
//		DNode<T> leftHand = new DNode<T>();
//		T choppingBlock;
//
//		// Special Case: single-element list
//		if (size == 1) {
//			choppingBlock = head.getElement();
//			head = tail = null;
//			size--;
//			modCount++;
//			return choppingBlock;
//		}
//
//		// The following two scenarios don't work with the function I've built, because
//		// my function is predicated on
//		// "jumping" to the node ahead from the node behind. Head has no prior node, and
//		// Tail has no following node
//
//		// Special Case: removing the 0th node
//		if (index == 0) {
//			choppingBlock = head.getElement();
//			head = head.getNext();
//			head.setPrevious(null);
//			size--;
//			modCount++;
//			return choppingBlock;
//		}
//
//		// Special Case: removing the tail node
//		if (index == size - 1) {
//			nodicTrack = tail;
//			leftHand = nodicTrack.getPrevious();
//
//			// Which we now assign to be the new tail, bing-bong-boom
//			choppingBlock = nodicTrack.getElement();
//			tail = leftHand;
//			leftHand.setNext(null);
//			size--;
//			modCount++;
//			return choppingBlock;
//		}
//
//		// Otherwise (and we don't need an "else", due to the "return" statement):
//
//			stepCounter = 0;
//			nodicTrack = head;
//			// Roll through the list until we reach the node just behind the indicated
//			// index, which we will then "jump"
//			while (stepCounter < index) {
//				nodicTrack = nodicTrack.getNext();
//				stepCounter++;
//			}
//
//			// Now that we're here, we first grab the element we're removing
//			choppingBlock = nodicTrack.getElement();
//
//			// And the sequentials
//			rightHand = nodicTrack.getNext();
//			leftHand = nodicTrack.getPrevious();
//
//			// And NOW, we just cut the indicated node out of the loop
//			rightHand.setPrevious(leftHand);
//			leftHand.setNext(rightHand);
//
//		size--;
//		modCount++;
//		return choppingBlock;
	}

	// Change the node at the indicated index to hold the indicated element, if the
	// node is extant
	public void set(int index, T element) {
		// Validity check
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();

		// I admit, I'm troubled by the fact that this and the previous function need
		// their old  validity checks in place or tests start failing. I'm not sure 
		// why that sort of check isn'tintegrated into the iterator. 
		// Maybe it COULD be and I just didn't, and leaving those checks in place
		// is just easier. Hrm. That's a slight disappointment. Maybe I'll screw 
		// with it more, later
		DLLIterator scamp = new DLLIterator(index);
		scamp.next();
		scamp.set(element);

//		DNode<T> nodicTrack = new DNode<T>();
//		int stepCounter;
//
//		// Same optimization as above; I guess I like a needless challenge
//		if (index <= size / 2) {
//		stepCounter = 0;
//		
//		nodicTrack = head;
//
//		// This should bring us up to the indicated node
//		while (stepCounter < index) {
//			nodicTrack = nodicTrack.getNext();
//			stepCounter++;
//		}
//		}
//		else {
//			// And here's the reverse, starting from the other end
//			nodicTrack = tail;
//			stepCounter = size - 1;
//			while (stepCounter > index) {
//				nodicTrack = nodicTrack.getPrevious();
//				stepCounter--;
//			}
//		}
//		// Now, we change the contents of this node, do some housekeeping, and we're
//		// done
//		nodicTrack.setElement(element);
//
//		modCount++;		

	}

	// Walk through the list to a given point, and return the element at that point
	public T get(int index) {
		if (size == 0)
			throw new IndexOutOfBoundsException();

		// Validity check; "size" is invalid because it marks the next open spot and
		// there's nothing there
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();

		// I almost feel bad, not having as verbose of comments to narrate what I'm
		// doing in this new-and-improved, listIterator-centric version. But... 
		// maybe that's a plus to people who ain't me
		DLLIterator scamp = new DLLIterator(index);
		T fallGuy = scamp.next();
		return fallGuy;

//		// Make a constant time function or two where we can
//		if (size != 0 && index == 0) {
//			T retVal = head.getElement();
//			return retVal;
//		}
//
//		// Ditto; size - 1 being the tail node
//		else if (size != 0 && index == size - 1) {
//			T retVal = tail.getElement();
//			return retVal;
//		}
//
//		else {
//			// Our usual spelunking tools
//			DNode<T> nodicTrack = new DNode<T>();
//			T retVal;
//			int stepCounter;
//			
//			// I think this is recognizable, by now
//			if (index <= size / 2) {
//				nodicTrack = head;
//				stepCounter = 0;
//				
//				// This should get us to our desired location
//				while (stepCounter < index) {
//					nodicTrack = nodicTrack.getNext();
//					stepCounter++;
//				}				
//			}
//			else {
//				nodicTrack = tail;
//				stepCounter = size - 1;
//				
//				while (stepCounter > index) {
//					nodicTrack = nodicTrack.getPrevious();
//					stepCounter--;
//				}
//			}
//			
//			// Either way, now we have our culprit
//			retVal = nodicTrack.getElement();
//
//			return retVal;
//		}

	}

	// Walk the array, return the number in the line of the node containing the
	// element, and -1 if it isn't found
	public int indexOf(T element) {

		// Well, these guys aren't going anywhere
		int indyQuery = -1;
		int stepCounter = 0;
		boolean gotcha = false;

		DLLIterator scamp = new DLLIterator();
		while (scamp.hasNext() && !gotcha) {
			if (scamp.next().equals(element)) {
				indyQuery = stepCounter;
				gotcha = true;
			} else
				stepCounter++;
		}

		// A "never found it" check is unnecessary, because indexOf is expected to
		// return -1 if the element is never found. Therefore, what-EVER happens,
		// we spit out indyQuery at the end; either it's the value of the
		// target, or it's the -1 we set it to to begin with
	
		return indyQuery;

//		DNode<T> nodicTrack = new DNode<T>();
//		nodicTrack = head;
//
//		while (nodicTrack != null && indyQuery == -1) {
//			if (nodicTrack.getElement().equals(element)) {
//				indyQuery = stepCounter;
//			} else {
//				nodicTrack = nodicTrack.getNext();
//				stepCounter++;
//			}
//
//		}
//
//		return indyQuery;
	}

	// Return whatever is marked as the head of the list, if extant
	public T first() {

		// I don't really see a reason to iter-ize first() or last()...

		// I think I can crunch this into five lines, though
		if (head != null) {
			T retVal = head.getElement();
			return retVal;
		} else
			throw new NoSuchElementException();
	}

	// Return whatever is marked as the tail of list, if extant
	public T last() {
		// Let's try that again
		if (tail != null) {
			T retVal = tail.getElement();
			return retVal;
		} else
			throw new NoSuchElementException();
	}

	// Walk through the list and see if we find a node that contains something what
	// looks like the passed element
	public boolean contains(T target) {
		if (size == 0)
			return false;

		// I don't think an iterator will change much, but it CAN be changed
		boolean gotcha = false;
		DLLIterator scamp = new DLLIterator();
		while (scamp.hasNext() && !gotcha) {
			if (scamp.next().equals(target))
				gotcha = true;
		}
		return gotcha;

//		// We need a boolean to track whether or not we've found it, and a node to walk
//		// the list
//		boolean aHa = false;
//		DNode<T> nodicTrack = new DNode<T>();
//		nodicTrack = head;
//
//		while (!aHa && nodicTrack != null) {
//			if (nodicTrack.getElement().equals(target))
//				aHa = true;
//			else
//				nodicTrack = nodicTrack.getNext();
//		}
//
//		return aHa;
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

		// Hmmmmm! Can we iter-ize toString? I rather suspect we can....
		// Gonna leave my size = 0 SC, though

		// I think the easiest way to do this is just to make a special case for an
		// empty list
		if (size == 0) {
			stringKing.append("[]");
			return stringKing.toString();
		}

		// So we still put the first bracket in
		stringKing.append("[");

		// Now, spawn an listIter, walk it on down
		DLLIterator scamp = new DLLIterator();
		while (scamp.hasNext()) {
			stringKing.append(scamp.next() + ", ");
		}

		// That should build the whole string, plus an extra comma and space, so clip
		// those, slap on a close bracket, bing-bang-boom; doneski
		stringKing.delete(stringKing.length() - 2, stringKing.length());
		stringKing.append("]");

		// And we're done
		return stringKing.toString();

//		DNode<T> stringSlinger = new DNode<T>();
//		stringSlinger = head;
//
//		// This is gonna be a little weird. First step, append a guaranteed open bracket
//		stringKing.append("[");
//
//		while (stringSlinger != null) {
//			// Every node, StringBuilder grabs the String'd-out contents, and attaches a
//			// grammatical separator
//			stringKing.append(stringSlinger.getElement().toString());
//			stringKing.append(", ");
//			stringSlinger = stringSlinger.getNext();
//		}
//
//		// Outside the loop, we should have run through the entire list, so clip off the
//		// last comma and space
//		stringKing.delete(stringKing.length() - 2, stringKing.length());
//
//		// And attach the back bracket
//		stringKing.append("]");

	}

	// This is extrapolated on in the private class below
	public Iterator<T> iterator() {
		return new DLLIterator();
	}

	// All right. Here we go. But I think we can just... co-opt SLLIterator?
	public ListIterator<T> listIterator() {
		return new DLLIterator();
	}

	// Let's find out. How big a trainwreck can it be? He said, last-words-ingly
	public ListIterator<T> listIterator(int startingIndex) {
		return new DLLIterator(startingIndex);
	}

	/** Iterator for IUDoubleLinkedList */
	private class DLLIterator implements ListIterator<T> {
		private DNode<T> nextNode;
		private DNode<T> lastNode;
		private int iterModCount;
		private int nextIndex;
		private int lastIndex;

		// Flag to (dis-/en-)able remove() and set() function
		boolean remSleep = true;

		// And this other flag will help us know which was our last action; previous or
		// next
		boolean wasNext = false;
		// As bizarre as this looks (and feels), as long as we check remSleep FIRST,
		// which will enable the act of removing to begin with, we just 
		// need this second boolean to act as the fork for which direction we're removing

		/** Creates a new iterator for the list */
		public DLLIterator() {
			this(0);
		}

		// Extra constructor, for summoning an iterator to a specific point
		public DLLIterator(int startingIndex) {
			// First off, the usual validity check
			if (startingIndex < 0 || startingIndex > size)
				throw new IndexOutOfBoundsException();

			// Special case: empty list
			if (size == 0) {
				nextNode = lastNode = head = tail = null;
				nextIndex = 0;
				lastIndex = -1;
				iterModCount = modCount;
			}

			// Special case: spawning at the list end
			else if (startingIndex == size) {
				nextNode = null;
				lastNode = tail;
				nextIndex = size;
				lastIndex = size - 1;
				iterModCount = modCount;
			}

			else {

				nextNode = head;
				for (int i = 0; i < startingIndex; i++) {
					nextNode = nextNode.getNext();
				}
				nextIndex = startingIndex;
				lastNode = nextNode.getPrevious();
				lastIndex = nextIndex - 1;
				iterModCount = modCount;
			}
		}

		// Check if there are any elements ahead of the iterator's current position
		public boolean hasNext() {
			// This sure seemed to work, so we won't be changing it
			if (iterModCount != modCount)
				throw new ConcurrentModificationException();

			// Handy little inversion-bang
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
			lastNode = nextNode;
			nextNode = nextNode.getNext();
			nextIndex++;
			lastIndex++;

			// THEN, we deactivate remSleep, allowing a call of remove() after any call of
			// next()
			remSleep = false;

			// And mark next as our last action
			wasNext = true;

			return retVal;
		}

		// Remove the last element the iterator most recently passed, if it has
		// Else, return an illegal state
		public void remove() {
			if (iterModCount != modCount)
				throw new ConcurrentModificationException();

			// Here's our "no consecutive remove() calls" check
			if (remSleep)
				throw new IllegalStateException();

			// Past those two gates, we know the iterator is synched, and we know that
			// either next() or previous() has been called. Now we need to know which
			DNode<T> markedOne = new DNode<T>();
			if (wasNext)
				markedOne = lastNode;
			else
				markedOne = nextNode;

			// You'll recall from our remove(index) function that we needed special cases
			// for 0th and final node removals. Single-size lists, too

			// Special Case: single-element list
			if (head == tail) {
				head = tail = null;
				size--;
				nextIndex = 0;
				lastIndex = -1;
				modCount++;
				iterModCount++;
				remSleep = true;
			}

			// Special Case: removing head node
			// If head's getNext() is the same as nextNode, then the iterator is just past
			// head, and that's what's being removed
			else if (markedOne == head) {
				head = head.getNext();
				;
				head.setPrevious(null);
				nextNode = head;
				nextIndex = 0;
				lastIndex = -1;
				size--;
				modCount++;
				iterModCount++;
				remSleep = true;

			}

			// Special Case: removing tail node
			// This one needs a setup because there's no nextNode to call; it's a void after
			// Tail
			else if (markedOne == tail) {
				// With previous(), now we can make this constant-time
				tail = tail.getPrevious();

				// Embrace the void
				tail.setNext(null);
				nextNode = null;
				lastNode = tail;

				size--;
				nextIndex = size;
				lastIndex = size - 1;
				modCount++;
				iterModCount++;
				remSleep = true;
			}

			// All other cases
			else {
				// No more loops, father. Now that we have lastNode, nextNode, and markedOne, we
				// can do this on the fly with no prep

				// We don't ACTUALLY need to know which direction we're coming from, if we write
				// everything as in relation to the marked node
				markedOne.getPrevious().setNext(markedOne.getNext());
				markedOne.getNext().setPrevious(markedOne.getPrevious());

				// What IS different is which node, next or last, changes. Luckily, we changed
				// the next and prev references of the nodes surrounding markedOne; we can
				// still use it to get these values
				if (wasNext)
					lastNode = markedOne.getPrevious();
				else
					nextNode = markedOne.getNext();

				// Then we flip remSleep on, so remove() can't be called twice
				remSleep = true;

				// Vital stats change
				nextIndex--;
				lastIndex--;
				size--;
				modCount++;
				iterModCount++;
			}
		}

		// Is there anything behind the iterator?
		public boolean hasPrevious() {
			// Repeat our built-in version check
			if (iterModCount != modCount)
				throw new ConcurrentModificationException();

			// Just a handy inversion of our hasNext() above
			return !(lastNode == null);
		}

		// Look if there's anything behind the iterator (baking in a version check) and
		// if so, regress the iterator and return that value
		public T previous() {
			if (!hasPrevious())
				throw new NoSuchElementException();

			// Now, we do all our well-worn sorcery from next, just in reverse
			T retVal = lastNode.getElement();

			// Then, iterator goes backward
			nextNode = lastNode;
			lastNode = lastNode.getPrevious();
			nextIndex--;
			lastIndex--;

			// Finally, we enable our remove method, and declare previous() as our last call
			remSleep = false;
			wasNext = false;

			return retVal;
		}

		// Returns the index number of the node directly ahead of the iterator
		public int nextIndex() {
			// Version check
			if (iterModCount != modCount)
				throw new ConcurrentModificationException();
			int capsNext = nextIndex;
			return capsNext;
		}

		// Returns the index number of the node directly behind the iterator
		public int previousIndex() {
			// Version check
			if (iterModCount != modCount)
				throw new ConcurrentModificationException();
			int capsPrev = lastIndex;
			return capsPrev;
		}

		// Replace the contents of the last-returned node to that of the passed element
		public void set(T element) {
			// First off, version check
			if (iterModCount != modCount)
				throw new ConcurrentModificationException();

			// Next up, have we made a call to next or previous since last we removed or set
			// an element?
			if (remSleep)
				throw new IllegalStateException();

			// Now, whose guts we changin'?
			DNode<T> markedOne = new DNode<T>();
			if (wasNext)
				markedOne = lastNode;
			else
				markedOne = nextNode;

			// Now, barge the lathe!
			markedOne.setElement(element);

			// Flip the trigger to prevent consecutive calls
			remSleep = true;

			modCount++;
			iterModCount++;

		}

		// Insert a new element, directly behind the current iterator's position
		public void add(T element) {
			// Version check
			if (iterModCount != modCount)
				throw new ConcurrentModificationException();

			// It's a bad Metro 2033 reference; I'm insufferable and I can't help it
			DNode<T> nodoNovus = new DNode<T>(element);

			// Special case: empty list
			if (size == 0) {
				head = tail = nodoNovus;

				// Since the add function is supposed to put things behind the iterator
				lastNode = nodoNovus;
				lastIndex = 0;
				nextIndex = 1;

				// Vital stats
				size++;
				modCount++;
				iterModCount++;
			}

			// Special case: new node is new head
			else if (!hasPrevious()) {

				// Link up
				head.setPrevious(nodoNovus);
				nodoNovus.setNext(head);

				// Title reassign
				head = nodoNovus;

				nextNode = head.getNext();
				lastNode = head;
				nextIndex = 1;
				lastIndex = 0;

				size++;
				modCount++;
				iterModCount++;
			}

			// Special case: new node is new tail
			else if (!hasNext()) {
				tail.setNext(nodoNovus);
				nodoNovus.setPrevious(tail);
				tail = nodoNovus;

				size++;
				modCount++;
				iterModCount++;

				nextIndex = size;
				lastIndex = size - 1;
			}

			// Otherwise, we're in the middle somewhere
			else {
				nodoNovus.setPrevious(nextNode.getPrevious());
				nodoNovus.setNext(lastNode.getNext());
				nextNode.setPrevious(nodoNovus);
				lastNode.setNext(nodoNovus);
				lastNode = nodoNovus;

				nextIndex++;
				lastIndex++;

				size++;
				modCount++;
				iterModCount++;
			}

		}
	}

}
