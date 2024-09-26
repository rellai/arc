package ru.rellai.arc.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

/**
 * DTO for {@link ru.rellai.arc.entity.Diagram}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiagramDto {
    Long id;
    String name;
    String img;
    String code;
    Long parentId;
}