# TP3 : Mapping objet relationnel avec JPA, Hibernate et Spring Data : Cas de Patient, Medecin, Rendez-vous, Consultation

<details><summary> <h2>Introduction</h2> </summary>
<b>ORM (Object Relational Mapping)</b> est un système qui met en œuvre la responsabilité du mappage de l'objet au modèle relationnel. Cela signifie qu'il est responsable du stockage des données du modèle objet dans le modèle relationnel et de la lecture des données du modèle relationnel dans le modèle objet.<br>
<b>JPA :</b> (Java persistence api) qui fournit des spécifications pour la persistance, la lecture et la gestion des données de votre objet Java vers des relations dans la base de données.<br>
<b>Hibernate :</b> Il existe plusieurs frameworks qui mettent en œuvre JPA. Hibernate est l'un d'entre eux. Il existe également d'autres frameworks. Mais si vous utilisez jpa avec spring, cela vous permet de passer à différents frameworks à l'avenir.<br>
<b>Spring Data JPA :</b> C'est une autre couche au-dessus de jpa que Spring fournit pour vous faciliter la vie.
</details>


<details><summary> <h2>Enoncé</h2> </summary>
Reprendre les exemples de mapping objet relationnel avec JPA, Hibernate et Spring Data :<br>
- Cas de Users et Roles
</details>

<details><summary> <h2>Conception</h2> </summary>
  <p align="center"><img src="assets/conception.PNG"></p>
</details>

<details><summary> <h2>Captures d'écran</h2> </summary>
<ol>
<ul><h4>Entities</h4>
  <ul><h6>User</h6></ul>
  <img src="assets/user.PNG"/>
  <ul><h6>Role</h6></ul>
  <img src="assets/role.PNG"/>
</ul>

<ul><h4>Repositories</h4>
  <ul><h6>User Repository</h6></ul>
  <img src="assets/userRepo.PNG"/>
  <ul><h6>Role Repository</h6></ul>
  <img src="assets/roleRepo.PNG"/>
</ul>

<ul><h4>Services</h4>
  <ul><h6>User service Interface</h6></ul>
  <img src="assets/userService.PNG"/>
  <ul><h6>User service Implementation</h6></ul>
  <img src="assets/serviceImpl1.PNG"/>
  <img src="assets/serviceImpl2.PNG"/>
</ul>

<ul><h4>Controllers</h4>
  <ul><h6>Rest Contoller</h6></ul>
  <img src="assets/controller.PNG"/>
</ul>

<ul><h4>Application</h4>
  <img src="assets/app1.PNG"/>
  <img src="assets/app2.PNG"/>
</ul>

<ul><h4></h4>
  <img src="assets/users.PNG"/>
</ul>

</ol>
</details>