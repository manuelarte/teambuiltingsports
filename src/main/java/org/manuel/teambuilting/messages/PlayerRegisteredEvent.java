package org.manuel.teambuilting.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author Manuel Doncel Martos
 * @since 11/01/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = PlayerRegisteredEvent.PlayerRegisteredEventBuilder.class)
@Data
@AllArgsConstructor
@Builder
public class PlayerRegisteredEvent {

    public static final String ROUTING_KEY = "player.registered";

    @NotNull
    private final BigInteger playerId;

    @NotNull
    private final String userId;

    @NotNull
    private final Date date;

    @JsonIgnore
    public String getRoutingKey() {
        return ROUTING_KEY;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class PlayerRegisteredEventBuilder {
    }
}
