package pl.wmsdev.sportly.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wmsdev.sportly.dto.EventDTO;
import pl.wmsdev.sportly.dto.EventRequest;
import pl.wmsdev.sportly.mapper.EventMapper;
import pl.wmsdev.sportly.model.Category;
import pl.wmsdev.sportly.model.Event;
import pl.wmsdev.sportly.model.Participant;
import pl.wmsdev.sportly.repository.EventRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

	private final EventRepository eventRepository;
	private final ParticipantService participantService;
	private final CategoryService categoryService;
	private final EventMapper eventMapper;

	@Override
	public List<EventDTO> getAllEvents() {
		return eventRepository.findAll().stream()
				.map(eventMapper::eventToEventDTO)
				.toList();
	}

	@Override
	public EventDTO getEventById(Long id) {
		return eventRepository.findById(id)
				.map(eventMapper::eventToEventDTO)
				.orElseThrow();
	}

	@Override
	public Event createEvent(EventRequest eventRequest) {

		Participant creator = participantService.findParticipantById(eventRequest.creatorId());
		Category category = categoryService.getCategoryById(eventRequest.categoryId());

		Event event = Event.builder()
				.name(eventRequest.name())
				.category(category)
				.creator(creator)
				.description(eventRequest.description())
				.startTime(eventRequest.startTime())
				.endTime(eventRequest.endTime())
				.cyclic(eventRequest.cyclic())
				.maxParticipants(eventRequest.maxNumberOfParticipants())
				.isPublic(eventRequest.isPublic())
				.requiredExperience(eventRequest.requiredExperience())
				.ageGroup(eventRequest.age())
				.latitude(eventRequest.latitude())
				.longitude(eventRequest.longitude())
				.build();

		event.addParticipant(creator);

		for (String email : eventRequest.inviteEmails()) {
			participantService.findParticipantByEmail(email).ifPresent(event::addParticipant);
		}

		return eventRepository.save(event);
	}
}
