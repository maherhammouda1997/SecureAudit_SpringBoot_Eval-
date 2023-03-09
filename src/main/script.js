// POST
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
