package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.AdSMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static jdk.dynalink.linker.support.Guards.isNull;


@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final AdSMapper adSMapper;
    private final UserRepository usersRepository;
    private final ImageRepository imageRepository;

    @Override
    public AdsDTO getAllAds() {
        List<AdDTO> list = adSMapper.adDTOToList(adRepository.findAll());
        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setCount(list.size());
        adsDTO.setResults(list);
        return adsDTO;
    }

    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO ad,
                       MultipartFile image,
                       Authentication authentication) throws IOException {

        User user = usersRepository.findUserByUserName(authentication.getName())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Ad adN = adSMapper.createOrUpdateAdDTOToEntity(ad);

        Image picture = new Image();
        picture.setImage(image.getBytes());
        picture = imageRepository.save(picture);

        adN.setImage(picture);
        adN.setAuthor(user);

        Ad savedAd = adRepository.save(adN);


        AdDTO adDTO = adSMapper.entityToAdDTO(savedAd);

        return adDTO;
    }

    @Override
    public ExtendedAdDTO getAds(Integer id) {
        return null;
    }

    @Override
    public void removeAd(Integer id) {


    }

    @Override
    public AdDTO updateAds(Integer id, CreateOrUpdateAdDTO ad, Authentication authentication) {
        return null;
    }

    @Override
    public AdsDTO getAdsMe(Authentication authentication) {
        return null;
    }

    @Override
    public String updateImage(Integer id, MultipartFile image, Authentication authentication) throws IOException {
        return null;
    }
}

/*
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
