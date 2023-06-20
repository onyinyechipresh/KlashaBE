package com.example.KlashaBE.apiResponse;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse {
    private DataResponse ghana;
    private DataResponse italy;
    private DataResponse newZealand;
}
