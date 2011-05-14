package com.google.code.hackaccess.sample.output;

import java.io.FileInputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFText2HTML;
import org.apache.pdfbox.util.PDFTextStripper;

import com.google.code.hackaccess.sample.util.StringUtil;

/**
 * HTML形式で出力します
 *
 */
public class HtmlOutput implements Output {

	private final String charSet;

	/**
	 * コンストラクタ
	 * 
	 * @param charSet 出力するHTMLの文字コード
	 */
	public HtmlOutput(final String charSet) {
		this.charSet = charSet;
	}
	
	/**
	 * 指定されたPDFの内容を、HTML形式で出力する
	 * 
	 * @param url 出力対象のPDFのURL
	 * @throws Exception 
	 */
	public void output(String url) throws Exception {
		FileInputStream pdfStream = null;
		PDDocument document = null;

		try {
			document = PDDocument.load(new URL(url));

			PDFTextStripper stripper = new PDFText2HTML( this.charSet );
			stripper.setSortByPosition( true );

			System.out.println(StringUtil.decodeNCR(stripper.getText(document)));
		} catch (Exception e) {
			throw e;
		} finally {
			if (pdfStream != null) {
				pdfStream.close();
			}
			if (document != null) {
				document.close();
			}
		}
	}

}
