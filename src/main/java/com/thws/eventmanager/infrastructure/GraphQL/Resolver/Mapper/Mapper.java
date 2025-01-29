package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper;

import com.thws.eventmanager.domain.models.Model;
import com.thws.eventmanager.infrastructure.GraphQL.Models.GQLModel;

public abstract class Mapper<M extends Model, GM extends GQLModel> {
    public abstract GM mapToGQL(M model);
    public abstract M mapToModel(GM gqlModel);

}
