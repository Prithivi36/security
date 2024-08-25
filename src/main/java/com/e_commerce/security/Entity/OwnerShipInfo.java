package com.e_commerce.security.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ownershipLog")
public class OwnerShipInfo {
    private String code;
    private LocalDateTime date;
    private List<String> ownerInfoList;
}
