package ru.skypro.homework.service;

import com.sun.istack.Interned;
import ru.skypro.homework.dto.ExtendedAdDTO;

public interface AdService {
    ExtendedAdDTO getExtendedAdByAdId(Long id);

}
