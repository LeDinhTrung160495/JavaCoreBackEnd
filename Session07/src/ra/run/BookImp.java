package ra.run;

import ra.entity.Book;

import java.util.Scanner;

public class BookImp {
    static Book[] arrBooks = new Book[100];
    static int index = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("***********MENU***********");
            System.out.println("1. Nhập thông tin n sách (n nhập từ bàn phím)");
            System.out.println("2. Tính lợi nhuận các sách");
            System.out.println("3. Hiển thị thông tin sách");
            System.out.println("4. Sắp xếp sách theo giá bán tăng dần");
            System.out.println("5. Sắp xếp sách theo giá bán giảm dần");
            System.out.println("6. Tìm sách theo tên sách (tên sách nhập từ bàn phím)");
            System.out.println("7. Thống kê số lượng sách theo năm xuất bản");
            System.out.println("8. Thống kê số lượng sách theo tác giả");
            System.out.println("9. Thoát");
            System.out.print("Sự lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    BookImp.inputListBook(scanner);
                    break;
                case 2:
                    BookImp.callListInterest();
                    break;
                case 3:
                    System.out.println("THÔNG TIN CÁC SÁCH:");
                    for (int i = 0; i < index; i++) {
                        arrBooks[i].displayData();
                    }
                    break;
                case 4:
                    BookImp.sortBookByExportPriceASC();
                    break;
                case 5:
                    BookImp.sortBookByInterestDESC();
                    break;
                case 6:
                    BookImp.searchBookByName(scanner);
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập từ 1-9");
            }
        } while (true);
    }

    public static void inputListBook(Scanner scanner) {
        System.out.println("Nhập số sách cần nhập dữ liệu: ");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            arrBooks[index] = new Book();
            arrBooks[index].inputData(scanner, arrBooks, index);
            index++;
        }
    }

    public static void callListInterest() {
        for (int i = 0; i < index; i++) {
            arrBooks[i].getInterest();
        }
        System.out.println("Lợi nhuận của các sách đã được tính");
    }

    public static void sortBookByExportPriceASC() {
        for (int i = 0; i < index - 1; i++) {
            for (int j = i + 1; j < index; j++) {
                if (arrBooks[i].getExportPrice() > arrBooks[j].getExportPrice()) {
                    Book bookTemp = arrBooks[i];
                    arrBooks[i] = arrBooks[j];
                    arrBooks[j] = bookTemp;
                }
            }
        }
        System.out.println("Đã sắp xếp sách theo giá bán tăng dần");
    }
    public static void sortBookByInterestDESC(){
        for (int i = 0; i < index - 1; i++) {
            for (int j = i + 1; j < index; j++) {
                if (arrBooks[i].getInterest() < arrBooks[j].getInterest()) {
                    Book bookTemp = arrBooks[i];
                    arrBooks[i] = arrBooks[j];
                    arrBooks[j] = bookTemp;
                }
            }
        }
        System.out.println("Đã sắp xếp sách theo lợi nhuận giảm dần dần");
    }
    public static void searchBookByName(Scanner scanner){
        System.out.println("Nhập vào tên sách cần tìm: ");
        String bookNameSearch = scanner.nextLine();
        boolean isExist = false;
        System.out.println("Thông tin các sách tìm kiếm: ");
        for (int i = 0; i < index; i++) {
            if (arrBooks[i].getBookName().toLowerCase().contains(bookNameSearch.toLowerCase())) {
                arrBooks[i].displayData();
                isExist = true;
            }
        }
        if (!isExist) {
            System.out.println("Không tìm được sách theo tên đã nhập");
        }
    }
    public static void getNumberBookByYear(Scanner scanner){
        int[] yearsBook = new int []
        int yearStatistic = Integer.parseInt(scanner.nextLine());
    }
}
