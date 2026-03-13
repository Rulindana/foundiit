package com.student.foundiit.controller.api;

import com.student.foundiit.dto.ApiResponse;
import com.student.foundiit.dto.ClaimDTO;
import com.student.foundiit.dto.ClaimRequestDTO;
import com.student.foundiit.model.Claim;
import com.student.foundiit.model.Item;
import com.student.foundiit.model.User;
import com.student.foundiit.service.ClaimService;
import com.student.foundiit.service.ItemService;
import com.student.foundiit.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/claims")
public class ClaimRestController {

    private final ClaimService claimService;
    private final ItemService itemService;
    private final UserService userService;

    public ClaimRestController(ClaimService claimService, ItemService itemService, UserService userService) {
        this.claimService = claimService;
        this.itemService = itemService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClaimDTO>>> getAllClaims() {
        List<ClaimDTO> claims = claimService.getAllClaims().stream()
                .map(ClaimDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(claims));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClaimDTO>> getClaimById(@PathVariable Long id) {
        Claim claim = claimService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(ClaimDTO.fromEntity(claim)));
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<ApiResponse<List<ClaimDTO>>> getClaimsByItem(@PathVariable Long itemId) {
        List<ClaimDTO> claims = claimService.getClaimsByItem(itemId).stream()
                .map(ClaimDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(claims));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<ClaimDTO>>> getClaimsByUser(@PathVariable Long userId) {
        List<ClaimDTO> claims = claimService.getClaimsByUser(userId).stream()
                .map(ClaimDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(claims));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClaimDTO>> submitClaim(@RequestBody ClaimRequestDTO dto) {
        User user = userService.findById(dto.getUserId());
        Item item = itemService.findById(dto.getItemId());

        Claim claim = new Claim();
        claim.setUser(user);
        claim.setItem(item);
        claim.setProofDescription(dto.getProofDescription());

        Claim saved = claimService.submitClaim(claim);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Claim submitted successfully", ClaimDTO.fromEntity(saved)));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Void>> updateClaimStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        claimService.updateClaimStatus(id, Claim.Status.valueOf(status.toUpperCase()));
        return ResponseEntity.ok(ApiResponse.success("Claim status updated", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteClaim(@PathVariable Long id) {
        claimService.deleteClaim(id);
        return ResponseEntity.ok(ApiResponse.success("Claim deleted successfully", null));
    }
}
