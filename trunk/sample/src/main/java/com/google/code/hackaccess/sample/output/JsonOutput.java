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
 * JSON‚ğo—Í‚µ‚Ü‚·
 * PDF“à‚ÌƒeƒLƒXƒg‰ğÍ•û–@‚ÍA•¶•”È’ñ‹Ÿ‚ÌwŒ´”­ü•Ó‚ÌŒÅ’è‘ª’è“_‚É‚¨‚¯‚é‹óŠÔü—Ê—¦‚Ì‘ª’èŒ‹‰Êxê—p‚Å‚·
 *
 */
public class JsonOutput implements Output {
	private int areaSize = 110;
	private String[][] points = new String[areaSize][5];
	
	private String[][] tmppoints = {
			{"1","•Ÿ“‡s™È’¬","Fukushima Sugituma","37.7490987","140.4667432"},
			{"2","•Ÿ“‡s‘å”g‘êƒm“ü","Fukushima Onami","37.7585306","140.553572"},
			{"3","ˆÉ’Bs—ìR’¬Î“c•F•½","Date Ryozen Ishida Hikohei","37.759424","140.7301391"},
			{"4","ˆÉ’BŒSì–“’¬‘åš’ß‘òšì’[","Date Kawamata Turusawa","37.663122","140.5957082"},
			{"5","‘Š”ns’†–ì›‘O","Soma Nakano","37.790975","140.927497"},
			{"6","“ì‘Š”ns­“‡‹æ¼’¬","Minamisoma Kashima Nishi","37.7032212","140.9648426"},
			{"7","“ì‘Š”ns­“‡‹æ›“à–{‰®•~","Minamisoma Kashima Terauchi","37.7007408","140.9608736"},
			{"10","“ñ–{¼sj“¹’†“‡","Nihonmatsu Harimiti","37.6011144","140.5838917"},
			{"11","“ñ–{¼s‘¾“cš‰º“c","Nihonmatsu Ota","37.569784","140.5762523"},
			{"12","“c‘ºs‘Dˆø’¬‘Dˆøš¬‘òì‘ã","Tamura Funahiki Funahiki Ozawakawasiro","37.4362001","140.5924433"},
			{"13","“c‘ºsí—t’¬¼Œü‰®Œ`","Tamura Tokiwa Nishi Mukiyakata","37.4441","140.6180266"},
			{"14","“c‘ºsí—t’¬í—t“à’¬","Tamura Tokiwa Tokiwanai","37.4400394","140.6461856"},
			{"15","“c‘ºsí—t’¬Rª­“‡","Tamura Tokiwa Yamane Kashima","37.4512277","140.6774311"},
			{"20","“c‘ºs‘Dˆø’¬VŠÚ‰º","Tamura Funehiki Niitate","37.4925135","140.5822886"},
			{"21","“c‘ºs‘Dˆø’¬ãˆÚ","Tamura Funehiki Kamiutushi","37.5144233","140.6581236"},
			{"22","“c‘ºs‘Dˆø’¬ãˆÚšŒã“c","Tamura Funehiki Kamiutushi Ushirota","37.5139649","140.6533073"},
			{"23","“c‘ºs‘Dˆø’¬“ìˆÚ…’†“à","Tamura Funehiki Minamiutushi","37.5070768","140.6239679"},
			{"31","‘o—tŒS˜Q]’¬’Ã“‡’‡‰«","Futaba Namie Tushima","37.5601452","140.7461314"},
			{"32","‘o—tŒS˜Q]’¬Ô‰F–Øèµ˜Y","Futaba Namie Akogi","37.5934916","140.7440805"},
			{"33","‘Š”nŒS”ÑŠÚ‘º’·“D","Soma Iitate Nagadoro","37.6128507","140.7493794"},
			{"34","‘o—tŒS˜Q]’¬’Ã“‡‘å‚–Ø","Futaba Namie Tushimao","37.5518624","140.7260468"},
			{"36","ˆÉ’BŒSì–“’¬R–Ø‰®‘å^","Date Kawamata Yamakiya Onukari","37.6111796","140.6680268"},
			{"37","ˆÉ’Bs—ìR’¬Î“c•ói‘ò","Date Ryozen Ishida Houjizawa","37.7588562","140.6875064"},
			{"38","‚¢‚í‚«sl‘q’¬”’Šâ•Û–Ø“c","Iwaki Yotukura Siroiwa","37.120888","140.9517325"},
			{"39","‘Š”nsRãã•À–Ø","Soma Yamakami","37.7685046","140.8579938"},
			{"41","“c‘ºs“s˜H’¬ŒÃ“¹","Tamura Miyakoji Furumiti","37.4346756","140.7953463"},
			{"42","“c‘ºsí—t’¬Rª•x‰ª","Tamura Tokiwa Yamane Tomioka","37.4644202","140.6658371"},
			{"43","‘o—tŒSì“à‘º‰ºì“à‹{“n","Futaba Kawauchi Simokawauchi Miyawata","37.3210959","140.8152722"},
			{"44","‚¢‚í‚«s‘å‹v’¬‘å‹v–îƒm–Ú‘ò","Iwaki Ohisa","37.178173","140.9530661"},
			{"45","‘o—tŒS“è—t’¬R“c‰ª”ü‚µX","Futaba Naraha","37.2440824","141.008025"},
			{"46","ˆÉ’BŒSì–“’¬R–Ø‰®ŒüoR","Date Kawamata Yamakiya Mukaideyama","37.5794174","140.7108919"},
			{"51","“c‘ºŒS¬–ì’¬¬–ìV’¬ŠÚ‰ô","Tamura Ono","37.2864282","140.6240025"},
			{"52","“c‘ºs‘Dˆø’¬‘Dˆø”nêìŒ´","Tamura Funehiki Funahiki Baba","37.4411634","140.5692006"},
			{"61","‘Š”nŒS”ÑŠÚ‘º”ª–Ø‘ò","Soma Iitate Yagisawa","37.6914248","140.8121305"},
			{"62","‘Š”nŒS”ÑŠÚ‘º‘–ì‘åt“°","Soma Iitate Kusano","37.699991","140.7456179"},
			{"63","‘Š”nŒS”ÑŠÚ‘º“ñ–‡‹´","Soma Iitate Nimaibashi","37.6923848","140.67651"},
			{"71","‘o—tŒSL–ì’¬‰º–k”—•c‘ã‘Ö","Futaba Hirono","37.2156334","140.9954551"},
			{"72","‚¢‚í‚«s‹v”V•l’¬‹v”V•lš–krª","Iwaki Hisanohama","37.1424509","140.9958325"},
			{"73","‚¢‚í‚«sl‘q’¬","Iwaki Yotukura","37.1088846","140.9899951"},
			{"74","‚¢‚í‚«s¬ì’¬‚”‹","Iwaki Ogawa Takahagi","37.1268065","140.8451765"},
			{"75","‚¢‚í‚«s“à‹½Œä‰X’¬","Iwaki Uchigomimaya","37.0501995","140.8738941"},
			{"76","‘o—tŒSì“à‘ºãì“à‘“n","Futaba Kawauchi Kamikawauchi Hayawata","37.3360573","140.8066007"},
			{"77","‚¢‚í‚«s¬ì’¬ã¬ì","Iwaki Ogawa Kamiogawa","37.134831","140.8567667"},
			{"78","ˆÉ’BŒSì–“’¬’ß‘ò","Date Kawamata Tsurusawa","37.66479859","140.5953026"},
			{"79","‘o—tŒS˜Q]’¬‰º’Ã“‡Š[","Futaba Namie Shimotushima","37.5628146","140.7588442"},
			{"80","“ì‘Š”nsŒ´’¬‹æ‚Œ©’¬","Minamisoma Haramachi Takami","37.6359705","140.9863316"},
			{"81","‘o—tŒS˜Q]’¬Ô‰F–ØÎ¬‰®","Futaba Namie Akougi Ishikoya","37.58220135","140.7954597"},
			{"83","‘o—tŒS˜Q]’¬Ô‰F–Ø­•½","Futaba Namie Akogi Kunugi","37.5611249","140.8169692"},
			{"84","‚¢‚í‚«sO˜a’¬·‰–","Iwaki Miwa","37.1839221","140.7165863"},
			{"85","•Ÿ“‡srˆäŒ´h","Fukushima Arai","37.7228025","140.3854531"},
			{"86","ŒSRs‘å’Î’¬’·‰EƒG–å—Ñ","Koriyama Otuki","37.3974423","140.3289006"},
			{"87","‘o—tŒSì“à‘ºãì“à‰Ôƒm“à","Futaba Kawauchi Kamikawauchi Hananouti","37.3672103","140.7223051"},
			{"88","•Ÿ“‡sŒõ‚ª‹u","Fukushima Hikarigaoka","37.6900638","140.47064"},
			{"89","ŒSRs–L“c’¬","Koriyama Toyota","37.3985321","140.3641917"},
			{"101","ˆÉ’Bs—ìR’¬‘åÎšOƒm—Ö","Date Ryozen Oishi","37.8040253","140.6395468"},
			{"102","ˆÉ’BsŒŠÚ’¬ŒŠÚš’¬","Date Tukidate","37.7360316","140.6106651"},
			{"103","“ì‘Š”nsŒ´’¬‹æ‚š‘å“¤•¿“à","Minamisoma Haramachi Taka","37.6063946","140.9857492"},
			{"104","‘o—tŒSŠ‹”ö‘º‘åš—‡š—‡","Futaba Katurao","37.5038709","140.7694373"},
			{"105","“c‘ºs“s˜H’¬ŒÃ“¹š›ƒm‘O","Tamura Miyakoji Furumiti Teranomae","37.431662","140.7914553"},
			{"106","‚¢‚í‚«sì‘O’¬¬”’ˆäš«ŠÄ¬‰®","Iwaki Kawamae","37.2998966","140.7033977"},
			{"107","“ì‘Š”nsŒ´’¬‹æ”nêš’†“à","Minamisoma Haramachi Baba Nakauchi","37.6082739","140.9233714"},
			{"108","“ì‘Š”nsŒ´’¬‹æ‘åŒ´‘ä”¨","Minamisoma Haramachi Ohara","37.6621453","140.8977145"}
		};

	public JsonOutput(){
		for(int i=0; i<tmppoints.length; i++){
			int index = Integer.parseInt(tmppoints[i][0]);
			points[index] = tmppoints[i];
		}
	}

	/**
	 * w’è‚³‚ê‚½PDF‚Ì“à—e‚ğAJSON‚Åo—Í‚·‚é
	 * 
	 * @param url ’Šo‘ÎÛ‚ÌPDF‚ÌURL
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
				if(lines[i].indexOf("‘ª’èƒGƒŠƒA y")==0){
					lines[i] = lines[i].replaceAll("\\*2", "");
					lines[i] = lines[i].replaceAll("i.*j", "");
					
					String regex = "‘ª’èƒGƒŠƒA y(\\d+)z.*(\\d+)Œ(\\d+)“ú\\s*(\\d+)(\\d+)•ª\\s*([\\d\\.]+).*";
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
