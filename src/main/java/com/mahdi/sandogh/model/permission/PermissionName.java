package com.mahdi.sandogh.model.permission;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by: mahdi
 * DateTime: ۲۰/۰۸/۲۰۲۰ - 20:09:32
 * Email: mahdip.devsc@gmail.com
 * http://github.com/mahdihp & http://gitlab.com/mahdihp
 */

public class PermissionName {

    public static final String ROLE_ADMIN_ACCESS_ALL = "Role_Admin_Access_All";
    public static final String ROLE_ADMIN_ACCESS_ = "Role_Admin_Access_All";


    public static final String API_ACCESS_TO_UPDATE_ONE = "ACCESS_TO_UPDATE_ONE";
    public static final String API_ACCESS_TO_SEARCH = "ACCESS_TO_SEARCH";
    public static final String API_ACCESS_TO_DETECT_CHANGES = "ACCESS_TO_DETECT_CHANGES";
    public static final String API_ACCESS_TO_DELETE = "ACCESS_TO_DELETE";
    public static final String API_ACCESS_TO_ADD = "ACCESS_TO_ADD";
    public static final String ACCESS_TO_USER_API = "ACCESS_TO_USER_API";
    public static final String ACCESS_TO_ROLE_API = "ACCESS_TO_ROLE_API";

    public static Set<String> getAllFeatureNames() {
        Set<String> result = new HashSet<String>();
        result.add(ROLE_ADMIN_ACCESS_ALL);
        result.add(API_ACCESS_TO_UPDATE_ONE);
        result.add(API_ACCESS_TO_SEARCH);
        result.add(API_ACCESS_TO_DETECT_CHANGES);
        result.add(API_ACCESS_TO_DELETE);
        result.add(API_ACCESS_TO_ADD);
        result.add(ACCESS_TO_USER_API);
        result.add(ACCESS_TO_ROLE_API);

        return result;

    }

}
