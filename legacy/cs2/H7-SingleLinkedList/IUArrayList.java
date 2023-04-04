import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Array-based implementation of IndexedUnsortedList. An Iterator with working
 * remove() method is implemented, but ListIterator is unsupported.
 * 
 * @author Jim "JCIII" Crowell
 *
 * @param <T> type to store
 */
public class IUArrayList<T> implements IndexedUnsortedList<T> {
	private static final int DEFAULT_CAPACITY = 10;
	private static final int NOT_FOUND = -1;

	private T[] array;
	private int rear;
	private int modCount;

	/** Creates an empty list with default initial capacity */
	public IUArrayList() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Creates an empty list with the given initial capacity
	 * 
	 * @param initialCapacity
	 */
	@SuppressWarnings("unchecked")
	public IUArrayList(int initialCapacity) {
		array = (T[]) (new Object[initialCapacity]);
		rear = 0;
		modCount = 0;
	}

	/** Double the capacity of array */
	private void expandCapacity() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	// Simply an add-at-index function where the index is 0
	public void addToFront(T element) {
		add(0, element);
	}

	// Do... the... add... method?
	public void addToRear(T element) {
		add(element);
	}

	// Add the supplied element to the end of the array
	public void add(T element) {
		if (array.length == rear)
			expandCapacity();

		array[rear] = element;
		rear++;
		modCount++;
	}

	// Find the targeted element, shift everything RIGHT of it to the right one
	// slot, insert new element
	public void addAfter(T element, T target) {
		if (isEmpty())
			throw new NoSuchElementException();
		
		if (!contains(target))
			throw new NoSuchElementException();

		if (array.length == rear)
			expandCapacity();

		int targetIndex = indexOf(target);
		int countBack = rear;
		while (countBack > targetIndex) {
			array[countBack] = array[countBack - 1];
			countBack--;
		}
		array[targetIndex + 1] = element;
		rear++;
		modCount++;
	}

	// Shift elements right to make a space, insert new element
	public void add(int index, T element) {
		if (index < 0 || index > rear)
			throw new IndexOutOfBoundsException();

		// If array is full, expand it
		if (array.length == rear)
			expandCapacity();

		int countBack = rear;
		while (countBack > index) {
			array[countBack] = array[countBack - 1];
			countBack--;

		}
		rear++;
		modCount++;
		array[index] = element;
	}

	// Remove value at the head of the array, shift everything back, inc mod, dec rear
	// I was tempted to just write "return remove(array[0])", but I'm too nervous to trust it
	public T removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException();

		T firstOut = array[0];

		int startPoint = 0;
		while (startPoint < rear - 1) {
			array[startPoint] = array[startPoint + 1];
			startPoint++;
		}
		array[rear - 1] = null;
		rear--;
		modCount++;
		return firstOut;
	}

	// Create copy of element at end of array, remove it, decrement rear, increment
	// modCount, and return the removed value
	public T removeLast() {
		if (isEmpty())
			throw new NoSuchElementException();

		T lastVal = array[rear - 1];
		array[rear - 1] = null;
		rear--;
		modCount++;
		return lastVal;
	}

	// Make copy of indicated element (if it exists), update the rear indicator,
	// shift all elements after removed one back, return
	// removed element
	public T remove(T element) {
		int index = indexOf(element);
		if (index == NOT_FOUND) {
			throw new NoSuchElementException();
		}

		T retVal = array[index];

		rear--;
		// shift elements
		for (int i = index; i < rear; i++) {
			array[i] = array[i + 1];
		}
		array[rear] = null;
		modCount++;

		return retVal;
	}

	// Make a copy of the element at the index, shift all elements after the
	// indicated one back a space, and return removed value
	public T remove(int index) {
		if (index < 0 || index >= rear)
			throw new IndexOutOfBoundsException();

		// Make copy of removed value
		T yerOut = array[index];

		// Shift elements
		int startPoint = index;
		while (startPoint < rear - 1) {
			array[startPoint] = array[startPoint + 1];
			startPoint++;
		}
		// Stats housekeeping
		array[rear - 1] = null;
		rear--;
		modCount++;
		// Spit product
		return yerOut;
	}

	// Replace the element at the indicated index with the new, indicated element
	public void set(int index, T element) {
		if (index < 0 || index >= rear)
			throw new IndexOutOfBoundsException();

		array[index] = element;
		modCount++;
	}

	// Make a copy of the element located at the index (if it's extant), and return
	// it
	public T get(int index) {
		if (index < 0 || index >= rear)
			throw new IndexOutOfBoundsException();
		// I'm really tempted to write a function that just does the "If isEmpty()" or
		// "if index < 0 blah blah" checks. So I don't
		// have to write out both lines every time. I could just do
		// "emptyCheck()/indexCheck()".

		T retElement = array[index];
		return retElement;
	}

	// Walk through entire array until finding a matching element, then return the
	// index of that element
	public int indexOf(T element) {
		int index = -1;
		
		int i = 0;
		while (index == -1 && i < rear) {
			if (element.equals(array[i])) {
				index = i;
			}
			else
				i++;
		}
		
		return index;
	}

	// Return copy of element at index 0
	public T first() {
		if (isEmpty())
			throw new NoSuchElementException();

		T firstElement = array[0];
		return firstElement;
	}

	// Return copy of element at the end of the array (which will be equal to rear -
	// 1, assuming rear is kept updated properly
	public T last() {
		if (isEmpty())
			throw new NoSuchElementException();

		T lastElement = array[rear - 1];
		return lastElement;
	}

	// Checks if a target exists in the array, utilizing the indexOf function
	public boolean contains(T target) {
		return (indexOf(target) != -1);
	}

	// Boolean check if rear is equal to 0 (as it is when the constructor is called
	// and nothing is done)
	public boolean isEmpty() {
		return rear == 0;
	}

	// Make a copy of rear, which indicates the size of the array, and return it
	public int size() {
		int sizeCount = rear; // This looks dumb, but I believe it preserves encaps
		return sizeCount;
	}
	
	// Empty list should still return brackets; failing that, a list of elements within brackets, without an extra comma
	public String toString() {
		StringBuilder stringKing = new StringBuilder();
		
		// Guaranteed open bracket
		stringKing.append("[");
		
		// Add every element in the list, if there are any, and separate them with a comma and space
		for (T element: this) {
			stringKing.append(element.toString());
			stringKing.append(", ");
		}
		// Conditional removal of trailing comma and space
		if (!isEmpty())
			stringKing.delete(stringKing.length() - 2, stringKing.length());
		
		// Guaranteed closing bracket
		stringKing.append("]");
		
		// It seems so goofy that the way to get a toString out of StringBuilder is to toString the StringBuilder into the 
		// toString original method. I love it. It's so bad
		return stringKing.toString();
	}

	// Already complete; supplied by the private class below
	public Iterator<T> iterator() {
		return new ALIterator();
	}

	// We're not screwing with listIterators until DL-Lists, I'm told
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	// See above
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}

	/** Iterator for IUArrayList */
	private class ALIterator implements Iterator<T> {
		private int nextIndex;
		private int iterModCount;
		private boolean remSleep = true;  //flag to keep track of if we can do a remove or not

		public ALIterator() {
			nextIndex = 0;
			iterModCount = modCount;
		}

		// As long as the thing in front of the iterator isn't nothingness, we should be good
		public boolean hasNext() {
			if (iterModCount != modCount)
				throw new ConcurrentModificationException();
			return nextIndex < rear;
		}

		// If there's something in front of the iterator, return it, and move the iterator forward once
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			T retVal = array[nextIndex];
			nextIndex++;
			remSleep = false;
			return retVal;
		}

		// Ensure we've passed over a value since the list creation/last iterRemove call and, if so, remove that value
		public void remove() {
			if (iterModCount != modCount)
				throw new ConcurrentModificationException();
			if (remSleep)
				throw new IllegalStateException();
			
			int moveCount = nextIndex - 1;
			while (moveCount < rear - 1) {
				array[moveCount] = array[moveCount + 1];
				moveCount++;
			}
			// Erase former tail of array
			array[rear-1] = null;
			// Re-label tail
			rear--;
			
			// The usual
			modCount++;
			iterModCount++;
			
			// Punch down nextIndex, because otherwise we're effectively moving the array AROUND the iterator
			nextIndex--;
			
			// Ensure we can't run two removes in a row
			remSleep = true;
				

		}
	}
}
