package com.rental_listing_landlord.landlord_post.controller;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rental_listing_landlord.landlord_post.entity.PropertyPhotos;
import com.rental_listing_landlord.landlord_post.services.PropertyPhotoService;

@RestController
@RequestMapping("api/v1/propertyphoto")
public class PropertyPhotoController {

	private PropertyPhotoService propertyPhotoService;

	@Autowired
	public PropertyPhotoController(PropertyPhotoService propertyPhotoService) {
		super();
		this.propertyPhotoService = propertyPhotoService;
	}
	
	private Logger LOGGER = Logger.getLogger(getClass().getName());
	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file){
		try {

			Boolean uploadFile = propertyPhotoService.uploadFile(file);
			if (uploadFile) {
				return new ResponseEntity<>("upload success", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/upload-data")
	public ResponseEntity<?> uploadFileWithData(@RequestParam String product, @RequestParam MultipartFile file) {
		List<String> extension = Arrays.asList( "jpeg", "png", "jpg" );
		if (file.isEmpty()) {
			return new ResponseEntity<>("please file upload", HttpStatus.BAD_REQUEST);
		}else {
			String originalFilename = file.getOriginalFilename();
			String fileExtension = FilenameUtils.getExtension(originalFilename);
			boolean contains = extension.contains(fileExtension);
			if(!contains)
			{
				return new ResponseEntity<>("please upload jpeg/png/jpg image", HttpStatus.BAD_REQUEST);
			}
		}

		try {

			String fileName = propertyPhotoService.uploadFileWithinData(file);
			if (StringUtils.hasText(fileName)) {
				ObjectMapper objectMapper = new ObjectMapper();
				PropertyPhotos productObj = objectMapper.readValue(product, PropertyPhotos.class);
				productObj.setFirstImage(fileName);

				Boolean saveProduct = propertyPhotoService.savePropertyPhootos(productObj);
				if (saveProduct) {
					return new ResponseEntity<>("Product & Image uploaded", HttpStatus.CREATED);
				} else {
					return new ResponseEntity<>("file uploaded but Product Not saved",
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				return new ResponseEntity<>("file upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	public String getContentType(String fileName) {
		String extension = FilenameUtils.getExtension(fileName);

		switch (extension) {
		case "pdf":
			return "application/pdf";
		case "xlsx":
			return "application/vnd.openxmlformats-officedocument.spreadsheettml.sheet";
		case "txt":
			return "text/plan";
		case "png":
			return "image/png";
		case "jpeg":
			return "image/jpeg";
		default:
			return "application/octet-stream";
		}

	}

}
