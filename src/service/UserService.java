package service;

import entites.User;

import java.util.ArrayList;
import java.util.Scanner;

public class UserService {
    private ArrayList<User> user;
    private Scanner scan = new Scanner(System.in);

    public UserService() {
        user = new ArrayList<>();
    }

    public void run() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Đăng nhập");
        System.out.println("1. Đăng nhập");
        System.out.println("2. Đăng kí");
        System.out.println("Nhập lựa chọn: ");
        int choice = Integer.parseInt(scan.nextLine());
        switch (choice) {
            case 1:
        }

    }

    public void Login() {

    }

    private boolean checkLogin(String username, String password, ArrayList<User> userArrayList) {
        boolean loginState = false;
        for (User i : userArrayList) {
//            if () {
//                return loginState = true;
//            }
            if (!i.getUsername().equals(username)) {
                System.out.println("Nhập sai username vui lòng nhập lại!");
                return loginState = false;
            }
            if (!i.getPassword().equals(password)) {
                System.out.println("Nhập sai password ");
                return loginState = false;
            }
        }
        return loginState;
    } // ktra usẻname,pass,email




    public void reigster(ArrayList<User> userArrayList) {
        System.out.println("Nhập tên tài khoản: ");
        String username = scan.nextLine();
        if (!findUsername(username, userArrayList)) {
            reigster(userArrayList);
            return;
        }

        System.out.println("Nhập mật khẩu: ");
        String password = scan.nextLine();
        if (!passwordValidate(password)) {
            reigster(userArrayList);
            return;
        }

        System.out.println("Nhập email: ");
        String email = scan.nextLine();
        if (!passwordValidate(email)) {
            reigster(userArrayList);
            return;
        }

        User newUser = new User(username,password,email);
        user.add(newUser);
    }

    private boolean passwordValidate(String password) {
        if (password.length() < 7 || password.length() > 15) {
            System.out.println("Mật khẩu phải từ 7-15 ký tự ");
            return false;
        } else if (!password.matches(".*[A-Z].*")) {
            System.out.println("Mật khẩu phải có 1 ký tự in hoa ");
            return false;
        } else if (!password.matches(".*[.,-_;@].*")) {
            System.out.println("Mật khẩu phải có 1 ký tự đặc biệt ");
            return false;
        }
        return true;
    }

    private boolean emailValidate(String email,ArrayList<User> userArrayList) {
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            System.out.println("Email không hợp lệ");
            return false;
        }
        for (User i : userArrayList) {
            if (i.getEmail().equals(email)) {
                System.out.println("Email đã tồn tại");
                return false;
            }
        }
        return true;
    }

    private boolean findUsername(String username, ArrayList<User> userArrayList) {
        for (User i : userArrayList) {
            if (i.getUsername().equals(username)) {
                System.out.println("Username đã tồn tại");
                return false;
            }
        }
        return true;
    }

    public StrchangeUserName()



}
