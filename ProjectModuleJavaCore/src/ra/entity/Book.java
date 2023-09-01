package ra.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Book implements IEntity<Category, Book> {
    private String bookId;
    private String bookTitle;
    private String author;
    private String publisher;
    private int publishedYear;
    private String description;
    private int catalogId;

    public Book() {
    }

    public Book(String bookId, String bookTitle, String author, String publisher, int publishedYear, String description, int catalogId) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.publishedYear = publishedYear;
        this.description = description;
        this.catalogId = catalogId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    @Override
    public void input(Scanner scanner, List<Category> categoryList, List<Book> bookList) {
        this.bookId = validateBookId(scanner, bookList);
        this.bookTitle = validateBookTitle(scanner, bookList);
        this.author = validateAuthor(scanner);
        this.publisher = validatePublisher(scanner);
        this.publishedYear = validatePublishedYear(scanner);
    }

    public String validateBookId(Scanner scanner, List<Book> bookList) {
        System.out.println("Nhập vào mã sách");
        do {
            String bookId = scanner.nextLine();
            // Kiểm tra không được để trống
            if (bookId != null || bookId.trim().length() != 0) {
                boolean isExistBookId = false;
                for (Book book : bookList
                ) {
                    if (book.bookId.equals(bookId)) {
                        isExistBookId = true;
                        break;
                    }
                }
                if (!isExistBookId) {
                    if (bookId.length() == 4) {
                        if (bookId.startsWith("B")) {
                            return bookId;
                        } else {
                            System.err.println("Vui lòng nhập mã sách với ký tự bắt đầu là S");
                        }
                    } else {
                        System.err.println("Vui lòng nhập mã sách gồm 4 ký tự");
                    }
                } else {
                    System.err.println("Mã sách đã trùng vui lòng nhập lại");
                }
            } else {
                System.err.println("Mã sách bị bỏ trống, vui lòng nhập lại");
            }
        } while (true);
    }

    public static String validateBookTitle(Scanner scanner, List<Book> bookList) {
        System.out.println("Nhập vào tiêu đề sách: ");
        do {
            String bookTitle = scanner.nextLine();
            // Kiểm tra không được để trống
            if (bookTitle != null || bookTitle.trim().length() != 0) {
                boolean isExistBookTitle = false;
                for (Book book : bookList
                ) {
                    if (book.bookTitle.equals(bookTitle)) {
                        isExistBookTitle = true;
                        break;
                    }
                }
                if (!isExistBookTitle) {
                    if (bookTitle.length() >= 6 && bookTitle.length() <= 50) {
                        return bookTitle;
                    } else {
                        System.err.println("Tên sách có từ 6-50 ký tự, vui lòng nhập lại");
                    }
                } else {
                    System.err.println("Tiêu đề sách đã trùng vui lòng nhập lại");
                }
            } else {
                System.err.println("Tiêu đề sách bị bỏ trống, vui lòng nhập lại");
            }
        } while (true);
    }

    public static String validateAuthor(Scanner scanner) {
        System.out.println("Nhập vào tên tác giả: ");
        String author = scanner.nextLine();
        // Kiểm tra không được để trống
        do {
            if (author != null || author.trim().length() != 0) {
                return author;
            } else {
                System.err.println("Tên tác giả bị bỏ trống, vui lòng nhập lại");
            }
        } while (true);
    }

    public static String validatePublisher(Scanner scanner) {
        System.out.println("Nhập vào nhà xuất bản: ");
        String publisher = scanner.nextLine();
        // Kiểm tra không được để trống
        do {
            if (publisher != null || publisher.trim().length() != 0) {
                return publisher;
            } else {
                System.err.println("Nhà xuất bản bị bỏ trống, vui lòng nhập lại");
            }
        } while (true);
    }

    public static int validatePublishedYear(Scanner scanner) {
        System.out.println("Nhập vào năm xuất bản: ");
        do {
            try {
                int publishedYear = Integer.parseInt(scanner.nextLine());
                //Lấy năm hiện tại
                Date now = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(now);
                int yearNow = calendar.get(Calendar.YEAR);
                if (publishedYear >= 1970 && publishedYear <= yearNow) {
                    return publishedYear;
                } else {
                    System.err.println("Năm xuất bản phải từ 1970 đến hiện tại, vui lòng nhập lại");
                }
            } catch (NumberFormatException ex1) {
                System.err.println("Vui lòng nhập năm xuất bản là số nguyên");
            } catch (Exception ex) {
                System.err.println("Lỗi không xác định, vui lòng liên hệ hệ thống");
            }
        } while (true);
    }

    public static String validateDescription(Scanner scanner) {
        System.out.println("Hãy mô tả sách: ");
        String description = scanner.nextLine();
        // Kiểm tra không được để trống
        do {
            if (description != null || description.trim().length() != 0) {
                return description;
            } else {
                System.err.println("Mô tả sách bị bỏ trống, vui lòng nhập lại");
            }
        } while (true);
    }

    @Override
    public void output(List<Category>categoryList) {
        String catalogName = categoryList.stream().filter(category -> category.getCatalogId()==this.catalogId).collect(Collectors.toList()).get(0).getCatalogName();
        System.out.printf("Mã sách: %s - Tiêu đề sách: %s - Tác giả: %s", this.bookId, this.bookTitle, this.author);
        System.out.printf("NXB: %s - Năm xuất bản: %d - Mô tả sách: %s - Tên thể loại sách: %d", this.publisher, this.publishedYear, this.description, catalogName);
    }
}
