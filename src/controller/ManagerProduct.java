package controller;

import file.ReaderAndWriteProduct;
import model.Product;
import sort.SortDown;
import sort.SortUp;
import validate.ValidateProduct;

import java.util.ArrayList;
import java.util.Scanner;

public class ManagerProduct {
    Scanner scanner = new Scanner(System.in);
    ValidateProduct validateProduct = new ValidateProduct();
    ReaderAndWriteProduct readerAndWriteProduct = new ReaderAndWriteProduct();
    ArrayList<Product> products = readerAndWriteProduct.reader();


    public void menu() {

        while (true) {

            System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ SẢN PHẨM ----");
            System.out.println("Chọn chức năng theo số (để tiếp tục)");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Sắp xếp");
            System.out.println("6. Tìm sản phẩm có giá đắt nhất");
            System.out.println("7. Đọc từ file");
            System.out.println("8. Ghi vào file");
            System.out.println("9. Thoát");
            System.out.println("Chọn chức năng");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        show();
                        break;

                    case 2:
                        addProduct(creatProduct());
                        break;

                    case 3:
                        editProduct();
                        break;

                    case 4:
                        delete();
                        break;

                    case 5:
                        sort();
                        break;

                    case 6:
                        findMaxProduct(products);
                        break;

                    case 7:
                        products = readerAndWriteProduct.reader();
                        System.out.println("đọc thành công");
                        break;


                    case 8:
                        readerAndWriteProduct.write(products);
                        System.out.println("Ghi file thành công");
                        break;


                    case 9:
                        System.exit(0);
                        break;

                    default:
                        throw new NumberFormatException();
                }

            } catch (NumberFormatException e) {
                System.err.println("Nhập sai chức năng, vui lòng nhập lại !!!");
            }
        }
    }


    public void show() {
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public Product creatProduct() {
        int id = validateProduct.validateID(products);
        String name = validateProduct.validateString("tên sản phẩm: ");
        double price = validateProduct.validatePrice();
        int amount = validateProduct.validateAmount();
        String describe = validateProduct.validateString("mô tả: ");
        return new Product(id, name, price, amount, describe);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void editProduct() {

        System.out.println("Nhập mã sản phẩm cần sửa");
        int id = Integer.parseInt(scanner.nextLine());
        int index = validateProduct.getIndexId(id, products);

        if (index != -1) {

            products.set(index, creatProduct());

        } else {

            System.err.println("Mã sản phẩm bạn nhập không tồn tại");
        }
    }

    public void delete() {

        System.out.println("Nhập mã sản phẩm bạn muốn xóa");
        int id = Integer.parseInt(scanner.nextLine());
        int index = validateProduct.getIndexId(id, products);

        if (index != -1) {

            products.remove(index);

        } else

            System.err.println("Mã sản phẩm bạn nhập không có");
    }

    public void sort() {
//        while (true) {
        System.out.println("---- Sắp xếp sản phẩm ----");
        System.out.println("1. Sắp xếp theo giá tăng dần");
        System.out.println("2. Sắp xếp theo giá giảm dần");
        System.out.println("3. Thoát");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            while (true) {


                switch (choice) {
                    case 1: //Sắp xếp tăng dần
                        products.sort(new SortUp());
                        System.out.println("Sắp xếp thành công ");
                        break;
                    case 2: // Sắp xếp giảm dần
                        products.sort(new SortDown());
                        System.out.println("Sắp xếp thành công");
                        break;
                    case 3: // thoát về menu
                        System.exit(0);
                        return;

                }
            }

        } catch (NumberFormatException e) {
            System.err.println("Nhập lựa chọn theo chức năng từ 1 đến 3 ");
        }

    }


    public void findMaxProduct(ArrayList<Product> products) {
        double max = products.get(0).getPrice();
        for (int i = 0; i < products.size(); i++) {
            if (max < products.get(i).getPrice()) {
                max = Double.parseDouble(products.get(i).toString());
            }
        }
        System.out.println(max);
    }
}
