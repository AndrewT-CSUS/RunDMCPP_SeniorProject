package com.RunDMCPP.Backend.services;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Collections;

// Service component, meaning it's a special class for business logic
@Service
@CrossOrigin(origins = "http://localhost:3000") // Allows requests from our React app
public class CloudinaryService {

    // Auto-injected Cloudinary object to interact with Cloudinary API
    @Autowired
    private Cloudinary cloudinary;

    // Values injected from application.properties (your Cloudinary account details)
    @Value("${cloudinary.cloud_name}")
    private String cloudName;
    @Value("${cloudinary.api_key}")
    private String apiKey;
    @Value("${cloudinary.api_secret}")
    private String apiSecret;

    // Constructor initializes Cloudinary with account details
    public CloudinaryService() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret));
    }

    // Method to fetch image URLs from Cloudinary
    public List<String> getImageUrlsFromCloudinary() {
        try {
            String folderName = "sample"; // Folder where images are stored
            Map result = cloudinary.search()
                        .expression("folder:" + folderName)
                        .maxResults(100)
                        .execute();

            List<Map> resources = (List<Map>) result.get("resources");
            // Convert the response to a list of URLs
            List<String> imageUrls = resources.stream()
                    .map(resource -> (String) resource.get("secure_url"))
                    .collect(Collectors.toList());

            return imageUrls;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // Return empty list in case of errors
        }
    }

}
