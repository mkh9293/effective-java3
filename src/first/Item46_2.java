package first;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Item46_2 {
	public static void main(String[] args) {
		List<String> words = Arrays.asList("test1","test2","test3");
		
		Map<String, List<String>> map = words.stream().collect(Collectors.groupingBy(word -> alphabetize(word)));
		System.out.println(map.keySet());
		System.out.println(map.values());
	}
	
	static String alphabetize(String str) {
		char[] arr = str.toCharArray();
		Arrays.sort(arr);
		return new String(arr);
	}
}
