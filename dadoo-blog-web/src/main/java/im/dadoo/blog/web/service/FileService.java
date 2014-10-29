/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.web.service;

import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author codekitten
 */
@Service
public class FileService {

	private static final String ROOT_DIR = "\\WEB-INF\\static\\";
	private static final String FILE_ROOT_URL = "/static/";
	
	public String save(MultipartFile file, String rootPath) throws IllegalStateException, IOException {
		if (!file.isEmpty()) {
			String[] ts = file.getOriginalFilename().split("\\.");
			System.out.println(file.getOriginalFilename());
			System.out.println(ts.length);
			String suffix = ts[ts.length - 1];
			
			String filename = String.valueOf(System.currentTimeMillis()) + Math.random();
			filename = DigestUtils.md5DigestAsHex(filename.getBytes("UTF-8")) + "." + suffix;
			String localFilepath = rootPath + ROOT_DIR + filename;
			File localFile = new File(localFilepath);
			localFile.mkdirs();
			localFile.createNewFile();
			
			file.transferTo(localFile);
			System.out.println(localFilepath);
			return FILE_ROOT_URL + filename;
		}
		else {
			return null;
		}
	}
}
