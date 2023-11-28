package com.holub.application.view;

import com.holub.application.model.Model;
import com.holub.application.util.InputStorage;

public abstract class View {

    InputStorage storage = InputStorage.getInstance(); // 값을 입력받고, 입력 받은값을 저장하는 저장소
    abstract public void execute(Model model);

}
