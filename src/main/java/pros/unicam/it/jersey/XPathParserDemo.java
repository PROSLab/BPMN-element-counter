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

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

public class XPathParserDemo {
	
	private boolean ConsiderExtendedSubProcess= true;

    public static void main(String[] args) throws Exception {
    	
    	//TODO METRICS
    	/*
    	 * 
    	 */
    	
        //Creation of the xls empty file
        Workbook wb = new HSSFWorkbook();    
        HSSFSheet sheet = (HSSFSheet) wb.createSheet("BPMN_Stats"); 
        HSSFRow rowhead = sheet.createRow((short)0);         
	    //creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method  
		rowhead.createCell(0).setCellValue("File Name");
        rowhead.createCell(1).setCellValue("bpmnModeler");
        rowhead.createCell(2).setCellValue("modelType");
		rowhead.createCell(3).setCellValue("isEnglish");
		rowhead.createCell(4).setCellValue("nTaskNoneLoopNoneCompensateNoneCallNone");
        rowhead.createCell(5).setCellValue("nTaskNoneLoopNoneCompensateNoneCall");
        rowhead.createCell(6).setCellValue("nTaskNoneLoopNoneCompensateCallNone");
        rowhead.createCell(7).setCellValue("nTaskNoneLoopNoneCompensateCall");
        rowhead.createCell(8).setCellValue("nTaskNoneLoopStandardCompensateNoneCallNone");
        rowhead.createCell(9).setCellValue("nTaskNoneLoopStandardCompensateNoneCall");
        rowhead.createCell(10).setCellValue("nTaskNoneLoopStandardCompensateCallNone");
        rowhead.createCell(11).setCellValue("nTaskNoneLoopStandardCompensateCall");
        rowhead.createCell(12).setCellValue("nTaskNoneLoopMIParallelCompensateNoneCallNone");
        rowhead.createCell(13).setCellValue("nTaskNoneLoopMIParallelCompensateNoneCall");
        rowhead.createCell(14).setCellValue("nTaskNoneLoopMIParallelCompensateCallNone");
        rowhead.createCell(15).setCellValue("nTaskNoneLoopMIParallelCompensateCall");
        rowhead.createCell(16).setCellValue("nTaskNoneLoopMISequentialCompensateNoneCallNone");
        rowhead.createCell(17).setCellValue("nTaskNoneLoopMISequentialCompensateNoneCall");
        rowhead.createCell(18).setCellValue("nTaskNoneLoopMISequentialCompensateCallNone");
        rowhead.createCell(19).setCellValue("nTaskNoneLoopMISequentialCompensateCall");
		rowhead.createCell(20).setCellValue("nTaskSendLoopNoneCompensateNoneCallNone");
        rowhead.createCell(21).setCellValue("nTaskSendLoopNoneCompensateNoneCall");
        rowhead.createCell(22).setCellValue("nTaskSendLoopNoneCompensateCallNone");
        rowhead.createCell(23).setCellValue("nTaskSendLoopNoneCompensateCall");
        rowhead.createCell(24).setCellValue("nTaskSendLoopStandardCompensateNoneCallNone");
        rowhead.createCell(25).setCellValue("nTaskSendLoopStandardCompensateNoneCall");
        rowhead.createCell(26).setCellValue("nTaskSendLoopStandardCompensateCallNone");
        rowhead.createCell(27).setCellValue("nTaskSendLoopStandardCompensateCall");
        rowhead.createCell(28).setCellValue("nTaskSendLoopMIParallelCompensateNoneCallNone");
        rowhead.createCell(29).setCellValue("nTaskSendLoopMIParallelCompensateNoneCall");
        rowhead.createCell(30).setCellValue("nTaskSendLoopMIParallelCompensateCallNone");
        rowhead.createCell(31).setCellValue("nTaskSendLoopMIParallelCompensateCall");
        rowhead.createCell(32).setCellValue("nTaskSendLoopMISequentialCompensateNoneCallNone");
        rowhead.createCell(33).setCellValue("nTaskSendLoopMISequentialCompensateNoneCall");
        rowhead.createCell(34).setCellValue("nTaskSendLoopMISequentialCompensateCallNone");
        rowhead.createCell(35).setCellValue("nTaskSendLoopMISequentialCompensateCall");
        rowhead.createCell(36).setCellValue("nTaskReceiveLoopNoneCompensateNoneCallNone");
        rowhead.createCell(37).setCellValue("nTaskReceiveLoopNoneCompensateNoneCall");
        rowhead.createCell(38).setCellValue("nTaskReceiveLoopNoneCompensateCallNone");
        rowhead.createCell(39).setCellValue("nTaskReceiveLoopNoneCompensateCall");
        rowhead.createCell(40).setCellValue("nTaskReceiveLoopStandardCompensateNoneCallNone");
        rowhead.createCell(41).setCellValue("nTaskReceiveLoopStandardCompensateNoneCall");
        rowhead.createCell(42).setCellValue("nTaskReceiveLoopStandardCompensateCallNone");
        rowhead.createCell(43).setCellValue("nTaskReceiveLoopStandardCompensateCall");
        rowhead.createCell(44).setCellValue("nTaskReceiveLoopMIParallelCompensateNoneCallNone");
        rowhead.createCell(45).setCellValue("nTaskReceiveLoopMIParallelCompensateNoneCall");
        rowhead.createCell(46).setCellValue("nTaskReceiveLoopMIParallelCompensateCallNone");
        rowhead.createCell(47).setCellValue("nTaskReceiveLoopMIParallelCompensateCall");
        rowhead.createCell(48).setCellValue("nTaskReceiveLoopMISequentialCompensateNoneCallNone");
        rowhead.createCell(49).setCellValue("nTaskReceiveLoopMISequentialCompensateNoneCall");
        rowhead.createCell(50).setCellValue("nTaskReceiveLoopMISequentialCompensateCallNone");
        rowhead.createCell(51).setCellValue("nTaskReceiveLoopMISequentialCompensateCall");
        rowhead.createCell(52).setCellValue("nTaskUserLoopNoneCompensateNoneCallNone");
        rowhead.createCell(53).setCellValue("nTaskUserLoopNoneCompensateNoneCall");
        rowhead.createCell(54).setCellValue("nTaskUserLoopNoneCompensateCallNone");
        rowhead.createCell(55).setCellValue("nTaskUserLoopNoneCompensateCall");
        rowhead.createCell(56).setCellValue("nTaskUserLoopStandardCompensateNoneCallNone");
        rowhead.createCell(57).setCellValue("nTaskUserLoopStandardCompensateNoneCall");
        rowhead.createCell(58).setCellValue("nTaskUserLoopStandardCompensateCallNone");
        rowhead.createCell(59).setCellValue("nTaskUserLoopStandardCompensateCall");
        rowhead.createCell(60).setCellValue("nTaskUserLoopMIParallelCompensateNoneCallNone");
        rowhead.createCell(61).setCellValue("nTaskUserLoopMIParallelCompensateNoneCall");
        rowhead.createCell(62).setCellValue("nTaskUserLoopMIParallelCompensateCallNone");
        rowhead.createCell(63).setCellValue("nTaskUserLoopMIParallelCompensateCall");
        rowhead.createCell(64).setCellValue("nTaskUserLoopMISequentialCompensateNoneCallNone");
        rowhead.createCell(65).setCellValue("nTaskUserLoopMISequentialCompensateNoneCall");
        rowhead.createCell(66).setCellValue("nTaskUserLoopMISequentialCompensateCallNone");
        rowhead.createCell(67).setCellValue("nTaskUserLoopMISequentialCompensateCall");
        rowhead.createCell(68).setCellValue("nTaskManualLoopNoneCompensateNoneCallNone");
        rowhead.createCell(69).setCellValue("nTaskManualLoopNoneCompensateNoneCall");
        rowhead.createCell(70).setCellValue("nTaskManualLoopNoneCompensateCallNone");
        rowhead.createCell(71).setCellValue("nTaskManualLoopNoneCompensateCall");
        rowhead.createCell(72).setCellValue("nTaskManualLoopStandardCompensateNoneCallNone");
        rowhead.createCell(73).setCellValue("nTaskManualLoopStandardCompensateNoneCall");
        rowhead.createCell(74).setCellValue("nTaskManualLoopStandardCompensateCallNone");
        rowhead.createCell(75).setCellValue("nTaskManualLoopStandardCompensateCall");
        rowhead.createCell(76).setCellValue("nTaskManualLoopMIParallelCompensateNoneCallNone");
        rowhead.createCell(77).setCellValue("nTaskManualLoopMIParallelCompensateNoneCall");
        rowhead.createCell(78).setCellValue("nTaskManualLoopMIParallelCompensateCallNone");
        rowhead.createCell(79).setCellValue("nTaskManualLoopMIParallelCompensateCall");
        rowhead.createCell(80).setCellValue("nTaskManualLoopMISequentialCompensateNoneCallNone");
        rowhead.createCell(81).setCellValue("nTaskManualLoopMISequentialCompensateNoneCall");
        rowhead.createCell(82).setCellValue("nTaskManualLoopMISequentialCompensateCallNone");
        rowhead.createCell(83).setCellValue("nTaskManualLoopMISequentialCompensateCall");
        rowhead.createCell(84).setCellValue("nTaskBusinessRuleLoopNoneCompensateNoneCallNone");
        rowhead.createCell(85).setCellValue("nTaskBusinessRuleLoopNoneCompensateNoneCall");
        rowhead.createCell(86).setCellValue("nTaskBusinessRuleLoopNoneCompensateCallNone");
        rowhead.createCell(87).setCellValue("nTaskBusinessRuleLoopNoneCompensateCall");
        rowhead.createCell(88).setCellValue("nTaskBusinessRuleLoopStandardCompensateNoneCallNone");
        rowhead.createCell(89).setCellValue("nTaskBusinessRuleLoopStandardCompensateNoneCall");
        rowhead.createCell(90).setCellValue("nTaskBusinessRuleLoopStandardCompensateCallNone");
        rowhead.createCell(91).setCellValue("nTaskBusinessRuleLoopStandardCompensateCall");
        rowhead.createCell(92).setCellValue("nTaskBusinessRuleLoopMIParallelCompensateNoneCallNone");
        rowhead.createCell(93).setCellValue("nTaskBusinessRuleLoopMIParallelCompensateNoneCall");
        rowhead.createCell(94).setCellValue("nTaskBusinessRuleLoopMIParallelCompensateCallNone");
        rowhead.createCell(95).setCellValue("nTaskBusinessRuleLoopMIParallelCompensateCall");
        rowhead.createCell(96).setCellValue("nTaskBusinessRuleLoopMISequentialCompensateNoneCallNone");
        rowhead.createCell(97).setCellValue("nTaskBusinessRuleLoopMISequentialCompensateNoneCall");
        rowhead.createCell(98).setCellValue("nTaskBusinessRuleLoopMISequentialCompensateCallNone");
        rowhead.createCell(99).setCellValue("nTaskBusinessRuleLoopMISequentialCompensateCall");
        rowhead.createCell(100).setCellValue("nTaskServiceLoopNoneCompensateNoneCallNone");
        rowhead.createCell(101).setCellValue("nTaskServiceLoopNoneCompensateNoneCall");
        rowhead.createCell(102).setCellValue("nTaskServiceLoopNoneCompensateCallNone");
        rowhead.createCell(103).setCellValue("nTaskServiceLoopNoneCompensateCall");
        rowhead.createCell(104).setCellValue("nTaskServiceLoopStandardCompensateNoneCallNone");
        rowhead.createCell(105).setCellValue("nTaskServiceLoopStandardCompensateNoneCall");
        rowhead.createCell(106).setCellValue("nTaskServiceLoopStandardCompensateCallNone");
        rowhead.createCell(107).setCellValue("nTaskServiceLoopStandardCompensateCall");
        rowhead.createCell(108).setCellValue("nTaskServiceLoopMIParallelCompensateNoneCallNone");
        rowhead.createCell(109).setCellValue("nTaskServiceLoopMIParallelCompensateNoneCall");
        rowhead.createCell(110).setCellValue("nTaskServiceLoopMIParallelCompensateCallNone");
        rowhead.createCell(111).setCellValue("nTaskServiceLoopMIParallelCompensateCall");
        rowhead.createCell(112).setCellValue("nTaskServiceLoopMISequentialCompensateNoneCallNone");
        rowhead.createCell(113).setCellValue("nTaskServiceLoopMISequentialCompensateNoneCall");
        rowhead.createCell(114).setCellValue("nTaskServiceLoopMISequentialCompensateCallNone");
        rowhead.createCell(115).setCellValue("nTaskServiceLoopMISequentialCompensateCall");
        rowhead.createCell(116).setCellValue("nTaskScriptLoopNoneCompensateNoneCallNone");
        rowhead.createCell(117).setCellValue("nTaskScriptLoopNoneCompensateNoneCall");
        rowhead.createCell(118).setCellValue("nTaskScriptLoopNoneCompensateCallNone");
        rowhead.createCell(119).setCellValue("nTaskScriptLoopNoneCompensateCall");
        rowhead.createCell(120).setCellValue("nTaskScriptLoopStandardCompensateNoneCallNone");
        rowhead.createCell(121).setCellValue("nTaskScriptLoopStandardCompensateNoneCall");
        rowhead.createCell(122).setCellValue("nTaskScriptLoopStandardCompensateCallNone");
        rowhead.createCell(123).setCellValue("nTaskScriptLoopStandardCompensateCall");
        rowhead.createCell(124).setCellValue("nTaskScriptLoopMIParallelCompensateNoneCallNone");
        rowhead.createCell(125).setCellValue("nTaskScriptLoopMIParallelCompensateNoneCall");
        rowhead.createCell(126).setCellValue("nTaskScriptLoopMIParallelCompensateCallNone");
        rowhead.createCell(127).setCellValue("nTaskScriptLoopMIParallelCompensateCall");
        rowhead.createCell(128).setCellValue("nTaskScriptLoopMISequentialCompensateNoneCallNone");
        rowhead.createCell(129).setCellValue("nTaskScriptLoopMISequentialCompensateNoneCall");
        rowhead.createCell(130).setCellValue("nTaskScriptLoopMISequentialCompensateCallNone");
        rowhead.createCell(131).setCellValue("nTaskScriptLoopMISequentialCompensateCall");
        rowhead.createCell(132).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensateNone");
        rowhead.createCell(133).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensate");
        rowhead.createCell(134).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensateNone");
        rowhead.createCell(135).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensate");
        rowhead.createCell(136).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensateNone");
        rowhead.createCell(137).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensate");
        rowhead.createCell(138).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensateNone");
        rowhead.createCell(139).setCellValue("nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensate");
        rowhead.createCell(140).setCellValue("nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone");
        rowhead.createCell(141).setCellValue("nSubProcessExtendedEventNoneAdHocLoopNoneCompensate");
        rowhead.createCell(142).setCellValue("nSubProcessExtendedEventNoneAdHocLoopParallelCompensateNone");
        rowhead.createCell(143).setCellValue("nSubProcessExtendedEventNoneAdHocLoopParallelCompensate");
        rowhead.createCell(144).setCellValue("nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone");
        rowhead.createCell(145).setCellValue("nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate");
        rowhead.createCell(146).setCellValue("nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone");
        rowhead.createCell(147).setCellValue("nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate");
        rowhead.createCell(148).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensateNone");
        rowhead.createCell(149).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensate");
        rowhead.createCell(150).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensateNone");
        rowhead.createCell(151).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensate");
        rowhead.createCell(152).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensateNone");
        rowhead.createCell(153).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensate");
        rowhead.createCell(154).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensateNone");
        rowhead.createCell(155).setCellValue("nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensate");
        rowhead.createCell(156).setCellValue("nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone");
        rowhead.createCell(157).setCellValue("nSubProcessExtendedEventNoneTransactionLoopNoneCompensate");
        rowhead.createCell(158).setCellValue("nSubProcessExtendedEventNoneTransactionLoopParallelCompensateNone");
        rowhead.createCell(159).setCellValue("nSubProcessExtendedEventNoneTransactionLoopParallelCompensate");
        rowhead.createCell(160).setCellValue("nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone");
        rowhead.createCell(161).setCellValue("nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate");
        rowhead.createCell(162).setCellValue("nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone");
        rowhead.createCell(163).setCellValue("nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate");
        rowhead.createCell(164).setCellValue("nSubProcessExtendedEventAdHocNoneLoopNoneCompensateNone");
        rowhead.createCell(165).setCellValue("nSubProcessExtendedEventAdHocNoneLoopNoneCompensate");
        rowhead.createCell(166).setCellValue("nSubProcessExtendedEventAdHocNoneLoopParallelCompensateNone");
        rowhead.createCell(167).setCellValue("nSubProcessExtendedEventAdHocNoneLoopParallelCompensate");
        rowhead.createCell(168).setCellValue("nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensateNone");
        rowhead.createCell(169).setCellValue("nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensate");
        rowhead.createCell(170).setCellValue("nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensateNone");
        rowhead.createCell(171).setCellValue("nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensate");
        rowhead.createCell(172).setCellValue("nSubProcessExtendedEventAdHocLoopNoneCompensateNone");
        rowhead.createCell(173).setCellValue("nSubProcessExtendedEventAdHocLoopNoneCompensate");
        rowhead.createCell(174).setCellValue("nSubProcessExtendedEventAdHocLoopParallelCompensateNone");
        rowhead.createCell(175).setCellValue("nSubProcessExtendedEventAdHocLoopParallelCompensate");
        rowhead.createCell(176).setCellValue("nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone");
        rowhead.createCell(177).setCellValue("nSubProcessExtendedEventAdHocLoopMIParallelCompensate");
        rowhead.createCell(178).setCellValue("nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone");
        rowhead.createCell(179).setCellValue("nSubProcessExtendedEventAdHocLoopMISequentialCompensate");
        rowhead.createCell(180).setCellValue("nSubProcessExtendedEventTransactionNoneLoopNoneCompensateNone");
        rowhead.createCell(181).setCellValue("nSubProcessExtendedEventTransactionNoneLoopNoneCompensate");
        rowhead.createCell(182).setCellValue("nSubProcessExtendedEventTransactionNoneLoopParallelCompensateNone");
        rowhead.createCell(183).setCellValue("nSubProcessExtendedEventTransactionNoneLoopParallelCompensate");
        rowhead.createCell(184).setCellValue("nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensateNone");
        rowhead.createCell(185).setCellValue("nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensate");
        rowhead.createCell(186).setCellValue("nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensateNone");
        rowhead.createCell(187).setCellValue("nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensate");
        rowhead.createCell(188).setCellValue("nSubProcessExtendedEventTransactionLoopNoneCompensateNone");
        rowhead.createCell(189).setCellValue("nSubProcessExtendedEventTransactionLoopNoneCompensate");
        rowhead.createCell(190).setCellValue("nSubProcessExtendedEventTransactionLoopParallelCompensateNone");
        rowhead.createCell(191).setCellValue("nSubProcessExtendedEventTransactionLoopParallelCompensate");
        rowhead.createCell(192).setCellValue("nSubProcessExtendedEventTransactionLoopMIParallelCompensateNone");
        rowhead.createCell(193).setCellValue("nSubProcessExtendedEventTransactionLoopMIParallelCompensate");
        rowhead.createCell(194).setCellValue("nSubProcessExtendedEventTransactionLoopMISequentialCompensateNone");
        rowhead.createCell(195).setCellValue("nSubProcessExtendedEventTransactionLoopMISequentialCompensate");
        rowhead.createCell(196).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopNoneCompensateNone");
        rowhead.createCell(197).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopNoneCompensate");
        rowhead.createCell(198).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopParallelCompensateNone");
        rowhead.createCell(199).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopParallelCompensate");
        rowhead.createCell(200).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopMIParallelCompensateNone");
        rowhead.createCell(201).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopMIParallelCompensate");
        rowhead.createCell(202).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopMISequentialCompensateNone");
        rowhead.createCell(203).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneLoopMISequentialCompensate");
        rowhead.createCell(204).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone");
        rowhead.createCell(205).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate");
        rowhead.createCell(206).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopParallelCompensateNone");
        rowhead.createCell(207).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopParallelCompensate");
        rowhead.createCell(208).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone");
        rowhead.createCell(209).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate");
        rowhead.createCell(210).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone");
        rowhead.createCell(211).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate");
        rowhead.createCell(212).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopNoneCompensateNone");
        rowhead.createCell(213).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopNoneCompensate");
        rowhead.createCell(214).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopParallelCompensateNone");
        rowhead.createCell(215).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopParallelCompensate");
        rowhead.createCell(216).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopMIParallelCompensateNone");
        rowhead.createCell(217).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopMIParallelCompensate");
        rowhead.createCell(218).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopMISequentialCompensateNone");
        rowhead.createCell(219).setCellValue("nSubProcessCollapsedEventNoneTransactionNoneLoopMISequentialCompensate");
        rowhead.createCell(220).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone");
        rowhead.createCell(221).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate");
        rowhead.createCell(222).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopParallelCompensateNone");
        rowhead.createCell(223).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopParallelCompensate");
        rowhead.createCell(224).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensateNone");
        rowhead.createCell(225).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensate");
        rowhead.createCell(226).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensateNone");
        rowhead.createCell(227).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensate");
        rowhead.createCell(228).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopNoneCompensateNone");
        rowhead.createCell(229).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopNoneCompensate");
        rowhead.createCell(230).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopParallelCompensateNone");
        rowhead.createCell(231).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopParallelCompensate");
        rowhead.createCell(232).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopMIParallelCompensateNone");
        rowhead.createCell(233).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopMIParallelCompensate");
        rowhead.createCell(234).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopMISequentialCompensateNone");
        rowhead.createCell(235).setCellValue("nSubProcessCollapsedEventAdHocNoneLoopMISequentialCompensate");
        rowhead.createCell(236).setCellValue("nSubProcessCollapsedEventAdHocLoopNoneCompensateNone");
        rowhead.createCell(237).setCellValue("nSubProcessCollapsedEventAdHocLoopNoneCompensate");
        rowhead.createCell(238).setCellValue("nSubProcessCollapsedEventAdHocLoopParallelCompensateNone");
        rowhead.createCell(239).setCellValue("nSubProcessCollapsedEventAdHocLoopParallelCompensate");
        rowhead.createCell(240).setCellValue("nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone");
        rowhead.createCell(241).setCellValue("nSubProcessCollapsedEventAdHocLoopMIParallelCompensate");
        rowhead.createCell(242).setCellValue("nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone");
        rowhead.createCell(243).setCellValue("nSubProcessCollapsedEventAdHocLoopMISequentialCompensate");
        rowhead.createCell(244).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopNoneCompensateNone");
        rowhead.createCell(245).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopNoneCompensate");
        rowhead.createCell(246).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopParallelCompensateNone");
        rowhead.createCell(247).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopParallelCompensate");
        rowhead.createCell(248).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopMIParallelCompensateNone");
        rowhead.createCell(249).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopMIParallelCompensate");
        rowhead.createCell(250).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopMISequentialCompensateNone");
        rowhead.createCell(251).setCellValue("nSubProcessCollapsedEventTransactionNoneLoopMISequentialCompensate");
        rowhead.createCell(252).setCellValue("nSubProcessCollapsedEventTransactionLoopNoneCompensateNone");
        rowhead.createCell(253).setCellValue("nSubProcessCollapsedEventTransactionLoopNoneCompensate");
        rowhead.createCell(254).setCellValue("nSubProcessCollapsedEventTransactionLoopParallelCompensateNone");
        rowhead.createCell(255).setCellValue("nSubProcessCollapsedEventTransactionLoopParallelCompensate");
        rowhead.createCell(256).setCellValue("nSubProcessCollapsedEventTransactionLoopMIParallelCompensateNone");
        rowhead.createCell(257).setCellValue("nSubProcessCollapsedEventTransactionLoopMIParallelCompensate");
        rowhead.createCell(258).setCellValue("nSubProcessCollapsedEventTransactionLoopMISequentialCompensateNone");
        rowhead.createCell(259).setCellValue("nSubProcessCollapsedEventTransactionLoopMISequentialCompensate");
        rowhead.createCell(260).setCellValue("nDataObject");
        rowhead.createCell(261).setCellValue("nDataObjectCollection");
        rowhead.createCell(262).setCellValue("nDataObjectReference");
        rowhead.createCell(263).setCellValue("nDataStore");
        rowhead.createCell(264).setCellValue("nDataInput");
        rowhead.createCell(265).setCellValue("nDataOutput");
        rowhead.createCell(266).setCellValue("nExclusiveGatewayNoMarker");
        rowhead.createCell(267).setCellValue("nExclusiveGatewayMarker");
        rowhead.createCell(268).setCellValue("nParallelGateway");
        rowhead.createCell(269).setCellValue("nInclusiveGateway");
        rowhead.createCell(270).setCellValue("nEventBasedGateway");
        rowhead.createCell(271).setCellValue("nEventBasedGatewayExclusiveInstantiation");
        rowhead.createCell(272).setCellValue("nEventBasedGatewayParallelInstantiation");
        rowhead.createCell(273).setCellValue("nComplexGateway");
        rowhead.createCell(274).setCellValue("nStartMultipleParallelEventDefinition");
        rowhead.createCell(275).setCellValue("nStartMultipleEventDefinition");
        rowhead.createCell(276).setCellValue("nStartNoneEventDefinition");
        rowhead.createCell(277).setCellValue("nStartSignalEventDefinition");
        rowhead.createCell(278).setCellValue("nStartConditionalEventDefinition");
        rowhead.createCell(279).setCellValue("nStartTimerEventDefinition");
        rowhead.createCell(280).setCellValue("nStartMessageEventDefinition");
        rowhead.createCell(281).setCellValue("nStartCompensateEventDefinition");
        rowhead.createCell(282).setCellValue("nStartEscalationEventDefinition");
        rowhead.createCell(283).setCellValue("nStartErrorEventDefinition");
        rowhead.createCell(284).setCellValue("nStartMessageEventSubProcessInterruptingDefinition");
        rowhead.createCell(285).setCellValue("nStartTimerEventSubProcessInterruptingDefinition");
        rowhead.createCell(286).setCellValue("nStartEscalationEventSubProcessInterruptingDefinition");
        rowhead.createCell(287).setCellValue("nStartConditionalEventSubProcessInterruptingDefinition");
        rowhead.createCell(288).setCellValue("nStartErrorEventSubProcessInterruptingDefinition");
        rowhead.createCell(289).setCellValue("nStartCompensateEventSubProcessInterruptingDefinition");
        rowhead.createCell(290).setCellValue("nStartSignalEventSubProcessInterruptingDefinition");
        rowhead.createCell(291).setCellValue("nStartMultipleEventSubProcessInterruptingDefinition");
        rowhead.createCell(292).setCellValue("nStartMultipleParallelEventSubProcessInterruptingDefinition");       
        rowhead.createCell(293).setCellValue("nStartMessageEventSubProcessNonInterruptingDefinition");
        rowhead.createCell(294).setCellValue("nStartTimerEventSubProcessNonInterruptingDefinition");
        rowhead.createCell(295).setCellValue("nStartEscalationEventSubProcessNonInterruptingDefinition");
        rowhead.createCell(296).setCellValue("nStartConditionalEventSubProcessNonInterruptingDefinition");
        rowhead.createCell(297).setCellValue("nStartSignalEventSubProcessNonInterruptingDefinition");
        rowhead.createCell(298).setCellValue("nStartMultipleParallelEventSubProcessNonInterruptingDefinition");
        rowhead.createCell(299).setCellValue("nStartMultipleEventSubProcessNonInterruptingDefinition");       
        rowhead.createCell(300).setCellValue("nEndNoneEventDefinition");
        rowhead.createCell(301).setCellValue("nEndMultipleEventDefinition"); 
        rowhead.createCell(302).setCellValue("nEndEscalationEventDefinition");
        rowhead.createCell(303).setCellValue("nEndErrorEventDefinition");
        rowhead.createCell(304).setCellValue("nEndSignalEventDefinition");
        rowhead.createCell(305).setCellValue("nEndCompensateEventDefinition");
        rowhead.createCell(306).setCellValue("nEndCancelEventDefinition"); 
        rowhead.createCell(307).setCellValue("nEndMessageEventDefinition");
        rowhead.createCell(308).setCellValue("nEndTerminateEventDefinition");
        rowhead.createCell(309).setCellValue("nIntermediateCatchMultipleEventDefinition");
        rowhead.createCell(310).setCellValue("nIntermediateCatchMultipleParallelEventDefinition");
        rowhead.createCell(311).setCellValue("nIntermediateCatchMessageEventDefinition");
        rowhead.createCell(312).setCellValue("nIntermediateCatchTimerEventDefinition");
        rowhead.createCell(313).setCellValue("nIntermediateCatchConditionalEventDefinition");
        rowhead.createCell(314).setCellValue("nIntermediateCatchLinkEventDefinition");
        rowhead.createCell(315).setCellValue("nIntermediateCatchSignalEventDefinition");
        rowhead.createCell(316).setCellValue("nIntermediateThrowNoneEventDefinition");
        rowhead.createCell(317).setCellValue("nIntermediateThrowMessageEventDefinition");
        rowhead.createCell(318).setCellValue("nIntermediateThrowEscalationEventDefinition");
        rowhead.createCell(319).setCellValue("nIntermediateThrowLinkEventDefinition");
        rowhead.createCell(320).setCellValue("nIntermediateThrowSignalEventDefinition");
        rowhead.createCell(321).setCellValue("nIntermediateThrowCompensateEventDefinition");
        rowhead.createCell(322).setCellValue("nIntermediateThrowMultipleParallelEventDefinition");
        rowhead.createCell(323).setCellValue("nIntermediateBoundaryMessageEvent");
        rowhead.createCell(324).setCellValue("nIntermediateBoundaryTimerEvent");
        rowhead.createCell(325).setCellValue("nIntermediateBoundaryCancelEvent");
        rowhead.createCell(326).setCellValue("nIntermediateBoundaryConditionalEvent ");
        rowhead.createCell(327).setCellValue("nIntermediateBoundaryEscalationEvent");
        rowhead.createCell(328).setCellValue("nIntermediateBoundaryErrorEvent");
        rowhead.createCell(329).setCellValue("nIntermediateBoundarySignalEvent");
        rowhead.createCell(330).setCellValue("nIntermediateBoundaryCompensateEvent");
        rowhead.createCell(331).setCellValue("nIntermediateBoundaryMultipleEvent");
        rowhead.createCell(332).setCellValue("nIntermediateBoundaryMultipleParallelEvent");
        rowhead.createCell(333).setCellValue("nIntermediateBoundaryTimerEventNonInterrupting");
        rowhead.createCell(334).setCellValue("nIntermediateBoundaryEscalationEventNonInterrupting");
        rowhead.createCell(335).setCellValue("nIntermediateBoundaryConditionalEventNonInterrupting");
        rowhead.createCell(336).setCellValue("nIntermediateBoundaryMessageEventNonInterrupting");
        rowhead.createCell(337).setCellValue("nIntermediateBoundarySignalEventNonInterrupting");
        rowhead.createCell(338).setCellValue("nIntermediateBoundaryMultipleEventNonInterrupting");
        rowhead.createCell(339).setCellValue("nIntermediateBoundaryMultipleParallelEventNonInterrupting");
        rowhead.createCell(340).setCellValue("nMessageFlow");
        rowhead.createCell(341).setCellValue("nSequenceFlow");
        rowhead.createCell(342).setCellValue("nDefaultFlow");
        rowhead.createCell(343).setCellValue("nConditionalFlow");
        rowhead.createCell(344).setCellValue("nLane");
        rowhead.createCell(345).setCellValue("nPoolExpanded");
        rowhead.createCell(346).setCellValue("nPoolCollapsed");
        rowhead.createCell(347).setCellValue("nPoolExpandedMultipleInstance");
        rowhead.createCell(348).setCellValue("nPoolCollapsedMultipleInstance");
        rowhead.createCell(349).setCellValue("nVerticalLane");
        rowhead.createCell(350).setCellValue("nVerticalPool");
        rowhead.createCell(351).setCellValue("nChoreographyTask");
        rowhead.createCell(352).setCellValue("nChoreographyTaskMultipleInstance");
        rowhead.createCell(353).setCellValue("nChoreographyTaskParallelInstance");
        rowhead.createCell(354).setCellValue("nChoreographyTaskLoop");
        rowhead.createCell(355).setCellValue("nChoreographySubprocessCollapsed");
        rowhead.createCell(356).setCellValue("nChoreographySubprocessCollapsedMultipleInstance");
        rowhead.createCell(357).setCellValue("nChoreographySubprocessCollapsedParallelInstance");
        rowhead.createCell(358).setCellValue("nChoreographySubprocessCollapsedLoop");
        rowhead.createCell(359).setCellValue("nChoreographySubprocessCollapsedCall");
        rowhead.createCell(360).setCellValue("nChoreographySubprocessCollapsedCallMultipleInstance");
        rowhead.createCell(361).setCellValue("nChoreographySubprocessCollapsedCallParallelInstance");
        rowhead.createCell(362).setCellValue("nChoreographySubprocessCollapsedCallLoop");
        rowhead.createCell(363).setCellValue("nChoreographySubprocessExpanded");
        rowhead.createCell(364).setCellValue("nChoreographySubprocessExpandedMultipleInstance");
        rowhead.createCell(365).setCellValue("nChoreographySubprocessExpandedParallelInstance");
        rowhead.createCell(366).setCellValue("nChoreographySubprocessExpandedLoop");
        rowhead.createCell(367).setCellValue("nChoreographyParticipant");
        rowhead.createCell(368).setCellValue("nChoreographyParticipantMultiple");       
        rowhead.createCell(369).setCellValue("nConversationNone");
        rowhead.createCell(370).setCellValue("nConversationSubProcess");
        rowhead.createCell(371).setCellValue("nConversationCall");
        rowhead.createCell(372).setCellValue("nConversationSubProcessCall");
        rowhead.createCell(373).setCellValue("nConversationLink");
        rowhead.createCell(374).setCellValue("nAssociationCompensate");
        rowhead.createCell(375).setCellValue("nAssociationUndirected");
        rowhead.createCell(376).setCellValue("nAssociationUnidirectional");        
        rowhead.createCell(377).setCellValue("nAssociationBidirectional");
        rowhead.createCell(378).setCellValue("nAssociationDataOutput");
        rowhead.createCell(379).setCellValue("nAssociationDataInput");
        rowhead.createCell(380).setCellValue("nCondition");
        rowhead.createCell(381).setCellValue("nGroup");
        rowhead.createCell(382).setCellValue("nTextAnnotation");
        rowhead.createCell(383).setCellValue("nOfExtensionElements");
        rowhead.createCell(384).setCellValue("TotalElements");        
        
        
//      if(ConsiderExtendedSubProcess) {
//    	creare tab per gestione elementi extended subprocess
        /*
         *    
        HSSFSheet sheet2 = (HSSFSheet) wb.createSheet("BPMN_Stats_ExtendedSubProcess"); 
        HSSFRow rowhead = sheet2.createRow((short)0); 
        rowhead.createCell(0).setCellValue("File Name");  
        rowhead.createCell(1).setCellValue("BPMN Modeler"); 
        ...TODO
        */
//    }
        
        // File's cycle of the testmodels folder
        File folder = new File("testmodels");
        File[] listOfFiles = folder.listFiles();
        
        for (int x = 0; x < listOfFiles.length; x++) {
        	
    	//Defining global variables
    	String namespace = "bpmn:";
    	String fileName;
        String bpmnModeler;
        boolean isEnglish=false;
        //Process Subprocess or Collaboration
        String modelType = null;
        
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
        
        int nTaskSendLoopNoneCompensateNoneCallNone=0;
        int nTaskSendLoopNoneCompensateNoneCall=0;
        int nTaskSendLoopNoneCompensateCallNone=0;
        int nTaskSendLoopNoneCompensateCall=0;
        int nTaskSendLoopStandardCompensateNoneCallNone=0;
        int nTaskSendLoopStandardCompensateNoneCall=0;
        int nTaskSendLoopStandardCompensateCallNone=0;
        int nTaskSendLoopStandardCompensateCall=0;
        int nTaskSendLoopMIParallelCompensateNoneCallNone=0;
        int nTaskSendLoopMIParallelCompensateNoneCall=0;
        int nTaskSendLoopMIParallelCompensateCallNone=0;
        int nTaskSendLoopMIParallelCompensateCall=0;
        int nTaskSendLoopMISequentialCompensateNoneCallNone=0;
        int nTaskSendLoopMISequentialCompensateNoneCall=0;
        int nTaskSendLoopMISequentialCompensateCallNone=0;
        int nTaskSendLoopMISequentialCompensateCall=0;
        int nTaskReceiveLoopNoneCompensateNoneCallNone=0;
        int nTaskReceiveLoopNoneCompensateNoneCall=0;
        int nTaskReceiveLoopNoneCompensateCallNone=0;
        int nTaskReceiveLoopNoneCompensateCall=0;
        int nTaskReceiveLoopStandardCompensateNoneCallNone=0;
        int nTaskReceiveLoopStandardCompensateNoneCall=0;
        int nTaskReceiveLoopStandardCompensateCallNone=0;
        int nTaskReceiveLoopStandardCompensateCall=0;
        int nTaskReceiveLoopMIParallelCompensateNoneCallNone=0;
        int nTaskReceiveLoopMIParallelCompensateNoneCall=0;
        int nTaskReceiveLoopMIParallelCompensateCallNone=0;
        int nTaskReceiveLoopMIParallelCompensateCall=0;
        int nTaskReceiveLoopMISequentialCompensateNoneCallNone=0;
        int nTaskReceiveLoopMISequentialCompensateNoneCall=0;
        int nTaskReceiveLoopMISequentialCompensateCallNone=0;
        int nTaskReceiveLoopMISequentialCompensateCall=0;
        int nTaskUserLoopNoneCompensateNoneCallNone=0;
        int nTaskUserLoopNoneCompensateNoneCall=0;
        int nTaskUserLoopNoneCompensateCallNone=0;
        int nTaskUserLoopNoneCompensateCall=0;
        int nTaskUserLoopStandardCompensateNoneCallNone=0;
        int nTaskUserLoopStandardCompensateNoneCall=0;
        int nTaskUserLoopStandardCompensateCallNone=0;
        int nTaskUserLoopStandardCompensateCall=0;
        int nTaskUserLoopMIParallelCompensateNoneCallNone=0;
        int nTaskUserLoopMIParallelCompensateNoneCall=0;
        int nTaskUserLoopMIParallelCompensateCallNone=0;
        int nTaskUserLoopMIParallelCompensateCall=0;
        int nTaskUserLoopMISequentialCompensateNoneCallNone=0;
        int nTaskUserLoopMISequentialCompensateNoneCall=0;
        int nTaskUserLoopMISequentialCompensateCallNone=0;
        int nTaskUserLoopMISequentialCompensateCall=0;
        int nTaskManualLoopNoneCompensateNoneCallNone=0;
        int nTaskManualLoopNoneCompensateNoneCall=0;
        int nTaskManualLoopNoneCompensateCallNone=0;
        int nTaskManualLoopNoneCompensateCall=0;
        int nTaskManualLoopStandardCompensateNoneCallNone=0;
        int nTaskManualLoopStandardCompensateNoneCall=0;
        int nTaskManualLoopStandardCompensateCallNone=0;
        int nTaskManualLoopStandardCompensateCall=0;
        int nTaskManualLoopMIParallelCompensateNoneCallNone=0;
        int nTaskManualLoopMIParallelCompensateNoneCall=0;
        int nTaskManualLoopMIParallelCompensateCallNone=0;
        int nTaskManualLoopMIParallelCompensateCall=0;
        int nTaskManualLoopMISequentialCompensateNoneCallNone=0;
        int nTaskManualLoopMISequentialCompensateNoneCall=0;
        int nTaskManualLoopMISequentialCompensateCallNone=0;
        int nTaskManualLoopMISequentialCompensateCall=0;
        int nTaskBusinessRuleLoopNoneCompensateNoneCallNone=0;
        int nTaskBusinessRuleLoopNoneCompensateNoneCall=0;
        int nTaskBusinessRuleLoopNoneCompensateCallNone=0;
        int nTaskBusinessRuleLoopNoneCompensateCall=0;
        int nTaskBusinessRuleLoopStandardCompensateNoneCallNone=0;
        int nTaskBusinessRuleLoopStandardCompensateNoneCall=0;
        int nTaskBusinessRuleLoopStandardCompensateCallNone=0;
        int nTaskBusinessRuleLoopStandardCompensateCall=0;
        int nTaskBusinessRuleLoopMIParallelCompensateNoneCallNone=0;
        int nTaskBusinessRuleLoopMIParallelCompensateNoneCall=0;
        int nTaskBusinessRuleLoopMIParallelCompensateCallNone=0;
        int nTaskBusinessRuleLoopMIParallelCompensateCall=0;
        int nTaskBusinessRuleLoopMISequentialCompensateNoneCallNone=0;
        int nTaskBusinessRuleLoopMISequentialCompensateNoneCall=0;
        int nTaskBusinessRuleLoopMISequentialCompensateCallNone=0;
        int nTaskBusinessRuleLoopMISequentialCompensateCall=0;
        int nTaskServiceLoopNoneCompensateNoneCallNone=0;
        int nTaskServiceLoopNoneCompensateNoneCall=0;
        int nTaskServiceLoopNoneCompensateCallNone=0;
        int nTaskServiceLoopNoneCompensateCall=0;
        int nTaskServiceLoopStandardCompensateNoneCallNone=0;
        int nTaskServiceLoopStandardCompensateNoneCall=0;
        int nTaskServiceLoopStandardCompensateCallNone=0;
        int nTaskServiceLoopStandardCompensateCall=0;
        int nTaskServiceLoopMIParallelCompensateNoneCallNone=0;
        int nTaskServiceLoopMIParallelCompensateNoneCall=0;
        int nTaskServiceLoopMIParallelCompensateCallNone=0;
        int nTaskServiceLoopMIParallelCompensateCall=0;
        int nTaskServiceLoopMISequentialCompensateNoneCallNone=0;
        int nTaskServiceLoopMISequentialCompensateNoneCall=0;
        int nTaskServiceLoopMISequentialCompensateCallNone=0;
        int nTaskServiceLoopMISequentialCompensateCall=0;
        int nTaskScriptLoopNoneCompensateNoneCallNone=0;
        int nTaskScriptLoopNoneCompensateNoneCall=0;
        int nTaskScriptLoopNoneCompensateCallNone=0;
        int nTaskScriptLoopNoneCompensateCall=0;
        int nTaskScriptLoopStandardCompensateNoneCallNone=0;
        int nTaskScriptLoopStandardCompensateNoneCall=0;
        int nTaskScriptLoopStandardCompensateCallNone=0;
        int nTaskScriptLoopStandardCompensateCall=0;
        int nTaskScriptLoopMIParallelCompensateNoneCallNone=0;
        int nTaskScriptLoopMIParallelCompensateNoneCall=0;
        int nTaskScriptLoopMIParallelCompensateCallNone=0;
        int nTaskScriptLoopMIParallelCompensateCall=0;
        int nTaskScriptLoopMISequentialCompensateNoneCallNone=0;
        int nTaskScriptLoopMISequentialCompensateNoneCall=0;
        int nTaskScriptLoopMISequentialCompensateCallNone=0;
        int nTaskScriptLoopMISequentialCompensateCall=0;
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
        int nPoolExpanded=0;
        int nPoolCollapsed=0;
        int nPoolExpandedMultipleInstance=0;
        int nPoolCollapsedMultipleInstance=0;
        int nVerticalLane=0;
        int nVerticalPool=0;
        //Choreography
        int nChoreographyTask=0;
        int nChoreographyTaskMultipleInstance=0;
        int nChoreographyTaskParallelInstance=0;
        int nChoreographyTaskLoop=0;
        int nChoreographySubprocessCollapsed=0;
        int nChoreographySubprocessCollapsedMultipleInstance=0;
        int nChoreographySubprocessCollapsedParallelInstance=0;
        int nChoreographySubprocessCollapsedLoop=0;
        int nChoreographySubprocessCollapsedCall=0;
        int nChoreographySubprocessCollapsedCallMultipleInstance=0;
        int nChoreographySubprocessCollapsedCallParallelInstance=0;
        int nChoreographySubprocessCollapsedCallLoop=0;
        int nChoreographySubprocessExpanded=0;
        int nChoreographySubprocessExpandedMultipleInstance=0;
        int nChoreographySubprocessExpandedParallelInstance=0;
        int nChoreographySubprocessExpandedLoop=0;
        int nChoreographyParticipant=0;
        int nChoreographyParticipantMultiple=0;       
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
        int nCondition=0;
        int nGroup=0;
        int nTextAnnotation=0;
        int nOfExtensionElements=0;
        int TotalElements=0;
   
        //Set BPMN models name
        fileName= listOfFiles[x].getName();
          
    	//Read bpmn models
    	File xmlFile = new File("C:/Users/User/Desktop/BPMN-element-counter/testmodels/"+fileName);
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
                return null;
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
        else {
        	bpmnModeler = "Undefined";
        }
 
        // Check if the model is a Collaboration, a Process or contain a Subprocess
        
        // Check if is a collaboration
        XPathExpression exprModelTypeCol = xpath.compile("//bpmn:definitions");
        Object resultModelType = exprModelTypeCol.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesModelType = (NodeList) resultModelType;
        
        for(int i=0; i<nodesModelType.getLength(); i++) {
        	
        	NodeList nodeModelType = nodesModelType.item(i).getChildNodes();
        	
        	for(int j=0; j<nodeModelType.getLength(); j++) {	          	 
        	
		        if(nodeModelType.item(j).getNodeName().toString() == "bpmn:collaboration") {
		        	
		        	modelType = "Collaboration";
		        	//If i find the collaboration xml tag, i cant skip the for
		        	break;
		        	//System.out.println(modelType);
		        }
		        else {
	        	modelType = "Process";
		        }
	        	//System.out.println(modelType);
		        
		        //TODO Choreography e Conversation
        	}
        }	
        
       

//----------------------------------------------BPMN STATS-------------------------------------------------
// XPath Query for showing all Tasks markers
//  	  nTask
//        nTaskMultipleIstance
//        nTaskMultipleIstanceSequential
//        nTaskLoopActivity
        
        //N° of normal tasks
        XPathExpression exprTask = xpath.compile("//bpmn:task");
        Object result = exprTask.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTask = (NodeList) result;
        doc.getDocumentElement().normalize(); 
          
          for(int i=0; i<nodesTask.getLength() ; i++) {
          	
          	Node TaskNode = nodesTask.item(i);   
          	
          	 
          	if(TaskNode.hasChildNodes()) {                
          		
          		NodeList taskChildNodes = TaskNode.getChildNodes();
          		         		
                  for(int j=0;j<taskChildNodes.getLength(); j++) {
                	 
                	     
	                  	if(taskChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {            
	                  		
	                  		if(taskChildNodes.item(j).getNodeName().contains("standardLoopCharacteristics")) {
	                  			nTaskLoopActivity++;
	                  		}
	                  		
	                  		if(taskChildNodes.item(j).getNodeName().contains("multiInstanceLoopCharacteristics")  && ((Element) taskChildNodes.item(j)).getAttribute("isSequential").contains("true")) {
	                  			nTaskMultipleInstanceSequential++;

	                  		}	
	                  		else if(taskChildNodes.item(j).getNodeName().contains("multiInstanceLoopCharacteristics"))
	                  			nTaskMultipleInstanceParallel++;	                  	
	                  	}
                  }
          		
          	}
          	
          	
          }
          
                
        nTask = nodesTask.getLength() - nTaskLoopActivity - nTaskMultipleInstanceSequential - nTaskMultipleInstanceParallel;

        
        //N° of receive tasks
        XPathExpression exprreceiveTask = xpath.compile("//bpmn:receiveTask");
        Object resultRT = exprreceiveTask.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesReceiveTask = (NodeList) resultRT;
        doc.getDocumentElement().normalize();  
        nReceiveTask = nodesReceiveTask.getLength();
        
        //N° of send tasks
        XPathExpression exprsendTask = xpath.compile("//bpmn:sendTask");
        Object resultST = exprsendTask.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesSendTask = (NodeList) resultST;
        doc.getDocumentElement().normalize();  
        nSendTask = nodesSendTask.getLength();
        
        //N° of user tasks
        XPathExpression expruserTask = xpath.compile("//bpmn:userTask");
        Object resultUT = expruserTask.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesUserTask = (NodeList) resultUT;
        doc.getDocumentElement().normalize();  
        nUserTask = nodesUserTask.getLength();
        
        //N° of manual tasks
        XPathExpression exprmanualTask = xpath.compile("//bpmn:manualTask");
        Object resultMT = exprmanualTask.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesManualTask = (NodeList) resultMT;
        doc.getDocumentElement().normalize();  
        nManualTask = nodesManualTask.getLength();
        
        //N° of businessrule tasks
        XPathExpression exprbusinessRuleTask = xpath.compile("//bpmn:businessRuleTask");
        Object resultBRT = exprbusinessRuleTask.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesBusinessRuleTask = (NodeList) resultBRT;
        doc.getDocumentElement().normalize();  
        nBusinessRuleTask = nodesBusinessRuleTask.getLength();
        
        //N° of service tasks
        XPathExpression exprserviceTask = xpath.compile("//bpmn:serviceTask");
        Object resultSeT = exprserviceTask.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesServiceTask = (NodeList) resultSeT;
        doc.getDocumentElement().normalize();  
        nServiceTask = nodesServiceTask.getLength();
        
        //N° of script tasks
        XPathExpression exprscriptTask = xpath.compile("//bpmn:scriptTask");
        Object resultScT = exprscriptTask.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesScriptTask = (NodeList) resultScT;
        doc.getDocumentElement().normalize();  
        nScriptTask = nodesScriptTask.getLength();
        
        //N° of call activity
        XPathExpression exprcallActivity = xpath.compile("//bpmn:callActivity");
        Object resultCA = exprcallActivity.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesCallActivity = (NodeList) resultCA;
        doc.getDocumentElement().normalize();  
        nCallActivity = nodesCallActivity.getLength();
        
        //SUB PROCESS
        
        // Check if contain a subProcess and the number of subprocess
        XPathExpression exprModelTypeSub = xpath.compile("//bpmn:subProcess");
        Object resultModelTypeSub = exprModelTypeSub.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesModelTypeSub = (NodeList) resultModelTypeSub;      
        for(int i=0; i<nodesModelTypeSub.getLength(); i++) {
        	
    	NodeList nodeModelType = nodesModelTypeSub.item(i).getChildNodes();
    
        	 for(int j=0; j<nodeModelType.getLength(); j++) {	

		        	if(nodeModelType.item(j).getNodeName().toString() == "bpmn:subProcess") {      		
		        		nSubProcessExtended++;
		            	//System.out.println("In the model: "+fileName+" there are: "+nSubProcess+" subProcess/es in the model");
		            }
        	 }
		        
        }    
        
        //N° of SubProcess Loop  
        XPathExpression exprSubPLoop = xpath.compile("//bpmn:subProcess//bpmn:standardLoopCharacteristics");
        Object resultSubPLoop = exprSubPLoop.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesSubPLoop = (NodeList) resultSubPLoop;
        doc.getDocumentElement().normalize();  
        nSubProcessLoop = nodesSubPLoop.getLength();
        
        //N° of SubProcess Sequential Multiple Instance
        XPathExpression exprSubPSMI = xpath.compile("//bpmn:subProcess//bpmn:multiInstanceLoopCharacteristics[@isSequential='true']");
        Object resultSubPSMI = exprSubPSMI.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesSubPSMI = (NodeList) resultSubPSMI;
        doc.getDocumentElement().normalize();  
        nSubProcessSequentialMultipleInstance = nodesSubPSMI.getLength();
        
        //N° of SubProcess Parallel Multiple Instance 
        XPathExpression exprSubPPMI = xpath.compile("//bpmn:subProcess//bpmn:multiInstanceLoopCharacteristics");
        Object resultSubPPMI = exprSubPPMI.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesSubPPMI = (NodeList) resultSubPPMI;
        doc.getDocumentElement().normalize();  
        nSubProcessParallelMultipleInstance = nodesSubPPMI.getLength() - nodesSubPSMI.getLength();
        
        //N° of Event Sub Process
        XPathExpression exprESubP = xpath.compile("//bpmn:subProcess[@triggeredByEvent='true']");
        Object resultESubP = exprESubP.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesESubP = (NodeList) resultESubP;
        doc.getDocumentElement().normalize();  
        nSubProcessEvent = nodesESubP.getLength();
        
        //N° of transaction
        XPathExpression exprTrans = xpath.compile("//bpmn:transaction");
        Object resultTrans = exprTrans.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTrans = (NodeList) resultTrans;
        doc.getDocumentElement().normalize();  
        nTransaction = nodesTrans.getLength();
        
        //N° of adHoc SubProcess 
        XPathExpression expradHoc = xpath.compile("//bpmn:adHocSubProcess");
        Object resultadHoc = expradHoc.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesadHoc = (NodeList) resultadHoc;
        doc.getDocumentElement().normalize();  
        nSubProcessAdHoc = nodesadHoc.getLength();
        
        //N° of Group
        XPathExpression exprGroup = xpath.compile("//bpmn:group");
        Object resultGroup = exprGroup.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesGroup = (NodeList) resultGroup;
        doc.getDocumentElement().normalize();  
        nGroup = nodesGroup.getLength();
        
        //N° of Condition
        XPathExpression exprCond = xpath.compile("//bpmn:condition");
        Object resultCond = exprCond.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesCond = (NodeList) resultCond;
        doc.getDocumentElement().normalize();  
        nCondition = nodesCond.getLength();   
         
//	    DATA OBJECTS------------------------------------------------------------------------------------
//      nDataObject
//      nDataObjectCollection
//      nDataStore
//      nDataObjectReference
//      nDataStoreReference
//		nDataInput
//		nDataOutput
        
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
        XPathExpression exprEBGEI = xpath.compile("//bpmn:eventBasedGateway[@eventGatewayType='Exclusive' && @instantiate='true']");
        Object resultEBGEI = exprEBGEI.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesEBGEI = (NodeList) resultEBGEI;
        doc.getDocumentElement().normalize();  
        nEventBasedGatewayExclusiveInstantiation = nodesEBGEI.getLength();
        
        //TO TEST Event Based Gateway Parallel Instantiation
        XPathExpression exprEBGPI = xpath.compile("//bpmn:eventBasedGateway[@eventGatewayType='Parallel' && @instantiate='true']");
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
	                  				((Element) StartEventSubProcessNonIntChildNodes.item(i)).getAttribute("isInterrupting").contains("false") == true) {
	                  			nStartSignalEventSubProcessNonInterruptingDefinition++;
	                  		}
	                  		
	                  		if(StartEventSubProcessNonIntChildNodes.item(j).getNodeName().contains("conditionalEventDefinition")&&
	                  				((Element) StartEventSubProcessNonIntChildNodes.item(i)).getAttribute("isInterrupting").contains("false") == true) {
	                  			nStartConditionalEventSubProcessNonInterruptingDefinition++;
	                  			}	
	                  		
	                  		if(StartEventSubProcessNonIntChildNodes.item(j).getNodeName().contains("timerEventDefinition")&&
	                  				((Element) StartEventSubProcessNonIntChildNodes.item(i)).getAttribute("isInterrupting").contains("false") == true) {
	                  			nStartTimerEventSubProcessNonInterruptingDefinition++;
	                  		}
	                  		
	                  		if(StartEventSubProcessNonIntChildNodes.item(j).getNodeName().contains("messageEventDefinition")&&
	                  				((Element) StartEventSubProcessNonIntChildNodes.item(i)).getAttribute("isInterrupting").contains("false") == true) {
	                  			nStartMessageEventSubProcessNonInterruptingDefinition++;
	                  			}                  		
	                  		
	                  		if(StartEventSubProcessNonIntChildNodes.item(j).getNodeName().contains("escalationEventDefinition")&&
	                  				((Element) StartEventSubProcessNonIntChildNodes.item(i)).getAttribute("isInterrupting").contains("false") == true) {
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
        
        // CHOREOGRAPHY
        //N° of Choreography partecipant
        XPathExpression exprChoPart = xpath.compile("//bpmn:choreography//bpmn:participant");
        Object resultChoPart = exprChoPart.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesChoPart = (NodeList) resultChoPart;
        doc.getDocumentElement().normalize();  
        nChoreographyParticipant = nodesChoPart.getLength(); 
        
        //N° of Choreography task
        XPathExpression exprChoTask = xpath.compile("//bpmn:choreographyTask");
        Object resultChoTask = exprChoPart.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesChoTask = (NodeList) resultChoTask;
        doc.getDocumentElement().normalize();  
        nChoreographyTask = nodesChoTask.getLength();
        
        //N° of Choreography SubProcess
        XPathExpression exprChoSub = xpath.compile("//bpmn:subChoreography");
        Object resultChoSub = exprChoSub.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesChoSub = (NodeList) resultChoSub;
        doc.getDocumentElement().normalize();  
        nChoreographySubprocess = nodesChoSub.getLength();
        
        //POOL
        //N° of Pool
        XPathExpression exprPool = xpath.compile("//bpmn:collaboration//bpmn:participant[@processRef]");
        Object resultPool= exprPool.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesPool = (NodeList) resultPool;
        doc.getDocumentElement().normalize();  
        nPoolExpanded = nodesPool.getLength(); 
        
        //N° of CollapsedPool
        XPathExpression exprCPool = xpath.compile("//bpmn:collaboration//bpmn:participant");
        Object resultCPool= exprCPool.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesCPool = (NodeList) resultCPool;
        doc.getDocumentElement().normalize();  
        nPoolCollapsed = nodesCPool.getLength() - nodesPool.getLength(); 
        
        //N° of Multiple Instance Pool 
        XPathExpression exprMIPool = xpath.compile("//bpmn:participant//bpmn:participantMultiplicity");
        Object resultMIPool= exprMIPool.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesMIPool = (NodeList) resultMIPool;
        doc.getDocumentElement().normalize();  
        nPoolExpandedMultipleInstance = nodesMIPool.getLength() - nodesPool.getLength();
        
        //N° of Vertical Pool
        XPathExpression exprVPool = xpath.compile("//bpmn:collaboration[@isHorizontal='false']");
        Object resultVPool = exprVPool.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesVPool = (NodeList) resultVPool;
        doc.getDocumentElement().normalize();  
        nVerticalPool = nodesVPool.getLength();
        
        //N° of Lane 
        XPathExpression exprLane = xpath.compile("//bpmn:lane");
        Object resultLane = exprLane.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesLane = (NodeList) resultLane;
        doc.getDocumentElement().normalize();  
        nLane = nodesLane.getLength();
        
        //N° of Vertical Lane
        XPathExpression exprVLane = xpath.compile("//bpmn:lane[@isHorizontal='false']");
        Object resultVLane  = exprVLane.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesVLane  = (NodeList) resultVLane;
        doc.getDocumentElement().normalize();  
        nVerticalLane = nodesVLane.getLength();
        
        
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
        
        XPathExpression exprCConv = xpath.compile("//bpmn:callConversation");
        Object resultCConv = exprCConv.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesCConv = (NodeList) resultCConv;
        doc.getDocumentElement().normalize();  
        nConversationCall = nodesCConv.getLength();
        
        XPathExpression exprConvLink = xpath.compile("//bpmn:conversationLink");
        Object resultConvLink = exprConvLink.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesConvLink = (NodeList) resultConvLink;
        doc.getDocumentElement().normalize();  
        nConversationLink = nodesConvLink.getLength();        
        
        //TODO
        XPathExpression exprConvSBC = xpath.compile("//bpmn:callConversation");
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
        
        TotalElements =1;     //TODO
          		

        
        	//creating the rows 
            HSSFRow row = sheet.createRow((short)x+1);  
            //inserting data   
            
            // Model Info          
        	rowhead.createCell(0).setCellValue(fileName);
            rowhead.createCell(1).setCellValue(bpmnModeler);
            rowhead.createCell(2).setCellValue(modelType);
            rowhead.createCell(3).setCellValue(isEnglish);
            rowhead.createCell(4).setCellValue(nTaskNoneLoopNoneCompensateNoneCallNone);
            rowhead.createCell(5).setCellValue(nTaskNoneLoopNoneCompensateNoneCall);
            rowhead.createCell(6).setCellValue(nTaskNoneLoopNoneCompensateCallNone);
            rowhead.createCell(7).setCellValue(nTaskNoneLoopNoneCompensateCall);
            rowhead.createCell(8).setCellValue(nTaskNoneLoopStandardCompensateNoneCallNone);
            rowhead.createCell(9).setCellValue(nTaskNoneLoopStandardCompensateNoneCall);
            rowhead.createCell(10).setCellValue(nTaskNoneLoopStandardCompensateCallNone);
            rowhead.createCell(11).setCellValue(nTaskNoneLoopStandardCompensateCall);
            rowhead.createCell(12).setCellValue(nTaskNoneLoopMIParallelCompensateNoneCallNone);
            rowhead.createCell(13).setCellValue(nTaskNoneLoopMIParallelCompensateNoneCall);
            rowhead.createCell(14).setCellValue(nTaskNoneLoopMIParallelCompensateCallNone);
            rowhead.createCell(15).setCellValue(nTaskNoneLoopMIParallelCompensateCall);
            rowhead.createCell(16).setCellValue(nTaskNoneLoopMISequentialCompensateNoneCallNone);
            rowhead.createCell(17).setCellValue(nTaskNoneLoopMISequentialCompensateNoneCall);
            rowhead.createCell(18).setCellValue(nTaskNoneLoopMISequentialCompensateCallNone);
            rowhead.createCell(19).setCellValue(nTaskNoneLoopMISequentialCompensateCall);
            rowhead.createCell(20).setCellValue(nTaskSendLoopNoneCompensateNoneCallNone);
            rowhead.createCell(21).setCellValue(nTaskSendLoopNoneCompensateNoneCall);
            rowhead.createCell(22).setCellValue(nTaskSendLoopNoneCompensateCallNone);
            rowhead.createCell(23).setCellValue(nTaskSendLoopNoneCompensateCall);
            rowhead.createCell(24).setCellValue(nTaskSendLoopStandardCompensateNoneCallNone);
            rowhead.createCell(25).setCellValue(nTaskSendLoopStandardCompensateNoneCall);
            rowhead.createCell(26).setCellValue(nTaskSendLoopStandardCompensateCallNone);
            rowhead.createCell(27).setCellValue(nTaskSendLoopStandardCompensateCall);
            rowhead.createCell(28).setCellValue(nTaskSendLoopMIParallelCompensateNoneCallNone);
            rowhead.createCell(29).setCellValue(nTaskSendLoopMIParallelCompensateNoneCall);
            rowhead.createCell(30).setCellValue(nTaskSendLoopMIParallelCompensateCallNone);
            rowhead.createCell(31).setCellValue(nTaskSendLoopMIParallelCompensateCall);
            rowhead.createCell(32).setCellValue(nTaskSendLoopMISequentialCompensateNoneCallNone);
            rowhead.createCell(33).setCellValue(nTaskSendLoopMISequentialCompensateNoneCall);
            rowhead.createCell(34).setCellValue(nTaskSendLoopMISequentialCompensateCallNone);
            rowhead.createCell(35).setCellValue(nTaskSendLoopMISequentialCompensateCall);
            rowhead.createCell(36).setCellValue(nTaskReceiveLoopNoneCompensateNoneCallNone);
            rowhead.createCell(37).setCellValue(nTaskReceiveLoopNoneCompensateNoneCall);
            rowhead.createCell(38).setCellValue(nTaskReceiveLoopNoneCompensateCallNone);
            rowhead.createCell(39).setCellValue(nTaskReceiveLoopNoneCompensateCall);
            rowhead.createCell(40).setCellValue(nTaskReceiveLoopStandardCompensateNoneCallNone);
            rowhead.createCell(41).setCellValue(nTaskReceiveLoopStandardCompensateNoneCall);
            rowhead.createCell(42).setCellValue(nTaskReceiveLoopStandardCompensateCallNone);
            rowhead.createCell(43).setCellValue(nTaskReceiveLoopStandardCompensateCall);
            rowhead.createCell(44).setCellValue(nTaskReceiveLoopMIParallelCompensateNoneCallNone);
            rowhead.createCell(45).setCellValue(nTaskReceiveLoopMIParallelCompensateNoneCall);
            rowhead.createCell(46).setCellValue(nTaskReceiveLoopMIParallelCompensateCallNone);
            rowhead.createCell(47).setCellValue(nTaskReceiveLoopMIParallelCompensateCall);
            rowhead.createCell(48).setCellValue(nTaskReceiveLoopMISequentialCompensateNoneCallNone);
            rowhead.createCell(49).setCellValue(nTaskReceiveLoopMISequentialCompensateNoneCall);
            rowhead.createCell(50).setCellValue(nTaskReceiveLoopMISequentialCompensateCallNone);
            rowhead.createCell(51).setCellValue(nTaskReceiveLoopMISequentialCompensateCall);
            rowhead.createCell(52).setCellValue(nTaskUserLoopNoneCompensateNoneCallNone);
            rowhead.createCell(53).setCellValue(nTaskUserLoopNoneCompensateNoneCall);
            rowhead.createCell(54).setCellValue(nTaskUserLoopNoneCompensateCallNone);
            rowhead.createCell(55).setCellValue(nTaskUserLoopNoneCompensateCall);
            rowhead.createCell(56).setCellValue(nTaskUserLoopStandardCompensateNoneCallNone);
            rowhead.createCell(57).setCellValue(nTaskUserLoopStandardCompensateNoneCall);
            rowhead.createCell(58).setCellValue(nTaskUserLoopStandardCompensateCallNone);
            rowhead.createCell(59).setCellValue(nTaskUserLoopStandardCompensateCall);
            rowhead.createCell(60).setCellValue(nTaskUserLoopMIParallelCompensateNoneCallNone);
            rowhead.createCell(61).setCellValue(nTaskUserLoopMIParallelCompensateNoneCall);
            rowhead.createCell(62).setCellValue(nTaskUserLoopMIParallelCompensateCallNone);
            rowhead.createCell(63).setCellValue(nTaskUserLoopMIParallelCompensateCall);
            rowhead.createCell(64).setCellValue(nTaskUserLoopMISequentialCompensateNoneCallNone);
            rowhead.createCell(65).setCellValue(nTaskUserLoopMISequentialCompensateNoneCall);
            rowhead.createCell(66).setCellValue(nTaskUserLoopMISequentialCompensateCallNone);
            rowhead.createCell(67).setCellValue(nTaskUserLoopMISequentialCompensateCall);
            rowhead.createCell(68).setCellValue(nTaskManualLoopNoneCompensateNoneCallNone);
            rowhead.createCell(69).setCellValue(nTaskManualLoopNoneCompensateNoneCall);
            rowhead.createCell(70).setCellValue(nTaskManualLoopNoneCompensateCallNone);
            rowhead.createCell(71).setCellValue(nTaskManualLoopNoneCompensateCall);
            rowhead.createCell(72).setCellValue(nTaskManualLoopStandardCompensateNoneCallNone);
            rowhead.createCell(73).setCellValue(nTaskManualLoopStandardCompensateNoneCall);
            rowhead.createCell(74).setCellValue(nTaskManualLoopStandardCompensateCallNone);
            rowhead.createCell(75).setCellValue(nTaskManualLoopStandardCompensateCall);
            rowhead.createCell(76).setCellValue(nTaskManualLoopMIParallelCompensateNoneCallNone);
            rowhead.createCell(77).setCellValue(nTaskManualLoopMIParallelCompensateNoneCall);
            rowhead.createCell(78).setCellValue(nTaskManualLoopMIParallelCompensateCallNone);
            rowhead.createCell(79).setCellValue(nTaskManualLoopMIParallelCompensateCall);
            rowhead.createCell(80).setCellValue(nTaskManualLoopMISequentialCompensateNoneCallNone);
            rowhead.createCell(81).setCellValue(nTaskManualLoopMISequentialCompensateNoneCall);
            rowhead.createCell(82).setCellValue(nTaskManualLoopMISequentialCompensateCallNone);
            rowhead.createCell(83).setCellValue(nTaskManualLoopMISequentialCompensateCall);
            rowhead.createCell(84).setCellValue(nTaskBusinessRuleLoopNoneCompensateNoneCallNone);
            rowhead.createCell(85).setCellValue(nTaskBusinessRuleLoopNoneCompensateNoneCall);
            rowhead.createCell(86).setCellValue(nTaskBusinessRuleLoopNoneCompensateCallNone);
            rowhead.createCell(87).setCellValue(nTaskBusinessRuleLoopNoneCompensateCall);
            rowhead.createCell(88).setCellValue(nTaskBusinessRuleLoopStandardCompensateNoneCallNone);
            rowhead.createCell(89).setCellValue(nTaskBusinessRuleLoopStandardCompensateNoneCall);
            rowhead.createCell(90).setCellValue(nTaskBusinessRuleLoopStandardCompensateCallNone);
            rowhead.createCell(91).setCellValue(nTaskBusinessRuleLoopStandardCompensateCall);
            rowhead.createCell(92).setCellValue(nTaskBusinessRuleLoopMIParallelCompensateNoneCallNone);
            rowhead.createCell(93).setCellValue(nTaskBusinessRuleLoopMIParallelCompensateNoneCall);
            rowhead.createCell(94).setCellValue(nTaskBusinessRuleLoopMIParallelCompensateCallNone);
            rowhead.createCell(95).setCellValue(nTaskBusinessRuleLoopMIParallelCompensateCall);
            rowhead.createCell(96).setCellValue(nTaskBusinessRuleLoopMISequentialCompensateNoneCallNone);
            rowhead.createCell(97).setCellValue(nTaskBusinessRuleLoopMISequentialCompensateNoneCall);
            rowhead.createCell(98).setCellValue(nTaskBusinessRuleLoopMISequentialCompensateCallNone);
            rowhead.createCell(99).setCellValue(nTaskBusinessRuleLoopMISequentialCompensateCall);
            rowhead.createCell(100).setCellValue(nTaskServiceLoopNoneCompensateNoneCallNone);
            rowhead.createCell(101).setCellValue(nTaskServiceLoopNoneCompensateNoneCall);
            rowhead.createCell(102).setCellValue(nTaskServiceLoopNoneCompensateCallNone);
            rowhead.createCell(103).setCellValue(nTaskServiceLoopNoneCompensateCall);
            rowhead.createCell(104).setCellValue(nTaskServiceLoopStandardCompensateNoneCallNone);
            rowhead.createCell(105).setCellValue(nTaskServiceLoopStandardCompensateNoneCall);
            rowhead.createCell(106).setCellValue(nTaskServiceLoopStandardCompensateCallNone);
            rowhead.createCell(107).setCellValue(nTaskServiceLoopStandardCompensateCall);
            rowhead.createCell(108).setCellValue(nTaskServiceLoopMIParallelCompensateNoneCallNone);
            rowhead.createCell(109).setCellValue(nTaskServiceLoopMIParallelCompensateNoneCall);
            rowhead.createCell(110).setCellValue(nTaskServiceLoopMIParallelCompensateCallNone);
            rowhead.createCell(111).setCellValue(nTaskServiceLoopMIParallelCompensateCall);
            rowhead.createCell(112).setCellValue(nTaskServiceLoopMISequentialCompensateNoneCallNone);
            rowhead.createCell(113).setCellValue(nTaskServiceLoopMISequentialCompensateNoneCall);
            rowhead.createCell(114).setCellValue(nTaskServiceLoopMISequentialCompensateCallNone);
            rowhead.createCell(115).setCellValue(nTaskServiceLoopMISequentialCompensateCall);
            rowhead.createCell(116).setCellValue(nTaskScriptLoopNoneCompensateNoneCallNone);
            rowhead.createCell(117).setCellValue(nTaskScriptLoopNoneCompensateNoneCall);
            rowhead.createCell(118).setCellValue(nTaskScriptLoopNoneCompensateCallNone);
            rowhead.createCell(119).setCellValue(nTaskScriptLoopNoneCompensateCall);
            rowhead.createCell(120).setCellValue(nTaskScriptLoopStandardCompensateNoneCallNone);
            rowhead.createCell(121).setCellValue(nTaskScriptLoopStandardCompensateNoneCall);
            rowhead.createCell(122).setCellValue(nTaskScriptLoopStandardCompensateCallNone);
            rowhead.createCell(123).setCellValue(nTaskScriptLoopStandardCompensateCall);
            rowhead.createCell(124).setCellValue(nTaskScriptLoopMIParallelCompensateNoneCallNone);
            rowhead.createCell(125).setCellValue(nTaskScriptLoopMIParallelCompensateNoneCall);
            rowhead.createCell(126).setCellValue(nTaskScriptLoopMIParallelCompensateCallNone);
            rowhead.createCell(127).setCellValue(nTaskScriptLoopMIParallelCompensateCall);
            rowhead.createCell(128).setCellValue(nTaskScriptLoopMISequentialCompensateNoneCallNone);
            rowhead.createCell(129).setCellValue(nTaskScriptLoopMISequentialCompensateNoneCall);
            rowhead.createCell(130).setCellValue(nTaskScriptLoopMISequentialCompensateCallNone);
            rowhead.createCell(131).setCellValue(nTaskScriptLoopMISequentialCompensateCall);
            rowhead.createCell(132).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensateNone);
            rowhead.createCell(133).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensate);
            rowhead.createCell(134).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensateNone);
            rowhead.createCell(135).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensate);
            rowhead.createCell(136).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensateNone);
            rowhead.createCell(137).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensate);
            rowhead.createCell(138).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensateNone);
            rowhead.createCell(139).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensate);
            rowhead.createCell(140).setCellValue(nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone);
            rowhead.createCell(141).setCellValue(nSubProcessExtendedEventNoneAdHocLoopNoneCompensate);
            rowhead.createCell(142).setCellValue(nSubProcessExtendedEventNoneAdHocLoopParallelCompensateNone);
            rowhead.createCell(143).setCellValue(nSubProcessExtendedEventNoneAdHocLoopParallelCompensate);
            rowhead.createCell(144).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone);
            rowhead.createCell(145).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate);
            rowhead.createCell(146).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone);
            rowhead.createCell(147).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate);
            rowhead.createCell(148).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensateNone);
            rowhead.createCell(149).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensate);
            rowhead.createCell(150).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensateNone);
            rowhead.createCell(151).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensate);
            rowhead.createCell(152).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensateNone);
            rowhead.createCell(153).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensate);
            rowhead.createCell(154).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensateNone);
            rowhead.createCell(155).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensate);
            rowhead.createCell(156).setCellValue(nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone);
            rowhead.createCell(157).setCellValue(nSubProcessExtendedEventNoneTransactionLoopNoneCompensate);
            rowhead.createCell(158).setCellValue(nSubProcessExtendedEventNoneTransactionLoopParallelCompensateNone);
            rowhead.createCell(159).setCellValue(nSubProcessExtendedEventNoneTransactionLoopParallelCompensate);
            rowhead.createCell(160).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone);
            rowhead.createCell(161).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate);
            rowhead.createCell(162).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone);
            rowhead.createCell(163).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate);
            rowhead.createCell(164).setCellValue(nSubProcessExtendedEventAdHocNoneLoopNoneCompensateNone);
            rowhead.createCell(165).setCellValue(nSubProcessExtendedEventAdHocNoneLoopNoneCompensate);
            rowhead.createCell(166).setCellValue(nSubProcessExtendedEventAdHocNoneLoopParallelCompensateNone);
            rowhead.createCell(167).setCellValue(nSubProcessExtendedEventAdHocNoneLoopParallelCompensate);
            rowhead.createCell(168).setCellValue(nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensateNone);
            rowhead.createCell(169).setCellValue(nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensate);
            rowhead.createCell(170).setCellValue(nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensateNone);
            rowhead.createCell(171).setCellValue(nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensate);
            rowhead.createCell(172).setCellValue(nSubProcessExtendedEventAdHocLoopNoneCompensateNone);
            rowhead.createCell(173).setCellValue(nSubProcessExtendedEventAdHocLoopNoneCompensate);
            rowhead.createCell(174).setCellValue(nSubProcessExtendedEventAdHocLoopParallelCompensateNone);
            rowhead.createCell(175).setCellValue(nSubProcessExtendedEventAdHocLoopParallelCompensate);
            rowhead.createCell(176).setCellValue(nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone);
            rowhead.createCell(177).setCellValue(nSubProcessExtendedEventAdHocLoopMIParallelCompensate);
            rowhead.createCell(178).setCellValue(nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone);
            rowhead.createCell(179).setCellValue(nSubProcessExtendedEventAdHocLoopMISequentialCompensate);
            rowhead.createCell(180).setCellValue(nSubProcessExtendedEventTransactionNoneLoopNoneCompensateNone);
            rowhead.createCell(181).setCellValue(nSubProcessExtendedEventTransactionNoneLoopNoneCompensate);
            rowhead.createCell(182).setCellValue(nSubProcessExtendedEventTransactionNoneLoopParallelCompensateNone);
            rowhead.createCell(183).setCellValue(nSubProcessExtendedEventTransactionNoneLoopParallelCompensate);
            rowhead.createCell(184).setCellValue(nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensateNone);
            rowhead.createCell(185).setCellValue(nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensate);
            rowhead.createCell(186).setCellValue(nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensateNone);
            rowhead.createCell(187).setCellValue(nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensate);
            rowhead.createCell(188).setCellValue(nSubProcessExtendedEventTransactionLoopNoneCompensateNone);
            rowhead.createCell(189).setCellValue(nSubProcessExtendedEventTransactionLoopNoneCompensate);
            rowhead.createCell(190).setCellValue(nSubProcessExtendedEventTransactionLoopParallelCompensateNone);
            rowhead.createCell(191).setCellValue(nSubProcessExtendedEventTransactionLoopParallelCompensate);
            rowhead.createCell(192).setCellValue(nSubProcessExtendedEventTransactionLoopMIParallelCompensateNone);
            rowhead.createCell(193).setCellValue(nSubProcessExtendedEventTransactionLoopMIParallelCompensate);
            rowhead.createCell(194).setCellValue(nSubProcessExtendedEventTransactionLoopMISequentialCompensateNone);
            rowhead.createCell(195).setCellValue(nSubProcessExtendedEventTransactionLoopMISequentialCompensate);
            rowhead.createCell(196).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopNoneCompensateNone);
            rowhead.createCell(197).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopNoneCompensate);
            rowhead.createCell(198).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopParallelCompensateNone);
            rowhead.createCell(199).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopParallelCompensate);
            rowhead.createCell(200).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopMIParallelCompensateNone);
            rowhead.createCell(201).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopMIParallelCompensate);
            rowhead.createCell(202).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopMISequentialCompensateNone);
            rowhead.createCell(203).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopMISequentialCompensate);
            rowhead.createCell(204).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone);
            rowhead.createCell(205).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate);
            rowhead.createCell(206).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopParallelCompensateNone);
            rowhead.createCell(207).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopParallelCompensate);
            rowhead.createCell(208).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone);
            rowhead.createCell(209).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate);
            rowhead.createCell(210).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone);
            rowhead.createCell(211).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate);
            rowhead.createCell(212).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopNoneCompensateNone);
            rowhead.createCell(213).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopNoneCompensate);
            rowhead.createCell(214).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopParallelCompensateNone);
            rowhead.createCell(215).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopParallelCompensate);
            rowhead.createCell(216).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopMIParallelCompensateNone);
            rowhead.createCell(217).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopMIParallelCompensate);
            rowhead.createCell(218).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopMISequentialCompensateNone);
            rowhead.createCell(219).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopMISequentialCompensate);
            rowhead.createCell(220).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone);
            rowhead.createCell(221).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate);
            rowhead.createCell(222).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopParallelCompensateNone);
            rowhead.createCell(223).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopParallelCompensate);
            rowhead.createCell(224).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensateNone);
            rowhead.createCell(225).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensate);
            rowhead.createCell(226).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensateNone);
            rowhead.createCell(227).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensate);
            rowhead.createCell(228).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopNoneCompensateNone);
            rowhead.createCell(229).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopNoneCompensate);
            rowhead.createCell(230).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopParallelCompensateNone);
            rowhead.createCell(231).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopParallelCompensate);
            rowhead.createCell(232).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopMIParallelCompensateNone);
            rowhead.createCell(233).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopMIParallelCompensate);
            rowhead.createCell(234).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopMISequentialCompensateNone);
            rowhead.createCell(235).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopMISequentialCompensate);
            rowhead.createCell(236).setCellValue(nSubProcessCollapsedEventAdHocLoopNoneCompensateNone);
            rowhead.createCell(237).setCellValue(nSubProcessCollapsedEventAdHocLoopNoneCompensate);
            rowhead.createCell(238).setCellValue(nSubProcessCollapsedEventAdHocLoopParallelCompensateNone);
            rowhead.createCell(239).setCellValue(nSubProcessCollapsedEventAdHocLoopParallelCompensate);
            rowhead.createCell(240).setCellValue(nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone);
            rowhead.createCell(241).setCellValue(nSubProcessCollapsedEventAdHocLoopMIParallelCompensate);
            rowhead.createCell(242).setCellValue(nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone);
            rowhead.createCell(243).setCellValue(nSubProcessCollapsedEventAdHocLoopMISequentialCompensate);
            rowhead.createCell(244).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopNoneCompensateNone);
            rowhead.createCell(245).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopNoneCompensate);
            rowhead.createCell(246).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopParallelCompensateNone);
            rowhead.createCell(247).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopParallelCompensate);
            rowhead.createCell(248).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopMIParallelCompensateNone);
            rowhead.createCell(249).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopMIParallelCompensate);
            rowhead.createCell(250).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopMISequentialCompensateNone);
            rowhead.createCell(251).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopMISequentialCompensate);
            rowhead.createCell(252).setCellValue(nSubProcessCollapsedEventTransactionLoopNoneCompensateNone);
            rowhead.createCell(253).setCellValue(nSubProcessCollapsedEventTransactionLoopNoneCompensate);
            rowhead.createCell(254).setCellValue(nSubProcessCollapsedEventTransactionLoopParallelCompensateNone);
            rowhead.createCell(255).setCellValue(nSubProcessCollapsedEventTransactionLoopParallelCompensate);
            rowhead.createCell(256).setCellValue(nSubProcessCollapsedEventTransactionLoopMIParallelCompensateNone);
            rowhead.createCell(257).setCellValue(nSubProcessCollapsedEventTransactionLoopMIParallelCompensate);
            rowhead.createCell(258).setCellValue(nSubProcessCollapsedEventTransactionLoopMISequentialCompensateNone);
            rowhead.createCell(259).setCellValue(nSubProcessCollapsedEventTransactionLoopMISequentialCompensate);
            rowhead.createCell(260).setCellValue(nDataObject);
            rowhead.createCell(261).setCellValue(nDataObjectCollection);
            rowhead.createCell(262).setCellValue(nDataObjectReference);
            rowhead.createCell(263).setCellValue(nDataStore);
            rowhead.createCell(264).setCellValue(nDataInput);
            rowhead.createCell(265).setCellValue(nDataOutput);
            rowhead.createCell(266).setCellValue(nExclusiveGatewayNoMarker);
            rowhead.createCell(267).setCellValue(nExclusiveGatewayMarker);
            rowhead.createCell(268).setCellValue(nParallelGateway);
            rowhead.createCell(269).setCellValue(nInclusiveGateway);
            rowhead.createCell(270).setCellValue(nEventBasedGateway);
            rowhead.createCell(271).setCellValue(nEventBasedGatewayExclusiveInstantiation);
            rowhead.createCell(272).setCellValue(nEventBasedGatewayParallelInstantiation);
            rowhead.createCell(273).setCellValue(nComplexGateway);
            rowhead.createCell(274).setCellValue(nStartMultipleParallelEventDefinition);
            rowhead.createCell(275).setCellValue(nStartMultipleEventDefinition);
            rowhead.createCell(276).setCellValue(nStartNoneEventDefinition);
            rowhead.createCell(277).setCellValue(nStartSignalEventDefinition);
            rowhead.createCell(278).setCellValue(nStartConditionalEventDefinition);
            rowhead.createCell(279).setCellValue(nStartTimerEventDefinition);
            rowhead.createCell(280).setCellValue(nStartMessageEventDefinition);
            rowhead.createCell(281).setCellValue(nStartCompensateEventDefinition);
            rowhead.createCell(282).setCellValue(nStartEscalationEventDefinition);
            rowhead.createCell(283).setCellValue(nStartErrorEventDefinition);
            rowhead.createCell(284).setCellValue(nStartMessageEventSubProcessInterruptingDefinition);
            rowhead.createCell(285).setCellValue(nStartTimerEventSubProcessInterruptingDefinition);
            rowhead.createCell(286).setCellValue(nStartEscalationEventSubProcessInterruptingDefinition);
            rowhead.createCell(287).setCellValue(nStartConditionalEventSubProcessInterruptingDefinition);
            rowhead.createCell(288).setCellValue(nStartErrorEventSubProcessInterruptingDefinition);
            rowhead.createCell(289).setCellValue(nStartCompensateEventSubProcessInterruptingDefinition);
            rowhead.createCell(290).setCellValue(nStartSignalEventSubProcessInterruptingDefinition);
            rowhead.createCell(291).setCellValue(nStartMultipleEventSubProcessInterruptingDefinition);
            rowhead.createCell(292).setCellValue(nStartMultipleParallelEventSubProcessInterruptingDefinition);       
            rowhead.createCell(293).setCellValue(nStartMessageEventSubProcessNonInterruptingDefinition);
            rowhead.createCell(294).setCellValue(nStartTimerEventSubProcessNonInterruptingDefinition);
            rowhead.createCell(295).setCellValue(nStartEscalationEventSubProcessNonInterruptingDefinition);
            rowhead.createCell(296).setCellValue(nStartConditionalEventSubProcessNonInterruptingDefinition);
            rowhead.createCell(297).setCellValue(nStartSignalEventSubProcessNonInterruptingDefinition);
            rowhead.createCell(298).setCellValue(nStartMultipleParallelEventSubProcessNonInterruptingDefinition);
            rowhead.createCell(299).setCellValue(nStartMultipleEventSubProcessNonInterruptingDefinition);       
            rowhead.createCell(300).setCellValue(nEndNoneEventDefinition);
            rowhead.createCell(301).setCellValue(nEndMultipleEventDefinition); 
            rowhead.createCell(302).setCellValue(nEndEscalationEventDefinition);
            rowhead.createCell(303).setCellValue(nEndErrorEventDefinition);
            rowhead.createCell(304).setCellValue(nEndSignalEventDefinition);
            rowhead.createCell(305).setCellValue(nEndCompensateEventDefinition);
            rowhead.createCell(306).setCellValue(nEndCancelEventDefinition); 
            rowhead.createCell(307).setCellValue(nEndMessageEventDefinition);
            rowhead.createCell(308).setCellValue(nEndTerminateEventDefinition);
            rowhead.createCell(309).setCellValue(nIntermediateCatchMultipleEventDefinition);
            rowhead.createCell(310).setCellValue(nIntermediateCatchMultipleParallelEventDefinition);
            rowhead.createCell(311).setCellValue(nIntermediateCatchMessageEventDefinition);
            rowhead.createCell(312).setCellValue(nIntermediateCatchTimerEventDefinition);
            rowhead.createCell(313).setCellValue(nIntermediateCatchConditionalEventDefinition);
            rowhead.createCell(314).setCellValue(nIntermediateCatchLinkEventDefinition);
            rowhead.createCell(315).setCellValue(nIntermediateCatchSignalEventDefinition);
            rowhead.createCell(316).setCellValue(nIntermediateThrowNoneEventDefinition);
            rowhead.createCell(317).setCellValue(nIntermediateThrowMessageEventDefinition);
            rowhead.createCell(318).setCellValue(nIntermediateThrowEscalationEventDefinition);
            rowhead.createCell(319).setCellValue(nIntermediateThrowLinkEventDefinition);
            rowhead.createCell(320).setCellValue(nIntermediateThrowSignalEventDefinition);
            rowhead.createCell(321).setCellValue(nIntermediateThrowCompensateEventDefinition);
            rowhead.createCell(322).setCellValue(nIntermediateThrowMultipleParallelEventDefinition);
            rowhead.createCell(323).setCellValue(nIntermediateBoundaryMessageEvent);
            rowhead.createCell(324).setCellValue(nIntermediateBoundaryTimerEvent);
            rowhead.createCell(325).setCellValue(nIntermediateBoundaryCancelEvent);
            rowhead.createCell(326).setCellValue(nIntermediateBoundaryConditionalEvent );
            rowhead.createCell(327).setCellValue(nIntermediateBoundaryEscalationEvent);
            rowhead.createCell(328).setCellValue(nIntermediateBoundaryErrorEvent);
            rowhead.createCell(329).setCellValue(nIntermediateBoundarySignalEvent);
            rowhead.createCell(330).setCellValue(nIntermediateBoundaryCompensateEvent);
            rowhead.createCell(331).setCellValue(nIntermediateBoundaryMultipleEvent);
            rowhead.createCell(332).setCellValue(nIntermediateBoundaryMultipleParallelEvent);
            rowhead.createCell(333).setCellValue(nIntermediateBoundaryTimerEventNonInterrupting);
            rowhead.createCell(334).setCellValue(nIntermediateBoundaryEscalationEventNonInterrupting);
            rowhead.createCell(335).setCellValue(nIntermediateBoundaryConditionalEventNonInterrupting);
            rowhead.createCell(336).setCellValue(nIntermediateBoundaryMessageEventNonInterrupting);
            rowhead.createCell(337).setCellValue(nIntermediateBoundarySignalEventNonInterrupting);
            rowhead.createCell(338).setCellValue(nIntermediateBoundaryMultipleEventNonInterrupting);
            rowhead.createCell(339).setCellValue(nIntermediateBoundaryMultipleParallelEventNonInterrupting);
            rowhead.createCell(340).setCellValue(nMessageFlow);
            rowhead.createCell(341).setCellValue(nSequenceFlow);
            rowhead.createCell(342).setCellValue(nDefaultFlow);
            rowhead.createCell(343).setCellValue(nConditionalFlow);
            rowhead.createCell(344).setCellValue(nLane);
            rowhead.createCell(345).setCellValue(nPoolExpanded);
            rowhead.createCell(346).setCellValue(nPoolCollapsed);
            rowhead.createCell(347).setCellValue(nPoolExpandedMultipleInstance);
            rowhead.createCell(348).setCellValue(nPoolCollapsedMultipleInstance);
            rowhead.createCell(349).setCellValue(nVerticalLane);
            rowhead.createCell(350).setCellValue(nVerticalPool);
            rowhead.createCell(351).setCellValue(nChoreographyTask);
            rowhead.createCell(352).setCellValue(nChoreographyTaskMultipleInstance);
            rowhead.createCell(353).setCellValue(nChoreographyTaskParallelInstance);
            rowhead.createCell(354).setCellValue(nChoreographyTaskLoop);
            rowhead.createCell(355).setCellValue(nChoreographySubprocessCollapsed);
            rowhead.createCell(356).setCellValue(nChoreographySubprocessCollapsedMultipleInstance);
            rowhead.createCell(357).setCellValue(nChoreographySubprocessCollapsedParallelInstance);
            rowhead.createCell(358).setCellValue(nChoreographySubprocessCollapsedLoop);
            rowhead.createCell(359).setCellValue(nChoreographySubprocessCollapsedCall);
            rowhead.createCell(360).setCellValue(nChoreographySubprocessCollapsedCallMultipleInstance);
            rowhead.createCell(361).setCellValue(nChoreographySubprocessCollapsedCallParallelInstance);
            rowhead.createCell(362).setCellValue(nChoreographySubprocessCollapsedCallLoop);
            rowhead.createCell(363).setCellValue(nChoreographySubprocessExpanded);
            rowhead.createCell(364).setCellValue(nChoreographySubprocessExpandedMultipleInstance);
            rowhead.createCell(365).setCellValue(nChoreographySubprocessExpandedParallelInstance);
            rowhead.createCell(366).setCellValue(nChoreographySubprocessExpandedLoop);
            rowhead.createCell(367).setCellValue(nChoreographyParticipant);
            rowhead.createCell(368).setCellValue(nChoreographyParticipantMultiple);       
            rowhead.createCell(369).setCellValue(nConversationNone);
            rowhead.createCell(370).setCellValue(nConversationSubProcess);
            rowhead.createCell(371).setCellValue(nConversationCall);
            rowhead.createCell(372).setCellValue(nConversationSubProcessCall);
            rowhead.createCell(373).setCellValue(nConversationLink);
            rowhead.createCell(374).setCellValue(nAssociationCompensate);
            rowhead.createCell(375).setCellValue(nAssociationUndirected);
            rowhead.createCell(376).setCellValue(nAssociationUnidirectional);        
            rowhead.createCell(377).setCellValue(nAssociationBidirectional);
            rowhead.createCell(378).setCellValue(nAssociationDataOutput);
            rowhead.createCell(379).setCellValue(nAssociationDataInput);
            rowhead.createCell(380).setCellValue(nCondition);
            rowhead.createCell(381).setCellValue(nGroup);
            rowhead.createCell(382).setCellValue(nTextAnnotation);
            rowhead.createCell(383).setCellValue(nOfExtensionElements);
            rowhead.createCell(384).setCellValue(TotalElements);              
            
//            if(ConsiderExtendedSubProcess && nSubProcessExtended >0) {
//            	//Salvare tutti gli 
//            }
           
      		FileOutputStream fileOut = new FileOutputStream("bpmn_stats.xls");
       		wb.write(fileOut);  
       		//closing the Stream  
       		fileOut.close();  

        	}
        
      //closing the workbook  
   		wb.close(); 
        }
}