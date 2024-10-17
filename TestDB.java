package Study;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TestDB {

    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "hr"; 
    private static final String PASSWORD = "hr";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Nhập số sinh viên cần thêm: ");
            int n = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < n; i++) {
                System.out.println("Nhập thông tin sinh viên thứ " + (i + 1) + ":");
                System.out.print("Tên: ");
                String name = scanner.nextLine();
                System.out.print("Giới tính: ");
                String gender = scanner.nextLine();
                System.out.print("Quê quán: ");
                String hometown = scanner.nextLine();
                System.out.print("Tuổi: ");
                int age = scanner.nextInt();
                scanner.nextLine();

                insertStudent(name, gender, hometown, age);
            }
        }
    }

    private static void insertStudent(String name, String gender, String hometown, int age) {
        String insertSQL = "INSERT INTO students (name, gender, hometown, age) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, gender);
            preparedStatement.setString(3, hometown);
            preparedStatement.setInt(4, age);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Thông tin sinh viên đã được thêm thành công!");
            }
        } catch (SQLException e) {
            // Phân loại lỗi dựa trên SQLState hoặc ErrorCode
            String sqlState = e.getSQLState();
            if (sqlState.equals("23000")) {
                System.out.println("Lỗi: Tên sinh viên đã tồn tại. Vui lòng nhập lại tên khác.");
            } else if (sqlState.startsWith("08")) {
                System.out.println("Lỗi: Không thể kết nối tới cơ sở dữ liệu. Vui lòng kiểm tra kết nối.");
            } else {
                System.out.println("Đã xảy ra lỗi không xác định. Vui lòng thử lại.");
            }
        }
    }
}
