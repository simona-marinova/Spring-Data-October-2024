package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="borrowing_records")
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordsImportDTO {

    @XmlElement(name="borrowing_record")
    private List<BorrowingRecordImportDTO> borrowingRecordImportDTOList;

    public BorrowingRecordsImportDTO(List<BorrowingRecordImportDTO> borrowingRecordImportDTOList) {
        this.borrowingRecordImportDTOList = borrowingRecordImportDTOList;
    }

    public BorrowingRecordsImportDTO() {
    }

    public List<BorrowingRecordImportDTO> getBorrowingRecordImportDTOList() {
        return borrowingRecordImportDTOList;
    }

    public void setBorrowingRecordImportDTOList(List<BorrowingRecordImportDTO> borrowingRecordImportDTOList) {
        this.borrowingRecordImportDTOList = borrowingRecordImportDTOList;
    }
}
