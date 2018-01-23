<?php 

session_start();
error_reporting(E_ALL);
ini_set('display_errors', 1);


  // echo "<pre>_POST:";
  // print_r($_POST);
  // echo "</pre>";

include_once("../../settings.php");

if(count($_POST) == 2) {
  $curl = curl_init();

  curl_setopt_array($curl, array(
  CURLOPT_PORT => $GLOBALS["port"],
  CURLOPT_URL => $GLOBALS["host"] . "/api/main-service/login",
  CURLOPT_RETURNTRANSFER => true,
  CURLOPT_ENCODING => "",
  CURLOPT_MAXREDIRS => 10,
  CURLOPT_TIMEOUT => 30,
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  CURLOPT_CUSTOMREQUEST => "POST",
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

  echo "<pre>data:";
  print_r($data);
  echo "</pre>";

  if ($data->loggedIn) {
    $_SESSION["client_id"] = $data->clientId;
    $_SESSION["logged_in"] = true;
    $_SESSION["user_name"] = $data->name;
    $_SESSION["user_email"] = $data->email;
    $_SESSION["user_address"] = $data->address;

    header("Location: /atm/site/html/main.html");
  } else {
    session_destroy();
    $error = '<div style="height: 30px; text-align: center;color: red;font-family: Arial; font-size: 10pt;">Wrong credentials. Please try again!</div>';
  }
  }
}

?>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Css -->
    <link href='https://fonts.googleapis.com/css?family=Karla:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="html/css/login.css">
  </head>

  <body>

    <script>
    function submitForm() {

      document.getElementById("loginForm").submit();

    }

    </script>

    <form id="loginForm" name="loginForm" target="" method="POST" action="">
    <div class="box">
      <h1>ATM Login</h1>
      <div class="form">
          <input type="text" placeholder="Card Number" id = "user" style="font-size:bold; color: #777;" name="username">
          <input type="password" placeholder="PIN Code" id = "pass" style="font-size:bold; color: #777;" name="password">
      </div>



      <div class="button">
          <span class="glyphicon glyphicon glyphicon-chevron-right" onclick="javascript:submitForm()"</span>
      </div>

    </div>
</form>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>

  </body>
</html>
