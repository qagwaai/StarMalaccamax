/**
 * Attributes.java
 * Created by pgirard at 8:13:34 PM on Aug 29, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.annotation.Embed;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
@Embed
public final class CaptainAttributes implements IsSerializable, Serializable, Model {
    /**
	 * 
	 */
    public static final String KNOWLEDGE = "Knowledge";
    /**
	 * 
	 */
    public static final String INTELLIGENCE = "Intelligence";
    /**
	 * 
	 */
    private int intelligence;
    /**
	 * 
	 */
    private int knowledge;

    /**
	 * 
	 */
    public CaptainAttributes() {

    }

    /**
     * @param intelligence
     *            the intelligence value of the captain
     * @param knowledge
     *            the knowledge value of the captain
     */
    public CaptainAttributes(final int intelligence, final int knowledge) {
        this.intelligence = intelligence;
        this.knowledge = knowledge;
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
        CaptainAttributes other = (CaptainAttributes) obj;
        if (intelligence != other.intelligence) {
            return false;
        }
        if (knowledge != other.knowledge) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @param skills
     *            the skills in a map
     */
    public void fromMap(final Map<String, Integer> skills) {
        for (String key : skills.keySet()) {
            if (key.equalsIgnoreCase(CaptainAttributes.KNOWLEDGE)) {
                setKnowledge(skills.get(key));
            }
            if (key.equalsIgnoreCase(CaptainAttributes.INTELLIGENCE)) {
                setIntelligence(skills.get(key));
            }
        }
    }

    /**
     * @return the intelligence
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * @return the knowledge
     */
    public int getKnowledge() {
        return knowledge;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + intelligence;
        result = prime * result + knowledge;
        return result;
    }

    /**
     * @param intelligence
     *            the intelligence to set
     */
    public void setIntelligence(final int intelligence) {
        this.intelligence = intelligence;
    }

    /**
     * @param knowledge
     *            the knowledge to set
     */
    public void setKnowledge(final int knowledge) {
        this.knowledge = knowledge;
    }

    /**
     * 
     * @return all skills in a map
     */
    public Map<String, Integer> toMap() {
        Map<String, Integer> values = new HashMap<String, Integer>();
        values.put(CaptainAttributes.INTELLIGENCE, getIntelligence());
        values.put(CaptainAttributes.KNOWLEDGE, getKnowledge());

        return values;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Attributes [intelligence=");
        builder.append(intelligence);
        builder.append(", knowledge=");
        builder.append(knowledge);
        builder.append("]");
        return builder.toString();
    }

}
