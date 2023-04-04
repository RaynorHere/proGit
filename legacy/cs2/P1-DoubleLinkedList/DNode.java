/**
 * Node represents a node in a linked list.
 *
 * @author Java Foundations, mvail, Jim "JCIII" Crowell
 * @version 4.0
 */
public class DNode<E> {
	private DNode<E> next;
	private DNode<E> previous;
	private E element;

	/**
  	 * Creates an empty node.
  	 */
	public DNode() {
		next = null;
		previous = null;
		element = null;
	}

	baroo
	

	/**
  	 * Creates a node storing the specified element.
 	 *
  	 * @param elem
  	 *            the element to be stored within the new node
  	 */
	public DNode(E elem) {
		next = null;
		element = elem;
	}

	/**
 	 * Returns the node that follows this one.
  	 *
  	 * @return the node that follows the current one
  	 */
	public DNode<E> getNext() {
		return next;
	}
	
	/**
	 * Returns the node that trails this one.
	 * 
	 * @return the node behind the current one
	 */
	public DNode<E> getPrevious() {
		return previous;
	}

	/**
 	 * Sets the node that follows this one.
 	 *
 	 * @param node
 	 *            the node to be set to follow the current one
 	 */
	public void setNext(DNode<E> node) {
		next = node;
	}
	
	/**
	 * Sets the node that trails this one.
	 * 
	 * @param node
	 * 			  the node to be set to trail the current one
	 */
	public void setPrevious(DNode<E> node) {
		previous = node;
	}

	/**
 	 * Returns the element stored in this node.
 	 *
 	 * @return the element stored in this node
 	 */
	public E getElement() {
		return element;
	}

	/**
 	 * Sets the element stored in this node.
  	 *
  	 * @param elem
  	 *            the element to be stored in this node
  	 */
	public void setElement(E elem) {
		element = elem;
	}

	@Override
	public String toString() {
		return "Element: " + element.toString() + " Has next: " + (next != null);
	}
}

