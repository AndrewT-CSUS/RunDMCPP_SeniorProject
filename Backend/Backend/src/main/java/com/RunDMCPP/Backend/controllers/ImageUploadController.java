package com.RunDMCPP.Backend.controllers;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

// RestController, meaning it can handle HTTP requests
@RestController
@CrossOrigin(origins = "http://localhost:3000") // Allows requests from our React app
public class ImageUploadController {

    // Auto-injected Cloudinary object to interact with Cloudinary API
    @Autowired
    private Cloudinary cloudinary;

    // Endpoint to upload an image
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // Upload the image to Cloudinary and get the result
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("secure_url"); // Extract the image URL
            return ResponseEntity.ok(Map.of("secure_url", imageUrl)); // Return the URL in the response
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error uploading image: " + e.getMessage());
        }
    }
    
}
