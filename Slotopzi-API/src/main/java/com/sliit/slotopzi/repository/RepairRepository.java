package com.sliit.slotopzi.repository;

import com.sliit.slotopzi.enums.RepairStatus;
import com.sliit.slotopzi.enums.RepairType;
import com.sliit.slotopzi.enums.ServiceEntryStatus;
import com.sliit.slotopzi.model.Repair;
import com.sliit.slotopzi.model.Section;
import com.sliit.slotopzi.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RepairRepository extends JpaRepository<Repair, Long> {
    Repair findByRepairId(long repairId);
    Repair findByStatusAndAndVehicle(RepairStatus status, Vehicle vehicle);
    Repair findByVehicleAndStatusIsNot(Vehicle vehicle,RepairStatus status);
    List<Repair> findAllByStaff_StaffIdAndStatusIsNot(long staffId,RepairStatus status);
    List<Repair> findAllByStaff_StaffIdAndStatusIs(long staffId,RepairStatus status);
    List<Repair> findAllByStatusAndVehicleVehicleId(RepairStatus status,long vehicleId);
    List<Repair> findAllByStatusAndVehicle_Customer_UserData_id(RepairStatus status,long userId);
    List<Repair> findAllByVehicle_VehicleIdAndRepairTypeOrderByRepairAddedDateDesc(long vehicleId, RepairType type);
    List<Repair> findDistinctByServiceEntries_ServiceEntryStatusAndServiceEntries_SubCategory_Section(ServiceEntryStatus status, Section section);
    List<Repair> findAllByRepairCompletedDateIsBefore(Date date);
    List<Repair> findAllByStatus(RepairStatus status);
}
