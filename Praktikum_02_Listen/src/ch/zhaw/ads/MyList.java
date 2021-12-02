package ch.zhaw.ads;

import java.util.AbstractList;

public class MyList extends AbstractList<Object>{
	
	private ListNode header = new ListNode();
	private ListNode tail = new ListNode();
	private ListNode pointer;
	
	public MyList() {
		header.setNext(tail);
		tail.setPrevious(header);
	}

	public boolean add (Object o) {
		boolean successful = false;
		ListNode a = new ListNode(o);
		pointer = tail;
		a.setNext(pointer);
		a.setPrevious(pointer.getPrevious());
		pointer.getPrevious().setNext(a);
		pointer.setPrevious(a);
		return successful;
	}
	
	public boolean remove (Object o) {
		boolean deleted = false;
		pointer = header;
		while (pointer!=tail&&!(deleted)) {
			pointer = pointer.getNext();
			if(pointer.getPrevious().getData()==o) {
				pointer.getPrevious().getPrevious().setNext(pointer);
				pointer.setPrevious(pointer.getPrevious().getPrevious());
				deleted = true;
			}
		}
		return deleted;
	}
	
	@Override
	public Object get(int pos) {
		int currentPosition = 0;
		pointer = header.getNext();
		while(currentPosition!=pos) {
			pointer = pointer.getNext();
			currentPosition++;
		}
		return pointer.getData();
	}
	
	public boolean isEmpty() {
		boolean empty = false;
		if(header.getNext()==tail&&tail.getPrevious()==header) {
			empty = true;
		}
		return empty;
	}

	@Override
	public int size() {
		int numberOfElements = 0;
		pointer = header.getNext();
		if (pointer==tail) {
			return numberOfElements;
		}
		numberOfElements = 1;
		while(pointer.getNext()!=tail) {
			numberOfElements++;
			pointer = pointer.getNext();
		}
		return numberOfElements;
	}
	
	public void clear() {
		header.setNext(tail);
		tail.setPrevious(header);
	}
	
	public class ListNode {
		private Object data;
		private ListNode next;
		private ListNode previous;
		
		public ListNode(Object o) {
			data = o;
		}
		
		public ListNode() {
		}

		public ListNode getNext() {
			return next;
		}
		
		public void setNext(ListNode o) {
			next = o;
		}
		
		public ListNode getPrevious() {
			return previous;
		}
		
		public void setPrevious(ListNode o) {
			previous = o;
		}
		
		public Object getData() {
			return data;
		}

	}

}
