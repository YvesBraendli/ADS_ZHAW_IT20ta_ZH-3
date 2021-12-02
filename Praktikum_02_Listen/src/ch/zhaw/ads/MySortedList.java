package ch.zhaw.ads;

public class MySortedList{

	private ListNode header = new ListNode();
	private ListNode tail = new ListNode();
	private ListNode pointer;

	public MySortedList() {
		header.setNext(tail);
		tail.setPrevious(header);
	}

	public boolean add(Object o) {
		boolean successful = false;
		char symbolToInsert;
		if (o instanceof String) {
			symbolToInsert = ((String) o).charAt(0);
		} else {
			symbolToInsert = (char) o;
		}
		ListNode a = new ListNode(o);
		if (header.getNext() == tail) {
			successful = true;
			header.setNext(a);
			a.setPrevious(header);
			a.setNext(tail);
			tail.setPrevious(a);
		}
		pointer = header.getNext();
		while (pointer != tail && !(successful)) {
			char symbolInListToCheck;
			if (pointer.getData() instanceof String) {
				symbolInListToCheck = ((String) pointer.getData()).charAt(0);
			} else {
				symbolInListToCheck = (char) pointer.getData();
			}
			if (symbolToInsert < symbolInListToCheck) {
				pointer = pointer.getNext();
				pointer.getPrevious().getPrevious().setNext(a);
				a.setPrevious(pointer.getPrevious().getPrevious());
				a.setNext(pointer.getPrevious());
				pointer.getPrevious().setPrevious(a);
				successful = true;
			} else {
				pointer = pointer.getNext();
				if (pointer == tail && !(successful)) {
					tail.getPrevious().setNext(a);
					a.setPrevious(tail.getPrevious());
					a.setNext(tail);
					tail.setPrevious(a);
					successful = true;
				}
			}
		}
		return successful;
	}


	public boolean remove(Object o) {
		boolean deleted = false;
		pointer = header;
		while (pointer != tail && !(deleted)) {
			pointer = pointer.getNext();
			if (pointer.getPrevious().getData() == o) {
				pointer.getPrevious().getPrevious().setNext(pointer);
				pointer.setPrevious(pointer.getPrevious().getPrevious());
				deleted = true;
			}
		}
		return deleted;
	}


	public Object get(int pos) {
		int currentPosition = 0;
		pointer = header.getNext();
		while (currentPosition != pos) {
			pointer = pointer.getNext();
			currentPosition++;
		}
		return pointer.getData();
	}


	public boolean isEmpty() {
		boolean empty = false;
		if (header.getNext() == tail && tail.getPrevious() == header) {
			empty = true;
		}
		return empty;
	}
	

	public void clear() {
		tail.setPrevious(header);
		header.setNext(tail);
	}


	public int size() {
		int numberOfElements = 0;
		pointer = header.getNext();
		if (pointer == tail) {
			return numberOfElements;
		}
		numberOfElements = 1;
		while (pointer.getNext() != tail) {
			numberOfElements++;
			pointer = pointer.getNext();
		}
		return numberOfElements;
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