# はじめに #
> PDFをテキスト化するツールおよびサービスについて、まとめます。
> 各ツールやサービスは、それぞれのサイトや同梱物の説明書きをよくお読みの上で、お使いください。

# セキュリティのかかったPDFについて #
> PDFには、以下の2種類のパスワードでセキュリティをかけることができます。
    * ページの抽出などの操作制限を行う"owner password"
    * ファイルをオープンする制限を行う"user password"

> そして、[文部省提供の『原発周辺の固定測定点における空間線量率の測定結果』](http://www.mext.go.jp/a_menu/saigaijohou/syousai/1304001.htm) は、**言語道断\*なことに、"owner password"により抽出が禁止されたPDFなのです。**

> 「アクセシビリティのための抽出」は許可されてはいますが、Adobe Acrobatで「テキスト（アクセシブル）」形式で抽出しても、肝心の表データがごっそりと抜け落ちます。

> "owner password"で抽出が禁止されたPDFでも、ツールによっては表データもテキスト抽出できます。ただし、ベタなテキストとして抽出できるだけであり、目次や表構造などの構造的な情報について抜き出す方法は、現状のところ見つかっていません。

> 基本的に、PDFに記録されている構造的な情報を利用するためには、データの提供元にセキュリティを外してもらう必要があります。

# PDFを抽出できるツール、サービスの一覧 #
> ※抽出禁止PDFとは、「アクセシビリティのための抽出」は許可されているが、「ページの抽出」が禁止されているものとします。

  * Acrobat Reader
    * 概要 : Adobeが無償配布しているPDFのビューワー
    * 環境 : Windows
    * ライセンス : -
    * テキスト以外の抽出形式 : なし
    * 抽出禁止PDFの抽出 : ×

  * Acrobat Acrobat
    * 概要 : Adobeの商用ソフト
    * 環境 : Windows
    * ライセンス : -
    * テキスト以外の抽出形式 : XML 1.0, HTML 3.2, HTML 4.01, Postscript, WORD など
    * 抽出禁止PDFの抽出 : ×

  * Foxit J-Reader http://www.foxitsoftware.com/
    * 概要 : 無料ソフト
    * 環境 : Windows
    * ライセンス : -
    * テキスト以外の抽出形式 : なし
    * 抽出禁止PDFの抽出 : ベタテキストとして抽出可能

  * xdoc2txt http://www31.ocn.ne.jp/~h_ishida/xdoc2txt.html
    * 概要 : 無料ソフト
    * 環境 : Windows
    * ライセンス : 非営利の場合、フリーで使用
    * テキスト以外の抽出形式 : なし
    * 抽出禁止PDFの抽出 : ベタテキストとして抽出可能

  * PDFlib TET http://pdflib.jp/product/tet/tet.html
    * 概要 : PDFからテキスト、画像、メタデータを抽出するツール
    * 環境 : Windows/Mac/Linuxなど
    * ライセンス : 商用（総代理店テックスタイルより震災関連用途に限り特別提供可能）
    * ライセンスキーを設定しない場合でも10ページ、1MBまでのPDFファイルなら扱うことができる。（ただし評価の目的）
    * テキスト以外の抽出形式 : XML
    * コマンドライン版が同梱されており、プログラミングしなくてもある程度の抽出は可能（マニュアル参照）
    * 日本語マニュアル http://pdflib.jp/product/download/tet.html#tet-manuals
    * 抽出禁止PDFの抽出 : shrug オプションにより可能。ただし権利者の意思を尊重した利用に限る。

  * Google Wireless Transcoder (GWT) http://www.google.co.jp/gwt/n
    * 概要 : 指定したURLのサイトデータを、携帯電話で見れるように変換するGoogleのサービス。PDFの内容もHTMLに変換される。
    * 環境 : Webサービス
    * ライセンス : -
    * テキスト以外の抽出形式 : HTML(非構造)
    * 抽出禁止PDFの抽出 : ベタテキストとして抽出可能
    * 備考 : 表の順序がおかしくなる、抽出できないものもある

  * PDF to Excel http://www.pdftoexcelonline.com/
    * 概要 : 指定したPDFの内容を、Execelファイルに変換する。変換結果はメールで送られてくる
    * 環境 : Webサービス
    * ライセンス : -
    * テキスト以外の抽出形式 : Excel
    * 抽出禁止PDFの抽出 : 〇

  * Poppler http://poppler.freedesktop.org/
    * 概要 : PDFレンダリングライブラリ
    * 環境 : Windows（Popplerの実装である、Sumatra PDF http://blog.kowalczyk.info/software/sumatrapdf/free-pdf-reader.html v0.8.1を利用して確認）
    * ライセンス : GNU General Public License
    * テキスト以外の抽出形式 : なし
    * 抽出禁止PDFの抽出 : ベタテキストとして抽出可能
    * 備考 : 表の順序がおかしくなる

  * MuPDF http://mupdf.com/
    * 概要 : PDFレンダリングライブラリ
    * 環境 : Windows（Popplerの実装である、Sumatra PDF http://blog.kowalczyk.info/software/sumatrapdf/free-pdf-reader.html v0.8.1を利用して確認）
    * ライセンス : GNU General Public License
    * テキスト以外の抽出形式 : 画像をPNG
    * 抽出禁止PDFの抽出 : ベタテキストとして抽出可能
    * 備考 : 表の順序がおかしくなる

  * PDFBox http://pdfbox.apache.org/
    * 概要 : PDFの作成や操作を行えるOSSのJavaライブラリ
    * 環境 : JVM
    * ライセンス : Apache License, Version 2.0
    * テキスト以外の抽出形式 : HTML(非構造)、1ページ単位でJPEGファイルとしても抽出可能
    * 抽出禁止PDFの抽出 : ベタテキストとして抽出可能

# PDFのセキュリティを外す方法について #

  * PDFUnlock! - Unlock secured PDF files online for free. [http://www.pdfunlock.com/ ](.md)
    * 概要 : Webサービスを使用してセキュリティを外す
    * 環境 : Webサービス
    * 結果 : 成功

  * Ghostscript/GSview http://auemath.aichi-edu.ac.jp/~khotta/ghost/
    * 概要 : GSViewでPDFを開き、File→Convertでpdfwriteを選択して出力する
    * 環境 : Windows
    * 結果 : PDFとして見掛け上は、ほぼ変わらない。しかし、構造的な情報が抜け落ちてしまうため、テキストとして出力したときに表内の順序がバラバラになる。
    * 備考 : プリンタドライバとして登録されるPDF作成ソフトも同様の結果になると思われます。

> ※ あとは、辞書ファイルや総当たりでパスワードを解析するツールである PDFCrack http://pdfcrack.sourceforge.net/、PDF Password Recovery Tools があります。最初の記述者(kamatamadai)の個人責任において、PDFCrackを30分ほど走らせてみましたが、パスワードは破れませんでした。

> ※ Freeware PDF Unlocker  [使用方法の参考](http://wxy117.seesaa.net/article/182841379.html) は、64bit版がなくて試せていません。