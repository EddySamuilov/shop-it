package shopIT.shopIT.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopIT.shopIT.dtos.ProductUpsertDTO;
import shopIT.shopIT.dtos.ProductSearchResponseDTO;
import shopIT.shopIT.exceptions.ProductNotFoundException;
import shopIT.shopIT.mappers.ProductMapper;
import shopIT.shopIT.models.Product;
import shopIT.shopIT.repositories.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

  public long create(ProductUpsertDTO productCreateDTO) {
    Product product = productMapper.toEntity(productCreateDTO);
    product.setCreated(LocalDateTime.now());
    product.setModified(LocalDateTime.now());

    return productRepository.save(product).getId();
  }

  public void update(String id, ProductUpsertDTO productUpdateDTO) {
    Product product = productRepository.findById(Long.valueOf(id))
        .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));

    product.setTitle(productUpdateDTO.getTitle());
    product.setDescription(productUpdateDTO.getDescription());
    product.setType(productUpdateDTO.getType());
    product.setModified(LocalDateTime.now());

    productRepository.save(product);
  }

  public void delete(String id) {
    productRepository.deleteById(Long.valueOf(id));
  }
}
