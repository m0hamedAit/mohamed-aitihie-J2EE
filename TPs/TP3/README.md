# TP3 : Mapping objet relationnel avec JPA, Hibernate et Spring Data : Cas de Patient, Medecin, Rendez-vous, Consultation

<details><summary> <h2>Introduction</h2> </summary>
<b>ORM (Object Relational Mapping)</b> est un système qui met en œuvre la responsabilité du mappage de l'objet au modèle relationnel. Cela signifie qu'il est responsable du stockage des données du modèle objet dans le modèle relationnel et de la lecture des données du modèle relationnel dans le modèle objet.<br>
<b>JPA :</b> (Java persistence api) qui fournit des spécifications pour la persistance, la lecture et la gestion des données de votre objet Java vers des relations dans la base de données.<br>
<b>Hibernate :</b> Il existe plusieurs frameworks qui mettent en œuvre JPA. Hibernate est l'un d'entre eux. Il existe également d'autres frameworks. Mais si vous utilisez jpa avec spring, cela vous permet de passer à différents frameworks à l'avenir.<br>
<b>Spring Data JPA :</b> C'est une autre couche au-dessus de jpa que Spring fournit pour vous faciliter la vie.
</details>


<details><summary> <h2>Enoncé</h2> </summary>
Reprendre les exemples de mapping objet relationnel avec JPA, Hibernate et Spring Data :<br>
- Cas  de Patient, Medecin, Rendez-vous, Consultation
</details>

<details><summary> <h2>Conception</h2> </summary>
  <p align="center"><img src="assets/conception.PNG"></p>
</details>

<details><summary> <h2>Captures d'écran</h2> </summary>
<ol>
<ul><h4>Entities</h4>
  <ul><h6>Consultation</h6></ul>
  <img src="assets/consultation.PNG"/>
  <ul><h6>Medecin</h6></ul>
  <img src="assets/medecin.PNG"/>
  <ul><h6>Patient</h6></ul>
  <img src="assets/patient.PNG"/>
  <ul><h6>RendezVous</h6></ul>
  <img src="assets/rendezVous.PNG"/>
  <ul><h6>StatusRDV</h6></ul>
  <img src="assets/statusRDV.PNG"/>
</ul>

<ul><h4>Repositories</h4>
  <ul><h6>Consultation Repository</h6></ul>
  <img src="assets/consultationRepo.PNG"/>
  <ul><h6>Medecin Repository</h6></ul>
  <img src="assets/medecinRepo.PNG"/>
  <ul><h6>Patient Repository</h6></ul>
  <img src="assets/patientRepo.PNG"/>
  <ul><h6>RendezVous Repository</h6></ul>
  <img src="assets/rdvRepo.PNG"/>
</ul>

<ul><h4>Services</h4>
  <ul><h6>Hopital service Interface</h6></ul>
  <img src="assets/IService.PNG"/>
  <ul><h6>Hopital service Implementation</h6></ul>
  <img src="assets/serviceIMPL1.PNG"/>
  <img src="assets/serviceIMPL2.PNG"/>
</ul>

<ul><h4>Controllers</h4>
  <ul><h6>Rest Contoller</h6></ul>
  <img src="assets/restController.PNG"/>
</ul>

<ul><h4>Application</h4>
  <img src="assets/Application1.PNG"/>
  <img src="assets/Application2.PNG"/>
</ul>

<ul><h4></h4>
  <img src="assets/patients.PNG"/>
</ul>

</ol>
</details>
