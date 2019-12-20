package de.hspf.swt.mondial.dao;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Data;

/**
 * This dao is extended via @see <a href="https://projectlombok.org/">lombok</a>.
 * 
 * @author ThomasSchuster
 */
@Entity
@Data
@Table(name = "economy")
@NamedQueries({
    @NamedQuery(name = "Economy.findAll", query = "SELECT e FROM Economy e"),
    @NamedQuery(name = "Economy.findByCountry", query = "SELECT e FROM Economy e WHERE e.country = :country"),
    @NamedQuery(name = "Economy.findByGdp", query = "SELECT e FROM Economy e WHERE e.gdp = :gdp"),
    @NamedQuery(name = "Economy.findByAgriculture", query = "SELECT e FROM Economy e WHERE e.agriculture = :agriculture"),
    @NamedQuery(name = "Economy.findByService", query = "SELECT e FROM Economy e WHERE e.service = :service"),
    @NamedQuery(name = "Economy.findByIndustry", query = "SELECT e FROM Economy e WHERE e.industry = :industry"),
    @NamedQuery(name = "Economy.findByInflation", query = "SELECT e FROM Economy e WHERE e.inflation = :inflation"),
    @NamedQuery(name = "Economy.findByUnemployment", query = "SELECT e FROM Economy e WHERE e.unemployment = :unemployment")})
public class Economy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "country")
    private String country;
    @Column(name = "gdp")
    private BigInteger gdp;
    @Column(name = "agriculture")
    private BigInteger agriculture;
    @Column(name = "service")
    private BigInteger service;
    @Column(name = "industry")
    private BigInteger industry;
    @Column(name = "inflation")
    private BigInteger inflation;
    @Column(name = "unemployment")
    private BigInteger unemployment;

    public Economy() {
    }

    public Economy(String country) {
        this.country = country;
    }
    
}
