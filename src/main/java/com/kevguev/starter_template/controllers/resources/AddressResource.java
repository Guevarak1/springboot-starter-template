package com.kevguev.starter_template.controllers.resources;

import com.kevguev.starter_template.services.models.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressResource {
    public String addressLine1;
    public String addressLine2;
    public String addressLine3;
    public String city;
    public String state;
    public String zipCode;

    public AddressResource(Address address) {
        this.addressLine1 = address.addressLine1;
        this.addressLine2 = address.addressLine2;
        this.addressLine3 = address.addressLine3;
        this.city = address.city;
        this.state = address.state;
        this.zipCode = address.zipCode;
    }
}
