# TP1 : Inversion de contrôle et Injection des dépendances

<details><summary> <h2>Introduction</h2> </summary>
<b>L’inversion de contrôle</b> (inversion of control, IoC) est un patron d'architecture commun à tous les frameworks (ou cadre de développement et d'exécution). Il fonctionne selon le principe que le flot d'exécution d'un logiciel n'est plus sous le contrôle direct de l'application elle-même mais du framework ou de la couche logicielle sous-jacente.

L’inversion de contrôle est un terme générique. Selon le problème, il existe différentes formes, ou représentation d'IoC, le plus connu étant <i>l'injection de dépendances</i>.

<b>L'injection de dépendances</b> (dependency injection) est un patron de conception permettant, en programmation orientée objet, de découpler les dépendances entre objets.
</details>


<details><summary> <h2>Enoncé</h2> </summary>
Rendre un compte rendu en reprenant l'exemple traité dans les vidéos des deux dernières séances :
<ol>
<li> Créer l'interface IDao</li>
<li> Créer une implémentation de cette interface</li>
<li> Créer l'interface IMetier</li>
<li> Créer une implémentation de cette interface en utilisant le couplage faible</li>
<li> Faire l'injection des dépendances :</li>
<ul>
<li> Par instanciation statique</li>
<li> Par instanciation dynamique</li>
<li>En utilisant le Framework Spring
  <ul>
  <li> Version XML</li>
  <li> Version annotations</li>
  </ul>
</li>
</ul>
</ol>
</details>

<details><summary> <h2>Conception</h2> </summary>
  <img src="assets/conception.PNG"/>
</details>

<details><summary> <h2>Captures d'écran</h2> </summary>
<ol>
<li> Créer l'interface IDao</li>
<img src="assets/IDao.PNG"/>
<li> Créer une implémentation de cette interface</li>
<img src="assets/Dao.PNG"/>
<li> Créer l'interface IMetier</li>
<img src="assets/IMetier.PNG"/>
<li> Créer une implémentation de cette interface en utilisant le couplage faible</li>
<img src="assets/metier.PNG"/>
<li> Faire l'injection des dépendances :</li>
<ul>
<li> Par instanciation statique</li>
<img src="assets/statiqueInst.PNG"/>
<li> Par instanciation dynamique</li>
<b>Fichier</b> <code>config.txt</code><br>
  <img src="assets/conf.PNG"/></br>
<img src="assets/dynamicInst.PNG"/>
<li>En utilisant le Framework Spring
  <ul>
  <li> Version XML</li>
   <b>Fichier</b> <code>applicationContext.xml</code>
  <img src="assets/applicationContext.PNG"/>
  <img src="assets/xmlInst.PNG"/>
  <li> Version annotations</li>
  <img src="assets/AnnotationInst.PNG"/>
  </ul>
</li>
</ul>
</ol>
</details>
