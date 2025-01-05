package softuni.exam.models.dto;


import org.hibernate.validator.constraints.Length;
import softuni.exam.models.entity.LibraryMember;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="borrowing_record")
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordImportDTO {

    //<borrowing_record>
    //        <borrow_date>2020-01-13</borrow_date>
    //        <return_date>2022-09-11</return_date>
    //        <book>
    //            <title>The Lord of the Rings</title>
    //        </book>
    //        <member>
    //            <id>27</id>
    //        </member>
    //        <remarks>Handle with care, fragile book.</remarks>
    //    </borrowing_record>

    @XmlElement(name="borrow_date")
    private String borrowDate;

    @XmlElement(name="return_date")
    private String returnDate;

    @XmlElement(name="book")
    private BookImportDTO bookImportDTO;

    @XmlElement(name="member")
    private LibraryMemberDTO libraryMemberDTO;

    @XmlElement
    @Length(min=3, max=100)
    private String remarks;

    public BorrowingRecordImportDTO() {
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public BookImportDTO getBookImportDTO() {
        return bookImportDTO;
    }

    public void setBookImportDTO(BookImportDTO bookImportDTO) {
        this.bookImportDTO = bookImportDTO;
    }

    public LibraryMemberDTO getLibraryMemberDTO() {
        return libraryMemberDTO;
    }

    public void setLibraryMemberDTO(LibraryMemberDTO libraryMemberDTO) {
        this.libraryMemberDTO = libraryMemberDTO;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
