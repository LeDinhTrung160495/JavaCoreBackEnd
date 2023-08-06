package ra.run;

import ra.impl.Student;

import java.util.Scanner;

public class StudentManagement {
    static Student[] arrStudents = new Student[100];
    static int numberStudent = 0;
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        do {
            System.out.println("**********MENU**********");
            System.out.println("1. Nhập thông tin n sinh viên(n nhập từ bàn phím)");
            System.out.println("2. Tính điểm trung bình tất cả sinh viên");
            System.out.println("3. Đánh giá xếp loại các sinh viên");
            System.out.println("4. Tính trạng thái của sinh viên");
            System.out.println("5. In thông tin các sinh viên");
            System.out.println("6. Sắp xếp sinh viên tăng dần theo điểm trung bình");
            System.out.println("7. Tìm kiếm sinh viên theo tên sinh viên");
            System.out.println("8. Thống kê sinh viên theo xếp loại");
            System.out.println("9. Thống ");
        }while (true);
    }
}
