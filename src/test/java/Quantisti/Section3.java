package Quantisti;

public class Section3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = 1000;
		int sum =0;

		for (int j = 0; j < i; j++) {

			if (j % 3 == 0 || j % 5 == 0) {

			sum = sum + j ;
			
			
			}

		}
	
	System.out.println(sum);
	}

}
