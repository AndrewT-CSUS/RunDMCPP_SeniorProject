package com.RunDMCPP.Backend.controllers;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.RunDMCPP.Backend.services.CloudinaryService;
import java.util.List;
import java.util.Collections;

// Marks this class as a RestController, meaning it can handle HTTP requests
@RestController
@CrossOrigin(origins = "http://localhost:3000") // Allows requests from our React app
public class ImageDisplayController {

    // Auto-injected service to interact with Cloudinary
    @Autowired
    private CloudinaryService cloudinaryService;

    // Endpoint to get image URLs from Cloudinary
    @GetMapping("/images")
    public ResponseEntity<List<String>> getImagesFromCloudinary() {
        try {
            List<String> imageUrls = cloudinaryService.getImageUrlsFromCloudinary(); // Fetch URLs
            return ResponseEntity.ok(imageUrls); // Return the URLs in the response
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
    
}
