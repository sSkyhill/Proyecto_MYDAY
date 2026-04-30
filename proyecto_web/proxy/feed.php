<?php

header("Content-Type: application/json");

echo file_get_contents(
  "http://localhost:8080/api-proyecto/rest/publicaciones"
);