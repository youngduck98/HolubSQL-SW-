package com.holub.application.view;

import com.holub.application.model.Model;
import com.holub.application.util.InputScanner;

import java.util.List;

public abstract class View {

    InputScanner scanner = InputScanner.getInstance(); // 값을 입력받고, 입력 받은값을 저장하는 저장소
    abstract public List<String> execute(Model model);

}
