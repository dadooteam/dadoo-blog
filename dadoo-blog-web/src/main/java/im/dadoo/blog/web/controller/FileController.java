/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.web.controller;

import im.dadoo.blog.web.service.FileService;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
  
  @Resource
  private FileService fileService;
	
  private static final String CKEDITOR_CALLBACK = 
          "<script type='text/javascript'>"
          + "window.parent.CKEDITOR.tools.callFunction(%d, '%s', '%s');"
          + "</script>";
  
	@RequestMapping(value = "/api/upload", method = RequestMethod.POST)
	@ResponseBody
	public String uploadImage(@RequestParam Integer CKEditorFuncNum, 
          @RequestParam MultipartFile upload, HttpSession session) 
          throws IllegalStateException, IOException {
    String root = session.getServletContext().getRealPath("/");
    String path = this.fileService.save(upload, root);
		String result = String.format(CKEDITOR_CALLBACK, CKEditorFuncNum, path, "upload success");
    return result;
  }
}
