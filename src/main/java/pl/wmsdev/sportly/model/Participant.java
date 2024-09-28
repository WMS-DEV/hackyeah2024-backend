package pl.wmsdev.sportly.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Participant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String email;

	@ManyToMany(mappedBy = "participants", fetch = FetchType.EAGER)
	@Builder.Default
	@ToString.Exclude
	private Set<Event> events = new HashSet<>();
}
