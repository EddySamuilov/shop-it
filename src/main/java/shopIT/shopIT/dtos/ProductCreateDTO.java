package shopIT.shopIT.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import shopIT.shopIT.enums.ProductType;

@Data
@NoArgsConstructor
public class ProductCreateDTO {

  @NotBlank(message = "The title must not be blank!")
  private String title;

  @Size(max = 255, message = "The description must not exceed 255 characters!")
  private String description;

  @NotNull(message = "The product type must be provided!")
  @Enumerated(EnumType.STRING)
  private ProductType type;
}
