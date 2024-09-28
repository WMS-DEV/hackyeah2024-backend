package pl.wmsdev.sportly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wmsdev.sportly.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

	Participant findByEmail(String email);
}
