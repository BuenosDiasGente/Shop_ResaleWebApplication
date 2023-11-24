package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.model.Ad;
//import ru.skypro.homework.model.Image;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdSMapper {

   /* UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

    @Mapping(target = "adImage", source = "ad.adImage")
    ExtendedAdDTO addEntityToExtendedAdDTO(Ad ad, Users users);*/
/*


    @Mapping(target = "header", source = "adHeader")
    @Mapping(target = "description", source = "adDescription")
    Ad addAdFromCreateOrUpdateAdDTO(CreateOrUpdateAdDTO createOrUpdateAdDTO);

    @Mapping(target = "image", source = "ad.imageId")
    @Mapping(target = "userId", source = "ad.userId")
    AdDTO toAdDTO(Ad ad);
*/



}
