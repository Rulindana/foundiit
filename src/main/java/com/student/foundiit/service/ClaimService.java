package com.student.foundiit.service;

import com.student.foundiit.exception.ResourceNotFoundException;
import com.student.foundiit.model.Claim;
import com.student.foundiit.repository.ClaimRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;

    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    public Claim submitClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    public Claim findById(Long id) {
        return claimRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found with id: " + id));
    }

    public List<Claim> getClaimsByItem(Long itemId) {
        return claimRepository.findByItemId(itemId);
    }

    public List<Claim> getClaimsByUser(Long userId) {
        return claimRepository.findByUserId(userId);
    }

    public void updateClaimStatus(Long id, Claim.Status status) {
        Claim claim = findById(id);
        claim.setStatus(status);
        claimRepository.save(claim);
    }

    public void deleteClaim(Long id) {
        if (!claimRepository.existsById(id)) {
            throw new ResourceNotFoundException("Claim not found with id: " + id);
        }
        claimRepository.deleteById(id);
    }
}
