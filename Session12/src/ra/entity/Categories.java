package ra.entity;

import ra.IShop;

import java.rmi.ServerError;
import java.util.List;
import java.util.Scanner;

public class Categories implements IShop {
    private int catalogId;
    private String catalogName;
    private boolean status;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName, boolean status) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.status = status;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner scanner, List<Categories> categoriesList, List<Product> productList) {
        System.out.println("Nhập vào mã danh mục sản phẩm");
        boolean checkCatalogId = true;
        do {
            try {
                this.catalogId = Integer.parseInt(scanner.nextLine());
                boolean isExistCatalogId = false;
                for (Categories category : categoriesList
                ) {
                    if (category.catalogId == this.catalogId) {
                        isExistCatalogId = true;
                        break;
                    }
                }
                if (!isExistCatalogId) {
                    if(this.catalogId>0){
                        checkCatalogId=false;
                    }else {
                        System.err.println("Mã danh mục sản phẩm phải lớn hơn 0, vui lòng nhập lại");
                    }
                } else {
                    System.err.println("Mã danh mục sản phẩm đã trùng, vui lòng nhập lại");
                }
            } catch (NumberFormatException ex) {
                System.err.println("Mã danh mục sản phẩm phải là số nguyên, vui lòng nhập lại");
            }
        } while (checkCatalogId);
        System.out.println("Nhập vào tên danh mục sản phẩm");
        boolean checkCatalogName = true;
        do {
            this.catalogName = scanner.nextLine();
            boolean isExistCatalogName = false;
            for (Categories category : categoriesList
            ) {
                if (category.catalogName.equals(this.catalogName)) {
                    isExistCatalogName = true;
                    break;
                }
            }
            if (!isExistCatalogName) {
                break;
            } else {
                System.err.println("Tên danh mục sản phẩm đã trùng vui lòng nhập lại");
            }
        } while (checkCatalogName);
        System.out.println("Nhập vào trạng thái danh mục sản phẩm");
        this.status = Boolean.parseBoolean(scanner.nextLine());
    }

    @Override
    public void displayData() {
        System.out.printf("Mã danh mục: %d - Tên danh mục: %s - Trạng thái danh mục: %b\n", this.catalogId, this.catalogName, this.status);
    }
}

