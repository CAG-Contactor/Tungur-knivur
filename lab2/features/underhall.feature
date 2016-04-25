# language: sv
Egenskap: tekniker ska kunna sköta underhållet av bankomaten

  Scenario: Fylla på med fack med 100-kronorssedlar
    Givet att bankomaten är tom
    Givet att teknikern fyllt på med 20 100-kronorssedlar
    När teknikern fyller på med 81 100-kronorssedlar
    Så innehåller bankomaten 100 100-kronorssedlar
    Så matar bankomaten ut 1 100-kronorssedlar

  Scenario: Fylla på med fack med 200-kronorssedlar
    Givet att teknikern fyllt på med 20 200-kronorssedlar
    När teknikern fyller på med 1000 200-kronorssedlar
    Så innehåller bankomaten 100 200-kronorssedlar
    Så matar bankomaten ut 920 200-kronorssedlar
