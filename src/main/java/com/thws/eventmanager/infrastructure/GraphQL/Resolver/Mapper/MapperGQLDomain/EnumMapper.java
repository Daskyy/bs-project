package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain;

import com.thws.eventmanager.domain.models.Permission;
import com.thws.eventmanager.domain.models.Status;
import com.thws.eventmanager.infrastructure.GraphQL.Models.PermissionGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Models.StatusGQL;

public class EnumMapper {
    //diese Klasse Mappt PermisseGQL<->Permission und StatusGQL<->Status

//    //Permission
//    public Permission toModel(PermissionGQL permissionGQL){
//        if(permissionGQL == null) return null;
//        return Permission.valueOf(permissionGQL.name());
//    }
//    public PermissionGQL toModelGQL(Permission permission){
//        if(permission == null) return null;
//        return PermissionGQL.valueOf(permission.name());
//    }

    //Status
    public Status toModel(StatusGQL statusGQL){
        if(statusGQL == null) return null;
        return Status.valueOf(statusGQL.name());
    }
    public StatusGQL toModelGQL(Status status){
        if(status == null) return null;
        return StatusGQL.valueOf(status.name());
    }


}
