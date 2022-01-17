package prob01;

import java.util.Arrays;
import java.util.Scanner;

public class Sort {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Before sort");
		String []  input = scanner.nextLine().split(" ");
		int[] nums = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
		
		System.out.println("\nAfter Sort.");
		for(int i = 0; i< nums.length; i++) {
			for(int j =1, tmp = 0; j <nums.length-i; j++) {
				if(nums[j-1] < nums[j]) {
					tmp = nums[j];
					nums[j] = nums[j-1];
					nums[j-1] = tmp; 
				}
			}
		}
		
		for(int num : nums) {
			System.out.print(num+" ");
		}
		
	}
}
