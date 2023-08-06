import java.util.Scanner;

public class StudentImp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student[] arrStudents = new Student[100];
        int index = 0;
        do {
            System.out.println("***********MENU**********");
            System.out.println("1. Nhập vào thông tin n sinh viên");
            System.out.println("2. Tính điểm trung bình tất cả sinh viên");
            System.out.println("3. Hiển thị thông tin tất cả sinh viên: ");
            System.out.println("4. Sắp xếp sinh viên theo điểm trung bình giảm dần");
            System.out.println("5. Tìm kiếm sinh viên theo tên sinh viên");
            System.out.println("6. Thoát");
            System.out.println("Sự lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    System.out.println("Nhập số sinh viên cần nhập dữ liệu: ");
                    int n = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < n; i++) {
                        System.out.println("Nhập thông tin sinh viên thứ "+(index+1));
                        arrStudents[index]=new Student();
                        arrStudents[index].inputData(scanner);
                        index++;
                    }
                    break;
                case 2:
                    for (int i = 0; i < index; i++) {
                        arrStudents[i].calAvgScore();
                    }
                    System.out.println("Đã tính xong điểm trung bình cho tất cả sinh viên");
                    break;
                case 3:
                    //Hiển thị thông tin sinh viên
                    System.out.println("THÔNG TIN CÁC SINH VIÊN");
                    for (int i = 0; i < index; i++) {
                        System.out.println("Thông tin sinh viên thứ: "+(i+1));
                        arrStudents[i].displayData();
                    }
                    break;
                case 4:
                    //Sắp xếp sinh viên theo điểm trung bình giảm dần
                    for (int i = 0; i < index-1; i++) {
                        for (int j = i+1; j < index; j++) {
                            if(arrStudents[i].getAvgScore()<arrStudents[j].getAvgScore()){
                                Student studentTemp = arrStudents[i];
                                arrStudents[i]=arrStudents[j];
                                arrStudents[j]=studentTemp;
                            }
                        }
                    }
                    System.out.println("Đã sắp xếp sinh viên theo điểm trung bình giảm dần");
                    break;
                case 5:
                    //Tìm kiếm sinh viên theo tên
                    //Nhập vào tên tìm kiếm
                    System.out.println("Hãy nhập tên mà bạn muốn tìm");
                    String searchedStudentName = scanner.nextLine();
                    System.out.println("Thông tin sinh viên mà bạn muốn tìm");
                    // Duyệt qua hàm for để tìm tên
                    boolean isExist=false;
                    for (int i = 0; i < index; i++) {
                        if(arrStudents[i].getStudentName().toLowerCase().contains(searchedStudentName.toLowerCase())){
                            arrStudents[i].displayData();
                            isExist=true;
                        }
                    }
                    if(!isExist){
                        System.out.println("Không tồn tại tên sinh viên mà bạn muốn tìm");
                    }
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập từ 1-6");
            }
        } while (true);
    }
}
