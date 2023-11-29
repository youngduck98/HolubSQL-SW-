package com.holub.application.controller;

import com.holub.application.model.Model;
import com.holub.application.model.vtc.VTC;
import com.sun.org.apache.xpath.internal.operations.Mod;

public class BookController {
    private static BookController bc;
    private BookController(Model model){
        VTC input = model.getInput();

    }

    public BookController getInstance(Model model){
        if(bc == null){
            bc = new BookController(model);
        }
        return bc;
    }
}
