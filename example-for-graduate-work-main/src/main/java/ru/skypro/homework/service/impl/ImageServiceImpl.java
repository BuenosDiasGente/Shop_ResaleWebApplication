package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    public Image saveToDb(MultipartFile multipartFile) throws IOException {
        Image image = new Image();
        image.setImage(multipartFile.getBytes());
        return imageRepository.save(image);
    }

    @Override
    public byte[] getById(int id) {
        return null;
    }
}
