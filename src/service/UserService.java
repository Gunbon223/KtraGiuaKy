package service;

import entites.User;

import java.util.ArrayList;
import java.util.Scanner;

public class UserService {
    private ArrayList<User> userArrayList;
    private Scanner scan = new Scanner(System.in);
    private User loginUser;
    private boolean loginState;

    public UserService() {
        userArrayList = new ArrayList<>();
        loginState = false;
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
                break;
            case 2:
                reigster();
                break;
            default:
                System.out.println("Nhập sai lựa chọn vui lòng nhập lại");
        }

    }

    public void Login() {

    }

//    private boolean checkLogin(String username, String password, ArrayList<User> userArrayList) {
//        boolean loginState = false;
//        for (User i : userArrayList) {
//            if () {
//                return loginState = true;
//            }
//            if (!i.getUsername().equals(username)) {
//                System.out.println("Nhập sai username vui lòng nhập lại!");
//                return loginState = false;
//            }
//            if (!i.getPassword().equals(password)) {
//                System.out.println("Nhập sai password ");
//                return loginState = false;
//            }
//        }
//        return loginState;
//    } // ktra usẻname,pass,email




    public void reigster() {
        System.out.print("Nhập tên tài khoản: ");
        String username = scan.nextLine();
        if (!findUsername(username)) {
            reigster();
            return;
        }

        System.out.print("Nhập mật khẩu: ");
        String password = scan.nextLine();
        if (!passwordValidate(password)) {
            reigster();
            return;
        }

        System.out.print("Nhập email: ");
        String email = scan.nextLine();
        if (!emailValidate(email)) {
            reigster();
            return;
        }

        User newUser = new User(username,password,email);
        userArrayList.add(newUser);
        System.out.println("Đăng ký thành công!");

    }

    private boolean passwordValidate(String password) {
        if (password.length() < 7 || password.length() > 15) {
            System.out.println("Mật khẩu phải từ 7-15 ký tự ");
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            System.out.println("Mật khẩu phải có 1 ký tự in hoa ");
            return false;
        }
        if (!password.matches(".*[.,-_;@].*")) {
            System.out.println("Mật khẩu phải có 1 ký tự đặc biệt ");
            return false;
        }
        return true;
    }

    private boolean emailValidate(String email) {
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

    private boolean findUsername(String username) {
        for (User i : userArrayList) {
            if (i.getUsername().equals(username)) {
                System.out.println("Username đã tồn tại");
                return false;
            }
        }
        return true;
    }

    private void changeUserName() {
        System.out.println("Nhập user name mới: ");
        String newUsername = scan.nextLine();
        if(!findUsername(newUsername)) {
            System.out.println("Nhập lại:");
            changeUserName();
            return;
        }
        loginUser.setUsername(newUsername);
        System.out.println("Đổi username thành công");
    }

    private void changePassword() {
        System.out.println("Nhập password mới: ");
        String newPass = scan.nextLine();
        if(!passwordValidate(newPass)) {
            System.out.println("Nhập lại:");
            changePassword();
            return;
        }
        loginUser.setPassword(newPass);
        System.out.println("Đổi password thành công");
    }

    private void changeEmail() {
        System.out.println("Nhập email mới: ");
        String newEmail = scan.nextLine();
        if(!emailValidate(newEmail)) {
            System.out.println("Nhập lại:");
            changePassword();
            return;
        }
        loginUser.setEmail(newEmail);
        System.out.println("Đổi password thành công");
    }

    private void forgetPassword() {
        System.out.println("Nhập email: ");
        String inputEmail = scan.nextLine();
        if(!emailValidate(inputEmail)) {
            forgetPassword();
            return;
        }
        changePassword();
    }

}
