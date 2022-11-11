package pros.unicam.it.bpmnelementcounter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.bridj.util.Pair;
import org.javatuples.Triplet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import javax.swing.JFileChooser;


public class Main {

	//private static boolean TextAnalysis = true;

	public static void main(String[] args) throws Exception{

		 System.out.println("=========== :: BPMN-Metrics-Extractor :: ===========");
		 System.out.println("\n Select the folder of BPMN models to be analysed:");
		 				
		 
		try {
			String path = "./bpmn_elements.csv";	
			//System.out.println("path: "+path);
			//If the file already exist, it is overwrited
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8));				    
			bw.write("fileName;");
			bw.write("bpmnModeler;");
			bw.write("modelType;");
			bw.write("isEnglish;");
			bw.write("nTaskNoneLoopNoneCompensateNoneCallNone;");
			bw.write("nTaskNoneLoopNoneCompensateNoneCall;");
			bw.write("nTaskNoneLoopNoneCompensateCallNone;");
			bw.write("nTaskNoneLoopNoneCompensateCall;");
			bw.write("nTaskNoneLoopStandardCompensateNoneCallNone;");
			bw.write("nTaskNoneLoopStandardCompensateNoneCall;");
			bw.write("nTaskNoneLoopStandardCompensateCallNone;");
			bw.write("nTaskNoneLoopStandardCompensateCall;");
			bw.write("nTaskNoneLoopMIParallelCompensateNoneCallNone;");
			bw.write("nTaskNoneLoopMIParallelCompensateNoneCall;");
			bw.write("nTaskNoneLoopMIParallelCompensateCallNone;");
			bw.write("nTaskNoneLoopMIParallelCompensateCall;");
			bw.write("nTaskNoneLoopMISequentialCompensateNoneCallNone;");
			bw.write("nTaskNoneLoopMISequentialCompensateNoneCall;");
			bw.write("nTaskNoneLoopMISequentialCompensateCallNone;");
			bw.write("nTaskNoneLoopMISequentialCompensateCall;");
			bw.write("nTaskSendLoopNoneCompensateNone;");
			bw.write("nTaskSendLoopNoneCompensate;");
			bw.write("nTaskSendLoopStandardCompensateNone;");           
			bw.write("nTaskSendLoopStandardCompensate;"); 
			bw.write("nTaskSendLoopMIParallelCompensateNone;"); 
			bw.write("nTaskSendLoopMIParallelCompensate;");
			bw.write("nTaskSendLoopMISequentialCompensateNone;"); 
			bw.write("nTaskSendLoopMISequentialCompensate;");
			bw.write("nTaskReceiveLoopNoneCompensateNone;");            
			bw.write("nTaskReceiveLoopNoneCompensate;");           
			bw.write("nTaskReceiveLoopStandardCompensateNone;");            
			bw.write("nTaskReceiveLoopStandardCompensate;");            
			bw.write("nTaskReceiveLoopMIParallelCompensateNone;");                        
			bw.write("nTaskReceiveLoopMIParallelCompensate;");            
			bw.write("nTaskReceiveLoopMISequentialCompensateNone;");           
			bw.write("nTaskReceiveLoopMISequentialCompensate;");            
			bw.write("nTaskUserLoopNoneCompensateNone;");            
			bw.write("nTaskUserLoopNoneCompensate;");           
			bw.write("nTaskUserLoopStandardCompensateNone;");            
			bw.write("nTaskUserLoopStandardCompensate;");           
			bw.write("nTaskUserLoopMIParallelCompensateNone;");            
			bw.write("nTaskUserLoopMIParallelCompensate;");            
			bw.write("nTaskUserLoopMISequentialCompensateNone;");            
			bw.write("nTaskUserLoopMISequentialCompensate;");            
			bw.write("nTaskManualLoopNoneCompensateNone;");            
			bw.write("nTaskManualLoopNoneCompensate;");            
			bw.write("nTaskManualLoopStandardCompensateNone;");            
			bw.write("nTaskManualLoopStandardCompensate;");            
			bw.write("nTaskManualLoopMIParallelCompensateNone;");            
			bw.write("nTaskManualLoopMIParallelCompensate;");            
			bw.write("nTaskManualLoopMISequentialCompensateNone;");            
			bw.write("nTaskManualLoopMISequentialCompensate;");            
			bw.write("nTaskBusinessRuleLoopNoneCompensateNone;");            
			bw.write("nTaskBusinessRuleLoopNoneCompensate;");            
			bw.write("nTaskBusinessRuleLoopStandardCompensateNone;");            
			bw.write("nTaskBusinessRuleLoopStandardCompensate;");            
			bw.write("nTaskBusinessRuleLoopMIParallelCompensateNone;");           
			bw.write("nTaskBusinessRuleLoopMIParallelCompensate;");            
			bw.write("nTaskBusinessRuleLoopMISequentialCompensateNone;");           
			bw.write("nTaskBusinessRuleLoopMISequentialCompensate;");            
			bw.write("nTaskServiceLoopNoneCompensateNone;");            
			bw.write("nTaskServiceLoopNoneCompensate;");            
			bw.write("nTaskServiceLoopStandardCompensateNone;");            
			bw.write("nTaskServiceLoopStandardCompensate;");            
			bw.write("nTaskServiceLoopMIParallelCompensateNone;");            
			bw.write("nTaskServiceLoopMIParallelCompensate;");            
			bw.write("nTaskServiceLoopMISequentialCompensateNone;");            
			bw.write("nTaskServiceLoopMISequentialCompensate;");            
			bw.write("nTaskScriptLoopNoneCompensateNone;");            
			bw.write("nTaskScriptLoopNoneCompensate;");           
			bw.write("nTaskScriptLoopStandardCompensateNone;");            
			bw.write("nTaskScriptLoopStandardCompensate;");            
			bw.write("nTaskScriptLoopMIParallelCompensateNone;");            
			bw.write("nTaskScriptLoopMIParallelCompensate;");            
			bw.write("nTaskScriptLoopMISequentialCompensateNone;");            
			bw.write("nTaskScriptLoopMISequentialCompensate;");            
			bw.write("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone;");
			bw.write("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate;");
			bw.write("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone;");
			bw.write("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate;");
			bw.write("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone;");
			bw.write("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate;");
			bw.write("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone;");
			bw.write("nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate;");
			bw.write("nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone;");
			bw.write("nSubProcessExtendedEventNoneAdHocLoopNoneCompensate;");
			bw.write("nSubProcessExtendedEventNoneAdHocLoopStandardCompensateNone;");
			bw.write("nSubProcessExtendedEventNoneAdHocLoopStandardCompensate;");
			bw.write("nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone;");
			bw.write("nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate;");
			bw.write("nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone;");
			bw.write("nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate;");
			bw.write("nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone;");
			bw.write("nSubProcessExtendedEventNoneTransactionLoopNoneCompensate;");
			bw.write("nSubProcessExtendedEventNoneTransactionLoopStandardCompensateNone;");
			bw.write("nSubProcessExtendedEventNoneTransactionLoopStandardCompensate;");
			bw.write("nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone;");
			bw.write("nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate;");
			bw.write("nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone;");
			bw.write("nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate;");
			bw.write("nSubProcessExtendedEventLoopNoneCompensateNone;");
			bw.write("nSubProcessExtendedEventLoopNoneCompensate;");
			bw.write("nSubProcessExtendedEventLoopStandardCompensateNone;");
			bw.write("nSubProcessExtendedEventLoopStandardCompensate;");
			bw.write("nSubProcessExtendedEventLoopMIParallelCompensateNone;");
			bw.write("nSubProcessExtendedEventLoopMIParallelCompensate;");
			bw.write("nSubProcessExtendedEventLoopMISequentialCompensateNone;");
			bw.write("nSubProcessExtendedEventLoopMISequentialCompensate;");
			bw.write("nSubProcessExtendedEventAdHocLoopNoneCompensateNone;");
			bw.write("nSubProcessExtendedEventAdHocLoopNoneCompensate;");
			bw.write("nSubProcessExtendedEventAdHocLoopStandardCompensateNone;");
			bw.write("nSubProcessExtendedEventAdHocLoopStandardCompensate;");
			bw.write("nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone;");
			bw.write("nSubProcessExtendedEventAdHocLoopMIParallelCompensate;");
			bw.write("nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone;");
			bw.write("nSubProcessExtendedEventAdHocLoopMISequentialCompensate;");
			bw.write("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone;");
			bw.write("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate;");
			bw.write("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone;");
			bw.write("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate;");
			bw.write("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone;");
			bw.write("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate;");
			bw.write("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone;");
			bw.write("nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate;");
			bw.write("nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone;");
			bw.write("nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate;");
			bw.write("nSubProcessCollapsedEventNoneAdHocLoopStandardCompensateNone;");
			bw.write("nSubProcessCollapsedEventNoneAdHocLoopStandardCompensate;");
			bw.write("nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone;");
			bw.write("nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate;");
			bw.write("nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone;");
			bw.write("nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate;");
			bw.write("nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone;");
			bw.write("nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate;");
			bw.write("nSubProcessCollapsedEventNoneTransactionLoopStandardCompensateNone;");
			bw.write("nSubProcessCollapsedEventNoneTransactionLoopStandardCompensate;");
			bw.write("nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensateNone;");
			bw.write("nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensate;");
			bw.write("nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensateNone;");
			bw.write("nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensate;");
			bw.write("nSubProcessCollapsedEventLoopNoneCompensateNone;");
			bw.write("nSubProcessCollapsedEventLoopNoneCompensate;");
			bw.write("nSubProcessCollapsedEventLoopStandardCompensateNone;");
			bw.write("nSubProcessCollapsedEventLoopStandardCompensate;");
			bw.write("nSubProcessCollapsedEventLoopMIParallelCompensateNone;");
			bw.write("nSubProcessCollapsedEventLoopMIParallelCompensate;");
			bw.write("nSubProcessCollapsedEventLoopMISequentialCompensateNone;");
			bw.write("nSubProcessCollapsedEventLoopMISequentialCompensate;");
			bw.write("nSubProcessCollapsedEventAdHocLoopNoneCompensateNone;");
			bw.write("nSubProcessCollapsedEventAdHocLoopNoneCompensate;");
			bw.write("nSubProcessCollapsedEventAdHocLoopStandardCompensateNone;");
			bw.write("nSubProcessCollapsedEventAdHocLoopStandardCompensate;");
			bw.write("nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone;");
			bw.write("nSubProcessCollapsedEventAdHocLoopMIParallelCompensate;");
			bw.write("nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone;");
			bw.write("nSubProcessCollapsedEventAdHocLoopMISequentialCompensate;");
			bw.write("nDataObject;");
			bw.write("nDataObjectCollection;");
			bw.write("nDataStore;");
			bw.write("nDataInput;");
			bw.write("nDataOutput;");
			bw.write("nExclusiveGatewayNoMarker;");
			bw.write("nExclusiveGatewayMarker;");
			bw.write("nParallelGateway;");
			bw.write("nInclusiveGateway;");
			bw.write("nEventBasedGateway;");
			bw.write("nEventBasedGatewayExclusiveInstantiation;");
			bw.write("nEventBasedGatewayParallelInstantiation;");
			bw.write("nComplexGateway;");
			bw.write("nStartMultipleParallelEventDefinition;");
			bw.write("nStartMultipleEventDefinition;");
			bw.write("nStartNoneEventDefinition;");
			bw.write("nStartSignalEventDefinition;");
			bw.write("nStartConditionalEventDefinition;");
			bw.write("nStartTimerEventDefinition;");
			bw.write("nStartMessageEventDefinition;");
			bw.write("nStartMessageEventSubProcessInterruptingDefinition;");
			bw.write("nStartTimerEventSubProcessInterruptingDefinition;");
			bw.write("nStartEscalationEventSubProcessInterruptingDefinition;");
			bw.write("nStartConditionalEventSubProcessInterruptingDefinition;");
			bw.write("nStartErrorEventSubProcessInterruptingDefinition;");
			bw.write("nStartCompensateEventSubProcessInterruptingDefinition;");
			bw.write("nStartSignalEventSubProcessInterruptingDefinition;");
			bw.write("nStartMultipleEventSubProcessInterruptingDefinition;");
			bw.write("nStartMultipleParallelEventSubProcessInterruptingDefinition;");       
			bw.write("nStartMessageEventSubProcessNonInterruptingDefinition;");
			bw.write("nStartTimerEventSubProcessNonInterruptingDefinition;");
			bw.write("nStartEscalationEventSubProcessNonInterruptingDefinition;");
			bw.write("nStartConditionalEventSubProcessNonInterruptingDefinition;");
			bw.write("nStartSignalEventSubProcessNonInterruptingDefinition;");
			bw.write("nStartMultipleParallelEventSubProcessNonInterruptingDefinition;");
			bw.write("nStartMultipleEventSubProcessNonInterruptingDefinition;");       
			bw.write("nEndNoneEventDefinition;");
			bw.write("nEndMultipleEventDefinition;"); 
			bw.write("nEndEscalationEventDefinition;");
			bw.write("nEndErrorEventDefinition;");
			bw.write("nEndSignalEventDefinition;");
			bw.write("nEndCompensateEventDefinition;");
			bw.write("nEndCancelEventDefinition;"); 
			bw.write("nEndMessageEventDefinition;");
			bw.write("nEndTerminateEventDefinition;");
			bw.write("nIntermediateCatchMultipleEventDefinition;");
			bw.write("nIntermediateCatchMultipleParallelEventDefinition;");
			bw.write("nIntermediateCatchMessageEventDefinition;");
			bw.write("nIntermediateCatchTimerEventDefinition;");
			bw.write("nIntermediateCatchConditionalEventDefinition;");
			bw.write("nIntermediateCatchLinkEventDefinition;");
			bw.write("nIntermediateCatchSignalEventDefinition;");
			bw.write("nIntermediateThrowNoneEventDefinition;");
			bw.write("nIntermediateThrowMessageEventDefinition;");
			bw.write("nIntermediateThrowEscalationEventDefinition;");
			bw.write("nIntermediateThrowLinkEventDefinition;");
			bw.write("nIntermediateThrowSignalEventDefinition;");
			bw.write("nIntermediateThrowCompensateEventDefinition;");
			bw.write("nIntermediateThrowMultipleEventDefinition;");
			bw.write("nIntermediateBoundaryMessageEvent;");
			bw.write("nIntermediateBoundaryTimerEvent;");
			bw.write("nIntermediateBoundaryCancelEvent;");
			bw.write("nIntermediateBoundaryConditionalEvent;");
			bw.write("nIntermediateBoundaryEscalationEvent;");
			bw.write("nIntermediateBoundaryErrorEvent;");
			bw.write("nIntermediateBoundarySignalEvent;");
			bw.write("nIntermediateBoundaryCompensateEvent;");
			bw.write("nIntermediateBoundaryMultipleEvent;");
			bw.write("nIntermediateBoundaryMultipleParallelEvent;");
			bw.write("nIntermediateBoundaryTimerEventNonInterrupting;");
			bw.write("nIntermediateBoundaryEscalationEventNonInterrupting;");
			bw.write("nIntermediateBoundaryConditionalEventNonInterrupting;");
			bw.write("nIntermediateBoundaryMessageEventNonInterrupting;");
			bw.write("nIntermediateBoundarySignalEventNonInterrupting;");
			bw.write("nIntermediateBoundaryMultipleEventNonInterrupting;");
			bw.write("nIntermediateBoundaryMultipleParallelEventNonInterrupting;");
			bw.write("nMessageFlow;");
			bw.write("nSequenceFlow;");
			bw.write("nDefaultFlow;");
			bw.write("nConditionalFlow;");
			bw.write("nLane;"); 
			bw.write("nPoolCollapsedMultiplicityNone;");
			bw.write("nPoolCollapsedMultiplicity;");
			bw.write("nPoolExpandedMultiplicityNone;");
			bw.write("nPoolExpandedMultiplicity;");
			bw.write("nChoreographyTask;");
			bw.write("nChoreographyMessage;");            
			bw.write("nChoreographyTaskSequentialMultipleInstance;");
			bw.write("nChoreographyTaskParallelMultipleInstance;");
			bw.write("nChoreographyTaskLoop;");
			bw.write("nChoreographySubprocessCollapsed;");
			bw.write("nChoreographySubprocessCollapsedParallelMultipleInstance;");
			bw.write("nChoreographySubprocessCollapsedSequentialMultipleInstance;");
			bw.write("nChoreographySubprocessCollapsedLoop;");
			bw.write("nChoreographySubprocessCollapsedCall;");
			bw.write("nChoreographySubprocessCollapsedCallSequentialMultipleInstance;");
			bw.write("nChoreographySubprocessCollapsedCallParallelMultipleInstance;");
			bw.write("nChoreographySubprocessCollapsedCallLoop;");
			bw.write("nChoreographySubprocessExpanded;");
			bw.write("nChoreographySubprocessExpandedSequentialMultipleInstance;");
			bw.write("nChoreographySubprocessExpandedParallelMultipleInstance;");
			bw.write("nChoreographySubprocessExpandedLoop;");
			bw.write("nChoreographyParticipant;");
			bw.write("nChoreographyParticipantMultiple;");       
			bw.write("nConversationNone;");
			bw.write("nConversationSubProcess;");
			bw.write("nConversationCall;");
			bw.write("nConversationLink;");
			bw.write("nAssociationUndirected;");
			bw.write("nAssociationUnidirectional;");        
			bw.write("nAssociationBidirectional;");
			bw.write("nAssociationDataOutput;");
			bw.write("nAssociationDataInput;");            
			bw.write("nGroup;");
			bw.write("nTextAnnotation;");
			bw.write("Model's Execution Time;");
			bw.write("TotalElements;"); 
			bw.write("Practical Complexity;"); 
			bw.write("File Size (Kb);");			
			bw.write("Duplicate String;"); 	
			bw.write("Labels Separated by (^^^);"); 
			bw.write("Concatenated Labels;");
			bw.write("Total number of words;"); 
			bw.write("Total number of characters;"); 
			bw.write("average;"); 
			bw.write("median;"); 
			bw.write("mode;"); 
			bw.write("\n"); 
			
//	If no github
 		    JFileChooser f = new JFileChooser();		
			f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			f.showSaveDialog(null);
			
			File file = f.getSelectedFile();
			String folderString = file.getAbsolutePath().toString();
			
			File folder = new File(folderString);
			File[] listOfFiles = folder.listFiles();
					
			for (int x = 0; x < listOfFiles.length; x++) {
//if no github 
				
				
				
/*if github For github files stored in subfolder and which path is stored in a file
				
				 	
				String folderPath = "/Users/fabriziofornari/Desktop/ANALISI SU MODELLI/Crawler_Models/";
				String filePath= "/Users/fabriziofornari/Desktop/ANALISI SU MODELLI/GitHubPaths.csv";
				BufferedReader br = new BufferedReader(new FileReader(filePath));
				String line;
				//int x=-1;
				//BufferedWriter writer = new BufferedWriter(new FileWriter("check"));
				while ((line = br.readLine()) != null) {
				//System.out.println(line);
end if github								
*/			
				try {
					
								
				long StartingtimeMillis = System.currentTimeMillis();
				//Defining global variables
				String fileName;
							
				
//if no GitHub				
				fileName= listOfFiles[x].getName();
				//if(!fileName.contains(".bpmn")) {System.out.println("File does not have a .bpmn extension");return;}
						
				//Read bpmn models
				File xmlFile = new File(folderString+"/"+fileName);	
// end if no github 
				
/*if GitHub
				fileName= line;
				//if(!fileName.contains(".bpmn")) {System.out.println("File does not have a .bpmn extension");return;}
						
				//Read bpmn models
				File xmlFile = new File(folderPath+"/"+line);	
				

endif GitHub	*/			
				
				String bpmnModeler="Undefined";
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
				int nConversationLink=0;
				//Association
				int nAssociationUndirected=0;
				int nAssociationUnidirectional=0;        
				int nAssociationBidirectional=0;
				int nAssociationDataOutput=0;
				int nAssociationDataInput=0;
				//Others
				int nGroup=0;
				int nTextAnnotation=0;
				int TotalElements=0;
				//SubProcesses Analysis variables
				int FileSize=0;
				String DuplicateString ;
				//Set BPMN models name
				
					String xml = new String(Files.readAllBytes(xmlFile.toPath()), StandardCharsets.UTF_8);  					
					FileSize = (int) xmlFile.length();
					
//					if(xml.isEmpty())
//						break;
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
					
				//[TODO: Namespace]
				// Check the modeler type
				if(doc.getDocumentElement().getAttribute("targetNamespace").contains("bpmn.io")) {
					bpmnModeler = "bpmn-js";					
				}
				else if (doc.getDocumentElement().getAttribute("targetNamespace").contains("signavio")) {
					bpmnModeler = "Signavio";
				}
				else if (doc.getDocumentElement().getAttribute("targetNamespace").contains("activiti")) {
					bpmnModeler = "Activiti";
				}
				else if (doc.getDocumentElement().getAttribute("targetNamespace").contains("camunda")) {
					bpmnModeler = "Camunda";
				}
				else if(doc.getDocumentElement().getAttribute("targetNamespace").contains("bpmn2")) {
					bpmnModeler = "BPMN2";
				}
				else if(doc.getDocumentElement().getAttribute("targetNamespace").contains("bpt-lab")) {
					bpmnModeler = "chor-js";
				}
				else {
					bpmnModeler = "Undefined";
				}
				
				// Check if the model is a Collaboration, a Process or contain a Subprocess
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

				// [SUBPROCESS EXTENDED]
				// SubProcess Normal Extended 
				for(int i=0;i<nodesSubprocesses.getLength();i++) {
					String SubprocessesID = (((Element) nodesSubprocesses.item(i)).getAttribute("id"));

					Node SubPnodeChild = nodesSubprocesses.item(i);  

					for(int j=0;j<nodesShapesList.getLength();j++) {
						String SubprocessesShape = (((Element) nodesShapesList.item(j)).getAttribute("bpmnElement"));

						NodeList SubPnodeChildNodes = SubPnodeChild.getChildNodes();  

						if(SubprocessesID.equalsIgnoreCase(SubprocessesShape) &&
								((Element) nodesShapesList.item(j)).getAttribute("isExpanded").contains("true")) {
							
						if(SubPnodeChildNodes.getLength()<=0) {
							if(((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")==false)
							{
									nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone++;
							}

							if(((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true"))
							{
									nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate++;
							}
						}
						else {
							
						for(int z=0;z<SubPnodeChildNodes.getLength(); z++)
						{
							try {

								

									if(SubPnodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
											SubPnodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
											((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")==false)
									{
										if(z==SubPnodeChildNodes.getLength()-1) 
											nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone++;
									}

									if(SubPnodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
											SubPnodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
											((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")){
										if(z==SubPnodeChildNodes.getLength()-1) 
											nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate++;
									}

									if(SubPnodeChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE) {

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

									}    						

								}catch (Exception e) {}
							}
						}}

					}

				}	
				nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone = nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone
						- nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone - nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone
						- nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone;

				nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate = nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate 
						- nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate - nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate
						- nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate;

				// [ADHOC EXTENDED]
				// SubProcess adhoc Extended
				for(int i=0;i<nodesSubprocessesAdHoc.getLength();i++) {
					String SubprocessesID = (((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("id"));

					Node SPAdHocNodeChild = nodesSubprocessesAdHoc.item(i);  

					for(int j=0;j<nodesShapesList.getLength();j++) {
						String SubprocessesShape = (((Element) nodesShapesList.item(j)).getAttribute("bpmnElement"));

						NodeList SPAdHocNodeChildNodes = SPAdHocNodeChild.getChildNodes();  
						
						if(SubprocessesID.equalsIgnoreCase(SubprocessesShape) &&
								((Element) nodesShapesList.item(j)).getAttribute("isExpanded").contains("true")) {
							
						if(SPAdHocNodeChildNodes.getLength()<=0) {
							
							if(((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false && 
									((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")==false)
									
							{									
									nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone++;	
							}


							if(((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true") &&
									((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")==false) {

									nSubProcessExtendedEventNoneAdHocLoopNoneCompensate++;	
							}

							
						}
						else {

						for(int z=0;z<SPAdHocNodeChildNodes.getLength(); z++)
						{
							try {
									if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
											SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
											((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false && 
											((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")==false)
											
									{
										if(z==SPAdHocNodeChildNodes.getLength()-1) 											
											nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone++;	
									}


									if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
											SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
											((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true") &&
											((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")==false) {
										if(z==SPAdHocNodeChildNodes.getLength()-1) 
											nSubProcessExtendedEventNoneAdHocLoopNoneCompensate++;	
									}

									if(SPAdHocNodeChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE) {			

										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false && 
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")==false) {
											nSubProcessExtendedEventNoneAdHocLoopStandardCompensateNone++;

										}

										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")&& 
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")==false) {
											nSubProcessExtendedEventNoneAdHocLoopStandardCompensate++;

										}

										//mi par
										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SPAdHocNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false&& 
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")==false) {
											nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone++;


										}

										//mi par comp
										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SPAdHocNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")&& 
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")==false) {
											nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate++;

										}

										//mi seq
										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SPAdHocNodeChildNodes.item(z)).getAttribute("isSequential").contains("true") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false&& 
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")==false) {
											nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone++;


										}
										//mi seq comp
										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SPAdHocNodeChildNodes.item(z)).getAttribute("isSequential").contains("true") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")&& 
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")==false) {
											nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate++;

										}


									}    						

								}catch (Exception e) {}
							}
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

				// [TRANSACTION EXTENDED]
				// SubProcess transaction Extended
				for(int i=0;i<nodesTransaction.getLength();i++) {
					String SubprocessesID = (((Element) nodesTransaction.item(i)).getAttribute("id"));

					Node TransactionNodeChild = nodesTransaction.item(i);  

					for(int j=0;j<nodesShapesList.getLength();j++) {
						String SubprocessesShape = (((Element) nodesShapesList.item(j)).getAttribute("bpmnElement")); 

						NodeList TransactionNodeChildNodes = TransactionNodeChild.getChildNodes(); 
						
						if(SubprocessesID.equalsIgnoreCase(SubprocessesShape) &&
								((Element) nodesShapesList.item(j)).getAttribute("isExpanded").contains("true")) {
							
						if(TransactionNodeChildNodes.getLength()<=0) {
							
							if(((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")==false)
							{
								nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone++;	
							}

							if(((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true"))
							{
								nSubProcessExtendedEventNoneTransactionLoopNoneCompensate++;	
							}
						}
						else {

						for(int z=0;z<TransactionNodeChildNodes.getLength(); z++)
						{
							try {

								
									if(TransactionNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
											TransactionNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
											((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")==false)
									{
										if(z==TransactionNodeChildNodes.getLength()-1) 
											nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone++;	
									}

									if(TransactionNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
											TransactionNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
											((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true"))
									{
										if(z==TransactionNodeChildNodes.getLength()-1) 
											nSubProcessExtendedEventNoneTransactionLoopNoneCompensate++;	
									}

									if(TransactionNodeChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE) {

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
										if(TransactionNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) TransactionNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate++;
										}

										//mi seq
										if(TransactionNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) TransactionNodeChildNodes.item(z)).getAttribute("isSequential").contains("true") &&
												((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")==false) {
											nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone++;


										}
										//mi seq comp
										if(TransactionNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) TransactionNodeChildNodes.item(z)).getAttribute("isSequential").contains("true") &&
												((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")) {
											nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate++;

										}

									}    						

								}catch (Exception e) {}
							}
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

				// [EVENT SUBPROCESS EXTENDED]   	
				// SubProcess event Extended
				for(int i=0;i<nodesSubprocessesEvent.getLength();i++) {
					String SubprocessesID = (((Element) nodesSubprocessesEvent.item(i)).getAttribute("id"));

					Node SubPExtendedEventNodeChild = nodesSubprocessesEvent.item(i);  

					for(int j=0;j<nodesShapesList.getLength();j++) {
						String SubprocessesShape = (((Element) nodesShapesList.item(j)).getAttribute("bpmnElement"));

						NodeList SubprocessesEventNodeChildNodes = SubPExtendedEventNodeChild.getChildNodes();  
						
						if(SubprocessesID.equalsIgnoreCase(SubprocessesShape) &&
								((Element) nodesShapesList.item(j)).getAttribute("isExpanded").contains("true")) {
							
						if(SubprocessesEventNodeChildNodes.getLength()<=0) {
							
							if(((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false &&
									((Element) nodesSubprocessesEvent.item(i)).getAttribute("triggeredByEvent").contains("true"))
							{
									nSubProcessExtendedEventLoopNoneCompensateNone++;		
							}

							if(((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")&&
									((Element) nodesSubprocessesEvent.item(i)).getAttribute("triggeredByEvent").contains("true"))
							{
									nSubProcessExtendedEventLoopNoneCompensate++;		
							}
						}
						else {
							

						for(int z=0;z<SubprocessesEventNodeChildNodes.getLength(); z++)
						{
							try {

									if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
											SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
											((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false &&
											((Element) nodesSubprocessesEvent.item(i)).getAttribute("triggeredByEvent").contains("true"))
									{
										if(z==SubprocessesEventNodeChildNodes.getLength()-1) 
											nSubProcessExtendedEventLoopNoneCompensateNone++;		
									}

									if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
											SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
											((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")&&
											((Element) nodesSubprocessesEvent.item(i)).getAttribute("triggeredByEvent").contains("true"))
									{
										if(z==SubprocessesEventNodeChildNodes.getLength()-1) 
											nSubProcessExtendedEventLoopNoneCompensate++;		
									}

									if(SubprocessesEventNodeChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE) {

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==true &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false&&
										((Element) nodesSubprocessesEvent.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessExtendedEventLoopStandardCompensateNone++;

										}

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==true &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")&&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessExtendedEventLoopStandardCompensate++;

										}

										//mi par
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false&&
														((Element) nodesSubprocessesEvent.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessExtendedEventLoopMIParallelCompensateNone++;

										}

										//mi par comp
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false&&
														((Element) nodesSubprocessesEvent.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessExtendedEventLoopMIParallelCompensate++;
										}

										//mi seq
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==true &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false&&
														((Element) nodesSubprocessesEvent.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessExtendedEventLoopMISequentialCompensateNone++;


										}
										//mi seq comp
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==true &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")&&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessExtendedEventLoopMISequentialCompensate++;			        					

										}


									}    						

								}catch (Exception e) {}
							}	
							}
						}

					}

				}	
				nSubProcessExtendedEventLoopNoneCompensateNone = nSubProcessExtendedEventLoopNoneCompensateNone - nSubProcessExtendedEventLoopStandardCompensateNone
						- nSubProcessExtendedEventLoopMIParallelCompensateNone - nSubProcessExtendedEventLoopMISequentialCompensateNone;
				nSubProcessExtendedEventLoopNoneCompensate = nSubProcessExtendedEventLoopNoneCompensate - nSubProcessExtendedEventLoopStandardCompensate
						- nSubProcessExtendedEventLoopMIParallelCompensate - nSubProcessExtendedEventLoopMISequentialCompensate;
				/* 
             // [SUBPROCESS EXPANDED EVENT + ADHOC]
SUBPROCESS EXPANDED EVENT + ADHOC
				 */
				for(int i=0;i<nodesSubprocessesAdHoc.getLength();i++) {
					String SubprocessesID = (((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("id"));

					Node SubPExtendedEventNodeChild = nodesSubprocessesAdHoc.item(i);  

					for(int j=0;j<nodesShapesList.getLength();j++) {
						String SubprocessesShape = (((Element) nodesShapesList.item(j)).getAttribute("bpmnElement"));

						NodeList SubprocessesEventNodeChildNodes = SubPExtendedEventNodeChild.getChildNodes(); 
						
						if(SubprocessesID.equalsIgnoreCase(SubprocessesShape) &&
								((Element) nodesShapesList.item(j)).getAttribute("isExpanded").contains("true")) {
							
						if(SubprocessesEventNodeChildNodes.getLength()<=0)	{
							
							if(((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false &&
									((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true"))
							{
									nSubProcessExtendedEventAdHocLoopNoneCompensateNone++;														
							}

							if(((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true") &&
									((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true"))
							{
									nSubProcessExtendedEventAdHocLoopNoneCompensate++;														

							}
							
						}
						else {

						for(int z=0;z<SubprocessesEventNodeChildNodes.getLength(); z++)
						{
							try {

								

									if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
											SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
											((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false &&
											((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true"))
									{
										if(z==SubprocessesEventNodeChildNodes.getLength()-1) 
											nSubProcessExtendedEventAdHocLoopNoneCompensateNone++;														
									}

									if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
											SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
											((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true") &&
											((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")) {

										if(z==SubprocessesEventNodeChildNodes.getLength()-1) 
											nSubProcessExtendedEventAdHocLoopNoneCompensate++;														

									}

									if(SubprocessesEventNodeChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE) {

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==true &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessExtendedEventAdHocLoopStandardCompensateNone++;

										}

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==true &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessExtendedEventAdHocLoopStandardCompensate++;

										}

										//mi par
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone++;


										}

										//mi par comp
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessExtendedEventAdHocLoopMIParallelCompensate++;

										}

										//mi seq
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==true &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone++;


										}
										//mi seq comp
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==true &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessExtendedEventAdHocLoopMISequentialCompensate++;

										}

									}    						

								}catch (Exception e) {}
							}
						}
						}
					}
				}	

				nSubProcessExtendedEventAdHocLoopNoneCompensateNone = nSubProcessExtendedEventAdHocLoopNoneCompensateNone - nSubProcessExtendedEventAdHocLoopStandardCompensateNone
						- nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone - nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone;

				nSubProcessExtendedEventAdHocLoopNoneCompensate = nSubProcessExtendedEventAdHocLoopNoneCompensate - nSubProcessExtendedEventAdHocLoopStandardCompensate
						- nSubProcessExtendedEventAdHocLoopMIParallelCompensate - nSubProcessExtendedEventAdHocLoopMISequentialCompensate;

				// [SUBPROCESS COLLAPSED]
				// SubProcess Normal Collapsed 
				for(int i=0;i<nodesSubprocesses.getLength();i++) {
					String SubprocessesID = (((Element) nodesSubprocesses.item(i)).getAttribute("id"));

					Node SubPnodeChild = nodesSubprocesses.item(i);  

					for(int j=0;j<nodesShapesList.getLength();j++) {
						String SubprocessesShape = (((Element) nodesShapesList.item(j)).getAttribute("bpmnElement"));

						NodeList SubPnodeChildNodes = SubPnodeChild.getChildNodes();  
						
						if(SubprocessesID.equals(SubprocessesShape) &&
								((Element) nodesShapesList.item(j)).getAttribute("isExpanded").contains("true")==false) {
							
						if(SubPnodeChildNodes.getLength()<=0) {
							
							if(((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")==false)
							{	
									nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone++;
							}

							if(((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")) 
							{											
									nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate++;

							}

						
						}
						else {
							
						
						for(int z=0;z<SubPnodeChildNodes.getLength(); z++)
						{

							try {
									if(SubPnodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
											SubPnodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
											((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")==false)
									{

										if(z==SubPnodeChildNodes.getLength()-1) 		
											nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone++;
									}

									if(SubPnodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
											SubPnodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
											((Element) nodesSubprocesses.item(i)).getAttribute("isForCompensation").contains("true")) {

										if(z==SubPnodeChildNodes.getLength()-1) 												
											nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate++;

									}

									if(SubPnodeChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE) {


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

									}

							}catch (Exception e) {}
						}
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


				// [ADHOC COLLAPSED]
				// SubProcess adhoc Collapsed				
				//Vuol dire che c'è almeno un tag adHocSubProcess, entrare per scoprire cos'è
				for(int i=0;i<nodesSubprocessesAdHoc.getLength();i++) {
					
					String SubprocessesID = (((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("id"));

					Node SPAdHocNodeChild = nodesSubprocessesAdHoc.item(i);  
					
					for(int j=0;j<nodesShapesList.getLength();j++) {
						
						String SubprocessesShape = (((Element) nodesShapesList.item(j)).getAttribute("bpmnElement"));
						NodeList SPAdHocNodeChildNodes = SPAdHocNodeChild.getChildNodes();  
						
						if(SubprocessesID.equalsIgnoreCase(SubprocessesShape) &&
								((Element) nodesShapesList.item(j)).getAttribute("isExpanded").contains("true")==false) {
							
						if(SPAdHocNodeChildNodes.getLength()<=0) {
							
							if(((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false &&
									((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")==false)
							{
									nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone++;
							}
							
							if(((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true") &&
									((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")==false)
							{
								nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate++;
							}
														
							
						}
						else {
							for(int z=0;z<SPAdHocNodeChildNodes.getLength(); z++)
							{
								try {		

									if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
											SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
											((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false &&
											((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")==false)
									{
										if(z==SPAdHocNodeChildNodes.getLength()-1) 
											nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone++;
									}

									//nodesSubprocessesAdHoc.item(i).getAttributes();
											
											
									if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
											SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
											((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true") &&
											((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")==false) {
										if(z==SPAdHocNodeChildNodes.getLength()-1) 
											nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate++;

									}


									if(SPAdHocNodeChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE) {

										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")==false) {
											nSubProcessCollapsedEventNoneAdHocLoopStandardCompensateNone++;

										}

										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")==false) {
											nSubProcessCollapsedEventNoneAdHocLoopStandardCompensate++;

										}

										//mi par
										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SPAdHocNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")==false) {
											nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone++;


										}

										//mi par comp
										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) SPAdHocNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")==false) {
											nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate++;

										}

										//mi seq
										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==true &&
												((Element) SPAdHocNodeChildNodes.item(z)).getAttribute("isSequential").contains("true") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")==false) {
											nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone++;


										}
										//mi seq comp
										if(SPAdHocNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")&&
												((Element) SPAdHocNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")&&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")==false) {
											nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate++;

										}



									}    						

								}catch (Exception e) {}
							}
						}
						}

					}

				}		
				nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone = nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone - nSubProcessCollapsedEventNoneAdHocLoopStandardCompensateNone
						- nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone - nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone;

				nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate = nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate - nSubProcessCollapsedEventNoneAdHocLoopStandardCompensate
						- nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate - nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate;
				
				// [TRANSACTION COLLAPSED]		
				// SubProcess transaction Collapsed				
for(int i=0;i<nodesTransaction.getLength();i++) {					
					
					String SubprocessesID = (((Element) nodesTransaction.item(i)).getAttribute("id"));

					Node TransactionNodeChild = nodesTransaction.item(i);  

					for(int j=0;j<nodesShapesList.getLength();j++) {
						String SubprocessesShape = (((Element) nodesShapesList.item(j)).getAttribute("bpmnElement"));

						NodeList TransactionNodeChildNodes = TransactionNodeChild.getChildNodes();  
						
						
						if(SubprocessesID.equalsIgnoreCase(SubprocessesShape) &&
								((Element) nodesShapesList.item(j)).getAttribute("isExpanded").contains("true")==false) {
							
						if(TransactionNodeChildNodes.getLength()<=0) {
							
							if(((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")==false)
							{
									nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone++;		
							}

							if(((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")) 
							{
									nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate++;		
							}

						}
						else
						{
						for(int z=0;z<TransactionNodeChildNodes.getLength(); z++)
						{
							try {

									if(TransactionNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
											TransactionNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
											((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")==false)
									{
										if(z==TransactionNodeChildNodes.getLength()-1) 
											nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone++;		
									}


									if(TransactionNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
											TransactionNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
											((Element) nodesTransaction.item(i)).getAttribute("isForCompensation").contains("true")) 
									{
										if(z==TransactionNodeChildNodes.getLength()-1) 
											nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate++;		
									}

									if(TransactionNodeChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE) {

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



									}    						

								}catch (Exception e) {}
						}
						}
						}

					}

				}
				
				nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone = nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone - nSubProcessCollapsedEventNoneTransactionLoopStandardCompensateNone
						- nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensateNone - nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensateNone;

				nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate = nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate - nSubProcessCollapsedEventNoneTransactionLoopStandardCompensate
						- nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensate - nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensate;
				
				// [EVENT SUBPROCESS COLLAPSED]
				// SubProcess event Collapsed
				for(int i=0;i<nodesSubprocessesEvent.getLength();i++) {
					String SubprocessesID = (((Element) nodesSubprocessesEvent.item(i)).getAttribute("id"));

					Node SubPExtendedEventNodeChild = nodesSubprocessesEvent.item(i);  

					for(int j=0;j<nodesShapesList.getLength();j++) {
						String SubprocessesShape = (((Element) nodesShapesList.item(j)).getAttribute("bpmnElement"));

						NodeList SubprocessesEventNodeChildNodes = SubPExtendedEventNodeChild.getChildNodes();  
						
						
						if(SubprocessesID.equalsIgnoreCase(SubprocessesShape) &&
								((Element) nodesShapesList.item(j)).getAttribute("isExpanded").contains("true")==false) {
							
						if(SubprocessesEventNodeChildNodes.getLength()<=0)	{
							
							if(((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false &&
									((Element) nodesSubprocessesEvent.item(i)).getAttribute("triggeredByEvent").contains("true"))
							{
									nSubProcessCollapsedEventLoopNoneCompensateNone++;

							}

							if(((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true") &&
									((Element) nodesSubprocessesEvent.item(i)).getAttribute("triggeredByEvent").contains("true") ) 
							{								
									nSubProcessCollapsedEventLoopNoneCompensate++;	
							}
						}else {
						
						for(int z=0;z<SubprocessesEventNodeChildNodes.getLength(); z++)
						{
							try {

									if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
											SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
											((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false &&
											((Element) nodesSubprocessesEvent.item(i)).getAttribute("triggeredByEvent").contains("true"))
									{
										if(z==SubprocessesEventNodeChildNodes.getLength()-1) 
											nSubProcessCollapsedEventLoopNoneCompensateNone++;

									}

									if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
											SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
											((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true") &&
											((Element) nodesSubprocessesEvent.item(i)).getAttribute("triggeredByEvent").contains("true") ) 
									{
										if(z==SubprocessesEventNodeChildNodes.getLength()-1) 
											nSubProcessCollapsedEventLoopNoneCompensate++;	
									}


									if(SubprocessesEventNodeChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE) {

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics") &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false &&
														((Element) nodesSubprocessesEvent.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessCollapsedEventLoopStandardCompensateNone++;

										}

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics") &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true") &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessCollapsedEventLoopStandardCompensate++;

										}

										//mi par
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false &&
														((Element) nodesSubprocessesEvent.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessCollapsedEventLoopMIParallelCompensateNone++;


										}

										//mi par comp
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")&&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("triggeredByEvent").contains("true") ) {
											nSubProcessCollapsedEventLoopMIParallelCompensate++;

										}

										//mi seq
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true") &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true")==false&&
														((Element) nodesSubprocessesEvent.item(i)).getAttribute("triggeredByEvent").contains("true") ) {
											nSubProcessCollapsedEventLoopMISequentialCompensateNone++;


										}
										//mi seq comp
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true") &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("isForCompensation").contains("true") &&
												((Element) nodesSubprocessesEvent.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessCollapsedEventLoopMISequentialCompensate++;

										}



									}    						
}
						catch (Exception e) {}
						}
						}
					}
					}

				}	
				nSubProcessCollapsedEventLoopNoneCompensateNone = nSubProcessCollapsedEventLoopNoneCompensateNone - nSubProcessCollapsedEventLoopStandardCompensateNone 
						- nSubProcessCollapsedEventLoopMIParallelCompensateNone - nSubProcessCollapsedEventLoopMISequentialCompensateNone;

				nSubProcessCollapsedEventLoopNoneCompensate = nSubProcessCollapsedEventLoopNoneCompensate - nSubProcessCollapsedEventLoopStandardCompensate
						- nSubProcessCollapsedEventLoopMIParallelCompensate - nSubProcessCollapsedEventLoopMISequentialCompensate;

				// [EVENT SUBPROCESS COLLAPSED + ADHOC]
				/*
SUBPROCESS Collapsed EVENT + ADHOC
				 */
				for(int i=0;i<nodesSubprocessesAdHoc.getLength();i++) {
					String SubprocessesID = (((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("id"));

					Node SubPExtendedEventNodeChild = nodesSubprocessesAdHoc.item(i);  

					for(int j=0;j<nodesShapesList.getLength();j++) {
						String SubprocessesShape = (((Element) nodesShapesList.item(j)).getAttribute("bpmnElement"));

						NodeList SubprocessesEventNodeChildNodes = SubPExtendedEventNodeChild.getChildNodes();  
						
						if(SubprocessesID.equalsIgnoreCase(SubprocessesShape) &&
								((Element) nodesShapesList.item(j)).getAttribute("isExpanded").contains("true")==false) {
							
						if(SubprocessesEventNodeChildNodes.getLength()<=0) {
							
							if(((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false&&
									((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true"))
							{
									nSubProcessCollapsedEventAdHocLoopNoneCompensateNone++;														
							}

							if(((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true") &&
									((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")) 
							{
									nSubProcessCollapsedEventAdHocLoopNoneCompensate++;														
							}
						}
						else {
						
						for(int z=0;z<SubprocessesEventNodeChildNodes.getLength(); z++)
						{
							try {
									if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
											SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
											((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false&&
											((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true"))
									{
										if(z==SubprocessesEventNodeChildNodes.getLength()-1) 
											nSubProcessCollapsedEventAdHocLoopNoneCompensateNone++;														
									}

									if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")==false &&
											SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics")==false &&
											((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true") &&
											((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")) 
									{
										if(z==SubprocessesEventNodeChildNodes.getLength()-1) 
											nSubProcessCollapsedEventAdHocLoopNoneCompensate++;														
									}

									if(SubprocessesEventNodeChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE) {

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics")&&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessCollapsedEventAdHocLoopStandardCompensateNone++;

										}

										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("standardLoopCharacteristics") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")&&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessCollapsedEventAdHocLoopStandardCompensate++;

										}

										//mi par
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone++;


										}

										//mi par comp
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessCollapsedEventAdHocLoopMIParallelCompensate++;

										}

										//mi seq
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true")&&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")==false &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone++;


										}
										//mi seq comp
										if(SubprocessesEventNodeChildNodes.item(z).getNodeName().contains("multiInstanceLoopCharacteristics") &&
												((Element) SubprocessesEventNodeChildNodes.item(z)).getAttribute("isSequential").contains("true") &&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("isForCompensation").contains("true")&&
												((Element) nodesSubprocessesAdHoc.item(i)).getAttribute("triggeredByEvent").contains("true")) {
											nSubProcessCollapsedEventAdHocLoopMISequentialCompensate++;

										}




									}    						

								}catch (Exception e) {}
						}
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
				/*nExtendedSubProcess= nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone+
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
				
				*/
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

				//[TODO DATA OBJECTS]
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
				XPathExpression exprDI = xpath.compile("//bpmn:ioSpecification//bpmn:dataInput");
				Object resultDI = exprDI.evaluate(doc, XPathConstants.NODESET);
				NodeList nodesDI = (NodeList) resultDI;
				doc.getDocumentElement().normalize();  
				nDataInput = nodesDI.getLength()/2;

				// N° of Data Output
				XPathExpression exprDOut = xpath.compile("//bpmn:ioSpecification//bpmn:dataOutput");
				Object resultDOut = exprDOut.evaluate(doc, XPathConstants.NODESET);
				NodeList nodesDOut = (NodeList) resultDOut;
				doc.getDocumentElement().normalize();  
				nDataOutput = nodesDOut.getLength()/2;


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
				
				XPathExpression exprExG = xpath.compile("//bpmn:exclusiveGateway");
				Object resultExG = exprExG.evaluate(doc, XPathConstants.NODESET);
				NodeList nodesExclusiveGateway = (NodeList) resultExG;
				NodeList nodesExG = (NodeList) resultExG;
				
				XPathExpression exprExclusiveGatewayShape = xpathShape.compile("//bpmn:BPMNShape");
				Object resultExclusiveGatewayShape  = exprExclusiveGatewayShape.evaluate(doc, XPathConstants.NODESET);       
				NodeList nodesExclusiveGatewayShape = (NodeList) resultExclusiveGatewayShape;
				doc.getDocumentElement().normalize();               

				for(int i=0;i<nodesExclusiveGateway.getLength();i++) {
					String GatewayID = (((Element) nodesExclusiveGateway.item(i)).getAttribute("id"));

					for(int j=0;j<nodesExclusiveGatewayShape.getLength();j++) {
						String GatewayShapeID = (((Element) nodesExclusiveGatewayShape.item(j)).getAttribute("bpmnElement"));

						if(GatewayID.equalsIgnoreCase(GatewayShapeID)) {
							
							if(((Element) nodesExclusiveGatewayShape.item(j)).getAttribute("isMarkerVisible").contains("true"))
									nExclusiveGatewayMarker++;
							else
								    nExclusiveGatewayNoMarker++;
						}
					}
				}

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
				
				//TO TEST Event Based Gateway Parallel Instantiation
				XPathExpression exprEBGPI = xpath.compile("//bpmn:eventBasedGateway[@eventGatewayType='Parallel' and @instantiate='true']");
				Object resultEBGPI = exprEBGPI.evaluate(doc, XPathConstants.NODESET);
				NodeList nodesEBGPI = (NodeList) resultEBGPI;
				doc.getDocumentElement().normalize();  
				nEventBasedGatewayParallelInstantiation = nodesEBGPI.getLength();				
				
				XPathExpression exprEBGEI = xpath.compile("//bpmn:eventBasedGateway[@instantiate='true']");
				Object resultEBGEI = exprEBGEI.evaluate(doc, XPathConstants.NODESET);
				NodeList nodesEBGEI = (NodeList) resultEBGEI;
				doc.getDocumentElement().normalize();  
				nEventBasedGatewayExclusiveInstantiation = nodesEBGEI.getLength() - nEventBasedGatewayParallelInstantiation;

				XPathExpression exprEBG = xpath.compile("//bpmn:eventBasedGateway");
				Object resultEBG = exprEBG.evaluate(doc, XPathConstants.NODESET);
				NodeList nodesEBG = (NodeList) resultEBG;
				doc.getDocumentElement().normalize();  
				nEventBasedGateway = nodesEBG.getLength() - (nEventBasedGatewayParallelInstantiation+nEventBasedGatewayExclusiveInstantiation);				
				
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
										((Element) nodesBoundaryCatchIntEvent.item(i)).getAttribute("cancelActivity").contains("false")==false
										&& NumberOfChildsOfEachBoundaryCatchEvent<=1) {
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
				nChoreographyParticipantMultiple = nodesChoPartM.getLength();
				//N° of Choreography participant
				XPathExpression exprChoPart = xpath.compile("//bpmn:choreography//bpmn:participant");
				Object resultChoPart = exprChoPart.evaluate(doc, XPathConstants.NODESET);
				NodeList nodesChoPart = (NodeList) resultChoPart;
				doc.getDocumentElement().normalize();  
				
				Triplet<Integer, String, String> idArrayParticipant  = new Triplet<Integer, String, String>(Integer.valueOf(1),"","");				
				
				
				for(int c = 0;c<nodesChoPart.getLength();c++) {
					
					try {
						String idParticipant = (((Element) nodesChoPart.item(c)).getAttribute("name"));
						
						idArrayParticipant.add(idParticipant,"");
						
						if(idArrayParticipant.getValue1().equalsIgnoreCase(idParticipant))
						{
							continue;
						}
						else {
							nChoreographyParticipant++; 
						}
					}catch (Exception e) {		
					}
				}
				
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
				XPathExpression exprPoolColM = xpath.compile("//bpmn:collaboration//bpmn:participant[not(@processRef)]//bpmn:participantMultiplicity");
				Object resultPoolColM = exprPoolColM.evaluate(doc, XPathConstants.NODESET);
				NodeList nodesPoolColM = (NodeList) resultPoolColM;
				doc.getDocumentElement().normalize();  
				nPoolCollapsedMultiplicity = nodesPoolColM.getLength();

				// Pool Collapsed Multiplicity None
				XPathExpression exprPoolCol = xpath.compile("//bpmn:collaboration//bpmn:participant[not(@processRef)]");
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
				XPathExpression exprUndirectedAssoc = xpath.compile("//bpmn:association[@associationDirection='None']");
				Object resultUndirectedAssoc = exprUndirectedAssoc.evaluate(doc, XPathConstants.NODESET);
				NodeList nodesUndirectedAssoc = (NodeList) resultUndirectedAssoc;
				doc.getDocumentElement().normalize();  
				nAssociationUndirected = nodesUndirectedAssoc.getLength();

				//N° of Sequence Flow
				XPathExpression exprSFlow = xpath.compile("//bpmn:sequenceFlow");
				Object resultSFlow = exprSFlow.evaluate(doc, XPathConstants.NODESET);
				NodeList nodesSFlow = (NodeList) resultSFlow;
				doc.getDocumentElement().normalize();  
				nSequenceFlow = nodesSFlow.getLength() - (nDefaultFlow + nConditionalFlow);
				
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
				
				XPathFactory xPathfactory = XPathFactory.newInstance();
				XPath xpathLang = xPathfactory.newXPath();
				XPathExpression expr = xpathLang.compile("//*[@name]");
				Object resultModelWords = expr.evaluate(doc, XPathConstants.NODESET);
				NodeList nodesModelWords = (NodeList) resultModelWords;										
				ArrayList<String> modelWords = new ArrayList<String>();   
				Vector<Double> modelWordsLenght = new Vector<Double>();  					
							
				
				if((nConversationNone+nConversationSubProcess+nConversationCall+nConversationLink)>0) 
				modelType = "Conversation";
				
				else if((nChoreographyTask+nChoreographyTaskSequentialMultipleInstance+
						nChoreographyTaskParallelMultipleInstance+nChoreographyTaskLoop+
						nChoreographySubprocessCollapsed+nChoreographySubprocessCollapsedParallelMultipleInstance+
						nChoreographySubprocessCollapsedSequentialMultipleInstance+
						nChoreographySubprocessCollapsedLoop+
						nChoreographySubprocessCollapsedCall+
						nChoreographySubprocessCollapsedCallSequentialMultipleInstance+
						nChoreographySubprocessCollapsedCallParallelMultipleInstance+
						nChoreographySubprocessCollapsedCallLoop+
						nChoreographySubprocessExpanded+
						nChoreographySubprocessExpandedSequentialMultipleInstance+
						nChoreographySubprocessExpandedParallelMultipleInstance+
						nChoreographySubprocessExpandedLoop)>0)
				modelType = "Choreography";
				
				else if(( ( (nPoolCollapsedMultiplicityNone+nPoolCollapsedMultiplicity+nPoolExpandedMultiplicityNone+nPoolExpandedMultiplicity) >1) || 
							 ( (nPoolCollapsedMultiplicityNone+nPoolCollapsedMultiplicity+nPoolExpandedMultiplicityNone+nPoolExpandedMultiplicity)==1 && nMessageFlow>0))) {
	
							modelType = "Collaboration";
							//If i find the collaboration xml tag, i cant skip the for
							
						}
				else 
					modelType = "Process";  
				
				
				// USEFULL OPERATIONS
				//[TODO: CHOREOGRAPHY]
				if(modelType=="Choreography")
					nMessageFlow = 0;

				if(modelType=="Process")
					nChoreographyMessage=0;
				
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
						nSubProcessCollapsedEventAdHocLoopNoneCompensateNone+
						nSubProcessCollapsedEventAdHocLoopNoneCompensate+
						nSubProcessCollapsedEventAdHocLoopStandardCompensateNone+
						nSubProcessCollapsedEventAdHocLoopStandardCompensate+
						nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone+
						nSubProcessCollapsedEventAdHocLoopMIParallelCompensate+
						nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone+
						nSubProcessCollapsedEventAdHocLoopMISequentialCompensate+
						nDataObject+
						nDataObjectCollection+
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
						nConversationLink+
						nAssociationUndirected+
						nAssociationUnidirectional+        
						nAssociationBidirectional+
						nAssociationDataOutput+
						nAssociationDataInput+            
						nGroup+
						nTextAnnotation;
				
				DuplicateString = "" + nTaskNoneLoopNoneCompensateNoneCallNone+
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
						nSubProcessCollapsedEventAdHocLoopNoneCompensateNone+
						nSubProcessCollapsedEventAdHocLoopNoneCompensate+
						nSubProcessCollapsedEventAdHocLoopStandardCompensateNone+
						nSubProcessCollapsedEventAdHocLoopStandardCompensate+
						nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone+
						nSubProcessCollapsedEventAdHocLoopMIParallelCompensate+
						nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone+
						nSubProcessCollapsedEventAdHocLoopMISequentialCompensate+
						nDataObject+
						nDataObjectCollection+
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
						nIntermediateBoundaryConditionalEvent +
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
						nChoreographyParticipant+
						nChoreographyParticipantMultiple+   
						nChoreographyMessage+
						nConversationNone+
						nConversationSubProcess+
						nConversationCall+
						nConversationLink+
						nAssociationUndirected+
						nAssociationUnidirectional+        
						nAssociationBidirectional+
						nAssociationDataOutput+
						nAssociationDataInput+
						nGroup+
						nTextAnnotation;
				
				int pComplexity = 0;
				
				if(nTaskNoneLoopNoneCompensateNoneCallNone>0)pComplexity++;
				if(nTaskNoneLoopNoneCompensateNoneCall>0)pComplexity++;
				if(nTaskNoneLoopNoneCompensateCallNone>0)pComplexity++;
				if(nTaskNoneLoopNoneCompensateCall>0)pComplexity++;
				if(nTaskNoneLoopStandardCompensateNoneCallNone>0)pComplexity++;
				if(nTaskNoneLoopStandardCompensateNoneCall>0)pComplexity++;
				if(nTaskNoneLoopStandardCompensateCallNone>0)pComplexity++;
				if(nTaskNoneLoopStandardCompensateCall>0)pComplexity++;
				if(nTaskNoneLoopMIParallelCompensateNoneCallNone>0)pComplexity++;
				if(nTaskNoneLoopMIParallelCompensateNoneCall>0)pComplexity++;
				if(nTaskNoneLoopMIParallelCompensateCallNone>0)pComplexity++;
				if(nTaskNoneLoopMIParallelCompensateCall>0)pComplexity++;
				if(nTaskNoneLoopMISequentialCompensateNoneCallNone>0)pComplexity++;
				if(nTaskNoneLoopMISequentialCompensateNoneCall>0)pComplexity++;
				if(nTaskNoneLoopMISequentialCompensateCallNone>0)pComplexity++;
				if(nTaskNoneLoopMISequentialCompensateCall>0)pComplexity++;
				if(nTaskSendLoopNoneCompensateNone>0)pComplexity++;
				if(nTaskSendLoopNoneCompensate>0)pComplexity++;
				if(nTaskSendLoopStandardCompensateNone>0)pComplexity++;
				if(nTaskSendLoopStandardCompensate>0)pComplexity++;
				if(nTaskSendLoopMIParallelCompensateNone>0)pComplexity++;
				if(nTaskSendLoopMIParallelCompensate>0)pComplexity++;
				if(nTaskSendLoopMISequentialCompensateNone>0)pComplexity++;
				if(nTaskSendLoopMISequentialCompensate>0)pComplexity++;
				if(nTaskReceiveLoopNoneCompensateNone>0)pComplexity++;
				if(nTaskReceiveLoopNoneCompensate>0)pComplexity++;
				if(nTaskReceiveLoopStandardCompensateNone>0)pComplexity++;
				if(nTaskReceiveLoopStandardCompensate>0)pComplexity++;
				if(nTaskReceiveLoopMIParallelCompensateNone>0)pComplexity++;
				if(nTaskReceiveLoopMIParallelCompensate>0)pComplexity++;
				if(nTaskReceiveLoopMISequentialCompensateNone>0)pComplexity++;
				if(nTaskReceiveLoopMISequentialCompensate>0)pComplexity++;
				if(nTaskUserLoopNoneCompensateNone>0)pComplexity++;
				if(nTaskUserLoopNoneCompensate>0)pComplexity++;
				if(nTaskUserLoopStandardCompensateNone>0)pComplexity++;
				if(nTaskUserLoopStandardCompensate>0)pComplexity++;
				if(nTaskUserLoopMIParallelCompensateNone>0)pComplexity++;
				if(nTaskUserLoopMIParallelCompensate>0)pComplexity++;
				if(nTaskUserLoopMISequentialCompensateNone>0)pComplexity++;
				if(nTaskUserLoopMISequentialCompensate>0)pComplexity++;
				if(nTaskManualLoopNoneCompensateNone>0)pComplexity++;
				if(nTaskManualLoopNoneCompensate>0)pComplexity++;
				if(nTaskManualLoopStandardCompensateNone>0)pComplexity++;
				if(nTaskManualLoopStandardCompensate>0)pComplexity++;
				if(nTaskManualLoopMIParallelCompensateNone>0)pComplexity++;
				if(nTaskManualLoopMIParallelCompensate>0)pComplexity++;
				if(nTaskManualLoopMISequentialCompensateNone>0)pComplexity++;
				if(nTaskManualLoopMISequentialCompensate>0)pComplexity++;
				if(nTaskBusinessRuleLoopNoneCompensateNone>0)pComplexity++;
				if(nTaskBusinessRuleLoopNoneCompensate>0)pComplexity++;
				if(nTaskBusinessRuleLoopStandardCompensateNone>0)pComplexity++;
				if(nTaskBusinessRuleLoopStandardCompensate>0)pComplexity++;
				if(nTaskBusinessRuleLoopMIParallelCompensateNone>0)pComplexity++;
				if(nTaskBusinessRuleLoopMIParallelCompensate>0)pComplexity++;
				if(nTaskBusinessRuleLoopMISequentialCompensateNone>0)pComplexity++;
				if(nTaskBusinessRuleLoopMISequentialCompensate>0)pComplexity++;
				if(nTaskServiceLoopNoneCompensateNone>0)pComplexity++;
				if(nTaskServiceLoopNoneCompensate>0)pComplexity++;
				if(nTaskServiceLoopStandardCompensateNone>0)pComplexity++;
				if(nTaskServiceLoopStandardCompensate>0)pComplexity++;
				if(nTaskServiceLoopMIParallelCompensateNone>0)pComplexity++;
				if(nTaskServiceLoopMIParallelCompensate>0)pComplexity++;
				if(nTaskServiceLoopMISequentialCompensateNone>0)pComplexity++;
				if(nTaskServiceLoopMISequentialCompensate>0)pComplexity++;
				if(nTaskScriptLoopNoneCompensateNone>0)pComplexity++;
				if(nTaskScriptLoopNoneCompensate>0)pComplexity++;
				if(nTaskScriptLoopStandardCompensateNone>0)pComplexity++;
				if(nTaskScriptLoopStandardCompensate>0)pComplexity++;
				if(nTaskScriptLoopMIParallelCompensateNone>0)pComplexity++;
				if(nTaskScriptLoopMIParallelCompensate>0)pComplexity++;
				if(nTaskScriptLoopMISequentialCompensateNone>0)pComplexity++;
				if(nTaskScriptLoopMISequentialCompensate>0)pComplexity++;
				if(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone>0)pComplexity++;
				if(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate>0)pComplexity++;
				if(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone>0)pComplexity++;
				if(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate>0)pComplexity++;
				if(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone>0)pComplexity++;
				if(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate>0)pComplexity++;
				if(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone>0)pComplexity++;
				if(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate>0)pComplexity++;
				if(nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone>0)pComplexity++;
				if(nSubProcessExtendedEventNoneAdHocLoopNoneCompensate>0)pComplexity++;
				if(nSubProcessExtendedEventNoneAdHocLoopStandardCompensateNone>0)pComplexity++;
				if(nSubProcessExtendedEventNoneAdHocLoopStandardCompensate>0)pComplexity++;
				if(nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone>0)pComplexity++;
				if(nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate>0)pComplexity++;
				if(nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone>0)pComplexity++;
				if(nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate>0)pComplexity++;
				if(nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone>0)pComplexity++;
				if(nSubProcessExtendedEventNoneTransactionLoopNoneCompensate>0)pComplexity++;
				if(nSubProcessExtendedEventNoneTransactionLoopStandardCompensateNone>0)pComplexity++;
				if(nSubProcessExtendedEventNoneTransactionLoopStandardCompensate>0)pComplexity++;
				if(nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone>0)pComplexity++;
				if(nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate>0)pComplexity++;
				if(nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone>0)pComplexity++;
				if(nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate>0)pComplexity++;
				if(nSubProcessExtendedEventLoopNoneCompensateNone>0)pComplexity++;
				if(nSubProcessExtendedEventLoopNoneCompensate>0)pComplexity++;
				if(nSubProcessExtendedEventLoopStandardCompensateNone>0)pComplexity++;
				if(nSubProcessExtendedEventLoopStandardCompensate>0)pComplexity++;
				if(nSubProcessExtendedEventLoopMIParallelCompensateNone>0)pComplexity++;
				if(nSubProcessExtendedEventLoopMIParallelCompensate>0)pComplexity++;
				if(nSubProcessExtendedEventLoopMISequentialCompensateNone>0)pComplexity++;
				if(nSubProcessExtendedEventLoopMISequentialCompensate>0)pComplexity++;
				if(nSubProcessExtendedEventAdHocLoopNoneCompensateNone>0)pComplexity++;
				if(nSubProcessExtendedEventAdHocLoopNoneCompensate>0)pComplexity++;
				if(nSubProcessExtendedEventAdHocLoopStandardCompensateNone>0)pComplexity++;
				if(nSubProcessExtendedEventAdHocLoopStandardCompensate>0)pComplexity++;
				if(nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone>0)pComplexity++;
				if(nSubProcessExtendedEventAdHocLoopMIParallelCompensate>0)pComplexity++;
				if(nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone>0)pComplexity++;
				if(nSubProcessExtendedEventAdHocLoopMISequentialCompensate>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneAdHocLoopStandardCompensateNone>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneAdHocLoopStandardCompensate>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneTransactionLoopStandardCompensateNone>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneTransactionLoopStandardCompensate>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensateNone>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensate>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensateNone>0)pComplexity++;
				if(nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensate>0)pComplexity++;
				if(nSubProcessCollapsedEventLoopNoneCompensateNone>0)pComplexity++;
				if(nSubProcessCollapsedEventLoopNoneCompensate>0)pComplexity++;
				if(nSubProcessCollapsedEventLoopStandardCompensateNone>0)pComplexity++;
				if(nSubProcessCollapsedEventLoopStandardCompensate>0)pComplexity++;
				if(nSubProcessCollapsedEventLoopMIParallelCompensateNone>0)pComplexity++;
				if(nSubProcessCollapsedEventLoopMIParallelCompensate>0)pComplexity++;
				if(nSubProcessCollapsedEventLoopMISequentialCompensateNone>0)pComplexity++;
				if(nSubProcessCollapsedEventLoopMISequentialCompensate>0)pComplexity++;
				if(nSubProcessCollapsedEventAdHocLoopNoneCompensateNone>0)pComplexity++;
				if(nSubProcessCollapsedEventAdHocLoopNoneCompensate>0)pComplexity++;
				if(nSubProcessCollapsedEventAdHocLoopStandardCompensateNone>0)pComplexity++;
				if(nSubProcessCollapsedEventAdHocLoopStandardCompensate>0)pComplexity++;
				if(nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone>0)pComplexity++;
				if(nSubProcessCollapsedEventAdHocLoopMIParallelCompensate>0)pComplexity++;
				if(nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone>0)pComplexity++;
				if(nSubProcessCollapsedEventAdHocLoopMISequentialCompensate>0)pComplexity++;
				if(nDataObject>0)pComplexity++;
				if(nDataObjectCollection>0)pComplexity++;
				if(nDataStore>0)pComplexity++;
				if(nDataInput>0)pComplexity++;
				if(nDataOutput>0)pComplexity++;
				if(nExclusiveGatewayNoMarker>0)pComplexity++;
				if(nExclusiveGatewayMarker>0)pComplexity++;
				if(nParallelGateway>0)pComplexity++;
				if(nInclusiveGateway>0)pComplexity++;
				if(nEventBasedGateway>0)pComplexity++;
				if(nEventBasedGatewayExclusiveInstantiation>0)pComplexity++;
				if(nEventBasedGatewayParallelInstantiation>0)pComplexity++;
				if(nComplexGateway>0)pComplexity++;
				if(nStartMultipleParallelEventDefinition>0)pComplexity++;
				if(nStartMultipleEventDefinition>0)pComplexity++;
				if(nStartNoneEventDefinition>0)pComplexity++;
				if(nStartSignalEventDefinition>0)pComplexity++;
				if(nStartConditionalEventDefinition>0)pComplexity++;
				if(nStartTimerEventDefinition>0)pComplexity++;
				if(nStartMessageEventDefinition>0)pComplexity++;			
				if(nStartMessageEventSubProcessInterruptingDefinition>0)pComplexity++;
				if(nStartTimerEventSubProcessInterruptingDefinition>0)pComplexity++;
				if(nStartEscalationEventSubProcessInterruptingDefinition>0)pComplexity++;
				if(nStartConditionalEventSubProcessInterruptingDefinition>0)pComplexity++;
				if(nStartErrorEventSubProcessInterruptingDefinition>0)pComplexity++;
				if(nStartCompensateEventSubProcessInterruptingDefinition>0)pComplexity++;
				if(nStartSignalEventSubProcessInterruptingDefinition>0)pComplexity++;
				if(nStartMultipleEventSubProcessInterruptingDefinition>0)pComplexity++;
				if(nStartMultipleParallelEventSubProcessInterruptingDefinition>0)pComplexity++;       
				if(nStartMessageEventSubProcessNonInterruptingDefinition>0)pComplexity++;
				if(nStartTimerEventSubProcessNonInterruptingDefinition>0)pComplexity++;
				if(nStartEscalationEventSubProcessNonInterruptingDefinition>0)pComplexity++;
				if(nStartConditionalEventSubProcessNonInterruptingDefinition>0)pComplexity++;
				if(nStartSignalEventSubProcessNonInterruptingDefinition>0)pComplexity++;
				if(nStartMultipleParallelEventSubProcessNonInterruptingDefinition>0)pComplexity++;
				if(nStartMultipleEventSubProcessNonInterruptingDefinition>0)pComplexity++;       
				if(nEndNoneEventDefinition>0)pComplexity++;
				if(nEndMultipleEventDefinition>0)pComplexity++; 
				if(nEndEscalationEventDefinition>0)pComplexity++;
				if(nEndErrorEventDefinition>0)pComplexity++;
				if(nEndSignalEventDefinition>0)pComplexity++;
				if(nEndCompensateEventDefinition>0)pComplexity++;
				if(nEndCancelEventDefinition>0)pComplexity++; 
				if(nEndMessageEventDefinition>0)pComplexity++;
				if(nEndTerminateEventDefinition>0)pComplexity++;
				if(nIntermediateCatchMultipleEventDefinition>0)pComplexity++;
				if(nIntermediateCatchMultipleParallelEventDefinition>0)pComplexity++;
				if(nIntermediateCatchMessageEventDefinition>0)pComplexity++;
				if(nIntermediateCatchTimerEventDefinition>0)pComplexity++;
				if(nIntermediateCatchConditionalEventDefinition>0)pComplexity++;
				if(nIntermediateCatchLinkEventDefinition>0)pComplexity++;
				if(nIntermediateCatchSignalEventDefinition>0)pComplexity++;
				if(nIntermediateThrowNoneEventDefinition>0)pComplexity++;
				if(nIntermediateThrowMessageEventDefinition>0)pComplexity++;
				if(nIntermediateThrowEscalationEventDefinition>0)pComplexity++;
				if(nIntermediateThrowLinkEventDefinition>0)pComplexity++;
				if(nIntermediateThrowSignalEventDefinition>0)pComplexity++;
				if(nIntermediateThrowCompensateEventDefinition>0)pComplexity++;
				if(nIntermediateThrowMultipleEventDefinition>0)pComplexity++;
				if(nIntermediateBoundaryMessageEvent>0)pComplexity++;
				if(nIntermediateBoundaryTimerEvent>0)pComplexity++;
				if(nIntermediateBoundaryCancelEvent>0)pComplexity++;
				if(nIntermediateBoundaryConditionalEvent >0)pComplexity++;
				if(nIntermediateBoundaryEscalationEvent>0)pComplexity++;
				if(nIntermediateBoundaryErrorEvent>0)pComplexity++;
				if(nIntermediateBoundarySignalEvent>0)pComplexity++;
				if(nIntermediateBoundaryCompensateEvent>0)pComplexity++;
				if(nIntermediateBoundaryMultipleEvent>0)pComplexity++;
				if(nIntermediateBoundaryMultipleParallelEvent>0)pComplexity++;
				if(nIntermediateBoundaryTimerEventNonInterrupting>0)pComplexity++;
				if(nIntermediateBoundaryEscalationEventNonInterrupting>0)pComplexity++;
				if(nIntermediateBoundaryConditionalEventNonInterrupting>0)pComplexity++;
				if(nIntermediateBoundaryMessageEventNonInterrupting>0)pComplexity++;
				if(nIntermediateBoundarySignalEventNonInterrupting>0)pComplexity++;
				if(nIntermediateBoundaryMultipleEventNonInterrupting>0)pComplexity++;
				if(nIntermediateBoundaryMultipleParallelEventNonInterrupting>0)pComplexity++;
				if(nMessageFlow>0)pComplexity++;
				if(nSequenceFlow>0)pComplexity++;
				if(nDefaultFlow>0)pComplexity++;
				if(nConditionalFlow>0)pComplexity++;
				if(nLane>0)pComplexity++;
				if(nPoolCollapsedMultiplicityNone>0)pComplexity++;
				if(nPoolCollapsedMultiplicity>0)pComplexity++;
				if(nPoolExpandedMultiplicityNone>0)pComplexity++;
				if(nPoolExpandedMultiplicity>0)pComplexity++;       
				if(nChoreographyTask>0)pComplexity++;
				if(nChoreographyTaskSequentialMultipleInstance>0)pComplexity++;
				if(nChoreographyTaskParallelMultipleInstance>0)pComplexity++;
				if(nChoreographyTaskLoop>0)pComplexity++;
				if(nChoreographySubprocessCollapsed>0)pComplexity++;
				if(nChoreographySubprocessCollapsedParallelMultipleInstance>0)pComplexity++;
				if(nChoreographySubprocessCollapsedSequentialMultipleInstance>0)pComplexity++;
				if(nChoreographySubprocessCollapsedLoop>0)pComplexity++;
				if(nChoreographySubprocessCollapsedCall>0)pComplexity++;
				if(nChoreographySubprocessCollapsedCallSequentialMultipleInstance>0)pComplexity++;
				if(nChoreographySubprocessCollapsedCallParallelMultipleInstance>0)pComplexity++;
				if(nChoreographySubprocessCollapsedCallLoop>0)pComplexity++;
				if(nChoreographySubprocessExpanded>0)pComplexity++;
				if(nChoreographySubprocessExpandedSequentialMultipleInstance>0)pComplexity++;
				if(nChoreographySubprocessExpandedParallelMultipleInstance>0)pComplexity++;
				if(nChoreographySubprocessExpandedLoop>0)pComplexity++;
				//if(nChoreographyParticipant>0)pComplexity++;
				//if(nChoreographyParticipantMultiple>0)pComplexity++;   
				if(nChoreographyMessage>0)pComplexity++;
				if(nConversationNone>0)pComplexity++;
				if(nConversationSubProcess>0)pComplexity++;
				if(nConversationCall>0)pComplexity++;
				if(nConversationLink>0)pComplexity++;
				if(nAssociationUndirected>0)pComplexity++;
				if(nAssociationUnidirectional>0)pComplexity++;        
				if(nAssociationBidirectional>0)pComplexity++;
				if(nAssociationDataOutput>0)pComplexity++;
				if(nAssociationDataInput>0)pComplexity++;
				if(nGroup>0)pComplexity++;
				if(nTextAnnotation>0)pComplexity++;


				long EndingtimeMillis = System.currentTimeMillis();
				
				double ExecutionTime = (EndingtimeMillis - StartingtimeMillis);
				
				//Duplicate finding
				
				Triplet<Integer, String, String> ModelNameAndString  = new Triplet<Integer, String, String>(Integer.valueOf(1),fileName,DuplicateString+FileSize+modelWords.toString());				
				ModelNameAndString.add(fileName,DuplicateString+FileSize+modelWords.toString());
				
				
				int Nofwords = 0;
				int Nofcharater = 0;
				double average;
				double median;
				double mode;
				double variance;
				
				for(int a=0; a<nodesModelWords.getLength(); a++) {
					
					
					if (TotalElements==0)
					{
						Nofwords = 0;
						Nofcharater = 0;
						average = 0;
						median = 0;
						mode = 0;
						variance = 0;	
						modelWords.add("NoLabels"); 
						break;
					}
					
					if(nodesModelWords.item(a).toString().contains("omgdc:Font") ||
						      nodesModelWords.item(a).toString().contains("semantic:definitions:") ||
							  nodesModelWords.item(a).toString().contains("semantic:globalUserTask") ||
							  nodesModelWords.item(a).toString().contains("dc:Font") ||
							  nodesModelWords.item(a).toString().contains("bpmn2:definitions") ||
							  nodesModelWords.item(a).toString().contains("bpmndi:BPMNDiagram") ||
							  nodesModelWords.item(a).toString().contains("ixbpmn:customDataValue") ||
							  nodesModelWords.item(a).toString().contains("signal:")	||
							  nodesModelWords.item(a).toString().contains("error:")		)
						continue;
				
					NamedNodeMap s = nodesModelWords.item(a).getAttributes();
					Node name = s.getNamedItem("name");	
					
					if(!name.getTextContent().isEmpty() && !name.getTextContent().equals(" ") &&  !name.getTextContent().equals(null)) {
						modelWords.add(name.getTextContent());
						modelWordsLenght.add((double) name.getTextContent().length());								

					}
					
				}
				
				
				//Collections.sort(modelWords, String.CASE_INSENSITIVE_ORDER);
				String modelStampSeparated = String.join("^^^", modelWords);
				String modelStamp = modelWords.toString();
							
				modelStamp = modelStamp.replaceAll(" &#13;&#10;", "");	
				modelStamp = modelStamp.replaceAll("&#10;", "");	
				modelStamp = modelStamp.replaceAll(";&#10;", "");	
				modelStamp = modelStamp.replaceAll("&#13;", "");	
				modelStamp = modelStamp.replaceAll(";&#13;", "");
				modelStamp = modelStamp.replaceAll("xA","");
				modelStamp = modelStamp.replaceAll("-","");
				modelStamp = modelStamp.replace("\n", "").replace("\r", "");
				modelStamp = modelStamp.replaceAll(" ","");
				modelStamp = modelStamp.replaceAll(",","");	
				modelStamp = modelStamp.replace("[","");
				modelStamp = modelStamp.replace("]","");
				modelStamp = modelStamp.replace(";","");
				modelStamp = modelStamp.replace("&amp;","");				
				
				modelStampSeparated = modelStampSeparated.replaceAll(" &#13;&#10;", "");	
				modelStampSeparated = modelStampSeparated.replaceAll("&#10;", "");	
				modelStampSeparated = modelStampSeparated.replaceAll(";&#10;", "");	
				modelStampSeparated = modelStampSeparated.replaceAll("&#13;", "");	
				modelStampSeparated = modelStampSeparated.replaceAll(";&#13;", "");
				modelStampSeparated = modelStampSeparated.replaceAll("xA","");
				modelStampSeparated = modelStampSeparated.replaceAll("-","");
				modelStampSeparated = modelStampSeparated.replace("\n", "").replace("\r", "");
				modelStampSeparated = modelStampSeparated.replaceAll(" ","");
				modelStampSeparated = modelStampSeparated.replace(";","");
				modelStampSeparated = modelStampSeparated.replace("&amp;","");
			
				if(nodesModelWords.getLength()>0) {
				
					Nofcharater= modelStamp.length();	
					Nofwords = modelWords.size();
			  
			 average = Nofcharater/Nofwords;				 
			 Collections.sort(modelWordsLenght);
			 			 
			 int middle = modelWordsLenght.size()/2;		 		 		 		
			 
			    if (Nofwords%2 == 1) {
			    	median = modelWordsLenght.elementAt(middle);
			    	
			    } else {
			    	median = (modelWordsLenght.elementAt(middle) + (modelWordsLenght.elementAt(middle-1)) )/2;			    	
			    }
			    
			    HashMap<Double,Integer> hm = new HashMap<Double,Integer>();
			    int max  = 1;
			    Double temp = 0.0;

			    for(int i = 0; i < modelWordsLenght.size(); i++) {

			        if (hm.get(modelWordsLenght.elementAt(i)) != null) {

			            int count = hm.get(modelWordsLenght.elementAt(i));
			            count++;
			            hm.put(modelWordsLenght.elementAt(i), count);

			            if(count > max) {
			                max  = count;
			                temp = modelWordsLenght.elementAt(i);
			            }
			        }

			        else 
			            hm.put(modelWordsLenght.elementAt(i),1);
			    }
			    		
					
			mode = temp;

		        double tempVar = 0;	        
		        for(int i = 0; i < modelWordsLenght.size(); i++) {
		        	tempVar += Math.pow(modelWordsLenght.elementAt(i)-average, 2);
		        	}	    
		        
			variance = tempVar/modelWordsLenght.size();
				
			}
			else
			{
				Nofwords = 0;
				Nofcharater = 0;
				average = 0;
				median = 0;
				mode = 0;
				variance = 0;	
				modelWords.add("NoLabels"); 
			}
			
			
				//inserting data        
				bw.write(fileName+";");
				bw.write(bpmnModeler+";");
				bw.write(modelType+";");
				bw.write(isEnglish+";");
				bw.write(nTaskNoneLoopNoneCompensateNoneCallNone+";");
				bw.write(nTaskNoneLoopNoneCompensateNoneCall+";");
				bw.write(nTaskNoneLoopNoneCompensateCallNone+";");
				bw.write(nTaskNoneLoopNoneCompensateCall+";");
				bw.write(nTaskNoneLoopStandardCompensateNoneCallNone+";");
				bw.write(nTaskNoneLoopStandardCompensateNoneCall+";");
				bw.write(nTaskNoneLoopStandardCompensateCallNone+";");
				bw.write(nTaskNoneLoopStandardCompensateCall+";");
				bw.write(nTaskNoneLoopMIParallelCompensateNoneCallNone+";");
				bw.write(nTaskNoneLoopMIParallelCompensateNoneCall+";");
				bw.write(nTaskNoneLoopMIParallelCompensateCallNone+";");
				bw.write(nTaskNoneLoopMIParallelCompensateCall+";");
				bw.write(nTaskNoneLoopMISequentialCompensateNoneCallNone+";");
				bw.write(nTaskNoneLoopMISequentialCompensateNoneCall+";");
				bw.write(nTaskNoneLoopMISequentialCompensateCallNone+";");
				bw.write(nTaskNoneLoopMISequentialCompensateCall+";");
				bw.write(nTaskSendLoopNoneCompensateNone+";");
				bw.write(nTaskSendLoopNoneCompensate+";");
				bw.write(nTaskSendLoopStandardCompensateNone+";"+nTaskSendLoopStandardCompensate+";"); 
				bw.write(nTaskSendLoopMIParallelCompensateNone+";"); 
				bw.write(nTaskSendLoopMIParallelCompensate+";");
				bw.write(nTaskSendLoopMISequentialCompensateNone+";"); 
				bw.write(nTaskSendLoopMISequentialCompensate+";");
				bw.write(nTaskReceiveLoopNoneCompensateNone+";");            
				bw.write(nTaskReceiveLoopNoneCompensate+";"+nTaskReceiveLoopStandardCompensateNone+";");            
				bw.write(nTaskReceiveLoopStandardCompensate+";");            
				bw.write(nTaskReceiveLoopMIParallelCompensateNone+";");                        
				bw.write(nTaskReceiveLoopMIParallelCompensate+";");            
				bw.write(nTaskReceiveLoopMISequentialCompensateNone+";"+nTaskReceiveLoopMISequentialCompensate+";");            
				bw.write(nTaskUserLoopNoneCompensateNone+";");            
				bw.write(nTaskUserLoopNoneCompensate+";"+nTaskUserLoopStandardCompensateNone+";");            
				bw.write(nTaskUserLoopStandardCompensate+";"+nTaskUserLoopMIParallelCompensateNone+";");            
				bw.write(nTaskUserLoopMIParallelCompensate+";");            
				bw.write(nTaskUserLoopMISequentialCompensateNone+";");            
				bw.write(nTaskUserLoopMISequentialCompensate+";");            
				bw.write(nTaskManualLoopNoneCompensateNone+";");            
				bw.write(nTaskManualLoopNoneCompensate+";");            
				bw.write(nTaskManualLoopStandardCompensateNone+";");            
				bw.write(nTaskManualLoopStandardCompensate+";");            
				bw.write(nTaskManualLoopMIParallelCompensateNone+";");            
				bw.write(nTaskManualLoopMIParallelCompensate+";");            
				bw.write(nTaskManualLoopMISequentialCompensateNone+";");            
				bw.write(nTaskManualLoopMISequentialCompensate+";");            
				bw.write(nTaskBusinessRuleLoopNoneCompensateNone+";");            
				bw.write(nTaskBusinessRuleLoopNoneCompensate+";");            
				bw.write(nTaskBusinessRuleLoopStandardCompensateNone+";");            
				bw.write(nTaskBusinessRuleLoopStandardCompensate+";");            
				bw.write(nTaskBusinessRuleLoopMIParallelCompensateNone+";"+nTaskBusinessRuleLoopMIParallelCompensate+";");            
				bw.write(nTaskBusinessRuleLoopMISequentialCompensateNone+";"+nTaskBusinessRuleLoopMISequentialCompensate+";");            
				bw.write(nTaskServiceLoopNoneCompensateNone+";");            
				bw.write(nTaskServiceLoopNoneCompensate+";");            
				bw.write(nTaskServiceLoopStandardCompensateNone+";");            
				bw.write(nTaskServiceLoopStandardCompensate+";");            
				bw.write(nTaskServiceLoopMIParallelCompensateNone+";");            
				bw.write(nTaskServiceLoopMIParallelCompensate+";");            
				bw.write(nTaskServiceLoopMISequentialCompensateNone+";");            
				bw.write(nTaskServiceLoopMISequentialCompensate+";");            
				bw.write(nTaskScriptLoopNoneCompensateNone+";");            
				bw.write(nTaskScriptLoopNoneCompensate+";"+nTaskScriptLoopStandardCompensateNone+";");            
				bw.write(nTaskScriptLoopStandardCompensate+";");            
				bw.write(nTaskScriptLoopMIParallelCompensateNone+";");            
				bw.write(nTaskScriptLoopMIParallelCompensate+";");            
				bw.write(nTaskScriptLoopMISequentialCompensateNone+";");            
				bw.write(nTaskScriptLoopMISequentialCompensate+";");            
				bw.write(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone+";");
				bw.write(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate+";");
				bw.write(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone+";");
				bw.write(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate+";");
				bw.write(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone+";");
				bw.write(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate+";");
				bw.write(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone+";");
				bw.write(nSubProcessExtendedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate+";");
				bw.write(nSubProcessExtendedEventNoneAdHocLoopNoneCompensateNone+";");
				bw.write(nSubProcessExtendedEventNoneAdHocLoopNoneCompensate+";");
				bw.write(nSubProcessExtendedEventNoneAdHocLoopStandardCompensateNone+";");
				bw.write(nSubProcessExtendedEventNoneAdHocLoopStandardCompensate+";");
				bw.write(nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensateNone+";");
				bw.write(nSubProcessExtendedEventNoneAdHocLoopMIParallelCompensate+";");
				bw.write(nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensateNone+";");
				bw.write(nSubProcessExtendedEventNoneAdHocLoopMISequentialCompensate+";");
				bw.write(nSubProcessExtendedEventNoneTransactionLoopNoneCompensateNone+";");
				bw.write(nSubProcessExtendedEventNoneTransactionLoopNoneCompensate+";");
				bw.write(nSubProcessExtendedEventNoneTransactionLoopStandardCompensateNone+";");
				bw.write(nSubProcessExtendedEventNoneTransactionLoopStandardCompensate+";");
				bw.write(nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensateNone+";");
				bw.write(nSubProcessExtendedEventNoneTransactionLoopMIParallelCompensate+";");
				bw.write(nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensateNone+";");
				bw.write(nSubProcessExtendedEventNoneTransactionLoopMISequentialCompensate+";");
				bw.write(nSubProcessExtendedEventLoopNoneCompensateNone+";");
				bw.write(nSubProcessExtendedEventLoopNoneCompensate+";");
				bw.write(nSubProcessExtendedEventLoopStandardCompensateNone+";");
				bw.write(nSubProcessExtendedEventLoopStandardCompensate+";");
				bw.write(nSubProcessExtendedEventLoopMIParallelCompensateNone+";");
				bw.write(nSubProcessExtendedEventLoopMIParallelCompensate+";");
				bw.write(nSubProcessExtendedEventLoopMISequentialCompensateNone+";");
				bw.write(nSubProcessExtendedEventLoopMISequentialCompensate+";");
				bw.write(nSubProcessExtendedEventAdHocLoopNoneCompensateNone+";");
				bw.write(nSubProcessExtendedEventAdHocLoopNoneCompensate+";");
				bw.write(nSubProcessExtendedEventAdHocLoopStandardCompensateNone+";");
				bw.write(nSubProcessExtendedEventAdHocLoopStandardCompensate+";");
				bw.write(nSubProcessExtendedEventAdHocLoopMIParallelCompensateNone+";");
				bw.write(nSubProcessExtendedEventAdHocLoopMIParallelCompensate+";");
				bw.write(nSubProcessExtendedEventAdHocLoopMISequentialCompensateNone+";");
				bw.write(nSubProcessExtendedEventAdHocLoopMISequentialCompensate+";");
				bw.write(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensateNone+";");
				bw.write(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopNoneCompensate+";");
				bw.write(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensateNone+";");
				bw.write(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopStandardCompensate+";");
				bw.write(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensateNone+";");
				bw.write(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMIParallelCompensate+";");
				bw.write(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensateNone+";");
				bw.write(nSubProcessCollapsedEventNoneAdHocNoneTransactionNoneLoopMISequentialCompensate+";");
				bw.write(nSubProcessCollapsedEventNoneAdHocLoopNoneCompensateNone+";");
				bw.write(nSubProcessCollapsedEventNoneAdHocLoopNoneCompensate+";");
				bw.write(nSubProcessCollapsedEventNoneAdHocLoopStandardCompensateNone+";");
				bw.write(nSubProcessCollapsedEventNoneAdHocLoopStandardCompensate+";");
				bw.write(nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensateNone+";");
				bw.write(nSubProcessCollapsedEventNoneAdHocLoopMIParallelCompensate+";");
				bw.write(nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensateNone+";");
				bw.write(nSubProcessCollapsedEventNoneAdHocLoopMISequentialCompensate+";");
				bw.write(nSubProcessCollapsedEventNoneTransactionLoopNoneCompensateNone+";");
				bw.write(nSubProcessCollapsedEventNoneTransactionLoopNoneCompensate+";");
				bw.write(nSubProcessCollapsedEventNoneTransactionLoopStandardCompensateNone+";");
				bw.write(nSubProcessCollapsedEventNoneTransactionLoopStandardCompensate+";");
				bw.write(nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensateNone+";");
				bw.write(nSubProcessCollapsedEventNoneTransactionLoopMIParallelCompensate+";");
				bw.write(nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensateNone+";");
				bw.write(nSubProcessCollapsedEventNoneTransactionLoopMISequentialCompensate+";");
				bw.write(nSubProcessCollapsedEventLoopNoneCompensateNone+";");
				bw.write(nSubProcessCollapsedEventLoopNoneCompensate+";");
				bw.write(nSubProcessCollapsedEventLoopStandardCompensateNone+";");
				bw.write(nSubProcessCollapsedEventLoopStandardCompensate+";");
				bw.write(nSubProcessCollapsedEventLoopMIParallelCompensateNone+";");
				bw.write(nSubProcessCollapsedEventLoopMIParallelCompensate+";");
				bw.write(nSubProcessCollapsedEventLoopMISequentialCompensateNone+";");
				bw.write(nSubProcessCollapsedEventLoopMISequentialCompensate+";");
				bw.write(nSubProcessCollapsedEventAdHocLoopNoneCompensateNone+";");
				bw.write(nSubProcessCollapsedEventAdHocLoopNoneCompensate+";");
				bw.write(nSubProcessCollapsedEventAdHocLoopStandardCompensateNone+";");
				bw.write(nSubProcessCollapsedEventAdHocLoopStandardCompensate+";");
				bw.write(nSubProcessCollapsedEventAdHocLoopMIParallelCompensateNone+";");
				bw.write(nSubProcessCollapsedEventAdHocLoopMIParallelCompensate+";");
				bw.write(nSubProcessCollapsedEventAdHocLoopMISequentialCompensateNone+";");
				bw.write(nSubProcessCollapsedEventAdHocLoopMISequentialCompensate+";");
				bw.write(nDataObject+";");
				bw.write(nDataObjectCollection+";");
				bw.write(nDataStore+";");
				bw.write(nDataInput+";");
				bw.write(nDataOutput+";");
				bw.write(nExclusiveGatewayNoMarker+";");
				bw.write(nExclusiveGatewayMarker+";");
				bw.write(nParallelGateway+";");
				bw.write(nInclusiveGateway+";");
				bw.write(nEventBasedGateway+";");
				bw.write(nEventBasedGatewayExclusiveInstantiation+";");
				bw.write(nEventBasedGatewayParallelInstantiation+";");
				bw.write(nComplexGateway+";");
				bw.write(nStartMultipleParallelEventDefinition+";");
				bw.write(nStartMultipleEventDefinition+";");
				bw.write(nStartNoneEventDefinition+";");
				bw.write(nStartSignalEventDefinition+";");
				bw.write(nStartConditionalEventDefinition+";");
				bw.write(nStartTimerEventDefinition+";");
				bw.write(nStartMessageEventDefinition+";");
				bw.write(nStartMessageEventSubProcessInterruptingDefinition+";");
				bw.write(nStartTimerEventSubProcessInterruptingDefinition+";");
				bw.write(nStartEscalationEventSubProcessInterruptingDefinition+";");
				bw.write(nStartConditionalEventSubProcessInterruptingDefinition+";");
				bw.write(nStartErrorEventSubProcessInterruptingDefinition+";");
				bw.write(nStartCompensateEventSubProcessInterruptingDefinition+";");
				bw.write(nStartSignalEventSubProcessInterruptingDefinition+";");
				bw.write(nStartMultipleEventSubProcessInterruptingDefinition+";");
				bw.write(nStartMultipleParallelEventSubProcessInterruptingDefinition+";");       
				bw.write(nStartMessageEventSubProcessNonInterruptingDefinition+";");
				bw.write(nStartTimerEventSubProcessNonInterruptingDefinition+";");
				bw.write(nStartEscalationEventSubProcessNonInterruptingDefinition+";");
				bw.write(nStartConditionalEventSubProcessNonInterruptingDefinition+";");
				bw.write(nStartSignalEventSubProcessNonInterruptingDefinition+";");
				bw.write(nStartMultipleParallelEventSubProcessNonInterruptingDefinition+";");
				bw.write(nStartMultipleEventSubProcessNonInterruptingDefinition+";");       
				bw.write(nEndNoneEventDefinition+";");
				bw.write(nEndMultipleEventDefinition+";"); 
				bw.write(nEndEscalationEventDefinition+";");
				bw.write(nEndErrorEventDefinition+";");
				bw.write(nEndSignalEventDefinition+";");
				bw.write(nEndCompensateEventDefinition+";");
				bw.write(nEndCancelEventDefinition+";"); 
				bw.write(nEndMessageEventDefinition+";");
				bw.write(nEndTerminateEventDefinition+";");
				bw.write(nIntermediateCatchMultipleEventDefinition+";");
				bw.write(nIntermediateCatchMultipleParallelEventDefinition+";");
				bw.write(nIntermediateCatchMessageEventDefinition+";");
				bw.write(nIntermediateCatchTimerEventDefinition+";");
				bw.write(nIntermediateCatchConditionalEventDefinition+";");
				bw.write(nIntermediateCatchLinkEventDefinition+";");
				bw.write(nIntermediateCatchSignalEventDefinition+";");
				bw.write(nIntermediateThrowNoneEventDefinition+";");
				bw.write(nIntermediateThrowMessageEventDefinition+";");
				bw.write(nIntermediateThrowEscalationEventDefinition+";");
				bw.write(nIntermediateThrowLinkEventDefinition+";");
				bw.write(nIntermediateThrowSignalEventDefinition+";");
				bw.write(nIntermediateThrowCompensateEventDefinition+";");
				bw.write(nIntermediateThrowMultipleEventDefinition+";");
				bw.write(nIntermediateBoundaryMessageEvent+";");
				bw.write(nIntermediateBoundaryTimerEvent+";");
				bw.write(nIntermediateBoundaryCancelEvent+";");
				bw.write(nIntermediateBoundaryConditionalEvent+";");
				bw.write(nIntermediateBoundaryEscalationEvent+";");
				bw.write(nIntermediateBoundaryErrorEvent+";");
				bw.write(nIntermediateBoundarySignalEvent+";");
				bw.write(nIntermediateBoundaryCompensateEvent+";");
				bw.write(nIntermediateBoundaryMultipleEvent+";");
				bw.write(nIntermediateBoundaryMultipleParallelEvent+";");
				bw.write(nIntermediateBoundaryTimerEventNonInterrupting+";");
				bw.write(nIntermediateBoundaryEscalationEventNonInterrupting+";");
				bw.write(nIntermediateBoundaryConditionalEventNonInterrupting+";");
				bw.write(nIntermediateBoundaryMessageEventNonInterrupting+";");
				bw.write(nIntermediateBoundarySignalEventNonInterrupting+";");
				bw.write(nIntermediateBoundaryMultipleEventNonInterrupting+";");
				bw.write(nIntermediateBoundaryMultipleParallelEventNonInterrupting+";");
				bw.write(nMessageFlow+";");
				bw.write(nSequenceFlow+";");
				bw.write(nDefaultFlow+";");
				bw.write(nConditionalFlow+";");
				bw.write(nLane+";"); 
				bw.write(nPoolCollapsedMultiplicityNone+";");
				bw.write(nPoolCollapsedMultiplicity+";");
				bw.write(nPoolExpandedMultiplicityNone+";");
				bw.write(nPoolExpandedMultiplicity+";");
				bw.write(nChoreographyTask+";");
				bw.write(nChoreographyMessage+";");            
				bw.write(nChoreographyTaskSequentialMultipleInstance+";");
				bw.write(nChoreographyTaskParallelMultipleInstance+";");
				bw.write(nChoreographyTaskLoop+";");
				bw.write(nChoreographySubprocessCollapsed+";");
				bw.write(nChoreographySubprocessCollapsedParallelMultipleInstance+";");
				bw.write(nChoreographySubprocessCollapsedSequentialMultipleInstance+";");
				bw.write(nChoreographySubprocessCollapsedLoop+";");
				bw.write(nChoreographySubprocessCollapsedCall+";");
				bw.write(nChoreographySubprocessCollapsedCallSequentialMultipleInstance+";");
				bw.write(nChoreographySubprocessCollapsedCallParallelMultipleInstance+";");
				bw.write(nChoreographySubprocessCollapsedCallLoop+";");
				bw.write(nChoreographySubprocessExpanded+";");
				bw.write(nChoreographySubprocessExpandedSequentialMultipleInstance+";");
				bw.write(nChoreographySubprocessExpandedParallelMultipleInstance+";");
				bw.write(nChoreographySubprocessExpandedLoop+";");
				bw.write(nChoreographyParticipant+";");
				bw.write(nChoreographyParticipantMultiple+";");       
				bw.write(nConversationNone+";");
				bw.write(nConversationSubProcess+";");
				bw.write(nConversationCall+";");
				bw.write(nConversationLink+";");
				bw.write(nAssociationUndirected+";");
				bw.write(nAssociationUnidirectional+";");        
				bw.write(nAssociationBidirectional+";");
				bw.write(nAssociationDataOutput+";");
				bw.write(nAssociationDataInput+";");            
				bw.write(nGroup+";");
				bw.write(nTextAnnotation+";");
				bw.write(ExecutionTime+";");      
				bw.write(TotalElements+";");	
				bw.write(pComplexity+";");	
				bw.write(FileSize+";");
				bw.write(DuplicateString+FileSize+modelStamp.toString()+";");	
				bw.write(modelStampSeparated.toString()+";");
				bw.write(modelStamp.toString()+";");
				bw.write(Nofwords+";");		
				bw.write(Nofcharater+";");
				bw.write(average+";");
				bw.write(median+";");
				bw.write(mode+"\n");
				
				} catch (Exception e) {
					
					 System.out.println("Exception: "+e.getMessage());
								
				}
		
			}
			
			bw.flush();		
			bw.close();			
			
		} catch (Exception e) {
			 System.out.println("Exception: "+e.getMessage());
			 return;
		}		
		
		System.out.println("\n=========== :: Analysis succesfully DONE. The .txt file is ready :: ===========");
	}	

}
