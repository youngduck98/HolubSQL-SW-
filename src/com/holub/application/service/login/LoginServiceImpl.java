package com.holub.application.service.login;

import com.holub.application.dao.Dao;
import com.holub.application.model.Model;

public class LoginServiceImpl implements LoginService{

    private static LoginService instance;
    private final Dao dao;

    private LoginServiceImpl(Dao dao){
        this.dao = dao;
    }

    public static LoginService getInstance(Dao dao){
        if (instance == null) {
            instance = new LoginServiceImpl(dao);
        }
        return instance;
    }

    @Override
    public void login(Model model) {
        // TODO
        // 1. dao에서 id, password 탐색
        // 2. Member 변수 생성
        // 3. Model에 저장
        // model.addAttribute("MyInfo", member);

    }

    @Override
    public void logout(Model model) {
        // TODO
        model.removeAttribute("MyInfo");
    }
}
