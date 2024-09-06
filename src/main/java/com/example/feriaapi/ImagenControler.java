package com.example.feriaapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImagenControler {
    @Autowired
    private ImagenRepository  imageRepository;

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return new ResponseEntity<>("Hola Mundo", HttpStatus.OK);
    }

    @GetMapping
    public List<Imagen> getAllProducts(){
        return imageRepository.findAll();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("imagen") MultipartFile file,
                                              @RequestParam("precio") int precio,
                                              @RequestParam("nombre") String nombre,
                                              @RequestParam("tipo") String tipo) {
        if (file.isEmpty() || !file.getContentType().startsWith("image/")) {
            return new ResponseEntity<>("Please upload a valid image file", HttpStatus.BAD_REQUEST);
        }

        Imagen img = new Imagen();
        img.setPrecio(precio);  // Asigna el precio
        img.setName(nombre);
        img.setTipo(tipo);


        try {
            img.setData(file.getBytes());
        } catch (IOException e) {
            return new ResponseEntity<>("Image upload failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            imageRepository.save(img);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save image: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Image uploaded successfully", HttpStatus.OK);
    }
}
