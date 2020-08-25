package com.crash.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.crash.service.exceptions.FileException;

@Service
public class ImageService {

	//Adicional. Converte png para jpg
	
	public BufferedImage getJpgImageFromFile(MultipartFile multiParFile) {
		String ext = FilenameUtils.getExtension(multiParFile.getOriginalFilename());
		if(!"png".equals(ext) && !"jpg".equals(ext)) {
			throw new FileException("Somente arquivos png e jpg são aceitos");
		}
		
		try {
			BufferedImage img = ImageIO.read(multiParFile.getInputStream());
			if("png".equals(ext)) {
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Erro ao ler o arquivo");
		}
		
	}

	private BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}
	
	
	public InputStream getInputStream(BufferedImage img, String extension) {
		
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (IOException e) {
			throw new FileException("Erro ao ler o arquivo");
		}
		
	}
	

}
