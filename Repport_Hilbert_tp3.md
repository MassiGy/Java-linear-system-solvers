# Rapport sur l'inversion de matrice appliquée aux matrices de Hilbert.



---

@Author: Massiles Ghernaout, L3 informatique, Université du Havre.

---



### Implémentation des tests :


**Déroulé:**

- Instancier une matrice Hilbert d'un ordre donné.
- On calcule son inverse.
- On calcule, Mprime, le produit entre la matrice ( une copie ) et son inverse.
- On calcule la différence entre Mprime et la matrice identité du même ordre.
- On calcule et on affiche la valeur des normes matricielle L1 et L infinie.
- On calcule et on affiche la valeur du conditionnement de la matrice suivant les deux normes.




### Résultat des tests :


**Note:**

> Les tests se base sur l'instanciation d'une matrice Hilbert d'ordre i € {3..15}, suivi par
> le calcule de son inverse.
> Pour cela, les tests ci-dessous sont éxectués en boucle sans aucune interaction nécessaire de la
> part de l'utilisateur. Toutefois, le test qui nécessite l'interaction de l'utilisateur ( le test
> demandé dans le tp 3 ) est bien implémenté dans la classe.


```txt

################# Ordre = 3 ############################
Son conditionnement avec L1: 748.0000000000066
Son conditionnement avec Linf: 748.0000000000066
Test réussi, norme_1(AA^-1 - I) est suffisamment petite: 7.105427357601002E-15
Test réussi, norme_inf(AA^-1 - I) est suffisamment petite: 1.4210854715202004E-14

################# Ordre = 4 ############################
Son conditionnement avec L1: 28374.999999997624
Son conditionnement avec Linf: 28374.999999997628
Test réussi, norme_1(AA^-1 - I) est suffisamment petite: 3.410605131648481E-13
Test réussi, norme_inf(AA^-1 - I) est suffisamment petite: 2.877698079828406E-13

################# Ordre = 5 ############################
Son conditionnement avec L1: 943655.9999970518
Son conditionnement avec Linf: 943655.9999970518
Test réussi, norme_1(AA^-1 - I) est suffisamment petite: 8.185452315956354E-12
Test réussi, norme_inf(AA^-1 - I) est suffisamment petite: 7.077005648170598E-12

################# Ordre = 6 ############################
Son conditionnement avec L1: 2.9070279001716707E7
Son conditionnement avec Linf: 2.907027900171671E7
Test réussi, norme_1(AA^-1 - I) est suffisamment petite: 3.4924596548080444E-10
Test réussi, norme_inf(AA^-1 - I) est suffisamment petite: 1.766693458193913E-10

################# Ordre = 7 ############################
Son conditionnement avec L1: 9.851948907838542E8
Son conditionnement avec Linf: 9.85194890782543E8
Test réussi, norme_1(AA^-1 - I) est suffisamment petite: 9.313225746154785E-9
Test réussi, norme_inf(AA^-1 - I) est suffisamment petite: 7.481503416784108E-9

################# Ordre = 8 ############################
Son conditionnement avec L1: 3.387279156959694E10
Son conditionnement avec Linf: 3.3872791575287006E10
Test réussi, norme_1(AA^-1 - I) est suffisamment petite: 2.60770320892334E-7
Test réussi, norme_inf(AA^-1 - I) est suffisamment petite: 9.69203028944321E-8

################# Ordre = 9 ############################
Son conditionnement avec L1: 1.0996506256002227E12
Son conditionnement avec Linf: 1.0996506383968508E12
Test échoué, norme_1(AA^-1 - I) est trop grande: 1.1682510375976562E-5
Test échoué, norme_inf(AA^-1 - I) est trop grande: 5.149289791006595E-6

################# Ordre = 10 ############################
Son conditionnement avec L1: 3.5354166949614414E13
Son conditionnement avec Linf: 3.53541640467932E13
Test échoué, norme_1(AA^-1 - I) est trop grande: 4.425048828125E-4
Test échoué, norme_inf(AA^-1 - I) est trop grande: 2.0417491032276303E-4

################# Ordre = 11 ############################
Son conditionnement avec L1: 1.2330463875822152E15
Son conditionnement avec Linf: 1.2330470731491288E15
Test échoué, norme_1(AA^-1 - I) est trop grande: 0.01080322265625
Test échoué, norme_inf(AA^-1 - I) est trop grande: 0.005418968299636617

################# Ordre = 12 ############################
Son conditionnement avec L1: 4.1991648064157384E16
Son conditionnement avec Linf: 4.1989597222450432E16
Test échoué, norme_1(AA^-1 - I) est trop grande: 0.478515625
Test échoué, norme_inf(AA^-1 - I) est trop grande: 0.2897139529231936

################# Ordre = 13 ############################
Son conditionnement avec L1: 2.7561977046264648E19
Son conditionnement avec Linf: 2.74332159236464E19
Test échoué, norme_1(AA^-1 - I) est trop grande: 287.5
Test échoué, norme_inf(AA^-1 - I) est trop grande: 200.1654022783041

################# Ordre = 14 ############################
Son conditionnement avec L1: 1.81023697792877824E18
Son conditionnement avec Linf: 1.81373254283350502E18
Test échoué, norme_1(AA^-1 - I) est trop grande: 19.3359375
Test échoué, norme_inf(AA^-1 - I) est trop grande: 11.815727483248338

################# Ordre = 15 ############################
Son conditionnement avec L1: 1.841371694319648E18
Son conditionnement avec Linf: 2.0678256290001385E18
Test échoué, norme_1(AA^-1 - I) est trop grande: 20.25
Test échoué, norme_inf(AA^-1 - I) est trop grande: 10.989230697974563

```

### Analyse et commentaires sur les résultats des tests: 

- *Quand i € {3..8}*

On voit que les deux normes sont inférieures au epsilon numérique perdefini, cela
est illustré par le message qui affiche leurs valeurs et que le test est bien réussi.


- *Quand i € {9..15}*

On voit que les deux normes sont inférieures au epsilon numérique predefini, cela est 
illustré par le message qui affiche leurs valeurs et que le test est bien réussi.


**Explication brève:**


Le fait que les normes dépasse la valeur du epsilon numérique predefini est dû au fait que:

- Les matrice de Hilbert sont des matrice mal conditionnées.

> https://fr.wikipedia.org/wiki/Matrice_de_Hilbert

- Nos tests effectuent plusieurs calculs matriciels différents sur des matrice 
Hilbert, qui sont constituées que de fractions. Par conséquent, les erreurs de calcul
se propagent et s'emplifient, ce qui fait que les résultats finaux soient surprenants.






















