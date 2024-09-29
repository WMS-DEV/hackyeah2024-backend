package pl.wmsdev.sportly.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wmsdev.sportly.dto.ParticipantDTO;
import pl.wmsdev.sportly.dto.ParticipantRequest;
import pl.wmsdev.sportly.mapper.ParticipantMapper;
import pl.wmsdev.sportly.model.Participant;
import pl.wmsdev.sportly.repository.ParticipantRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

	private final ParticipantRepository participantRepository;
	private final ParticipantMapper participantMapper;

	@Override
	public Optional<Participant> findParticipantById(Long id) {
		return participantRepository.findById(id);
	}

	@Override
	public Optional<Participant> findParticipantByEmail(String email) {
		return participantRepository.findByEmail(email);
	}

	@Override
	public Participant registerParticipant(ParticipantRequest participantRequest) {
		Participant participant = Participant.builder()
				.name(participantRequest.name())
				.email(participantRequest.email())
				.build();
		return participantRepository.save(participant);
	}

	@Override
	public ParticipantDTO getParticipantWithEventsById(Long id) {
		return participantMapper.participantToParticipantWithEventsDTO(participantRepository.findById(id).orElseThrow());
	}
}
