# 概要 #
ページ抽出が禁止されたPDFの抽出例についてまとめる。

## 元となるPDF ##
以下のPDFを元に抽出を行う。
このPDFは、文部省提供であるのに、"owner password"によりページ抽出が禁止されている。

  * 福島第一原子力発電所の20Km以遠のモニタリング結果 平成23年5月10日（火曜日）19時00分時点
> http://www.mext.go.jp/component/a_menu/other/detail/__icsFiles/afieldfile/2011/05/10/1305872_051019.pdf
  * PDFリンク元 : 文部省提供の『原発周辺の固定測定点における空間線量率の測定結果』
> http://www.mext.go.jp/a_menu/saigaijohou/syousai/1304001.htm

| **ページ** | **内容** |
|:--------|:-------|
| 1       | モニタリング結果表(1) |
| 2       | モニタリング結果表(2) |
| 3       | モニタリング結果表(3) |
| 4       | モニタリング結果図 |
| 5       | <<日常生活と放射線>> |

## 抽出結果 ##
### 1. Adobe Acrobatによる抽出 ###
#### 1A. Adobe Acrobatによるコピー＆ペースト ####
> 文書のすべてを選択してコピーし、クリップボードの内容をファイルに書き出す。

> 抽出結果：文字列の順序が正しく抽出できず、読むことができない。
  1. [テキスト形式](http://code.google.com/p/hack-access/downloads/detail?name=1305872_051019.clipboard.txt)
（ただし、結果図のページの抽出は可能）

#### 1B. Adobe Acrobatによる「テキスト（アクセシブル）」形式で抽出 ####
> ページ抽出が禁止されているため、「テキスト（アクセシブル）」形式以外の抽出はできない。
> （テキスト（プレーン）形式を出力するためにはパスワードが要求される）
> 抽出結果：表など、大半のデータが抽出されない。
```
［6,900ﾏｲｸﾛｼｰﾍﾞﾙﾄ/回］ 


［1,000ﾏｲｸﾛｼｰﾍﾞﾙﾄ/年］ 

　　(以下、略)
```

### 2. PDFBoxライブラリを使用した抽出 ###
> Apache PDFBox - Java PDF Library http://pdfbox.apache.org/ を使用した抽出。PDFBoxは、検索エンジンLucene/Solrでも使用されており、実績がある。コマンドラインからも使用ができる。

> 抽出結果としては変わらないが、Javaプログラムから扱った場合のサンプルコードを [Google Code](http://code.google.com/p/hack-access/source/browse/#svn%2Ftrunk%2Fsample) に置いておいた。(サンプルコードの方では、HTML出力時に数値文字列参照を文字に変換する処理を追加している)
> また、サンプルコードでは [Radiation Data in Japan](http://radiation.s3-website-us-east-1.amazonaws.com/)を作成された ymdmstk さんから提供されたソースを元にした独自形式でも出力されるようになっている。

> 抽出結果：全ての文字情報が抽出される。(4ページ目/5ページ目も含む)
    1. [テキスト形式](http://code.google.com/p/hack-access/downloads/detail?name=1305872_051019_PDFBox.txt)
    1. [HTML形式](http://code.google.com/p/hack-access/downloads/detail?name=1305872_051019_PDFBox.html)
    1. [独自形式 ](http://code.google.com/p/hack-access/downloads/detail?name=1305872_051019_PDFBox_json.txt)
（ただし、結果図のページ（4ページ目）は順序が狂っている）

### 3. 抽出制限を解除した後で抽出 ###
> http://www.pdfunlock.com/ を使用して抽出制限を外す。 http://hack-access.googlecode.com/files/1305872_051019.unlocked.pdf
> 制限を外されたPDFからは、テキストはもちろん、Adobe Acrobatを使用してXML 1.0, HTML 3.2, HTML 4.01, Postscript, WORD などとして抽出できる。

> 抽出結果(テキスト)：図中以外の文字情報が抽出される。
    1. [テキスト形式](http://code.google.com/p/hack-access/downloads/detail?name=1305872_051019.unlocked.txt)
    1. [HTML 4.01形式(画像つき)の圧縮ファイル](http://code.google.com/p/hack-access/downloads/detail?name=HTML%204.01.ZIP)
    1. [XML 1.0形式(画像つき)の圧縮ファイル](http://code.google.com/p/hack-access/downloads/detail?name=XML%201.0.ZIP)