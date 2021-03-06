package first;

import java.io.IOException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 스트림 사용 예시2. (과도한 스트림 사용)
 * 입력받은 minGroupSize 보다 사이즈가 큰 아나그램 집합의 값들을 출력하는 예제
 */
public class Item45_2 {
	public static void main(String[] args) throws IOException{
		// args[0] = "staple stapel stplea staples"
		// args[1] = 2
		String dics = args[0];
		int minGroupSize = Integer.parseInt(args[1]);
		
		try(Stream<String> words = Pattern.compile(" ").splitAsStream(dics)) {
			words.collect(
					Collectors.groupingBy(word -> word.chars().sorted() // words 중 하나의 값을 추출하여 char 배열로 변환 후 정렬 (staple -> ['a','e'...])
							               .collect(StringBuilder::new, (c,a) -> c.append(""), StringBuilder::append).toString()
							               // sb.append((char) c); 에서 char 타입을 명시한 이유는 int값이 리턴되기 때문이다. (char 처리는 스트림을 삼가하자)
							               // -> 책에 자바버전이 낮아 (char)를 명시한듯 하다. 자바8 부턴 어떤 방식으로 누산할지만 정하면 문제없이 작동한다. 
							               
							               // collect 메서드는 3가지 인수를 받는다.
							               // 1. 공급자: 대상 객체의 새로운 인스턴스 생성 (StringBuilder::new)
							               // 2. 누산자: 요소를 대상에 추가 (sb, c) -> sb.append((char) c)  char 배열로 나누어진 값들을 append
							               // 3. 결합자: 두 객체를 하나로 병합 (words의 값들을 위의 루트를 실행하면서 결합시킴)
							               // 정리. 누산자를 통해(이때 타입은 StringBulider::new 타입임) 계산된 값을 StringBuilder::append로 붙임. 
							               
					)
				 )					
			     .values()
			     .stream()
			     .filter(group -> group.size() <= minGroupSize) // 그룹된 값들 중 요소의 개수(size)가 minGroupSize보다 큰 것만 새 요소로 저장
			     .map(group -> group.size() + " : "+ group) // 새 요소들의 size 와 요소들을 size : [...] 형태로 담음
			     .forEach(System.out::println); // 출력
		}
	}
}
