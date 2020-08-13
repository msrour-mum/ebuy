package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product  p WHERE p.price >= :fromPrice AND p.price <= :toPrice")
    List<Product> getProductByPrice(double fromPrice, double toPrice);
    Product getProductByIsService(boolean isService);


    List<Product> findByIsDeleted(boolean isDeleted);
    List<Product> findByIsDeletedAndIsServiceAndIsPublishedAndProductStatus_Id(boolean isDeleted,boolean isService,boolean isPublished,int statusId);
    List<Product> findByIsDeletedAndIsServiceAndIsPublishedAndProductStatus_IdAndUser_Id(boolean isDeleted,boolean isService,boolean isPublished,int statusId,int userid);


    List<Product> findByIsDeletedAndIsServiceAndProductStatus_Id(boolean isDeleted,boolean isService,int statusId);
    List<Product> findByIsDeletedAndIsServiceAndProductStatus_IdAndUser_Id(boolean isDeleted,boolean isService,int statusId,int userid);


    //List<Product> findByIsDeletedAndIsServiceAndProductStatus_Id(boolean isDeleted,boolean isService,int statusId);
    //List<Product> findByIsDeletedAndIsServiceAndUser_IdAndProductStatus_Id(boolean isDeleted,boolean isService,int userid,int statusId);

    List<Product> findByProductStatus_Id(int statusId);




    @Modifying
    @Query(value = "UPDATE product SET isDeleted=1 WHERE id=?1",nativeQuery = true)
    public void updateItemDelete( @Param("id") Integer id);


    @Modifying
    @Query(value = "UPDATE product SET isPublished=?2 WHERE id=?1",nativeQuery = true)
    public void updateItemPublished( @Param("id") Integer id,@Param("isPublished")  Boolean isPublished);


    @Modifying
    @Query(value = "UPDATE product SET statusId=?2 WHERE id=?1",nativeQuery = true)
    public void updateStatus( @Param("id") Integer id, @Param("statusId") Integer statusId);


    @Query(value = "select  p.* from product p\n" +
            "inner join user vendor on vendor.id=p.vendorId\n" +
            "inner join category cat on cat.id=p.categoryId\n" +
            "where p.statusId=2 and  p.isDeleted=0 and p.isService=0 and p.isPublished=1\n" +
            "and p.name like %:name%\n" +
            "and vendor.name like %:vendorName%\n" +
            "and p.price between :priceFrom and :priceTo", nativeQuery = true)
    public List<Product> search( @Param("name") String name,@Param("vendorName") String vendorName ,
                                @Param("priceFrom") double priceFrom, @Param("priceTo") double priceTo);


    @Query(value = "select \n" +
            "    product.id,\n" +
            "    product.cost,\n" +
            "    product.description,\n" +
            "    product.imageUrl,\n" +
            "    product.isPublished,\n" +
            "    product.isService,\n" +
            "    product.name,\n" +
            "   \n" +
            "    product.shortDescription,\n" +
            "    product.categoryId,\n" +
            "    product.statusId,\n" +
            "    product.vendorId,\n" +
            "    product.isDeleted ,\n" +
            "    sum(oi.quantity * (product.price-product.cost)) as price\n" +
            " from product \n" +
            "inner join orderitem oi on product.id=oi.product_id\n" +
            "where product.isDeleted=0 and product.isService=0 and product.isPublished=1 and product.vendorId=:vendorId\n" +
            "group by  product.id,\n" +
            "    product.cost,\n" +
            "    product.description,\n" +
            "    product.imageUrl,\n" +
            "    product.isPublished,\n" +
            "    product.isService,\n" +
            "    product.name,   \n" +
            "    product.shortDescription,\n" +
            "    product.categoryId,\n" +
            "    product.statusId,\n" +
            "    product.vendorId,\n" +
            "    product.isDeleted ", nativeQuery = true)
    public List<Product> reportProfit( @Param("vendorId") int vendorId);

    List<Product> findByIdIn(List<Integer> productIds);

}
