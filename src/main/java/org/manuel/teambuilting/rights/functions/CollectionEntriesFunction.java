package org.manuel.teambuilting.rights.functions;

import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken;
import lombok.AllArgsConstructor;
import org.manuel.teambuilting.core.model.PlayerDependentEntity;
import org.manuel.teambuilting.core.repositories.PlayerDependentRepository;
import org.manuel.teambuilting.rights.AppRightConstraint;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Manuel Doncel Martos
 * @since 31/07/2017.
 */
@AllArgsConstructor
public class CollectionEntriesFunction<Entity extends PlayerDependentEntity,
        Repository extends PlayerDependentRepository>
        implements AppRightConstraint<Entity> {

    private final Predicate<Collection<Entity>> predicate;
    private final Repository repository;

    @Override
    public void isGranted(final Entity object, final AuthenticationJsonWebToken authentication) {
        final Function<Entity, Collection<Entity>> function =
                entity -> repository.findByPlayerId(entity.getPlayerId());
        final CustomEntryFunction<Entity, Collection<Entity>> collectionMatchFunction =
                CustomEntryFunction.of(function, predicate);

        collectionMatchFunction.isGranted(object, authentication);
    }

}
