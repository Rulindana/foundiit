package com.student.foundiit.dto;

import com.student.foundiit.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ItemDTO {
    private Long id;
    private String title;
    private String category;
    private String type;
    private String location;
    private String description;
    private String image;
    private String status;
    private Long userId;
    private String userName;
    private LocalDateTime createdAt;

    public ItemDTO() {
    }

    public ItemDTO(Long id, String title, String category, String type, String location, String description,
            String image, String status, Long userId, String userName, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.type = type;
        this.location = location;
        this.description = description;
        this.image = image;
        this.status = status;
        this.userId = userId;
        this.userName = userName;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static ItemDTO fromEntity(Item item) {
        ItemDTO dto = new ItemDTO();
        dto.setId(item.getId());
        dto.setTitle(item.getTitle());
        dto.setCategory(item.getCategory());
        dto.setType(item.getType().name());
        dto.setLocation(item.getLocation());
        dto.setDescription(item.getDescription());
        dto.setImage(item.getImage());
        dto.setStatus(item.getStatus().name());
        dto.setUserId(item.getUser().getId());
        dto.setUserName(item.getUser().getName());
        dto.setCreatedAt(item.getCreatedAt());
        return dto;
    }
}
