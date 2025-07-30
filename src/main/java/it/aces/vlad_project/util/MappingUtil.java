package it.aces.vlad_project.util;

import org.mapstruct.Named;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class MappingUtil {
    @Named("mapEntitiesToIds")
    public static Set<UUID> mapEntitiesToIds(Set<?> entities) {
        if (entities == null) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(entity -> {
                    try {
                        Method getIdMethod = entity.getClass().getMethod("getId");
                        return (UUID) getIdMethod.invoke(entity);
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Entity does not have a getId method", e);
                    }
                }).collect(Collectors.toSet());
    }

}
