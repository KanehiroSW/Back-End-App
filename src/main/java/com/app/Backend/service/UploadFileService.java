package com.app.Backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.*;

@Service
public class UploadFileService {
    private String folder="images//";

    public String saveImageProducto(MultipartFile file) throws IOException {
        if(!file.isEmpty()) {
            byte [] bytes = file.getBytes();
            Path path = Paths.get(folder+file.getOriginalFilename());
            Files.write(path, bytes);
            return file.getOriginalFilename();
        }
        return "default.jpg";
    }

    public String saveImageTienda(MultipartFile file) throws IOException {
        if(!file.isEmpty()) {
            byte [] bytes = file.getBytes();
            Path path = Paths.get(folder+file.getOriginalFilename());
            Files.write(path, bytes);
            return file.getOriginalFilename();
        }
        return "default.jpg";
    }

    public void deleteImageProducto(String nombre) {
        String ruta="images//";
        File file = new File(ruta+nombre);
        file.delete();
    }

    public void deleteImageTienda(String nombre) {
        String ruta="images//";
        File file = new File(ruta+nombre);
        file.delete();
    }
}
