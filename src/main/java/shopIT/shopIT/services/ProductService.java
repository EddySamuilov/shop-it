package shopIT.shopIT.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopIT.shopIT.dtos.ProductCreateDTO;
import shopIT.shopIT.dtos.ProductSearchResponseDTO;
import shopIT.shopIT.exceptions.ProductNotFoundException;
import shopIT.shopIT.mappers.ProductMapper;
import shopIT.shopIT.models.Product;
import shopIT.shopIT.repositories.ProductRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

  public static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

  private static final String PRODUCT_NOT_FOUND = "Product not found!";

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @Transactional(readOnly = true)
  public ProductSearchResponseDTO findById(String id) {
    return productRepository.findById(Long.valueOf(id))
        .map(productMapper::toDTO)
        .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));
  }

  @Transactional(readOnly = true)
  public List<ProductSearchResponseDTO> getAll() {
    return productRepository.findAll()
        .stream()
        .map(productMapper::toDTO)
        .toList();
  }

  public Product create(ProductCreateDTO productCreateDTO) {
    return productRepository.save(productMapper.toEntity(productCreateDTO));
  }
}
