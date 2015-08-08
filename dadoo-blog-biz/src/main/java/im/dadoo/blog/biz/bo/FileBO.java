/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im.dadoo.blog.biz.bo;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import java.io.File;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author codekitten
 */
public class FileBO {

  private final Auth auth;

  private final String bucket;

  private final UploadManager uploadManager;

  private final String project;

  private final String root;

  public FileBO(String accessKey, String secretKey, String bucket, String project, String root) {
    this.auth = Auth.create(accessKey, secretKey);
    this.bucket = bucket;
    this.project = project;
    this.root = root;
    this.uploadManager = new UploadManager();
  }

  public String save(File src) throws Exception {
    String result = null;
    try {
      checkNotNull(src);
      checkArgument(src.length() > 0L);
      //get upload token
      String token = this.auth.uploadToken(this.bucket);
      checkArgument(StringUtils.isNotBlank(token), "upload token is blank");
      String key = String.format("%s/%s", this.project, src.getName());
      //upload
      Response response = this.uploadManager.put(src, key, token);
      System.out.println(response.bodyString());
      result = this.root + key;
    } catch (QiniuException e) {
      throw e;
    } catch (Exception e) {
      throw e;
    }
    return result;
  }
}
