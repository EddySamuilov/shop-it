package shopIT.shopIT.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shopIT.shopIT.enums.ProductType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

  @Column(nullable = false)
  @NotBlank(message = "The title must not be blank!")
  private String title;

  @Size(max = 255, message = "The description must not exceed 255 characters!")
  private String description;

  @NotNull
  @Enumerated(EnumType.STRING)
  private ProductType type;
}
