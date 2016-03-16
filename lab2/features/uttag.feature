# language: sv
Egenskap: kunder ska kunna ta ut pengar

  Scenario: som kund ska jag kunna ta ut pengar som finns på mitt konto
    Givet att bankomaten är full
    När kunden loggar in med kontonummer 1234567 och pinkod 1423
    När kunden tar ut 1000kr
    Så har kunden 0kr kvar på kontot
    Så har kunden fått ut 1000kr


  Scenario: kund försöker ta ut med än maxgränsen för ett uttag
    Givet att bankomaten är full
    När kunden loggar in med kontonummer 9898989L och pinkod 9898
    När kunden tar ut 6000kr
    Så nekas kunden uttag
