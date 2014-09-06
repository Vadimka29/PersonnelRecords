package list;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MyArrayList<E> implements java.io.Serializable, Cloneable, Iterable<E> {
	private static final long serialVersionUID = -220286038234083605L;
	private Object[] elements;
	private int size;
	
	//inner class iterator
	private class InnerIter implements Iterator<E>{
		private int current;

		@Override
		public boolean hasNext() {
			return current < size;
		}
		
		@Override
		public E next() {
			@SuppressWarnings("unchecked")
			E toReturn = (E) elements[current];
			current ++;
			return toReturn;
		}

		@Override
		public void remove() {
			MyArrayList.this.remove(current);
		}
	}
	public MyArrayList(int initialCapacity){
		if(initialCapacity < 0)
			throw new IllegalArgumentException("Initial capacity < 0");
		elements = new Object[initialCapacity];
	}
	public MyArrayList(){
		this(10);
	}
	public MyArrayList(Collection<? extends E> collection){
		elements = collection.toArray();
		size = elements.length;
	}
	public MyArrayList(MyArrayList<? extends E> collection){
		//elements = co
	}
	public int size(){
		return size;
	}
	public boolean isEmpty(){
		return size == 0;
	}
	public boolean add(E e){
		//ensureCapacity
		int minCapacity = size + 1;
		checkCapacity(minCapacity);
		elements[size++] = e;
		return true;
	}
	private void checkCapacity(int minCapacity){
		if(minCapacity - elements.length > 0){
			grow(minCapacity);
		}
	}
	//grow capacity
	private void grow(int minCapacity){
		int oldCapacity = elements.length;
		//formula for speed optimization
		int newCapacity = (oldCapacity*3)/2 + 1;
		if(newCapacity - Integer.MAX_VALUE < 0)
			throw new OutOfMemoryError();
		elements = Arrays.copyOf(elements, newCapacity);
	}
	public boolean add(int index, E e){
		//check array bounds
		rangeCheck(index);
		int minCapacity = size + 1;
		checkCapacity(minCapacity);
		//Make free position for new element
		System.arraycopy(elements, index, elements, index + 1, size - index);
		elements[index] = e;
		size ++;
		return true;
	}
	//check bounds of array
	private void rangeCheck(int index){
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Illegal index");
	}
	private void rangeCheck(int from, int to){
		if(from < 0 || to > size)
			throw new IndexOutOfBoundsException("Illegal index");
	}
	public boolean addAll(Collection<? extends E> collection){
		Object[] newArray = collection.toArray();
		int newLength = newArray.length;
		checkCapacity(size + newLength);
		//arraycopy(source, from, destination, from, count of items)
		System.arraycopy(newArray, 0, elements, size, newLength);
		size += newLength;
		//if newLength == 0 - there are no elements to add
		return newLength != 0;
	}
	public boolean addAll(int index, Collection<? extends E> collection){
		rangeCheck(index);
		Object[] newArray = collection.toArray();
		int newLength = newArray.length;
		checkCapacity(size + newLength);
		int itemCount = size - index;
		if(itemCount != 0)
			System.arraycopy(elements, index, elements, index + newLength, itemCount);
		System.arraycopy(newArray, 0, elements, index, newLength);
		size += newLength;
		return itemCount != 0;
	}
	public void clear(){
		for(int i = 0; i < size; i++){
			elements[i] = null;
		}
		size = 0;
	}
	@Override
	public Object clone(){
		MyArrayList<E> list = new MyArrayList<>();
		list.elements = Arrays.copyOf(elements, size);
		list.size = this.size;
		return list;
	}
	public int indexOf(Object o){
		if(o == null){
			for(int i = 0; i < size; i++){
				if(elements[i] == null)
					return i;
			}
		}
		else{
			for(int i = 0; i < size; i++){
				if(elements[i].equals(o))
					return i;
			}
		}
		return -1;
	}
	//if indexOf < 0 - there is no such elements
	public boolean contains(Object o){
		return indexOf(o) >= 0;
	}
	@SuppressWarnings("unchecked")
	public E get(int index){
		rangeCheck(index);
		return (E) elements[index];
	}
	public int lastIndexOf(Object ob){
		if(ob == null){
			for(int i = size - 1; i >= 0; i--)
				if(elements[i] == null)
					return i;
		}
		else{
			for(int i = size - 1; i >= 0; i--){
				if(ob.equals(elements[i]))
					return i;
			}
		}
		return -1;
	}
	public E remove(int index){
		rangeCheck(index);
		@SuppressWarnings("unchecked")
		E oldValue = (E) elements[index];
		//number of items which must be moved
		int copyCounter = size - index - 1;
		if(copyCounter > 0)
			System.arraycopy(elements, index + 1, elements, index, copyCounter);
		//gc will delete this item
		elements[--size] = null;
		return oldValue;
	}
	public boolean remove(Object ob){
		//if ob is null
		if(ob == null){
			for(int i = 0; i < size; i++){
				if(elements[i] == null){
					remove(i);
					return true;
				}
			}
		}
		else{
			for(int i = 0; i < size; i++){
				if(ob.equals(elements[i])){
					remove(i);
					return true;
				}
			}
		}
		return false;
	}
	public boolean removeAll(Collection<?> c){
		if(c.isEmpty() || c == null)
			return false;
		boolean modStatus = false;
		for(int i = 0; i < size; i++){
			if(c.contains(elements[i])){
				remove(i);
				i--;
				modStatus = true;
			}
		}
		return (modStatus)? true : false;
	}
	public void removeRange(int fromIndex, int toIndex){
		rangeCheck(fromIndex, toIndex);
		Iterator<E> it = this.iterator();
		int generalSize = size;
		for(int i = 0; i < generalSize; i++){
			if(i >= fromIndex && i < toIndex)
				it.remove();
			else
				it.next();
		}
	}
	public boolean retainAll(Collection<?> c){
		if(c == null)
			return false;
		if(c.isEmpty()){
			clear();
			return true;
		}
		boolean modStatus = false;
		for(int i = 0; i < size; i++){
			if(!c.contains(elements[i])){
				remove(i);
				i--;
				modStatus = true;
			}
		}
		return (modStatus) ? true : false;
	}
	public E set(int index, E e){
		rangeCheck(index);
		@SuppressWarnings("unchecked")
		E oldValue = (E) elements[index];
		elements[index] = e;
		return oldValue;
	}
	public 	MyArrayList<E> subList(int fromIndex, int toIndex){
		rangeCheck(fromIndex, toIndex);
		@SuppressWarnings("unchecked")
		MyArrayList<E> list = (MyArrayList<E>)this.clone();
		Iterator<E> it = list.iterator();
		int generalSize = size;
		for(int i = 0; i < generalSize; i++){
			if(i < fromIndex || i >= toIndex)
				it.remove();
			else
				it.next();
		}
		return list;
	}
	public Object[] toArray(){
		return Arrays.copyOf(elements, size);
	}
	@Override
	public Iterator<E> iterator() {
		return new InnerIter();
	}
	public String toString(){
		StringBuffer toReturn = new StringBuffer();
		toReturn.append("********** LIST OF EMPLOYEES **********\n");
		for (int i = 0; i < size; i++) {
			if (elements[i] == null){
				toReturn.append(elements[i] + "\n");
				toReturn.append("______________________________________\n");
			}
			else {
				toReturn.append(elements[i].toString());
				toReturn.append("______________________________________\n");
			}
		}
		toReturn.append("************* END OF LIST *************");
		return toReturn.toString();
	}
}
