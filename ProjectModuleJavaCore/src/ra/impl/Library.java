package ra.impl;

import ra.entity.Book;
import ra.entity.Category;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Library {
    static List<Category> categoryList = new ArrayList<>();
    static List<Book> bookList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        readDataFromCategoryFile();
        readDataFromBookFile();
        do {
            System.out.format("+----------------------------------------+%n");
            System.out.printf("|%-40s|","=========== QUẢN LÝ THƯ VIỆN ===========");
            System.out.printf("\n");
            System.out.format("+----------------------------------------+%n");
            System.out.printf("|%-40s|","1. Quản lý Thể loại");
            System.out.printf("\n");
            System.out.format("+----------------------------------------+%n");
            System.out.printf("|%-40s|","2. Quản lý Sách");
            System.out.printf("\n");
            System.out.format("+----------------------------------------+%n");
            System.out.printf("|%-40s|","3. Thoát");
            System.out.printf("\n");
            System.out.format("+----------------------------------------+%n");
            System.out.print("Sự lựa chọn của bạn là: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        Library.catalogMenu();
                        break;
                    case 2:
                        Library.bookMenu();
                        break;
                    case 3:
                        System.exit(0);
                    default:
                        System.out.println("Vui lòng chọn từ 1-3");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Vui lòng chọn số");
            } catch (Exception ex) {
                System.out.println("Có lỗi không xác định, vui lòng liên hệ hệ thống");
            }
        } while (true);
    }

    public static void catalogMenu() {
        boolean isExit = true;
        do {
            System.out.format("+-------------------------------------------------------+%n");
            System.out.printf("|%-55s|","=================== QUẢN LÝ THỂ LOẠI ==================");
            System.out.printf("\n");
            System.out.format("+-------------------------------------------------------+%n");
            System.out.printf("|%-55s|","1. Thêm mới thể loại");
            System.out.printf("\n");
            System.out.format("+-------------------------------------------------------+%n");
            System.out.printf("|%-55s|","2. Hiển thị danh sách theo tên A – Z");
            System.out.printf("\n");
            System.out.format("+-------------------------------------------------------+%n");
            System.out.printf("|%-55s|","3. Thống kê thể loại và số sách có trong mỗi thể loại");
            System.out.printf("\n");
            System.out.format("+-------------------------------------------------------+%n");
            System.out.printf("|%-55s|","4. Cập nhật thể loại");
            System.out.printf("\n");
            System.out.format("+-------------------------------------------------------+%n");
            System.out.printf("|%-55s|","5. Xóa thể loại");
            System.out.printf("\n");
            System.out.format("+-------------------------------------------------------+%n");
            System.out.printf("|%-55s|","6. Quay lại (QUẢN LÝ THƯ VIỆN)");
            System.out.printf("\n");
            System.out.format("+-------------------------------------------------------+%n");
            System.out.print("Sự lựa chọn của bạn là: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        Library.inputCategory();
                        break;
                    case 2:
                        Library.displayCategoryListASC();
                        break;
                    case 3:
                        Library.statisticNumberOfBookByCategory();
                        break;
                    case 4:
                        Library.updateCatalog();
                        break;
                    case 5:
                        Library.deleteCatalog();
                        break;
                    case 6:
                        writeDataToCategoryFile();
                        isExit = false;
                        break;
                    default:
                        System.out.println("Vui lòng chọn từ 1-6");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Vui lòng chọn số");
            } catch (Exception ex) {
                System.out.println("Có lỗi không xác định, vui lòng liên hệ hệ thống");
            }
        } while (isExit);
    }

    public static void readDataFromCategoryFile() {
        File file = new File("categories.txt");
        if (!file.exists()) {
            return;
        }
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            categoryList = (List<Category>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Không tồn tại file");
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file");
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình đọc dữ liệu từ file");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.err.println("Có lỗi khi đóng stream");
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    System.err.println("Có lỗi khi đóng stream");
                }
            }
        }
    }

    public static void writeDataToCategoryFile() {
        File file = new File("categories.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(categoryList);
            oos.flush();
        } catch (FileNotFoundException e) {
            System.err.println("Không tồn tại file");
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi dữ liệu ra file");
        } catch (Exception e) {
            System.err.println("Xảy ra lỗi trong quá trình ghi dữ liệu ra file");
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    System.err.println("Có lỗi khi đóng stream");
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    System.err.println("Có lỗi khi đóng stream");
                }
            }
        }
    }

    public static void inputCategory() {
        System.out.println("Nhập vào số thể loại sách cần nhập dữ liệu");
        do {
            try {
                int number = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < number; i++) {
                    System.out.println("Nhập vào thông tin thể loại sách thứ " + (i + 1) + ": ");
                    Category categoryNew = new Category();
                    categoryNew.input(scanner, categoryList, bookList);
                    categoryList.add(categoryNew);
                }
                break;
            } catch (NumberFormatException nfe) {
                System.err.println("Vui lòng nhập vào một số nguyên dương");
            } catch (Exception ex) {
                System.err.println("Có lỗi không xác định, vui lòng liên hệ hệ thống");
            }
        } while (true);
    }

    public static void displayCategoryListASC() {
        categoryList.stream().sorted(Comparator.comparing(Category::getCatalogName)).forEach(category -> category.output(categoryList));
    }

    public static void statisticNumberOfBookByCategory() {
        //Khai báo danh sách chứa số lượng thống kê của sách theo thể loại
        List<Integer> listCountBook = new ArrayList<>();
        //Duyệt từng thể loại sách
        categoryList.stream().forEach(category -> {
            //Tính số sách trên từng thể loại
            int cnt = (int) bookList.stream().filter(book -> category.getCatalogId() == book.getCatalogId()).count();
            listCountBook.add(cnt);
        });
        //Hiển thị thống kê
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.printf("Thể loại sách: %s - Số lượng: %d\n", categoryList.get(i).getCatalogName(), listCountBook.get(i));
        }
    }

    public static void updateCatalog() {
        //1. Nhập vào mã thể loại cần cập nhập
        System.out.println("Nhập vào mã thể loại cần cập nhập");
        String updateCatalogId;
        boolean isExit = true;
        do {
            updateCatalogId = scanner.nextLine();
            if (validateCatalogId(updateCatalogId)) {
                isExit = false;
            }
        } while (isExit);
        //2. Chuyển qua kiểu dữ liệu integer
        int intUpdateCatalogId = Integer.parseInt(updateCatalogId);
        //3. Tìm index thể loại cần cập nhập trong danh sách
        int indexUpdate = getIndexCatalogById(intUpdateCatalogId);
        if (indexUpdate >= 0) {
//            categoryList.remove(indexUpdate);
            // Nhập dữ liệu cho danh mục cần cập nhập
//            Category catalogUpdate = new Category();
//            catalogUpdate.input(scanner, categoryList, bookList);
            //Add dữ liệu vào mảng
//            categoryList.add(indexUpdate, catalogUpdate);
            categoryList.get(indexUpdate).setCatalogName(Category.validateCatalogName(scanner,categoryList));
            categoryList.get(indexUpdate).setCatalogStatus(Category.validateCatalogStatus(scanner));
            System.out.println("Đã cập nhập thành công");
        } else {
            System.err.println("Mã thể loại cần cập nhập không tồn tại");
        }
    }

    public static void deleteCatalog() {
        //Nhập vào mã thể loại cần xóa
        System.out.println("Nhập vào mã thể loại cần xóa");
        String deleteCatalogId;
        boolean isExit = true;
        do {
            deleteCatalogId = scanner.nextLine();
            if (validateCatalogId(deleteCatalogId)) {
                isExit = false;
            }
        } while (isExit);
        //2. Chuyển qua kiểu dữ liệu integer
        int intDeleteCatalogId = Integer.parseInt(deleteCatalogId);
        //3. Tìm index thể loại cần xóa trong danh sách
        int indexDelete = getIndexCatalogById(intDeleteCatalogId);
        if (indexDelete >= 0) {
            //Kiểm tra trong thể loại có sách chưa
            boolean isExist = false;
            for (Book book : bookList
            ) {
                if (book.getCatalogId() == intDeleteCatalogId) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                categoryList.remove(indexDelete);
                System.out.println("Đã xóa thành công");
            } else {
                System.err.println("Thể loại đã có sách, không thể xóa");
            }
        } else {
            System.err.println("Mã thể loại không tồn tại");
        }
    }

    public static boolean validateCatalogId(String catalogId) {
        // Kiểm tra không được để trống
        if (catalogId != null && catalogId.trim().length() != 0) {
            //Kiểm tra kiểu dữ liệu
            try {
                int intCatalogId = Integer.parseInt(catalogId);
                if (intCatalogId > 0) {
                    return true;
                } else {
                    System.err.println("Mã thể loại sách có giá trị lớn hơn 0, vui lòng nhập lại");
                    return false;
                }
            } catch (NumberFormatException ex1) {
                System.err.println("Mã thể loại sách không phải định dạng số nguyên, vui lòng nhập lại");
                return false;
            } catch (Exception ex) {
                System.err.println("Lỗi không xác định, vui lòng liên hệ hệ thống");
                return false;
            }
        } else {
            System.err.println("Mã thể loại sách nhập bị bỏ trống, vui lòng nhập lại");
            return false;
        }
    }

    public static int getIndexCatalogById(int catalogId) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getCatalogId() == catalogId) {
                return i;
            }
        }
        return -1;
    }

    public static void bookMenu() {
        boolean isExit = true;
        do {
            System.out.format("+-------------------------------------------------------+%n");
            System.out.printf("|%-55s|","==================== QUẢN LÝ SÁCH =====================");
            System.out.printf("\n");
            System.out.format("+-------------------------------------------------------+%n");
            System.out.printf("|%-55s|","1. Thêm mới sách");
            System.out.printf("\n");
            System.out.format("+-------------------------------------------------------+%n");
            System.out.printf("|%-55s|","2. Cập nhật thông tin sách");
            System.out.printf("\n");
            System.out.format("+-------------------------------------------------------+%n");
            System.out.printf("|%-55s|","3. Xóa sách");
            System.out.printf("\n");
            System.out.format("+-------------------------------------------------------+%n");
            System.out.printf("|%-55s|","4. Tìm kiếm sách");
            System.out.printf("\n");
            System.out.format("+-------------------------------------------------------+%n");
            System.out.printf("|%-55s|","5. Hiển thị danh sách sách theo nhóm thể loại");
            System.out.printf("\n");
            System.out.format("+-------------------------------------------------------+%n");
            System.out.printf("|%-55s|","6. Quay lại (QUẢN LÝ THƯ VIỆN)");
            System.out.printf("\n");
            System.out.format("+-------------------------------------------------------+%n");
            System.out.print("Sự lựa chọn của bạn là: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        Library.inputBook();
                        break;
                    case 2:
                        Library.updateBookByBookId();
                        break;
                    case 3:
                        Library.deleteBookByBookId();
                        break;
                    case 4:
                        Library.searchBookByTitleByAuthorByPublisher();
                        break;
                    case 5:
                        Library.displayListBookByCategory();
                        break;
                    case 6:
                        writeDataToBookFile();
                        isExit = false;
                        break;
                    default:
                        System.out.println("Vui lòng chọn từ 1-6");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Vui lòng chọn số");
            } catch (Exception ex) {
                System.out.println("Có lỗi không xác định, vui lòng liên hệ hệ thống");
            }
        } while (isExit);
    }

    public static void readDataFromBookFile() {
        File file = new File("books.txt");
        if (!file.exists()) {
            return;
        }
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            bookList = (List<Book>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Không tồn tại file");
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file");
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình đọc dữ liệu từ file");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.err.println("Có lỗi khi đóng stream");
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    System.err.println("Có lỗi khi đóng stream");
                }
            }
        }
    }

    public static void writeDataToBookFile() {
        File file = new File("books.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(bookList);
            oos.flush();
        } catch (FileNotFoundException e) {
            System.err.println("Không tồn tại file");
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi dữ liệu ra file");
        } catch (Exception e) {
            System.err.println("Xảy ra lỗi trong quá trình ghi dữ liệu ra file");
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    System.err.println("Có lỗi khi đóng stream");
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    System.err.println("Có lỗi khi đóng stream");
                }
            }
        }
    }

    public static void inputBook() {
        //B1: Khỏi tạo đối tượng book thêm mới:
        Book newBook = new Book();
        //B2: Nhập thông tin newBook
        newBook.input(scanner, categoryList, bookList);
        //B3: Cho chọn sách thuộc thể loại nào - Hiển thị các thể loại theo Menu để người dùng chọn
        System.out.println("========== CATALOG ==========");
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, categoryList.get(i).getCatalogName());
        }
        int choiceCatalog;
        System.out.println("Chọn thể loại: ");
        do {
            try {
                choiceCatalog = Integer.parseInt(scanner.nextLine());
                if (choiceCatalog > 0 && choiceCatalog <= categoryList.size()) {
                    break;
                } else {
                    System.err.println("Thể loại phải là số nguyên từ 1 đến " + categoryList.size());
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Dữ liệu phải là số, vui lòng nhập lại");
            } catch (Exception ex) {
                System.err.println("Có lỗi không xác định, vui lòng liên hệ hệ thống");
            }
        } while (true);
        newBook.setCatalogId(categoryList.get(choiceCatalog - 1).getCatalogId());
        //B4: add sản phẩm vào productList
        bookList.add(newBook);
    }

    public static void updateBookByBookId() {
        System.out.println("Nhập vào mã sách cần cập nhập");
        String updateBookId;
        boolean isExit = true;
        do {
            updateBookId = scanner.nextLine();
            if (validateInputBookId(updateBookId)) {
                isExit = false;
            }
        } while (isExit);
        //Tìm index sách cần cập nhập trong danh sách
        int indexUpdate = getIndexBookByBookId(updateBookId);
        if (indexUpdate >= 0) {
            bookList.get(indexUpdate).setBookTitle(Book.validateBookTitle(scanner, bookList));
            bookList.get(indexUpdate).setAuthor(Book.validateAuthor(scanner));
            bookList.get(indexUpdate).setPublisher(Book.validatePublisher(scanner));
            bookList.get(indexUpdate).setPublishedYear(Book.validatePublishedYear(scanner));
            bookList.get(indexUpdate).setDescription(Book.validateDescription(scanner));
            System.out.println("Đã cập nhập thành công");
        } else {
            System.err.println("Mã sách cần cập nhập không tồn tại");
        }
    }

    public static void deleteBookByBookId() {
        System.out.println("Nhập vào mã sách cần xóa");
        String deleteBooKId;
        boolean isExit = true;
        do {
            deleteBooKId = scanner.nextLine();
            if (validateInputBookId(deleteBooKId)) {
                isExit = false;
            }
        } while (isExit);
        // Tìm index của sách cần xóa trong danh sách
        int indexDelete = getIndexBookByBookId(deleteBooKId);
        if (indexDelete >= 0) {
            // Thực hiện xóa sách
            bookList.remove(indexDelete);
            System.out.println("Đã xóa thành công");
        } else {
            System.err.println("Mã sách cần xóa không tồn tại");
        }
    }

    public static void searchBookByTitleByAuthorByPublisher() {
        System.out.println("Nhập vào từ khóa tìm kiếm(tiêu đề, tên tác giả, NXB)");
        String wordSearch = scanner.nextLine();
        boolean isExist = false;
        System.out.println("Thông tin của sách đang tìm kiếm");
        for (Book book : bookList
        ) {
            if (book.getBookTitle().toLowerCase().contains(wordSearch.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(wordSearch.toLowerCase()) ||
                    book.getPublisher().toLowerCase().contains(wordSearch.toLowerCase())) {
                book.output(categoryList);
                isExist = true;
            }
        }
        if (!isExist) {
            System.out.println("Thông tin sách đang được tìm không tồn tại");
        }
    }

    public static void displayListBookByCategory() {
        categoryList.stream().forEach(category -> {
            //In thông tin thể loại
            System.out.println(category.getCatalogName());
            //In thông tin sách thuộc thể loại đang xét
            bookList.stream().filter(book -> book.getCatalogId() == category.getCatalogId()).forEach(book -> System.out.printf("\t%s\n", book.getBookTitle()));
        });
    }

    public static boolean validateInputBookId(String bookId) {
        // Kiểm tra không được để trống
        if (bookId != null && bookId.trim().length() != 0) {
            if (bookId.length() == 4) {
                if (bookId.startsWith("B")) {
                    return true;
                } else {
                    System.err.println("Vui lòng nhập mã sách cần cập nhập với ký tự bắt đầu là B");
                    return false;
                }
            } else {
                System.err.println("Vui lòng nhập mã sách cần cập nhập gồm 4 ký tự");
                return false;
            }
        } else {
            System.err.println("Mã sách bị bỏ trống, vui lòng nhập lại");
            return false;
        }
    }

    public static int getIndexBookByBookId(String bookId) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getBookId().equals(bookId)) {
                return i;
            }
        }
        return -1;
    }
}
