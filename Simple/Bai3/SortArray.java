package Simple.Bai3;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

public class SortArray {
	public static void main(String[] args) {
		String filePath = "src/Simple/Bai3/input.txt";
		int[] arr = null;

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath)); // Đọc toàn bộ dòng vào danh sách
            int n = Integer.parseInt(lines.get(0).trim()); // Đọc số lượng phần tử

            arr = new int[n];
            String[] parts = lines.get(1).trim().split(" "); // Tách các phần tử

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(parts[i]);
            }

            // In mảng để kiểm tra
            System.out.print("Mảng trước khi sắp xếp :");
            for (int elem : arr) {
                System.out.print(elem + " " );
            }
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
			quickSort(arr, 0, arr.length - 1);
			System.out.print("Mảng sau khi sắp xếp :");
			for(int i : arr){
				System.out.print(i + " ");
			}
		}
		
	
	public static void quickSort(int[] arr, int low, int high) {
	    if (low < high) {
	        int pi = partition(arr, low, high);

	        // Sắp xếp đệ quy các phần mảng
	        quickSort(arr, low, pi - 1);
	        quickSort(arr, pi + 1, high);
	    }
	}

	public static int partition(int[] arr, int low, int high) {
	    int pivot = arr[high];
	    int i = (low - 1); // Chỉ số của phần tử nhỏ hơn

	    for (int j = low; j < high; j++) {
	        if (arr[j] < pivot) {
	            i++;
	            // Hoán đổi arr[i] và arr[j]
	            int temp = arr[i];
	            arr[i] = arr[j];
	            arr[j] = temp;
	        }
	    }

	    // Hoán đổi arr[i+1] và phần tử pivot
	    int temp = arr[i + 1];
	    arr[i + 1] = arr[high];
	    arr[high] = temp;

	    return i + 1;
	}
}
