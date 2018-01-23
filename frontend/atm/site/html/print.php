<?php
error_reporting(E_ALL);
ini_set('display_errors', 0);

include_once("../../../settings.php");

  $curl = curl_init();
  curl_setopt_array($curl, array(
  CURLOPT_PORT => $GLOBALS["port"],
  CURLOPT_URL => $GLOBALS["host"] . "/api/main-service/account/1",
  CURLOPT_RETURNTRANSFER => true,
  CURLOPT_ENCODING => "",
  CURLOPT_MAXREDIRS => 10,
  CURLOPT_TIMEOUT => 30,
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  CURLOPT_CUSTOMREQUEST => "GET",
  CURLOPT_POSTFIELDS => "{
      \"username\":\"" . $_POST['username'] . "\",
      \"password\":\"" . $_POST['password'] . "\"
    }",
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
  //echo $response;
  $data = json_decode($response);

  if (isset($data) && count($data) > 0) {
    // do nothing

  } else {
    session_destroy();
    $error = '<div style="height: 30px; text-align: center;color: red;font-family: Arial; font-size: 10pt;">Wrong credentials. Please try again!</div>';
  }
  }
?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

  </head>

  <body onload="javascript: window.print();">
  <table style="border: 1px solid #777;" border="1">
  <tr>
    <td>Date</td>
    <td>Details</td>
    <td>Value Date</td>
    <td>Debit</td>
    <td>Credit</td>
    <td>Balance</td>
  </tr>

<?php

foreach ($data as $k => $v) {

  $debit = $v->transactionType == 'w' ? $v->transactionAmount * -1 : 0;
  $credit = $v->transactionType == 'd' ? $v->transactionAmount : 0;
  $balance = $balance + $debit + $credit;

  $style = $debit < 0 ? 'style="color: red;"' : 'style="color: green;"';
  $style2 = $debit < 0 ? 'color: red;"' : 'color: green;'; 

  echo '            
    <tr>
    <td ' . $style . '>' . $v->unixTimeStamp . '</td>
    <td ' . $style . '>' . $v->doneBy . ' via ' . strtoupper($v->clientType) . ', Transaction-ID: ' . $v->transactionUUID . '</td>
    <td ' . $style . '>' . $v->unixTimeStamp . '</td>
    <td ' . $style . '>' . $debit . '</td>
    <td ' . $style . '>' . $credit . '</td>
    <td ' . $style . '>' . $balance . '</td>
  </tr>
  ';

}

  echo '</table></body></html>'; 
?>