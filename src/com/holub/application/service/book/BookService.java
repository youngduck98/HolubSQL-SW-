package com.holub.application.service.book;

import com.holub.application.domain.book.Book;
import com.holub.application.domain.member.Grant;
import com.holub.application.model.Model;

import java.io.IOException;
import java.util.List;


public interface BookService {

    // uuid 리스트를 받아서 Book 리스트 반환
    public List<Book> getBookList(Model model);

    // bookInfo를 받아서 book 추가
    public void addBook(Model model);
    
    // uuid를 입력받아서 book 삭제 -> book의 CheckOutState를 삭제로 변경
    public void deleteBook(Model model) throws IOException ;
    
    // uuid를 입력받아서 해당 책 정보 수정
    public void modifyBookInfo(Model model) throws IOException;

    // 장르 반환
    public List<String> getBookGenre();
    
    // 입력받은 장르의 책 반환
    public List<Book> getOnlySpecialGenre(Model model);

}
