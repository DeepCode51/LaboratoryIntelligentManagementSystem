package top.yjzloveyzh.common.utils;

import java.util.List;

import top.yjzloveyzh.common.pojo.Permission;

public class PermissionUtil {

    public static boolean hasPermission(List<Permission> permissions, String permission) {

        for (Permission permissionItem : permissions) {

            if (permissionItem.getName().equals(permission)) {

                return true;
            }
        }

        return false;
    }
}
