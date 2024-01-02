package shopIT.shopIT.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopIT.shopIT.dtos.ProductDTO;
import shopIT.shopIT.mappers.ProductMapper;
import shopIT.shopIT.repositories.ProductRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @Transactional(readOnly = true)
  public List<ProductDTO> getAll() {
    return productRepository.findAll()
        .stream()
        .map(productMapper::toDTO)
        .toList();
  }
}
