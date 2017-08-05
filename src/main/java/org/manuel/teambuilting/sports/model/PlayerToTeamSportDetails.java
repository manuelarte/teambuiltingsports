/**
 * 
 */
package org.manuel.teambuilting.sports.model;

import com.mongodb.annotations.Immutable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.manuel.teambuilting.core.model.IdAndPlayerDependentEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Set;

/**
 * @author Manuel Doncel Martos
 *
 */
@Document
@Immutable
@Getter
@lombok.Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerToTeamSportDetails implements IdAndPlayerDependentEntity<String> {

	@Id
	private String id;
	
	@NotNull
	private BigInteger playerId;
	
	@NotNull
	@Indexed
	private String sport;
	
	private String bio;
	
	@NotNull
	@Indexed
	private String mainPosition;
	
	private Set<String> otherPositions;

	@PersistenceConstructor
	public PlayerToTeamSportDetails(final BigInteger playerId, final String sport, final String bio, final String mainPosition, final Set<String> otherPositions) {
		this.playerId = playerId;
		this.sport = sport;
		this.bio = bio; 
		this.mainPosition = mainPosition;
		this.otherPositions = otherPositions;
	}

}