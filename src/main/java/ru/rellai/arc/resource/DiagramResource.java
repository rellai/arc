package ru.rellai.arc.resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.rellai.arc.dto.DiagramDto;
import ru.rellai.arc.mapper.DiagramMapper;
import ru.rellai.arc.entity.Diagram;
import ru.rellai.arc.repository.DiagramRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/v1/diagrams")
@RequiredArgsConstructor
public class DiagramResource {

    private final DiagramRepository diagramRepository;

    private final DiagramMapper diagramMapper;

    private final ObjectMapper objectMapper;

    @GetMapping
    public List<DiagramDto> getList() {
        List<Diagram> diagrams = diagramRepository.findAll();
        return diagrams.stream()
                .map(diagramMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public DiagramDto getOne(@PathVariable Long id) {
        Optional<Diagram> diagramOptional = diagramRepository.findById(id);
        return diagramMapper.toDto(diagramOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @GetMapping("/by-ids")
    public List<DiagramDto> getMany(@RequestParam List<Long> ids) {
        List<Diagram> diagrams = diagramRepository.findAllById(ids);
        return diagrams.stream()
                .map(diagramMapper::toDto)
                .toList();
    }

    @PostMapping
    public DiagramDto create(@RequestBody DiagramDto dto) {
        Diagram diagram = diagramMapper.toEntity(dto);
        Diagram resultDiagram = diagramRepository.save(diagram);
        return diagramMapper.toDto(resultDiagram);
    }

    @PatchMapping("/{id}")
    public DiagramDto patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        Diagram diagram = diagramRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        DiagramDto diagramDto = diagramMapper.toDto(diagram);
        objectMapper.readerForUpdating(diagramDto).readValue(patchNode);
        diagramMapper.updateWithNull(diagramDto, diagram);

        Diagram resultDiagram = diagramRepository.save(diagram);
        return diagramMapper.toDto(resultDiagram);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        Collection<Diagram> diagrams = diagramRepository.findAllById(ids);

        for (Diagram diagram : diagrams) {
            DiagramDto diagramDto = diagramMapper.toDto(diagram);
            objectMapper.readerForUpdating(diagramDto).readValue(patchNode);
            diagramMapper.updateWithNull(diagramDto, diagram);
        }

        List<Diagram> resultDiagrams = diagramRepository.saveAll(diagrams);
        return resultDiagrams.stream()
                .map(Diagram::getId)
                .toList();
    }

    @DeleteMapping("/{id}")
    public DiagramDto delete(@PathVariable Long id) {
        Diagram diagram = diagramRepository.findById(id).orElse(null);
        if (diagram != null) {
            diagramRepository.delete(diagram);
        }
        return diagramMapper.toDto(diagram);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        diagramRepository.deleteAllById(ids);
    }
}
