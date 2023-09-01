package ra.impl;

import ra.entity.Student;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class StudentImpl {
    static List<Student> listStudent = new ArrayList<>();

    public static void main(String[] args) {
        readDataFromFile();//No
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("1. Nhập thông tin các sinh viên");
            System.out.println("2. Tính tuổi các sinh viên");
            System.out.println("3. Tính điểm trung bình và xếp loại");
            System.out.println("4. Sắp xếp sinh viên theo tuổi tăng dần");
            System.out.println("5. Thống kê sinh viên theo xếp loại sinh viên");
            System.out.println("6. Cập nhập thông tin sinh viên");
            System.out.println("7. Tìm kiếm sinh viên theo tên");
            System.out.println("8. Hiện thị thông tin tất cả sinh viên");
            System.out.println("9. Thoát");
            System.out.println("Sự lựa chọn của bạn: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        StudentImpl.inputListStudent(scanner);
                        break;
                    case 2:
                        StudentImpl.calListAge();
                        break;
                    case 3:
                        StudentImpl.calAvg_rank();
                        break;
                    case 4:
                        StudentImpl.sortStudent();
                        break;
                    case 5:
                        StudentImpl.statistic();
                        break;
                    case 6:
                        StudentImpl.updateStudent(scanner);
                        break;
                    case 7:
                        StudentImpl.searchStudentByName(scanner);
                        break;
                    case 8:
                        StudentImpl.displayListStudent();
                        break;
                    case 9:
                        writeDataToFile();
                        System.exit(0);
                    default:
                        System.err.println("Vui lòng chọn từ 1-8");
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Vui lòng chọn số");
            } catch (Exception ex) {
                System.err.println("Có lỗi không xác định, vui lòng liên hệ hệ thống");
            }
        } while (true);
    }

    public static void readDataFromFile() {
        File file = new File("listStudent.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            if (ois.readObject() != null) {
                listStudent = (List<Student>) ois.readObject();
            }
        } catch (FileNotFoundException ex1) {
            System.err.println("Không tồn tại File");
        } catch (IOException ex2) {
            System.err.println("Lỗi khi đọc File");
        } catch (ClassNotFoundException ex) {
            System.err.println("Lớp không tồn tại");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    System.err.println("Lỗi IO");
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    System.err.println("Lỗi IO");
                }
            }
        }
    }

    public static void writeDataToFile() {
        File file = new File("listStudent.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listStudent);
            oos.flush();
        } catch (FileNotFoundException ex1) {
            System.err.println("File không tồn tại");
        } catch (IOException ex2) {
            System.err.println("Lỗi IO");
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ex1) {
                    System.err.println("Lỗi runtime");
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException ex2) {
                    System.err.println("Lỗi IO");
                }
            }
        }
    }

    public static void inputListStudent(Scanner scanner) {
        System.out.println("Nhập vào số sinh viên cần nhập dữ liệu:");
        do {
            try {
                int number = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < number; i++) {
                    Student student = new Student();
                    student.inputData(scanner, listStudent);
                    listStudent.add(student);
                }
                break;
            } catch (NumberFormatException nfe) {
                System.err.println("Vui lòng nhập vào một số nguyên dương");
            }
        } while (true);
    }

    public static void calListAge() {
        //Java8
        listStudent.forEach(student -> student.callAge());
        System.out.println("Đã tính xong tuổi cho tất cả các sinh viên");
    }

    public static void calAvg_rank() {
        //Java8
        listStudent.forEach(student -> student.calAvgMark_Rank());
        System.out.println("Đã tính xong điểm trung bình và xếp loại cho tất cả các sinh viên");
    }

    public static void sortStudent() {
        //Java8
        listStudent.sort(Comparator.comparing(Student::getAge));
        System.out.println("Đã sắp xếp sinh viên theo tuổi tăng dần");
    }

    public static void statistic() {
        //Java8
        int cntYeu = (int) listStudent.stream().filter(student -> student.getClassification().equals("Yếu")).count();
        int cntTb = (int) listStudent.stream().filter(student -> student.getClassification().equals("Trung bình")).count();
        int cntKha = (int) listStudent.stream().filter(student -> student.getClassification().equals("Khá")).count();
        int cntGioi = (int) listStudent.stream().filter(student -> student.getClassification().equals("Giỏi")).count();
        int cntXS = (int) listStudent.stream().filter(student -> student.getClassification().equals("Xuất sắc")).count();
        System.out.printf("Thống kê: Xuất sắc: %d - Giỏi: %d - Khá: %d - Trung bình: %d - Yếu: %d\n", cntXS, cntGioi, cntKha, cntTb, cntYeu);
    }

    public static void updateStudent(Scanner scanner) {
        System.out.println("Nhập vào mã sinh viên cần cập nhập: ");
        String studentId = scanner.nextLine();
        int index = -1;
        for (int i = 0; i < listStudent.size(); i++) {
            if (listStudent.get(i).getStudentId().equals(studentId)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            listStudent.get(index).setStudentName(Student.validateStudentName(scanner));
            listStudent.get(index).setSex(Student.validateSex(scanner));
            listStudent.get(index).setBirthday(Student.validateBirthDay(scanner));
            listStudent.get(index).setHtmlScore(Student.validateScoreHTML(scanner));
            listStudent.get(index).setCssScore(Student.validateScoreCss(scanner));
            listStudent.get(index).setJavascriptScore(Student.validateScoreJavascript(scanner));
            listStudent.get(index).callAge();
            listStudent.get(index).calAvgMark_Rank();
        } else {
            System.err.println("Không tồn tại mã số sinh viên như vậy, vui lòng nhập lại");
        }
    }

    public static void searchStudentByName(Scanner scanner) {
        System.out.println("Nhập vào tên sinh viên cần tìm: ");
        String studentName = scanner.nextLine();
        //Java8
        List<Student>searchedListStudentByName=listStudent.stream().filter(student ->student.getStudentName().toLowerCase().contains(studentName.toLowerCase())).collect(Collectors.toList());
        searchedListStudentByName.forEach(student -> student.displayData());
    }

    public static void displayListStudent() {
        //Java8
        listStudent.forEach(student -> student.displayData());
    }
}

