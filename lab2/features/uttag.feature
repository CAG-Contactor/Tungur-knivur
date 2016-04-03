# language: sv
Egenskap: kunder ska kunna ta ut pengar

  Scenario: som kund ska jag bara få tre försök att mata in pinkod
    När kunden med kontonummer 1234567 loggar in med fel pinkod
    Så har inte kontot låsts
    När kunden med kontonummer 1234567 loggar in med fel pinkod
    Så har inte kontot låsts
    När kunden med kontonummer 1234567 loggar in med fel pinkod
    Så har kontot låsts

  Scenario: som kund ska jag bara få tre försök att mata in pinkod
    När kunden med kontonummer 4242424 loggar in med fel pinkod
    Så har inte kontot låsts
    När kunden med kontonummer 4242424 loggar in med fel pinkod
    Så har inte kontot låsts
    När kunden avbryter
    När kunden med kontonummer 4242424 loggar in med fel pinkod
    Så har inte kontot låsts

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

  Scenario: kund tar ut pengar och får tillbaka olika valörer på sedlar
    Givet att bankomaten är full
    Givet att kunden med kontonummer 1234567 är inloggad
    Givet att kunden med kontonummer 1234567 har 80000kr på kontot
    När kunden tar ut 4800kr
    Så har kunden fått ut
      | 0 | 1000 |
      | 9 | 500  |
      | 0 | 200  |
      | 3 | 100  |

  Scenario: kund tar ut pengar och får tillbaka olika valörer på sedlar
    Givet att teknikern fyllt på med 100 100-kronorssedlar
    Givet att kunden med kontonummer 1234567 är inloggad
    Givet att kunden med kontonummer 1234567 har 80000kr på kontot
    När kunden tar ut 5000kr
    Så har kunden fått ut
      | 0  | 1000 |
      | 0  | 500  |
      | 0  | 200  |
      | 50 | 100  |

  Scenario: kund tar ut pengar och får tillbaka olika valörer på sedlar
    Givet att teknikern fyllt på med 100 200-kronorssedlar
    Givet att teknikern fyllt på med 6 100-kronorssedlar
    Givet att kunden med kontonummer 1234567 är inloggad
    Givet att kunden med kontonummer 1234567 har 80000kr på kontot
    När kunden tar ut 5000kr
    Så har kunden fått ut
      | 0  | 1000 |
      | 0  | 500  |
      | 6  | 100  |
      | 22 | 200  |

  Scenariomall: kund tar ut pengar
    Givet att teknikern fyllt på med <in1000> 1000-kronorssedlar
    Givet att teknikern fyllt på med <in500> 500-kronorssedlar
    Givet att teknikern fyllt på med <in200> 200-kronorssedlar
    Givet att teknikern fyllt på med <in100> 100-kronorssedlar
    Givet att kunden med kontonummer 1234567 är inloggad
    Givet att kunden med kontonummer 1234567 har 80000kr på kontot
    När kunden tar ut <uttag>kr
    Så har kunden fått ut
      | <ut1000> | 1000 |
      | <ut500>  | 500  |
      | <ut200>  | 200  |
      | <ut100>  | 100  |

    Exempel:
      | in1000 | in500 | in200 | in100 | uttag | ut1000 | ut500 | ut200 | ut100 |
      | 0      | 0     | 0     | 1     | 100   | 0      | 0     | 0     | 1     |
      | 0      | 0     | 2     | 3     | 400   | 0      | 0     | 0     | 0     |
      | 1      | 0     | 0     | 0     | 1000  | 1      | 0     | 0     | 0     |
      | 0      | 5     | 0     | 100   | 4300  | 0      | 5     | 0     | 18    |
      | 100    | 100   | 100   | 100   | 5001  | 0      | 0     | 0     | 0     |
      | 100    | 100   | 0     | 0     | 1000  | 0      | 2     | 0     | 0     |
      | 100    | 100   | 100   | 100   | 150   | 0      | 0     | 0     | 0     |
      | 0      | 0     | 0     | 0     | 100   | 0      | 0     | 0     | 0     |
      | -10    | -10   | -10   | -10   | 100   | 0      | 0     | 0     | 0     |
      | -10    | -10   | -10   | -10   | -100  | 0      | 0     | 0     | 0     |
      | 1      | 1     | 1     | 1     | -100  | 0      | 0     | 0     | 0     |