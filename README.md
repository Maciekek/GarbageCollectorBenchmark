#Opracowanie prostego testu porównującego wydajność mechanizmów garbage collector w JVM.

##Java -version
>java version "1.8.0_31"\n

>Java(TM) SE Runtime Environment (build 1.8.0_31-b13)

>Java HotSpot(TM) 64-Bit Server VM (build 25.31-b07, mixed mode)

##Sprzęt 
Procesor: Intel i3
Ram: 4Gb

##Do sprawdzenia
- Test 1: Alokacje w 1 wątku. Obiekty o stałym rozmiarze
- Test 2: Alokacje w 4 wątkach. Obiekty o stałym rozmiarze
- Test 3: Alokacje w 1 wątku. Obiekty o zmienny rozmiarze
- Test 4: Alokacje w 4 wątkach. Obiekty o zmiennym rozmiarze

##Sposób testowania
Każdy fragment (klasą zawiąrające definicje jednego testu) był odpalany osobno od całej reszty. Dlatego wyniki czasem mniej lub bardziej się rozbiegają. Ale każdy test był zawsze wynowywanej na "czystej" pamięci.

##Wyniki czasowe
---
##Test 1 - Alokacje w 1 wątku. Obiekty o stałym rozmiarze
| GC name            | czas N alokacji (ms)	| ilość alokacji (10s)   	|   	
|--------------------|----------------------|---------------------------|
| ```SerialGC```   |  211.479239          |77913                   |   	
| SerialGC 1024      |   219.483286        | 78897                  |   	
| ParallelOldGC      |247.628856           |  77101                 |      
| ```ParallelOldGC 1024 ```|  257.784193         | 78061   	                |   
| CMS                | 218.152595          |  77291                 |   
| CMS 1024           |  227.948263         |  74391                 |   
| G1                 | 281.524169          |  44512                 |   	
| G1 1024            |  291.782228         |  54236	                |   	

##Zwycięzca
Dla N alokacji: SerialGC

Dla ilości alokacji (10ms): ParallelOldGC 1024


---
##Test 2  - Alokacje w 4 wątkach. Obiekty o stałym rozmiarze
| GC name            | czas N alokacji (ms)	| ilość alokacji (10s)   	|   	
|--------------------|----------------------|---------------------------|
| SerialGC           |  731.116081          | 77438                  |   
| SerialGC 1024      |  726.248975       |   77830                |   	
| ParallelOldGC      |  828.874919        |  76031                 |      
| ParallelOldGC 1024 |  823.839536        |   78215 	                |  
| CMS                |  758.062652        |   73466                |   
|``` CMS 1024 ```       | 692.899751           |   79052	                    |  
| G1                 |  886.583372        |  48179 	                    |   
| ```G1 1024 ```   | 611.98746         |   57434    	                |   	

##Zwycięzca
Dla N alokacji: G1 1024   

Dla ilości alokacji (10ms): CMS 1024 

---
##Test 3 - Alokacje w 1 wątku. Obiekty o zmienny rozmiarze
| GC name            | czas N alokacji (ms)	| ilość alokacji (10s)   	|   
|--------------------|----------------------|---------------------------|
| SerialGC           |  1230.818306         | 3589                   |   
| SerialGC 1024      |  1172.93309       |   3582                 |   
| ParallelOldGC      |  1377.847254       |  3370                  |     
| ParallelOldGC 1024 | 1344.401746        |   3422  	                |   
| CMS                | 1244.462355        |   3528                 |   
| CMS 1024           |  1240.92799        |   3183                 |   
| ```G1  ```       |  697.044297        |   4235                 |   	
|``` G1 1024```      |  735.961529        |   4401                |   	

##Zwycięzca
Dla N alokacji: G1 

Dla ilości alokacji (10ms): G1 1024  


---
##Test 4 - Alokacje w 4 wątkach. Obiekty o zmiennym rozmiarze
| GC name            | czas N alokacji (ms)	| ilość alokacji (10s)   |
|--------------------|----------------------|---------------------------|
| SerialGC           | 26051.025733        | 2334                   |   
| SerialGC 1024      |  26435.930303      |   2340                 |   
| ParallelOldGC      |  35581.014867      |   2330                 |     
| ParallelOldGC 1024 |  28344.686789      |   2197  	                | 
| CMS                |   27411.918159     |   2367                 |   
| CMS 1024           |   21137.274544     |   2732                 |   
| G1                 |  12945.789622      |   3935                 |  
| ```G1 1024```      |  10599.991046      |   4167	                |   

##Zwycięzca
Dla N alokacji: G1 1024 

Dla ilości alokacji (10ms): G1 1024 


#Omówienie wyników.
Jak widać, główym zwycięzcą tych "zawodów" Jest G1. 
Jednak można zauważyć pewną zależność. Garbage G1 zaczyna być opłacalny dopiero wtedy kiedy mamy pewną (dużą) ilośc danych. Test 1 był najprostszym i najmniej złożonym testem i możemy zauważyć, że w takich prostych (jednowątkowych) operacja użycie G1 nie jest opłacalne. Co ciekawe bardzo dobrze wypadł najstarszy (jednowątkowny!) Garbage SerialGC. W środku stawki pojawiają się CMS w konfiguracji pamięci (min=max) 1024. CMS wielowątkowy odśmiecacz radzi sobie w pewnych okolicznościach jednak w porównaniu do G1 wypada dosyć słabo. 
Wnioski są dla mnie takie. Jeśli dodawanie danych jest stosunkowo mało złożone, obiekty są małe i jednowątkowe nie ma co wybierać największej broni jaką jest G1. Dla prostych danych zdecydowanie wystarczą podstawowe odśmiecacze (jak wyszło w teście nawet SerialGC poradził sobie w jednym przypadku najlepiej).
Także zachłanność nie zawsze popłaca.
W moim komputerze jest zainstalowane 4GB Ramu. Ciekawa sytuacja wystąpiła kiedy przydzieliłem 2048 pamięci dla JVM. Wydawać by się mogło, że będzie dużo szybiej. Jednak efekt był zupełnie odwrotny. GC ledwno radził sobie z tym co mu kazałem robić. Wyniki było kolosalnie wielkie, stąd pominąłem je. Podejrzewam, że JVM nie miał już miejsca RAM i zaczął zrzucać pamięć na dysk. Co ciekawe, mimo ogromnych różnic czasowych, nie występowały żadne błędy. 
Fajne zadanie pokazujące, że nie wszystko co najnowszę i proponowane nada się akurat w naszym przypadku. Jak zawsze trzeba mierzyć siły na zamiary;)
