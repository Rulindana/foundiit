package com.student.foundiit.dto;

import com.student.foundiit.model.Claim;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ClaimDTO {
    private Long id;
    private Long itemId;
    private String itemTitle;
    private Long userId;
    private String userName;
    private String proofDescription;
    private String status;
    private LocalDateTime createdAt;

    public ClaimDTO() {
    }

    public ClaimDTO(Long id, Long itemId, String itemTitle, Long userId, String userName, String proofDescription,
            String status, LocalDateTime createdAt) {
        this.id = id;
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.userId = userId;
        this.userName = userName;
        this.proofDescription = proofDescription;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
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

    public String getProofDescription() {
        return proofDescription;
    }

    public void setProofDescription(String proofDescription) {
        this.proofDescription = proofDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static ClaimDTO fromEntity(Claim claim) {
        ClaimDTO dto = new ClaimDTO();
        dto.setId(claim.getId());
        dto.setItemId(claim.getItem().getId());
        dto.setItemTitle(claim.getItem().getTitle());
        dto.setUserId(claim.getUser().getId());
        dto.setUserName(claim.getUser().getName());
        dto.setProofDescription(claim.getProofDescription());
        dto.setStatus(claim.getStatus().name());
        dto.setCreatedAt(claim.getCreatedAt());
        return dto;
    }
}
