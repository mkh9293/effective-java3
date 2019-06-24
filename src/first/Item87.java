package first;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Item87 {
	public static void main(String[] args) {
		
	}
}

/**
 * 커스텀 직렬화하여 기존 데이터를 모두 복원하고 필요없는 데이터는 직렬화 대상에서 제외함.
 */
final class StringList implements Serializable {
	private transient int size = 0;
	private transient Entry head =null;
	
	private static class Entry {
		String data;
		Entry next;
		Entry previous;
	}
	
	public final void add(String s) {
		
	}
	
	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();
		s.writeInt(size);
		
		for (Entry e = head; e!=null; e = e.next) {
			s.writeObject(e.data);
		}
	}
	
	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		s.defaultReadObject();
		int numElements = s.readInt();
		
		for(int i=0; i<numElements; i++) {
			add((String) s.readObject());
		}
	}
	
}
