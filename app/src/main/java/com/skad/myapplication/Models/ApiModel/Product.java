package com.skad.myapplication.Models.ApiModel;
import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
    private long id;
    private String title;
    private String body_html;
    private String vendor;
    private String product_type;
    private String created_at;
    private String handle;
    private String updated_at;
    private String published_at;
    private String template_suffix;
    private String published_scope;
    private String tags;
    private String status;
    private String admin_graphql_api_id;
    private List<Option> options;
    private List<Image> images;
    private Image image;
    private Boolean isFav = false;
    private String price;
    private String selectedColor;
    private String selectedSize;
    private String selectedCaret;
    private String selectedMetal;
    private String selectedWeight;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(String selectedColor) {
        this.selectedColor = selectedColor;
    }

    public String getSelectedSize() {
        return selectedSize;
    }

    public void setSelectedSize(String selectedSize) {
        this.selectedSize = selectedSize;
    }

    public String getSelectedCaret() {
        return selectedCaret;
    }

    public void setSelectedCaret(String selectedCaret) {
        this.selectedCaret = selectedCaret;
    }

    public String getSelectedMetal() {
        return selectedMetal;
    }

    public void setSelectedMetal(String selectedMetal) {
        this.selectedMetal = selectedMetal;
    }

    public String getSelectedWeight() {
        return selectedWeight;
    }

    public void setSelectedWeight(String selectedWeight) {
        this.selectedWeight = selectedWeight;
    }

    public Boolean getFav() {
        return isFav;
    }

    public void setFav(Boolean fav) {
        isFav = fav;
    }

    // Getters and setters for each field
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody_html() {
        return body_html;
    }

    public void setBody_html(String body_html) {
        this.body_html = body_html;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getTemplate_suffix() {
        return template_suffix;
    }

    public void setTemplate_suffix(String template_suffix) {
        this.template_suffix = template_suffix;
    }

    public String getPublished_scope() {
        return published_scope;
    }

    public void setPublished_scope(String published_scope) {
        this.published_scope = published_scope;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdmin_graphql_api_id() {
        return admin_graphql_api_id;
    }

    public void setAdmin_graphql_api_id(String admin_graphql_api_id) {
        this.admin_graphql_api_id = admin_graphql_api_id;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
