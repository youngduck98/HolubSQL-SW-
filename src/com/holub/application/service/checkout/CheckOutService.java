package com.holub.application.service.checkout;

import com.holub.application.domain.book.Book;
import com.holub.application.domain.checkout.CheckOut;
import com.holub.application.domain.member.Grant;
import com.holub.application.model.Model;

import java.security.cert.Extension;
import java.time.LocalDate;
import java.util.List;

public interface CheckOutService {

    // 책 uuid를 사용해서 빌리기
    public void checkOutBook(Grant grant, Integer uuid);

    // uuid를 사용해서 책 반납하기
    public void returnBook(Grant grant, Integer uuid);
    
    // uuid를 사용해서 책 연장하기
    public void extensionDueDate(Grant grant, Integer uuid, LocalDate date);

    // uuid 리스트를 사용해서 찾은 대출정보 리턴
    public List<CheckOut> searchCheckOut(Grant grant, List<Integer> uuids);

}
