package com.google.code.hackaccess.sample;

import junit.framework.TestCase;

public class PDFConvTest extends TestCase {

	public void testMainS1() {
		String[] args = {"TEXT"};
		PDFConv.main(args);
	}
/*
	public void testMainS2() {
		String[] args = {"HTML"};
		PDFConv.main(args);
	}

	public void testMainS3() {
		String[] args = {"JSON"};
		PDFConv.main(args);
	}

	public void testMainS11() {
		String[] args = {"TEXT", "-url", "http://www.mext.go.jp/component/a_menu/other/detail/__icsFiles/afieldfile/2011/05/14/1305872_051413.pdf"};
		PDFConv.main(args);
	}

	public void testMainS12() {
		String[] args = {"HTML", "-url", "http://www.mext.go.jp/component/a_menu/other/detail/__icsFiles/afieldfile/2011/05/14/1305872_051413.pdf"};
		PDFConv.main(args);
	}

	public void testMainS13() {
		String[] args = {"JSON", "-url", "http://www.mext.go.jp/component/a_menu/other/detail/__icsFiles/afieldfile/2011/05/14/1305872_051413.pdf"};
		PDFConv.main(args);
	}
*/
}
