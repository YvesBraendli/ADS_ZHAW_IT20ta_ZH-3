package ch.zhaw.ads;

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
