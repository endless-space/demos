package com.endless.demo.jvm;

public class MyListTest {

	public static class Node {
		public Object data;
		public Node next;
		
		@Override
		public String toString() {
			return "" + data;
		}
	}
	
	public static class MyList {
		public Node head;
		public Node tail;
		
		public void add(int i) {
			if (head == null) {
				Node newHead = new Node();
				newHead.data = i;
				newHead.next = null;
				
				head = newHead;
				tail = newHead;
			} else {
				Node newTail = new Node();
				newTail.data = i;
				newTail.next = null;
				
				tail.next = newTail;
				tail = newTail;
			}
		}
		
		public void reverse() {
			if (head == null)
				return;
			
			Node elem = head;
			Node prevElem = null;
			while (elem != null) {
				Node nextElem = elem.next; 
				
				if (elem == head) {
					elem.next = null;
					
				} else {
					elem.next = prevElem;
				}
				
				prevElem = elem;
				elem = nextElem;
			}
			
			head = prevElem;
		}
		
		@Override
		public String toString() {
			if (head == null)
				return "";
			
			StringBuilder sb = new StringBuilder();
			
			Node elem = head;
			for (; elem.next != null;) {
				sb.append(elem.data + " ");
				
				elem = elem.next;
			}
			sb.append(elem + " ");
			
			return sb.toString();
		}
	}
	
	public static void main(String[] args) {
		MyList list = new MyList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(0xA);
		list.add(0xB);
		list.add(0xC);
		
		list.reverse();
		//list.reverse();
		
		System.out.println(list);
	}
}
