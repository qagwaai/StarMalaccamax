/**
 * StarDTO.java
 * Created by pgirard at 11:47:38 AM on Sep 1, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.annotation.Entity;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
@Entity
public final class StarDTO implements IsSerializable, Serializable, Star {
    /**
	 * 
	 */
    @com.google.code.twig.annotation.Id
    @com.googlecode.objectify.annotation.Id
    private Long id;
    /**
	 * 
	 */
    private Long solarSystemId;
    /**
	 * 
	 */
    private String componentId;
    /**
	 * 
	 */
    private int orbit;
    /**
	 * 
	 */
    private String size;
    /**
	 * 
	 */
    private String spectralType;
    /**
	 * 
	 */
    private double spectralTypeDec;
    /**
	 * 
	 */
    private boolean isCompanion;

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
        StarDTO other = (StarDTO) obj;
        if (componentId == null) {
            if (other.componentId != null) {
                return false;
            }
        } else if (!componentId.equals(other.componentId)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (isCompanion != other.isCompanion) {
            return false;
        }
        if (orbit != other.orbit) {
            return false;
        }
        if (size == null) {
            if (other.size != null) {
                return false;
            }
        } else if (!size.equals(other.size)) {
            return false;
        }
        if (solarSystemId == null) {
            if (other.solarSystemId != null) {
                return false;
            }
        } else if (!solarSystemId.equals(other.solarSystemId)) {
            return false;
        }
        if (spectralType == null) {
            if (other.spectralType != null) {
                return false;
            }
        } else if (!spectralType.equals(other.spectralType)) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getComponentIdentifier() {
        return componentId;
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
    public int getOrbit() {
        return orbit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSize() {
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
    public String getSpectralType() {
        return spectralType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSpectralTypeDec() {
        return spectralTypeDec;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((componentId == null) ? 0 : componentId.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + (isCompanion ? 1231 : 1237);
        result = prime * result + orbit;
        result = prime * result + ((size == null) ? 0 : size.hashCode());
        result = prime * result + ((solarSystemId == null) ? 0 : solarSystemId.hashCode());
        result = prime * result + ((spectralType == null) ? 0 : spectralType.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCompanion() {
        return isCompanion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCompanion(final boolean isCompanion) {
        this.isCompanion = isCompanion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setComponentIdentifier(final String componentId) {
        this.componentId = componentId;
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
    public void setOrbit(final int orbit) {
        this.orbit = orbit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSize(final String size) {
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
    public void setSpectralType(final String spectralType) {
        this.spectralType = spectralType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpectralTypeDec(final double spectralTypeDec) {
        this.spectralTypeDec = spectralTypeDec;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("StarDTO [componentId=");
        builder.append(componentId);
        builder.append(", id=");
        builder.append(id);
        builder.append(", isCompanion=");
        builder.append(isCompanion);
        builder.append(", orbit=");
        builder.append(orbit);
        builder.append(", size=");
        builder.append(size);
        builder.append(", solarSystemId=");
        builder.append(solarSystemId);
        builder.append(", spectralType=");
        builder.append(spectralType);
        builder.append(", spectralTypeDec=");
        builder.append(spectralTypeDec);
        builder.append("]");
        return builder.toString();
    }

}
