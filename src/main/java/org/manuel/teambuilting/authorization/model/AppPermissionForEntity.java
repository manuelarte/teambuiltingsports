package org.manuel.teambuilting.authorization.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import org.manuel.teambuilting.authorization.permissions.AppCrudPermission;

import java.util.Set;

/**
 * @author Manuel Doncel Martos
 * @since 05/08/2017.
 */
@JsonDeserialize(builder = AppPermissionForEntity.AppPermissionForEntityBuilder.class)
@AllArgsConstructor
@Builder
public class AppPermissionForEntity {

    @Singular
    private final Set<AppCrudPermission> permissions;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AppPermissionForEntityBuilder {

    }

}
