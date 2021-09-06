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
        rowhead.createCell(0).setCellValue("fileName");  
  		rowhead.createCell(1).setCellValue("bpmnModeler");  
  		rowhead.createCell(2).setCellValue("isEnglish");
  		rowhead.createCell(3).setCellValue("modelType");
  		
  		// Task
  		rowhead.createCell(4).setCellValue("nTask");
        rowhead.createCell(5).setCellValue("nTaskMultipleInstanceParallel");
        rowhead.createCell(6).setCellValue("nTaskMultipleInstanceSequential");
        rowhead.createCell(7).setCellValue("nTaskLoopActivity");
        rowhead.createCell(8).setCellValue("nReceiveTask");
        rowhead.createCell(9).setCellValue("nSendTask");
        rowhead.createCell(10).setCellValue("nUserTask");
        rowhead.createCell(11).setCellValue("nManualTask");
        rowhead.createCell(12).setCellValue("nBusinessRuleTask");
        rowhead.createCell(13).setCellValue("nServiceTask");
        rowhead.createCell(14).setCellValue("nScriptTask");  
        
        // Call Activity Typed
        rowhead.createCell(15).setCellValue("nCallActivity");
        
        // SubProcess TODO


        // Data Objects
        rowhead.createCell(25).setCellValue("nDataObject");
        rowhead.createCell(25).setCellValue("nDataObjectCollection");
        rowhead.createCell(26).setCellValue("nDataObjectReference");
        rowhead.createCell(27).setCellValue("nDataStore");
        rowhead.createCell(29).setCellValue("nDataInput");
        rowhead.createCell(30).setCellValue("nDataOutput");
        // TODO rowhead.createCell(30).setCellValue("nDataNone"); data object none
        
        // Gateway
        rowhead.createCell(31).setCellValue("nExclusiveGatewayEmpty");
        rowhead.createCell(31).setCellValue("nExclusiveGateway");
        rowhead.createCell(32).setCellValue("nParallelGateway");
        rowhead.createCell(33).setCellValue("nInclusiveGateway");
        rowhead.createCell(34).setCellValue("nEventBasedGateway");
        rowhead.createCell(35).setCellValue("nParallelEventBasedGateway");
        rowhead.createCell(36).setCellValue("nComplexGateway");
        
        // Flow
        rowhead.createCell(88).setCellValue("nMessageFlow");
        rowhead.createCell(89).setCellValue("nSequenceFlow");
        rowhead.createCell(90).setCellValue("nDefaultFlow");
        rowhead.createCell(91).setCellValue("nConditionalFlow");
        
        // Pool & Lane
        rowhead.createCell(24).setCellValue("nLane");
        rowhead.createCell(92).setCellValue("nExpandedPool");
        rowhead.createCell(94).setCellValue("nCollapsedPool");
        rowhead.createCell(93).setCellValue("nMultipleInstancePool");                                   
        rowhead.createCell(95).setCellValue("nVerticalLane");
        rowhead.createCell(96).setCellValue("nVerticalPool");
        
        // Choreography
        rowhead.createCell(97).setCellValue("nChoreographyTask");
        rowhead.createCell(98).setCellValue("nChoreographyParticipant");
        rowhead.createCell(99).setCellValue("nChoreographySubprocess");
        
        // Conversation
        rowhead.createCell(100).setCellValue("nConversation");
        rowhead.createCell(101).setCellValue("nSubConversation");
        rowhead.createCell(102).setCellValue("nCallConversation");
        rowhead.createCell(103).setCellValue("nConversationLink");
        
        // Association
        rowhead.createCell(105).setCellValue("nAssociation");
        rowhead.createCell(106).setCellValue("nCompensateAssociation");
        rowhead.createCell(107).setCellValue("nUnidirectionalAssociation");
        rowhead.createCell(108).setCellValue("nUndirectedAssociation");
        rowhead.createCell(109).setCellValue("nBidirectionalAssociation");
        rowhead.createCell(111).setCellValue("ndataOutputAssociation");
        rowhead.createCell(112).setCellValue("ndataInputAssociation");
        
        // Start Events
        rowhead.createCell(38).setCellValue("nStartNoneEvent");
        rowhead.createCell(39).setCellValue("nStartMultipleParallelEventDefinition");
        rowhead.createCell(40).setCellValue("nStartMultipleEventDefinition");
        rowhead.createCell(41).setCellValue("nStartSignalEventDefinition");
        rowhead.createCell(42).setCellValue("nStartConditionalEventDefinition");
        rowhead.createCell(43).setCellValue("nStartTimerEventDefinition");
        rowhead.createCell(44).setCellValue("nStartMessageEventDefinition");
        rowhead.createCell(45).setCellValue("nStartCompensateEventDefinition");
        rowhead.createCell(46).setCellValue("nStartEscalationEventDefinition");
        rowhead.createCell(47).setCellValue("nStartErrorEventDefinition");
        
        // Start Events Sub Process Interrupting
        rowhead.createCell(44).setCellValue("nStartMessageEventSubProcessInterruptingDefinition");
        rowhead.createCell(43).setCellValue("nStartTimerEventSubProcessInterruptingDefinition");
        rowhead.createCell(46).setCellValue("nStartEscalationEventSubProcessInterruptingDefinition");
        rowhead.createCell(42).setCellValue("nStartConditionalEventSubProcessInterruptingDefinition");
        rowhead.createCell(47).setCellValue("nStartErrorEventSubProcessInterruptingDefinition");
        rowhead.createCell(45).setCellValue("nStartCompensateEventSubProcessInterruptingDefinition");
        rowhead.createCell(41).setCellValue("nStartSignalEventSubProcessInterruptingDefinition");
        rowhead.createCell(39).setCellValue("nStartMultipleParallelEventSubProcessInterruptingDefinition");
        rowhead.createCell(40).setCellValue("nStartMultipleEventSubProcessInterruptingDefinition");   
        
        // Start Events Sub Process NON Interrupting
        rowhead.createCell(44).setCellValue("nStartMessageEventSubProcessNonInterruptingDefinition");
        rowhead.createCell(43).setCellValue("nStartTimerEventSubProcessNonInterruptingDefinition");
        rowhead.createCell(46).setCellValue("nStartEscalationEventSubProcessNonInterruptingDefinition");
        rowhead.createCell(42).setCellValue("nStartConditionalEventSubProcessNonInterruptingDefinition");
        rowhead.createCell(41).setCellValue("nStartSignalEventSubProcessNonInterruptingDefinition");
        rowhead.createCell(39).setCellValue("nStartMultipleParallelEventSubProcessNonInterruptingDefinition");
        rowhead.createCell(40).setCellValue("nStartMultipleEventSubProcessNonInterruptingDefinition"); 

        // End Events
        rowhead.createCell(48).setCellValue("nEndNoneEvent");
        rowhead.createCell(49).setCellValue("nEndMultipleEventDefinition"); 
        rowhead.createCell(50).setCellValue("nEndEscalationEventDefinition");
        rowhead.createCell(51).setCellValue("nEndErrorEventDefinition");
        rowhead.createCell(52).setCellValue("nEndSignalEventDefinition");
        rowhead.createCell(53).setCellValue("nEndCompensateEventDefinition");
        rowhead.createCell(54).setCellValue("nEndCancelEventDefinition"); 
        rowhead.createCell(55).setCellValue("nEndMessageEventDefinition");
        rowhead.createCell(56).setCellValue("nEndTerminateEventDefinition");
        
        //Intermediate Events Catch 
        rowhead.createCell(57).setCellValue("nIntermediateCatchMultipleEventDefinition");
        rowhead.createCell(58).setCellValue("nIntermediateCatchMultipleParallelEventDefinition");
        rowhead.createCell(59).setCellValue("nIntermediateCatchMessageEventDefinition");
        rowhead.createCell(60).setCellValue("nIntermediateCatchTimerEventDefinition");
        rowhead.createCell(61).setCellValue("nIntermediateCatchConditionalEventDefinition");
        rowhead.createCell(62).setCellValue("nIntermediateCatchLinkEventDefinition");
        rowhead.createCell(63).setCellValue("nIntermediateCatchSignalEventDefinition");
        
        // Intermediate Boundary Interrupting
        rowhead.createCell(71).setCellValue("nBoundaryMessageEvent");
        rowhead.createCell(72).setCellValue("nBoundaryTimerEvent");
        rowhead.createCell(73).setCellValue("nBoundaryCancelEvent");
        rowhead.createCell(74).setCellValue("nBoundaryConditionalEvent");
        rowhead.createCell(75).setCellValue("nBoundaryEscalationEvent");
        rowhead.createCell(76).setCellValue("nBoundaryErrorEvent");
        rowhead.createCell(77).setCellValue("nBoundarySignalEvent");
        rowhead.createCell(78).setCellValue("nBoundaryCompensateEvent");
        rowhead.createCell(79).setCellValue("nBoundaryMultipleEvent");
        rowhead.createCell(80).setCellValue("nBoundaryMultipleParallelEvent");
        
        //Intermediate Boundary NON Interrupting
        rowhead.createCell(81).setCellValue("nBoundaryTimerEventNonInt");
        rowhead.createCell(82).setCellValue("nBoundaryEscalationEventNonInt");
        rowhead.createCell(83).setCellValue("nBoundaryConditionalEventNonInt");
        rowhead.createCell(84).setCellValue("nBoundaryMessageEventNonInt");
        rowhead.createCell(85).setCellValue("nBoundarySignalEventNonInt");
        rowhead.createCell(86).setCellValue("nBoundaryMultipleEventNonInt");
        rowhead.createCell(87).setCellValue("nBoundaryMultipleParallelEventNonInt");
        
        //Intermediate Throwhead Events  
        rowhead.createCell(64).setCellValue("nIntermediateThrowheadNoneEventDefinition");
        rowhead.createCell(65).setCellValue("nIntermediateThrowheadMessageEventDefinition");
        rowhead.createCell(66).setCellValue("nIntermediateThrowheadEscalationEventDefinition");
        rowhead.createCell(67).setCellValue("nIntermediateThrowheadLinkEventDefinition");
        rowhead.createCell(68).setCellValue("nIntermediateThrowheadSignalEventDefinition");
        rowhead.createCell(69).setCellValue("nIntermediateThrowheadCompensateEventDefinition");
        rowhead.createCell(70).setCellValue("nIntermediateThrowheadMultipleParallelEventDefinition");

        // OTHERS
        rowhead.createCell(104).setCellValue("nITSystem");
        rowhead.createCell(110).setCellValue("nTextAnnotation");
        rowhead.createCell(23).setCellValue("nGroup");
        rowhead.createCell(37).setCellValue("nCondition");
        rowhead.createCell(113).setCellValue("nOfExtensionElements");
        rowhead.createCell(114).setCellValue("TotalElements");
        
        
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
        //Process Subprocess or Collaboration
        String modelType = null;
        
        int nTask=0;
        int nTaskMultipleInstanceSequential=0; //to add
        int nTaskMultipleInstanceParallel=0;
        int nTaskLoopActivity=0;
        int nReceiveTask=0;
        int nSendTask=0;
        int nUserTask=0;
        int nManualTask=0;
        int nBusinessRuleTask=0;
        int nServiceTask=0;
        int nScriptTask=0;        
        int nCallActivity=0;
        
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
        int nPoolExpanded=0;
        int nPoolCollapsed=0;
        int nPoolExpandedMultipleInstance=0;
        int nPoolCollapsedMultipleInstance=0;
        int nVerticalLane=0;
        int nVerticalPool=0;
        int nLane=0;
                
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
        boolean isEnglish=false;
            
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
      		row.createCell(0).setCellValue(fileName);  
      		row.createCell(1).setCellValue(bpmnModeler);  
      		row.createCell(2).setCellValue(isEnglish);
      		row.createCell(3).setCellValue(modelType);
      		
      		// Task
      		row.createCell(4).setCellValue(nTask);
            row.createCell(5).setCellValue(nTaskMultipleInstanceParallel);
            row.createCell(6).setCellValue(nTaskMultipleInstanceSequential);
            row.createCell(7).setCellValue(nTaskLoopActivity);
            row.createCell(8).setCellValue(nReceiveTask);
            row.createCell(9).setCellValue(nSendTask);
            row.createCell(10).setCellValue(nUserTask);
            row.createCell(11).setCellValue(nManualTask);
            row.createCell(12).setCellValue(nBusinessRuleTask);
            row.createCell(13).setCellValue(nServiceTask);
            row.createCell(14).setCellValue(nScriptTask);       
            row.createCell(15).setCellValue(nCallActivity);
            
            // SubProcess
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocNoneLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocNoneLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocNoneLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocNoneLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionNoneLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionNoneLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionNoneLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionNoneLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocNoneLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionNoneLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocNoneLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocNoneLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocNoneLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocNoneLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocNoneLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocNoneLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventAdHocLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionNoneLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionNoneLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionNoneLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionNoneLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionNoneLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionNoneLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessExtendedEventTransactionLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneAdHocNoneLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneTransactionNoneLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventAdHocNoneLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventAdHocLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventAdHocLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventAdHocLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventAdHocLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventAdHocLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventAdHocLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventTransactionNoneLoopMISequentialCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventTransactionLoopNoneCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventTransactionLoopNoneCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventTransactionLoopParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventTransactionLoopParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventTransactionLoopMIParallelCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventTransactionLoopMIParallelCompensate);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventTransactionLoopMISequentialCompensateNone);
            row.createCell(16).setCellValue(nSubProcessCollapsedEventTransactionLoopMISequentialCompensate);
           
            // Data Objects
            row.createCell(25).setCellValue(nDataObject);
            row.createCell(26).setCellValue(nDataObjectReference);
            row.createCell(28).setCellValue(nDataStore);
            row.createCell(29).setCellValue(nDataInput);
            row.createCell(30).setCellValue(nDataOutput);
            
            // Gateway
            row.createCell(31).setCellValue(nExclusiveGatewayNoMarker);
            row.createCell(31).setCellValue(nExclusiveGatewayMarker);
            row.createCell(32).setCellValue(nParallelGateway);
            row.createCell(33).setCellValue(nInclusiveGateway);
            row.createCell(34).setCellValue(nEventBasedGateway);
            row.createCell(34).setCellValue(nEventBasedGatewayExclusiveInstantiation);
            row.createCell(34).setCellValue(nEventBasedGatewayParallelInstantiation);
            row.createCell(36).setCellValue(nComplexGateway);
                      
            // Flow
            row.createCell(88).setCellValue(nMessageFlow);
            row.createCell(89).setCellValue(nSequenceFlow);
            row.createCell(90).setCellValue(nDefaultFlow);
            row.createCell(91).setCellValue(nConditionalFlow);
            
            // Pool & Lane
            row.createCell(24).setCellValue(nLane);
            row.createCell(92).setCellValue(nPoolExpanded);
            row.createCell(94).setCellValue(nPoolCollapsed);
            row.createCell(93).setCellValue(nPoolExpandedMultipleInstance);                                   
            row.createCell(95).setCellValue(nVerticalLane);
            row.createCell(96).setCellValue(nVerticalPool);
            
            // Choreography
            row.createCell(97).setCellValue(nChoreographyTask);
            row.createCell(98).setCellValue(nChoreographyParticipant);
            row.createCell(99).setCellValue(nChoreographySubprocess);
            
            // Conversation
            row.createCell(100).setCellValue(nConversationNone);
            row.createCell(101).setCellValue(nConversationSubProcess);
            row.createCell(102).setCellValue(nConversationCall);
            row.createCell(103).setCellValue(nConversationLink);
            
            // Association
            row.createCell(106).setCellValue(nAssociationCompensate);
            row.createCell(107).setCellValue(nAssociationUnidirectional);
            row.createCell(108).setCellValue(nAssociationUndirected);
            row.createCell(109).setCellValue(nAssociationBidirectional);
            row.createCell(111).setCellValue(nAssociationDataOutput);
            row.createCell(112).setCellValue(nAssociationDataInput);
            
            // Start Events
            row.createCell(38).setCellValue(nStartNoneEventDefinition);
            row.createCell(39).setCellValue(nStartMultipleParallelEventDefinition);
            row.createCell(40).setCellValue(nStartMultipleEventDefinition);
            row.createCell(41).setCellValue(nStartSignalEventDefinition);
            row.createCell(42).setCellValue(nStartConditionalEventDefinition);
            row.createCell(43).setCellValue(nStartTimerEventDefinition);
            row.createCell(44).setCellValue(nStartMessageEventDefinition);
            row.createCell(45).setCellValue(nStartCompensateEventDefinition);
            row.createCell(46).setCellValue(nStartEscalationEventDefinition);
            row.createCell(47).setCellValue(nStartErrorEventDefinition);
            
            // Start Events Sub Process Interrupting
            row.createCell(44).setCellValue(nStartMessageEventSubProcessInterruptingDefinition);
            row.createCell(43).setCellValue(nStartTimerEventSubProcessInterruptingDefinition);
            row.createCell(46).setCellValue(nStartEscalationEventSubProcessInterruptingDefinition);
            row.createCell(42).setCellValue(nStartConditionalEventSubProcessInterruptingDefinition);
            row.createCell(47).setCellValue(nStartErrorEventSubProcessInterruptingDefinition);
            row.createCell(45).setCellValue(nStartCompensateEventSubProcessInterruptingDefinition);
            row.createCell(41).setCellValue(nStartSignalEventSubProcessInterruptingDefinition);
            row.createCell(39).setCellValue(nStartMultipleParallelEventSubProcessInterruptingDefinition);
            row.createCell(40).setCellValue(nStartMultipleEventSubProcessInterruptingDefinition);   
            
            // Start Events Sub Process NON Interrupting
            row.createCell(44).setCellValue(nStartMessageEventSubProcessNonInterruptingDefinition);
            row.createCell(43).setCellValue(nStartTimerEventSubProcessNonInterruptingDefinition);
            row.createCell(46).setCellValue(nStartEscalationEventSubProcessNonInterruptingDefinition);
            row.createCell(42).setCellValue(nStartConditionalEventSubProcessNonInterruptingDefinition);
            row.createCell(41).setCellValue(nStartSignalEventSubProcessNonInterruptingDefinition);
            row.createCell(39).setCellValue(nStartMultipleParallelEventSubProcessNonInterruptingDefinition);
            row.createCell(40).setCellValue(nStartMultipleEventSubProcessNonInterruptingDefinition); 
 
            // End Events
            row.createCell(48).setCellValue(nEndNoneEventDefinition);
            row.createCell(49).setCellValue(nEndMultipleEventDefinition); 
            row.createCell(50).setCellValue(nEndEscalationEventDefinition);
            row.createCell(51).setCellValue(nEndErrorEventDefinition);
            row.createCell(52).setCellValue(nEndSignalEventDefinition);
            row.createCell(53).setCellValue(nEndCompensateEventDefinition);
            row.createCell(54).setCellValue(nEndCancelEventDefinition); 
            row.createCell(55).setCellValue(nEndMessageEventDefinition);
            row.createCell(56).setCellValue(nEndTerminateEventDefinition);
            
            //Intermediate Events Catch 
            row.createCell(57).setCellValue(nIntermediateCatchMultipleEventDefinition);
            row.createCell(58).setCellValue(nIntermediateCatchMultipleParallelEventDefinition);
            row.createCell(59).setCellValue(nIntermediateCatchMessageEventDefinition);
            row.createCell(60).setCellValue(nIntermediateCatchTimerEventDefinition);
            row.createCell(61).setCellValue(nIntermediateCatchConditionalEventDefinition);
            row.createCell(62).setCellValue(nIntermediateCatchLinkEventDefinition);
            row.createCell(63).setCellValue(nIntermediateCatchSignalEventDefinition);
            
            // Intermediate Boundary Interrupting
            row.createCell(71).setCellValue(nIntermediateBoundaryMessageEvent);
            row.createCell(72).setCellValue(nIntermediateBoundaryTimerEvent);
            row.createCell(73).setCellValue(nIntermediateBoundaryCancelEvent);
            row.createCell(74).setCellValue(nIntermediateBoundaryConditionalEvent);
            row.createCell(75).setCellValue(nIntermediateBoundaryEscalationEvent);
            row.createCell(76).setCellValue(nIntermediateBoundaryErrorEvent);
            row.createCell(77).setCellValue(nIntermediateBoundarySignalEvent);
            row.createCell(78).setCellValue(nIntermediateBoundaryCompensateEvent);
            row.createCell(79).setCellValue(nIntermediateBoundaryMultipleEvent);
            row.createCell(80).setCellValue(nIntermediateBoundaryMultipleParallelEvent);
            
            //Intermediate Boundary NON Interrupting
            row.createCell(81).setCellValue(nIntermediateBoundaryTimerEventNonInterrupting);
            row.createCell(82).setCellValue(nIntermediateBoundaryEscalationEventNonInterrupting);
            row.createCell(83).setCellValue(nIntermediateBoundaryConditionalEventNonInterrupting);
            row.createCell(84).setCellValue(nIntermediateBoundaryMessageEventNonInterrupting);
            row.createCell(85).setCellValue(nIntermediateBoundarySignalEventNonInterrupting);
            row.createCell(86).setCellValue(nIntermediateBoundaryMultipleEventNonInterrupting);
            row.createCell(87).setCellValue(nIntermediateBoundaryMultipleParallelEventNonInterrupting);
            
            //Intermediate Throw Events  
            row.createCell(64).setCellValue(nIntermediateThrowNoneEventDefinition);
            row.createCell(65).setCellValue(nIntermediateThrowMessageEventDefinition);
            row.createCell(66).setCellValue(nIntermediateThrowEscalationEventDefinition);
            row.createCell(67).setCellValue(nIntermediateThrowLinkEventDefinition);
            row.createCell(68).setCellValue(nIntermediateThrowSignalEventDefinition);
            row.createCell(69).setCellValue(nIntermediateThrowCompensateEventDefinition);
            row.createCell(70).setCellValue(nIntermediateThrowMultipleParallelEventDefinition);
  
            // OTHERS
            row.createCell(110).setCellValue(nTextAnnotation);
            row.createCell(23).setCellValue(nGroup);
            row.createCell(37).setCellValue(nCondition);
            row.createCell(113).setCellValue(nOfExtensionElements);
            row.createCell(114).setCellValue(TotalElements);
            
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