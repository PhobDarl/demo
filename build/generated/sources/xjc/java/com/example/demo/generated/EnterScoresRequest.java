package com.example.demo.generated;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="homeTeam" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="awayTeam" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "homeTeam",
    "awayTeam",
    "homeGoals",
    "awayGoals"
})
@XmlRootElement(name = "enterScoresRequest")
public class EnterScoresRequest {

    @XmlElement(required = true)
    protected String homeTeam;
    @XmlElement(required = true)
    protected String awayTeam;
    protected int homeGoals;
    protected int awayGoals;

    /**
     * Gets the value of the homeTeam property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeTeam() {
        return homeTeam;
    }

    /**
     * Sets the value of the homeTeam property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeTeam(String value) {
        this.homeTeam = value;
    }

    /**
     * Gets the value of the awayTeam property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAwayTeam() {
        return awayTeam;
    }

    /**
     * Sets the value of the awayTeam property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAwayTeam(String value) {
        this.awayTeam = value;
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
