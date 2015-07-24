/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im.dadoo.blog.web.ao;

import im.dadoo.blog.biz.bo.FileBO;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author codekitten
 */
@Component
public class FileAO {

  private static final Logger logger = LoggerFactory.getLogger(FileAO.class);
  
  @Resource
  private FileBO fileBO;
  
  public String save(MultipartFile file) throws Exception {
    String result = null;
    if (file != null && !file.isEmpty()) {
      byte[] bytes = file.getBytes();
      String tempName = DigestUtils.md5DigestAsHex(bytes);
      File tempFile = new File(tempName);
      file.transferTo(tempFile);
      result = this.fileBO.save(tempFile);
      tempFile.delete();
    } else {
      logger.warn("file is null or empty");
    }
    return result;
  }
}
