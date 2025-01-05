package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.LibraryMemberDTO;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.LibraryMemberService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LibraryMemberServiceImpl implements LibraryMemberService {

    private static final String FILE_PATH = "src/main/resources/files/json/library-members.json";
    private final LibraryMemberRepository libraryMemberRepository;
    private Gson gson;
    private ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public LibraryMemberServiceImpl(LibraryMemberRepository libraryMemberRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.libraryMemberRepository = libraryMemberRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.libraryMemberRepository.count()>0;
    }

    @Override
    public String readLibraryMembersFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importLibraryMembers() throws IOException {
        StringBuilder sb = new StringBuilder();
        LibraryMemberDTO[] libraryMemberDTOS = this.gson.fromJson(readLibraryMembersFileContent(), LibraryMemberDTO[].class);
        for (LibraryMemberDTO libraryMemberDTO : libraryMemberDTOS) {
            if(this.libraryMemberRepository.findByPhoneNumber(libraryMemberDTO.getPhoneNumber()).isPresent() ||
                    !this.validationUtil.isValid(libraryMemberDTO) || libraryMemberDTO.getFirstName() ==null) {
                sb.append("Invalid library member").append(System.lineSeparator());
                continue;
            }
           LibraryMember libraryMember= this.modelMapper.map(libraryMemberDTO, LibraryMember.class);
            this.libraryMemberRepository.saveAndFlush(libraryMember);
            sb.append(String.format("Successfully imported library member %s - %s",
                 libraryMember.getFirstName(), libraryMember.getLastName())).append(System.lineSeparator());
        }
        return sb.toString();

    }
}
