# EXERCICE_05

![le menu](images/menu.jpg)

Exemple de contenu pour le fichier de configuration : shapesinthemazes_niveaux.txt qui doit être enregistré à la racine du répertoire utilisateur.

```python
# Orientation # epaisseur # position # debut # fin 

[Practice 1]
Goal = 900, 300

Infos = "Avec les flèches du clavier ou avec les touches ZQSD, faire passer le rectangle par la porte pour aller toucher le drapeau"; 500; 300; 300

Mur = VERTICAL,    5,       0,        	0,   600
Mur = VERTICAL,    5,     995,        	0,   600
Mur = HORIZONTAL,  5,       0,        	0,  1000
Mur = HORIZONTAL,  5,     595,        	0,  1000

Mur = VERTICAL,   15,     350,          0,   250
Mur = VERTICAL,   15,     350,        300,   600

Perso = GREY, 10, 10, 100, 50


[Practice 2]
Goal = 900, 300

Infos = "Pour changer la forme du rectangle, il faut appuyer sur 'CONTROLE' + une flèche pour sortir un coté, ou 'CONTROLE' + 'MAJ' + flèche pour le rentrer."; 500; 300; 300

Mur = VERTICAL,    5,       0,        	0,   600
Mur = VERTICAL,    5,     995,        	0,   600
Mur = HORIZONTAL,  5,       0,        	0,  1000
Mur = HORIZONTAL,  5,     595,        	0,  1000
	
Mur = VERTICAL,   15,     350,         0,    250
Mur = VERTICAL,   15,     350,        300,   600

Perso = MAROON, 5, 5, 70, 70

[Practice 3]
Goal = 900, 300

Infos = "Tous les chemins ne se valent pas ..."; 500; 300; 300

Mur = VERTICAL,    5,       0,        	0,   600
Mur = VERTICAL,    5,     995,        	0,   600
Mur = HORIZONTAL,  5,       0,        	0,  1000
Mur = HORIZONTAL,  5,     595,        	0,  1000
	
Mur = VERTICAL,   15,     350,          0,    60
Mur = VERTICAL,   15,     350,        100,   500
Mur = VERTICAL,   15,     350,        570,   600

Mur = VERTICAL,   15,     420,          0,   70
Mur = VERTICAL,   15,     460,         70,   140
Mur = VERTICAL,   15,     460,        180,   230
Mur = VERTICAL,   15,     420,        230,   600

Mur = HORIZONTAL, 15,      70,        420,   475
Mur = HORIZONTAL, 15,     230,        420,   475

Perso = MAROON, 5, 5, 80, 40

[Practice 4 (Marius)]
Goal = 900, 300

Infos = "Ça se complique ..."; 700; 300; 300

Mur = VERTICAL,    5,       0,        	0,   600
Mur = VERTICAL,    5,     995,        	0,   600
Mur = HORIZONTAL,  5,       0,        	0,  1000
Mur = HORIZONTAL,  5,     595,        	0,  1000
	
Mur = VERTICAL,   15,     350,          0,   140
Mur = VERTICAL,   15,     350,        210,   600

Mur = VERTICAL,   15,     420,        210,   280

Mur = VERTICAL,   15,     490,        135,   210
Mur = VERTICAL,   15,     490,        280,   350

Mur = VERTICAL,   15,     530,        260,   290

Mur = VERTICAL,   15,     600,          0,   315
Mur = VERTICAL,   15,     600,        350,   600

Mur = VERTICAL,   15,     510,        180,   215

Mur = HORIZONTAL, 15,     135,        350,   505

Mur = HORIZONTAL, 15,     210,        350,   420
Mur = HORIZONTAL, 15,     200,        490,   520

Mur = HORIZONTAL, 15,     180,        520,   600

Mur = HORIZONTAL, 15,     280,        420,   490

Mur = HORIZONTAL, 15,     350,        490,   600


Perso = BLACK, 5, 275, 75, 40
```
