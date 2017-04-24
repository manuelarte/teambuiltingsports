package org.manuel.teambuilting.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Manuel Doncel Martos
 * @since 11/01/2017.
 */
@Data
@AllArgsConstructor
public class TeamDeletedEvent {

    public static final String ROUTING_KEY = "team.cud.deleted";

    @NotNull
    private final String teamId;

    private final String userId;

    @NotNull
    private final Date date;

    @JsonIgnore
    public String getRoutingKey() {
        return ROUTING_KEY;
    }
}
