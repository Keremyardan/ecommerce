package com.ecommerce.core.config.modelMapper;

import org.modelmapper.ModelMapper;

public interface IModelMapperService {
    ModelMapper forRequest();

    ModelMapper forResponse();
}
