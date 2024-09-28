package pl.wmsdev.sportly.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.wmsdev.sportly.dto.EventRequest;
import pl.wmsdev.sportly.model.Category;
import pl.wmsdev.sportly.model.Participant;
import pl.wmsdev.sportly.repository.CategoryRepository;
import pl.wmsdev.sportly.repository.EventRepository;
import pl.wmsdev.sportly.repository.ParticipantRepository;
import pl.wmsdev.sportly.service.EventService;
import pl.wmsdev.sportly.values.AgeGroup;
import pl.wmsdev.sportly.values.Cycle;
import pl.wmsdev.sportly.values.RequiredExperience;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {

	private final static String CATEGORIES_PATH = "/mockdata/categories.csv";
	private final static String PARTICIPANTS_PATH = "/mockdata/participants.csv";
	private final static String EVENTS_PATH = "/mockdata/eventRequests.csv";

	private final EventRepository eventRepository;
	private final CategoryRepository categoryRepository;
	private final ParticipantRepository participantRepository;

	private final EventService eventService;

	@Override
	public void run(String... args) throws Exception {
		eventRepository.deleteAll();
		categoryRepository.deleteAll();
		participantRepository.deleteAll();

		loadCategories();
		loadParticipants();
		loadEvents();
	}

	@SneakyThrows
	private void loadCategories() {
		InputStream categoriesStream = getClass().getResourceAsStream(CATEGORIES_PATH);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(categoriesStream, StandardCharsets.UTF_8))) {
			String line;

			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				addCategory(data[0], Long.parseLong(data[1]));
			}
		}
	}

	private void addCategory(String name, Long calories) {
		Category category = Category.builder()
				.name(name)
				.caloriesPerHour(calories)
				.build();

		categoryRepository.save(category);
	}

	@SneakyThrows
	private void loadParticipants() {
		InputStream participantsPath = getClass().getResourceAsStream(PARTICIPANTS_PATH);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(participantsPath, StandardCharsets.UTF_8))) {
			String line;

			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				addParticipant(data[0], data[1]);
			}
		}
	}

	private void addParticipant(String name, String email) {
		Participant participant = Participant.builder()
				.name(name)
				.email(email)
				.build();

		participantRepository.save(participant);
	}

	@SneakyThrows
	private void loadEvents() {
		InputStream eventsPath = getClass().getResourceAsStream(EVENTS_PATH);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(eventsPath, StandardCharsets.UTF_8))) {
			String line = reader.readLine();

			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				EventRequest eventRequest = EventRequest.builder()
						.name(data[0])
						.categoryId(Long.parseLong(data[1]))
						.creatorId(Long.parseLong(data[2]))
						.latitude(Double.parseDouble(data[3]))
						.longitude(Double.parseDouble(data[4]))
						.description(data[5])
						.startTime(Long.parseLong(data[6]))
						.endTime(Long.parseLong(data[7]))
						.cyclic(Cycle.valueOf(data[8]))
						.maxNumberOfParticipants(Integer.parseInt(data[9]))
						.inviteEmails(List.of(data[10].split(";")))
						.isPublic(Boolean.parseBoolean(data[11]))
						.requiredExperience(RequiredExperience.valueOf(data[12]))
						.age(AgeGroup.valueOf(data[13]))
						.build();

				eventService.createEvent(eventRequest);
			}
		}
	}
}
