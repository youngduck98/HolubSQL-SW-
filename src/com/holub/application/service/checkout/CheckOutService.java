package com.holub.application.service.checkout;

import com.holub.application.domain.book.Book;
import com.holub.application.domain.checkout.CheckOut;
import com.holub.application.domain.member.Grant;
import com.holub.application.model.Model;

import java.io.IOException;
import java.security.cert.Extension;
import java.time.LocalDate;
import java.util.List;

public interface CheckOutService {

    // 책 빌리기
    public void checkOutBook(Grant grant, CheckOut checkOutInfo);

    // uuid를 사용해서 책 반납하기
    public void returnBook(Grant grant, Integer returnUuid);
    
    // uuid를 사용해서 책 연장하기
    public void extensionDueDate(Grant grant, LocalDate dueDateInfo, Integer dueDateUuid) throws IOException;

    // uuid 리스트를 사용해서 찾은 대출정보 리턴 -> 관리자가 checkOutTable 모두 받을때 사용
    public List<CheckOut> getCheckOutList(Grant grant, List<Integer> uuidList) throws IOException;

    public void sortCheckOut(String[] callName, int[] asc);

    // 나의 CheckOutList정보 반환
    public List<CheckOut> getMyCheckOutInfo(Integer myUuid);
}
