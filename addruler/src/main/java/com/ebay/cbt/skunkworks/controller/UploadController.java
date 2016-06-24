package com.ebay.cbt.skunkworks.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="/")
public class UploadController {
	private static int offsetw = 10;
	private static int offseth = 10;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public Map<String, String> index() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("greeting", "hello world!");
		return map;
	}
	
	/**
	 * Upload single file
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(@RequestParam("name") String name,
			@RequestParam("length") String length, @RequestParam("width") String width, @RequestParam("file") MultipartFile file) {

		if (!file.isEmpty()) {
			try {
				//byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				String serverFile = dir.getAbsolutePath()
						+ File.separator + name + ".jpg";
				File imgFile = new File(file.getOriginalFilename());
				file.transferTo(imgFile);
				BufferedImage img = ImageIO.read(imgFile);
				offsetw = (int)(img.getWidth()*0.03);
				offseth = (int)(img.getHeight()*0.1);
				Graphics2D graph = img.createGraphics();
				graph.setColor(Color.BLACK);
				graph.drawLine(offseth, offsetw, img.getWidth()/2-offseth, offsetw);
				graph.drawLine(img.getWidth()/2+offseth,offsetw, img.getWidth()-offseth, offsetw);
				graph.drawLine(offseth, offsetw, offseth, img.getHeight()/2-offsetw);
				graph.drawLine(offseth, img.getHeight()/2+offsetw, offseth, img.getHeight()-offsetw);
				graph.setColor(Color.BLACK);
		        graph.drawString(length, img.getWidth()/2, offsetw);
		        graph.setStroke(new BasicStroke(2));
		        graph.drawString(width, offseth, img.getHeight()/2);
		        graph.dispose();
		        ImageIO.write(img, "jpg", new File(serverFile));			
				return "You successfully uploaded file=" + name;
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name
					+ " because the file was empty.";
		}
	}
	
	@RequestMapping(value = "/image/{imageName}", method = RequestMethod.GET, produces=MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {
        File serverFile = new File(System.getProperty("catalina.home") + File.separator + "tmpFiles" + File.separator + imageName + ".jpg");

        return Files.readAllBytes(serverFile.toPath());
    }

}
