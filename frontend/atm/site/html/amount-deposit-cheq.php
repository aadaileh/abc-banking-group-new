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
    <link rel="stylesheet" type="text/css" href="css/amount.css">
  </head>

  <body>

    <script type="text/javascript">
      var time = 300000; //5 minutes
      var theTimer = setTimeout("document.location.href='../index.php'",time);
    </script>

    <script>
    function myFunction(e) {
        var pass1 = document.getElementById("user_amount").value;


        window.location= "/atm/site/html/deposit-menu-amount-cheq.php?amount=" + pass1

    }

    </script>

    <div class="box">
      <h1>Enter amount :</h1>
      <div class="form">
          <input type="text" placeholder="amount" id = "user_amount" style="color: #777;"></input>

          <div class="buttons">
            <input type="submit" value="Submit" onclick="javascript:myFunction(true)" style="color: #000;">
            <a href="main.html"><input type="button" name="name" value="Back" style="color: #000;"></a>
          </div>

      </div>

    </div>

  </body>
</html>
