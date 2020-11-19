# Installazione python:
# Mi funziona con Python 3.8.6

# Installazione dipendenze:
# pip3 install pandas
# pip3 install xlrd
# pip3 install faster_than_requests NB:(Mi ha richiesto Microsoft Visual C++ 14.0)

# Per leggere il file Xlsx
import pandas as pd
# HTTP getter
import requests as requests
# Per le operazioni dell'OS
import os

"""
Input:
    - Nome del file XLSX (Deve stare nella stessa cartella dello script main.py)
    - Nome della tab del file XLSX (BPMN nel nostro caso)
    - Nome della colonna da prendere (In questo caso "link_file")

Output:
    - dataframe of link to download
"""
df = pd.read_excel("BPMNMODELSGITHUB.xlsx", sheet_name="BPMN", usecols=[2])

# Trasformo la colonna in lista per le operazioni di estrazione
list_link_to_download = df["link_file"].tolist()


# Creazione di una cartella in cui inserire i file .BPMN che genererò
bpmn_folder_name = "bpmn_files"
if not os.path.exists(bpmn_folder_name):
    os.makedirs(bpmn_folder_name)
# Cambio directory
os.chdir(bpmn_folder_name)

# Ciclo i link
for (i, link) in enumerate(list_link_to_download):
    # Ottengo la risposta di ogni link e la metto in response
    response = requests.get(link)
    # Se la risposta non è errore 404 cioè che il file non esiste più:
    if response.status_code != 404:
        # Assegno il nome del file con l'indice
        file_name = str(i)+".bpmn"
        # Stampo la creazione dei file
        print(f"{i} -> creato {file_name}")
        # Scrivo nel file il contenuto della riga
        with open(file_name, 'w', encoding='utf8') as out:
            out.write(response.text)
    else:
        # Se è not found notifico che non creo il file
        print(f"{i} -> Not found, file non creato")
