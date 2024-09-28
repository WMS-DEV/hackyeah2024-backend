package pl.wmsdev.sportly.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.firewall.RequestRejectedException;
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

	private static final int MILLIS_IN_HOUR = 3_600_000;

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
	public void deleteEvent(Long id) {
		eventRepository.deleteById(id);
	}

	@Override
	public Event createEvent(EventRequest eventRequest) {

		Participant creator = participantService.findParticipantById(eventRequest.creatorId()).orElseThrow();
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
				.calories(calculateCalories(category, eventRequest.startTime(), eventRequest.endTime()))
				.build();

		event.addParticipant(creator);

		for (String email : eventRequest.inviteEmails()) {
			participantService.findParticipantById(email).ifPresent(event::addParticipant);
		}

		return eventRepository.save(event);
	}

	private Integer calculateCalories(Category category, Long startTime, Long endTime) {
		return (int) (category.getCaloriesPerHour() * (endTime - startTime) / MILLIS_IN_HOUR);
	}

	@Override
	public void joinEvent(Long eventId, Long participantId) {
		Event event = eventRepository.findById(eventId).orElseThrow();
		Participant participant = participantService.findParticipantById(participantId).orElseThrow();

		if (event.getMaxParticipants() == event.getParticipants().size()) {
			throw new RequestRejectedException("Event is full");
		}

		event.addParticipant(participant);
		eventRepository.save(event);
	}

	@Override
	public void leaveEvent(Long eventId, Long participantId) {
		Event event = eventRepository.findById(eventId).orElseThrow();
		Participant participant = participantService.findParticipantById(participantId).orElseThrow();

		if (participant.equals(event.getCreator())) {
			throw new RequestRejectedException("Creator cannot leave the event, cancel it instead");
		}

		event.removeParticipant(participant);
		eventRepository.save(event);
	}
}
