# language: sv
Egenskap: tekniker ska kunna sköta underhållet av bankomaten

  Scenario: Fylla på med fack med 100-kronorssedlar
    Givet att det finns 20 sedlar i facket för 100-kronorssedlar
    När teknikern fyller med 81 100-kronorssedlar
    Så matar bankomaten ut 1 100-kronorssedlar
    Så innehåller bankomaten 100 100-kronorssedlar
