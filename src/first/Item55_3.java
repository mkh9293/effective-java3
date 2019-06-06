package first;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Item55_3 {
	public static void main(String[] args) {
		// java9 에서 os 프로세스 관리, 컨트롤을 위한 패키지가 추가됨
		ProcessHandle ph = ProcessHandle.current();
		
		// null 체크를 isPrsent() 로 했을 때.
		Optional<ProcessHandle> parentProcess = ph.parent();
		System.out.println("부모 PID: " + 
				(parentProcess.isPresent() ? String.valueOf(parentProcess.get().pid()) : "N/A" ));

		// null 체크를 orElse() 로 했을 때.
		System.out.println("부모 PID: " + ph.parent().map(h -> String.valueOf(h.pid())).orElse("N/A"));
		
		
		// Stream 혹은 List 를  Optional<T> 타입으로 받아 처리하는 경우
		List<Optional<String>> listOfOptionals = Arrays.asList(
									Optional.empty(), Optional.of("one"), Optional.empty(), Optional.of("two"));
		
		// 필터로 옵셔널에 값이 있으면 map(매핑) 한다.
		List<String> filteredList = listOfOptionals.stream()
											.filter(Optional::isPresent)
											.map(Optional::get)
											.collect(Collectors.toList());
		
		filteredList.forEach(x -> System.out.println(x)); // one two
		
		// java9에서 소개된 Optional의 stream()메소드.
		// Optional을 stream으로 변환해주고 값의 유무에 따른 스트림으로 변환해준다.
		List<String> filteredList2 = listOfOptionals.stream()
													.flatMap(Optional::stream)
													.collect(Collectors.toList());
		filteredList2.forEach(x -> System.out.println(x)); // one two
		
	
		// 하면 안되는 것  1. - 컬렉션, 스트림, 배열, 옵셔널 같은 컨테이너 타입은 옵셔널로 감싸면 안됨.
		// Optional<List<T>> Optional<Map<T, T>> Optional<Stream<T>>...
		// Optional<int[]> Optional<Optional<T>> ...
		
		// 하면 안되는 것  2. - 박싱된 기본 타입을 담은 옵셔널도 반환해선 안됨.
		// Optional<Integer> Optional<Double> ...
		
		// 하면 안되는 것 3. - 옵셔널을 맵의 값으로 사용하면 암됨.
		// Map<K, Optional<V>> map, Map<Optional<K>, Optional<V>> map
		// Map<Optional<K>, V> map
	}
}

// 활용방안으로 필드가 필수 필드인지, 선택적 필드인지 명시가 가능하다.
class NutritionFacts {
	private int a; // 필수 필드
	private OptionalInt b; // 선택 필드 (명시적)
	
	public int getb() {
		return b.orElse(0);
	}
}
