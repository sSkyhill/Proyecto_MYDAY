<?php

header("Content-Type: application/json");

$data = json_decode(file_get_contents("php://input"), true);

$url = "http://localhost:8080/api-proyecto/rest/usuarios/login";

$options = [
  "http" => [
    "header" => "Content-Type: application/json",
    "method" => "POST",
    "content" => json_encode($data)
  ]
];

echo file_get_contents($url, false, stream_context_create($options));