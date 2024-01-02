package shopIT.shopIT.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shopIT.shopIT.dtos.ProductDTO;
import shopIT.shopIT.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductResource {

  private final ProductService productService;

  @GetMapping("/search")
  public ResponseEntity<List<ProductDTO>> getAll() {
    List<ProductDTO> products = productService.getAll();
    return ResponseEntity.ok(products);
  }
}
