package shopIT.shopIT.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import shopIT.shopIT.dtos.ProductDTO;
import shopIT.shopIT.models.Product;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {

  ProductDTO toDTO(Product product);

}
