# Setup

- VSCode oder anderen Text-Editor installieren

Für Code-Formatierung on-save:

- `Prettier`-Plugin installieren
- `Format on Save` in VSCode aktivieren

Alles was man für JS + React braucht installieren:

- VS-Code plugins
- `yarn` installieren (sowas wie npm, solltest du kennen, notfalls frag Lam)

Anschließend im `fd-frontend` Ordner in Konsole:
`yarn` - Sollte alle Dependencies installieren

# Frontend (einzeln) starten

- Konsole öffnen, `fd-frontend` navigieren
  `yarn run dev`
- In der Konsole sollte der Link stehen

## Was macht das Frontend?

Gibt nicht viel zu sagen. Das ist unsere eigentliche Website, die der Nutzer dann sieht.

Ziel ist es hier, die Daten aus dem Backend über APIs anzufragen und in der Website einzuarbeiten / Anfragen über API machen, dass bestimmte Daten gespeichert oder verändert werden im Backend.

## Übers Testen

Wir haben nicht viel Zeit für Unit-Tests etc., ein eigener Test durch Starten der Website muss fürs Erste reichen.
Lam wird sich um das Testen von Frontend + Backend (und API-Anfragen hier im Frontend) wahrscheinlich die meiste Zeit kümmern.
