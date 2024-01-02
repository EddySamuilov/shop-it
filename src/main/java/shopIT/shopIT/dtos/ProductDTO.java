package shopIT.shopIT.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

  @Column(nullable = false)
  @NotBlank(message = "The title must be populated!")
  private String title;

  private String description;
}
