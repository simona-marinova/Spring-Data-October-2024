package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

public class SaleImportDTO {
    //{
    //  "discounted": true,
    //  "number": "1000123",
    //  "saleDate": "2022-02-02 12:43:00",
    //  "seller": 1
    //},

    @Expose
    private boolean discounted;

    @Expose
    @Length(min=7, max=7)
    private String number;

    @Expose
    private String saleDate;

    @Expose
    private int seller;

    public SaleImportDTO() {
    }

    public SaleImportDTO(boolean discounted, String number, String saleDate, int seller) {
        this.discounted = discounted;
        this.number = number;
        this.saleDate = saleDate;
        this.seller = seller;
    }

    public boolean isDiscounted() {
        return discounted;
    }

    public void setDiscounted(boolean discounted) {
        this.discounted = discounted;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public int getSeller() {
        return seller;
    }

    public void setSeller(int seller) {
        this.seller = seller;
    }
}
