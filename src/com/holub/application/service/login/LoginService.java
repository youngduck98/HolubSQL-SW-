package com.holub.application.service.login;

import com.holub.application.model.Model;

public interface LoginService {

    // id, password 를 받아서 Member를 찾고 model에 저장 -> 로그인
    public void login(Model model);

    // model에 있는 MyInfo 삭제 -> 로그아웃
    public void logout(Model model);

}
