package org.example.apppdpiqbackend.service.specialty;

import lombok.RequiredArgsConstructor;
import org.example.apppdpiqbackend.entity.Attachment;
import org.example.apppdpiqbackend.entity.Specialty;
import org.example.apppdpiqbackend.exception.MyConflictException;
import org.example.apppdpiqbackend.exception.MyNotFoundException;
import org.example.apppdpiqbackend.mapper.AttachmentMapper;
import org.example.apppdpiqbackend.mapper.SpecialMapper;
import org.example.apppdpiqbackend.payload.AddSpecialtyDto;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.SpecialtyDto;
import org.example.apppdpiqbackend.repository.AttachmentRepository;
import org.example.apppdpiqbackend.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpecialtyService {
    private final TransactionTemplate transactionTemplate;
    private final AttachmentRepository attachmentRepository;
    private final SpecialityRepository specialityRepository;
    private final AttachmentMapper attachmentMapper;
    private final SpecialMapper specialMapper;

    @Value("${upload.directory}")
    private String baseDirectory;


    public ApiResponse<List<SpecialtyDto>> all() {
        List<Specialty> specialties = specialityRepository.findAll();
        List<SpecialtyDto> specialtyDtos = new ArrayList<>();
        for (Specialty specialty : specialties) {
            specialtyDtos.add(specialMapper.toDto(specialty,
                    attachmentMapper.toDto(specialty.getPhoto())));
        }
        return ApiResponse.success(specialtyDtos);
    }

    public ApiResponse<SpecialtyDto> one(UUID id) {
        Specialty specialty = findSpecialityById(id);
        return ApiResponse.success(specialMapper.toDto(specialty,
                attachmentMapper.toDto(specialty.getPhoto())));
    }

    public ApiResponse<?> delete(UUID id) {
        findSpecialityById(id);
        specialityRepository.deleteById(id);
        return ApiResponse.success("Ok o'chirildi");
    }

    //agar natogri multipart file jo'naysa bazaga specialtyni saqlamaslik kerak va aksincha speciltyda xatolik yuzaga kelsa multipart fileni bazaga saqlamaslik kerak
    @Transactional(rollbackFor = {MyNotFoundException.class, MyConflictException.class, MultipartException.class})
    public ApiResponse<SpecialtyDto> add(AddSpecialtyDto specialtyDto,
                                         MultipartFile multipartFile) throws IOException {
        checkSpecialityByName(specialtyDto);
        Attachment attachment = getAttachmentFromMultipartFile(multipartFile);
        attachmentRepository.save(attachment);
        Specialty savedSpecialty = specialityRepository.save(Specialty.builder()
                .name(specialtyDto.getName())
                .photo(attachment)
                .description(specialtyDto.getDescription())
                .build());

        return ApiResponse.success(specialMapper.toDto(savedSpecialty,
                attachmentMapper.toDto(savedSpecialty.getPhoto())));
    }

    private void checkSpecialityByName(AddSpecialtyDto specialtyDto) {
        if (specialityRepository.findByName(specialtyDto.getName()).isPresent()) {
            throw new MyConflictException("Bunday nomli item borku");
        }
    }

    //agar natogri multipart file jo'naysa bazaga specialtyni saqlamaslik kerak va aksincha speciltyda xatolik yuzaga kelsa multipart fileni bazaga saqlamaslik kerak
    @Transactional(rollbackFor = {MyNotFoundException.class, MyConflictException.class, MultipartException.class})
    public ApiResponse<SpecialtyDto> edit(UUID id,
                                          AddSpecialtyDto specialtyDto,
                                          MultipartFile file) throws IOException {
        Specialty speciality = findSpecialityById(id);
        checkSpecialityByName(specialtyDto);
        Attachment attachment = getAttachmentFromMultipartFile(file);
        speciality.setPhoto(attachment);
        speciality.setName(specialtyDto.getName());
        speciality.setDescription(specialtyDto.getDescription());
        return ApiResponse.success(specialMapper.toDto(specialityRepository.save(speciality),
                attachmentMapper.toDto(attachmentRepository.save(attachment))));
    }

    private Attachment getAttachmentFromMultipartFile(MultipartFile file) throws IOException {
        File savedFile = createContentUrl(file).toFile();
        return Attachment.builder()
                .size(file.getSize())
                .contentType(file.getContentType())
                .originalName(file.getOriginalFilename())
                .contentUrl(savedFile.getAbsolutePath())
                .build();

    }

    private Path createContentUrl(MultipartFile file) throws IOException {
        Path directoryPath = Paths.get(getPath());
        Path filepath = directoryPath.resolve(UUID.randomUUID() + ".jpg");
        Files.copy(file.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);
        return filepath;
    }

    private String getPath() throws IOException {
        String year = String.valueOf(java.time.Year.now().getValue());
        String month = String.format("%02d", java.time.MonthDay.now().getMonthValue());
        String day = String.format("%02d", java.time.LocalDate.now().getDayOfMonth());
        String fullPath = Paths.get(baseDirectory, year, month, day).toString();
        Path directoryPath = Paths.get(fullPath);
        Files.createDirectories(directoryPath);
        return directoryPath.toString();
    }

    private Specialty findSpecialityById(UUID id) {
        return specialityRepository.findById(id).orElseThrow(() ->
                new MyNotFoundException("Bu Specialty topilmadi"));
    }

}
