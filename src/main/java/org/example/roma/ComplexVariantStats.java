package org.example.roma;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Objects;

public class ComplexVariantStats {
    private String tsoOrigin;					// complexVariants.complexVariant.tsoOrigin
    private Integer actionsQty;					// complexVariants.complexVariant.actionsSet.action - all actions which are related to given tsoOrigin
    private String complexVariant;				// complexVariants.complexVariant.id

    public String getTsoOrigin() {
        return tsoOrigin;
    }

    public void setTsoOrigin(String tsoOrigin) {
        this.tsoOrigin = tsoOrigin;
    }

    public Integer getActionsQty() {
        return actionsQty;
    }

    public void setActionsQty(Integer actionsQty) {
        this.actionsQty = actionsQty;
    }

    public String getComplexVariant() {
        return complexVariant;
    }

    public void setComplexVariant(String complexVariant) {
        this.complexVariant = complexVariant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexVariantStats that = (ComplexVariantStats) o;
        return Objects.equals(tsoOrigin, that.tsoOrigin) && Objects.equals(actionsQty, that.actionsQty) && Objects.equals(complexVariant, that.complexVariant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tsoOrigin, actionsQty, complexVariant);
    }

    @Override
    public String toString() {
        return "ComplexVariantStats{" +
                "tsoOrigin='" + tsoOrigin + '\'' +
                ", actionsQty=" + actionsQty +
                ", complexVariant='" + complexVariant + '\'' +
                '}';
    }
}
