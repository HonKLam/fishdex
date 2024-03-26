# Setup
- [IntelliJ (Community Edition)](https://www.jetbrains.com/idea/download/?fromIDE=&section=mac) installieren
- Java Software Development Kit (`jdk`) Version `21` in IntelliJ installieren
  - in Projekt-Einstellungen richtige Version auswählen - Link für Tutorial ist [hier](https://www.baeldung.com/intellij-change-java-version) (Es sollte bei SDK `coretto-21` stehen)
- Richtige JDK-Version für Gradle anpassen - Link für Tutorial ist [hier](https://stackoverflow.com/questions/67079327/how-can-i-fix-unsupported-class-file-major-version-60-in-intellij-idea) (die erste Lösung)

# Backend (alleine) zum Testen starten
- Konsole öffnen
- zum Projekt `fd-backend` navigieren (`cd Pfad/zum/Projekt/fishdex/fd-backend`), dann:
`./gradlew bootRun` (Fragt Lam wenn unsicher) - Konsole offen lassen, der Server ist gestartet
- Geht in Browser auf Seite: `localhost:8080`
- Ihr solltet ein ``Greetings from Spring Boot!`` auf der Seite sehen.

## Was macht das Backend

**1. API-Schnittstellen anbieten (Aufnehmen und Senden von Daten)**

Wir verwenden [APIs](https://en.wikipedia.org/wiki/API) um zwischen unserem Frontend und dem Backend kommunizieren zu können.
Das Ziel im Backend ist es, API-Schnittstellen zu erstellen, auf die das Frontend dann später immer zugreifen kann, wenn es was will.
(Sei es, um Daten anzufordern, um neue Daten zu speichern, um Daten zu verändern, ...)

**2. Normale Klassen und Erstellung von Objekten (Verarbeiten von Daten)**

Neben den APIs brauchen wir auch die eigentlichen Klassen in unserer App, von denen wir Objekte instanziieren.
Jede Klasse besitzt dabei also Eigenschaften/Properties (Name, Datum, Fischart, ...) und Methoden (füge Fisch hinzu, ändere Namen) die aufgerufen werden können.
Die Informationen und Methoden dieser Objekte werden dann durch die API-Schnittstellen angeboten. (Gebe dem Frontend diese Daten, Ändere diese Daten im Backend, ...)

**3. Arbeit mit einer Datenbank (Speichern von Daten)**

Daten müssen ja irgendwo gespeichert werden, um sie zum Beispiel beim nächsten Mal wieder auszulesen und anzuzeigen oder um zwischen unterschiedlichen Benutzern wechseln zu können.
Das Backend hält also auch eine Datenbank, die bei Bedarf Sachen reinschreibt oder ausliest, damit weitergearbetet werden kann.
Wie genau die Datenbankanbindung aussieht, schaut sich Lam nochmal an.

## Übers Testen

**Für APIs**

Um Schnittstellen zu testen, müsst ihr (nachdem ihr den Backend-Server (siehe oben) gestartet habt) über den Browser hinter `localhost:8080` eine "Adresse" anhängen.
Habt ihr zum Beispiel eine Schnittstelle bei `/hello` definiert, müsst ihr also auf `localhost:8080/hello` gehen, um zu sehen, was der Backendserver wiedergibt.

Man kann auch die App [Insomnia](https://insomnia.rest/download) installieren, die das API-Testen deutlich vereinfacht.
Das API-Testen kann Lam aber übernehmen.

**Für normale Klassen und Objekte**

Erstellt eigene Dateien, wo ihr ein Objekt einer definierten Klasse erstellen könnt. Gebt dem Objekt Werte, führt seine Methoden aus und schaut, ob es das macht, was ihr erwartet.
(Das richtige Testen zwischen Frontend + Backend übernimmt Lam)

**Für Datenbanken**

Ehrlich gesagt keine Ahnung. Wird schon passen.
