package first;

import java.util.Objects;

public class Item49_2 {
	public static void main(String[] args) {
		String str = null;
		
		String s = Objects.requireNonNull(str, "없음");
		System.out.println(s);
	}
}
