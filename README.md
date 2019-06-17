# De verjaardagswensen kata

"Geleend" van <http://matteo.vaccari.name/blog/archives/154>.

## Probleem

1. Lees een lijst van werknemer records van een csv bestand.
2. Stuur een verjaardagsmail naar elke werknemer die vandaag verjaart.

De eerste lijnen van het csv bestand zijn als volgt:

```
last_name, first_name, date_of_birth, email
Doe, John, 1982/10/08, john.doe@example.com
Ann, Mary, 1975/09/11, mary.ann@example.com
```

De verjaardagsmail heeft de volgende text:

```
Subject: Gelukkige Verjaardag
Gelukkige verjaardag, beste John
```

Waar John wordt vervangen door de `first_name` van de werknemer.

Het programma moet door een main methode zoals deze gestart worden:

```
public static void main(String[] args) {
  ...
  BirthdayService birthdayService = new BirthdayService(employeeRepository,
  emailService);
  birthdayService.sendGreetings(today());
}
```

## Doelen

Het doel van deze kata is om een oplossing te vinden die:
* Testbaar is; we zouden de interne logica appart moeten kunnen testen, zonder
  ooit een echte mail te sturen.
* Flexibel is; we verwachten dat de data in de toekomst niet uit een csv maar
  een database komt. Of we willen geen email sturen, maar via Facebook of zo.
* een goed design heeft; deel business logica op van infrastructuur.

## Opgave

Er is 1 falende test, los die op.
Refactor dan de code naar het 'Ports and Adapters' patroon.

## Optionele requirement

Dit is een optioneel  requirement.
Om de business logica iets moeilijker te maken, kan je deze nog doen.
Mensen die op 29 februari verjaren krijgen hun mail al op 28 februari.
Behalve als het een schrikkeljaar is.
Dan gewoon op 29 februari.
