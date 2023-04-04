import java.util.Comparator;

/**
 * Class for sorting lists that implement the IndexedUnsortedList interface,
 * using ordering defined by class of objects in list or a Comparator.
 * As written uses Mergesort algorithm.
 *
 * @author CS221
 */
public class Sort
{	
	/**
	 * Returns a new list that implements the IndexedUnsortedList interface. 
	 * As configured, uses WrappedDLL. Must be changed if using 
	 * your own IUDoubleLinkedList class. 
	 * 
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	private static <T> IndexedUnsortedList<T> newList() 
	{
		return new IUDoubleLinkedList<T>(); //TODO: replace with your IUDoubleLinkedList for extra-credit
	}
	
	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @see IndexedUnsortedList 
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnsortedList<T> list) 
	{
		mergesort(list);
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using given Comparator.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 * @see IndexedUnsortedList 
	 */
	public static <T> void sort(IndexedUnsortedList <T> list, Comparator<T> c) 
	{
		mergesort(list, c);
	}
	
	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface, 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 */
	private static <T extends Comparable<T>> void mergesort(IndexedUnsortedList<T> list)
	{
		
		
		if (list.size() < 2)
			return;
		
		// We'll need a consistent variable for size, because the way I have it
		// set up will cause it to change over time
		int capacity = list.size();
				
		// Deciding value for dividing list into halves
		int fulcrum = capacity / 2;
		
		
		
		IUDoubleLinkedList<T> leftSide = new IUDoubleLinkedList<T>();
		IUDoubleLinkedList<T> rightSide = new IUDoubleLinkedList<T>();
		
		
		// Walk through list, everything of less value than the fulcrum value goes left
		// Everything else goes right
		for (int i = 0; i < capacity; i++) {
			if (i < fulcrum) {
				leftSide.add(list.removeFirst());
			}
			else {
				rightSide.add(list.removeFirst());
			}
		}
		
		// Initial recursion, breaking lists down as far as possible
		mergesort(rightSide);
		mergesort(leftSide);
		
		
		// This loop compares every element between the lists and adds the lesser to the master list
		while (leftSide.size() > 0 && rightSide.size() > 0) {
			if (leftSide.first().compareTo(rightSide.first()) < 0) {
				list.add(leftSide.removeFirst());
			} else {
				list.add(rightSide.removeFirst());
			}
			
		}
		
		// These next two loops only occur once ONE of the lists run out of elements
		// Since the lists should already be sorted, we should be able to be confident
		// that any remaining elements are greater than the ones already sorted thus far.
		// Therefore, just add the remaining pieces until there are no more
		while (rightSide.size() > 0) {
			list.add(rightSide.removeFirst());
		}
		
		while (leftSide.size() > 0) {
			list.add(leftSide.removeFirst());
		}
		
		return;		
		
	}		
	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface,
	 * using the given Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 */
	private static <T> void mergesort(IndexedUnsortedList<T> list, Comparator<T> c)
	{
		
		// Very little of this actually has to change; we basically just offload
		// the compareTo() functionality to the values returned by the Comparator
		int capacity = list.size();
		
		if (capacity < 2)
			return;
		
		
		
		// Deciding value for dividing list into halves
		int fulcrum = capacity / 2;
		
		IUDoubleLinkedList<T> leftSide = new IUDoubleLinkedList<T>();
		IUDoubleLinkedList<T> rightSide = new IUDoubleLinkedList<T>();
		
		
		// Walk through list, everything of less value than the fulcrum value goes left
		// Everything else goes right
		for (int i = 0; i < capacity; i++) {
			if (i < fulcrum) {
				leftSide.add(list.removeFirst());
			}
			else {
				rightSide.add(list.removeFirst());
			}
		}
		
		// Initial recursion, breaking lists down as far as possible
		mergesort(rightSide, c);
		mergesort(leftSide, c);
		
		
		// This loop compares every element between the lists and adds the lesser to the master list
		while (leftSide.size() > 0 && rightSide.size() > 0) {
			if (c.compare(leftSide.first(), rightSide.first()) < 0) {
				list.add(leftSide.removeFirst());
			} else {
				list.add(rightSide.removeFirst());
			}
			
		}
		
		// These next two loops only occur once ONE of the lists run out of elements
		// Since the lists should already be sorted, we should be able to be confident
		// that any remaining elements are greater than the ones already sorted thus far.
		// Therefore, just add the remaining pieces until there are no more
		while (rightSide.size() > 0) {
			list.add(rightSide.removeFirst());
		}
		
		while (leftSide.size() > 0) {
			list.add(leftSide.removeFirst());
		}
		
		return;		

	}
	
}
