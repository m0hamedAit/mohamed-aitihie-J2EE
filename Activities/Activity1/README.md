# TP6 : Activité Pratique Spring MVC Thymeleaf


<details><summary> <h2>Enoncé</h2> </summary>
Créer une application Web basée sur Spring MVC, Spring Data JPA et Spring Security qui permet de gérer des étudiants.
Chaque étudiant est défini par:
 - Son id
 - Son nom
 - Son prénom
 - Son email
 - Sa date naissance
 - Son genre : MASCULIN ou FEMININ
 - Un attribut qui indique si il est en règle ou non
L'application doit offrir les fonctionnalités suivantes :
  - Chercher des étudiants par nom
  - Faire la pagination
  - Supprimer des étudiants en utilisant la méthode (DELETE au lieu de GET)
  - Saisir et Ajouter des étudiants avec validation des formulaires
  - Editer et mettre à jour des étudiants
  - Créer une page template 
  - Sécuriser l'accès à l'application avec un système d'authentification basé sur Spring security en utilisant la stratégie UseDetails Service
 - Ajouter d'autres fonctionnalités supplémentaires
</details>

<details><summary> <h2>Conception</h2> </summary>
  <p align="center"><img src="assets/conception.PNG"></p>
</details>

<details><summary> <h2>Captures d'écran</h2> </summary>
<ol>
<ul><h4>Entities</h4>
  <ul><h6>Student</h6></ul>
  <img src="assets/student.PNG"/>
</ul>
<ul><h4>Enums</h4>
  <ul><h6>Sexe</h6></ul>
  <img src="assets/sexe.PNG"/>
</ul>

<ul><h4>Repositories</h4>
  <ul><h6>Student Repository</h6></ul>
  <img src="assets/studentRepository.PNG"/>
</ul>

<ul><h4>Services</h4>
  <ul><h6>StudentService Interface</h6></ul>
  <img src="assets/studentService.PNG"/>
  <ul><h6>StudentService Implementation</h6></ul>
  <img src="assets/studentServiceImpl.PNG"/>
</ul>

<ul><h4>Controllers</h4>
  <ul><h6>Student Controller</h6></ul>
  <img src="assets/studentController1.PNG"/>
  <img src="assets/studentController2.PNG"/>
  <img src="assets/studentController3.PNG"/>
</ul>

<ul><h4>Security</h4>
  <ul><h5>Entities</h5>
  <ul><h6>AppUser</h6></ul>
  <img src="assets/appUser.PNG"/>
  <ul><h6>AppRole</h6></ul>
  <img src="assets/appRole.PNG"/>
  </ul>

  <ul><h5>Repositories</h5>
    <ul><h6>AppUser Repository</h6></ul>
    <img src="assets/appUserRepository.PNG"/>
    <ul><h6>AppRole Repository</h6></ul>
    <img src="assets/appRoleRepository.PNG"/>
  </ul>

  <ul><h5>Services</h5>
    <ul><h6>Security service</h6></ul>
    <img src="assets/securityService.PNG"/>
    <ul><h6>Security service Implementation</h6></ul>
    <img src="assets/securityServiceImpl1.PNG"/>
    <img src="assets/securityServiceImpl2.PNG"/>
    <img src="assets/securityServiceImpl3.PNG"/>
    <ul><h6>UserDetails service Implementation</h6></ul>
    <img src="assets/userDetailsServiceImpl.PNG"/>
  </ul>

  <ul><h5>Controllers</h5>
    <ul><h6>Security Controller</h6></ul>
    <img src="assets/securityController.PNG"/>
  </ul>

  <ul><h5>Configuration</h5>
    <ul><h6>Security Config</h6></ul>
    <img src="assets/securityConfig.PNG"/>
  </ul>
</ul>
</ol>
</details>

<details><summary> <h2>Interfaces</h2> </summary>
<ol>
  <ul><h4>Login page</h4>
    <img src="assets/login.PNG"/>
  </ul>
  <ul><h4>Login en tant que ADMIN</h4>
    <ul><h6>Home</h6></ul>
    <img src="assets/homeAsAdmin.PNG"/>
    <ul><h6>Add Student</h6></ul>
      <img src="assets/addstudent1.PNG"/>
      <img src="assets/addstudent2.PNG"/>
    <ul><h6>Update Student</h6></ul>
      <img src="assets/updateStudent1.PNG"/>
      <img src="assets/updateStudent2.PNG"/>
    <ul><h6>Delete Student</h6></ul>
      <img src="assets/deleteStudent0.PNG"/>
      <img src="assets/deleteStudent.PNG"/>
      <img src="assets/deleteStudent2.PNG"/>
  </ul>
  <ul><h6>Login en tant qu'USER</h6>
    <p>USER ne peut que voir la liste des patients et faire une recherche</p>
    <img src="assets/homeAsUser.PNG"/>
  </ul>
</ol>
</details>
