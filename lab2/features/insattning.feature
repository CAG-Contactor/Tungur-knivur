# language: sv
Egenskap: kunder ska kunna sätta in pengar


  Bakgrund: det finns lite pengar i bankomaten
    Givet att bankomaten är tom
    Givet att teknikern fyllt på med 50 100-kronorssedlar
    Givet att teknikern fyllt på med 50 200-kronorssedlar
    Givet att teknikern fyllt på med 50 500-kronorssedlar
    Givet att teknikern fyllt på med 50 1000-kronorssedlar

  Scenario: kund ska kunna sätta in pengar på kontot
    Givet kunden loggar in med kontonummer 1234567 och pinkod 1423
    När kunden sätter in 2 100-kronorssedel
    Så har kunden 1200kr på kontot
    Så finns det 52 100-kronorssedlar i bankomaten
    Så finns det 50 200-kronorssedlar i bankomaten

