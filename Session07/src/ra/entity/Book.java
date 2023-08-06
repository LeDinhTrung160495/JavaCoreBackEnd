package ra.entity;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Book {
    private String bookId;
    private String bookName;
    private float importPrice;
    private float exportPrice;
    private String author;
    private float interest;
    private int year;

    public Book() {
    }

    public Book(String bookId, String bookName, float importPrice, float exportPrice, String author, float interest, int year) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.author = author;
        this.interest = interest;
        this.year = year;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void inputData(Scanner scanner, Book[] arrBooks, int numberBook) {
        System.out.println("Nhập vào mã sách: ");
        boolean checkBookId = true;
        do {
            this.bookId = scanner.nextLine();
            boolean isBookExist = false;
            for (int i = 0; i < numberBook; i++) {
                if (arrBooks[i].bookId.equals(this.bookId)) {
                    isBookExist = true;
                    break;
                }
            }
            if (!isBookExist) {
                break;
            } else {
                System.err.println("Mã sách đã tồn tại, vui lòng nhập lại");
            }

        } while (checkBookId);
        System.out.println("Nhập vào tên sách: ");
        boolean checkBookName = true;
        do {
            this.bookName = scanner.nextLine();
            boolean isExist = false;
            for (int i = 0; i < numberBook; i++) {
                if (arrBooks[i].bookName.equals(this.bookName)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                String bookNameRegex = "B[a-zA-Z0-9]{3}";
                if (Pattern.matches(bookNameRegex, this.bookName)) {
                    break;
                } else {
                    System.err.println("Vui lòng nhập tên sách theo đúng định dạng");
                }
            } else {
                System.err.println("Tên sách đã tồn tại, vui lòng nhập lại");
            }
        } while (checkBookName);
        System.out.println("Nhập vào giá nhập của sách");
        boolean checkImportPrice = true;
        do {
            this.importPrice = Float.parseFloat(scanner.nextLine());
            if (this.importPrice > 0) {
                break;
            } else {
                System.err.println("Vui lòng nhập giá nhập của sách có giá trị lớn hơn 0");
            }
        } while (checkImportPrice);
        System.out.println("Nhập vào giá xuất của sách");
        boolean checkExportPrice = true;
        do {
            this.exportPrice = Float.parseFloat(scanner.nextLine());
            if (this.exportPrice >= (this.importPrice * 130 / 100)) {
                break;
            } else {
                System.err.println("Vui lòng nhập giá xuất của sách có giá trị lớn hơn ít nhất 30% so với giá nhập");
            }
        } while (checkExportPrice);
        System.out.println("Nhập vào tên tác giả");
        boolean checkAuthor = true;
        do {
            this.author = scanner.nextLine();
            String authorRegex = "[a-zA-Z]{6,50}";
            if (Pattern.matches(authorRegex, this.author)) {
                break;
            } else {
                System.err.println("Vui lòng nhập tên tác giả, có từ 6-50 ký tự");
            }
        } while (checkAuthor);
        System.out.println("Nhập năm xuất bản");
        boolean checkYear = true;
        do {
            this.year = Integer.parseInt(scanner.nextLine());
            if (this.year >= 2000) {
                break;
            } else {
                System.err.println("Vui lòng nhập năm xuất bản sau năm 2000");
            }
        } while (checkYear);
    }

    public void displayData() {
        System.out.printf("Mã sách: %s - Tên sách: %s - Tên tác giả: %s\n", this.bookId, this.bookName, this.author);
        System.out.printf("Giá nhập: %.1f - Giá xuất: %.1f - Lợi nhuận: %.1f - Năm xuất bản: %d\n", this.importPrice, this.exportPrice, this.interest, this.year);
    }

    public void callInterest() {
        this.interest = this.exportPrice - this.importPrice;
    }
}
