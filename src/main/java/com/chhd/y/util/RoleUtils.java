package com.chhd.y.util;

import com.chhd.y.pojo.User;

public class RoleUtils {

    public static int checkPlus(User user) {
        int plus;
        if (user.getRole() == 0 || user.getRole() == 10) {
            plus = 1;
        } else {
            plus = 0;
        }
        return plus;
    }

    public static int checkAdmin(int role) {
        int admin;
        if (role == 0 || role == 1) {
            admin = 1;
        } else {
            admin = 0;
        }
        return admin;
    }
}
