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
from lxml import etree
"""
Input:
    - Nome del file XLSX (Deve stare nella stessa cartella dello script main.py)
    - Nome della tab del file XLSX (BPMN nel nostro caso)
    - Nome della colonna da prendere (In questo caso "link_file")

Output:
    - dataframe of link to download
"""

import lxml.etree
namespace = "bpmn:";
doc = lxml.etree.parse('bpmn_files/129.bpmn')
count=  doc.xpath('count(//bpmn:task )', namespaces={
  'bpmn': 'http://www.omg.org/spec/BPMN/20100524/MODEL',
  })
print(count)