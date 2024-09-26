package ru.rellai.arc.mapper;

import org.mapstruct.*;
import ru.rellai.arc.entity.Diagram;
import ru.rellai.arc.dto.DiagramDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DiagramMapper {
    @Mapping(source = "parentId", target = "parent.id")
    Diagram toEntity(DiagramDto diagramDto);

    @Mapping(source = "parent.id", target = "parentId")
    DiagramDto toDto(Diagram diagram);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "parentId", target = "parent")
    Diagram partialUpdate(DiagramDto diagramDto, @MappingTarget Diagram diagram);

    default Diagram createDiagram(Long parentId) {
        if (parentId == null) {
            return null;
        }
        Diagram diagram = new Diagram();
        diagram.setId(parentId);
        return diagram;
    }

    @InheritConfiguration(name = "partialUpdate")
    Diagram updateWithNull(DiagramDto diagramDto, @MappingTarget Diagram diagram);
}