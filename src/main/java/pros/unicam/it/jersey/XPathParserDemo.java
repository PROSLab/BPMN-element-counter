package pros.unicam.it.jersey;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.intellij.lang.annotations.Language;
import org.languagetool.JLanguageTool;
import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.RuleMatch;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;   

public class XPathParserDemo {
	
	private static boolean ConsiderExtendedSubProcess = true;

    public static void main(String[] args) throws Exception {

    	
        //Creation of the xls empty file
        Workbook wb = new XSSFWorkbook();    
        XSSFSheet sheet = (XSSFSheet) wb.createSheet("BPMN_Stats"); 
        XSSFSheet sheet2 = (XSSFSheet) wb.createSheet("BPMN_Stats_ExtendedSubProcess"); 
        XSSFRow rowhead = sheet.createRow((short)0);         
	    //creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method  
        rowhead.createCell(0  ).setCellValue("fileName");
        rowhead.createCell(1  ).setCellValue("bpmnModeler");
        rowhead.createCell(2  ).setCellValue("modelType");
        rowhead.createCell(3  ).setCellValue("isEnglish");
        rowhead.createCell(4  ).setCellValue("nTaskNoneLoopNoneCompensateNoneCallNone");
        rowhead.createCell(5  ).setCellValue("nTaskNoneLoopNoneCompensateNoneCall");
        rowhead.createCell(6  ).setCellValue("nTaskNoneLoopNoneCompensateCallNone");
        rowhead.createCell(7  ).setCellValue("nTaskNoneLoopNoneCompensateCall");
        rowhead.createCell(8  ).setCellValue("nTaskNoneLoopStandardCompensateNoneCallNone");
        rowhead.createCell(9  ).setCellValue("nTaskNoneLoopStandardCompensateNoneCall");
        rowhead.createCell(10 ).setCellValue("nTaskNoneLoopStandardCompensateCallNone");
        rowhead.createCell(11 ).setCellValue("nTaskNoneLoopStandardCompensateCall");
        rowhead.createCell(12 ).setCellValue("nTaskNoneLoopMIParallelCompensateNoneCallNone");
        rowhead.createCell(13 ).setCellValue("nTaskNoneLoopMIParallelCompensateNoneCall");
        rowhead.createCell(14 ).setCellValue("nTaskNoneLoopMIParallelCompensateCallNone");
        rowhead.createCell(15 ).setCellValue("nTaskNoneLoopMIParallelCompensateCall");
        rowhead.createCell(16 ).setCellValue("nTaskNoneLoopMISequentialCompensateNoneCallNone");
        rowhead.createCell(17 ).setCellValue("nTaskNoneLoopMISequentialCompensateNoneCall");
        rowhead.createCell(18 ).setCellValue("nTaskNoneLoopMISequentialCompensateCallNone");
        rowhead.createCell(19 ).setCellValue("nTaskNoneLoopMISequentialCompensateCall");
        rowhead.createCell(20 ).setCellValue("nTaskSendLoopNoneCompensateNone");
        rowhead.createCell(21 ).setCellValue("nTaskSendLoopNoneCompensate");
        rowhead.createCell(22 ).setCellValue("nTaskSendLoopStandardCompensateNone");           
        rowhead.createCell(23 ).setCellValue("nTaskSendLoopStandardCompensate"); 
        rowhead.createCell(24 ).setCellValue("nTaskSendLoopMIParallelCompensateNone"); 
        rowhead.createCell(25 ).setCellValue("nTaskSendLoopMIParallelCompensate");
        rowhead.createCell(26 ).setCellValue("nTaskSendLoopMISequentialCompensateNone"); 
        rowhead.createCell(27 ).setCellValue("nTaskSendLoopMISequentialCompensate");
        rowhead.createCell(28 ).setCellValue("nTaskReceiveLoopNoneCompensateNone");            
        rowhead.createCell(29 ).setCellValue("nTaskReceiveLoopNoneCompensate");           
        rowhead.createCell(30 ).setCellValue("nTaskReceiveLoopStandardCompensateNone");            
        rowhead.createCell(31 ).setCellValue("nTaskReceiveLoopStandardCompensate");            
        rowhead.createCell(32 ).setCellValue("nTaskReceiveLoopMIParallelCompensateNone");                        
        rowhead.createCell(33 ).setCellValue("nTaskReceiveLoopMIParallelCompensate");            
        rowhead.createCell(34 ).setCellValue("nTaskReceiveLoopMISequentialCompensateNone");           
        rowhead.createCell(35 ).setCellValue("nTaskReceiveLoopMISequentialCompensate");            
        rowhead.createCell(36 ).setCellValue("nTaskUserLoopNoneCompensateNone");            
        rowhead.createCell(37 ).setCellValue("nTaskUserLoopNoneCompensate");           
        rowhead.createCell(38 ).setCellValue("nTaskUserLoopStandardCompensateNone");            
        rowhead.createCell(39 ).setCellValue("nTaskUserLoopStandardCompensate");           
        rowhead.createCell(40 ).setCellValue("nTaskUserLoopMIParallelCompensateNone");            
        rowhead.createCell(41 ).setCellValue("nTaskUserLoopMIParallelCompensate");            
        rowhead.createCell(42 ).setCellValue("nTaskUserLoopMISequentialCompensateNone");            
        rowhead.createCell(43 ).setCellValue("nTaskUserLoopMISequentialCompensate");            
        rowhead.createCell(44 ).setCellValue("nTaskManualLoopNoneCompensateNone");            
        rowhead.createCell(45 ).setCellValue("nTaskManualLoopNoneCompensate");            
        rowhead.createCell(46 ).setCellValue("nTaskManualLoopStandardCompensateNone");            
        rowhead.createCell(47 ).setCellValue("nTaskManualLoopStandardCompensate");            
        rowhead.createCell(48 ).setCellValue("nTaskManualLoopMIParallelCompensateNone");            
        rowhead.createCell(49 ).setCellValue("nTaskManualLoopMIParallelCompensate");            
        rowhead.createCell(50 ).setCellValue("nTaskManualLoopMISequentialCompensateNone");            
        rowhead.createCell(51 ).setCellValue("nTaskManualLoopMISequentialCompensate");            
        rowhead.createCell(52 ).setCellValue("nTaskBusinessRuleLoopNoneCompensateNone");            
        rowhead.createCell(53 ).setCellValue("nTaskBusinessRuleLoopNoneCompensate");            
        rowhead.createCell(54 ).setCellValue("nTaskBusinessRuleLoopStandardCompensateNone");            
        rowhead.createCell(55 ).setCellValue("nTaskBusinessRuleLoopStandardCompensate");            
        rowhead.createCell(56 ).setCellValue("nTaskBusinessRuleLoopMIParallelCompensateNone");           
        rowhead.createCell(57 ).setCellValue("nTaskBusinessRuleLoopMIParallelCompensate");            
        rowhead.createCell(58 ).setCellValue("nTaskBusinessRuleLoopMISequentialCompensateNone");           
        rowhead.createCell(59 ).setCellValue("nTaskBusinessRuleLoopMISequentialCompensate");            
        rowhead.createCell(60 ).setCellValue("nTaskServiceLoopNoneCompensateNone");            
        rowhead.createCell(61 ).setCellValue("nTaskServiceLoopNoneCompensate");            
        rowhead.createCell(62 ).setCellValue("nTaskServiceLoopStandardCompensateNone");            
        rowhead.createCell(63 ).setCellValue("nTaskServiceLoopStandardCompensate");            
        rowhead.createCell(64 ).setCellValue("nTaskServiceLoopMIParallelCompensateNone");            
        rowhead.createCell(65 ).setCellValue("nTaskServiceLoopMIParallelCompensate");            
        rowhead.createCell(66 ).setCellValue("nTaskServiceLoopMISequentialCompensateNone");            
        rowhead.createCell(67 ).setCellValue("nTaskServiceLoopMISequentialCompensate");            
        rowhead.createCell(68 ).setCellValue("nTaskScriptLoopNoneCompensateNone");            
        rowhead.createCell(69 ).setCellValue("nTaskScriptLoopNoneCompensate");           
        rowhead.createCell(70 ).setCellValue("nTaskScriptLoopStandardCompensateNone");            
        rowhead.createCell(71 ).setCellValue("nTaskScriptLoopStandardCompensate");            
        rowhead.createCell(72 ).setCellValue("nTaskScriptLoopMIParallelCompensateNone");            
        rowhead.createCell(73 ).setCellValue("nTaskScriptLoopMIParallelCompensate");            
        rowhead.createCell(74 ).setCellValue("nTaskScriptLoopMISequentialCompensateNone");            
        rowhead.createCell(75 ).setCellValue("nTaskScriptLoopMISequentialCompensate");            
        rowhead.createCell(76 ).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensateNone");
        rowhead.createCell(77 ).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensate");
        rowhead.createCell(78 ).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensateNone");
        rowhead.createCell(79 ).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensate");
        rowhead.createCell(80 ).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensateNone");
        rowhead.createCell(81 ).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensate");
        rowhead.createCell(82 ).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensateNone");
        rowhead.createCell(83 ).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensate");
        rowhead.createCell(84 ).setCellValue("nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone");
        rowhead.createCell(85 ).setCellValue("nSubProcessExtendedEventNoneAdHocLoopNoneCompensate");
        rowhead.createCell(86 ).setCellValue("nSubProcessExtendedEventNoneAdHocLoopParallelCompensateNone");
        rowhead.createCell(87 ).setCellValue("nSubProcessExtendedEventNoneAdHocLoopParallelCompensate");
        rowhead.createCell(88 ).setCellValue("nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone");
        rowhead.createCell(89 ).setCellValue("nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate");
        rowhead.createCell(90 ).setCellValue("nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone");
        rowhead.createCell(91 ).setCellValue("nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate");
        rowhead.createCell(92 ).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensateNone");
        rowhead.createCell(93 ).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensate");
        rowhead.createCell(94 ).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensateNone");
        rowhead.createCell(95 ).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensate");
        rowhead.createCell(96 ).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensateNone");
        rowhead.createCell(97 ).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensate");
        rowhead.createCell(98 ).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensateNone");
        rowhead.createCell(99 ).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensate");
        rowhead.createCell(100).setCellValue("nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone");
        rowhead.createCell(101).setCellValue("nSubProcessExtendedEventNoneTransactionLoopNoneCompensate");
        rowhead.createCell(102).setCellValue("nSubProcessExtendedEventNoneTransactionLoopParallelCompensateNone");
        rowhead.createCell(103).setCellValue("nSubProcessExtendedEventNoneTransactionLoopParallelCompensate");
        rowhead.createCell(104).setCellValue("nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone");
        rowhead.createCell(105).setCellValue("nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate");
        rowhead.createCell(106).setCellValue("nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone");
        rowhead.createCell(107).setCellValue("nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate");
        rowhead.createCell(108).setCellValue("nSubProcessExtendedEventAdHocNoneLoopNoneCompensateNone");
        rowhead.createCell(109).setCellValue("nSubProcessExtendedEventAdHocNoneLoopNoneCompensate");
        rowhead.createCell(110).setCellValue("nSubProcessExtendedEventAdHocNoneLoopParallelCompensateNone");
        rowhead.createCell(111).setCellValue("nSubProcessExtendedEventAdHocNoneLoopParallelCompensate");
        rowhead.createCell(112).setCellValue("nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensateNone");
        rowhead.createCell(113).setCellValue("nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensate");
        rowhead.createCell(114).setCellValue("nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensateNone");
        rowhead.createCell(115).setCellValue("nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensate");
        rowhead.createCell(116).setCellValue("nSubProcessExtendedEventAdHocLoopNoneCompensateNone");
        rowhead.createCell(117).setCellValue("nSubProcessExtendedEventAdHocLoopNoneCompensate");
        rowhead.createCell(118).setCellValue("nSubProcessExtendedEventAdHocLoopParallelCompensateNone");
        rowhead.createCell(119).setCellValue("nSubProcessExtendedEventAdHocLoopParallelCompensate");
        rowhead.createCell(120).setCellValue("nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone");
        rowhead.createCell(121).setCellValue("nSubProcessExtendedEventAdHocLoopMIParallelCompensate");
        rowhead.createCell(122).setCellValue("nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone");
        rowhead.createCell(123).setCellValue("nSubProcessExtendedEventAdHocLoopMISequentialCompensate");
        rowhead.createCell(124).setCellValue("nSubProcessExtendedEventTransactionNoneLoopNoneCompensateNone");
        rowhead.createCell(125).setCellValue("nSubProcessExtendedEventTransactionNoneLoopNoneCompensate");
        rowhead.createCell(126).setCellValue("nSubProcessExtendedEventTransactionNoneLoopParallelCompensateNone");
        rowhead.createCell(127).setCellValue("nSubProcessExtendedEventTransactionNoneLoopParallelCompensate");
        rowhead.createCell(128).setCellValue("nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensateNone");
        rowhead.createCell(129).setCellValue("nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensate");
        rowhead.createCell(130).setCellValue("nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensateNone");
        rowhead.createCell(131).setCellValue("nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensate");
        rowhead.createCell(132).setCellValue("nSubProcessExtendedEventTransactionLoopNoneCompensateNone");
        rowhead.createCell(133).setCellValue("nSubProcessExtendedEventTransactionLoopNoneCompensate");
        rowhead.createCell(134).setCellValue("nSubProcessExtendedEventTransactionLoopParallelCompensateNone");
        rowhead.createCell(135).setCellValue("nSubProcessExtendedEventTransactionLoopParallelCompensate");
        rowhead.createCell(136).setCellValue("nSubProcessExtendedEventTransactionLoopMIParallelCompensateNone");
        rowhead.createCell(137).setCellValue("nSubProcessExtendedEventTransactionLoopMIParallelCompensate");
        rowhead.createCell(138).setCellValue("nSubProcessExtendedEventTransactionLoopMISequentialCompensateNone");
        rowhead.createCell(139).setCellValue("nSubProcessExtendedEventTransactionLoopMISequentialCompensate");
        rowhead.createCell(140).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopNoneCompensateNone");
        rowhead.createCell(141).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopNoneCompensate");
        rowhead.createCell(142).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopParallelCompensateNone");
        rowhead.createCell(143).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopParallelCompensate");
        rowhead.createCell(144).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopMIParallelCompensateNone");
        rowhead.createCell(145).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopMIParallelCompensate");
        rowhead.createCell(146).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopMISequentialCompensateNone");
        rowhead.createCell(147).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopMISequentialCompensate");
        rowhead.createCell(148).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone");
        rowhead.createCell(149).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate");
        rowhead.createCell(150).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopParallelCompensateNone");
        rowhead.createCell(151).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopParallelCompensate");
        rowhead.createCell(152).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone");
        rowhead.createCell(153).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate");
        rowhead.createCell(154).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone");
        rowhead.createCell(155).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate");
        rowhead.createCell(156).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopNoneCompensateNone");
        rowhead.createCell(157).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopNoneCompensate");
        rowhead.createCell(158).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopParallelCompensateNone");
        rowhead.createCell(159).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopParallelCompensate");
        rowhead.createCell(160).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopMIParallelCompensateNone");
        rowhead.createCell(161).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopMIParallelCompensate");
        rowhead.createCell(162).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopMISequentialCompensateNone");
        rowhead.createCell(163).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopMISequentialCompensate");
        rowhead.createCell(164).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone");
        rowhead.createCell(165).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate");
        rowhead.createCell(166).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopParallelCompensateNone");
        rowhead.createCell(167).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopParallelCompensate");
        rowhead.createCell(168).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensateNone");
        rowhead.createCell(169).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensate");
        rowhead.createCell(170).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensateNone");
        rowhead.createCell(171).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensate");
        rowhead.createCell(172).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopNoneCompensateNone");
        rowhead.createCell(173).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopNoneCompensate");
        rowhead.createCell(174).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopParallelCompensateNone");
        rowhead.createCell(175).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopParallelCompensate");
        rowhead.createCell(176).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopMIParallelCompensateNone");
        rowhead.createCell(177).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopMIParallelCompensate");
        rowhead.createCell(178).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopMISequentialCompensateNone");
        rowhead.createCell(179).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopMISequentialCompensate");
        rowhead.createCell(180).setCellValue("nSubProcessCollapsedEventAdHocLoopNoneCompensateNone");
        rowhead.createCell(181).setCellValue("nSubProcessCollapsedEventAdHocLoopNoneCompensate");
        rowhead.createCell(182).setCellValue("nSubProcessCollapsedEventAdHocLoopParallelCompensateNone");
        rowhead.createCell(183).setCellValue("nSubProcessCollapsedEventAdHocLoopParallelCompensate");
        rowhead.createCell(184).setCellValue("nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone");
        rowhead.createCell(185).setCellValue("nSubProcessCollapsedEventAdHocLoopMIParallelCompensate");
        rowhead.createCell(186).setCellValue("nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone");
        rowhead.createCell(187).setCellValue("nSubProcessCollapsedEventAdHocLoopMISequentialCompensate");
        rowhead.createCell(188).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopNoneCompensateNone");
        rowhead.createCell(189).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopNoneCompensate");
        rowhead.createCell(190).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopParallelCompensateNone");
        rowhead.createCell(191).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopParallelCompensate");
        rowhead.createCell(192).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopMIParallelCompensateNone");
        rowhead.createCell(193).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopMIParallelCompensate");
        rowhead.createCell(194).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopMISequentialCompensateNone");
        rowhead.createCell(195).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopMISequentialCompensate");
        rowhead.createCell(196).setCellValue("nSubProcessCollapsedEventTransactionLoopNoneCompensateNone");
        rowhead.createCell(197).setCellValue("nSubProcessCollapsedEventTransactionLoopNoneCompensate");
        rowhead.createCell(198).setCellValue("nSubProcessCollapsedEventTransactionLoopParallelCompensateNone");
        rowhead.createCell(199).setCellValue("nSubProcessCollapsedEventTransactionLoopParallelCompensate");
        rowhead.createCell(200).setCellValue("nSubProcessCollapsedEventTransactionLoopMIParallelCompensateNone");
        rowhead.createCell(201).setCellValue("nSubProcessCollapsedEventTransactionLoopMIParallelCompensate");
        rowhead.createCell(202).setCellValue("nSubProcessCollapsedEventTransactionLoopMISequentialCompensateNone");
        rowhead.createCell(203).setCellValue("nSubProcessCollapsedEventTransactionLoopMISequentialCompensate");
        rowhead.createCell(204).setCellValue("nDataObject");
        rowhead.createCell(205).setCellValue("nDataObjectCollection");
        rowhead.createCell(206).setCellValue("nDataObjectReference");
        rowhead.createCell(207).setCellValue("nDataStore");
        rowhead.createCell(208).setCellValue("nDataInput");
        rowhead.createCell(209).setCellValue("nDataOutput");
        rowhead.createCell(210).setCellValue("nExclusiveGatewayNoMarker");
        rowhead.createCell(211).setCellValue("nExclusiveGatewayMarker");
        rowhead.createCell(212).setCellValue("nParallelGateway");
        rowhead.createCell(213).setCellValue("nInclusiveGateway");
        rowhead.createCell(214).setCellValue("nEventBasedGateway");
        rowhead.createCell(215).setCellValue("nEventBasedGatewayExclusiveInstantiation");
        rowhead.createCell(216).setCellValue("nEventBasedGatewayParallelInstantiation");
        rowhead.createCell(217).setCellValue("nComplexGateway");
        rowhead.createCell(218).setCellValue("nStartMultipleParallelEventDefinition");
        rowhead.createCell(219).setCellValue("nStartMultipleEventDefinition");
        rowhead.createCell(220).setCellValue("nStartNoneEventDefinition");
        rowhead.createCell(221).setCellValue("nStartSignalEventDefinition");
        rowhead.createCell(222).setCellValue("nStartConditionalEventDefinition");
        rowhead.createCell(223).setCellValue("nStartTimerEventDefinition");
        rowhead.createCell(224).setCellValue("nStartMessageEventDefinition");
        rowhead.createCell(225).setCellValue("nStartCompensateEventDefinition");
        rowhead.createCell(226).setCellValue("nStartEscalationEventDefinition");
        rowhead.createCell(227).setCellValue("nStartErrorEventDefinition");
        rowhead.createCell(228).setCellValue("nStartMessageEventSubProcessInterruptingDefinition");
        rowhead.createCell(229).setCellValue("nStartTimerEventSubProcessInterruptingDefinition");
        rowhead.createCell(230).setCellValue("nStartEscalationEventSubProcessInterruptingDefinition");
        rowhead.createCell(231).setCellValue("nStartConditionalEventSubProcessInterruptingDefinition");
        rowhead.createCell(232).setCellValue("nStartErrorEventSubProcessInterruptingDefinition");
        rowhead.createCell(233).setCellValue("nStartCompensateEventSubProcessInterruptingDefinition");
        rowhead.createCell(234).setCellValue("nStartSignalEventSubProcessInterruptingDefinition");
        rowhead.createCell(235).setCellValue("nStartMultipleEventSubProcessInterruptingDefinition");
        rowhead.createCell(236).setCellValue("nStartMultipleParallelEventSubProcessInterruptingDefinition");       
        rowhead.createCell(237).setCellValue("nStartMessageEventSubProcessNonInterruptingDefinition");
        rowhead.createCell(238).setCellValue("nStartTimerEventSubProcessNonInterruptingDefinition");
        rowhead.createCell(239).setCellValue("nStartEscalationEventSubProcessNonInterruptingDefinition");
        rowhead.createCell(240).setCellValue("nStartConditionalEventSubProcessNonInterruptingDefinition");
        rowhead.createCell(241).setCellValue("nStartSignalEventSubProcessNonInterruptingDefinition");
        rowhead.createCell(242).setCellValue("nStartMultipleParallelEventSubProcessNonInterruptingDefinition");
        rowhead.createCell(243).setCellValue("nStartMultipleEventSubProcessNonInterruptingDefinition");       
        rowhead.createCell(244).setCellValue("nEndNoneEventDefinition");
        rowhead.createCell(245).setCellValue("nEndMultipleEventDefinition"); 
        rowhead.createCell(246).setCellValue("nEndEscalationEventDefinition");
        rowhead.createCell(247).setCellValue("nEndErrorEventDefinition");
        rowhead.createCell(248).setCellValue("nEndSignalEventDefinition");
        rowhead.createCell(249).setCellValue("nEndCompensateEventDefinition");
        rowhead.createCell(250).setCellValue("nEndCancelEventDefinition"); 
        rowhead.createCell(251).setCellValue("nEndMessageEventDefinition");
        rowhead.createCell(252).setCellValue("nEndTerminateEventDefinition");
        rowhead.createCell(253).setCellValue("nIntermediateCatchMultipleEventDefinition");
        rowhead.createCell(254).setCellValue("nIntermediateCatchMultipleParallelEventDefinition");
        rowhead.createCell(255).setCellValue("nIntermediateCatchMessageEventDefinition");
        rowhead.createCell(256).setCellValue("nIntermediateCatchTimerEventDefinition");
        rowhead.createCell(257).setCellValue("nIntermediateCatchConditionalEventDefinition");
        rowhead.createCell(258).setCellValue("nIntermediateCatchLinkEventDefinition");
        rowhead.createCell(259).setCellValue("nIntermediateCatchSignalEventDefinition");
        rowhead.createCell(260).setCellValue("nIntermediateThrowNoneEventDefinition");
        rowhead.createCell(261).setCellValue("nIntermediateThrowMessageEventDefinition");
        rowhead.createCell(262).setCellValue("nIntermediateThrowEscalationEventDefinition");
        rowhead.createCell(263).setCellValue("nIntermediateThrowLinkEventDefinition");
        rowhead.createCell(264).setCellValue("nIntermediateThrowSignalEventDefinition");
        rowhead.createCell(265).setCellValue("nIntermediateThrowCompensateEventDefinition");
        rowhead.createCell(266).setCellValue("nIntermediateThrowMultipleParallelEventDefinition");
        rowhead.createCell(267).setCellValue("nIntermediateBoundaryMessageEvent");
        rowhead.createCell(268).setCellValue("nIntermediateBoundaryTimerEvent");
        rowhead.createCell(269).setCellValue("nIntermediateBoundaryCancelEvent");
        rowhead.createCell(270).setCellValue("nIntermediateBoundaryConditionalEvent ");
        rowhead.createCell(271).setCellValue("nIntermediateBoundaryEscalationEvent");
        rowhead.createCell(272).setCellValue("nIntermediateBoundaryErrorEvent");
        rowhead.createCell(273).setCellValue("nIntermediateBoundarySignalEvent");
        rowhead.createCell(274).setCellValue("nIntermediateBoundaryCompensateEvent");
        rowhead.createCell(275).setCellValue("nIntermediateBoundaryMultipleEvent");
        rowhead.createCell(276).setCellValue("nIntermediateBoundaryMultipleParallelEvent");
        rowhead.createCell(277).setCellValue("nIntermediateBoundaryTimerEventNonInterrupting");
        rowhead.createCell(278).setCellValue("nIntermediateBoundaryEscalationEventNonInterrupting");
        rowhead.createCell(279).setCellValue("nIntermediateBoundaryConditionalEventNonInterrupting");
        rowhead.createCell(280).setCellValue("nIntermediateBoundaryMessageEventNonInterrupting");
        rowhead.createCell(281).setCellValue("nIntermediateBoundarySignalEventNonInterrupting");
        rowhead.createCell(282).setCellValue("nIntermediateBoundaryMultipleEventNonInterrupting");
        rowhead.createCell(283).setCellValue("nIntermediateBoundaryMultipleParallelEventNonInterrupting");
        rowhead.createCell(284).setCellValue("nMessageFlow");
        rowhead.createCell(285).setCellValue("nSequenceFlow");
        rowhead.createCell(286).setCellValue("nDefaultFlow");
        rowhead.createCell(287).setCellValue("nConditionalFlow");
        rowhead.createCell(288).setCellValue("nLane"); 
        rowhead.createCell(289).setCellValue("nPoolCollapsedMultiplicityNone");
        rowhead.createCell(290).setCellValue("nPoolCollapsedMultiplicity");
        rowhead.createCell(291).setCellValue("nPoolExpandedMultiplicityNone");
        rowhead.createCell(292).setCellValue("nPoolExpandedMultiplicity");
        rowhead.createCell(293).setCellValue("nChoreographyTask");
        rowhead.createCell(294).setCellValue("nChoreographyMessage");            
        rowhead.createCell(295).setCellValue("nChoreographyTaskSequentialMultipleInstance");
        rowhead.createCell(296).setCellValue("nChoreographyTaskParallelMultipleInstance");
        rowhead.createCell(297).setCellValue("nChoreographyTaskLoop");
        rowhead.createCell(298).setCellValue("nChoreographySubprocessCollapsed");
        rowhead.createCell(299).setCellValue("nChoreographySubprocessCollapsedParallelMultipleInstance");
        rowhead.createCell(300).setCellValue("nChoreographySubprocessCollapsedSequentialMultipleInstance");
        rowhead.createCell(301).setCellValue("nChoreographySubprocessCollapsedLoop");
        rowhead.createCell(302).setCellValue("nChoreographySubprocessCollapsedCall");
        rowhead.createCell(303).setCellValue("nChoreographySubprocessCollapsedCallSequentialMultipleInstance");
        rowhead.createCell(304).setCellValue("nChoreographySubprocessCollapsedCallParallelMultipleInstance");
        rowhead.createCell(305).setCellValue("nChoreographySubprocessCollapsedCallLoop");
        rowhead.createCell(306).setCellValue("nChoreographySubprocessExpanded");
        rowhead.createCell(307).setCellValue("nChoreographySubprocessExpandedSequentialMultipleInstance");
        rowhead.createCell(308).setCellValue("nChoreographySubprocessExpandedParallelMultipleInstance");
        rowhead.createCell(309).setCellValue("nChoreographySubprocessExpandedLoop");
        rowhead.createCell(310).setCellValue("nChoreographyParticipant");
        rowhead.createCell(311).setCellValue("nChoreographyParticipantMultiple");       
        rowhead.createCell(312).setCellValue("nConversationNone");
        rowhead.createCell(313).setCellValue("nConversationSubProcess");
        rowhead.createCell(314).setCellValue("nConversationCall");
        rowhead.createCell(315).setCellValue("nConversationSubProcessCall");
        rowhead.createCell(316).setCellValue("nConversationLink");
        rowhead.createCell(317).setCellValue("nAssociationCompensate");
        rowhead.createCell(318).setCellValue("nAssociationUndirected");
        rowhead.createCell(319).setCellValue("nAssociationUnidirectional");        
        rowhead.createCell(320).setCellValue("nAssociationBidirectional");
        rowhead.createCell(321).setCellValue("nAssociationDataOutput");
        rowhead.createCell(322).setCellValue("nAssociationDataInput");
        rowhead.createCell(323).setCellValue("nGroup");
        rowhead.createCell(324).setCellValue("nTextAnnotation");
        rowhead.createCell(325).setCellValue("nOfExtensionElements");
        rowhead.createCell(326).setCellValue("TotalElements");
        
//        Font font = wb.createFont();  
//        font.setFontHeightInPoints((short)15);  
//        font.setFontName("Gill Sans MT");  
//        font.setBold(true);  
//        font.setStrikeout(true);
//        
//        CellStyle bold = wb.createCellStyle(); 
//		bold.setFont(font);
//		
//		 
//			 rowhead.setRowStyle(bold);        	
       
        

        // File's cycle of the testmodels folder
        File folder = new File("testmodels");
        File[] listOfFiles = folder.listFiles();
        
        for (int x = 0; x < listOfFiles.length; x++) {
        	
    	//Defining global variables
    	String fileName;
        String bpmnModeler;
        boolean isEnglish=false;

        //Process Subprocess or Collaboration
        String modelType = null;
        int nGeneralSubProcess=0;
        
        int nTaskNoneLoopNoneCompensateNoneCallNone=0;
        int nTaskNoneLoopNoneCompensateNoneCall=0;
        int nTaskNoneLoopNoneCompensateCallNone=0;
        int nTaskNoneLoopNoneCompensateCall=0;
        int nTaskNoneLoopStandardCompensateNoneCallNone=0;
        int nTaskNoneLoopStandardCompensateNoneCall=0;
        int nTaskNoneLoopStandardCompensateCallNone=0;
        int nTaskNoneLoopStandardCompensateCall=0;
        int nTaskNoneLoopMIParallelCompensateNoneCallNone=0;
        int nTaskNoneLoopMIParallelCompensateNoneCall=0;
        int nTaskNoneLoopMIParallelCompensateCallNone=0;
        int nTaskNoneLoopMIParallelCompensateCall=0;
        int nTaskNoneLoopMISequentialCompensateNoneCallNone=0;
        int nTaskNoneLoopMISequentialCompensateNoneCall=0;
        int nTaskNoneLoopMISequentialCompensateCallNone=0;
        int nTaskNoneLoopMISequentialCompensateCall=0;
        int nTaskSendLoopNoneCompensateNone=0;
        int nTaskSendLoopNoneCompensate=0;
        int nTaskSendLoopStandardCompensateNone=0;
        int nTaskSendLoopStandardCompensate=0;
        int nTaskSendLoopMIParallelCompensateNone=0;
        int nTaskSendLoopMIParallelCompensate=0;
        int nTaskSendLoopMISequentialCompensateNone=0;
        int nTaskSendLoopMISequentialCompensate=0;
        int nTaskReceiveLoopNoneCompensateNone=0;
        int nTaskReceiveLoopNoneCompensate=0;
        int nTaskReceiveLoopStandardCompensateNone=0;
        int nTaskReceiveLoopStandardCompensate=0;
        int nTaskReceiveLoopMIParallelCompensateNone=0;
        int nTaskReceiveLoopMIParallelCompensate=0;
        int nTaskReceiveLoopMISequentialCompensateNone=0;
        int nTaskReceiveLoopMISequentialCompensate=0;
        int nTaskUserLoopNoneCompensateNone=0;
        int nTaskUserLoopNoneCompensate=0;
        int nTaskUserLoopStandardCompensateNone=0;
        int nTaskUserLoopStandardCompensate=0;
        int nTaskUserLoopMIParallelCompensateNone=0;
        int nTaskUserLoopMIParallelCompensate=0;
        int nTaskUserLoopMISequentialCompensateNone=0;
        int nTaskUserLoopMISequentialCompensate=0;
        int nTaskManualLoopNoneCompensateNone=0;
        int nTaskManualLoopNoneCompensate=0;
        int nTaskManualLoopStandardCompensateNone=0;
        int nTaskManualLoopStandardCompensate=0;
        int nTaskManualLoopMIParallelCompensateNone=0;
        int nTaskManualLoopMIParallelCompensate=0;
        int nTaskManualLoopMISequentialCompensateNone=0;
        int nTaskManualLoopMISequentialCompensate=0;
        int nTaskBusinessRuleLoopNoneCompensateNone=0;
        int nTaskBusinessRuleLoopNoneCompensate=0;
        int nTaskBusinessRuleLoopStandardCompensateNone=0;
        int nTaskBusinessRuleLoopStandardCompensate=0;
        int nTaskBusinessRuleLoopMIParallelCompensateNone=0;
        int nTaskBusinessRuleLoopMIParallelCompensate=0;
        int nTaskBusinessRuleLoopMISequentialCompensateNone=0;
        int nTaskBusinessRuleLoopMISequentialCompensate=0;
        int nTaskServiceLoopNoneCompensateNone=0;
        int nTaskServiceLoopNoneCompensate=0;
        int nTaskServiceLoopStandardCompensateNone=0;
        int nTaskServiceLoopStandardCompensate=0;
        int nTaskServiceLoopMIParallelCompensateNone=0;
        int nTaskServiceLoopMIParallelCompensate=0;
        int nTaskServiceLoopMISequentialCompensateNone=0;
        int nTaskServiceLoopMISequentialCompensate=0;
        int nTaskScriptLoopNoneCompensateNone=0;
        int nTaskScriptLoopNoneCompensate=0;
        int nTaskScriptLoopStandardCompensateNone=0;
        int nTaskScriptLoopStandardCompensate=0;
        int nTaskScriptLoopMIParallelCompensateNone=0;
        int nTaskScriptLoopMIParallelCompensate=0;
        int nTaskScriptLoopMISequentialCompensateNone=0;
        int nTaskScriptLoopMISequentialCompensate=0;
        //Subprocess
        int nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensateNone=0;
        int nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensate=0;
        int nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensateNone=0;
        int nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensate=0;
        int nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensateNone=0;
        int nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensate=0;
        int nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensateNone=0;
        int nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensate=0;
        int nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone=0;
        int nSubProcessExtendedEventNoneAdHocLoopNoneCompensate=0;
        int nSubProcessExtendedEventNoneAdHocLoopParallelCompensateNone=0;
        int nSubProcessExtendedEventNoneAdHocLoopParallelCompensate=0;
        int nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone=0;
        int nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate=0;
        int nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone=0;
        int nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate=0;
        int nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensateNone=0;
        int nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensate=0;
        int nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensateNone=0;
        int nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensate=0;
        int nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensateNone=0;
        int nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensate=0;
        int nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensateNone=0;
        int nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensate=0;
        int nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone=0;
        int nSubProcessExtendedEventNoneTransactionLoopNoneCompensate=0;
        int nSubProcessExtendedEventNoneTransactionLoopParallelCompensateNone=0;
        int nSubProcessExtendedEventNoneTransactionLoopParallelCompensate=0;
        int nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone=0;
        int nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate=0;
        int nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone=0;
        int nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate=0;
        int nSubProcessExtendedEventAdHocNoneLoopNoneCompensateNone=0;
        int nSubProcessExtendedEventAdHocNoneLoopNoneCompensate=0;
        int nSubProcessExtendedEventAdHocNoneLoopParallelCompensateNone=0;
        int nSubProcessExtendedEventAdHocNoneLoopParallelCompensate=0;
        int nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensateNone=0;
        int nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensate=0;
        int nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensateNone=0;
        int nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensate=0;
        int nSubProcessExtendedEventAdHocLoopNoneCompensateNone=0;
        int nSubProcessExtendedEventAdHocLoopNoneCompensate=0;
        int nSubProcessExtendedEventAdHocLoopParallelCompensateNone=0;
        int nSubProcessExtendedEventAdHocLoopParallelCompensate=0;
        int nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone=0;
        int nSubProcessExtendedEventAdHocLoopMIParallelCompensate=0;
        int nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone=0;
        int nSubProcessExtendedEventAdHocLoopMISequentialCompensate=0;
        int nSubProcessExtendedEventTransactionNoneLoopNoneCompensateNone=0;
        int nSubProcessExtendedEventTransactionNoneLoopNoneCompensate=0;
        int nSubProcessExtendedEventTransactionNoneLoopParallelCompensateNone=0;
        int nSubProcessExtendedEventTransactionNoneLoopParallelCompensate=0;
        int nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensateNone=0;
        int nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensate=0;
        int nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensateNone=0;
        int nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensate=0;
        int nSubProcessExtendedEventTransactionLoopNoneCompensateNone=0;
        int nSubProcessExtendedEventTransactionLoopNoneCompensate=0;
        int nSubProcessExtendedEventTransactionLoopParallelCompensateNone=0;
        int nSubProcessExtendedEventTransactionLoopParallelCompensate=0;
        int nSubProcessExtendedEventTransactionLoopMIParallelCompensateNone=0;
        int nSubProcessExtendedEventTransactionLoopMIParallelCompensate=0;
        int nSubProcessExtendedEventTransactionLoopMISequentialCompensateNone=0;
        int nSubProcessExtendedEventTransactionLoopMISequentialCompensate=0;
        int nSubProcessCollapsedEventNoneAdHocNoneLoopNoneCompensateNone=0;
        int nSubProcessCollapsedEventNoneAdHocNoneLoopNoneCompensate=0;
        int nSubProcessCollapsedEventNoneAdHocNoneLoopParallelCompensateNone=0;
        int nSubProcessCollapsedEventNoneAdHocNoneLoopParallelCompensate=0;
        int nSubProcessCollapsedEventNoneAdHocNoneLoopMIParallelCompensateNone=0;
        int nSubProcessCollapsedEventNoneAdHocNoneLoopMIParallelCompensate=0;
        int nSubProcessCollapsedEventNoneAdHocNoneLoopMISequentialCompensateNone=0;
        int nSubProcessCollapsedEventNoneAdHocNoneLoopMISequentialCompensate=0;
        int nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone=0;
        int nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate=0;
        int nSubProcessCollapsedEventNoneAdHocLoopParallelCompensateNone=0;
        int nSubProcessCollapsedEventNoneAdHocLoopParallelCompensate=0;
        int nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone=0;
        int nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate=0;
        int nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone=0;
        int nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate=0;
        int nSubProcessCollapsedEventNoneTransactionNoneLoopNoneCompensateNone=0;
        int nSubProcessCollapsedEventNoneTransactionNoneLoopNoneCompensate=0;
        int nSubProcessCollapsedEventNoneTransactionNoneLoopParallelCompensateNone=0;
        int nSubProcessCollapsedEventNoneTransactionNoneLoopParallelCompensate=0;
        int nSubProcessCollapsedEventNoneTransactionNoneLoopMIParallelCompensateNone=0;
        int nSubProcessCollapsedEventNoneTransactionNoneLoopMIParallelCompensate=0;
        int nSubProcessCollapsedEventNoneTransactionNoneLoopMISequentialCompensateNone=0;
        int nSubProcessCollapsedEventNoneTransactionNoneLoopMISequentialCompensate=0;
        int nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone=0;
        int nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate=0;
        int nSubProcessCollapsedEventNoneTransactionLoopParallelCompensateNone=0;
        int nSubProcessCollapsedEventNoneTransactionLoopParallelCompensate=0;
        int nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensateNone=0;
        int nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensate=0;
        int nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensateNone=0;
        int nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensate=0;
        int nSubProcessCollapsedEventAdHocNoneLoopNoneCompensateNone=0;
        int nSubProcessCollapsedEventAdHocNoneLoopNoneCompensate=0;
        int nSubProcessCollapsedEventAdHocNoneLoopParallelCompensateNone=0;
        int nSubProcessCollapsedEventAdHocNoneLoopParallelCompensate=0;
        int nSubProcessCollapsedEventAdHocNoneLoopMIParallelCompensateNone=0;
        int nSubProcessCollapsedEventAdHocNoneLoopMIParallelCompensate=0;
        int nSubProcessCollapsedEventAdHocNoneLoopMISequentialCompensateNone=0;
        int nSubProcessCollapsedEventAdHocNoneLoopMISequentialCompensate=0;
        int nSubProcessCollapsedEventAdHocLoopNoneCompensateNone=0;
        int nSubProcessCollapsedEventAdHocLoopNoneCompensate=0;
        int nSubProcessCollapsedEventAdHocLoopParallelCompensateNone=0;
        int nSubProcessCollapsedEventAdHocLoopParallelCompensate=0;
        int nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone=0;
        int nSubProcessCollapsedEventAdHocLoopMIParallelCompensate=0;
        int nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone=0;
        int nSubProcessCollapsedEventAdHocLoopMISequentialCompensate=0;
        int nSubProcessCollapsedEventTransactionNoneLoopNoneCompensateNone=0;
        int nSubProcessCollapsedEventTransactionNoneLoopNoneCompensate=0;
        int nSubProcessCollapsedEventTransactionNoneLoopParallelCompensateNone=0;
        int nSubProcessCollapsedEventTransactionNoneLoopParallelCompensate=0;
        int nSubProcessCollapsedEventTransactionNoneLoopMIParallelCompensateNone=0;
        int nSubProcessCollapsedEventTransactionNoneLoopMIParallelCompensate=0;
        int nSubProcessCollapsedEventTransactionNoneLoopMISequentialCompensateNone=0;
        int nSubProcessCollapsedEventTransactionNoneLoopMISequentialCompensate=0;
        int nSubProcessCollapsedEventTransactionLoopNoneCompensateNone=0;
        int nSubProcessCollapsedEventTransactionLoopNoneCompensate=0;
        int nSubProcessCollapsedEventTransactionLoopParallelCompensateNone=0;
        int nSubProcessCollapsedEventTransactionLoopParallelCompensate=0;
        int nSubProcessCollapsedEventTransactionLoopMIParallelCompensateNone=0;
        int nSubProcessCollapsedEventTransactionLoopMIParallelCompensate=0;
        int nSubProcessCollapsedEventTransactionLoopMISequentialCompensateNone=0;
        int nSubProcessCollapsedEventTransactionLoopMISequentialCompensate=0;
        // Data Object
        int nDataObject=0;
        int nDataObjectCollection=0;
        int nDataObjectReference=0;
        int nDataStore=0;
        int nDataInput=0;
        int nDataOutput=0;
        // Gateway
        int nExclusiveGatewayNoMarker=0;
        int nExclusiveGatewayMarker=0;
        int nParallelGateway=0;
        int nInclusiveGateway=0;
        int nEventBasedGateway=0;
        int nEventBasedGatewayExclusiveInstantiation=0;
        int nEventBasedGatewayParallelInstantiation=0;
        int nComplexGateway=0;
        //Events
        int nStartMultipleParallelEventDefinition=0;
        int nStartMultipleEventDefinition=0;
        int nStartNoneEventDefinition=0;
        int nStartSignalEventDefinition=0;
        int nStartConditionalEventDefinition=0;
        int nStartTimerEventDefinition=0;
        int nStartMessageEventDefinition=0;
        int nStartCompensateEventDefinition=0;
        int nStartEscalationEventDefinition=0;
        int nStartErrorEventDefinition=0;
        int nStartMessageEventSubProcessInterruptingDefinition=0;
        int nStartTimerEventSubProcessInterruptingDefinition=0;
        int nStartEscalationEventSubProcessInterruptingDefinition=0;
        int nStartConditionalEventSubProcessInterruptingDefinition=0;
        int nStartErrorEventSubProcessInterruptingDefinition=0;
        int nStartCompensateEventSubProcessInterruptingDefinition=0;
        int nStartSignalEventSubProcessInterruptingDefinition=0;
        int nStartMultipleEventSubProcessInterruptingDefinition=0;
        int nStartMultipleParallelEventSubProcessInterruptingDefinition=0;       
        int nStartMessageEventSubProcessNonInterruptingDefinition=0;
        int nStartTimerEventSubProcessNonInterruptingDefinition=0;
        int nStartEscalationEventSubProcessNonInterruptingDefinition=0;
        int nStartConditionalEventSubProcessNonInterruptingDefinition=0;
        int nStartSignalEventSubProcessNonInterruptingDefinition=0;
        int nStartMultipleParallelEventSubProcessNonInterruptingDefinition=0;
        int nStartMultipleEventSubProcessNonInterruptingDefinition=0;       
        int nEndNoneEventDefinition = 0;
        int nEndMultipleEventDefinition = 0; 
        int nEndEscalationEventDefinition= 0;
        int	nEndErrorEventDefinition=  0;
        int nEndSignalEventDefinition=  0;
        int nEndCompensateEventDefinition=  0;
        int nEndCancelEventDefinition=  0; 
        int nEndMessageEventDefinition=  0;
        int nEndTerminateEventDefinition=  0;
        int nIntermediateCatchMultipleEventDefinition=0;
        int nIntermediateCatchMultipleParallelEventDefinition=0;
        int nIntermediateCatchMessageEventDefinition=0;
        int nIntermediateCatchTimerEventDefinition=0;
        int nIntermediateCatchConditionalEventDefinition=0;
        int nIntermediateCatchLinkEventDefinition=0;
        int nIntermediateCatchSignalEventDefinition=0;
        int nIntermediateThrowNoneEventDefinition=0;
        int nIntermediateThrowMessageEventDefinition=0;
        int nIntermediateThrowEscalationEventDefinition=0;
        int nIntermediateThrowLinkEventDefinition=0;
        int nIntermediateThrowSignalEventDefinition=0;
        int nIntermediateThrowCompensateEventDefinition=0;
        int nIntermediateThrowMultipleParallelEventDefinition=0;
        int nIntermediateBoundaryMessageEvent=0;
        int nIntermediateBoundaryTimerEvent=0;
        int nIntermediateBoundaryCancelEvent=0;
        int nIntermediateBoundaryConditionalEvent =0;
        int nIntermediateBoundaryEscalationEvent=0;
        int nIntermediateBoundaryErrorEvent=0;
        int nIntermediateBoundarySignalEvent=0;
        int nIntermediateBoundaryCompensateEvent=0;
        int nIntermediateBoundaryMultipleEvent=0;
        int nIntermediateBoundaryMultipleParallelEvent=0;
        int nIntermediateBoundaryTimerEventNonInterrupting=0;
        int nIntermediateBoundaryEscalationEventNonInterrupting=0;
        int nIntermediateBoundaryConditionalEventNonInterrupting=0;
        int nIntermediateBoundaryMessageEventNonInterrupting=0;
        int nIntermediateBoundarySignalEventNonInterrupting=0;
        int nIntermediateBoundaryMultipleEventNonInterrupting=0;
        int nIntermediateBoundaryMultipleParallelEventNonInterrupting=0;
        //Flow
        int nMessageFlow=0;
        int nSequenceFlow=0;
        int nDefaultFlow=0;
        int nConditionalFlow=0;
        //Swimlanes
        int nLane=0;
        int nPoolCollapsedMultiplicityNone=0;
		int	nPoolCollapsedMultiplicity=0;
		int nPoolExpandedMultiplicityNone=0;
		int	nPoolExpandedMultiplicity=0;       
        //Choreography
        int nChoreographyTask=0;
        int nChoreographyTaskSequentialMultipleInstance=0;
        int nChoreographyTaskParallelMultipleInstance=0;
        int nChoreographyTaskLoop=0;
        int nChoreographySubprocessCollapsed=0;
        int nChoreographySubprocessCollapsedParallelMultipleInstance=0;
        int nChoreographySubprocessCollapsedSequentialMultipleInstance=0;
        int nChoreographySubprocessCollapsedLoop=0;
        int nChoreographySubprocessCollapsedCall=0;
        int nChoreographySubprocessCollapsedCallSequentialMultipleInstance=0;
        int nChoreographySubprocessCollapsedCallParallelMultipleInstance=0;
        int nChoreographySubprocessCollapsedCallLoop=0;
        int nChoreographySubprocessExpanded=0;
        int nChoreographySubprocessExpandedSequentialMultipleInstance=0;
        int nChoreographySubprocessExpandedParallelMultipleInstance=0;
        int nChoreographySubprocessExpandedLoop=0;
        int nChoreographyParticipant=0;
        int nChoreographyParticipantMultiple=0;   
        int nChoreographyMessage=0;
        //Conversation
        int nConversationNone=0;
        int nConversationSubProcess=0;
        int nConversationCall=0;
        int nConversationSubProcessCall=0;
        int nConversationLink=0;
        //Association
        int nAssociationCompensate=0;
        int nAssociationUndirected=0;
        int nAssociationUnidirectional=0;        
        int nAssociationBidirectional=0;
        int nAssociationDataOutput=0;
        int nAssociationDataInput=0;
        //Others
        int nGroup=0;
        int nTextAnnotation=0;
        int nOfExtensionElements=0;
        int TotalElements=0;
   
        //Set BPMN models name
        fileName= listOfFiles[x].getName();
          
    	//Read bpmn models
    	File xmlFile = new File("./testmodels/"+fileName);
    	String xml = new String(Files.readAllBytes(xmlFile.toPath()), StandardCharsets.UTF_8);       
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xml)));
        XPath xpath = XPathFactory.newInstance().newXPath();
        xpath.setNamespaceContext(new NamespaceContext() {

            @Override
            public Iterator getPrefixes(String arg0) {
                return null;
            }

            @Override
            public String getPrefix(String arg0) {
                return null;
            }

            @Override
            public String getNamespaceURI(String arg0) {
                if("bpmn".equals(arg0)) {
                    return "http://www.omg.org/spec/BPMN/20100524/MODEL";
                }
                return "http://www.omg.org/spec/BPMN/20100524/DI";
            }
        });
        
        // TRUE if model has labels in english
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpathLang = xPathfactory.newXPath();
        XPathExpression expr = xpathLang.compile("//@name");
        Object resultModelWords = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesModelWords = (NodeList) resultModelWords;
        ArrayList<String> modelWords = new ArrayList<String>();       
        
	        for(int a=0; a<nodesModelWords.getLength(); a++) {
	        	
	        	modelWords.add(nodesModelWords.item(a).getTextContent());
	        	JLanguageTool langTool = new JLanguageTool(new BritishEnglish());
	            List<RuleMatch> matches = langTool.check(modelWords.get(a));
	            isEnglish=true;
	            //If there is a word not in english, check this word and suggest correction
	            for (RuleMatch match : matches) {

//		              System.out.println("Potential error in model "+fileName+" at characters " +
//		                  match.getFromPos() + "-" + match.getToPos() + ": " +
//		                  match.getMessage());
//		              System.out.println("Suggested correction(s): " +
//		                  match.getSuggestedReplacements());
		              isEnglish=false;
		              break;		              
	            }	            
	        }
	        
	        
        // Check the modeler type
        if(doc.getDocumentElement().getAttributeNode("targetNamespace").getTextContent().contains("bpmn.io")) {
        	bpmnModeler = "bpmn-js";
        }
        else if (doc.getDocumentElement().getAttributeNode("targetNamespace").getTextContent().contains("signavio")) {
        	bpmnModeler = "Signavio";
        }
        else if (doc.getDocumentElement().getAttributeNode("targetNamespace").getTextContent().contains("camunda")) {
        	bpmnModeler = "Camunda";
        }
        else if(doc.getDocumentElement().getAttributeNode("targetNamespace").getTextContent().contains("bpmn2")) {
        	bpmnModeler = "BPMN2";
        }
        else {
        	bpmnModeler = "Undefined";
        }
 
        // Check if the model is a Collaboration, a Process or contain a Subprocess
        
        // Check if is a collaboration
        XPathExpression exprModelTypeCol = xpath.compile("//bpmn:definitions");
        Object resultModelType = exprModelTypeCol.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesModelType = (NodeList) resultModelType;       
        
        for(int i=0; i<nodesModelType.getLength(); i++) {
        	
        	Node ChildsModelType = nodesModelType.item(i);
        	
        	if(ChildsModelType.hasChildNodes()) {
        		
        		NodeList ChildModelType = ChildsModelType.getChildNodes();

        	for(int j=0;j<ChildModelType.getLength(); j++) {
        		
        		if(ChildModelType.item(j).getNodeType() == Node.ELEMENT_NODE) {            
             		
	        		String nodeModelType =  ChildModelType.item(j).getNodeName();
	        		
				        if(nodeModelType.contains("conversation")) {				        	
				        	modelType = "Conversation";
				        	break;		
				        }
				        
				        if(nodeModelType.contains("choreography") && 
				        		(nodeModelType.contains("conversation")) == false ){
							
							modelType = "Choreography";
							break;
						}
				        
				        if(nodeModelType.contains("collaboration")) {
				        	
				        	modelType = "Collaboration";
				        	//If i find the collaboration xml tag, i cant skip the for
				        	break;
				        } 
				        
				        if(nodeModelType.contains("conversation")) {
				        	
				        	modelType = "Conversation";
				        	//If i find the collaboration xml tag, i cant skip the for
				        	break;
				        } 
				        
		        		if((nodeModelType.contains("collaboration")) == false &&
		        		   (nodeModelType.contains("choreography")) == false &&
        				   (nodeModelType.contains("conversation")) == false && 
		        		    nodeModelType.contains("process")){
				        	modelType = "Process";
					        }                 
          	 
        		}
        	}
        	}
        }

//----------------------------------------------BPMN STATS-------------------------------------------------
// XPath Query for showing all Tasks markers
//  	  nTask
//        nTaskMultipleIstance
//        nTaskMultipleIstanceSequential
//        nTaskLoopActivity
        
//        //N° of normal tasks
//        XPathExpression exprTask = xpath.compile("//bpmn:task");
//        Object result = exprTask.evaluate(doc, XPathConstants.NODESET);
//        NodeList nodesTask = (NodeList) result;
//        doc.getDocumentElement().normalize(); 
//          
//          for(int i=0; i<nodesTask.getLength() ; i++) {
//          	
//          	Node TaskNode = nodesTask.item(i);   
//          	
//          	 
//          	if(TaskNode.hasChildNodes()) {                
//          		
//          		NodeList taskChildNodes = TaskNode.getChildNodes();
//          		         		
//                  for(int j=0;j<taskChildNodes.getLength(); j++) {
//                	 
//                	     
//	                  	if(taskChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {            
//	                  		
//	                  		if(taskChildNodes.item(j).getNodeName().contains("standardLoopCharacteristics")) {
//	                  			nTaskLoopActivity++;
//	                  		}
//	                  		
//	                  		if(taskChildNodes.item(j).getNodeName().contains("multiInstanceLoopCharacteristics")  && ((Element) taskChildNodes.item(j)).getAttribute("isSequential").contains("true")) {
//	                  			nTaskMultipleInstanceSequential++;
//
//	                  		}	
//	                  		else if(taskChildNodes.item(j).getNodeName().contains("multiInstanceLoopCharacteristics"))
//	                  			nTaskMultipleInstanceParallel++;	                  	
//	                  	}
//                  }
//          		
//          	}
//          	
//          	
//          }
//          
        // SUBPROCESSES
        //N° subProcess 
        XPathExpression exprSubprocesses = xpath.compile("//bpmn:subProcess[not(contains(@triggeredByEvent,'true'))]");
        Object resultSubprocesses  = exprSubprocesses.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesSubprocesses = (NodeList) resultSubprocesses;
        doc.getDocumentElement().normalize();  
        
        //N° subProcess Event
        XPathExpression exprSubprocessesEvent = xpath.compile("//bpmn:subProcess[@triggeredByEvent='true']");
        Object resultSubprocessesEvent  = exprSubprocessesEvent.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesSubprocessesEvent = (NodeList) resultSubprocessesEvent;
        doc.getDocumentElement().normalize(); 
        
        //N° subProcess AdHoc
        XPathExpression exprSubprocessesAdHoc = xpath.compile("//bpmn:adHocSubProcess");
        Object resultSubprocessesAdHoc  = exprSubprocessesAdHoc.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesSubprocessesAdHoc = (NodeList) resultSubprocessesAdHoc;
        doc.getDocumentElement().normalize(); 
        
        //N° Transaction
        XPathExpression exprTransaction = xpath.compile("//bpmn:transaction");
        Object resultTransaction  = exprTransaction.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTransaction = (NodeList) resultTransaction;
        doc.getDocumentElement().normalize(); 
        
        
        xpath.setNamespaceContext(new NamespaceContext() {

            @Override
            public Iterator getPrefixes(String arg0) {
                return null;
            }

            @Override
            public String getPrefix(String arg0) {
                return null;
            }

            @Override
            public String getNamespaceURI(String arg0) {
                if("bpmn".equals(arg0)) {
                    return "http://www.omg.org/spec/BPMN/20100524/DI";
                }
                return null;
            }
        });
        
        XPathExpression exprSubprocessesShapes = xpath.compile("//bpmn:BPMNShape");
        Object resultSubprocessesShapes  = exprSubprocessesShapes.evaluate(doc, XPathConstants.NODESET);       
        NodeList nodesSubprocessesShapes = (NodeList) resultSubprocessesShapes;
        doc.getDocumentElement().normalize();               
        	
        // SubProcess
        	for(int i=0;i<nodesSubprocesses.getLength();i++) {
        		String SubprocessesID = (((Element) nodesSubprocesses.item(i)).getAttribute("id"));
        		
        		for(int j=0;j<nodesSubprocessesShapes.getLength();j++) {
        		String SubprocessesShape = (((Element) nodesSubprocessesShapes.item(j)).getAttribute("bpmnElement"));
        		
        			if(SubprocessesID.equalsIgnoreCase(SubprocessesShape)) {
        				/*
        		        int nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensateNone=0;
        		        int nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensate=0;
        		        int nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensateNone=0;
        		        int nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensate=0;
        		        int nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensateNone=0;
        		        int nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensate=0;
        		        int nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensateNone=0;
        		        int nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensate=0;
						
        		        int nSubProcessCollapsedEventNoneAdHocNoneLoopNoneCompensateNone=0;
		                int nSubProcessCollapsedEventNoneAdHocNoneLoopNoneCompensate=0;
		                int nSubProcessCollapsedEventNoneAdHocNoneLoopParallelCompensateNone=0;
		                int nSubProcessCollapsedEventNoneAdHocNoneLoopParallelCompensate=0;
		                int nSubProcessCollapsedEventNoneAdHocNoneLoopMIParallelCompensateNone=0;
		                int nSubProcessCollapsedEventNoneAdHocNoneLoopMIParallelCompensate=0;
		                int nSubProcessCollapsedEventNoneAdHocNoneLoopMISequentialCompensateNone=0;
		                int nSubProcessCollapsedEventNoneAdHocNoneLoopMISequentialCompensate=0;
        				int nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensateNone=0;
        		        int nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensate=0;
        		        int nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensateNone=0;
        		        int nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensate=0;
        		        int nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensateNone=0;
        		        int nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensate=0;
        		        int nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensateNone=0;
        		        int nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensate=0; */
        				
		        		//SubProcess Event
        				/*int nSubProcessExtendedEventAdHocNoneLoopNoneCompensateNone=0;
        		        int nSubProcessExtendedEventAdHocNoneLoopNoneCompensate=0;
        		        int nSubProcessExtendedEventAdHocNoneLoopParallelCompensateNone=0;
        		        int nSubProcessExtendedEventAdHocNoneLoopParallelCompensate=0;
        		        int nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensateNone=0;
        		        int nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensate=0;
        		        int nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensateNone=0;
        		        int nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensate=0;
        		        int nSubProcessExtendedEventAdHocLoopNoneCompensateNone=0;
        		        int nSubProcessExtendedEventAdHocLoopNoneCompensate=0;
        		        int nSubProcessExtendedEventAdHocLoopParallelCompensateNone=0;
        		        int nSubProcessExtendedEventAdHocLoopParallelCompensate=0;
        		        int nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone=0;
        		        int nSubProcessExtendedEventAdHocLoopMIParallelCompensate=0;
        		        int nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone=0;
        		        int nSubProcessExtendedEventAdHocLoopMISequentialCompensate=0;
        		        int nSubProcessExtendedEventTransactionNoneLoopNoneCompensateNone=0;
        		        int nSubProcessExtendedEventTransactionNoneLoopNoneCompensate=0;
        		        int nSubProcessExtendedEventTransactionNoneLoopParallelCompensateNone=0;
        		        int nSubProcessExtendedEventTransactionNoneLoopParallelCompensate=0;
        		        int nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensateNone=0;
        		        int nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensate=0;
        		        int nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensateNone=0;
        		        int nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensate=0;*/
		        		
		        		//SubProcess AdHoc
        				/* int nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone=0;
        		        int nSubProcessExtendedEventNoneAdHocLoopNoneCompensate=0;
        		        int nSubProcessExtendedEventNoneAdHocLoopParallelCompensateNone=0;
        		        int nSubProcessExtendedEventNoneAdHocLoopParallelCompensate=0;
        		        int nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone=0;
        		        int nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate=0;
        		        int nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone=0;
        		        int nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate=0; */
        				
        				
        				//Transaction
        				
        			  /*
        		        int nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone=0;
        		        int nSubProcessExtendedEventNoneTransactionLoopNoneCompensate=0;
        		        int nSubProcessExtendedEventNoneTransactionLoopParallelCompensateNone=0;
        		        int nSubProcessExtendedEventNoneTransactionLoopParallelCompensate=0;
        		        int nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone=0;
        		        int nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate=0;
        		        int nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone=0;
        		        int nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate=0; 
        		        int nSubProcessExtendedEventTransactionLoopNoneCompensateNone=0;
        		        int nSubProcessExtendedEventTransactionLoopNoneCompensate=0;
        		        int nSubProcessExtendedEventTransactionLoopParallelCompensateNone=0;
        		        int nSubProcessExtendedEventTransactionLoopParallelCompensate=0;
        		        int nSubProcessExtendedEventTransactionLoopMIParallelCompensateNone=0;
        		        int nSubProcessExtendedEventTransactionLoopMIParallelCompensate=0;
        		        int nSubProcessExtendedEventTransactionLoopMISequentialCompensateNone=0;
        		        int nSubProcessExtendedEventTransactionLoopMISequentialCompensate=0; 
        				int nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone=0;
		                int nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate=0;
		                int nSubProcessCollapsedEventNoneTransactionLoopParallelCompensateNone=0;
		                int nSubProcessCollapsedEventNoneTransactionLoopParallelCompensate=0;
		                int nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensateNone=0;
		                int nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensate=0;
		                int nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensateNone=0;
		                int nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensate=0;
		                int nSubProcessCollapsedEventTransactionLoopNoneCompensateNone=0;
		                int nSubProcessCollapsedEventTransactionLoopNoneCompensate=0;
		                int nSubProcessCollapsedEventTransactionLoopParallelCompensateNone=0;
		                int nSubProcessCollapsedEventTransactionLoopParallelCompensate=0;
		                int nSubProcessCollapsedEventTransactionLoopMIParallelCompensateNone=0;
		                int nSubProcessCollapsedEventTransactionLoopMIParallelCompensate=0;
		                int nSubProcessCollapsedEventTransactionLoopMISequentialCompensateNone=0;
		                int nSubProcessCollapsedEventTransactionLoopMISequentialCompensate=0; */
		                
		           /*   
		                int nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone=0;
		                int nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate=0;
		                int nSubProcessCollapsedEventNoneAdHocLoopParallelCompensateNone=0;
		                int nSubProcessCollapsedEventNoneAdHocLoopParallelCompensate=0;
		                int nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone=0;
		                int nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate=0;
		                int nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone=0;
		                int nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate=0;
		                int nSubProcessCollapsedEventNoneTransactionNoneLoopNoneCompensateNone=0;
		                int nSubProcessCollapsedEventNoneTransactionNoneLoopNoneCompensate=0;
		                int nSubProcessCollapsedEventNoneTransactionNoneLoopParallelCompensateNone=0;
		                int nSubProcessCollapsedEventNoneTransactionNoneLoopParallelCompensate=0;
		                int nSubProcessCollapsedEventNoneTransactionNoneLoopMIParallelCompensateNone=0;
		                int nSubProcessCollapsedEventNoneTransactionNoneLoopMIParallelCompensate=0;
		                int nSubProcessCollapsedEventNoneTransactionNoneLoopMISequentialCompensateNone=0;
		                int nSubProcessCollapsedEventNoneTransactionNoneLoopMISequentialCompensate=0;
		                
		                int nSubProcessCollapsedEventAdHocNoneLoopNoneCompensateNone=0;
		                int nSubProcessCollapsedEventAdHocNoneLoopNoneCompensate=0;
		                int nSubProcessCollapsedEventAdHocNoneLoopParallelCompensateNone=0;
		                int nSubProcessCollapsedEventAdHocNoneLoopParallelCompensate=0;
		                int nSubProcessCollapsedEventAdHocNoneLoopMIParallelCompensateNone=0;
		                int nSubProcessCollapsedEventAdHocNoneLoopMIParallelCompensate=0;
		                int nSubProcessCollapsedEventAdHocNoneLoopMISequentialCompensateNone=0;
		                int nSubProcessCollapsedEventAdHocNoneLoopMISequentialCompensate=0;
		                int nSubProcessCollapsedEventAdHocLoopNoneCompensateNone=0;
		                int nSubProcessCollapsedEventAdHocLoopNoneCompensate=0;
		                int nSubProcessCollapsedEventAdHocLoopParallelCompensateNone=0;
		                int nSubProcessCollapsedEventAdHocLoopParallelCompensate=0;
		                int nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone=0;
		                int nSubProcessCollapsedEventAdHocLoopMIParallelCompensate=0;
		                int nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone=0;
		                int nSubProcessCollapsedEventAdHocLoopMISequentialCompensate=0;
		                int nSubProcessCollapsedEventTransactionNoneLoopNoneCompensateNone=0;
		                int nSubProcessCollapsedEventTransactionNoneLoopNoneCompensate=0;
		                int nSubProcessCollapsedEventTransactionNoneLoopParallelCompensateNone=0;
		                int nSubProcessCollapsedEventTransactionNoneLoopParallelCompensate=0;
		                int nSubProcessCollapsedEventTransactionNoneLoopMIParallelCompensateNone=0;
		                int nSubProcessCollapsedEventTransactionNoneLoopMIParallelCompensate=0;
		                int nSubProcessCollapsedEventTransactionNoneLoopMISequentialCompensateNone=0;
		                int nSubProcessCollapsedEventTransactionNoneLoopMISequentialCompensate=0;
*/
		        		
        			}
        		}
        	}
        	
        	 xpath.setNamespaceContext(new NamespaceContext() {

                 @Override
                 public Iterator getPrefixes(String arg0) {
                     return null;
                 }

                 @Override
                 public String getPrefix(String arg0) {
                     return null;
                 }

                 @Override
                 public String getNamespaceURI(String arg0) {
                     if("bpmn".equals(arg0)) {
                         return "http://www.omg.org/spec/BPMN/20100524/MODEL";
                     }
                     return null;
                 }
             });

          // This is a counter to detect a general SubProcess extended 
        	nGeneralSubProcess = nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensateNone+
				        nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensate+
				        nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensateNone+
				        nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensate+
				        nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensateNone+
				        nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensate+
				        nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensateNone+
				        nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensate+
				        nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone+
				        nSubProcessExtendedEventNoneAdHocLoopNoneCompensate+
				        nSubProcessExtendedEventNoneAdHocLoopParallelCompensateNone+
				        nSubProcessExtendedEventNoneAdHocLoopParallelCompensate+
				        nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone+
				        nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate+
				        nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone+
				        nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate+
				        nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensateNone+
				        nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensate+
				        nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensateNone+
				        nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensate+
				        nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensateNone+
				        nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensate+
				        nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensateNone+
				        nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensate+
				        nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone+
				        nSubProcessExtendedEventNoneTransactionLoopNoneCompensate+
				        nSubProcessExtendedEventNoneTransactionLoopParallelCompensateNone+
				        nSubProcessExtendedEventNoneTransactionLoopParallelCompensate+
				        nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone+
				        nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate+
				        nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone+
				        nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate+
				        nSubProcessExtendedEventAdHocNoneLoopNoneCompensateNone+
				        nSubProcessExtendedEventAdHocNoneLoopNoneCompensate+
				        nSubProcessExtendedEventAdHocNoneLoopParallelCompensateNone+
				        nSubProcessExtendedEventAdHocNoneLoopParallelCompensate+
				        nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensateNone+
				        nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensate+
				        nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensateNone+
				        nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensate+
				        nSubProcessExtendedEventAdHocLoopNoneCompensateNone+
				        nSubProcessExtendedEventAdHocLoopNoneCompensate+
				        nSubProcessExtendedEventAdHocLoopParallelCompensateNone+
				        nSubProcessExtendedEventAdHocLoopParallelCompensate+
				        nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone+
				        nSubProcessExtendedEventAdHocLoopMIParallelCompensate+
				        nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone+
				        nSubProcessExtendedEventAdHocLoopMISequentialCompensate+
				        nSubProcessExtendedEventTransactionNoneLoopNoneCompensateNone+
				        nSubProcessExtendedEventTransactionNoneLoopNoneCompensate+
				        nSubProcessExtendedEventTransactionNoneLoopParallelCompensateNone+
				        nSubProcessExtendedEventTransactionNoneLoopParallelCompensate+
				        nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensateNone+
				        nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensate+
				        nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensateNone+
				        nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensate+
				        nSubProcessExtendedEventTransactionLoopNoneCompensateNone+
				        nSubProcessExtendedEventTransactionLoopNoneCompensate+
				        nSubProcessExtendedEventTransactionLoopParallelCompensateNone+
				        nSubProcessExtendedEventTransactionLoopParallelCompensate+
				        nSubProcessExtendedEventTransactionLoopMIParallelCompensateNone+
				        nSubProcessExtendedEventTransactionLoopMIParallelCompensate+
				        nSubProcessExtendedEventTransactionLoopMISequentialCompensateNone+
				        nSubProcessExtendedEventTransactionLoopMISequentialCompensate;

        	
        //All Task none        

        XPathExpression exprTask11 = xpath.compile("//bpmn:task[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[@isSequential='false']");
        Object resultTask11 = exprTask11.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask11 = (NodeList) resultTask11;
        doc.getDocumentElement().normalize();  
        nTaskNoneLoopMIParallelCompensateCallNone = nodesTask11.getLength();
        
        XPathExpression exprTask9 = xpath.compile("//bpmn:task[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[@isSequential='false']");
        Object resultTask9 = exprTask9.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask9 = (NodeList) resultTask9;
        doc.getDocumentElement().normalize();  
        nTaskNoneLoopMIParallelCompensateNoneCallNone = nodesTask9.getLength();
        
        XPathExpression exprTask12= xpath.compile("//bpmn:callActivity[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[@isSequential='false']");
        Object resultTask12 = exprTask12.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask12 = (NodeList) resultTask12;
        doc.getDocumentElement().normalize();  
        nTaskNoneLoopMIParallelCompensateCall = nodesTask12.getLength();

        XPathExpression exprTask15 = xpath.compile("//bpmn:task[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[@isSequential='true']");
        Object resultTask15= exprTask15.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask15 = (NodeList) resultTask15;
        doc.getDocumentElement().normalize();  
        nTaskNoneLoopMISequentialCompensateCallNone = nodesTask15.getLength();

        XPathExpression exprTask16= xpath.compile("//bpmn:callActivity[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[@isSequential='true']");
        Object resultTask16 = exprTask16.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask16 = (NodeList) resultTask16;
        doc.getDocumentElement().normalize();  
        nTaskNoneLoopMISequentialCompensateCall = nodesTask16.getLength();

        XPathExpression exprTask6 = xpath.compile("//bpmn:callActivity[@isForCompensation='true']//bpmn:standardLoopCharacteristics");
        Object resultTask6 = exprTask6.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask6 = (NodeList) resultTask6;
        doc.getDocumentElement().normalize();  
        nTaskNoneLoopStandardCompensateNoneCall = nodesTask6.getLength();      
        
        XPathExpression exprTask7 = xpath.compile("//bpmn:task[@isForCompensation='true']//bpmn:standardLoopCharacteristics");
        Object resultTask7 = exprTask7.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask7 = (NodeList) resultTask7;
        doc.getDocumentElement().normalize();  
        nTaskNoneLoopStandardCompensateCallNone = nodesTask7.getLength();
        
        XPathExpression exprTask10 = xpath.compile("//bpmn:callActivity[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[@isSequential='false']");
        Object resultTask10 = exprTask10.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask10 = (NodeList) resultTask10;
        doc.getDocumentElement().normalize();  
        nTaskNoneLoopMIParallelCompensateNoneCall = nodesTask10.getLength();
        
        XPathExpression exprTask13 = xpath.compile("//bpmn:task[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[@isSequential='true']");
        Object resultTask13 = exprTask13.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask13 = (NodeList) resultTask13;
        doc.getDocumentElement().normalize();  
        nTaskNoneLoopMISequentialCompensateNoneCallNone = nodesTask13.getLength();
        
        XPathExpression exprTask14 = xpath.compile("//bpmn:callActivity[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[@isSequential='true']");
        Object resultTask14 = exprTask14.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask14 = (NodeList) resultTask14;
        doc.getDocumentElement().normalize();  
        nTaskNoneLoopMISequentialCompensateNoneCall = nodesTask14.getLength();       
        
        XPathExpression exprTask8 = xpath.compile("//bpmn:callActivity[@isForCompensation='true']//bpmn:standardLoopCharacteristics");
        Object resultTask8 = exprTask8.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask8 = (NodeList) resultTask8;
        doc.getDocumentElement().normalize();  
        nTaskNoneLoopStandardCompensateCall = nodesTask8.getLength();
        
        XPathExpression exprTask5 = xpath.compile("//bpmn:task[not(contains(@isForCompensation,'true'))]//bpmn:standardLoopCharacteristics");
        Object resultTask5 = exprTask5.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask5 = (NodeList) resultTask5;
        doc.getDocumentElement().normalize();  
        nTaskNoneLoopStandardCompensateNoneCallNone = nodesTask5.getLength();
        
        XPathExpression exprTask3 = xpath.compile("//bpmn:task[@isForCompensation='true']");
        Object resultTask3 = exprTask3.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask3 = (NodeList) resultTask3;
        doc.getDocumentElement().normalize();  
        nTaskNoneLoopNoneCompensateCallNone = nodesTask3.getLength() 
        	 - nTaskNoneLoopStandardCompensateCallNone - nTaskNoneLoopMISequentialCompensateCallNone 
        	 - nTaskNoneLoopMIParallelCompensateCallNone;
        
        XPathExpression exprTask4 = xpath.compile("//bpmn:callActivity[@isForCompensation='true']");
        Object resultTask4 = exprTask4.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask4 = (NodeList) resultTask4;
        doc.getDocumentElement().normalize();  
        nTaskNoneLoopNoneCompensateCall = nodesTask4.getLength() 
        		- nTaskNoneLoopStandardCompensateCall - nTaskNoneLoopMISequentialCompensateCall 
           	    - nTaskNoneLoopMIParallelCompensateCall;           
        
        XPathExpression exprTask = xpath.compile("//bpmn:task");
        Object resultTask = exprTask.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask = (NodeList) resultTask;
        doc.getDocumentElement().normalize();  
        nTaskNoneLoopNoneCompensateNoneCallNone = nodesTask.getLength() 
        	- (nTaskNoneLoopNoneCompensateCallNone + nTaskNoneLoopStandardCompensateNoneCallNone  
                    + nTaskNoneLoopMIParallelCompensateCallNone + nTaskNoneLoopMISequentialCompensateNoneCallNone
                	+ nTaskNoneLoopMISequentialCompensateCallNone + nTaskNoneLoopMIParallelCompensateNoneCallNone
                	+ nTaskNoneLoopStandardCompensateCallNone); 	
        
        XPathExpression exprTask2 = xpath.compile("//bpmn:callActivity");
        Object resultTask2 = exprTask2.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask2 = (NodeList) resultTask2;
        doc.getDocumentElement().normalize();  
        nTaskNoneLoopNoneCompensateNoneCall = nodesTask2.getLength() 
        		- (nTaskNoneLoopNoneCompensateCall + nTaskNoneLoopMIParallelCompensateNoneCall 
                		+ nTaskNoneLoopMIParallelCompensateCall + nTaskNoneLoopMISequentialCompensateNoneCall
                		+ nTaskNoneLoopMISequentialCompensateCall + nTaskNoneLoopStandardCompensateNoneCall 
                		+ nTaskNoneLoopStandardCompensateCall);       
        
        //All Task send
        XPathExpression exprTask17 = xpath.compile("//bpmn:sendTask[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[@isSequential='false']");
        Object resultTask17 = exprTask17.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask17 = (NodeList) resultTask17;
        doc.getDocumentElement().normalize();  
        nTaskSendLoopMIParallelCompensate = nodesTask17.getLength();
        
        XPathExpression exprTask18= xpath.compile("//bpmn:sendTask[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[@isSequential='false']");
        Object resultTask18  = exprTask18.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask18 = (NodeList) resultTask18;
        doc.getDocumentElement().normalize();  
        nTaskSendLoopMIParallelCompensateNone = nodesTask18.getLength();

        XPathExpression exprTask20 = xpath.compile("//bpmn:sendTask[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[@isSequential='true']");
        Object resultTask20= exprTask20.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask20 = (NodeList) resultTask20;
        doc.getDocumentElement().normalize();  
        nTaskSendLoopMISequentialCompensate = nodesTask20.getLength();    
        
        XPathExpression exprTask23 = xpath.compile("//bpmn:sendTask[@isForCompensation='true']//bpmn:standardLoopCharacteristics");
        Object resultTask23 = exprTask23.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask23 = (NodeList) resultTask23;
        doc.getDocumentElement().normalize();  
        nTaskSendLoopStandardCompensate = nodesTask23.getLength();
        
        XPathExpression exprTask25 = xpath.compile("//bpmn:sendTask[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[@isSequential='true']");
        Object resultTask25 = exprTask25.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask25 = (NodeList) resultTask25;
        doc.getDocumentElement().normalize();  
        nTaskSendLoopMISequentialCompensateNone = nodesTask25.getLength();     
        
        XPathExpression exprTask28 = xpath.compile("//bpmn:sendTask[not(contains(@isForCompensation,'true'))]//bpmn:standardLoopCharacteristics");
        Object resultTask28 = exprTask28.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask28 = (NodeList) resultTask28;
        doc.getDocumentElement().normalize();  
        nTaskSendLoopStandardCompensateNone = nodesTask28.getLength();
        
        XPathExpression exprTask29 = xpath.compile("//bpmn:sendTask[@isForCompensation='true']");
        Object resultTask29 = exprTask29.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask29 = (NodeList) resultTask29;
        doc.getDocumentElement().normalize();  
        nTaskSendLoopNoneCompensate = nodesTask29.getLength() 
        	 - nTaskSendLoopStandardCompensate - nTaskSendLoopMISequentialCompensate 
        	 - nTaskSendLoopMIParallelCompensate;         
        
        XPathExpression exprTask31 = xpath.compile("//bpmn:sendTask");
        Object resultTask31 = exprTask31.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask31 = (NodeList) resultTask31;
        doc.getDocumentElement().normalize();  
        nTaskSendLoopNoneCompensateNone = nodesTask31.getLength() 
        	- (nTaskSendLoopNoneCompensate + nTaskSendLoopStandardCompensateNone  
                    + nTaskSendLoopMIParallelCompensate + nTaskSendLoopMISequentialCompensateNone
                	+ nTaskSendLoopMISequentialCompensate + nTaskSendLoopMIParallelCompensateNone
                	+ nTaskSendLoopStandardCompensate); 	   
        
        //N° of receive tasks
        XPathExpression exprTaskS1 = xpath.compile("//bpmn:receiveTask[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[@isSequential='false']");
        Object resultTaskS1 = exprTaskS1.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskS1 = (NodeList) resultTaskS1;
        doc.getDocumentElement().normalize();  
        nTaskReceiveLoopMIParallelCompensate = nodesTaskS1.getLength();
        
        XPathExpression exprTaskS2= xpath.compile("//bpmn:receiveTask[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[@isSequential='false']");
        Object resultTaskS2 = exprTaskS2.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskS2 = (NodeList) resultTaskS2;
        doc.getDocumentElement().normalize();  
        nTaskReceiveLoopMIParallelCompensateNone = nodesTaskS2.getLength();

        XPathExpression exprTaskS3 = xpath.compile("//bpmn:receiveTask[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[@isSequential='true']");
        Object resultTaskS3= exprTaskS3.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskS3 = (NodeList) resultTaskS3;
        doc.getDocumentElement().normalize();  
        nTaskReceiveLoopMISequentialCompensate = nodesTaskS3.getLength();    
        
        XPathExpression exprTaskS4 = xpath.compile("//bpmn:receiveTask[@isForCompensation='true']//bpmn:standardLoopCharacteristics");
        Object resultTaskS4= exprTaskS4.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskS4= (NodeList) resultTaskS4;
        doc.getDocumentElement().normalize();  
        nTaskReceiveLoopStandardCompensate = nodesTaskS4.getLength();
        
        XPathExpression exprTaskS5 = xpath.compile("//bpmn:receiveTask[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[@isSequential='true']");
        Object resultTaskS5 = exprTaskS5.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskS5 = (NodeList) resultTaskS5;
        doc.getDocumentElement().normalize();  
        nTaskReceiveLoopMISequentialCompensateNone = nodesTaskS5.getLength();     
        
        XPathExpression exprTaskS6 = xpath.compile("//bpmn:receiveTask[not(contains(@isForCompensation,'true'))]//bpmn:standardLoopCharacteristics");
        Object resultTaskS6 = exprTaskS6.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskS6 = (NodeList) resultTaskS6;
        doc.getDocumentElement().normalize();  
        nTaskReceiveLoopStandardCompensateNone = nodesTaskS6.getLength();
        
        XPathExpression exprTaskS7 = xpath.compile("//bpmn:receiveTask[@isForCompensation='true']");
        Object resultTaskS7 = exprTaskS7.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskS7 = (NodeList) resultTaskS7;
        doc.getDocumentElement().normalize();  
        nTaskReceiveLoopNoneCompensate = nodesTaskS7.getLength() 
        	 - nTaskReceiveLoopStandardCompensate - nTaskReceiveLoopMISequentialCompensate 
        	 - nTaskReceiveLoopMIParallelCompensate;         
        
        XPathExpression exprTaskS8 = xpath.compile("//bpmn:receiveTask");
        Object resultTaskS8 = exprTaskS8.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskS8 = (NodeList) resultTaskS8;
        doc.getDocumentElement().normalize();  
        nTaskReceiveLoopNoneCompensateNone = nodesTaskS8.getLength() 
        	- (nTaskReceiveLoopNoneCompensate + nTaskReceiveLoopStandardCompensateNone  
                    + nTaskReceiveLoopMIParallelCompensate + nTaskReceiveLoopMISequentialCompensateNone
                	+ nTaskReceiveLoopMISequentialCompensate + nTaskReceiveLoopMIParallelCompensateNone
                	+ nTaskReceiveLoopStandardCompensate);
        
      //All Task user
	    XPathExpression exprTaskU1 = xpath.compile("//bpmn:userTask[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[@isSequential='false']");
	    Object resultTaskU1 = exprTaskU1.evaluate(doc, XPathConstants.NODESET);
	    NodeList nodesTaskU1 = (NodeList) resultTaskU1;
	    doc.getDocumentElement().normalize();  
	    nTaskUserLoopMIParallelCompensate = nodesTaskU1.getLength();
	    
	    XPathExpression exprTaskU2= xpath.compile("//bpmn:userTask[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[@isSequential='false']");
	    Object resultTaskU2  = exprTaskU2.evaluate(doc, XPathConstants.NODESET);
	    NodeList nodesTaskU2 = (NodeList) resultTaskU2;
	    doc.getDocumentElement().normalize();  
	    nTaskUserLoopMIParallelCompensateNone = nodesTaskU2.getLength();
	
	    XPathExpression exprTaskU3 = xpath.compile("//bpmn:userTask[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[@isSequential='true']");
	    Object resultTaskU3= exprTaskU3.evaluate(doc, XPathConstants.NODESET);
	    NodeList nodesTaskU3 = (NodeList) resultTaskU3;
	    doc.getDocumentElement().normalize();  
	    nTaskUserLoopMISequentialCompensate = nodesTaskU3.getLength();    
	    
	    XPathExpression exprTaskU4 = xpath.compile("//bpmn:userTask[@isForCompensation='true']//bpmn:standardLoopCharacteristics");
	    Object resultTaskU4 = exprTaskU4.evaluate(doc, XPathConstants.NODESET);
	    NodeList nodesTaskU4 = (NodeList) resultTaskU4;
	    doc.getDocumentElement().normalize();  
	    nTaskUserLoopStandardCompensate = nodesTaskU4.getLength();
	    
	    XPathExpression exprTaskU5 = xpath.compile("//bpmn:userTask[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[@isSequential='true']");
	    Object resultTaskU5 = exprTaskU5.evaluate(doc, XPathConstants.NODESET);
	    NodeList nodesTaskU5 = (NodeList) resultTaskU5;
	    doc.getDocumentElement().normalize();  
	    nTaskUserLoopMISequentialCompensateNone = nodesTaskU5.getLength();     
	    
	    XPathExpression exprTaskU6 = xpath.compile("//bpmn:userTask[not(contains(@isForCompensation,'true'))]//bpmn:standardLoopCharacteristics");
	    Object resultTaskU6 = exprTask28.evaluate(doc, XPathConstants.NODESET);
	    NodeList nodesTaskU6 = (NodeList) resultTaskU6;
	    doc.getDocumentElement().normalize();  
	    nTaskUserLoopStandardCompensateNone = nodesTaskU6.getLength();
	    
	    XPathExpression exprTaskU7= xpath.compile("//bpmn:userTask[@isForCompensation='true']");
	    Object resultTaskU7 = exprTaskU7.evaluate(doc, XPathConstants.NODESET);
	    NodeList nodesTaskU7 = (NodeList) resultTaskU7;
	    doc.getDocumentElement().normalize();  
	    nTaskUserLoopNoneCompensate = nodesTaskU7.getLength() 
	    	 - nTaskUserLoopStandardCompensate - nTaskUserLoopMISequentialCompensate 
	    	 - nTaskUserLoopMIParallelCompensate;         
	    
	    XPathExpression exprTaskU8 = xpath.compile("//bpmn:userTask");
	    Object resultTaskU8 = exprTaskU8.evaluate(doc, XPathConstants.NODESET);
	    NodeList nodesTaskU8 = (NodeList) resultTaskU8;
	    doc.getDocumentElement().normalize();  
	    nTaskUserLoopNoneCompensateNone = nodesTaskU8.getLength() 
	    	- (nTaskUserLoopNoneCompensate + nTaskUserLoopStandardCompensateNone  
	                + nTaskUserLoopMIParallelCompensate + nTaskUserLoopMISequentialCompensateNone
	            	+ nTaskUserLoopMISequentialCompensate + nTaskUserLoopMIParallelCompensateNone
	            	+ nTaskUserLoopStandardCompensate);
        
	    //All Task manual
        XPathExpression exprTaskM1 = xpath.compile("//bpmn:manualTask[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[@isSequential='false']");
        Object resultTaskM1 = exprTaskM1.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskM1 = (NodeList) resultTaskM1;
        doc.getDocumentElement().normalize();  
        nTaskManualLoopMIParallelCompensate = nodesTaskM1.getLength();
        
        XPathExpression exprTaskM2= xpath.compile("//bpmn:manualTask[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[@isSequential='false']");
        Object resultTaskM2  = exprTaskM2.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskM2 = (NodeList) resultTaskM2;
        doc.getDocumentElement().normalize();  
        nTaskManualLoopMIParallelCompensateNone = nodesTaskM2.getLength();

        XPathExpression exprTaskM3 = xpath.compile("//bpmn:manualTask[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[@isSequential='true']");
        Object resultTaskM3= exprTaskM3.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskM3 = (NodeList) resultTaskM3;
        doc.getDocumentElement().normalize();  
        nTaskManualLoopMISequentialCompensate = nodesTaskM3.getLength();    
        
        XPathExpression exprTaskM4 = xpath.compile("//bpmn:manualTask[@isForCompensation='true']//bpmn:standardLoopCharacteristics");
        Object resultTaskM4 = exprTaskM4.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskM4 = (NodeList) resultTaskM4;
        doc.getDocumentElement().normalize();  
        nTaskManualLoopStandardCompensate = nodesTaskM4.getLength();
        
        XPathExpression exprTaskM5 = xpath.compile("//bpmn:manualTask[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[@isSequential='true']");
        Object resultTaskM5 = exprTaskM5.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskM5 = (NodeList) resultTaskM5;
        doc.getDocumentElement().normalize();  
        nTaskManualLoopMISequentialCompensateNone = nodesTaskM5.getLength();     
        
        XPathExpression exprTaskM6 = xpath.compile("//bpmn:manualTask[not(contains(@isForCompensation,'true'))]//bpmn:standardLoopCharacteristics");
        Object resultTaskM6 = exprTaskM6.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskM6 = (NodeList) resultTaskM6;
        doc.getDocumentElement().normalize();  
        nTaskManualLoopStandardCompensateNone = nodesTaskM6.getLength();
        
        XPathExpression exprTaskM7 = xpath.compile("//bpmn:manualTask[@isForCompensation='true']");
        Object resultTaskM7= exprTaskM7.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskM7 = (NodeList) resultTaskM7;
        doc.getDocumentElement().normalize();  
        nTaskManualLoopNoneCompensate = nodesTaskM7.getLength() 
        	 - nTaskManualLoopStandardCompensate - nTaskManualLoopMISequentialCompensate 
        	 - nTaskManualLoopMIParallelCompensate;         
        
        XPathExpression exprTaskM8 = xpath.compile("//bpmn:manualTask");
        Object resultTaskM8 = exprTaskM8.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskM8 = (NodeList) resultTaskM8;
        doc.getDocumentElement().normalize();  
        nTaskManualLoopNoneCompensateNone = nodesTaskM8.getLength() 
        	- (nTaskManualLoopNoneCompensate + nTaskManualLoopStandardCompensateNone  
                    + nTaskManualLoopMIParallelCompensate + nTaskManualLoopMISequentialCompensateNone
                	+ nTaskManualLoopMISequentialCompensate + nTaskManualLoopMIParallelCompensateNone
                	+ nTaskManualLoopStandardCompensate);
        
      //All Task Business Rule
        XPathExpression exprTaskBR1 = xpath.compile("//bpmn:businessRuleTask[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[@isSequential='false']");
        Object resultTaskBR1 = exprTaskBR1.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskBR1 = (NodeList) resultTaskBR1;
        doc.getDocumentElement().normalize();  
        nTaskBusinessRuleLoopMIParallelCompensate = nodesTaskBR1.getLength();
        
        XPathExpression exprTaskBR2= xpath.compile("//bpmn:businessRuleTask[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[@isSequential='false']");
        Object resultTaskBR2  = exprTaskBR2.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskBR2 = (NodeList) resultTaskBR2;
        doc.getDocumentElement().normalize();  
        nTaskBusinessRuleLoopMIParallelCompensateNone = nodesTaskBR2.getLength();

        XPathExpression exprTaskBR3 = xpath.compile("//bpmn:businessRuleTask[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[@isSequential='true']");
        Object resultTaskBR3= exprTaskBR3.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskBR3 = (NodeList) resultTaskBR3;
        doc.getDocumentElement().normalize();  
        nTaskBusinessRuleLoopMISequentialCompensate = nodesTaskBR3.getLength();    
        
        XPathExpression exprTaskBR4 = xpath.compile("//bpmn:businessRuleTask[@isForCompensation='true']//bpmn:standardLoopCharacteristics");
        Object resultTaskBR4 = exprTaskBR4.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskBR4 = (NodeList) resultTaskBR4;
        doc.getDocumentElement().normalize();  
        nTaskBusinessRuleLoopStandardCompensate = nodesTaskBR4.getLength();
        
        XPathExpression exprTaskBR5 = xpath.compile("//bpmn:businessRuleTask[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[@isSequential='true']");
        Object resultTaskBR5 = exprTaskBR5.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskBR5 = (NodeList) resultTaskBR5;
        doc.getDocumentElement().normalize();  
        nTaskBusinessRuleLoopMISequentialCompensateNone = nodesTaskBR5.getLength();     
        
        XPathExpression exprTaskBR6 = xpath.compile("//bpmn:businessRuleTask[not(contains(@isForCompensation,'true'))]//bpmn:standardLoopCharacteristics");
        Object resultTaskBR6 = exprTaskBR6.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskBR6 = (NodeList) resultTaskBR6;
        doc.getDocumentElement().normalize();  
        nTaskBusinessRuleLoopStandardCompensateNone = nodesTaskBR6.getLength();
        
        XPathExpression exprTaskBR7 = xpath.compile("//bpmn:businessRuleTask[@isForCompensation='true']");
        Object resultTaskBR7 = exprTaskBR7.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskBR7 = (NodeList) resultTaskBR7;
        doc.getDocumentElement().normalize();  
        nTaskBusinessRuleLoopNoneCompensate = nodesTaskBR7.getLength() 
        	 - nTaskBusinessRuleLoopStandardCompensate - nTaskBusinessRuleLoopMISequentialCompensate 
        	 - nTaskBusinessRuleLoopMIParallelCompensate;         
        
        XPathExpression exprTaskBR8 = xpath.compile("//bpmn:businessRuleTask");
        Object resultTaskBR8 = exprTaskBR8.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskBR8 = (NodeList) resultTaskBR8;
        doc.getDocumentElement().normalize();  
        nTaskBusinessRuleLoopNoneCompensateNone = nodesTaskBR8.getLength() 
        	- (nTaskBusinessRuleLoopNoneCompensate + nTaskBusinessRuleLoopStandardCompensateNone  
                    + nTaskBusinessRuleLoopMIParallelCompensate + nTaskBusinessRuleLoopMISequentialCompensateNone
                	+ nTaskBusinessRuleLoopMISequentialCompensate + nTaskBusinessRuleLoopMIParallelCompensateNone
                	+ nTaskBusinessRuleLoopStandardCompensate);
      
        //All Task Service
        XPathExpression exprTaskSer1 = xpath.compile("//bpmn:serviceTask[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[@isSequential='false']");
        Object resultTaskSer1 = exprTaskSer1.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskSer1 = (NodeList) resultTaskSer1;
        doc.getDocumentElement().normalize();  
        nTaskServiceLoopMIParallelCompensate = nodesTaskSer1.getLength();
        
        XPathExpression exprTaskSer2= xpath.compile("//bpmn:serviceTask[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[@isSequential='false']");
        Object resultTaskSer2  = exprTaskSer2.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskSer2 = (NodeList) resultTaskSer2;
        doc.getDocumentElement().normalize();  
        nTaskServiceLoopMIParallelCompensateNone = nodesTaskSer2.getLength();

        XPathExpression exprTaskSer3 = xpath.compile("//bpmn:serviceTask[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[@isSequential='true']");
        Object resultTaskSer3= exprTaskSer3.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskSer3 = (NodeList) resultTaskSer3;
        doc.getDocumentElement().normalize();  
        nTaskServiceLoopMISequentialCompensate = nodesTaskSer3.getLength();    
        
        XPathExpression exprTaskSer4 = xpath.compile("//bpmn:serviceTask[@isForCompensation='true']//bpmn:standardLoopCharacteristics");
        Object resultTaskSer4 = exprTaskSer4.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskSer4 = (NodeList) resultTaskSer4;
        doc.getDocumentElement().normalize();  
        nTaskServiceLoopStandardCompensate = nodesTaskSer4.getLength();
        
        XPathExpression exprTaskSer5 = xpath.compile("//bpmn:serviceTask[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[@isSequential='true']");
        Object resultTaskSer5 = exprTaskSer5.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskSer5= (NodeList) resultTaskSer5;
        doc.getDocumentElement().normalize();  
        nTaskServiceLoopMISequentialCompensateNone = nodesTaskSer5.getLength();     
        
        XPathExpression exprTaskSer6 = xpath.compile("//bpmn:serviceTask[not(contains(@isForCompensation,'true'))]//bpmn:standardLoopCharacteristics");
        Object resultTaskSer6 = exprTaskSer6.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskSer6 = (NodeList) resultTaskSer6;
        doc.getDocumentElement().normalize();  
        nTaskServiceLoopStandardCompensateNone = nodesTaskSer6.getLength();
        
        XPathExpression exprTaskSer7 = xpath.compile("//bpmn:serviceTask[@isForCompensation='true']");
        Object resultTaskSer7 = exprTaskSer7.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskSer7 = (NodeList) resultTaskSer7;
        doc.getDocumentElement().normalize();  
        nTaskServiceLoopNoneCompensate = nodesTaskSer7.getLength() 
        	 - nTaskServiceLoopStandardCompensate - nTaskServiceLoopMISequentialCompensate 
        	 - nTaskServiceLoopMIParallelCompensate;         
        
        XPathExpression exprTaskSer8 = xpath.compile("//bpmn:serviceTask");
        Object resultTaskSer8 = exprTaskSer8.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskSer8 = (NodeList) resultTaskSer8;
        doc.getDocumentElement().normalize();  
        nTaskServiceLoopNoneCompensateNone = nodesTaskSer8.getLength() 
        	- (nTaskServiceLoopNoneCompensate + nTaskServiceLoopStandardCompensateNone  
                    + nTaskServiceLoopMIParallelCompensate + nTaskServiceLoopMISequentialCompensateNone
                	+ nTaskServiceLoopMISequentialCompensate + nTaskServiceLoopMIParallelCompensateNone
                	+ nTaskServiceLoopStandardCompensate);
        
        //All Task Script
        XPathExpression exprTaskScr1 = xpath.compile("//bpmn:scriptTask[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[@isSequential='false']");
        Object resultTaskScr1 = exprTaskScr1.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskScr1 = (NodeList) resultTaskScr1;
        doc.getDocumentElement().normalize();  
        nTaskScriptLoopMIParallelCompensate = nodesTaskScr1.getLength();
        
        XPathExpression exprTaskScr2= xpath.compile("//bpmn:scriptTask[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[@isSequential='false']");
        Object resultTaskScr2  = exprTaskScr2.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskScr2 = (NodeList) resultTaskScr2;
        doc.getDocumentElement().normalize();  
        nTaskScriptLoopMIParallelCompensateNone = nodesTaskScr2.getLength();

        XPathExpression exprTaskScr3 = xpath.compile("//bpmn:scriptTask[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[@isSequential='true']");
        Object resultTaskScr3= exprTaskScr3.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskScr3 = (NodeList) resultTaskScr3;
        doc.getDocumentElement().normalize();  
        nTaskScriptLoopMISequentialCompensate = nodesTaskScr3.getLength();    
        
        XPathExpression exprTaskScr4 = xpath.compile("//bpmn:scriptTask[@isForCompensation='true']//bpmn:standardLoopCharacteristics");
        Object resultTaskScr4 = exprTaskScr4.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskScr4 = (NodeList) resultTaskScr4;
        doc.getDocumentElement().normalize();  
        nTaskScriptLoopStandardCompensate = nodesTaskScr4.getLength();
        
        XPathExpression exprTaskScr5 = xpath.compile("//bpmn:scriptTask[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[@isSequential='true']");
        Object resultTaskScr5= exprTaskScr5.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskScr5 = (NodeList) resultTaskScr5;
        doc.getDocumentElement().normalize();  
        nTaskScriptLoopMISequentialCompensateNone = nodesTaskScr5.getLength();     
        
        XPathExpression exprTaskScr6 = xpath.compile("//bpmn:scriptTask[not(contains(@isForCompensation,'true'))]//bpmn:standardLoopCharacteristics");
        Object resultTaskScr6 = exprTaskScr6.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskScr6 = (NodeList) resultTaskScr6;
        doc.getDocumentElement().normalize();  
        nTaskScriptLoopStandardCompensateNone = nodesTaskScr6.getLength();
        
        XPathExpression exprTaskScr7 = xpath.compile("//bpmn:scriptTask[@isForCompensation='true']");
        Object resultTaskScr7= exprTaskScr7.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskScr7 = (NodeList) resultTaskScr7;
        doc.getDocumentElement().normalize();  
        nTaskScriptLoopNoneCompensate = nodesTaskScr7.getLength() 
        	 - nTaskScriptLoopStandardCompensate - nTaskScriptLoopMISequentialCompensate 
        	 - nTaskScriptLoopMIParallelCompensate;         
        
        XPathExpression exprTaskScr8 = xpath.compile("//bpmn:scriptTask");
        Object resultTaskScr8 = exprTaskScr8.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTaskScr8 = (NodeList) resultTaskScr8;
        doc.getDocumentElement().normalize();  
        nTaskScriptLoopNoneCompensateNone = nodesTaskScr8.getLength() 
        	- (nTaskScriptLoopNoneCompensate + nTaskScriptLoopStandardCompensateNone  
                    + nTaskScriptLoopMIParallelCompensate + nTaskScriptLoopMISequentialCompensateNone
                	+ nTaskScriptLoopMISequentialCompensate + nTaskScriptLoopMIParallelCompensateNone
                	+ nTaskScriptLoopStandardCompensate);
        
        //N° of Group
        XPathExpression exprGroup = xpath.compile("//bpmn:group");
        Object resultGroup = exprGroup.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesGroup = (NodeList) resultGroup;
        doc.getDocumentElement().normalize();  
        nGroup = nodesGroup.getLength();  
         
//	    DATA OBJECTS------------------------------------------------------------------------------------
        
        // N° of Data Object
        XPathExpression exprDOC = xpath.compile("//bpmn:dataObject[@isCollection='true']");
        Object resultDOC = exprDOC.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesDOC = (NodeList) resultDOC;
        doc.getDocumentElement().normalize();  
        nDataObjectCollection = nodesDOC.getLength();
        
        // N° of Data Object
        XPathExpression exprDO = xpath.compile("//bpmn:dataObject");
        Object resultDO = exprDO.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesDO = (NodeList) resultDO;
        doc.getDocumentElement().normalize();  
        nDataObject = nodesDO.getLength() - nDataObjectCollection;
        
        // N° of Data Store
        XPathExpression exprDS = xpath.compile("//bpmn:dataStoreReference");
        Object resultDS = exprDS.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesDS = (NodeList) resultDS;
        doc.getDocumentElement().normalize();  
        nDataStore = nodesDS.getLength();
        
        // N° of Data Input
        XPathExpression exprDI = xpath.compile("//bpmn:dataInput");
        Object resultDI = exprDI.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesDI = (NodeList) resultDI;
        doc.getDocumentElement().normalize();  
        nDataInput = nodesDI.getLength();
        
        // N° of Data Output
        XPathExpression exprDOut = xpath.compile("//bpmn:dataOutput");
        Object resultDOut = exprDOut.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesDOut = (NodeList) resultDOut;
        doc.getDocumentElement().normalize();  
        nDataOutput = nodesDOut.getLength();
        
        // GATEWAYS-------------------------------------------------------------------------------------
        
        // Exclusive Gateway Empty 
        XPathExpression exprExGEmpty = xpath.compile("//bpmn:exclusiveGateway//bpmn:BPMNShape[@isMarkerVisible='false']");
        Object resultExGEmpty = exprExGEmpty.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesExGEmpty = (NodeList) resultExGEmpty;
        doc.getDocumentElement().normalize();  
        nExclusiveGatewayNoMarker = nodesExGEmpty.getLength();
        
        // Exclusive Gateway 
        XPathExpression exprExG = xpath.compile("//bpmn:exclusiveGateway");
        Object resultExG = exprExG.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesExG = (NodeList) resultExG;
        doc.getDocumentElement().normalize();  
        nExclusiveGatewayMarker = nodesExG.getLength() - nExclusiveGatewayNoMarker;
        
        XPathExpression exprPaG = xpath.compile("//bpmn:parallelGateway");
        Object resultPaG = exprPaG.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesPaG = (NodeList) resultPaG;
        doc.getDocumentElement().normalize();  
        nParallelGateway = nodesPaG.getLength();
        
        XPathExpression exprInG = xpath.compile("//bpmn:inclusiveGateway");
        Object resultInG = exprInG.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesInG = (NodeList) resultInG;
        doc.getDocumentElement().normalize();  
        nInclusiveGateway = nodesInG.getLength();
        
        XPathExpression exprEBG = xpath.compile("//bpmn:eventBasedGateway[@eventGatewayType='Exclusive']");
        Object resultEBG = exprEBG.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesEBG = (NodeList) resultEBG;
        doc.getDocumentElement().normalize();  
        nEventBasedGateway = nodesEBG.getLength();

        //TO TEST Event Based Gateway Exclusive Instantiation
        XPathExpression exprEBGEI = xpath.compile("//bpmn:eventBasedGateway[@eventGatewayType='Exclusive' and @instantiate='true']");
        Object resultEBGEI = exprEBGEI.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesEBGEI = (NodeList) resultEBGEI;
        doc.getDocumentElement().normalize();  
        nEventBasedGatewayExclusiveInstantiation = nodesEBGEI.getLength();
        
        //TO TEST Event Based Gateway Parallel Instantiation
        XPathExpression exprEBGPI = xpath.compile("//bpmn:eventBasedGateway[@eventGatewayType='Parallel' and @instantiate='true']");
        Object resultEBGPI = exprEBGPI.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesEBGPI = (NodeList) resultEBGPI;
        doc.getDocumentElement().normalize();  
        nEventBasedGatewayParallelInstantiation = nodesEBGPI.getLength();
        
        XPathExpression exprCoG = xpath.compile("//bpmn:complexGateway");
        Object resultCoG = exprCoG.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesCoG = (NodeList) resultCoG;
        doc.getDocumentElement().normalize();  
        nComplexGateway = nodesCoG.getLength();       
        
        // EVENTS
        
        // Start Events
        
        XPathExpression exprStartEvent = xpath.compile("//bpmn:startEvent");
        Object resultStartEvent = exprStartEvent.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesStartEvent = (NodeList) resultStartEvent;
        doc.getDocumentElement().normalize();         
        
        for(int i=0; i<nodesStartEvent.getLength(); i++) {
        	
        	Node StartEventNode = nodesStartEvent.item(i);   
        	
        	if(((Element) nodesStartEvent.item(i)).getAttribute("parallelMultiple").contains("true")) {
        		nStartMultipleParallelEventDefinition++;
        	}      	
        	
        	if(StartEventNode.hasChildNodes()) {                
        		
        		NodeList StartEventChildNodes = StartEventNode.getChildNodes();
        		         		
                for(int j=0;j<StartEventChildNodes.getLength(); j++) {
                

	                  	if(StartEventChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
	                  		
	                  		if(((Element) nodesStartEvent.item(i)).getAttribute("parallelMultiple").contains("true") == false && StartEventChildNodes.item(j).getNodeName().contains("cancelEventDefinition")) {
	                  			
	                  			nStartMultipleEventDefinition++;
	                  	   }
	                  				                  			
	                  		if(StartEventChildNodes.item(j).getNodeName().contains("signalEventDefinition")) {
	                  			nStartSignalEventDefinition++;
	                  		}
	                  		
	                  		if(StartEventChildNodes.item(j).getNodeName().contains("conditionalEventDefinition")) {
	                  			nStartConditionalEventDefinition++;
	                  			}	
	                  		
	                  		if(StartEventChildNodes.item(j).getNodeName().contains("timerEventDefinition")) {
	                  			nStartTimerEventDefinition++;
	                  		}
	                  		
	                  		if(StartEventChildNodes.item(j).getNodeName().contains("messageEventDefinition")) {
	                  			nStartMessageEventDefinition++;
	                  			}
	                  		
	                  		if(StartEventChildNodes.item(j).getNodeName().contains("compensateEventDefinition")) {
	                  			nStartCompensateEventDefinition++;
	                  		}	                  		
	                  		
	                  		if(StartEventChildNodes.item(j).getNodeName().contains("escalationEventDefinition")) {
	                  			nStartEscalationEventDefinition++;
	                  		}
	                  		
	                  		if(StartEventChildNodes.item(j).getNodeName().contains("errorEventDefinition")) {
	                  			nStartErrorEventDefinition++;
	                  			}	
	                  		

	                  			                  	
	                  	}
                }
        		
        	} 
        	else
        		nStartNoneEventDefinition++;
        }
        
        // Start Events Sub Process Interrupting

        XPathExpression exprStartEventSubProcessInt = xpath.compile("//bpmn:startEvent");
        Object resultStartEventSubProcessInt = exprStartEventSubProcessInt.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesStartEventSubProcessInt = (NodeList) resultStartEventSubProcessInt;
        doc.getDocumentElement().normalize();         
        
        for(int i=0; i<nodesStartEventSubProcessInt.getLength(); i++) {
        	
        	Node StartEventNodeSubProcessInt = nodesStartEventSubProcessInt.item(i);   
        	
        	if(((Element) nodesStartEventSubProcessInt.item(i)).getAttribute("parallelMultiple").contains("true")) {
        		nStartMultipleParallelEventSubProcessInterruptingDefinition++;
        	}      	
        	
        	if(StartEventNodeSubProcessInt.hasChildNodes()) {                
        		
        		NodeList StartEventSubProcessIntChildNodes = StartEventNodeSubProcessInt.getChildNodes();
        		         		
                for(int j=0;j<StartEventSubProcessIntChildNodes.getLength(); j++) {
                

	                  	if(StartEventSubProcessIntChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
	                  		
	                  		if(((Element) nodesStartEventSubProcessInt.item(i)).getAttribute("parallelMultiple").contains("true") == false && StartEventSubProcessIntChildNodes.item(j).getNodeName().contains("cancelEventDefinition")) {
	                  			nStartMultipleEventSubProcessInterruptingDefinition++;
	                  	   }
	                  				                  			
	                  		if(StartEventSubProcessIntChildNodes.item(j).getNodeName().contains("signalEventDefinition")) {
	                  			nStartSignalEventSubProcessInterruptingDefinition++;
	                  		}
	                  		
	                  		if(StartEventSubProcessIntChildNodes.item(j).getNodeName().contains("conditionalEventDefinition")) {
	                  			nStartConditionalEventSubProcessInterruptingDefinition++;
	                  			}	
	                  		
	                  		if(StartEventSubProcessIntChildNodes.item(j).getNodeName().contains("timerEventDefinition")) {
	                  			nStartTimerEventSubProcessInterruptingDefinition++;
	                  		}
	                  		
	                  		if(StartEventSubProcessIntChildNodes.item(j).getNodeName().contains("messageEventDefinition")) {
	                  			nStartMessageEventSubProcessInterruptingDefinition++;
	                  			}
	                  		
	                  		if(StartEventSubProcessIntChildNodes.item(j).getNodeName().contains("compensateEventDefinition")) {
	                  			nStartCompensateEventSubProcessInterruptingDefinition++;
	                  		}	                  		
	                  		
	                  		if(StartEventSubProcessIntChildNodes.item(j).getNodeName().contains("escalationEventDefinition")) {
	                  			nStartEscalationEventSubProcessInterruptingDefinition++;
	                  		}
	                  		
	                  		if(StartEventSubProcessIntChildNodes.item(j).getNodeName().contains("errorEventDefinition")) {
	                  			nStartErrorEventSubProcessInterruptingDefinition++;
	                  			}	 			                  	
	                  	}
                }
        		
        	} 

        }
        
        // Start Events Sub Process NON-Interrupting
        
        XPathExpression exprStartEventSubProcessNonInt = xpath.compile("//bpmn:startEvent");
        Object resultStartEventSubProcessNonInt = exprStartEventSubProcessNonInt.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesStartEventSubProcessNonInt = (NodeList) resultStartEventSubProcessNonInt;
        doc.getDocumentElement().normalize();         
        
        for(int i=0; i<nodesStartEventSubProcessNonInt.getLength(); i++) {
        	
        	Node StartEventNodeSubProcessNonInt = nodesStartEventSubProcessNonInt.item(i);   
        	
        	if(((Element) nodesStartEventSubProcessNonInt.item(i)).getAttribute("parallelMultiple").contains("true")) {
        		nStartMultipleParallelEventSubProcessNonInterruptingDefinition++;
        	}      	
        	
        	if(StartEventNodeSubProcessNonInt.hasChildNodes()) {                
        		
        		NodeList StartEventSubProcessNonIntChildNodes = StartEventNodeSubProcessNonInt.getChildNodes();
        		         		
                for(int j=0;j<StartEventSubProcessNonIntChildNodes.getLength(); j++) {
                

	                  	if(StartEventSubProcessNonIntChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
	                  		
	                  		if(((Element) nodesStartEventSubProcessNonInt.item(i)).getAttribute("parallelMultiple").contains("true") == false && StartEventSubProcessNonIntChildNodes.item(j).getNodeName().contains("cancelEventDefinition")) {
	                  			nStartMultipleEventSubProcessNonInterruptingDefinition++;
	                  	   }
	                  				                  			
	                  		if(StartEventSubProcessNonIntChildNodes.item(j).getNodeName().contains("signalEventDefinition") &&
	                  				((Element) nodesStartEventSubProcessNonInt.item(i)).getAttribute("isInterrupting").contains("false") == true) {
	                  			nStartSignalEventSubProcessNonInterruptingDefinition++;
	                  		}
	                  		
	                  		if(StartEventSubProcessNonIntChildNodes.item(j).getNodeName().contains("conditionalEventDefinition") &&
	                  				((Element) nodesStartEventSubProcessNonInt.item(i)).getAttribute("isInterrupting").contains("false") == true) {
	                  			nStartConditionalEventSubProcessNonInterruptingDefinition++;
	                  			}	
	                  		
	                  		if(StartEventSubProcessNonIntChildNodes.item(j).getNodeName().contains("timerEventDefinition") &&
	                  				((Element) nodesStartEventSubProcessNonInt.item(i)).getAttribute("isInterrupting").contains("false") == true) {
	                  			nStartTimerEventSubProcessNonInterruptingDefinition++;
	                  		}
	                  		
	                  		if(StartEventSubProcessNonIntChildNodes.item(j).getNodeName().contains("messageEventDefinition")&&
	                  				((Element) nodesStartEventSubProcessNonInt.item(i)).getAttribute("isInterrupting").contains("false") == true) {
	                  			nStartMessageEventSubProcessNonInterruptingDefinition++;
	                  			}                  		
	                  		
	                  		if(StartEventSubProcessNonIntChildNodes.item(j).getNodeName().contains("escalationEventDefinition")&&
	                  				((Element) nodesStartEventSubProcessNonInt.item(i)).getAttribute("isInterrupting").contains("false") == true) {
	                  			nStartEscalationEventSubProcessNonInterruptingDefinition++;
	                  		}
		                  	
	                  	}
                }
        		
        	} 

        }
        
        
        // End Events
        
        XPathExpression exprEndEvent = xpath.compile("//bpmn:endEvent");
        Object resultEndEvent = exprEndEvent.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesEndEvent = (NodeList) resultEndEvent;
        doc.getDocumentElement().normalize();         
        
        for(int i=0; i<nodesEndEvent.getLength(); i++) {
        	
        	Node EndEventNode = nodesEndEvent.item(i);   
        	
        	
        	
        	if(EndEventNode.hasChildNodes()) {                
        		
        		NodeList EndEventChildNodes = EndEventNode.getChildNodes();
        		   
        		boolean msg = false;
                boolean term = false;
                boolean canc = false;
        		
                for(int j=0;j<EndEventChildNodes.getLength(); j++) {
                
                
                	
	                  	if(EndEventChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
	                  		
	                
	                  		if(EndEventChildNodes.item(j).getNodeName().contains("signalEventDefinition")) {
	                  			nEndSignalEventDefinition++;
	                  		}
	                  		
	                  		if(EndEventChildNodes.item(j).getNodeName().contains("messageEventDefinition")) {
	                  			nEndMessageEventDefinition++;
	                  			msg = true;
	                  			}
	                  		
	                  		if(EndEventChildNodes.item(j).getNodeName().contains("compensateEventDefinition")) {
	                  			nEndCompensateEventDefinition++;
	                  		}	                  		
	                  		
	                  		if(EndEventChildNodes.item(j).getNodeName().contains("escalationEventDefinition")) {
	                  			nEndEscalationEventDefinition++;
	                  		}
	                  		
	                  		if(EndEventChildNodes.item(j).getNodeName().contains("errorEventDefinition")) {
	                  			nEndErrorEventDefinition++;
	                  			}	
	                  		
	                  		if(EndEventChildNodes.item(j).getNodeName().contains("terminateEventDefinition")) {
	                  			nEndTerminateEventDefinition++;
	                  			 term = true;
	                  			}
	                  		
	                  		if(EndEventChildNodes.item(j).getNodeName().contains("cancelEventDefinition")) {
	                  			nEndCancelEventDefinition++;	                  			
	                  			canc = true;
	                  			}
	                  		       
	                  		if(msg && term && canc) {
	                  			nEndMultipleEventDefinition++;
	                  			nEndCancelEventDefinition = nEndCancelEventDefinition - nEndMultipleEventDefinition;
	                  			nEndTerminateEventDefinition = nEndTerminateEventDefinition - nEndMultipleEventDefinition;
	                  			nEndMessageEventDefinition = nEndMessageEventDefinition - nEndMultipleEventDefinition;
	                 }
	               }
                }      		
        	} 
        	else
        		nEndNoneEventDefinition++;
        }

        // Intermediate Catch Events
        
        XPathExpression exprIntEvent = xpath.compile("//bpmn:intermediateCatchEvent");
        Object resultIntEvent = exprIntEvent.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesIntEvent = (NodeList) resultIntEvent;
        doc.getDocumentElement().normalize();         
        
        for(int i=0; i<nodesIntEvent.getLength(); i++) {
        	
        	Node IntEventNode = nodesIntEvent.item(i);   
        	
        	if(((Element) nodesIntEvent.item(i)).getAttribute("parallelMultiple").contains("true")) {
        		nIntermediateCatchMultipleParallelEventDefinition++;
        	}           	
        	 
        	if(IntEventNode.hasChildNodes()) {                
        		
        		NodeList IntEventChildNodes = IntEventNode.getChildNodes();
        		         		
                for(int j=0;j<IntEventChildNodes.getLength(); j++) {
                		
	                  	if(IntEventChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
	                  		
	                  		if(((Element) nodesIntEvent.item(i)).getAttribute("parallelMultiple").contains("true") == false && IntEventChildNodes.item(j).getNodeName().contains("cancelEventDefinition")) {
	                  			nIntermediateCatchMultipleEventDefinition++;
	                  	   }
	                  		
	                  		if(IntEventChildNodes.item(j).getNodeName().contains("messageEventDefinition")) {
	                  			nIntermediateCatchMessageEventDefinition++;
	                  		}
	                  		
	                  		if(IntEventChildNodes.item(j).getNodeName().contains("timerEventDefinition")) {
	                  			nIntermediateCatchTimerEventDefinition++;
	                  		}
	                  		
	                  		if(IntEventChildNodes.item(j).getNodeName().contains("conditionalEventDefinition")) {
	                  			nIntermediateCatchConditionalEventDefinition++;
	                  			}	
	                  		
	                  		if(IntEventChildNodes.item(j).getNodeName().contains("linkEventDefinition")) {
	                  			nIntermediateCatchLinkEventDefinition++;
	                  		}
	                  		
	                  		if(IntEventChildNodes.item(j).getNodeName().contains("signalEventDefinition")) {
	                  			nIntermediateCatchSignalEventDefinition++;
	                  		}	                  			                  		
	                  			                  	
	                  	}
                }
        		
        	} 

        }
        
        // Intermediate Throw Events
        
        XPathExpression exprThrEvent = xpath.compile("//bpmn:intermediateThrowEvent");
        Object resultThrEvent = exprThrEvent.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesThrEvent = (NodeList) resultThrEvent;
        doc.getDocumentElement().normalize();         
        
        for(int i=0; i<nodesThrEvent.getLength(); i++) {
        	
        	Node ThrEventNode = nodesThrEvent.item(i);   
        	 
        	if(ThrEventNode.hasChildNodes()) {                
        		
        		NodeList ThrEventChildNodes = ThrEventNode.getChildNodes();
        		         		
                for(int j=0;j<ThrEventChildNodes.getLength(); j++) {
                

	                  	if(ThrEventChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
	                  		
	                  		if(ThrEventChildNodes.item(j).getNodeName().contains("messageEventDefinition")) {
	                  		nIntermediateThrowMessageEventDefinition++;
	                  		}
	                  		
	                  		if(ThrEventChildNodes.item(j).getNodeName().contains("escalationEventDefinition")) {
	                  			nIntermediateThrowEscalationEventDefinition++;
	                  		}
	                  		
	                  		if(ThrEventChildNodes.item(j).getNodeName().contains("linkEventDefinition")) {
	                  			nIntermediateThrowLinkEventDefinition++;
	                  		}
	                  		
	                  		if(ThrEventChildNodes.item(j).getNodeName().contains("signalEventDefinition")) {
	                  			nIntermediateThrowSignalEventDefinition++;
	                  		}
 		
	                  		if(ThrEventChildNodes.item(j).getNodeName().contains("compensateEventDefinition")) {
	                  			nIntermediateThrowCompensateEventDefinition++;
	                  		}	                  		
	                  		
	                  		if(ThrEventChildNodes.item(j).getNodeName().contains("terminateEventDefinition")) {
	                  		   nIntermediateThrowMultipleParallelEventDefinition++;
	                  		}
	                  			                  	
	                  	}
                }
        		
        	} 
        	else
        		nIntermediateThrowNoneEventDefinition++;
        }
        
        // Intermediate Catch Events - Boundary Interrupting
        
        XPathExpression exprBoundaryCatchIntEvent = xpath.compile("//bpmn:boundaryEvent");
        Object resultBoundaryCatchIntEvent = exprBoundaryCatchIntEvent.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesBoundaryCatchIntEvent = (NodeList) resultBoundaryCatchIntEvent;
        doc.getDocumentElement().normalize();  
        
        for(int i=0; i<nodesBoundaryCatchIntEvent.getLength(); i++) {
        	
        	Node BoundaryCatchIntEventNode = nodesBoundaryCatchIntEvent.item(i);   
        	
        	if(BoundaryCatchIntEventNode.hasChildNodes()) {                
        		
        		NodeList CatchIntEventChildNodes = BoundaryCatchIntEventNode.getChildNodes();

                for(int j=0;j<CatchIntEventChildNodes.getLength(); j++) {
                		                                	
	                  	if(CatchIntEventChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
	                  		
	                  		if(((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("parallelMultiple").contains("true") &&
	                  		   ((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("cancelActivity").contains("false") == false) {
	                    		nIntermediateBoundaryMultipleParallelEvent++;
	                    		break;
	                    	}           
	                  		
	                  		if(((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("parallelMultiple").contains("true") == false &&
	                  		   ((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("cancelActivity").contains("false") == false) {
	                    		nIntermediateBoundaryMultipleEvent++;
	                    		break;
	                    	}      
	                  		
	                  		if(CatchIntEventChildNodes.item(j).getNodeName().contains("messageEventDefinition") &&
	 	                  		   ((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("cancelActivity").contains("false") == false) {
	                  			nIntermediateBoundaryMessageEvent++;
	                  		}
	                  		
	                  		if(CatchIntEventChildNodes.item(j).getNodeName().contains("timerEventDefinition") &&
	 	                  		   ((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("cancelActivity").contains("false") == false) {
	                  			nIntermediateBoundaryTimerEvent++;
	                  		}
	                  		
	                  		if(CatchIntEventChildNodes.item(j).getNodeName().contains("escalationEventDefinition") &&
	 	                  		   ((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("cancelActivity").contains("false") == false) {
	                  			nIntermediateBoundaryEscalationEvent++;
	                  		}
	                  		
	                  		if(CatchIntEventChildNodes.item(j).getNodeName().contains("conditionalEventDefinition") &&
	 	                  		   ((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("cancelActivity").contains("false") == false) {
	                  			nIntermediateBoundaryConditionalEvent++;
	                  		}
	                  		
	                  		if(CatchIntEventChildNodes.item(j).getNodeName().contains("errorEventDefinition") &&
	 	                  		   ((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("cancelActivity").contains("false") == false) {
	                  			nIntermediateBoundaryErrorEvent++;
	                  		}

	                  		
	                  		if(CatchIntEventChildNodes.item(j).getNodeName().contains("cancelEventDefinition") &&
	 	                  		   ((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("cancelActivity").contains("false") == false) {
	                  			nIntermediateBoundaryCancelEvent++;
	                  		}	 
	                  		
	                  		if(CatchIntEventChildNodes.item(j).getNodeName().contains("compensateEventDefinition") &&
	 	                  		   ((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("cancelActivity").contains("false") == false) {
	                  			nIntermediateBoundaryCompensateEvent++;
	                  		}	                  		
	                  		
	                  		if(CatchIntEventChildNodes.item(j).getNodeName().contains("signalEventDefinition") &&
	 	                  		   ((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("cancelActivity").contains("false") == false) {
	                  			nIntermediateBoundarySignalEvent++;
	                  		}	   
                  		             	
	                  	}
                }
        		
        	} 
        }
        
        // Intermediate Catch Events - Boundary NON Interrupting
        
        XPathExpression exprCatchNonIntEvent = xpath.compile("//bpmn:boundaryEvent");
        Object resultCatchNonIntEvent = exprCatchNonIntEvent.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesCatchNonIntEvent = (NodeList) resultCatchNonIntEvent;
        doc.getDocumentElement().normalize();         
        
        for(int i=0; i<nodesCatchNonIntEvent.getLength(); i++) {
        	
        	Node CatchNonIntEventNode = nodesCatchNonIntEvent.item(i);   
        	
            	       	
        	 
        	if(CatchNonIntEventNode.hasChildNodes()) {                
        		
        		NodeList CatchNonIntEventChildNodes = CatchNonIntEventNode.getChildNodes();
        		         		
                for(int j=0;j<CatchNonIntEventChildNodes.getLength(); j++) {
                

	                  	if(CatchNonIntEventChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
	                  		
	                  		if(((Element) nodesCatchNonIntEvent.item(i)).getAttribute("parallelMultiple").contains("true") == false &&
	                  		   ((Element) nodesCatchNonIntEvent.item(i)).getAttribute("cancelActivity").contains("false") &&
	                  			CatchNonIntEventChildNodes.item(j).getNodeName().contains("cancelEventDefinition")) {
	                  			nIntermediateBoundaryMultipleEventNonInterrupting++;
	                  			break;
	                  	    }	     
	                  		
	                  		if(((Element) nodesCatchNonIntEvent.item(i)).getAttribute("parallelMultiple").contains("true") &&
	 	                  	   ((Element) nodesCatchNonIntEvent.item(i)).getAttribute("cancelActivity").contains("false") &&
	 	                  	   CatchNonIntEventChildNodes.item(j).getNodeName().contains("cancelEventDefinition")) {
	                  		   nIntermediateBoundaryMultipleParallelEventNonInterrupting++;
	 	                  			break;
	 	                  	    }	                  		
	 	                  		
	                  		
	                  		if(CatchNonIntEventChildNodes.item(j).getNodeName().contains("messageEventDefinition") &&
	                  		  ((Element) nodesCatchNonIntEvent.item(i)).getAttribute("cancelActivity").contains("false")) {
	                  			nIntermediateBoundaryMessageEventNonInterrupting++;
	                  			break;
	                  		}
	                  		
	                  		if(CatchNonIntEventChildNodes.item(j).getNodeName().contains("timerEventDefinition")&&
	  	                  		  ((Element) nodesCatchNonIntEvent.item(i)).getAttribute("cancelActivity").contains("false")) {
	                  			nIntermediateBoundaryTimerEventNonInterrupting++;
	                  		}
	                  		
	                  		if(CatchNonIntEventChildNodes.item(j).getNodeName().contains("escalationEventDefinition")&&
	  	                  		  ((Element) nodesCatchNonIntEvent.item(i)).getAttribute("cancelActivity").contains("false")) {
	                  			nIntermediateBoundaryEscalationEventNonInterrupting++;
	                  		}
	                  		
	                  		if(CatchNonIntEventChildNodes.item(j).getNodeName().contains("conditionalEventDefinition") &&
	  	                  		  ((Element) nodesCatchNonIntEvent.item(i)).getAttribute("cancelActivity").contains("false")){
	                  			nIntermediateBoundaryConditionalEventNonInterrupting++;
	                  		}	                  		                  		
	                  		
	                  		if(CatchNonIntEventChildNodes.item(j).getNodeName().contains("signalEventDefinition")&&
	  	                  		  ((Element) nodesCatchNonIntEvent.item(i)).getAttribute("cancelActivity").contains("false")) {
	                  			nIntermediateBoundarySignalEventNonInterrupting++;
	                  		}	   
	                  		
	                  			                  	
	                  	}
                }
        		
        	} 
        }
        
        //N° of Choreography participant multiple      
        XPathExpression exprChoPartM = xpath.compile("//bpmn:choreography//bpmn:participant//bpmn:participantMultiplicity");
        Object resultChoPartM = exprChoPartM.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesChoPartM = (NodeList) resultChoPartM;
        doc.getDocumentElement().normalize();  
        nChoreographyParticipantMultiple = nodesChoPartM.getLength() ; 
        
        //N° of Choreography participant
        XPathExpression exprChoPart = xpath.compile("//bpmn:choreography//bpmn:participant");
        Object resultChoPart = exprChoPart.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesChoPart = (NodeList) resultChoPart;
        doc.getDocumentElement().normalize();  
        nChoreographyParticipant = nodesChoPart.getLength() - nChoreographyParticipantMultiple; 
        
        //N° of Choreography tasks
        XPathExpression exprChoTaskPI = xpath.compile("//bpmn:choreographyTask[@loopType='MultiInstanceParallel']");
        Object resultChoTaskPI = exprChoTaskPI.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesChoTaskPI = (NodeList) resultChoTaskPI;
        doc.getDocumentElement().normalize();  
        nChoreographyTaskParallelMultipleInstance = nodesChoTaskPI.getLength();
        
        XPathExpression exprChoTaskMI = xpath.compile("//bpmn:choreographyTask[@loopType='MultiInstanceSequential']");
        Object resultChoTaskMI = exprChoTaskMI.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesChoTaskMI = (NodeList) resultChoTaskMI;
        doc.getDocumentElement().normalize();  
        nChoreographyTaskSequentialMultipleInstance = nodesChoTaskMI.getLength();        
        
        XPathExpression exprChoTaskLoop = xpath.compile("//bpmn:choreographyTask[@loopType='Standard']");
        Object resultChoTaskLoop = exprChoTaskLoop.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesChoTaskLoop = (NodeList) resultChoTaskLoop;
        doc.getDocumentElement().normalize();  
        nChoreographyTaskLoop = nodesChoTaskLoop.getLength();
        
        XPathExpression exprChoTask = xpath.compile("//bpmn:choreographyTask");
        Object resultChoTask = exprChoTask.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesChoTask = (NodeList) resultChoTask;
        doc.getDocumentElement().normalize();  
        nChoreographyTask = nodesChoTask.getLength() - nChoreographyTaskLoop - nChoreographyTaskSequentialMultipleInstance - nChoreographyTaskParallelMultipleInstance;
        
        //message
        XPathExpression exprChoMsg = xpath.compile("//bpmn:message");
        Object resultChoMsg = exprChoMsg.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesChoMsg = (NodeList) resultChoMsg;
        doc.getDocumentElement().normalize();  
        nChoreographyMessage = nodesChoMsg.getLength();

        //N° of Choreography SubProcess Expanded  & N° of Choreography SubProcess Collapsed  Example: <bpmndi:BPMNShape id="SubChoreography_0vzey3j_di" isExpanded='false'>
        
        XPathExpression exprChoSubprocesses = xpath.compile("//bpmn:subChoreography");
        Object resultChoSubprocesses  = exprChoSubprocesses .evaluate(doc, XPathConstants.NODESET);
        NodeList nodesChoSubprocesses = (NodeList) resultChoSubprocesses;
        doc.getDocumentElement().normalize();  
        
        
        xpath.setNamespaceContext(new NamespaceContext() {

            @Override
            public Iterator getPrefixes(String arg0) {
                return null;
            }

            @Override
            public String getPrefix(String arg0) {
                return null;
            }

            @Override
            public String getNamespaceURI(String arg0) {
                if("bpmn".equals(arg0)) {
                    return "http://www.omg.org/spec/BPMN/20100524/DI";
                }
                return null;
            }
        });
        
        XPathExpression exprChoSubprocessesShapes = xpath.compile("//bpmn:BPMNShape");
        Object resultChoSubprocessesShapes  = exprChoSubprocessesShapes.evaluate(doc, XPathConstants.NODESET);       
        NodeList nodesChoSubprocessesShapes = (NodeList) resultChoSubprocessesShapes;
        doc.getDocumentElement().normalize();               
        
        	for(int i=0;i<nodesChoSubprocesses.getLength();i++) {
        		String ChoSubprocessesID = (((Element) nodesChoSubprocesses.item(i)).getAttribute("id"));
        		
        		for(int j=0;j<nodesChoSubprocessesShapes.getLength();j++) {
        		String ChoSubprocessesShape = (((Element) nodesChoSubprocessesShapes.item(j)).getAttribute("bpmnElement"));
        		
        			if(ChoSubprocessesID.equalsIgnoreCase(ChoSubprocessesShape)) {
		        	//System.out.println("id: "+ChoSubprocessesID+" bpmnElement: "+ChoSubprocessesShape+" SONO UGUALI");
		        		
		        		// Choreography Expanded
		        		if(((Element) nodesChoSubprocesses.item(i)).getAttribute("loopType").contains("Standard") &&
	                  		  ((Element) nodesChoSubprocessesShapes.item(j)).getAttribute("isExpanded").contains("true")) {
		        			nChoreographySubprocessExpandedLoop++;
                		}
		        		
		        		if(((Element) nodesChoSubprocesses.item(i)).getAttribute("loopType").contains("Parallel") &&
		                  		  ((Element) nodesChoSubprocessesShapes.item(j)).getAttribute("isExpanded").contains("true")) {
		        			nChoreographySubprocessExpandedParallelMultipleInstance++;
	                	}
		        		
		        		if(((Element) nodesChoSubprocesses.item(i)).getAttribute("loopType").contains("Sequential") &&
		                  		  ((Element) nodesChoSubprocessesShapes.item(j)).getAttribute("isExpanded").contains("true")) {
		        			nChoreographySubprocessExpandedSequentialMultipleInstance++;
	                	}
		        		
		        		if(((Element) nodesChoSubprocessesShapes.item(j)).getAttribute("isExpanded").contains("true")) {
		        			nChoreographySubprocessExpanded++;
		        			
	                	}
		        		
		        		// Choreography Collapsed
		        		
		        		if(((Element) nodesChoSubprocesses.item(i)).getAttribute("loopType").contains("Standard") &&
		                  		  ((Element) nodesChoSubprocessesShapes.item(j)).getAttribute("isExpanded").contains("true")==false) {
		        			nChoreographySubprocessCollapsedLoop++;
	                		}
			        		
			        		if(((Element) nodesChoSubprocesses.item(i)).getAttribute("loopType").contains("Parallel") &&
			                  		  ((Element) nodesChoSubprocessesShapes.item(j)).getAttribute("isExpanded").contains("true")==false) {
			        			nChoreographySubprocessCollapsedParallelMultipleInstance++;
		                	}
			        		
			        		if(((Element) nodesChoSubprocesses.item(i)).getAttribute("loopType").contains("Sequential") &&
			                  		  ((Element) nodesChoSubprocessesShapes.item(j)).getAttribute("isExpanded").contains("true")==false) {
			        			nChoreographySubprocessCollapsedSequentialMultipleInstance++;
		                	}
			        		
			        		if(((Element) nodesChoSubprocessesShapes.item(j)).getAttribute("isExpanded").contains("true")==false) {
			        			nChoreographySubprocessCollapsed++;			        			
		                	}
			        					        				        			
		        	}       			
        		}
        	}
        	
        	nChoreographySubprocessCollapsed =  nChoreographySubprocessCollapsed - (nChoreographySubprocessCollapsedLoop + nChoreographySubprocessCollapsedParallelMultipleInstance + nChoreographySubprocessCollapsedSequentialMultipleInstance);
        	nChoreographySubprocessExpanded = nChoreographySubprocessExpanded - ( nChoreographySubprocessExpandedSequentialMultipleInstance + nChoreographySubprocessExpandedParallelMultipleInstance + nChoreographySubprocessExpandedLoop);
        
        	 xpath.setNamespaceContext(new NamespaceContext() {

                 @Override
                 public Iterator getPrefixes(String arg0) {
                     return null;
                 }

                 @Override
                 public String getPrefix(String arg0) {
                     return null;
                 }

                 @Override
                 public String getNamespaceURI(String arg0) {
                     if("bpmn".equals(arg0)) {
                         return "http://www.omg.org/spec/BPMN/20100524/MODEL";
                     }
                     return null;
                 }
             });
        	 
        //N° of Call Choreography             
        XPathExpression exprChoSubCallLoop = xpath.compile("//bpmn:callChoreography[@loopType='Standard']");
        Object resultChoSubCallLoop = exprChoSubCallLoop.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesChoSubCallLoop = (NodeList) resultChoSubCallLoop;
        doc.getDocumentElement().normalize();  
        nChoreographySubprocessCollapsedCallLoop = nodesChoSubCallLoop.getLength();
        
        XPathExpression exprChoSubCallPMI  = xpath.compile("//bpmn:callChoreography[@loopType='MultiInstanceParallel']");
        Object resultChoSubCallPMI = exprChoSubCallPMI.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesChoSubCallPMI = (NodeList) resultChoSubCallPMI;
        doc.getDocumentElement().normalize();  
        nChoreographySubprocessCollapsedCallParallelMultipleInstance = nodesChoSubCallPMI.getLength();
        
        XPathExpression exprChoSubCallSMI= xpath.compile("//bpmn:callChoreography[@loopType='MultiInstanceSequential']");
        Object resultChoSubCallSMI = exprChoSubCallSMI.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesChoSubCallSMI = (NodeList) resultChoSubCallSMI;
        doc.getDocumentElement().normalize();  
        nChoreographySubprocessCollapsedCallSequentialMultipleInstance = nodesChoSubCallSMI.getLength();

        XPathExpression exprChoSubCall = xpath.compile("//bpmn:callChoreography");
        Object resultChoSubCall  = exprChoSubCall.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesChoSubCall  = (NodeList) resultChoSubCall;
        doc.getDocumentElement().normalize();  
        nChoreographySubprocessCollapsedCall = nodesChoSubCall.getLength() - nChoreographySubprocessCollapsedCallSequentialMultipleInstance - nChoreographySubprocessCollapsedCallParallelMultipleInstance - nChoreographySubprocessCollapsedCallLoop;
        
        //N° of Lane 
        XPathExpression exprLane = xpath.compile("//bpmn:lane");
        Object resultLane = exprLane.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesLane = (NodeList) resultLane;
        doc.getDocumentElement().normalize();  
        nLane = nodesLane.getLength();
        
        // Pool Expanded Multiplicity
        XPathExpression exprPoolExM = xpath.compile("//bpmn:collaboration//bpmn:participant[@processRef]//bpmn:participantMultiplicity");
        Object resultPoolExM= exprPoolExM.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesPoolExM = (NodeList) resultPoolExM;
        doc.getDocumentElement().normalize();  
        nPoolExpandedMultiplicity = nodesPoolExM.getLength();
        
        // Pool Expanded Multiplicity None
        XPathExpression exprPoolEx = xpath.compile("//bpmn:collaboration//bpmn:participant[@processRef]");
        Object resultPoolEx= exprPoolEx.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesPoolEx = (NodeList) resultPoolEx;
        doc.getDocumentElement().normalize();  
        nPoolExpandedMultiplicityNone = nodesPoolEx.getLength() - nPoolExpandedMultiplicity;
        
        // Pool Collapsed Multiplicity 
        XPathExpression exprPoolColM = xpath.compile("//bpmn:collaboration//bpmn:participant[not(contains(@processRef,'sid'))]//bpmn:participantMultiplicity");
        Object resultPoolColM = exprPoolColM.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesPoolColM = (NodeList) resultPoolColM;
        doc.getDocumentElement().normalize();  
        nPoolCollapsedMultiplicity = nodesPoolColM.getLength();
        
        // Pool Collapsed Multiplicity None
        XPathExpression exprPoolCol = xpath.compile("//bpmn:collaboration//bpmn:participant[not(contains(@processRef,'sid'))]");
        Object resultPoolCol= exprPoolCol.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesPoolCol = (NodeList) resultPoolCol;
        doc.getDocumentElement().normalize();  
        nPoolCollapsedMultiplicityNone = nodesPoolCol.getLength() - nPoolCollapsedMultiplicity;
        
        
        //FLOW
        //N° of Message Flow
        XPathExpression exprMSGFlow = xpath.compile("//bpmn:messageFlow");
        Object resultMSGFlow  = exprMSGFlow .evaluate(doc, XPathConstants.NODESET);
        NodeList nodesMSGFlow  = (NodeList) resultMSGFlow;
        doc.getDocumentElement().normalize();  
        nMessageFlow = nodesMSGFlow.getLength(); 
        
        //N° of Default Flow
        XPathExpression exprDFlow = xpath.compile("//bpmn:exclusiveGateway[@default]");
        Object resultDFlow = exprDFlow.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesDFlow = (NodeList) resultDFlow;
        doc.getDocumentElement().normalize();  
        nDefaultFlow = nodesDFlow.getLength();
        
        //N° of Conditional Flow
        XPathExpression exprCFlow = xpath.compile("//bpmn:sequenceFlow//bpmn:conditionExpression");
        Object resultCFlow = exprCFlow.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesCFlow = (NodeList) resultCFlow;
        doc.getDocumentElement().normalize();  
        nConditionalFlow = nodesCFlow.getLength();
        
        //N° of Sequence Flow
        XPathExpression exprSFlow = xpath.compile("//bpmn:sequenceFlow");
        Object resultSFlow = exprSFlow.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesSFlow = (NodeList) resultSFlow;
        doc.getDocumentElement().normalize();  
        nSequenceFlow = nodesSFlow.getLength();
        
        //CONVERSATION
        XPathExpression exprConv = xpath.compile("//bpmn:conversation");
        Object resultConv = exprConv.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesConv = (NodeList) resultConv;
        doc.getDocumentElement().normalize();  
        nConversationNone = nodesConv.getLength();
        
        XPathExpression exprSConv = xpath.compile("//bpmn:subConversation");
        Object resultSConv = exprSConv.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesSConv = (NodeList) resultSConv;
        doc.getDocumentElement().normalize();  
        nConversationSubProcess = nodesSConv.getLength();
        
        XPathExpression exprCConv = xpath.compile("//bpmn:callConversation[(contains(@calledElementRef,'sid'))]");
        Object resultCConv = exprCConv.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesCConv = (NodeList) resultCConv;
        doc.getDocumentElement().normalize();  
        nConversationCall = nodesCConv.getLength();
        
        XPathExpression exprConvLink = xpath.compile("//bpmn:conversationLink");
        Object resultConvLink = exprConvLink.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesConvLink = (NodeList) resultConvLink;
        doc.getDocumentElement().normalize();  
        nConversationLink = nodesConvLink.getLength();        
        
        XPathExpression exprConvSBC = xpath.compile("//bpmn:callConversation[not(contains(@calledElementRef,'sid'))]");
        Object resultConvSBC = exprConvSBC.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesConvSBC = (NodeList) resultConvSBC;
        doc.getDocumentElement().normalize();  
        nConversationSubProcessCall = nodesConvSBC.getLength(); 
        
        // ASSOCIATIONS
        //dataInputAssociation
        XPathExpression exprIAssoc = xpath.compile("//bpmn:dataInputAssociation");
        Object resultIAssoc = exprIAssoc.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesIAssoc = (NodeList) resultIAssoc;
        doc.getDocumentElement().normalize();  
        nAssociationDataInput = nodesIAssoc.getLength();
        
        //dataOutputAssociation
        XPathExpression exprOAssoc = xpath.compile("//bpmn:dataOutputAssociation");
        Object resultOAssoc = exprOAssoc.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesOAssoc = (NodeList) resultOAssoc;
        doc.getDocumentElement().normalize();  
        nAssociationDataOutput = nodesOAssoc.getLength();
        
        //COMPENSATE ASSOCIATION
        XPathExpression exprCAssoc = xpath.compile("//bpmn:endEvent//bpmn:compensateEventDefinition[@waitForCompletion='true']");
        Object resultCAssoc = exprCAssoc.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesCAssoc = (NodeList) resultCAssoc;
        doc.getDocumentElement().normalize();  
        nAssociationCompensate = nodesCAssoc.getLength();
        
        //Unidirectional Association
        XPathExpression exprUnidirectionalAssoc = xpath.compile("//bpmn:association[@associationDirection='One']");
        Object resultUnidirectionalAssoc = exprUnidirectionalAssoc.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesUnidirectionalAssoc = (NodeList) resultUnidirectionalAssoc;
        doc.getDocumentElement().normalize();  
        nAssociationUnidirectional = nodesUnidirectionalAssoc.getLength();
        
        //Unidirected Association
        XPathExpression exprUndirectedAssoc = xpath.compile("//bpmn:association[@associationDirection='None']");
        Object resultUndirectedAssoc = exprUndirectedAssoc.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesUndirectedAssoc = (NodeList) resultUndirectedAssoc;
        doc.getDocumentElement().normalize();  
        nAssociationUndirected = nodesUndirectedAssoc.getLength();
        
        //Bidirectional Association
        XPathExpression exprBidirectionalAssoc = xpath.compile("//bpmn:association[@associationDirection='Both']");
        Object resultBidirectionalAssoc = exprBidirectionalAssoc.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesBidirectionalAssoc = (NodeList) resultBidirectionalAssoc;
        doc.getDocumentElement().normalize();  
        nAssociationBidirectional = nodesBidirectionalAssoc.getLength();
        
        //Text Annotation
        XPathExpression exprTextAnn = xpath.compile("//bpmn:textAnnotation");
        Object resultTextAnn = exprTextAnn.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTextAnn = (NodeList) resultTextAnn;
        doc.getDocumentElement().normalize();  
        nTextAnnotation = nodesTextAnn.getLength();
        
        //Extension Elements 
//        XPathExpression exprExtension = xpath.compile("//bpmn:extensionElements");
//        Object resultExtension = exprExtension.evaluate(doc, XPathConstants.NODESET);
//        NodeList nodesExtension = (NodeList) resultExtension;
//        doc.getDocumentElement().normalize();  
//        nOfExtensionElements = nodesExtension.getLength();
        
        	TotalElements = 0; //TODO
        	
        	//creating the rows 
            XSSFRow row = sheet.createRow((short)x+1);  
                      
            CellStyle styleLOW = wb.createCellStyle();  
            CellStyle styleMEDIUM = wb.createCellStyle();  
            CellStyle styleHIGH = wb.createCellStyle();  
     
            styleLOW.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());  
            styleLOW.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleMEDIUM.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());  
            styleMEDIUM.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleHIGH.setFillForegroundColor(IndexedColors.GREEN.getIndex());  
            styleHIGH.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            
            //inserting data        
            row.createCell(0  ).setCellValue(fileName);
            row.createCell(1  ).setCellValue(bpmnModeler);
            row.createCell(2  ).setCellValue(modelType);
            row.createCell(3  ).setCellValue(isEnglish);
            row.createCell(4  ).setCellValue(nTaskNoneLoopNoneCompensateNoneCallNone);
            row.createCell(5  ).setCellValue(nTaskNoneLoopNoneCompensateNoneCall);
            row.createCell(6  ).setCellValue(nTaskNoneLoopNoneCompensateCallNone);
            row.createCell(7  ).setCellValue(nTaskNoneLoopNoneCompensateCall);
            row.createCell(8  ).setCellValue(nTaskNoneLoopStandardCompensateNoneCallNone);
            row.createCell(9  ).setCellValue(nTaskNoneLoopStandardCompensateNoneCall);
            row.createCell(10 ).setCellValue(nTaskNoneLoopStandardCompensateCallNone);
            row.createCell(11 ).setCellValue(nTaskNoneLoopStandardCompensateCall);
            row.createCell(12 ).setCellValue(nTaskNoneLoopMIParallelCompensateNoneCallNone);
            row.createCell(13 ).setCellValue(nTaskNoneLoopMIParallelCompensateNoneCall);
            row.createCell(14 ).setCellValue(nTaskNoneLoopMIParallelCompensateCallNone);
            row.createCell(15 ).setCellValue(nTaskNoneLoopMIParallelCompensateCall);
            row.createCell(16 ).setCellValue(nTaskNoneLoopMISequentialCompensateNoneCallNone);
            row.createCell(17 ).setCellValue(nTaskNoneLoopMISequentialCompensateNoneCall);
            row.createCell(18 ).setCellValue(nTaskNoneLoopMISequentialCompensateCallNone);
            row.createCell(19 ).setCellValue(nTaskNoneLoopMISequentialCompensateCall);
            row.createCell(20 ).setCellValue(nTaskSendLoopNoneCompensateNone);
            row.createCell(21 ).setCellValue(nTaskSendLoopNoneCompensate);
            row.createCell(22 ).setCellValue(nTaskSendLoopStandardCompensateNone);           
            row.createCell(23 ).setCellValue(nTaskSendLoopStandardCompensate); 
            row.createCell(24 ).setCellValue(nTaskSendLoopMIParallelCompensateNone); 
            row.createCell(25 ).setCellValue(nTaskSendLoopMIParallelCompensate);
            row.createCell(26 ).setCellValue(nTaskSendLoopMISequentialCompensateNone); 
            row.createCell(27 ).setCellValue(nTaskSendLoopMISequentialCompensate);
            row.createCell(28 ).setCellValue(nTaskReceiveLoopNoneCompensateNone);            
            row.createCell(29 ).setCellValue(nTaskReceiveLoopNoneCompensate);           
            row.createCell(30 ).setCellValue(nTaskReceiveLoopStandardCompensateNone);            
            row.createCell(31 ).setCellValue(nTaskReceiveLoopStandardCompensate);            
            row.createCell(32 ).setCellValue(nTaskReceiveLoopMIParallelCompensateNone);                        
            row.createCell(33 ).setCellValue(nTaskReceiveLoopMIParallelCompensate);            
            row.createCell(34 ).setCellValue(nTaskReceiveLoopMISequentialCompensateNone);           
            row.createCell(35 ).setCellValue(nTaskReceiveLoopMISequentialCompensate);            
            row.createCell(36 ).setCellValue(nTaskUserLoopNoneCompensateNone);            
            row.createCell(37 ).setCellValue(nTaskUserLoopNoneCompensate);           
            row.createCell(38 ).setCellValue(nTaskUserLoopStandardCompensateNone);            
            row.createCell(39 ).setCellValue(nTaskUserLoopStandardCompensate);           
            row.createCell(40 ).setCellValue(nTaskUserLoopMIParallelCompensateNone);            
            row.createCell(41 ).setCellValue(nTaskUserLoopMIParallelCompensate);            
            row.createCell(42 ).setCellValue(nTaskUserLoopMISequentialCompensateNone);            
            row.createCell(43 ).setCellValue(nTaskUserLoopMISequentialCompensate);            
            row.createCell(44 ).setCellValue(nTaskManualLoopNoneCompensateNone);            
            row.createCell(45 ).setCellValue(nTaskManualLoopNoneCompensate);            
            row.createCell(46 ).setCellValue(nTaskManualLoopStandardCompensateNone);            
            row.createCell(47 ).setCellValue(nTaskManualLoopStandardCompensate);            
            row.createCell(48 ).setCellValue(nTaskManualLoopMIParallelCompensateNone);            
            row.createCell(49 ).setCellValue(nTaskManualLoopMIParallelCompensate);            
            row.createCell(50 ).setCellValue(nTaskManualLoopMISequentialCompensateNone);            
            row.createCell(51 ).setCellValue(nTaskManualLoopMISequentialCompensate);            
            row.createCell(52 ).setCellValue(nTaskBusinessRuleLoopNoneCompensateNone);            
            row.createCell(53 ).setCellValue(nTaskBusinessRuleLoopNoneCompensate);            
            row.createCell(54 ).setCellValue(nTaskBusinessRuleLoopStandardCompensateNone);            
            row.createCell(55 ).setCellValue(nTaskBusinessRuleLoopStandardCompensate);            
            row.createCell(56 ).setCellValue(nTaskBusinessRuleLoopMIParallelCompensateNone);           
            row.createCell(57 ).setCellValue(nTaskBusinessRuleLoopMIParallelCompensate);            
            row.createCell(58 ).setCellValue(nTaskBusinessRuleLoopMISequentialCompensateNone);           
            row.createCell(59 ).setCellValue(nTaskBusinessRuleLoopMISequentialCompensate);            
            row.createCell(60 ).setCellValue(nTaskServiceLoopNoneCompensateNone);            
            row.createCell(61 ).setCellValue(nTaskServiceLoopNoneCompensate);            
            row.createCell(62 ).setCellValue(nTaskServiceLoopStandardCompensateNone);            
            row.createCell(63 ).setCellValue(nTaskServiceLoopStandardCompensate);            
            row.createCell(64 ).setCellValue(nTaskServiceLoopMIParallelCompensateNone);            
            row.createCell(65 ).setCellValue(nTaskServiceLoopMIParallelCompensate);            
            row.createCell(66 ).setCellValue(nTaskServiceLoopMISequentialCompensateNone);            
            row.createCell(67 ).setCellValue(nTaskServiceLoopMISequentialCompensate);            
            row.createCell(68 ).setCellValue(nTaskScriptLoopNoneCompensateNone);            
            row.createCell(69 ).setCellValue(nTaskScriptLoopNoneCompensate);           
            row.createCell(70 ).setCellValue(nTaskScriptLoopStandardCompensateNone);            
            row.createCell(71 ).setCellValue(nTaskScriptLoopStandardCompensate);            
            row.createCell(72 ).setCellValue(nTaskScriptLoopMIParallelCompensateNone);            
            row.createCell(73 ).setCellValue(nTaskScriptLoopMIParallelCompensate);            
            row.createCell(74 ).setCellValue(nTaskScriptLoopMISequentialCompensateNone);            
            row.createCell(75 ).setCellValue(nTaskScriptLoopMISequentialCompensate);            
            row.createCell(76 ).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensateNone);
            row.createCell(77 ).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensate);
            row.createCell(78 ).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensateNone);
            row.createCell(79 ).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensate);
            row.createCell(80 ).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensateNone);
            row.createCell(81 ).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensate);
            row.createCell(82 ).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensateNone);
            row.createCell(83 ).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensate);
            row.createCell(84 ).setCellValue(nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone);
            row.createCell(85 ).setCellValue(nSubProcessExtendedEventNoneAdHocLoopNoneCompensate);
            row.createCell(86 ).setCellValue(nSubProcessExtendedEventNoneAdHocLoopParallelCompensateNone);
            row.createCell(87 ).setCellValue(nSubProcessExtendedEventNoneAdHocLoopParallelCompensate);
            row.createCell(88 ).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone);
            row.createCell(89 ).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate);
            row.createCell(90 ).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone);
            row.createCell(91 ).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate);
            row.createCell(92 ).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensateNone);
            row.createCell(93 ).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensate);
            row.createCell(94 ).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensateNone);
            row.createCell(95 ).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensate);
            row.createCell(96 ).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensateNone);
            row.createCell(97 ).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensate);
            row.createCell(98 ).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensateNone);
            row.createCell(99 ).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensate);
            row.createCell(100).setCellValue(nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone);
            row.createCell(101).setCellValue(nSubProcessExtendedEventNoneTransactionLoopNoneCompensate);
            row.createCell(102).setCellValue(nSubProcessExtendedEventNoneTransactionLoopParallelCompensateNone);
            row.createCell(103).setCellValue(nSubProcessExtendedEventNoneTransactionLoopParallelCompensate);
            row.createCell(104).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone);
            row.createCell(105).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate);
            row.createCell(106).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone);
            row.createCell(107).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate);
            row.createCell(108).setCellValue(nSubProcessExtendedEventAdHocNoneLoopNoneCompensateNone);
            row.createCell(109).setCellValue(nSubProcessExtendedEventAdHocNoneLoopNoneCompensate);
            row.createCell(110).setCellValue(nSubProcessExtendedEventAdHocNoneLoopParallelCompensateNone);
            row.createCell(111).setCellValue(nSubProcessExtendedEventAdHocNoneLoopParallelCompensate);
            row.createCell(112).setCellValue(nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensateNone);
            row.createCell(113).setCellValue(nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensate);
            row.createCell(114).setCellValue(nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensateNone);
            row.createCell(115).setCellValue(nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensate);
            row.createCell(116).setCellValue(nSubProcessExtendedEventAdHocLoopNoneCompensateNone);
            row.createCell(117).setCellValue(nSubProcessExtendedEventAdHocLoopNoneCompensate);
            row.createCell(118).setCellValue(nSubProcessExtendedEventAdHocLoopParallelCompensateNone);
            row.createCell(119).setCellValue(nSubProcessExtendedEventAdHocLoopParallelCompensate);
            row.createCell(120).setCellValue(nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone);
            row.createCell(121).setCellValue(nSubProcessExtendedEventAdHocLoopMIParallelCompensate);
            row.createCell(122).setCellValue(nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone);
            row.createCell(123).setCellValue(nSubProcessExtendedEventAdHocLoopMISequentialCompensate);
            row.createCell(124).setCellValue(nSubProcessExtendedEventTransactionNoneLoopNoneCompensateNone);
            row.createCell(125).setCellValue(nSubProcessExtendedEventTransactionNoneLoopNoneCompensate);
            row.createCell(126).setCellValue(nSubProcessExtendedEventTransactionNoneLoopParallelCompensateNone);
            row.createCell(127).setCellValue(nSubProcessExtendedEventTransactionNoneLoopParallelCompensate);
            row.createCell(128).setCellValue(nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensateNone);
            row.createCell(129).setCellValue(nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensate);
            row.createCell(130).setCellValue(nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensateNone);
            row.createCell(131).setCellValue(nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensate);
            row.createCell(132).setCellValue(nSubProcessExtendedEventTransactionLoopNoneCompensateNone);
            row.createCell(133).setCellValue(nSubProcessExtendedEventTransactionLoopNoneCompensate);
            row.createCell(134).setCellValue(nSubProcessExtendedEventTransactionLoopParallelCompensateNone);
            row.createCell(135).setCellValue(nSubProcessExtendedEventTransactionLoopParallelCompensate);
            row.createCell(136).setCellValue(nSubProcessExtendedEventTransactionLoopMIParallelCompensateNone);
            row.createCell(137).setCellValue(nSubProcessExtendedEventTransactionLoopMIParallelCompensate);
            row.createCell(138).setCellValue(nSubProcessExtendedEventTransactionLoopMISequentialCompensateNone);
            row.createCell(139).setCellValue(nSubProcessExtendedEventTransactionLoopMISequentialCompensate);
            row.createCell(140).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopNoneCompensateNone);
            row.createCell(141).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopNoneCompensate);
            row.createCell(142).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopParallelCompensateNone);
            row.createCell(143).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopParallelCompensate);
            row.createCell(144).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopMIParallelCompensateNone);
            row.createCell(145).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopMIParallelCompensate);
            row.createCell(146).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopMISequentialCompensateNone);
            row.createCell(147).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopMISequentialCompensate);
            row.createCell(148).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone);
            row.createCell(149).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate);
            row.createCell(150).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopParallelCompensateNone);
            row.createCell(151).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopParallelCompensate);
            row.createCell(152).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone);
            row.createCell(153).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate);
            row.createCell(154).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone);
            row.createCell(155).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate);
            row.createCell(156).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopNoneCompensateNone);
            row.createCell(157).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopNoneCompensate);
            row.createCell(158).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopParallelCompensateNone);
            row.createCell(159).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopParallelCompensate);
            row.createCell(160).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopMIParallelCompensateNone);
            row.createCell(161).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopMIParallelCompensate);
            row.createCell(162).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopMISequentialCompensateNone);
            row.createCell(163).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopMISequentialCompensate);
            row.createCell(164).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone);
            row.createCell(165).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate);
            row.createCell(166).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopParallelCompensateNone);
            row.createCell(167).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopParallelCompensate);
            row.createCell(168).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensateNone);
            row.createCell(169).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensate);
            row.createCell(170).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensateNone);
            row.createCell(171).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensate);
            row.createCell(172).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopNoneCompensateNone);
            row.createCell(173).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopNoneCompensate);
            row.createCell(174).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopParallelCompensateNone);
            row.createCell(175).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopParallelCompensate);
            row.createCell(176).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopMIParallelCompensateNone);
            row.createCell(177).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopMIParallelCompensate);
            row.createCell(178).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopMISequentialCompensateNone);
            row.createCell(179).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopMISequentialCompensate);
            row.createCell(180).setCellValue(nSubProcessCollapsedEventAdHocLoopNoneCompensateNone);
            row.createCell(181).setCellValue(nSubProcessCollapsedEventAdHocLoopNoneCompensate);
            row.createCell(182).setCellValue(nSubProcessCollapsedEventAdHocLoopParallelCompensateNone);
            row.createCell(183).setCellValue(nSubProcessCollapsedEventAdHocLoopParallelCompensate);
            row.createCell(184).setCellValue(nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone);
            row.createCell(185).setCellValue(nSubProcessCollapsedEventAdHocLoopMIParallelCompensate);
            row.createCell(186).setCellValue(nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone);
            row.createCell(187).setCellValue(nSubProcessCollapsedEventAdHocLoopMISequentialCompensate);
            row.createCell(188).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopNoneCompensateNone);
            row.createCell(189).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopNoneCompensate);
            row.createCell(190).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopParallelCompensateNone);
            row.createCell(191).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopParallelCompensate);
            row.createCell(192).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopMIParallelCompensateNone);
            row.createCell(193).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopMIParallelCompensate);
            row.createCell(194).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopMISequentialCompensateNone);
            row.createCell(195).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopMISequentialCompensate);
            row.createCell(196).setCellValue(nSubProcessCollapsedEventTransactionLoopNoneCompensateNone);
            row.createCell(197).setCellValue(nSubProcessCollapsedEventTransactionLoopNoneCompensate);
            row.createCell(198).setCellValue(nSubProcessCollapsedEventTransactionLoopParallelCompensateNone);
            row.createCell(199).setCellValue(nSubProcessCollapsedEventTransactionLoopParallelCompensate);
            row.createCell(200).setCellValue(nSubProcessCollapsedEventTransactionLoopMIParallelCompensateNone);
            row.createCell(201).setCellValue(nSubProcessCollapsedEventTransactionLoopMIParallelCompensate);
            row.createCell(202).setCellValue(nSubProcessCollapsedEventTransactionLoopMISequentialCompensateNone);
            row.createCell(203).setCellValue(nSubProcessCollapsedEventTransactionLoopMISequentialCompensate);
            row.createCell(204).setCellValue(nDataObject);
            row.createCell(205).setCellValue(nDataObjectCollection);
            row.createCell(206).setCellValue(nDataObjectReference);
            row.createCell(207).setCellValue(nDataStore);
            row.createCell(208).setCellValue(nDataInput);
            row.createCell(209).setCellValue(nDataOutput);
            row.createCell(210).setCellValue(nExclusiveGatewayNoMarker);
            row.createCell(211).setCellValue(nExclusiveGatewayMarker);
            row.createCell(212).setCellValue(nParallelGateway);
            row.createCell(213).setCellValue(nInclusiveGateway);
            row.createCell(214).setCellValue(nEventBasedGateway);
            row.createCell(215).setCellValue(nEventBasedGatewayExclusiveInstantiation);
            row.createCell(216).setCellValue(nEventBasedGatewayParallelInstantiation);
            row.createCell(217).setCellValue(nComplexGateway);
            row.createCell(218).setCellValue(nStartMultipleParallelEventDefinition);
            row.createCell(219).setCellValue(nStartMultipleEventDefinition);
            row.createCell(220).setCellValue(nStartNoneEventDefinition);
            row.createCell(221).setCellValue(nStartSignalEventDefinition);
            row.createCell(222).setCellValue(nStartConditionalEventDefinition);
            row.createCell(223).setCellValue(nStartTimerEventDefinition);
            row.createCell(224).setCellValue(nStartMessageEventDefinition);
            row.createCell(225).setCellValue(nStartCompensateEventDefinition);
            row.createCell(226).setCellValue(nStartEscalationEventDefinition);
            row.createCell(227).setCellValue(nStartErrorEventDefinition);
            row.createCell(228).setCellValue(nStartMessageEventSubProcessInterruptingDefinition);
            row.createCell(229).setCellValue(nStartTimerEventSubProcessInterruptingDefinition);
            row.createCell(230).setCellValue(nStartEscalationEventSubProcessInterruptingDefinition);
            row.createCell(231).setCellValue(nStartConditionalEventSubProcessInterruptingDefinition);
            row.createCell(232).setCellValue(nStartErrorEventSubProcessInterruptingDefinition);
            row.createCell(233).setCellValue(nStartCompensateEventSubProcessInterruptingDefinition);
            row.createCell(234).setCellValue(nStartSignalEventSubProcessInterruptingDefinition);
            row.createCell(235).setCellValue(nStartMultipleEventSubProcessInterruptingDefinition);
            row.createCell(236).setCellValue(nStartMultipleParallelEventSubProcessInterruptingDefinition);       
            row.createCell(237).setCellValue(nStartMessageEventSubProcessNonInterruptingDefinition);
            row.createCell(238).setCellValue(nStartTimerEventSubProcessNonInterruptingDefinition);
            row.createCell(239).setCellValue(nStartEscalationEventSubProcessNonInterruptingDefinition);
            row.createCell(240).setCellValue(nStartConditionalEventSubProcessNonInterruptingDefinition);
            row.createCell(241).setCellValue(nStartSignalEventSubProcessNonInterruptingDefinition);
            row.createCell(242).setCellValue(nStartMultipleParallelEventSubProcessNonInterruptingDefinition);
            row.createCell(243).setCellValue(nStartMultipleEventSubProcessNonInterruptingDefinition);       
            row.createCell(244).setCellValue(nEndNoneEventDefinition);
            row.createCell(245).setCellValue(nEndMultipleEventDefinition); 
            row.createCell(246).setCellValue(nEndEscalationEventDefinition);
            row.createCell(247).setCellValue(nEndErrorEventDefinition);
            row.createCell(248).setCellValue(nEndSignalEventDefinition);
            row.createCell(249).setCellValue(nEndCompensateEventDefinition);
            row.createCell(250).setCellValue(nEndCancelEventDefinition); 
            row.createCell(251).setCellValue(nEndMessageEventDefinition);
            row.createCell(252).setCellValue(nEndTerminateEventDefinition);
            row.createCell(253).setCellValue(nIntermediateCatchMultipleEventDefinition);
            row.createCell(254).setCellValue(nIntermediateCatchMultipleParallelEventDefinition);
            row.createCell(255).setCellValue(nIntermediateCatchMessageEventDefinition);
            row.createCell(256).setCellValue(nIntermediateCatchTimerEventDefinition);
            row.createCell(257).setCellValue(nIntermediateCatchConditionalEventDefinition);
            row.createCell(258).setCellValue(nIntermediateCatchLinkEventDefinition);
            row.createCell(259).setCellValue(nIntermediateCatchSignalEventDefinition);
            row.createCell(260).setCellValue(nIntermediateThrowNoneEventDefinition);
            row.createCell(261).setCellValue(nIntermediateThrowMessageEventDefinition);
            row.createCell(262).setCellValue(nIntermediateThrowEscalationEventDefinition);
            row.createCell(263).setCellValue(nIntermediateThrowLinkEventDefinition);
            row.createCell(264).setCellValue(nIntermediateThrowSignalEventDefinition);
            row.createCell(265).setCellValue(nIntermediateThrowCompensateEventDefinition);
            row.createCell(266).setCellValue(nIntermediateThrowMultipleParallelEventDefinition);
            row.createCell(267).setCellValue(nIntermediateBoundaryMessageEvent);
            row.createCell(268).setCellValue(nIntermediateBoundaryTimerEvent);
            row.createCell(269).setCellValue(nIntermediateBoundaryCancelEvent);
            row.createCell(270).setCellValue(nIntermediateBoundaryConditionalEvent );
            row.createCell(271).setCellValue(nIntermediateBoundaryEscalationEvent);
            row.createCell(272).setCellValue(nIntermediateBoundaryErrorEvent);
            row.createCell(273).setCellValue(nIntermediateBoundarySignalEvent);
            row.createCell(274).setCellValue(nIntermediateBoundaryCompensateEvent);
            row.createCell(275).setCellValue(nIntermediateBoundaryMultipleEvent);
            row.createCell(276).setCellValue(nIntermediateBoundaryMultipleParallelEvent);
            row.createCell(277).setCellValue(nIntermediateBoundaryTimerEventNonInterrupting);
            row.createCell(278).setCellValue(nIntermediateBoundaryEscalationEventNonInterrupting);
            row.createCell(279).setCellValue(nIntermediateBoundaryConditionalEventNonInterrupting);
            row.createCell(280).setCellValue(nIntermediateBoundaryMessageEventNonInterrupting);
            row.createCell(281).setCellValue(nIntermediateBoundarySignalEventNonInterrupting);
            row.createCell(282).setCellValue(nIntermediateBoundaryMultipleEventNonInterrupting);
            row.createCell(283).setCellValue(nIntermediateBoundaryMultipleParallelEventNonInterrupting);
            row.createCell(284).setCellValue(nMessageFlow);
            row.createCell(285).setCellValue(nSequenceFlow);
            row.createCell(286).setCellValue(nDefaultFlow);
            row.createCell(287).setCellValue(nConditionalFlow);
            row.createCell(288).setCellValue(nLane); 
            row.createCell(289).setCellValue(nPoolCollapsedMultiplicityNone);
            row.createCell(290).setCellValue(nPoolCollapsedMultiplicity);
            row.createCell(291).setCellValue(nPoolExpandedMultiplicityNone);
            row.createCell(292).setCellValue(nPoolExpandedMultiplicity);
            row.createCell(293).setCellValue(nChoreographyTask);
            row.createCell(294).setCellValue(nChoreographyMessage);            
            row.createCell(295).setCellValue(nChoreographyTaskSequentialMultipleInstance);
            row.createCell(296).setCellValue(nChoreographyTaskParallelMultipleInstance);
            row.createCell(297).setCellValue(nChoreographyTaskLoop);
            row.createCell(298).setCellValue(nChoreographySubprocessCollapsed);
            row.createCell(299).setCellValue(nChoreographySubprocessCollapsedParallelMultipleInstance);
            row.createCell(300).setCellValue(nChoreographySubprocessCollapsedSequentialMultipleInstance);
            row.createCell(301).setCellValue(nChoreographySubprocessCollapsedLoop);
            row.createCell(302).setCellValue(nChoreographySubprocessCollapsedCall);
            row.createCell(303).setCellValue(nChoreographySubprocessCollapsedCallSequentialMultipleInstance);
            row.createCell(304).setCellValue(nChoreographySubprocessCollapsedCallParallelMultipleInstance);
            row.createCell(305).setCellValue(nChoreographySubprocessCollapsedCallLoop);
            row.createCell(306).setCellValue(nChoreographySubprocessExpanded);
            row.createCell(307).setCellValue(nChoreographySubprocessExpandedSequentialMultipleInstance);
            row.createCell(308).setCellValue(nChoreographySubprocessExpandedParallelMultipleInstance);
            row.createCell(309).setCellValue(nChoreographySubprocessExpandedLoop);
            row.createCell(310).setCellValue(nChoreographyParticipant);
            row.createCell(311).setCellValue(nChoreographyParticipantMultiple);       
            row.createCell(312).setCellValue(nConversationNone);
            row.createCell(313).setCellValue(nConversationSubProcess);
            row.createCell(314).setCellValue(nConversationCall);
            row.createCell(315).setCellValue(nConversationSubProcessCall);
            row.createCell(316).setCellValue(nConversationLink);
            row.createCell(317).setCellValue(nAssociationCompensate);
            row.createCell(318).setCellValue(nAssociationUndirected);
            row.createCell(319).setCellValue(nAssociationUnidirectional);        
            row.createCell(320).setCellValue(nAssociationBidirectional);
            row.createCell(321).setCellValue(nAssociationDataOutput);
            row.createCell(322).setCellValue(nAssociationDataInput);            
            row.createCell(323).setCellValue(nGroup);
            row.createCell(324).setCellValue(nTextAnnotation);
            row.createCell(325).setCellValue(nOfExtensionElements);
            row.createCell(326).setCellValue(TotalElements);              
            
            for(Cell cell : row) {
            	String data="";
            	
	            	if(cell.getCellType()==CellType.NUMERIC) {
	            	    data = String.valueOf(cell.getNumericCellValue());
	            	double str1 = Double.parseDouble(data);
	            	
	            	if(str1 >= 1 && str1 <= 5){
	            		cell.setCellStyle(styleLOW);       
	                }
	            	
	            	if(str1 > 5 && str1 <= 10){
	            		cell.setCellStyle(styleMEDIUM);       
	                }
	            	
	            	if(str1 > 10){
	            		cell.setCellStyle(styleHIGH);       
	                }
            	}
            	
            }
           
            if(ConsiderExtendedSubProcess && nGeneralSubProcess>0) {
            	
            	String SubProcessModelID="";
            	
            	
                // TO READAPT
                XSSFRow rowhead2 = sheet2.createRow((short)0); 
                rowhead2.createCell(0  ).setCellValue("Original File Name");
                rowhead2.createCell(1  ).setCellValue("bpmnModeler");
                rowhead2.createCell(2  ).setCellValue("modelType");
                rowhead2.createCell(3  ).setCellValue("isEnglish");
                rowhead2.createCell(4  ).setCellValue("nTaskNoneLoopNoneCompensateNoneCallNone");
                rowhead2.createCell(5  ).setCellValue("nTaskNoneLoopNoneCompensateNoneCall");
                rowhead2.createCell(6  ).setCellValue("nTaskNoneLoopNoneCompensateCallNone");
                rowhead2.createCell(7  ).setCellValue("nTaskNoneLoopNoneCompensateCall");
                rowhead2.createCell(8  ).setCellValue("nTaskNoneLoopStandardCompensateNoneCallNone");
                rowhead2.createCell(9  ).setCellValue("nTaskNoneLoopStandardCompensateNoneCall");
                rowhead2.createCell(10 ).setCellValue("nTaskNoneLoopStandardCompensateCallNone");
                rowhead2.createCell(11 ).setCellValue("nTaskNoneLoopStandardCompensateCall");
                rowhead2.createCell(12 ).setCellValue("nTaskNoneLoopMIParallelCompensateNoneCallNone");
                rowhead2.createCell(13 ).setCellValue("nTaskNoneLoopMIParallelCompensateNoneCall");
                rowhead2.createCell(14 ).setCellValue("nTaskNoneLoopMIParallelCompensateCallNone");
                rowhead2.createCell(15 ).setCellValue("nTaskNoneLoopMIParallelCompensateCall");
                rowhead2.createCell(16 ).setCellValue("nTaskNoneLoopMISequentialCompensateNoneCallNone");
                rowhead2.createCell(17 ).setCellValue("nTaskNoneLoopMISequentialCompensateNoneCall");
                rowhead2.createCell(18 ).setCellValue("nTaskNoneLoopMISequentialCompensateCallNone");
                rowhead2.createCell(19 ).setCellValue("nTaskNoneLoopMISequentialCompensateCall");
                rowhead2.createCell(20 ).setCellValue("nTaskSendLoopNoneCompensateNone");
                rowhead2.createCell(21 ).setCellValue("nTaskSendLoopNoneCompensate");
                rowhead2.createCell(22 ).setCellValue("nTaskSendLoopStandardCompensateNone");           
                rowhead2.createCell(23 ).setCellValue("nTaskSendLoopStandardCompensate"); 
                rowhead2.createCell(24 ).setCellValue("nTaskSendLoopMIParallelCompensateNone"); 
                rowhead2.createCell(25 ).setCellValue("nTaskSendLoopMIParallelCompensate");
                rowhead2.createCell(26 ).setCellValue("nTaskSendLoopMISequentialCompensateNone"); 
                rowhead2.createCell(27 ).setCellValue("nTaskSendLoopMISequentialCompensate");
                rowhead2.createCell(28 ).setCellValue("nTaskReceiveLoopNoneCompensateNone");            
                rowhead2.createCell(29 ).setCellValue("nTaskReceiveLoopNoneCompensate");           
                rowhead2.createCell(30 ).setCellValue("nTaskReceiveLoopStandardCompensateNone");            
                rowhead2.createCell(31 ).setCellValue("nTaskReceiveLoopStandardCompensate");            
                rowhead2.createCell(32 ).setCellValue("nTaskReceiveLoopMIParallelCompensateNone");                        
                rowhead2.createCell(33 ).setCellValue("nTaskReceiveLoopMIParallelCompensate");            
                rowhead2.createCell(34 ).setCellValue("nTaskReceiveLoopMISequentialCompensateNone");           
                rowhead2.createCell(35 ).setCellValue("nTaskReceiveLoopMISequentialCompensate");            
                rowhead2.createCell(36 ).setCellValue("nTaskUserLoopNoneCompensateNone");            
                rowhead2.createCell(37 ).setCellValue("nTaskUserLoopNoneCompensate");           
                rowhead2.createCell(38 ).setCellValue("nTaskUserLoopStandardCompensateNone");            
                rowhead2.createCell(39 ).setCellValue("nTaskUserLoopStandardCompensate");           
                rowhead2.createCell(40 ).setCellValue("nTaskUserLoopMIParallelCompensateNone");            
                rowhead2.createCell(41 ).setCellValue("nTaskUserLoopMIParallelCompensate");            
                rowhead2.createCell(42 ).setCellValue("nTaskUserLoopMISequentialCompensateNone");            
                rowhead2.createCell(43 ).setCellValue("nTaskUserLoopMISequentialCompensate");            
                rowhead2.createCell(44 ).setCellValue("nTaskManualLoopNoneCompensateNone");            
                rowhead2.createCell(45 ).setCellValue("nTaskManualLoopNoneCompensate");            
                rowhead2.createCell(46 ).setCellValue("nTaskManualLoopStandardCompensateNone");            
                rowhead2.createCell(47 ).setCellValue("nTaskManualLoopStandardCompensate");            
                rowhead2.createCell(48 ).setCellValue("nTaskManualLoopMIParallelCompensateNone");            
                rowhead2.createCell(49 ).setCellValue("nTaskManualLoopMIParallelCompensate");            
                rowhead2.createCell(50 ).setCellValue("nTaskManualLoopMISequentialCompensateNone");            
                rowhead2.createCell(51 ).setCellValue("nTaskManualLoopMISequentialCompensate");            
                rowhead2.createCell(52 ).setCellValue("nTaskBusinessRuleLoopNoneCompensateNone");            
                rowhead2.createCell(53 ).setCellValue("nTaskBusinessRuleLoopNoneCompensate");            
                rowhead2.createCell(54 ).setCellValue("nTaskBusinessRuleLoopStandardCompensateNone");            
                rowhead2.createCell(55 ).setCellValue("nTaskBusinessRuleLoopStandardCompensate");            
                rowhead2.createCell(56 ).setCellValue("nTaskBusinessRuleLoopMIParallelCompensateNone");           
                rowhead2.createCell(57 ).setCellValue("nTaskBusinessRuleLoopMIParallelCompensate");            
                rowhead2.createCell(58 ).setCellValue("nTaskBusinessRuleLoopMISequentialCompensateNone");           
                rowhead2.createCell(59 ).setCellValue("nTaskBusinessRuleLoopMISequentialCompensate");            
                rowhead2.createCell(60 ).setCellValue("nTaskServiceLoopNoneCompensateNone");            
                rowhead2.createCell(61 ).setCellValue("nTaskServiceLoopNoneCompensate");            
                rowhead2.createCell(62 ).setCellValue("nTaskServiceLoopStandardCompensateNone");            
                rowhead2.createCell(63 ).setCellValue("nTaskServiceLoopStandardCompensate");            
                rowhead2.createCell(64 ).setCellValue("nTaskServiceLoopMIParallelCompensateNone");            
                rowhead2.createCell(65 ).setCellValue("nTaskServiceLoopMIParallelCompensate");            
                rowhead2.createCell(66 ).setCellValue("nTaskServiceLoopMISequentialCompensateNone");            
                rowhead2.createCell(67 ).setCellValue("nTaskServiceLoopMISequentialCompensate");            
                rowhead2.createCell(68 ).setCellValue("nTaskScriptLoopNoneCompensateNone");            
                rowhead2.createCell(69 ).setCellValue("nTaskScriptLoopNoneCompensate");           
                rowhead2.createCell(70 ).setCellValue("nTaskScriptLoopStandardCompensateNone");            
                rowhead2.createCell(71 ).setCellValue("nTaskScriptLoopStandardCompensate");            
                rowhead2.createCell(72 ).setCellValue("nTaskScriptLoopMIParallelCompensateNone");            
                rowhead2.createCell(73 ).setCellValue("nTaskScriptLoopMIParallelCompensate");            
                rowhead2.createCell(74 ).setCellValue("nTaskScriptLoopMISequentialCompensateNone");            
                rowhead2.createCell(75 ).setCellValue("nTaskScriptLoopMISequentialCompensate");            
                rowhead2.createCell(76 ).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensateNone");
                rowhead2.createCell(77 ).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensate");
                rowhead2.createCell(78 ).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensateNone");
                rowhead2.createCell(79 ).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensate");
                rowhead2.createCell(80 ).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensateNone");
                rowhead2.createCell(81 ).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensate");
                rowhead2.createCell(82 ).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensateNone");
                rowhead2.createCell(83 ).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensate");
                rowhead2.createCell(84 ).setCellValue("nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone");
                rowhead2.createCell(85 ).setCellValue("nSubProcessExtendedEventNoneAdHocLoopNoneCompensate");
                rowhead2.createCell(86 ).setCellValue("nSubProcessExtendedEventNoneAdHocLoopParallelCompensateNone");
                rowhead2.createCell(87 ).setCellValue("nSubProcessExtendedEventNoneAdHocLoopParallelCompensate");
                rowhead2.createCell(88 ).setCellValue("nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone");
                rowhead2.createCell(89 ).setCellValue("nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate");
                rowhead2.createCell(90 ).setCellValue("nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone");
                rowhead2.createCell(91 ).setCellValue("nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate");
                rowhead2.createCell(92 ).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensateNone");
                rowhead2.createCell(93 ).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensate");
                rowhead2.createCell(94 ).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensateNone");
                rowhead2.createCell(95 ).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensate");
                rowhead2.createCell(96 ).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensateNone");
                rowhead2.createCell(97 ).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensate");
                rowhead2.createCell(98 ).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensateNone");
                rowhead2.createCell(99 ).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensate");
                rowhead2.createCell(100).setCellValue("nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone");
                rowhead2.createCell(101).setCellValue("nSubProcessExtendedEventNoneTransactionLoopNoneCompensate");
                rowhead2.createCell(102).setCellValue("nSubProcessExtendedEventNoneTransactionLoopParallelCompensateNone");
                rowhead2.createCell(103).setCellValue("nSubProcessExtendedEventNoneTransactionLoopParallelCompensate");
                rowhead2.createCell(104).setCellValue("nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone");
                rowhead2.createCell(105).setCellValue("nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate");
                rowhead2.createCell(106).setCellValue("nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone");
                rowhead2.createCell(107).setCellValue("nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate");
                rowhead2.createCell(108).setCellValue("nSubProcessExtendedEventAdHocNoneLoopNoneCompensateNone");
                rowhead2.createCell(109).setCellValue("nSubProcessExtendedEventAdHocNoneLoopNoneCompensate");
                rowhead2.createCell(110).setCellValue("nSubProcessExtendedEventAdHocNoneLoopParallelCompensateNone");
                rowhead2.createCell(111).setCellValue("nSubProcessExtendedEventAdHocNoneLoopParallelCompensate");
                rowhead2.createCell(112).setCellValue("nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensateNone");
                rowhead2.createCell(113).setCellValue("nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensate");
                rowhead2.createCell(114).setCellValue("nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensateNone");
                rowhead2.createCell(115).setCellValue("nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensate");
                rowhead2.createCell(116).setCellValue("nSubProcessExtendedEventAdHocLoopNoneCompensateNone");
                rowhead2.createCell(117).setCellValue("nSubProcessExtendedEventAdHocLoopNoneCompensate");
                rowhead2.createCell(118).setCellValue("nSubProcessExtendedEventAdHocLoopParallelCompensateNone");
                rowhead2.createCell(119).setCellValue("nSubProcessExtendedEventAdHocLoopParallelCompensate");
                rowhead2.createCell(120).setCellValue("nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone");
                rowhead2.createCell(121).setCellValue("nSubProcessExtendedEventAdHocLoopMIParallelCompensate");
                rowhead2.createCell(122).setCellValue("nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone");
                rowhead2.createCell(123).setCellValue("nSubProcessExtendedEventAdHocLoopMISequentialCompensate");
                rowhead2.createCell(124).setCellValue("nSubProcessExtendedEventTransactionNoneLoopNoneCompensateNone");
                rowhead2.createCell(125).setCellValue("nSubProcessExtendedEventTransactionNoneLoopNoneCompensate");
                rowhead2.createCell(126).setCellValue("nSubProcessExtendedEventTransactionNoneLoopParallelCompensateNone");
                rowhead2.createCell(127).setCellValue("nSubProcessExtendedEventTransactionNoneLoopParallelCompensate");
                rowhead2.createCell(128).setCellValue("nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensateNone");
                rowhead2.createCell(129).setCellValue("nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensate");
                rowhead2.createCell(130).setCellValue("nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensateNone");
                rowhead2.createCell(131).setCellValue("nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensate");
                rowhead2.createCell(132).setCellValue("nSubProcessExtendedEventTransactionLoopNoneCompensateNone");
                rowhead2.createCell(133).setCellValue("nSubProcessExtendedEventTransactionLoopNoneCompensate");
                rowhead2.createCell(134).setCellValue("nSubProcessExtendedEventTransactionLoopParallelCompensateNone");
                rowhead2.createCell(135).setCellValue("nSubProcessExtendedEventTransactionLoopParallelCompensate");
                rowhead2.createCell(136).setCellValue("nSubProcessExtendedEventTransactionLoopMIParallelCompensateNone");
                rowhead2.createCell(137).setCellValue("nSubProcessExtendedEventTransactionLoopMIParallelCompensate");
                rowhead2.createCell(138).setCellValue("nSubProcessExtendedEventTransactionLoopMISequentialCompensateNone");
                rowhead2.createCell(139).setCellValue("nSubProcessExtendedEventTransactionLoopMISequentialCompensate");
                rowhead2.createCell(140).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopNoneCompensateNone");
                rowhead2.createCell(141).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopNoneCompensate");
                rowhead2.createCell(142).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopParallelCompensateNone");
                rowhead2.createCell(143).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopParallelCompensate");
                rowhead2.createCell(144).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopMIParallelCompensateNone");
                rowhead2.createCell(145).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopMIParallelCompensate");
                rowhead2.createCell(146).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopMISequentialCompensateNone");
                rowhead2.createCell(147).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopMISequentialCompensate");
                rowhead2.createCell(148).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone");
                rowhead2.createCell(149).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate");
                rowhead2.createCell(150).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopParallelCompensateNone");
                rowhead2.createCell(151).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopParallelCompensate");
                rowhead2.createCell(152).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone");
                rowhead2.createCell(153).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate");
                rowhead2.createCell(154).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone");
                rowhead2.createCell(155).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate");
                rowhead2.createCell(156).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopNoneCompensateNone");
                rowhead2.createCell(157).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopNoneCompensate");
                rowhead2.createCell(158).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopParallelCompensateNone");
                rowhead2.createCell(159).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopParallelCompensate");
                rowhead2.createCell(160).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopMIParallelCompensateNone");
                rowhead2.createCell(161).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopMIParallelCompensate");
                rowhead2.createCell(162).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopMISequentialCompensateNone");
                rowhead2.createCell(163).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopMISequentialCompensate");
                rowhead2.createCell(164).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone");
                rowhead2.createCell(165).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate");
                rowhead2.createCell(166).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopParallelCompensateNone");
                rowhead2.createCell(167).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopParallelCompensate");
                rowhead2.createCell(168).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensateNone");
                rowhead2.createCell(169).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensate");
                rowhead2.createCell(170).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensateNone");
                rowhead2.createCell(171).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensate");
                rowhead2.createCell(172).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopNoneCompensateNone");
                rowhead2.createCell(173).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopNoneCompensate");
                rowhead2.createCell(174).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopParallelCompensateNone");
                rowhead2.createCell(175).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopParallelCompensate");
                rowhead2.createCell(176).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopMIParallelCompensateNone");
                rowhead2.createCell(177).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopMIParallelCompensate");
                rowhead2.createCell(178).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopMISequentialCompensateNone");
                rowhead2.createCell(179).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopMISequentialCompensate");
                rowhead2.createCell(180).setCellValue("nSubProcessCollapsedEventAdHocLoopNoneCompensateNone");
                rowhead2.createCell(181).setCellValue("nSubProcessCollapsedEventAdHocLoopNoneCompensate");
                rowhead2.createCell(182).setCellValue("nSubProcessCollapsedEventAdHocLoopParallelCompensateNone");
                rowhead2.createCell(183).setCellValue("nSubProcessCollapsedEventAdHocLoopParallelCompensate");
                rowhead2.createCell(184).setCellValue("nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone");
                rowhead2.createCell(185).setCellValue("nSubProcessCollapsedEventAdHocLoopMIParallelCompensate");
                rowhead2.createCell(186).setCellValue("nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone");
                rowhead2.createCell(187).setCellValue("nSubProcessCollapsedEventAdHocLoopMISequentialCompensate");
                rowhead2.createCell(188).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopNoneCompensateNone");
                rowhead2.createCell(189).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopNoneCompensate");
                rowhead2.createCell(190).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopParallelCompensateNone");
                rowhead2.createCell(191).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopParallelCompensate");
                rowhead2.createCell(192).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopMIParallelCompensateNone");
                rowhead2.createCell(193).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopMIParallelCompensate");
                rowhead2.createCell(194).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopMISequentialCompensateNone");
                rowhead2.createCell(195).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopMISequentialCompensate");
                rowhead2.createCell(196).setCellValue("nSubProcessCollapsedEventTransactionLoopNoneCompensateNone");
                rowhead2.createCell(197).setCellValue("nSubProcessCollapsedEventTransactionLoopNoneCompensate");
                rowhead2.createCell(198).setCellValue("nSubProcessCollapsedEventTransactionLoopParallelCompensateNone");
                rowhead2.createCell(199).setCellValue("nSubProcessCollapsedEventTransactionLoopParallelCompensate");
                rowhead2.createCell(200).setCellValue("nSubProcessCollapsedEventTransactionLoopMIParallelCompensateNone");
                rowhead2.createCell(201).setCellValue("nSubProcessCollapsedEventTransactionLoopMIParallelCompensate");
                rowhead2.createCell(202).setCellValue("nSubProcessCollapsedEventTransactionLoopMISequentialCompensateNone");
                rowhead2.createCell(203).setCellValue("nSubProcessCollapsedEventTransactionLoopMISequentialCompensate");
                rowhead2.createCell(204).setCellValue("nDataObject");
                rowhead2.createCell(205).setCellValue("nDataObjectCollection");
                rowhead2.createCell(206).setCellValue("nDataObjectReference");
                rowhead2.createCell(207).setCellValue("nDataStore");
                rowhead2.createCell(208).setCellValue("nDataInput");
                rowhead2.createCell(209).setCellValue("nDataOutput");
                rowhead2.createCell(210).setCellValue("nExclusiveGatewayNoMarker");
                rowhead2.createCell(211).setCellValue("nExclusiveGatewayMarker");
                rowhead2.createCell(212).setCellValue("nParallelGateway");
                rowhead2.createCell(213).setCellValue("nInclusiveGateway");
                rowhead2.createCell(214).setCellValue("nEventBasedGateway");
                rowhead2.createCell(215).setCellValue("nEventBasedGatewayExclusiveInstantiation");
                rowhead2.createCell(216).setCellValue("nEventBasedGatewayParallelInstantiation");
                rowhead2.createCell(217).setCellValue("nComplexGateway");
                rowhead2.createCell(218).setCellValue("nStartMultipleParallelEventDefinition");
                rowhead2.createCell(219).setCellValue("nStartMultipleEventDefinition");
                rowhead2.createCell(220).setCellValue("nStartNoneEventDefinition");
                rowhead2.createCell(221).setCellValue("nStartSignalEventDefinition");
                rowhead2.createCell(222).setCellValue("nStartConditionalEventDefinition");
                rowhead2.createCell(223).setCellValue("nStartTimerEventDefinition");
                rowhead2.createCell(224).setCellValue("nStartMessageEventDefinition");
                rowhead2.createCell(225).setCellValue("nStartCompensateEventDefinition");
                rowhead2.createCell(226).setCellValue("nStartEscalationEventDefinition");
                rowhead2.createCell(227).setCellValue("nStartErrorEventDefinition");
                rowhead2.createCell(228).setCellValue("nStartMessageEventSubProcessInterruptingDefinition");
                rowhead2.createCell(229).setCellValue("nStartTimerEventSubProcessInterruptingDefinition");
                rowhead2.createCell(230).setCellValue("nStartEscalationEventSubProcessInterruptingDefinition");
                rowhead2.createCell(231).setCellValue("nStartConditionalEventSubProcessInterruptingDefinition");
                rowhead2.createCell(232).setCellValue("nStartErrorEventSubProcessInterruptingDefinition");
                rowhead2.createCell(233).setCellValue("nStartCompensateEventSubProcessInterruptingDefinition");
                rowhead2.createCell(234).setCellValue("nStartSignalEventSubProcessInterruptingDefinition");
                rowhead2.createCell(235).setCellValue("nStartMultipleEventSubProcessInterruptingDefinition");
                rowhead2.createCell(236).setCellValue("nStartMultipleParallelEventSubProcessInterruptingDefinition");       
                rowhead2.createCell(237).setCellValue("nStartMessageEventSubProcessNonInterruptingDefinition");
                rowhead2.createCell(238).setCellValue("nStartTimerEventSubProcessNonInterruptingDefinition");
                rowhead2.createCell(239).setCellValue("nStartEscalationEventSubProcessNonInterruptingDefinition");
                rowhead2.createCell(240).setCellValue("nStartConditionalEventSubProcessNonInterruptingDefinition");
                rowhead2.createCell(241).setCellValue("nStartSignalEventSubProcessNonInterruptingDefinition");
                rowhead2.createCell(242).setCellValue("nStartMultipleParallelEventSubProcessNonInterruptingDefinition");
                rowhead2.createCell(243).setCellValue("nStartMultipleEventSubProcessNonInterruptingDefinition");       
                rowhead2.createCell(244).setCellValue("nEndNoneEventDefinition");
                rowhead2.createCell(245).setCellValue("nEndMultipleEventDefinition"); 
                rowhead2.createCell(246).setCellValue("nEndEscalationEventDefinition");
                rowhead2.createCell(247).setCellValue("nEndErrorEventDefinition");
                rowhead2.createCell(248).setCellValue("nEndSignalEventDefinition");
                rowhead2.createCell(249).setCellValue("nEndCompensateEventDefinition");
                rowhead2.createCell(250).setCellValue("nEndCancelEventDefinition"); 
                rowhead2.createCell(251).setCellValue("nEndMessageEventDefinition");
                rowhead2.createCell(252).setCellValue("nEndTerminateEventDefinition");
                rowhead2.createCell(253).setCellValue("nIntermediateCatchMultipleEventDefinition");
                rowhead2.createCell(254).setCellValue("nIntermediateCatchMultipleParallelEventDefinition");
                rowhead2.createCell(255).setCellValue("nIntermediateCatchMessageEventDefinition");
                rowhead2.createCell(256).setCellValue("nIntermediateCatchTimerEventDefinition");
                rowhead2.createCell(257).setCellValue("nIntermediateCatchConditionalEventDefinition");
                rowhead2.createCell(258).setCellValue("nIntermediateCatchLinkEventDefinition");
                rowhead2.createCell(259).setCellValue("nIntermediateCatchSignalEventDefinition");
                rowhead2.createCell(260).setCellValue("nIntermediateThrowNoneEventDefinition");
                rowhead2.createCell(261).setCellValue("nIntermediateThrowMessageEventDefinition");
                rowhead2.createCell(262).setCellValue("nIntermediateThrowEscalationEventDefinition");
                rowhead2.createCell(263).setCellValue("nIntermediateThrowLinkEventDefinition");
                rowhead2.createCell(264).setCellValue("nIntermediateThrowSignalEventDefinition");
                rowhead2.createCell(265).setCellValue("nIntermediateThrowCompensateEventDefinition");
                rowhead2.createCell(266).setCellValue("nIntermediateThrowMultipleParallelEventDefinition");
                rowhead2.createCell(267).setCellValue("nIntermediateBoundaryMessageEvent");
                rowhead2.createCell(268).setCellValue("nIntermediateBoundaryTimerEvent");
                rowhead2.createCell(269).setCellValue("nIntermediateBoundaryCancelEvent");
                rowhead2.createCell(270).setCellValue("nIntermediateBoundaryConditionalEvent ");
                rowhead2.createCell(271).setCellValue("nIntermediateBoundaryEscalationEvent");
                rowhead2.createCell(272).setCellValue("nIntermediateBoundaryErrorEvent");
                rowhead2.createCell(273).setCellValue("nIntermediateBoundarySignalEvent");
                rowhead2.createCell(274).setCellValue("nIntermediateBoundaryCompensateEvent");
                rowhead2.createCell(275).setCellValue("nIntermediateBoundaryMultipleEvent");
                rowhead2.createCell(276).setCellValue("nIntermediateBoundaryMultipleParallelEvent");
                rowhead2.createCell(277).setCellValue("nIntermediateBoundaryTimerEventNonInterrupting");
                rowhead2.createCell(278).setCellValue("nIntermediateBoundaryEscalationEventNonInterrupting");
                rowhead2.createCell(279).setCellValue("nIntermediateBoundaryConditionalEventNonInterrupting");
                rowhead2.createCell(280).setCellValue("nIntermediateBoundaryMessageEventNonInterrupting");
                rowhead2.createCell(281).setCellValue("nIntermediateBoundarySignalEventNonInterrupting");
                rowhead2.createCell(282).setCellValue("nIntermediateBoundaryMultipleEventNonInterrupting");
                rowhead2.createCell(283).setCellValue("nIntermediateBoundaryMultipleParallelEventNonInterrupting");
                rowhead2.createCell(284).setCellValue("nMessageFlow");
                rowhead2.createCell(285).setCellValue("nSequenceFlow");
                rowhead2.createCell(286).setCellValue("nDefaultFlow");
                rowhead2.createCell(287).setCellValue("nConditionalFlow");
                rowhead2.createCell(288).setCellValue("nLane"); 
                rowhead2.createCell(289).setCellValue("nPoolCollapsedMultiplicityNone");
                rowhead2.createCell(290).setCellValue("nPoolCollapsedMultiplicity");
                rowhead2.createCell(291).setCellValue("nPoolExpandedMultiplicityNone");
                rowhead2.createCell(292).setCellValue("nPoolExpandedMultiplicity");
                rowhead2.createCell(293).setCellValue("nChoreographyTask");
                rowhead2.createCell(294).setCellValue("nChoreographyMessage");            
                rowhead2.createCell(295).setCellValue("nChoreographyTaskSequentialMultipleInstance");
                rowhead2.createCell(296).setCellValue("nChoreographyTaskParallelMultipleInstance");
                rowhead2.createCell(297).setCellValue("nChoreographyTaskLoop");
                rowhead2.createCell(298).setCellValue("nChoreographySubprocessCollapsed");
                rowhead2.createCell(299).setCellValue("nChoreographySubprocessCollapsedParallelMultipleInstance");
                rowhead2.createCell(300).setCellValue("nChoreographySubprocessCollapsedSequentialMultipleInstance");
                rowhead2.createCell(301).setCellValue("nChoreographySubprocessCollapsedLoop");
                rowhead2.createCell(302).setCellValue("nChoreographySubprocessCollapsedCall");
                rowhead2.createCell(303).setCellValue("nChoreographySubprocessCollapsedCallSequentialMultipleInstance");
                rowhead2.createCell(304).setCellValue("nChoreographySubprocessCollapsedCallParallelMultipleInstance");
                rowhead2.createCell(305).setCellValue("nChoreographySubprocessCollapsedCallLoop");
                rowhead2.createCell(306).setCellValue("nChoreographySubprocessExpanded");
                rowhead2.createCell(307).setCellValue("nChoreographySubprocessExpandedSequentialMultipleInstance");
                rowhead2.createCell(308).setCellValue("nChoreographySubprocessExpandedParallelMultipleInstance");
                rowhead2.createCell(309).setCellValue("nChoreographySubprocessExpandedLoop");
                rowhead2.createCell(310).setCellValue("nChoreographyParticipant");
                rowhead2.createCell(311).setCellValue("nChoreographyParticipantMultiple");       
                rowhead2.createCell(312).setCellValue("nConversationNone");
                rowhead2.createCell(313).setCellValue("nConversationSubProcess");
                rowhead2.createCell(314).setCellValue("nConversationCall");
                rowhead2.createCell(315).setCellValue("nConversationSubProcessCall");
                rowhead2.createCell(316).setCellValue("nConversationLink");
                rowhead2.createCell(317).setCellValue("nAssociationCompensate");
                rowhead2.createCell(318).setCellValue("nAssociationUndirected");
                rowhead2.createCell(319).setCellValue("nAssociationUnidirectional");        
                rowhead2.createCell(320).setCellValue("nAssociationBidirectional");
                rowhead2.createCell(321).setCellValue("nAssociationDataOutput");
                rowhead2.createCell(322).setCellValue("nAssociationDataInput");
                rowhead2.createCell(323).setCellValue("nGroup");
                rowhead2.createCell(324).setCellValue("nTextAnnotation");
                rowhead2.createCell(325).setCellValue("nOfExtensionElements");
                rowhead2.createCell(326).setCellValue("TotalElements");         
                
                // TO READAPT
                XSSFRow row2 = sheet2.createRow((short)x+1);  
                row2.createCell(0  ).setCellValue(fileName);
                row2.createCell(1  ).setCellValue(bpmnModeler);
                row2.createCell(2  ).setCellValue(modelType);
                row2.createCell(3  ).setCellValue(isEnglish);
                row2.createCell(4  ).setCellValue(nTaskNoneLoopNoneCompensateNoneCallNone);
                row2.createCell(5  ).setCellValue(nTaskNoneLoopNoneCompensateNoneCall);
                row2.createCell(6  ).setCellValue(nTaskNoneLoopNoneCompensateCallNone);
                row2.createCell(7  ).setCellValue(nTaskNoneLoopNoneCompensateCall);
                row2.createCell(8  ).setCellValue(nTaskNoneLoopStandardCompensateNoneCallNone);
                row2.createCell(9  ).setCellValue(nTaskNoneLoopStandardCompensateNoneCall);
                row2.createCell(10 ).setCellValue(nTaskNoneLoopStandardCompensateCallNone);
                row2.createCell(11 ).setCellValue(nTaskNoneLoopStandardCompensateCall);
                row2.createCell(12 ).setCellValue(nTaskNoneLoopMIParallelCompensateNoneCallNone);
                row2.createCell(13 ).setCellValue(nTaskNoneLoopMIParallelCompensateNoneCall);
                row2.createCell(14 ).setCellValue(nTaskNoneLoopMIParallelCompensateCallNone);
                row2.createCell(15 ).setCellValue(nTaskNoneLoopMIParallelCompensateCall);
                row2.createCell(16 ).setCellValue(nTaskNoneLoopMISequentialCompensateNoneCallNone);
                row2.createCell(17 ).setCellValue(nTaskNoneLoopMISequentialCompensateNoneCall);
                row2.createCell(18 ).setCellValue(nTaskNoneLoopMISequentialCompensateCallNone);
                row2.createCell(19 ).setCellValue(nTaskNoneLoopMISequentialCompensateCall);
                row2.createCell(20 ).setCellValue(nTaskSendLoopNoneCompensateNone);
                row2.createCell(21 ).setCellValue(nTaskSendLoopNoneCompensate);
                row2.createCell(22 ).setCellValue(nTaskSendLoopStandardCompensateNone);           
                row2.createCell(23 ).setCellValue(nTaskSendLoopStandardCompensate); 
                row2.createCell(24 ).setCellValue(nTaskSendLoopMIParallelCompensateNone); 
                row2.createCell(25 ).setCellValue(nTaskSendLoopMIParallelCompensate);
                row2.createCell(26 ).setCellValue(nTaskSendLoopMISequentialCompensateNone); 
                row2.createCell(27 ).setCellValue(nTaskSendLoopMISequentialCompensate);
                row2.createCell(28 ).setCellValue(nTaskReceiveLoopNoneCompensateNone);            
                row2.createCell(29 ).setCellValue(nTaskReceiveLoopNoneCompensate);           
                row2.createCell(30 ).setCellValue(nTaskReceiveLoopStandardCompensateNone);            
                row2.createCell(31 ).setCellValue(nTaskReceiveLoopStandardCompensate);            
                row2.createCell(32 ).setCellValue(nTaskReceiveLoopMIParallelCompensateNone);                        
                row2.createCell(33 ).setCellValue(nTaskReceiveLoopMIParallelCompensate);            
                row2.createCell(34 ).setCellValue(nTaskReceiveLoopMISequentialCompensateNone);           
                row2.createCell(35 ).setCellValue(nTaskReceiveLoopMISequentialCompensate);            
                row2.createCell(36 ).setCellValue(nTaskUserLoopNoneCompensateNone);            
                row2.createCell(37 ).setCellValue(nTaskUserLoopNoneCompensate);           
                row2.createCell(38 ).setCellValue(nTaskUserLoopStandardCompensateNone);            
                row2.createCell(39 ).setCellValue(nTaskUserLoopStandardCompensate);           
                row2.createCell(40 ).setCellValue(nTaskUserLoopMIParallelCompensateNone);            
                row2.createCell(41 ).setCellValue(nTaskUserLoopMIParallelCompensate);            
                row2.createCell(42 ).setCellValue(nTaskUserLoopMISequentialCompensateNone);            
                row2.createCell(43 ).setCellValue(nTaskUserLoopMISequentialCompensate);            
                row2.createCell(44 ).setCellValue(nTaskManualLoopNoneCompensateNone);            
                row2.createCell(45 ).setCellValue(nTaskManualLoopNoneCompensate);            
                row2.createCell(46 ).setCellValue(nTaskManualLoopStandardCompensateNone);            
                row2.createCell(47 ).setCellValue(nTaskManualLoopStandardCompensate);            
                row2.createCell(48 ).setCellValue(nTaskManualLoopMIParallelCompensateNone);            
                row2.createCell(49 ).setCellValue(nTaskManualLoopMIParallelCompensate);            
                row2.createCell(50 ).setCellValue(nTaskManualLoopMISequentialCompensateNone);            
                row2.createCell(51 ).setCellValue(nTaskManualLoopMISequentialCompensate);            
                row2.createCell(52 ).setCellValue(nTaskBusinessRuleLoopNoneCompensateNone);            
                row2.createCell(53 ).setCellValue(nTaskBusinessRuleLoopNoneCompensate);            
                row2.createCell(54 ).setCellValue(nTaskBusinessRuleLoopStandardCompensateNone);            
                row2.createCell(55 ).setCellValue(nTaskBusinessRuleLoopStandardCompensate);            
                row2.createCell(56 ).setCellValue(nTaskBusinessRuleLoopMIParallelCompensateNone);           
                row2.createCell(57 ).setCellValue(nTaskBusinessRuleLoopMIParallelCompensate);            
                row2.createCell(58 ).setCellValue(nTaskBusinessRuleLoopMISequentialCompensateNone);           
                row2.createCell(59 ).setCellValue(nTaskBusinessRuleLoopMISequentialCompensate);            
                row2.createCell(60 ).setCellValue(nTaskServiceLoopNoneCompensateNone);            
                row2.createCell(61 ).setCellValue(nTaskServiceLoopNoneCompensate);            
                row2.createCell(62 ).setCellValue(nTaskServiceLoopStandardCompensateNone);            
                row2.createCell(63 ).setCellValue(nTaskServiceLoopStandardCompensate);            
                row2.createCell(64 ).setCellValue(nTaskServiceLoopMIParallelCompensateNone);            
                row2.createCell(65 ).setCellValue(nTaskServiceLoopMIParallelCompensate);            
                row2.createCell(66 ).setCellValue(nTaskServiceLoopMISequentialCompensateNone);            
                row2.createCell(67 ).setCellValue(nTaskServiceLoopMISequentialCompensate);            
                row2.createCell(68 ).setCellValue(nTaskScriptLoopNoneCompensateNone);            
                row2.createCell(69 ).setCellValue(nTaskScriptLoopNoneCompensate);           
                row2.createCell(70 ).setCellValue(nTaskScriptLoopStandardCompensateNone);            
                row2.createCell(71 ).setCellValue(nTaskScriptLoopStandardCompensate);            
                row2.createCell(72 ).setCellValue(nTaskScriptLoopMIParallelCompensateNone);            
                row2.createCell(73 ).setCellValue(nTaskScriptLoopMIParallelCompensate);            
                row2.createCell(74 ).setCellValue(nTaskScriptLoopMISequentialCompensateNone);            
                row2.createCell(75 ).setCellValue(nTaskScriptLoopMISequentialCompensate);            
                row2.createCell(76 ).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensateNone);
                row2.createCell(77 ).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensate);
                row2.createCell(78 ).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensateNone);
                row2.createCell(79 ).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensate);
                row2.createCell(80 ).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensateNone);
                row2.createCell(81 ).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensate);
                row2.createCell(82 ).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensateNone);
                row2.createCell(83 ).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensate);
                row2.createCell(84 ).setCellValue(nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone);
                row2.createCell(85 ).setCellValue(nSubProcessExtendedEventNoneAdHocLoopNoneCompensate);
                row2.createCell(86 ).setCellValue(nSubProcessExtendedEventNoneAdHocLoopParallelCompensateNone);
                row2.createCell(87 ).setCellValue(nSubProcessExtendedEventNoneAdHocLoopParallelCompensate);
                row2.createCell(88 ).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone);
                row2.createCell(89 ).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate);
                row2.createCell(90 ).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone);
                row2.createCell(91 ).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate);
                row2.createCell(92 ).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensateNone);
                row2.createCell(93 ).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensate);
                row2.createCell(94 ).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensateNone);
                row2.createCell(95 ).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensate);
                row2.createCell(96 ).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensateNone);
                row2.createCell(97 ).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensate);
                row2.createCell(98 ).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensateNone);
                row2.createCell(99 ).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensate);
                row2.createCell(100).setCellValue(nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone);
                row2.createCell(101).setCellValue(nSubProcessExtendedEventNoneTransactionLoopNoneCompensate);
                row2.createCell(102).setCellValue(nSubProcessExtendedEventNoneTransactionLoopParallelCompensateNone);
                row2.createCell(103).setCellValue(nSubProcessExtendedEventNoneTransactionLoopParallelCompensate);
                row2.createCell(104).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone);
                row2.createCell(105).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate);
                row2.createCell(106).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone);
                row2.createCell(107).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate);
                row2.createCell(108).setCellValue(nSubProcessExtendedEventAdHocNoneLoopNoneCompensateNone);
                row2.createCell(109).setCellValue(nSubProcessExtendedEventAdHocNoneLoopNoneCompensate);
                row2.createCell(110).setCellValue(nSubProcessExtendedEventAdHocNoneLoopParallelCompensateNone);
                row2.createCell(111).setCellValue(nSubProcessExtendedEventAdHocNoneLoopParallelCompensate);
                row2.createCell(112).setCellValue(nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensateNone);
                row2.createCell(113).setCellValue(nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensate);
                row2.createCell(114).setCellValue(nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensateNone);
                row2.createCell(115).setCellValue(nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensate);
                row2.createCell(116).setCellValue(nSubProcessExtendedEventAdHocLoopNoneCompensateNone);
                row2.createCell(117).setCellValue(nSubProcessExtendedEventAdHocLoopNoneCompensate);
                row2.createCell(118).setCellValue(nSubProcessExtendedEventAdHocLoopParallelCompensateNone);
                row2.createCell(119).setCellValue(nSubProcessExtendedEventAdHocLoopParallelCompensate);
                row2.createCell(120).setCellValue(nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone);
                row2.createCell(121).setCellValue(nSubProcessExtendedEventAdHocLoopMIParallelCompensate);
                row2.createCell(122).setCellValue(nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone);
                row2.createCell(123).setCellValue(nSubProcessExtendedEventAdHocLoopMISequentialCompensate);
                row2.createCell(124).setCellValue(nSubProcessExtendedEventTransactionNoneLoopNoneCompensateNone);
                row2.createCell(125).setCellValue(nSubProcessExtendedEventTransactionNoneLoopNoneCompensate);
                row2.createCell(126).setCellValue(nSubProcessExtendedEventTransactionNoneLoopParallelCompensateNone);
                row2.createCell(127).setCellValue(nSubProcessExtendedEventTransactionNoneLoopParallelCompensate);
                row2.createCell(128).setCellValue(nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensateNone);
                row2.createCell(129).setCellValue(nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensate);
                row2.createCell(130).setCellValue(nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensateNone);
                row2.createCell(131).setCellValue(nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensate);
                row2.createCell(132).setCellValue(nSubProcessExtendedEventTransactionLoopNoneCompensateNone);
                row2.createCell(133).setCellValue(nSubProcessExtendedEventTransactionLoopNoneCompensate);
                row2.createCell(134).setCellValue(nSubProcessExtendedEventTransactionLoopParallelCompensateNone);
                row2.createCell(135).setCellValue(nSubProcessExtendedEventTransactionLoopParallelCompensate);
                row2.createCell(136).setCellValue(nSubProcessExtendedEventTransactionLoopMIParallelCompensateNone);
                row2.createCell(137).setCellValue(nSubProcessExtendedEventTransactionLoopMIParallelCompensate);
                row2.createCell(138).setCellValue(nSubProcessExtendedEventTransactionLoopMISequentialCompensateNone);
                row2.createCell(139).setCellValue(nSubProcessExtendedEventTransactionLoopMISequentialCompensate);
                row2.createCell(140).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopNoneCompensateNone);
                row2.createCell(141).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopNoneCompensate);
                row2.createCell(142).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopParallelCompensateNone);
                row2.createCell(143).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopParallelCompensate);
                row2.createCell(144).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopMIParallelCompensateNone);
                row2.createCell(145).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopMIParallelCompensate);
                row2.createCell(146).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopMISequentialCompensateNone);
                row2.createCell(147).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopMISequentialCompensate);
                row2.createCell(148).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone);
                row2.createCell(149).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate);
                row2.createCell(150).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopParallelCompensateNone);
                row2.createCell(151).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopParallelCompensate);
                row2.createCell(152).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone);
                row2.createCell(153).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate);
                row2.createCell(154).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone);
                row2.createCell(155).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate);
                row2.createCell(156).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopNoneCompensateNone);
                row2.createCell(157).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopNoneCompensate);
                row2.createCell(158).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopParallelCompensateNone);
                row2.createCell(159).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopParallelCompensate);
                row2.createCell(160).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopMIParallelCompensateNone);
                row2.createCell(161).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopMIParallelCompensate);
                row2.createCell(162).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopMISequentialCompensateNone);
                row2.createCell(163).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopMISequentialCompensate);
                row2.createCell(164).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone);
                row2.createCell(165).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate);
                row2.createCell(166).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopParallelCompensateNone);
                row2.createCell(167).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopParallelCompensate);
                row2.createCell(168).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensateNone);
                row2.createCell(169).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensate);
                row2.createCell(170).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensateNone);
                row2.createCell(171).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensate);
                row2.createCell(172).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopNoneCompensateNone);
                row2.createCell(173).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopNoneCompensate);
                row2.createCell(174).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopParallelCompensateNone);
                row2.createCell(175).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopParallelCompensate);
                row2.createCell(176).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopMIParallelCompensateNone);
                row2.createCell(177).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopMIParallelCompensate);
                row2.createCell(178).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopMISequentialCompensateNone);
                row2.createCell(179).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopMISequentialCompensate);
                row2.createCell(180).setCellValue(nSubProcessCollapsedEventAdHocLoopNoneCompensateNone);
                row2.createCell(181).setCellValue(nSubProcessCollapsedEventAdHocLoopNoneCompensate);
                row2.createCell(182).setCellValue(nSubProcessCollapsedEventAdHocLoopParallelCompensateNone);
                row2.createCell(183).setCellValue(nSubProcessCollapsedEventAdHocLoopParallelCompensate);
                row2.createCell(184).setCellValue(nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone);
                row2.createCell(185).setCellValue(nSubProcessCollapsedEventAdHocLoopMIParallelCompensate);
                row2.createCell(186).setCellValue(nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone);
                row2.createCell(187).setCellValue(nSubProcessCollapsedEventAdHocLoopMISequentialCompensate);
                row2.createCell(188).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopNoneCompensateNone);
                row2.createCell(189).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopNoneCompensate);
                row2.createCell(190).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopParallelCompensateNone);
                row2.createCell(191).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopParallelCompensate);
                row2.createCell(192).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopMIParallelCompensateNone);
                row2.createCell(193).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopMIParallelCompensate);
                row2.createCell(194).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopMISequentialCompensateNone);
                row2.createCell(195).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopMISequentialCompensate);
                row2.createCell(196).setCellValue(nSubProcessCollapsedEventTransactionLoopNoneCompensateNone);
                row2.createCell(197).setCellValue(nSubProcessCollapsedEventTransactionLoopNoneCompensate);
                row2.createCell(198).setCellValue(nSubProcessCollapsedEventTransactionLoopParallelCompensateNone);
                row2.createCell(199).setCellValue(nSubProcessCollapsedEventTransactionLoopParallelCompensate);
                row2.createCell(200).setCellValue(nSubProcessCollapsedEventTransactionLoopMIParallelCompensateNone);
                row2.createCell(201).setCellValue(nSubProcessCollapsedEventTransactionLoopMIParallelCompensate);
                row2.createCell(202).setCellValue(nSubProcessCollapsedEventTransactionLoopMISequentialCompensateNone);
                row2.createCell(203).setCellValue(nSubProcessCollapsedEventTransactionLoopMISequentialCompensate);
                row2.createCell(204).setCellValue(nDataObject);
                row2.createCell(205).setCellValue(nDataObjectCollection);
                row2.createCell(206).setCellValue(nDataObjectReference);
                row2.createCell(207).setCellValue(nDataStore);
                row2.createCell(208).setCellValue(nDataInput);
                row2.createCell(209).setCellValue(nDataOutput);
                row2.createCell(210).setCellValue(nExclusiveGatewayNoMarker);
                row2.createCell(211).setCellValue(nExclusiveGatewayMarker);
                row2.createCell(212).setCellValue(nParallelGateway);
                row2.createCell(213).setCellValue(nInclusiveGateway);
                row2.createCell(214).setCellValue(nEventBasedGateway);
                row2.createCell(215).setCellValue(nEventBasedGatewayExclusiveInstantiation);
                row2.createCell(216).setCellValue(nEventBasedGatewayParallelInstantiation);
                row2.createCell(217).setCellValue(nComplexGateway);
                row2.createCell(218).setCellValue(nStartMultipleParallelEventDefinition);
                row2.createCell(219).setCellValue(nStartMultipleEventDefinition);
                row2.createCell(220).setCellValue(nStartNoneEventDefinition);
                row2.createCell(221).setCellValue(nStartSignalEventDefinition);
                row2.createCell(222).setCellValue(nStartConditionalEventDefinition);
                row2.createCell(223).setCellValue(nStartTimerEventDefinition);
                row2.createCell(224).setCellValue(nStartMessageEventDefinition);
                row2.createCell(225).setCellValue(nStartCompensateEventDefinition);
                row2.createCell(226).setCellValue(nStartEscalationEventDefinition);
                row2.createCell(227).setCellValue(nStartErrorEventDefinition);
                row2.createCell(228).setCellValue(nStartMessageEventSubProcessInterruptingDefinition);
                row2.createCell(229).setCellValue(nStartTimerEventSubProcessInterruptingDefinition);
                row2.createCell(230).setCellValue(nStartEscalationEventSubProcessInterruptingDefinition);
                row2.createCell(231).setCellValue(nStartConditionalEventSubProcessInterruptingDefinition);
                row2.createCell(232).setCellValue(nStartErrorEventSubProcessInterruptingDefinition);
                row2.createCell(233).setCellValue(nStartCompensateEventSubProcessInterruptingDefinition);
                row2.createCell(234).setCellValue(nStartSignalEventSubProcessInterruptingDefinition);
                row2.createCell(235).setCellValue(nStartMultipleEventSubProcessInterruptingDefinition);
                row2.createCell(236).setCellValue(nStartMultipleParallelEventSubProcessInterruptingDefinition);       
                row2.createCell(237).setCellValue(nStartMessageEventSubProcessNonInterruptingDefinition);
                row2.createCell(238).setCellValue(nStartTimerEventSubProcessNonInterruptingDefinition);
                row2.createCell(239).setCellValue(nStartEscalationEventSubProcessNonInterruptingDefinition);
                row2.createCell(240).setCellValue(nStartConditionalEventSubProcessNonInterruptingDefinition);
                row2.createCell(241).setCellValue(nStartSignalEventSubProcessNonInterruptingDefinition);
                row2.createCell(242).setCellValue(nStartMultipleParallelEventSubProcessNonInterruptingDefinition);
                row2.createCell(243).setCellValue(nStartMultipleEventSubProcessNonInterruptingDefinition);       
                row2.createCell(244).setCellValue(nEndNoneEventDefinition);
                row2.createCell(245).setCellValue(nEndMultipleEventDefinition); 
                row2.createCell(246).setCellValue(nEndEscalationEventDefinition);
                row2.createCell(247).setCellValue(nEndErrorEventDefinition);
                row2.createCell(248).setCellValue(nEndSignalEventDefinition);
                row2.createCell(249).setCellValue(nEndCompensateEventDefinition);
                row2.createCell(250).setCellValue(nEndCancelEventDefinition); 
                row2.createCell(251).setCellValue(nEndMessageEventDefinition);
                row2.createCell(252).setCellValue(nEndTerminateEventDefinition);
                row2.createCell(253).setCellValue(nIntermediateCatchMultipleEventDefinition);
                row2.createCell(254).setCellValue(nIntermediateCatchMultipleParallelEventDefinition);
                row2.createCell(255).setCellValue(nIntermediateCatchMessageEventDefinition);
                row2.createCell(256).setCellValue(nIntermediateCatchTimerEventDefinition);
                row2.createCell(257).setCellValue(nIntermediateCatchConditionalEventDefinition);
                row2.createCell(258).setCellValue(nIntermediateCatchLinkEventDefinition);
                row2.createCell(259).setCellValue(nIntermediateCatchSignalEventDefinition);
                row2.createCell(260).setCellValue(nIntermediateThrowNoneEventDefinition);
                row2.createCell(261).setCellValue(nIntermediateThrowMessageEventDefinition);
                row2.createCell(262).setCellValue(nIntermediateThrowEscalationEventDefinition);
                row2.createCell(263).setCellValue(nIntermediateThrowLinkEventDefinition);
                row2.createCell(264).setCellValue(nIntermediateThrowSignalEventDefinition);
                row2.createCell(265).setCellValue(nIntermediateThrowCompensateEventDefinition);
                row2.createCell(266).setCellValue(nIntermediateThrowMultipleParallelEventDefinition);
                row2.createCell(267).setCellValue(nIntermediateBoundaryMessageEvent);
                row2.createCell(268).setCellValue(nIntermediateBoundaryTimerEvent);
                row2.createCell(269).setCellValue(nIntermediateBoundaryCancelEvent);
                row2.createCell(270).setCellValue(nIntermediateBoundaryConditionalEvent );
                row2.createCell(271).setCellValue(nIntermediateBoundaryEscalationEvent);
                row2.createCell(272).setCellValue(nIntermediateBoundaryErrorEvent);
                row2.createCell(273).setCellValue(nIntermediateBoundarySignalEvent);
                row2.createCell(274).setCellValue(nIntermediateBoundaryCompensateEvent);
                row2.createCell(275).setCellValue(nIntermediateBoundaryMultipleEvent);
                row2.createCell(276).setCellValue(nIntermediateBoundaryMultipleParallelEvent);
                row2.createCell(277).setCellValue(nIntermediateBoundaryTimerEventNonInterrupting);
                row2.createCell(278).setCellValue(nIntermediateBoundaryEscalationEventNonInterrupting);
                row2.createCell(279).setCellValue(nIntermediateBoundaryConditionalEventNonInterrupting);
                row2.createCell(280).setCellValue(nIntermediateBoundaryMessageEventNonInterrupting);
                row2.createCell(281).setCellValue(nIntermediateBoundarySignalEventNonInterrupting);
                row2.createCell(282).setCellValue(nIntermediateBoundaryMultipleEventNonInterrupting);
                row2.createCell(283).setCellValue(nIntermediateBoundaryMultipleParallelEventNonInterrupting);
                row2.createCell(284).setCellValue(nMessageFlow);
                row2.createCell(285).setCellValue(nSequenceFlow);
                row2.createCell(286).setCellValue(nDefaultFlow);
                row2.createCell(287).setCellValue(nConditionalFlow);
                row2.createCell(288).setCellValue(nLane); 
                row2.createCell(289).setCellValue(nPoolCollapsedMultiplicityNone);
                row2.createCell(290).setCellValue(nPoolCollapsedMultiplicity);
                row2.createCell(291).setCellValue(nPoolExpandedMultiplicityNone);
                row2.createCell(292).setCellValue(nPoolExpandedMultiplicity);
                row2.createCell(293).setCellValue(nChoreographyTask);
                row2.createCell(294).setCellValue(nChoreographyMessage);            
                row2.createCell(295).setCellValue(nChoreographyTaskSequentialMultipleInstance);
                row2.createCell(296).setCellValue(nChoreographyTaskParallelMultipleInstance);
                row2.createCell(297).setCellValue(nChoreographyTaskLoop);
                row2.createCell(298).setCellValue(nChoreographySubprocessCollapsed);
                row2.createCell(299).setCellValue(nChoreographySubprocessCollapsedParallelMultipleInstance);
                row2.createCell(300).setCellValue(nChoreographySubprocessCollapsedSequentialMultipleInstance);
                row2.createCell(301).setCellValue(nChoreographySubprocessCollapsedLoop);
                row2.createCell(302).setCellValue(nChoreographySubprocessCollapsedCall);
                row2.createCell(303).setCellValue(nChoreographySubprocessCollapsedCallSequentialMultipleInstance);
                row2.createCell(304).setCellValue(nChoreographySubprocessCollapsedCallParallelMultipleInstance);
                row2.createCell(305).setCellValue(nChoreographySubprocessCollapsedCallLoop);
                row2.createCell(306).setCellValue(nChoreographySubprocessExpanded);
                row2.createCell(307).setCellValue(nChoreographySubprocessExpandedSequentialMultipleInstance);
                row2.createCell(308).setCellValue(nChoreographySubprocessExpandedParallelMultipleInstance);
                row2.createCell(309).setCellValue(nChoreographySubprocessExpandedLoop);
                row2.createCell(310).setCellValue(nChoreographyParticipant);
                row2.createCell(311).setCellValue(nChoreographyParticipantMultiple);       
                row2.createCell(312).setCellValue(nConversationNone);
                row2.createCell(313).setCellValue(nConversationSubProcess);
                row2.createCell(314).setCellValue(nConversationCall);
                row2.createCell(315).setCellValue(nConversationSubProcessCall);
                row2.createCell(316).setCellValue(nConversationLink);
                row2.createCell(317).setCellValue(nAssociationCompensate);
                row2.createCell(318).setCellValue(nAssociationUndirected);
                row2.createCell(319).setCellValue(nAssociationUnidirectional);        
                row2.createCell(320).setCellValue(nAssociationBidirectional);
                row2.createCell(321).setCellValue(nAssociationDataOutput);
                row2.createCell(322).setCellValue(nAssociationDataInput);            
                row2.createCell(323).setCellValue(nGroup);
                row2.createCell(324).setCellValue(nTextAnnotation);
                row2.createCell(325).setCellValue(nOfExtensionElements);
                row2.createCell(326).setCellValue(TotalElements);           
                
                for(Cell cell : row2) {
                	String data="";
                	
    	            	if(cell.getCellType()==CellType.NUMERIC) {
    	            	    data = String.valueOf(cell.getNumericCellValue());
    	            	double str1 = Double.parseDouble(data);
    	            	
    	            	if(str1 >= 1 && str1 <= 5){
    	            		cell.setCellStyle(styleLOW);       
    	                }
    	            	
    	            	if(str1 > 5 && str1 <= 10){
    	            		cell.setCellStyle(styleMEDIUM);       
    	                }
    	            	
    	            	if(str1 > 10){
    	            		cell.setCellStyle(styleHIGH);       
    	                }
                	}
                	
                }
            }
            
      		FileOutputStream fileOut = new FileOutputStream("bpmn_stats.xlsx");
       		wb.write(fileOut);  
       		//closing the Stream  
       		fileOut.close();  
       		System.out.println(fileName+": Analysis DONE");
        	}
        
      //closing the workbook  
   		wb.close(); 
        }
    }
