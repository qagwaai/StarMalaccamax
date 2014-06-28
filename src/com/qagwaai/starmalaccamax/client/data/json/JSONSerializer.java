package com.qagwaai.starmalaccamax.client.data.json;

import java.util.ArrayList;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.qagwaai.starmalaccamax.client.data.PlanetRecord;
import com.qagwaai.starmalaccamax.shared.model.LocationDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

public final class JSONSerializer {

    private JSONSerializer() {
        
    }
    
    public static JSONValue toJSON(ArrayList<PlanetDTO> planets) {
        JSONArray arrayPlanets = new JSONArray();
        for (int index = 0; index < planets.size(); index++) {
            arrayPlanets.set(index, toJSON(planets.get(index)));
        }
        return arrayPlanets;
    }
    
    
    public static JSONValue toJSON(PlanetDTO planet) {
        JSONObject jsonPlanet = new JSONObject();
        if (planet.getId() == null) {
            jsonPlanet.put(PlanetRecord.ID, new JSONNumber(-1));
        } else {
            jsonPlanet.put(PlanetRecord.ID, new JSONNumber(planet.getId()));
        }
        if (planet.getSolarSystemId() == null) {
            jsonPlanet.put(PlanetRecord.SOLARSYSTEM_ID, new JSONNumber(-1));
        } else {
            jsonPlanet.put(PlanetRecord.SOLARSYSTEM_ID, new JSONNumber(planet.getSolarSystemId()));
        }
        jsonPlanet.put(PlanetRecord.SIZE, new JSONNumber(planet.getSize()));
        jsonPlanet.put(PlanetRecord.ATMOSPHERE, new JSONNumber(planet.getAtmosphere()));
        if (planet.getHydrographics() == null) {
            jsonPlanet.put(PlanetRecord.HYDROGRAPHICS, new JSONNumber(-1));
        } else {
            jsonPlanet.put(PlanetRecord.HYDROGRAPHICS, new JSONNumber(planet.getHydrographics()));
        }
        if (planet.getPopulation() == null) {
            jsonPlanet.put(PlanetRecord.POPULATION, new JSONNumber(-1));
        } else {
            jsonPlanet.put(PlanetRecord.POPULATION, new JSONNumber(planet.getPopulation()));
        }
        jsonPlanet.put(PlanetRecord.DOCK_QUALITY, new JSONNumber(planet.getDockQuality()));
        jsonPlanet.put(PlanetRecord.GOVERNMENT, new JSONNumber(planet.getGovernment()));
        jsonPlanet.put(PlanetRecord.LAW_LEVEL, new JSONNumber(planet.getLawLevel()));
        jsonPlanet.put(PlanetRecord.TECH_LEVEL, new JSONNumber(planet.getTechLevel()));
        jsonPlanet.put(PlanetRecord.ORBIT, new JSONNumber(planet.getOrbit()));
        jsonPlanet.put(PlanetRecord.IS_GAS_GIANT, JSONBoolean.getInstance(planet.isGasGiant()));
        jsonPlanet.put(PlanetRecord.NAME, new JSONString(planet.getName()));
        jsonPlanet.put(PlanetRecord.IS_MAIN_WORLD, JSONBoolean.getInstance(planet.isMainWorld()));
        jsonPlanet.put(PlanetRecord.IS_SATELLITE, JSONBoolean.getInstance(planet.isSatellite()));
        if (planet.getOrbitalDistance() == null) {
            jsonPlanet.put(PlanetRecord.ORBITAL_DISTANCE, new JSONNumber(-1));
        } else {
            jsonPlanet.put(PlanetRecord.ORBITAL_DISTANCE, new JSONNumber(planet.getOrbitalDistance()));
        }
        JSONObject planetLocation = new JSONObject();
        planetLocation.put(LocationDTO.SOLAR_SYSTEM_ID, new JSONNumber(planet.getLocation().getSolarSystemId()));
        planetLocation.put(LocationDTO.PLANET_ID, new JSONNumber(planet.getLocation().getPlanetId()));
        planetLocation.put(LocationDTO.X, new JSONNumber(planet.getLocation().getX()));
        planetLocation.put(LocationDTO.Y, new JSONNumber(planet.getLocation().getY()));
        planetLocation.put(LocationDTO.Z, new JSONNumber(planet.getLocation().getZ()));        
        jsonPlanet.put(PlanetRecord.LOCATION_OBJ, planetLocation);
        if (planet.getSatelliteParentId() == null) {
            jsonPlanet.put(PlanetRecord.SATELLITE_PARENT_ID, new JSONNumber(-1));
        } else {
            jsonPlanet.put(PlanetRecord.SATELLITE_PARENT_ID, new JSONNumber(planet.getSatelliteParentId()));
        }
        jsonPlanet.put(PlanetRecord.ORBIT_CENTER_X, new JSONNumber(planet.getOrbitCenterX()));
        jsonPlanet.put(PlanetRecord.ORBIT_CENTER_Y, new JSONNumber(planet.getOrbitCenterY()));
        if (planet.getOrbitalEccentricity() == null) {
            jsonPlanet.put(PlanetRecord.ORBITAL_ECCENTRICITY, new JSONNumber(-1));
        } else {
            jsonPlanet.put(PlanetRecord.ORBITAL_ECCENTRICITY, new JSONNumber(planet.getOrbitalEccentricity()));
        }
        jsonPlanet.put(PlanetRecord.ORBIT_ROTATION, new JSONNumber(planet.getOrbitRotation()));
        jsonPlanet.put(PlanetRecord.ORBIT_X_RELATIVE, new JSONNumber(planet.getOrbitXRel()));
        jsonPlanet.put(PlanetRecord.ORBIT_Y_RELATIVE, new JSONNumber(planet.getOrbitYRel()));
        
        return jsonPlanet;
    }
}
