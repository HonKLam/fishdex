# Coding-Richtlinien

## Namensgebung

- Verwende aussagekräftige Namen für Variablen, Funktionen und Klassen.
- camelCase größte Zeit in Frontend, PascalCase in Backend
- Vermeide Abkürzungen, außer wenn weit verbreitet.

## Kommentare

- Schreibe aussagekräftige Kommentare in Deutsch (Code in Englisch!)
- Denk daran, bei jeder neuen Seite einen Kommentar über Autor, Datum, Version zu machen
- Kommentiere komplexe Teile des Codes für besseres Verständnis.

# Bezüglich Git und GitHub

(Falls hier nochmal Hilfe benötigt wird, kann Lam nochmal nen Git-Crashcourse machen)

Wir arbeiten hauptsächlich im `dev`-Branch. Der `main`-Branch ist hauptsächlich ein Backup.

## Allgeiner Ablauf beim Coden

**Vor einem neuen Feature-Branch immer den dev-Branch auf den neusten Stand bringen**

```
git checkout dev
```

```
git pull
```

**Erstell für jedes Feature neuen Git-Branch (zB. `feature/irgendein-feature`)**

```
git checkout -b feature/irgendein-feature
```

**Mach deine Änderungen und lad sie in deinen lokalen Branch hoch**

```
git add .
```

```
git commit -m "Eine Commitnachricht hinzufügen"
```

**Lad jz deine Commits in die Cloud in die repo hoch**

```
git push
```

**Mach auf GitHub einen Pull Request, um in den `dev`-Branch zu mergen**

## Code-Reviews

- Es braucht mind. 1 Person, die sich deinen PR anschaut und bestätigen muss (die meiste Zeit wahrscheinlich Lam)
