package org.artem.courses.mapper;

import org.artem.courses.dto.ResourceDTO;
import org.artem.courses.entity.Resource;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public abstract class ResourceMapper {
    public abstract ResourceDTO toDTO(Resource resource);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateResourceFromDTO(ResourceDTO resourceDTO, @MappingTarget Resource entity);

}
