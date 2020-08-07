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




        User userAdmin1 = new User("Admin", "admin@mum.com",  roleAdmin, pass, true, "1232", " Fairfield, Iowa ,1000 N 4th St","");
        User userAdmin2 = new User("Mahmoud Srour", "msrour@mum.edu",  roleAdmin, pass, true, "1232", " Fairfield, Iowa ,1000 N 4th St","");
        User userAdmin3 = new User("Moustafa Zein", "mzein@mum.com",  roleAdmin, pass, true, "1232", " Fairfield, Iowa ,1000 N 4th St","");
        User userAdmin4 = new User("Ibrahim Samier", "adminIbrahim@mum.com",  roleAdmin, pass, true, "1232", " Fairfield, Iowa ,1000 N 4th St","");
        em.persist(userAdmin1);
        em.persist(userAdmin2);
        em.persist(userAdmin3);
        em.persist(userAdmin4);





        Category category1 = new Category(1,"Watches");
        Category category2 = new Category(2,"Laptops");
        Category category3 = new Category(3,"Shampoo");
        Category category4 = new Category(4,"T-Shert");
        Category category5 = new Category(5,"Shoes");
        Category category6 = new Category(6,"Mobile");
        Category category7 = new Category(7,"Coffee Machine");
        Category category8 = new Category(8,"TV");

        em.persist(category1);
        em.persist(category2);
        em.persist(category3);
        em.persist(category4);
        em.persist(category5);
        em.persist(category6);
        em.persist(category7);
        em.persist(category8);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category2);
        categoryList.add(category3);
        categoryList.add(category4);
        categoryList.add(category5);
        categoryList.add(category6);
        categoryList.add(category7);
        categoryList.add(category8);

        ProductStatus productStatusPending = new ProductStatus(1,"Pending");
        ProductStatus productStatusActive = new ProductStatus(2,"Active");
        ProductStatus productStatusRejected = new ProductStatus(3,"Rejected");
        em.persist(productStatusPending);
        em.persist(productStatusActive);
        em.persist(productStatusRejected);


        //Vendors Users
        List<User> vendorList = new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++) {
            User user1 = new User(categoryList.get(i).getName()+ " Vender ", "Vender"+ i+"@mum.com",  roleVendor, pass, true, "1232" , " Fairfield, Iowa ,1000 N 4th St","");
            em.persist(user1);
            vendorList.add(user1);
        }

        //End Users
        for (int i = 1; i < 11; i++) {
            User user1 = new User("User "+ i, "User"+ i+"@mum.com",  roleUser, pass, true, "1232", " Fairfield, Iowa ,1000 N 4th St","");
            em.persist(user1);
        }


        Product productSubscription = new Product("Subscription Fees",  "Subscription Fees","Subscription Fees, pay with vendor membership", userAdmin1, category1, 0, 20000, productStatusActive, true, true, "");
        em.persist(productSubscription);

        //Products
        int picNo=1;


        //Watches
        for (int i = 0; i < 5; i++) {
            Category category = categoryList.get(0);
            User user = vendorList.get(0);
            Product product = new Product("Watches "+i+1,  "Apple Watch Series 3 (GPS, 38mm) - Space Gray Aluminum Case with Black Sport Band\n",
                    "Bought watch & was unsure if I would keep it? I am Not good with computers! Wanted this to motivate me to be more active & the Fall feature.\n" +
                            "Wow!!! You Cannot have it back! This was Christmas Gift to myself! Hooked up thru my Iphone8 so easy! Bought several bands!\n" +
                            "Update:Screen froze...cannot unlock! Tech support no help!",
                    user, category, (i+1)*20, (i+1)*40, productStatusActive, true, false, "/uploads/"+user.getId()+"/"+ picNo+".jpg");
            picNo++;
            em.persist(product);
        }

        //Laptops
        for (int i = 0; i < 5; i++) {
            Category category = categoryList.get(1);
            User user = vendorList.get(1);
            Product product = new Product("Laptop "+i+1,  "Acer Aspire 5 Slim Laptop, 15.6 inches Full HD IPS Display, AMD Ryzen 3 3200U, Vega 3 Graphics, ",
                    "Everything but the battery life on this watch is great! It has been easy to figure out for a variety of uses. I love the activity goals and challenges. The only complaint I have is the battery life (charging once a day), but that was not unexpected after researching before purchase. Just because it was expected doesn't mean it's not something that needs improving though.",
                    user, category, (i+1)*200, (i+1)*300, productStatusActive, true, false, "/uploads/"+user.getId()+"/"+ picNo+".jpg");
            picNo++;
            em.persist(product);
        }
        //Shampoo
        for (int i = 0; i < 5; i++) {
            Category category = categoryList.get(2);
            User user = vendorList.get(2);
            Product product = new Product("Shampoo "+i+1,  "OUAI Detox Shampoo. Clarifying Cleanse for Dirt",
                    "INGREDIENTS THAT GET THE JOB DONE. All OUAI products are carefully crafted to cut styling time and nourish your hair health. We put the good stuff in and leave the bad stuff out, without ever sacrificing quality. We are always trying to do better for the planet.",
                    user, category, (i+1)*2.5, (i+1)*3, productStatusActive, true, false, "/uploads/"+user.getId()+"/"+ picNo+".jpg");
            picNo++;
            em.persist(product);
        }
        //T-Shert
        for (int i = 0; i < 5; i++) {
            Category category = categoryList.get(3);
            User user = vendorList.get(3);
            Product product = new Product("T-Shert "+i+1,  "NITAGUT Men's Casual Slim Fit Short Sleeve Pocket",
                    "Comfortable and slim fit casual wear, suit for any casual occasions, such as daily wear, sports, work, holiday, beach etc, perfect gift for families, friends and boyfriend.\n" +
                            "Please note: All buttons are just for decoration,sewn shut, not any function",
                    user, category, (i+1)*10, (i+1)*15, productStatusActive, true, false, "/uploads/"+user.getId()+"/"+ picNo+".jpg");
            picNo++;
            em.persist(product);
        }
        //Shoes
        for (int i = 0; i < 5; i++) {
            Category category = categoryList.get(4);
            User user = vendorList.get(4);
            Product product = new Product("Shoe "+i+1,  "Walking Shoes Sock Sneakers - Mesh Slip On Air",
                    "This shoe's sizing is equivalent to street shoe size.Width between medium and wide.\n" +
                            "Boxed toe for toe stands,super lightweight and flexible just like socks,breathable and smooth fabric provide great freedom and comfortable feeling. Let you enjoy your travel on holidays.",
                    user, category, (i+1)*15, (i+1)*17, productStatusActive, true, false, "/uploads/"+user.getId()+"/"+ picNo+".jpg");
            picNo++;
            em.persist(product);
        }
        //Mobile
        for (int i = 0; i < 5; i++) {
            Category category = categoryList.get(5);
            User user = vendorList.get(5);
            Product product = new Product("Mobile "+i+1,  "Samsung Galaxy A20s A207M/DS, 32GB/3GB",
                    "International Model - No Warranty in US. Compatible with Most GSM Carriers like T-Mobile, AT&T, MetroPCS, etc. Will NOT work with CDMA Carriers Such as Verizon, Sprint, Boost\n" +
                            "RAM 3GB , ROM 32GB Internal Memory ; MicroSD (Up to 512GB), Android 9.0 (Pie), Qualcomm SDM450 Snapdragon 450 (14 nm), Octa-core 1.8 GHz Cortex-A53, Adreno 506",
                    user, category, (i+1)*200, (i+1)*300, productStatusActive, true, false, "/uploads/"+user.getId()+"/"+ picNo+".jpg");
            picNo++;
            em.persist(product);
        }
        //Coffee Machine
        for (int i = 0; i < 5; i++) {
            Category category = categoryList.get(6);
            User user = vendorList.get(6);
            Product product = new Product("Coffee Machine "+i+1,  "Classy Mini Single Serve Espresso",
                    "Works with Lavazza Blue capsules\n" +
                            "2 Coffee selections: simple touch controls, 1 programmable free dose and 1 pre-set.\n" +
                            "Visual alerts: fitted with a visual indicator to show when the water tank is empty or the capsules drawer is full.",
                    user, category, (i+1)*20, (i+1)*40, productStatusActive, true, false, "/uploads/"+user.getId()+"/"+ picNo+".jpg");
            picNo++;
            em.persist(product);
        }

        //TV
        for (int i = 0; i < 5; i++) {
            Category category = categoryList.get(7);
            User user = vendorList.get(7);
            Product product = new Product("TV "+i+1,  "Classy Mini Single Serve Espresso",
                    "Insignia HD Smart TV – Fire TV Edition delivers 720p picture quality with deep blacks and rich colors.\n" +
                            "With the Fire TV experience built-in, enjoy tens of thousands of channels, apps, and Alexa skills, including Disney+, Netflix, YouTube, Prime Video, Hulu, SHOWTIME, STARZ, and more",
                    user, category, (i+1)*20, (i+1)*40, productStatusActive, true, false, "/uploads/"+user.getId()+"/"+ picNo+".jpg");
            picNo++;
            em.persist(product);
        }

//        for (int i = 1; i < 21; i++) {
//            Category category = categoryList.get(i%5);
//            User user = vendorList.get(i%5);
//            Product product = new Product("Product "+i,  "Product description "+i,"Product description roduct description roduct description roduct description roduct description roduct description roduct description roduct description roduct description "+i, user, category, i*20, i*40, productStatusActive, true, false, "");
//            em.persist(product);
//        }




        TypedQuery<User> q = em.createQuery("from User where roleId=3", User.class);
        List<User> users = q.getResultList();

        TypedQuery<Product> q2 = em.createQuery("from Product  ", Product.class);
        List<Product> products = q2.getResultList();

        int recCount = 10;

        for (User user: users)
        {

            for (int k = 0; k < 7; k++) {


                Order order = new Order(user, new Date(), 0, 20);
                int total = 0;
                int c1 = new Random().nextInt(4) + 1;
                for (int i = 0; i < c1; i++) {
                    Product p = products.get(new Random().nextInt(products.size()));
                    int qt = new Random().nextInt(3);
                    OrderItem item = new OrderItem(p, qt, p.getPrice() * qt);
                    order.addItem(item);
                    total += p.getPrice() * qt;
                }
                order.setTotal(total);
                em.persist(order);
            }

            int ct = new Random().nextInt(2)+1;
            String cardNo="54789632587412"+recCount;
            MerchantCard merchantCard = new MerchantCard(user.getName(),cardNo,123,"02/24",new CardType(ct,"sadsd"),100000);
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
