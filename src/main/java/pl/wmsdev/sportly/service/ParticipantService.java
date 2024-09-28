package pl.wmsdev.sportly.service;

import pl.wmsdev.sportly.model.Participant;

import java.util.Optional;

public interface ParticipantService {

	Optional<Participant> findParticipantById(Long id);

	Optional<Participant> findParticipantByEmail(String email);
}
