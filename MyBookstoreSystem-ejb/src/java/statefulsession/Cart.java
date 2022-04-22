package statefulsession;
//充当购物车的元组数据：（书名、价格、数量）
public class Cart {
    
    private String isbn;
    private String title;
    private double price;
    private Integer number = 1;

    public Cart() {
        
    }

    public Cart(String isbn) {
        this.isbn = isbn;
    }
    
    public Cart(String isbn, String title, double price) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
    }

    public Cart(String isbn, String title, double price, Integer number) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.number = number;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
