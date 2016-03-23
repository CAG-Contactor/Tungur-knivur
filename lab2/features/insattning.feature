# language: sv
Egenskap: kunder ska kunna sätta in pengar

  Bakgrund: det finns lite pengar i bankomaten
    Givet att teknikern fyllt på med 50 100-kronorssedlar
    Givet att teknikern fyllt på med 50 200-kronorssedlar
    Givet att teknikern fyllt på med 50 500-kronorssedlar
    Givet att teknikern fyllt på med 50 1000-kronorssedlar


  Scenario: kund ska kunna sätta in pengar på kontot
    Givet att kunden med kontonummer 2222222 är inloggad
    Givet att kunden med kontonummer 2222222 har 0kr på kontot
    När kunden sätter in 2 100-kronorssedel
    Så har kunden 200kr på kontot
    Så finns det 52 100-kronorssedlar i bankomaten
