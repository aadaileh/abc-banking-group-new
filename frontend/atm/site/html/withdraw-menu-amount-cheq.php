<?php 
error_reporting(E_ALL);
ini_set('display_errors', 0);

session_start();

include_once("../../../settings.php");

  
if(count($_GET) > 0) {
  $curl = curl_init();

  curl_setopt_array($curl, array(
  CURLOPT_PORT => $GLOBALS["port"],
  CURLOPT_URL => $GLOBALS["host"] . "/api/main-service/withdraw",
  CURLOPT_RETURNTRANSFER => true,
  CURLOPT_ENCODING => "",
  CURLOPT_MAXREDIRS => 10,
  CURLOPT_TIMEOUT => 30,
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  CURLOPT_CUSTOMREQUEST => "PUT",
  CURLOPT_POSTFIELDS => "{
      \"clientId\":\"1\",
      \"transactionType\":\"w\",
      \"clientType\":\"atm\",
      \"iban\":\"UK12345ertz567678\",
      \"swift\":\"JDHUIEJDM\",
      \"amount\":\"" . $_GET['amount'] . "\",
      \"beneficiaryFullName\":\"Ahmed Al-Adaileh\"
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

  echo "<pre>data:";
  print_r($data);
  echo "</pre>";

  if ($data->results == true) {
    // do nothing
    echo $message = "Fund transfer is accomplished correctly. Plese check your account to see this action in your transactions list";
    //header("Location: /account.php");
  } else {
    echo "FAILURE";
  }
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

    <!-- Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Css -->
    <link href='https://fonts.googleapis.com/css?family=Karla:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="css/deposit-menu-amount.css">
  </head>

  <script type="text/javascript">
    var time = 300000; //5 minutes
    var theTimer = setTimeout("document.location.href='../index.php'",time);
  </script>


  <body>
    <ul class="col-md-12" id="navbar">
      <div class="bar" id="bar1">
        <a class="links" href="?amount=20"><span></span></a>
        <li class="col-md-12"><a href="#">£20</a><span class="icon glyphicon glyphicon-chevron-right"></span></li>
      </div>
      <div class="bar" id="bar2">
        <a class="links" href="?amount=40"><span></span></a>
        <li class="col-md-12"><a href="#">£40</a><span class="icon glyphicon glyphicon-chevron-right"></span></li>
      </div>
      <div class="bar" id="bar3">
        <a class="links" href="?amount=60"><span></span></a>
        <li class="col-md-12"><a href="#">£60</a><span class="icon glyphicon glyphicon-chevron-right"></span></li>
      </div>
      <div class="bar" id="bar4">
        <a class="links" href="amount-withdraw-cheq.php"><span></span></a>
        <li class="col-md-12"><a href="#">Custom</a><span class="icon glyphicon glyphicon glyphicon-edit"></span></li>
      </div>
      <div class="bar" id="bar5">
        <a class="links" href="main.html"><span></span></a>
        <li class="col-md-12"><a href="#">Back</a><span class="icon glyphicon glyphicon-arrow-left"></span></li>
      </div>


    </ul>


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
