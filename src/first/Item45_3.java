package first;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Item45_3 {
	public static void main(String[] args) {
		String dics = args[0];
		int min = Integer.parseInt(args[1]);
		
		try(Stream<String> words = Pattern.compile(" ").splitAsStream(dics)) {
			words.collect(Collectors.groupingBy(word -> alphabetize(word))).values().stream()
			     .filter(group -> group.size() >= min)
			     .forEach(g -> System.out.println(g.size() + " : "+g));
		}
	}
	
	static String alphabetize(String word) {
		char[] a = word.toCharArray();
		Arrays.sort(a);
		return new String(a);
	}
}
