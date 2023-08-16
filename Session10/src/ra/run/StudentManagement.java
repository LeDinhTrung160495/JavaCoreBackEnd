package ra.run;

import ra.impl.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {
    public static List<Student> studentList = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            System.out.println("**********MENU**********");
            System.out.println("1. Nhập thông tin n sinh viên(n nhập từ bàn phím)");
            System.out.println("2. Tính điểm trung bình tất cả sinh viên");
            System.out.println("3. Đánh giá xếp loại các sinh viên");
            System.out.println("4. Tính trạng thái của sinh viên");
            System.out.println("5. In thông tin các sinh viên");
            System.out.println("6. Sắp xếp sinh viên theo điểm trung bình tăng dần ");
            System.out.println("7. Tìm kiếm sinh viên theo tên sinh viên");
            System.out.println("8. Thống kê sinh viên theo xếp loại");
            System.out.println("9. Thống kê sinh viên theo trạng thái ");
            System.out.println("10. Thoát");
            System.out.print("Sự lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    StudentManagement.inputListStudent(scanner);
                    break;
                case 2:
                    StudentManagement.callAvgMarkListStudent();
                    break;
                case 3:
                    StudentManagement.callRank();
                    break;
                case 4:
                    StudentManagement.callStudentStatus();
                    break;
                case 5:
                    StudentManagement.displayListStudent();
                    break;
                case 6:
                    StudentManagement.sortStudentByAvgMarkASC();
                    break;
                case 7:
                    StudentManagement.searchStudentByName(scanner);
                    break;
                case 8:
                    StudentManagement.statisticByRank();
                    break;
                case 9:
                    StudentManagement.statisticByStatus();
                    break;
                case 10:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập 1 số từ 1 - 10");
            }
        } while (true);
    }

    public static void inputListStudent(Scanner scanner) {
        System.out.println("Nhập số sinh viên cần nhập thông tin: ");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin sinh viên thứ " + (i + 1));
            Student student = new Student();
            student.inputData(scanner,studentList);
            studentList.add(student);
        }
    }

    public static void callAvgMarkListStudent() {
        for (Student student:studentList
             ) {
            student.callAvgMark();
        }
        System.out.println("Đã tính xong điểm trung bình của tất cả sinh viên");
    }

    public static void callRank() {
        for (Student student:studentList
        ) {
            student.callClassificationStudent();
        }
        System.out.println("Đã xếp loại cho tất cả các sinh viên");
    }

    public static void callStudentStatus() {
        for (Student student:studentList
        ) {
            student.callStatusStudent();
        }
        System.out.println("Đã tính xong trạng thái tất cả sinh viên");
    }

    public static void displayListStudent() {
        for (Student student:studentList
        ) {
            student.displayData();
        }
    }

    public static void sortStudentByAvgMarkASC() {
        studentList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                return Float.compare(student1.getAvgScore(),student2.getAvgScore());
            }
        });
        System.out.println("Đã sắp xếp sinh viên theo điểm trung bình tăng dần");
    }

    public static void searchStudentByName(Scanner scanner) {
        System.out.println("Nhập vào tên sinh viên cần tìm");
        String studentNameSearch = scanner.nextLine();
        boolean isExist = false;
        System.out.println("Thông tin của sinh viên đang tìm kiếm");
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentName().toLowerCase().contains(studentNameSearch.toLowerCase())) {
                studentList.get(i).displayData();
                isExist = true;
            }
        }
        if (!isExist) {
            System.out.println("Tên sinh viên đang được tìm không tồn tại");
        }
    }

    public static void statisticByRank() {
        int cntYeu = 0,
                cntTB = 0,
                cntKha = 0,
                cntGioi = 0,
                cntXuatSac = 0;
        for (int i = 0; i < studentList.size(); i++) {
            switch (studentList.get(i).getClassification()) {
                case "Xếp loại yếu":
                    cntYeu++;
                    break;
                case "Xếp loại trung bình":
                    cntTB++;
                    break;
                case "Xếp loại khá":
                    cntKha++;
                    break;
                case "Xếp loại giỏi":
                    cntGioi++;
                    break;
                default:
                    cntXuatSac++;
            }
        }
        System.out.printf("Xuất sắc: %d - Giỏi: %d - Khá: %d - Trung bình: %d - Yếu: %d\n", cntXuatSac, cntGioi, cntKha, cntTB, cntYeu);
    }

    public static void statisticByStatus() {
        int cntPass = 0, cntFail = 0;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStatus().equals("PASS")) {
                cntPass++;
            } else {
                cntFail++;
            }
        }
        System.out.printf("Đậu: %d - Rớt: %d\n", cntPass, cntFail);
    }
}