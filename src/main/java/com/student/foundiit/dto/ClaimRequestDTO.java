package com.student.foundiit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ClaimRequestDTO {
    private Long itemId;
    private Long userId;
    private String proofDescription;

    public ClaimRequestDTO() {
    }

    public ClaimRequestDTO(Long itemId, Long userId, String proofDescription) {
        this.itemId = itemId;
        this.userId = userId;
        this.proofDescription = proofDescription;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProofDescription() {
        return proofDescription;
    }

    public void setProofDescription(String proofDescription) {
        this.proofDescription = proofDescription;
    }
}
