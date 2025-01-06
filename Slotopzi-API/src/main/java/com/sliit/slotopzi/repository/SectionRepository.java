package com.sliit.slotopzi.repository;

import com.sliit.slotopzi.model.Section;
import com.sliit.slotopzi.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Long> {
    Section findBySectionName(String sectionName);
    Section findBySectionId(long sectionId);
    Section findByStaff(Staff staff);
    Section findByStaff_UserData_id(long userid);
//     List<Section> findAllByStaff(Staff staff);
}
