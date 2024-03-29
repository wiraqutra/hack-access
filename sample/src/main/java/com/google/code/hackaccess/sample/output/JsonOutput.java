package com.google.code.hackaccess.sample.output;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.google.code.hackaccess.sample.util.StringUtil;

/**
 * JSONを出力します
 * PDF内のテキスト解析方法は、文部省提供の『原発周辺の固定測定点における空間線量率の測定結果』専用です
 *
 */
public class JsonOutput implements Output {
	private int areaSize = 110;
	private String[][] points = new String[areaSize][5];
	
	private String[][] tmppoints = {
			{"1","福島市杉妻町","Fukushima Sugituma","37.7490987","140.4667432"},
			{"2","福島市大波滝ノ入","Fukushima Onami","37.7585306","140.553572"},
			{"3","伊達市霊山町石田彦平","Date Ryozen Ishida Hikohei","37.759424","140.7301391"},
			{"4","伊達郡川俣町大字鶴沢字川端","Date Kawamata Turusawa","37.663122","140.5957082"},
			{"5","相馬市中野寺前","Soma Nakano","37.790975","140.927497"},
			{"6","南相馬市鹿島区西町","Minamisoma Kashima Nishi","37.7032212","140.9648426"},
			{"7","南相馬市鹿島区寺内本屋敷","Minamisoma Kashima Terauchi","37.7007408","140.9608736"},
			{"10","二本松市針道中島","Nihonmatsu Harimiti","37.6011144","140.5838917"},
			{"11","二本松市太田字下田","Nihonmatsu Ota","37.569784","140.5762523"},
			{"12","田村市船引町船引字小沢川代","Tamura Funahiki Funahiki Ozawakawasiro","37.4362001","140.5924433"},
			{"13","田村市常葉町西向屋形","Tamura Tokiwa Nishi Mukiyakata","37.4441","140.6180266"},
			{"14","田村市常葉町常葉内町","Tamura Tokiwa Tokiwanai","37.4400394","140.6461856"},
			{"15","田村市常葉町山根鹿島","Tamura Tokiwa Yamane Kashima","37.4512277","140.6774311"},
			{"20","田村市船引町新舘下","Tamura Funehiki Niitate","37.4925135","140.5822886"},
			{"21","田村市船引町上移","Tamura Funehiki Kamiutushi","37.5144233","140.6581236"},
			{"22","田村市船引町上移字後田","Tamura Funehiki Kamiutushi Ushirota","37.5139649","140.6533073"},
			{"23","田村市船引町南移水中内","Tamura Funehiki Minamiutushi","37.5070768","140.6239679"},
			{"31","双葉郡浪江町津島仲沖","Futaba Namie Tushima","37.5601452","140.7461314"},
			{"32","双葉郡浪江町赤宇木手七郎","Futaba Namie Akogi","37.5934916","140.7440805"},
			{"33","相馬郡飯舘村長泥","Soma Iitate Nagadoro","37.6128507","140.7493794"},
			{"34","双葉郡浪江町津島大高木","Futaba Namie Tushimao","37.5518624","140.7260468"},
			{"36","伊達郡川俣町山木屋大洪","Date Kawamata Yamakiya Onukari","37.6111796","140.6680268"},
			{"37","伊達市霊山町石田宝司沢","Date Ryozen Ishida Houjizawa","37.7588562","140.6875064"},
			{"38","いわき市四倉町白岩保木田","Iwaki Yotukura Siroiwa","37.120888","140.9517325"},
			{"39","相馬市山上上並木","Soma Yamakami","37.7685046","140.8579938"},
			{"41","田村市都路町古道","Tamura Miyakoji Furumiti","37.4346756","140.7953463"},
			{"42","田村市常葉町山根富岡","Tamura Tokiwa Yamane Tomioka","37.4644202","140.6658371"},
			{"43","双葉郡川内村下川内宮渡","Futaba Kawauchi Simokawauchi Miyawata","37.3210959","140.8152722"},
			{"44","いわき市大久町大久矢ノ目沢","Iwaki Ohisa","37.178173","140.9530661"},
			{"45","双葉郡楢葉町山田岡美し森","Futaba Naraha","37.2440824","141.008025"},
			{"46","伊達郡川俣町山木屋向出山","Date Kawamata Yamakiya Mukaideyama","37.5794174","140.7108919"},
			{"51","田村郡小野町小野新町舘廻","Tamura Ono","37.2864282","140.6240025"},
			{"52","田村市船引町船引馬場川原","Tamura Funehiki Funahiki Baba","37.4411634","140.5692006"},
			{"61","相馬郡飯舘村八木沢","Soma Iitate Yagisawa","37.6914248","140.8121305"},
			{"62","相馬郡飯舘村草野大師堂","Soma Iitate Kusano","37.699991","140.7456179"},
			{"63","相馬郡飯舘村二枚橋","Soma Iitate Nimaibashi","37.6923848","140.67651"},
			{"71","双葉郡広野町下北迫苗代替","Futaba Hirono","37.2156334","140.9954551"},
			{"72","いわき市久之浜町久之浜字北荒蒔","Iwaki Hisanohama","37.1424509","140.9958325"},
			{"73","いわき市四倉町","Iwaki Yotukura","37.1088846","140.9899951"},
			{"74","いわき市小川町高萩","Iwaki Ogawa Takahagi","37.1268065","140.8451765"},
			{"75","いわき市内郷御厩町","Iwaki Uchigomimaya","37.0501995","140.8738941"},
			{"76","双葉郡川内村上川内早渡","Futaba Kawauchi Kamikawauchi Hayawata","37.3360573","140.8066007"},
			{"77","いわき市小川町上小川","Iwaki Ogawa Kamiogawa","37.134831","140.8567667"},
			{"78","伊達郡川俣町鶴沢","Date Kawamata Tsurusawa","37.66479859","140.5953026"},
			{"79","双葉郡浪江町下津島萱深","Futaba Namie Shimotushima","37.5628146","140.7588442"},
			{"80","南相馬市原町区高見町","Minamisoma Haramachi Takami","37.6359705","140.9863316"},
			{"81","双葉郡浪江町赤宇木石小屋","Futaba Namie Akougi Ishikoya","37.58220135","140.7954597"},
			{"83","双葉郡浪江町赤宇木椚平","Futaba Namie Akogi Kunugi","37.5611249","140.8169692"},
			{"84","いわき市三和町差塩","Iwaki Miwa","37.1839221","140.7165863"},
			{"85","福島市荒井原宿","Fukushima Arai","37.7228025","140.3854531"},
			{"86","郡山市大槻町長右エ門林","Koriyama Otuki","37.3974423","140.3289006"},
			{"87","双葉郡川内村上川内花ノ内","Futaba Kawauchi Kamikawauchi Hananouti","37.3672103","140.7223051"},
			{"88","福島市光が丘","Fukushima Hikarigaoka","37.6900638","140.47064"},
			{"89","郡山市豊田町","Koriyama Toyota","37.3985321","140.3641917"},
			{"101","伊達市霊山町大石字三ノ輪","Date Ryozen Oishi","37.8040253","140.6395468"},
			{"102","伊達市月舘町月舘字町","Date Tukidate","37.7360316","140.6106651"},
			{"103","南相馬市原町区高字大豆柄内","Minamisoma Haramachi Taka","37.6063946","140.9857492"},
			{"104","双葉郡葛尾村大字落合字落合","Futaba Katurao","37.5038709","140.7694373"},
			{"105","田村市都路町古道字寺ノ前","Tamura Miyakoji Furumiti Teranomae","37.431662","140.7914553"},
			{"106","いわき市川前町小白井字将監小屋","Iwaki Kawamae","37.2998966","140.7033977"},
			{"107","南相馬市原町区馬場字中内","Minamisoma Haramachi Baba Nakauchi","37.6082739","140.9233714"},
			{"108","南相馬市原町区大原台畑","Minamisoma Haramachi Ohara","37.6621453","140.8977145"}
		};

	public JsonOutput(){
		for(int i=0; i<tmppoints.length; i++){
			int index = Integer.parseInt(tmppoints[i][0]);
			points[index] = tmppoints[i];
		}
	}

	/**
	 * 指定されたPDFの内容を、JSONで出力する
	 * 
	 * @param url 抽出対象のPDFのURL
	 * @throws Exception 
	 */
	public void output(String url) throws Exception {

		List<Map<String,String>>[] dataList = new ArrayList[areaSize];
		for(int i=0; i<areaSize; i++){
			dataList[i] = new ArrayList<Map<String, String>>();
		}
		
		PDDocument document = null;
		try {
			document = PDDocument.load(new URL(url));
		
			PDFTextStripper stripper = new PDFTextStripper();
			stripper.setSortByPosition( true );
			String text = stripper.getText(document);
			text = StringUtil.zenkakuNumToHankaku(text);
			
			java.util.Date d = new java.util.Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			
			String[] lines = text.split("\n");
			for(int i=0; i<lines.length; i++){
				lines[i] = lines[i].trim();
				if(lines[i].indexOf("測定エリア 【")==0){
					lines[i] = lines[i].replaceAll("\\*2", "");
					lines[i] = lines[i].replaceAll("（.*）", "");
					
					String regex = "測定エリア 【(\\d+)】.*(\\d+)月(\\d+)日\\s*(\\d+)時(\\d+)分\\s*([\\d\\.]+).*";
					Pattern p = Pattern.compile(regex);
					
					Matcher m = p.matcher(lines[i]);
					if (m.find()){
						int area = Integer.parseInt(m.group(1));
						String month = m.group(2);
						String day = m.group(3);
						String hour = m.group(4);
						String min = m.group(5);
						float value = Float.parseFloat(m.group(6));
						
			   			Map<String, String> datamap = new HashMap<String, String>();
						datamap.put("datetime", getDateString(null, month, day, hour, min));
						datamap.put("value", value+"");
						dataList[area].add(datamap);
					}
				
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (document != null) {
				document.close();
			}
		}
		
		for(int i=0; i<areaSize; i++){
			if(dataList[i].size() != 0 && points[i][0] != null){
				System.out.println("["+points[i][0]+"] = "+points[i][1]);
				System.out.println(dataList[i].toString());
			}
		}
	}
	
	private static String getDateString(String year, String month, String day, String time, String min){
		if(year == null){
			java.util.Date d = new java.util.Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			year = String.valueOf(cal.get(Calendar.YEAR));
		}
		if(month.length() == 1){
			month = "0"+month;
		}
		if(day.length()==1){
			day = "0"+day;
		}
		if(time.length()==1){
			time = "0"+time;
		}
		if(min == null){
			min = "00";
		}
		return year+"-"+month+"-"+day+" "+time+":"+min;
	}
}
