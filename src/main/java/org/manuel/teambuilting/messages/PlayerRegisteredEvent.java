package org.manuel.teambuilting.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigInteger;
import java.time.Instant;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Manuel Doncel Martos
 * @since 11/01/2017.
 */
@Data
@AllArgsConstructor
@Builder
public class PlayerRegisteredEvent {

    public static final String ROUTING_KEY = "player.cud.registered";

    @NotNull
    private final BigInteger playerId;

    @NotNull
    private final String userId;

    @NotNull
    private final Instant date;

    @JsonIgnore
    public String getRoutingKey() {
        return ROUTING_KEY;
    }
}
