package Simple.Bai2;
import java.util.*;

public class ElectricMoneyCalc {
	public static void CalculateEMoney(int a) {
		int ans = 0;
		if (0 < a && a <= 100) {
			ans += a * 1000;
		} else if (100 < a && a <= 150) {
			ans = ans + 100 * 1000 + (a - 100) * 1500;
		} else if (150 < a) {
			ans = ans + 100 * 1000 + 50 * 1500 + (a - 150) * 2000;
		} else if (a < 0) {
			System.out.print("Invalid Number");

		}
		System.out.print(ans + " đ");

	}


	public static void main(String[] args) {
		try (// TODO Auto-generated method stub
			 Scanner input = new Scanner(System.in)) {
			int number = input.nextInt();
			CalculateEMoney(number);
		}
	}

}
