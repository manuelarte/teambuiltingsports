package org.manuel.teambuilting.authorization.functions;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.authorization.rights.AppRightConstraint;
import org.manuel.teambuilting.core.model.PlayerDependentEntity;
import org.manuel.teambuilting.core.repositories.PlayerDependentRepository;
import org.springframework.security.core.Authentication;

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
    public boolean isGranted(final Entity object, final Authentication authentication) {
        final Function<Entity, Collection<Entity>> function =
                entity -> repository.findByPlayerId(entity.getPlayerId());
        final CustomEntryFunction<Entity, Collection<Entity>> collectionMatchFunction =
                CustomEntryFunction.of(function, predicate);

        return collectionMatchFunction.isGranted(object, authentication);
    }

}
