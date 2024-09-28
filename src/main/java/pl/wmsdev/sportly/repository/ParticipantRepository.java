package pl.wmsdev.sportly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wmsdev.sportly.model.Participant;

import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

	@Query("SELECT p FROM Participant p WHERE p.email=:email")
	Optional<Participant> findByEmail(String email);
}
