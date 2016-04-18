# language: sv
Egenskap: En egenskap

  Scenario: Det ska gå att summera och subtrahera
    Givet att min kalkylator är nollställd
    När jag matar in plus 10
    När jag matar in minus 4
    Så har jag 6

  Scenario: Det ska gå att summera två tal
    Givet att min kalkylator är nollställd
    När jag summerar 1,3 och 2,1
    Så har jag 3,4

  Scenariomall: Alla räknesätt ska gå att kombinera
    Givet att min kalkylator är nollställd
    När jag matar in plus <plus>
    När jag matar in minus <minus>
    När jag multiplicerar med <multiplicera>
    Så har jag <summa>

    Exempel: Exempel på räknesätt
      | plus | minus | multiplicera | summa |
      | 1    | 1     | 1            | 0     |
      | 0    | 1     | 1            | -1    |
      | 0    | 0     | 1            | 0     |

  Scenario: Det ska gå att räkna ut ett medelvärde
    Givet att min kalkylator är nollställd
    När jag matar in dessa tal för att räkna ut medeltal
      | 10 |
      | 20 |
      | 10 |
      | 15 |
    Så har jag 13,75
