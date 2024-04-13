package shopIT.shopIT.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import shopIT.shopIT.dtos.ProductCreateDTO;
import shopIT.shopIT.dtos.ProductSearchResponseDTO;
import shopIT.shopIT.models.Product;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductDTOMapper {

  ProductSearchResponseDTO toDTO(Product product);

  default Product updateEntityFromDTO(ProductCreateDTO dto, @MappingTarget Product entity) {
    entity.setModified(LocalDateTime.now());
    return entity;
  }

}
