Digital Books — Examenproject 

Welkom bij de documentatie van Digital Books, een digitaal boekenwinkel- en reserveringssysteem ontwikkeld met Java Spring Boot en een modern Single Page Application (SPA) frontend.

Over het Project:

Digital Books is een webapplicatie waarmee gebruikers boeken kunnen bekijken en reserveren uit het assortiment. Het systeem maakt een strikt onderscheid tussen normale gebruikers en administrators:

Gebruikers (USER): Kunnen de catalogus bekijken, beschikbare voorraden inzien, het gewenste aantal selecteren en boeken toevoegen aan hun reserveringen. Daarnaast ontvangen ze notificaties van de beheerder over de status van hun reserveringen (bijv. wanneer het boek mag worden opgehaald).

Administrators (ADMIN): Hebben een dedicated admin-dashboard om nieuwe gebruikersaccounts aan te maken, bestaande accounts te beheren, berichten naar specifieke gebruikers te sturen en binnengekomen reserveringen goed te keuren ("Zet op: Mag ophalen").

Functionaliteiten

Gebruikersfunctionaliteiten

Authenticatie & Sessiebeheer: Inloggen met gebruikersnaam en wachtwoord (sessie wordt lokaal opgeslagen via localStorage).

Catalogusoverzicht: Dynamische weergave van beschikbare boeken met live voorraadbeheer.

Reserveringen plaatsen: Selecteren van het aantal gewenste exemplaren en direct reserveren.

Stockcontrole: Automatische vermindering van de voorraad bij het plaatsen van een reservering en blokkade bij uitverkochte items.

Mijn Reserveringen: Eigen reserveringen inzien inclusief de huidige status ("⏳ In afwachting" of "🟢 Goedgekeurd / Mag ophalen").

Notificatiesysteem: Ontvangen van berichten van de beheerder met de optie om meldingen als gelezen te markeren.

⚙️ Admin Functionaliteiten

Gebruikersbeheer (CRUD):

Aanmaken van nieuwe gebruikers accounts (keuze uit rollen USER of ADMIN).

Inzien van alle geregistreerde accounts in een overzichtelijke tabel.

Verwijderen van gebruikersaccounts.

Reserveringsbeheer:

Inzien van alle geplaatste reserveringen van alle gebruikers.

Reserveringsstatus bijwerken met één klik op de knop "✅ Zet op: Mag ophalen".

Automatische berichtgeneratie naar de gebruiker bij goedkeuring van de reservering.

Berichtensysteem:

Direct berichten sturen naar specifieke gebruikers vanuit het dashboard.

🛠️ Architectuur & Technologieën

Het project is opgebouwd volgens een nette RESTful lagenarchitectuur (Controller - Service/Repository - Entity).

Backend

Java 17 / 21

Spring Boot 3.x

Spring Data JPA / Hibernate: Object-Relational Mapping voor gegevensbeheer.

Spring Web (MVC / REST): REST Endpoints voor communicatie met de frontend.

MySQL Database: Relationele database voor persistente opslag van boeken, gebruikers, reserveringen en berichten.

Frontend

HTML5 & CSS3: Responsive UI met een strak en modern ontwerp (style.css).

Vanilla JavaScript (ES6+): Single Page Application (SPA) logica met behulp van de Fetch API voor asynchrone HTTP-verzoeken.

🗄️ Databasestructuur & Entiteiten

Het systeem bevat vier kernentiteiten in JPA:

User (user tabel):

id (Long, PK)

username (String)

email (String)

password (String)

role (String: ADMIN of USER)

Book (book tabel):

id (Long, PK)

titel (String)

auteur (String)

isbn (String)

prijs (double)

stock (int)

omschrijving (String)

Reservation (reservation tabel):

id (Long, PK)

user (ManyToOne relatie met User)

book (ManyToOne relatie met Book)

aantal (int)

bevestigd (boolean)

Message (message tabel):

id (Long, PK)

recipientUsername (String)

content (String)

📡 REST API Endpoints

🔑 Gebruikers (/api/users)

Method

Endpoint

Omschrijving

GET

/api/users

Haal alle gebruikers op

GET

/api/users/find?username={name}

Zoek specifieke gebruiker op basis van gebruikersnaam

POST

/api/users

Maak een nieuwe gebruiker aan (inclusief rol)

DELETE

/api/users/{id}

Verwijder een gebruiker

📚 Boeken (/api/books)

Method

Endpoint

Omschrijving

GET

/api/books

Haal de volledige boekencatalogus op

🎫 Reserveringen (/api/reservations)

Method

Endpoint

Omschrijving

GET

/api/reservations

Haal alle reserveringen op

POST

/api/reservations?userId={uId}&bookId={bId}&aantal={n}

Maak een nieuwe reservering aan en verminder stock

PATCH

/api/reservations/{id}/confirm

Bevestig reservering ("Mag ophalen") + verstuur notificatie

DELETE

/api/reservations/{id}

Verwijder/annuleer een reservering

✉️ Berichten (/api/messages)

Method

Endpoint

Omschrijving

GET

/api/messages?username={name}

Haal alle berichten op voor een specifieke gebruiker

POST

/api/messages

Verstuur een nieuw bericht naar een gebruiker

DELETE

/api/messages/{id}

Verwijder een gelezen bericht

🚀 Installatie & Uitvoering

1. Database Voorbereiding

Zorg dat MySQL actief is en maak een database aan (bijvoorbeeld boekenwinkel):

CREATE DATABASE boekenwinkel;


Controleer de gegevens in src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/boekenwinkel?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=JOUW_WACHTWOORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


2. Applicatie Starten

Open het project in IntelliJ IDEA.

Run het hoofdbestand: BoekenwinkelApplication.java.

De server start op op de standaard poort 8080.

3. Applicatie Openen

Open je browser en navigeer naar:

http://localhost:8080


🗃️ Handmatige Stock Aanpassen via MySQL

Indien je de voorraad van een boek handmatig wilt herstellen of aanpassen in MySQL, kun je daarvoor de volgende query gebruiken:

UPDATE book
SET stock = 10
WHERE id = 1;


(Vervang 10 door het gewenste aantal en id = 1 door het ID van het boek dat je wilt aanpassen).

## Gebruik van AI-tools

Tijdens de ontwikkeling van dit project is gebruikgemaakt van AI-assistentie (Gemini) als ontwikkel- en leerhulpmiddel.

### Waarvoor is AI ingezet?
- **Code-optimalisatie & Debugging:** Het opsporen van Spring Boot- en JavaScript-fouten (zoals het oplossen van `cannot find symbol` import-fouten en REST endpoint-afhandelingen).
- **
- **Documentatie:** Assistentie bij het opstellen en structureren van deze `README.md`.
-