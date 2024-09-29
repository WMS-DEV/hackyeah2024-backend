package pl.wmsdev.sportly.service;

import pl.wmsdev.sportly.dto.EventDTO;
import pl.wmsdev.sportly.dto.EventRequest;
import pl.wmsdev.sportly.dto.ParticipationRequest;
import pl.wmsdev.sportly.model.Event;

import java.util.List;

public interface EventService {

	List<EventDTO> getAllEvents();

	EventDTO getEventById(Long id);

	void deleteEvent(Long id);

	Event createEvent(EventRequest eventRequest);

	void joinEvent(Long eventId, ParticipationRequest participationRequest);

	void leaveEvent(Long eventId, ParticipationRequest participationRequest);
}
