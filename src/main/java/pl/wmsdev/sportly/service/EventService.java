package pl.wmsdev.sportly.service;

import pl.wmsdev.sportly.dto.EventDTO;

import java.util.List;

public interface EventService {

	List<EventDTO> getAllEvents();
}
