package ru.otus.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Genre;
import ru.otus.service.ConsoleService;
import ru.otus.service.GenreService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao daoGenre;
    private final ConsoleService consoleService;

    public GenreServiceImpl(final GenreDao daoGenre, final ConsoleService consoleService) {
        this.daoGenre = daoGenre;
        this.consoleService = consoleService;
    }

    @Override
    public void loadAndPrintGenreList() {
        List<Genre> list = daoGenre.getAll();
        consoleService.printGenreList(list);
    }

    @Override
    public void add() {
        String genreName = consoleService.enterGenreName();
        daoGenre.insert(new Genre(0, genreName));
    }

    @Override
    public void edit() {
        List<Genre> list = daoGenre.getAll();
        consoleService.printGenreList(list);
        long genreId = consoleService.enterGenreNumber();
        String genreName = consoleService.enterGenreName();
        daoGenre.update(new Genre(genreId, genreName));
    }

    @Override
    public void remove() {
        loadAndPrintGenreList();
        long genreId = consoleService.enterGenreNumber();
        daoGenre.deleteById(genreId);
    }
}