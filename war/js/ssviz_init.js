if (typeof SSVIZ == 'undefined') {
	SSVIZ = {};
	logConsole.debug("ssviz created");
}

var SSVIZ = SSVIZ || { };

SSVIZ.animationFrameRequestId;
SSVIZ.loadTimer;
SSVIZ.container;
SSVIZ.solarsystem;
SSVIZ.planets;
SSVIZ.stars;
SSVIZ.doRotation = true;
SSVIZ.mouseMoved = false;


SSVIZ.shutdown = function() {
	logConsole.debug("shutdown[]");
	clearInterval(SSVIZ.loadTimer);
	SSVIZ.container.removeEventListener ('mousemove', SSVIZ.onDocumentMouseMove, false);
	window.removeEventListener('resize', SSVIZ.onWindowResize, false);
	window.removeEventListener('DOMMouseScroll', SSVIZ.onDocumentMouseWheel, false);
	window.removeEventListener('mousewheel', SSVIZ.onDocumentMouseWheel, false);

	cancelAnimationFrame(SSVIZ.animationFrameRequestId);
	logConsole.debug("shutdown[] complete");
}


SSVIZ.onDocumentMouseWheel = function ( event ) {
	var delta = (event.wheelDeltaY * 100);
	//logConsole.debug("onDocumentMouseWheel[ " + delta + "]");
	if ((camera.position.z + delta) < 0) {
		return;
	}
	camera.position.z = camera.position.z + delta;
	SSVIZ.mouseMoved = true;
}

SSVIZ.startup = function( elementId, rootHeight, rootWidth ) {
	logConsole.debug("startup[ " + elementId + " ]");
	SSVIZ.loadTimer = setInterval(function() {SSVIZ.startupInit( elementId, rootHeight, rootWidth )}, 3000);
}

SSVIZ.setSolarSystem = function ( jsonObject ) {
	logConsole.debug("setSolarSystem[ " + jsonObject + " ]");
	SSVIZ.solarSystem = JSON.parse( jsonObject );
	
}

SSVIZ.setStars = function ( jsonObject ) {
	logConsole.debug("setStars[ " + jsonObject + " ]");
	SSVIZ.stars = JSON.parse( jsonObject );
	
	var mainStar = SSVIZ.stars[0];
	var starTexture = SSVIZ.getStarTexture(mainStar.spectralType, mainStar.spectralTypeDec);
	var starScale = SSVIZ.getStarScale(mainStar.spectralType, mainStar.spectralTypeDec, mainStar.size);	    		
	var mapA = new THREE.ImageUtils.loadTexture(starTexture);
	
	var sunSprite = new THREE.Sprite({
		map : mapA,
		blending : THREE.AdditiveBlending,
		useScreenCoordinates : false
	});
	//sunSprite.position.x = -radius * 10;
	sunSprite.name = "sunSprite";
	sunSprite.position.x = 0;
	sunSprite.position.y = 0;
	sunSprite.position.z = 0;
	var scaleX = mapA.image.width * starScale;
	var scaleY = mapA.image.height * starScale;
	sunSprite.scale.set( scaleX, scaleY, 1 );
	scene.add(sunSprite);
}


SSVIZ.setAPlanet = function( jsonObject ) {
	var planet = eval('(' + jsonObject + ')');
	
	logConsole.debug(Log.dumpObject(planet));
}

SSVIZ.setAllPlanets = function( jsonArray ) {
	//var planets = eval('(' + jsonArray + ')');
	SSVIZ.planets = JSON.parse(jsonArray);
	if( Object.prototype.toString.call( SSVIZ.planets ) === '[object Array]' ) 
	{ 
		logConsole.debug('GotArray of size ' + SSVIZ.planets.length); 
		//SSVIZ.addMoon(scene, "textures/planets/moon_1024.jpg", 9, radius, geometry, moonScale, "moon_2", 0.1);
		//SSVIZ.addPlanet( scene, radius, 0.1 );
		for (planetIndex = 0; planetIndex < SSVIZ.planets.length; planetIndex++) {
			var planet = SSVIZ.planets[planetIndex];
			logConsole.debug("Got Planet [" + planet.name + "] in orbit [" + planet.orbit +"] and is Gas Giant [" + planet.isGasGiant + "] and is Satellite [" + planet.isSatellite + "]");
			if (!planet.isSatellite) {
				var planetRadius = ((radius * 2) * planet.orbit) * 10;
				SSVIZ.addPlanet( scene, planet.name, planetRadius, planet.orbitRotation, planet.size );
				SSVIZ.addPlanetOrbit( scene, planet.name, planetRadius );
			}
		}
	}
	var clearColor = renderer.getClearColor();
	var clearAlpha = renderer.getClearAlpha();

	//var output = new THREE.SceneExporter().parse( scene, clearColor, clearAlpha );
	//alert(output);
}

SSVIZ.startupInit = function( elementId, rootHeight, rootWidth ) {
	logConsole.debug("startup.init[ " + elementId + ", " + rootHeight + ", " + rootWidth + " ]");
	if ( !Detector.webgl ) {

		Detector.addGetWebGLMessage();
		return;

	}
	SSVIZ.init( elementId, rootHeight, rootWidth );
	SSVIZ.buildScene();
	SSVIZ.animate();
	SSVIZ.addListeners();
	SSVIZ.onWindowResize();
}

SSVIZ.addListeners = function() {
	logConsole.debug("addListeners[]");
	SSVIZ.container.addEventListener( 'mousemove', SSVIZ.onDocumentMouseMove, false );
	window.addEventListener('resize', SSVIZ.onWindowResize, false);
	window.addEventListener('DOMMouseScroll', SSVIZ.onDocumentMouseWheel, false);
	window.addEventListener('mousewheel', SSVIZ.onDocumentMouseWheel, false);

}

SSVIZ.getPos = function( el ) {
    // yay readability
    for (var lx=0, ly=0;
         el != null;
         lx += el.offsetLeft, ly += el.offsetTop, el = el.offsetParent);
    return {x: lx,y: ly};
}

SSVIZ.onDocumentMouseMove = function ( event ) {
	event.preventDefault();
	
	var offsetParent = SSVIZ.getPos(event.target);
	// window offset + titlebar
	var relativeX = event.clientX - offsetParent.x;
	// window offcet + margin
	var relativeY = event.clientY - offsetParent.y;
	
	

	mouse.x = ( relativeX / event.target.clientWidth ) * 2 - 1;
	mouse.y = - ( relativeY / event.target.clientHeight ) * 2 + 1;
	//mouse.x = ( event.clientX / window.innerWidth ) * 2 - 1;
	//mouse.y = - ( event.clientY / window.innerHeight ) * 2 + 1;

	//logConsole.debug("onDocumentMouseMove[" + Log.dumpObject(mouse) + "]");
	SSVIZ.mouseMoved = true;

}

SSVIZ.init = function( elementId, rootHeight, rootWidth ) {
	var width, height;
	logConsole.debug("init[ " + elementId + " ]");
	//window.sayHello("from javascript");
	
	// init base
	//container = document.createElement('div');
	SSVIZ.container = document.getElementById(elementId);
	//document.body.appendChild(container);
	scene = new THREE.Scene();
	renderer = SSVIZ.initRenderer(rootWidth, rootHeight);
	SSVIZ.container.appendChild(renderer.domElement);
	camera = SSVIZ.initCamera(scene, rootWidth, rootHeight, radius);
	camera.name = "rootCamera";
	scene.add(camera);
	controls = SSVIZ.initControls(camera, renderer, radius);
	projector = new THREE.Projector();
	//scene.add( new THREE.AxisHelper() );

	// Directional Light
	dirLight = new THREE.DirectionalLight(0xFFFFFF);
	dirLight.position.set(-1, 0, 1).normalize();
	dirLight.name = "dirLight";
	scene.add(dirLight);

	stats = new Stats();
	stats.domElement.style.position = 'absolute';
	stats.domElement.style.top = '0px';
	stats.domElement.style.zIndex = 100;
	SSVIZ.container.appendChild(stats.domElement);

}


SSVIZ.buildScene = function() {
	logConsole.debug("buildScene[]");
	


	geometry = new THREE.SphereGeometry(radius, 100, 50);
	geometry.computeTangents();

	// sun

	//meshSun = new THREE.Mesh(geometry, materialNormalMap);
	//meshSun.position.set(-radius * 10, 0, 0);
	//meshSun.scale.set(sunScale, sunScale, sunScale);
	//scene.add(meshSun);

//	var sunSprite = new THREE.Sprite({
//		map : THREE.ImageUtils.loadTexture("textures/sun.png"),
//		blending : THREE.AdditiveBlending,
//		useScreenCoordinates : false
//	});
//	sunSprite.position.x = -radius * 10;
//	sunSprite.position.y = 0;
//	sunSprite.position.z = 0;
//	sunSprite.scale = new THREE.Vector3(100, 100, 100);
//	scene.add(sunSprite);
	// planet


	// moon

	//var materialMoon = new THREE.MeshPhongMaterial({
	//	color : 0xffffff,
	//	map : moonTexture
	//});

	// meshMoon = new THREE.Mesh(geometry, materialMoon);
	//meshMoon.position.set(radius * 5, 0, 0);
	//meshMoon.scale.set(moonScale, moonScale, moonScale);
	//scene.add(meshMoon);
	
	//SSVIZ.addMoon(scene, "textures/planets/moon_1024.jpg", 5, radius, geometry, moonScale, "moon_1024", 0.1);
	//SSVIZ.addMoon(scene, "textures/planets/moon1.jpg", 7, radius, geometry, moonScale * 3, "moon1", 0.5);

	// stars

	var i, vector, starsGeometry = new THREE.Geometry();

	for (i = 0; i < 3000; i++) {

		vector = new THREE.Vector3(Math.random() * 2 - 1,
				Math.random() * 2 - 1, Math.random() * 2 - 1);
		vector.multiplyScalar(radius * 10);

		starsGeometry.vertices.push(new THREE.Vertex(vector));

	}

	var stars, starsMaterials = [ new THREE.ParticleBasicMaterial({
		color : 0x555555,
		size : 2,
		sizeAttenuation : false
	}), new THREE.ParticleBasicMaterial({
		color : 0x555555,
		size : 1,
		sizeAttenuation : false
	}), new THREE.ParticleBasicMaterial({
		color : 0x333333,
		size : 2,
		sizeAttenuation : false
	}), new THREE.ParticleBasicMaterial({
		color : 0x3a3a3a,
		size : 1,
		sizeAttenuation : false
	}), new THREE.ParticleBasicMaterial({
		color : 0x1a1a1a,
		size : 2,
		sizeAttenuation : false
	}), new THREE.ParticleBasicMaterial({
		color : 0x1a1a1a,
		size : 1,
		sizeAttenuation : false
	}) ];

	for (i = 10; i < 30; i++) {

		stars = new THREE.ParticleSystem(starsGeometry, starsMaterials[i % 6]);

		stars.rotation.x = Math.random() * 6;
		stars.rotation.y = Math.random() * 6;
		stars.rotation.z = Math.random() * 6;

		var s = i * 10;
		stars.scale.set(s, s, s);

		stars.matrixAutoUpdate = false;
		stars.updateMatrix();
		stars.name = "stars[" + i + "]";

		scene.add(stars);

	}

	//particleLight = new THREE.Mesh(new THREE.SphereGeometry(4, 8, 8),
	//		new THREE.MeshBasicMaterial({
	//			color : 0xffffff
	//		}));
	//particleLight.position.y = 300;
	// scene.addObject( particleLight );

	//var directionalLight = new THREE.DirectionalLight(Math.random() * 0xffffff);
	//directionalLight.position.x = Math.random() - 0.5;
	//directionalLight.position.y = Math.random() - 0.5;
	//directionalLight.position.z = Math.random() - 0.5;
	//directionalLight.position.normalize();

	pointLight = new THREE.PointLight(0xffffff, 1, 1);
	pointLight.name = "pointLight";
	scene.add(pointLight);

}

SSVIZ.addPlanetOrbit = function( scene, name, radius ) {
	
	logConsole.debug("addPlanetOrbit[ " + name + ", " + radius + " ]");
	var material = new THREE.LineBasicMaterial({
        color: 0xffffff,
    });
	
	var orbitGeometry = new THREE.CircleGeometry( radius, 32 );
	var orbitObject = new THREE.Line( orbitGeometry, material );
	orbitObject.rotation.x = 1.57079633;  // 90 degrees	
	orbitObject.name = name + " Orbit";
	scene.add(orbitObject);
}

SSVIZ.addPlanet = function( scene, name, radius, rotation, size ) {
	logConsole.debug("addPlanet[ " + name + ", " + radius + ", " + rotation + ", " + size + " ]");
	var planetTexture = THREE.ImageUtils.loadTexture("textures/planets/third.jpg");
	var cloudsTexture = THREE.ImageUtils.loadTexture("textures/planets/earth_clouds_1024.png");
	var normalTexture = THREE.ImageUtils.loadTexture("textures/planets/earth_normal_2048.jpg");

	var shader = THREE.ShaderUtils.lib["normal"]
	var uniforms = THREE.UniformsUtils.clone(shader.uniforms);

	uniforms["tNormal"].texture = normalTexture;
	uniforms["uNormalScale"].value = 0.85;

	uniforms["tDiffuse"].texture = planetTexture;
	// uniforms[ "tSpecular" ].texture = specularTexture;

	uniforms["enableAO"].value = false;
	uniforms["enableDiffuse"].value = true;
	uniforms["enableSpecular"].value = false;

	uniforms["uDiffuseColor"].value.setHex(0xffffff);
	// uniforms[ "uSpecularColor" ].value.setHex( 0x666666 );
	uniforms["uAmbientColor"].value.setHex(0x000000);

	uniforms["uShininess"].value = 20;

	uniforms["uDiffuseColor"].value.convertGammaToLinear();
	// uniforms[ "uSpecularColor" ].value.convertGammaToLinear();
	uniforms["uAmbientColor"].value.convertGammaToLinear();

	var materialNormalMap = new THREE.ShaderMaterial({
		fragmentShader : shader.fragmentShader,
		vertexShader : shader.vertexShader,
		uniforms : uniforms,
		lights : true
	});
	
	

	meshPlanet = new THREE.Mesh(geometry, materialNormalMap);
	meshPlanet.name = name + " Planet";
	scaleSize = size / 100000;
	meshPlanet.scale.set(scaleSize, scaleSize, scaleSize)
	meshPlanet.rotation.y = 0;
	meshPlanet.rotation.z = tilt;
	meshPlanet.position.x = radius;
	meshPlanet.position.y = 0;
	meshPlanet.position.z = 0;
	scene.add(meshPlanet);
	meshPlanets.push(meshPlanet);
	rotationPlanets.push(rotation / 1000);

	// clouds

	var materialClouds = new THREE.MeshLambertMaterial({
		color : 0xffffff,
		map : cloudsTexture,
		transparent : true
	});

	meshClouds = new THREE.Mesh(geometry, materialClouds);
	meshClouds.name = name + " Clouds";
	meshClouds.scale.set(scaleSize * cloudsScale, scaleSize * cloudsScale, scaleSize * cloudsScale);
	meshClouds.rotation.z = tilt;
	meshClouds.position.x = radius;
	meshClouds.position.y = 0;
	meshClouds.position.z = 0;
	scene.add(meshClouds);
	meshPlanetClouds.push(meshClouds);
}

SSVIZ.addMoon = function(scene, texturePath, radiusModifier, radius, geometry, scale, name, rotation) {
	logConsole.debug("addMoon[ " + texturePath + ", " + radiusModifier + ", " + scale + " ]");
	var moonTexture = THREE.ImageUtils.loadTexture(texturePath);
	
	var materialMoon = new THREE.MeshPhongMaterial({
		color : 0xffffff,
		map : moonTexture
	});

	var meshMoon = new THREE.Mesh(geometry, materialMoon);
	meshMoons.push(meshMoon);
	meshMoon.position.set(radiusModifier * radius, 0, 0);
	meshMoon.scale.set(moonScale, moonScale, moonScale);
	meshMoon.name = name + " Moon";
	rotationMoons.push(rotation);
	scene.add(meshMoon);
	
	
}

SSVIZ.initRenderer = function(width, height) {
	logConsole.debug("initRenderer[ " + width + ", " + height + " ]");
	renderer = new THREE.WebGLRenderer({
		clearAlpha : 1,
		clearColor : 0x000000,
		antialias : true
	});
	renderer.setSize(width, height);
	renderer.sortObjects = false;
	renderer.autoClear = false;
	renderer.gammaInput = true;
	renderer.gammaOutput = true;
	return renderer;
}

SSVIZ.initCamera = function(scene, width, height, radius) {
	logConsole.debug("initCamera[ " + width + ", " + height + ", " + radius
			+ " ]");

	camera = new THREE.PerspectiveCamera(25, width / height, 50, 1e7);
	camera.position.z = radius * 7 * 100;
	return camera;
}

SSVIZ.initControls = function(camera, renderer, radius) {
	logConsole.debug("initControls[]");

	controls = new THREE.TrackballControls(camera, renderer.domElement);

	controls.rotateSpeed = 1.0;
	controls.zoomSpeed = 1.2;
	controls.panSpeed = 0.2;

	controls.noZoom = false;
	controls.noPan = false;

	controls.staticMoving = false;
	controls.dynamicDampingFactor = 0.3;

	controls.minDistance = radius * 1.1;
	controls.maxDistance = radius * 10000;

	controls.keys = [ 65, 83, 68 ]; // [ rotateKey, zoomKey, panKey ]
	return controls;
}

SSVIZ.onWindowResize = function ( event ) {
	logConsole.debug("onWindowResize[]");

	// TODO - change from pulling from window to container
	width = window.innerWidth;
	height = window.innerHeight;

	renderer.domElement.style.top = 0;
	renderer.domElement.style.left = 0;
	renderer.setSize(width, height);

	camera.aspect = width / height;
	camera.updateProjectionMatrix();

	controls.screen.width = width;
	controls.screen.height = height;

	camera.radius = (width + height) / 4;
}


// ------------------------------------------------------------//

