<h1 align="center">Spiele-Sammlung</h1>

## Aktuell verfügbare Spiele:
### 1. Wer wird Millionär
> **Hinweis**: Die Spielregeln und Spielbeschreibung können auch im Spiel selbst zu Beginn aufgerufen werden.
<details><summary>Spielbeschreibung</summary>

> "Wer wird Millionär?" ist ein Quiz-Spiel, das auf der gleichnamigen Fernsehsendung basiert. Das Spiel besteht aus mehreren Runden, in denen die Spieler Fragen aus verschiedenen Wissensgebieten beantworten müssen, um einen Geldpreis zu gewinnen.   
<br>
"Wer wird Millionär?" ist ein unterhaltsames und herausforderndes Spiel, das Wissen und strategisches Denken erfordert, um den Hauptpreis zu gewinnen.
</details>

<details><summary>Spielregeln</summary>

> **Spielmechanik**: Zu Beginn des Spiels wählt der Spieler zwischen dem Risikomdous oder dem normalen. Im Risikomodus fällt die Sicherheitsstufe bei 16.000€ weg, sodass der Spieler auf 500€ zurückfallen kann. Im Gegenzug dazu erhält er einen weiteren Joker. Pro richtig beantworteter Frage steigt der Geldbetrag. Das Ziel des Spiels ist es, durch das Beantworten einer Reihe von Fragen den Geldbetrag zu erhöhen und schließlich den Hauptpreis zu gewinnen.
<br><br>
**Fragen und Antworten**: Das Spiel besteht aus bis zu 15 Fragen, die in verschiedenen Schwierigkeitsgraden eingestuft sind. Jede Frage hat vier mögliche Antworten, von denen nur eine richtig ist. Der Spieler muss die richtige Antwort auswählen, um zur nächsten Frage zu gelangen. Dabei stehen ihm verschiedene Joker zur Verfügung.
<br>**Pro Frage gilt ein Zeitlimit von 60 Sekunden!**
<br><br>
**Sicherheitsstufen**: Jeweils bei 500€ und bei 16.000€ gibt es Standardmäßig Sicherheitsstufen. Beantwortet ma eine Frage falsch, so fällt man maximal auf diese letzte Sicherheitsstufe zurück und gewinnt diesen Betrag trotzdem. Der Risikmodus stellt eine Ausnahme dar. Siehe Spielmechanik.
<br><br>
**Gewinnprogression**: Mit jeder richtig beantworteten Frage erhöht sich der Geldbetrag, den der Spieler gewinnt. Die Schwierigkeit der Fragen steigt ebenfalls mit zunehmendem Geldbetrag. Das Ziel des Spiels ist es, alle 15 Fragen richtig zu beantworten und den Hauptpreis von einer Millionen Euro zu gewinnen.
<br><br>
**Fehler und Spielende**: Wenn der Spieler eine Frage falsch beantwortet, kann er einen Teil seiner Gewinne verlieren. Der Spieler kann allerdings auch jederzeit aufhören und den bereits erspielten Betrag mitnehmen.
</details> 

<details><summary>Mehrspieler-Modus</summary>

> Beim Mehrspielermodus können bis zu 5 Spieler antreten.  
> Dabei erhalten alle Spieler nacheinander die gleiche Sortierfrage, der Spieler welche die schnellste und gleichzeitig richtige Antwort gegeben hat, ist dann für das Hauptspiel qualifiziert.  
> Für den Fall, dass niemand die richtige Antwort hatte, wird die Qualifizierungsrunde wiederholt.
</details>
<details><Summary>Joker</Summary>

> **Folgende Joker gibt es im Spiel:**
<br>
> * **50/50 Joker**
<br> Zwei falsche Antworten werden entfernt.<br><br>
> * **Telefonjoker**
<br> Der Spieler kann eine von drei Personen anrufen, welche einem hilft<br><br>
> * **Publikumsjoker**
<br> Das Publikum stimmt ab, der Spieler bekommt das Ergebnis für alle Optionen in Prozent angezeigt<br><br>
> * **Zusatzjoker** (Nur im Riskomodus!)<br>
> Der Spieler kann eine Person aus dem Publikum auswählen, die ihm hilft.
<br><br>**Jeder Joker kann nur EINMAL pro Spiel genutzt werden!**
</details>
<details><summary>Befehle</summary>

> **Folgende Befehle stehen während der Beantwort der Fragen zur Verfügung:**
<br>
> * **stop**
<br>Du beendest das Spiel und gewinnst (natürlich virtuell) die bereits erspielte Geldsumme.
<br><br>
> * **joker**
<br>Solltest du noch Joker haben, kannst du im angezeigten Menü deinen gewünschten Joker auswählen und so benutzen.
</details>

### 2. Wordmix

<details><summary>Spielbeschreibung</summary>

> Bei Wordmix wurden alle Buchstaben eines Wortes in eine zufällige Reihenfolge gebracht. Deine Aufgabe ist es, das richtige Wort zu erraten.
</details>
<details><summary>Befehle</summary>

> **Folgende Befehle stehen während der Beantwortung der Fragen zur Verfügung:**
<br>
> * **next**
    <br>Du bekommst ein neues durchgemixtes Wort.
    <br><br>
> * **stop**
    <br>Du beendest Wordmix und wir bringen dich zurück ins Hauptmenü.
</details>
<br>

#### Für Nerds, Tutoren und Dozenten:
<details><summary>Übersicht der Klassen</summary>

**Einzelne Klassen**
> * **Player**  
    > Diese Klasse repräsentiert die Spielerobjekte. Jeder Spieler hat einen Namen und ein Alter.<br><br>
> * **Names**  
    > Diese Klasse repräsentiert eine Liste von Namen, zusätzlich beinhaltet sie die Funktion **generateRandomName** um sich einen zufälligen Namen aus dieser Liste zu holen.

**Spielklassen**
> * **Game** (Oberklasse)  
    > Diese Klasse repräsentiert die Oberklasse über die einzelnen Spiele. Sie legt fest, dass jede Spielklasse einen Spielenamen hat.
    <br><br>
> * **WWM** (Erbt von Game)  
    > Diese Klasse repräsentiert das Spiel "Wer wird Millionär", daher ist deren Spielname auch per Standard so definiert.
    <br><br>**Sie beinhaltet folgende Funktionen:**<br>
    <br> - **newQuestion** (Gibt basierend auf dem Fortschritt des Spiels eine einfache, mittlere oder schwere Frage zurück.)
    <br><br> - **gameRules** (Zeigt die Spielbeschreibung + Regeln an)
    <br><br> - **riskOrNot** (Der Spieler wählt zwischen Normalen Modus und Risikomodus)
    <br><br> - **nextQuestion** (Steuert den hauptsächlichen Spielfluss, ruft die benötigen Funktionen zum Anzeigen der nächsten Frage, zum einloggen der Antwort etc. auf. - Wertet auch aus, ob der Spieler richtig geantwortet hat.)
    <br><br> - **singleOrMultiPlayer** (Der Spieler wählt zwischen Einzel und Mehrspieler)
    <br><br> - **handleMultiPlayer** (Steuert den Spielfluss der Qualifizierungsrunde bei Mehrspieler)
    <br><br> - **startGame** (Startet das Spiel, gibt die Möglichkeit zum anzeigen der Spielregeln, außerdem ruft es die Funktion zum auswählen des Spielmodus auf, anschließend dann die Hauptfunktion für erste Frage.)
    <br><br>
> * **Wordmix** (Erbt von Game)
    > Diese Klasse repräsentiert das Spiel "Wordmix", daher ist deren Spielname auch per Standard so definiert. Beinhaltet zusätzlich eine Liste aus Wörtern für das Spiel.
    <br><br>**Sie beinhaltet folgende Funktionen:**<br>
    <br> - **use** (Steuert den gesamten Spielfluss)

**Jokerklassen**
> * **Joker** (Oberklasse)  
    > Diese Klasse repräsentiert die Oberklasse über die einzelnen Spiele. Sie legt fest, dass jeder Joker einen Namen hat.
    <br><br>
> * **FiftyFiftyJoker** (Erbt von Joker)  
    > Repräsentiert den 50/50 Joker
    <br><br>
> * **Telefonjoker** (Erbt von Joker)  
    > Repräsentiert den Telefonjoker
    <br><br>
> * **Publikumsjoker** (Erbt von Joker)  
    > Repräsentiert den Publikumsjoker
    <br><br>
> * **Zusatzjoker** (Erbt von Joker)  
    > Repräsentiert den Zusatzjoker
    <br><br>
    > **Alle Joker besitzen die Funktion use(), damit werden die Joker und deren Funktionalität aufgerufen!**

**Fragenklassen**
> * **Question** (Oberklasse)  
    > Diese Klasse repräsentiert die Oberklasse für die verschiedenen Fragenarten. Sie legt fest, dass jede Fragenklasse eine Frage und eine Schwierigkeitsstufe braucht. Außerdem enthält sie die Liste der Fragen.
    <br><br>
> * **MultipleChoiceQuestion** (Erbt von Question)  
    > Repräsentiert die MultipleChoiceQuestions für "Wer wird Millionär". Jede Frage hat vier vorgegebene Antwortmöglichkeiten, wovon nur eine richtig ist.
    <br><br>**Sie beinhaltet folgende Funktionen:**<br>
    <br> - **getQuestion** (Zeigt die aktuelle Frage an)  
    <br> - **getQuestionLoggedIn** (Gibt die Frage noch mal mit markierter Wahl des Spielers aus)  
    <br> - **getAnswer** (Gibt die Frage samt Lösung durch farbliche Markierungen aus)  
    <br> - **chooseSolution** (Gibt dem Spieler die Möglichkeit eine Antwort auswählen, alternativ das Spiel zu beenden oder einen Joker zu nutzen)
    <br><br>
> * **SortingQuestion** (Erbt von Question)  
    > Diese Klasse repräsentiert die Sortierfragen für "Wer wird Millionär" im Mehrspielermodus während der Qualifizierung.
    <br><br>**Sie beinhaltet folgende Funktionen:**<br>
    <br> - **getNewQuestion** (Gibt eine zufällige Sortierfrage zurück)
</details>

<details><summary>Klassendiagramm</summary>

![alt text](https://i.imgur.com/nXDRA1G.png)
</details>