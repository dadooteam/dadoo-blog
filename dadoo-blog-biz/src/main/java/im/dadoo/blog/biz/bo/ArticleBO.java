/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im.dadoo.blog.biz.bo;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import com.google.common.collect.Lists;
import im.dadoo.blog.biz.dao.ArticleDAO;
import im.dadoo.blog.biz.dao.TagArticleDAO;
import im.dadoo.blog.biz.dao.TagDAO;
import im.dadoo.blog.biz.dto.ArticleDTO;
import im.dadoo.blog.domain.Article;
import im.dadoo.blog.domain.Tag;
import im.dadoo.blog.domain.TagArticle;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

/**
 *
 * @author codekitten
 */
@Service
public class ArticleBO {

  @Resource
  private TagDAO tagDAO;

  @Resource
  private ArticleDAO articleDAO;

  @Resource
  private TagArticleDAO taDAO;

  public void insert(String title, String html, List<Long> tagIds) {
    String text = Jsoup.parse(html).text();
    Article article = new Article();
    article.setTitle(title);
    article.setHtml(html);
    article.setText(text);
    article.setGmtCreate(System.currentTimeMillis());
    article.setClick(0);
    long articleId = this.articleDAO.insert(article);

    if (articleId > 0L) {
      if (tagIds != null && !tagIds.isEmpty()) {
        for (long tagId : tagIds) {
          TagArticle ta = new TagArticle();
          ta.setTagId(tagId);
          ta.setArticleId(articleId);
          this.taDAO.insert(ta);
        }
      }
    }
  }

  public void updateById(long id, String title, String html, List<Long> tagIds) {
    checkArgument(id > 0L);
    String text = Jsoup.parse(html).text();
    Article article = this.articleDAO.findById(id);
    if (article != null) {
      article.setTitle(title);
      article.setHtml(html);
      article.setText(text);
      this.articleDAO.updateById(article);
      this.taDAO.deleteByArticleId(id);
      if (tagIds != null && !tagIds.isEmpty()) {
        for (long tagId : tagIds) {
          TagArticle ta = new TagArticle();
          ta.setArticleId(id);
          ta.setTagId(tagId);
          this.taDAO.insert(ta);
        }
      }
    }
  }

  public void click(long id) {
    checkArgument(id > 0L);
    this.articleDAO.updateClick(id);
  }

  public void deleteById(long id) {
    checkArgument(id > 0L);
    this.articleDAO.deleteById(id);
    this.taDAO.deleteByArticleId(id);
  }

  public ArticleDTO findById(long id) {
    checkArgument(id > 0L);
    Article article = this.articleDAO.findById(id);
    ArticleDTO result = this.toDTO(article);
    return result;
  }

  public ArticleDTO findPrevById(long id) {
    checkArgument(id > 0L);
    Article article = this.articleDAO.findPrevById(id);
    ArticleDTO result = this.toDTO(article);
    return result;
  }

  public ArticleDTO findNextById(long id) {
    checkArgument(id > 0L);
    Article article = this.articleDAO.findNextById(id);
    ArticleDTO result = this.toDTO(article);
    return result;
  }

  public Pair<ArticleDTO, ArticleDTO> findPrevAndNextById(long id) {
    return Pair.of(this.findPrevById(id), this.findNextById(id));
  }

  public List<ArticleDTO> page(int pagecount, int pagesize) {
    List<Article> articles = this.articleDAO.page(pagecount, pagesize);
    return this.toDTOs(articles);
  }

  public List<Article> listMostVisitedArticles(long limit) {
    checkArgument(limit > 0L);
    return this.articleDAO.listOrderByClickDesc(limit);
  }

  public List<ArticleDTO> pageByTagId(long tagId, int pagecount, int pagesize) {
    List<Article> articles = this.articleDAO.pageByTagId(tagId, pagecount, pagesize);
    return this.toDTOs(articles);
  }

  public long size() {
    return this.articleDAO.size();
  }

  public long sizeByTagId(long tagId) {
    checkArgument(tagId > 0L);
    return this.taDAO.sizeByTagId(tagId);
  }

  public int maxPagecount(int pagesize) {
    checkArgument(pagesize > 0);
    Long size = this.articleDAO.size();
    return 1 + (size.intValue() - 1) / pagesize;
  }

  public int maxPagecountByTagId(long tagId, int pagesize) {
    checkArgument(tagId > 0L);
    checkArgument(pagesize > 0);
    Long size = this.taDAO.sizeByTagId(tagId);
    return 1 + (size.intValue() - 1) / pagesize;
  }

  public ArticleDTO toDTO(Article article) {
    ArticleDTO result = null;
    if (article != null) {
      result = new ArticleDTO();
      result.setArticle(article);
      List<Tag> tags = this.tagDAO.listByArticleId(article.getId());
      result.setTags(tags);
    }
    return result;
  }

  public List<ArticleDTO> toDTOs(List<Article> articles) {
    List<ArticleDTO> result = Lists.newArrayList();
    if (articles != null && !articles.isEmpty()) {
      for (Article article : articles) {
        result.add(this.toDTO(article));
      }
    }
    return result;
  }
}
