# Installazione python:
# Mi funziona con Python 3.8.6

# Installazione dipendenze:
# pip3 install pandas
# pip3 install xlrd
# pip install xlsxwriter

# Per scrivere sul file Xlsx
import pandas as pd
# Per le operazioni dell'OS
import os
import lxml.etree
import glob
"""
Input:
    - Lista di file BPMN all'interno della cartella bpmn_files
    - File csv nominato "BPMN-metrics-output"

Output:
    - file xsl con i valori delle metriche dei file bpmn esamianti
"""


for files in os.listdir('bpmn_files'):
    namespace = "bpmn:";
    doc = lxml.etree.parse('bpmn_files/'+files);

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
    # Event - Intermediate
    
    #######################################################
    # Event - Boundary
    
    #######################################################
    # Statistical paramethers
    
    # Ora che ho creato il file, leggo il contenuto del file xml
    # Setting the file name
    fileName = files
    
    # dataframe da inserire nella riga del file excel
    df = pd.DataFrame({'BPMN_File_Name': [fileName],'nTask': [nTask],'nSendTask': [nSendTask],'nUserTask' : [nUserTask],'nManualTask' : [nManualTask],
                       'nBusinessRuleTask': [nBusinessRuleTask],'nServiceTask': [nServiceTask],'nScriptTask': [nScriptTask],'nCallActivity' : [nCallActivity],
                       'nSubProcess' : [nSubProcess],'nTransaction' : [nTransaction],'nAdHocSubProcess' : [nAdHocSubProcess],'nGroup': [nGroup],
                       'nCollaboration': [nCollaboration],'nLaneSet': [nLaneSet],'nLane' : [nLane],'nDataObject' : [nDataObject],'nDataObjectReference': [nDataObjectReference],'nDataStore': [nDataStore],
                       'nDataStoreReference': [nDataStoreReference],'nDataInput' : [nDataInput],'nDataOutput' : [nDataOutput],
                       'nExclusiveGateway' : [nExclusiveGateway],'nParallelGateway' : [nParallelGateway],'nInclusiveGateway': [nInclusiveGateway],'nEventBasedGateway' : [nEventBasedGateway],'nCondition': [nCondition],
                       'nIntermediateThrowEvent' : [nIntermediateThrowEvent],'nStartEvent' : [nStartEvent],'nStartSignalEventDefinition' : [nStartSignalEventDefinition],'nStartConditionalEventDefinition': [nStartConditionalEventDefinition],
                       'nStartTimerEventDefinition': [nStartTimerEventDefinition],'nStartMessageEventDefinition' : [nStartMessageEventDefinition],'nStartCompensateEventDefinition' : [nStartCompensateEventDefinition],'nStartCancelEventDefinition': [nStartCancelEventDefinition],
                       'nStartEscalationEventDefinition': [nStartEscalationEventDefinition],'nStartErrorEventDefinition': [nStartErrorEventDefinition],'nEndEvent': [nEndEvent],'nEndTerminateEventDefinition' : [nEndTerminateEventDefinition],'nEndEscalationEventDefinition' : [nEndEscalationEventDefinition],
                       'nEndMessageEventDefinition': [nEndMessageEventDefinition],
                       'nEndErrorEventDefinition': [nEndErrorEventDefinition],
                       'nEndCompensateEventDefinition': [nEndCompensateEventDefinition],
                       'nEndCancelEventDefinition': [nEndCancelEventDefinition]})
    print(" File "+fileName+"  succesfully analyzed ")
    
    # Convert the dataframe to an XlsxWriter Excel object e quindi aggiungo la riga nel file excel
    df.to_csv('BPMN-metrics-output.csv', index=False, mode = 'a')


