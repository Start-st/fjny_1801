package org.sxj.service;

import org.springframework.web.multipart.MultipartFile;
import org.sxj.utils.PictureResult;

public interface PictureUploadService {
	public PictureResult pictureUpload(MultipartFile uploadFile);
}