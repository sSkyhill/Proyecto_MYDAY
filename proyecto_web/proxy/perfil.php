<?php

header("Content-Type: application/json");

$u = $_GET["usuario"];

echo file_get_contents(
  "http://localhost:8080/api-proyecto/rest/publicaciones/" . $u
);