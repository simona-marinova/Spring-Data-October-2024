package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BorrowingRecordImportDTO;
import softuni.exam.models.dto.BorrowingRecordsImportDTO;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.repository.BookRepository;
import softuni.exam.repository.BorrowingRecordRepository;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.BorrowingRecordsService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowingRecordsServiceImpl implements BorrowingRecordsService {

    private static final String FILE_PATH = "src/main/resources/files/xml/borrowing-records.xml";
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final BookRepository bookRepository;
    private final LibraryMemberRepository libraryMemberRepository;


    @Autowired
    public BorrowingRecordsServiceImpl(BorrowingRecordRepository borrowingRecordRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, BookRepository bookRepository, LibraryMemberRepository libraryMemberRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.bookRepository = bookRepository;
        this.libraryMemberRepository = libraryMemberRepository;
    }

    @Override
    public boolean areImported() {
        return this.borrowingRecordRepository.count() > 0;
    }

    @Override
    public String readBorrowingRecordsFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importBorrowingRecords() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        BorrowingRecordsImportDTO borrowingRecordsImportDTO = this.xmlParser.fromFile(FILE_PATH, BorrowingRecordsImportDTO.class);
        for (BorrowingRecordImportDTO borrowingRecordImportDTO : borrowingRecordsImportDTO.getBorrowingRecordImportDTOList()) {
            if (!bookRepository.findByTitle(borrowingRecordImportDTO.getBookImportDTO().getTitle()).isPresent() ||
                    !validationUtil.isValid(borrowingRecordImportDTO) ||
                    !libraryMemberRepository.findById((Long) borrowingRecordImportDTO.getLibraryMemberDTO().getId()).isPresent()
                    || borrowingRecordImportDTO.getBorrowDate()==null ||
            borrowingRecordImportDTO.getReturnDate()==null) {
                sb.append("Invalid borrowing record").append(System.lineSeparator());
                continue;
            }
            BorrowingRecord borrowingRecord = this.modelMapper.map(borrowingRecordImportDTO, BorrowingRecord.class);
            borrowingRecord.setBook(bookRepository.findByTitle(borrowingRecordImportDTO.getBookImportDTO().getTitle()).get());
            borrowingRecord.setLibraryMember(libraryMemberRepository.findById(borrowingRecordImportDTO.getLibraryMemberDTO().getId()).get());
            sb.append(String.format("Successfully imported borrowing record %s - %s", borrowingRecordImportDTO.getBookImportDTO().getTitle(), borrowingRecordImportDTO.getBorrowDate())).append(System.lineSeparator());
            this.borrowingRecordRepository.saveAndFlush(borrowingRecord);

        }
return sb.toString();
    }

    @Override
    public String exportBorrowingRecords() {

            StringBuilder sb = new StringBuilder();
            List<BorrowingRecord> borrowingRecordList = this.borrowingRecordRepository.findAllByBorrowDateBeforeOrderByBorrowDateDesc(LocalDate.parse("2021-09-10"));
        for (BorrowingRecord borrowingRecord : borrowingRecordList) {
            if(borrowingRecord.getBook().getGenre() == borrowingRecord.getBook().getGenre().SCIENCE_FICTION )
                sb.append(String.format("Book title: %s\n" +
                                        "*Book author: %s\n" +
                                        "**Date borrowed: %s\n" +
                                        "***Borrowed by: %s %s",
                                borrowingRecord.getBook().getTitle(), borrowingRecord.getBook().getAuthor(),
                               borrowingRecord.getBorrowDate().toString()
                                ,borrowingRecord.getLibraryMember().getFirstName(), borrowingRecord.getLibraryMember().getLastName() ))
                        .append(System.lineSeparator());
            }

            return sb.toString();

        }

}