package first;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public class Item86_2 {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// 직렬화
		Member member = new Member("test");
		byte[] byteMember;
		
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try(ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(member);
				byteMember = baos.toByteArray();
			}
		}
		
		System.out.println(Base64.getEncoder().encodeToString(byteMember));
		//rO0ABXNyAAxmaXJzdC5NZW1iZXLVGDEOeRvjaAIAAkkAA2FnZUwABG5hbWV0ABJMamF2YS9sYW5nL1N0cmluZzt4cAAAAAx0AAR0ZXN0
		
		// 역직렬화
		String base64Member = "rO0ABXNyAAxmaXJzdC5NZW1iZXJSGF4DQEBi+wIAAkkAA2FnZUwABG5hbWV0ABJMamF2YS9sYW5nL1N0cmluZzt4cAAAAAB0AAR0ZXN0";
		byte[] decodeByteMember = Base64.getDecoder().decode(base64Member);
		try(ByteArrayInputStream bais = new ByteArrayInputStream(decodeByteMember)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				Object objectMember = ois.readObject();
				Member members = (Member) objectMember;
				System.out.println(members);
			}
		}
		
	}
}

class Member implements Serializable {
	private String name = null;
	private int age;
	
	public Member(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "name : " + name + " age : " + age;
	}
}