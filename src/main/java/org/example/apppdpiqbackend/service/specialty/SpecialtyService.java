package org.example.apppdpiqbackend.service.specialty;

import lombok.RequiredArgsConstructor;
import org.example.apppdpiqbackend.entity.Attachment;
import org.example.apppdpiqbackend.entity.Specialty;
import org.example.apppdpiqbackend.exception.MyNotFoundException;
import org.example.apppdpiqbackend.mapper.AttachmentMapper;
import org.example.apppdpiqbackend.mapper.SpecialMapper;
import org.example.apppdpiqbackend.payload.AddSpecialtyDto;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.SpecialtyDto;
import org.example.apppdpiqbackend.repository.AttachmentRepository;
import org.example.apppdpiqbackend.repository.SpecialityRepository;
import org.example.apppdpiqbackend.util.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpecialtyService {
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
        specialityRepository.deleteById(id);
        return ApiResponse.success("Ok o'chirildi");
    }

    public ApiResponse<SpecialtyDto> add(AddSpecialtyDto specialtyDto,
                                         MultipartFile multipartFile) throws IOException {
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

    public ApiResponse<SpecialtyDto> edit(UUID id,
                                          AddSpecialtyDto specialtyDto,
                                          MultipartFile file) throws IOException {
        Specialty speciality = findSpecialityById(id);
        Attachment attachment = getAttachmentFromMultipartFile(file);
        speciality.setPhoto(attachment);
        speciality.setName(specialtyDto.getName());
        speciality.setDescription(specialtyDto.getDescription());
        return ApiResponse.success(specialMapper.toDto(specialityRepository.save(speciality),
                attachmentMapper.toDto(attachmentRepository.save(attachment))));
    }

    private Attachment getAttachmentFromMultipartFile(MultipartFile file) throws IOException {
        File savedFile = createContentUrl(file);
        return Attachment.builder()
                .size(file.getSize())
                .contentType(file.getContentType())
                .originalName(file.getOriginalFilename())
                .contentUrl(savedFile.getAbsolutePath())
                .build();

    }

    private File createContentUrl(MultipartFile file) throws IOException {
        LocalDate currentDate = LocalDate.now();
        String year = DateUtils.formatDate(currentDate, "yyyy");
        String month = DateUtils.formatDate(currentDate, "MM");
        String day = DateUtils.formatDate(currentDate, "dd");

        Path directories = getPath(year, month, day);

        String fileName = UUID.randomUUID() + ".jpg";
        String directoryPath = baseDirectory + directories + File.separator + fileName;
        File destinationFile = new File(directoryPath);
        file.transferTo(destinationFile);
        return destinationFile;
    }

    private Path getPath(String year, String month, String day) throws IOException {
        Path yearDirectory = FileSystems.getDefault().getPath(baseDirectory, year);
        Files.createDirectories(yearDirectory);

        Path monthDirectory = FileSystems.getDefault().getPath(yearDirectory.toString(), month);
        Files.createDirectories(monthDirectory);

        Path dayDirectory = FileSystems.getDefault().getPath(monthDirectory.toString(), day);
        return Files.createDirectories(dayDirectory);
    }

    private Specialty findSpecialityById(UUID id) {
        return specialityRepository.findById(id).orElseThrow(() ->
                new MyNotFoundException("Bu Specialty topilmadi"));
    }
}
