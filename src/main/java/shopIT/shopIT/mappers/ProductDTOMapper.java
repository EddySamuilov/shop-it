package shopIT.shopIT.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import shopIT.shopIT.dtos.ProductSearchResponseDTO;
import shopIT.shopIT.models.Product;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductDTOMapper {

  ProductSearchResponseDTO toDTO(Product product);

}
