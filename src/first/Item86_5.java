package first;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public class Item86_5 {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// 직렬화
		Employees emp = new Employees();
		byte[] byteMember;
		
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try(ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(emp);
				byteMember = baos.toByteArray();
			}
		}
	
//		System.out.println(Base64.getEncoder().encodeToString(byteMember));
		// 역직렬화
		byte[] decodeByteMember = Base64.getDecoder().decode(Base64.getEncoder().encodeToString(byteMember));
		try(ByteArrayInputStream bais = new ByteArrayInputStream(decodeByteMember)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				Employees emps = (Employees) ois.readObject();
				System.out.println(emps.name);
				System.out.println(emps.address);
				System.out.println(emps.age);
			}
		}
		

        
		
	}
}

class Employees extends Persons implements Serializable {

	String address;
	int age = -1;
	
	public Employees() {
		try {
			if(age < 0) {
	            throw new IllegalArgumentException("Negative Zombie2 value");
	        }
		} catch (IllegalArgumentException e) {
//			System.gc();
//	        System.runFinalization();
	        this.finalize();
		}
		  
	        this.age = age;
	        
	}
	
	@Override
	public void finalize() {
		super.name = "11";
		super.someThing();
	}

}	

class Persons implements Serializable{

	String name = "name";
	int age;
	
	  public void someThing() {
	        System.out.println(22);
	    }
	
	private void readObjectNoData() throws InvalidObjectException {
		name = "123";
		age = 1;
	}
	
//	@Override
//	protected final void finalize() {
//	}

}
