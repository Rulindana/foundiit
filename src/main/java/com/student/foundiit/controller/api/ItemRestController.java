package com.student.foundiit.controller.api;

import com.student.foundiit.dto.ApiResponse;
import com.student.foundiit.dto.ItemDTO;
import com.student.foundiit.dto.ItemRequestDTO;
import com.student.foundiit.model.Item;
import com.student.foundiit.model.User;
import com.student.foundiit.service.ItemService;
import com.student.foundiit.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/items")
public class ItemRestController {

    private final ItemService itemService;
    private final UserService userService;

    public ItemRestController(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ItemDTO>>> getAllItems(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String category) {

        List<Item> items;

        if (type != null) {
            items = itemService.getItemsByType(Item.ItemType.valueOf(type.toUpperCase()));
        } else if (status != null) {
            items = itemService.getItemsByStatus(Item.Status.valueOf(status.toUpperCase()));
        } else if (category != null) {
            items = itemService.getItemsByCategory(category);
        } else {
            items = itemService.getAllItems();
        }

        List<ItemDTO> dtos = items.stream()
                .map(ItemDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(dtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ItemDTO>> getItemById(@PathVariable Long id) {
        Item item = itemService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(ItemDTO.fromEntity(item)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ItemDTO>> createItem(@RequestBody ItemRequestDTO dto) {
        User user = userService.findById(dto.getUserId());

        Item item = new Item();
        item.setTitle(dto.getTitle());
        item.setCategory(dto.getCategory());
        item.setType(Item.ItemType.valueOf(dto.getType().toUpperCase()));
        item.setLocation(dto.getLocation());
        item.setDescription(dto.getDescription());
        item.setUser(user);

        Item saved = itemService.saveItem(item);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Item created successfully", ItemDTO.fromEntity(saved)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ItemDTO>> updateItem(@PathVariable Long id, @RequestBody ItemRequestDTO dto) {
        Item item = new Item();
        item.setTitle(dto.getTitle());
        item.setCategory(dto.getCategory());
        item.setType(Item.ItemType.valueOf(dto.getType().toUpperCase()));
        item.setLocation(dto.getLocation());
        item.setDescription(dto.getDescription());

        Item updated = itemService.updateItem(id, item);
        return ResponseEntity.ok(ApiResponse.success("Item updated successfully", ItemDTO.fromEntity(updated)));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Void>> updateItemStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        itemService.updateStatus(id, Item.Status.valueOf(status.toUpperCase()));
        return ResponseEntity.ok(ApiResponse.success("Item status updated", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok(ApiResponse.success("Item deleted successfully", null));
    }
}
