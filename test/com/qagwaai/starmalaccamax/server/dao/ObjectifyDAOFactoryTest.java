/**
 * 
 */
package com.qagwaai.starmalaccamax.server.dao;

import static org.junit.Assert.fail;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author ehldxae
 *
 */
public class ObjectifyDAOFactoryTest {

	
	@Test
	public void testStaticInitialization() {
		
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY); 
		assertNotNull(factory);
	}


}
