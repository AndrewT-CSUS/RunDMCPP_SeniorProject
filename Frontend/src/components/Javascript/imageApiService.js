// imageApiService.js

// Function to upload an image to our server
const uploadImage = async (file, accessToken) => {
    const formData = new FormData();
    formData.append('file', file); // Attach the file to form data

    try {
        // Attempt to send the image to our backend
        const response = await fetch('http://localhost:8080/upload', {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${accessToken}` // Add access token to headers
            },
            body: formData,
        });
        if (!response.ok) throw new Error(`Network response was not ok. Status: ${response.status}`);
        return await response.json(); // Parse and return the response
    } catch (error) {
        console.error('Error during image upload:', error);
        throw error;
    }
};

export { uploadImage }; // Export the function for use elsewhere
