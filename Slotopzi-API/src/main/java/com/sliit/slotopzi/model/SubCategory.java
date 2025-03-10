package com.sliit.slotopzi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sub_category")
public class SubCategory {

    @Id
    @GeneratedValue
    private long subCatId;

    @Column(nullable = false)
    private int time;

    @Column(nullable = false)
    private String subCatName;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "section_id")
    Section section;

    @OneToMany(targetEntity = ServiceEntry.class, mappedBy = "subCategory", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ServiceEntry> serviceEntries;

    public long getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(long subCatId) {
        this.subCatId = subCatId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSubCatName() {
        return subCatName;
    }

    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Set<ServiceEntry> getServiceEntries() {
        return serviceEntries;
    }

    public void setServiceEntries(Set<ServiceEntry> serviceEntries) {
        this.serviceEntries = serviceEntries;
    }
}
