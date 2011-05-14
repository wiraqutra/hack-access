package com.google.code.hackaccess.sample.output;


/**
 * 出力インターフェース
 *
 */
public interface Output {

	/**
	 * 指定されたPDFの内容を編集して、出力する
	 * 
	 * @param url 出力対象のPDFのURL
	 * @throws Exception 
	 */
	public void output(String url) throws Exception;
	
}
