# Installazione python:
# Mi funziona con Python 3.8.6

# Installazione dipendenze: 
# pip3 install pandas  
# pip3 install xlrd
# pip install xlsxwriter

# Per le operazioni dell'OS
import os
import lxml.etree
import glob
import csv
import sys
"""
Input:
    - Lista di file BPMN all'interno della cartella bpmn_files
    - File csv nominato "BPMN-metrics-output"

Output:
    - file xsl con i valori delle metriche dei file bpmn esamianti
"""
#Il primo argomento che passiamo è il percorso alla cartella contenente i modelli
#Il secondo argomento è il nome del file csv da creare che verrà creato nella stessa cartella dove risiede lo script
# a quel argomento si accede con sys.argv[1];
#Si lancia lo script con il comando $python3 main.py path/to/bpmn/models/folder Metriche.csv

# Setting the file name
numberOfInvalid = 0;
nEndMultipleEventDefinition = 0.0;

with open(sys.argv[2],'w') as file:
    writer = csv.writer(file)
    writer.writerow(["BPMN_File_Name","BPMN_Modeler","nTask","nSendTask","nUserTask","nManualTask","nBusinessRuleTask","nServiceTask","nScriptTask","nCallActivity","nSubProcess","nTransaction","nAdHocSubProcess","nGroup","nCollaboration","nLaneSet","nLane","nDataObject","nDataObjectReference","nDataStore","nDataStoreReference","nDataInput","nDataOutput","nExclusiveGateway","nParallelGateway","nInclusiveGateway","nEventBasedGateway","nComplexGateway","nCondition","nIntermediateThrowEvent","nStartMultipleParallelEventDefinition","nStartMultipleEventDefinition","nStartNoneEvent","nStartSignalEventDefinition","nStartConditionalEventDefinition","nStartTimerEventDefinition","nStartMessageEventDefinition","nStartCompensateEventDefinition","nStartCancelEventDefinition","nStartEscalationEventDefinition","nStartErrorEventDefinition","nEndEventNone","nEndTerminateEventDefinition","nEndEscalationEventDefinition","nEndMessageEventDefinition","nEndErrorEventDefinition","nEndCompensateEventDefinition","nEndCancelEventDefinition","nEndSignalEventDefinition","nEndMultipleEventDefinition","nIntermediateCatchEvent","nIntermediateCatchMultipleEventDefinition","nIntermediateCatchMultipleParallelEventDefinition","nIntermediateCatchMessageEventDefinition","nIntermediateCatchTimerEventDefinition","nIntermediateCatchConditionalEventDefinition","nIntermediateCatchLinkEventDefinition","nIntermediateSignalMessageEventDefinition","nIntermediateThrowMessageEventDefinition","nIntermediateThrowEscalationEventDefinition","nIntermediateThrowLinkEventDefinition","nIntermediateThrowSignalEventDefinition","nIntermediateThrowCompensateEventDefinition","nBoundaryEventDefinition","nBoundaryMessageEvent","nBoundaryTimerEvent","nBoundaryCancelEvent","nBoundaryConditionalEvent","nBoundaryEscalationEvent","nBoundaryErrorEvent","nBoundarySignalEvent","nBoundaryCompensateEvent","nBoundaryTimerEventNonInt","nBoundaryEscalationEventNonInt","nBoundaryConditionalEventNonInt","nBoundaryMessageEventNonInt","ngroup","nMessageFlow","nSequenceFlow","nDefaultFlow","nPool","nVerticalLane","nVerticalPool","nChoreographyTask","nChoreographyParticipant","nChoreographySubprocess","nITSystem","nCompensateAssociation","nUnidirectionalAssociation","nUndirectedAssociation","nBidirectionalAssociation","nTextAnnotation"])

   
for files in os.listdir(sys.argv[1]):
    namespace = "bpmn:";

    #print("Model Name "+files);
    if(".bpmn" in files):
        
        try:
            doc = lxml.etree.parse(sys.argv[1]+'/'+files);
            str = open(sys.argv[1]+'/'+files,'r', encoding="utf8").read()
        except:
            ++numberOfInvalid
            print(files)
            print(numberOfInvalid)
            continue
            
        
        # Discover the modeler type
        if str.find('camunda') != -1:
            bpmnModeler = "Camunda"

        elif str.find('signavio') != -1:
            bpmnModeler = "Signavio"
            
        else: 
            bpmnModeler = "Undefined"
            
        # Setting the file name
        fileName = files
        
        # Calcolo metriche dei file
        #######################################################
        # Task
        nTask=  doc.xpath('count(//bpmn:task )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nSendTask=  doc.xpath('count(//bpmn:sendTask )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nReceiveTask=  doc.xpath('count(//bpmn:receiveTask )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nUserTask=  doc.xpath('count(//bpmn:userTask )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nManualTask=  doc.xpath('count(//bpmn:manualTask )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nBusinessRuleTask=  doc.xpath('count(//bpmn:businessRuleTask )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nServiceTask=  doc.xpath('count(//bpmn:serviceTask )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nScriptTask=  doc.xpath('count(//bpmn:scriptTask )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nCallActivity=  doc.xpath('count(//bpmn:callActivity )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nSubProcess=  doc.xpath('count(//bpmn:subProcess )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nEventSubProcess=  doc.xpath('count(//bpmn:subProcess[@triggeredByEvent="true"] )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nTransaction=  doc.xpath('count(//bpmn:transaction )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nAdHocSubProcess=  doc.xpath('count(//bpmn:adHocSubProcess )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        #######################################################
        # Sequence Flow
        nSequenceFlow=  doc.xpath('count(//bpmn:sequenceFlow )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        # Message Flow
        nMessageFlow=  doc.xpath('count(//bpmn:messageFlow )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        # Default Flow
        nDefaultFlow= doc.xpath('count(//bpmn:exclusiveGateway[@default])', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        #######################################################
        # Group
        nGroup=  doc.xpath('count(//bpmn:group )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        #######################################################
        # Pool/Participant
        nCollaboration=  doc.xpath('count(//bpmn:collaboration )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nPool= doc.xpath('count(//bpmn:participant)', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nLaneSet=  doc.xpath('count(//bpmn:laneSet )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nLane=  doc.xpath('count(//bpmn:lane )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nVerticalPool= doc.xpath('count(//bpmn:collaboration[@isHorizontal="false"])', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nVerticalLane= doc.xpath('count(//bpmn:lane[@isHorizontal="false"])', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        
        #######################################################
        # Data Object/Store
        nDataObject=  doc.xpath('count(//bpmn:dataObject )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nDataObjectReference=  doc.xpath('count(//bpmn:dataObjectReference )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nDataStore=  doc.xpath('count(//bpmn:dataStore )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nDataStoreReference=  doc.xpath('count(//bpmn:dataStoreReference )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nDataInput=  doc.xpath('count(//bpmn:dataInput )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nDataOutput=  doc.xpath('count(//bpmn:dataOutput )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        #######################################################
        # Gateway
        nExclusiveGateway=  doc.xpath('count(//bpmn:exclusiveGateway )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nParallelGateway=  doc.xpath('count(//bpmn:parallelGateway )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nInclusiveGateway=  doc.xpath('count(//bpmn:inclusiveGateway )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nEventBasedGateway=  doc.xpath('count(//bpmn:eventBasedGateway )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nComplexGateway= doc.xpath('count(//bpmn:complexGateway )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        #######################################################
        # Event - condition
        nCondition=  doc.xpath('count(//bpmn:condition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        #######################################################
        # Event - Start
        nIntermediateThrowEvent=  doc.xpath('count(//bpmn:intermediateThrowEvent )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nStartNoneEvent= doc.xpath('count(//bpmn:startEvent )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nStartMultipleParallelEventDefinition=  doc.xpath('count(//bpmn:startEvent[@isInterrupting="true" and @parallelMultiple="true"])', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        }) 
        nStartMultipleEventDefinition=  (doc.xpath('count(//bpmn:startEvent[@isInterrupting="true"])', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        }) - nStartMultipleParallelEventDefinition)
        nStartSignalEventDefinition=  doc.xpath('count(//bpmn:startEvent//bpmn:signalEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nStartConditionalEventDefinition=  doc.xpath('count(//bpmn:startEvent//bpmn:conditionalEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nStartTimerEventDefinition=  doc.xpath('count(//bpmn:startEvent//bpmn:timerEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nStartMessageEventDefinition=  doc.xpath('count(//bpmn:startEvent//bpmn:messageEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nStartCompensateEventDefinition=  doc.xpath('count(//bpmn:startEvent//bpmn:compensateEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nStartCancelEventDefinition=  (doc.xpath('count(//bpmn:startEvent//bpmn:cancelEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        }) - nStartMultipleParallelEventDefinition - nStartMultipleEventDefinition)
        nStartEscalationEventDefinition=  doc.xpath('count(//bpmn:startEvent//bpmn:escalationEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nStartErrorEventDefinition=  doc.xpath('count(//bpmn:startEvent//bpmn:errorEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nStartNoneEvent=nStartNoneEvent-nStartSignalEventDefinition-nStartConditionalEventDefinition-nStartTimerEventDefinition-nStartMessageEventDefinition-nStartCompensateEventDefinition-nStartCancelEventDefinition-nStartEscalationEventDefinition-nStartErrorEventDefinition-nStartMultipleParallelEventDefinition-nStartMultipleEventDefinition
        #######################################################
        # Event - End
        nEndEventNone = doc.xpath('count(//bpmn:endEvent)', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        
        x= doc.xpath('count(//bpmn:endEvent/* )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        
        if(x==4):
            nEndMultipleEventDefinition += 1.0;
        
        nEndEscalationEventDefinition=  doc.xpath('count(//bpmn:endEvent//bpmn:escalationEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nEndErrorEventDefinition=  doc.xpath('count(//bpmn:endEvent//bpmn:errorEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nEndSignalEventDefinition=  doc.xpath('count(//bpmn:endEvent//bpmn:signalEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nEndCompensateEventDefinition=  doc.xpath('count(//bpmn:endEvent//bpmn:compensateEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nEndCancelEventDefinition=  (doc.xpath('count(//bpmn:endEvent//bpmn:cancelEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        }) - nEndMultipleEventDefinition) 
        nEndMessageEventDefinition=  (doc.xpath('count(//bpmn:endEvent//bpmn:messageEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        }) - nEndMultipleEventDefinition) 
        nEndTerminateEventDefinition=  (doc.xpath('count(//bpmn:endEvent//bpmn:terminateEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        }) - nEndMultipleEventDefinition) 
        nEndEventNone=nEndEventNone-nEndTerminateEventDefinition-nEndEscalationEventDefinition-nEndMessageEventDefinition-nEndErrorEventDefinition-nEndCompensateEventDefinition-nEndCancelEventDefinition-nEndSignalEventDefinition-nEndMultipleEventDefinition
        #######################################################
        # Event - Intermediate Catch       
        nIntermediateCatchMultipleEventDefinition=  doc.xpath('count(//bpmn:intermediateCatchEvent[@parallelMultiple="false"])', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nIntermediateCatchMultipleParallelEventDefinition=  doc.xpath('count(//bpmn:intermediateCatchEvent[@parallelMultiple="true"])', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nIntermediateCatchEvent=  (doc.xpath('count(//bpmn:intermediateCatchEvent )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        }) - nIntermediateCatchMultipleEventDefinition - nIntermediateCatchMultipleParallelEventDefinition)
        nIntermediateCatchMessageEventDefinition=  doc.xpath('count(//bpmn:intermediateCatchEvent//bpmn:messageEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nIntermediateCatchTimerEventDefinition=  doc.xpath('count(//bpmn:intermediateCatchEvent//bpmn:timerEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nIntermediateCatchConditionalEventDefinition=  doc.xpath('count(//bpmn:intermediateCatchEvent//bpmn:conditionalEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nIntermediateCatchLinkEventDefinition=  doc.xpath('count(//bpmn:intermediateCatchEvent//bpmn:linkEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nIntermediateSignalMessageEventDefinition=  doc.xpath('count(//bpmn:intermediateCatchEvent//bpmn:signaleEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        #######################################################
        # Event - Intermediate Throw
        nIntermediateThrowMessageEventDefinition=  doc.xpath('count(//bpmn:intermediateThrowEvent//bpmn:messageEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nIntermediateThrowEscalationEventDefinition=  doc.xpath('count(//bpmn:intermediateThrowEvent//bpmn:escalationEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nIntermediateThrowLinkEventDefinition=  doc.xpath('count(//bpmn:intermediateThrowEvent//bpmn:linkEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nIntermediateThrowSignalEventDefinition=  doc.xpath('count(//bpmn:intermediateThrowEvent//bpmn:signalEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nIntermediateThrowCompensateEventDefinition=  doc.xpath('count(//bpmn:intermediateThrowEvent//bpmn:compensateEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nIntermediateThrowMultipleParallelEventDefinition=  doc.xpath('count(//bpmn:intermediateThrowEvent//bpmn:cancelEventDefinition/bpmn:terminateEventDefinition )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        #######################################################
        # Event - Boundary Non-Interrupting 
        nBoundaryTimerEventNonInt=  doc.xpath('count(//bpmn:boundaryEvent[@cancelActivity="false"]//bpmn:timerEventDefinition)', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nBoundaryEscalationEventNonInt=  doc.xpath('count(//bpmn:boundaryEvent[@cancelActivity="false"]//bpmn:escalationEventDefinition)', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nBoundaryConditionalEventNonInt=  doc.xpath('count(//bpmn:boundaryEvent[@cancelActivity="false"]//bpmn:conditionalEventDefinition)', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nBoundarySignalEventNonInt=  doc.xpath('count(//bpmn:boundaryEvent[@cancelActivity="false"]//bpmn:signalEventDefinition)', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nBoundaryMessageEventNonInt=  doc.xpath('count(//bpmn:boundaryEvent[@cancelActivity="false"]//bpmn:timerMessageDefinition)', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        ######################################################
        nBoundaryMessageEvent=  (doc.xpath('count(//bpmn:boundaryEvent//bpmn:timerMessageDefinition)', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        }) - nBoundaryMessageEventNonInt)
        nBoundaryTimerEvent=  (doc.xpath('count(//bpmn:boundaryEvent//bpmn:timerEventDefinition)', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        }) - nBoundaryTimerEventNonInt)  
        nBoundaryConditionalEvent= (doc.xpath('count(//bpmn:boundaryEvent//bpmn:conditionalEventDefinition)', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        }) - nBoundaryConditionalEventNonInt)
        nBoundaryEscalationEvent=  (doc.xpath('count(//bpmn:boundaryEvent//bpmn:escalationEventDefinition)', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        }) - nBoundaryEscalationEventNonInt)
        nBoundarySignalEvent= (doc.xpath('count(//bpmn:boundaryEvent//bpmn:signalEventDefinition)', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        }) - nBoundarySignalEventNonInt)
        nBoundaryEventDefinition=  doc.xpath('count(//bpmn:boundaryEvent)', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nBoundaryCancelEvent=  doc.xpath('count(//bpmn:boundaryEvent//bpmn:cancelEventDefinition)', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nBoundaryErrorEvent=  doc.xpath('count(//bpmn:boundaryEvent//bpmn:errorEventDefinition)', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nBoundaryCompensateEvent=  doc.xpath('count(//bpmn:boundaryEvent//bpmn:compensateEventDefinition)', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        
        #######################################################
        # Choreography
        nChoreographyParticipant= doc.xpath('count(//bpmn:choreography//bpmn:participant )', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nChoreographyTask= doc.xpath('count(//bpmn:choreography)', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nChoreographySubprocess= doc.xpath('count(//bpmn:subChoreography)', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        
        #######################################################
        # ITSystem
        nITSystem= doc.xpath('count(//bpmn:textAnnotation//bpmn:extensionElements)', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        #######################################################
        # TextAnnotation
        nTextAnnotation= doc.xpath('count(//bpmn:textAnnotation)', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        #######################################################
        # Association
        nCompensateAssociation= doc.xpath('count(//bpmn:association)', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nUnidirectionalAssociation= doc.xpath('count(//bpmn:association[@associationDirection="One"])', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nUndirectedAssociation= doc.xpath('count(//bpmn:association[@associationDirection="None"])', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        nBidirectionalAssociation = doc.xpath('count(//bpmn:association[@associationDirection="Both"])', namespaces={
        'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
        })
        
        #######################################################
        # Statistical paramethers
        
        # Ora che ho creato il file, leggo il contenuto del file xml
        # dataframe da inserire nella riga del file excel
        with open(sys.argv[2],'a', newline='') as file:
            writer = csv.writer(file)
            writer.writerow([fileName,bpmnModeler,nTask,nSendTask,nUserTask,nManualTask,nBusinessRuleTask,nServiceTask,nScriptTask,nCallActivity
                        ,nSubProcess,nTransaction,nAdHocSubProcess,nGroup
                        ,nCollaboration,nLaneSet,nLane,nDataObject,nDataObjectReference,nDataStore
                        ,nDataStoreReference,nDataInput,nDataOutput
                        ,nExclusiveGateway,nParallelGateway,nInclusiveGateway,nEventBasedGateway,nComplexGateway,nCondition
                        ,nIntermediateThrowEvent,nStartMultipleParallelEventDefinition,nStartMultipleEventDefinition,nStartNoneEvent,nStartSignalEventDefinition,nStartConditionalEventDefinition
                        ,nStartTimerEventDefinition,nStartMessageEventDefinition,nStartCompensateEventDefinition,nStartCancelEventDefinition
                        ,nStartEscalationEventDefinition,nStartErrorEventDefinition,nEndEventNone,nEndTerminateEventDefinition,nEndEscalationEventDefinition
                        ,nEndMessageEventDefinition,nEndErrorEventDefinition,nEndCompensateEventDefinition
                        ,nEndCancelEventDefinition,nEndSignalEventDefinition,nEndMultipleEventDefinition,nIntermediateCatchEvent,nIntermediateCatchMultipleEventDefinition,nIntermediateCatchMultipleParallelEventDefinition,nIntermediateCatchMessageEventDefinition
                        ,nIntermediateCatchTimerEventDefinition,nIntermediateCatchConditionalEventDefinition,nIntermediateCatchLinkEventDefinition
                        ,nIntermediateSignalMessageEventDefinition,nIntermediateThrowMessageEventDefinition
                        ,nIntermediateThrowEscalationEventDefinition,nIntermediateThrowLinkEventDefinition
                        ,nIntermediateThrowSignalEventDefinition,nIntermediateThrowCompensateEventDefinition                       
                        ,nBoundaryEventDefinition,nBoundaryMessageEvent,nBoundaryTimerEvent
                        ,nBoundaryCancelEvent,nBoundaryConditionalEvent ,nBoundaryEscalationEvent
                        ,nBoundaryErrorEvent,nBoundarySignalEvent,nBoundaryCompensateEvent
                        ,nBoundaryTimerEventNonInt,nBoundaryEscalationEventNonInt,nBoundaryConditionalEventNonInt
                        ,nBoundaryMessageEventNonInt,nGroup,nMessageFlow,nSequenceFlow,nDefaultFlow,nPool,nVerticalLane,nVerticalPool,
                        nChoreographyTask,nChoreographyParticipant,nChoreographySubprocess,nITSystem,nCompensateAssociation,
                        nUnidirectionalAssociation,nUndirectedAssociation,nBidirectionalAssociation,nTextAnnotation])
        #print("Metrics of "+fileName+" file with: "+bpmnModeler+" Modeler are succesfully extracted ")
    # Convert the dataframe to an XlsxWriter Excel object e quindi aggiungo la riga nel file excel
    #df.to_csv('BPMN-metrics-output.csv', header=1, index=False, mode = 'a')


