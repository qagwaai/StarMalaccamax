/**
 * @fileOverview Solar System Visualization
 * @author Peter Girard
 * @version 1.0
 */

if (typeof SSVIZ == 'undefined') {
	SSVIZ = {};
	logConsole.debug( "ssviz created" );
}

/* region Variable Initialization */
var SSVIZ = SSVIZ || {};

SSVIZ.animationFrameRequestId;
SSVIZ.loadTimer;
SSVIZ.container;
SSVIZ.solarsystem;
SSVIZ.planets;
SSVIZ.stars;
SSVIZ.doRotation = false;
SSVIZ.mouseMoved = false;
/* endregion Variable Initialization */

/* region startup */

/**
 * 
 * @param elementId
 * @param rootHeight
 * @param rootWidth
 */
SSVIZ.startupInit = function( elementId, rootHeight, rootWidth ) {
	logConsole.debug( "startup.init[ " + elementId + ", " + rootHeight + ", " + rootWidth + " ]" );
	if (!Detector.webgl) {

		Detector.addGetWebGLMessage();
		return;

	}
	SSVIZ.init( elementId, rootHeight, rootWidth );
	SSVIZ.buildScene();
	SSVIZ.animate();
	SSVIZ.addListeners();
	SSVIZ.onWindowResize();
};

/**
 * 
 * @param elementId
 * @param rootHeight
 * @param rootWidth
 */
SSVIZ.startup = function( elementId, rootHeight, rootWidth ) {
	logConsole.debug( "startup[ " + elementId + " ]" );
	SSVIZ.loadTimer = setInterval( function() {
		SSVIZ.startupInit( elementId, rootHeight, rootWidth );
	}, 3000 );
};

/**
 * @param elementId
 * @param rootHeight
 * @param rootWidth
 */
SSVIZ.init = function( elementId, rootHeight, rootWidth ) {
	// var width, height;
	logConsole.debug( "init[ " + elementId + " ]" );
	// window.sayHello("from javascript");

	// init base
	// container = document.createElement('div');
	SSVIZ.container = document.getElementById( elementId );
	// document.body.appendChild(container);
	scene = new THREE.Scene();
	renderer = SSVIZ.initRenderer( rootWidth, rootHeight );
	SSVIZ.container.appendChild( renderer.domElement );
	camera = SSVIZ.initCamera( scene, rootWidth, rootHeight, radius );
	camera.name = "rootCamera";
	scene.add( camera );
	controls = SSVIZ.initControls( camera, renderer, radius );
	projector = new THREE.Projector();
	// scene.add( new THREE.AxisHelper() );
	ambientLight = new THREE.AmbientLight( 0x000000 );
	scene.add( ambientLight );

	// Directional Light
	dirLight = new THREE.DirectionalLight( 0xFFFFFF );
	dirLight.position.set( -1, 0, 1 ).normalize();
	dirLight.name = "dirLight";
	//dirLight.shadowCameraFar = camera.far;
	//scene.add( dirLight );

	stats = new Stats();
	stats.domElement.style.position = 'absolute';
	stats.domElement.style.top = '0px';
	stats.domElement.style.zIndex = 100;
	SSVIZ.container.appendChild( stats.domElement );

	
	// postprocessing

	var renderModel = new THREE.RenderPass( scene, camera );
	var effectFilm = new THREE.FilmPass( 0.35, 0.75, 2048, false );

	effectFilm.renderToScreen = true;

	composer = new THREE.EffectComposer( renderer );

	composer.addPass( renderModel );
	composer.addPass( effectFilm );
};


/**
 * 
 */
SSVIZ.buildScene = function() {
	logConsole.debug( "buildScene[]" );

	geometry = new THREE.SphereGeometry( radius, 100, 50 );
	geometry.computeTangents();

	// sun

	// meshSun = new THREE.Mesh(geometry, materialNormalMap);
	// meshSun.position.set(-radius * 10, 0, 0);
	// meshSun.scale.set(sunScale, sunScale, sunScale);
	// scene.add(meshSun);

	// var sunSprite = new THREE.Sprite({
	// map : THREE.ImageUtils.loadTexture("textures/sun.png"),
	// blending : THREE.AdditiveBlending,
	// useScreenCoordinates : false
	// });
	// sunSprite.position.x = -radius * 10;
	// sunSprite.position.y = 0;
	// sunSprite.position.z = 0;
	// sunSprite.scale = new THREE.Vector3(100, 100, 100);
	// scene.add(sunSprite);
	// planet

	// moon

	// var materialMoon = new THREE.MeshPhongMaterial({
	// color : 0xffffff,
	// map : moonTexture
	// });

	// meshMoon = new THREE.Mesh(geometry, materialMoon);
	// meshMoon.position.set(radius * 5, 0, 0);
	// meshMoon.scale.set(moonScale, moonScale, moonScale);
	// scene.add(meshMoon);

	// SSVIZ.addMoon(scene, "textures/planets/moon_1024.jpg", 5, radius, geometry, moonScale, "moon_1024", 0.1);
	// SSVIZ.addMoon(scene, "textures/planets/moon1.jpg", 7, radius, geometry, moonScale * 3, "moon1", 0.5);

	// stars

	var i, vector, starsGeometry = new THREE.Geometry();

	for (i = 0; i < 3000; i++) {

		vector = new THREE.Vector3( Math.random() * 2 - 1, Math.random() * 2 - 1, Math.random() * 2 - 1 );
		vector.multiplyScalar( radius * 10 );

		starsGeometry.vertices.push( new THREE.Vertex( vector ) );

	}

	var stars, starsMaterials = [ new THREE.ParticleBasicMaterial( {
		color : 0x555555,
		size : 2,
		sizeAttenuation : false
	} ), new THREE.ParticleBasicMaterial( {
		color : 0x555555,
		size : 1,
		sizeAttenuation : false
	} ), new THREE.ParticleBasicMaterial( {
		color : 0x333333,
		size : 2,
		sizeAttenuation : false
	} ), new THREE.ParticleBasicMaterial( {
		color : 0x3a3a3a,
		size : 1,
		sizeAttenuation : false
	} ), new THREE.ParticleBasicMaterial( {
		color : 0x1a1a1a,
		size : 2,
		sizeAttenuation : false
	} ), new THREE.ParticleBasicMaterial( {
		color : 0x1a1a1a,
		size : 1,
		sizeAttenuation : false
	} ) ];

	for (i = 10; i < 30; i++) {

		stars = new THREE.ParticleSystem( starsGeometry, starsMaterials[i % 6] );

		stars.rotation.x = Math.random() * 6;
		stars.rotation.y = Math.random() * 6;
		stars.rotation.z = Math.random() * 6;

		var s = i * 10;
		stars.scale.set( s, s, s );

		stars.matrixAutoUpdate = false;
		stars.updateMatrix();
		stars.name = "stars[" + i + "]";

		// scene.add( stars );

	}

	// particleLight = new THREE.Mesh(new THREE.SphereGeometry(4, 8, 8),
	// new THREE.MeshBasicMaterial({
	// color : 0xffffff
	// }));
	// particleLight.position.y = 300;
	// scene.addObject( particleLight );

	// var directionalLight = new THREE.DirectionalLight(Math.random() * 0xffffff);
	// directionalLight.position.x = Math.random() - 0.5;
	// directionalLight.position.y = Math.random() - 0.5;
	// directionalLight.position.z = Math.random() - 0.5;
	// directionalLight.position.normalize();

	pointLight = new THREE.PointLight( 0xffffff, 10000000, 10000000 );
	pointLight.name = "sun pointLight";
	scene.add( pointLight );

};


/**
 * @param width
 * @param height
 * @returns THREE.WebGLRenderer
 */
SSVIZ.initRenderer = function( width, height ) {
	logConsole.debug( "initRenderer[ " + width + ", " + height + " ]" );
	renderer = new THREE.WebGLRenderer( {
		clearAlpha : 1,
		clearColor : 0x000000,
		antialias : true
	} );
	renderer.setSize( width, height );
	renderer.sortObjects = false;
	renderer.autoClear = false;
	renderer.gammaInput = true;
	renderer.gammaOutput = true;
	return renderer;
};

/**
 * @param scene
 * @param width
 * @param height
 * @param radius
 * @returns THREE.PerspectiveCamera
 */
SSVIZ.initCamera = function( scene, width, height, radius ) {
	logConsole.debug( "initCamera[ " + width + ", " + height + ", " + radius + " ]" );

	camera = new THREE.PerspectiveCamera( 25, width / height, 50, 1e7 );
	camera.position.z = radius * 7 * 100;
	return camera;
};

/**
 * 
 * @param camera
 * @param renderer
 * @param radius
 * @returns THREE.TrackballControls
 */
SSVIZ.initControls = function( camera, renderer, radius ) {
	logConsole.debug( "initControls[]" );

	controls = new THREE.TrackballControls( camera, renderer.domElement );

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
};

/* endregion startup */

/* region listeners */
/**
 * @param event
 */
SSVIZ.onWindowResize = function( event ) {
	logConsole.debug( "onWindowResize[]" );

	// TODO - change from pulling from window to container
	width = window.innerWidth;
	height = window.innerHeight;

	renderer.domElement.style.top = 0;
	renderer.domElement.style.left = 0;
	renderer.setSize( width, height );

	camera.aspect = width / height;
	camera.updateProjectionMatrix();

	controls.screen.width = width;
	controls.screen.height = height;

	camera.radius = (width + height) / 4;
};

/**
 * 
 * @param event
 */
SSVIZ.onDocumentMouseMove = function( event ) {
	event.preventDefault();

	var offsetParent = SSVIZ.getPos( event.target );
	// window offset + titlebar
	var relativeX = event.clientX - offsetParent.x;
	// window offset + margin
	var relativeY = event.clientY - offsetParent.y;

	mouse.x = (relativeX / event.target.clientWidth) * 2 - 1;
	mouse.y = -(relativeY / event.target.clientHeight) * 2 + 1;
	// mouse.x = ( event.clientX / window.innerWidth ) * 2 - 1;
	// mouse.y = - ( event.clientY / window.innerHeight ) * 2 + 1;

	// logConsole.debug("onDocumentMouseMove[" + Log.dumpObject(mouse) + "]");
	SSVIZ.mouseMoved = true;

};

/**
 * 
 * @param event
 */
SSVIZ.onDocumentMouseWheel = function( event ) {
	var delta = (event.wheelDeltaY * 100);
	// logConsole.debug("onDocumentMouseWheel[ " + delta + "]");
	if ((camera.position.z + delta) < 0) {
		return;
	}
	camera.position.z = camera.position.z + delta;
	SSVIZ.mouseMoved = true;
};

/**
 * 
 */
SSVIZ.addListeners = function() {
	logConsole.debug( "addListeners[]" );
	SSVIZ.container.addEventListener( 'mousemove', SSVIZ.onDocumentMouseMove, false );
	window.addEventListener( 'resize', SSVIZ.onWindowResize, false );
	window.addEventListener( 'DOMMouseScroll', SSVIZ.onDocumentMouseWheel, false );
	window.addEventListener( 'mousewheel', SSVIZ.onDocumentMouseWheel, false );

};

/**
 * 
 * @param el
 * @returns {___anonymous5231_5256}
 */
SSVIZ.getPos = function( el ) {
	// yay readability
	for ( var lx = 0, ly = 0; el != null; lx += el.offsetLeft, ly += el.offsetTop, el = el.offsetParent) {
		;
	}
	return {
		x : lx,
		y : ly
	};
};
/* endregion listeners */

/* region shutdown */
/**
 * 
 */
SSVIZ.shutdown = function() {
	logConsole.debug( "shutdown[]" );
	clearInterval( SSVIZ.loadTimer );
	SSVIZ.container.removeEventListener( 'mousemove', SSVIZ.onDocumentMouseMove, false );
	window.removeEventListener( 'resize', SSVIZ.onWindowResize, false );
	window.removeEventListener( 'DOMMouseScroll', SSVIZ.onDocumentMouseWheel, false );
	window.removeEventListener( 'mousewheel', SSVIZ.onDocumentMouseWheel, false );

	cancelAnimationFrame( SSVIZ.animationFrameRequestId );
	logConsole.debug( "shutdown[] complete" );
};

/* endregion shutdown */

/* region buildSolarSystem */

/**
 * 
 * @param jsonObject
 */
SSVIZ.setSolarSystem = function( jsonObject ) {
	logConsole.debug( "setSolarSystem[ " + jsonObject + " ]" );
	SSVIZ.solarSystem = JSON.parse( jsonObject );

};

/**
 * 
 * @param jsonObject
 */
SSVIZ.setStars = function( jsonObject ) {
	logConsole.debug( "setStars[ " + jsonObject + " ]" );
	SSVIZ.stars = JSON.parse( jsonObject );

	var mainStar = SSVIZ.stars[0];
	var starTexture = SSVIZ.getStarTexture( mainStar.spectralType, mainStar.spectralTypeDec );
	var starScale = SSVIZ.getStarScale( mainStar.spectralType, mainStar.spectralTypeDec, mainStar.size );
	var mapA = new THREE.ImageUtils.loadTexture( starTexture, {}, function() {
		/**
		 * called after texture has been loaded
		 */
		logConsole.debug( "setStars[ textureLoaded []]" );
		var sunSprite = new THREE.Sprite( {
			map : mapA,
			blending : THREE.AdditiveBlending,
			useScreenCoordinates : false
		} );
		// sunSprite.position.x = -radius * 10;
		sunSprite.name = "sunSprite";
		sunSprite.position.x = 0;
		sunSprite.position.y = 0;
		sunSprite.position.z = 0;
		var scaleX = mapA.image.width * starScale;
		var scaleY = mapA.image.height * starScale;
		sunSprite.scale.set( scaleX, scaleY, 1 );
		scene.add( sunSprite );
	} );

};

/**
 * 
 * @param jsonObject
 */
SSVIZ.setAPlanet = function( jsonObject ) {
	var planet = eval( '(' + jsonObject + ')' );

	logConsole.debug( Log.dumpObject( planet ) );
};

/**
 * 
 * @param jsonArray
 */
SSVIZ.setAllPlanets = function( jsonArray ) {
	var planetIndex = 0;

	// var planets = eval('(' + jsonArray + ')');
	SSVIZ.planets = JSON.parse( jsonArray );
	if (Object.prototype.toString.call( SSVIZ.planets ) === '[object Array]') {
		logConsole.debug( 'GotArray of size ' + SSVIZ.planets.length );
		// SSVIZ.addMoon(scene, "textures/planets/moon_1024.jpg", 9, radius, geometry, moonScale, "moon_2", 0.1);
		// SSVIZ.addPlanet( scene, radius, 0.1 );
		for (planetIndex = 0; planetIndex < SSVIZ.planets.length; planetIndex++) {
			var planet = SSVIZ.planets[planetIndex];
			logConsole.debug( "Got Planet [" + planet.name + "] in orbit [" + planet.orbit + "] and is Gas Giant ["
					+ planet.isGasGiant + "] and is Satellite [" + planet.isSatellite + "]" );
			if (!planet.isSatellite) {
				var planetRadius = ((radius * 2) * planet.orbit) * 10;
				SSVIZ.addPlanet( scene, planet.name, planetRadius, planet.orbitRotation, planet.size );
				SSVIZ.addPlanetOrbit( scene, planet.name, planetRadius );
			}
		}
	}
	// var clearColor = renderer.getClearColor();
	// var clearAlpha = renderer.getClearAlpha();

	// var output = new THREE.SceneExporter().parse( scene, clearColor, clearAlpha );
	// alert(output);
};

/**
 * @param scene
 * @param name
 * @param radius
 */
SSVIZ.addPlanetOrbit = function( scene, name, radius ) {

	logConsole.debug( "addPlanetOrbit[ " + name + ", " + radius + " ]" );
	var material = new THREE.LineBasicMaterial( {
		color : 0xffffff
	} );

	var orbitGeometry = new THREE.CircleGeometry( radius, 32 );
	var orbitObject = new THREE.Line( orbitGeometry, material );
	orbitObject.rotation.x = 1.57079633; // 90 degrees
	orbitObject.name = name + " Orbit";
	// scene.add( orbitObject );
};

/**
 * @param scene
 * @param name
 * @param radius
 * @param rotation
 * @param size
 */
SSVIZ.addPlanet = function( scene, name, radius, rotation, size ) {
	logConsole.debug( "addPlanet[ " + name + ", " + radius + ", " + rotation + ", " + size + " ]" );
	var planetTexture = THREE.ImageUtils.loadTexture( "textures/planets/third.jpg" );
	//var cloudsTexture = THREE.ImageUtils.loadTexture( "textures/planets/earth_clouds_1024.png" );
	var normalTexture = THREE.ImageUtils.loadTexture( "textures/planets/earth_normal_2048.jpg" );

	var shader = THREE.ShaderUtils.lib["normal"];
	var uniforms = THREE.UniformsUtils.clone( shader.uniforms );

	uniforms["tNormal"].texture = normalTexture;
	uniforms["uNormalScale"].value.set( 0.85, 0.85 );

	uniforms["tDiffuse"].texture = planetTexture;
	// uniforms[ "tSpecular" ].texture = specularTexture;

	uniforms["enableAO"].value = false;
	uniforms["enableDiffuse"].value = true;
	uniforms["enableSpecular"].value = false;

	uniforms["uDiffuseColor"].value.setHex( 0xffffff );
	// uniforms[ "uSpecularColor" ].value.setHex( 0x666666 );
	uniforms["uAmbientColor"].value.setHex( 0x000000 );

	uniforms["uShininess"].value = 15;

	// uniforms["uDiffuseColor"].value.convertGammaToLinear();
	// uniforms[ "uSpecularColor" ].value.convertGammaToLinear();
	// uniforms["uAmbientColor"].value.convertGammaToLinear();

	var materialNormalMap = new THREE.ShaderMaterial( {
		fragmentShader : shader.fragmentShader,
		vertexShader : shader.vertexShader,
		uniforms : uniforms,
		lights : true
	} );

	meshPlanet = new THREE.Mesh( geometry, materialNormalMap );
	meshPlanet.name = name + " Planet";
	scaleSize = size / 100000;
	meshPlanet.scale.set( scaleSize, scaleSize, scaleSize );
	meshPlanet.rotation.y = 0;
	meshPlanet.rotation.z = tilt;
	meshPlanet.position.x = radius;
	meshPlanet.position.y = 0;
	meshPlanet.position.z = 0;
	meshPlanet.visible = true;
	scene.add( meshPlanet );
	meshPlanets.push( meshPlanet );
	rotationPlanets.push( rotation / 1000 );

	// clouds

	/*var materialClouds = new THREE.MeshLambertMaterial( {
		color : 0xffffff,
		map : cloudsTexture,
		transparent : true
	} );

	meshClouds = new THREE.Mesh( geometry, materialClouds );
	meshClouds.name = name + " Clouds";
	meshClouds.scale.set( scaleSize * cloudsScale, scaleSize * cloudsScale, scaleSize * cloudsScale );
	meshClouds.rotation.z = tilt;
	meshClouds.position.x = radius;
	meshClouds.position.y = 0;
	meshClouds.position.z = 0;
	meshClouds.visible = true;*/

	// scene.add( meshClouds );
	// meshPlanetClouds.push( meshClouds );
	// var meshCloudsClone = meshClouds.clone();
	// meshCloudsClone.position.x = meshCloudsClone.position.x - 100;
	// meshCloudsClone.name = name + " Clouds Clone";
	// scene.add( meshCloudsClone );
	// Planet Directional Light
	//var dirLight = new THREE.DirectionalLight( 0xFFFFFF );
	//dirLight.position.set( radius + 10000, 0, 0 );
	//dirLight.name = name + " dirLight";
	// dirLight.shadowCameraFar = camera.far;
	//dirLight.target = meshClouds;
	//scene.add( dirLight );

	// scene.add( dirLight );
	// dirLight.target = meshClouds;
};

/**
 * @param scene
 * @param texturePath
 * @param radiusModifier
 * @param radius
 * @param geometry
 * @param scale
 * @param name
 * @param rotation
 */
SSVIZ.addMoon = function( scene, texturePath, radiusModifier, radius, geometry, scale, name, rotation ) {
	logConsole.debug( "addMoon[ " + texturePath + ", " + radiusModifier + ", " + scale + " ]" );
	var moonTexture = THREE.ImageUtils.loadTexture( texturePath );

	var materialMoon = new THREE.MeshPhongMaterial( {
		color : 0xffffff,
		map : moonTexture
	} );

	var meshMoon = new THREE.Mesh( geometry, materialMoon );
	meshMoons.push( meshMoon );
	meshMoon.position.set( radiusModifier * radius, 0, 0 );
	meshMoon.scale.set( moonScale, moonScale, moonScale );
	meshMoon.name = name + " Moon";
	rotationMoons.push( rotation );
	scene.add( meshMoon );

};

/* endregion buildSolarSystem */

/* region tools */

/**
 * 
 */
SSVIZ.exportScene = function() {
	var clearColor = renderer.getClearColor();
	var clearAlpha = renderer.getClearAlpha();

	var sceneExport = new THREE.SceneExporter().parse( scene, clearColor, clearAlpha );

	var blob = new Blob( [ sceneExport ], {
		type : 'text/plain'
	} );
	var objectURL = URL.createObjectURL( blob );

	window.open( objectURL, '_blank' );
	window.focus();
};

/* endregion tools */

/* region render */

/**
 * 
 */
SSVIZ.animate = function() {

	SSVIZ.animationFrameRequestId = requestAnimationFrame( SSVIZ.animate );

	SSVIZ.render( rotationSpeed );
	stats.update();

};

/**
 * @param pause
 */
SSVIZ.setRotation = function( pause ) {
	SSVIZ.doRotation = !pause;
};

/**
 * 
 */
SSVIZ.render = function() {

	var delta = clock.getDelta();
	var planetIndex = 0;
	var moonIndex = 0;
	// var timer = new Date().getTime() * 0.0001;

	if (!meshPlanet) {
		return;
	}

	if (SSVIZ.doRotation) {
		meshPlanet.rotation.y += rotationSpeed * delta;
		meshClouds.rotation.y += 1.25 * rotationSpeed * delta;

		// var angle = delta * rotationSpeed;

		// meshMoon.position = new THREE.Vector3(
		// Math.cos( angle ) * meshMoon.position.x - Math.sin( angle ) * meshMoon.position.z,
		// 0,
		// Math.sin( angle ) * meshMoon.position.x + Math.cos( angle ) * meshMoon.position.z
		// );
		// meshMoon.rotation.y -= angle;

		for (planetIndex = 0; planetIndex < meshPlanets.length; planetIndex++) {
			var planetAngle = delta * rotationPlanets[planetIndex];
			var planetToMove = meshPlanets[planetIndex];
			planetToMove.position = new THREE.Vector3( Math.cos( planetAngle ) * planetToMove.position.x - Math.sin( planetAngle )
					* planetToMove.position.z, 0, Math.sin( planetAngle ) * planetToMove.position.x + Math.cos( planetAngle )
					* planetToMove.position.z );
			// var planetCloudsToMove = meshPlanetClouds[planetIndex];
			// planetCloudsToMove.position = planetToMove.position;
		}

		for (moonIndex = 0; moonIndex < meshMoons.length; moonIndex++) {
			var moonAngle = delta * rotationMoons[moonIndex];
			var moonToMove = meshMoons[moonIndex];
			moonToMove.position = new THREE.Vector3( Math.cos( moonAngle ) * moonToMove.position.x - Math.sin( moonAngle )
					* moonToMove.position.z, 0, Math.sin( moonAngle ) * moonToMove.position.x + Math.cos( moonAngle )
					* moonToMove.position.z );
			moonToMove.rotation.y -= moonAngle;
		}

	}
	if (SSVIZ.mouseMoved) {
		SSVIZ.mouseMoved = false;
		// determine if mouse is over item to highlight
		var vector = new THREE.Vector3( mouse.x, mouse.y, 0.5 );
		projector.unprojectVector( vector, camera );

		var ray = new THREE.Ray( camera.position, vector.subSelf( camera.position ).normalize() );
		// logConsole.debug("camera x,y,z " + camera.position.x + "," + camera.position.y + "," + camera.position.z);

		var intersects = ray.intersectObjects( scene.children );

		if (intersects.length > 0) {
			var intersected = intersects[0].object;
			logConsole.debug( "intersected[" + intersected.name + "]" );
			// sayHello(intersected.name);
			window.onMouseOverPlanet( intersected.name );
		} else {
			window.onMouseOverPlanet( '' );
		}
	}
	// particleLight.position.x = Math.sin( timer * 7 ) * 300;
	// particleLight.position.y = Math.cos( timer * 5 ) * 400;
	// particleLight.position.z = Math.cos( timer * 3 ) * 300;

	// pointLight.position.x = particleLight.position.x;
	// pointLight.position.y = particleLight.position.y;
	// pointLight.position.z = particleLight.position.z;

	controls.update();

	renderer.clear();
	//renderer.render( scene, camera );
	composer.render( delta );
	// logConsole.debug( camera.position.z );

};

/* endregion render */

/* region rules */

/**
 * @param spectralType
 * @param spectralTypeDec
 * @param size
 * @returns {Number} scale of the star
 */
SSVIZ.getStarScale = function( spectralType, spectralTypeDec, size ) {
	var scale = 1000;

	switch (spectralType) {
	case 'O':
		if (spectralTypeDec < 5) {
			switch (size) {
			case 'D':
				scale = 200;
				break;
			case 'Ia':
				scale = 90000;
				break;
			case 'Ib':
				scale = 80000;
				break;
			case 'II':
				scale = 70000;
				break;
			case 'III':
				scale = 50000;
				break;
			case 'IV':
				scale = 40000;
				break;
			case 'V':
				scale = 20000;
				break;
			case 'VI':
				scale = 10000;
				break;
			default:
				scale = 1000;
				break;
			}
		} else {
			switch (size) {
			case 'D':
				scale = 250;
				break;
			case 'Ia':
				scale = 120000;
				break;
			case 'Ib':
				scale = 105000;
				break;
			case 'II':
				scale = 90000;
				break;
			case 'III':
				scale = 65000;
				break;
			case 'IV':
				scale = 55000;
				break;
			case 'V':
				scale = 35000;
				break;
			case 'VI':
				scale = 20000;
				break;
			default:
				scale = 1000;
				break;
			}
		}
		break;
	case 'B':
		if (spectralTypeDec < 5) {
			switch (size) {
			case 'D':
				scale = 260;
				break;
			case 'Ia':
				scale = 60000;
				break;
			case 'Ib':
				scale = 50000;
				break;
			case 'II':
				scale = 30000;
				break;
			case 'III':
				scale = 25000;
				break;
			case 'IV':
				scale = 20000;
				break;
			case 'V':
				scale = 18000;
				break;
			case 'VI':
				scale = 15000;
				break;
			default:
				scale = 1000;
				break;
			}
		} else {
			switch (size) {
			case 'D':
				scale = 260;
				break;
			case 'Ia':
				scale = 30000;
				break;
			case 'Ib':
				scale = 25000;
				break;
			case 'II':
				scale = 20000;
				break;
			case 'III':
				scale = 15000;
				break;
			case 'IV':
				scale = 10000;
				break;
			case 'V':
				scale = 6500;
				break;
			case 'VI':
				scale = 2500;
				break;
			default:
				scale = 1000;
				break;
			}
		}
		break;
	case 'A':
		if (spectralTypeDec < 5) {
			switch (size) {
			case 'D':
				scale = 360;
				break;
			case 'Ia':
				scale = 18000;
				break;
			case 'Ib':
				scale = 16000;
				break;
			case 'II':
				scale = 14000;
				break;
			case 'III':
				scale = 12000;
				break;
			case 'IV':
				scale = 6000;
				break;
			case 'V':
				scale = 3200;
				break;
			case 'VI':
				scale = 1100;
				break;
			default:
				scale = 1000;
				break;
			}
		} else {
			switch (size) {
			case 'D':
				scale = 360;
				break;
			case 'Ia':
				scale = 15000;
				break;
			case 'Ib':
				scale = 13000;
				break;
			case 'II':
				scale = 11000;
				break;
			case 'III':
				scale = 9000;
				break;
			case 'IV':
				scale = 4000;
				break;
			case 'V':
				scale = 2100;
				break;
			case 'VI':
				scale = 1000;
				break;
			default:
				scale = 1000;
				break;
			}
		}
		break;
	case 'F':
		if (spectralTypeDec < 5) {
			switch (size) {
			case 'D':
				scale = 420;
				break;
			case 'Ia':
				scale = 13000;
				break;
			case 'Ib':
				scale = 12000;
				break;
			case 'II':
				scale = 10000;
				break;
			case 'III':
				scale = 8000;
				break;
			case 'IV':
				scale = 2500;
				break;
			case 'V':
				scale = 1700;
				break;
			case 'VI':
				scale = 1000;
				break;
			default:
				scale = 1000;
				break;
			}
		} else {
			switch (size) {
			case 'D':
				scale = 420;
				break;
			case 'Ia':
				scale = 12000;
				break;
			case 'Ib':
				scale = 10000;
				break;
			case 'II':
				scale = 8100;
				break;
			case 'III':
				scale = 5000;
				break;
			case 'IV':
				scale = 2000;
				break;
			case 'V':
				scale = 1300;
				break;
			case 'VI':
				scale = 800;
				break;
			default:
				scale = 1000;
				break;
			}
		}
		break;
	case 'G':
		if (spectralTypeDec < 5) {
			switch (size) {
			case 'D':
				scale = 630;
				break;
			case 'Ia':
				scale = 12000;
				break;
			case 'Ib':
				scale = 10000;
				break;
			case 'II':
				scale = 8100;
				break;
			case 'III':
				scale = 2500;
				break;
			case 'IV':
				scale = 1750;
				break;
			case 'V':
				scale = 1040;
				break;
			case 'VI':
				scale = 600;
				break;
			default:
				scale = 1000;
				break;
			}
		} else {
			switch (size) {
			case 'D':
				scale = 630;
				break;
			case 'Ia':
				scale = 13000;
				break;
			case 'Ib':
				scale = 12000;
				break;
			case 'II':
				scale = 10000;
				break;
			case 'III':
				scale = 3200;
				break;
			case 'IV':
				scale = 2000;
				break;
			case 'V':
				scale = 940;
				break;
			case 'VI':
				scale = 528;
				break;
			default:
				scale = 1000;
				break;
			}
		}
		break;
	case 'K':
		if (spectralTypeDec < 5) {
			switch (size) {
			case 'D':
				scale = 830;
				break;
			case 'Ia':
				scale = 14000;
				break;
			case 'Ib':
				scale = 13000;
				break;
			case 'II':
				scale = 11000;
				break;
			case 'III':
				scale = 4000;
				break;
			case 'IV':
				scale = 2300;
				break;
			case 'V':
				scale = 825;
				break;
			case 'VI':
				scale = 430;
				break;
			default:
				scale = 1000;
				break;
			}
		} else {
			switch (size) {
			case 'D':
				scale = 830;
				break;
			case 'Ia':
				scale = 18000;
				break;
			case 'Ib':
				scale = 16000;
				break;
			case 'II':
				scale = 14000;
				break;
			case 'III':
				scale = 5000;
				break;
			case 'IV':
				scale = 1000;
				break;
			case 'V':
				scale = 570;
				break;
			case 'VI':
				scale = 330;
				break;
			default:
				scale = 1000;
				break;
			}
		}
		break;
	case 'M':
		if (spectralTypeDec < 5) {
			switch (size) {
			case 'D':
				scale = 1110;
				break;
			case 'Ia':
				scale = 20000;
				break;
			case 'Ib':
				scale = 16000;
				break;
			case 'II':
				scale = 14000;
				break;
			case 'III':
				scale = 7400;
				break;
			case 'IV':
				scale = 1000;
				break;
			case 'V':
				scale = 489;
				break;
			case 'VI':
				scale = 154;
				break;
			default:
				scale = 1000;
				break;
			}
		} else {
			switch (size) {
			case 'D':
				scale = 1110;
				break;
			case 'Ia':
				scale = 25000;
				break;
			case 'Ib':
				scale = 20000;
				break;
			case 'II':
				scale = 18000;
				break;
			case 'III':
				scale = 9200;
				break;
			case 'IV':
				scale = 1000;
				break;
			case 'V':
				scale = 331;
				break;
			case 'VI':
				scale = 104;
				break;
			default:
				scale = 1000;
				break;
			}
		}
		break;
	default:
		logConsole.warn( 'Spectral Type ' + spectralType + ' not handled' );
		break;
	}
	scale = scale / 10;

	logConsole.debug( "getStarScale[ " + spectralType + " , " + spectralTypeDec + " , " + size + " ] = " + scale );
	return scale;
};

/**
 * @param spectralType
 * @param spectralTypeDec
 * @returns {string} the texture path of the star
 */
SSVIZ.getStarTexture = function( spectralType, spectralTypeDec ) {
	var starTexture = "textures/sun.png";
	switch (spectralType) {
	case 'O':
		if (spectralTypeDec < 5) {
			starTexture = "textures/star/sun_o0.png";
		} else {
			starTexture = "textures/star/sun_o5.png";
		}
		break;
	case 'B':
		if (spectralTypeDec < 5) {
			starTexture = "textures/star/sun_b0.png";
		} else {
			starTexture = "textures/star/sun_b5.png";
		}
		break;
	case 'A':
		if (spectralTypeDec < 5) {
			starTexture = "textures/star/sun_a0.png";
		} else {
			starTexture = "textures/star/sun_a5.png";
		}
		break;
	case 'F':
		if (spectralTypeDec < 5) {
			starTexture = "textures/star/sun_f0.png";
		} else {
			starTexture = "textures/star/sun_f5.png";
		}
		break;
	case 'G':
		if (spectralTypeDec < 5) {
			starTexture = "textures/star/sun_g0.png";
		} else {
			starTexture = "textures/star/sun_g5.png";
		}
		break;
	case 'K':
		if (spectralTypeDec < 5) {
			starTexture = "textures/star/sun_k0.png";
		} else {
			starTexture = "textures/star/sun_k5.png";
		}
		break;
	case 'M':
		if (spectralTypeDec < 5) {
			starTexture = "textures/star/sun_m0.png";
		} else {
			starTexture = "textures/star/sun_m5.png";
		}
		break;
	default:
		logConsole.warn( 'Spectral Type ' + spectralType + ' not handled' );
		break;
	}
	logConsole.debug( "getStarTexture[ " + spectralType + " , " + spectralTypeDec + " ] = " + starTexture );
	return starTexture;
};

/* endregion rules */