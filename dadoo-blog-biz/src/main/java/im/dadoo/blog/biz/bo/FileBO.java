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
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import im.dadoo.blog.domain.QiniuFile;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author codekitten
 */
public class FileBO {

  private final Auth auth;

  private final String bucket;

  private final UploadManager uploadManager;

  private final BucketManager bucketManager;

  private final String project;

  private final String root;

  public FileBO(String accessKey, String secretKey, String bucket, String project, String root) {
    this.auth = Auth.create(accessKey, secretKey);
    this.bucket = bucket;
    this.project = project;
    this.root = root;
    this.uploadManager = new UploadManager();
    this.bucketManager = new BucketManager(this.auth);
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

  public void delete(String key) throws Exception {
    checkArgument(StringUtils.isNotBlank(key));
    this.bucketManager.delete(this.bucket, key);
  }
  
  public List<QiniuFile> page(int pagecount, int pagesize) {
    List<QiniuFile> result = new ArrayList<>();
    BucketManager.FileListIterator iterator
            = bucketManager.createFileListIterator(this.bucket, this.project, pagesize, null);
    for (int i = 1; i < pagecount; i++) {
      if (iterator.hasNext()) {
        iterator.next();
      } else {
        break;
      }
    }
    if (iterator.hasNext()) {
      FileInfo[] infos = (FileInfo[]) iterator.next();
      for (FileInfo info : infos) {
        if (info != null) {
          QiniuFile file = new QiniuFile();
          file.setKey(info.key);
          file.setUrl(this.root + info.key);
          result.add(file);
        }
      }
    }
    return result;
  }
}
