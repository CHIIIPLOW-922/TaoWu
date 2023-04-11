package com.joji.taowu.common.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joji.taowu.common.entity.Address;
import lombok.Data;

@Data
public class AddressParam {

    @JsonProperty("user_id")
    private Integer userId;
    private Address address;
}
