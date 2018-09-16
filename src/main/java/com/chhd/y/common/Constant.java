package com.chhd.y.common;

public interface Constant {

    enum Regex {
        email("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

        String s;

        Regex(String s) {
            this.s = s;
        }

        public String getS() {
            return s;
        }
    }
}
