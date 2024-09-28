package pl.wmsdev.sportly.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wmsdev.sportly.dto.ParticipantDTO;
import pl.wmsdev.sportly.service.ParticipantService;

@RestController
@RequestMapping("/api/v1/participant")
@RequiredArgsConstructor
public class ParticipantController {

	private final ParticipantService participantService;

	@GetMapping("/{participantId}")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Get participant by id")
	public ResponseEntity<ParticipantDTO> getParticipantById(@PathVariable Long participantId) {
		ParticipantDTO participant = participantService.getParticipantWithEventsById(participantId);
		return ResponseEntity.ok(participant);
	}

}
