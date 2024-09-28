package pl.wmsdev.sportly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wmsdev.sportly.dto.EventDTO;
import pl.wmsdev.sportly.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

	private final EventService eventService;

	@GetMapping
	public List<EventDTO> getAllEvents() {
		return eventService.getAllEvents();
	}

}
