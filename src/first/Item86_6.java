package first;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Item86_6 {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Sub sub = new Sub();
		
		ObjectOutputStream oos = new ObjectOutputStream(
										new FileOutputStream("temp.txt"));
		oos.writeObject(sub);
		oos.close();
		
		// deserializable
		ObjectInputStream ois = new ObjectInputStream(
										new FileInputStream("temp.txt"));
		Sub sub2 = (Sub) ois.readObject();
		ois.close();
		System.out.println(sub2.version + " : " + sub2.defaultVersion);
	}
}

class Sub extends Super {
	String version = null;
	
	Sub() {
		super();
//		version = "1.1.1";
	}
	private void readObjectNoData() throws InvalidObjectException {
		System.out.println(33);
		throw new InvalidObjectException("123");
	}
	
}

class Super implements Serializable {
	String defaultVersion = null;
	
	private void readObjectNoData() throws InvalidObjectException {
		System.out.println(33);
		throw new InvalidObjectException("123");
	}
}