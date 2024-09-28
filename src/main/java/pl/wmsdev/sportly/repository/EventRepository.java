package pl.wmsdev.sportly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wmsdev.sportly.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
