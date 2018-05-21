package com.shaowei.workflow.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Client.
 */
@Entity
@Table(name = "client")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_code")
    private Long clientCode;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "location")
    private String location;

    @Column(name = "manager")
    private String manager;

    @Column(name = "diagnostic")
    private String diagnostic;

    @Column(name = "company_nanme")
    private String companyNanme;

    @Column(name = "alert")
    private String alert;

    @Column(name = "score")
    private Integer score;

    @Column(name = "business_sirteria_1")
    private String businessSirteria1;

    @Column(name = "business_sirteria_2")
    private String businessSirteria2;

    @Column(name = "business_data")
    private String businessData;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Trader trader;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Region region;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Type type;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientCode() {
        return clientCode;
    }

    public Client clientCode(Long clientCode) {
        this.clientCode = clientCode;
        return this;
    }

    public void setClientCode(Long clientCode) {
        this.clientCode = clientCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Client companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public Client location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getManager() {
        return manager;
    }

    public Client manager(String manager) {
        this.manager = manager;
        return this;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public Client diagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
        return this;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getCompanyNanme() {
        return companyNanme;
    }

    public Client companyNanme(String companyNanme) {
        this.companyNanme = companyNanme;
        return this;
    }

    public void setCompanyNanme(String companyNanme) {
        this.companyNanme = companyNanme;
    }

    public String getAlert() {
        return alert;
    }

    public Client alert(String alert) {
        this.alert = alert;
        return this;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public Integer getScore() {
        return score;
    }

    public Client score(Integer score) {
        this.score = score;
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getBusinessSirteria1() {
        return businessSirteria1;
    }

    public Client businessSirteria1(String businessSirteria1) {
        this.businessSirteria1 = businessSirteria1;
        return this;
    }

    public void setBusinessSirteria1(String businessSirteria1) {
        this.businessSirteria1 = businessSirteria1;
    }

    public String getBusinessSirteria2() {
        return businessSirteria2;
    }

    public Client businessSirteria2(String businessSirteria2) {
        this.businessSirteria2 = businessSirteria2;
        return this;
    }

    public void setBusinessSirteria2(String businessSirteria2) {
        this.businessSirteria2 = businessSirteria2;
    }

    public String getBusinessData() {
        return businessData;
    }

    public Client businessData(String businessData) {
        this.businessData = businessData;
        return this;
    }

    public void setBusinessData(String businessData) {
        this.businessData = businessData;
    }

    public Trader getTrader() {
        return trader;
    }

    public Client trader(Trader trader) {
        this.trader = trader;
        return this;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public Region getRegion() {
        return region;
    }

    public Client region(Region region) {
        this.region = region;
        return this;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Type getType() {
        return type;
    }

    public Client type(Type type) {
        this.type = type;
        return this;
    }

    public void setType(Type type) {
        this.type = type;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Client client = (Client) o;
        if (client.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), client.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Client{" +
            "id=" + getId() +
            ", clientCode=" + getClientCode() +
            ", companyName='" + getCompanyName() + "'" +
            ", location='" + getLocation() + "'" +
            ", manager='" + getManager() + "'" +
            ", diagnostic='" + getDiagnostic() + "'" +
            ", companyNanme='" + getCompanyNanme() + "'" +
            ", alert='" + getAlert() + "'" +
            ", score=" + getScore() +
            ", businessSirteria1='" + getBusinessSirteria1() + "'" +
            ", businessSirteria2='" + getBusinessSirteria2() + "'" +
            ", businessData='" + getBusinessData() + "'" +
            "}";
    }
}
