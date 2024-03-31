package org.artem.courses.mapper;

import org.artem.courses.dto.BlockDTO;
import org.artem.courses.dto.ResourceDTO;
import org.artem.courses.entity.Block;
import org.artem.courses.entity.Resource;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = ResourceMapper.class)
public abstract class BlockMapper {

    public abstract BlockDTO toDTO(Block block);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "resources", expression = "java(updateResources(blockDTO,entity))")
    public abstract void updateBlockFromDTO(BlockDTO blockDTO, @MappingTarget Block entity);

    protected List<Resource> updateResources(BlockDTO blockDTO, @MappingTarget Block entity) {
        ResourceMapper resourceMapper = Mappers.getMapper(ResourceMapper.class);
        if (blockDTO.getResources() == null) {
            return entity.getResources();
        }
        List<Resource> resources = entity.getResources();
        if (blockDTO.getResources().isEmpty()) {
            resources.clear();
        } else {
            List<UUID> uuids = blockDTO.getResources().stream().map(ResourceDTO::getUuid).toList();
            resources.removeIf(resource -> !uuids.contains(resource.getUuid()));

            for (ResourceDTO resourceDTO : blockDTO.getResources()) {
                Resource resource = getOrCreateResource(entity, resourceDTO.getUuid());
                resourceMapper.updateResourceFromDTO(resourceDTO, resource);
                if (resource.getId() == null) {
                    resources.add((resource));
                }
            }
        }
        return resources;
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
