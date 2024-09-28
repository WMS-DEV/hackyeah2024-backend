package pl.wmsdev.sportly.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wmsdev.sportly.dto.EventDTO;
import pl.wmsdev.sportly.dto.EventRequest;
import pl.wmsdev.sportly.model.Event;
import pl.wmsdev.sportly.service.EventService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
@Slf4j
public class EventController {

	private final EventService eventService;

	@GetMapping
	@Operation(summary = "Get all events")
	public ResponseEntity<List<EventDTO>> getAllEvents() {
		List<EventDTO> events = eventService.getAllEvents();
		return ResponseEntity.ok(events);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get event by id")
	public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
		EventDTO event = eventService.getEventById(id);
		return ResponseEntity.ok(event);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Create new event")
	public ResponseEntity<?> createEvent(@RequestBody EventRequest eventRequest) {
		Event event = eventService.createEvent(eventRequest);
		return ResponseEntity.created(URI.create("/api/v1/events/" + event.getId())).build();
	}

}
