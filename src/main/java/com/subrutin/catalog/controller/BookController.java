package com.subrutin.catalog.controller;

import com.subrutin.catalog.dto.BookCreateDTO;
import com.subrutin.catalog.dto.BookDetailDTO;
import com.subrutin.catalog.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/book")
@Api(value = "Book Controller")
public class BookController {

    private final BookService bookService;

    @GetMapping("/list")
    @Operation(summary = "List of Books")
    public String findBookList(Model model) {
        List<BookDetailDTO> books = bookService.findBookListDetail();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/new")
    @Operation(summary = "Get New Book")
    public String loadBookForm(Model model) {
        BookCreateDTO dto = new BookCreateDTO();
        model.addAttribute("bookCreateDTO", dto);
        return "book/book-new";
    }

    @PostMapping("/new")
    @Operation(summary = "Post New Book")
    public String addNewBook(@ModelAttribute("bookCreateDTO") @Valid BookCreateDTO dto,
                             BindingResult bindingResult,
                             Errors errors,
                             Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("bookCreateDTO", dto);
            return "book/book-new";
        }
        bookService.createNewBook(dto);
        return "redirect:/book/list";

    }

}
