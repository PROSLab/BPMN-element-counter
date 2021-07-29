package pros.unicam.it.jersey;

import java.io.File;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
        String modelType;
        
        int nTask=0;
        int nTaskMultipleIstance=0;
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
        
        
    	//Read files
    	File xmlFile = new File("C:/Users/User/Desktop/BPMN-element-counter/testmodels/Collaboration.bpmn");
    	String xml = new String(Files.readAllBytes(xmlFile.toPath()), StandardCharsets.UTF_8);
        System.out.println(xml);
        
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
        
        System.out.println(bpmnModeler);
        
        
        // Check if the model is a Collaboration, a Process or contain a Subprocess
        
        // Check if is a collaboration
        XPathExpression exprModelTypeCol = xpath.compile("//bpmn:definitions");
        Object resultModelType = exprModelTypeCol.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesModelType = (NodeList) resultModelType;
        
        for(int i=0; i<nodesModelType.getLength(); i++) {
        	
        	NodeList nodeModelType = nodesModelType.item(i).getChildNodes();
        	for(int j=0; j<nodeModelType.getLength(); j++) {	
           	 
        	
		        if(nodesModelType.item(i).getChildNodes().toString() == "bpmn:collaboration") {
		        	modelType = "Collaboration";
		        	System.out.println(modelType);
		        }
	        
	        	modelType = "Process";
	        	System.out.println(modelType);
        	}
        }	
        
        // Check if contain a subProcess and the number of subprocess
        XPathExpression exprModelTypeSub = xpath.compile("//bpmn:process");
        Object resultModelTypeSub = exprModelTypeSub.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesModelTypeSub = (NodeList) resultModelTypeSub;      
        for(int i=0; i<nodesModelTypeSub.getLength(); i++) {
        	
    	NodeList nodeModelType = nodesModelTypeSub.item(i).getChildNodes();
    	int NumberOfsubProcess = 0;
        	
        	 for(int j=0; j<nodeModelType.getLength(); j++) {	
        	 
        		 
		        	if(nodeModelType.item(j).getNodeName().toString() == "bpmn:subProcess") {      		
		        		NumberOfsubProcess++;
		            	System.out.println("There are: "+NumberOfsubProcess+" subProcess/es in the model");
		            }
        	 }
		        
        }    
        // XPath Query for showing all Intermediate Catch Events

//        try {
//            XPathExpression expr = xpath.compile("//bpmn:intermediateCatchEvent");
//            Object result = expr.evaluate(doc, XPathConstants.NODESET);
//            NodeList nodes = (NodeList) result;
//            doc.getDocumentElement().normalize();            
//            //Intermediate Catch Event 
//            NodeList listOfIntermediateCatchEvents = doc.getElementsByTagName("bpmn:intermediateCatchEvent");
//            int totalIntermediateCatchEvents = listOfIntermediateCatchEvents.getLength();
//            System.out.println("Total number of Intermediate Catch Events: " + totalIntermediateCatchEvents);
//            
//            
//            for(int i=0; i<listOfIntermediateCatchEvents.getLength() ; i++) {
//            	
//            	Node intermediateCatchEvent = listOfIntermediateCatchEvents.item(i);   
//            	
//            	if(intermediateCatchEvent.hasChildNodes()) {
//            		
//            		NodeList intermediateCatchEventChldNodes = intermediateCatchEvent.getChildNodes();
//            		
//            		//System.out.println(intermediateCatchEventChldNodes.getLength());
//            		
//                    for(int j=0;j<intermediateCatchEventChldNodes.getLength(); j++) {
//
//                    	if(intermediateCatchEventChldNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
//                    	//System.out.println(" "+intermediateCatchEventChldNodes.item(j).getNodeName());
//                    	}
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

    }
}