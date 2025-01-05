package softuni.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BookImportDTO;
import softuni.exam.models.entity.Book;
import softuni.exam.repository.BookRepository;
import softuni.exam.service.BookService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import softuni.exam.util.ValidationUtil;


@Service
public class BookServiceImpl implements BookService {

    private static final String FILE_PATH = "src/main/resources/files/json/books.json";
    private final BookRepository bookRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
private final ValidationUtil validationUtil;

@Autowired
    public BookServiceImpl(BookRepository bookRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.bookRepository = bookRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.bookRepository.count()>0;
    }

    @Override
    public String readBooksFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importBooks() throws IOException {
        StringBuilder sb = new StringBuilder();
        BookImportDTO[] bookImportDTOS = this.gson.fromJson(readBooksFromFile(), BookImportDTO[].class);
        for (BookImportDTO bookImportDTO : bookImportDTOS) {
            if (this.bookRepository.findByTitle(bookImportDTO.getTitle()).isPresent()
                    || !this.validationUtil.isValid(bookImportDTO)) {
                sb.append("Invalid book").append(System.lineSeparator());
                continue;
            }
            Book book = this.modelMapper.map(bookImportDTO, Book.class);

            this.bookRepository.saveAndFlush(book);
            sb.append(String.format("Successfully imported book %s - %s",
                     bookImportDTO.getAuthor(), bookImportDTO.getTitle())).append(System.lineSeparator());

        }


        return sb.toString();

    }
}
