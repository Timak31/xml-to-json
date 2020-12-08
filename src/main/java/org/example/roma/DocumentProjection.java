package org.example.roma;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DocumentProjection {
    private Metadata metadata;
    private ZonedDateTime createdAt;  //CreationDateTime critical
    // branch outages which are grouped by mnec+cnec+tsoOrigin
    private Map<MnecCnecTsoIdentification, CriticalBranchOutage> groupedCriticalBranches; // List of all afterCOIdList in document
    private List<String> afterCOIdList; // aggregated ComplexVariant data
    private List<ComplexVariantStats> complexVariantStatList;

    public DocumentProjection() {
    }

    public DocumentProjection(Metadata metadata, ZonedDateTime createdAt, Map<MnecCnecTsoIdentification, CriticalBranchOutage> groupedCriticalBranches, List<String> afterCOIdList, List<ComplexVariantStats> complexVariantStatList) {
        this.metadata = metadata;
        this.createdAt = createdAt;
        this.groupedCriticalBranches = groupedCriticalBranches;
        this.afterCOIdList = afterCOIdList;
        this.complexVariantStatList = complexVariantStatList;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Map<MnecCnecTsoIdentification, CriticalBranchOutage> getGroupedCriticalBranches() {
        return groupedCriticalBranches;
    }

    public void setGroupedCriticalBranches(Map<MnecCnecTsoIdentification, CriticalBranchOutage> groupedCriticalBranches) {
        this.groupedCriticalBranches = groupedCriticalBranches;
    }

    public List<String> getAfterCOIdList() {
        return afterCOIdList;
    }

    public void setAfterCOIdList(List<String> afterCOIdList) {
        this.afterCOIdList = afterCOIdList;
    }

    public List<ComplexVariantStats> getComplexVariantStatList() {
        return complexVariantStatList;
    }

    public void setComplexVariantStatList(List<ComplexVariantStats> complexVariantStatList) {
        this.complexVariantStatList = complexVariantStatList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentProjection that = (DocumentProjection) o;
        return Objects.equals(metadata, that.metadata) && Objects.equals(createdAt, that.createdAt) && Objects.equals(groupedCriticalBranches, that.groupedCriticalBranches) && Objects.equals(afterCOIdList, that.afterCOIdList) && Objects.equals(complexVariantStatList, that.complexVariantStatList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metadata, createdAt, groupedCriticalBranches, afterCOIdList, complexVariantStatList);
    }

    @Override
    public String toString() {
        return "DocumentProjection{" +
                "metadata=" + metadata +
                ", createdAt=" + createdAt +
                ", groupedCriticalBranches=" + groupedCriticalBranches +
                ", afterCOIdList=" + afterCOIdList +
                ", complexVariantStatList=" + complexVariantStatList +
                '}';
    }
}
