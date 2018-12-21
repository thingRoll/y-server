package com.chhd.y.util;

import com.chhd.y.pojo.User;

public class RoleUtils {

    public static int checkPlus(User user) {
        return user != null && (user.getRole() == 0 || user.getRole() == 10) ? 1 : 0;
    }

    public static int checkAdmin(int role) {
        if (role == 0 || role == 1 || role == 2) {
            return 1;
        } else {
            return 0;
        }
    }

    public static boolean checkPermission(User user) {
        return user != null && checkPermission(user.getRole());
    }

    private static boolean checkPermission(int role) {
        return role == 0 || role == 1;
    }
}
