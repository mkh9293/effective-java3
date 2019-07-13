package first;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Item87_4 {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		StringLists str = new StringLists();
		for(int i=0; i<2000; i++) {
			str.add(""+i);	
		}
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temp.txt"));
		oos.writeObject(str);
		oos.close();
		
		// deserializable
//		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("temp.txt"));
//		StringLists sub2 = (StringLists) ois.readObject();
//		ois.close();
//		sub2.print();
	}
}


class StringLists implements Serializable {
	private transient int size = 0;
	private transient Entry head = null;
	
	private static class Entry  {
		String data;
		Entry next;
		Entry previous;
		
		public Entry(String s) {
			this.data = s;
		}
	}
	
	public synchronized void add(String s) {
		this.size++;
		Entry entry = new Entry(s);
		
		if(head==null) {
			this.head = entry;
			return;
		} 
		
		Entry temp = head;
		while(temp.next != null) 
			temp = temp.next;
		
		temp.next = entry;
	}
	
	public synchronized void print() {
		while(head != null) {
			System.out.println(head.data);
			
			head = head.next;
		}
	}
	
	public void test() {
		System.out.println();
	}
	
	private synchronized void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();
		s.writeInt(size);
	
		for (Entry e = head; e!=null; e = e.next) {
			s.writeObject(e.data);
		}
	}
	
	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		s.defaultReadObject();
		int numElements = s.readInt();
		int temp = 10;
		for(int i=0; i<numElements; i++) {
			add((String) s.readObject());
		}
	}
}