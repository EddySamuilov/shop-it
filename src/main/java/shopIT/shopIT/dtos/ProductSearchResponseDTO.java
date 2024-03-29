package shopIT.shopIT.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchResponseDTO {

  private String title;

  private String description;

  private String type;
}
