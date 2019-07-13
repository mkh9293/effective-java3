package first;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Item87_2 {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Custom custom = new Custom();
		Custom2 custom2 = new Custom2();
		String[] arr = new String[custom2.getDatas().size()];
		arr[0] = custom2.getDatas().get(0).toString();
		arr[1] = custom2.getDatas().get(1).toString();
		custom.SetCustom2(arr);
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temp.txt"));
		oos.writeObject(custom);
		oos.close();
		
		// deserializable
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("temp.txt"));
		Custom sub2 = (Custom) ois.readObject();
		ois.close();
		System.out.println(sub2);
	}
}

class Custom implements Serializable {
	private String data;
	private String[] datas;
	
	public Custom() {
		this.data = "i am Custom";
	}
	
	public void SetCustom2(String[] datas) {
		this.datas = datas;
	}
	
	@Override
	public String toString() {
		return this.data + " , " + Arrays.toString(this.datas);
	}
	
}

class Custom2 {
	private final List<Data> datas; 
	
	public Custom2() {
		datas = new ArrayList<>();
		datas.add(new Data());
		datas.add(new Data());
	}
	
	public List<Data> getDatas() {
		return datas;
	}
}

class Data {
	private String code = "code";
	private String tempCode = "temp code";
	
	@Override
	public String toString() {
		return "code : " + code + " , tempCode : " + tempCode;
	}
}
