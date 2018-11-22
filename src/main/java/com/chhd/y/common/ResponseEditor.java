package com.chhd.y.common;

import java.util.List;

public class ResponseEditor {

    private int errno;
    private List<String> data;

    public ResponseEditor(int errno, List<String> data) {
        this.errno = errno;
        this.data = data;
    }

    public int getErrno() {
        return errno;
    }

    public List<String> getData() {
        return data;
    }
}
