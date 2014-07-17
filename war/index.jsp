<%@ page language="java" contentType="text/html; charset=Cp1252" pageEncoding="Cp1252"%>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
 	<link href=' http://fonts.googleapis.com/css?family=Vollkorn' rel='stylesheet' type='text/css'>
 	<link type="text/css" rel="stylesheet" href="StarMalaccamax.css">
 	<title>Star Malaccamax</title>
 	
 	    <style type="text/css">
        body { overflow:hidden }
        #loading {
            position: absolute;
            left: 25%;
            top: 20%;
            padding: 2px;
            z-index: 20001;
            height: auto;
        }

        #loading a {
            color: #225588;
        }

        #loading .loadingIndicator {
            background: white;
            font: bold 13px tahoma, arial, helvetica;
            padding: 10px;
            margin: 0;
            height: auto;
            color: #444;
        }

        #loadingMsg {
            font: normal 10px arial, tahoma, sans-serif;
        }
    </style>
	<script type="text/javascript" src="lib/log4js-mini.js"></script>
	<script type="text/javascript" src="lib/modernizr.custom.18579.js"></script>
    <script>
    	if (!Modernizr.webgl) {
    	   alert('Your browser does not support webgl...');
    	}
		var logConsole = new Log(Log.DEBUG, Log.consoleLogger); 
		logConsole.debug("Logger created");
    </script>
<%
   UserService userService = UserServiceFactory.getUserService();
   if (userService.isUserLoggedIn()) {
%>
    <script type="text/javascript">
      var info = { "email" : "<%= userService.getCurrentUser().getEmail() %>" };
    </script>
    
    <!-- SS Library Visualization  -->
    <script type="text/javascript" src="lib/three.r53.js"></script>
	<script type="text/javascript" src="lib/Detector.js"></script>
	<script type="text/javascript" src="lib/Stats.js"></script>
	<script type="text/javascript" src="lib/TrackballControls.js"></script>
	<script type="text/javascript" src="lib/SceneExporter.js"></script>
	<script src="lib/CopyShader.js"></script>
	<script src="lib/FilmShader.js"></script>

	<script src="lib/EffectComposer.js"></script>
	<script src="lib/ShaderPass.js"></script>
	<script src="lib/MaskPass.js"></script>
	<script src="lib/RenderPass.js"></script>
	<script src="lib/FilmPass.js"></script>
	<script type="text/javascript" src="js/ssviz.js"></script>
	<script>
    	var clock = new THREE.Clock();

    	var radius = 6371,
    	tilt = 0.41,
    	rotationSpeed = 0.1,
    	cloudsScale = 1.005,
    	moonScale = 0.23,
    	sunScale = 0.10,
    	container, stats,
    	camera, controls, scene, renderer,
    	geometry, meshPlanet, meshClouds, meshMoon,
    	dirLight, ambientLight, composer;
    	var particleLight, pointLight;
    	var meshPlanets=new Array();
    	var meshPlanetClouds=new Array();
    	var meshMoons=new Array();
    	var anglePlanets=new Array();
    	var angleMoons=new Array();
    	var rotationPlanets=new Array();
    	var rotationMoons=new Array();
    	var mouse = { x: 0, y: 0 };
    	var projector;
    	
    	//height = window.innerHeight,
    	//width  = window.innerWidth,
    </script>
    <!-- SS Library Visualization  -->
  </head>
  <body>
    <!-- OPTIONAL: include this if you want history support -->
    <iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1' style="position:absolute;width:0;height:0;border:0"></iframe>
	<!--add loading indicator while the app is being loaded-->
	<div id="loadingWrapper">
	<div id="loading">
	    <div class="loadingIndicator">
	        <!--<img src="images/pieces/48/cube_green.gif" width="32" height="32" style="margin-right:8px;float:left;vertical-align:top;"/>Star Malaccamax<br/>-->
	        <img src="images/loading.gif" width="16" height="16" style="margin-right:8px;float:left;vertical-align:top;"/>Star Malaccamax 1.0.1.3<br/>
	        <span id="loadingMsg">Loading styles and images...</span></div>
		</div>
	</div>
	
	
	<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = 'Loading Core API...';</script>
	
	<!--include the SC Core API-->
	<script src=starmalaccamax/sc/modules/ISC_Core.js></script>
	
	<!--include SmartClient -->
	<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = 'Loading UI Components...';</script>
	<script src='starmalaccamax/sc/modules/ISC_Foundation.js'></script>
	<script src='starmalaccamax/sc/modules/ISC_Containers.js'></script>
	<script src='starmalaccamax/sc/modules/ISC_Grids.js'></script>
	<script src='starmalaccamax/sc/modules/ISC_Forms.js'></script>
	<script src='starmalaccamax/sc/modules/ISC_RichTextEditor.js'></script>
	<script src='starmalaccamax/sc/modules/ISC_Calendar.js'></script>
	<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = 'Loading Data API...';</script>
	<script src='starmalaccamax/sc/modules/ISC_DataBinding.js'></script>
	<script>
	function readCookie(name) {
		var nameEQ = name + "=";
		var ca = document.cookie.split(';');
		for(var i=0;i < ca.length;i++) {
			var c = ca[i];
			while (c.charAt(0)==' ') c = c.substring(1,c.length);
			if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
		}
		return null;
	}
	
	// Determine what skin file to load
	var currentSkin = readCookie('skin_name_2_4');
	if (currentSkin == null) currentSkin = "Simplicity";
	</script>
	
	<!--load skin-->
	<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = 'Loading Skin...';</script>
	
	<script type="text/javascript">
	document.write("<"+"script src=starmalaccamax/sc/skins/" + currentSkin + "/load_skin.js?isc_version=7.1.js><"+"/script>");
	</script>
	
	<!--include the application JS-->
	<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = 'Loading Application. Please wait...';</script>
	<script type="text/javascript" src="starmalaccamax/starmalaccamax.nocache.js"></script>

<%
   } else {
%>
  </head>
  <body bgcolor="black" text="white">
  	<span class="largeBold">Welcome to Star Malaccamax version 1.0.1.3</span>
  	<div class="storyText">The year is 12752, there are 115,184 solar systems that have been visited through the galaxy.  Ships make their way through jump gates that connect each of the known systems.</div>	
  	<div class="storyText">Your job is to make money.  Most solar systems have planets that support markets.  Trade between the planets can provide a major source of revenue.</div>
  	<%
  		String loginStr = request.getRequestURI();
  		if (request.getQueryString() != null) {
	  		if (request.getQueryString().length() > 0) {
	  			loginStr += "?" + request.getQueryString();
	  		}
	  	}
  	%>
    <a href="<%= userService.createLoginURL(loginStr) %>">Log in</a>
    <br>
    <hr>
    <%=request.getHeader( "User-Agent" )%><br>
    <img src="https://developers.google.com/appengine/images/appengine-silver-120x30.gif" alt="Powered by Google App Engine" />
<%
   }
%>
 </body>
</html>