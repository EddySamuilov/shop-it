package shopIT.shopIT.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
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
}
