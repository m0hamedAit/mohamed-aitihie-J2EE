# TP6 : Activité Pratique Spring MVC Thymeleaf


<details><summary> <h2>Enoncé</h2> </summary>
Créer une application Web JEE basée sur Spring MVC, Thylemeaf et Spring Data JPA qui permet de gérer les patients. L'application doit permettre les fonctionnalités suivantes :
  - Afficher les patients
  - Faire la pagination
  - Chercher les patients
  - Supprimer un patient
  - Faire des améliorations supplémentaires
</details>

<details><summary> <h2>Conception</h2> </summary>
  <p align="center"><img src="assets/conception.PNG"></p>
</details>

<details><summary> <h2>Captures d'écran</h2> </summary>
<ol>
<ul><h4>Entities</h4>
  <ul><h6>Patient</h6></ul>
  <img src="assets/patient.PNG"/>
</ul>

<ul><h4>Repositories</h4>
  <ul><h6>Patient Repository</h6></ul>
  <img src="assets/patientRepository.PNG"/>
</ul>

<ul><h4>Services</h4>
  <ul><h6>Hopital service Interface</h6></ul>
  <img src="assets/hospitalService.PNG"/>
  <ul><h6>Hopital service Implementation</h6></ul>
  <img src="assets/hospitalServiceImpl.PNG"/>
</ul>

<ul><h4>Controllers</h4>
  <ul><h6>Patient Controller</h6></ul>
  <img src="assets/patientController1.PNG"/>
  <img src="assets/patientController2.PNG"/>
</ul>

<ul><h4>Security</h4>
  <ul><h5>Entities</h5>
  <ul><h6>AppUser</h6></ul>
  <img src="assets/appUser.PNG"/>
  <ul><h6>AppRole : les roles que peut avoir les utilisateurs</h6></ul>
  <img src="assets/appRole.PNG"/>
  </ul>

  <ul><h5>Repositories</h5>
    <ul><h6>AppUser Repository</h6></ul>
    <img src="assets/appUserRepo.PNG"/>
    <ul><h6>AppRole Repository</h6></ul>
    <img src="assets/appRoleRepo.PNG"/>
  </ul>

  <ul><h5>Services</h5>
    <ul><h6>Security service</h6></ul>
    <img src="assets/SecurityService.PNG"/>
    <ul><h6>Security service Implementation</h6></ul>
    <img src="assets/securityServiceImpl1.PNG"/>
    <img src="assets/securityServiceImpl2.PNG"/>
    <ul><h6>UserDetails service Implementation</h6></ul>
    <img src="assets/userDetailsServiceImpl.PNG"/>
  </ul>

  <ul><h5>Controllers</h5>
    <ul><h6>Security Controller</h6></ul>
    <img src="assets/securityController.PNG"/>
  </ul>

  <ul><h5>Configuration</h5>
    <ul><h6>Security Config</h6></ul>
    <img src="assets/securityConf.PNG"/>
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
    <ul><h6>Add patient</h6></ul>
      <img src="assets/addPatient.PNG"/>
      <img src="assets/addPatient2.PNG"/>
    <ul><h6>Update Patient</h6></ul>
      <img src="assets/updatePatient.PNG"/>
      <img src="assets/updatePatient2.PNG"/>
      <img src="assets/updatePatient3.PNG"/>
    <ul><h6>Delete Patient</h6></ul>
      <img src="assets/deletePatient.PNG"/>
      <img src="assets/deletePatient2.PNG"/>
  </ul>
  <ul><h6>Login en tant qu'USER</h6>
    <p>USER ne peut que voir la liste des patients et faire une recherche</p>
    <img src="assets/homeAsUser.PNG"/>
  </ul>

  <h2>Améliorations supplémentaires</h2>
  <ol>
    <ul>Fake data avec Faker</ul>
    <ul>Messages de validation avec Spring boot validation</ul>
    <ul>Page Login personnalisé</ul>
    <ul>Page 403</ul>

  </ol>
</ol>
</details>