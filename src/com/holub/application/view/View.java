package com.holub.application.view;

import com.holub.application.model.vtc.VTC;

public interface View {

<<<<<<< HEAD
public abstract class View {
    InputScanner scanner = InputScanner.getInstance(); // 값을 입력받고, 입력 받은값을 저장하는 저장소
    abstract public List<String> execute(Model model);
=======
    VTC execute();

>>>>>>> 740643524f44e24d9630aca38dca422dc3bd55e4
}
