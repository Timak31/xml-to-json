package org.example.roma;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.util.Optional;

public class CriticalBranchOutage {
    private String cbId;						// criticalBranches.criticalBranch.id
    private String cbName;						// criticalBranches.criticalBranch.branch.name
    private String outageName;					// criticalBranches.criticalBranch.branch.outage.name
    private Optional<String> from;				// criticalBranches.criticalBranch.branch.outage.branch.from
    private Optional<String> to;				// criticalBranches.criticalBranch.branch.outage.branch.to

    public String getCbId() {
        return cbId;
    }

    public void setCbId(String cbId) {
        this.cbId = cbId;
    }

    public String getCbName() {
        return cbName;
    }

    public void setCbName(String cbName) {
        this.cbName = cbName;
    }

    public String getOutageName() {
        return outageName;
    }

    public void setOutageName(String outageName) {
        this.outageName = outageName;
    }

    public Optional<String> getFrom() {
        return from;
    }

    public void setFrom(Optional<String> from) {
        this.from = from;
    }

    public Optional<String> getTo() {
        return to;
    }

    public void setTo(Optional<String> to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CriticalBranchOutage that = (CriticalBranchOutage) o;
        return Objects.equals(cbId, that.cbId) && Objects.equals(cbName, that.cbName) && Objects.equals(outageName, that.outageName) && Objects.equals(from, that.from) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cbId, cbName, outageName, from, to);
    }

    @Override
    public String toString() {
        return "CriticalBranchOutage{" +
                "cbId='" + cbId + '\'' +
                ", cbName='" + cbName + '\'' +
                ", outageName='" + outageName + '\'' +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
