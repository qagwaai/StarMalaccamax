/**
 * PlanetDTO.java
 * Created by pgirard at 1:54:11 PM on Sep 9, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;

import com.google.code.twig.annotation.Embedded;
import com.google.code.twig.annotation.Index;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.annotation.Entity;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
@Entity
public final class PlanetDTO implements IsSerializable, Serializable, Planet {
    /**
	 * 
	 */
    @com.google.code.twig.annotation.Id
    @com.googlecode.objectify.annotation.Id
    @com.googlecode.objectify.annotation.Index
    private Long id;
    /**
	 * 
	 */
    @Index
    @com.googlecode.objectify.annotation.Index
    private Long solarSystemId;
    /**
	 * 
	 */
    private int size;
    /**
	 * 
	 */
    private int atmosphere;
    /**
	 * 
	 */
    private Double hydrographics;
    /**
	 * 
	 */
    private Long population;
    /**
	 * 
	 */
    private int dockQuality;
    /**
	 * 
	 */
    private int government;
    /**
	 * 
	 */
    private int lawLevel;
    /**
	 * 
	 */
    private int techLevel;
    /**
	 * 
	 */
    private int orbit;
    /**
	 * 
	 */
    @com.googlecode.objectify.annotation.Index
    private boolean isGasGiant;
    /**
	 * 
	 */
    private String name;

    /**
	 * 
	 */
    private boolean isMainWorld;

    /**
	 * 
	 */
    private boolean isSatellite;

    /**
	 * 
	 */
    private Double orbitalDistance;
    /**
	 * 
	 */
    @Embedded
    private LocationDTO location;

    /**
	 * 
	 */
    private Long satelliteParentId;

    private int orbitCenterX;
    private int orbitCenterY;

    private Double orbitalEccentricity;

    private int orbitRotation;

    private int orbitXRel;
    private int orbitYRel;

    /**
	 * 
	 */
    public PlanetDTO() {
        location = new LocationDTO();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PlanetDTO other = (PlanetDTO) obj;
        if (atmosphere != other.atmosphere) {
            return false;
        }
        if (dockQuality != other.dockQuality) {
            return false;
        }
        if (government != other.government) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (isGasGiant != other.isGasGiant) {
            return false;
        }
        if (isMainWorld != other.isMainWorld) {
            return false;
        }
        if (isSatellite != other.isSatellite) {
            return false;
        }
        if (lawLevel != other.lawLevel) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (orbit != other.orbit) {
            return false;
        }
        if (population == null) {
            if (other.population != null) {
                return false;
            }
        } else if (!population.equals(other.population)) {
            return false;
        }
        if (size != other.size) {
            return false;
        }
        if (solarSystemId == null) {
            if (other.solarSystemId != null) {
                return false;
            }
        } else if (!solarSystemId.equals(other.solarSystemId)) {
            return false;
        }
        if (techLevel != other.techLevel) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAtmosphere() {
        return atmosphere;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDockQuality() {
        return dockQuality;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getGovernment() {
        return government;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getHydrographics() {
        return hydrographics;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLawLevel() {
        return lawLevel;
    }

    /**
     * @return the location
     */
    public LocationDTO getLocation() {
        return location;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOrbit() {
        return orbit;
    }

    @Override
    public Double getOrbitalDistance() {
        return orbitalDistance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getPopulation() {
        return population;
    }

    @Override
    public Long getSatelliteParentId() {
        return satelliteParentId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getSolarSystemId() {
        return solarSystemId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTechLevel() {
        return techLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + atmosphere;
        result = prime * result + dockQuality;
        result = prime * result + government;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + (isGasGiant ? 1231 : 1237);
        result = prime * result + (isMainWorld ? 1231 : 1237);
        result = prime * result + (isSatellite ? 1231 : 1237);
        result = prime * result + lawLevel;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + orbit;
        result = prime * result + ((population == null) ? 0 : population.hashCode());
        result = prime * result + size;
        result = prime * result + ((solarSystemId == null) ? 0 : solarSystemId.hashCode());
        result = prime * result + techLevel;
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGasGiant() {
        return isGasGiant;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean isMainWorld() {
        return isMainWorld;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean isSatellite() {
        return isSatellite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAtmosphere(final int atmosphere) {
        this.atmosphere = atmosphere;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDockQuality(final int dockQuality) {
        this.dockQuality = dockQuality;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGasGiant(final boolean isGasGiant) {
        this.isGasGiant = isGasGiant;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGovernment(final int government) {
        this.government = government;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHydrographics(final Double hydrographics) {
        this.hydrographics = hydrographics;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLawLevel(final int lawLevel) {
        this.lawLevel = lawLevel;
    }

    /**
     * @param location
     *            the location to set
     */
    public void setLocation(final LocationDTO location) {
        this.location = location;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setMainWorld(final boolean isMainWorld) {
        this.isMainWorld = isMainWorld;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOrbit(final int orbit) {
        this.orbit = orbit;
    }

    @Override
    public void setOrbitalDistance(final Double orbitalDistance) {
        this.orbitalDistance = orbitalDistance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPopulation(final Long population) {
        this.population = population;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setSatellite(final boolean isSatellite) {
        this.isSatellite = isSatellite;
    }

    @Override
    public void setSatelliteParentId(final Long planetId) {
        this.satelliteParentId = planetId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSize(final int size) {
        this.size = size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSolarSystemId(final Long solarSystemId) {
        this.solarSystemId = solarSystemId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTechLevel(final int techLevel) {
        this.techLevel = techLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "PlanetDTO [atmosphere=" + atmosphere + ", dockQuality=" + dockQuality + ", government=" + government
            + ", hydrographics=" + hydrographics + ", id=" + id + ", isGasGiant=" + isGasGiant + ", isMainWorld="
            + isMainWorld + ", isSatellite=" + isSatellite + ", lawLevel=" + lawLevel + ", location=" + location
            + ", name=" + name + ", orbit=" + orbit + ", population=" + population + ", size=" + size
            + ", solarSystemId=" + solarSystemId + ", techLevel=" + techLevel + "]";
    }

    @Override
    public int getOrbitCenterX() {
        return orbitCenterX;
    }

    @Override
    public void setOrbitCenterX(int x) {
        this.orbitCenterX = x;
    }

    @Override
    public int getOrbitCenterY() {
        return orbitCenterY;
    }

    @Override
    public void setOrbitCenterY(int y) {
        this.orbitCenterY = y;
    }

    @Override
    public Double getOrbitalEccentricity() {
        return orbitalEccentricity;
    }

    @Override
    public void setOrbitalEccentricity(Double eccentricity) {
        this.orbitalEccentricity = eccentricity;
    }

    @Override
    public int getOrbitRotation() {
        return orbitRotation;
    }

    @Override
    public void setOrbitRotation(int angle) {
        this.orbitRotation = angle;
    }

    @Override
    public int getOrbitXRel() {
        return orbitXRel;
    }

    @Override
    public void setOrbitXRel(int xrel) {
        orbitXRel = xrel;
    }

    @Override
    public int getOrbitYRel() {
        return orbitYRel;
    }

    @Override
    public void setOrbitYRel(int yrel) {
        orbitYRel = yrel;
    }
    
    public static String getFieldGetter(String fieldName) {
    	String methodName = null;
    	
    	if (fieldName.equals("id")) {
    		methodName = "getId";
    	} else if (fieldName.equals("solarSystemId")) {
    		methodName = "getSolarSystemId";
    	} else if (fieldName.equals("isGasGiant")) {
    		methodName = "isGasGiant";
    	}
    	return methodName;
    }
}
