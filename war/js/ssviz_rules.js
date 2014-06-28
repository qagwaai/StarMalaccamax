/**
 * 
 */


SSVIZ.getStarScale = function(spectralType, spectralTypeDec, size) {
	var scale = 1000;
	
	switch (spectralType) {
	case 'O':
	    if (spectralTypeDec < 5) {
	    	switch(size) {
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
	    	switch(size) {
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
	    	switch(size) {
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
	    	switch(size) {
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
	    	switch(size) {
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
	    	switch(size) {
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
	    	switch(size) {
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
	    	switch(size) {
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
	    	switch(size) {
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
	    	switch(size) {
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
	    	switch(size) {
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
	    	switch(size) {
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
	    	switch(size) {
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
	    	switch(size) {
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
		logConsole.warn('Spectral Type ' + spectralType + ' not handled');
		break;
	}
	scale = scale / 10;
	
	logConsole.debug("getStarScale[ " + spectralType + " , " + spectralTypeDec + " , " + size + " ] = " + scale);
	return scale;
}


SSVIZ.getStarTexture = function(spectralType, spectralTypeDec) {
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
		logConsole.warn('Spectral Type ' + spectralType + ' not handled');
		break;
	}
	logConsole.debug("getStarTexture[ " + spectralType + " , " + spectralTypeDec + " ] = " + starTexture);
	return starTexture;
}