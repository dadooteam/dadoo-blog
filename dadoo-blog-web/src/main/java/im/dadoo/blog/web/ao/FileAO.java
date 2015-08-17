/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im.dadoo.blog.web.ao;

import im.dadoo.blog.biz.bo.FileBO;
import im.dadoo.blog.domain.QiniuFile;
import java.io.File;
import java.util.List;
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
  
  public List<QiniuFile> page(int pagecount, int pagesize) {
    return this.fileBO.page(pagecount, pagesize);
  }
  
  public void delete(String key) throws Exception {
    this.fileBO.delete(key);
  }
}
