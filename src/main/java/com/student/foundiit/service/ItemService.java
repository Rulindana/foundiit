package com.student.foundiit.service;

import com.student.foundiit.exception.ResourceNotFoundException;
import com.student.foundiit.model.Item;
import com.student.foundiit.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item saveItem(Item item, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Files.copy(file.getInputStream(), uploadPath.resolve(fileName));
            item.setImage(fileName);
        }
        return itemRepository.save(item);
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> getItemsByType(Item.ItemType type) {
        return itemRepository.findByType(type);
    }

    public List<Item> getItemsByStatus(Item.Status status) {
        return itemRepository.findByStatus(status);
    }

    public List<Item> getItemsByCategory(String category) {
        return itemRepository.findByCategory(category);
    }

    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
    }

    public Item updateItem(Long id, Item updatedItem) {
        Item existing = findById(id);
        existing.setTitle(updatedItem.getTitle());
        existing.setCategory(updatedItem.getCategory());
        existing.setType(updatedItem.getType());
        existing.setLocation(updatedItem.getLocation());
        existing.setDescription(updatedItem.getDescription());
        if (updatedItem.getImage() != null) {
            existing.setImage(updatedItem.getImage());
        }
        return itemRepository.save(existing);
    }

    public void updateStatus(Long id, Item.Status status) {
        Item item = findById(id);
        item.setStatus(status);
        itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Item not found with id: " + id);
        }
        itemRepository.deleteById(id);
    }
}
