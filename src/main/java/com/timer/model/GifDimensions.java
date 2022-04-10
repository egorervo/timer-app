package com.timer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GifDimensions {
    private List<ImageDimensions> dimensions;
    private int maxWidth;
    private int maxHeight;
}
