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
#Si lancia lo script con il comando $python3 mainRevised.py path/to/bpmn/models/folder Metriche.csv


with open(sys.argv[2],'w',newline='') as file:
    writer = csv.writer(file)
    writer.writerow(["BPMN_File_Name","BPMN_Modeler","nTask","nSendTask","nUserTask","nManualTask","nBusinessRuleTask","nServiceTask","nScriptTask","nCallActivity","nSubProcess","nTransaction","nAdHocSubProcess","nGroup","nCollaboration","nLaneSet","nLane","nDataObject","nDataObjectReference","nDataStore","nDataStoreReference","nDataInput","nDataOutput","nExclusiveGateway","nParallelGateway","nInclusiveGateway","nEventBasedGateway","nCondition","nIntermediateThrowEvent","nStartEvent","nStartSignalEventDefinition","nStartConditionalEventDefinition","nStartTimerEventDefinition","nStartMessageEventDefinition","nStartCompensateEventDefinition","nStartCancelEventDefinition","nStartEscalationEventDefinition","nStartErrorEventDefinition","nEndEvent","nEndTerminateEventDefinition","nEndEscalationEventDefinition","nEndMessageEventDefinition","nEndErrorEventDefinition","nEndCompensateEventDefinition","nEndCancelEventDefinition","nIntermediateCatchEvent","nIntermediateCatchMessageEventDefinition","nIntermediateCatchTimerEventDefinition","nIntermediateCatchConditionalEventDefinition","nIntermediateCatchLinkEventDefinition","nIntermediateSignalMessageEventDefinition","nIntermediateThrowEventDefinition","nIntermediateThrowMessageEventDefinition","nIntermediateThrowEscalationEventDefinition","nIntermediateThrowLinkEventDefinition","nIntermediateThrowSignalEventDefinition","nIntermediateThrowCompensateEventDefinition","nIntermediateThrowCancelEventDefinition","nBoundaryEventDefinition","nBoundaryMessageEvent","nBoundaryTimerEvent","nBoundaryCancelEvent","nBoundaryConditionalEvent","nBoundaryEscalationEvent","nBoundaryErrorEvent","nBoundarySignalEvent","nBoundaryCompensateEvent","nBoundaryTimerEventCancel","nBoundaryEscalationEventCancel","nBoundaryConditionalEventCancel","nBoundaryMessageEventCancel","ngroup","nMessageFlow"])

   
for files in os.listdir(sys.argv[1]):
    namespace = "bpmn:";
    doc = lxml.etree.parse(sys.argv[1]+'/'+files);
    str = open(sys.argv[1]+'/'+files,'r').read()
    
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
    nTransaction=  doc.xpath('count(//bpmn:transaction )', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nAdHocSubProcess=  doc.xpath('count(//bpmn:adHocSubProcess )', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    #######################################################
    # Message Flow
    nMessageFlow=  doc.xpath('count(//bpmn:messageFlow )', namespaces={
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
    nLaneSet=  doc.xpath('count(//bpmn:laneSet )', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nLane=  doc.xpath('count(//bpmn:lane )', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    #######################################################
    # Pool/Participant
    nCollaboration=  doc.xpath('count(//bpmn:collaboration )', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nLaneSet=  doc.xpath('count(//bpmn:laneSet )', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nLane=  doc.xpath('count(//bpmn:lane )', namespaces={
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
    nStartEvent=  doc.xpath('count(//bpmn:startEvent )', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
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
    nStartCancelEventDefinition=  doc.xpath('count(//bpmn:startEvent//bpmn:cancelEventDefinition )', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nStartEscalationEventDefinition=  doc.xpath('count(//bpmn:startEvent//bpmn:escalationEventDefinition )', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nStartErrorEventDefinition=  doc.xpath('count(//bpmn:startEvent//bpmn:errorEventDefinition )', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    #######################################################
    # Event - End
    nEndEvent=  doc.xpath('count(//bpmn:endEvent )', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nEndTerminateEventDefinition=  doc.xpath('count(//bpmn:endEvent//bpmn:terminateEventDefinition )', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nEndEscalationEventDefinition=  doc.xpath('count(//bpmn:endEvent//bpmn:escalationEventDefinition )', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nEndMessageEventDefinition=  doc.xpath('count(//bpmn:endEvent//bpmn:messageEventDefinition )', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nEndErrorEventDefinition=  doc.xpath('count(//bpmn:endEvent//bpmn:errorEventDefinition )', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nEndCompensateEventDefinition=  doc.xpath('count(//bpmn:endEvent//bpmn:compensateEventDefinition )', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nEndCancelEventDefinition=  doc.xpath('count(//bpmn:endEvent//bpmn:cancelEventDefinition )', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    #######################################################
    # Event - Intermediate Catch
    nIntermediateCatchEvent=  doc.xpath('count(//bpmn:intermediateCatchEvent )', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
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
    nIntermediateThrowEventDefinition=  doc.xpath('count(//bpmn:intermediateThrowEvent)', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
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
    nIntermediateThrowCancelEventDefinition=  doc.xpath('count(//bpmn:intermediateThrowEvent//bpmn:cancelEventDefinition )', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    #######################################################
    # Event - Boundary 
    nBoundaryEventDefinition=  doc.xpath('count(//bpmn:boundaryEvent)', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nBoundaryMessageEvent=  doc.xpath('count(//bpmn:boundaryEvent//bpmn:timerMessageDefinition)', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nBoundaryTimerEvent=  doc.xpath('count(//bpmn:boundaryEvent//bpmn:timerEventDefinition)', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nBoundaryCancelEvent=  doc.xpath('count(//bpmn:boundaryEvent//bpmn:cancelEventDefinition)', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })    
    nBoundaryConditionalEvent=  doc.xpath('count(//bpmn:boundaryEvent//bpmn:conditionalEventDefinition)', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nBoundaryEscalationEvent=  doc.xpath('count(//bpmn:boundaryEvent//bpmn:escalationEventDefinition)', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nBoundaryErrorEvent=  doc.xpath('count(//bpmn:boundaryEvent//bpmn:errorEventDefinition)', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nBoundarySignalEvent=  doc.xpath('count(//bpmn:boundaryEvent//bpmn:signalEventDefinition)', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nBoundaryCompensateEvent=  doc.xpath('count(//bpmn:boundaryEvent//bpmn:compensateEventDefinition)', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    #######################################################
    # Event - Boundary Non-Interrupting PROBLEM
    nBoundaryTimerEventCancel=  doc.xpath('count(//bpmn:boundaryEvent//bpmn:timerEventDefinition)', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nBoundaryEscalationEventCancel=  doc.xpath('count(//bpmn:boundaryEvent//bpmn:escalationEventDefinition)', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nBoundaryConditionalEventCancel=  doc.xpath('count(//bpmn:boundaryEvent//bpmn:conditionalEventDefinition)', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nBoundarySignalEventCancel=  doc.xpath('count(//bpmn:boundaryEvent//bpmn:signalEventDefinition)', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nBoundaryMessageEventCancel=  doc.xpath('count(//bpmn:boundaryEvent//bpmn:timerMessageDefinition)', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    #######################################################
    # Statistical paramethers
    
    # Ora che ho creato il file, leggo il contenuto del file xml
    # dataframe da inserire nella riga del file excel
    with open(sys.argv[2],'a') as file:
        writer = csv.writer(file)
        writer.writerow([fileName,bpmnModeler,nTask,nSendTask,nUserTask,nManualTask,nBusinessRuleTask,nServiceTask,nScriptTask,nCallActivity
                       ,nSubProcess,nTransaction,nAdHocSubProcess,nGroup
                       ,nCollaboration,nLaneSet,nLane,nDataObject,nDataObjectReference,nDataStore
                       ,nDataStoreReference,nDataInput,nDataOutput
                       ,nExclusiveGateway,nParallelGateway,nInclusiveGateway,nEventBasedGateway,nCondition
                       ,nIntermediateThrowEvent,nStartEvent,nStartSignalEventDefinition,nStartConditionalEventDefinition
                       ,nStartTimerEventDefinition,nStartMessageEventDefinition,nStartCompensateEventDefinition,nStartCancelEventDefinition
                       ,nStartEscalationEventDefinition,nStartErrorEventDefinition,nEndEvent,nEndTerminateEventDefinition,nEndEscalationEventDefinition
                       ,nEndMessageEventDefinition,nEndErrorEventDefinition,nEndCompensateEventDefinition
                       ,nEndCancelEventDefinition,nIntermediateCatchEvent,nIntermediateCatchMessageEventDefinition
                       ,nIntermediateCatchTimerEventDefinition,nIntermediateCatchConditionalEventDefinition,nIntermediateCatchLinkEventDefinition
                       ,nIntermediateSignalMessageEventDefinition,nIntermediateThrowEventDefinition,nIntermediateThrowMessageEventDefinition
                       ,nIntermediateThrowEscalationEventDefinition,nIntermediateThrowLinkEventDefinition
                       ,nIntermediateThrowSignalEventDefinition,nIntermediateThrowCompensateEventDefinition,nIntermediateThrowCancelEventDefinition                       
                       ,nBoundaryEventDefinition,nBoundaryMessageEvent,nBoundaryTimerEvent
                       ,nBoundaryCancelEvent,nBoundaryConditionalEvent ,nBoundaryEscalationEvent
                       ,nBoundaryErrorEvent,nBoundarySignalEvent,nBoundaryCompensateEvent
                       ,nBoundaryTimerEventCancel,nBoundaryEscalationEventCancel,nBoundaryConditionalEventCancel
                       ,nBoundaryMessageEventCancel,nGroup,nMessageFlow])
    #print("Metrics of "+fileName+" file with: "+bpmnModeler+" Modeler are succesfully extracted ")

    # Convert the dataframe to an XlsxWriter Excel object e quindi aggiungo la riga nel file excel
    #df.to_csv('BPMN-metrics-output.csv', header=1, index=False, mode = 'a')


