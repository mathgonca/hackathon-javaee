package com.stefanini.service.util;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

public interface IGenericService<T, I extends Serializable> {
    void salver(@Valid T entity);

    T atualizar(@Valid T entity);

    void remover(@Valid I id);

    List<T> getList();

    T encontrar(I id);
}
