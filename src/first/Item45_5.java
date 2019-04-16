package first;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 데카르트 곱 (반복문, 스트림)
 */
public class Item45_5 {
	public static void main(String[] args) {
		newDeck().forEach(System.out::println);
	}
	
	// 반복문 방식
	/*private static List<Card> newDeck() {
		List<Card> result = new ArrayList<>();
		for(Suit suit : Suit.values()) 
			for(Rank rank : Rank.values())
				result.add(new Card(suit, rank));
		
		return result;
	}*/
	
	// 스트림 사용
	private static List<Card> newDeck() {
		return Stream.of(Suit.values())
				.flatMap(suit -> Stream.of(Rank.values()).map(rank -> new Card(suit, rank)))
				.collect(Collectors.toList());
	}
}

final class Card {
	Suit suit;
	Rank rank;
	
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return "suit : "+suit+" , rank : "+rank;
	}
}
enum Suit {
	SUIT1, SUIT2
}
enum Rank {
	RANK1, RANK2
}