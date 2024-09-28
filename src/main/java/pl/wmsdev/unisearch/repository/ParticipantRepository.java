package pl.wmsdev.unisearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wmsdev.unisearch.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
