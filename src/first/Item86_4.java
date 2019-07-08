package first;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public class Item86_4 {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// 직렬화
		Notebook notebook = new Notebook();
		byte[] byteMember;
		
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try(ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(notebook);
				byteMember = baos.toByteArray();
			}
		}
		
		System.out.println(Base64.getEncoder().encodeToString(byteMember));
		
		// 역직렬화
		String base64Member = "rO0ABXNyAA5maXJzdC5Ob3RlYm9va2E57rxBftYsAgACTAAHbWFjaGluZXQAD0xmaXJzdC9NYWNoaW5lO0wABG5hbWV0ABJMamF2YS9sYW5nL1N0cmluZzt4cHBw";
		byte[] decodeByteMember = Base64.getDecoder().decode(base64Member);
		try(ByteArrayInputStream bais = new ByteArrayInputStream(decodeByteMember)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				Object objectMember = ois.readObject();
				Notebook notebooks = (Notebook) objectMember;
				System.out.println(notebooks);
			}
		}
	}
}

// 인터페이스에 메소드가 추가될 경우,
// 해당 인터페이스를 구현한 클래스들은 계속해서 내부가 변경된다.
interface Machine extends Serializable {
	public void test();
}

class Notebook implements Machine {
	public Machine machine;
	private String name;
	
	@Override
	public void test() {}
	
	@Override
	public String toString() {
		return "name : " + this.name;
	}
}

class Desktop implements Machine {
	private String name;
	private int age;
	
	public String getName() {
		return this.name;
	}
	
	public int getAge() {
		return this.age;
	}
	
	@Override
	public void test() {}
	
	@Override
	public String toString() {
		return "name : " + this.name;
	}
}

