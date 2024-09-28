package pl.wmsdev.sportly.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wmsdev.sportly.model.Participant;
import pl.wmsdev.sportly.repository.ParticipantRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

	private final ParticipantRepository participantRepository;

	@Override
	public Participant findParticipantById(Long id) {
		return participantRepository.findById(id).orElseThrow();
	}

	@Override
	public Optional<Participant> findParticipantByEmail(String email) {
		return participantRepository.findByEmail(email);
	}
}
