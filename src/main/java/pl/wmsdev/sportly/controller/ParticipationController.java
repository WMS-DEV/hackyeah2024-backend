package pl.wmsdev.sportly.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wmsdev.sportly.dto.ParticipationRequest;
import pl.wmsdev.sportly.service.EventService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events/{eventId}")
public class ParticipationController {

	private final EventService eventService;

	@PostMapping("/join")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary = "Join event by id")
	public ResponseEntity<?> joinEvent(@PathVariable Long eventId, @RequestBody ParticipationRequest participationRequest) {
		eventService.joinEvent(eventId, participationRequest.participantId());
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/leave")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary = "Leave event by id")
	public ResponseEntity<?> leaveEvent(@PathVariable Long eventId, @RequestBody ParticipationRequest participationRequest) {
		eventService.leaveEvent(eventId, participationRequest.participantId());
		return ResponseEntity.noContent().build();
	}
}
