package Study;
import java.util.Scanner;
public class Test1 {

	public static void main(String[] args) {
		int ans = 10;
		int cnt = 0;
		try (Scanner input = new Scanner(System.in)) {
			int check = 0;
			while(cnt <= 5) {
				if(check != ans) {
					check = input.nextInt();
				}
				else
				{ 
					System.out.println("Thành công");
					break;
				}
				cnt++;
				if(cnt == 5 && check != ans) {
					System.out.println("Nhập quá số lần, số cần nhập là 10");
					break;
				}

			}
		}

	}

}
