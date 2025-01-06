package com.sliit.slotopzi.repository;

import com.sliit.slotopzi.model.Section;
import com.sliit.slotopzi.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    SubCategory findBySubCatNameAndSection(String subCatName, Section section);
    SubCategory findBySubCatId(long subCatId);
    SubCategory findBySubCatName(String subName);
    List<SubCategory> findAllBySection(Section section);
}
