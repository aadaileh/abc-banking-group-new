<?php

$curl = curl_init();

curl_setopt_array($curl, array(
  CURLOPT_PORT => "443",
  CURLOPT_URL => "https://warm-harbor-89034.herokuapp.com/db",
  CURLOPT_RETURNTRANSFER => true,
  CURLOPT_ENCODING => "",
  CURLOPT_MAXREDIRS => 10,
  CURLOPT_TIMEOUT => 30,
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  CURLOPT_CUSTOMREQUEST => "POST",
  CURLOPT_POSTFIELDS => "{  \n\t\"mail\":\"test@test.de\",\n   \"password\":\"password\",\n   \"lastName\":\"lastName\",\n   \"name\":\"name\",\n   \"address\":\"somewhere\"\n}",
  CURLOPT_COOKIE => "JSESSIONID=0DDBAD1D0FE43DA1018C57CE81707920",
  CURLOPT_HTTPHEADER => array(
    "authorization: Basic YXBpdXNlcjpwYXNz",
    "content-type: application/json"
  ),
));

$response = curl_exec($curl);
$err = curl_error($curl);

curl_close($curl);

if ($err) {
  echo "cURL Error #:" . $err;
} else {
  echo $response;
}