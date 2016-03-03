Cucumberbaserade BDD-tester
===========================

Köra tester
-----------

Tester startas med:

    $ mvn [ clean ] test

Om man vill inkludera cucumber-options:

    $ mvn [ clean ] test -Pbdd-test -Dcucumber.options="--tags @mytag"
