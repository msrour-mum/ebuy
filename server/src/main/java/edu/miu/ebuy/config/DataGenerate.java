package mum.itweet.config;

import mum.itweet.model.*;
import mum.itweet.model.lookups.Gender;
import mum.itweet.model.lookups.PostStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DataGenerate {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("edu");

    public static void Generate() throws IOException {
        //SpringApplication.run(ItweetApplication.class, args);

        BCryptPasswordEncoder passwordUtil=new BCryptPasswordEncoder();
        String pass=passwordUtil.encode("sa");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Role roleAdmin = new Role(1, "Admin");
        Role roleContentManager = new Role(2, "ContentManager");
        Role roleMarketManager = new Role(3, "MarketManager");
        Role roleUser = new Role(4, "RegularUser");

        User userAdmin1 = new User("Admin", "admin@mum.com", true, roleAdmin, pass, true, new Date(), 1, "1232", "Admin User");
        User userAdmin2 = new User("Mahmoud Srour", "msrour@mum.edu", true, roleAdmin, pass, true, new Date(), 1, "1232", "Admin User");
        User userAdmin3 = new User("Moustafa Zein", "mzein@mum.com", true, roleContentManager, pass, true, new Date(), 1, "1232", "Admin User");
        User userAdmin4 = new User("Mo Salah", "mosalah@mum.com", true, roleMarketManager, pass, true, new Date(), 1, "1232", "Admin User");
        User userAdmin5 = new User("Ibrahim Samier", "adminIbrahim@mum.com", true, roleAdmin, pass, true, new Date(), 1, "1232", "Admin User");


        User user1 = new User("Moustafa Zein", "user1@mum.com", false, roleUser, pass, true, new Date(), 1, "1232", "User 1");
        User user2 = new User("Mahmoud Srour", "user2@mum.com", false, roleUser, pass, true, new Date(), 1, "1232", "User 2");
        User user3 = new User("Mo Salah", "user3@mum.com", false, roleUser, pass, true, new Date(), 1, "1232", "User 3");
        User user4 = new User("Ibrahim Samer", "user4@mum.com", false, roleUser, pass, true, new Date(), 1, "1232", "User 4");
        User user5 = new User("Mobark Salem", "user5@mum.com", false, roleUser, pass, true, new Date(), 1, "1232", "User 5");
        User user6 = new User("Yasser Kmal", "user6@mum.com", false, roleUser, pass, true, new Date(), 1, "1232", "User 6");
        User user7 = new User("Said Said", "user7@mum.com", false, roleUser, pass, true, new Date(), 1, "1232", "User 7");

        em.persist(roleAdmin);
        em.persist(roleContentManager);
        em.persist(roleMarketManager);
        em.persist(roleUser);

        em.persist(userAdmin1);
        em.persist(userAdmin2);
        em.persist(userAdmin3);
        em.persist(userAdmin4);
        em.persist(userAdmin5);


        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
        em.persist(user4);
        em.persist(user5);
        em.persist(user6);
        em.persist(user7);


        UnhealthyKey unhealthyKey1=new UnhealthyKey(1,"Bad");
        UnhealthyKey unhealthyKey2=new UnhealthyKey(2,"shut");
        UnhealthyKey unhealthyKey3=new UnhealthyKey(3,"bitch");
        em.persist(unhealthyKey1);
        em.persist(unhealthyKey2);
        em.persist(unhealthyKey3);



        for (int i = 8; i < 15; i++) {
            User userTemp = new User("Name : " + i, "user" + i + "@mum.com", false, roleUser, pass, true,new Date(), 1, "1232", "Bio " + i);
            em.persist(userTemp);
        }


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
