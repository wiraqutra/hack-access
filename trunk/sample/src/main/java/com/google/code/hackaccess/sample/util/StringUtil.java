package com.google.code.hackaccess.sample.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文字列操作ユーティリティクラス
 *
 */
public class StringUtil {

	/**
	 * 数値文字参照(Numeric character reference)を、元の文字に変換して返します.
	 * 
	 * @param text 変換前の数値文字参照を含む文字列
	 * @return 変換後の文字列
	 */
	public static String decodeNCR(String text) {
		Pattern pattern = Pattern.compile("&#(\\d+);|&#([\\da-fA-F]+);");
		Matcher matcher = pattern.matcher(text);
		StringBuffer sb = new StringBuffer();
		Character buf;

		while(matcher.find()){
			if(matcher.group(1) != null){
				buf = new Character(
						  (char)Integer.parseInt(matcher.group(1)));
			}else{
				buf = new Character(
						  (char)Integer.parseInt(matcher.group(2), 16));
			}
			matcher.appendReplacement(sb, buf.toString());
		}

		matcher.appendTail(sb);
		
		return sb.toString();
	}

	/**
	 * 全角数字を半角数字に変換します
	 * 
	 * @param s 全角数字
	 * @return 半角数字
	 */
	public static String zenkakuNumToHankaku(final String s) {
		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if (c >= '０' && c <= '９') {
				sb.setCharAt(i, (char)(c - '０' + '0'));
			}
			if (c == '．'){
				sb.setCharAt(i, '.');
			}
		}
		return sb.toString();
	}
}
