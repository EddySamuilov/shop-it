package shopIT.shopIT.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import shopIT.shopIT.dtos.ProductCreateDTO;
import shopIT.shopIT.dtos.ProductSearchResponseDTO;
import shopIT.shopIT.models.Product;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {

  ProductSearchResponseDTO toDTO(Product product);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "created", ignore = true)
  @Mapping(target = "modified", ignore = true)
  Product toEntity(ProductCreateDTO source);

  default Product mapDTOToEntity(ProductCreateDTO productCreateDTO) {
    Product product = toEntity(productCreateDTO);
    product.setCreated(LocalDateTime.now());
    product.setModified(LocalDateTime.now());
    return product;
  }

  default Product updateEntityFromDTO(ProductCreateDTO dto, @MappingTarget Product entity) {
    entity.setModified(LocalDateTime.now());
    return entity;
  }
}
