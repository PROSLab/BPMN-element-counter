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
        int nStartCancelEventDefinition=0;
        int nStartEscalationEventDefinition=0;
        int nStartErrorEventDefinition=0;
        int nEndEventNone = 0;
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
        int nIntermediateSignalMessageEventDefinition=0;
        int nIntermediateThrowEvent=0;
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
        int nMessageFlow=0;
        int nSequenceFlow=0;
        int nDefaultFlow=0;
        int nConditionalFlow=0;
        int nPool=0;
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
        int TotalElements=0;
        boolean isEnglish=false;
        
        
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
//        rowhead.createCell(17).setCellValue("nGroup");
//        rowhead.createCell(18).setCellValue("nLane");
//        rowhead.createCell(19).setCellValue("nDataObject");
//        rowhead.createCell(20).setCellValue("nDataObjectReference");
//        rowhead.createCell(21).setCellValue("nDataStore");
//        rowhead.createCell(22).setCellValue("nDataStoreReference");
//        rowhead.createCell(23).setCellValue("nDataInput");
//        rowhead.createCell(24).setCellValue("nDataOutput");
//        rowhead.createCell(25).setCellValue("nExclusiveGateway");
//        rowhead.createCell(26).setCellValue("nParallelGateway");
//        rowhead.createCell(27).setCellValue("nInclusiveGateway");
//        rowhead.createCell(28).setCellValue("nEventBasedGateway");
//        rowhead.createCell(29).setCellValue("nComplexGateway");
//        rowhead.createCell(30).setCellValue("nCondition");
//        rowhead.createCell(31).setCellValue("nStartMultipleParallelEventDefinition");
//        rowhead.createCell(32).setCellValue("nStartMultipleEventDefinition");
//        rowhead.createCell(33).setCellValue("nStartNoneEvent");
//        rowhead.createCell(34).setCellValue("nStartSignalEventDefinition");
//        rowhead.createCell(35).setCellValue("nStartConditionalEventDefinition");
//        rowhead.createCell(36).setCellValue("nStartTimerEventDefinition");
//        rowhead.createCell(37).setCellValue("nStartMessageEventDefinition");
//        rowhead.createCell(38).setCellValue("nStartCompensateEventDefinition");
//        rowhead.createCell(39).setCellValue("nStartCancelEventDefinition");
//        rowhead.createCell(40).setCellValue("nStartEscalationEventDefinition");
//        rowhead.createCell(41).setCellValue("nStartErrorEventDefinition");
//        rowhead.createCell(42).setCellValue("nEndEventNone");
//        rowhead.createCell(43).setCellValue("nEndMultipleEventDefinition"); 
//        rowhead.createCell(44).setCellValue("nEndEscalationEventDefinition");
//        rowhead.createCell(45).setCellValue("nEndErrorEventDefinition");
//        rowhead.createCell(46).setCellValue("nEndSignalEventDefinition");
//        rowhead.createCell(47).setCellValue("nEndCompensateEventDefinition");
//        rowhead.createCell(48).setCellValue("nEndCancelEventDefinition"); 
//        rowhead.createCell(49).setCellValue("nEndMessageEventDefinition");
//        rowhead.createCell(50).setCellValue("nEndTerminateEventDefinition");
//        rowhead.createCell(51).setCellValue("nIntermediateCatchMultipleEventDefinition");
//        rowhead.createCell(52).setCellValue("nIntermediateCatchMultipleParallelEventDefinition");
//        rowhead.createCell(53).setCellValue("nIntermediateCatchMessageEventDefinition");
//        rowhead.createCell(54).setCellValue("nIntermediateCatchTimerEventDefinition");
//        rowhead.createCell(55).setCellValue("nIntermediateCatchConditionalEventDefinition");
//        rowhead.createCell(56).setCellValue("nIntermediateCatchLinkEventDefinition");
//        rowhead.createCell(57).setCellValue("nIntermediateSignalMessageEventDefinition");
//        rowhead.createCell(58).setCellValue("nIntermediateThrowEvent");
//        rowhead.createCell(59).setCellValue("nIntermediateThrowMessageEventDefinition");
//        rowhead.createCell(60).setCellValue("nIntermediateThrowEscalationEventDefinition");
//        rowhead.createCell(61).setCellValue("nIntermediateThrowLinkEventDefinition");
//        rowhead.createCell(62).setCellValue("nIntermediateThrowSignalEventDefinition");
//        rowhead.createCell(63).setCellValue("nIntermediateThrowCompensateEventDefinition");
//        rowhead.createCell(64).setCellValue("nIntermediateThrowMultipleParallelEventDefinition");
//        rowhead.createCell(65).setCellValue("nBoundaryMessageEvent");
//        rowhead.createCell(66).setCellValue("nBoundaryTimerEvent");
//        rowhead.createCell(67).setCellValue("nBoundaryCancelEvent");
//        rowhead.createCell(68).setCellValue("nBoundaryConditionalEvent");
//        rowhead.createCell(69).setCellValue("nBoundaryEscalationEvent");
//        rowhead.createCell(70).setCellValue("nBoundaryErrorEvent");
//        rowhead.createCell(71).setCellValue("nBoundarySignalEvent");
//        rowhead.createCell(72).setCellValue("nBoundaryCompensateEvent");
//        rowhead.createCell(73).setCellValue("nBoundaryTimerEventNonInt");
//        rowhead.createCell(74).setCellValue("nBoundaryEscalationEventNonInt");
//        rowhead.createCell(75).setCellValue("nBoundaryConditionalEventNonInt");
//        rowhead.createCell(76).setCellValue("nBoundaryMessageEventNonInt");
//        rowhead.createCell(77).setCellValue("nMessageFlow");
//        rowhead.createCell(78).setCellValue("nSequenceFlow");
//        rowhead.createCell(79).setCellValue("nDefaultFlow");
//        rowhead.createCell(80).setCellValue("nConditionalFlow");
//        rowhead.createCell(81).setCellValue("nPool");
//        rowhead.createCell(82).setCellValue("nVerticalLane");
//        rowhead.createCell(83).setCellValue("nVerticalPool");
//        rowhead.createCell(84).setCellValue("nChoreographyTask");
//        rowhead.createCell(85).setCellValue("nChoreographyParticipant");
//        rowhead.createCell(86).setCellValue("nChoreographySubprocess");
//        rowhead.createCell(87).setCellValue("nConversation");
//        rowhead.createCell(88).setCellValue("nSubConversation");
//        rowhead.createCell(89).setCellValue("nCallConversation");
//        rowhead.createCell(90).setCellValue("nConversationLink");
//        rowhead.createCell(91).setCellValue("nITSystem");
//        rowhead.createCell(92).setCellValue("nAssociation");
//        rowhead.createCell(93).setCellValue("nCompensateAssociation");
//        rowhead.createCell(94).setCellValue("nUnidirectionalAssociation");
//        rowhead.createCell(95).setCellValue("nUndirectedAssociation");
//        rowhead.createCell(96).setCellValue("nBidirectionalAssociation");
//        rowhead.createCell(97).setCellValue("nTextAnnotation");
//        rowhead.createCell(98).setCellValue("ndataOutputAssociation");
//        rowhead.createCell(99).setCellValue("ndataInputAssociation");
//        rowhead.createCell(100).setCellValue("TotalElements");
        
        
        // File's cycle of the testmodels folder
        File folder = new File("testmodels");
        File[] listOfFiles = folder.listFiles();
        
        for (int x = 0; x < listOfFiles.length; x++) {
        
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
        
        try {
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
	
	                  		if(taskChildNodes.item(j).getNodeName() == "bpmn:standardLoopCharacteristics") {
	                  			nTaskLoopActivity++;
	                  		}
	                  		//isSequential= true TODO
	                  		if(taskChildNodes.item(j).getNodeName() == "bpmn:multiInstanceLoopCharacteristics") {
	                  			nTaskMultipleInstanceSequential++;
	                  			
	                  		}	
	                  		else if(taskChildNodes.item(j).getNodeName() == "bpmn:multiInstanceLoopCharacteristics")
	                  			nTaskMultipleInstance++;	                  	
	                  	}
                  }
          		
          	}
          	
          	
          }
          
          
      } catch (Exception E) {
          System.out.println(E);
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
        //        nEventSubProcess=  doc.xpath('count(//bpmn:subProcess[@triggeredByEvent="true"] )', namespaces={
//                'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
//                })
        
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
        
        
        
        //Example of nested search
//        try {
//            XPathExpression exprTask = xpath.compile("//bpmn:task");
//            Object result = exprTask.evaluate(doc, XPathConstants.NODESET);
//            NodeList nodesTask = (NodeList) result;
//            doc.getDocumentElement().normalize();  
//            //N° of normal tasks
//            nTask = nodesTask.getLength();
//            
//            NodeList listOfTaskNodes = doc.getElementsByTagName("bpmn:multiInstanceLoopCharacteristics");
//            
//            for(int i=0; i<listOfTaskNodes.getLength() ; i++) {
//            	
//            	Node TaskNode = listOfTaskNodes.item(i);   
//            	 System.out.println(listOfTaskNodes.item(i).getNodeName());
//            	 
//            	if(TaskNode.hasChildNodes()) {
//            		
//            		NodeList taskChildNodes = TaskNode.getChildNodes();
//            		
//            		
//                    for(int j=0;j<taskChildNodes.getLength(); j++) {
//                  	  
//                  	 
//                  	  
//  	                  	if(taskChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
//  	
//  	                  		if(taskChildNodes.item(j).getNodeName() == "bpmn:standardLoopCharacteristics")
//  	                  			nTaskLoopActivity++;
//  	                  		if(taskChildNodes.item(j).getNodeName() == "bpmn:sendTask")
//  	                  			nSendTask++;
//  	                  		if(taskChildNodes.item(j).getNodeName() == "bpmn:receiveTask")
//  	                  			nReceiveTask++;
//  	                  		if(taskChildNodes.item(j).getNodeName() == "bpmn:userTask")
//  	                  			nUserTask++;
//  	                  		if(taskChildNodes.item(j).getNodeName() == "bpmn:manualTask")
//  	                  			nManualTask++;
//  	                  		if(taskChildNodes.item(j).getNodeName() == "bpmn:businessRuleTask")
//  	                  			nBusinessRuleTask++;
//  	                  		if(taskChildNodes.item(j).getNodeName() == "bpmn:serviceTask")
//  	                  			nServiceTask++;
//  	                  		if(taskChildNodes.item(j).getNodeName() == "bpmn:scriptTask")
//  	                  			nScriptTask++;
//  	                  		if(taskChildNodes.item(j).getNodeName() == "bpmn:callActivity")
//  	                  			nCallActivity++;
//  	                  	
//  	                  	}
//                    }
//            		
//            	}
//            	
//            	
//            }
//            
//            
//        } catch (Exception E) {
//            System.out.println(E);
//        }
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
//            row.createCell(17).setCellValue(nGroup);
//            row.createCell(18).setCellValue(nLane);
//            row.createCell(19).setCellValue(nDataObject);
//            row.createCell(20).setCellValue(nDataObjectReference);
//            row.createCell(21).setCellValue(nDataStore);
//            row.createCell(22).setCellValue(nDataStoreReference);
//            row.createCell(23).setCellValue(nDataInput);
//            row.createCell(24).setCellValue(nDataOutput);
//            row.createCell(25).setCellValue(nExclusiveGateway);
//            row.createCell(26).setCellValue(nParallelGateway);
//            row.createCell(27).setCellValue(nInclusiveGateway);
//            row.createCell(28).setCellValue(nEventBasedGateway);
//            row.createCell(29).setCellValue(nComplexGateway);
//            row.createCell(30).setCellValue(nCondition);
//            row.createCell(31).setCellValue(nStartMultipleParallelEventDefinition);
//            row.createCell(32).setCellValue(nStartMultipleEventDefinition);
//            row.createCell(33).setCellValue(nStartNoneEvent);
//            row.createCell(34).setCellValue(nStartSignalEventDefinition);
//            row.createCell(35).setCellValue(nStartConditionalEventDefinition);
//            row.createCell(36).setCellValue(nStartTimerEventDefinition);
//            row.createCell(37).setCellValue(nStartMessageEventDefinition);
//            row.createCell(38).setCellValue(nStartCompensateEventDefinition);
//            row.createCell(39).setCellValue(nStartCancelEventDefinition);
//            row.createCell(40).setCellValue(nStartEscalationEventDefinition);
//            row.createCell(41).setCellValue(nStartErrorEventDefinition);
//            row.createCell(42).setCellValue(nEndEventNone);
//            row.createCell(43).setCellValue(nEndMultipleEventDefinition); 
//            row.createCell(44).setCellValue(nEndEscalationEventDefinition);
//            row.createCell(45).setCellValue(nEndErrorEventDefinition);
//            row.createCell(46).setCellValue(nEndSignalEventDefinition);
//            row.createCell(47).setCellValue(nEndCompensateEventDefinition);
//            row.createCell(48).setCellValue(nEndCancelEventDefinition); 
//            row.createCell(49).setCellValue(nEndMessageEventDefinition);
//            row.createCell(50).setCellValue(nEndTerminateEventDefinition);
//            row.createCell(51).setCellValue(nIntermediateCatchMultipleEventDefinition);
//            row.createCell(52).setCellValue(nIntermediateCatchMultipleParallelEventDefinition);
//            row.createCell(53).setCellValue(nIntermediateCatchMessageEventDefinition);
//            row.createCell(54).setCellValue(nIntermediateCatchTimerEventDefinition);
//            row.createCell(55).setCellValue(nIntermediateCatchConditionalEventDefinition);
//            row.createCell(56).setCellValue(nIntermediateCatchLinkEventDefinition);
//            row.createCell(57).setCellValue(nIntermediateSignalMessageEventDefinition);
//            row.createCell(58).setCellValue(nIntermediateThrowEvent);
//            row.createCell(59).setCellValue(nIntermediateThrowMessageEventDefinition);
//            row.createCell(60).setCellValue(nIntermediateThrowEscalationEventDefinition);
//            row.createCell(61).setCellValue(nIntermediateThrowLinkEventDefinition);
//            row.createCell(62).setCellValue(nIntermediateThrowSignalEventDefinition);
//            row.createCell(63).setCellValue(nIntermediateThrowCompensateEventDefinition);
//            row.createCell(64).setCellValue(nIntermediateThrowMultipleParallelEventDefinition);
//            row.createCell(65).setCellValue(nBoundaryMessageEvent);
//            row.createCell(66).setCellValue(nBoundaryTimerEvent);
//            row.createCell(67).setCellValue(nBoundaryCancelEvent);
//            row.createCell(68).setCellValue(nBoundaryConditionalEvent);
//            row.createCell(69).setCellValue(nBoundaryEscalationEvent);
//            row.createCell(70).setCellValue(nBoundaryErrorEvent);
//            row.createCell(71).setCellValue(nBoundarySignalEvent);
//            row.createCell(72).setCellValue(nBoundaryCompensateEvent);
//            row.createCell(73).setCellValue(nBoundaryTimerEventNonInt);
//            row.createCell(74).setCellValue(nBoundaryEscalationEventNonInt);
//            row.createCell(75).setCellValue(nBoundaryConditionalEventNonInt);
//            row.createCell(76).setCellValue(nBoundaryMessageEventNonInt);
//            row.createCell(77).setCellValue(nMessageFlow);
//            row.createCell(78).setCellValue(nSequenceFlow);
//            row.createCell(79).setCellValue(nDefaultFlow);
//            row.createCell(80).setCellValue(nConditionalFlow);
//            row.createCell(81).setCellValue(nPool);
//            row.createCell(82).setCellValue(nVerticalLane);
//            row.createCell(83).setCellValue(nVerticalPool);
//            row.createCell(84).setCellValue(nChoreographyTask);
//            row.createCell(85).setCellValue(nChoreographyParticipant);
//            row.createCell(86).setCellValue(nChoreographySubprocess);
//            row.createCell(87).setCellValue(nConversation);
//            row.createCell(88).setCellValue(nSubConversation);
//            row.createCell(89).setCellValue(nCallConversation);
//            row.createCell(90).setCellValue(nConversationLink);
//            row.createCell(91).setCellValue(nITSystem);
//            row.createCell(92).setCellValue(nAssociation);
//            row.createCell(93).setCellValue(nCompensateAssociation);
//            row.createCell(94).setCellValue(nUnidirectionalAssociation);
//            row.createCell(95).setCellValue(nUndirectedAssociation);
//            row.createCell(96).setCellValue(nBidirectionalAssociation);
//            row.createCell(97).setCellValue(nTextAnnotation);
//            row.createCell(98).setCellValue(ndataOutputAssociation);
//            row.createCell(99).setCellValue(ndataInputAssociation);
//            row.createCell(100).setCellValue(TotalElements);
            
            
      		FileOutputStream fileOut = new FileOutputStream("bpmn_stats.xls");
       		wb.write(fileOut);  
       		//closing the Stream  
       		fileOut.close();  
            //closing the workbook  
       		wb.close(); 
        	}
        }
}