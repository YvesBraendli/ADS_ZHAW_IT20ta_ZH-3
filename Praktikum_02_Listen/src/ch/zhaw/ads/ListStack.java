package ch.zhaw.ads;

import java.util.ArrayList;

public class ListStack implements Stack {

	private ArrayList<Object> liste = new ArrayList<Object>();
	
	@Override
	public void push(Object x) throws StackOverflowError {
		liste.add(0, x);
	}

	@Override
	public Object pop() {
		if(isEmpty()) {
			return null;
		}
		return liste.remove(0);
	}

	@Override
	public boolean isEmpty() {
		return liste.size() == 0;
	}

	@Override
	public Object peek() {
		if(isEmpty()) {
			return null;
		}
		return liste.get(0);
	}

	@Override
	public void removeAll() {
		liste = new ArrayList<Object>();		
	}

	@Override
	public boolean isFull() {
		return false;
	}
	
}

