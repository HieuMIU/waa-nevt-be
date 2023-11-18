package nevt.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.PathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.nio.file.StandardCopyOption;

@CrossOrigin(origins = "*")
@RequestMapping("/api/images")
@RestController
public class FileUploadController {

    @Value("${imagesFolder}")
    String imagesFolder;
    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestPart("file") MultipartFile file) {

        File uploadDir = new File(imagesFolder);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFileName = System.currentTimeMillis() + "-" + fileName;

        try {
            Path targetLocation = uploadDir.toPath().resolve(uniqueFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);


            return new ResponseEntity<String>(uniqueFileName, HttpStatus.OK);
        } catch (IOException ex) {
            return ResponseEntity.status(500).body("Failed to upload the file");
        }
    }

    @GetMapping("/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws IOException {
        try {
            // Assuming the imagesFolder is the root folder where your images are stored
            Path imagePath = Paths.get(imagesFolder, imageName);
            Resource imageResource = new PathResource(imagePath);

            if (imageResource.exists()) {
                return ResponseEntity.ok()
                        .contentLength(Files.size(imagePath))
                        .header("Content-Type", "image/jpg") // Adjust the content type based on your image type
                        .body(imageResource);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            System.out.println("Error fetching image: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
