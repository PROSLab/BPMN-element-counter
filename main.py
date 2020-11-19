# Installazione python:
# Mi funziona con Python 3.8.6

# Installazione dipendenze:
# pip3 install pandas
# pip3 install xlrd
# pip3 install faster_than_requests NB:(Mi ha richiesto Microsoft Visual C++ 14.0)
# pip install xlsxwriter

# Per scrivere sul file Xlsx
import pandas as pd
# HTTP getter
import requests as requests
# Per le operazioni dell'OS
import os
import xml.etree.ElementTree as et 

"""
Input:
    - Nome del file XLSX (Deve stare nella stessa cartella dello script main.py)
    - Nome della tab del file XLSX (BPMN nel nostro caso)
    - Nome della colonna da prendere (In questo caso "link_file")

Output:
    - dataframe of link to download
"""

# Creazione file metriche
writer = pd.ExcelWriter('BPMN-metrics-output.xlsx', engine='xlsxwriter')


# Ora che ho creato il file, leggo il contenuto del file xml

fileName = "test";
nStartEvent= "0";



# dataframe da inserire nella riga del file excel
df = pd.DataFrame({'BPMN_File_Name': [fileName],
                   'Start_Event': [nStartEvent]})

# Convert the dataframe to an XlsxWriter Excel object e quindi aggiungo la riga nel file excel
df.to_excel(writer, sheet_name='Sheet1', index=False)
writer.save()

'''
import lxml.etree
namespace = "bpmn:";
doc = lxml.etree.parse('bpmn_files/129.bpmn')
count=  doc.xpath('count(//bpmn:task )', namespaces={
  'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
  })
print(count) 
'''
