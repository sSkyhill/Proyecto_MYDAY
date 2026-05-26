let user = "";

// ELEMENTOS
const loginDiv = document.getElementById("login");
const app = document.getElementById("app");

const feed = document.getElementById("feed");
const perfil = document.getElementById("perfil");
const subirDiv = document.getElementById("subir");

const usuario = document.getElementById("usuario");
const contrasena = document.getElementById("contrasena");

const nombrePerfil = document.getElementById("nombrePerfil");

const texto = document.getElementById("texto");
const imagen = document.getElementById("imagen");

const misFotos = document.getElementById("misFotos");


// ================= RESTAURAR SESIÓN =================
window.addEventListener("load", () => {

  const savedUser = localStorage.getItem("user");

  if (savedUser) {

    user = savedUser;

    loginDiv.style.display = "none";
    app.style.display = "block";

    nombrePerfil.innerText = user;

    cargarFeed();
    cargarPerfil();
  }
});


// ================= LOGIN =================
async function hacerLogin() {

  const res = await fetch("proxy/login.php", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      nombreUsuario: usuario.value,
      contrasena: contrasena.value
    })
  });

  const data = await res.text();

  if (data === "OK") {

    user = usuario.value;

    localStorage.setItem("user", user); // 🔥 guardar sesión

    loginDiv.style.display = "none";
    app.style.display = "block";

    nombrePerfil.innerText = user;

    cargarFeed();
    cargarPerfil();

  } else {
    alert("Login incorrecto");
  }
}


// ================= LOGOUT =================
function cerrarSesion() {

  localStorage.removeItem("user");

  user = "";

  app.style.display = "none";
  loginDiv.style.display = "block";

  usuario.value = "";
  contrasena.value = "";
}


// ================= VISTAS =================
function mostrar(id) {

  feed.style.display = "none";
  subirDiv.style.display = "none";
  perfil.style.display = "none";

  document.getElementById(id).style.display = "block";
}


// ================= FEED =================
async function cargarFeed() {

  const res = await fetch("proxy/feed.php");
  const data = await res.json();

  feed.innerHTML = "";

  const hoy = new Date().toISOString().split("T")[0];

  data.forEach(p => {

    const fecha = (p.fechaImagen || "").split(" ")[0];

    if (fecha === hoy) {

      feed.innerHTML += `
        <div class="post">
          <h4>${p.nombreUsuario}</h4>
          <p>${p.comentario}</p>

          <img src="data:image/jpeg;base64,${p.imagenBase64}">

          <small>${p.fechaImagen}</small>
        </div>
      `;
    }
  });
}


// ================= PERFIL =================
async function cargarPerfil() {

  const res = await fetch("proxy/feed.php");
  const data = await res.json();

  misFotos.innerHTML = "";

  data
    .filter(p => p.nombreUsuario === user)
    .forEach(p => {

      misFotos.innerHTML += `
        <div class="perfil-post">
          <img src="data:image/jpeg;base64,${p.imagenBase64}">
        </div>
      `;
    });
}


// ================= SUBIR =================
async function subirPost() {

  const file = imagen.files[0];

  if (!file) {
    alert("Selecciona una imagen");
    return;
  }

  const reader = new FileReader();

  reader.onloadend = async function () {

    const base64 = reader.result.split(",")[1];

    const res = await fetch("proxy/subir.php", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        nombreUsuario: user,
        comentario: texto.value,
        fechaImagen: new Date().toISOString().replace("T", " ").split(".")[0],
        imagenBase64: base64
      })
    });

    await res.text();

    texto.value = "";
    imagen.value = "";

    cargarFeed();
    cargarPerfil();
  };

  reader.readAsDataURL(file);
}


// ================= REGISTRO =================
async function registrar() {

  const userReg = document.getElementById("reg_usuario").value;
  const email = document.getElementById("reg_email").value;
  const pass1 = document.getElementById("reg_pass").value;
  const pass2 = document.getElementById("reg_pass2").value;

  if (!userReg || !email || !pass1 || !pass2) {
    alert("Rellena todos los campos");
    return;
  }

  if (pass1 !== pass2) {
    alert("Las contraseñas no coinciden");
    return;
  }

  const res = await fetch("proxy/usuarios.php", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      nombreUsuario: userReg,
      email: email,
      contrasena: pass1
    })
  });

  const text = await res.text();

  if (text.includes("Usuario creado")) {
    alert("Usuario registrado correctamente");
    mostrarLogin();
  } else {
    alert("Error: " + text);
  }
}


// ================= UI LOGIN/REGISTER =================
function mostrarRegistro() {
  document.getElementById("loginForm").style.display = "none";
  document.getElementById("registerForm").style.display = "block";
}

function mostrarLogin() {
  document.getElementById("loginForm").style.display = "block";
  document.getElementById("registerForm").style.display = "none";
}