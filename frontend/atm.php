<?php 
session_start();
error_reporting(E_ALL);
ini_set('display_errors', 1);

if(count($_POST) == 2) {
	$curl = curl_init();

	curl_setopt_array($curl, array(
	CURLOPT_PORT => "8080",
	CURLOPT_URL => "http://localhost:8080/api/main/login",
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
		$_SESSION["logged_in"] = true;
		$_SESSION["user_name"] = $data->name;
		$_SESSION["user_email"] = $data->email;
		$_SESSION["user_address"] = $data->address;

		header("Location: /abc-banking-group-coursework/account.php");
	} else {
		echo "FAILURE";
	}
	}
}

?>



<!DOCTYPE html>
<!-- saved from url=(0078)https://www.imcreator.com/viewer/vbid-3c6e4999-6jjfjyp2/vbid-0b792965-lok1anrm -->
<html xmlns="http://www.w3.org/1999/xhtml" class="gr__imcreator_com"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ABC BANKING GROUP - LOGIN</title>
		<!-- META -->
		
		<meta name="viewport" content="width=device-width, maximum-scale=1"> 
	
	
		<meta name="robots" content="noindex, nofollow">
	
	
	
		<script type="text/javascript" async="" src="./login_files/ga.js.Download"></script><script type="text/javascript" id="www-widgetapi-script" src="./login_files/www-widgetapi.js.Download" async=""></script><script src="./login_files/iframe_api"></script>
		<!-- CSS -->
		<link rel="stylesheet" href="./login_files/common.css">
		<link rel="stylesheet" href="./login_files/sweetalert.css">
		<link rel="stylesheet" type="text/css" href="./login_files/all_css.css">

	
		
			<link rel="icon" type="image/png" href="https://lh3.googleusercontent.com/dd_e0xA19up9208Tv6odcjHEw6z4cKAA6fTgjZ9ynkKoSnr5R4vFxI7gZp6pnZH1Vi5T5f-fphjFsrLG=s30">
			<link rel="apple-touch-icon" href="https://lh3.googleusercontent.com/dd_e0xA19up9208Tv6odcjHEw6z4cKAA6fTgjZ9ynkKoSnr5R4vFxI7gZp6pnZH1Vi5T5f-fphjFsrLG=s30">
		
		
	
		<link rel="stylesheet" type="text/css" href="./login_files/font-awesome.min.css">
	
		<link id="style-0b792965-dzebo07f" rel="stylesheet" type="text/css" href="./login_files/stripe_dynamic_css">
		<link id="style-3c6e4999-autozada" rel="stylesheet" type="text/css" href="./login_files/stripe_dynamic_css(1)">
		<link id="style-1300b5e8-zvbdbml3" rel="stylesheet" type="text/css" href="./login_files/stripe_dynamic_css(2)">
		<link id="style-3c6e4999-clgaknqg" rel="stylesheet" type="text/css" href="./login_files/stripe_dynamic_css(3)">
		<link id="style-3c6e4999-zbawwujg" rel="stylesheet" type="text/css" href="./login_files/stripe_dynamic_css(4)">
	<link id="vbid-0b792965-lok1anrm-STRIPE_DATA" rel="stylesheet" type="text/css" href="./login_files/stripe_data_css"> 
	
		<link id="vbid-3c6e4999-6jjfjyp2-STRIPE_DATA" rel="stylesheet" type="text/css" href="./login_files/stripe_data_css(1)"> 
	
	<link rel="stylesheet" type="text/css" href="./login_files/fonts.css">
		<link rel="stylesheet" type="text/css" href="./login_files/effects.css">
		<link rel="stylesheet" type="text/css" href="./login_files/lightbox.css">
	
	<link rel="stylesheet" type="text/css" href="./login_files/spimeview.css">
	
		
		<!-- SCRIPT -->
		<script src="./login_files/jquery-2.x-git.min.js.Download" type="text/javascript"></script>		
		<script type="text/javascript">IMOS.pageView();</script>
		
<script type="text/javascript">
function submitForm() {
//	alert("form-submitted");
	document.loginForm.submit();
}
</script>


<script type="text/javascript" charset="UTF-8" src="./login_files/common.js.Download"></script><script type="text/javascript" charset="UTF-8" src="./login_files/util.js.Download"></script><script type="text/javascript" charset="UTF-8" src="./login_files/geocoder.js.Download"></script><script type="text/javascript" charset="UTF-8" src="./login_files/stats.js.Download"></script><script type="text/javascript" charset="UTF-8" src="./login_files/AuthenticationService.Authenticate"></script><style id="for-burger">.hamburger-inner:before,.hamburger-inner,.hamburger-inner:after {background-color:rgb(0, 0, 0);}</style></head>

	<body class="fast-animated-bg fixed-bg " data-ecommerce-solution="NONE" data-root-id="vbid-3c6e4999-6jjfjyp2" data-root-style-id="style-3c6e4999-clgaknqg" data-default-currency="USD" data-osid="osid--dd087bc5-0d4d2708" data-app-version="1.4.1g" data-caller="preview" data-ecommerce-dashboard="http://dashboard.shoprocket.co" data-static-server="//www.imcreator.com" data-imos-server="https://imos004-dot-im--os.appspot.com" data-gr-c-s-loaded="true" style="">
	
		<div id="xprs" data-website-name="abc-banking-group-coursework" class="xprs-holder desktop-mode">
			<div class="main-page ">
				<div id="content">
					<div id="vbid-0b792965-lok1anrm" class="master container style-0b792965-dzebo07f content stripes  website-style" data-itemtype="folder" data-creator="" data-itemname="Login" data-itemslug="login" data-itemstyleid="style-0b792965-dzebo07f" data-margintop="" data-arranger="stripes" data-layout="multi" data-vbid="vbid-0b792965-lok1anrm" data-preset-type-id="ITEMS" data-preview-style="style-0b792965-dzebo07f" data-style="style-5a24e-kzdyurm3ju" data-absolute-path="https://www.imcreator.com/viewer" style="visibility: visible;">
	<!-- ARRANGER SETTINGS -->
	<div class="arranger-settings" style="display:none;" data-arranger_type="stripes" data-arranger_cols="0" data-arranger_item_max_width="400" data-arranger_item_min_width="230" data-arranger_item_ratio="1" data-arranger_item_spacing="20" data-arranger_items_per_page="all" data-arranger_order_type="regular" data-auto_play="false" data-auto_play_duration="4" data-flex_element_effect=""></div>
	<div class="layout-settings" style="display:none;" data-type="multi"></div>
	<div id="children">
	
	
	
	<!-- MENUS START -->
		<div id="vbid-3c6e4999-glvf0jbj" class="master item-box force-transparency header-box style-3c6e4999-autozada injected          animated-color" data-holder-type="header" "="" data-child-type="ORIGIN" data-styleid="style-3c6e4999-autozada" data-preview-styleid="style-3c6e4999-autozada" data-preset-type-id="MENUS" data-original-menu-width="386">
			<div id="no-image" class="stripe-background load-high-res "></div>
			<div class="header-wrapper item-wrapper menus-wrapper " style="">
					
					<div class="item-content leaf menu_layout header content" data-preview-style="style-3c6e4999-autozada" data-style="style-7f715-scjjarqgxu" data-behavior-type="NOTHING" data-orig-thumb-height="" data-orig-thumb-width="" data-vbid="vbid-3c6e4999-glvf0jbj" data-bgimg="">
<div class="preview-content-wrapperx">
<div class="preview-content-holder">
	<div class="left-div" data-orig-font-size="14">
	<div class="benet" style="min-height:inherit;"></div>
	<div class="logo-holder">
		<!-- ICON TEMPLATE -->
		
<div class="element-placeholder" data-elementtype="ICON" style="display:none;"></div>

	</div>
	<div class="helper-div">
		<div class="item-details menu">
			<!-- TITLE TEMPLATE -->
			
<div class="preview-title-holder removable-parent">
	
	<a href="https://www.imcreator.com/viewer/vbid-3c6e4999-6jjfjyp2/vbid-3c6e4999-6jjfjyp2" data-link-type="EXISTING" target="_self">
	
		<h2 id="vbid-3c6e4999-mdya6awz" class="preview-element preview-title magic-circle-holder inner-page text-element quick-text-style-menu   allow-mobile-hide" data-menu-name="PREVIEW_TITLE" data-orig-font-size="14" style="font-size: 14px;">ABC BANKING GROUP COURSEWORK</h2>
		</a>
	</div>
	<br>

			<!-- SUBTITLE TEMPLATE -->
			
<div class="preview-subtitle-holder removable-parent">
	
	<h3 id="vbid-3c6e4999-8lfpadri" class="preview-element preview-subtitle magic-circle-holder text-element quick-text-style-menu   allow-mobile-hide" data-menu-name="PREVIEW_SUBTITLE" data-orig-font-size="16">We are the banking</h3>

</div>
<br>

		</div>
		</div>
	</div>
		<div class="right-div">
			<div class="benet" style="min-height:inherit;"></div>
			<!-- LINKS TEMPLATE -->
			<div class="preview-item-links "><?php include_once "menu.php"; ?></div>
			
			
			<button class="hamburger links-menu-btn  small" type="button">
  				<span class="hamburger-box">
   				 <span class="hamburger-inner"></span>
  				</span>
			</button>
			
		
			
		</div>
	</div>
</div>
	<!-- LAYOUT SETTINGS -->
	<div class="layout-settings" style="display:none;" data-type="menu" data-menu_overlay="absolute" data-menu_scroll="true" data-always_minified="false" data-menu_position="top" data-menu_align="left" data-background_color="rgb(255, 255, 255)"></div>
</div>
					
			</div>
		</div>
		<!-- MENUS END -->
		
		
	
	
	<!-- FORM START -->
		<div id="vbid-1300b5e8-b8bbswbh" class="master item-box  page-box style-1300b5e8-zvbdbml3    button-effects btn_hover3      " data-holder-type="page" "="" data-child-type="STYLE" data-styleid="style-1300b5e8-zvbdbml3" data-preview-styleid="style-1300b5e8-zvbdbml3" data-preset-type-id="FORM">
			<div id="no-image" class="stripe-background load-high-res "></div>
			<div class="page-wrapper item-wrapper form-wrapper " style="">
					
					<div class="item-content leaf multi_layout page content -container" data-self="vbid-1300b5e8-b8bbswbh" data-preview-style="style-1300b5e8-zvbdbml3" data-style="style-e4cfb-6huavktiba" data-orig-thumb-height="3551" data-orig-thumb-width="5326" data-vbid="vbid-1300b5e8-b8bbswbh" data-bgimg="https://lh3.googleusercontent.com/uSw4RsXVgMW4bF9wzOSeNowk44nzwNqw6YWs8G04BC4ijXL3H4vg28acPMQEz17TWlJoEgfLpQ9gOrWh">
<div class="multi-container preview image-cover" style="min-height: inherit;">
	<div class="Picture item-preview" style="min-height: inherit;">
		<div class="preview-image-holder">
			<div id="no-image" class="background-image-div preview-element image-source magic-circle-holder unfold-left load-high-res" data-menu-name="BACKGROUND_IMAGE" style="">
			</div>
			<div class="helper-div middle-center">
			<!-- <div class="benet" style="min-height:inherit;"></div> -->
			<div class="pic-side">
				<div class="vertical-aligner">
					<div id="vbid-1300b5e8-hmcgws67-holder" class="preview-image-holder inner-pic-holder" data-menu-name="PREVIEW_INLINE_IMAGE_HOLDER">

	<div id="vbid-1300b5e8-hmcgws67" class="inner-pic preview-element  magic-circle-holder" data-menu-name="PREVIEW_INLINE_IMAGE" style="background-image: url(&quot;https://lh3.googleusercontent.com/uSw4RsXVgMW4bF9wzOSeNowk44nzwNqw6YWs8G04BC4ijXL3H4vg28acPMQEz17TWlJoEgfLpQ9gOrWh=s1600&quot;);" data-orig-width="5326" data-orig-height="3551">
		<!-- VIDEO TEMPLATE -->
		

<div class="element-placeholder" data-elementtype="VIDEO" style="display:none;"></div>


		<!-- MAP TEMPLATE -->
		
<div class="element-placeholder" data-elementtype="MAP" style="display:none;"></div>

		<!-- RAW TEMPLATE -->
		
<div class="element-placeholder" data-elementtype="RAW" style="display:none;"></div>

	</div>
	
</div>
	

				</div>
			</div><div class="text-side shrinker-parent">
					<div class="vertical-aligner">
						<div class="item-details preview-content-wrapper  multi" style="position:relative;">
							<div class="draggable-div-holder" style="margin-left: 0px; margin-top: 15px;"></div>
							<div class="preview-content-holder shrinker-content">				
											




<!--											
<br class="upper-line-break">
<div class="preview-subtitle-holder removable-parent order-handle">
	
	<h3 id="vbid-1300b5e8-dbkw2a9u" class="preview-element preview-subtitle magic-circle-holder text-element quick-text-style-menu   allow-mobile-hide" data-menu-name="PREVIEW_SUBTITLE" data-orig-font-size="12">Please login using your Username and Password</h3>

</div>
<br class="lower-line-break">

<form name="loginForm" target="" method="post">
<br class="upper-line-break">
<div class="preview-form order-handle removable-parent">
	<div class="field-holder">
		<input autocomplete="off" id="username" name="username" class="Field field-mandatory">
	</div>
	</div>
	<br class="lower-line-break">							
											
										
<br class="upper-line-break">
<div class="preview-form order-handle removable-parent">
	<div class="field-holder">
		<input autocomplete="off" id="password" name="password" class="Field field-mandatory" type="password">
	</div>
	</div>
	<br class="lower-line-break">

											
<div class="preview-item-links order-handle removable-parent" style="display:inline-block;">

		<i class="clickable g-recaptcha" data-sitekey="6Lc2KCUUAAAAAGP2G2L0bhHTq_hcnbo_we19MIXA" style="pointer-events: auto; display: block; margin: 5px; box-sizing: border-box;"></i><a class="removable-parent clickable" data-link-type="SUBMIT" data-text="" target="_blank">

	<span id="login-id" onclick="submitForm()">Login &gt;</span>
</a>
</div>
											
	</form>									
-->

<iframe src="http://localhost/atm/site/index.html" width="800px;" height="500px;z-index: 100000;"></iframe>
											
										
									
								
							</div>
						</div>
					</div>
				</div>	
				
			</div>
		</div>
	</div>
</div>
</div>
<!-- LAYOUT SETTINGS -->
<div class="layout-settings" style="display:none;" data-type="multi"></div>
					
			</div>
		</div>
		<!-- FORM END -->
		
	
	
	<!-- FOOTERS START -->
		<div id="vbid-3c6e4999-mru93chc" class="master item-box  gallery-box style-3c6e4999-zbawwujg injected         " data-holder-type="gallery" "="" data-child-type="ORIGIN" data-styleid="style-3c6e4999-zbawwujg" data-preview-styleid="style-3c6e4999-zbawwujg" data-preset-type-id="FOOTERS">
			<div id="no-image" class="stripe-background load-high-res "></div>
			<div class="gallery-wrapper item-wrapper footers-wrapper " style="">
					
					<!-- CACHED VERSION (7) OF vbid-3c6e4999-mru93chc FROM  15-01-2018 13:16:41 --><div class="sub container style-3c6e4999-zbawwujg content flex   " data-itemtype="folder" data-creator="" data-itemname="LOADING Copy" data-itemslug="loading-copy" data-itemstyleid="style-3c6e4999-zbawwujg" data-margintop="" data-arranger="flex" data-layout="multi" data-vbid="vbid-3c6e4999-mru93chc" data-preset-type-id="FOOTERS" data-preview-style="style-3c6e4999-zbawwujg" data-style="style-f3095-j0xhjgzynh" data-absolute-path="" style="overflow: hidden;">
	<!-- ARRANGER SETTINGS -->
	<div class="arranger-settings" style="display:none;" data-arranger_type="flex" data-arranger_cols="0" data-arranger_item_max_width="400" data-arranger_item_min_width="230" data-arranger_item_ratio="1" data-arranger_item_spacing="20" data-arranger_items_per_page="all" data-arranger_order_type="regular" data-auto_play="true" data-auto_play_duration="4" data-flex_element_effect="effect-fadein"></div>
	<div class="layout-settings" style="display:none;" data-type="multi"></div>
	<div id="children">
	
		
		<div id="items-holder-wrapper"><img src="./login_files/6zb8WwTey8418Mlgf5wJomMN_7JjY8T8ULq74HO77G1_wYJQC3o70RMGnRyP_89wZ4jQ93uNwIP82kKRz6AakfxfKA=s50" class="flex-arrows right layer5" style="display: none; float: none; right: 0px; position: absolute;"><img src="./login_files/ZMARmveTg1geksYKXZKdh71KW09XrhDLg8N-XrfXCGsDBEHnuKwhmYpHd55Y2-NwuwLX8qsyx26JNyJWtr1jEcxD=s50" class="flex-arrows left layer5" style="display: none; float: none; left: 0px; position: absolute;">
			<div id="items-holder" style="width: 1519px;">
	
	
	
	
		<div id="vbid-3c6e4999-rr4ycud1" class="sub item-box  page-box style-3c6e4999-zbawwujg           slide-1 play-effect" data-holder-type="page" "="" data-child-type="SLIDE" data-styleid="style-3c6e4999-zbawwujg" data-preview-styleid="style-3c6e4999-zbawwujg" data-preset-type-id="UNRESOLVED" data-page-num="1" data-visible="visible" style="left: 0px; width: 1519px;">
			
			<div class="page-wrapper item-wrapper " style="">
					
					<div class="item-content leaf multi_layout page content -container" data-self="vbid-3c6e4999-rr4ycud1" data-preview-style="style-3c6e4999-zbawwujg" data-style="style-f3095-yq39oaomol" data-orig-thumb-height="" data-orig-thumb-width="" data-vbid="vbid-3c6e4999-rr4ycud1" data-bgimg="">
<div class="multi-container preview image-cover" style="min-height: inherit;">
	<div class="Picture item-preview" style="min-height: inherit;">
		<div class="preview-image-holder">
			<div id="no-image" class="background-image-div preview-element image-source magic-circle-holder unfold-left load-high-res" data-menu-name="BACKGROUND_IMAGE" style="">
			</div>
			<div class="helper-div middle-center">
			<!-- <div class="benet" style="min-height:inherit;"></div> -->
			<div class="pic-side">
				<div class="vertical-aligner">
					<div id="no-image-holder" class="preview-image-holder inner-pic-holder" data-menu-name="PREVIEW_INLINE_IMAGE_HOLDER">

	<div id="no-image" class="inner-pic preview-element  magic-circle-holder  load-high-res " data-menu-name="PREVIEW_INLINE_IMAGE" style="" data-orig-width="" data-orig-height="">
		<!-- VIDEO TEMPLATE -->
		

<div class="element-placeholder" data-elementtype="VIDEO" style="display:none;"></div>


		<!-- MAP TEMPLATE -->
		
<div class="element-placeholder" data-elementtype="MAP" style="display:none;"></div>

		<!-- RAW TEMPLATE -->
		
<div class="element-placeholder" data-elementtype="RAW" style="display:none;"></div>

	</div>
	
</div>
	

				</div>
			</div><div class="text-side shrinker-parent" style="">
					<div class="vertical-aligner">
						<div class="item-details preview-content-wrapper  multi" style="position:relative;">
							<div class="draggable-div-holder" style="margin-left: 0px; margin-top: -129px;"></div>
							<div class="preview-content-holder shrinker-content effect-fadein">
								
									<!--  BY SPECIFIC ORDER -->
									
										
											
												
<br class="upper-line-break">
<div class="preview-title-holder removable-parent order-handle">
	
	<h2 id="vbid-3c6e4999-vcasybbb" class="preview-element preview-title magic-circle-holder inner-page text-element quick-text-style-menu   allow-mobile-hide" data-menu-name="PREVIEW_TITLE" data-orig-font-size="16" style="font-size: 16px;">ABC Banking Group</h2>
	
</div>
<br class="lower-line-break">

											
										
									
										
											
												<div class="preview-item-links order-handle removable-parent" style="display:inline-block;">


</div>
											
										
									
										
											
												
<br class="upper-line-break">
	<div class="preview-social-wrapper removable-parent order-handle">
		<div id="vbid-3c6e4999-wh5nsq1q" class="preview-element preview-social-holder magic-circle-holder" data-menu-name="PREVIEW_SOCIAL" data-theme="6">
			
			<div id="FACEBOOK" class="link-entry " data-menu-name="PREVIEW_SOCIAL" style="display:inline-block;" data-title="FACEBOOK" data-img-url="/images/socialmedia/6facebook.png">
				<a class="social-link-url" href="http://www.facebook.com/" target="_blank"><img class="preview-link-img " src="./login_files/6facebook.png"></a>
			</div>
			
			<div id="TWITTER" class="link-entry " data-menu-name="PREVIEW_SOCIAL" style="display:inline-block;" data-title="TWITTER" data-img-url="/images/socialmedia/6twitter.png">
				<a class="social-link-url" href="http://www.twitter.com/" target="_blank"><img class="preview-link-img " src="./login_files/6twitter.png"></a>
			</div>
			
			<div id="INSTAGRAM" class="link-entry " data-menu-name="PREVIEW_SOCIAL" style="display:inline-block;" data-title="INSTAGRAM" data-img-url="/images/socialmedia/6instagram.png">
				<a class="social-link-url" href="http://www.instagram.com/" target="_blank"><img class="preview-link-img " src="./login_files/6instagram.png"></a>
			</div>
			
		</div> 
	</div>
	<br class="lower-line-break">

											
										
									
								
							</div>
						</div>
					</div>
				</div>	
				
			</div>
		</div>
	</div>
</div>
</div>
<!-- LAYOUT SETTINGS -->
<div class="layout-settings" style="display:none;" data-type="multi"></div>
					
			</div>
		</div>
		
		
		
	
		
			</div>
		<div id="paginator" style="margin-left: -6.5px; display: none; bottom: 50px;"><div id="nav1" class="page-navigator active" data-page-num="1"></div></div></div>
		
		
	
	</div>
</div>
					
			</div>
		</div>
		<!-- FOOTERS END -->
		
		
	
		
	
	</div>
</div>
				</div>
			</div>
		<div class="light-box-wrapper  space-layer" style="display:none;">
	<div class="light-box-image-holder">
		<div class="light-box-image animated">
			
		</div>
		<div class="lightbox-text-wrapper ">
			<div class="lightbox-text-holder animated">
				<div class="lightbox-title"></div>
				<div class="lightbox-subtitle"></div>
				<div id="paginator" style="display:none;">
				</div>
			</div>
		</div>
	</div>
	    <img src="./login_files/EWqW7DEI4kOTRMLjK2-ObFHp-EYBt5apFYZ1LVFAhLtTLjigCRfx5hCCTKbIjIm68VQ00p9twloHJ9w8=s50" class="download-gallery-btn clickable" style="display: none;">
		<img src="./login_files/TgRyMQvJ3_h9RmOnu7AlhIE7NLOOBsRoBounARrs8fQv8HCRPaFtpBneSqJOSZpI6l7He_bAZKN179JBig=s50" class="close-lightbox-btn clickable" style="opacity: 1;">
		<img src="./login_files/43-pXHjwrpmVO8Oean-6BD0uzARvcqUQrpdi7Yw2bxaXwEoP21UdN5kW6Ks9pdOxf7ropMUrh0djgYPwYPU=s50" class="lightbox-arrow lightbox-left clickable top-layer">
		<img src="./login_files/9rwgVnDglPdPFugSu98fhDmxzjXC9KovZ_7BuHkXPIv6jvg9S96flGnhL_e4y8mIpPpZQstfqEV-WitY=s50" class="lightbox-arrow lightbox-right clickable top-layer">
</div>

	</div>
	
	<script src="./login_files/lightbox.js.Download" type="text/javascript"></script>
	<script src="./login_files/spimeengine.js.Download" type="text/javascript"></script>
	
	
	<!--  SHOPROCKET  ECOMMERCE  -->
	<script>
		var JSR = jQuery.noConflict();
		var $ = jQuery.noConflict();
	</script>		
	
	<input type="hidden" name="sr-companyid" id="sr-companyid" value="">
	


	
	<div id="user-pref" style="display:none;" data-lang="en"></div>
	<div style="display:none;" id="lightbox-menus-holder">
		
<div class="login-dialog-wrapper white_content delay-anim" style="text-align:center;">

	<div class="login dialog-holder">
		<div class="login-title"><span class="im-text form-type  t-t">Login</span></div>
		<form id="login-form">
			<img id="user-gravatar" src="./login_files/saved_resource"><br>
			<input name="name" type="text" placeholder="nickname/email" data-placeholder="nickname/email"><br>
			<input name="password" type="password" placeholder="password" data-placeholder="password" autocomplete="off"><br>
			<input id="login-btn" class="clickable" type="button" value="LOGIN">
			<div id="goto-register" class="clickable t-t">Create a new account</div>
			<div id="forgot-password" class="clickable t-t">Forgot Password?</div>
		</form>
	</div>
	
	<div class="register dialog-holder" style="display:none;">
		<div class="login-title"><span class="im-text form-type  t-t">Login</span></div>
		<form id="register-form" class="hide-phone">
			<img id="user-gravatar" src="./login_files/saved_resource"><br>
			<input name="email" type="text" placeholder="email" data-placeholder="email"><br>
			
			<input name="name" type="text" placeholder="nickname" data-placeholder="nickname"><br>
			<input name="password" type="password" placeholder="password" data-placeholder="password" autocomplete="off"><br>
			
			<input id="terms" type="checkbox" checked="checked" disabled=""><span class="form-label" style="line-height: normal;display: inline-block;vertical-align: middle; margin: 10px;"><a href="http://www.imcreator.com/terms-of-service" target="_blank" style="padding:0px;" class="terms-link  t-t">I have read the Terms of Use <br>and the Privacy Policy and accept them</a></span><br>
			<input id="register-btn" class="clickable" type="button" value="JOIN">
			<div id="goto-login" class="clickable t-t">Already a member?</div>
		</form>
	</div>
	
	<div class="forgot-pass dialog-holder" style="display:none;">
		<div class="login-title"><span class="im-text form-type t-t">Login</span></div>
		<form id="forgot-pass-form">
			<img id="user-gravatar" src="./login_files/saved_resource"><br>
			<input name="email" type="text" placeholder="email" data-placeholder="email"><br>
			<input id="forgot-pass-btn" class="clickable" type="button" value="SEND">
			<div id="goto-login" class="clickable t-t">Back</div>
		</form>
	</div>
</div>
	</div>
	<div id="fade" class="black_overlay fadein-noscript" style="display:none;"></div>
	
<div id="UMS_TOOLTIP" style="position: absolute; cursor: pointer; z-index: 2147483647; background: transparent; top: -100000px; left: -100000px;"></div><iframe frameborder="0" allowtransparency="true" src="./login_files/index-6da11f252ec7f2954d8b82138b5a7a14.html" name="stripe_checkout_app" class="stripe_checkout_app" style="z-index: 2147483647; display: none; background: rgba(0, 0, 0, 0.004); border: 0px none transparent; overflow-x: hidden; overflow-y: auto; visibility: visible; margin: 0px; padding: 0px; -webkit-tap-highlight-color: transparent; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%;"></iframe></body><umsdataelement id="UMSSendDataEventElement"></umsdataelement></html>