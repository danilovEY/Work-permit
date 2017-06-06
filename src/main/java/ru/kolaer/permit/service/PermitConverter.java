package ru.kolaer.permit.service;

/**
 * Created by danilovey on 06.06.2017.
 */
public interface PermitConverter<T> {
    T convertPermit(Integer idPermit);
}
