package edu.miu.ebuy.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class Context {

    public static JPAUserDetails getUser()
    {
       return (JPAUserDetails) SecurityContextHolder
               .getContext()
               .getAuthentication()
               .getPrincipal();
    }

    public static int getUserId()
    {
        return getUser().getId();
    }
    public static String getUserIdAsString()
    {
        return Integer.toString(getUser().getId());
    }
    public static String getUserName()
    {
       return getUser().getUsername();
    }
    public static boolean isAuthenticated()
    {
        //ToDo:Check another way
       return !SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser");
    }
}
