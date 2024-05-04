package shopIT.shopIT.web.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shopIT.shopIT.dtos.ProductSearchResponseDTO;
import shopIT.shopIT.dtos.ProductUpsertDTO;
import shopIT.shopIT.services.ProductService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductResource {

  private final ProductService productService;

  @Operation(summary = "Retrieves a product by the provided id.")
  @ApiResponse(responseCode = "200", description = "Request completed successfully!")
  @ApiResponse(responseCode = "401", description = "Bad request! Check the validation restrictions!")
  @ApiResponse(responseCode = "404", description = "Product not found!")
  @GetMapping("/{id}")
  public ResponseEntity<ProductSearchResponseDTO> findById(@PathVariable String id) {
    return ResponseEntity.ok(productService.findById(id));
  }

  @Operation(summary = "Retrieves all the existing products.")
  @ApiResponse(responseCode = "200", description = "Request completed successfully!")
  @ApiResponse(responseCode = "401", description = "Bad request! Check the validation restrictions!")
  @ApiResponse(responseCode = "404", description = "Products not found!")
  @GetMapping
  public ResponseEntity<List<ProductSearchResponseDTO>> getAll() {
    return ResponseEntity.ok(productService.getAll());
  }

  @Operation(summary = "Creates a product.")
  @ApiResponse(responseCode = "200", description = "Request completed successfully!")
  @ApiResponse(responseCode = "401", description = "Bad request! Check the validation restrictions!")
  @PostMapping("/save")
  public ResponseEntity<Void> create(@RequestBody @Valid ProductUpsertDTO productUpsertDTO, HttpServletRequest request) {
    long productId = productService.create(productUpsertDTO);

    URI uri = ServletUriComponentsBuilder.fromRequest(request)
        .replacePath("/api/v1/products/{id}")
        .buildAndExpand(productId)
        .toUri();

    return ResponseEntity.created(uri).build();
  }

  @Operation(summary = "Updates a product.")
  @ApiResponse(responseCode = "200", description = "Request completed successfully!")
  @ApiResponse(responseCode = "401", description = "Bad request! Check the validation restrictions!")
  @ApiResponse(responseCode = "404", description = "Product not found!")
  @PutMapping("{id}/update")
  public ResponseEntity<Void> update(
      @PathVariable String id,
      @RequestBody @Valid ProductUpsertDTO productUpsertDTO,
      HttpServletRequest request
  ) {
    productService.update(id, productUpsertDTO);

    URI uri = ServletUriComponentsBuilder.fromRequest(request)
        .replacePath("/api/v1/products/{id}")
        .buildAndExpand(id)
        .toUri();

    return ResponseEntity.created(uri).build();
  }

  @Operation(summary = "Deletes a product.")
  @ApiResponse(responseCode = "200", description = "Request completed successfully!")
  @ApiResponse(responseCode = "401", description = "Bad request! Check the validation restrictions!")
  @ApiResponse(responseCode = "404", description = "Product not found!")
  @PutMapping("{id}/delete")
  public ResponseEntity<Void> update(@PathVariable String id) {
    productService.delete(id);

    return ResponseEntity.ok().build();
  }
}
