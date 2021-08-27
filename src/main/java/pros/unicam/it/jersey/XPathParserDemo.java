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
import org.xml.sax.InputSource;

public class XPathParserDemo {

    public static void main(String[] args) throws Exception {
    	
    	
    	
        
        
        //Creation of the xls empty file
        Workbook wb = new HSSFWorkbook();    
        HSSFSheet sheet = (HSSFSheet) wb.createSheet("BPMN_Stats"); 
        HSSFRow rowhead = sheet.createRow((short)0);         
	    //creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method  
        rowhead.createCell(0).setCellValue("File Name");  
        rowhead.createCell(1).setCellValue("BPMN Modeler"); 
        rowhead.createCell(2).setCellValue("isEnglish");
        rowhead.createCell(3).setCellValue("Model Type");  
        rowhead.createCell(4).setCellValue("nTask");
        rowhead.createCell(5).setCellValue("nTaskMultipleInstance");
        rowhead.createCell(6).setCellValue("nTaskMultipleInstanceSequential");
        rowhead.createCell(7).setCellValue("nTaskLoopActivity");
        rowhead.createCell(8).setCellValue("nReceiveTask");
        rowhead.createCell(9).setCellValue("nSendTask");
        rowhead.createCell(10).setCellValue("nUserTask");
        rowhead.createCell(11).setCellValue("nManualTask");
        rowhead.createCell(12).setCellValue("nBusinessRuleTask");
        rowhead.createCell(13).setCellValue("nServiceTask");
        rowhead.createCell(14).setCellValue("nScriptTask");       
        rowhead.createCell(15).setCellValue("nCallActivity");
        rowhead.createCell(16).setCellValue("nSubProcess");
        rowhead.createCell(17).setCellValue("nTransaction");
        rowhead.createCell(18).setCellValue("nAdHocSubProcess");
        rowhead.createCell(19).setCellValue("nEventSubProcess");
        rowhead.createCell(20).setCellValue("nGroup");
        rowhead.createCell(21).setCellValue("nLane");
        rowhead.createCell(22).setCellValue("nDataObject");
        rowhead.createCell(23).setCellValue("nDataObjectReference");
        rowhead.createCell(24).setCellValue("nDataStore");
        rowhead.createCell(25).setCellValue("nDataStoreReference");
        rowhead.createCell(26).setCellValue("nDataInput");
        rowhead.createCell(27).setCellValue("nDataOutput");
        rowhead.createCell(28).setCellValue("nExclusiveGateway");
        rowhead.createCell(29).setCellValue("nParallelGateway");
        rowhead.createCell(30).setCellValue("nInclusiveGateway");
        rowhead.createCell(31).setCellValue("nEventBasedGateway");
        rowhead.createCell(32).setCellValue("nParallelEventBasedGateway");
        rowhead.createCell(33).setCellValue("nComplexGateway");
        rowhead.createCell(34).setCellValue("nCondition");
        rowhead.createCell(35).setCellValue("nStartNoneEvent");
        rowhead.createCell(36).setCellValue("nStartMultipleParallelEventDefinition");
        rowhead.createCell(37).setCellValue("nStartMultipleEventDefinition");
        rowhead.createCell(38).setCellValue("nStartSignalEventDefinition");
        rowhead.createCell(39).setCellValue("nStartConditionalEventDefinition");
        rowhead.createCell(40).setCellValue("nStartTimerEventDefinition");
        rowhead.createCell(41).setCellValue("nStartMessageEventDefinition");
        rowhead.createCell(42).setCellValue("nStartCompensateEventDefinition");
        rowhead.createCell(43).setCellValue("nStartEscalationEventDefinition");
        rowhead.createCell(44).setCellValue("nStartErrorEventDefinition");
        rowhead.createCell(45).setCellValue("nEndNoneEvent");
        rowhead.createCell(46).setCellValue("nEndMultipleEventDefinition"); 
        rowhead.createCell(47).setCellValue("nEndEscalationEventDefinition");
        rowhead.createCell(48).setCellValue("nEndErrorEventDefinition");
        rowhead.createCell(49).setCellValue("nEndSignalEventDefinition");
        rowhead.createCell(50).setCellValue("nEndCompensateEventDefinition");
        rowhead.createCell(51).setCellValue("nEndCancelEventDefinition"); 
        rowhead.createCell(52).setCellValue("nEndMessageEventDefinition");
        rowhead.createCell(53).setCellValue("nEndTerminateEventDefinition");
        rowhead.createCell(54).setCellValue("nIntermediateCatchMultipleEventDefinition");
        rowhead.createCell(55).setCellValue("nIntermediateCatchMultipleParallelEventDefinition");
        rowhead.createCell(56).setCellValue("nIntermediateCatchMessageEventDefinition");
        rowhead.createCell(57).setCellValue("nIntermediateCatchTimerEventDefinition");
        rowhead.createCell(58).setCellValue("nIntermediateCatchConditionalEventDefinition");
        rowhead.createCell(59).setCellValue("nIntermediateCatchLinkEventDefinition");
        rowhead.createCell(60).setCellValue("nIntermediateCatchSignalEventDefinition");
        rowhead.createCell(61).setCellValue("nIntermediateThrowNoneEventDefinition");
        rowhead.createCell(62).setCellValue("nIntermediateThrowMessageEventDefinition");
        rowhead.createCell(63).setCellValue("nIntermediateThrowEscalationEventDefinition");
        rowhead.createCell(64).setCellValue("nIntermediateThrowLinkEventDefinition");
        rowhead.createCell(65).setCellValue("nIntermediateThrowSignalEventDefinition");
        rowhead.createCell(66).setCellValue("nIntermediateThrowCompensateEventDefinition");
        rowhead.createCell(67).setCellValue("nIntermediateThrowMultipleParallelEventDefinition");
        rowhead.createCell(68).setCellValue("nBoundaryMessageEvent");
        rowhead.createCell(69).setCellValue("nBoundaryTimerEvent");
        rowhead.createCell(70).setCellValue("nBoundaryCancelEvent");
        rowhead.createCell(71).setCellValue("nBoundaryConditionalEvent");
        rowhead.createCell(72).setCellValue("nBoundaryEscalationEvent");
        rowhead.createCell(73).setCellValue("nBoundaryErrorEvent");
        rowhead.createCell(74).setCellValue("nBoundarySignalEvent");
        rowhead.createCell(75).setCellValue("nBoundaryCompensateEvent");
        rowhead.createCell(76).setCellValue("nBoundaryTimerEventNonInt");
        rowhead.createCell(77).setCellValue("nBoundaryEscalationEventNonInt");
        rowhead.createCell(78).setCellValue("nBoundaryConditionalEventNonInt");
        rowhead.createCell(79).setCellValue("nBoundaryMessageEventNonInt");
        rowhead.createCell(80).setCellValue("nBoundarySignalEventNonInt");
        rowhead.createCell(81).setCellValue("nMessageFlow");
        rowhead.createCell(82).setCellValue("nSequenceFlow");
        rowhead.createCell(83).setCellValue("nDefaultFlow");
        rowhead.createCell(84).setCellValue("nConditionalFlow");
        rowhead.createCell(85).setCellValue("nPool");
        rowhead.createCell(86).setCellValue("nCollapsedPool");       
        rowhead.createCell(87).setCellValue("nVerticalLane");
        rowhead.createCell(88).setCellValue("nVerticalPool");
        rowhead.createCell(89).setCellValue("nChoreographyTask");
        rowhead.createCell(90).setCellValue("nChoreographyParticipant");
        rowhead.createCell(91).setCellValue("nChoreographySubprocess");
        rowhead.createCell(92).setCellValue("nConversation");
        rowhead.createCell(93).setCellValue("nSubConversation");
        rowhead.createCell(94).setCellValue("nCallConversation");
        rowhead.createCell(95).setCellValue("nConversationLink");
        rowhead.createCell(96).setCellValue("nITSystem");
        rowhead.createCell(97).setCellValue("nAssociation");
        rowhead.createCell(98).setCellValue("nCompensateAssociation");
        rowhead.createCell(99).setCellValue("nUnidirectionalAssociation");
        rowhead.createCell(100).setCellValue("nUndirectedAssociation");
        rowhead.createCell(101).setCellValue("nBidirectionalAssociation");
        rowhead.createCell(102).setCellValue("nTextAnnotation");
        rowhead.createCell(103).setCellValue("ndataOutputAssociation");
        rowhead.createCell(104).setCellValue("ndataInputAssociation");
        rowhead.createCell(105).setCellValue("nOfExtensionElements");
        rowhead.createCell(106).setCellValue("TotalElements");
        
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
        int nTaskMultipleInstance=0;
        int nTaskLoopActivity=0;
        int nReceiveTask=0;
        int nSendTask=0;
        int nUserTask=0;
        int nManualTask=0;
        int nBusinessRuleTask=0;
        int nServiceTask=0;
        int nScriptTask=0;        
        int nCallActivity=0;
        int nSubProcess=0;
        int nTransaction=0;
        int nAdHocSubProcess=0;
        int nEventSubProcess=0;
        int nGroup=0;
        int nLane=0;
        int nDataObject=0;
        int nDataObjectReference=0;
        int nDataStore=0;
        int nDataStoreReference=0;
        int nDataInput=0;
        int nDataOutput=0;
        int nExclusiveGateway=0;
        int nParallelGateway=0;
        int nInclusiveGateway=0;
        int nEventBasedGateway=0;
        int nParallelEventBasedGateway=0;
        int nComplexGateway=0;
        int nCondition=0;
        int nStartMultipleParallelEventDefinition=0;
        int nStartMultipleEventDefinition=0;
        int nStartNoneEvent=0;
        int nStartSignalEventDefinition=0;
        int nStartConditionalEventDefinition=0;
        int nStartTimerEventDefinition=0;
        int nStartMessageEventDefinition=0;
        int nStartCompensateEventDefinition=0;
        int nStartEscalationEventDefinition=0;
        int nStartErrorEventDefinition=0;
        int nEndNoneEvent = 0;
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
        int nBoundaryMessageEvent=0;
        int nBoundaryTimerEvent=0;
        int nBoundaryCancelEvent=0;
        int nBoundaryConditionalEvent =0;
        int nBoundaryEscalationEvent=0;
        int nBoundaryErrorEvent=0;
        int nBoundarySignalEvent=0;
        int nBoundaryCompensateEvent=0;
        int nBoundaryTimerEventNonInt=0;
        int nBoundaryEscalationEventNonInt=0;
        int nBoundaryConditionalEventNonInt=0;
        int nBoundaryMessageEventNonInt=0;
        int nBoundarySignalEventNonInt=0;
        int nMessageFlow=0;
        int nSequenceFlow=0;
        int nDefaultFlow=0;
        int nConditionalFlow=0;
        int nPool=0;
        int nCollapsedPool=0;
        int nVerticalLane=0;
        int nVerticalPool=0;
        int nChoreographyTask=0;
        int nChoreographyParticipant=0;
        int nChoreographySubprocess=0;
        int nConversation=0;
        int nSubConversation=0;
        int nCallConversation=0;
        int nConversationLink=0;
        int nITSystem=0;
        int nAssociation=0;
        int nCompensateAssociation=0;
        int nUnidirectionalAssociation=0;
        int nUndirectedAssociation=0;
        int nBidirectionalAssociation=0;
        int nTextAnnotation=0;
        int ndataOutputAssociation=0;
        int ndataInputAssociation=0;
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
        	}
        }	
        
        // Check if contain a subProcess and the number of subprocess
        XPathExpression exprModelTypeSub = xpath.compile("//bpmn:process");
        Object resultModelTypeSub = exprModelTypeSub.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesModelTypeSub = (NodeList) resultModelTypeSub;      
        for(int i=0; i<nodesModelTypeSub.getLength(); i++) {
        	
    	NodeList nodeModelType = nodesModelTypeSub.item(i).getChildNodes();
    
        	 for(int j=0; j<nodeModelType.getLength(); j++) {	

		        	if(nodeModelType.item(j).getNodeName().toString() == "bpmn:subProcess") {      		
		        		nSubProcess++;
		            	//System.out.println("In the model: "+fileName+" there are: "+nSubProcess+" subProcess/es in the model");
		            }
        	 }
		        
        }    

//----------------------------------------------BPMN STATS-------------------------------------------------
// XPath Query for showing all Tasks markers
//  	  nTask
//        nTaskMultipleIstance
//        nTaskMultipleIstanceSequential
//        nTaskLoopActivity
        
          XPathExpression exprTask = xpath.compile("//bpmn:task");
          Object result = exprTask.evaluate(doc, XPathConstants.NODESET);
          NodeList nodesTask = (NodeList) result;
          doc.getDocumentElement().normalize();  
          //N° of normal tasks
          nTask = nodesTask.getLength();
          
          for(int i=0; i<nodesTask.getLength() ; i++) {
          	
          	Node TaskNode = nodesTask.item(i);   
          	
          	 
          	if(TaskNode.hasChildNodes()) {                
          		
          		NodeList taskChildNodes = TaskNode.getChildNodes();
          		         		
                  for(int j=0;j<taskChildNodes.getLength(); j++) {
                	 
                	     
	                  	if(taskChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
	                  		
	                  
	                  		// TODO BUG
	                  		if(taskChildNodes.item(j).getNodeName().contains("standardLoopCharacteristics")) {
	                  			nTaskLoopActivity++;
	                  		}
	                  		//isSequential= true TODO
	                  		if(taskChildNodes.item(j).getNodeName().contains("multiInstanceLoopCharacteristics")  && ((Element) taskChildNodes.item(j)).getAttribute("isSequential").contains("true")) {
	                  			nTaskMultipleInstanceSequential++;

	                  		}	
	                  		else if(taskChildNodes.item(j).getNodeName().contains("multiInstanceLoopCharacteristics"))
	                  			nTaskMultipleInstance++;	                  	
	                  	}
                  }
          		
          	}
          	
          	
          }

  
        
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
                
        //N° of eventsubprocess  
        XPathExpression exprESubP = xpath.compile("//bpmn:subProcess[@triggeredByEvent='true']");
        Object resultESubP = exprESubP.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesESubP = (NodeList) resultESubP;
        doc.getDocumentElement().normalize();  
        nEventSubProcess = nodesESubP.getLength();
        
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
        nAdHocSubProcess = nodesadHoc.getLength();
        
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
//      nDataStore
//      nDataObjectReference
//      nDataStoreReference
//		nDataInput
//		nDataOutput
        
        // N° of Data Object
        XPathExpression exprDO = xpath.compile("//bpmn:dataObject");
        Object resultDO = exprDO.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesDO = (NodeList) resultDO;
        doc.getDocumentElement().normalize();  
        nDataObject = nodesDO.getLength();
        
        // N° of Data Store
        XPathExpression exprDS = xpath.compile("//bpmn:dataStore");
        Object resultDS = exprDS.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesDS = (NodeList) resultDS;
        doc.getDocumentElement().normalize();  
        nDataStore = nodesDS.getLength();
        
        // N° of Data Object Reference
        XPathExpression exprDOR = xpath.compile("//bpmn:dataObjectReference");
        Object resultDOR = exprDOR.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesDOR = (NodeList) resultDOR;
        doc.getDocumentElement().normalize();  
        nDataObjectReference = nodesDOR.getLength();
        
        // N° of Data Store Reference
        XPathExpression exprDSR = xpath.compile("//bpmn:dataStoreReference");
        Object resultDSR = exprDSR.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesDSR = (NodeList) resultDSR;
        doc.getDocumentElement().normalize();  
        nDataStoreReference = nodesDSR.getLength();
        
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
        
        // Empty or Not is the same
        XPathExpression exprExG = xpath.compile("//bpmn:exclusiveGateway");
        Object resultExG = exprExG.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesExG = (NodeList) resultExG;
        doc.getDocumentElement().normalize();  
        nExclusiveGateway = nodesExG.getLength();
        
        // Parallel Event Based 
        XPathExpression exprEBGP = xpath.compile("//bpmn:eventBasedGateway[@eventGatewayType='Parallel']");
        Object resultEBGP = exprEBGP.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesEBGP = (NodeList) resultEBGP;
        doc.getDocumentElement().normalize();  
        nParallelEventBasedGateway = nodesEBGP.getLength();
        
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
        	
        	 
        	if(StartEventNode.hasChildNodes()) {                
        		
        		NodeList StartEventChildNodes = StartEventNode.getChildNodes();
        		         		
                for(int j=0;j<StartEventChildNodes.getLength(); j++) {
                

	                  	if(StartEventChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
	                  		
	                  		

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
	                  		
	                  		//TODO
//	                  		if(false) {
//	                  			nStartMultipleParallelEventDefinition++;
//	                  			}	
//	                  		//TODO
//	                  		if(false) {
//	                  			nStartMultipleEventDefinition++;
//	                  			}	
	                  			                  	
	                  	}
                }
        		
        	} 
        	else
        		nStartNoneEvent++;
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
	                  		
//	                  		//TODO
//	                  		if(false) {
//	                  			nEndMultipleEventDefinition++;
//	                  			}	
	                  			                  	
	                  	}
                }
        		
        	} 
        	else
        		nEndNoneEvent++;
        }
        
        // Intermediate Catch Events
        
        XPathExpression exprIntEvent = xpath.compile("//bpmn:intermediateCatchEvent");
        Object resultIntEvent = exprIntEvent.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesIntEvent = (NodeList) resultIntEvent;
        doc.getDocumentElement().normalize();         
        
        for(int i=0; i<nodesIntEvent.getLength(); i++) {
        	
        	Node IntEventNode = nodesIntEvent.item(i);   
        	
        	 
        	if(IntEventNode.hasChildNodes()) {                
        		
        		NodeList IntEventChildNodes = IntEventNode.getChildNodes();
        		         		
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
	                  		

//	                  		//TODO
//	                  		if(false) {
//	                  			nIntermediateCatchMultipleEventDefinition++;
//	                  			}
	                  		
	                  	//TODO
//	                  		if(false) {
//	                  			nIntermediateCatchMultipleParallelEventDefinition++;
//	                  			}
	                  			                  	
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
	                  		
//	                  		//TODO
//	                  		if(false) {
//	                  			nIntermediateThrowMultipleEventDefinition++;
//	                  			}	
	                  			                  	
	                  	}
                }
        		
        	} 
        	else
        		nIntermediateThrowNoneEventDefinition++;
        }
        
        // Intermediate Catch Events - Boundary Interrupting
        
        XPathExpression exprCatchIntEvent = xpath.compile("//bpmn:boundaryEvent");
        Object resultCatchIntEvent = exprCatchIntEvent.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesCatchIntEvent = (NodeList) resultCatchIntEvent;
        doc.getDocumentElement().normalize();         
        
        for(int i=0; i<nodesCatchIntEvent.getLength(); i++) {
        	
        	Node CatchIntEventNode = nodesCatchIntEvent.item(i);   
        	
        	 
        	if(CatchIntEventNode.hasChildNodes()) {                
        		
        		NodeList CatchIntEventChildNodes = CatchIntEventNode.getChildNodes();
        		         		
                for(int j=0;j<CatchIntEventChildNodes.getLength(); j++) {
                

	                  	if(CatchIntEventChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
	                  		
	                  		if(CatchIntEventChildNodes.item(j).getNodeName().contains("messageEventDefinition")) {
	                  			nBoundaryMessageEvent++;
	                  		}
	                  		
	                  		if(CatchIntEventChildNodes.item(j).getNodeName().contains("timerEventDefinition")) {
	                  			nBoundaryTimerEvent++;
	                  		}
	                  		
	                  		if(CatchIntEventChildNodes.item(j).getNodeName().contains("escalationEventDefinition")) {
	                  			nBoundaryEscalationEvent++;
	                  		}
	                  		
	                  		if(CatchIntEventChildNodes.item(j).getNodeName().contains("conditionalEventDefinition")) {
	                  			nBoundaryConditionalEvent++;
	                  		}
	                  		
	                  		if(CatchIntEventChildNodes.item(j).getNodeName().contains("errorEventDefinition")) {
	                  			nBoundaryErrorEvent++;
	                  		}
	                  		
	                  		if(CatchIntEventChildNodes.item(j).getNodeName().contains("cancelEventDefinition")) {
	                  			nBoundaryCancelEvent++;
	                  		}	 
	                  		
	                  		if(CatchIntEventChildNodes.item(j).getNodeName().contains("compensateEventDefinition")) {
	                  			nBoundaryCompensateEvent++;
	                  		}	                  		
	                  		
	                  		if(CatchIntEventChildNodes.item(j).getNodeName().contains("signalEventDefinition")) {
	                  			nBoundarySignalEvent++;
	                  		}	   
	                  		
//	                  		//TODO
//	                  		if(false) {
//	                  			nBoundaryMultipleEvent++;
//	                  			}	
	                  	//TODO
//	                  		if(false) {
//	                  			nBoundaryMultipleParallelEvent++;
//	                  			}	
	                  			                  	
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
        	
        	 
        	if(CatchNonIntEventNode.hasChildNodes()) {                
        		
        		NodeList CatchNonIntEventChildNodes = CatchNonIntEventNode.getChildNodes();
        		         		
                for(int j=0;j<CatchNonIntEventChildNodes.getLength(); j++) {
                

	                  	if(CatchNonIntEventChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
	                  		
	                  		if(CatchNonIntEventChildNodes.item(j).getNodeName().contains("messageEventDefinition")) {
	                  			nBoundaryMessageEventNonInt++;
	                  		}
	                  		
	                  		if(CatchNonIntEventChildNodes.item(j).getNodeName().contains("timerEventDefinition")) {
	                  			nBoundaryTimerEventNonInt++;
	                  		}
	                  		
	                  		if(CatchNonIntEventChildNodes.item(j).getNodeName().contains("escalationEventDefinition")) {
	                  			nBoundaryEscalationEventNonInt++;
	                  		}
	                  		
	                  		if(CatchNonIntEventChildNodes.item(j).getNodeName().contains("conditionalEventDefinition")) {
	                  			nBoundaryConditionalEventNonInt++;
	                  		}	                  		                  		
	                  		
	                  		if(CatchNonIntEventChildNodes.item(j).getNodeName().contains("signalEventDefinition")) {
	                  			nBoundarySignalEventNonInt++;
	                  		}	   
	                  		
//	                  		//TODO
//	                  		if(false) {
//	                  			nBoundaryMultipleEvent++;
//	                  			}	
	                  	//TODO
//	                  		if(false) {
//	                  			nBoundaryMultipleParallelEvent++;
//	                  			}	
	                  			                  	
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
        nPool = nodesPool.getLength(); 
        
        //N° of CollapsedPool
        XPathExpression exprCPool = xpath.compile("//bpmn:collaboration//bpmn:participant");
        Object resultCPool= exprCPool.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesCPool = (NodeList) resultCPool;
        doc.getDocumentElement().normalize();  
        nCollapsedPool = nodesCPool.getLength() - nodesPool.getLength(); 
        
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
        nConversation = nodesConv.getLength();
        
        XPathExpression exprSConv = xpath.compile("//bpmn:subConversation");
        Object resultSConv = exprSConv.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesSConv = (NodeList) resultSConv;
        doc.getDocumentElement().normalize();  
        nSubConversation = nodesSConv.getLength();
        
        XPathExpression exprCConv = xpath.compile("//bpmn:callConversation");
        Object resultCConv = exprCConv.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesCConv = (NodeList) resultCConv;
        doc.getDocumentElement().normalize();  
        nCallConversation = nodesCConv.getLength();
        
        XPathExpression exprConvLink = xpath.compile("//bpmn:conversationLink");
        Object resultConvLink = exprConvLink.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesConvLink = (NodeList) resultConvLink;
        doc.getDocumentElement().normalize();  
        nConversationLink = nodesConvLink.getLength();
        
        // IT SYSTEM
        XPathExpression exprITS = xpath.compile("//bpmn:textAnnotation//bpmn:extensionElements[@dataObjectType='IT-systems']");
        Object resultITS = exprITS.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesITS = (NodeList) resultITS;
        doc.getDocumentElement().normalize();  
        nITSystem = nodesITS.getLength();
        
        // ASSOCIATIONS
        //dataInputAssociation
        XPathExpression exprIAssoc = xpath.compile("//bpmn:dataInputAssociation");
        Object resultIAssoc = exprIAssoc.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesIAssoc = (NodeList) resultIAssoc;
        doc.getDocumentElement().normalize();  
        ndataInputAssociation = nodesIAssoc.getLength();
        
        //dataOutputAssociation
        XPathExpression exprOAssoc = xpath.compile("//bpmn:dataOutputAssociation");
        Object resultOAssoc = exprOAssoc.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesOAssoc = (NodeList) resultOAssoc;
        doc.getDocumentElement().normalize();  
        ndataOutputAssociation = nodesOAssoc.getLength();
        
        XPathExpression exprAssoc = xpath.compile("//bpmn:association");
        Object resultAssoc = exprAssoc.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesAssoc = (NodeList) resultAssoc;
        doc.getDocumentElement().normalize();  
        nAssociation = nodesAssoc.getLength() + ndataInputAssociation + ndataOutputAssociation;
        
        //COMPENSATE ASSOCIATION
        XPathExpression exprCAssoc = xpath.compile("//bpmn:endEvent//bpmn:compensateEventDefinition[@waitForCompletion='true']");
        Object resultCAssoc = exprCAssoc.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesCAssoc = (NodeList) resultCAssoc;
        doc.getDocumentElement().normalize();  
        nCompensateAssociation = nodesCAssoc.getLength();
        
        //Unidirectional Association
        XPathExpression exprUnidirectionalAssoc = xpath.compile("//bpmn:association[@associationDirection='One']");
        Object resultUnidirectionalAssoc = exprUnidirectionalAssoc.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesUnidirectionalAssoc = (NodeList) resultUnidirectionalAssoc;
        doc.getDocumentElement().normalize();  
        nUnidirectionalAssociation = nodesUnidirectionalAssoc.getLength();
        
        //Unidirected Association
        XPathExpression exprUndirectedAssoc = xpath.compile("//bpmn:association[@associationDirection='None']");
        Object resultUndirectedAssoc = exprUndirectedAssoc.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesUndirectedAssoc = (NodeList) resultUndirectedAssoc;
        doc.getDocumentElement().normalize();  
        nUndirectedAssociation = nodesUndirectedAssoc.getLength();
        
        //Bidirectional Association
        XPathExpression exprBidirectionalAssoc = xpath.compile("//bpmn:association[@associationDirection='Both']");
        Object resultBidirectionalAssoc = exprBidirectionalAssoc.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesBidirectionalAssoc = (NodeList) resultBidirectionalAssoc;
        doc.getDocumentElement().normalize();  
        nBidirectionalAssociation = nodesBidirectionalAssoc.getLength();
        
        //Text Annotation
        XPathExpression exprTextAnn = xpath.compile("//bpmn:textAnnotation");
        Object resultTextAnn = exprTextAnn.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesTextAnn = (NodeList) resultTextAnn;
        doc.getDocumentElement().normalize();  
        nTextAnnotation = nodesTextAnn.getLength();
        
        //Extension Elements 
        XPathExpression exprExtension = xpath.compile("//bpmn:extensionElements");
        Object resultExtension = exprExtension.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesExtension = (NodeList) resultExtension;
        doc.getDocumentElement().normalize();  
        nOfExtensionElements = nodesExtension.getLength();
        
        TotalElements = nTask + nTaskMultipleInstanceSequential + nTaskMultipleInstance + nTaskLoopActivity
                + nReceiveTask+ nSendTask+ nUserTask+ nManualTask
                + nBusinessRuleTask
                + nServiceTask
                + nScriptTask        
                + nCallActivity
                + nSubProcess
                + nTransaction
                + nAdHocSubProcess
                + nEventSubProcess
                + nGroup
                + nLane
                + nDataObject
                + nDataObjectReference
                + nDataStore
                + nDataStoreReference
                + nDataInput
                + nDataOutput
                + nExclusiveGateway
                + nParallelGateway
                + nInclusiveGateway
                + nEventBasedGateway
                + nParallelEventBasedGateway
                + nComplexGateway
                + nCondition
                + nStartMultipleParallelEventDefinition
                + nStartMultipleEventDefinition
                + nStartNoneEvent
                + nStartSignalEventDefinition
                + nStartConditionalEventDefinition
                + nStartTimerEventDefinition
                + nStartMessageEventDefinition
                + nStartCompensateEventDefinition
                + nStartEscalationEventDefinition
                + nStartErrorEventDefinition
                + nEndNoneEvent
                + nEndMultipleEventDefinition
                + nEndEscalationEventDefinition
                + nEndErrorEventDefinition
                + nEndSignalEventDefinition
                + nEndCompensateEventDefinition
                + nEndCancelEventDefinition 
                + nEndMessageEventDefinition
                + nEndTerminateEventDefinition
                + nIntermediateCatchMultipleEventDefinition
                + nIntermediateCatchMultipleParallelEventDefinition
                + nIntermediateCatchMessageEventDefinition
                + nIntermediateCatchTimerEventDefinition
                + nIntermediateCatchConditionalEventDefinition
                + nIntermediateCatchLinkEventDefinition
                + nIntermediateCatchSignalEventDefinition
                + nIntermediateThrowNoneEventDefinition
                + nIntermediateThrowMessageEventDefinition
                + nIntermediateThrowEscalationEventDefinition
                + nIntermediateThrowLinkEventDefinition
                + nIntermediateThrowSignalEventDefinition
                + nIntermediateThrowCompensateEventDefinition
                + nIntermediateThrowMultipleParallelEventDefinition
                + nBoundaryMessageEvent
                + nBoundaryTimerEvent
                + nBoundaryCancelEvent
                + nBoundaryConditionalEvent 
                + nBoundaryEscalationEvent
                + nBoundaryErrorEvent
                + nBoundarySignalEvent
                + nBoundaryCompensateEvent
                + nBoundaryTimerEventNonInt
                + nBoundaryEscalationEventNonInt
                + nBoundaryConditionalEventNonInt
                + nBoundaryMessageEventNonInt
                + nBoundarySignalEventNonInt
                + nMessageFlow
                + nSequenceFlow
                + nDefaultFlow
                + nConditionalFlow
                + nPool
                + nVerticalLane
                + nVerticalPool
                + nChoreographyTask
                + nChoreographyParticipant
                + nChoreographySubprocess
                + nConversation
                + nSubConversation
                + nCallConversation
                + nConversationLink
                + nITSystem
                + nAssociation
                + nCompensateAssociation
                + nUnidirectionalAssociation
                + nUndirectedAssociation
                + nBidirectionalAssociation
                + nTextAnnotation
                + ndataOutputAssociation
                + ndataInputAssociation;

        
        	//creating the rows 
            HSSFRow row = sheet.createRow((short)x+1);  
            //inserting data   
      		row.createCell(0).setCellValue(fileName);  
      		row.createCell(1).setCellValue(bpmnModeler);  
      		row.createCell(2).setCellValue(isEnglish);
      		row.createCell(3).setCellValue(modelType);
      		row.createCell(4).setCellValue(nTask);
            row.createCell(5).setCellValue(nTaskMultipleInstance);
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
            row.createCell(16).setCellValue(nSubProcess);
            row.createCell(17).setCellValue(nTransaction);
            row.createCell(18).setCellValue(nAdHocSubProcess);
            row.createCell(19).setCellValue(nEventSubProcess);
            row.createCell(20).setCellValue(nGroup);
            row.createCell(21).setCellValue(nLane);
            row.createCell(22).setCellValue(nDataObject);
            row.createCell(23).setCellValue(nDataObjectReference);
            row.createCell(24).setCellValue(nDataStore);
            row.createCell(25).setCellValue(nDataStoreReference);
            row.createCell(26).setCellValue(nDataInput);
            row.createCell(27).setCellValue(nDataOutput);
            row.createCell(28).setCellValue(nExclusiveGateway);
            row.createCell(29).setCellValue(nParallelGateway);
            row.createCell(30).setCellValue(nInclusiveGateway);
            row.createCell(31).setCellValue(nEventBasedGateway);
            row.createCell(32).setCellValue(nParallelEventBasedGateway);
            row.createCell(33).setCellValue(nComplexGateway);
            row.createCell(34).setCellValue(nCondition);
            row.createCell(35).setCellValue(nStartNoneEvent);
            row.createCell(36).setCellValue(nStartMultipleParallelEventDefinition);
            row.createCell(37).setCellValue(nStartMultipleEventDefinition);
            row.createCell(38).setCellValue(nStartSignalEventDefinition);
            row.createCell(39).setCellValue(nStartConditionalEventDefinition);
            row.createCell(40).setCellValue(nStartTimerEventDefinition);
            row.createCell(41).setCellValue(nStartMessageEventDefinition);
            row.createCell(42).setCellValue(nStartCompensateEventDefinition);
            row.createCell(43).setCellValue(nStartEscalationEventDefinition);
            row.createCell(44).setCellValue(nStartErrorEventDefinition);
            row.createCell(45).setCellValue(nEndNoneEvent);
            row.createCell(46).setCellValue(nEndMultipleEventDefinition); 
            row.createCell(47).setCellValue(nEndEscalationEventDefinition);
            row.createCell(48).setCellValue(nEndErrorEventDefinition);
            row.createCell(49).setCellValue(nEndSignalEventDefinition);
            row.createCell(50).setCellValue(nEndCompensateEventDefinition);
            row.createCell(51).setCellValue(nEndCancelEventDefinition); 
            row.createCell(52).setCellValue(nEndMessageEventDefinition);
            row.createCell(53).setCellValue(nEndTerminateEventDefinition);
            row.createCell(54).setCellValue(nIntermediateCatchMultipleEventDefinition);
            row.createCell(55).setCellValue(nIntermediateCatchMultipleParallelEventDefinition);
            row.createCell(56).setCellValue(nIntermediateCatchMessageEventDefinition);
            row.createCell(57).setCellValue(nIntermediateCatchTimerEventDefinition);
            row.createCell(58).setCellValue(nIntermediateCatchConditionalEventDefinition);
            row.createCell(59).setCellValue(nIntermediateCatchLinkEventDefinition);
            row.createCell(60).setCellValue(nIntermediateCatchSignalEventDefinition);
            row.createCell(61).setCellValue(nIntermediateThrowNoneEventDefinition);
            row.createCell(62).setCellValue(nIntermediateThrowMessageEventDefinition);
            row.createCell(63).setCellValue(nIntermediateThrowEscalationEventDefinition);
            row.createCell(64).setCellValue(nIntermediateThrowLinkEventDefinition);
            row.createCell(65).setCellValue(nIntermediateThrowSignalEventDefinition);
            row.createCell(66).setCellValue(nIntermediateThrowCompensateEventDefinition);
            row.createCell(67).setCellValue(nIntermediateThrowMultipleParallelEventDefinition);
            row.createCell(68).setCellValue(nBoundaryMessageEvent);
            row.createCell(69).setCellValue(nBoundaryTimerEvent);
            row.createCell(70).setCellValue(nBoundaryCancelEvent);
            row.createCell(71).setCellValue(nBoundaryConditionalEvent);
            row.createCell(72).setCellValue(nBoundaryEscalationEvent);
            row.createCell(73).setCellValue(nBoundaryErrorEvent);
            row.createCell(74).setCellValue(nBoundarySignalEvent);
            row.createCell(75).setCellValue(nBoundaryCompensateEvent);
            row.createCell(76).setCellValue(nBoundaryTimerEventNonInt);
            row.createCell(77).setCellValue(nBoundaryEscalationEventNonInt);
            row.createCell(78).setCellValue(nBoundaryConditionalEventNonInt);
            row.createCell(79).setCellValue(nBoundaryMessageEventNonInt);
            row.createCell(80).setCellValue(nBoundarySignalEventNonInt);
            row.createCell(81).setCellValue(nMessageFlow);
            row.createCell(82).setCellValue(nSequenceFlow);
            row.createCell(83).setCellValue(nDefaultFlow);
            row.createCell(84).setCellValue(nConditionalFlow);
            row.createCell(85).setCellValue(nPool);
            row.createCell(86).setCellValue(nCollapsedPool);            
            row.createCell(87).setCellValue(nVerticalLane);
            row.createCell(88).setCellValue(nVerticalPool);
            row.createCell(89).setCellValue(nChoreographyTask);
            row.createCell(90).setCellValue(nChoreographyParticipant);
            row.createCell(91).setCellValue(nChoreographySubprocess);
            row.createCell(92).setCellValue(nConversation);
            row.createCell(93).setCellValue(nSubConversation);
            row.createCell(94).setCellValue(nCallConversation);
            row.createCell(95).setCellValue(nConversationLink);
            row.createCell(96).setCellValue(nITSystem);
            row.createCell(97).setCellValue(nAssociation);
            row.createCell(98).setCellValue(nCompensateAssociation);
            row.createCell(99).setCellValue(nUnidirectionalAssociation);
            row.createCell(100).setCellValue(nUndirectedAssociation);
            row.createCell(101).setCellValue(nBidirectionalAssociation);
            row.createCell(102).setCellValue(nTextAnnotation);
            row.createCell(103).setCellValue(ndataOutputAssociation);
            row.createCell(104).setCellValue(ndataInputAssociation);
            row.createCell(105).setCellValue(nOfExtensionElements);
            row.createCell(106).setCellValue(TotalElements);
            
            
      		FileOutputStream fileOut = new FileOutputStream("bpmn_stats.xls");
       		wb.write(fileOut);  
       		//closing the Stream  
       		fileOut.close();  
            //closing the workbook  
       		wb.close(); 
        	}
        }
}