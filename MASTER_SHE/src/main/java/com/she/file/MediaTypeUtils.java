package com.she.file;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

public class MediaTypeUtils {
	private static Logger logger = LoggerFactory.getLogger(MediaTypeUtils.class);

	// abc.zip
	// abc.pdf,..
	public MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
		// application/pdf
		// application/xml
		// image/gif, ...
		String mineType = servletContext.getMimeType(fileName);
		try {
			MediaType mediaType = MediaType.parseMediaType(mineType);
			return mediaType;
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return MediaType.APPLICATION_OCTET_STREAM;
		}
		return null;
	}

}