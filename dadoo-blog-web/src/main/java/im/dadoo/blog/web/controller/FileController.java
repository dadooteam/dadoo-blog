/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im.dadoo.blog.web.controller;

import im.dadoo.blog.web.ao.FileAO;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author codekitten
 */
@Controller
public class FileController {
  
  private static final Logger logger = LoggerFactory.getLogger(FileController.class);
  
  private static final Logger elogger = LoggerFactory.getLogger(Exception.class);
  
  @Resource
  private FileAO fileAO;
  
  private static final String CKEDITOR_CALLBACK
          = "<script type='text/javascript'>"
          + "window.parent.CKEDITOR.tools.callFunction(%d, '%s', '%s');"
          + "</script>";
  
  @RequestMapping(value = "/api/upload", method = RequestMethod.POST)
  @ResponseBody
  public String uploadImage(HttpSession session, @RequestParam Integer CKEditorFuncNum,
          @RequestParam MultipartFile upload) {
    String result = null;
    try {
      String path = this.fileAO.save(upload);
      result = String.format(CKEDITOR_CALLBACK, CKEditorFuncNum, path, "upload success");
    } catch (Exception e) {
      logger.error(e.getLocalizedMessage());
      elogger.error("uploadImage error", e);
    }
    return result;
  }
}
