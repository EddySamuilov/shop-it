package shopIT.shopIT.enums;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
public enum ProductType {
  ELECTRONICS("Electronic gadgets"),
  CLOTHES("Brand new clothes"),
  BOOKS("The most interesting books"),
  OTHER("All the other stuff");

  private final String label;
}
