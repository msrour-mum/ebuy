package edu.miu.ebuy.config;

import edu.miu.ebuy.models.*;
import edu.miu.ebuy.models.lookup.CardType;
import edu.miu.ebuy.models.lookup.ProductStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

;

public class DataGenerate {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("edu");

    public static void Generate() throws IOException {
        //SpringApplication.run(ItweetApplication.class, args);

        BCryptPasswordEncoder passwordUtil=new BCryptPasswordEncoder();
        String pass=passwordUtil.encode("sa");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Role roleAdmin = new Role(1, "Admin");
        Role roleVendor = new Role(2, "Vendor");
        Role roleUser = new Role(3, "RegularUser");

        em.persist(roleAdmin);
        em.persist(roleVendor);
        em.persist(roleUser);


        CardType cardTypeVisa= new CardType(1,"Visa");
        CardType cardTypeMaster= new CardType(2,"MasterCard");
        CardType cardTypeAm= new CardType(3,"AMEX");

        em.persist(cardTypeVisa);
        em.persist(cardTypeMaster);
        em.persist(cardTypeAm);




        User userAdmin1 = new User("Admin", "admin@mum.com",  roleAdmin, pass, true, "1232", " Fairfield, Iowa ,1000 N 4th St","imageUrl");
        User userAdmin2 = new User("Mahmoud Srour", "msrour@mum.edu",  roleAdmin, pass, true, "1232", " Fairfield, Iowa ,1000 N 4th St","imageUrl");
        User userAdmin3 = new User("Moustafa Zein", "mzein@mum.com",  roleAdmin, pass, true, "1232", " Fairfield, Iowa ,1000 N 4th St","imageUrl");
        User userAdmin4 = new User("Ibrahim Samier", "adminIbrahim@mum.com",  roleAdmin, pass, true, "1232", " Fairfield, Iowa ,1000 N 4th St","imageUrl");
        em.persist(userAdmin1);
        em.persist(userAdmin2);
        em.persist(userAdmin3);
        em.persist(userAdmin4);


        //Vendors Users
        List<User> vendorList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            User user1 = new User("Vender "+ i, "Vender "+ i+"@mum.com",  roleVendor, pass, true, "1232" , " Fairfield, Iowa ,1000 N 4th St","imageUrl");
            em.persist(user1);
            vendorList.add(user1);
        }

        //End Users
        for (int i = 1; i < 11; i++) {
            User user1 = new User("User "+ i, "User "+ i+"@mum.com",  roleUser, pass, true, "1232", " Fairfield, Iowa ,1000 N 4th St","imageUrl");
            em.persist(user1);
        }


        Category category1 = new Category(1,"Category 1");
        Category category2 = new Category(1,"Category 2");
        Category category3 = new Category(1,"Category 3");
        Category category4 = new Category(1,"Category 4");
        Category category5 = new Category(1,"Category 5");
        em.persist(category1);
        em.persist(category2);
        em.persist(category3);
        em.persist(category4);
        em.persist(category5);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category2);
        categoryList.add(category3);
        categoryList.add(category4);
        categoryList.add(category5);

        ProductStatus productStatusPending = new ProductStatus(1,"Pending");
        ProductStatus productStatusActive = new ProductStatus(2,"Active");
        ProductStatus productStatusRejected = new ProductStatus(3,"Rejected");
        em.persist(productStatusPending);
        em.persist(productStatusActive);
        em.persist(productStatusRejected);


        Product productSubscription = new Product("Subscription Fees",  "Subscription Fees","Subscription Fees, pay with vendor membership", userAdmin1, category1, 0, 20000, productStatusActive, true, true, "imageUrl");
        em.persist(productSubscription);

        //Products
        for (int i = 1; i < 21; i++) {
            Category category = categoryList.get(i%5);
            User user = vendorList.get(i%5);
            Product product = new Product("Product "+i,  "Product description"+i,"Product description"+i, user, category, i*20, i*40, productStatusActive, true, false, "imageUrl");
            em.persist(product);
        }




        TypedQuery<User> q = em.createQuery("from User where roleId=3", User.class);
        List<User> users = q.getResultList();

        TypedQuery<Product> q2 = em.createQuery("from Product  ", Product.class);
        List<Product> products = q2.getResultList();

        int recCount = 10;

        for (User user: users)
        {
            Order order = new Order(user,new Date(),0,20);
            int total=0;
            int c1 = new Random().nextInt(4)+1;
            for (int i = 0; i < c1; i++) {
                Product p= products.get(new Random().nextInt(products.size()));
                int qt = new Random().nextInt(3);
                OrderItem item = new OrderItem(p,qt,p.getPrice()*qt);
                order.addItem(item);
                total+=p.getPrice()*qt;
            }
            order.setTotal(total);
            em.persist(order);


            int ct = new Random().nextInt(2)+1;
            String cardNo="54789632587412"+recCount;
            MerchantCard merchantCard = new MerchantCard(user.getName(),cardNo,123,"24/02",new CardType(ct,""),100000);
            UserCard userCard = new UserCard(user.getName(),cardNo,123,"24/02",new CardType(ct,""));
            recCount++;
            em.persist(merchantCard);
            em.persist(userCard);
        }




/*
        TypedQuery<User> q = em.createQuery("from User where isAdmin=0", User.class);
        List<User> users = q.getResultList();
        int postCount = 1;

        for (User userM : users) {
            for (User userS : users) {
                if (doRandom(4,1)) {
                    if(userM.getId()!=userS.getId()) {
                        Following following = new Following(userS, userM, new Date());
                        em.persist(following);
                    }
                }
            }
        }

        for (User user : users) {
            for (int i = 0; i < 7; i++) {

                if (user.isAdmin())
                    continue;
                PostStatus p = PostStatus.Active;
                boolean isNotActivePost =doRandom(10,i);
                if(isNotActivePost)
                    p=PostStatus.Pending;

                Post post = new Post(user, "This is post " + postCount + "from user :" + user.getEmail(), p, null, null, new Date(), new Date());
                em.persist(post);

                if (doRandom(3, i)) {


                    int selectNo= getRandomNumberInRange(1,60);
                    String sourceImg="post ("+selectNo+").jpg";

                    try {
                        Path path = Paths.get("C:\\uploads\\" + user.getId());
                        Path newDir = Files.createDirectory(path);
                    }
                    catch(Exception EX) { }


                    try {


                    Path temp = Files.copy
                            (Paths.get("C:\\uploads\\sourceImg\\"+sourceImg),
                                    Paths.get("C:\\uploads\\"+user.getId()+"\\"+post.getId()+".jpg"));
                    post.setImageUrl("/uploads/"+user.getId()+"/"+post.getId()+".jpg");
                    em.persist(post);
                    }
                    catch(Exception EX) { }
                }

                if(!isNotActivePost) {
                    for (User userAction : users) {
                        if (doRandom(3, i)) {
                            //Comment comment = new Comment(userAction, post, "This comments from user " + userAction.getEmail());
                            Comment comment = new Comment(userAction,  "This comments from user " + userAction.getEmail());
                            post.addComment(comment);
                            em.persist(post);
                        }

                        if (doRandom(4, i)) {
                            PostLikes postLikes = new PostLikes(userAction,true,new Date());
                            post.addLike(postLikes);
                            em.persist(post);
                        }
                    }
                }
                postCount++;
            }
        }



        TypedQuery<User> q2 = em.createQuery("from User ", User.class);
        List<User> useralls = q2.getResultList();
        for (User user : useralls)
        {
            try {
                Path path = Paths.get("C:\\uploads\\" + user.getId());
                Path newDir = Files.createDirectory(path);
            } catch (Exception EX) {}



            try {
                Path temp = Files.copy
                        (Paths.get("C:\\uploads\\ProfilesPic\\" + user.getId() + ".jpg"),
                                Paths.get("C:\\uploads\\" + user.getId() + "\\" + "profiel"+user.getId() + ".jpg"));
                user.setPhotoUrl("/uploads/" + user.getId() + "/" + "profiel"+user.getId() + ".jpg");
                em.persist(user);
            } catch (Exception EX) {            }
        }
*/
        em.getTransaction().commit();
        emf.close();


    }

    public static boolean doRandom(int factor , int seed) {
        Random rand = new Random();
        int randNo = rand.nextInt(1000*seed+1);
        if (randNo % factor == 0) return true;
        return false;
    }

    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return (int) (min + (Math.random() * (max - min)));
        //Random r = new Random();
       // return r.nextInt((max - min) + 1) + min;
    }

//    public static Date getBirthDate() {
//
//        Date date=new Date();
//        date.
//        date.setYear(getRandomNumberInRange(1970,2010));
//        date.setMonth(getRandomNumberInRange(1,12));
//
//        return date;
//    }
}
