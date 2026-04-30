<?php

header("Content-Type: text/plain");

$data = json_decode(file_get_contents("php://input"), true);

$url = "http://localhost:8080/api-proyecto/rest/usuarios";

$options = [
  "http" => [
    "header" =>
      "Content-Type: application/json\r\n" .
      "Accept: text/plain\r\n",
    "method" => "POST",
    "content" => json_encode($data),
    "ignore_errors" => true
  ]
];

echo file_get_contents($url, false, stream_context_create($options));