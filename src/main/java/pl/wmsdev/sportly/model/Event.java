package pl.wmsdev.sportly.model;

import jakarta.persistence.*;
import lombok.*;
import pl.wmsdev.sportly.values.AgeGroup;
import pl.wmsdev.sportly.values.Cycle;
import pl.wmsdev.sportly.values.RequiredExperience;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@ManyToOne
	private Participant creator;

	@ManyToOne
	private Category category;

	private String description;
	// in ms from the epoch
	private Long startTime;
	private Long endTime;
	private Double latitude;
	private Double longitude;
	private Cycle cyclic;
	private Integer maxParticipants;

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name = "particpant_events",
		joinColumns = @JoinColumn(name = "event_id"),
		inverseJoinColumns = @JoinColumn(name = "participant_id"))
	@ToString.Exclude
	@Builder.Default
	private Set<Participant> participants = new HashSet<>();
	private Boolean isPublic;
	private RequiredExperience requiredExperience;
	private AgeGroup ageGroup;

	public void addParticipant(Participant participant) {
		participants.add(participant);
		participant.getEvents().add(this);
	}
}
