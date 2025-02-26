package vn.viettuts.qlsv.entity;

import vn.viettuts.qlsv.utils.CalendarAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Product {
    private String id;
    private String productName;
    private String productCategory;
    private String productSupplier;
    @XmlJavaTypeAdapter(CalendarAdapter.class)
    private Calendar productExpiredDate;
    private String quantity;
    private String price;
    @XmlJavaTypeAdapter(CalendarAdapter.class)
    private Calendar importDate;

    public Product() {
    }

    public Product(String id, String productName, String productCategory, String productSupplier, Calendar productExpiredDate, String quantity, String price, Calendar importDate) {
        this.id = id;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productSupplier = productSupplier;
        this.productExpiredDate = productExpiredDate;
        this.quantity = quantity;
        this.price = price;
        this.importDate = importDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductSupplier() {
        return productSupplier;
    }

    public void setProductSupplier(String productSupplier) {
        this.productSupplier = productSupplier;
    }

    public String getProductExpiredDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(productExpiredDate.getTime());
    }

    public void setProductExpiredDate(Calendar productExpiredDate) {
        this.productExpiredDate = productExpiredDate;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImportDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(importDate.getTime());
    }

    public void setImportDate(Calendar importDate) {
        this.importDate = importDate;
    }
}
