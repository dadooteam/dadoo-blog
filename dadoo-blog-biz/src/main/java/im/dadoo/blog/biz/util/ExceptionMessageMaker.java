/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.biz.util;

/**
 *
 * @author shuwen.zsw
 */
public final class ExceptionMessageMaker {
  
  public static final String NULL_POINTER_TPL = "%s 不能为空";
  
  public static final String GT_TPL = "%s 必须大于 %s,当前为 %s";
  
  public static final String GE_TPL = "%s 必须大于等于 %s, 当前为 %s";
  
  public static final String LT_TPL = "%s 必须小于 %s,当前为 %s";
  
  private ExceptionMessageMaker() {}
  
  public static final String makeNullPointerMessage(String name) {
    return String.format(NULL_POINTER_TPL, name);
  }
  
  public static final <T> String makeGTMessage(String name, T need, T real) {
    return String.format(GT_TPL, name, need, real);
  }
  
  public static final <T> String makeGEMessage(String name, T need, T real) {
    return String.format(GE_TPL, name, need, real);
  }
  
  public static final <T> String makeLTMessage(String name, T need, T real) {
    return String.format(LT_TPL, name, need, real);
  }
  
}
