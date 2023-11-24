package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.mapper.AdSMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;

import javax.validation.Valid;
import java.io.IOException;
/*

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

   */
/* private final AdRepository adRepository;
    private final AdSMapper adSMapper;
    private final UsersRepository usersRepository;


    @Override
    public ExtendedAdDTO getExtendedAdByAdId(Long id) {
        Ad ad = adRepository.findAdById(id);
        return adSMapper.addEntityToExtendedAdDTO(ad,ad.getUserId());
    }

    public AdDTO addAd(CreateOrUpdateAdDTO ad, MultipartFile image, Authentication authentication )throws IOException{

        Users user = usersRepository.findUserByUserName(authentication.getName());
        Ad adN = adSMapper.addAdFromCreateOrUpdateAdDTO(ad);
        //adN.setAdImage(image.);



        return adSMapper.toAdDTO(adN);




}


*//*

   */
/* @Override


        Ad ad = adMapper.toAdEntity(properties);
        ad.setImage(imageService.upload(image));
        ad.setAuthor(user);
        adRepository.save(ad);
        log.debug("Created ad " + ad);

        return adMapper.toAdDto(ad);
    }*//*





}
*/
