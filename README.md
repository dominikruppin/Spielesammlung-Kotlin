<h1 align="center">Grundlagen der Programmierung</h1>
<h3 align="center">Abschlussaufgabe Quiz</h3>
<br>

<p align="center">
  <img src="img/img1.png" width="50%">
</p>

> **Hinweis**: Lies die Aufgabe aufmerksam. Versuche vor Beginn, die Aufgabe zu strukturieren. Notiere dir skizzenartig/in einem Klassendiagramm, was dein Programm braucht und setze deinen Plan Schritt für Schritt um.


## 1. Szenario

Du bist neuerdings App-Developer und bekommst deinen ersten Auftrag: Du sollst das neue Videospiel „Golden Syntax“ mitentwickeln. Bei diesem Spiel handelt es sich um ein Quiz mit verschiedenen Fragen. Dieser Spiel kann auch mit mehreren Spielern gespielt werden. Die Spieler können die Fragen mithilfe von Jokern manipulieren. In deinem Developer-Team wird dir die Aufgabe zugeteilt, die **Spielelogik** zu entwickeln. Danach sollst du dem Team dein Spiel anhand eines Beispielquizes vorstellen.


## 2. Details

Die Vorlage ist ein leeres Projekt in IntelliJ. Erstelle alle nötigen Dateien: Es gibt eine main.kt, in der das Spiel abläuft. Alle Klassen erhalten <span style="text-decoration:underline;">jeweils</span> eine eigene Datei. Funktionen kannst du <span style="text-decoration:underline;">zusammen</span> in eine Datei verlagern.


### Informationen zu den Fragen:

* Es soll mindestens 2 verschiedene Sorten von Fragen geben (Beispiele: Multiple-Choice Fragen, Textantwort Fragen, Wahr/Falsch,...).
* Erstelle dafür eine Klasse _Frage_, diese implementiert die Logik, die alle Fragen gemeinsam haben. Von dieser Klasse erben dann die genauen Fragetypen.
* Für die Demo-Version des Quizes sollen ca 30 Fragen existieren. Diese Fragen kommen aus verschiedenen Themenbereichen (z.B. Geographie, Biologie, Geschichte, ...)

### Informationen zum Spieler:

* Es gibt eine Klasse _Spieler_. Diese Klasse speichert für jeden Spieler den Namen, das Alter, den aktuellen Punkte-/Kontostand, und eine Liste von Jokern. Du kannst nach belieben weitere Eigenschaften hinzufügen.
* Um am Quiz teilnehmen zu können, muss ein Spieler mindestens 12 Jahre alt sein.
* Im Quiz können mehrere Spieler gegeneinander antreten.


### Informationen zu den Jokern:
* Es gibt mindestens 2 Arten von Jokern, einige Ideen dafür:
  * 50:50 Joker, eliminiert falsche Antworten
  * ein Joker, der die richtige Antwort farbig markiert
  * ein Joker, der die aktuelle Frage an einem anderen Spieler abgibt
  * ein Joker, der eine Frage überspringen lässt um eine neue Frage zu erhalten
  * ...
* Jeder Joker kann nur einmal verwendet werden. Alternativ kannst du hier aber auch ein anderes System entwickeln.
* Beim Quizstart erhält jeder Spieler die selbe Anzahl an Jokern

### Informationen zum Quiz
* Bei Spielstart, wird gefragt, wie viele Spieler teilnehmen möchten. Die entsprechende Anzahl an Spielern wird dann nach ihrem Namen und Alter gefragt und entsprechend initialisiert.
* Das Spiel läuft in Runden ab. Wie genau die Runden in dem Quiz definiert sind, ist dir überlassen, aber einige Ideen:
  * eine Frage pro Spieler
  * jede Kategorie ist eine eigene Runde
  * eine Runde dauert bis ein Spieler x Fragen richtig beantwortet
  * ...
* Das Quiz hat ein klar definiertes Ende implementiert. Du kannst dich für die genaue Regel selbst entscheiden. Einige Ideen:
  * Es wird eine feste Anzahl Runden gespielt
  * Sobald ein Spieler x Fragen/Runden gewonnen hat, bzw. eine gewisse Punktzahl erreicht hat, endet das Spiel
  * Jeder Spieler startet mit einer Anzahl an Leben. Bei einer falsch beantworteten Frage, verliert man eins der Leben. Das Quiz endet, sobald nur 1 Spieler übrig ist.

## 3. Optionale, freiwillige Ergänzungen und Anmerkungen

Selbstverständlich darfst du dein Spiel mit deinen eigenen Ideen weiter ausschmücken.  \
Einige Ideen zur Erweiterung:


* Du könntest Teams von Spielern gemeinsam spielen lassen. Überlege dir selbst, wie du die Regeln dafür anpassen müsstest. 
* Weitere Joker oder Fragentypen sind immer gerne gesehen.
* ...
