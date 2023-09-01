package ra.impl;

import ra.entity.Book;
import ra.entity.Category;

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
        do {
            System.out.println("========== QUẢN LÝ THƯ VIỆN ==========");
            System.out.println("1. Quản lý Thể loại");
            System.out.println("2. Quản lý Sách");
            System.out.println("3. Thoát");
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
                        System.err.println("Vui lòng chọn từ 1-3");
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Vui lòng chọn số");
            } catch (Exception ex) {
                System.err.println("Có lỗi không xác định, vui lòng liên hệ hệ thống");
            }
        } while (true);
    }

    public static void catalogMenu() {
        boolean isExit = true;
        do {
            System.out.println("========== QUẢN LÝ THỂ LOẠI ==========");
            System.out.println("1. Thêm mới thể loại");
            System.out.println("2. Hiển thị danh sách theo tên A – Z");
            System.out.println("3. Thống kê thể loại và số sách có trong mỗi thể loại");
            System.out.println("4. Cập nhật thể loại");
            System.out.println("5. Xóa thể loại");
            System.out.println("6. Quay lại (QUẢN LÝ THƯ VIỆN)");
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
                        isExit = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-6");
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Vui lòng chọn số");
            } catch (Exception ex) {
                System.err.println("Có lỗi không xác định, vui lòng liên hệ hệ thống");
            }
        } while (isExit);
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
            categoryList.remove(indexUpdate);
            // Nhập dữ liệu cho danh mục cần cập nhập
            Category catalogUpdate = new Category();
            catalogUpdate.input(scanner, categoryList, bookList);
            //Add dữ liệu vào mảng
            categoryList.add(indexUpdate, catalogUpdate);
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
                System.out.println("Đã xóa xong thể loại");
            } else {
                System.err.println("Thể loại đã có sách, không thể xóa");
            }
        } else {
            System.err.println("Mã thể loại không tồn tại");
        }
    }

    public static boolean validateCatalogId(String catalogId) {
        // Kiểm tra không được để trống
        if (catalogId != null || catalogId.trim().length() != 0) {
            int intCatalogId = Integer.parseInt(catalogId);
            //Kiểm tra kiểu dữ liệu
            try {
                if (intCatalogId > 0) {
                    return true;
                } else {
                    System.err.println("Mã thể loại sách có giá trị lớn hơn 0, vui lòng nhập lại");
                    return false;
                }
            } catch (NumberFormatException ex1) {
                System.out.println("Mã thể loại sách không phải định dạng số nguyên, vui lòng nhập lại");
                return false;
            } catch (Exception ex) {
                System.err.println("Lỗi không xác định, vui lòng liên hệ hệ thống");
                return false;
            }
        } else {
            System.err.println("Mã thể loại sách bị bỏ trống, vui lòng nhập lại");
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
            System.out.println("========== QUẢN LÝ SÁCH ==========");
            System.out.println("1. Thêm mới sách");
            System.out.println("2. Cập nhật thông tin sách");
            System.out.println("3. Xóa sách");
            System.out.println("4. Tìm kiếm sách");
            System.out.println("5. Hiển thị danh sách sách theo nhóm thể loại");
            System.out.println("6. Quay lại (QUẢN LÝ THƯ VIỆN)");
            System.out.print("Sự lựa chọn của bạn là: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        Library.inputBook();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        Library.displayListBookByCategory();
                        break;
                    case 6:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-6");
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Vui lòng chọn số");
            } catch (Exception ex) {
                System.err.println("Có lỗi không xác định, vui lòng liên hệ hệ thống");
            }
        } while (isExit);
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
        System.out.println("Chọn thể loại: ");
        int choiceCatalog = Integer.parseInt(scanner.nextLine());
        newBook.setCatalogId(categoryList.get(choiceCatalog-1).getCatalogId());
        //B4: add sản phẩm vào productList
        bookList.add(newBook);
    }

    public static void displayListBookByCategory() {
        categoryList.stream().forEach(category -> {
            //In thông tin thể loại
            System.out.println(category.getCatalogName());
            //In thông tin sách thuộc thể loại đang xét
            bookList.stream().filter(book -> book.getCatalogId() == category.getCatalogId()).forEach(book -> System.out.printf("\t%s\n", book.getBookTitle()));
        });
    }
    public static boolean validateInputBookId()
}
