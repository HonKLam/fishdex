# Fishdex - Das Angler-Logbuch

## Setup

### Git repo runterladen

```
git clone https://github.com/HonKLam/fishdex
```

### Vollständiges Projekt (Front- + Backend) mit Docker bauen + Starten

Oftmals wird es notwenig sein, Frontend + Backend zusammen zu starten, um Sachen zu testen etc..

Wir verwenden [docker](https://docs.docker.com/engine/install/), um eine Art "Virtuelle Maschine" zu erstellen, die für uns sowohl Frontend als auch Backend baut und startet.

Um also die App im aktuellen Zustand mit Frontend + Backend zu starten, folgenden Schritte machen:

- Docker installieren (siehe Link oben)
- in Konsole Projekt navigieren (`cd pfad/zu/fishdex`)

```
docker compose up --build
```

Es sollte bei `localhost:80` das Frontend starten und bei `localhost:8080` das Backend starten.
(fragt Lam wenn Hilfe hier benötigt wird)

**Wenn also das Frontend eine Anfrage an das Backend stellt, sollte die Adresse `localhost:8080` ansteuern.**

## Tickets

Wir arbeiten mit Tickets, die einen Meilenstein definieren und abgearbeitet werden müssen.
Tickets findet ihr bei GitHub unter [Meilenstein](https://github.com/HonKLam/fishdex/milestone/1) oder auf der [Roadmap](https://github.com/users/HonKLam/projects/10).

Bitte weist euch jeweils eure Tickets zu (GitHub Acc benötigt) und updated aktiv den Status des Tickets (`Todo`, `In progress`, `Done`)

Diskussionen, Fragen etc. können dann im Ticket im Chat reingeschrieben werden.
