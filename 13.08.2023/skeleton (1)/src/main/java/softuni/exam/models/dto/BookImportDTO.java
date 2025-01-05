package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

public class BookImportDTO {
    //"author": "F. Scott Fitzgerald",
    //    "available": true,
    //    "description": "A classic novel set in the roaring 20s",
    //    "genre": "CLASSIC_LITERATURE",
    //    "title": "The Great Gatsby",
    //    "rating": 9.1

    // казва че това не е наред
    //   "author": "Aldous Huxley",
    //    "available": true,
    //    "description": "A dystopian novel envisioning a future society",
    //    "genre": "SCIENCE_FICTION",
    //    "title": "Brave New World",
    //    "rating": 4.7


    @Expose
    @Length(min=3, max=40)
    private String author;

    @Expose
    private boolean available;

    @Expose
    @Length(min=5)
    private String description;

    @Expose
    private String genre;

    @Expose
    @Length(min=3, max=40)
    private String title;

    @Expose
    @Min(value=0)
    private double rating;

    public BookImportDTO() {
    }

    public BookImportDTO(String author, boolean available, String description, String genre, String title, double rating) {
        this.author = author;
        this.available = available;
        this.description = description;
        this.genre = genre;
        this.title = title;
        this.rating = rating;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
