package com.example.openapiproject.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AptDetailDto {
    private String aptName;
    private String addr;
    private String dongCnt;
    private String peopleCnt;
    private String company;
    private String tel;
    private String fax;
    private String url;
    private String aptCode;
    private String hoCnt;
}
