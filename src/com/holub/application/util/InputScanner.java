package com.holub.application.util;

import java.util.*;

public class InputScanner {

    private static final InputScanner instance = new InputScanner();
    private final Scanner sc;

    private InputScanner() {
        this.sc = new Scanner(System.in);
    }

    public static InputScanner getInstance() {
        return instance;
    }

    public String inputString() {
        return sc.next();
    }

    public Integer inputInteger() {
        return sc.nextInt();
    }

}
