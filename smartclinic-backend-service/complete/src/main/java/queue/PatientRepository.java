package queue;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PatientRepository extends CrudRepository<Patient, Long> {
	
	@Query("select p from Patient p where p.firstName=?1 and p.lastName=?2")
	List<Patient> findByFirstNameAndLastname(String firstName, String lastName);
	
	@Query("select p from Patient p where p.email=?1")
	List<Patient> findByEmail(String email);
}
