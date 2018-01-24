<?php 

error_reporting(E_ALL);
ini_set('display_errors', 1);

#warm-harbor
$curl_warm_harbor = curl_init();
curl_setopt_array($curl_warm_harbor, array(
CURLOPT_PORT => "443",
CURLOPT_URL => "https://warm-harbor-89034.herokuapp.com/api/main-service/login",
CURLOPT_RETURNTRANSFER => true,
CURLOPT_ENCODING => "",
CURLOPT_MAXREDIRS => 10,
CURLOPT_TIMEOUT => 30,
CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
CURLOPT_CUSTOMREQUEST => "POST",
CURLOPT_POSTFIELDS => "{\"username\":\"aadaileh\",\"password\":\"pass\"}",
CURLOPT_HTTPHEADER => array("authorization: Basic YXBpdXNlcjpwYXNz","content-type: application/json"),));
$response_warm_harbor = curl_exec($curl_warm_harbor);
$err_warm_harbor = curl_error($curl_warm_harbor);
curl_close($curl_warm_harbor);

echo "<pre>curl results (warm_harbor):";
print_r($response_warm_harbor);
print_r($err_warm_harbor);
echo "</pre>";

echo "Now sleep for 3 minutes, to free MySql resources...".
sleep(180);

#calm-sea
$curl_calm_sea = curl_init();
curl_setopt_array($curl_calm_sea, array(
CURLOPT_PORT => "443",
CURLOPT_URL => "https://calm-sea-95089.herokuapp.com/api/main-service/login",
CURLOPT_RETURNTRANSFER => true,
CURLOPT_ENCODING => "",
CURLOPT_MAXREDIRS => 10,
CURLOPT_TIMEOUT => 30,
CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
CURLOPT_CUSTOMREQUEST => "POST",
CURLOPT_POSTFIELDS => "{\"username\":\"aadaileh\",\"password\":\"pass\"}",
CURLOPT_HTTPHEADER => array("authorization: Basic YXBpdXNlcjpwYXNz","content-type: application/json"),));
$response_calm_sea = curl_exec($curl_calm_sea);
$err_calm_sea = curl_error($curl_calm_sea);
curl_close($curl_calm_sea);

echo "<pre>curl results (calm_sea):";
print_r($response_calm_sea);
print_r($err_calm_sea);
echo "</pre>";
?>