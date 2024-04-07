package shopIT.shopIT.web.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shopIT.shopIT.dtos.ProductCreateDTO;
import shopIT.shopIT.dtos.ProductSearchResponseDTO;
import shopIT.shopIT.models.Product;
import shopIT.shopIT.services.ProductService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductResource {

  private final ProductService productService;

  @GetMapping("/{id}")
  public ResponseEntity<ProductSearchResponseDTO> findById(@PathVariable String id) {
    ProductSearchResponseDTO product = productService.findById(id);
    return ResponseEntity.ok(product);
  }

  @GetMapping("/search")
  public ResponseEntity<List<ProductSearchResponseDTO>> getAll() {
    List<ProductSearchResponseDTO> products = productService.getAll();
    return ResponseEntity.ok(products);
  }

  @PostMapping("/save")
  public ResponseEntity<Void> create(@RequestBody @Valid ProductCreateDTO productCreateDTO, HttpServletRequest request) {
    Product product = productService.create(productCreateDTO);

    URI uri = ServletUriComponentsBuilder
        .fromRequest(request)
        .replacePath("/api/v1/products/{id}")
        .buildAndExpand(product.getId())
        .toUri();

    return ResponseEntity.created(uri).build();
  }
}
