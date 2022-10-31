package com.subrutin.catalog.web;

import com.subrutin.catalog.dto.BookCreateDTO;
import com.subrutin.catalog.dto.BookDetailDTO;
import com.subrutin.catalog.dto.BookUpdateRequestDTO;
import com.subrutin.catalog.service.BookService;
import com.subrutin.catalog.service.impl.GreetingServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class BookResource {

//    Logger log = LoggerFactory.getLogger(BookResource.class);

    private final BookService bookService;


//    nama yang salah /get-book/{bookId}
    @GetMapping("/book/{bookId}")
    public BookDetailDTO findBookDetail(@PathVariable("bookId") Long id) {
        StopWatch stopWatch = new StopWatch();
        log.info("start findBookDetail "+id);
        stopWatch.start();
        BookDetailDTO result = bookService.findBookDetailById(id);
        log.info("finish findBookDetail. execution time = {}",stopWatch.getTotalTimeMillis());
        return result;
    }

//    nama yang salah /save-book /create-book
    @PostMapping("/book")
    public ResponseEntity<Void> createNewBook(@RequestBody BookCreateDTO dto){
        bookService.createNewBook(dto);
        return ResponseEntity.created(URI.create("/book")).build();
    }

    @GetMapping("/book")
    public ResponseEntity<List<BookDetailDTO>> findBookList(){
        return ResponseEntity.ok().body(bookService.findBookListDetail());

    }

    @PutMapping("/book/{bookId}")
    public ResponseEntity<Void> updateBook(@PathVariable("bookId") Long bookId, @RequestBody BookUpdateRequestDTO dto){
        bookService.updateBook(bookId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/book/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") Long bookId){
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }

}
