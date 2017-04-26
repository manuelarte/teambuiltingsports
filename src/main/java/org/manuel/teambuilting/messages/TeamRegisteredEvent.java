package org.manuel.teambuilting.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Instant;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Manuel Doncel Martos
 * @since 11/01/2017.
 */
@Data
@AllArgsConstructor
public class TeamRegisteredEvent {

    public static final String ROUTING_KEY = "team.cud.registered";

    @NotNull
    private final String teamId;

    private final String userId;

    @NotNull
    private final Instant date;

    @JsonIgnore
    public String getRoutingKey() {
        return ROUTING_KEY;
    }
}
