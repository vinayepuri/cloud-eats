package com.delivery.cloudeats.api.model;

import com.delivery.cloudeats.domain.model.Kitchen;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@JsonRootName("cuisines")
@Data
public class KitchensXmlWrapper {

    @JsonProperty("cuisine")
    @JacksonXmlElementWrapper(useWrapping = false)
    @NonNull
    private List<Kitchen> kitchens;
}
