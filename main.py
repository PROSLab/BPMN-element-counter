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

Output:
    - file xsl con i valori delle metriche dei file bpmn esamianti
"""

# Creazione file metriche
writer = pd.ExcelWriter('BPMN-metrics-output.xlsx', engine='xlsxwriter')

namespace = "bpmn:";
doc = lxml.etree.parse('bpmn_files/129.bpmn')

# Calcolo metriche dei file
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
# Ora che ho creato il file, leggo il contenuto del file xml

fileName = 'test'

# dataframe da inserire nella riga del +file excel
df = pd.DataFrame({'BPMN_File_Name': [fileName],
                   'nTask': [nTask],
                   'nSendTask': [nSendTask],
                   'nUserTask' : [nUserTask],
                   'nManualTask' : [nManualTask],
                   'nBusinessRuleTask': [nBusinessRuleTask],
                   'nServiceTask': [nServiceTask],
                   'nScriptTask': [nScriptTask],
                   'nCallActivity' : [nCallActivity],
                   'nSubProcess' : [nSubProcess],
                   'nTransaction' : [nTransaction],
                   'nAdHocSubProcess' : [nAdHocSubProcess]})

# Convert the dataframe to an XlsxWriter Excel object e quindi aggiungo la riga nel file excel
df.to_excel(writer, sheet_name='Sheet1', index=False)
writer.save()

