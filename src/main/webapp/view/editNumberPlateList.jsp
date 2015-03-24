<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<!--[if IE 9]><html class="lt-ie10" lang="en" > <![endif]-->
<html class="no-js" lang="en" >

<head>
	<meta charset="utf-8">
	<!-- If you delete this meta tag World War Z will become a reality -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Cloud1008</title>
	<link rel="icon" type="image/svg+xml" href="images/1oo8.svg">
	
	<!-- If you are using the CSS version, only link these 2 files, you may add app.css to use for your overrides if you like -->
	<link rel="stylesheet" href="css/normalize.css">
	<link rel="stylesheet" href="css/foundation.css">
	
	<!-- If you are using the gem version, you need this only -->
	<!-- <link rel="stylesheet" href="css/app.css">
	-->
	<script src="js/vendor/modernizr.js"></script>
	
	<!-- form and the dropzone.js styles -->
	<link rel="stylesheet" type="text/css" href="css/dropzone.css" />
    <link rel="stylesheet" type="text/css" href="css/uploader.css" />
    <link rel="stylesheet" type="text/css" href="css/font.css" />
</head>
<body>
	<div class="sticky">
	  <!-- body content here -->
	<nav class="top-bar" data-topbar role="navigation" >
	  <ul class="title-area">
	    <li class="name">
	      <h1><a href="#">Cloud1008</a></h1>
	    </li>
	     <!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone -->
	    <li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
	  </ul>
	
	  <section class="top-bar-section">
	    <!-- Right Nav Section -->
	    
	    <ul class="right">
	      <li class="has-dropdown">
	        <a href="#">Edit</a>
	        <ul class="dropdown">
	          <li><a href="http://www.google.com">Delete all</a></li>
	        </ul>
	      </li>
	      <li class="has-dropdown">
	        <a href="#">Account</a>
	        <ul class="dropdown">
	          <li><a href="http://www.google.com">Login in</a></li>
	          <li><a href="#">Sign up</a></li>
	          <li><a href="#">Login out</a></li>
	        </ul>
	      </li>
	    </ul>
	
	    <!-- Left Nav Section -->
	    <ul class="left">
	   	<li class="has-form">
		<div class="row collapse">
		  <div class="large-8 small-9 columns">
		      <input type="text" placeholder="Find Stuff">
		    </div>
		    <div class="large-4 small-3 columns">
		      <a href="#" class="alert button expand">Search</a>
		    </div>
		  </div>
		</li>
		<li class="has-form">
		  <a href="http://foundation.zurb.com/docs" class="button">Delete</a>
		</li>
	    </ul>
	  </section>
	</nav>
	</div>
	<!-- Here is the dropzone.js form -->
	<form action="./add" id="uploader" class="dropzone" method="post" enctype="multipart/form-data">
	<div class="bigplus"></div>

	</form>
	<div class="footer">
		<a href="about.html" data-search="">About</a>
		<a href="blog.html" data-search="">Blog</a>
		<a href="APP.html" data-search="">APP</a>
		<a href="developers.html" data-search="">Developers</a>
		<a href="contact.html" data-search="">Contact</a>
		<img src="images/1oo8.svg" />
	</div>

	<script src="js/vendor/jquery.js"></script>
	<script src="js/vendor/fastclick.js"></script>
	<script src="js/foundation.min.js"></script>
	<script src="script/dropzone.js"></script>
	<script src="script/uploader.js"></script>
</body>
</html>