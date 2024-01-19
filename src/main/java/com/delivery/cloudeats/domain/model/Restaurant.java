package com.delivery.cloudeats.domain.model;

import com.delivery.cloudeats.core.validation.Groups;
import com.delivery.cloudeats.core.validation.Multiple;
import com.delivery.cloudeats.core.validation.ValueZeroAddDescription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ValueZeroAddDescription(fieldValue = "deliveryFee", fieldDescription = "name", mandatoryDescription = "Free Delivery")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull
//    @NotEmpty
    @NotBlank
    @Column(nullable = false)
    private String name;

//    @DecimalMin("0")
//    @PositiveOrZero
//    @DeliveryFee
    @Multiple(number = 5)
    @Column(name = "delivery_fee", nullable = false)
    private BigDecimal deliveryFee;

//    @JsonIgnore
//    @JsonIgnoreProperties("hibernateLazyInitializer")
    @Valid
    @ConvertGroup(from = Default.class, to = Groups.KitchenId.class)
    @NotNull
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "kitchen_id", nullable = false)
    private Kitchen kitchen;

    @JsonIgnore
    @Embedded
    private Address address;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime registerDate;

    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime updateDate;

    @JsonIgnore
    @ManyToMany//(fetch = FetchType.EAGER)
    @JoinTable(name = "restaurant_payment_method", joinColumns = @JoinColumn(name = "restaurant_id"),
               inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
    private List<PaymentMethod> paymentMethods = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();

}
