package pl.wmsdev.sportly.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wmsdev.sportly.dto.EventDTO;
import pl.wmsdev.sportly.dto.EventRequest;
import pl.wmsdev.sportly.model.Event;
import pl.wmsdev.sportly.service.EventService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class EventController {

	private final EventService eventService;

	@GetMapping("/events")
	public ResponseEntity<List<EventDTO>> getAllEvents() {
		List<EventDTO> events = eventService.getAllEvents();
		return ResponseEntity.ok(events);
	}

	@PostMapping("/events")
	public ResponseEntity<?> createEvent(@RequestBody EventRequest eventRequest) {

		Event event = eventService.createEvent(eventRequest);

		return ResponseEntity.created(URI.create("/api/v1/event/" + event.getId())).build();
	}

}
