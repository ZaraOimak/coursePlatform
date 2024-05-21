package org.artem.courses.mapper;

import org.artem.courses.dto.BlockDTO;
import org.artem.courses.dto.ResourceDTO;
import org.artem.courses.entity.Block;
import org.artem.courses.entity.Resource;
import org.artem.courses.entity.Section;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = ResourceMapper.class)
public abstract class BlockMapper {
    @Autowired
    private ResourceMapper resourceMapper;

    public abstract BlockDTO toDTO(Block block);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "resources", expression = "java(updateResources(blockDTO,entity))")
    public abstract void updateBlockFromDTO(BlockDTO blockDTO, @MappingTarget Block entity);


    protected List<Resource> updateResources(BlockDTO blockDTO, @MappingTarget Block entity) {

        if (blockDTO.getResources() == null) {
            return entity.getResources();
        }
        List<Resource> resources = entity.getResources();
        if (blockDTO.getResources().isEmpty()) {
            resources.clear();
        } else {
            Set<UUID> updatedUuids = blockDTO.getResources().stream().map(ResourceDTO::getUuid).collect(Collectors.toSet());


            for (ResourceDTO resourceDTO : blockDTO.getResources()) {
                Resource resource = getOrCreateResource(entity, resourceDTO.getUuid());
                resourceMapper.updateResourceFromDTO(resourceDTO, resource);
                if (resource.getId() == null) {
                    updatedUuids.add(resource.getUuid());
                    resources.add((resource));
                }
            }
            deleteRemovedResources(resources,resources.stream().map(Resource::getUuid).filter(Predicate.not(updatedUuids::contains)).toList());
        }
        return resources;
    }

    private void deleteRemovedResources(List<Resource> resources, List<UUID> uuidsToRemove) {
        for(UUID uuid : uuidsToRemove){
            Resource resource = resources.stream().filter(it -> uuid.equals(it.getUuid())).findFirst().orElseThrow();
            Integer position = resource.getPosition();
            resources.remove(resource);
            resources.stream().filter(it -> it.getPosition() > position).forEach(it -> it.setPosition(it.getPosition()-1));
        }
    }

    private Resource getOrCreateResource(Block block, UUID resourceUuid) {
        if (resourceUuid == null) {
            Resource resource = new Resource();
            resource.setUuid(UUID.randomUUID());
            resource.setParent(block);
            resource.setPosition(block.getResources().stream().mapToInt(Resource::getPosition).max().orElse(-1) + 1);
            return resource;
        }
        return block.getResources().stream().filter(resource -> resource.getUuid().equals(resourceUuid)).findFirst().orElseThrow();
    }


}
