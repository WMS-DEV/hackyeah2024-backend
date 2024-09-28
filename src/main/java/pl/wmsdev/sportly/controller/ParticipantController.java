package pl.wmsdev.sportly.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wmsdev.sportly.dto.ParticipantDTO;
import pl.wmsdev.sportly.dto.ParticipantRequest;
import pl.wmsdev.sportly.model.Participant;
import pl.wmsdev.sportly.service.ParticipantService;

import java.net.URI;

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

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Register participant")
	@SneakyThrows
	public ResponseEntity<?> registerParticipant(@RequestBody ParticipantRequest participantRequest) {
		Participant participant = participantService.registerParticipant(participantRequest);
		return ResponseEntity.created(new URI("/api/v1/participant/" + participant.getId())).build();
	}
}
