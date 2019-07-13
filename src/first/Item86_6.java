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
		System.out.println(sub2.version + " : " + sub2.defaultVersion);   // version : default value2
	}
}

// 기존 직렬화가능 클래스에(class Sub implements Serializable) 
// 직렬화 가능 상위클래스를 추가(class Super) 
class Sub extends Super implements Serializable {
	String version = "version";
}

class Super implements Serializable {
	String defaultVersion = "default value";
	
	private void readObjectNoData() {
		this.defaultVersion = "default value2";
	}
}

class Extern {
	public int age;
	
	public int getAge() {
		return age;
	}
	
	static class Internal implements Serializable {
		private int newAge;
		
		public void setAge() {
			Extern ex = new Extern();
			this.newAge = ex.getAge();
		}
	}
}