package com.example.nupsisign.controller;

import com.example.nupsisign.FileUpload.FileResponse;
import com.example.nupsisign.FileUpload.FileResponseMessage;
import com.example.nupsisign.FileUpload.IFileUploadService;
import com.example.nupsisign.Model.Dbset.Arquivos;
import com.example.nupsisign.Model.Dbset.Usuario;
import com.example.nupsisign.Model.Repositorio.ArquivoRepositorio;
import com.example.nupsisign.Model.Repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/files")
public class FileUploadController {

    private final IFileUploadService fileUploadService;

    @Autowired
    private ArquivoRepositorio arquivoRepositorio;

    // This method uploads the files to the uploads directory
    @PostMapping("/upload-files")
    public ResponseEntity<FileResponseMessage> uploadFiles(@RequestParam("file") MultipartFile[] files, @RequestParam("usuario") Usuario usuario) {

        String message = null;

        if (files == null) {
            message = "Please select a file!";
            return ResponseEntity.status(400).body(new FileResponseMessage(message));
        }

        if (usuario == null) {
            message = "Please select a user!";
            return ResponseEntity.status(400).body(new FileResponseMessage(message));
        }

        try {
            List<String> fileNames = new ArrayList<>();
            Arquivos arquivo = new Arquivos();

            String[] nomeArquivos = {"certidãoDeNascimento", "identidade", "certidãoDeCasamento", "escrituraDeDivorcio", "cpf", "endereço"};

            for (int i = 0; i < files.length; i++) {
                if (files[i] != null) {
                    fileUploadService.save(files[i], usuario.getIdUsuario() + "_" + nomeArquivos[i] + ".pdf");
                    fileNames.add(files[i].getOriginalFilename());
                    switch (i) {
                        case 0:
                            arquivo.setCertidãoDeNascimento(files[i].getOriginalFilename());
                            break;
                        case 1:
                            arquivo.setIdentidade(files[i].getOriginalFilename());
                            break;
                        case 2:
                            arquivo.setCertidãoDeCasamento(files[i].getOriginalFilename());
                            break;
                        case 3:
                            arquivo.setEscrituraDeDivorcio(files[i].getOriginalFilename());
                            break;
                        case 4:
                            arquivo.setCpf(files[i].getOriginalFilename());
                            break;
                        case 5:
                            arquivo.setEndereço(files[i].getOriginalFilename());
                            break;
                    }
                }
            }
            arquivo.setUsuario(usuario);
            this.arquivoRepositorio.save(arquivo);

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
