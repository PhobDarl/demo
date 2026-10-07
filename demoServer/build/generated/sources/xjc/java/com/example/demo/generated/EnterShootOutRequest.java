package com.example.demo.generated;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="gameId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="homeGoals" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="awayGoals" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "gameId",
    "homeGoals",
    "awayGoals"
})
@XmlRootElement(name = "enterShootOutRequest")
public class EnterShootOutRequest {

    protected long gameId;
    protected int homeGoals;
    protected int awayGoals;

    /**
     * Gets the value of the gameId property.
     * 
     */
    public long getGameId() {
        return gameId;
    }

    /**
     * Sets the value of the gameId property.
     * 
     */
    public void setGameId(long value) {
        this.gameId = value;
    }

    /**
     * Gets the value of the homeGoals property.
     * 
     */
    public int getHomeGoals() {
        return homeGoals;
    }

    /**
     * Sets the value of the homeGoals property.
     * 
     */
    public void setHomeGoals(int value) {
        this.homeGoals = value;
    }

    /**
     * Gets the value of the awayGoals property.
     * 
     */
    public int getAwayGoals() {
        return awayGoals;
    }

    /**
     * Sets the value of the awayGoals property.
     * 
     */
    public void setAwayGoals(int value) {
        this.awayGoals = value;
    }

}
