package io.pivotal.loansource;

/**
 * @author Amine Achouri
 */
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@ToString
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private UUID orderUuid;

    @NotBlank
    private String itemName;

    private long productId;

    private long customerId;

    private OrderStatus orderStatus;
}
