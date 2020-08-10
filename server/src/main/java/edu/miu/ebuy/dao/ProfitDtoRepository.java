package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.Product;
import edu.miu.ebuy.models.dto.ProfitDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfitDtoRepository extends JpaRepository<ProfitDto, Integer> {
    @Query( value="SELECT * FROM vwVendorProfit WHERE  vendorId=?1" ,nativeQuery= true)
    public List<ProfitDto> findByVendorId(int vendorId);
}
