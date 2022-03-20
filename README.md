# DotsBoxes-Parcial-DAM-Solution
Solution to exam parcial (abril 2022)

# Dots and Boxes

Dots and Boxes és un joc tradicional que va ser descrit per primera vegada per un matemàtic francès, Édouard Lucas, al segle XIX. Aquest és un joc clàssic de llapis i paper, senzill i divertit per a 2 o més jugadors.

El joc comença amb una graella buida de punts. La graella pot ser de qualsevol mida.

Normalment, es tira una moneda o es fa una pedra/paper/tisora per decidir qui comença la partida.

En aquest enllaç teniu un vídeo explicatiu de les regles i de com es juga. [Dots and Boxes](https://www.youtube.com/watch?v=FKv5KBzFW_s).

**Regles**

* Cada torn, un jugador ha de dibuixar una línia entre punts adjacents horitzontalment o verticalment.
* La línia dibuixada ha d'estar disponible i no haver estat dibuixada anteriorment.
* Cada torn els jugadors disposen d'un únic moviment.
* El jugador que aconsegueix completar un quadrat (és a dir, dibuixa la quarta paret d'una caixa) el guanya, guanyant un punt. A més a més, assoleixen un moviment addicional en aquest torn per cada caixa completada.
* Es dibuixen línies fins que es tanquen tots els quadrats
* Guanya el jugador amb més quadrats tancats.

**Consideracions sobre el repositori**

* El prototip del joc que es facilita als estudiants està dissenyat per a dos jugadors.
* Per dibuixar una línia, el jugador ha de fer clic en dos punts adjacents horitzontalment o verticalment per dibuixar una línia.
* El jugador manté el torn fins que no realitzar una jugada vàlida.

**MOLT IMPORTANT**:

* Per realitzar l'examen, heu de fer una fork del projecte i entrega un pull request amb  (*cognomNomDAMParcialAbril*).
* Intenteu entendre primer el codi i dedicar el temps a les funcionalitats que us demano.
* Intenteu fer les modificacions mínimes i necessaries al codi que us adjunto.


**Requeriments**

En aquest examen haureu de completar la implementació bàsica (**Requisits Mínims**) d'aquest joc per obtenir un 5. Per tal, d'aconseguir més nota es demana als estudiants que analitzin, millorin i apliquin les metodologies vistes a l'assignatura.

## Requisits Mínims  [punts=5]

* **Requisit 1**: El tauler s'ha d'actualitzar en temps real. S'ha d'actualitzar després de cada moviment la puntuació de cada jugador i també mostrar quin és el jugador que li toca fer el moviment.
* **Requisit 2**: S'ha d'implementar una funció per validar si una jugada és correcta. Recordeu:
  * Una jugada serà vàlida *si i només si*:
    * El jugador fa clic en dos punts adjacents horitzontalment o verticalment.
    * Les diagonals no són vàlides.
    * Els punts han d'estar lliures.
* * **Requisit 3**: Heu de notificar mitjançant l'ús d'un toast quan una jugada no es vàlida indicant el motiu al missatge.
* * **Requisit 4**: Afegir una pantalla inicial amb un botó amb el text iniciar partida. Quan l'usuari fa clic ha de començar la partida. I quan s'acaba la partida s'hi ha de retornar a aquesta pantalla.


## Requisits per obtenir una nota fins a 8 [punts=3]

Seleccionar una de les dues opcions:

**Opció sense MVVM o databinding**:

* **Completar**: Requisits Mínims per aconseguir el 5.
* **Requisit 5**: S'ha de validar la implementació validar la jugada amb un test unitari.
* **Requisit 6**: Quan la partida acaba, s'ha d'implementar un Dialog indicant que la partida ha acabat i el jugador(red/blue) ha estat el guanyador amb dos botons repetir o acabar. Acabar es torna a la pantalla d'inici. Repetir reinicialitza el tauler i comença una nova partida.

**Opció amb MVVM o databinding**) 

* **Completar**: Requisits Mínims per aconseguir el 5.
* **Requisit 7**: Ús dels patrons de disseny MVVM i databindings.

## Requisits per aconseguir 10 [punts=2]

* **Completar**: Requisits per obtenir una nota fins a 8 amb l'opció (**sense MVVM o databinding**).
* **Requisit 7**: Ús dels patrons de disseny MVVM i databindings.
* **Requisit 8**: Reflexió en un paràgraf sobre el repositori i la seva implementació. Estan ben plantejats els models? Es presenta una estructura lògica? Es podria millorar? Com?

