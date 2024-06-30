package website.BAS.User;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "users")  // Specify the MongoDB collection name
public class user {
    @Id
    private String id;

    private String type;
    private String place;
    private Double oPrice;
    private Double sPrice;
    private String isNego;
    private String product;
    private String specs;
    private String category;
    private String phoneNo;
    private String email;
    private Date date;
    private String contact;
    private List<String> images;
    private List<String> customers;
    private List<String> wishlist;
    private String acceptedTo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Double getoPrice() {
        return oPrice;
    }

    public void setoPrice(Double oPrice) {
        this.oPrice = oPrice;
    }

    public Double getsPrice() {
        return sPrice;
    }

    public void setsPrice(Double sPrice) {
        this.sPrice = sPrice;
    }

    public String getIsNego() {
        return isNego;
    }

    public void setIsNego(String isNego) {
        this.isNego = isNego;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCustomers() {
        return customers;
    }

    public void setCustomers(List<String> customers) {
        this.customers = customers;
    }

    public List<String> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<String> wishlist) {
        this.wishlist = wishlist;
    }

    public String getAcceptedTo() {
        return acceptedTo;
    }

    public void setAcceptedTo(String acceptedTo) {
        this.acceptedTo = acceptedTo;
    }
    public user(String id, String type, String place, Double oPrice, Double sPrice, String isNego, String product, String specs, String category, String email, Date date, String contact, List<String> images, List<String> customers, List<String> wishlist, String acceptedTo) {
        this.id = id;
        this.type = type;
        this.place = place;
        this.oPrice = oPrice;
        this.sPrice = sPrice;
        this.isNego = isNego;
        this.product = product;
        this.specs = specs;
        this.category = category;
        this.email = email;
        this.date = date;
        this.contact = contact;
        this.images = images;
        this.customers = customers;
        this.wishlist = wishlist;
        this.acceptedTo = acceptedTo;
    }

    public user() {
        // Default constructor for Spring Data MongoDB
    }


}
