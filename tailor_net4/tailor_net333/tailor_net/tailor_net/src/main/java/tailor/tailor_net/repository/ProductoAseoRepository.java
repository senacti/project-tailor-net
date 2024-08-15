package tailor.tailor_net.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tailor.tailor_net.model.ProductoAseo;


@Repository
public interface ProductoAseoRepository extends JpaRepository<ProductoAseo, Integer> {
}
