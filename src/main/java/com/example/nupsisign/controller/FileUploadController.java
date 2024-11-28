package com.example.nupsisign.controller;

import com.example.nupsisign.FileUpload.FileResponse;
import com.example.nupsisign.FileUpload.FileResponseMessage;
import com.example.nupsisign.FileUpload.IFileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/files")
public class FileUploadController {

    private final IFileUploadService fileUploadService;

    // This method uploads the files to the uploads directory
    @PostMapping("/upload-files")
    public ResponseEntity<FileResponseMessage> uploadFiles(@RequestParam("file") MultipartFile[] files) {
        String message = null;

        // Check if the files are empty
        try {
            List<String> fileNames = new ArrayList<>();
            Arrays.stream(files).forEach(file -> {
                fileUploadService.save(file);
                fileNames.add(file.getOriginalFilename());
            });

            message = "Uploaded the files successfully: " + fileNames;
            return ResponseEntity.status(200).body(new FileResponseMessage(message));

        // Catch any exceptions that occur during the file upload process
        } catch (Exception e) {
            message = "Could not upload the files!" + e.getMessage();
            return ResponseEntity.status(500).body(new FileResponseMessage(message));
        }
    }

    @GetMapping("/file/{fileName:.+}")
    public ResponseEntity<Resource> getFileByName(@PathVariable String fileName) {
        Resource file = fileUploadService.getFileByName(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/all-files")
    public ResponseEntity<List<FileResponse>> getListFiles() {
        List<FileResponse> fileResponses = fileUploadService.loadAllFiles()
            .map(path -> {
                String filename = path.getFileName().toString();
                String url = MvcUriComponentsBuilder
                    .fromMethodName(FileUploadController.class,
                            "getFileByName",
                            path.getFileName().toString()).build().toString();

            return new FileResponse(filename, url);
        }).toList();

        return ResponseEntity.status(200).body(fileResponses);
    }
}
