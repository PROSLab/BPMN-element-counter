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


for files in os.listdir('test'):
    namespace = "bpmn:";
    doc = lxml.etree.parse('test/'+files);

    # Calcolo metriche dei file
    #######################################################
    # Task
    #######################################################
    #######################################################
    # Event - Boundary 
    nBoundaryEventDefinition=  doc.xpath('count(//bpmn:boundaryEvent)', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    nBoundaryMessageEvent=  doc.xpath('count(//bpmn:boundaryEvent//bpmn:messageEventDefinition)', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    #######################################################
    # Event - Boundary Non-Interrupting
    nBoundaryMessageEventCancel=  doc.xpath('count(//bpmn:boundaryEvent/context[.="false"]//bpmn:messageEventDefinition)', namespaces={
    'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
    })
    #######################################################
    # Statistical paramethers
    
    # Ora che ho creato il file, leggo il contenuto del file xml
    # Setting the file name
    fileName = files
    bpmnModeler = "Camunda" # TODO or Signavio
    
    # dataframe da inserire nella riga del file excel
    df = pd.DataFrame({'BPMN_File_Name': [fileName],'BPMN_Modeler': [bpmnModeler],
    'nBoundaryMessageEvent': [nBoundaryMessageEvent],'nBoundaryMessageEventCancel': [nBoundaryMessageEventCancel]})
    
    print(" File "+fileName+"  succesfully analyzed ")
    
    # Convert the dataframe to an XlsxWriter Excel object e quindi aggiungo la riga nel file excel
    df.to_csv('BPMN-metrics-output.csv', index=False, mode = 'a')


