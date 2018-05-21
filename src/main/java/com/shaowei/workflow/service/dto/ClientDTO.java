package com.shaowei.workflow.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Client entity.
 */
public class ClientDTO implements Serializable {

    private Long id;

    private Long clientCode;

    private String companyName;

    private String location;

    private String manager;

    private String diagnostic;

    private String companyNanme;

    private String alert;

    private Integer score;

    private String businessSirteria1;

    private String businessSirteria2;

    private String businessData;

    private Long traderId;

    private Long regionId;

    private String regionName;

    private Long typeId;

    private String typeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientCode() {
        return clientCode;
    }

    public void setClientCode(Long clientCode) {
        this.clientCode = clientCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getCompanyNanme() {
        return companyNanme;
    }

    public void setCompanyNanme(String companyNanme) {
        this.companyNanme = companyNanme;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getBusinessSirteria1() {
        return businessSirteria1;
    }

    public void setBusinessSirteria1(String businessSirteria1) {
        this.businessSirteria1 = businessSirteria1;
    }

    public String getBusinessSirteria2() {
        return businessSirteria2;
    }

    public void setBusinessSirteria2(String businessSirteria2) {
        this.businessSirteria2 = businessSirteria2;
    }

    public String getBusinessData() {
        return businessData;
    }

    public void setBusinessData(String businessData) {
        this.businessData = businessData;
    }

    public Long getTraderId() {
        return traderId;
    }

    public void setTraderId(Long traderId) {
        this.traderId = traderId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClientDTO clientDTO = (ClientDTO) o;
        if (clientDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), clientDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
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
            ", trader=" + getTraderId() +
            ", region=" + getRegionId() +
            ", region='" + getRegionName() + "'" +
            ", type=" + getTypeId() +
            ", type='" + getTypeName() + "'" +
            "}";
    }
}
