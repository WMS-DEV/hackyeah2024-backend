package pl.wmsdev.sportly.service;

import pl.wmsdev.sportly.dto.ParticipantDTO;
import pl.wmsdev.sportly.dto.ParticipantRequest;
import pl.wmsdev.sportly.model.Participant;

import java.util.Optional;

public interface ParticipantService {

	Optional<Participant> findParticipantById(Long id);

	ParticipantDTO getParticipantWithEventsById(Long id);

	Optional<Participant> findParticipantById(String email);

	Participant registerParticipant(ParticipantRequest participantRequest);
}
