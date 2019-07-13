package first;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public class Item86_3 {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// 직렬화
		Man2 person = new Man2();
		byte[] byteMember;
		
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try(ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(person);
				byteMember = baos.toByteArray();
			}
		}
		
		System.out.println(Base64.getEncoder().encodeToString(byteMember));
		//rO0ABXNyAAxmaXJzdC5NZW1iZXLVGDEOeRvjaAIAAkkAA2FnZUwABG5hbWV0ABJMamF2YS9sYW5nL1N0cmluZzt4cAAAAAx0AAR0ZXN0
		
		// 역직렬화
		String base64Member = "rO0ABXNyAApmaXJzdC5NYW4yqDPTxyHype8CAAFJAANhZ2V4cgAMZmlyc3QuUGVyc29uPl3EwUl0/kICAAFMAARuYW1ldAASTGphdmEvbGFuZy9TdHJpbmc7eHBwAAAAAA==";
		byte[] decodeByteMember = Base64.getDecoder().decode(base64Member);
		try(ByteArrayInputStream bais = new ByteArrayInputStream(decodeByteMember)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				Object objectMember = ois.readObject();
//				Person persons = (Person) objectMember;
				Man2 persons = (Man2) objectMember;
				System.out.println(persons);
			}
		}
	}
}

class Person implements Serializable {
	private String name;
}

class Man extends Person {
	private int age;
	
	public int getAge() {
		return age;
	}
	
	public void getAge1() {
		System.out.println(123);
	}
	
	@Override
	public String toString() {
		return "age : " + age;
	}
}

class Man2 extends Person {
	private int age;
	
	public int getAge() {
		return age;
	}
	
	@Override
	public String toString() {
		return "age : " + age;
	}
}

