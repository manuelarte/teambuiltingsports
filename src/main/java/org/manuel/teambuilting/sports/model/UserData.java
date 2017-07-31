package org.manuel.teambuilting.sports.model;

import com.mongodb.annotations.Immutable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.manuel.teambuilting.core.model.PlayerDependentEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

/**
 * @author Manuel on 11/12/2016.
 */
@Immutable
@Builder(toBuilder = true)
@Document
@Getter
@Setter
@NoArgsConstructor
public class UserData implements PlayerDependentEntity {

    @Id
    private String userId;

    @NotNull
    private BigInteger playerId;

    @PersistenceConstructor
    public UserData(final String userId, final BigInteger playerId) {
        this.userId = userId;
        this.playerId = playerId;
    }

}
