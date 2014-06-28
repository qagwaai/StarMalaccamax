/**
 * 
 */

SSVIZ.exportScene = function() {
	var clearColor = renderer.getClearColor();
	var clearAlpha = renderer.getClearAlpha();
	
	var sceneExport = new THREE.SceneExporter().parse( scene, clearColor, clearAlpha );
	
	var blob = new Blob( [ sceneExport ], { type: 'text/plain' } );
	var objectURL = URL.createObjectURL( blob );

	window.open( objectURL, '_blank' );
	window.focus();
}

SSVIZ.animate = function() {
	
	SSVIZ.animationFrameRequestId = requestAnimationFrame( SSVIZ.animate );

	SSVIZ.render( rotationSpeed );
	stats.update();

};

SSVIZ.setRotation = function( pause ) {
	SSVIZ.doRotation = !pause;
}

SSVIZ.render = function() {

	var delta = clock.getDelta();
	var timer = new Date().getTime() * 0.0001;
	
	if (!meshPlanet) {
		return;
	}
	
	if (SSVIZ.doRotation) {
		meshPlanet.rotation.y += rotationSpeed * delta;
		meshClouds.rotation.y += 1.25 * rotationSpeed * delta;
	
		var angle = delta * rotationSpeed;
	
		//meshMoon.position = new THREE.Vector3(
		//	Math.cos( angle ) * meshMoon.position.x - Math.sin( angle ) * meshMoon.position.z,
		//	0,
		//	Math.sin( angle ) * meshMoon.position.x + Math.cos( angle ) * meshMoon.position.z
		//);
		//meshMoon.rotation.y -= angle;
		
		for (planetIndex = 0; planetIndex < meshPlanets.length; planetIndex++) {
			var planetAngle = delta * rotationPlanets[planetIndex];
			var planetToMove = meshPlanets[planetIndex];
			planetToMove.position = new THREE.Vector3(
					Math.cos( planetAngle ) * planetToMove.position.x - Math.sin( planetAngle ) * planetToMove.position.z,
					0,
					Math.sin( planetAngle ) * planetToMove.position.x + Math.cos( planetAngle ) * planetToMove.position.z
				);
			var planetCloudsToMove = meshPlanetClouds[planetIndex];
			planetCloudsToMove.position = planetToMove.position;
		}
		
		for (moonIndex = 0; moonIndex < meshMoons.length; moonIndex++) {
			var moonAngle = delta * rotationMoons[moonIndex];
			var moonToMove = meshMoons[moonIndex];
			moonToMove.position = new THREE.Vector3(
					Math.cos( moonAngle ) * moonToMove.position.x - Math.sin( moonAngle ) * moonToMove.position.z,
					0,
					Math.sin( moonAngle ) * moonToMove.position.x + Math.cos( moonAngle ) * moonToMove.position.z
				);
			moonToMove.rotation.y -= moonAngle;
		}
	
	}
	if (SSVIZ.mouseMoved) {
		SSVIZ.mouseMoved = false;
		// determine if mouse is over item to highlight
		var vector = new THREE.Vector3( mouse.x, mouse.y, 0.5 );
		projector.unprojectVector( vector, camera );
	
		var ray = new THREE.Ray( camera.position, vector.subSelf( camera.position ).normalize() );
		//logConsole.debug("camera x,y,z " + camera.position.x + "," + camera.position.y + "," + camera.position.z);
	
		var intersects = ray.intersectObjects( scene.children );
	
		if ( intersects.length > 0 ) {
			var intersected = intersects[ 0 ].object;
			logConsole.debug("intersected[" + intersected.name + "]");
			//sayHello(intersected.name);
			window.onMouseOverPlanet(intersected.name);
		} else {
			window.onMouseOverPlanet('');
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
	renderer.render( scene, camera );
	
	//logConsole.debug( camera.position.z );

};