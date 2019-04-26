package first;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class Item46 {
	public static void main(String[] args) {
		String str = "main main args main args string string args main";
		
		// 잘못된 코드
		Map<String, Long> freq = new HashMap<>();
		try(Stream<String> words = Stream.of(str.split(" "))) {
			words.forEach(word -> {freq.merge(word.toLowerCase(), 1L, Long::sum);});
		}
		
		// 단어별 빈도 수 구하기
		Map<String, Long> map;
		try(Stream<String> words = Stream.of(str.split(" "))){
			map = words.collect(groupingBy(String::toLowerCase, counting()));
		}
		
		// 빈도수가 가장 높은 순서대로 2개 뽑기
		List<String> list = map.keySet().stream()
				               .sorted(Comparator.comparing(map::get).reversed()).peek(i->{System.out.println(map.get(i));})
				               .limit(2)
				               .collect(toList());
		list.forEach(System.out::println);
		
		
		
		// toMap 메서드 (스트림을 맵으로 취합)
		List<Album> albumList = Arrays.asList(new Album[] {
			new Album(new Artist("moon"), 15),
			new Album(new Artist("moon"), 10),			
			new Album(new Artist("kim"), 15),
			new Album(new Artist("jin"), 5)
		});
		
		// 키가 중복될 경우, sale 값이 더 큰 것으로 값 매핑 
		Map<Artist, Album> topHists = albumList.stream().collect(
										toMap(Album::getArtist,  
											  a->a, 
											  BinaryOperator.maxBy(Comparator.comparing(Album::getSales))));
		for(Artist keys : topHists.keySet()) {
			System.out.println(topHists.get(keys).toString());
		}
		
		// 키가 중복될 경우, 마지막에 쓴 값으로 매핑
		Map<Artist, Album> lastHits = albumList.stream().collect(toMap(Album::getArtist, a->a, (oldVal, newVal) -> newVal));
		for(Artist keys : lastHits.keySet()) {
			System.out.println(lastHits.get(keys).toString());
		}
	}
}

class Album {
	private Artist artist;
	private int sales;
	
	public Album(Artist artist, int sales) {
		this.artist = artist;
		this.sales = sales;
	}
	
	public Artist getArtist() {
		return this.artist;
	}
	public int getSales() {
		return this.sales;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(!(o instanceof Album)) return false;
		Album album = (Album) o;
		return album.artist == artist && album.sales == sales;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(artist, sales);
	}

	
	@Override
	public String toString() {
		return this.artist +" : "+this.sales;
	}
}

class Artist {
	private String name;
	
	public Artist(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(!(o instanceof Artist)) return false;
		Artist artist = (Artist) o;
		return artist.name == name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		return this.name;
	}
}
