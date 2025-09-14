package com.rental_listing_landlord.landlord_post.servicesimpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.rental_listing_landlord.landlord_post.entity.PropertyPhotos;
import com.rental_listing_landlord.landlord_post.entity.PropertyPosting;
import com.rental_listing_landlord.landlord_post.repository.PropertyPhotosRepository;
import com.rental_listing_landlord.landlord_post.services.PropertyPhotoService;
import com.rental_listing_landlord.landlord_post.services.PropertyPostServices;

import io.github.resilience4j.retry.annotation.Retry;
@Service
public class PropertyPhotosServiceImpl  implements PropertyPhotoService{

	@Value("${file.upload.path}")
	private String uploadPath;

	private Logger LOGGER = Logger.getLogger(getClass().getName());
	private PropertyPhotosRepository propertyPhotosRepository;
	@Autowired
	public PropertyPhotosServiceImpl( PropertyPhotosRepository propertyPhotosRepository) {
		super();
		this.propertyPhotosRepository = propertyPhotosRepository;
	}

	@Transactional
	@Retry(
			name="${spring.application.name}",
			fallbackMethod = ""
			)
	@Override
	public Boolean savePropertyPhootos(PropertyPhotos propertyPhotos) {
		PropertyPhotos savedPropertyPhotos = propertyPhotosRepository.save(propertyPhotos);
		LOGGER.info("Save photos is being executed successfully");
		if(!ObjectUtils.isEmpty(savedPropertyPhotos))
			return true;
		return false;
	}

	@Override
	@Transactional
	@Retry(
			name="${spring.application.name}",
			fallbackMethod = ""
			)
	public Boolean uploadFile(MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		
		File saveFile = new File(uploadPath);
		if(!saveFile.exists()) {
			saveFile.mkdir();
		}
		String storePath = uploadPath.concat(fileName);
		long upload = Files.copy(file.getInputStream(), Paths.get(storePath));
		if(upload!=0) {
			LOGGER.info("Upload File is being executed successfully");
			return true;
		}
		LOGGER.warning("OOps Some error is being encoutered in uploading of files");
		return false;
	}

	@Override
	public String uploadFileWithinData(MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		File saveFile = new File(uploadPath);
		String randomString = UUID.randomUUID().toString();
		String removeExtension = FilenameUtils.removeExtension(fileName);
		String extension = FilenameUtils.getExtension(fileName);
		fileName = removeExtension+"_"+randomString+"."+extension;
		
		if(!saveFile.exists()) {
			saveFile.mkdir();
		}
		String storePath = uploadPath.concat(fileName);
		long upload = Files.copy(file.getInputStream(), Paths.get(storePath));
		if(upload!=0) {
			LOGGER.info("Upload File is being executed Successfully");
			return fileName;
		}
		LOGGER.warning("OOps some error is being encountered in uploading of the files with data");
		return null;
	}
}
