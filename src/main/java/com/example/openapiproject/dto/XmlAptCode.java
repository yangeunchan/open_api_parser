package com.example.openapiproject.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlAptCode {
    @XmlElement(name = "as1")
    private String as1;
    @XmlElement(name = "as2")
    private String as2;
    @XmlElement(name = "as3")
    private String as3;
    @XmlElement(name = "as4")
    private String as4;
    @XmlElement(name = "bjdCode")
    private String bjdCode;
    @XmlElement(name = "kaptCode")
    private String kaptCode;
    @XmlElement(name = "kaptName")
    private String kaptName;
}
