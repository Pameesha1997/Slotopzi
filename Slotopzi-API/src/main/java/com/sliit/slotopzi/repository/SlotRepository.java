package com.sliit.slotopzi.repository;

import com.sliit.slotopzi.enums.SlotStatus;
import com.sliit.slotopzi.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {
    Slot findBySlotName(String slotName);
    Slot findByStatus(SlotStatus status);
    List<Slot> findAllBySection_SectionName(String sectionName);
    List<Slot> findAllBySection_SectionNameAndStatusIsNot(String sectionName, SlotStatus status);
    List<Slot> findAllBySection_SectionNameAndStatusIs(String sectionName, SlotStatus status);
    @Query(value = "SELECT COUNT(slotid) FROM `slot` WHERE section_id=?1",nativeQuery = true)
    int getTotalCount(long sectionId);
    @Query(value = "SELECT COUNT(slotid) FROM `slot` WHERE section_id=?1 AND status='AVAILABLE'",nativeQuery = true)
    int getFreeSlotCount(long sectionId);
    @Query(value = "SELECT COUNT(slotid) FROM `slot` WHERE section_id=?1 AND status='NOTAVAILABLE'",nativeQuery = true)
    int getNotAvailSlots(long sectionId);
}
