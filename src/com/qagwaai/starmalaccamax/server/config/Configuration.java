package com.qagwaai.starmalaccamax.server.config;



public class Configuration {
	
	private int daoFactory;
    /**
     * Constructor
     */
    private Configuration() {
    }

    private static Configuration ref;

    /**
     * singleton - get instance
     * 
     * @return the Application singleton
     */
    public static Configuration getInstance() {
        if (ref == null) {
            ref = new Configuration();
        }
        return ref;
    }
    
    public int getDAOFactory() {
    	return daoFactory;
    }

	public void setDAOFactory(int factory) {
		daoFactory = factory;
	}
}
