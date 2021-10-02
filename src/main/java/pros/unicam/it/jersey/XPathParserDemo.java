package pros.unicam.it.jersey;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.apache.commons.lang3.SystemUtils;
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;    

public class XPathParserDemo {

	private static boolean ConsiderExtendedSubProcess = false;

	public static void main(String[] args) {

		try {

		//Creation of the xls empty file
		Workbook wb = new XSSFWorkbook();    
		XSSFSheet sheet = (XSSFSheet) wb.createSheet("BPMN_Stats"); 
		XSSFSheet sheet2 = (XSSFSheet) wb.createSheet("BPMN_Stats_ExtendedSubProcess"); 
		XSSFRow rowhead = sheet.createRow((short)0);         
		//creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method  
		rowhead.createCell(0).setCellValue("fileName");
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
		rowhead.createCell(20).setCellValue("nTaskSendLoopNoneCompensateNone");
		rowhead.createCell(21).setCellValue("nTaskSendLoopNoneCompensate");
		rowhead.createCell(22).setCellValue("nTaskSendLoopStandardCompensateNone");           
		rowhead.createCell(23).setCellValue("nTaskSendLoopStandardCompensate"); 
		rowhead.createCell(24).setCellValue("nTaskSendLoopMIParallelCompensateNone"); 
		rowhead.createCell(25).setCellValue("nTaskSendLoopMIParallelCompensate");
		rowhead.createCell(26).setCellValue("nTaskSendLoopMISequentialCompensateNone"); 
		rowhead.createCell(27).setCellValue("nTaskSendLoopMISequentialCompensate");
		rowhead.createCell(28).setCellValue("nTaskReceiveLoopNoneCompensateNone");            
		rowhead.createCell(29).setCellValue("nTaskReceiveLoopNoneCompensate");           
		rowhead.createCell(30).setCellValue("nTaskReceiveLoopStandardCompensateNone");            
		rowhead.createCell(31).setCellValue("nTaskReceiveLoopStandardCompensate");            
		rowhead.createCell(32).setCellValue("nTaskReceiveLoopMIParallelCompensateNone");                        
		rowhead.createCell(33).setCellValue("nTaskReceiveLoopMIParallelCompensate");            
		rowhead.createCell(34).setCellValue("nTaskReceiveLoopMISequentialCompensateNone");           
		rowhead.createCell(35).setCellValue("nTaskReceiveLoopMISequentialCompensate");            
		rowhead.createCell(36).setCellValue("nTaskUserLoopNoneCompensateNone");            
		rowhead.createCell(37).setCellValue("nTaskUserLoopNoneCompensate");           
		rowhead.createCell(38).setCellValue("nTaskUserLoopStandardCompensateNone");            
		rowhead.createCell(39).setCellValue("nTaskUserLoopStandardCompensate");           
		rowhead.createCell(40).setCellValue("nTaskUserLoopMIParallelCompensateNone");            
		rowhead.createCell(41).setCellValue("nTaskUserLoopMIParallelCompensate");            
		rowhead.createCell(42).setCellValue("nTaskUserLoopMISequentialCompensateNone");            
		rowhead.createCell(43).setCellValue("nTaskUserLoopMISequentialCompensate");            
		rowhead.createCell(44).setCellValue("nTaskManualLoopNoneCompensateNone");            
		rowhead.createCell(45).setCellValue("nTaskManualLoopNoneCompensate");            
		rowhead.createCell(46).setCellValue("nTaskManualLoopStandardCompensateNone");            
		rowhead.createCell(47).setCellValue("nTaskManualLoopStandardCompensate");            
		rowhead.createCell(48).setCellValue("nTaskManualLoopMIParallelCompensateNone");            
		rowhead.createCell(49).setCellValue("nTaskManualLoopMIParallelCompensate");            
		rowhead.createCell(50).setCellValue("nTaskManualLoopMISequentialCompensateNone");            
		rowhead.createCell(51).setCellValue("nTaskManualLoopMISequentialCompensate");            
		rowhead.createCell(52).setCellValue("nTaskBusinessRuleLoopNoneCompensateNone");            
		rowhead.createCell(53).setCellValue("nTaskBusinessRuleLoopNoneCompensate");            
		rowhead.createCell(54).setCellValue("nTaskBusinessRuleLoopStandardCompensateNone");            
		rowhead.createCell(55).setCellValue("nTaskBusinessRuleLoopStandardCompensate");            
		rowhead.createCell(56).setCellValue("nTaskBusinessRuleLoopMIParallelCompensateNone");           
		rowhead.createCell(57).setCellValue("nTaskBusinessRuleLoopMIParallelCompensate");            
		rowhead.createCell(58).setCellValue("nTaskBusinessRuleLoopMISequentialCompensateNone");           
		rowhead.createCell(59).setCellValue("nTaskBusinessRuleLoopMISequentialCompensate");            
		rowhead.createCell(60).setCellValue("nTaskServiceLoopNoneCompensateNone");            
		rowhead.createCell(61).setCellValue("nTaskServiceLoopNoneCompensate");            
		rowhead.createCell(62).setCellValue("nTaskServiceLoopStandardCompensateNone");            
		rowhead.createCell(63).setCellValue("nTaskServiceLoopStandardCompensate");            
		rowhead.createCell(64).setCellValue("nTaskServiceLoopMIParallelCompensateNone");            
		rowhead.createCell(65).setCellValue("nTaskServiceLoopMIParallelCompensate");            
		rowhead.createCell(66).setCellValue("nTaskServiceLoopMISequentialCompensateNone");            
		rowhead.createCell(67).setCellValue("nTaskServiceLoopMISequentialCompensate");            
		rowhead.createCell(68).setCellValue("nTaskScriptLoopNoneCompensateNone");            
		rowhead.createCell(69).setCellValue("nTaskScriptLoopNoneCompensate");           
		rowhead.createCell(70).setCellValue("nTaskScriptLoopStandardCompensateNone");            
		rowhead.createCell(71).setCellValue("nTaskScriptLoopStandardCompensate");            
		rowhead.createCell(72).setCellValue("nTaskScriptLoopMIParallelCompensateNone");            
		rowhead.createCell(73).setCellValue("nTaskScriptLoopMIParallelCompensate");            
		rowhead.createCell(74).setCellValue("nTaskScriptLoopMISequentialCompensateNone");            
		rowhead.createCell(75).setCellValue("nTaskScriptLoopMISequentialCompensate");            
		rowhead.createCell(76).setCellValue("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone");
		rowhead.createCell(77).setCellValue("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate");
		rowhead.createCell(78).setCellValue("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone");
		rowhead.createCell(79).setCellValue("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate");
		rowhead.createCell(80).setCellValue("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone");
		rowhead.createCell(81).setCellValue("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate");
		rowhead.createCell(82).setCellValue("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone");
		rowhead.createCell(83).setCellValue("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate");
		rowhead.createCell(84).setCellValue("nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone");
		rowhead.createCell(85).setCellValue("nSubProcessExtendedEventNoneAdHocLoopNoneCompensate");
		rowhead.createCell(86).setCellValue("nSubProcessExtendedEventNoneAdHocLoopStandardCompensateNone");
		rowhead.createCell(87).setCellValue("nSubProcessExtendedEventNoneAdHocLoopStandardCompensate");
		rowhead.createCell(88).setCellValue("nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone");
		rowhead.createCell(89).setCellValue("nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate");
		rowhead.createCell(90).setCellValue("nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone");
		rowhead.createCell(91).setCellValue("nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate");
		rowhead.createCell(92).setCellValue("nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone");
		rowhead.createCell(93).setCellValue("nSubProcessExtendedEventNoneTransactionLoopNoneCompensate");
		rowhead.createCell(94).setCellValue("nSubProcessExtendedEventNoneTransactionLoopStandardCompensateNone");
		rowhead.createCell(95).setCellValue("nSubProcessExtendedEventNoneTransactionLoopStandardCompensate");
		rowhead.createCell(96).setCellValue("nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone");
		rowhead.createCell(97).setCellValue("nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate");
		rowhead.createCell(98).setCellValue("nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone");
		rowhead.createCell(99).setCellValue("nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate");
		rowhead.createCell(100).setCellValue("nSubProcessExtendedEventLoopNoneCompensateNone");
		rowhead.createCell(101).setCellValue("nSubProcessExtendedEventLoopNoneCompensate");
		rowhead.createCell(102).setCellValue("nSubProcessExtendedEventLoopStandardCompensateNone");
		rowhead.createCell(103).setCellValue("nSubProcessExtendedEventLoopStandardCompensate");
		rowhead.createCell(104).setCellValue("nSubProcessExtendedEventLoopMIParallelCompensateNone");
		rowhead.createCell(105).setCellValue("nSubProcessExtendedEventLoopMIParallelCompensate");
		rowhead.createCell(106).setCellValue("nSubProcessExtendedEventLoopMISequentialCompensateNone");
		rowhead.createCell(107).setCellValue("nSubProcessExtendedEventLoopMISequentialCompensate");
		rowhead.createCell(108).setCellValue("nSubProcessExtendedEventAdHocLoopNoneCompensateNone");
		rowhead.createCell(109).setCellValue("nSubProcessExtendedEventAdHocLoopNoneCompensate");
		rowhead.createCell(110).setCellValue("nSubProcessExtendedEventAdHocLoopStandardCompensateNone");
		rowhead.createCell(111).setCellValue("nSubProcessExtendedEventAdHocLoopStandardCompensate");
		rowhead.createCell(112).setCellValue("nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone");
		rowhead.createCell(113).setCellValue("nSubProcessExtendedEventAdHocLoopMIParallelCompensate");
		rowhead.createCell(114).setCellValue("nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone");
		rowhead.createCell(115).setCellValue("nSubProcessExtendedEventAdHocLoopMISequentialCompensate");
		rowhead.createCell(116).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone");
		rowhead.createCell(117).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate");
		rowhead.createCell(118).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone");
		rowhead.createCell(119).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate");
		rowhead.createCell(120).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone");
		rowhead.createCell(121).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate");
		rowhead.createCell(122).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone");
		rowhead.createCell(123).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate");
		rowhead.createCell(124).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone");
		rowhead.createCell(125).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate");
		rowhead.createCell(126).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopStandardCompensateNone");
		rowhead.createCell(127).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopStandardCompensate");
		rowhead.createCell(128).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone");
		rowhead.createCell(129).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate");
		rowhead.createCell(130).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone");
		rowhead.createCell(131).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate");
		rowhead.createCell(132).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone");
		rowhead.createCell(133).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate");
		rowhead.createCell(134).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopStandardCompensateNone");
		rowhead.createCell(135).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopStandardCompensate");
		rowhead.createCell(136).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensateNone");
		rowhead.createCell(137).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensate");
		rowhead.createCell(138).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensateNone");
		rowhead.createCell(139).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensate");
		rowhead.createCell(140).setCellValue("nSubProcessCollapsedEventLoopNoneCompensateNone");
		rowhead.createCell(141).setCellValue("nSubProcessCollapsedEventLoopNoneCompensate");
		rowhead.createCell(142).setCellValue("nSubProcessCollapsedEventLoopStandardCompensateNone");
		rowhead.createCell(143).setCellValue("nSubProcessCollapsedEventLoopStandardCompensate");
		rowhead.createCell(144).setCellValue("nSubProcessCollapsedEventLoopMIParallelCompensateNone");
		rowhead.createCell(145).setCellValue("nSubProcessCollapsedEventLoopMIParallelCompensate");
		rowhead.createCell(146).setCellValue("nSubProcessCollapsedEventLoopMISequentialCompensateNone");
		rowhead.createCell(147).setCellValue("nSubProcessCollapsedEventLoopMISequentialCompensate");
		rowhead.createCell(148).setCellValue("nSubProcessCollapsedEventLoopNoneCompensateNone");
		rowhead.createCell(149).setCellValue("nSubProcessCollapsedEventAdHocLoopNoneCompensate");
		rowhead.createCell(150).setCellValue("nSubProcessCollapsedEventAdHocLoopStandardCompensateNone");
		rowhead.createCell(151).setCellValue("nSubProcessCollapsedEventAdHocLoopStandardCompensate");
		rowhead.createCell(152).setCellValue("nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone");
		rowhead.createCell(153).setCellValue("nSubProcessCollapsedEventAdHocLoopMIParallelCompensate");
		rowhead.createCell(154).setCellValue("nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone");
		rowhead.createCell(155).setCellValue("nSubProcessCollapsedEventAdHocLoopMISequentialCompensate");
		rowhead.createCell(156).setCellValue("nDataObject");
		rowhead.createCell(157).setCellValue("nDataObjectCollection");
		rowhead.createCell(158).setCellValue("nDataObjectReference");
		rowhead.createCell(159).setCellValue("nDataStore");
		rowhead.createCell(160).setCellValue("nDataInput");
		rowhead.createCell(161).setCellValue("nDataOutput");
		rowhead.createCell(162).setCellValue("nExclusiveGatewayNoMarker");
		rowhead.createCell(163).setCellValue("nExclusiveGatewayMarker");
		rowhead.createCell(164).setCellValue("nParallelGateway");
		rowhead.createCell(165).setCellValue("nInclusiveGateway");
		rowhead.createCell(166).setCellValue("nEventBasedGateway");
		rowhead.createCell(167).setCellValue("nEventBasedGatewayExclusiveInstantiation");
		rowhead.createCell(168).setCellValue("nEventBasedGatewayParallelInstantiation");
		rowhead.createCell(169).setCellValue("nComplexGateway");
		rowhead.createCell(170).setCellValue("nStartMultipleParallelEventDefinition");
		rowhead.createCell(171).setCellValue("nStartMultipleEventDefinition");
		rowhead.createCell(172).setCellValue("nStartNoneEventDefinition");
		rowhead.createCell(173).setCellValue("nStartSignalEventDefinition");
		rowhead.createCell(174).setCellValue("nStartConditionalEventDefinition");
		rowhead.createCell(175).setCellValue("nStartTimerEventDefinition");
		rowhead.createCell(176).setCellValue("nStartMessageEventDefinition");
		rowhead.createCell(177).setCellValue("nStartMessageEventSubProcessInterruptingDefinition");
		rowhead.createCell(178).setCellValue("nStartTimerEventSubProcessInterruptingDefinition");
		rowhead.createCell(179).setCellValue("nStartEscalationEventSubProcessInterruptingDefinition");
		rowhead.createCell(180).setCellValue("nStartConditionalEventSubProcessInterruptingDefinition");
		rowhead.createCell(181).setCellValue("nStartErrorEventSubProcessInterruptingDefinition");
		rowhead.createCell(182).setCellValue("nStartCompensateEventSubProcessInterruptingDefinition");
		rowhead.createCell(183).setCellValue("nStartSignalEventSubProcessInterruptingDefinition");
		rowhead.createCell(184).setCellValue("nStartMultipleEventSubProcessInterruptingDefinition");
		rowhead.createCell(185).setCellValue("nStartMultipleParallelEventSubProcessInterruptingDefinition");       
		rowhead.createCell(186).setCellValue("nStartMessageEventSubProcessNonInterruptingDefinition");
		rowhead.createCell(187).setCellValue("nStartTimerEventSubProcessNonInterruptingDefinition");
		rowhead.createCell(188).setCellValue("nStartEscalationEventSubProcessNonInterruptingDefinition");
		rowhead.createCell(189).setCellValue("nStartConditionalEventSubProcessNonInterruptingDefinition");
		rowhead.createCell(190).setCellValue("nStartSignalEventSubProcessNonInterruptingDefinition");
		rowhead.createCell(191).setCellValue("nStartMultipleParallelEventSubProcessNonInterruptingDefinition");
		rowhead.createCell(192).setCellValue("nStartMultipleEventSubProcessNonInterruptingDefinition");       
		rowhead.createCell(193).setCellValue("nEndNoneEventDefinition");
		rowhead.createCell(194).setCellValue("nEndMultipleEventDefinition"); 
		rowhead.createCell(195).setCellValue("nEndEscalationEventDefinition");
		rowhead.createCell(196).setCellValue("nEndErrorEventDefinition");
		rowhead.createCell(197).setCellValue("nEndSignalEventDefinition");
		rowhead.createCell(198).setCellValue("nEndCompensateEventDefinition");
		rowhead.createCell(199).setCellValue("nEndCancelEventDefinition"); 
		rowhead.createCell(200).setCellValue("nEndMessageEventDefinition");
		rowhead.createCell(201).setCellValue("nEndTerminateEventDefinition");
		rowhead.createCell(202).setCellValue("nIntermediateCatchMultipleEventDefinition");
		rowhead.createCell(203).setCellValue("nIntermediateCatchMultipleParallelEventDefinition");
		rowhead.createCell(204).setCellValue("nIntermediateCatchMessageEventDefinition");
		rowhead.createCell(205).setCellValue("nIntermediateCatchTimerEventDefinition");
		rowhead.createCell(206).setCellValue("nIntermediateCatchConditionalEventDefinition");
		rowhead.createCell(207).setCellValue("nIntermediateCatchLinkEventDefinition");
		rowhead.createCell(208).setCellValue("nIntermediateCatchSignalEventDefinition");
		rowhead.createCell(209).setCellValue("nIntermediateThrowNoneEventDefinition");
		rowhead.createCell(210).setCellValue("nIntermediateThrowMessageEventDefinition");
		rowhead.createCell(211).setCellValue("nIntermediateThrowEscalationEventDefinition");
		rowhead.createCell(212).setCellValue("nIntermediateThrowLinkEventDefinition");
		rowhead.createCell(213).setCellValue("nIntermediateThrowSignalEventDefinition");
		rowhead.createCell(214).setCellValue("nIntermediateThrowCompensateEventDefinition");
		rowhead.createCell(215).setCellValue("nIntermediateThrowMultipleEventDefinition");
		rowhead.createCell(216).setCellValue("nIntermediateBoundaryMessageEvent");
		rowhead.createCell(217).setCellValue("nIntermediateBoundaryTimerEvent");
		rowhead.createCell(218).setCellValue("nIntermediateBoundaryCancelEvent");
		rowhead.createCell(219).setCellValue("nIntermediateBoundaryConditionalEvent");
		rowhead.createCell(220).setCellValue("nIntermediateBoundaryEscalationEvent");
		rowhead.createCell(221).setCellValue("nIntermediateBoundaryErrorEvent");
		rowhead.createCell(222).setCellValue("nIntermediateBoundarySignalEvent");
		rowhead.createCell(223).setCellValue("nIntermediateBoundaryCompensateEvent");
		rowhead.createCell(224).setCellValue("nIntermediateBoundaryMultipleEvent");
		rowhead.createCell(225).setCellValue("nIntermediateBoundaryMultipleParallelEvent");
		rowhead.createCell(226).setCellValue("nIntermediateBoundaryTimerEventNonInterrupting");
		rowhead.createCell(227).setCellValue("nIntermediateBoundaryEscalationEventNonInterrupting");
		rowhead.createCell(228).setCellValue("nIntermediateBoundaryConditionalEventNonInterrupting");
		rowhead.createCell(229).setCellValue("nIntermediateBoundaryMessageEventNonInterrupting");
		rowhead.createCell(230).setCellValue("nIntermediateBoundarySignalEventNonInterrupting");
		rowhead.createCell(231).setCellValue("nIntermediateBoundaryMultipleEventNonInterrupting");
		rowhead.createCell(232).setCellValue("nIntermediateBoundaryMultipleParallelEventNonInterrupting");
		rowhead.createCell(233).setCellValue("nMessageFlow");
		rowhead.createCell(234).setCellValue("nSequenceFlow");
		rowhead.createCell(235).setCellValue("nDefaultFlow");
		rowhead.createCell(236).setCellValue("nConditionalFlow");
		rowhead.createCell(237).setCellValue("nLane"); 
		rowhead.createCell(238).setCellValue("nPoolCollapsedMultiplicityNone");
		rowhead.createCell(239).setCellValue("nPoolCollapsedMultiplicity");
		rowhead.createCell(240).setCellValue("nPoolExpandedMultiplicityNone");
		rowhead.createCell(241).setCellValue("nPoolExpandedMultiplicity");
		rowhead.createCell(242).setCellValue("nChoreographyTask");
		rowhead.createCell(243).setCellValue("nChoreographyMessage");            
		rowhead.createCell(244).setCellValue("nChoreographyTaskSequentialMultipleInstance");
		rowhead.createCell(245).setCellValue("nChoreographyTaskParallelMultipleInstance");
		rowhead.createCell(246).setCellValue("nChoreographyTaskLoop");
		rowhead.createCell(247).setCellValue("nChoreographySubprocessCollapsed");
		rowhead.createCell(248).setCellValue("nChoreographySubprocessCollapsedParallelMultipleInstance");
		rowhead.createCell(249).setCellValue("nChoreographySubprocessCollapsedSequentialMultipleInstance");
		rowhead.createCell(250).setCellValue("nChoreographySubprocessCollapsedLoop");
		rowhead.createCell(251).setCellValue("nChoreographySubprocessCollapsedCall");
		rowhead.createCell(252).setCellValue("nChoreographySubprocessCollapsedCallSequentialMultipleInstance");
		rowhead.createCell(253).setCellValue("nChoreographySubprocessCollapsedCallParallelMultipleInstance");
		rowhead.createCell(254).setCellValue("nChoreographySubprocessCollapsedCallLoop");
		rowhead.createCell(255).setCellValue("nChoreographySubprocessExpanded");
		rowhead.createCell(256).setCellValue("nChoreographySubprocessExpandedSequentialMultipleInstance");
		rowhead.createCell(257).setCellValue("nChoreographySubprocessExpandedParallelMultipleInstance");
		rowhead.createCell(258).setCellValue("nChoreographySubprocessExpandedLoop");
		rowhead.createCell(259).setCellValue("nChoreographyParticipant");
		rowhead.createCell(260).setCellValue("nChoreographyParticipantMultiple");       
		rowhead.createCell(261).setCellValue("nConversationNone");
		rowhead.createCell(262).setCellValue("nConversationSubProcess");
		rowhead.createCell(263).setCellValue("nConversationCall");
		rowhead.createCell(264).setCellValue("nConversationSubProcessCall");
		rowhead.createCell(265).setCellValue("nConversationLink");
		rowhead.createCell(266).setCellValue("nAssociationCompensate");
		rowhead.createCell(267).setCellValue("nAssociationUndirected");
		rowhead.createCell(268).setCellValue("nAssociationUnidirectional");        
		rowhead.createCell(269).setCellValue("nAssociationBidirectional");
		rowhead.createCell(270).setCellValue("nAssociationDataOutput");
		rowhead.createCell(271).setCellValue("nAssociationDataInput");            
		rowhead.createCell(272).setCellValue("nGroup");
		rowhead.createCell(273).setCellValue("nTextAnnotation");
		rowhead.createCell(274).setCellValue("TotalElements"); 

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


		JFileChooser f = new JFileChooser();		
		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		f.showSaveDialog(null);

		File file = f.getSelectedFile();
		String folderString = file.getAbsolutePath().toString();

		System.out.println(folderString);

		File folder = new File(folderString);

		File[] listOfFiles = folder.listFiles();

		for (int x = 0; x < listOfFiles.length; x++) {

			//Defining global variables
			String fileName;
			String bpmnModeler;
			boolean isEnglish=false;

			//Process Subprocess or Collaboration
			String modelType = null;
			int nExtendedSubProcess=0;

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

			//Subprocess Extended
			int nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone=0;
			int nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate=0;
			int nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone=0;
			int nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate=0;
			int nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone=0;
			int nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate=0;
			int nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone=0;
			int nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate=0;

			int nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone=0;
			int nSubProcessExtendedEventNoneAdHocLoopNoneCompensate=0;
			int nSubProcessExtendedEventNoneAdHocLoopStandardCompensateNone=0;
			int nSubProcessExtendedEventNoneAdHocLoopStandardCompensate=0;
			int nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone=0;
			int nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate=0;
			int nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone=0;
			int nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate=0;

			int nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone=0;
			int nSubProcessExtendedEventNoneTransactionLoopNoneCompensate=0;
			int nSubProcessExtendedEventNoneTransactionLoopStandardCompensateNone=0;
			int nSubProcessExtendedEventNoneTransactionLoopStandardCompensate=0;
			int nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone=0;
			int nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate=0;
			int nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone=0;
			int nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate=0;

			int nSubProcessExtendedEventLoopNoneCompensateNone= 0;
			int nSubProcessExtendedEventLoopNoneCompensate= 0;
			int nSubProcessExtendedEventLoopStandardCompensateNone= 0;
			int nSubProcessExtendedEventLoopStandardCompensate= 0;
			int nSubProcessExtendedEventLoopMIParallelCompensateNone= 0;
			int nSubProcessExtendedEventLoopMIParallelCompensate= 0;
			int nSubProcessExtendedEventLoopMISequentialCompensateNone= 0;
			int nSubProcessExtendedEventLoopMISequentialCompensate= 0;

			int nSubProcessExtendedEventAdHocLoopNoneCompensateNone=0;
			int nSubProcessExtendedEventAdHocLoopNoneCompensate=0;
			int nSubProcessExtendedEventAdHocLoopStandardCompensateNone=0;
			int nSubProcessExtendedEventAdHocLoopStandardCompensate=0;
			int nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone=0;
			int nSubProcessExtendedEventAdHocLoopMIParallelCompensate=0;
			int nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone=0;
			int nSubProcessExtendedEventAdHocLoopMISequentialCompensate=0;


			//Subprocess Collapsed
			int nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone=0;
			int nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate=0;
			int nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone=0;
			int nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate=0;
			int nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone=0;
			int nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate=0;
			int nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone=0;
			int nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate=0;

			int nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone=0;
			int nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate=0;
			int nSubProcessCollapsedEventNoneAdHocLoopStandardCompensateNone=0;
			int nSubProcessCollapsedEventNoneAdHocLoopStandardCompensate=0;
			int nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone=0;
			int nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate=0;
			int nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone=0;
			int nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate=0;

			int nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone=0;
			int nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate=0;
			int nSubProcessCollapsedEventNoneTransactionLoopStandardCompensateNone=0;
			int nSubProcessCollapsedEventNoneTransactionLoopStandardCompensate=0;
			int nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensateNone=0;
			int nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensate=0;
			int nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensateNone=0;
			int nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensate=0;

			int nSubProcessCollapsedEventLoopNoneCompensateNone= 0;
			int nSubProcessCollapsedEventLoopNoneCompensate= 0;
			int nSubProcessCollapsedEventLoopStandardCompensateNone= 0;
			int nSubProcessCollapsedEventLoopStandardCompensate= 0;
			int nSubProcessCollapsedEventLoopMIParallelCompensateNone= 0;
			int nSubProcessCollapsedEventLoopMIParallelCompensate= 0;
			int nSubProcessCollapsedEventLoopMISequentialCompensateNone= 0;
			int nSubProcessCollapsedEventLoopMISequentialCompensate= 0;

			int nSubProcessCollapsedEventAdHocLoopNoneCompensateNone=0;
			int nSubProcessCollapsedEventAdHocLoopNoneCompensate=0;
			int nSubProcessCollapsedEventAdHocLoopStandardCompensateNone=0;
			int nSubProcessCollapsedEventAdHocLoopStandardCompensate=0;
			int nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone=0;
			int nSubProcessCollapsedEventAdHocLoopMIParallelCompensate=0;
			int nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone=0;
			int nSubProcessCollapsedEventAdHocLoopMISequentialCompensate=0;

			// Data Object
			int nDataObject=0;
			int nDataObjectCollection=0;
			int nDataObjectReference=0;
			int nDataStore=0;
			int nDataInput=0;
			int nDataOutput=0;
			int nMessage=0;
			
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
			int nIntermediateThrowMultipleEventDefinition=0;
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
			int TotalElements=0;

			//Set BPMN models name
			fileName= listOfFiles[x].getName();

			if(SystemUtils.IS_OS_WINDOWS) {
				System.out.println(folderString+"\\"+fileName);
				if(!(folderString+"\\"+fileName).contains(".bpmn"))continue;
			}else {
				System.out.println(folderString+"/"+fileName);
				if(!(folderString+"/"+fileName).contains(".bpmn"))continue;
			}
			//Read bpmn models
			File xmlFile = new File(folderString+"/"+fileName);

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

			//[TODO: Language]
			// TRUE if model has labels in english
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpathLang = xPathfactory.newXPath();
			XPathExpression expr = xpathLang.compile("//@name");
			Object resultModelWords = expr.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesModelWords = (NodeList) resultModelWords;
			ArrayList<String> modelWords = new ArrayList<String>();       

//			for(int a=0; a<nodesModelWords.getLength(); a++) {
//
//				modelWords.add(nodesModelWords.item(a).getTextContent());
//				JLanguageTool langTool = new JLanguageTool(new BritishEnglish());
//				List<RuleMatch> matches = langTool.check(modelWords.get(a));
//				isEnglish=true;
//				//If there is a word not in english, check this word and suggest correction
//				for (RuleMatch match : matches) {
//
//					//		              System.out.println("Potential error in model "+fileName+" at characters " +
//					//		                  match.getFromPos() + "-" + match.getToPos() + ": " +
//					//		                  match.getMessage());
//					//		              System.out.println("Suggested correction(s): " +
//					//		                  match.getSuggestedReplacements());
//					isEnglish=false;
//					break;		              
//				}	            
//			}


			//[TODO: Namespace]
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
			else if(doc.getDocumentElement().getAttributeNode("targetNamespace").getTextContent().contains("bpt-lab")) {
				bpmnModeler = "chor-js";
			}
			else {
				bpmnModeler = "Undefined";
			}

			// Check if the model is a Collaboration, a Process or contain a Subprocess

			//[TODO: Diagram Type]
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

							if(nodeModelType.contains("choreography")){

								modelType = "Choreography";
								break;
							}

							if(nodeModelType.contains("collaboration")) {

								modelType = "Collaboration";
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

			//[TODO: BPMN STATS]
			//----------------------------------------------BPMN STATS-------------------------------------------------
			//[TODO: Shape NodeList]
			XPath xpathShape = XPathFactory.newInstance().newXPath();
			xpathShape.setNamespaceContext(new NamespaceContext() {

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

			XPathExpression exprNodeShapes = xpathShape.compile("//bpmn:BPMNShape");
			Object resultNodeShapes  = exprNodeShapes.evaluate(doc, XPathConstants.NODESET);       
			NodeList nodesShapesList = (NodeList) resultNodeShapes;
			doc.getDocumentElement().normalize();    

			//[TODO: Subprocess NodeList]
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

			// [TODO: SUBPROCESS EXTENDED]
			// SubProcess Normal Extended 
			for(int i=0;i<nodesSubprocesses.getLength();i++) {
				String SubprocessesID = (((Element) nodesSubprocesses.item(i)).getAttribute("id"));

				Node SubPnodeChild = nodesSubprocesses.item(i);  

				for(int j=0;j<nodesShapesList.getLength();j++) {
					String SubprocessesShape = (((Element) nodesShapesList.item(j)).getAttribute("bpmnElement"));

					if(SubPnodeChild.hasChildNodes()) { 

						NodeList SubPnodeChildNodes = SubPnodeChild.getChildNodes();  

						for(int z=0;z<SubPnodeChildNodes.getLength(); z++)
						{
							try {
								if(SubPnodeChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE) {

									if(SubprocessesID.equalsIgnoreCase(SubprocessesShape) &&
											((Element) nodesShapesList.item(j)).getAttribute("isExpanded").contains("true")) {

										if(SubPnodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics") &&
												((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone++;

										}

										if(SubPnodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics") &&
												((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate++;

										}

										//mi par
										if(SubPnodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubPnodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone++;


										}

										//mi par comp
										if(SubPnodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubPnodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate++;

										}

										//mi seq
										if(SubPnodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubPnodeChildNodes.item(z)).getAttribute("isSequential").contains("true") &&
												((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone++;


										}
										//mi seq comp
										if(SubPnodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubPnodeChildNodes.item(z)).getAttribute("isSequential").contains("true") &&
												((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate++;

										}

										if(SubPnodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
												SubPnodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
												((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone++;		

										}

										if(SubPnodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
												SubPnodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
												((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate++;

										}


									}    						

								}}catch (Exception e) {}

						}
					}
				}

			}	
			nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone = nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone
					- nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone - nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone
					- nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone;

			nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate = nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate 
					- nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate - nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate
					- nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate;

			// [TODO: ADHOC EXTENDED]
			// SubProcess adhoc Extended
			for(int i=0;i<nodesSubprocessesAdHoc.getLength();i++) {
				String SubprocessesID = (((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("id"));

				Node SPAdHocNodeChild = nodesSubprocessesAdHoc.item(i);  

				for(int j=0;j<nodesShapesList.getLength();j++) {
					String SubprocessesShape = (((Element) nodesShapesList.item(j)).getAttribute("bpmnElement"));

					if(SPAdHocNodeChild.hasChildNodes()) { 

						NodeList SPAdHocNodeChildNodes = SPAdHocNodeChild.getChildNodes();  

						for(int z=0;z<SPAdHocNodeChildNodes.getLength(); z++)
						{
							try {
								if(SPAdHocNodeChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE) {

									if(SubprocessesID.equalsIgnoreCase(SubprocessesShape) &&
											((Element) nodesShapesList.item(j)).getAttribute("isExpanded").contains("true")) {

										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessExtendedEventNoneAdHocLoopStandardCompensateNone++;

										}

										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessExtendedEventNoneAdHocLoopStandardCompensate++;

										}

										//mi par
										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SPAdHocNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone++;


										}

										//mi par comp
										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SPAdHocNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate++;

										}

										//mi seq
										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SPAdHocNodeChildNodes.item(z)).getAttribute("isSequential").contains("true") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone++;


										}
										//mi seq comp
										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SPAdHocNodeChildNodes.item(z)).getAttribute("isSequential").contains("true") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate++;

										}

										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
												SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone++;	

										}

										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
												SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessExtendedEventNoneAdHocLoopNoneCompensate++;

										}


									}    						

								}}catch (Exception e) {}
						}
					}
				}

			}		
			nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone = nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone
					- nSubProcessExtendedEventNoneAdHocLoopStandardCompensateNone - nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone
					- nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone;
			nSubProcessExtendedEventNoneAdHocLoopNoneCompensate = nSubProcessExtendedEventNoneAdHocLoopNoneCompensate
					- nSubProcessExtendedEventNoneAdHocLoopStandardCompensate - nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate
					- nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate;

			// [TODO: TRANSACTION EXTENDED]
			// SubProcess transaction Extended
			for(int i=0;i<nodesTransaction.getLength();i++) {
				String SubprocessesID = (((Element) nodesTransaction.item(i)).getAttribute("id"));

				Node TransactionNodeChild = nodesTransaction.item(i);  

				for(int j=0;j<nodesShapesList.getLength();j++) {
					String SubprocessesShape = (((Element) nodesShapesList.item(j)).getAttribute("bpmnElement"));

					if(TransactionNodeChild.hasChildNodes()) { 

						NodeList TransactionNodeChildNodes = TransactionNodeChild.getChildNodes();  

						for(int z=0;z<TransactionNodeChildNodes.getLength(); z++)
						{
							try {
								if(TransactionNodeChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE) {
									if(SubprocessesID.equalsIgnoreCase(SubprocessesShape) &&
											((Element) nodesShapesList.item(j)).getAttribute("isExpanded").contains("true")) {

										if(TransactionNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==true &&
												((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessExtendedEventNoneTransactionLoopStandardCompensateNone++;

										}

										if(TransactionNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==true &&
												((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessExtendedEventNoneTransactionLoopStandardCompensate++;

										}

										//mi par
										if(TransactionNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) TransactionNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone++;


										}

										//mi par comp
										if(TransactionNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) TransactionNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate++;

										}

										//mi seq
										if(TransactionNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) TransactionNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==true &&
												((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone++;


										}
										//mi seq comp
										if(TransactionNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) TransactionNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==true &&
												((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate++;

										}

										if(TransactionNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
												TransactionNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
												((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone++;	

										}

										if(TransactionNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
												TransactionNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
												((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessExtendedEventNoneTransactionLoopNoneCompensate++;

										}


									}    						

								}}catch (Exception e) {}
						}
					}
				}

			}
			nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone = nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone 
					- nSubProcessExtendedEventNoneTransactionLoopStandardCompensateNone - nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone
					- nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone;

			nSubProcessExtendedEventNoneTransactionLoopNoneCompensate = nSubProcessExtendedEventNoneTransactionLoopNoneCompensate 
					- nSubProcessExtendedEventNoneTransactionLoopStandardCompensate - nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate
					- nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate;

			// [TODO: EVENT SUBPROCESS EXTENDED]   	
			// SubProcess event Extended
			for(int i=0;i<nodesSubprocessesEvent.getLength();i++) {
				String SubprocessesID = (((Element) nodesSubprocessesEvent.item(i)).getAttribute("id"));

				Node SubPExtendedEventNodeChild = nodesSubprocessesEvent.item(i);  

				for(int j=0;j<nodesShapesList.getLength();j++) {
					String SubprocessesShape = (((Element) nodesShapesList.item(j)).getAttribute("bpmnElement"));

					if(SubPExtendedEventNodeChild.hasChildNodes()) { 

						NodeList SubprocessesEventNodeChildNodes = SubPExtendedEventNodeChild.getChildNodes();  

						for(int z=0;z<SubprocessesEventNodeChildNodes.getLength(); z++)
						{
							try {
								if(SubprocessesEventNodeChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE) {
									if(SubprocessesID.equalsIgnoreCase(SubprocessesShape) &&
											((Element) nodesShapesList.item(j)).getAttribute("isExpanded").contains("true")) {

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==true &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessExtendedEventLoopStandardCompensateNone++;

										}

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==true &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessExtendedEventLoopStandardCompensate++;

										}

										//mi par
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessExtendedEventLoopMIParallelCompensateNone++;

										}

										//mi par comp
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessExtendedEventLoopMIParallelCompensate++;
										}

										//mi seq
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==true &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessExtendedEventLoopMISequentialCompensateNone++;


										}
										//mi seq comp
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==true &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessExtendedEventLoopMISequentialCompensate++;			        					

										}

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
												SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessExtendedEventLoopNoneCompensateNone++;	

										}

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
												SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessExtendedEventLoopNoneCompensate++;

										}


									}    						

								}}catch (Exception e) {}
						}
					}
				}

			}	
			nSubProcessExtendedEventLoopNoneCompensateNone = nSubProcessExtendedEventLoopNoneCompensateNone - nSubProcessExtendedEventLoopStandardCompensateNone
					- nSubProcessExtendedEventLoopMIParallelCompensateNone - nSubProcessExtendedEventLoopMISequentialCompensateNone;
			nSubProcessExtendedEventLoopNoneCompensate = nSubProcessExtendedEventLoopNoneCompensate - nSubProcessExtendedEventLoopStandardCompensate
					- nSubProcessExtendedEventLoopMIParallelCompensate - nSubProcessExtendedEventLoopMISequentialCompensate;
			/* 
             // [TODO: SUBPROCESS EXPANDED EVENT + ADHOC]
SUBPROCESS EXPANDED EVENT + ADHOC
			 */
			for(int i=0;i<nodesSubprocessesEvent.getLength();i++) {
				String SubprocessesID = (((Element) nodesSubprocessesEvent.item(i)).getAttribute("id"));

				Node SubPExtendedEventNodeChild = nodesSubprocessesEvent.item(i);  

				for(int j=0;j<nodesShapesList.getLength();j++) {
					String SubprocessesShape = (((Element) nodesShapesList.item(j)).getAttribute("bpmnElement"));

					if(SubPExtendedEventNodeChild.hasChildNodes()) { 

						NodeList SubprocessesEventNodeChildNodes = SubPExtendedEventNodeChild.getChildNodes();  

						for(int z=0;z<SubprocessesEventNodeChildNodes.getLength(); z++)
						{
							try {
								if(SubprocessesEventNodeChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE) {
									if(SubprocessesID.equalsIgnoreCase(SubprocessesShape) &&
											((Element) nodesShapesList.item(j)).getAttribute("isExpanded").contains("true")) {

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==true &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("completionQuantity").contains("1") ) {
											nSubProcessExtendedEventAdHocLoopStandardCompensateNone++;

										}

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==true &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true") &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("completionQuantity").contains("1")) {
											nSubProcessExtendedEventAdHocLoopStandardCompensate++;

										}

										//mi par
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("completionQuantity").contains("1")) {
											nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone++;


										}

										//mi par comp
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true") &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("completionQuantity").contains("1")) {
											nSubProcessExtendedEventAdHocLoopMIParallelCompensate++;

										}

										//mi seq
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==true &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("completionQuantity").contains("1")) {
											nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone++;


										}
										//mi seq comp
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==true &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true") &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("completionQuantity").contains("1")) {
											nSubProcessExtendedEventAdHocLoopMISequentialCompensate++;

										}

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
												SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("completionQuantity").contains("1")) {
											nSubProcessExtendedEventAdHocLoopNoneCompensateNone++;	

										}

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
												SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true") &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("completionQuantity").contains("1")) {
											nSubProcessExtendedEventAdHocLoopNoneCompensate++;

										}


									}    						

								}}catch (Exception e) {}
						}
					}
				}

			}	

			nSubProcessExtendedEventAdHocLoopNoneCompensateNone = nSubProcessExtendedEventAdHocLoopNoneCompensateNone - nSubProcessExtendedEventAdHocLoopStandardCompensateNone
					- nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone - nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone;

			nSubProcessExtendedEventAdHocLoopNoneCompensate = nSubProcessExtendedEventAdHocLoopNoneCompensate - nSubProcessExtendedEventAdHocLoopStandardCompensate
					- nSubProcessExtendedEventAdHocLoopMIParallelCompensate - nSubProcessExtendedEventAdHocLoopMISequentialCompensate;

			// [TODO: SUBPROCESS COLLAPSED]
			// SubProcess Normal Collapsed 
			for(int i=0;i<nodesSubprocesses.getLength();i++) {
				String SubprocessesID = (((Element) nodesSubprocesses.item(i)).getAttribute("id"));

				Node SubPnodeChild = nodesSubprocesses.item(i);  

				for(int j=0;j<nodesShapesList.getLength();j++) {
					String SubprocessesShape = (((Element) nodesShapesList.item(j)).getAttribute("bpmnElement"));

					if(SubPnodeChild.hasChildNodes()) { 

						NodeList SubPnodeChildNodes = SubPnodeChild.getChildNodes();  

						for(int z=0;z<SubPnodeChildNodes.getLength(); z++)
						{
							try {
								if(SubPnodeChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE) {
									if(SubprocessesID.equalsIgnoreCase(SubprocessesShape) &&
											((Element) nodesShapesList.item(j)).getAttribute("isExpanded").contains("true")==false) {

										if(SubPnodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics") &&
												((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone++;

										}

										if(SubPnodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics") &&
												((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate++;

										}

										//mi par
										if(SubPnodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubPnodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone++;


										}

										//mi par comp
										if(SubPnodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubPnodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate++;

										}

										//mi seq
										if(SubPnodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubPnodeChildNodes.item(z)).getAttribute("isSequential").contains("true") &&
												((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone++;


										}
										//mi seq comp
										if(SubPnodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubPnodeChildNodes.item(z)).getAttribute("isSequential").contains("true") &&
												((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate++;

										}

										if(SubPnodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
												SubPnodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
												((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone++;

										}

										if(SubPnodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
												SubPnodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
												((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate++;

										}


									}    						

								}}catch (Exception e) {}
						}
					}
				}

			}	
			nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone = nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone 
					- nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone - nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone
					- nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone;

			nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate = nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate
					- nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate - nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate
					- nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate;


			// [TODO: ADHOC COLLAPSED]
			// SubProcess adhoc Collapsed
			for(int i=0;i<nodesSubprocessesAdHoc.getLength();i++) {
				String SubprocessesID = (((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("id"));

				Node SPAdHocNodeChild = nodesSubprocessesAdHoc.item(i);  

				for(int j=0;j<nodesShapesList.getLength();j++) {
					String SubprocessesShape = (((Element) nodesShapesList.item(j)).getAttribute("bpmnElement"));

					if(SPAdHocNodeChild.hasChildNodes()) { 

						NodeList SPAdHocNodeChildNodes = SPAdHocNodeChild.getChildNodes();  

						for(int z=0;z<SPAdHocNodeChildNodes.getLength(); z++)
						{
							try {
								if(SPAdHocNodeChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE) {
									if(SubprocessesID.equalsIgnoreCase(SubprocessesShape) &&
											((Element) nodesShapesList.item(j)).getAttribute("isExpanded").contains("true")==false) {

										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessCollapsedEventNoneAdHocLoopStandardCompensateNone++;

										}

										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessCollapsedEventNoneAdHocLoopStandardCompensate++;

										}

										//mi par
										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SPAdHocNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone++;


										}

										//mi par comp
										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) SPAdHocNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate++;

										}

										//mi seq
										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) SPAdHocNodeChildNodes.item(z)).getAttribute("isSequential").contains("true") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone++;


										}
										//mi seq comp
										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")&&
												((Element) SPAdHocNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")&&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate++;

										}

										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
												SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone++;	

										}

										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
												SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate++;

										}


									}    						

								}}catch (Exception e) {}
						}
					}
				}

			}		
			nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone = nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone - nSubProcessCollapsedEventNoneAdHocLoopStandardCompensateNone
					- nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone - nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone;

			nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate = nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate - nSubProcessCollapsedEventNoneAdHocLoopStandardCompensate
					- nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate - nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate;

			// [TODO: TRANSACTION COLLAPSED]		
			// SubProcess transaction Collapsed
			for(int i=0;i<nodesTransaction.getLength();i++) {
				String SubprocessesID = (((Element) nodesTransaction.item(i)).getAttribute("id"));

				Node TransactionNodeChild = nodesTransaction.item(i);  

				for(int j=0;j<nodesShapesList.getLength();j++) {
					String SubprocessesShape = (((Element) nodesShapesList.item(j)).getAttribute("bpmnElement"));

					if(TransactionNodeChild.hasChildNodes()) { 

						NodeList TransactionNodeChildNodes = TransactionNodeChild.getChildNodes();  

						for(int z=0;z<TransactionNodeChildNodes.getLength(); z++)
						{
							try {
								if(TransactionNodeChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE) {
									if(SubprocessesID.equalsIgnoreCase(SubprocessesShape) &&
											((Element) nodesShapesList.item(j)).getAttribute("isExpanded").contains("true")==false) {

										if(TransactionNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics") &&
												((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessCollapsedEventNoneTransactionLoopStandardCompensateNone++;

										}

										if(TransactionNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics") &&
												((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessCollapsedEventNoneTransactionLoopStandardCompensate++;

										}

										//mi par
										if(TransactionNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) TransactionNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensateNone++;


										}

										//mi par comp
										if(TransactionNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) TransactionNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensate++;

										}

										//mi seq
										if(TransactionNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) TransactionNodeChildNodes.item(z)).getAttribute("isSequential").contains("true") &&
												((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensateNone++;


										}
										//mi seq comp
										if(TransactionNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) TransactionNodeChildNodes.item(z)).getAttribute("isSequential").contains("true") &&
												((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensate++;

										}

										if(TransactionNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
												TransactionNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
												((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone++;			

										}

										if(TransactionNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
												TransactionNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
												((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate++;

										}


									}    						

								}}catch (Exception e) {}
						}
					}
				}

			}
			nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone = nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone - nSubProcessCollapsedEventNoneTransactionLoopStandardCompensateNone
					- nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensateNone - nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensateNone;

			nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate = nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate - nSubProcessCollapsedEventNoneTransactionLoopStandardCompensate
					- nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensate - nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensate;

			// [TODO: EVENT SUBPROCESS COLLAPSED]
			// SubProcess event Collapsed
			for(int i=0;i<nodesSubprocessesEvent.getLength();i++) {
				String SubprocessesID = (((Element) nodesSubprocessesEvent.item(i)).getAttribute("id"));

				Node SubPExtendedEventNodeChild = nodesSubprocessesEvent.item(i);  

				for(int j=0;j<nodesShapesList.getLength();j++) {
					String SubprocessesShape = (((Element) nodesShapesList.item(j)).getAttribute("bpmnElement"));

					if(SubPExtendedEventNodeChild.hasChildNodes()) { 

						NodeList SubprocessesEventNodeChildNodes = SubPExtendedEventNodeChild.getChildNodes();  

						for(int z=0;z<SubprocessesEventNodeChildNodes.getLength(); z++)
						{
							try {
								if(SubprocessesEventNodeChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE) {
									if(SubprocessesID.equalsIgnoreCase(SubprocessesShape) &&
											((Element) nodesShapesList.item(j)).getAttribute("isExpanded").contains("true")==false) {

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics") &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false ) {
											nSubProcessCollapsedEventLoopStandardCompensateNone++;

										}

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics") &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true") ) {
											nSubProcessCollapsedEventLoopStandardCompensate++;

										}

										//mi par
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false ) {
											nSubProcessCollapsedEventLoopMIParallelCompensateNone++;


										}

										//mi par comp
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true") ) {
											nSubProcessCollapsedEventLoopMIParallelCompensate++;

										}

										//mi seq
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true") &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false ) {
											nSubProcessCollapsedEventLoopMISequentialCompensateNone++;


										}
										//mi seq comp
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true") &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true") ) {
											nSubProcessCollapsedEventLoopMISequentialCompensate++;

										}

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
												SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false ) {
											nSubProcessCollapsedEventLoopNoneCompensateNone++;		

										}

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
												SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")  ) {
											nSubProcessCollapsedEventLoopNoneCompensate++;

										}


									}    						

								}}catch (Exception e) {}
						}
					}
				}

			}	
			nSubProcessCollapsedEventLoopNoneCompensateNone = nSubProcessCollapsedEventLoopNoneCompensateNone - nSubProcessCollapsedEventLoopStandardCompensateNone 
					- nSubProcessCollapsedEventLoopMIParallelCompensateNone - nSubProcessCollapsedEventLoopMISequentialCompensateNone;

			nSubProcessCollapsedEventLoopNoneCompensate = nSubProcessCollapsedEventLoopNoneCompensate - nSubProcessCollapsedEventLoopStandardCompensate
					- nSubProcessCollapsedEventLoopMIParallelCompensate - nSubProcessCollapsedEventLoopMISequentialCompensate;

			// [TODO: EVENT SUBPROCESS COLLAPSED + ADHOC]
			/*
SUBPROCESS Collapsed EVENT + ADHOC
			 */
			for(int i=0;i<nodesSubprocessesEvent.getLength();i++) {
				String SubprocessesID = (((Element) nodesSubprocessesEvent.item(i)).getAttribute("id"));

				Node SubPExtendedEventNodeChild = nodesSubprocessesEvent.item(i);  

				for(int j=0;j<nodesShapesList.getLength();j++) {
					String SubprocessesShape = (((Element) nodesShapesList.item(j)).getAttribute("bpmnElement"));

					if(SubPExtendedEventNodeChild.hasChildNodes()) { 

						NodeList SubprocessesEventNodeChildNodes = SubPExtendedEventNodeChild.getChildNodes();  

						for(int z=0;z<SubprocessesEventNodeChildNodes.getLength(); z++)
						{
							try {
								if(SubprocessesEventNodeChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE) {
									if(SubprocessesID.equalsIgnoreCase(SubprocessesShape) &&
											((Element) nodesShapesList.item(j)).getAttribute("isExpanded").contains("true")==false) {

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")&&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("completionQuantity").contains("1") ) {
											nSubProcessCollapsedEventAdHocLoopStandardCompensateNone++;

										}

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics") &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true") &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("completionQuantity").contains("1")) {
											nSubProcessCollapsedEventAdHocLoopStandardCompensate++;

										}

										//mi par
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("completionQuantity").contains("1")) {
											nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone++;


										}

										//mi par comp
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true") &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("completionQuantity").contains("1")) {
											nSubProcessCollapsedEventAdHocLoopMIParallelCompensate++;

										}

										//mi seq
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")&&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("completionQuantity").contains("1")) {
											nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone++;


										}
										//mi seq comp
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true") &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true") &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("completionQuantity").contains("1")) {
											nSubProcessCollapsedEventAdHocLoopMISequentialCompensate++;

										}

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
												SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("completionQuantity").contains("1")) {
											nSubProcessCollapsedEventAdHocLoopNoneCompensateNone++;			

										}

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
												SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true") &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("completionQuantity").contains("1")) {
											nSubProcessCollapsedEventAdHocLoopNoneCompensate++;

										}


									}    						

								}}catch (Exception e) {}
						}
					}
				}

			}	
			nSubProcessCollapsedEventAdHocLoopNoneCompensateNone = nSubProcessCollapsedEventAdHocLoopNoneCompensateNone - nSubProcessCollapsedEventAdHocLoopStandardCompensateNone
					- nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone - nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone;

			nSubProcessCollapsedEventAdHocLoopNoneCompensate = nSubProcessCollapsedEventAdHocLoopNoneCompensate - nSubProcessCollapsedEventAdHocLoopStandardCompensate
					- nSubProcessCollapsedEventAdHocLoopMIParallelCompensate - nSubProcessCollapsedEventAdHocLoopMISequentialCompensate;



			// This is a counter to detect a general SubProcess extended 
			//nExtendedSubProcess 
			/*TODO SUM ALL EXTENDED SUBPROCESS TYPE*/
			nExtendedSubProcess= nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone+
					nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate+
					nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone+
					nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate+
					nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone+
					nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate+
					nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone+
					nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate+
					nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone+
					nSubProcessExtendedEventNoneAdHocLoopNoneCompensate+
					nSubProcessExtendedEventNoneAdHocLoopStandardCompensateNone+
					nSubProcessExtendedEventNoneAdHocLoopStandardCompensate+
					nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone+
					nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate+
					nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone+
					nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate+
					nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone+
					nSubProcessExtendedEventNoneTransactionLoopNoneCompensate+
					nSubProcessExtendedEventNoneTransactionLoopStandardCompensateNone+
					nSubProcessExtendedEventNoneTransactionLoopStandardCompensate+
					nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone+
					nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate+
					nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone+
					nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate+
					nSubProcessExtendedEventLoopNoneCompensateNone+
					nSubProcessExtendedEventLoopNoneCompensate+
					nSubProcessExtendedEventLoopStandardCompensateNone+
					nSubProcessExtendedEventLoopStandardCompensate+
					nSubProcessExtendedEventLoopMIParallelCompensateNone+
					nSubProcessExtendedEventLoopMIParallelCompensate+
					nSubProcessExtendedEventLoopMISequentialCompensateNone+
					nSubProcessExtendedEventLoopMISequentialCompensate+
					nSubProcessExtendedEventAdHocLoopNoneCompensateNone+
					nSubProcessExtendedEventAdHocLoopNoneCompensate+
					nSubProcessExtendedEventAdHocLoopStandardCompensateNone+
					nSubProcessExtendedEventAdHocLoopStandardCompensate+
					nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone+
					nSubProcessExtendedEventAdHocLoopMIParallelCompensate+
					nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone+
					nSubProcessExtendedEventAdHocLoopMISequentialCompensate;

			//[TODO TASK NONE] 
			//All Task none        

			XPathExpression exprTask11 = xpath.compile("//bpmn:task[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[not(contains(@isSequential,'true'))]");
			Object resultTask11 = exprTask11.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesTask11 = (NodeList) resultTask11;
			doc.getDocumentElement().normalize();  
			nTaskNoneLoopMIParallelCompensateCallNone = nodesTask11.getLength();

			XPathExpression exprTask9 = xpath.compile("//bpmn:task[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[not(contains(@isSequential,'true'))]");
			Object resultTask9 = exprTask9.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesTask9 = (NodeList) resultTask9;
			doc.getDocumentElement().normalize();  
			nTaskNoneLoopMIParallelCompensateNoneCallNone = nodesTask9.getLength();

			XPathExpression exprTask12= xpath.compile("//bpmn:callActivity[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[not(contains(@isSequential,'true'))]");
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

			XPathExpression exprTask10 = xpath.compile("//bpmn:callActivity[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[not(contains(@isSequential,'true'))]");
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

			//[TODO TASK SEND]
			//All Task send
			XPathExpression exprTask17 = xpath.compile("//bpmn:sendTask[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[not(contains(@isSequential,'true'))]");
			Object resultTask17 = exprTask17.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesTask17 = (NodeList) resultTask17;
			doc.getDocumentElement().normalize();  
			nTaskSendLoopMIParallelCompensate = nodesTask17.getLength();

			XPathExpression exprTask18= xpath.compile("//bpmn:sendTask[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[not(contains(@isSequential,'true'))]");
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

			//[TODO TASK RECEIVE]
			//N° of receive tasks
			XPathExpression exprTaskS1 = xpath.compile("//bpmn:receiveTask[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[not(contains(@isSequential,'true'))]");
			Object resultTaskS1 = exprTaskS1.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesTaskS1 = (NodeList) resultTaskS1;
			doc.getDocumentElement().normalize();  
			nTaskReceiveLoopMIParallelCompensate = nodesTaskS1.getLength();

			XPathExpression exprTaskS2= xpath.compile("//bpmn:receiveTask[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[not(contains(@isSequential,'true'))]");
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
			
			//[TODO TASK USER]
			//All Task user
			XPathExpression exprTaskU1 = xpath.compile("//bpmn:userTask[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[not(contains(@isSequential,'true'))]");
			Object resultTaskU1 = exprTaskU1.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesTaskU1 = (NodeList) resultTaskU1;
			doc.getDocumentElement().normalize();  
			nTaskUserLoopMIParallelCompensate = nodesTaskU1.getLength();

			XPathExpression exprTaskU2= xpath.compile("//bpmn:userTask[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[not(contains(@isSequential,'true'))]");
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
			Object resultTaskU6 = exprTaskU6.evaluate(doc, XPathConstants.NODESET);
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

			//[TODO TASK MANUAL]
			//All Task manual
			XPathExpression exprTaskM1 = xpath.compile("//bpmn:manualTask[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[not(contains(@isSequential,'true'))]");
			Object resultTaskM1 = exprTaskM1.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesTaskM1 = (NodeList) resultTaskM1;
			doc.getDocumentElement().normalize();  
			nTaskManualLoopMIParallelCompensate = nodesTaskM1.getLength();

			XPathExpression exprTaskM2= xpath.compile("//bpmn:manualTask[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[not(contains(@isSequential,'true'))]");
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

			//[TODO TASK BUSINESS RULE]
			//All Task Business Rule
			XPathExpression exprTaskBR1 = xpath.compile("//bpmn:businessRuleTask[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[not(contains(@isSequential,'true'))]");
			Object resultTaskBR1 = exprTaskBR1.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesTaskBR1 = (NodeList) resultTaskBR1;
			doc.getDocumentElement().normalize();  
			nTaskBusinessRuleLoopMIParallelCompensate = nodesTaskBR1.getLength();

			XPathExpression exprTaskBR2= xpath.compile("//bpmn:businessRuleTask[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[not(contains(@isSequential,'true'))]");
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

			//[TODO TASK SERVICE]
			//All Task Service
			XPathExpression exprTaskSer1 = xpath.compile("//bpmn:serviceTask[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[not(contains(@isSequential,'true'))]");
			Object resultTaskSer1 = exprTaskSer1.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesTaskSer1 = (NodeList) resultTaskSer1;
			doc.getDocumentElement().normalize();  
			nTaskServiceLoopMIParallelCompensate = nodesTaskSer1.getLength();

			XPathExpression exprTaskSer2= xpath.compile("//bpmn:serviceTask[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[not(contains(@isSequential,'true'))]");
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

			//[TODO TASK SCRIPT]
			//All Task Script
			XPathExpression exprTaskScr1 = xpath.compile("//bpmn:scriptTask[@isForCompensation='true']//bpmn:multiInstanceLoopCharacteristics[not(contains(@isSequential,'true'))]");
			Object resultTaskScr1 = exprTaskScr1.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesTaskScr1 = (NodeList) resultTaskScr1;
			doc.getDocumentElement().normalize();  
			nTaskScriptLoopMIParallelCompensate = nodesTaskScr1.getLength();

			XPathExpression exprTaskScr2= xpath.compile("//bpmn:scriptTask[not(contains(@isForCompensation,'true'))]//bpmn:multiInstanceLoopCharacteristics[not(contains(@isSequential,'true'))]");
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

			//[TODO ARTIFACTS]
			//[TODO TEXT ANNOTATION]
			//Text Annotation
			XPathExpression exprTextAnn = xpath.compile("//bpmn:textAnnotation");
			Object resultTextAnn = exprTextAnn.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesTextAnn = (NodeList) resultTextAnn;
			doc.getDocumentElement().normalize();  
			nTextAnnotation = nodesTextAnn.getLength();
			
			//[TODO GROUP]
			//N° of Group
			XPathExpression exprGroup = xpath.compile("//bpmn:group");
			Object resultGroup = exprGroup.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesGroup = (NodeList) resultGroup;
			doc.getDocumentElement().normalize();  
			nGroup = nodesGroup.getLength();  

			//[TODO DATA ONJECTS]
			//DATA OBJECTS------------------------------------------------------------------------------------

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
			

			// N° of Data Store
			XPathExpression exprDS = xpath.compile("//bpmn:dataStoreReference");
			Object resultDS = exprDS.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesDS = (NodeList) resultDS;
			doc.getDocumentElement().normalize();  
			nDataStore = nodesDS.getLength();

			//message
			XPathExpression exprChoMsg = xpath.compile("//bpmn:message");
			Object resultChoMsg = exprChoMsg.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesChoMsg = (NodeList) resultChoMsg;
			doc.getDocumentElement().normalize();  
			nChoreographyMessage = nodesChoMsg.getLength();

			//[TODO GATEWAYS]
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

			//[TODO EVENTS]
			// EVENTS

			//[TODO START EVENT]
			// Start Events

			XPathExpression exprStartEventInterrupting = xpath.compile("//bpmn:startEvent[not(contains(@isInterrupting,'false'))]");
			Object resultStartEventInterrupting = exprStartEventInterrupting.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesStartEventInterrupting = (NodeList) resultStartEventInterrupting;
			doc.getDocumentElement().normalize();    

			for(int i=0; i<nodesStartEventInterrupting.getLength(); i++) {

				Node StartEventNode = nodesStartEventInterrupting.item(i);   

				if(((Element) nodesStartEventInterrupting.item(i)).getAttribute("parallelMultiple").contains("true")) {
					nStartMultipleParallelEventDefinition++;
				}      	
				else {
					NodeList StartEventInterruptingChildNodes = StartEventNode.getChildNodes();
										
						int NumberOfChildsOfEachStartEventNode=0;
						
						for (int z = 0; z < StartEventInterruptingChildNodes.getLength(); z++) {
							
							if (StartEventInterruptingChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
					        		StartEventInterruptingChildNodes.item(z).getNodeName().contains("messageEventDefinition"))
						        	NumberOfChildsOfEachStartEventNode++;
							
							if (StartEventInterruptingChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
						        	StartEventInterruptingChildNodes.item(z).getNodeName().contains("timerEventDefinition"))
						        	NumberOfChildsOfEachStartEventNode++;
							
							if (StartEventInterruptingChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
						        	StartEventInterruptingChildNodes.item(z).getNodeName().contains("escalationEventDefinition")) 
					        		NumberOfChildsOfEachStartEventNode++;
							
							if (StartEventInterruptingChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
						        	StartEventInterruptingChildNodes.item(z).getNodeName().contains("conditionalEventDefinition"))
						        	NumberOfChildsOfEachStartEventNode++;
							
							if (StartEventInterruptingChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
						        	StartEventInterruptingChildNodes.item(z).getNodeName().contains("errorEventDefinition"))
						        	NumberOfChildsOfEachStartEventNode++;
							
							if (StartEventInterruptingChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
						        	StartEventInterruptingChildNodes.item(z).getNodeName().contains("cancelEventDefinition"))
						        	NumberOfChildsOfEachStartEventNode++;
							
							if (StartEventInterruptingChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
						        	StartEventInterruptingChildNodes.item(z).getNodeName().contains("compensateEventDefinition"))
						        	NumberOfChildsOfEachStartEventNode++;
							
							if (StartEventInterruptingChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
						        	StartEventInterruptingChildNodes.item(z).getNodeName().contains("signalEventDefinition"))
						        	NumberOfChildsOfEachStartEventNode++;
							
							if (StartEventInterruptingChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
						        	StartEventInterruptingChildNodes.item(z).getNodeName().contains("terminateEventDefinition"))
						        	NumberOfChildsOfEachStartEventNode++;    					        
					    }
						
						if(NumberOfChildsOfEachStartEventNode==0)
							nStartNoneEventDefinition++;						
							
						if(NumberOfChildsOfEachStartEventNode > 1 && ((Element) nodesStartEventInterrupting.item(i)).getAttribute("parallelMultiple").contains("true")==false)
							nStartMultipleEventDefinition++;
					
						else {
							for(int j=0;j<StartEventInterruptingChildNodes.getLength(); j++) {
		
								if(StartEventInterruptingChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
			
									if(StartEventInterruptingChildNodes.item(j).getNodeName().contains("signalEventDefinition")									
											&& ((Element) nodesStartEventInterrupting.item(i)).getAttribute("isInterrupting").contains("false")==false) {
										nStartSignalEventDefinition++;
									}
		
									if(StartEventInterruptingChildNodes.item(j).getNodeName().contains("conditionalEventDefinition")
											&& ((Element) nodesStartEventInterrupting.item(i)).getAttribute("isInterrupting").contains("false")==false) {
										nStartConditionalEventDefinition++;
									}	
		
									if(StartEventInterruptingChildNodes.item(j).getNodeName().contains("timerEventDefinition")
											&& ((Element) nodesStartEventInterrupting.item(i)).getAttribute("isInterrupting").contains("false")==false) {
										nStartTimerEventDefinition++;
									}
		
									if(StartEventInterruptingChildNodes.item(j).getNodeName().contains("messageEventDefinition")
											&& ((Element) nodesStartEventInterrupting.item(i)).getAttribute("isInterrupting").contains("false")==false) {
										nStartMessageEventDefinition++;
									}
								}
							}	
					}			 				
				}
			}
			//[TODO: START EVENTS SUB PROCESS INTERRUPTING AND NON INTERRUPTING ARE AVAILABLE ONLY INSIDE EVENT-SUBPROCESSES]
			// Start Events Sub Process Interrupting

			XPathExpression exprStartEventSubProcessInt = xpath.compile("//bpmn:subProcess[@triggeredByEvent='true']//bpmn:startEvent");
			Object resultStartEventSubProcessInt = exprStartEventSubProcessInt.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesStartEventSubProcessInt = (NodeList) resultStartEventSubProcessInt;
			doc.getDocumentElement().normalize();         

			for(int i=0; i<nodesStartEventSubProcessInt.getLength(); i++) {

				Node StartEventNodeSubProcessInt = nodesStartEventSubProcessInt.item(i);   

				if(((Element) nodesStartEventSubProcessInt.item(i)).getAttribute("parallelMultiple").contains("true") &&
						((Element) nodesStartEventSubProcessInt.item(i)).getAttribute("isInterrupting").contains("false")==false) {
					nStartMultipleParallelEventSubProcessInterruptingDefinition++;
				}      	
				else {
					NodeList StartEventSubProcessIntChildNodes = StartEventNodeSubProcessInt.getChildNodes();

					int NumberOfChildsOfEachStartEventSubProcessInt=0;
					
					for (int z = 0; z < StartEventSubProcessIntChildNodes.getLength(); z++) {
						
						if (StartEventSubProcessIntChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
				        		StartEventSubProcessIntChildNodes.item(z).getNodeName().contains("messageEventDefinition")) {
				        	NumberOfChildsOfEachStartEventSubProcessInt++;
				        }
						
						if (StartEventSubProcessIntChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
				        		StartEventSubProcessIntChildNodes.item(z).getNodeName().contains("timerEventDefinition")) {
				        	NumberOfChildsOfEachStartEventSubProcessInt++;
				        }
						
						
						if (StartEventSubProcessIntChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
				        		StartEventSubProcessIntChildNodes.item(z).getNodeName().contains("escalationEventDefinition")) {
				        	NumberOfChildsOfEachStartEventSubProcessInt++;				        	
				        }
						
				        
				        if (StartEventSubProcessIntChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
				        		StartEventSubProcessIntChildNodes.item(z).getNodeName().contains("conditionalEventDefinition")) {
				        	NumberOfChildsOfEachStartEventSubProcessInt++;
				        }
				        
				        if (StartEventSubProcessIntChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
				        		StartEventSubProcessIntChildNodes.item(z).getNodeName().contains("errorEventDefinition")) {
					        	NumberOfChildsOfEachStartEventSubProcessInt++;
					        }
				        
				        if (StartEventSubProcessIntChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
				        		StartEventSubProcessIntChildNodes.item(z).getNodeName().contains("cancelEventDefinition")) {
					        	NumberOfChildsOfEachStartEventSubProcessInt++;
					        }	
				        
				        if (StartEventSubProcessIntChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
				        		StartEventSubProcessIntChildNodes.item(z).getNodeName().contains("compensateEventDefinition")) {
				        	NumberOfChildsOfEachStartEventSubProcessInt++;
				        }
				        
				        if (StartEventSubProcessIntChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
				        		StartEventSubProcessIntChildNodes.item(z).getNodeName().contains("signalEventDefinition")) {
				        	NumberOfChildsOfEachStartEventSubProcessInt++;
				        }
				        
				        if (StartEventSubProcessIntChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
				        		StartEventSubProcessIntChildNodes.item(z).getNodeName().contains("terminateEventDefinition")) {
					        	NumberOfChildsOfEachStartEventSubProcessInt++;
					        }   
				     
				    }	
					
					if(NumberOfChildsOfEachStartEventSubProcessInt > 1 && ((Element) nodesStartEventSubProcessInt.item(i)).getAttribute("parallelMultiple").contains("true")==false
							&& ((Element) nodesStartEventSubProcessInt.item(i)).getAttribute("isInterrupting").contains("false")==false) {
						nStartMultipleEventSubProcessInterruptingDefinition++;
						
					}
					else {
						for(int j=0;j<StartEventSubProcessIntChildNodes.getLength(); j++) {
	
	
							if(StartEventSubProcessIntChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
		
								if(StartEventSubProcessIntChildNodes.item(j).getNodeName().contains("signalEventDefinition")&&
										((Element) nodesStartEventSubProcessInt.item(i)).getAttribute("isInterrupting").contains("false") == false) {
									nStartSignalEventSubProcessInterruptingDefinition++;
								}
	
								if(StartEventSubProcessIntChildNodes.item(j).getNodeName().contains("conditionalEventDefinition")&&
										((Element) nodesStartEventSubProcessInt.item(i)).getAttribute("isInterrupting").contains("false") == false) {
									nStartConditionalEventSubProcessInterruptingDefinition++;
								}	
	
								if(StartEventSubProcessIntChildNodes.item(j).getNodeName().contains("timerEventDefinition")&&
										((Element) nodesStartEventSubProcessInt.item(i)).getAttribute("isInterrupting").contains("false") == false) {
									nStartTimerEventSubProcessInterruptingDefinition++;
								}
	
								if(StartEventSubProcessIntChildNodes.item(j).getNodeName().contains("messageEventDefinition")&&
										((Element) nodesStartEventSubProcessInt.item(i)).getAttribute("isInterrupting").contains("false") == false) {
									nStartMessageEventSubProcessInterruptingDefinition++;
								}
								
	
								if(StartEventSubProcessIntChildNodes.item(j).getNodeName().contains("compensateEventDefinition")&&
										((Element) nodesStartEventSubProcessInt.item(i)).getAttribute("isInterrupting").contains("false") == false) {
									nStartCompensateEventSubProcessInterruptingDefinition++;
								}	                  		
	
								if(StartEventSubProcessIntChildNodes.item(j).getNodeName().contains("escalationEventDefinition")&&
										((Element) nodesStartEventSubProcessInt.item(i)).getAttribute("isInterrupting").contains("false") == false) {
									nStartEscalationEventSubProcessInterruptingDefinition++;
								}
	
								if(StartEventSubProcessIntChildNodes.item(j).getNodeName().contains("errorEventDefinition")&&
										((Element) nodesStartEventSubProcessInt.item(i)).getAttribute("isInterrupting").contains("false") == false) {
									nStartErrorEventSubProcessInterruptingDefinition++;
								}	
							}
						}
					}
				}
			}

			// Start Events Sub Process NON-Interrupting

			XPathExpression exprStartEventSubProcessNonInt = xpath.compile("//bpmn:startEvent[@isInterrupting='false']");
			Object resultStartEventSubProcessNonInt = exprStartEventSubProcessNonInt.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesStartEventSubProcessNonInt = (NodeList) resultStartEventSubProcessNonInt;        

			for(int i=0; i<nodesStartEventSubProcessNonInt.getLength(); i++) {

				Node StartEventNodeSubProcessNonInt = nodesStartEventSubProcessNonInt.item(i);   

				if(((Element) nodesStartEventSubProcessNonInt.item(i)).getAttribute("parallelMultiple").contains("true")
						&& ((Element) nodesStartEventSubProcessNonInt.item(i)).getAttribute("isInterrupting").contains("false")) {
					nStartMultipleParallelEventSubProcessNonInterruptingDefinition++;
				}      	              
				else {
					NodeList StartEventSubProcessNonIntChildNodes = StartEventNodeSubProcessNonInt.getChildNodes();
					
					int NumberOfChildsOfEachStartEventSubProcessNonInt=0;
					
					for (int z = 0; z < StartEventSubProcessNonIntChildNodes.getLength(); z++) {
						
						if (StartEventSubProcessNonIntChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
					        	StartEventSubProcessNonIntChildNodes.item(z).getNodeName().contains("messageEventDefinition")) {
					        	NumberOfChildsOfEachStartEventSubProcessNonInt++;
					        }
						
						if (StartEventSubProcessNonIntChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
					        	StartEventSubProcessNonIntChildNodes.item(z).getNodeName().contains("timerEventDefinition")) {
					        	NumberOfChildsOfEachStartEventSubProcessNonInt++;
					        }
						
						if (StartEventSubProcessNonIntChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
					        	StartEventSubProcessNonIntChildNodes.item(z).getNodeName().contains("escalationEventDefinition")) {
					        	NumberOfChildsOfEachStartEventSubProcessNonInt++;				        	
					        }
						
						
						if (StartEventSubProcessNonIntChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
					        	StartEventSubProcessNonIntChildNodes.item(z).getNodeName().contains("conditionalEventDefinition")) {
					        	NumberOfChildsOfEachStartEventSubProcessNonInt++;
					        }
						
						if (StartEventSubProcessNonIntChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
					        	StartEventSubProcessNonIntChildNodes.item(z).getNodeName().contains("errorEventDefinition")) {
					        	NumberOfChildsOfEachStartEventSubProcessNonInt++;
					        }
						
						if (StartEventSubProcessNonIntChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
					        	StartEventSubProcessNonIntChildNodes.item(z).getNodeName().contains("cancelEventDefinition")) {
					        	NumberOfChildsOfEachStartEventSubProcessNonInt++;
					        }
						
						if (StartEventSubProcessNonIntChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
					        	StartEventSubProcessNonIntChildNodes.item(z).getNodeName().contains("compensateEventDefinition")) {
					        	NumberOfChildsOfEachStartEventSubProcessNonInt++;
					        }
						
				        if (StartEventSubProcessNonIntChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
				        	StartEventSubProcessNonIntChildNodes.item(z).getNodeName().contains("signalEventDefinition")) {
				        	NumberOfChildsOfEachStartEventSubProcessNonInt++;
				        }
				         
				        if (StartEventSubProcessNonIntChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
					        	StartEventSubProcessNonIntChildNodes.item(z).getNodeName().contains("terminateEventDefinition")) {
					        	NumberOfChildsOfEachStartEventSubProcessNonInt++;
					        }
				        				      
				    }
					
					if(NumberOfChildsOfEachStartEventSubProcessNonInt > 1 && ((Element) nodesStartEventSubProcessNonInt.item(i)).getAttribute("parallelMultiple").contains("true")==false
							&& ((Element) nodesStartEventSubProcessNonInt.item(i)).getAttribute("isInterrupting").contains("false"))
						nStartMultipleEventSubProcessNonInterruptingDefinition++;
					
					else {
						for(int j=0;j<StartEventSubProcessNonIntChildNodes.getLength(); j++) {
	
							if(StartEventSubProcessNonIntChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {

								if(StartEventSubProcessNonIntChildNodes.item(j).getNodeName().contains("signalEventDefinition") &&
										((Element) nodesStartEventSubProcessNonInt.item(i)).getAttribute("isInterrupting").contains("false")) {
									nStartSignalEventSubProcessNonInterruptingDefinition++;
								}
	
								if(StartEventSubProcessNonIntChildNodes.item(j).getNodeName().contains("conditionalEventDefinition") &&
										((Element) nodesStartEventSubProcessNonInt.item(i)).getAttribute("isInterrupting").contains("false")) {
									nStartConditionalEventSubProcessNonInterruptingDefinition++;
								}	
	
								if(StartEventSubProcessNonIntChildNodes.item(j).getNodeName().contains("timerEventDefinition") &&
										((Element) nodesStartEventSubProcessNonInt.item(i)).getAttribute("isInterrupting").contains("false") ) {
									nStartTimerEventSubProcessNonInterruptingDefinition++;
								}
	
								if(StartEventSubProcessNonIntChildNodes.item(j).getNodeName().contains("messageEventDefinition")&&
										((Element) nodesStartEventSubProcessNonInt.item(i)).getAttribute("isInterrupting").contains("false")) {
									nStartMessageEventSubProcessNonInterruptingDefinition++;
								}                  		
	
								if(StartEventSubProcessNonIntChildNodes.item(j).getNodeName().contains("escalationEventDefinition")&&
										((Element) nodesStartEventSubProcessNonInt.item(i)).getAttribute("isInterrupting").contains("false")) {
									nStartEscalationEventSubProcessNonInterruptingDefinition++;
								}
							}
						}
					}
				}
			}
			
			nStartMultipleEventDefinition = nStartMultipleEventDefinition - nStartMultipleParallelEventSubProcessInterruptingDefinition;
			nStartMultipleParallelEventDefinition = nStartMultipleParallelEventDefinition - nStartMultipleEventSubProcessInterruptingDefinition;
			nStartSignalEventDefinition = nStartSignalEventDefinition - nStartSignalEventSubProcessInterruptingDefinition;
			nStartConditionalEventDefinition = nStartConditionalEventDefinition - nStartConditionalEventSubProcessInterruptingDefinition;
			nStartTimerEventDefinition = nStartTimerEventDefinition - nStartTimerEventSubProcessInterruptingDefinition;
			nStartMessageEventDefinition = nStartMessageEventDefinition - nStartMessageEventSubProcessInterruptingDefinition;						
			
			//[TODO: END EVENTS]
			// End Events

			XPathExpression exprEndEvent = xpath.compile("//bpmn:endEvent");
			Object resultEndEvent = exprEndEvent.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesEndEvent = (NodeList) resultEndEvent;
			doc.getDocumentElement().normalize();         

			for(int i=0; i<nodesEndEvent.getLength(); i++) {

				Node EndEventNode = nodesEndEvent.item(i);   

				NodeList EndEventChildNodes = EndEventNode.getChildNodes();
				
				int NumberOfChildsOfEachEndEvent=0;
				
				for (int z = 0; z < EndEventChildNodes.getLength(); z++) {
					
			        if (EndEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
			        		EndEventChildNodes.item(z).getNodeName().contains("signalEventDefinition")) {
			        	NumberOfChildsOfEachEndEvent++;
			        }
			        
			        if (EndEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							EndEventChildNodes.item(z).getNodeName().contains("messageEventDefinition")) {
			        	NumberOfChildsOfEachEndEvent++;
			        }
			        
			        if (EndEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							EndEventChildNodes.item(z).getNodeName().contains("compensateEventDefinition")) {	
			        	NumberOfChildsOfEachEndEvent++;
			        }
			        
			        if (EndEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							EndEventChildNodes.item(z).getNodeName().contains("escalationEventDefinition")) {
			        	NumberOfChildsOfEachEndEvent++;
			        	
			        }
			        if (EndEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							EndEventChildNodes.item(z).getNodeName().contains("errorEventDefinition")) {
			        	NumberOfChildsOfEachEndEvent++;
			        	
			        }
			        if (EndEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							EndEventChildNodes.item(z).getNodeName().contains("terminateEventDefinition")) {
			        	NumberOfChildsOfEachEndEvent++;
			        	
			        }
			        if (EndEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
						EndEventChildNodes.item(z).getNodeName().contains("cancelEventDefinition")) {
			        	NumberOfChildsOfEachEndEvent++;
			        }
			    
				}
				if(NumberOfChildsOfEachEndEvent==0)
					nEndNoneEventDefinition++;
													
				if(NumberOfChildsOfEachEndEvent > 1) {
					nEndMultipleEventDefinition++;
					
				}		
				else {
					for(int j=0;j<EndEventChildNodes.getLength(); j++) {

							if(EndEventChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
															
									
								if(EndEventChildNodes.item(j).getNodeName().contains("signalEventDefinition")) {
									nEndSignalEventDefinition++;
								}
	
								if(EndEventChildNodes.item(j).getNodeName().contains("messageEventDefinition")) {
									nEndMessageEventDefinition++;
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
								}
	
								if(EndEventChildNodes.item(j).getNodeName().contains("cancelEventDefinition")) {
									nEndCancelEventDefinition++;	                  			
									
								}

						}
					}    
				}
			}

			//[TODO: INTERMEDIATE CATCH EVENTS]
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
				else {
					
					int NumberOfChildsOfEachIntermediateCatchtEvent=0;
					NodeList IntEventChildNodes = IntEventNode.getChildNodes();
					
					for (int z = 0; z < IntEventChildNodes.getLength(); z++) {
						
						if (IntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
								IntEventChildNodes.item(z).getNodeName().contains("messageEventDefinition")) {
							NumberOfChildsOfEachIntermediateCatchtEvent++;
					        }
						
						if (IntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
								IntEventChildNodes.item(z).getNodeName().contains("timerEventDefinition")) {
							NumberOfChildsOfEachIntermediateCatchtEvent++;
					        }
						
						if (IntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
								IntEventChildNodes.item(z).getNodeName().contains("escalationEventDefinition")) {
							NumberOfChildsOfEachIntermediateCatchtEvent++;				        	
					        }					
						
						if (IntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
				        		IntEventChildNodes.item(z).getNodeName().contains("linkEventDefinition")) {
				        	NumberOfChildsOfEachIntermediateCatchtEvent++;
					        }
						
						if (IntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
								IntEventChildNodes.item(z).getNodeName().contains("conditionalEventDefinition")) {
							NumberOfChildsOfEachIntermediateCatchtEvent++;
					        }
						
						if (IntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
								IntEventChildNodes.item(z).getNodeName().contains("errorEventDefinition")) {
							NumberOfChildsOfEachIntermediateCatchtEvent++;
					        }
						
						if (IntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
								IntEventChildNodes.item(z).getNodeName().contains("cancelEventDefinition")) {
							NumberOfChildsOfEachIntermediateCatchtEvent++;
					        }
						
						if (IntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
								IntEventChildNodes.item(z).getNodeName().contains("compensateEventDefinition")) {
							NumberOfChildsOfEachIntermediateCatchtEvent++;
					        }
						
				        if (IntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
				        		IntEventChildNodes.item(z).getNodeName().contains("signalEventDefinition")) {
				        	NumberOfChildsOfEachIntermediateCatchtEvent++;
				        }
				         
				        if (IntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
				        		IntEventChildNodes.item(z).getNodeName().contains("terminateEventDefinition")) {
				        	NumberOfChildsOfEachIntermediateCatchtEvent++;
					        }
					}  
				        if(NumberOfChildsOfEachIntermediateCatchtEvent > 1 && ((Element) nodesIntEvent.item(i)).getAttribute("parallelMultiple").contains("true") == false ) {
							nIntermediateCatchMultipleEventDefinition++;
						}
				        
				        else {

					for(int j=0;j<IntEventChildNodes.getLength(); j++) {

						if(IntEventChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {

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
			}

			//[TODO: INTERMEDIATE THROW EVENTS]
			// Intermediate Throw Events

			XPathExpression exprThrEvent = xpath.compile("//bpmn:intermediateThrowEvent");
			Object resultThrEvent = exprThrEvent.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesThrEvent = (NodeList) resultThrEvent;
			doc.getDocumentElement().normalize();         

			for(int i=0; i<nodesThrEvent.getLength(); i++) {

				Node ThrEventNode = nodesThrEvent.item(i);  
				
				NodeList ThrEventChildNodes = ThrEventNode.getChildNodes();
				
				int NumberOfChildsOfEachIntermediateThrowEvent=0;
				
				for (int z = 0; z < ThrEventChildNodes.getLength(); z++) {

					if (ThrEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							ThrEventChildNodes.item(z).getNodeName().contains("messageEventDefinition")) {
						NumberOfChildsOfEachIntermediateThrowEvent++;
				        }
					
					if (ThrEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							ThrEventChildNodes.item(z).getNodeName().contains("timerEventDefinition")) {
						NumberOfChildsOfEachIntermediateThrowEvent++;
				        }
					
					if (ThrEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							ThrEventChildNodes.item(z).getNodeName().contains("escalationEventDefinition")) {
						NumberOfChildsOfEachIntermediateThrowEvent++;				        	
				        }					
					
					if (ThrEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
			        		ThrEventChildNodes.item(z).getNodeName().contains("linkEventDefinition")) {
			        	NumberOfChildsOfEachIntermediateThrowEvent++;
				        }
					
					if (ThrEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							ThrEventChildNodes.item(z).getNodeName().contains("conditionalEventDefinition")) {
						NumberOfChildsOfEachIntermediateThrowEvent++;
				        }
					
					if (ThrEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							ThrEventChildNodes.item(z).getNodeName().contains("errorEventDefinition")) {
						NumberOfChildsOfEachIntermediateThrowEvent++;
				        }
					
					if (ThrEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							ThrEventChildNodes.item(z).getNodeName().contains("cancelEventDefinition")) {
						NumberOfChildsOfEachIntermediateThrowEvent++;
				        }
					
					if (ThrEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							ThrEventChildNodes.item(z).getNodeName().contains("compensateEventDefinition")) {
						NumberOfChildsOfEachIntermediateThrowEvent++;
				        }
					
			        if (ThrEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
			        		ThrEventChildNodes.item(z).getNodeName().contains("signalEventDefinition")) {
			        	NumberOfChildsOfEachIntermediateThrowEvent++;
			        }
			         
			        if (ThrEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
			        		ThrEventChildNodes.item(z).getNodeName().contains("terminateEventDefinition")) {
			        	NumberOfChildsOfEachIntermediateThrowEvent++;
				        }
				}
				
				if(NumberOfChildsOfEachIntermediateThrowEvent==0)
					nIntermediateThrowNoneEventDefinition++;
				
				if(NumberOfChildsOfEachIntermediateThrowEvent > 1 && ((Element) nodesThrEvent.item(i)).getAttribute("parallelMultiple").contains("true") == false ) {
					
		        	nIntermediateThrowMultipleEventDefinition++;
				}
			        else {
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
	
							}
						}
			        }
				}

			// Intermediate Catch Events - Boundary Interrupting

			XPathExpression exprBoundaryCatchIntEvent = xpath.compile("//bpmn:boundaryEvent[not(contains(@cancelActivity,'false'))]");
			Object resultBoundaryCatchIntEvent = exprBoundaryCatchIntEvent.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesBoundaryCatchIntEvent = (NodeList) resultBoundaryCatchIntEvent;
			doc.getDocumentElement().normalize();  

			for(int i=0; i<nodesBoundaryCatchIntEvent.getLength(); i++) {
				
				Node BoundaryCatchIntEventNode = nodesBoundaryCatchIntEvent.item(i);   
				
				 if(((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("parallelMultiple").contains("true") &&
							((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("cancelActivity").contains("false")==false ) {
						nIntermediateBoundaryMultipleParallelEvent++;
						
					}    

				NodeList CatchIntEventChildNodes = BoundaryCatchIntEventNode.getChildNodes();
				
				int NumberOfChildsOfEachBoundaryCatchEvent=0;
				
				for (int z = 0; z < CatchIntEventChildNodes.getLength(); z++) {
					
					if (CatchIntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							CatchIntEventChildNodes.item(z).getNodeName().contains("messageEventDefinition")) {
						NumberOfChildsOfEachBoundaryCatchEvent++;
				        }
					
					if (CatchIntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							CatchIntEventChildNodes.item(z).getNodeName().contains("timerEventDefinition")) {
						NumberOfChildsOfEachBoundaryCatchEvent++;
				        }
					
					if (CatchIntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							CatchIntEventChildNodes.item(z).getNodeName().contains("escalationEventDefinition")) {
						NumberOfChildsOfEachBoundaryCatchEvent++;				        	
				        }					
					
					if (CatchIntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							CatchIntEventChildNodes.item(z).getNodeName().contains("conditionalEventDefinition")) {
						NumberOfChildsOfEachBoundaryCatchEvent++;
				        }
					
					if (CatchIntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							CatchIntEventChildNodes.item(z).getNodeName().contains("errorEventDefinition")) {
						NumberOfChildsOfEachBoundaryCatchEvent++;
				        }
					
					if (CatchIntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							CatchIntEventChildNodes.item(z).getNodeName().contains("cancelEventDefinition")) {
						NumberOfChildsOfEachBoundaryCatchEvent++;
				        }
					
					if (CatchIntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							CatchIntEventChildNodes.item(z).getNodeName().contains("compensateEventDefinition")) {
						NumberOfChildsOfEachBoundaryCatchEvent++;
				        }
					
			        if (CatchIntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
			        		CatchIntEventChildNodes.item(z).getNodeName().contains("signalEventDefinition")) {
			        	NumberOfChildsOfEachBoundaryCatchEvent++;
			        }
			         
			        if (CatchIntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
			        		CatchIntEventChildNodes.item(z).getNodeName().contains("terminateEventDefinition")) {
			        	NumberOfChildsOfEachBoundaryCatchEvent++;
				        }
				}
			        if(NumberOfChildsOfEachBoundaryCatchEvent > 1 && ((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("parallelMultiple").contains("true") == false ) {
			        	nIntermediateBoundaryMultipleEvent++;
					}      

			        else { 
						for(int j=0;j<CatchIntEventChildNodes.getLength(); j++) {
	
							if(CatchIntEventChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
	
								if(CatchIntEventChildNodes.item(j).getNodeName().contains("messageEventDefinition") &&
										((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("cancelActivity").contains("false")==false ) {
									nIntermediateBoundaryMessageEvent++;
								}
	
								if(CatchIntEventChildNodes.item(j).getNodeName().contains("timerEventDefinition") &&
										((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("cancelActivity").contains("false")==false ) {
									nIntermediateBoundaryTimerEvent++;
								}
	
								if(CatchIntEventChildNodes.item(j).getNodeName().contains("escalationEventDefinition") &&
										((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("cancelActivity").contains("false")==false ) {
									nIntermediateBoundaryEscalationEvent++;
								}
	
								if(CatchIntEventChildNodes.item(j).getNodeName().contains("conditionalEventDefinition") &&
										((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("cancelActivity").contains("false")==false ) {
									nIntermediateBoundaryConditionalEvent++;
								}
	
								if(CatchIntEventChildNodes.item(j).getNodeName().contains("errorEventDefinition") &&
										((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("cancelActivity").contains("false")==false ) {
									nIntermediateBoundaryErrorEvent++;
								}
	
	
								if(CatchIntEventChildNodes.item(j).getNodeName().contains("cancelEventDefinition") &&
										((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("cancelActivity").contains("false")==false ) {
									nIntermediateBoundaryCancelEvent++;
								}	 
	
								if(CatchIntEventChildNodes.item(j).getNodeName().contains("compensateEventDefinition") &&
										((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("cancelActivity").contains("false")==false) {
									nIntermediateBoundaryCompensateEvent++;
								}	                  		
	
								if(CatchIntEventChildNodes.item(j).getNodeName().contains("signalEventDefinition") &&
										((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("cancelActivity").contains("false")==false) {
									nIntermediateBoundarySignalEvent++;
								}	   
							}
						}
					}
				
			}

			// Intermediate Catch Events - Boundary NON Interrupting

			XPathExpression exprCatchNonIntEvent = xpath.compile("//bpmn:boundaryEvent[@cancelActivity='false']");
			Object resultCatchNonIntEvent = exprCatchNonIntEvent.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesCatchNonIntEvent = (NodeList) resultCatchNonIntEvent;
			doc.getDocumentElement().normalize();         

			for(int i=0; i<nodesCatchNonIntEvent.getLength(); i++) {

				Node CatchNonIntEventNode = nodesCatchNonIntEvent.item(i);    
				 
				 if(((Element) nodesCatchNonIntEvent.item(i)).getAttribute("parallelMultiple").contains("true") &&
							((Element) nodesCatchNonIntEvent.item(i)).getAttribute("cancelActivity").contains("false")) {
						nIntermediateBoundaryMultipleParallelEventNonInterrupting++;				
					}

				NodeList CatchNonIntEventChildNodes = CatchNonIntEventNode.getChildNodes();
				
				int NumberOfChildsOfEachBoundaryCatchNonIntEvent=0;
				
				for (int z = 0; z < CatchNonIntEventChildNodes.getLength(); z++) {
					
					
					if (CatchNonIntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							CatchNonIntEventChildNodes.item(z).getNodeName().contains("messageEventDefinition")) {
						NumberOfChildsOfEachBoundaryCatchNonIntEvent++;
				        }
					
					if (CatchNonIntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							CatchNonIntEventChildNodes.item(z).getNodeName().contains("timerEventDefinition")) {
						NumberOfChildsOfEachBoundaryCatchNonIntEvent++;
				        }
					
					if (CatchNonIntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							CatchNonIntEventChildNodes.item(z).getNodeName().contains("escalationEventDefinition")) {
						NumberOfChildsOfEachBoundaryCatchNonIntEvent++;				        	
				        }					
					
					if (CatchNonIntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							CatchNonIntEventChildNodes.item(z).getNodeName().contains("conditionalEventDefinition")) {
						NumberOfChildsOfEachBoundaryCatchNonIntEvent++;
				        }
					
					if (CatchNonIntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							CatchNonIntEventChildNodes.item(z).getNodeName().contains("errorEventDefinition")) {
						NumberOfChildsOfEachBoundaryCatchNonIntEvent++;
				        }
					
					if (CatchNonIntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							CatchNonIntEventChildNodes.item(z).getNodeName().contains("cancelEventDefinition")) {
						NumberOfChildsOfEachBoundaryCatchNonIntEvent++;
				        }
					
					if (CatchNonIntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
							CatchNonIntEventChildNodes.item(z).getNodeName().contains("compensateEventDefinition")) {
						NumberOfChildsOfEachBoundaryCatchNonIntEvent++;
				        }
					
			        if (CatchNonIntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
			        		CatchNonIntEventChildNodes.item(z).getNodeName().contains("signalEventDefinition")) {
			        	NumberOfChildsOfEachBoundaryCatchNonIntEvent++;
			        }
			         
			        if (CatchNonIntEventChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE &&
			        		CatchNonIntEventChildNodes.item(z).getNodeName().contains("terminateEventDefinition")) {
			        	NumberOfChildsOfEachBoundaryCatchNonIntEvent++;
				        }
			        
			        
				}
				
		        if(NumberOfChildsOfEachBoundaryCatchNonIntEvent > 1 && ((Element) nodesCatchNonIntEvent.item(i)).getAttribute("parallelMultiple").contains("true") == false ) {
					
		        	nIntermediateBoundaryMultipleEventNonInterrupting++;
					
				}else {	

						for(int j=0;j<CatchNonIntEventChildNodes.getLength(); j++) {
	
							if(CatchNonIntEventChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
								
								if(CatchNonIntEventChildNodes.item(j).getNodeName().contains("messageEventDefinition")) {
									nIntermediateBoundaryMessageEventNonInterrupting++;
									
								}
	
								if(CatchNonIntEventChildNodes.item(j).getNodeName().contains("timerEventDefinition")) {
									nIntermediateBoundaryTimerEventNonInterrupting++;
								}
	
								if(CatchNonIntEventChildNodes.item(j).getNodeName().contains("escalationEventDefinition")) {
									nIntermediateBoundaryEscalationEventNonInterrupting++;
								}
	
								if(CatchNonIntEventChildNodes.item(j).getNodeName().contains("conditionalEventDefinition") ){
									nIntermediateBoundaryConditionalEventNonInterrupting++;
								}	                  		                  		
	
								if(CatchNonIntEventChildNodes.item(j).getNodeName().contains("signalEventDefinition")) {
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

			//[TODO: to add to the excel file]
			//message
			XPathExpression exprMsg = xpath.compile("//bpmn:message");
			Object resultMsg = exprMsg.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesMsg = (NodeList) resultMsg;
			doc.getDocumentElement().normalize();  
			nMessage = nodesMsg.getLength();

			
			//[TODO: CHOREOGRAPHY SUBPROCESS EXPANDED & COLLAPSED]
			//N° of Choreography SubProcess Expanded  & N° of Choreography SubProcess Collapsed  Example: <bpmndi:BPMNShape id="SubChoreography_0vzey3j_di" isExpanded='false'>

			XPathExpression exprChoSubprocesses = xpath.compile("//bpmn:subChoreography");
			Object resultChoSubprocesses  = exprChoSubprocesses .evaluate(doc, XPathConstants.NODESET);
			NodeList nodesChoSubprocesses = (NodeList) resultChoSubprocesses;
			doc.getDocumentElement().normalize();  

			XPathExpression exprChoSubprocessesShapes = xpathShape.compile("//bpmn:BPMNShape");
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

			//[TODO: CHOREOGRAPHY CALL]
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

			//[TODO: SWIMLANE]
			//[TODO: LANE]
			//N° of Lane 
			XPathExpression exprLane = xpath.compile("//bpmn:lane");
			Object resultLane = exprLane.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesLane = (NodeList) resultLane;
			doc.getDocumentElement().normalize();  
			nLane = nodesLane.getLength();

			//[TODO: POOL]
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
			XPathExpression exprPoolColM = xpath.compile("//bpmn:collaboration//bpmn:participant[not[@processRef]]//bpmn:participantMultiplicity");
			Object resultPoolColM = exprPoolColM.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesPoolColM = (NodeList) resultPoolColM;
			doc.getDocumentElement().normalize();  
			nPoolCollapsedMultiplicity = nodesPoolColM.getLength();

			// Pool Collapsed Multiplicity None
			XPathExpression exprPoolCol = xpath.compile("//bpmn:collaboration//bpmn:participant[not[@processRef]]");
			Object resultPoolCol= exprPoolCol.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesPoolCol = (NodeList) resultPoolCol;
			doc.getDocumentElement().normalize();  
			nPoolCollapsedMultiplicityNone = nodesPoolCol.getLength() - nPoolCollapsedMultiplicity;

			//[TODO: CONNECTING OBJECTS]
			//[TODO: FLOW]
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
			nSequenceFlow = nodesSFlow.getLength() - (nDefaultFlow + nConditionalFlow);
			
			//[TODO: ASSOCIATIONS]
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
			
			//Bidirectional Association
			XPathExpression exprBidirectionalAssoc = xpath.compile("//bpmn:association[@associationDirection='Both']");
			Object resultBidirectionalAssoc = exprBidirectionalAssoc.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesBidirectionalAssoc = (NodeList) resultBidirectionalAssoc;
			doc.getDocumentElement().normalize();  
			nAssociationBidirectional = nodesBidirectionalAssoc.getLength();

			//Unidirected Association
			XPathExpression exprUndirectedAssoc = xpath.compile("//bpmn:association");
			Object resultUndirectedAssoc = exprUndirectedAssoc.evaluate(doc, XPathConstants.NODESET);
			NodeList nodesUndirectedAssoc = (NodeList) resultUndirectedAssoc;
			doc.getDocumentElement().normalize();  
			nAssociationUndirected = nodesUndirectedAssoc.getLength() - (nAssociationCompensate + nAssociationUnidirectional + nAssociationBidirectional );


			//[TODO: CONVERSATION]
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

			//[TODO: CHOREOGRAPHY]

			if(modelType=="Choreography")
				nMessageFlow = 0;

			//Not Considered elements:
			// 1. ChoreographyParticipant (integrated into choreography task)
			// 2. ChoreographyParticipantMultiplicity (integrated into choreography task)
			TotalElements = nTaskNoneLoopNoneCompensateNoneCallNone+
					nTaskNoneLoopNoneCompensateNoneCall+
					nTaskNoneLoopNoneCompensateCallNone+
					nTaskNoneLoopNoneCompensateCall+
					nTaskNoneLoopStandardCompensateNoneCallNone+
					nTaskNoneLoopStandardCompensateNoneCall+
					nTaskNoneLoopStandardCompensateCallNone+
					nTaskNoneLoopStandardCompensateCall+
					nTaskNoneLoopMIParallelCompensateNoneCallNone+
					nTaskNoneLoopMIParallelCompensateNoneCall+
					nTaskNoneLoopMIParallelCompensateCallNone+
					nTaskNoneLoopMIParallelCompensateCall+
					nTaskNoneLoopMISequentialCompensateNoneCallNone+
					nTaskNoneLoopMISequentialCompensateNoneCall+
					nTaskNoneLoopMISequentialCompensateCallNone+
					nTaskNoneLoopMISequentialCompensateCall+
					nTaskSendLoopNoneCompensateNone+
					nTaskSendLoopNoneCompensate+
					nTaskSendLoopStandardCompensateNone+           
					nTaskSendLoopStandardCompensate+ 
					nTaskSendLoopMIParallelCompensateNone+ 
					nTaskSendLoopMIParallelCompensate+
					nTaskSendLoopMISequentialCompensateNone+ 
					nTaskSendLoopMISequentialCompensate+
					nTaskReceiveLoopNoneCompensateNone+            
					nTaskReceiveLoopNoneCompensate+           
					nTaskReceiveLoopStandardCompensateNone+            
					nTaskReceiveLoopStandardCompensate+            
					nTaskReceiveLoopMIParallelCompensateNone+                        
					nTaskReceiveLoopMIParallelCompensate+            
					nTaskReceiveLoopMISequentialCompensateNone+           
					nTaskReceiveLoopMISequentialCompensate+            
					nTaskUserLoopNoneCompensateNone+            
					nTaskUserLoopNoneCompensate+           
					nTaskUserLoopStandardCompensateNone+            
					nTaskUserLoopStandardCompensate+           
					nTaskUserLoopMIParallelCompensateNone+            
					nTaskUserLoopMIParallelCompensate+            
					nTaskUserLoopMISequentialCompensateNone+            
					nTaskUserLoopMISequentialCompensate+            
					nTaskManualLoopNoneCompensateNone+            
					nTaskManualLoopNoneCompensate+            
					nTaskManualLoopStandardCompensateNone+            
					nTaskManualLoopStandardCompensate+            
					nTaskManualLoopMIParallelCompensateNone+            
					nTaskManualLoopMIParallelCompensate+            
					nTaskManualLoopMISequentialCompensateNone+            
					nTaskManualLoopMISequentialCompensate+            
					nTaskBusinessRuleLoopNoneCompensateNone+            
					nTaskBusinessRuleLoopNoneCompensate+            
					nTaskBusinessRuleLoopStandardCompensateNone+            
					nTaskBusinessRuleLoopStandardCompensate+            
					nTaskBusinessRuleLoopMIParallelCompensateNone+           
					nTaskBusinessRuleLoopMIParallelCompensate+            
					nTaskBusinessRuleLoopMISequentialCompensateNone+           
					nTaskBusinessRuleLoopMISequentialCompensate+            
					nTaskServiceLoopNoneCompensateNone+            
					nTaskServiceLoopNoneCompensate+            
					nTaskServiceLoopStandardCompensateNone+            
					nTaskServiceLoopStandardCompensate+            
					nTaskServiceLoopMIParallelCompensateNone+            
					nTaskServiceLoopMIParallelCompensate+            
					nTaskServiceLoopMISequentialCompensateNone+            
					nTaskServiceLoopMISequentialCompensate+            
					nTaskScriptLoopNoneCompensateNone+            
					nTaskScriptLoopNoneCompensate+           
					nTaskScriptLoopStandardCompensateNone+            
					nTaskScriptLoopStandardCompensate+            
					nTaskScriptLoopMIParallelCompensateNone+            
					nTaskScriptLoopMIParallelCompensate+            
					nTaskScriptLoopMISequentialCompensateNone+            
					nTaskScriptLoopMISequentialCompensate+            
					nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone+
					nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate+
					nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone+
					nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate+
					nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone+
					nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate+
					nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone+
					nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate+
					nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone+
					nSubProcessExtendedEventNoneAdHocLoopNoneCompensate+
					nSubProcessExtendedEventNoneAdHocLoopStandardCompensateNone+
					nSubProcessExtendedEventNoneAdHocLoopStandardCompensate+
					nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone+
					nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate+
					nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone+
					nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate+
					nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone+
					nSubProcessExtendedEventNoneTransactionLoopNoneCompensate+
					nSubProcessExtendedEventNoneTransactionLoopStandardCompensateNone+
					nSubProcessExtendedEventNoneTransactionLoopStandardCompensate+
					nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone+
					nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate+
					nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone+
					nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate+
					nSubProcessExtendedEventLoopNoneCompensateNone+
					nSubProcessExtendedEventLoopNoneCompensate+
					nSubProcessExtendedEventLoopStandardCompensateNone+
					nSubProcessExtendedEventLoopStandardCompensate+
					nSubProcessExtendedEventLoopMIParallelCompensateNone+
					nSubProcessExtendedEventLoopMIParallelCompensate+
					nSubProcessExtendedEventLoopMISequentialCompensateNone+
					nSubProcessExtendedEventLoopMISequentialCompensate+
					nSubProcessExtendedEventAdHocLoopNoneCompensateNone+
					nSubProcessExtendedEventAdHocLoopNoneCompensate+
					nSubProcessExtendedEventAdHocLoopStandardCompensateNone+
					nSubProcessExtendedEventAdHocLoopStandardCompensate+
					nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone+
					nSubProcessExtendedEventAdHocLoopMIParallelCompensate+
					nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone+
					nSubProcessExtendedEventAdHocLoopMISequentialCompensate+
					nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone+
					nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate+
					nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone+
					nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate+
					nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone+
					nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate+
					nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone+
					nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate+
					nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone+
					nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate+
					nSubProcessCollapsedEventNoneAdHocLoopStandardCompensateNone+
					nSubProcessCollapsedEventNoneAdHocLoopStandardCompensate+
					nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone+
					nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate+
					nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone+
					nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate+
					nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone+
					nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate+
					nSubProcessCollapsedEventNoneTransactionLoopStandardCompensateNone+
					nSubProcessCollapsedEventNoneTransactionLoopStandardCompensate+
					nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensateNone+
					nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensate+
					nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensateNone+
					nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensate+
					nSubProcessCollapsedEventLoopNoneCompensateNone+
					nSubProcessCollapsedEventLoopNoneCompensate+
					nSubProcessCollapsedEventLoopStandardCompensateNone+
					nSubProcessCollapsedEventLoopStandardCompensate+
					nSubProcessCollapsedEventLoopMIParallelCompensateNone+
					nSubProcessCollapsedEventLoopMIParallelCompensate+
					nSubProcessCollapsedEventLoopMISequentialCompensateNone+
					nSubProcessCollapsedEventLoopMISequentialCompensate+
					nSubProcessCollapsedEventLoopNoneCompensateNone+
					nSubProcessCollapsedEventAdHocLoopNoneCompensate+
					nSubProcessCollapsedEventAdHocLoopStandardCompensateNone+
					nSubProcessCollapsedEventAdHocLoopStandardCompensate+
					nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone+
					nSubProcessCollapsedEventAdHocLoopMIParallelCompensate+
					nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone+
					nSubProcessCollapsedEventAdHocLoopMISequentialCompensate+
					nDataObject+
					nDataObjectCollection+
					nDataObjectReference+
					nDataStore+
					nDataInput+
					nDataOutput+
					nExclusiveGatewayNoMarker+
					nExclusiveGatewayMarker+
					nParallelGateway+
					nInclusiveGateway+
					nEventBasedGateway+
					nEventBasedGatewayExclusiveInstantiation+
					nEventBasedGatewayParallelInstantiation+
					nComplexGateway+
					nStartMultipleParallelEventDefinition+
					nStartMultipleEventDefinition+
					nStartNoneEventDefinition+
					nStartSignalEventDefinition+
					nStartConditionalEventDefinition+
					nStartTimerEventDefinition+
					nStartMessageEventDefinition+
					nStartMessageEventSubProcessInterruptingDefinition+
					nStartTimerEventSubProcessInterruptingDefinition+
					nStartEscalationEventSubProcessInterruptingDefinition+
					nStartConditionalEventSubProcessInterruptingDefinition+
					nStartErrorEventSubProcessInterruptingDefinition+
					nStartCompensateEventSubProcessInterruptingDefinition+
					nStartSignalEventSubProcessInterruptingDefinition+
					nStartMultipleEventSubProcessInterruptingDefinition+
					nStartMultipleParallelEventSubProcessInterruptingDefinition+       
					nStartMessageEventSubProcessNonInterruptingDefinition+
					nStartTimerEventSubProcessNonInterruptingDefinition+
					nStartEscalationEventSubProcessNonInterruptingDefinition+
					nStartConditionalEventSubProcessNonInterruptingDefinition+
					nStartSignalEventSubProcessNonInterruptingDefinition+
					nStartMultipleParallelEventSubProcessNonInterruptingDefinition+
					nStartMultipleEventSubProcessNonInterruptingDefinition+       
					nEndNoneEventDefinition+
					nEndMultipleEventDefinition+ 
					nEndEscalationEventDefinition+
					nEndErrorEventDefinition+
					nEndSignalEventDefinition+
					nEndCompensateEventDefinition+
					nEndCancelEventDefinition+ 
					nEndMessageEventDefinition+
					nEndTerminateEventDefinition+
					nIntermediateCatchMultipleEventDefinition+
					nIntermediateCatchMultipleParallelEventDefinition+
					nIntermediateCatchMessageEventDefinition+
					nIntermediateCatchTimerEventDefinition+
					nIntermediateCatchConditionalEventDefinition+
					nIntermediateCatchLinkEventDefinition+
					nIntermediateCatchSignalEventDefinition+
					nIntermediateThrowNoneEventDefinition+
					nIntermediateThrowMessageEventDefinition+
					nIntermediateThrowEscalationEventDefinition+
					nIntermediateThrowLinkEventDefinition+
					nIntermediateThrowSignalEventDefinition+
					nIntermediateThrowCompensateEventDefinition+
					nIntermediateThrowMultipleEventDefinition+
					nIntermediateBoundaryMessageEvent+
					nIntermediateBoundaryTimerEvent+
					nIntermediateBoundaryCancelEvent+
					nIntermediateBoundaryConditionalEvent+
					nIntermediateBoundaryEscalationEvent+
					nIntermediateBoundaryErrorEvent+
					nIntermediateBoundarySignalEvent+
					nIntermediateBoundaryCompensateEvent+
					nIntermediateBoundaryMultipleEvent+
					nIntermediateBoundaryMultipleParallelEvent+
					nIntermediateBoundaryTimerEventNonInterrupting+
					nIntermediateBoundaryEscalationEventNonInterrupting+
					nIntermediateBoundaryConditionalEventNonInterrupting+
					nIntermediateBoundaryMessageEventNonInterrupting+
					nIntermediateBoundarySignalEventNonInterrupting+
					nIntermediateBoundaryMultipleEventNonInterrupting+
					nIntermediateBoundaryMultipleParallelEventNonInterrupting+
					nMessageFlow+
					nSequenceFlow+
					nDefaultFlow+
					nConditionalFlow+
					nLane+ 
					nPoolCollapsedMultiplicityNone+
					nPoolCollapsedMultiplicity+
					nPoolExpandedMultiplicityNone+
					nPoolExpandedMultiplicity+
					nChoreographyTask+
					nChoreographyMessage+            
					nChoreographyTaskSequentialMultipleInstance+
					nChoreographyTaskParallelMultipleInstance+
					nChoreographyTaskLoop+
					nChoreographySubprocessCollapsed+
					nChoreographySubprocessCollapsedParallelMultipleInstance+
					nChoreographySubprocessCollapsedSequentialMultipleInstance+
					nChoreographySubprocessCollapsedLoop+
					nChoreographySubprocessCollapsedCall+
					nChoreographySubprocessCollapsedCallSequentialMultipleInstance+
					nChoreographySubprocessCollapsedCallParallelMultipleInstance+
					nChoreographySubprocessCollapsedCallLoop+
					nChoreographySubprocessExpanded+
					nChoreographySubprocessExpandedSequentialMultipleInstance+
					nChoreographySubprocessExpandedParallelMultipleInstance+
					nChoreographySubprocessExpandedLoop+      
					nConversationNone+
					nConversationSubProcess+
					nConversationCall+
					nConversationSubProcessCall+
					nConversationLink+
					nAssociationCompensate+
					nAssociationUndirected+
					nAssociationUnidirectional+        
					nAssociationBidirectional+
					nAssociationDataOutput+
					nAssociationDataInput+            
					nGroup+
					nTextAnnotation;

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
			row.createCell(0).setCellValue(fileName);
			row.createCell(1).setCellValue(bpmnModeler);
			row.createCell(2).setCellValue(modelType);
			row.createCell(3).setCellValue(isEnglish);
			row.createCell(4).setCellValue(nTaskNoneLoopNoneCompensateNoneCallNone);
			row.createCell(5).setCellValue(nTaskNoneLoopNoneCompensateNoneCall);
			row.createCell(6).setCellValue(nTaskNoneLoopNoneCompensateCallNone);
			row.createCell(7).setCellValue(nTaskNoneLoopNoneCompensateCall);
			row.createCell(8).setCellValue(nTaskNoneLoopStandardCompensateNoneCallNone);
			row.createCell(9).setCellValue(nTaskNoneLoopStandardCompensateNoneCall);
			row.createCell(10).setCellValue(nTaskNoneLoopStandardCompensateCallNone);
			row.createCell(11).setCellValue(nTaskNoneLoopStandardCompensateCall);
			row.createCell(12).setCellValue(nTaskNoneLoopMIParallelCompensateNoneCallNone);
			row.createCell(13).setCellValue(nTaskNoneLoopMIParallelCompensateNoneCall);
			row.createCell(14).setCellValue(nTaskNoneLoopMIParallelCompensateCallNone);
			row.createCell(15).setCellValue(nTaskNoneLoopMIParallelCompensateCall);
			row.createCell(16).setCellValue(nTaskNoneLoopMISequentialCompensateNoneCallNone);
			row.createCell(17).setCellValue(nTaskNoneLoopMISequentialCompensateNoneCall);
			row.createCell(18).setCellValue(nTaskNoneLoopMISequentialCompensateCallNone);
			row.createCell(19).setCellValue(nTaskNoneLoopMISequentialCompensateCall);
			row.createCell(20).setCellValue(nTaskSendLoopNoneCompensateNone);
			row.createCell(21).setCellValue(nTaskSendLoopNoneCompensate);
			row.createCell(22).setCellValue(nTaskSendLoopStandardCompensateNone);           
			row.createCell(23).setCellValue(nTaskSendLoopStandardCompensate); 
			row.createCell(24).setCellValue(nTaskSendLoopMIParallelCompensateNone); 
			row.createCell(25).setCellValue(nTaskSendLoopMIParallelCompensate);
			row.createCell(26).setCellValue(nTaskSendLoopMISequentialCompensateNone); 
			row.createCell(27).setCellValue(nTaskSendLoopMISequentialCompensate);
			row.createCell(28).setCellValue(nTaskReceiveLoopNoneCompensateNone);            
			row.createCell(29).setCellValue(nTaskReceiveLoopNoneCompensate);           
			row.createCell(30).setCellValue(nTaskReceiveLoopStandardCompensateNone);            
			row.createCell(31).setCellValue(nTaskReceiveLoopStandardCompensate);            
			row.createCell(32).setCellValue(nTaskReceiveLoopMIParallelCompensateNone);                        
			row.createCell(33).setCellValue(nTaskReceiveLoopMIParallelCompensate);            
			row.createCell(34).setCellValue(nTaskReceiveLoopMISequentialCompensateNone);           
			row.createCell(35).setCellValue(nTaskReceiveLoopMISequentialCompensate);            
			row.createCell(36).setCellValue(nTaskUserLoopNoneCompensateNone);            
			row.createCell(37).setCellValue(nTaskUserLoopNoneCompensate);           
			row.createCell(38).setCellValue(nTaskUserLoopStandardCompensateNone);            
			row.createCell(39).setCellValue(nTaskUserLoopStandardCompensate);           
			row.createCell(40).setCellValue(nTaskUserLoopMIParallelCompensateNone);            
			row.createCell(41).setCellValue(nTaskUserLoopMIParallelCompensate);            
			row.createCell(42).setCellValue(nTaskUserLoopMISequentialCompensateNone);            
			row.createCell(43).setCellValue(nTaskUserLoopMISequentialCompensate);            
			row.createCell(44).setCellValue(nTaskManualLoopNoneCompensateNone);            
			row.createCell(45).setCellValue(nTaskManualLoopNoneCompensate);            
			row.createCell(46).setCellValue(nTaskManualLoopStandardCompensateNone);            
			row.createCell(47).setCellValue(nTaskManualLoopStandardCompensate);            
			row.createCell(48).setCellValue(nTaskManualLoopMIParallelCompensateNone);            
			row.createCell(49).setCellValue(nTaskManualLoopMIParallelCompensate);            
			row.createCell(50).setCellValue(nTaskManualLoopMISequentialCompensateNone);            
			row.createCell(51).setCellValue(nTaskManualLoopMISequentialCompensate);            
			row.createCell(52).setCellValue(nTaskBusinessRuleLoopNoneCompensateNone);            
			row.createCell(53).setCellValue(nTaskBusinessRuleLoopNoneCompensate);            
			row.createCell(54).setCellValue(nTaskBusinessRuleLoopStandardCompensateNone);            
			row.createCell(55).setCellValue(nTaskBusinessRuleLoopStandardCompensate);            
			row.createCell(56).setCellValue(nTaskBusinessRuleLoopMIParallelCompensateNone);           
			row.createCell(57).setCellValue(nTaskBusinessRuleLoopMIParallelCompensate);            
			row.createCell(58).setCellValue(nTaskBusinessRuleLoopMISequentialCompensateNone);           
			row.createCell(59).setCellValue(nTaskBusinessRuleLoopMISequentialCompensate);            
			row.createCell(60).setCellValue(nTaskServiceLoopNoneCompensateNone);            
			row.createCell(61).setCellValue(nTaskServiceLoopNoneCompensate);            
			row.createCell(62).setCellValue(nTaskServiceLoopStandardCompensateNone);            
			row.createCell(63).setCellValue(nTaskServiceLoopStandardCompensate);            
			row.createCell(64).setCellValue(nTaskServiceLoopMIParallelCompensateNone);            
			row.createCell(65).setCellValue(nTaskServiceLoopMIParallelCompensate);            
			row.createCell(66).setCellValue(nTaskServiceLoopMISequentialCompensateNone);            
			row.createCell(67).setCellValue(nTaskServiceLoopMISequentialCompensate);            
			row.createCell(68).setCellValue(nTaskScriptLoopNoneCompensateNone);            
			row.createCell(69).setCellValue(nTaskScriptLoopNoneCompensate);           
			row.createCell(70).setCellValue(nTaskScriptLoopStandardCompensateNone);            
			row.createCell(71).setCellValue(nTaskScriptLoopStandardCompensate);            
			row.createCell(72).setCellValue(nTaskScriptLoopMIParallelCompensateNone);            
			row.createCell(73).setCellValue(nTaskScriptLoopMIParallelCompensate);            
			row.createCell(74).setCellValue(nTaskScriptLoopMISequentialCompensateNone);            
			row.createCell(75).setCellValue(nTaskScriptLoopMISequentialCompensate);            
			row.createCell(76).setCellValue(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone);
			row.createCell(77).setCellValue(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate);
			row.createCell(78).setCellValue(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone);
			row.createCell(79).setCellValue(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate);
			row.createCell(80).setCellValue(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone);
			row.createCell(81).setCellValue(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate);
			row.createCell(82).setCellValue(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone);
			row.createCell(83).setCellValue(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate);
			row.createCell(84).setCellValue(nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone);
			row.createCell(85).setCellValue(nSubProcessExtendedEventNoneAdHocLoopNoneCompensate);
			row.createCell(86).setCellValue(nSubProcessExtendedEventNoneAdHocLoopStandardCompensateNone);
			row.createCell(87).setCellValue(nSubProcessExtendedEventNoneAdHocLoopStandardCompensate);
			row.createCell(88).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone);
			row.createCell(89).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate);
			row.createCell(90).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone);
			row.createCell(91).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate);
			row.createCell(92).setCellValue(nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone);
			row.createCell(93).setCellValue(nSubProcessExtendedEventNoneTransactionLoopNoneCompensate);
			row.createCell(94).setCellValue(nSubProcessExtendedEventNoneTransactionLoopStandardCompensateNone);
			row.createCell(95).setCellValue(nSubProcessExtendedEventNoneTransactionLoopStandardCompensate);
			row.createCell(96).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone);
			row.createCell(97).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate);
			row.createCell(98).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone);
			row.createCell(99).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate);
			row.createCell(100).setCellValue(nSubProcessExtendedEventLoopNoneCompensateNone);
			row.createCell(101).setCellValue(nSubProcessExtendedEventLoopNoneCompensate);
			row.createCell(102).setCellValue(nSubProcessExtendedEventLoopStandardCompensateNone);
			row.createCell(103).setCellValue(nSubProcessExtendedEventLoopStandardCompensate);
			row.createCell(104).setCellValue(nSubProcessExtendedEventLoopMIParallelCompensateNone);
			row.createCell(105).setCellValue(nSubProcessExtendedEventLoopMIParallelCompensate);
			row.createCell(106).setCellValue(nSubProcessExtendedEventLoopMISequentialCompensateNone);
			row.createCell(107).setCellValue(nSubProcessExtendedEventLoopMISequentialCompensate);
			row.createCell(108).setCellValue(nSubProcessExtendedEventAdHocLoopNoneCompensateNone);
			row.createCell(109).setCellValue(nSubProcessExtendedEventAdHocLoopNoneCompensate);
			row.createCell(110).setCellValue(nSubProcessExtendedEventAdHocLoopStandardCompensateNone);
			row.createCell(111).setCellValue(nSubProcessExtendedEventAdHocLoopStandardCompensate);
			row.createCell(112).setCellValue(nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone);
			row.createCell(113).setCellValue(nSubProcessExtendedEventAdHocLoopMIParallelCompensate);
			row.createCell(114).setCellValue(nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone);
			row.createCell(115).setCellValue(nSubProcessExtendedEventAdHocLoopMISequentialCompensate);
			row.createCell(116).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone);
			row.createCell(117).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate);
			row.createCell(118).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone);
			row.createCell(119).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate);
			row.createCell(120).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone);
			row.createCell(121).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate);
			row.createCell(122).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone);
			row.createCell(123).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate);
			row.createCell(124).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone);
			row.createCell(125).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate);
			row.createCell(126).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopStandardCompensateNone);
			row.createCell(127).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopStandardCompensate);
			row.createCell(128).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone);
			row.createCell(129).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate);
			row.createCell(130).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone);
			row.createCell(131).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate);
			row.createCell(132).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone);
			row.createCell(133).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate);
			row.createCell(134).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopStandardCompensateNone);
			row.createCell(135).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopStandardCompensate);
			row.createCell(136).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensateNone);
			row.createCell(137).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensate);
			row.createCell(138).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensateNone);
			row.createCell(139).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensate);
			row.createCell(140).setCellValue(nSubProcessCollapsedEventLoopNoneCompensateNone);
			row.createCell(141).setCellValue(nSubProcessCollapsedEventLoopNoneCompensate);
			row.createCell(142).setCellValue(nSubProcessCollapsedEventLoopStandardCompensateNone);
			row.createCell(143).setCellValue(nSubProcessCollapsedEventLoopStandardCompensate);
			row.createCell(144).setCellValue(nSubProcessCollapsedEventLoopMIParallelCompensateNone);
			row.createCell(145).setCellValue(nSubProcessCollapsedEventLoopMIParallelCompensate);
			row.createCell(146).setCellValue(nSubProcessCollapsedEventLoopMISequentialCompensateNone);
			row.createCell(147).setCellValue(nSubProcessCollapsedEventLoopMISequentialCompensate);
			row.createCell(148).setCellValue(nSubProcessCollapsedEventLoopNoneCompensateNone);
			row.createCell(149).setCellValue(nSubProcessCollapsedEventAdHocLoopNoneCompensate);
			row.createCell(150).setCellValue(nSubProcessCollapsedEventAdHocLoopStandardCompensateNone);
			row.createCell(151).setCellValue(nSubProcessCollapsedEventAdHocLoopStandardCompensate);
			row.createCell(152).setCellValue(nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone);
			row.createCell(153).setCellValue(nSubProcessCollapsedEventAdHocLoopMIParallelCompensate);
			row.createCell(154).setCellValue(nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone);
			row.createCell(155).setCellValue(nSubProcessCollapsedEventAdHocLoopMISequentialCompensate);
			row.createCell(156).setCellValue(nDataObject);
			row.createCell(157).setCellValue(nDataObjectCollection);
			row.createCell(158).setCellValue(nDataObjectReference);
			row.createCell(159).setCellValue(nDataStore);
			row.createCell(160).setCellValue(nDataInput);
			row.createCell(161).setCellValue(nDataOutput);
			row.createCell(162).setCellValue(nExclusiveGatewayNoMarker);
			row.createCell(163).setCellValue(nExclusiveGatewayMarker);
			row.createCell(164).setCellValue(nParallelGateway);
			row.createCell(165).setCellValue(nInclusiveGateway);
			row.createCell(166).setCellValue(nEventBasedGateway);
			row.createCell(167).setCellValue(nEventBasedGatewayExclusiveInstantiation);
			row.createCell(168).setCellValue(nEventBasedGatewayParallelInstantiation);
			row.createCell(169).setCellValue(nComplexGateway);
			row.createCell(170).setCellValue(nStartMultipleParallelEventDefinition);
			row.createCell(171).setCellValue(nStartMultipleEventDefinition);
			row.createCell(172).setCellValue(nStartNoneEventDefinition);
			row.createCell(173).setCellValue(nStartSignalEventDefinition);
			row.createCell(174).setCellValue(nStartConditionalEventDefinition);
			row.createCell(175).setCellValue(nStartTimerEventDefinition);
			row.createCell(176).setCellValue(nStartMessageEventDefinition);
			row.createCell(177).setCellValue(nStartMessageEventSubProcessInterruptingDefinition);
			row.createCell(178).setCellValue(nStartTimerEventSubProcessInterruptingDefinition);
			row.createCell(179).setCellValue(nStartEscalationEventSubProcessInterruptingDefinition);
			row.createCell(180).setCellValue(nStartConditionalEventSubProcessInterruptingDefinition);
			row.createCell(181).setCellValue(nStartErrorEventSubProcessInterruptingDefinition);
			row.createCell(182).setCellValue(nStartCompensateEventSubProcessInterruptingDefinition);
			row.createCell(183).setCellValue(nStartSignalEventSubProcessInterruptingDefinition);
			row.createCell(184).setCellValue(nStartMultipleEventSubProcessInterruptingDefinition);
			row.createCell(185).setCellValue(nStartMultipleParallelEventSubProcessInterruptingDefinition);       
			row.createCell(186).setCellValue(nStartMessageEventSubProcessNonInterruptingDefinition);
			row.createCell(187).setCellValue(nStartTimerEventSubProcessNonInterruptingDefinition);
			row.createCell(188).setCellValue(nStartEscalationEventSubProcessNonInterruptingDefinition);
			row.createCell(189).setCellValue(nStartConditionalEventSubProcessNonInterruptingDefinition);
			row.createCell(190).setCellValue(nStartSignalEventSubProcessNonInterruptingDefinition);
			row.createCell(191).setCellValue(nStartMultipleParallelEventSubProcessNonInterruptingDefinition);
			row.createCell(192).setCellValue(nStartMultipleEventSubProcessNonInterruptingDefinition);       
			row.createCell(193).setCellValue(nEndNoneEventDefinition);
			row.createCell(194).setCellValue(nEndMultipleEventDefinition); 
			row.createCell(195).setCellValue(nEndEscalationEventDefinition);
			row.createCell(196).setCellValue(nEndErrorEventDefinition);
			row.createCell(197).setCellValue(nEndSignalEventDefinition);
			row.createCell(198).setCellValue(nEndCompensateEventDefinition);
			row.createCell(199).setCellValue(nEndCancelEventDefinition); 
			row.createCell(200).setCellValue(nEndMessageEventDefinition);
			row.createCell(201).setCellValue(nEndTerminateEventDefinition);
			row.createCell(202).setCellValue(nIntermediateCatchMultipleEventDefinition);
			row.createCell(203).setCellValue(nIntermediateCatchMultipleParallelEventDefinition);
			row.createCell(204).setCellValue(nIntermediateCatchMessageEventDefinition);
			row.createCell(205).setCellValue(nIntermediateCatchTimerEventDefinition);
			row.createCell(206).setCellValue(nIntermediateCatchConditionalEventDefinition);
			row.createCell(207).setCellValue(nIntermediateCatchLinkEventDefinition);
			row.createCell(208).setCellValue(nIntermediateCatchSignalEventDefinition);
			row.createCell(209).setCellValue(nIntermediateThrowNoneEventDefinition);
			row.createCell(210).setCellValue(nIntermediateThrowMessageEventDefinition);
			row.createCell(211).setCellValue(nIntermediateThrowEscalationEventDefinition);
			row.createCell(212).setCellValue(nIntermediateThrowLinkEventDefinition);
			row.createCell(213).setCellValue(nIntermediateThrowSignalEventDefinition);
			row.createCell(214).setCellValue(nIntermediateThrowCompensateEventDefinition);
			row.createCell(215).setCellValue(nIntermediateThrowMultipleEventDefinition);
			row.createCell(216).setCellValue(nIntermediateBoundaryMessageEvent);
			row.createCell(217).setCellValue(nIntermediateBoundaryTimerEvent);
			row.createCell(218).setCellValue(nIntermediateBoundaryCancelEvent);
			row.createCell(219).setCellValue(nIntermediateBoundaryConditionalEvent);
			row.createCell(220).setCellValue(nIntermediateBoundaryEscalationEvent);
			row.createCell(221).setCellValue(nIntermediateBoundaryErrorEvent);
			row.createCell(222).setCellValue(nIntermediateBoundarySignalEvent);
			row.createCell(223).setCellValue(nIntermediateBoundaryCompensateEvent);
			row.createCell(224).setCellValue(nIntermediateBoundaryMultipleEvent);
			row.createCell(225).setCellValue(nIntermediateBoundaryMultipleParallelEvent);
			row.createCell(226).setCellValue(nIntermediateBoundaryTimerEventNonInterrupting);
			row.createCell(227).setCellValue(nIntermediateBoundaryEscalationEventNonInterrupting);
			row.createCell(228).setCellValue(nIntermediateBoundaryConditionalEventNonInterrupting);
			row.createCell(229).setCellValue(nIntermediateBoundaryMessageEventNonInterrupting);
			row.createCell(230).setCellValue(nIntermediateBoundarySignalEventNonInterrupting);
			row.createCell(231).setCellValue(nIntermediateBoundaryMultipleEventNonInterrupting);
			row.createCell(232).setCellValue(nIntermediateBoundaryMultipleParallelEventNonInterrupting);
			row.createCell(233).setCellValue(nMessageFlow);
			row.createCell(234).setCellValue(nSequenceFlow);
			row.createCell(235).setCellValue(nDefaultFlow);
			row.createCell(236).setCellValue(nConditionalFlow);
			row.createCell(237).setCellValue(nLane); 
			row.createCell(238).setCellValue(nPoolCollapsedMultiplicityNone);
			row.createCell(239).setCellValue(nPoolCollapsedMultiplicity);
			row.createCell(240).setCellValue(nPoolExpandedMultiplicityNone);
			row.createCell(241).setCellValue(nPoolExpandedMultiplicity);
			row.createCell(242).setCellValue(nChoreographyTask);
			row.createCell(243).setCellValue(nChoreographyMessage);            
			row.createCell(244).setCellValue(nChoreographyTaskSequentialMultipleInstance);
			row.createCell(245).setCellValue(nChoreographyTaskParallelMultipleInstance);
			row.createCell(246).setCellValue(nChoreographyTaskLoop);
			row.createCell(247).setCellValue(nChoreographySubprocessCollapsed);
			row.createCell(248).setCellValue(nChoreographySubprocessCollapsedParallelMultipleInstance);
			row.createCell(249).setCellValue(nChoreographySubprocessCollapsedSequentialMultipleInstance);
			row.createCell(250).setCellValue(nChoreographySubprocessCollapsedLoop);
			row.createCell(251).setCellValue(nChoreographySubprocessCollapsedCall);
			row.createCell(252).setCellValue(nChoreographySubprocessCollapsedCallSequentialMultipleInstance);
			row.createCell(253).setCellValue(nChoreographySubprocessCollapsedCallParallelMultipleInstance);
			row.createCell(254).setCellValue(nChoreographySubprocessCollapsedCallLoop);
			row.createCell(255).setCellValue(nChoreographySubprocessExpanded);
			row.createCell(256).setCellValue(nChoreographySubprocessExpandedSequentialMultipleInstance);
			row.createCell(257).setCellValue(nChoreographySubprocessExpandedParallelMultipleInstance);
			row.createCell(258).setCellValue(nChoreographySubprocessExpandedLoop);
			row.createCell(259).setCellValue(nChoreographyParticipant);
			row.createCell(260).setCellValue(nChoreographyParticipantMultiple);       
			row.createCell(261).setCellValue(nConversationNone);
			row.createCell(262).setCellValue(nConversationSubProcess);
			row.createCell(263).setCellValue(nConversationCall);
			row.createCell(264).setCellValue(nConversationSubProcessCall);
			row.createCell(265).setCellValue(nConversationLink);
			row.createCell(266).setCellValue(nAssociationCompensate);
			row.createCell(267).setCellValue(nAssociationUndirected);
			row.createCell(268).setCellValue(nAssociationUnidirectional);        
			row.createCell(269).setCellValue(nAssociationBidirectional);
			row.createCell(270).setCellValue(nAssociationDataOutput);
			row.createCell(271).setCellValue(nAssociationDataInput);            
			row.createCell(272).setCellValue(nGroup);
			row.createCell(273).setCellValue(nTextAnnotation);
			row.createCell(274).setCellValue(TotalElements);             

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

			if(ConsiderExtendedSubProcess && nExtendedSubProcess>0) {

				String SubProcessModelID="";


				// TO READAPT
				XSSFRow rowhead2 = sheet2.createRow((short)0); 
				rowhead2.createCell(0).setCellValue("fileName");
				rowhead2.createCell(1).setCellValue("bpmnModeler");
				rowhead2.createCell(2).setCellValue("modelType");
				rowhead2.createCell(3).setCellValue("isEnglish");
				rowhead2.createCell(4).setCellValue("nTaskNoneLoopNoneCompensateNoneCallNone");
				rowhead2.createCell(5).setCellValue("nTaskNoneLoopNoneCompensateNoneCall");
				rowhead2.createCell(6).setCellValue("nTaskNoneLoopNoneCompensateCallNone");
				rowhead2.createCell(7).setCellValue("nTaskNoneLoopNoneCompensateCall");
				rowhead2.createCell(8).setCellValue("nTaskNoneLoopStandardCompensateNoneCallNone");
				rowhead2.createCell(9).setCellValue("nTaskNoneLoopStandardCompensateNoneCall");
				rowhead2.createCell(10).setCellValue("nTaskNoneLoopStandardCompensateCallNone");
				rowhead2.createCell(11).setCellValue("nTaskNoneLoopStandardCompensateCall");
				rowhead2.createCell(12).setCellValue("nTaskNoneLoopMIParallelCompensateNoneCallNone");
				rowhead2.createCell(13).setCellValue("nTaskNoneLoopMIParallelCompensateNoneCall");
				rowhead2.createCell(14).setCellValue("nTaskNoneLoopMIParallelCompensateCallNone");
				rowhead2.createCell(15).setCellValue("nTaskNoneLoopMIParallelCompensateCall");
				rowhead2.createCell(16).setCellValue("nTaskNoneLoopMISequentialCompensateNoneCallNone");
				rowhead2.createCell(17).setCellValue("nTaskNoneLoopMISequentialCompensateNoneCall");
				rowhead2.createCell(18).setCellValue("nTaskNoneLoopMISequentialCompensateCallNone");
				rowhead2.createCell(19).setCellValue("nTaskNoneLoopMISequentialCompensateCall");
				rowhead2.createCell(20).setCellValue("nTaskSendLoopNoneCompensateNone");
				rowhead2.createCell(21).setCellValue("nTaskSendLoopNoneCompensate");
				rowhead2.createCell(22).setCellValue("nTaskSendLoopStandardCompensateNone");           
				rowhead2.createCell(23).setCellValue("nTaskSendLoopStandardCompensate"); 
				rowhead2.createCell(24).setCellValue("nTaskSendLoopMIParallelCompensateNone"); 
				rowhead2.createCell(25).setCellValue("nTaskSendLoopMIParallelCompensate");
				rowhead2.createCell(26).setCellValue("nTaskSendLoopMISequentialCompensateNone"); 
				rowhead2.createCell(27).setCellValue("nTaskSendLoopMISequentialCompensate");
				rowhead2.createCell(28).setCellValue("nTaskReceiveLoopNoneCompensateNone");            
				rowhead2.createCell(29).setCellValue("nTaskReceiveLoopNoneCompensate");           
				rowhead2.createCell(30).setCellValue("nTaskReceiveLoopStandardCompensateNone");            
				rowhead2.createCell(31).setCellValue("nTaskReceiveLoopStandardCompensate");            
				rowhead2.createCell(32).setCellValue("nTaskReceiveLoopMIParallelCompensateNone");                        
				rowhead2.createCell(33).setCellValue("nTaskReceiveLoopMIParallelCompensate");            
				rowhead2.createCell(34).setCellValue("nTaskReceiveLoopMISequentialCompensateNone");           
				rowhead2.createCell(35).setCellValue("nTaskReceiveLoopMISequentialCompensate");            
				rowhead2.createCell(36).setCellValue("nTaskUserLoopNoneCompensateNone");            
				rowhead2.createCell(37).setCellValue("nTaskUserLoopNoneCompensate");           
				rowhead2.createCell(38).setCellValue("nTaskUserLoopStandardCompensateNone");            
				rowhead2.createCell(39).setCellValue("nTaskUserLoopStandardCompensate");           
				rowhead2.createCell(40).setCellValue("nTaskUserLoopMIParallelCompensateNone");            
				rowhead2.createCell(41).setCellValue("nTaskUserLoopMIParallelCompensate");            
				rowhead2.createCell(42).setCellValue("nTaskUserLoopMISequentialCompensateNone");            
				rowhead2.createCell(43).setCellValue("nTaskUserLoopMISequentialCompensate");            
				rowhead2.createCell(44).setCellValue("nTaskManualLoopNoneCompensateNone");            
				rowhead2.createCell(45).setCellValue("nTaskManualLoopNoneCompensate");            
				rowhead2.createCell(46).setCellValue("nTaskManualLoopStandardCompensateNone");            
				rowhead2.createCell(47).setCellValue("nTaskManualLoopStandardCompensate");            
				rowhead2.createCell(48).setCellValue("nTaskManualLoopMIParallelCompensateNone");            
				rowhead2.createCell(49).setCellValue("nTaskManualLoopMIParallelCompensate");            
				rowhead2.createCell(50).setCellValue("nTaskManualLoopMISequentialCompensateNone");            
				rowhead2.createCell(51).setCellValue("nTaskManualLoopMISequentialCompensate");            
				rowhead2.createCell(52).setCellValue("nTaskBusinessRuleLoopNoneCompensateNone");            
				rowhead2.createCell(53).setCellValue("nTaskBusinessRuleLoopNoneCompensate");            
				rowhead2.createCell(54).setCellValue("nTaskBusinessRuleLoopStandardCompensateNone");            
				rowhead2.createCell(55).setCellValue("nTaskBusinessRuleLoopStandardCompensate");            
				rowhead2.createCell(56).setCellValue("nTaskBusinessRuleLoopMIParallelCompensateNone");           
				rowhead2.createCell(57).setCellValue("nTaskBusinessRuleLoopMIParallelCompensate");            
				rowhead2.createCell(58).setCellValue("nTaskBusinessRuleLoopMISequentialCompensateNone");           
				rowhead2.createCell(59).setCellValue("nTaskBusinessRuleLoopMISequentialCompensate");            
				rowhead2.createCell(60).setCellValue("nTaskServiceLoopNoneCompensateNone");            
				rowhead2.createCell(61).setCellValue("nTaskServiceLoopNoneCompensate");            
				rowhead2.createCell(62).setCellValue("nTaskServiceLoopStandardCompensateNone");            
				rowhead2.createCell(63).setCellValue("nTaskServiceLoopStandardCompensate");            
				rowhead2.createCell(64).setCellValue("nTaskServiceLoopMIParallelCompensateNone");            
				rowhead2.createCell(65).setCellValue("nTaskServiceLoopMIParallelCompensate");            
				rowhead2.createCell(66).setCellValue("nTaskServiceLoopMISequentialCompensateNone");            
				rowhead2.createCell(67).setCellValue("nTaskServiceLoopMISequentialCompensate");            
				rowhead2.createCell(68).setCellValue("nTaskScriptLoopNoneCompensateNone");            
				rowhead2.createCell(69).setCellValue("nTaskScriptLoopNoneCompensate");           
				rowhead2.createCell(70).setCellValue("nTaskScriptLoopStandardCompensateNone");            
				rowhead2.createCell(71).setCellValue("nTaskScriptLoopStandardCompensate");            
				rowhead2.createCell(72).setCellValue("nTaskScriptLoopMIParallelCompensateNone");            
				rowhead2.createCell(73).setCellValue("nTaskScriptLoopMIParallelCompensate");            
				rowhead2.createCell(74).setCellValue("nTaskScriptLoopMISequentialCompensateNone");            
				rowhead2.createCell(75).setCellValue("nTaskScriptLoopMISequentialCompensate");            
				rowhead2.createCell(76).setCellValue("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone");
				rowhead2.createCell(77).setCellValue("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate");
				rowhead2.createCell(78).setCellValue("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone");
				rowhead2.createCell(79).setCellValue("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate");
				rowhead2.createCell(80).setCellValue("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone");
				rowhead2.createCell(81).setCellValue("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate");
				rowhead2.createCell(82).setCellValue("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone");
				rowhead2.createCell(83).setCellValue("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate");
				rowhead2.createCell(84).setCellValue("nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone");
				rowhead2.createCell(85).setCellValue("nSubProcessExtendedEventNoneAdHocLoopNoneCompensate");
				rowhead2.createCell(86).setCellValue("nSubProcessExtendedEventNoneAdHocLoopStandardCompensateNone");
				rowhead2.createCell(87).setCellValue("nSubProcessExtendedEventNoneAdHocLoopStandardCompensate");
				rowhead2.createCell(88).setCellValue("nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone");
				rowhead2.createCell(89).setCellValue("nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate");
				rowhead2.createCell(90).setCellValue("nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone");
				rowhead2.createCell(91).setCellValue("nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate");
				rowhead2.createCell(92).setCellValue("nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone");
				rowhead2.createCell(93).setCellValue("nSubProcessExtendedEventNoneTransactionLoopNoneCompensate");
				rowhead2.createCell(94).setCellValue("nSubProcessExtendedEventNoneTransactionLoopStandardCompensateNone");
				rowhead2.createCell(95).setCellValue("nSubProcessExtendedEventNoneTransactionLoopStandardCompensate");
				rowhead2.createCell(96).setCellValue("nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone");
				rowhead2.createCell(97).setCellValue("nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate");
				rowhead2.createCell(98).setCellValue("nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone");
				rowhead2.createCell(99).setCellValue("nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate");
				rowhead2.createCell(100).setCellValue("nSubProcessExtendedEventLoopNoneCompensateNone");
				rowhead2.createCell(101).setCellValue("nSubProcessExtendedEventLoopNoneCompensate");
				rowhead2.createCell(102).setCellValue("nSubProcessExtendedEventLoopStandardCompensateNone");
				rowhead2.createCell(103).setCellValue("nSubProcessExtendedEventLoopStandardCompensate");
				rowhead2.createCell(104).setCellValue("nSubProcessExtendedEventLoopMIParallelCompensateNone");
				rowhead2.createCell(105).setCellValue("nSubProcessExtendedEventLoopMIParallelCompensate");
				rowhead2.createCell(106).setCellValue("nSubProcessExtendedEventLoopMISequentialCompensateNone");
				rowhead2.createCell(107).setCellValue("nSubProcessExtendedEventLoopMISequentialCompensate");
				rowhead2.createCell(108).setCellValue("nSubProcessExtendedEventAdHocLoopNoneCompensateNone");
				rowhead2.createCell(109).setCellValue("nSubProcessExtendedEventAdHocLoopNoneCompensate");
				rowhead2.createCell(110).setCellValue("nSubProcessExtendedEventAdHocLoopStandardCompensateNone");
				rowhead2.createCell(111).setCellValue("nSubProcessExtendedEventAdHocLoopStandardCompensate");
				rowhead2.createCell(112).setCellValue("nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone");
				rowhead2.createCell(113).setCellValue("nSubProcessExtendedEventAdHocLoopMIParallelCompensate");
				rowhead2.createCell(114).setCellValue("nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone");
				rowhead2.createCell(115).setCellValue("nSubProcessExtendedEventAdHocLoopMISequentialCompensate");
				rowhead2.createCell(116).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone");
				rowhead2.createCell(117).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate");
				rowhead2.createCell(118).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone");
				rowhead2.createCell(119).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate");
				rowhead2.createCell(120).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone");
				rowhead2.createCell(121).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate");
				rowhead2.createCell(122).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone");
				rowhead2.createCell(123).setCellValue("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate");
				rowhead2.createCell(124).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone");
				rowhead2.createCell(125).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate");
				rowhead2.createCell(126).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopStandardCompensateNone");
				rowhead2.createCell(127).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopStandardCompensate");
				rowhead2.createCell(128).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone");
				rowhead2.createCell(129).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate");
				rowhead2.createCell(130).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone");
				rowhead2.createCell(131).setCellValue("nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate");
				rowhead2.createCell(132).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone");
				rowhead2.createCell(133).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate");
				rowhead2.createCell(134).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopStandardCompensateNone");
				rowhead2.createCell(135).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopStandardCompensate");
				rowhead2.createCell(136).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensateNone");
				rowhead2.createCell(137).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensate");
				rowhead2.createCell(138).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensateNone");
				rowhead2.createCell(139).setCellValue("nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensate");
				rowhead2.createCell(140).setCellValue("nSubProcessCollapsedEventLoopNoneCompensateNone");
				rowhead2.createCell(141).setCellValue("nSubProcessCollapsedEventLoopNoneCompensate");
				rowhead2.createCell(142).setCellValue("nSubProcessCollapsedEventLoopStandardCompensateNone");
				rowhead2.createCell(143).setCellValue("nSubProcessCollapsedEventLoopStandardCompensate");
				rowhead2.createCell(144).setCellValue("nSubProcessCollapsedEventLoopMIParallelCompensateNone");
				rowhead2.createCell(145).setCellValue("nSubProcessCollapsedEventLoopMIParallelCompensate");
				rowhead2.createCell(146).setCellValue("nSubProcessCollapsedEventLoopMISequentialCompensateNone");
				rowhead2.createCell(147).setCellValue("nSubProcessCollapsedEventLoopMISequentialCompensate");
				rowhead2.createCell(148).setCellValue("nSubProcessCollapsedEventLoopNoneCompensateNone");
				rowhead2.createCell(149).setCellValue("nSubProcessCollapsedEventAdHocLoopNoneCompensate");
				rowhead2.createCell(150).setCellValue("nSubProcessCollapsedEventAdHocLoopStandardCompensateNone");
				rowhead2.createCell(151).setCellValue("nSubProcessCollapsedEventAdHocLoopStandardCompensate");
				rowhead2.createCell(152).setCellValue("nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone");
				rowhead2.createCell(153).setCellValue("nSubProcessCollapsedEventAdHocLoopMIParallelCompensate");
				rowhead2.createCell(154).setCellValue("nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone");
				rowhead2.createCell(155).setCellValue("nSubProcessCollapsedEventAdHocLoopMISequentialCompensate");
				rowhead2.createCell(156).setCellValue("nDataObject");
				rowhead2.createCell(157).setCellValue("nDataObjectCollection");
				rowhead2.createCell(158).setCellValue("nDataObjectReference");
				rowhead2.createCell(159).setCellValue("nDataStore");
				rowhead2.createCell(160).setCellValue("nDataInput");
				rowhead2.createCell(161).setCellValue("nDataOutput");
				rowhead2.createCell(162).setCellValue("nExclusiveGatewayNoMarker");
				rowhead2.createCell(163).setCellValue("nExclusiveGatewayMarker");
				rowhead2.createCell(164).setCellValue("nParallelGateway");
				rowhead2.createCell(165).setCellValue("nInclusiveGateway");
				rowhead2.createCell(166).setCellValue("nEventBasedGateway");
				rowhead2.createCell(167).setCellValue("nEventBasedGatewayExclusiveInstantiation");
				rowhead2.createCell(168).setCellValue("nEventBasedGatewayParallelInstantiation");
				rowhead2.createCell(169).setCellValue("nComplexGateway");
				rowhead2.createCell(170).setCellValue("nStartMultipleParallelEventDefinition");
				rowhead2.createCell(171).setCellValue("nStartMultipleEventDefinition");
				rowhead2.createCell(172).setCellValue("nStartNoneEventDefinition");
				rowhead2.createCell(173).setCellValue("nStartSignalEventDefinition");
				rowhead2.createCell(174).setCellValue("nStartConditionalEventDefinition");
				rowhead2.createCell(175).setCellValue("nStartTimerEventDefinition");
				rowhead2.createCell(176).setCellValue("nStartMessageEventDefinition");
				rowhead2.createCell(177).setCellValue("nStartMessageEventSubProcessInterruptingDefinition");
				rowhead2.createCell(178).setCellValue("nStartTimerEventSubProcessInterruptingDefinition");
				rowhead2.createCell(179).setCellValue("nStartEscalationEventSubProcessInterruptingDefinition");
				rowhead2.createCell(180).setCellValue("nStartConditionalEventSubProcessInterruptingDefinition");
				rowhead2.createCell(181).setCellValue("nStartErrorEventSubProcessInterruptingDefinition");
				rowhead2.createCell(182).setCellValue("nStartCompensateEventSubProcessInterruptingDefinition");
				rowhead2.createCell(183).setCellValue("nStartSignalEventSubProcessInterruptingDefinition");
				rowhead2.createCell(184).setCellValue("nStartMultipleEventSubProcessInterruptingDefinition");
				rowhead2.createCell(185).setCellValue("nStartMultipleParallelEventSubProcessInterruptingDefinition");       
				rowhead2.createCell(186).setCellValue("nStartMessageEventSubProcessNonInterruptingDefinition");
				rowhead2.createCell(187).setCellValue("nStartTimerEventSubProcessNonInterruptingDefinition");
				rowhead2.createCell(188).setCellValue("nStartEscalationEventSubProcessNonInterruptingDefinition");
				rowhead2.createCell(189).setCellValue("nStartConditionalEventSubProcessNonInterruptingDefinition");
				rowhead2.createCell(190).setCellValue("nStartSignalEventSubProcessNonInterruptingDefinition");
				rowhead2.createCell(191).setCellValue("nStartMultipleParallelEventSubProcessNonInterruptingDefinition");
				rowhead2.createCell(192).setCellValue("nStartMultipleEventSubProcessNonInterruptingDefinition");       
				rowhead2.createCell(193).setCellValue("nEndNoneEventDefinition");
				rowhead2.createCell(194).setCellValue("nEndMultipleEventDefinition"); 
				rowhead2.createCell(195).setCellValue("nEndEscalationEventDefinition");
				rowhead2.createCell(196).setCellValue("nEndErrorEventDefinition");
				rowhead2.createCell(197).setCellValue("nEndSignalEventDefinition");
				rowhead2.createCell(198).setCellValue("nEndCompensateEventDefinition");
				rowhead2.createCell(199).setCellValue("nEndCancelEventDefinition"); 
				rowhead2.createCell(200).setCellValue("nEndMessageEventDefinition");
				rowhead2.createCell(201).setCellValue("nEndTerminateEventDefinition");
				rowhead2.createCell(202).setCellValue("nIntermediateCatchMultipleEventDefinition");
				rowhead2.createCell(203).setCellValue("nIntermediateCatchMultipleParallelEventDefinition");
				rowhead2.createCell(204).setCellValue("nIntermediateCatchMessageEventDefinition");
				rowhead2.createCell(205).setCellValue("nIntermediateCatchTimerEventDefinition");
				rowhead2.createCell(206).setCellValue("nIntermediateCatchConditionalEventDefinition");
				rowhead2.createCell(207).setCellValue("nIntermediateCatchLinkEventDefinition");
				rowhead2.createCell(208).setCellValue("nIntermediateCatchSignalEventDefinition");
				rowhead2.createCell(209).setCellValue("nIntermediateThrowNoneEventDefinition");
				rowhead2.createCell(210).setCellValue("nIntermediateThrowMessageEventDefinition");
				rowhead2.createCell(211).setCellValue("nIntermediateThrowEscalationEventDefinition");
				rowhead2.createCell(212).setCellValue("nIntermediateThrowLinkEventDefinition");
				rowhead2.createCell(213).setCellValue("nIntermediateThrowSignalEventDefinition");
				rowhead2.createCell(214).setCellValue("nIntermediateThrowCompensateEventDefinition");
				rowhead2.createCell(215).setCellValue("nIntermediateThrowMultipleEventDefinition");
				rowhead2.createCell(216).setCellValue("nIntermediateBoundaryMessageEvent");
				rowhead2.createCell(217).setCellValue("nIntermediateBoundaryTimerEvent");
				rowhead2.createCell(218).setCellValue("nIntermediateBoundaryCancelEvent");
				rowhead2.createCell(219).setCellValue("nIntermediateBoundaryConditionalEvent");
				rowhead2.createCell(220).setCellValue("nIntermediateBoundaryEscalationEvent");
				rowhead2.createCell(221).setCellValue("nIntermediateBoundaryErrorEvent");
				rowhead2.createCell(222).setCellValue("nIntermediateBoundarySignalEvent");
				rowhead2.createCell(223).setCellValue("nIntermediateBoundaryCompensateEvent");
				rowhead2.createCell(224).setCellValue("nIntermediateBoundaryMultipleEvent");
				rowhead2.createCell(225).setCellValue("nIntermediateBoundaryMultipleParallelEvent");
				rowhead2.createCell(226).setCellValue("nIntermediateBoundaryTimerEventNonInterrupting");
				rowhead2.createCell(227).setCellValue("nIntermediateBoundaryEscalationEventNonInterrupting");
				rowhead2.createCell(228).setCellValue("nIntermediateBoundaryConditionalEventNonInterrupting");
				rowhead2.createCell(229).setCellValue("nIntermediateBoundaryMessageEventNonInterrupting");
				rowhead2.createCell(230).setCellValue("nIntermediateBoundarySignalEventNonInterrupting");
				rowhead2.createCell(231).setCellValue("nIntermediateBoundaryMultipleEventNonInterrupting");
				rowhead2.createCell(232).setCellValue("nIntermediateBoundaryMultipleParallelEventNonInterrupting");
				rowhead2.createCell(233).setCellValue("nMessageFlow");
				rowhead2.createCell(234).setCellValue("nSequenceFlow");
				rowhead2.createCell(235).setCellValue("nDefaultFlow");
				rowhead2.createCell(236).setCellValue("nConditionalFlow");
				rowhead2.createCell(237).setCellValue("nLane"); 
				rowhead2.createCell(238).setCellValue("nPoolCollapsedMultiplicityNone");
				rowhead2.createCell(239).setCellValue("nPoolCollapsedMultiplicity");
				rowhead2.createCell(240).setCellValue("nPoolExpandedMultiplicityNone");
				rowhead2.createCell(241).setCellValue("nPoolExpandedMultiplicity");
				rowhead2.createCell(242).setCellValue("nChoreographyTask");
				rowhead2.createCell(243).setCellValue("nChoreographyMessage");            
				rowhead2.createCell(244).setCellValue("nChoreographyTaskSequentialMultipleInstance");
				rowhead2.createCell(245).setCellValue("nChoreographyTaskParallelMultipleInstance");
				rowhead2.createCell(246).setCellValue("nChoreographyTaskLoop");
				rowhead2.createCell(247).setCellValue("nChoreographySubprocessCollapsed");
				rowhead2.createCell(248).setCellValue("nChoreographySubprocessCollapsedParallelMultipleInstance");
				rowhead2.createCell(249).setCellValue("nChoreographySubprocessCollapsedSequentialMultipleInstance");
				rowhead2.createCell(250).setCellValue("nChoreographySubprocessCollapsedLoop");
				rowhead2.createCell(251).setCellValue("nChoreographySubprocessCollapsedCall");
				rowhead2.createCell(252).setCellValue("nChoreographySubprocessCollapsedCallSequentialMultipleInstance");
				rowhead2.createCell(253).setCellValue("nChoreographySubprocessCollapsedCallParallelMultipleInstance");
				rowhead2.createCell(254).setCellValue("nChoreographySubprocessCollapsedCallLoop");
				rowhead2.createCell(255).setCellValue("nChoreographySubprocessExpanded");
				rowhead2.createCell(256).setCellValue("nChoreographySubprocessExpandedSequentialMultipleInstance");
				rowhead2.createCell(257).setCellValue("nChoreographySubprocessExpandedParallelMultipleInstance");
				rowhead2.createCell(258).setCellValue("nChoreographySubprocessExpandedLoop");
				rowhead2.createCell(259).setCellValue("nChoreographyParticipant");
				rowhead2.createCell(260).setCellValue("nChoreographyParticipantMultiple");       
				rowhead2.createCell(261).setCellValue("nConversationNone");
				rowhead2.createCell(262).setCellValue("nConversationSubProcess");
				rowhead2.createCell(263).setCellValue("nConversationCall");
				rowhead2.createCell(264).setCellValue("nConversationSubProcessCall");
				rowhead2.createCell(265).setCellValue("nConversationLink");
				rowhead2.createCell(266).setCellValue("nAssociationCompensate");
				rowhead2.createCell(267).setCellValue("nAssociationUndirected");
				rowhead2.createCell(268).setCellValue("nAssociationUnidirectional");        
				rowhead2.createCell(269).setCellValue("nAssociationBidirectional");
				rowhead2.createCell(270).setCellValue("nAssociationDataOutput");
				rowhead2.createCell(271).setCellValue("nAssociationDataInput");            
				rowhead2.createCell(272).setCellValue("nGroup");
				rowhead2.createCell(273).setCellValue("nTextAnnotation");
				rowhead2.createCell(274).setCellValue("TotalElements");          

				// TO READAPT
				XSSFRow row2 = sheet2.createRow((short)x+1);  
				row2.createCell(0).setCellValue(fileName);
				row2.createCell(1).setCellValue(bpmnModeler);
				row2.createCell(2).setCellValue(modelType);
				row2.createCell(3).setCellValue(isEnglish);
				row2.createCell(4).setCellValue(nTaskNoneLoopNoneCompensateNoneCallNone);
				row2.createCell(5).setCellValue(nTaskNoneLoopNoneCompensateNoneCall);
				row2.createCell(6).setCellValue(nTaskNoneLoopNoneCompensateCallNone);
				row2.createCell(7).setCellValue(nTaskNoneLoopNoneCompensateCall);
				row2.createCell(8).setCellValue(nTaskNoneLoopStandardCompensateNoneCallNone);
				row2.createCell(9).setCellValue(nTaskNoneLoopStandardCompensateNoneCall);
				row2.createCell(10).setCellValue(nTaskNoneLoopStandardCompensateCallNone);
				row2.createCell(11).setCellValue(nTaskNoneLoopStandardCompensateCall);
				row2.createCell(12).setCellValue(nTaskNoneLoopMIParallelCompensateNoneCallNone);
				row2.createCell(13).setCellValue(nTaskNoneLoopMIParallelCompensateNoneCall);
				row2.createCell(14).setCellValue(nTaskNoneLoopMIParallelCompensateCallNone);
				row2.createCell(15).setCellValue(nTaskNoneLoopMIParallelCompensateCall);
				row2.createCell(16).setCellValue(nTaskNoneLoopMISequentialCompensateNoneCallNone);
				row2.createCell(17).setCellValue(nTaskNoneLoopMISequentialCompensateNoneCall);
				row2.createCell(18).setCellValue(nTaskNoneLoopMISequentialCompensateCallNone);
				row2.createCell(19).setCellValue(nTaskNoneLoopMISequentialCompensateCall);
				row2.createCell(20).setCellValue(nTaskSendLoopNoneCompensateNone);
				row2.createCell(21).setCellValue(nTaskSendLoopNoneCompensate);
				row2.createCell(22).setCellValue(nTaskSendLoopStandardCompensateNone);           
				row2.createCell(23).setCellValue(nTaskSendLoopStandardCompensate); 
				row2.createCell(24).setCellValue(nTaskSendLoopMIParallelCompensateNone); 
				row2.createCell(25).setCellValue(nTaskSendLoopMIParallelCompensate);
				row2.createCell(26).setCellValue(nTaskSendLoopMISequentialCompensateNone); 
				row2.createCell(27).setCellValue(nTaskSendLoopMISequentialCompensate);
				row2.createCell(28).setCellValue(nTaskReceiveLoopNoneCompensateNone);            
				row2.createCell(29).setCellValue(nTaskReceiveLoopNoneCompensate);           
				row2.createCell(30).setCellValue(nTaskReceiveLoopStandardCompensateNone);            
				row2.createCell(31).setCellValue(nTaskReceiveLoopStandardCompensate);            
				row2.createCell(32).setCellValue(nTaskReceiveLoopMIParallelCompensateNone);                        
				row2.createCell(33).setCellValue(nTaskReceiveLoopMIParallelCompensate);            
				row2.createCell(34).setCellValue(nTaskReceiveLoopMISequentialCompensateNone);           
				row2.createCell(35).setCellValue(nTaskReceiveLoopMISequentialCompensate);            
				row2.createCell(36).setCellValue(nTaskUserLoopNoneCompensateNone);            
				row2.createCell(37).setCellValue(nTaskUserLoopNoneCompensate);           
				row2.createCell(38).setCellValue(nTaskUserLoopStandardCompensateNone);            
				row2.createCell(39).setCellValue(nTaskUserLoopStandardCompensate);           
				row2.createCell(40).setCellValue(nTaskUserLoopMIParallelCompensateNone);            
				row2.createCell(41).setCellValue(nTaskUserLoopMIParallelCompensate);            
				row2.createCell(42).setCellValue(nTaskUserLoopMISequentialCompensateNone);            
				row2.createCell(43).setCellValue(nTaskUserLoopMISequentialCompensate);            
				row2.createCell(44).setCellValue(nTaskManualLoopNoneCompensateNone);            
				row2.createCell(45).setCellValue(nTaskManualLoopNoneCompensate);            
				row2.createCell(46).setCellValue(nTaskManualLoopStandardCompensateNone);            
				row2.createCell(47).setCellValue(nTaskManualLoopStandardCompensate);            
				row2.createCell(48).setCellValue(nTaskManualLoopMIParallelCompensateNone);            
				row2.createCell(49).setCellValue(nTaskManualLoopMIParallelCompensate);            
				row2.createCell(50).setCellValue(nTaskManualLoopMISequentialCompensateNone);            
				row2.createCell(51).setCellValue(nTaskManualLoopMISequentialCompensate);            
				row2.createCell(52).setCellValue(nTaskBusinessRuleLoopNoneCompensateNone);            
				row2.createCell(53).setCellValue(nTaskBusinessRuleLoopNoneCompensate);            
				row2.createCell(54).setCellValue(nTaskBusinessRuleLoopStandardCompensateNone);            
				row2.createCell(55).setCellValue(nTaskBusinessRuleLoopStandardCompensate);            
				row2.createCell(56).setCellValue(nTaskBusinessRuleLoopMIParallelCompensateNone);           
				row2.createCell(57).setCellValue(nTaskBusinessRuleLoopMIParallelCompensate);            
				row2.createCell(58).setCellValue(nTaskBusinessRuleLoopMISequentialCompensateNone);           
				row2.createCell(59).setCellValue(nTaskBusinessRuleLoopMISequentialCompensate);            
				row2.createCell(60).setCellValue(nTaskServiceLoopNoneCompensateNone);            
				row2.createCell(61).setCellValue(nTaskServiceLoopNoneCompensate);            
				row2.createCell(62).setCellValue(nTaskServiceLoopStandardCompensateNone);            
				row2.createCell(63).setCellValue(nTaskServiceLoopStandardCompensate);            
				row2.createCell(64).setCellValue(nTaskServiceLoopMIParallelCompensateNone);            
				row2.createCell(65).setCellValue(nTaskServiceLoopMIParallelCompensate);            
				row2.createCell(66).setCellValue(nTaskServiceLoopMISequentialCompensateNone);            
				row2.createCell(67).setCellValue(nTaskServiceLoopMISequentialCompensate);            
				row2.createCell(68).setCellValue(nTaskScriptLoopNoneCompensateNone);            
				row2.createCell(69).setCellValue(nTaskScriptLoopNoneCompensate);           
				row2.createCell(70).setCellValue(nTaskScriptLoopStandardCompensateNone);            
				row2.createCell(71).setCellValue(nTaskScriptLoopStandardCompensate);            
				row2.createCell(72).setCellValue(nTaskScriptLoopMIParallelCompensateNone);            
				row2.createCell(73).setCellValue(nTaskScriptLoopMIParallelCompensate);            
				row2.createCell(74).setCellValue(nTaskScriptLoopMISequentialCompensateNone);            
				row2.createCell(75).setCellValue(nTaskScriptLoopMISequentialCompensate);            
				row2.createCell(76).setCellValue(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone);
				row2.createCell(77).setCellValue(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate);
				row2.createCell(78).setCellValue(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone);
				row2.createCell(79).setCellValue(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate);
				row2.createCell(80).setCellValue(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone);
				row2.createCell(81).setCellValue(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate);
				row2.createCell(82).setCellValue(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone);
				row2.createCell(83).setCellValue(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate);
				row2.createCell(84).setCellValue(nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone);
				row2.createCell(85).setCellValue(nSubProcessExtendedEventNoneAdHocLoopNoneCompensate);
				row2.createCell(86).setCellValue(nSubProcessExtendedEventNoneAdHocLoopStandardCompensateNone);
				row2.createCell(87).setCellValue(nSubProcessExtendedEventNoneAdHocLoopStandardCompensate);
				row2.createCell(88).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone);
				row2.createCell(89).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate);
				row2.createCell(90).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone);
				row2.createCell(91).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate);
				row2.createCell(92).setCellValue(nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone);
				row2.createCell(93).setCellValue(nSubProcessExtendedEventNoneTransactionLoopNoneCompensate);
				row2.createCell(94).setCellValue(nSubProcessExtendedEventNoneTransactionLoopStandardCompensateNone);
				row2.createCell(95).setCellValue(nSubProcessExtendedEventNoneTransactionLoopStandardCompensate);
				row2.createCell(96).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone);
				row2.createCell(97).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate);
				row2.createCell(98).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone);
				row2.createCell(99).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate);
				row2.createCell(100).setCellValue(nSubProcessExtendedEventLoopNoneCompensateNone);
				row2.createCell(101).setCellValue(nSubProcessExtendedEventLoopNoneCompensate);
				row2.createCell(102).setCellValue(nSubProcessExtendedEventLoopStandardCompensateNone);
				row2.createCell(103).setCellValue(nSubProcessExtendedEventLoopStandardCompensate);
				row2.createCell(104).setCellValue(nSubProcessExtendedEventLoopMIParallelCompensateNone);
				row2.createCell(105).setCellValue(nSubProcessExtendedEventLoopMIParallelCompensate);
				row2.createCell(106).setCellValue(nSubProcessExtendedEventLoopMISequentialCompensateNone);
				row2.createCell(107).setCellValue(nSubProcessExtendedEventLoopMISequentialCompensate);
				row2.createCell(108).setCellValue(nSubProcessExtendedEventAdHocLoopNoneCompensateNone);
				row2.createCell(109).setCellValue(nSubProcessExtendedEventAdHocLoopNoneCompensate);
				row2.createCell(110).setCellValue(nSubProcessExtendedEventAdHocLoopStandardCompensateNone);
				row2.createCell(111).setCellValue(nSubProcessExtendedEventAdHocLoopStandardCompensate);
				row2.createCell(112).setCellValue(nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone);
				row2.createCell(113).setCellValue(nSubProcessExtendedEventAdHocLoopMIParallelCompensate);
				row2.createCell(114).setCellValue(nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone);
				row2.createCell(115).setCellValue(nSubProcessExtendedEventAdHocLoopMISequentialCompensate);
				row2.createCell(116).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone);
				row2.createCell(117).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate);
				row2.createCell(118).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone);
				row2.createCell(119).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate);
				row2.createCell(120).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone);
				row2.createCell(121).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate);
				row2.createCell(122).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone);
				row2.createCell(123).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate);
				row2.createCell(124).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone);
				row2.createCell(125).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate);
				row2.createCell(126).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopStandardCompensateNone);
				row2.createCell(127).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopStandardCompensate);
				row2.createCell(128).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone);
				row2.createCell(129).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate);
				row2.createCell(130).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone);
				row2.createCell(131).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate);
				row2.createCell(132).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone);
				row2.createCell(133).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate);
				row2.createCell(134).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopStandardCompensateNone);
				row2.createCell(135).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopStandardCompensate);
				row2.createCell(136).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensateNone);
				row2.createCell(137).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensate);
				row2.createCell(138).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensateNone);
				row2.createCell(139).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensate);
				row2.createCell(140).setCellValue(nSubProcessCollapsedEventLoopNoneCompensateNone);
				row2.createCell(141).setCellValue(nSubProcessCollapsedEventLoopNoneCompensate);
				row2.createCell(142).setCellValue(nSubProcessCollapsedEventLoopStandardCompensateNone);
				row2.createCell(143).setCellValue(nSubProcessCollapsedEventLoopStandardCompensate);
				row2.createCell(144).setCellValue(nSubProcessCollapsedEventLoopMIParallelCompensateNone);
				row2.createCell(145).setCellValue(nSubProcessCollapsedEventLoopMIParallelCompensate);
				row2.createCell(146).setCellValue(nSubProcessCollapsedEventLoopMISequentialCompensateNone);
				row2.createCell(147).setCellValue(nSubProcessCollapsedEventLoopMISequentialCompensate);
				row2.createCell(148).setCellValue(nSubProcessCollapsedEventLoopNoneCompensateNone);
				row2.createCell(149).setCellValue(nSubProcessCollapsedEventAdHocLoopNoneCompensate);
				row2.createCell(150).setCellValue(nSubProcessCollapsedEventAdHocLoopStandardCompensateNone);
				row2.createCell(151).setCellValue(nSubProcessCollapsedEventAdHocLoopStandardCompensate);
				row2.createCell(152).setCellValue(nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone);
				row2.createCell(153).setCellValue(nSubProcessCollapsedEventAdHocLoopMIParallelCompensate);
				row2.createCell(154).setCellValue(nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone);
				row2.createCell(155).setCellValue(nSubProcessCollapsedEventAdHocLoopMISequentialCompensate);
				row2.createCell(156).setCellValue(nDataObject);
				row2.createCell(157).setCellValue(nDataObjectCollection);
				row2.createCell(158).setCellValue(nDataObjectReference);
				row2.createCell(159).setCellValue(nDataStore);
				row2.createCell(160).setCellValue(nDataInput);
				row2.createCell(161).setCellValue(nDataOutput);
				row2.createCell(162).setCellValue(nExclusiveGatewayNoMarker);
				row2.createCell(163).setCellValue(nExclusiveGatewayMarker);
				row2.createCell(164).setCellValue(nParallelGateway);
				row2.createCell(165).setCellValue(nInclusiveGateway);
				row2.createCell(166).setCellValue(nEventBasedGateway);
				row2.createCell(167).setCellValue(nEventBasedGatewayExclusiveInstantiation);
				row2.createCell(168).setCellValue(nEventBasedGatewayParallelInstantiation);
				row2.createCell(169).setCellValue(nComplexGateway);
				row2.createCell(170).setCellValue(nStartMultipleParallelEventDefinition);
				row2.createCell(171).setCellValue(nStartMultipleEventDefinition);
				row2.createCell(172).setCellValue(nStartNoneEventDefinition);
				row2.createCell(173).setCellValue(nStartSignalEventDefinition);
				row2.createCell(174).setCellValue(nStartConditionalEventDefinition);
				row2.createCell(175).setCellValue(nStartTimerEventDefinition);
				row2.createCell(176).setCellValue(nStartMessageEventDefinition);
				row2.createCell(177).setCellValue(nStartMessageEventSubProcessInterruptingDefinition);
				row2.createCell(178).setCellValue(nStartTimerEventSubProcessInterruptingDefinition);
				row2.createCell(179).setCellValue(nStartEscalationEventSubProcessInterruptingDefinition);
				row2.createCell(180).setCellValue(nStartConditionalEventSubProcessInterruptingDefinition);
				row2.createCell(181).setCellValue(nStartErrorEventSubProcessInterruptingDefinition);
				row2.createCell(182).setCellValue(nStartCompensateEventSubProcessInterruptingDefinition);
				row2.createCell(183).setCellValue(nStartSignalEventSubProcessInterruptingDefinition);
				row2.createCell(184).setCellValue(nStartMultipleEventSubProcessInterruptingDefinition);
				row2.createCell(185).setCellValue(nStartMultipleParallelEventSubProcessInterruptingDefinition);       
				row2.createCell(186).setCellValue(nStartMessageEventSubProcessNonInterruptingDefinition);
				row2.createCell(187).setCellValue(nStartTimerEventSubProcessNonInterruptingDefinition);
				row2.createCell(188).setCellValue(nStartEscalationEventSubProcessNonInterruptingDefinition);
				row2.createCell(189).setCellValue(nStartConditionalEventSubProcessNonInterruptingDefinition);
				row2.createCell(190).setCellValue(nStartSignalEventSubProcessNonInterruptingDefinition);
				row2.createCell(191).setCellValue(nStartMultipleParallelEventSubProcessNonInterruptingDefinition);
				row2.createCell(192).setCellValue(nStartMultipleEventSubProcessNonInterruptingDefinition);       
				row2.createCell(193).setCellValue(nEndNoneEventDefinition);
				row2.createCell(194).setCellValue(nEndMultipleEventDefinition); 
				row2.createCell(195).setCellValue(nEndEscalationEventDefinition);
				row2.createCell(196).setCellValue(nEndErrorEventDefinition);
				row2.createCell(197).setCellValue(nEndSignalEventDefinition);
				row2.createCell(198).setCellValue(nEndCompensateEventDefinition);
				row2.createCell(199).setCellValue(nEndCancelEventDefinition); 
				row2.createCell(200).setCellValue(nEndMessageEventDefinition);
				row2.createCell(201).setCellValue(nEndTerminateEventDefinition);
				row2.createCell(202).setCellValue(nIntermediateCatchMultipleEventDefinition);
				row2.createCell(203).setCellValue(nIntermediateCatchMultipleParallelEventDefinition);
				row2.createCell(204).setCellValue(nIntermediateCatchMessageEventDefinition);
				row2.createCell(205).setCellValue(nIntermediateCatchTimerEventDefinition);
				row2.createCell(206).setCellValue(nIntermediateCatchConditionalEventDefinition);
				row2.createCell(207).setCellValue(nIntermediateCatchLinkEventDefinition);
				row2.createCell(208).setCellValue(nIntermediateCatchSignalEventDefinition);
				row2.createCell(209).setCellValue(nIntermediateThrowNoneEventDefinition);
				row2.createCell(210).setCellValue(nIntermediateThrowMessageEventDefinition);
				row2.createCell(211).setCellValue(nIntermediateThrowEscalationEventDefinition);
				row2.createCell(212).setCellValue(nIntermediateThrowLinkEventDefinition);
				row2.createCell(213).setCellValue(nIntermediateThrowSignalEventDefinition);
				row2.createCell(214).setCellValue(nIntermediateThrowCompensateEventDefinition);
				row2.createCell(215).setCellValue(nIntermediateThrowMultipleEventDefinition);
				row2.createCell(216).setCellValue(nIntermediateBoundaryMessageEvent);
				row2.createCell(217).setCellValue(nIntermediateBoundaryTimerEvent);
				row2.createCell(218).setCellValue(nIntermediateBoundaryCancelEvent);
				row2.createCell(219).setCellValue(nIntermediateBoundaryConditionalEvent);
				row2.createCell(220).setCellValue(nIntermediateBoundaryEscalationEvent);
				row2.createCell(221).setCellValue(nIntermediateBoundaryErrorEvent);
				row2.createCell(222).setCellValue(nIntermediateBoundarySignalEvent);
				row2.createCell(223).setCellValue(nIntermediateBoundaryCompensateEvent);
				row2.createCell(224).setCellValue(nIntermediateBoundaryMultipleEvent);
				row2.createCell(225).setCellValue(nIntermediateBoundaryMultipleParallelEvent);
				row2.createCell(226).setCellValue(nIntermediateBoundaryTimerEventNonInterrupting);
				row2.createCell(227).setCellValue(nIntermediateBoundaryEscalationEventNonInterrupting);
				row2.createCell(228).setCellValue(nIntermediateBoundaryConditionalEventNonInterrupting);
				row2.createCell(229).setCellValue(nIntermediateBoundaryMessageEventNonInterrupting);
				row2.createCell(230).setCellValue(nIntermediateBoundarySignalEventNonInterrupting);
				row2.createCell(231).setCellValue(nIntermediateBoundaryMultipleEventNonInterrupting);
				row2.createCell(232).setCellValue(nIntermediateBoundaryMultipleParallelEventNonInterrupting);
				row2.createCell(233).setCellValue(nMessageFlow);
				row2.createCell(234).setCellValue(nSequenceFlow);
				row2.createCell(235).setCellValue(nDefaultFlow);
				row2.createCell(236).setCellValue(nConditionalFlow);
				row2.createCell(237).setCellValue(nLane); 
				row2.createCell(238).setCellValue(nPoolCollapsedMultiplicityNone);
				row2.createCell(239).setCellValue(nPoolCollapsedMultiplicity);
				row2.createCell(240).setCellValue(nPoolExpandedMultiplicityNone);
				row2.createCell(241).setCellValue(nPoolExpandedMultiplicity);
				row2.createCell(242).setCellValue(nChoreographyTask);
				row2.createCell(243).setCellValue(nChoreographyMessage);            
				row2.createCell(244).setCellValue(nChoreographyTaskSequentialMultipleInstance);
				row2.createCell(245).setCellValue(nChoreographyTaskParallelMultipleInstance);
				row2.createCell(246).setCellValue(nChoreographyTaskLoop);
				row2.createCell(247).setCellValue(nChoreographySubprocessCollapsed);
				row2.createCell(248).setCellValue(nChoreographySubprocessCollapsedParallelMultipleInstance);
				row2.createCell(249).setCellValue(nChoreographySubprocessCollapsedSequentialMultipleInstance);
				row2.createCell(250).setCellValue(nChoreographySubprocessCollapsedLoop);
				row2.createCell(251).setCellValue(nChoreographySubprocessCollapsedCall);
				row2.createCell(252).setCellValue(nChoreographySubprocessCollapsedCallSequentialMultipleInstance);
				row2.createCell(253).setCellValue(nChoreographySubprocessCollapsedCallParallelMultipleInstance);
				row2.createCell(254).setCellValue(nChoreographySubprocessCollapsedCallLoop);
				row2.createCell(255).setCellValue(nChoreographySubprocessExpanded);
				row2.createCell(256).setCellValue(nChoreographySubprocessExpandedSequentialMultipleInstance);
				row2.createCell(257).setCellValue(nChoreographySubprocessExpandedParallelMultipleInstance);
				row2.createCell(258).setCellValue(nChoreographySubprocessExpandedLoop);
				row2.createCell(259).setCellValue(nChoreographyParticipant);
				row2.createCell(260).setCellValue(nChoreographyParticipantMultiple);       
				row2.createCell(261).setCellValue(nConversationNone);
				row2.createCell(262).setCellValue(nConversationSubProcess);
				row2.createCell(263).setCellValue(nConversationCall);
				row2.createCell(264).setCellValue(nConversationSubProcessCall);
				row2.createCell(265).setCellValue(nConversationLink);
				row2.createCell(266).setCellValue(nAssociationCompensate);
				row2.createCell(267).setCellValue(nAssociationUndirected);
				row2.createCell(268).setCellValue(nAssociationUnidirectional);        
				row2.createCell(269).setCellValue(nAssociationBidirectional);
				row2.createCell(270).setCellValue(nAssociationDataOutput);
				row2.createCell(271).setCellValue(nAssociationDataInput);            
				row2.createCell(272).setCellValue(nGroup);
				row2.createCell(273).setCellValue(nTextAnnotation);
				row2.createCell(274).setCellValue(TotalElements);           

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

			
			FileOutputStream fileOut = new FileOutputStream("bpmn_stats2.xlsx");
			wb.write(fileOut);  
			//closing the Stream  
			fileOut.close();  
			//System.out.println(fileName+": Analysis DONE");
		}
		
		//closing the workbook  
		wb.close(); 
		} catch (Exception e) {
	        System.out.println("Exception: "+e.getMessage());
	        //writer.write(fileEntry.getName()+","+"invalid"+", "+e.getMessage().replace(",", "-")+"\n");            
	        //writer.write(fileEntry.getName()+","+"invalid"+", "+exp+"\n"); 
			return;

	    }
		System.out.println("Analysis DONE");
	}
	}
	
