# language: sv
Egenskap: kunder ska kunna ta ut pengar

  Scenario: som kund ska jag bara få tre försök att mata in pinkod
    När kunden med kontonummer 1234567 loggar in med fel pinkod
    Så har inte kontot låsts
    När kunden med kontonummer 1234567 loggar in med fel pinkod
    Så har inte kontot låsts
    När kunden med kontonummer 1234567 loggar in med fel pinkod
    Så har kontot låsts

  Scenario: som kund ska jag kunna ta ut pengar som finns på mitt konto
    Givet att bankomaten är full
    Givet att kunden med kontonummer 1234567 är inloggad
    Givet att kunden med kontonummer 1234567 har 1000kr på kontot
    När kunden tar ut 1000kr
    Så har kunden 0kr kvar på kontot
    Så har kunden fått ut 1000kr

  Scenario: kund försöker ta ut med än maxgränsen för ett uttag
    Givet att bankomaten är full
    Givet att kunden med kontonummer 1234567 är inloggad
    När kunden tar ut 6000kr
    Så nekas kunden uttag

  Scenario: kund försöker ta ut mer pengar än vad som finns på kontot
    Givet att bankomaten är full
    Givet att kunden med kontonummer 1234567 är inloggad
    Givet att kunden med kontonummer 1234567 har 4000kr på kontot
    När kunden tar ut 5000kr
    Så nekas kunden uttag

  Scenario: kund försöker ta ut mer pengar än vad som finns i bankomaten
    Givet att teknikern fyllt på med 10 200-kronorssedlar
    Givet att kunden med kontonummer 1234567 är inloggad
    Givet att kunden med kontonummer 1234567 har 4000kr på kontot
    När kunden tar ut 3000kr
    Så nekas kunden uttag
