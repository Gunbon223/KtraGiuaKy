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
                    loginState = login();
                    if (!loginState) {
                        System.out.println("Đăng nhập thất bại!");
                        run();
                    }
                    else {
                        loginMenu();
                    }
                    break;
                case 2:
                    register();
                    loginState=false;
                    run();
                    break;
                default:
                    System.out.println("Nhập sai lựa chọn vui lòng nhập lại");
                    run();
                    break;
            }
    }

    public void loginMenu() {
        System.out.println("Menu đăng nhập: ");
        System.out.println("1. Đổi username");
        System.out.println("2. Đổi password");
        System.out.println("3. Đổi email");
        System.out.println("4. Đăng xuất");
        System.out.println("0. Thoát");
        System.out.println("Nhập lựa chọn");
        int choice = Integer.parseInt(scan.nextLine());
        switch (choice)
        {
            case 1:
                changeUserName();
                changeUserInArray();
                loginMenu();
            case 2:
                String newPass = changePassword();
                loginUser.setPassword(newPass);
                changeUserInArray();
                loginMenu();
            case 3:
                changeEmail();
                changeUserInArray();
                loginMenu();
            case 4:
                loginState =false;
                loginUser =null;
                run();
                return;
            case 0:
                System.exit(0);
            default:
                System.out.println("Nhập sai lựa chọn vui lòng nhập lại");
                loginMenu();
        }
    }
    public void changeUserInArray() {
        userArrayList.remove(loginUser);
        userArrayList.add(loginUser);
    }

    public boolean login() {
        int choice;
        System.out.print("Nhập username: ");
        String username = scan.nextLine();
        if(!findUsername(username)) {
            System.out.println("Tên đăng nhập không tồn tại, kiểm tra lại username!");
            return false;
        }
        System.out.print("Nhập password: ");
        String password = scan.nextLine();
        if(!findPassword(password)) {
            System.out.println("Sai mật khẩu");
            System.out.println("1. Đăng nhập lại");
            System.out.println("2. Quên mật khẩu");
            System.out.println("Nhập lưa chọn: ");
            choice = Integer.parseInt(scan.nextLine());
            switch (choice) {
                case 1:
                    return login();
                case 2:
                    System.out.println("Nhập email: ");
                    String inputEmail = scan.nextLine();
                    if (findEmail(inputEmail)) {
                        for (User i : userArrayList) {
                            if (i.getUsername().equals(username)) {
                                i.setPassword(changePassword());
                            }
                        }
                        return false;
                    }
                    else System.out.println("Không tìm thấy tài khoản");
                    run();
                    return false;
                default:
                    System.out.println("Nhập sai lựa chọn vui lòng nhập lại");
                    return login();
            }
        }
        for (User i :userArrayList) {
            if(i.getUsername().equals(username))
            loginUser = i;
        }
        return true;
    }



    public void register() {
        System.out.print("Nhập tên tài khoản mới: ");
        String username = scan.nextLine();
        if (findUsername(username)) {
            System.out.println("Tên đăng nhập đã tồn tại!");
            register();
            return;
        }
        System.out.print("Nhập mật khẩu mới: ");
        String password = scan.nextLine();
        if (!passwordValidate(password) ) {
            register();
            return;
        }
        System.out.print("Nhập email: ");
        String email = scan.nextLine();
        if (!emailValidate(email)||findEmail(email)) {
            System.out.println("Email không hợp lệ hoăc đã tồn tại");
            register();
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
        return true;
    }

    private boolean findEmail(String email) {
        for (User i : userArrayList) {
            if (i.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private boolean findUsername(String username) {
        for (User i : userArrayList) {
            if (i.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private boolean findPassword(String password) {
        for (User i : userArrayList) {
            if (i.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private void changeUserName() {
        System.out.println("Nhập tên tài khoản mới: ");
        String newUsername = scan.nextLine();
        if(findUsername(newUsername)) {
            System.out.println("Tên tài khoản đã tồn tại!");
            changeUserName();
            return;
        }
        loginUser.setUsername(newUsername);
        System.out.println("Đổi username thành công");
    }

    private String changePassword() {
        System.out.println("Nhập password mới: ");
        String newPass = scan.nextLine();
        if(!passwordValidate(newPass)) {
            System.out.println("Nhập lại:");
            changePassword();
            return newPass;
        }
        System.out.println("Đổi password thành công");
        return newPass;
    }

    private void changeEmail() {
        System.out.println("Nhập email mới: ");
        String newEmail = scan.nextLine();
        if(!emailValidate(newEmail)||findEmail(newEmail)) {
            System.out.println("Email sai định dạng hoặc đã tồn tại");
            System.out.println("Nhập lại:");
            changeEmail();
            return;
        }
        loginUser.setEmail(newEmail);
        System.out.println("Đổi Email thành công");
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


    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

}
