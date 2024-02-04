# Résultats des tests (résolution de systèmes linéaires)

---

@Author: Massiles Ghernaout, L3 informatique, Université du Havre.

---

*Note*
> Les classes Matrice et Vecteur ont été testées lors de l'étape précédente.




### SysDiagonal

A:=<br>
   {3, 0,  0, 0}<br>
   {0, 7,  0, 0}<br>
   {0, 0, -2, 0}<br>
   {0, 0, 0, -5}

b := <br>
    { 1, 7, -6, 8 }


* résolution Ax=b, puis calcule des trois norme pour Ax-b
* Les normes sont bien inférieures au epsilon, donc le test est positif.

### SysTriangInf

A:=<br>
   {3, 0, 0}<br>
   {7, 2, 0}<br>
   {5, 10, 3}<br>

b := <br>
    {1, 3, 2}


* résolution Ax=b, puis calcule des trois normes pour Ax-b
* Les normes sont bien inférieures au epsilon, donc le test est positif.
* idem pour SysTriangInfUnite

### SysTriangSup

A:=<br>
   {3, 4, 5}<br>
   {0, 1, 7}<br>
   {0, 0, 8}

b := <br>
{3, 7, 2}


* résolution Ax=b, puis calcule des trois normes pour Ax-b
* Les normes sont bien inférieures au epsilon, donc le test est positif.
* idem pour SysTriangSupUnite


### Helder

A:=<br>
  { 1, 2,  3,  4}<br>
  {-2, 3,  6, -1}<br>
  { 3, 4, -6,  2}<br>
  {-5, 2,  3, -1}<br>

b := <br>
    {3, 7, 2, -1}


* factorisation de A en LDR.
* Vérification que A = LDR

#### Deuxième Test, A²x=b

A:=<br>
   { 1, 0, 0}<br>
   { 0, 1, 0}<br>
   { 0, 0, 1}

b := <br>
{3, 5, 8}


* factorisation de A en LDR.
* Résolution du système Ay=b, où y = Ax
* Puis, résolution du système Ax=y
* Calcule de la norme de A²x-b, et vérifier qu'elle sont bien inférieures epsilon

*Note:*
- D'autres tests ont été effectués au fur et à mesure pour vérifier le bon fonctionnement des autres méthodes présentes dans les différentes classes.




