package com.google.code.hackaccess.sample;

import java.io.IOException;

/**
 * 抽出対象のPDFを指定します
 *
 */
public class UrlOption {
	// TODO 以下の定数を、設定ファイルから読み込むように変更する
	static String SOURCE_URL = "http://www.mext.go.jp/a_menu/saigaijohou/syousai/1304001.htm";
	static String CHAR_SET = "UTF-8";
	static String XPATH_EXPRESSION = "//a/@href";
	static String TARGET_MATCH_EXPRESSION = "/component/a_menu/other/detail/__icsFiles/afieldfile/";
	static String PREFIX_URL = "http://www.mext.go.jp";

	/**
	 * 抽出対象URLを返す
	 * 
	 * @param args 第2引数以降のコマンドライン引数
	 * @return 
	 * @throws Exception 
	 */
	
	public static String getUrl(String[] args) throws Exception {
		String url = null;
	
		if (args != null){
			url = getOptionUrl(args);
		}
		
		if (url == null){
			url = getFukushimaUrl();
		}
	
		return url;
	}
	
	/**
	 * コマンドラインの引数で指定された抽出対象URLを返す
	 * 
	 * @param args 第2引数以降のコマンドライン引数
	 * @return 抽出対象URL
	 */
	private static String getOptionUrl(String[] args) throws Exception {
		String url = null;

		for( int i=0; i<args.length; i++ )
		{
			if( args[i].equals( "-url" ) )
			{
				i++;
				if( i >= args.length )
				{
					throw new IOException("URL is not specified");
				}
				url = args[i];
			}
		}
		
		return url;
	}


	/**
	 * 福島第1及び第2原子力発電所周辺のモニタリングカーを用いた固定測定点における空間線量率の測定結果の最新のURLを返す
	 * 
	 * @param args コマンドラインの引数(<command>より後ろ)
	 * @return 抽出対象URL
	 * @throws Exception 
	 */
	private static String getFukushimaUrl() throws Exception {
		String srcUrl = SOURCE_URL;
		String charSet = CHAR_SET;
		String xpathExpression = XPATH_EXPRESSION;
		String targetMatchExpression = TARGET_MATCH_EXPRESSION;
		String prefixUrl = PREFIX_URL;
		
		UrlResolver resolver = new UrlResolver(srcUrl, charSet, xpathExpression, targetMatchExpression, prefixUrl);
		String url = resolver.getLatestTargetUrl();
		
		return url;
	}
}
