package shopIT.shopIT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopIT.shopIT.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
