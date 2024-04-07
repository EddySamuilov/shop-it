package shopIT.shopIT.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import shopIT.shopIT.dtos.ProductCreateDTO;
import shopIT.shopIT.models.Product;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)

public interface ProductEntityMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "created", ignore = true)
  @Mapping(target = "modified", ignore = true)
  Product toEntity(ProductCreateDTO source);
}