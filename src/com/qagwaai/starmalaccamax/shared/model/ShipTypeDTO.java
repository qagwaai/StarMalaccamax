/**
 * ShipTypeDTO.java
 * Created by pgirard at 7:38:32 AM on Nov 5, 2010
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
public final class ShipTypeDTO implements IsSerializable, Serializable, ShipType {

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
    @com.googlecode.objectify.annotation.Index
    private String name;

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
    public String getName() {
        return name;
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
    public void setName(final String name) {
        this.name = name;
    }
    public static String getFieldGetter(String fieldName) {
    	String methodName = null;
    	
    	if (fieldName.equals("id")) {
    		methodName = "getId";
    	} else if (fieldName.equals("name")) {
    		methodName = "getName";
    	}
    	return methodName;
    }

}
