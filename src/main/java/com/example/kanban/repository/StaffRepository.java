package com.example.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.kanban.entity.Staff;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

/**
 * @author Deepak Devaraj
 * This is the JPA Repository for the Staff Entity
 */
@Repository
public interface StaffRepository extends JpaRepository<Staff , Long> {
	public Staff findByStaffId(@Param("staffId") Long staffId);
	public Staff findByStaffName(@Param("staffName") String staffName);
}
