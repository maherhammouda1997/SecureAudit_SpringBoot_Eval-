/*
La méthode fetch () permet de récupérer des ressources à travers le réseau de manière asynchrone.
*/

// POST
// On récupère le formulaire par son ID
const form = document.getElementById("post_form_auditeurs");

form.addEventListener("submit", (event) => {
  event.preventDefault();

  const formData = {
    civilite: document.querySelector("input[name=civilite]:checked").value,
    //civilite: document.getElementById("civilite_post").value,
    nom: document.getElementById("nom_post").value,
    prenom: document.getElementById("prenom_post").value,
  };

  console.log(formData);

  let headers = new Headers();
  headers.append("Content-Type", "application/json");

  // Fetch prend en argument le chemin de la ressource
  fetch("http://localhost:8080/auditeur/post", {
    method: "POST",
    headers: headers,
    body: JSON.stringify(formData),
  })
    .then((response) => response.text())
    .then((result) => {
      console.log(result);
    })
    .catch((error) => {
      console.error(error);
    });
});

// GET ALL
document
  .getElementById("get_all_form_auditeurs")
  .addEventListener("submit", function (event) {
    event.preventDefault();
    fetch("http://localhost:8080/auditeur/get")
      .then((response) => response.json())
      .then((data) => console.log(data))
      .catch((error) => console.error(error));
  });

// GET BY ID
document
  .getElementById("get_by_id_form_auditeurs")
  .addEventListener("submit", function (event) {
    event.preventDefault();
    const id_auditeur = document.getElementById(
      "id_auditeur_get_by_id_auditeur"
    ).value;
    fetch(`http://localhost:8080/auditeur/get/${id_auditeur}`)
      .then((response) => response.json())
      .then((data) => console.log(data))
      .catch((error) => console.error(error));
  });

// UPDATE
const formUpdate = document.querySelector("#put_form_auditeurs");
formUpdate.addEventListener("submit", function (event) {
  event.preventDefault();

  const auditeurId = document.querySelector("#id_auditeur_put").value;

  const civilite = document.querySelector("input[name=civilite]:checked").value;
  const nom = document.querySelector("#nom").value;
  const prenom = document.querySelector("#prenom").value;

  const dataUpdated = {
    id_auditeur: auditeurId,
    civilite: civilite,
    nom: nom,
    prenom: prenom,
  };

  let headers = new Headers();
  headers.append("Content-Type", "application/json");
  fetch(`http://localhost:8080/auditeur/put/${auditeurId}`, {
    method: "PUT",
    headers: headers,
    body: JSON.stringify(dataUpdated),
  })
    .then((response) => response.json())
    .then((result) => {
      console.log(result);
    })
    .catch((error) => {
      console.error(error);
    });
});

// DELETE
const formDel = document.getElementById("del_form_auditeurs");

formDel.addEventListener("submit", (event) => {
  event.preventDefault();

  const formData = new FormData(formDel);
  const id_auditeur = formData.get("id_auditeur_del");

  let headers = new Headers();
  headers.append("Content-Type", "application/json");

  fetch(`http://localhost:8080/auditeur/del/${id_auditeur}`, {
    method: "DELETE",
    headers: headers,
  })
    .then((response) => response.text())
    .then((result) => {
      console.log(result);
    })
    .catch((error) => {
      console.error(error);
    });
});
