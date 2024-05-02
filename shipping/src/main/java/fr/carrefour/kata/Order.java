package fr.carrefour.kata;

/**
 * @author Amine Achouri
 */

import java.io.Serializable;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order implements Serializable {

    private UUID orderUuid;

    @NotBlank
    private String itemName;

    private OrderStatus orderStatus;


}
