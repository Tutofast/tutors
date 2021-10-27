package com.arkisoftware.tutofast.tutors.config;

import com.arkisoftware.tutofast.tutors.command.domain.Tutor;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
    @Bean
    public Repository<Tutor> eventSourcingRepository(EventStore eventStore){
        return EventSourcingRepository.builder(Tutor.class)
                .eventStore(eventStore)
                .build();
    }
}
