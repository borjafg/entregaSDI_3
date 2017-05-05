package uo.sdi.dto.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum(String.class)
public enum UserStatusDTO {
    ENABLED, DISABLED
}