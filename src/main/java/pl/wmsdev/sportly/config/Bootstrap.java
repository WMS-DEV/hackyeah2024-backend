package pl.wmsdev.sportly.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.wmsdev.sportly.model.Category;
import pl.wmsdev.sportly.model.Event;
import pl.wmsdev.sportly.model.Participant;
import pl.wmsdev.sportly.repository.CategoryRepository;
import pl.wmsdev.sportly.repository.EventRepository;
import pl.wmsdev.sportly.repository.ParticipantRepository;
import pl.wmsdev.sportly.values.AgeGroup;
import pl.wmsdev.sportly.values.Cycle;
import pl.wmsdev.sportly.values.RequiredExperience;

import java.util.List;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {

	private final EventRepository eventRepository;
	private final CategoryRepository categoryRepository;
	private final ParticipantRepository participantRepository;

	@Override
	public void run(String... args) throws Exception {
		eventRepository.deleteAll();
		categoryRepository.deleteAll();
		participantRepository.deleteAll();

		addCategory("Soccer", 500L);
		addCategory("Basketball", 450L);
		addCategory("Running", 600L);
		addCategory("Tennis", 450L);

		addParticipant("Maciek", "maciek@gmail.com");
		addParticipant("Szymon", "szymon@gmail.com");
		addParticipant("Tymek", "tymek@gmail.com");

		Participant participant = participantRepository.findAll().get(0);
		Category category = categoryRepository.findAll().get(1);

		Event event = Event.builder()
				.creator(participant)
				.name("Gramy w kosza")
				.description("Czwartek to super dzień na koszykówkę")
				.cyclic(Cycle.WEEKLY)
				.ageGroup(AgeGroup.ALL)
				.startTime(System.currentTimeMillis())
				.endTime(System.currentTimeMillis() + 3600)
				.requiredExperience(RequiredExperience.NOVICE)
				.maxParticipants(8)
				.isPublic(true)
				.latitude(34.6)
				.longitude(34.1)
				.build();

		eventRepository.saveAndFlush(event);
		event.addParticipant(participant);
		event.setCategory(category);

		log.info("Event: {}", event);

		eventRepository.save(event);
	}

	private void addCategory(String name, Long calories) {
		Category category = Category.builder()
				.name(name)
				.caloriesPerHour(calories)
				.build();

		categoryRepository.save(category);
	}

	private void addParticipant(String name, String email) {
		Participant participant = Participant.builder()
				.name(name)
				.email(email)
				.build();

		participantRepository.save(participant);
	}
}
