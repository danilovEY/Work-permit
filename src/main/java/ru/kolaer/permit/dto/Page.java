package ru.kolaer.permit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by danilovey on 14.04.2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {
    private Integer number;
    private Long total;
    private Integer pageSize;
    private List<T> data;
}
