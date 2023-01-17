# VideosDB

## Descriere generală
Aplicația implementează o bază de date simplificată pentru filme, seriale și actori, similară cu IMDb. Utilizatorii pot fi de diferite tipuri și pot marca fimlele sau serialele ca vizionate, le pot adăuga la lista de favorite și le pot acorda o notă. Tot ei pot solicita ercomandări sau pot căuta un anumit film sau serial.
Interogările care se pot face p e baza de date pot oferi clasamente cu actori(in ordinea notelor pe care filmele acestora le-au obtinuit/după numărul de premii pe care aceștia le-au primit) sau clasamente cu filme sau seriale(sortate după rating-ul acordat de utilizatori, după durata acestora, după numărul vizualizărilor, etc.).

## How to use
Aplicația rulează prin intermediul clasei Main, aceasta efectuând teste de corectitudine asupra bazei de date. Implementarea a fost efectuată în pachetul databases.


## Detalii suplimentare despre implementare
Pentru rezolvarea acestei probleme am inceput cu crearea unui Database, reprezentat
de clasa MyDatabase, in care am stocat toate datele primite in variabila input
din metoda action. MyDatabase contine 4 ArrayList-uri, cate unul pentru fiecare
dintre Actor/Movie/Serial/UserData. Aceste entitati au o structura similara cu
clasele *InputData din pachetul fileio, cu mentiunea ca s-au adaugat fielduri
suplimentare(exemplu: in ActorData avem rating si numberOfAwards, in VideoData
avem addedToFavorite si overallRating, etc), acestea fiind necesare pentru re-
zolvarea query-urilor si a recomandarilor.

Dupa ce am creat un element de tipul MyDatabase, vom parcurge fiecare actiune
din input. In functie de tipul actiunii, vom executa metodele executeCommand,
executeQuery sau execute Recommendation. Toate metodele vor returna un string,
acesta reprezentand textul ce urmeaza sa fie scris ca mesaj la output. Dupa
executarea oricarei metode dintre cele 3 de mai sus, se va scrie rezultatul in
output. De asemenea, fiecare dintre cele 3 metode de tipul execute, au ca prin-
cipal scop rularea actiunii corespunzatoare inputului. Astfel, executeCommand
va executa metodele pentru comenzi din clasa MyDatabase, si va proceda similar
pentru query-uri si recomandări.

Pentru comenzi, se va cauta mai intai utilizatorul sau filmul/serialul in baza
de date, iar acestea se vor pasa, dupa caz mai departe pentru executarea
comenzii. Dupa ce se verifica toate condițiile, se vor face modificările nece-
sare asupra database-ului si se va returna un mesaj de succes. In cazul in care
una din condiții nu este respectata, se va returna un mesaj de eroare.

In ce priveste query-urile, acestea vor primi ca parametri filtrele alaturi de
tipul de sortare si, eventual, numarul maxim de elemente ce trebuie sa fie afi-
sate. Toate query-urile functioneaza dupa urmatorul scenariu: se face o copie
a bazei de date pe care se opereaza(Actors/Movies/etc.), daca este necesar, se
calculeaza date necesare in rezolvarea query-ului(numarul de vizualizari pentru
filme/seriale, durata unui serial, numarul de rating-uri acordate de un user,
etc.), se executa filtrarea elementelor, urmata de o sortare crescatoare(in
cazul in care tipul de sortare este descendent, se va inversa lista), iar in
final se va forma string-ul ce va fi returnat.
Filtrarea se realizeaza fie prin functiile ce se gasesc la finalul clasei
MyDatabase, sectiunea UTILS, fie cu ajutorul unor bucle, fie prin metoda
removeIf si expresii Lambda. Sortarea se face folosind comparatorii din clasa
Comparators, aflata in pachetul databases.

Recomandarile ruleaza dupa urmatorul scenariu: daca este necesar, se verifica
daca pentru user-ul dat ca parametru se poate aplica recomandarea respectiva,
se face o copie a bazelor de date pentru filme si seriale(in cazul Standard
Recommendation, unde se cauta primul video nevizualizat, acest pas si urmatoa-
rele nu se aplica), se elimina video-urile deja vizualizate, se filtreaza ele-
mentele si se sorteaza(doar daca este necesar), ca in final sa se genereze
textul ce urmeaza sa fie returnat ca output.

Clasa MyDatabase contine 4 sectiuni: Commands, Queries, Recommendations si
Utils. In ultima gasim metode ce calculeaza campurile suplimentare adaugate in
clasele de tip Data din database. Aceste campuri sunt utile pe parcursul inte-
rogarilor si a recomandarilor. Tot aici gasim si metode de search prin baza de
date si alte metode utile.
