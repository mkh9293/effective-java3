package first;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Item87_3 {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Name name = new Name(null , null , null);
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temp.txt"));
		oos.writeObject(name);
		oos.close();
		
		// deserializable
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("temp.txt"));
		Name sub2 = (Name) ois.readObject();
		ois.close();
		System.out.println(sub2);
	}
}

class Name implements Serializable {
	/**
	 * 성. null이 아니어야 함.
	 * @serial
	 */
	private final String lastName;
	
	/**
	 * 이름. null이 아니어야 함.
	 * @serial
	 */
	private final String firstName;
	
	/**
	 * 중간이름. 중간이름이 없다면 null.
	 * @serial
	 */
	private final String middleName;
	
	public Name(String lastName, String firstName, String middleName) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
	}
	
	@Override
	public String toString() {
		return "lastName : " + lastName + " , firstName : " + firstName + " , middleName : " + middleName;
	}
	
	private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException {
		s.defaultReadObject();
		
		if(lastName == null || firstName == null)
			throw new InvalidObjectException("lastName 또는 firtName은 null이 되면 안된다.");
	}
}
