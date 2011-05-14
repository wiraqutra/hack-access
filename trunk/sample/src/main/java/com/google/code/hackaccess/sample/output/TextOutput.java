package com.google.code.hackaccess.sample.output;

import java.io.FileInputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;


/**
 * プレーンテキスト形式で出力します
 *
 */
public class TextOutput implements Output {

	private final String charSet;

	/**
	 * コンストラクタ
	 * 
	 * @param charSet 出力するHTMLの文字コード
	 */
	public TextOutput(final String charSet) {
		this.charSet = charSet;
	}

	/**
	 * 指定されたPDFの内容を、プレーンテキスト形式で出力する
	 * 
	 * @param url 出力対象のPDFのURL
	 * @throws Exception 
	 */
	public void output(String url) throws Exception {
		FileInputStream pdfStream = null;
		PDDocument document = null;

		try {
			document = PDDocument.load(new URL(url));

			PDFTextStripper stripper = new PDFTextStripper(this.charSet);
			stripper.setSortByPosition( true );

			System.out.println(stripper.getText(document));
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
