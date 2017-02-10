package queue;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
	
	@Query("select d from Doctor d where d.firstName=?1 and d.lastName=?2")
	List<Doctor> findByFirstNameAndLastname(String firstName, String lastName);
	
	@Query("select d from Doctor d where d.email=?1")
	List<Doctor> findByEmail(String email);
	
	@Query("select d.firstName,d.lastName,d.qualification,c.clinicName, dc.startTime, dc.endTime from Doctor d, Clinics c, DoctorClinics dc where dc.clinicID = c.id and dc.doctorID = d.id and c.id=?1")
	List<Object> findByClinic(Integer id);
	
}
