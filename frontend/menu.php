<?php

error_reporting(E_ALL);
ini_set('display_errors', 0);

include_once("settings.php");

echo '
<ul class="preview-links-wrapper">

		<li class="removable-parent">
		
			<a class="removable-parent" href="home.php" data-link-type="EXISTING" target="_self">
			<span id="vbid-6a196-4jtkgmay" class="preview-element Link item-link magic-circle-holder text-element custom" data-menu-name="PREVIEW_LINK">Home</span>
			</a>
	
		</li>
';


if ($_SESSION["logged_in"] == 1) {

	echo '
		<li class="removable-parent">
		
			<a class="removable-parent" href="account.php" data-link-type="EXISTING" target="_self">
			<span id="vbid-6a196-4jtkgmay" class="preview-element Link item-link magic-circle-holder text-element custom" data-menu-name="PREVIEW_LINK">Account</span>
			</a>
	
		</li>

		<li class="removable-parent">
		
			<a class="removable-parent" href="fund-transfer.php" data-link-type="EXISTING" target="_self">
			<span id="vbid-6a196-4jtkgmay" class="preview-element Link item-link magic-circle-holder text-element custom" data-menu-name="PREVIEW_LINK">Fund Transfer</span>
			</a>
	
		</li>

		<li class="removable-parent">
		
			<a class="removable-parent" href="atm.php" data-link-type="EXISTING" target="_self">
			<span id="vbid-6a196-4jtkgmay" class="preview-element Link item-link magic-circle-holder text-element custom" data-menu-name="PREVIEW_LINK">ATM</span>
			</a>
	
		</li>		
	';	

} else {

	echo '
		<li class="removable-parent">
		
			<a class="removable-parent" href="login.php" data-link-type="EXISTING" target="_self">
			<span id="vbid-6a196-4jtkgmay" class="preview-element Link item-link magic-circle-holder text-element custom" data-menu-name="PREVIEW_LINK">Account</span>
			</a>
	
		</li>

		<li class="removable-parent">
		
			<a class="removable-parent" href="login.php" data-link-type="EXISTING" target="_self">
			<span id="vbid-6a196-4jtkgmay" class="preview-element Link item-link magic-circle-holder text-element custom" data-menu-name="PREVIEW_LINK">Fund Transfer</span>
			</a>
	
		</li>

		<li class="removable-parent">
		
			<a class="removable-parent" href="login.php" data-link-type="EXISTING" target="_self">
			<span id="vbid-6a196-4jtkgmay" class="preview-element Link item-link magic-circle-holder text-element custom" data-menu-name="PREVIEW_LINK">ATM</span>
			</a>
	
		</li>		
	';
}


echo '
		
		<li class="removable-parent">
		
			<a class="removable-parent" href="team.php" data-link-type="EXISTING" target="_self">
		
			<span id="vbid-6a196-b83lhbhb" class="preview-element Link item-link magic-circle-holder text-element custom" data-menu-name="PREVIEW_LINK">Our Team</span>
			</a>
	
		</li>
		
		<li class="removable-parent">
		
			<a class="removable-parent" href="contact.php" data-link-type="EXISTING" target="_self">
		
			<span id="vbid-6a196-jul4qep4" class="preview-element Link item-link magic-circle-holder text-element custom" data-menu-name="PREVIEW_LINK">Contact</span>
			</a>
	
		</li>';

	if ($_SESSION["logged_in"] == 1) {

	echo '
		<li class="removable-parent">
		
			<a class="removable-parent" href="logout.php" data-link-type="EXISTING" target="_self">
		
			<span id="vbid-6a196-wngvd8j3" class="preview-element Link item-link magic-circle-holder text-element custom" data-menu-name="PREVIEW_LINK">Logout</span>
			</a>
	
		</li>
';

} else {
	echo '

		<li class="removable-parent">
		
			<a class="removable-parent" href="login.php" data-link-type="EXISTING" target="_self">
		
			<span id="vbid-6a196-wngvd8j3" class="preview-element Link item-link magic-circle-holder text-element custom" data-menu-name="PREVIEW_LINK">Login</span>
			</a>
	
		</li>
	';
}

echo '
<div class="element-placeholder" data-elementtype="LINK" style="display:none;"></div>
	
	</ul>
';
?>