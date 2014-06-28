/**
 * OpportunityTreeNode.java
 * Created by pgirard at 1:12:39 PM on Jan 25, 2011
 * in the com.qagwaai.starmalaccamax.client.data package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import com.qagwaai.starmalaccamax.client.game.mvp.PlayerOpportunitiesViewImpl;
import com.smartgwt.client.widgets.tree.TreeNode;

/**
 * @author pgirard
 * @deprecated
 */
public final class OpportunityTreeNode extends TreeNode {
    /**
	 * 
	 */
    private boolean loaded = false;
    /**
	 * 
	 */
    private String nodeType;

    /**
	 * 
	 */
    public OpportunityTreeNode() {

    }

    /**
     * @param name
     *            the ship name
     */
    public OpportunityTreeNode(final String name) {
        setName(name);
    }

    /**
     * @return the name
     */
    public String getName() {
        return getAttributeAsString(PlayerOpportunitiesViewImpl.SHIP_NAME);
    }

    /**
     * @return the nodeType
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * @return the loaded
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * @param loaded
     *            the loaded to set
     */
    public void setLoaded(final boolean loaded) {
        this.loaded = loaded;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        setAttribute(PlayerOpportunitiesViewImpl.SHIP_NAME, name);
    }

    /**
     * @param nodeType
     *            the nodeType to set
     */
    public void setNodeType(final String nodeType) {
        this.nodeType = nodeType;
    }

}
