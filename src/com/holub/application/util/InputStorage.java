package com.holub.application.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputStorage {

    private static final InputStorage instance = new InputStorage();
    private final Scanner sc;
    private final List<String> storage;

    private InputStorage(){
        sc = new Scanner(System.in);
        storage = new ArrayList<>();
    }

    public static InputStorage getInstance(){
        return instance;
    }

    public void getInput() {
        String input = sc.next();
        storage.add(input);
    }

    public List<String> returnStorage() {
        List<String> temp = new ArrayList<>(storage);
        storage.clear();
        return temp;
    }

    public String getStorage(int i) {
        if (storage.size() >= i + 1) {
            return storage.get(i);
        }
        return null;
    }

}
