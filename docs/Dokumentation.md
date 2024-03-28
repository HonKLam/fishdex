# Fishdex Doku

(Ein paar Gedanken, die wahrscheinlich in die Doku mit rein müssen - es kann sein dass nicht alles rein muss)

## Idee

- Angler-Logbuch
- angelehnt an den `Pokédex` => Fishdex
- Fisch-Sammelbuch, Logbuch zum Einsehen der Angel-Aktivitäten
- Eventuell Mehrbenutzer-System mit Profilen

## Strukturierung/Organisation des Projekts

### Allgemein

- GitHub-repo als Zentraler Punkt für alles (Code, Doku, Projektorganisation)
- Meilensteine setzen
- pro Meilenstein -> Tickets festlegen -> Tickets zuweisen und machen
- Team-Treffen vor Ort / Online oftmals wöchentlich -> alles in Protokolle verfasst
- Entscheidungen wurden gemeinsam festgelegt oder von Team-Leiter

### Coding

- Wir haben Coding Richtlinien vereinbart
- Feature-Branch, Pull Request und Approval System auf GitHub

### Verlauf

- Erkennbar in der GitHub Roadmap (die verlinken oder screenshotten und zeigen oder komplett eigene Historie noma bauen?)

### Probleme im Verlauf

- Allgemine Probleme + Wie wurden die gelöst?

## Vorgehensmodell

- Eher iteratives Modell mit Meilensteinen und Tickets pro Meilenstein (siehe oben für mehr Erklärung)
- Eher unsicher mit neuer Technologie gewesen (Backend) -> von daher iteratives Modell um Schritt für Schritt an Java/Spring Boot herantasten zu können ohne zu viele Features vorzunehmen

## Software-Architektur

### Frontend

- JavaScript mit React-Library
  -> ausgewählt für einfacheres Erstellen vom Frontend, wiederverwendbare Komponenten, bereits Vorerfahrung mit Library

### Backend

- Java mit Spring/Spring Boot
  -> ausgewählt für sehr beliebte Technologie für Backend-Web-Anwendung, viel Dokumentation, Objektorientiert und simpel implementierbar

### Datenbank

(Hier nochmal Lam fragen für was wir uns letztendlich entschieden haben und warum)

### Verbindung

Sowohl Frontend als auch Backend funktionieren beide unabhängig voneinander.
Es könnte Front- oder Backend mit iwas anderem ausgetauscht werden -> müssen nur Verbindungen wiederhergestellt werden.

Verbindung erfolgt über API (Application Programming Interface), die vom Backend angeboten werden.
Auf Schnittstellen greift Frontend dann über API-Anfragen zu, um Daten zu bekommen, zu verändern oder zu speichern.

### Docker

Docker verwenden, um sowohl Frontend und Backend in einem Container / Art "virtuelle Maschine" zu starten und einfach die Seite zu testen oder später die Webapp auf einem externen Server zu hosten.
