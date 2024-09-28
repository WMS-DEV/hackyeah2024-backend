package pl.wmsdev.sportly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wmsdev.sportly.model.Participant;

import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

	Optional<Participant> findByEmail(String email);
}
