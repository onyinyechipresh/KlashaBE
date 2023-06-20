package com.example.KlashaBE.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PopulationCount {
    private Long year;
    private String value;
    private String sex;
    private String reliability;
}

