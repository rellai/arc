package ru.rellai.arc.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.rellai.arc.dto.DiagramDto;
import ru.rellai.arc.entity.Diagram;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-24T15:54:34+0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.4 (Ubuntu)"
)
@Component
public class DiagramMapperImpl implements DiagramMapper {

    @Override
    public Diagram toEntity(DiagramDto diagramDto) {
        if ( diagramDto == null ) {
            return null;
        }

        Diagram diagram = new Diagram();

        diagram.setParent( diagramDtoToDiagram( diagramDto ) );
        diagram.setId( diagramDto.getId() );
        diagram.setName( diagramDto.getName() );
        diagram.setImg( diagramDto.getImg() );
        diagram.setCode( diagramDto.getCode() );

        return diagram;
    }

    @Override
    public DiagramDto toDto(Diagram diagram) {
        if ( diagram == null ) {
            return null;
        }

        Long parentId = null;
        Long id = null;
        String name = null;
        String img = null;
        String code = null;

        parentId = diagramParentId( diagram );
        id = diagram.getId();
        name = diagram.getName();
        img = diagram.getImg();
        code = diagram.getCode();

        DiagramDto diagramDto = new DiagramDto( id, name, img, code, parentId );

        return diagramDto;
    }

    @Override
    public Diagram partialUpdate(DiagramDto diagramDto, Diagram diagram) {
        if ( diagramDto == null ) {
            return diagram;
        }

        if ( diagramDto.getParentId() != null ) {
            diagram.setParent( createDiagram( diagramDto.getParentId() ) );
        }
        if ( diagramDto.getId() != null ) {
            diagram.setId( diagramDto.getId() );
        }
        if ( diagramDto.getName() != null ) {
            diagram.setName( diagramDto.getName() );
        }
        if ( diagramDto.getImg() != null ) {
            diagram.setImg( diagramDto.getImg() );
        }
        if ( diagramDto.getCode() != null ) {
            diagram.setCode( diagramDto.getCode() );
        }

        return diagram;
    }

    @Override
    public Diagram updateWithNull(DiagramDto diagramDto, Diagram diagram) {
        if ( diagramDto == null ) {
            return diagram;
        }

        if ( diagramDto.getParentId() != null ) {
            diagram.setParent( createDiagram( diagramDto.getParentId() ) );
        }
        if ( diagramDto.getId() != null ) {
            diagram.setId( diagramDto.getId() );
        }
        if ( diagramDto.getName() != null ) {
            diagram.setName( diagramDto.getName() );
        }
        if ( diagramDto.getImg() != null ) {
            diagram.setImg( diagramDto.getImg() );
        }
        if ( diagramDto.getCode() != null ) {
            diagram.setCode( diagramDto.getCode() );
        }

        return diagram;
    }

    protected Diagram diagramDtoToDiagram(DiagramDto diagramDto) {
        if ( diagramDto == null ) {
            return null;
        }

        Diagram diagram = new Diagram();

        diagram.setId( diagramDto.getParentId() );

        return diagram;
    }

    private Long diagramParentId(Diagram diagram) {
        Diagram parent = diagram.getParent();
        if ( parent == null ) {
            return null;
        }
        return parent.getId();
    }
}
