package ra.run;

import ra.entity.Book;

import java.util.Scanner;

public class BookImp {
    static Book[] arrBooks = new Book[100];
    static int numberBook = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("***********MENU***********");
            System.out.println("1. Nhập thông tin n sách (n nhập từ bàn phím)");
            System.out.println("2. Tính lợi nhuận các sách");
            System.out.println("3. Hiển thị thông tin sách");
            System.out.println("4. Sắp xếp sách theo giá bán tăng dần");
            System.out.println("5. Sắp xếp sách theo lợi nhuận giảm dần");
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
                    BookImp.displayListBook();
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
                    BookImp.getNumberBookByYear();
                    break;
                case 8:
                    BookImp.getNumberBookByAuthor();
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
            arrBooks[numberBook] = new Book();
            arrBooks[numberBook].inputData(scanner, arrBooks, numberBook);
            numberBook++;
        }
    }

    public static void callListInterest() {
        for (int i = 0; i < numberBook; i++) {
            arrBooks[i].callInterest();
        }
        System.out.println("Lợi nhuận của các sách đã được tính");
    }

    public static void displayListBook() {
        System.out.println("THÔNG TIN CÁC SÁCH");
        for (int i = 0; i < numberBook; i++) {
            arrBooks[i].displayData();
        }
    }

    public static void sortBookByExportPriceASC() {
        for (int i = 0; i < numberBook - 1; i++) {
            for (int j = i + 1; j < numberBook; j++) {
                if (arrBooks[i].getExportPrice() > arrBooks[j].getExportPrice()) {
                    Book bookTemp = arrBooks[i];
                    arrBooks[i] = arrBooks[j];
                    arrBooks[j] = bookTemp;
                }
            }
        }
        System.out.println("Đã sắp xếp sách theo giá bán tăng dần");
    }

    public static void sortBookByInterestDESC() {
        for (int i = 0; i < numberBook - 1; i++) {
            for (int j = i + 1; j < numberBook; j++) {
                if (arrBooks[i].getInterest() < arrBooks[j].getInterest()) {
                    Book bookTemp = arrBooks[i];
                    arrBooks[i] = arrBooks[j];
                    arrBooks[j] = bookTemp;
                }
            }
        }
        System.out.println("Đã sắp xếp sách theo lợi nhuận giảm dần ");
    }

    public static void searchBookByName(Scanner scanner) {
        System.out.println("Nhập vào tên sách cần tìm: ");
        String bookNameSearch = scanner.nextLine();
        boolean isExist = false;
        System.out.println("Thông tin các sách tìm kiếm: ");
        for (int i = 0; i < numberBook; i++) {
            if (arrBooks[i].getBookName().toLowerCase().contains(bookNameSearch.toLowerCase())) {
                arrBooks[i].displayData();
                isExist = true;
            }
        }
        if (!isExist) {
            System.out.println("Không tìm được sách theo tên đã nhập");
        }
    }

    public static void getNumberBookByYear() {
        int[] yearBook = new int[numberBook];
        int cnt = 0;
        for (int i = 0; i < numberBook - 1; i++) {
            boolean isExist = false;
            for (int j = i + 1; j < numberBook; j++) {
                if (arrBooks[i].getYear() == arrBooks[j].getYear()) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                yearBook[cnt] = arrBooks[i].getYear();
                cnt++;
            }
        }
        yearBook[cnt] = arrBooks[numberBook - 1].getYear();
        int arrNumberBookByYear[] = new int[cnt + 1];
        for (int i = 0; i <= cnt; i++) {
            int cntYear = 0;
            for (int j = 0; j < numberBook; j++) {
                if (yearBook[i] == arrBooks[j].getYear()) {
                    cntYear++;
                }
            }
            arrNumberBookByYear[i] = cntYear;
        }
        System.out.println("Thống kê số lượng sách theo năm xuất bản:");
        for (int i = 0; i <= cnt; i++) {
            System.out.printf("%20d", yearBook[i]);
        }
        System.out.printf("\n");
        for (int i = 0; i <= cnt; i++) {
            System.out.printf("%20d", arrNumberBookByYear[i]);
        }
        System.out.printf("\n");
    }
    public static void getNumberBookByAuthor() {
        String[] authorBook = new String[numberBook];
        int cntAuthor = 0;
        for (int i = 0; i < numberBook - 1; i++) {
            boolean isExist = false;
            for (int j = i + 1; j < numberBook; j++) {
                if (arrBooks[i].getAuthor() == arrBooks[j].getAuthor()) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                authorBook[cntAuthor] = arrBooks[i].getAuthor();
                cntAuthor++;
            }
        }
        authorBook[cntAuthor] = arrBooks[numberBook - 1].getAuthor();
        int arrNumberBookByYear[] = new int[cntAuthor + 1];
        for (int i = 0; i <= cntAuthor; i++) {
            int cntBookByAuthor = 0;
            for (int j = 0; j < numberBook; j++) {
                if (authorBook[i] == arrBooks[j].getAuthor()) {
                    cntBookByAuthor++;
                }
            }
            arrNumberBookByYear[i] = cntBookByAuthor;
        }
        System.out.println("Thống kê số lượng sách theo tên tác giả:");
        for (int i = 0; i <= cntAuthor; i++) {
            System.out.printf("%20s", authorBook[i]);
        }
        System.out.printf("\n");
        for (int i = 0; i <= cntAuthor; i++) {
            System.out.printf("%20d", arrNumberBookByYear[i]);
        }
        System.out.printf("\n");
    }
}
