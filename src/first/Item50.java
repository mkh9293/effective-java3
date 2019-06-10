package first;

import java.util.Date;

/**
 * 
 * 적시에 방어적 복사본은 생성하라.
 * 
 * 1. Date 타입은 낡은 api 이니 왠만하면 Instant, LocalDateTime, ZonedDateTime을 사용해라
 * 2. 
 */
public class Item50 {
	public static void main(String[] args) {
	
		// Date 클래스는 불변객체가 아니므로 언제 어디서든 변경가능하다.
		Period p = new Period(new Date(), new Date());
		
	}
}


// !!이렇게 가변 변수들은 외부 보낼 때, 내부로 가지고 올 때 클라이언트에 의해서 변경가능 하니 모두 조심해야 한다.!!
// 만약 레거시 코드가 Date 타입을 사용하면서,
// Period 클래스를 사용하는 클라이언트를 신뢰할 수 없다면,
// 방어적 복사본을 사용해야 한다!
class Period {
	private final Date start;
	private final Date end;
	
	// 매개변수 start, end의 시간으로 새로운 Date 클래스를 만든다.
	// 새로운 Date 클래스는 Period에서 만들었으므로 신뢰가능한 Date 클래스라는 것 명심.
	public Period(Date start, Date end) {
		this.start = new Date(start.getTime());
		this.end = new Date(end.getTime());
		
		// 멀티스레드에서 위험성을 최소화 하기 위해,
		// 새로운 객체를 생성한 후에 유효성 검사 진행.
		// 만약 유효성 검사를 먼저 진행 할 경우, 다른 스레드에서 유효성을 통과한 필드를 바꿀 수도 있기 때문이다.
		if(this.start.compareTo(this.end) > 0) {
			throw new IllegalArgumentException(
					this.start + " is late than " + this.end);
		}
	}
	
	// 외부로 보낸 내부 필드를 방어하기 위해 새로운 객체를 만들어서 보낸다.
	// 이렇게 가변 변수들은 외부 보낼 때, 내부로 가지고 올 때 클라이언트에 의해서 변경가능 하니 모두 조심해야 한다.
	public Date getStart() {
		return new Date(this.start.getTime());
	}
	
	public Date getEnd() {
		return new Date(this.end.getTime());
	}
}