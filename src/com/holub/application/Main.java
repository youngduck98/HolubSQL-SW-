package com.holub.application;

public class Main {
    public static void main(String[] args) throws Exception {
        Config config = new Config();
        config.execute();
        MyApplication myApplication = new MyApplication();
        myApplication.execute();
    }
}
