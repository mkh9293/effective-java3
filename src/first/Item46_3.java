package first;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

public class Item46_3 {
	public static void main(String[] args) {
		List<BlogPost> posts = Arrays.asList(
				new BlogPost("testTitle1", "testAuthor1", BlogPostType.NEWS, 1),
				new BlogPost("testGuide1", "testGuide1", BlogPostType.GUIDE, 5),
				new BlogPost("testGuide1", "testGuide1", BlogPostType.GUIDE, 22),
				new BlogPost("testGuide3", "testGuide1", BlogPostType.NEWS, 50),
				new BlogPost("testGuide2", "testGuide2", BlogPostType.GUIDE, 10),
				new BlogPost("testReview1", "testReview1", BlogPostType.REVIEW, 10));
		
		// BlogPostType 으로 BlogPost 를 매핑
		Map<BlogPostType, List<BlogPost>> postsPerType = posts.stream().collect(groupingBy(BlogPost::getType));
		System.out.println(postsPerType.keySet());
		System.out.println(postsPerType.values());
		
		System.out.println();
		
		// BlogPostType과 author로 BlogPost를 매핑 (BlogPostType과 author가 같은 것들로만 매핑)
		Map<Tuple, List<BlogPost>> postsPerTypeAuthor = posts.stream().collect(groupingBy(post -> new Tuple(post.getType(), post.getAuthor())));
		System.out.println(postsPerTypeAuthor.keySet());
		System.out.println(postsPerTypeAuthor.values());
		
		System.out.println();
		
		// downstream collector를 set으로 지정
		Map<BlogPostType, Set<BlogPost>> postPerTypeToSet = posts.stream().collect(groupingBy(BlogPost::getType, toSet()));
		System.out.println(postPerTypeToSet.keySet());
		System.out.println(postPerTypeToSet.values());
		
		System.out.println();
		
		// grouping by multiple Fields (첫번째 그룹화에 따라 두번째 그룹화를 수행)
		Map<String, Map<BlogPostType, List<BlogPost>>> map = posts.stream().collect(groupingBy(BlogPost::getAuthor, groupingBy(BlogPost::getType)));
		for(String key : map.keySet()) {
			System.out.println("key : "+key);
			System.out.println("value : "+map.get(key));
		}
		
		System.out.println();
		
		// downstream collector를 이용하여 type 별로 likes의 평균을 구하기
		Map<BlogPostType, Double> averageLikesPerType = posts.stream().collect(groupingBy(BlogPost::getType, averagingInt(BlogPost::getLikes)));
		for(BlogPostType key : averageLikesPerType.keySet()) {
			System.out.println("key : "+key);
			System.out.println("value : "+averageLikesPerType.get(key));
		}
		
		System.out.println();
		
		// downstream collector를 이용하여 type 별로 likes의 합계 구하기
		Map<BlogPostType, Integer> sumLikesPerType = posts.stream().collect(groupingBy(BlogPost::getType, summingInt(BlogPost::getLikes)));
		for(BlogPostType key : sumLikesPerType.keySet()) {
			System.out.println("key : "+key);
			System.out.println("value : "+sumLikesPerType.get(key));
		}
		
		System.out.println();
		
		// downstream collector를 이용하여 type 별로 likes가 가장 큰 type 구하기 
		Map<BlogPostType, Optional<BlogPost>> maxLikesPerPostType = posts.stream().collect(groupingBy(BlogPost::getType, maxBy(Comparator.comparingInt(BlogPost::getLikes))));
		for(BlogPostType key : maxLikesPerPostType.keySet()) {
			System.out.println("key : "+key);
			System.out.println("value : "+maxLikesPerPostType.get(key));
		}
		
		System.out.println();
		
		// type 별로 각 count, sum, average, min, max 값을 출력해줌
		Map<BlogPostType, IntSummaryStatistics> likeStatisticsPerType = posts.stream().collect(groupingBy(BlogPost::getType, summarizingInt(BlogPost::getLikes)));
		for(BlogPostType key : likeStatisticsPerType.keySet()) {
			System.out.println("key : "+key);
			System.out.println("value : "+likeStatisticsPerType.get(key));
		}
		
		System.out.println();
		
		// joining 을 이용하여 결과 값 합치기
		Map<BlogPostType, String> joinTitlePerType = posts.stream().collect(groupingBy(BlogPost::getType, mapping(BlogPost::getTitle, joining(", ", "Post titles [", "]"))));
		for(BlogPostType key : joinTitlePerType.keySet()) {
			System.out.println("key : "+key);
			System.out.println("value : "+joinTitlePerType.get(key));
		}
		
		// 리턴 타입을 명시하기(변경하기)
		EnumMap<BlogPostType, List<BlogPost>> toEnumMapPerType = posts.stream().collect(groupingBy(BlogPost::getType, () -> new EnumMap<>(BlogPostType.class), toList()));
		
		// 리턴 타입 클래스를 동시성 보장하는 Map으로 받기
		ConcurrentMap<BlogPostType, List<BlogPost>> concPostsPerType = posts.stream().collect(groupingByConcurrent(BlogPost::getType));
	}
}

class BlogPost {
	String title;
	String author;
	BlogPostType type;
	int likes;
	
	public BlogPost(String title, String author, BlogPostType type, int likes) {
		this.title = title;
		this.author = author;
		this.type = type;
		this.likes = likes;
	}
	
	public BlogPostType getType() {
		return type;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public int getLikes() {
		return likes;
	}
	
	public String getTitle() {
		return title;
	}
	
	@Override
	public String toString() {
		return this.title + " : " + this.author +" : "+this.type+" : "+this.likes;
	}
}

enum BlogPostType {
	NEWS, REVIEW, GUIDE
}

class Tuple {
	BlogPostType type;
	String author;
	
	private int hashCode;
	
	public Tuple(BlogPostType type, String author) {
		this.type = type;
		this.author = author;
	}
	
	@Override
	public String toString() {
		return this.type + " : "+this.author;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(!(o instanceof Tuple)) return false;
		Tuple tp = (Tuple) o;
		return tp.type == type && tp.author == author;
	}
	
	@Override
	public int hashCode() {
		int result = hashCode;
		if(result == 0) {
			result = type.hashCode();
			result = 31 * result * author.hashCode();
		}
		return result;
	}
}