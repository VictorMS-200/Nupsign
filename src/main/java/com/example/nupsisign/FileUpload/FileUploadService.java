package com.example.nupsisign.FileUpload;

import lombok.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileUploadService implements IFileUploadService {

    private final Path rootLocation = Paths.get("uploads");

    // This method initializes the uploads directory if it does not exist
    @Override
    public void init() {
        try {
            File tempDirectory = new File(rootLocation.toUri());
            boolean exists = tempDirectory.exists();

            if (!exists) {
                Files.createDirectory(rootLocation);
            }

        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage:", e);
        }
    }

    // This method saves the file to the uploads directory
    @Override
    public void save(MultipartFile file, String fileName) {
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: ", e);
        }
    }

    // This method retrieves the file by its name
    @Override
    public Resource getFileByName(String fileName) {
        try {
            Path file = rootLocation.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable())
                return resource;
            else
                throw new RuntimeException("Could not read the file!");

        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    // This method deletes all files in the uploads directory
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public Stream<Path> loadAllFiles() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);

        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
