package shopIT.shopIT.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shopIT.shopIT.enums.ProductType;

@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

  @Column(nullable = false)
  @NotBlank(message = "The title must not be blank!")
  private String title;

  @Size(max = 255, message = "The description must not exceed 255 characters!")
  private String description;

  @NotNull(message = "The product cannot be null!")
  @Enumerated(EnumType.STRING)
  private ProductType type;
}
