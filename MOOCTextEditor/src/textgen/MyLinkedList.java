package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		this.size=0;
		this.head = new LLNode<E>();
		this.tail = new LLNode<E>();
		
		this.tail.prev=head;
		this.head.next=tail;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if(element == null)
		{
			throw new NullPointerException();
		}
		// TODO: Implement this method
		LLNode<E>  last = new LLNode<E>();
		last.data= element;
		
		last.next = this.tail;
		last.prev = this.tail.prev;
		last.prev.next = last;
		this.tail.prev = last;
		
        this.size = this.size +1;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		/*For example, if the list has the elements 6 and 9 (in that order) and
		you call "get" with index 0, you should get back 6.
		If the method is called with an
		invalid index (say -1 or 2 in the example above), 
		you will thrown an IndexOutOfBoundsException.*/	
		if(index < 0 || this.size == 0|| this.size <= index){
			//System.out.println("out of bound");
			throw new IndexOutOfBoundsException();
		}
			//System.out.println("getting the index data");
			LLNode<E> indexobj = new LLNode<E>();
			indexobj.next = this.head;
			if(this.size != 0 && this.size > index && index >= 0) 
			{   
				for (int i=0; i<=index; i++)
				{
				  indexobj.next =indexobj.next.next;
				}
			}
		// TODO: Implement this method.
		return indexobj.next.data;
	}
	
	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{	
		if(index < 0 || this.size < index){
			
			throw new IndexOutOfBoundsException();
		}
		if(element == null)
		{
			throw new NullPointerException();
		}
		
		if(index != this.size && index !=0){
			
			LLNode<E> indexobj2 = new LLNode<E>();
			indexobj2.next = this.head;
			indexobj2.data = element;
			for (int i=0; i <= index; i++)
			{
				indexobj2.next=indexobj2.next.next;
			}
			indexobj2.prev= indexobj2.next.prev;
			
			indexobj2.prev.next = indexobj2;
			indexobj2.next.prev = indexobj2;
		}
		
		if (index == this.size && index != 0)
		{
			this.add(element); // adds one to size.
			this.size = this.size -1; // size will increase when the func. end.
		}
		if (index == 0)
		{
			LLNode<E> indexobj = new LLNode<E>();
			indexobj.data=element;
			indexobj.prev = this.head;
			indexobj.next = this.head.next;
			this.head.next.prev= indexobj;
			this.head.next=indexobj;	
		}
		this.size++;
		// TODO: Implement this method
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if(index < 0 || this.size < index || this.size == 0){
			//System.out.println("out of bound");
			throw new IndexOutOfBoundsException();
		}
	
			LLNode<E> indexobj3 = new LLNode<E>();
			indexobj3.next = this.head;
			
			for (int i=0; i <= index; i++)
			{
				indexobj3.next=indexobj3.next.next;
			}
			//removing.
	
			indexobj3.next.prev.next= indexobj3.next.next;
			indexobj3.next.next.prev= indexobj3.next.prev;
			
			indexobj3.next.next = indexobj3;
			indexobj3.next.prev = indexobj3;
			
			this.size --;
			return indexobj3.next.data;
			
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if(index < 0 || this.size < index){
			
			throw new IndexOutOfBoundsException();
		}
		if(element == null)
		{
			throw new NullPointerException();
		}
		LLNode<E> indexobj4 = new LLNode<E>();
		indexobj4.next = this.head;
		for (int i=0; i <= index; i++)
		{
			indexobj4.next=indexobj4.next.next;
		}
		
		E data;
		data = indexobj4.next.data;
		indexobj4.next.data = element;
		
		return data;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor. for adding prev and next nodes automatically.

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
    /*public LLNode (E thedata, LLNode<E> prevNode)
    {
    	
    }*/
    public LLNode()
    {
    	this.next=null;
    	this.data=null;
    	this.prev=null;	
    }
   
    	
    
    
}
