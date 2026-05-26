# MY.DAY

> Tu día, una foto, un recuerdo para siempre.

---

#  Descripción

**MY.DAY** es una aplicación multiplataforma disponible en **web**, **escritorio** y **móvil**, diseñada para compartir momentos diarios mediante fotografías.

Cada usuario puede subir imágenes que estarán visibles únicamente durante **24 horas** en el muro principal (*feed*). Una vez transcurrido ese tiempo, las publicaciones desaparecen del feed público y pasan a formar parte del perfil privado del usuario, funcionando como un diario personal visual.

La aplicación combina la experiencia de una red social temporal con la privacidad y organización de un diario digital.

---


# Arquitectura del proyecto

La aplicación está dividida en varias partes conectadas entre sí mediante una API RESTful desarrollada en Java.

## Backend API

La API se encarga de:

- Gestión de usuarios
- Inicio de sesión y autenticación
- Gestión de publicaciones
- Envío y recepción de imágenes
- Control de publicaciones temporales
- Comunicación entre plataformas

### Tecnologías utilizadas

- Java
- RESTful API
- Apache Tomcat
- Maven

---

# 🌐 Aplicación Web

Versión accesible desde navegador enfocada en una experiencia visual sencilla y dinámica.

## Tecnologías utilizadas

- HTML
- CSS
- JavaScript
- PHP

## Funcionalidades

- Visualización del feed diario
- Registro e inicio de sesión
- Subida de imágenes
- Visualización del perfil del usuario

---

# 🖥️ Aplicación de Escritorio

Aplicación desarrollada para entorno Windows.

## Tecnologías utilizadas

- C#
- Windows Forms
- Visual Studio

## Funcionalidades

- Inicio de sesión y registro
- Visualización del feed
- Subida de fotografías
- Gestión del perfil personal

---

# 📱 Aplicación Móvil

Aplicación Android diseñada para compartir momentos diarios desde cualquier lugar.

## Tecnologías utilizadas

- Android Studio
- Java

## Funcionalidades

- Captura de fotografías desde cámara
- Subida de imágenes desde galería
- Feed diario
- Perfil del usuario
- Inicio de sesión y registro

---

# Vistas principales

## 📰 Feed Principal

Muestra únicamente las publicaciones realizadas durante las últimas 24 horas.

### Características

- Actualización dinámica
- Visualización de publicaciones recientes
- Contenido temporal

---

##  Subida de Fotos 📸

Permite:

- Tomar fotografías desde cámara
- Seleccionar imágenes desde el dispositivo
- Publicar contenido diario

Dependiendo de la plataforma:

- En móvil se puede utilizar la cámara directamente
- En web y escritorio se pueden subir archivos locales

---

## 👤 Perfil

Cada usuario dispone de un perfil personal donde:

- Puede ver todas sus publicaciones anteriores
- Las fotos permanecen guardadas como diario privado
- El contenido ya no aparece en el feed público al pasar las 24 horas.

---

# Sistema de autenticación

La aplicación incluye:

- Registro de usuarios
- Inicio de sesión
- Validación de campos
- Comprobación de credenciales
- Gestión básica de usuarios

---

# ⚙️ Tecnologías utilizadas

| Área | Tecnologías |
|------|-------------|
| Backend | Java RESTful API, Apache Tomcat, Maven |
| Web | HTML, CSS, JavaScript, PHP |
| Escritorio | C#, Windows Forms, Visual Studio |
| Móvil | Java, Android Studio |

---

# Estructura general del proyecto

```bash
MY.DAY/
│
├── backend-api/
│   ├── src/
│   ├── pom.xml
│   └── ...
│
├── web-app/
│   ├── html/
│   ├── css/
│   ├── js/
│   ├── php/
│   └── ...
│
├── desktop-app/
│   ├── forms/
│   ├── resources/
│   └── ...
│
├── mobile-app/
│   ├── activities/
│   ├── layouts/
│   └── ...
│
└── README.md
```

---

# Objetivo del proyecto

El objetivo de **MY.DAY** es ofrecer una forma sencilla y personal de compartir recuerdos diarios, manteniendo la inmediatez de las redes sociales temporales mientras se conserva un historial privado para cada usuario.

---

# Autor

Proyecto desarrollado por **Alberto Carril Saura**.

---
