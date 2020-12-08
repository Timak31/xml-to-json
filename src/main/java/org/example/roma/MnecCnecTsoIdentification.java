package org.example.roma;

import java.util.Objects;

public class MnecCnecTsoIdentification {
    private Boolean mnec;			// criticalBranches.criticalBranch.MNEC
    private Boolean cnec;			// criticalBranches.criticalBranch.CNEC
    private TsoOrigin tsoOrigin;	// criticalBranches.criticalBranch.tsoOrigin

    public Boolean getMnec() {
        return mnec;
    }

    public void setMnec(Boolean mnec) {
        this.mnec = mnec;
    }

    public Boolean getCnec() {
        return cnec;
    }

    public void setCnec(Boolean cnec) {
        this.cnec = cnec;
    }

    public TsoOrigin getTsoOrigin() {
        return tsoOrigin;
    }

    public void setTsoOrigin(TsoOrigin tsoOrigin) {
        this.tsoOrigin = tsoOrigin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MnecCnecTsoIdentification that = (MnecCnecTsoIdentification) o;
        return Objects.equals(mnec, that.mnec) && Objects.equals(cnec, that.cnec) && tsoOrigin == that.tsoOrigin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mnec, cnec, tsoOrigin);
    }

    @Override
    public String toString() {
        return "MnecCnecTsoIdentification{" +
                "mnec=" + mnec +
                ", cnec=" + cnec +
                ", tsoOrigin=" + tsoOrigin +
                '}';
    }
}
