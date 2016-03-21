# language: sv
Egenskap: kunder ska kunna sätta in pengar

  Scenario: kund ska kunna sätta in pengar på kontot
    Givet att kunden med kontonummer 2222222 är inloggad
    Givet att kunden med kontonummer 2222222 har 0kr på kontot
    När kunden sätter in 2 100-kronorssedel
    Så har kunden 200kr på kontot
