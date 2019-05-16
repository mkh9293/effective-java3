package first;

/**
 * 가변인수는 신중히 사용.
 */
public class Item53 {
	public static void main(String[] args) {
//		System.out.println(sum());
//		System.out.println(min());
		System.out.println(min2(0,1));
	}
	
	public static int min2(int firstArg, int...remaingArgs) {
		int min = firstArg;
		for(int arg : remaingArgs) {
			if(arg < min) min = arg;
		}
		return min;
	}
	
	public static int min(int...args) {
		if(args.length == 0) {
			throw new IllegalArgumentException("인수가 1개 이상 필요함.");
		}
		int min = args[0];
		
		for(int i=1; i<=args.length; i++) {
			if(args[i] < min) min = args[i];
		}
		
		return min;
	}
	
	public static int sum(int... args) {
		int sum = 0;
		for(int arg : args) {
			sum += arg;
		}
		return sum;
	}
}
