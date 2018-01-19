package com.leap.base.util;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串格式化工具
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class StringUtil {

  private StringUtil() {
  }

  public static String format(String pattern, Object... arguments) {
    if (IsEmpty.object(arguments))
      return null;
    MessageFormat format = new MessageFormat(pattern);
    for (int i = 0; i < arguments.length; i++) {
      if (arguments[i] == null) {
        format.setFormat(i, null);
      }
    }
    return format.format(arguments);
  }

  /**
   * 快速判断字符串是否为手机号
   */
  public static boolean isMobileNO(String mobiles) {
    Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
    Matcher m = p.matcher(mobiles);
    return m.matches();
  }

  public static boolean isEmail(String email) {
    String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    Pattern p = Pattern.compile(str);
    Matcher m = p.matcher(email);

    return m.matches();
  }

  // 验证固话
  public static boolean isPhoneNumberValid(String phoneNumber) {
    String expression = "^(\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}$";
    Pattern pattern = Pattern.compile(expression);
    Matcher m = pattern.matcher(phoneNumber);
    return m.matches();

  }

  // 判断字符串是否为数字
  private static boolean isNumeric(String str) {
    Pattern pattern = Pattern.compile("[0-9]*");
    Matcher isNum = pattern.matcher(str);
    return isNum.matches();
  }

  // 判断字符串是否为日期格式
  public static boolean isDate(String strDate) {
    Pattern pattern = Pattern.compile(
        "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
    Matcher m = pattern.matcher(strDate);
    if (m.matches()) {
      return true;
    } else {
      return false;
    }
  }
}
