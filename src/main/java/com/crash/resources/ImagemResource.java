package com.crash.resources;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crash.domain.Imagem;
import com.crash.service.ImagemService;


@RestController
@RequestMapping(value = "/imagem")
public class ImagemResource {

	@Autowired
	private ImagemService service;

	@PostMapping
	public @ResponseBody ResponseEntity<Imagem> uploadImagem(@RequestParam(value = "fotos") MultipartFile fotos)
			throws IOException {
		Imagem imagem = new Imagem();
		imagem.setFotos(fotos.getBytes());
		// No angular seleciono um arquivo e seto no PaginasEntity.
		service.insert(imagem);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<byte[]> findById( @PathVariable Integer id) {	
		Imagem img = service.findById(id);
		byte[] buffer = img.getFotos();
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(buffer);
	}

}
