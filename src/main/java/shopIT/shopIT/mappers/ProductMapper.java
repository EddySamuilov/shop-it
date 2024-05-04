package shopIT.shopIT.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import shopIT.shopIT.dtos.ProductUpsertDTO;
import shopIT.shopIT.dtos.ProductSearchResponseDTO;
import shopIT.shopIT.models.Product;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {

  ProductSearchResponseDTO toDTO(Product source);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "created", ignore = true)
  @Mapping(target = "modified", ignore = true)
  Product toEntity(ProductUpsertDTO source);

}
