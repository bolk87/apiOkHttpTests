package entity;

import com.google.gson.annotations.SerializedName;

public class Pet {
    private Long id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private Tag[] tags;
    private String status;

    // Конструкторы
    public Pet() {}

    public Pet(Long id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Вложенные классы
    static class Category {
        private Long id;
        private String name;
    }

    static class Tag {
        private Long id;
        private String name;
    }
}