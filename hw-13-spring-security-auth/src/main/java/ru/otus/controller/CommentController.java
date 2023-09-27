package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.LibraryUser;
import ru.otus.service.CommentService;
import ru.otus.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;

    @PreAuthorize("hasAuthority('ADMIN') or principal.username == #comment.user.username")
    @PostMapping("/delete/{comment}")
    public String deleteComment(@PathVariable Comment comment) {
        long id = comment.getBook().getId();
        commentService.deleteComment(comment);
        return "redirect:/view/" + id;
    }

    @GetMapping(value = "/create/comment", params = "bookId")
    public String addComment(@RequestParam("bookId") Book book,
                             Model model, Principal principal) {
        model.addAttribute("comment", new Comment("", book, (LibraryUser) userService.loadUserByUsername(principal.getName())));
        return "editComment";
    }

    @PostMapping("/create/comment")
    public String addBook(@ModelAttribute Comment comment) {
        commentService.addOrSaveComment(comment);
        return "redirect:/view/" + comment.getBook().getId();
    }

    @PreAuthorize("hasAuthority('ADMIN') or principal.username == #comment.user.username")
    @GetMapping("/edit/{comment}")
    public String editComment(@PathVariable Comment comment, Model model) {
        model.addAttribute("comment", comment);
        return "editComment";
    }

}
