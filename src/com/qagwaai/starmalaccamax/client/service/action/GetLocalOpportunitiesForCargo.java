/**
 * GetCurrentUser.java
 * Created by pgirard at 3:57:10 PM on Jul 22, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.actions package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.ShipCargoDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;

/**
 * @author pgirard
 * 
 */
public final class GetLocalOpportunitiesForCargo implements IsSerializable,
    Action<GetLocalOpportunitiesForCargoResponse> {
    /**
	 * 
	 */
    private ShipCargoDTO cargo;
    /**
	 * 
	 */
    private ShipDTO ship;

    /**
	 * 
	 */
    public GetLocalOpportunitiesForCargo() {

    }

    /**
     * @param cargo
     *            the cargo
     * @param ship
     *            the ship
     */
    public GetLocalOpportunitiesForCargo(final ShipCargoDTO cargo, final ShipDTO ship) {
        this.cargo = cargo;
        this.ship = ship;
    }

    /**
     * @return the cargo
     */
    public ShipCargoDTO getCargo() {
        return cargo;
    }

    /**
     * @return the ship
     */
    public ShipDTO getShip() {
        return ship;
    }

    /**
     * @param cargo
     *            the cargo to set
     */
    public void setCargo(final ShipCargoDTO cargo) {
        this.cargo = cargo;
    }

    /**
     * @param ship
     *            the ship to set
     */
    public void setShip(final ShipDTO ship) {
        this.ship = ship;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GetLocalOpportunitiesForCargo [cargo=");
        builder.append(cargo);
        builder.append(", ship=");
        builder.append(ship);
        builder.append("]");
        return builder.toString();
    }

}
