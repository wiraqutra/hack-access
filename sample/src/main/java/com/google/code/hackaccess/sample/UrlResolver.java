package com.google.code.hackaccess.sample;

import java.net.URL;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPather;

/**
 * 指定されたURLのサイトから、抽出対象のPDFのURLを取得します
 *
 */
public class UrlResolver {

	private final String srcUrl;
	private final String charSet;
	private final String xpathExpression;
	private final String targetMatchExpression;
	private final String prefixUrl;
	
	public UrlResolver(final String srcUrl,
						final String charSet,
						final String xpathExpression,
						final String targetMatchExpression,
						final String prefixUrl) {
		this.srcUrl = srcUrl;
		this.charSet = charSet;
		this.xpathExpression = xpathExpression;
		this.targetMatchExpression = targetMatchExpression;
		this.prefixUrl = prefixUrl;
		
	}

	public String getLatestTargetUrl() throws Exception {
		String targetUrl = null;
		CleanerProperties props = new CleanerProperties();
		 
		// set some properties to non-default values
		props.setTranslateSpecialEntities(true);
		props.setTransResCharsToNCR(true);
		props.setOmitComments(true);

		// do parsing
		TagNode tagNode;
			tagNode = new HtmlCleaner(props).clean( new URL(srcUrl), charSet );
		
		XPather xpath = new XPather(xpathExpression);
		Object[] results = null;
		results = xpath.evaluateAgainstNode(tagNode);
		
		for(Object result: results){
			String url = (String)result;
			if(url.indexOf(targetMatchExpression)==0){
				targetUrl = prefixUrl + result.toString().trim();
				break;
			}
		}

		return targetUrl;
	}
}

